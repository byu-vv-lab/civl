package edu.udel.cis.vsl.civl.library.bundle;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.library.civlc.LibcivlcEvaluator;
import edu.udel.cis.vsl.civl.library.civlc.LibcivlcEvaluator.CIVLOperation;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLSyntaxException;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLBundleType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.object.SymbolicObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType.SymbolicTypeKind;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicUnionType;

public class LibbundleExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	/* ************************** Instance fields ************************** */

	private LibcivlcEvaluator libevaluator;

	/* **************************** Constructors *************************** */

	public LibbundleExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig) {
		super(name, primaryExecutor, modelFactory, symbolicUtil, civlConfig);
		this.libevaluator = new LibcivlcEvaluator(name, evaluator,
				modelFactory, symbolicUtil);
	}

	/* ******************** Methods from LibraryExecutor ******************* */

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, statement);
	}

	/* ************************** Private Methods ************************** */

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
		case "$bundle_pack":
			state = executeBundlePack(state, pid, process,
					(CIVLBundleType) call.function().returnType(), lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$bundle_size":
			state = executeBundleSize(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$bundle_unpack":
			state = executeBundleUnpack(state, pid, process, arguments,
					argumentValues, call.getSource());
			break;
		case "$bundle_unpack_apply":
			state = executeBundleUnpackApply(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		}
		state = stateFactory.setLocation(state, pid, call.target());
		return state;
	}

	/**
	 * Returns the size of a bundle.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param lhs
	 *            The left hand side expression of the call, which is to be
	 *            assigned with the returned value of the function call. If NULL
	 *            then no assignment happens.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param civlSource
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBundleSize(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource civlSource)
			throws UnsatisfiablePathConditionException {
		SymbolicObject arrayObject;
		SymbolicExpression array;
		NumericExpression size;

		assert arguments.length == 1;
		assert argumentValues[0].operator() == SymbolicOperator.UNION_INJECT;
		arrayObject = argumentValues[0].argument(1);
		assert arrayObject instanceof SymbolicExpression;
		array = (SymbolicExpression) arrayObject;
		size = symbolicUtil.sizeof(civlSource, array.type());
		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, size);
		return state;
	}

	/**
	 * Creates a bundle from the memory region specified by ptr and size,
	 * copying the data into the new bundle:
	 * 
	 * $bundle $bundle_pack(void *ptr, int size);
	 * 
	 * Copies the data out of the bundle into the region specified:
	 * 
	 * void $bundle_unpack($bundle bundle, void *ptr, int size);
	 * 
	 * Post-Condition: The data in bundle is in the form of an unrolled one
	 * dimensional array.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param bundleType
	 *            The bundle type of the model.
	 * @param lhs
	 *            The left hand side expression of the call, which is to be
	 *            assigned with the returned value of the function call. If NULL
	 *            then no assignment happens.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBundlePack(State state, int pid, String process,
			CIVLBundleType bundleType, LHSExpression lhs,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		SymbolicExpression pointer = argumentValues[0];
		NumericExpression size = (NumericExpression) argumentValues[1];
		NumericExpression count = null; // count = size / sizeof(datatype)
		SymbolicType elementType;
		SymbolicUnionType symbolicBundleType;
		SymbolicExpression array;
		SymbolicExpression bundle = null;
		int index;
		IntObject indexObj;
		NumericExpression arrayIndex = universe.zeroInt();
		NumericExpression arrayLength; // unrolled array length
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		Evaluation eval;

		if (pointer.type().typeKind() != SymbolicTypeKind.TUPLE) {
			throw new CIVLUnimplementedFeatureException(
					"string literals in message passing function calls,",
					source);
		}
		// check if size is zero
		if (size.isZero()) {
			// if size is 0 then just ignore the pointer. The pointer could be
			// NULL, or even invalid. The result is still a bundle of size 0.
			symbolicBundleType = bundleType.getDynamicType(universe);
			index = 0;
			indexObj = universe.intObject(0);
			elementType = bundleType.getElementType(index);
			array = universe.emptyArray(elementType);
			bundle = universe.unionInject(symbolicBundleType, indexObj, array);
		} else if (!size.isZero()
				&& symbolicUtil.getDyscopeId(source, pointer) == -1
				&& symbolicUtil.getVariableId(source, pointer) == -1) {
			throw new CIVLSyntaxException(
					"Packing a NULL message with size larger than 0", source);
		} else {
			if (ref.isArrayElementReference()) {
				SymbolicExpression parent = symbolicUtil.parentPointer(source,
						pointer);

				arrayIndex = universe.integer(symbolicUtil.getArrayIndex(
						source, pointer));
				eval = evaluator.dereference(source, state, process, parent,
						false);
				state = eval.state;
				array = eval.value;
			} else {
				eval = evaluator.dereference(source, state, process, pointer,
						false);
				state = eval.state;
				array = eval.value;
			}
			array = symbolicUtil.arrayUnrolling(state, process, array, source);
			arrayLength = universe.length(array);
			elementType = universe.arrayRead(array, zero).type();
			count = universe.divide(size,
					symbolicUtil.sizeof(arguments[1].getSource(), elementType));
			arrayIndex = libevaluator.getIndexInUnrolledArray(state, process,
					pointer, arrayLength, source);
			array = symbolicUtil.getSubArray(array, arrayIndex,
					universe.add(arrayIndex, count), state, process, source);
			symbolicBundleType = bundleType.getDynamicType(universe);
			index = bundleType.getIndexOf(elementType);
			indexObj = universe.intObject(index);
			bundle = universe.unionInject(symbolicBundleType, indexObj, array);
		}
		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, bundle);
		return state;
	}

	/**
	 * Copies the data out of the bundle into the region specified:
	 * 
	 * void $bundle_unpack($bundle bundle, void *ptr, int size); <br>
	 * 
	 * Pre-Condition : The data in bundle is in the form of an unrolled one
	 * dimensional array.<br>
	 * 
	 * @see{executeBunldePack :post-condition.<br>
	 * 
	 * 
	 * @author Ziqing Luo
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBundleUnpack(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		SymbolicExpression bundle = argumentValues[0];
		SymbolicExpression pointer = argumentValues[1];
		SymbolicExpression targetObject = null;
		SymbolicExpression arrayPointer = null;
		NumericExpression arrayIdx = universe.zeroInt();
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		Evaluation eval;

		if (ref.isArrayElementReference()) {
			arrayPointer = symbolicUtil.parentPointer(source, pointer);
		}
		try {
			// TODO: this method being called should not throw a SARLException:
			eval = libevaluator.bundleUnpack(state, process, bundle, pointer,
					source);
			state = eval.state;
			targetObject = eval.value;
		} catch (SARLException e) {
			// TODO: FIX arrayIdx!!
			throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
					Certainty.PROVEABLE, process,
					"Attempt to write beyond array bound: index=" + arrayIdx,
					symbolicUtil.stateToString(state), source);
		} catch (Exception e) {
			throw new CIVLInternalException("Cannot complete unpack", source);
		}
		// If it's assigned to an array or an object
		if (arrayPointer != null && targetObject != null)
			state = primaryExecutor.assign(source, state, process,
					arrayPointer, targetObject);
		else if (targetObject != null)
			state = primaryExecutor.assign(source, state, process, pointer,
					targetObject);
		return state;
	}

	/**
	 * bundle unpack then do an operation. This method corresponding to the
	 * CIVL-C function:
	 * <code>$bundle_unpack_apply($bundle bundle, void * buf, int count, $operation op);</code>
	 * Bundle contains the first operand which is going to be used in the
	 * operation. The pointer "buf" points to the object stores the second
	 * operand which is going to be used in the operation.
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param pid
	 *            The pid of the process
	 * @param process
	 *            The identifier of the process
	 * @param arguments
	 *            The expression of arguments of the CIVL-C function
	 *            <code>$bundle_unpack_apply($bundle bundle, void * buf, int count, $operation op);</code>
	 * @param argumentValues
	 *            The symbolic expression of arguments of the CIVL-C function
	 *            <code>$bundle_unpack_apply($bundle bundle, void * buf, int count, $operation op);</code>
	 * @param source
	 *            The civl source of this statement
	 * @return the state after execution.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBundleUnpackApply(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression bundle = argumentValues[0];
		SymbolicExpression pointer = argumentValues[1];
		SymbolicExpression bufPointer = null;
		// otherData: The data inside the object pointed by "buf".
		SymbolicExpression secOperand = null;
		// data: the data inside the bundle.
		SymbolicExpression data = null;
		SymbolicExpression dataElement = null;
		SymbolicExpression secOperandElement = null;
		SymbolicExpression opRet = null; // result after applying one operation
		//
		// the final array will be assigned to the pointer "buf". Since the
		// assigned pointer could be the parent pointer of the given one, so
		// there may have some cells stay unchanged in the array. That's the
		// reason we need this variable: writeBackArray.
		// SymbolicExpression writeBackArray = null;
		NumericExpression i;
		NumericExpression bufIndex;
		NumericExpression count = (NumericExpression) argumentValues[2];
		NumericExpression operation = (NumericExpression) argumentValues[3];
		BooleanExpression pathCondition = state.getPathCondition();
		BooleanExpression claim;
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		Reasoner reasoner = universe.reasoner(pathCondition);
		CIVLOperation CIVL_Op;
		Evaluation eval;

		// In executor, operation must be concrete.
		// ------Translate operation
		CIVL_Op = CIVLOperation.values()[((IntegerNumber) reasoner
				.extractNumber(operation)).intValue()];
		// ------Obtain otherData
		if (ref.isArrayElementReference()) {
			bufPointer = symbolicUtil.parentPointer(source, pointer);
			// bufIndex = symbolicUtil.getArrayIndex(source, pointer);
			eval = evaluator.dereference(source, state, process, bufPointer,
					false);
			state = eval.state;
			secOperand = eval.value;
		} else {
			bufPointer = pointer;
			eval = evaluator.dereference(source, state, process, bufPointer,
					false);
			state = eval.state;
			secOperand = eval.value;
		}
		// ------checking if secOperand is null
		if (secOperand.isNull() || secOperand == null) {
			eval = libevaluator.bundleUnpack(state, process, bundle, pointer,
					source);
			state = eval.state;
			data = eval.value;
			return primaryExecutor.assign(source, state, process, bufPointer,
					data);
		}
		secOperand = symbolicUtil.arrayUnrolling(state, process, secOperand,
				arguments[1].getSource());
		bufIndex = libevaluator.getIndexInUnrolledArray(state, process,
				pointer, universe.length(secOperand), arguments[1].getSource());
		// ------Obtain data form bundle
		data = (SymbolicExpression) bundle.argument(1);
		// ------checking if data is null
		if (data.isNull() || data == null)
			return state;

		// ------execute operation
		i = universe.zeroInt();
		claim = universe.lessThan(i, count);
		try {
			while (reasoner.isValid(claim)) {
				dataElement = universe.arrayRead(data, i);
				secOperandElement = universe.arrayRead(secOperand, bufIndex);
				opRet = libevaluator.civlOperation(state, process, dataElement,
						secOperandElement, CIVL_Op, source);
				secOperand = universe.arrayWrite(secOperand, bufIndex, opRet);
				// update
				i = universe.add(i, one);
				bufIndex = universe.add(bufIndex, one);
				claim = universe.lessThan(i, count);
			}
		} catch (SARLException e) {
			throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
					Certainty.PROVEABLE, process,
					"Attempt to write beyond array bound: index=" + i,
					symbolicUtil.stateToString(state), source);
		}

		secOperand = symbolicUtil.arrayCasting(state, process, secOperand,
				pointer.type(), arguments[1].getSource());
		state = primaryExecutor.assign(source, state, process, bufPointer,
				secOperand);
		return state;
	}

}
