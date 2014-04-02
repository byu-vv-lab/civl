package edu.udel.cis.vsl.civl.library.stdio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLSyntaxException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.library.CommonLibraryExecutor;
import edu.udel.cis.vsl.civl.library.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructOrUnionType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicCompleteArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicFunctionType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

/**
 * Executor for stdio function calls. Some methods may be used elsewhere so this
 * executor may be loaded even if the user program has not included stdio. (See
 * "Other Public Methods".)
 * 
 * 
 * <pre>
 * $fopen
 * $fprintf
 * $stdout
 * </pre>
 * 
 * Different header files can be introduced for different purposes:
 * 
 * plain C program
 * 
 * translate C to CIVL: add creation of filesystem, all functions use that
 * argument.
 * 
 * or just get the variable named filesystem in the header file.
 * 
 * 
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibstdioExecutor extends CommonLibraryExecutor implements
		LibraryExecutor {

	/** The SARL character type. */
	private SymbolicType charType;

	/**
	 * The SARL character 0, i.e., '\0' or '\u0000', used as the
	 * "null character constant" in C.
	 */
	private SymbolicExpression nullCharExpr;

	/**
	 * The CIVL handle type "$filesystem". This is a pointer type with base type
	 * a struct type.
	 */
	private CIVLPointerType filesystemType;

	/**
	 * The base type of the pointer type $filesystem; a structure type with
	 * fields (0) scope, and (1) files.
	 */
	private CIVLStructOrUnionType filesystemStructType;

	/**
	 * The CIVL struct type $file, defined in stdio.
	 */
	private CIVLStructOrUnionType fileType;

	/**
	 * The symbolic type corresponding to fileType.
	 */
	private SymbolicTupleType fileSymbolicType;

	/**
	 * The CIVL type FILE, defined in stdio.
	 */
	private CIVLStructOrUnionType FILEtype;

	private SymbolicType FILESymbolicType;

	private IntObject zeroObj, oneObj, twoObj, threeObj, fourObj, fiveObj,
			sixObj;

	private NumericExpression zeroInt, oneInt;

	/**
	 * The symbolic type array-of-char (char[]).
	 */
	private SymbolicArrayType stringSymbolicType;

	/**
	 * Empty file contents: array of string of length 0.
	 */
	private SymbolicExpression emptyContents;

	private SymbolicConstant initialContentsFunction;

	/* **************************** Constructors *************************** */

	/**
	 * Create a new instance of library executor for "stdio.h".
	 * 
	 * @param primaryExecutor
	 *            The main executor of the system.
	 * @param output
	 *            The output stream for printing.
	 * @param enablePrintf
	 *            True iff print is enabled, reflecting command line options.
	 */
	public LibstdioExecutor(Executor primaryExecutor, PrintStream output,
			boolean enablePrintf, ModelFactory modelFactory) {
		super(primaryExecutor, output, enablePrintf, modelFactory);

		charType = universe.characterType();
		nullCharExpr = universe.canonic(universe.character('\u0000'));
		zeroObj = (IntObject) universe.canonic(universe.intObject(0));
		oneObj = (IntObject) universe.canonic(universe.intObject(1));
		zeroInt = universe.zeroInt();
		oneInt = universe.oneInt();
		stringSymbolicType = (SymbolicArrayType) universe.canonic(universe
				.arrayType(universe.characterType()));
		emptyContents = universe.canonic(universe
				.emptyArray(stringSymbolicType));

		SymbolicFunctionType stringToStringArray = universe.functionType(
				Arrays.asList(stringSymbolicType),
				universe.arrayType(stringSymbolicType));

		initialContentsFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("contents"), universe
						.functionType(Arrays.asList(stringSymbolicType),
								stringSymbolicType)));
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Given a symbolic expression of type array of char, returns a string
	 * representation. If it is a concrete array of char consisting of concrete
	 * characters, this will be the obvious string. Otherwise the result is
	 * something readable but unspecified.
	 */
	private String getString(SymbolicExpression stringExpr) {
		return null;
	}

	/**
	 * Given a pointer to char, returns the symbolic expression of type array of
	 * char which is the string pointed to.
	 * 
	 * The method will succeed if any of the following holds: (1) the pointer
	 * points to element 0 of an array of char. In that case, it is just assumed
	 * that the string is the whole array. (2) the pointer points to element i
	 * of an array of char, where i is a concrete positive integer and the array
	 * length is also concrete. In that case, the elements of the array are
	 * scanned starting from position i until the first null charcter is
	 * reached, or the end of the array is reached, and the string is construted
	 * from those scanned characters (including the null character). In other
	 * situations, this method may fail, in which case it throws an exception.
	 * 
	 * @param state
	 *            the state in which this evaluation is taking place
	 * @param source
	 *            the source information used to report errors
	 * @param charPointer
	 *            a symbolic expression which is a pointer to a char
	 * @throws CIVLUnimplementedFeatureException
	 *             if it is not possible to extract the string expression.
	 * @return the symbolic expression which is an array of type char
	 *         representing the string pointed to
	 * @throws UnsatisfiablePathConditionException
	 *             of something goes wrong evaluating the string
	 */
	private Evaluation getStringExpression(State state, CIVLSource source,
			SymbolicExpression charPointer)
			throws UnsatisfiablePathConditionException {
		BooleanExpression pc = state.getPathCondition();
		Reasoner reasoner = universe.reasoner(pc);
		ReferenceExpression symRef = evaluator.getSymRef(charPointer);

		if (symRef.isArrayElementReference()) {
			ArrayElementReference arrayEltRef = (ArrayElementReference) symRef;
			SymbolicExpression arrayReference = evaluator.parentPointer(source,
					charPointer);
			NumericExpression indexExpr = arrayEltRef.getIndex();
			Evaluation eval = evaluator.dereference(source, state,
					arrayReference);
			int index;

			if (indexExpr.isZero())
				index = 0;
			else {
				IntegerNumber indexNum = (IntegerNumber) reasoner
						.extractNumber(indexExpr);

				if (indexNum == null)
					throw new CIVLUnimplementedFeatureException(
							"non-concrete symbolic index into string", source);
				index = indexNum.intValue();
			}
			if (index == 0)
				return eval;
			else if (index > 0) {
				SymbolicExpression arrayValue = eval.value;
				SymbolicArrayType arrayType = (SymbolicArrayType) arrayValue
						.type();
				LinkedList<SymbolicExpression> charExprList = new LinkedList<>();
				int length;

				if (arrayType.isComplete()) {
					NumericExpression extent = ((SymbolicCompleteArrayType) arrayType)
							.extent();
					IntegerNumber extentNum = (IntegerNumber) reasoner
							.extractNumber(extent);

					if (extentNum == null)
						throw new CIVLUnimplementedFeatureException(
								"pointer into string of non-concrete length",
								source);
					length = extentNum.intValue();
				} else
					throw new CIVLUnimplementedFeatureException(
							"pointer into string of unknown length", source);
				for (int i = index; i < length; i++) {
					SymbolicExpression charExpr = universe.arrayRead(
							arrayValue, universe.integer(i));

					charExprList.add(charExpr);
					// if you wanted to get heavy-weight, call the prover to see
					// if charExpr equals the null character instead of this:
					if (nullCharExpr.equals(charExpr))
						break;
				}
				eval.value = universe.array(charType, charExprList);
				return eval;
			} else
				throw new CIVLInternalException("negative pointer index: "
						+ index, source);
		}
		throw new CIVLUnimplementedFeatureException(
				"pointer to char is not into an array of char", source);
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
		return universe.array(
				stringSymbolicType,
				Arrays.asList(universe.apply(initialContentsFunction,
						Arrays.asList(filename))));
	}

	/**
	 * <pre>
	 * FILE * $fopen($filesystem fs, const char * restrict filename,
	 *               const char * restrict mode);
	 * </pre>
	 * 
	 */
	private State execute_fopen(State state, int pid, Expression[] expressions,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression filesystemPointer = argumentValues[0];
		Evaluation eval = evaluator.dereference(expressions[0].getSource(),
				state, filesystemPointer);
		SymbolicExpression fileSystemStructure;
		SymbolicExpression fileArray;
		SymbolicExpression scope;
		SymbolicExpression filename;
		SymbolicSequence<?> fileSequence;
		int numFiles;
		int fileIndex;
		String mode;
		NumericExpression isInput, isOutput, isBinary, isWide = zeroInt;
		SymbolicExpression contents;
		SymbolicExpression theFile;

		state = eval.state;
		fileSystemStructure = eval.value;
		scope = universe.tupleRead(fileSystemStructure, zeroObj);
		fileArray = universe.tupleRead(fileSystemStructure, oneObj);
		eval = getStringExpression(state, expressions[1].getSource(),
				argumentValues[1]);
		state = eval.state;
		filename = eval.value;

		mode = getString(argumentValues[2]);

		// does a file by that name already exist in the filesystem?
		// assume all are concrete.

		if (fileArray.operator() != SymbolicOperator.CONCRETE)
			throw new CIVLUnimplementedFeatureException(
					"non-concrete file system", expressions[0]);
		fileSequence = (SymbolicSequence<?>) fileArray.argument(0);
		numFiles = fileSequence.size();
		for (fileIndex = 0; fileIndex < numFiles; fileIndex++) {
			SymbolicExpression tmpFile = fileSequence.get(fileIndex);
			SymbolicExpression tmpFilename = universe.tupleRead(tmpFile,
					zeroObj);

			if (tmpFilename.equals(filename)) {
				theFile = tmpFile;
				break;
			}
		}
		if (fileIndex == numFiles) {
			// file not found: create it.
			switch (mode) {
			case "r":
				// assume file exists with unconstrained contents
				isInput = oneInt;
				isOutput = zeroInt;
				isBinary = zeroInt;
				contents = initialContents(filename);
				break;
			case "w":
			case "wx":
				// assume file does not yet exist
				isInput = zeroInt;
				isOutput = oneInt;
				isBinary = zeroInt;
				contents = emptyContents;
				break;
			case "a":
			case "r+":
				// assume file exists
				isInput = oneInt;
				isOutput = oneInt;
				isBinary = zeroInt;
				contents = initialContents(filename);
			default:
				throw new CIVLUnimplementedFeatureException("FILE mode " + mode);
			}
			theFile = universe.tuple(fileSymbolicType, Arrays.asList(filename,
					contents, isOutput, isInput, isBinary, isWide));
			fileArray = universe.append(fileArray, theFile);
			fileSystemStructure = universe.tupleWrite(fileSystemStructure,
					oneObj, fileArray);
			state = primaryExecutor.assign(expressions[1].getSource(), state,
					filesystemPointer, fileSystemStructure);
		}
		// now theFile is the new file
		// malloc a new FILE object with appropriate pointers
		
		{
			// $file *file; // the actual file to which this refers
			// $filesystem fs; // file system to which this FILE is associated
			// int pos1; // the chunk index (first index) in the contents
			// int pos2; // the character index (second index) in the contents
			// int mode; // Stream mode: r/w/a
			// int isOpen; // is this FILE open (0 or 1)?
			NumericExpression pos1, pos2;
			// create enum type for mode.
		}
		
		
		
		return null;

	}

	/**
	 * Execute a function call statement for a certain process at a given state.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The Id of the process that the call statement belongs to.
	 * @param statement
	 *            The call statement to be executed.
	 * @return The new state after executing the call statement.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeWork(State state, int pid,
			CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		int numArgs;

		statement = (CallOrSpawnStatement) statement;
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
		case "printf":
			state = executePrintf(state, pid, arguments, argumentValues);
			state = stateFactory.setLocation(state, pid, statement.target());
			break;
		default:
			throw new CIVLUnimplementedFeatureException(name.name(), statement);

		}
		return state;
	}

	/* ************************ Methods from Library *********************** */

	@Override
	public String name() {
		return "stdio";
	}

	/* ******************** Methods from LibraryExecutor ******************* */

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, (CallOrSpawnStatement) statement);
	}

	@Override
	public State initialize(State state) {
		// create abstract functions
		// create stdout, stdin
		// need to find variable called CIVL_files, but which process looks?
		// can make all these functions take an extra argument ($filesystem).
		// like malloc, can have two versions
		// or know which process is invoking and look up from there
		return state;
	}

	@Override
	public State wrapUp(State state) {
		return state;
	}

	/* ************************ Other Public Methods *********************** */

	/**
	 * Execute <code>printf()</code> function. See C11 Sec. 7.21.6.1 and
	 * 7.21.6.3. Prototype:
	 * 
	 * <pre>
	 * int printf(const char * restrict format, ...);
	 * </pre>
	 * 
	 * Escape characters can be supported; the following have been tested:
	 * <code>\n</code>, <code>\r</code>, <code>\b</code>, <code>\t</code>,
	 * <code>\"</code>, <code>\'</code>, and <code>\\</code>. Some (but not all)
	 * format specifiers can be supported and the following have been tested:
	 * <code>%d</code>, <code>%o</code>, <code>%x</code>, <code>%f</code>,
	 * <code>%e</code>, <code>%g</code>, <code>%a</code>, <code>%c</code>,
	 * <code>%p</code>, and <code>%s</code>.
	 * 
	 * TODO CIVL currently dosen't support 'printf("%c" , c)'(where c is a char
	 * type variable)?
	 * 
	 * 
	 * @param state
	 * @param pid
	 * @param argumentValues
	 * @return State
	 * @throws UnsatisfiablePathConditionException
	 */
	public State executePrintf(State state, int pid, Expression[] expressions,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		if (this.enablePrintf) {
			// TODO: use StringBuffer instead for performance
			String stringOfSymbolicExpression = "";
			String format = "";
			ArrayList<String> arguments = new ArrayList<String>();
			CIVLSource source = state.getProcessState(pid).getLocation()
					.getSource();

			// don't assume argumentValues[0] is a pointer to an element of an
			// array. Check it. If it is not, through an exception.
			SymbolicExpression arrayPointer = evaluator.parentPointer(source,
					argumentValues[0]);
			Evaluation eval = evaluator
					.dereference(source, state, arrayPointer);

			if (eval.value.operator() != SymbolicOperator.CONCRETE)
				throw new CIVLUnimplementedFeatureException(
						"non-concrete format strings",
						expressions[0].getSource());

			SymbolicSequence<?> originalArray = (SymbolicSequence<?>) eval.value
					.argument(0);

			state = eval.state;

			int numChars = originalArray.size();
			char[] formatChars = new char[numChars];

			for (int i = 0; i < originalArray.size(); i++) {
				SymbolicExpression charExpr = originalArray.get(i);
				Character theChar = universe.extractCharacter(charExpr);

				if (theChar == null)
					throw new CIVLUnimplementedFeatureException(
							"non-concrete character in format string at position "
									+ i, expressions[0].getSource());

				formatChars[i] = theChar;
			}
			format = new String(formatChars);
			for (int i = 1; i < argumentValues.length; i++) {
				SymbolicExpression argument = argumentValues[i];
				CIVLType argumentType = expressions[i].getExpressionType();

				if (argumentType instanceof CIVLPointerType
						&& ((CIVLPointerType) argumentType).baseType()
								.isCharType()
						&& argument.operator() == SymbolicOperator.CONCRETE) {
					// also check format code is %s before doing this!
					arrayPointer = evaluator.parentPointer(source, argument);

					// index is not necessarily 0! FIX ME!

					eval = evaluator.dereference(source, state, arrayPointer);
					originalArray = (SymbolicSequence<?>) eval.value
							.argument(0);
					state = eval.state;
					stringOfSymbolicExpression = "";

					// fix this way of making the string:

					for (int j = 0; j < originalArray.size(); j++) {
						stringOfSymbolicExpression += originalArray.get(j)
								.toString().charAt(1);
					}
					arguments.add(stringOfSymbolicExpression);
				} else
					arguments.add(argument.toString());
			}

			// TODO: print pointers in a much nicer way

			// TODO: at model building time, check statically that the
			// expression types are compatible with corresponding conversion
			// specifiers
			format = format.replaceAll("%lf", "%s");
			format = format.replaceAll("%[0-9]*[.]?[0-9]*[dfoxegacp]", "%s");
			for (int i = 0; i < format.length(); i++) {
				if (format.charAt(i) == '%') {
					if (format.charAt(i + 1) != 's')
						throw new CIVLSyntaxException("The format:%"
								+ format.charAt(i + 1)
								+ " is not allowed in printf",
								expressions[0].getSource());
				}
			}
			try {
				output.printf(format, arguments.toArray());
			} catch (Exception e) {
				throw new CIVLInternalException("unexpected error in printf",
						expressions[0].getSource());
			}
		}
		return state;
	}

}
