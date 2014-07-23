package edu.udel.cis.vsl.civl.library.seq;

import java.math.BigInteger;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericSymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicCompleteArrayType;

public class LibseqExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	public LibseqExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig) {
		super(name, primaryExecutor, modelFactory, symbolicUtil, civlConfig);
	}

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, statement);
	}

	/**
	 * Executes a system function call, updating the left hand side expression
	 * with the returned value if any.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param call
	 *            The function call statement to be executed.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeWork(State state, int pid, CallOrSpawnStatement call)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		LHSExpression lhs;
		int numArgs;
		String process = state.getProcessState(pid).name() + "(id=" + pid + ")";

		numArgs = call.arguments().size();
		name = call.function().name();
		lhs = call.lhs();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = call.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "$seq_init":
			state = executeSeqInit(state, pid, process, arguments,
					argumentValues, call.getSource());
			break;
		case "$seq_insert":
			state = executeSeqInsert(state, pid, process, arguments,
					argumentValues, call.getSource());
			break;
		case "$seq_length":
			state = executeSeqLength(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		}
		state = stateFactory.setLocation(state, pid, call.target());
		return state;
	}

	private State executeSeqInit(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		SymbolicExpression arrayPtr = argumentValues[0];
		NumericExpression count = (NumericExpression) argumentValues[1];
		SymbolicExpression elePointer = argumentValues[2];
		CIVLSource arrayPtrSource = arguments[0].getSource();
		CIVLSource elePtrSource = arguments[2].getSource();

		if (symbolicUtil.isNullPointer(arrayPtr)
				|| symbolicUtil.isNullPointer(elePointer)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.DEREFERENCE, Certainty.PROVEABLE, process,
					"Both the first and the third argument of $seq_init() "
							+ "must be non-null pointers.\n"
							+ "actual value of first argument: "
							+ symbolicUtil.symbolicExpressionToString(
									arrayPtrSource, state, arrayPtr)
							+ "\n"
							+ "actual value of third argument: "
							+ symbolicUtil.symbolicExpressionToString(
									elePtrSource, state, elePointer),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		} else {
			CIVLType arrayType = symbolicUtil.typeOfObjByPointer(
					arrayPtrSource, state, arrayPtr);

			if (!arrayType.isIncompleteArrayType()) {
				String arrayPtrString = symbolicUtil
						.symbolicExpressionToString(arrayPtrSource, state,
								arrayPtr);
				CIVLExecutionException err = new CIVLExecutionException(
						ErrorKind.SEQUENCE, Certainty.PROVEABLE, process,
						"The first argument of $seq_init() must be "
								+ "a pointer to an incomplete array.\n"
								+ "actual first argument: " + arrayPtrString
								+ "\n" + "actual type of " + arrayPtrString
								+ ": pointer to " + arrayType,
						symbolicUtil.stateToString(state), source);

				this.errorLogger.reportError(err);
				return state;
			} else {
				CIVLType eleType = symbolicUtil.typeOfObjByPointer(
						elePtrSource, state, elePointer);
				CIVLType arrayEleType = ((CIVLArrayType) arrayType)
						.elementType();

				if (!arrayEleType.equals(eleType)) {
					CIVLExecutionException err = new CIVLExecutionException(
							ErrorKind.DEREFERENCE,
							Certainty.PROVEABLE,
							process,
							"The element type of the array that the first argument "
									+ "points to of $seq_init() must be the same as "
									+ "the type of the object that the third argument points to.\n"
									+ "actual element type of the given array: "
									+ arrayEleType
									+ "\n"
									+ "actual type of object pointed to by the third argument: "
									+ eleType,
							symbolicUtil.stateToString(state), source);

					this.errorLogger.reportError(err);
					return state;
				} else {
					Evaluation eval = evaluator.dereference(elePtrSource,
							state, process, elePointer, false);
					SymbolicExpression eleValue, arrayValue;
					SymbolicCompleteArrayType arrayValueType;
					NumericSymbolicConstant index;
					SymbolicExpression arrayEleFunction;

					state = eval.state;
					eleValue = eval.value;
					arrayValueType = universe.arrayType(eleValue.type(), count);
					index = (NumericSymbolicConstant) universe
							.symbolicConstant(universe.stringObject("i"),
									universe.integerType());
					arrayEleFunction = universe.lambda(index, eleValue);
					arrayValue = universe.arrayLambda(arrayValueType,
							arrayEleFunction);
					state = primaryExecutor.assign(source, state, process,
							arrayPtr, arrayValue);
				}
			}
		}
		return state;
	}

	// assume count is concrete
	private State executeSeqInsert(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		SymbolicExpression arrayPtr = argumentValues[0];
		NumericExpression index = (NumericExpression) argumentValues[1];
		SymbolicExpression valuesPtr = argumentValues[2];
		NumericExpression count = (NumericExpression) argumentValues[3];
		CIVLSource arrayPtrSource = arguments[0].getSource();
		CIVLType arrayType;
		Evaluation eval;
		SymbolicExpression arrayValue;
		int countInt, indexInt;

		if (symbolicUtil.isNullPointer(arrayPtr)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.DEREFERENCE, Certainty.PROVEABLE, process,
					"The first argument of $seq_insert() "
							+ "must be a non-null pointer.\n"
							+ "actual value of first argument: "
							+ symbolicUtil.symbolicExpressionToString(
									arrayPtrSource, state, arrayPtr),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		if (count.isZero())// no op
			return state;
		arrayType = symbolicUtil.typeOfObjByPointer(arrayPtrSource, state,
				arrayPtr);
		if (!arrayType.isIncompleteArrayType()) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE,
					Certainty.PROVEABLE,
					process,
					"The first argument of $seq_insert() "
							+ "must be of a pointer to incomplete array of type T.\n"
							+ "actual value of first argument: pointer to "
							+ arrayType, symbolicUtil.stateToString(state),
					source);

			this.errorLogger.reportError(err);
			return state;
		}
		eval = evaluator.dereference(arrayPtrSource, state, process, arrayPtr,
				false);
		state = eval.state;
		arrayValue = eval.value;
		countInt = ((IntegerNumber) universe.extractNumber(count)).intValue();
		indexInt = ((IntegerNumber) universe.extractNumber(index)).intValue();
		for (int i = 0; i < countInt; i++) {
			SymbolicExpression value, valuePtr;
			BinaryExpression pointerAdd = modelFactory.binaryExpression(
					source,
					BINARY_OPERATOR.POINTER_ADD,
					arguments[2],
					modelFactory.integerLiteralExpression(source,
							BigInteger.valueOf(i)));

			eval = evaluator.pointerAdd(state, pid, process, pointerAdd,
					valuesPtr, universe.integer(i));
			state = eval.state;
			valuePtr = eval.value;
			eval = evaluator.dereference(source, state, process, valuePtr,
					false);
			state = eval.state;
			value = eval.value;
			arrayValue = universe.insertElementAt(arrayValue, indexInt + i,
					value);
		}
		state = primaryExecutor.assign(source, state, process, arrayPtr,
				arrayValue);
		return state;
	}

	private State executeSeqLength(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression seqPtr = argumentValues[0];
		CIVLSource seqSource = arguments[0].getSource();

		if (symbolicUtil.isNullPointer(seqPtr)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.DEREFERENCE, Certainty.PROVEABLE, process,
					"The argument of $seq_length() must be a non-null pointer.\n"
							+ "actual argument: "
							+ symbolicUtil.symbolicExpressionToString(
									seqSource, state, seqPtr),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		} else {
			Evaluation eval = evaluator.dereference(seqSource, state, process,
					seqPtr, false);
			SymbolicExpression seq;

			state = eval.state;
			seq = eval.value;
			if (!(seq.type() instanceof SymbolicArrayType)) {
				CIVLExecutionException err = new CIVLExecutionException(
						ErrorKind.SEQUENCE,
						Certainty.PROVEABLE,
						process,
						"The argument of $seq_length() must be a sequence of objects of the same type.\n"
								+ "actual argument: "
								+ symbolicUtil.symbolicExpressionToString(
										seqSource, state, seq),
						symbolicUtil.stateToString(state), source);

				this.errorLogger.reportError(err);
				return state;
			} else if (lhs != null)
				state = primaryExecutor.assign(state, pid, process, lhs,
						universe.length(seq));
		}
		return state;
	}

}
