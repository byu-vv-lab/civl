package edu.udel.cis.vsl.civl.library.common.stdio;

import java.util.Arrays;

import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.err.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.library.common.LibraryWorker;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

public class StdioLibraryWorker extends LibraryWorker {

	/**
	 * The symbolic type corresponding to fileType.
	 */
	private SymbolicTupleType fileSymbolicType;

	/**
	 * Abstract function for the initial content of a file. Different files
	 * should have different initial content.
	 */
	private SymbolicConstant initialContentsFunction;

	private SymbolicConstant fileLengthFunction;

	public StdioLibraryWorker(Evaluator evaluator, Executor executor,
			ModelFactory modelFactory) {
		super(executor, evaluator.universe());
		Model model = modelFactory.model();
		CIVLType fileType = model.fileType();
		SymbolicType stringSymbolicType;
		SymbolicType stringArrayType;

		stringSymbolicType = (SymbolicArrayType) universe.canonic(universe
				.arrayType(universe.characterType()));
		stringArrayType = (SymbolicArrayType) universe.canonic(universe
				.arrayType(stringSymbolicType));
		if (fileType != null)
			this.fileSymbolicType = (SymbolicTupleType) fileType
					.getDynamicType(universe);
		initialContentsFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("contents"), universe
						.functionType(Arrays.asList(stringSymbolicType),
								stringArrayType)));
		fileLengthFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("fileLength"), universe
						.functionType(Arrays.asList(stringSymbolicType),
								universe.integerType())));
	}

	Evaluation evaluateWork(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		int numArgs;
		CIVLSource source = statement.getSource();
		LHSExpression lhs = statement.lhs();

		numArgs = statement.arguments().size();
		name = statement.function().name();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = statement.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "$textFileLength":
			return evaluate_text_file_length(source, state, pid, lhs,
					arguments, argumentValues);
		default:
			throw new CIVLUnimplementedFeatureException(name.name(), statement);

		}
	}

	/**
	 * Returns the symbolic expression representing the initial contents of a
	 * file named filename. This is the array of length 1 whose sole element is
	 * the expression "initialContents(filename)", which is the application of
	 * the abstract function initialContentsFunction to the filename.
	 * 
	 * @param filename
	 *            symbolic expression of string type (i.e., an array of char)
	 * @return symbolic expression representing initial contents of that file
	 */
	private SymbolicExpression initialContents(SymbolicExpression filename) {
		return universe.apply(initialContentsFunction, Arrays.asList(filename));
	}

	private SymbolicExpression fileLength(SymbolicExpression filename) {
		return universe.apply(this.fileLengthFunction, Arrays.asList(filename));
	}

	private Evaluation evaluate_text_file_length(CIVLSource source,
			State state, int pid, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		Expression fileSystemExpression = modelFactory
				.civlFilesystemVariableExpression();
		Evaluation eval = evaluator.evaluate(state, pid, fileSystemExpression);
		SymbolicExpression filesystemPointer;
		SymbolicExpression fileSystemStructure;
		SymbolicExpression fileArray;
		SymbolicSequence<?> fileSequence;
		SymbolicExpression filename;
		int numFiles;
		int fileIndex;
		SymbolicExpression theFile = null;
		SymbolicExpression length;

		filesystemPointer = eval.value;
		state = eval.state;
		eval = evaluator.dereference(fileSystemExpression.getSource(), state,
				filesystemPointer);
		state = eval.state;
		fileSystemStructure = eval.value;
		fileArray = universe.tupleRead(fileSystemStructure, oneObject);
		eval = evaluator.getStringExpression(state, arguments[0].getSource(),
				argumentValues[0]);
		state = eval.state;
		filename = eval.value;

		// does a file by that name already exist in the filesystem?
		// assume all are concrete.
		if (fileArray.operator() != SymbolicOperator.CONCRETE)
			throw new CIVLUnimplementedFeatureException(
					"non-concrete file system", arguments[0]);
		fileSequence = (SymbolicSequence<?>) fileArray.argument(0);
		numFiles = fileSequence.size();
		for (fileIndex = 0; fileIndex < numFiles; fileIndex++) {
			SymbolicExpression tmpFile = fileSequence.get(fileIndex);
			SymbolicExpression tmpFilename = universe.tupleRead(tmpFile,
					zeroObject);

			if (tmpFilename.equals(filename)) {
				theFile = tmpFile;
				break;
			}
		}
		if (fileIndex == numFiles) {
			// file not found: create it.
			NumericExpression isInput = this.zero;
			NumericExpression isOutput = this.zero;
			NumericExpression isBinary = zero;
			SymbolicExpression contents = initialContents(filename);
			length = this.fileLength(filename);
			SymbolicExpression isWide = this.zero;

			theFile = universe.tuple(fileSymbolicType, Arrays.asList(filename,
					contents, isOutput, isInput, isBinary, isWide, length));
			fileArray = universe.append(fileArray, theFile);
			fileSystemStructure = universe.tupleWrite(fileSystemStructure,
					oneObject, fileArray);
			state = executor.assign(fileSystemExpression.getSource(), state,
					filesystemPointer, fileSystemStructure);
		} else {
			SymbolicExpression isBinary = universe.tupleRead(theFile,
					universe.intObject(4));

			if (!isBinary.equals(this.zero)) {
				throw new CIVLExecutionException(ErrorKind.OTHER,
						Certainty.CONCRETE, "The file "
								+ arguments[0].toString()
								+ " is not a text file.", source);
			}
			length = universe.tupleRead(theFile, universe.intObject(6));
		}
		// if (lhs != null) {
		//
		//
		// state = primaryExecutor.assign(state, pid, lhs, length);
		// }
		return new Evaluation(state, length);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
