package edu.udel.cis.vsl.civl.library.bundle;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryExecutor;
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
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType.SymbolicTypeKind;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicUnionType;

public class LibbundleExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	/* **************************** Constructors *************************** */

	public LibbundleExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig) {
		super(name, primaryExecutor, modelFactory, symbolicUtil, civlConfig);
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
			array = symbolicUtil.arrayFlatten(state, process, array, source);
			arrayLength = universe.length(array);
			elementType = universe.arrayRead(array, zero).type();
			count = universe.divide(size,
					symbolicUtil.sizeof(arguments[1].getSource(), elementType));
			arrayIndex = getIndexInUnrolledArray(state, process, pointer,
					arrayLength, source);
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
			eval = bundleUnpack(state, process, bundle, pointer, source);
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
			eval = bundleUnpack(state, process, bundle, pointer, source);
			state = eval.state;
			data = eval.value;
			return primaryExecutor.assign(source, state, process, bufPointer,
					data);
		}
		secOperand = symbolicUtil.arrayFlatten(state, process, secOperand,
				arguments[1].getSource());
		bufIndex = getIndexInUnrolledArray(state, process, pointer,
				universe.length(secOperand), arguments[1].getSource());
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
				opRet = civlOperation(state, process, dataElement,
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

	/**
	 * Unpacking the given bundle, assigning the data in bundle to the object
	 * pointed by the given pointer.
	 * 
	 * Pre-Condition : Data in bundle is in the form of a unrolled one
	 * dimensional array.
	 * 
	 * Implementation details: First, it's guaranteed that the data in bundle is
	 * always in the form of a one dimensional array(also can be understood as a
	 * unrolled array or a sequence of data).<br>
	 * Second, inside this function, it contains a cast from the one dimensional
	 * array mentioned above to another type specified by the parameter
	 * "pointer". A correct CIVL program or C program should make sure that cast
	 * is legal, otherwise an error will be reported.<br>
	 * Third, the object used to store the data in bundle can have a larger size
	 * than the data itself.
	 * 
	 * @link 
	 *       {edu.udel.cis.vsl.civl.dynamic.common.CommonSymbolicUtility.arrayCasting
	 *       }
	 * @link {edu.udel.cis.vsl.civl.dynamic.common.CommonSymbolicUtility.
	 *       arrayUnrolling}
	 * 
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The identifier of the process
	 * @param bundle
	 *            The bundle type object
	 * @param pointer
	 *            The pointer to the address of the object which will be
	 *            assigned by bundle data
	 * @param civlsource
	 *            The CIVL Source of the bundle_unpack statement
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private Evaluation bundleUnpack(State state, String process,
			SymbolicExpression bundle, SymbolicExpression pointer,
			CIVLSource civlsource) throws UnsatisfiablePathConditionException {
		if (!(bundle.type() instanceof SymbolicUnionType))
			throw new CIVLInternalException(
					"Bundle doesn't have a SymbolicUnionType", civlsource);
		SymbolicExpression data = (SymbolicExpression) bundle.argument(1);
		SymbolicExpression obj;
		SymbolicExpression array;
		NumericExpression dataSize;
		BooleanExpression claim;
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		SymbolicExpression unrolledDataArray = symbolicUtil.arrayFlatten(state,
				process, data, civlsource);
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		Evaluation eval = new Evaluation(state, null);

		dataSize = universe.length(unrolledDataArray);
		// ------If data size is zero, do nothing
		if (reasoner.isValid(universe.equals(dataSize, zero)))
			return eval;

		// ------If pointer is an array element reference, the array is pointed
		// by the parent pointer.
		if (ref.isArrayElementReference()) {
			SymbolicExpression parentPtr = symbolicUtil.parentPointer(
					civlsource, pointer);
			SymbolicExpression unrolledArray;
			NumericExpression indexInUnrolledArray, unrolledArraySize;

			eval = evaluator.dereference(civlsource, state, process, parentPtr,
					false);
			state = eval.state;
			array = eval.value;
			// ------Unrolling the array pointed by the parent pointer.
			// Note: Since data in bundle is guaranteed to be in the form of a
			// 1-d array, unrolling the receiver array can make things easier.
			unrolledArray = symbolicUtil.arrayFlatten(state, process, array,
					civlsource);
			unrolledArraySize = universe.length(unrolledArray);
			indexInUnrolledArray = getIndexInUnrolledArray(state, process,
					pointer, unrolledArraySize, civlsource);
			unrolledArray = this.oneDimenAssign(state, process, unrolledArray,
					indexInUnrolledArray, data, civlsource);
			eval.state = state;
			eval.value = symbolicUtil.arrayCasting(state, process,
					unrolledArray, array.type(), civlsource);
			return eval;

		} else {
			// ------If the pointer points to a memory space or an array.
			eval = evaluator.dereference(civlsource, state, process, pointer,
					false);
			state = eval.state;
			obj = eval.value;
			if (obj.type() instanceof SymbolicArrayType) {
				SymbolicExpression objArray = symbolicUtil.arrayFlatten(state,
						process, obj, civlsource);

				objArray = this.oneDimenAssign(state, process, objArray, zero,
						data, civlsource);
				eval.state = state;
				eval.value = symbolicUtil.arrayCasting(state, process,
						objArray, obj.type(), civlsource);
				return eval;
			} else {
				claim = universe.equals(dataSize, one);
				if (reasoner.isValid(claim)) {
					eval.state = state;
					eval.value = universe.arrayRead(data, zero);
					return eval;
				} else {
					throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
							Certainty.PROVEABLE, "Bundle Unpack", process,
							civlsource);
				}
			}
		}
	}

	/**
	 * Assigns a sequence of data (in the form of a one dimensional array, not
	 * the same "sequence" as a type of CIVL-C language) to an one dimensional
	 * array.
	 * 
	 * Pre-Condition: Parameters "array" and "data" are both one dimensional
	 * array.
	 * 
	 * Note: This function can be public if any other module want use it.
	 * 
	 * Implementation details: Since multiple dimensional arrays are represented
	 * in CIVL as nested one dimensional arrays, the one dimensional assignment
	 * is an operation only on the most outer array in those nested arrays.
	 * Note: So it's recommended to passing one dimensional arrays as parameters
	 * for "array" and "data".
	 * 
	 * e.g. For an array "a"<code>int a[2][2];</code> assigned by data
	 * <code>int b = [2][4];</code>, it finally will return a[2][4];
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The identifier of the process
	 * @param array
	 *            The array is going to be assigned by a set of data.
	 * @param data
	 *            The data is going to be assigned to an array
	 * @param civlsource
	 * @return The array after assignment.
	 */
	private SymbolicExpression oneDimenAssign(State state, String process,
			SymbolicExpression array, NumericExpression arrayIndex,
			SymbolicExpression data, CIVLSource civlsource) {
		NumericExpression arrayLength, dataLength, arrayFreeSpace;
		BooleanExpression claim;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		arrayLength = universe.length(array);
		dataLength = universe.length(data);
		if ((array.type() instanceof SymbolicArrayType)
				&& (data.type() instanceof SymbolicArrayType)) {
			arrayFreeSpace = universe.subtract(arrayLength, arrayIndex);

			claim = universe.and(universe.equals(zero, arrayIndex),
					universe.equals(arrayFreeSpace, dataLength));
			if (reasoner.isValid(claim)) {
				return data;
			} else {
				NumericExpression i = universe.zeroInt();

				claim = universe.lessThan(i, dataLength);
				while (reasoner.isValid(claim)) {
					SymbolicExpression element = universe.arrayRead(data, i);

					try {
						array = universe.arrayWrite(array, arrayIndex, element);
					} catch (SARLException e) {
						throw new SARLException("Array assignment out of bound");
					}
					// update
					i = universe.add(i, one);
					arrayIndex = universe.add(arrayIndex, one);
					claim = universe.lessThan(i, dataLength);
				}
				return array;
			}
		} else
			throw new CIVLInternalException("arguments: " + array + " and "
					+ data + "must be an one dimensional array.\n", civlsource);
	}

	/**
	 * Computes the new index for an array element reference after the
	 * referenced array being unrolled.
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The identifier of the process
	 * @param oriPointer
	 *            The pointer to the original array(before unrolling) or array
	 *            element.
	 * @param unrolledArraySize
	 *            The size of the unrolled array.
	 * @param civlsource
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private NumericExpression getIndexInUnrolledArray(State state,
			String process, SymbolicExpression oriPointer,
			NumericExpression unrolledArraySize, CIVLSource civlsource)
			throws UnsatisfiablePathConditionException {
		ReferenceExpression ref = symbolicUtil.getSymRef(oriPointer);

		if (ref.isArrayElementReference()) {
			SymbolicExpression parentPtr = symbolicUtil.parentPointer(
					civlsource, oriPointer);
			SymbolicExpression array = evaluator.dereference(civlsource, state,
					process, parentPtr, false).value;
			NumericExpression arrayLength = universe.length(array);
			NumericExpression chunkLength;
			NumericExpression newIndex;
			NumericExpression arrayIdx = universe.integer(symbolicUtil
					.getArrayIndex(civlsource, oriPointer));

			chunkLength = universe.divide(unrolledArraySize, arrayLength);
			newIndex = universe.multiply(arrayIdx, chunkLength);
			return newIndex;
		} else
			return zero;
	}

	/**
	 * Completing an operation (which is included in CIVLOperation enumerator).
	 * 
	 * @author Ziqing Luo
	 * @param arg0
	 *            The new data got from the bundle
	 * @param arg1
	 *            The data has already been received previously
	 * @param op
	 *            The CIVL Operation
	 * @return
	 */
	private SymbolicExpression civlOperation(State state, String process,
			SymbolicExpression arg0, SymbolicExpression arg1, CIVLOperation op,
			CIVLSource civlsource) {
		BooleanExpression claim;

		/*
		 * For MAX and MIN operation, if CIVL cannot figure out a concrete
		 * result, make a abstract function for it.
		 */
		try {
			switch (op) {
			// TODO: consider using heuristic to switch to abstract
			// functions if these expressions get too big (max,min):
			case CIVL_MAX:
				claim = universe.lessThan((NumericExpression) arg1,
						(NumericExpression) arg0);
				return universe.cond(claim, arg0, arg1);
			case CIVL_MIN:
				claim = universe.lessThan((NumericExpression) arg0,
						(NumericExpression) arg1);
				return universe.cond(claim, arg0, arg1);
			case CIVL_SUM:
				return universe.add((NumericExpression) arg0,
						(NumericExpression) arg1);
			case CIVL_PROD:
				return universe.multiply((NumericExpression) arg0,
						(NumericExpression) arg1);
			case CIVL_LAND:
				return universe.and((BooleanExpression) arg0,
						(BooleanExpression) arg1);
			case CIVL_LOR:
				return universe.or((BooleanExpression) arg0,
						(BooleanExpression) arg1);
			case CIVL_LXOR:
				BooleanExpression notNewData = universe
						.not((BooleanExpression) arg0);
				BooleanExpression notPrevData = universe
						.not((BooleanExpression) arg1);

				return universe.or(
						universe.and(notNewData, (BooleanExpression) arg1),
						universe.and((BooleanExpression) arg0, notPrevData));
			case CIVL_BAND:
			case CIVL_BOR:
			case CIVL_BXOR:
			case CIVL_MINLOC:
			case CIVL_MAXLOC:
			case CIVL_REPLACE:
			default:
				throw new CIVLUnimplementedFeatureException("CIVLOperation: "
						+ op.name());
			}
		} catch (ClassCastException e) {
			throw new CIVLExecutionException(ErrorKind.OTHER,
					Certainty.PROVEABLE, process,
					"Invalid operands type for CIVL Operation: " + op.name(),
					civlsource);
		}
	}
}
