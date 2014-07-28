package edu.udel.cis.vsl.civl.library.seq;

import java.math.BigInteger;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryExecutor;
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
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;

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
		case "$seq_remove":
			state = executeSeqRemove(state, pid, process, arguments,
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
					SymbolicExpression eleValue, arrayValue;
					Evaluation eval = evaluator.dereference(elePtrSource,
							state, process, elePointer, false);

					state = eval.state;
					eleValue = eval.value;
					arrayValue = symbolicUtil.newArray(
							state.getPathCondition(), count, eleValue);
					state = primaryExecutor.assign(source, state, process,
							arrayPtr, arrayValue);
				}
			}
		}
		return state;
	}

	/**
	 * <p>
	 * Given a pointer an object of type "incomplete-array-of-T", inserts count
	 * elements into the array starting at position index. The subsequence
	 * elements of the array are shifted up, and the final length of the array
	 * will be its original length plus count. The values to be inserted are
	 * taken from the region specified by parameters values.
	 * </p>
	 * 
	 * <p>
	 * Precondition: 0<=index<=length, where length is the length of the array
	 * in the pre-state. If index=length, this appends the elements to the end
	 * of the array. If index=0, this inserts the elements at the beginning of
	 * the array. If count=0, this is a no-op and values will not be evaluated
	 * (hence may be NULL).
	 * </p>
	 * 
	 * <p>
	 * Parameters: array: pointer-to-incomplete-array-of-T index: any integer
	 * type, 0<=index<=length values: pointer-to-T count: any integer type,
	 * count>=0
	 * </p>
	 * 
	 * @param state
	 *            The state where the function is called
	 * @param pid
	 *            The PID of the process that executes this function call
	 * @param process
	 *            The process information for error report
	 * @param arguments
	 *            The arguments of function call
	 * @param argumentValues
	 *            The values of the arguments of the function call
	 * @param source
	 *            The source information of the call statement for error report
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeSeqInsert(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		return executeSeqInsertOrRemove(state, pid, process, arguments,
				argumentValues, source, true);
	}

	/**
	 * 
	 * @param state
	 * @param pid
	 * @param process
	 * @param lhs
	 * @param arguments
	 * @param argumentValues
	 * @param source
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeSeqLength(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression seqPtr = argumentValues[0];
		CIVLSource seqSource = arguments[0].getSource();

		if (symbolicUtil.isNullPointer(seqPtr)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE, Certainty.PROVEABLE, process,
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
						ErrorKind.SEQUENCE, Certainty.PROVEABLE, process,
						"The argument of $seq_length() must be a sequence of "
								+ "objects of the same type.\n"
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

	private State executeSeqRemove(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		return executeSeqInsertOrRemove(state, pid, process, arguments,
				argumentValues, source, false);
	}

	private State executeSeqInsertOrRemove(State state, int pid,
			String process, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source,
			boolean isInsert) throws UnsatisfiablePathConditionException {
		SymbolicExpression arrayPtr = argumentValues[0];
		NumericExpression index = (NumericExpression) argumentValues[1];
		SymbolicExpression valuesPtr = argumentValues[2];
		NumericExpression count = (NumericExpression) argumentValues[3];
		CIVLSource arrayPtrSource = arguments[0].getSource(), valuesPtrSource = arguments[2]
				.getSource();
		CIVLType arrayType, arrayEleType, valueType;
		Evaluation eval;
		SymbolicExpression arrayValue;
		int countInt, indexInt, lengthInt;
		String functionName = isInsert ? "$seq_insert()" : "$seq_remove()";

		if (symbolicUtil.isNullPointer(arrayPtr)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.DEREFERENCE, Certainty.PROVEABLE, process,
					"The first argument of "
							+ functionName
							+ " must be a non-null pointer.\n"
							+ "actual value of first argument: "
							+ symbolicUtil.symbolicExpressionToString(
									arrayPtrSource, state, arrayPtr),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		if (count.isZero())// no op
			return state;
		if (isInsert && symbolicUtil.isNullPointer(valuesPtr)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.DEREFERENCE, Certainty.PROVEABLE, process,
					"The third argument of "
							+ functionName
							+ " must be a non-null pointer when the forth "
							+ "argument is greater than zero.\n"
							+ "actual value of third argument: "
							+ symbolicUtil.symbolicExpressionToString(
									valuesPtrSource, state, valuesPtr),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		arrayType = symbolicUtil.typeOfObjByPointer(arrayPtrSource, state,
				arrayPtr);
		if (!arrayType.isIncompleteArrayType()) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE,
					Certainty.PROVEABLE,
					process,
					"The first argument of "
							+ functionName
							+ " must be of a pointer to incomplete array of type T.\n"
							+ "actual type of the first argument: pointer to "
							+ arrayType, symbolicUtil.stateToString(state),
					source);

			this.errorLogger.reportError(err);
			return state;
		}
		arrayEleType = ((CIVLArrayType) arrayType).elementType();
		valueType = symbolicUtil.typeOfObjByPointer(valuesPtrSource, state,
				valuesPtr);
		if (!arrayEleType.equals(valueType)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE,
					Certainty.PROVEABLE,
					process,
					"The first argument of "
							+ functionName
							+ " must be a pointer to incomplete array of type T, and"
							+ " the third argument must be a pointer to type T. \n"
							+ "actual type of the first argument: pointer to "
							+ arrayEleType + "\n"
							+ "actual type of the third argument: pointer to "
							+ valueType, symbolicUtil.stateToString(state),
					source);

			this.errorLogger.reportError(err);
			return state;
		}
		eval = evaluator.dereference(arrayPtrSource, state, process, arrayPtr,
				false);
		state = eval.state;
		arrayValue = eval.value;
		if (arrayValue.operator() != SymbolicOperator.CONCRETE) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE,
					Certainty.PROVEABLE,
					process,
					"The first argument of "
							+ functionName
							+ "must be a pointer to a concrete array.\n"
							+ "actual value of the array pointed to by the first argument: "
							+ symbolicUtil.symbolicExpressionToString(
									arrayPtrSource, state, arrayValue),
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		countInt = ((IntegerNumber) universe.extractNumber(count)).intValue();
		indexInt = ((IntegerNumber) universe.extractNumber(index)).intValue();
		lengthInt = ((IntegerNumber) universe.extractNumber(universe
				.length(arrayValue))).intValue();

		if (isInsert && (indexInt < 0 || indexInt >= lengthInt)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE, Certainty.PROVEABLE, process,
					"The index for $seq_insert() is out of the range of the array index.\n"
							+ "array length: " + lengthInt + "\n" + "index: "
							+ indexInt, symbolicUtil.stateToString(state),
					source);

			this.errorLogger.reportError(err);
			return state;
		} else if (!isInsert && (countInt > lengthInt - indexInt)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.SEQUENCE, Certainty.PROVEABLE, process,
					"Insufficient data to be removed for $seq_remove().\n"
							+ "array length: " + lengthInt + "\n"
							+ "start index: " + indexInt + "\n"
							+ "number of elements to be removed: " + countInt,
					symbolicUtil.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		for (int i = 0; i < countInt; i++) {
			SymbolicExpression value, valuePtr;

			if (i == 0)
				valuePtr = valuesPtr;
			else {
				BinaryExpression pointerAdd = modelFactory.binaryExpression(
						source, BINARY_OPERATOR.POINTER_ADD, arguments[2],
						modelFactory.integerLiteralExpression(source,
								BigInteger.valueOf(i)));

				eval = evaluator.pointerAdd(state, pid, process, pointerAdd,
						valuesPtr, universe.integer(i));
				state = eval.state;
				valuePtr = eval.value;
			}
			if (isInsert) {
				eval = evaluator.dereference(source, state, process, valuePtr,
						false);
				state = eval.state;
				value = eval.value;
				arrayValue = universe.insertElementAt(arrayValue, indexInt + i,
						value);
			} else {
				value = universe.arrayRead(arrayValue, index);
				state = primaryExecutor.assign(valuesPtrSource, state, process,
						valuePtr, value);
				arrayValue = universe.removeElementAt(arrayValue, indexInt);
			}
		}
		state = primaryExecutor.assign(source, state, process, arrayPtr,
				arrayValue);
		return state;
	}
}