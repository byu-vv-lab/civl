package edu.udel.cis.vsl.civl.library.civlc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLSyntaxException;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLBundleType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLDomainType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.util.IF.Singleton;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericSymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression.ReferenceKind;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.object.StringObject;
import edu.udel.cis.vsl.sarl.IF.object.SymbolicObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicCompleteArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType.SymbolicTypeKind;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicUnionType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

/**
 * Implementation of the execution for system functions declared civlc.h.
 * 
 * @author siegel
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibcivlcExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	/* **************************** Constructors *************************** */

	/**
	 * Creates a new instance of the library executor for civlc.h.
	 * 
	 * @param primaryExecutor
	 *            The executor for normal CIVL execution.
	 * @param output
	 *            The output stream to be used in the enabler.
	 * @param enablePrintf
	 *            If printing is enabled for the printf function.
	 * @param modelFactory
	 *            The model factory of the system.
	 */
	public LibcivlcExecutor(String name, Executor primaryExecutor,
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
	 * Creates a bundle from the memory region specified by ptr and size,
	 * copying the data into the new bundle:
	 * 
	 * $bundle $bundle_pack(void *ptr, int size);
	 * 
	 * Copies the data out of the bundle into the region specified:
	 * 
	 * void $bundle_unpack($bundle bundle, void *ptr, int size);
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
		Expression pointerExpr = arguments[0];
		SymbolicExpression pointer = argumentValues[0];
		NumericExpression size = (NumericExpression) argumentValues[1];
		SymbolicType elementType;
		SymbolicType pureElementType;
		SymbolicUnionType symbolicBundleType;
		int index;
		IntObject indexObj;
		SymbolicExpression array;
		SymbolicExpression bundle = null;

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
			elementType = evaluator.referencedType(source, state, pointer);
			pureElementType = universe.pureType(elementType);
			symbolicBundleType = bundleType.getDynamicType(universe);
			index = bundleType.getIndexOf(pureElementType);
			indexObj = universe.intObject(index);
			array = getArrayFromPointer(state, process, pointerExpr, pointer,
					size, source);
			bundle = universe.unionInject(symbolicBundleType, indexObj, array);
		}
		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, bundle);
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
	 * Copies the data out of the bundle into the region specified:
	 * 
	 * void $bundle_unpack($bundle bundle, void *ptr, int size);
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
		SymbolicExpression array = null;
		SymbolicExpression arrayPointer = null;
		NumericExpression arrayIdx = universe.zeroInt();
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		Evaluation eval;

		// If the given pointer points to an array element or
		// allocated memory space, the target object is an array of objects of
		// the given type, so the final result should be assigned to the parent
		// pointer which points to the array instead of an element of the array.
		//
		// Else, the target object can only hold one element in bundle and the
		// target object should be assigned directly to the given pointer.
		if (ref.isArrayElementReference()) {
			arrayPointer = symbolicUtil.parentPointer(source, pointer);
			eval = evaluator.dereference(source, state, process, arrayPointer,
					false);
			state = eval.state;
			array = eval.value;
			arrayIdx = ((ArrayElementReference) ref).getIndex();
		}
		try {
			targetObject = symbolicUtil.bundleUnpack(bundle, array, arrayIdx,
					state.getPathCondition());
		} catch (SARLException e) {
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

		/* Older Version */
		// assert bundle.operator() == SymbolicOperator.UNION_INJECT;
		// if (zeroLengthValid == ResultType.YES) {
		// return state;
		// } else {
		// BooleanExpression oneLengthClaim = universe.equals(length, one);
		// ResultType oneLengthValid = reasoner.valid(oneLengthClaim)
		// .getResultType();
		//
		// if (oneLengthValid == ResultType.YES) {
		// SymbolicExpression element = universe.arrayRead(array, zero);
		//
		// state = primaryExecutor.assign(source, state, process, pointer,
		// element);
		// return state;
		// } else {
		// // if pointer is to element 0 of an array and lengths are equal,
		// // just assign the whole array
		// // else try to get concrete length, and iterate making
		// // assignment
		// if (kind == ReferenceKind.ARRAY_ELEMENT) {
		// NumericExpression pointerIndex = ((ArrayElementReference) symRef)
		// .getIndex();
		// SymbolicExpression parentPointer = symbolicUtil
		// .parentPointer(source, pointer);
		// Evaluation eval = evaluator.dereference(source, state,
		// process, parentPointer, false);
		// SymbolicExpression targetArray = eval.value;
		// BooleanExpression claim;
		//
		// state = eval.state;
		// pathCondition = state.getPathCondition();
		// claim = universe.and(
		// universe.equals(pointerIndex, zero),
		// universe.equals(length,
		// universe.length(targetArray)));
		// if (reasoner.isValid(claim)) {
		// state = primaryExecutor.assign(source, state, process,
		// parentPointer, array);
		//
		// return state;
		// } else {
		// IntegerNumber concreteLength = (IntegerNumber) reasoner
		// .extractNumber(length);
		//
		// if (concreteLength != null) {
		// int lengthInt = concreteLength.intValue();
		//
		// for (int i = 0; i < lengthInt; i++) {
		// NumericExpression sourceIndex = universe
		// .integer(i);
		// NumericExpression targetIndex = universe.add(
		// pointerIndex, sourceIndex);
		//
		// try {
		// SymbolicExpression element = universe
		// .arrayRead(array,
		// universe.integer(i));
		//
		// targetArray = universe.arrayWrite(
		// targetArray, targetIndex, element);
		// } catch (SARLException e) {
		// throw new CIVLExecutionException(
		// ErrorKind.OUT_OF_BOUNDS,
		// Certainty.CONCRETE, process,
		// "Attempt to write beyond array bound: index="
		// + targetIndex,
		// symbolicUtil.stateToString(state),
		// source);
		// }
		// }
		// state = primaryExecutor.assign(source, state,
		// process, parentPointer, targetArray);
		// return state;
		// }
		// }
		// }
		// }
		// }
		// throw new CIVLInternalException("Cannot complete unpack", source);
	}

	/**
	 * Creates a new global communicator object and returns a handle to it. The
	 * global communicator will have size communication places. The global
	 * communicator defines a communication "universe" and encompasses message
	 * buffers and all other components of the state associated to
	 * message-passing. The new object will be allocated in the given scope.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeGcommCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression gcomm;
		SymbolicExpression nprocs = argumentValues[1];
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression isInit;
		SymbolicExpression isInitArray;
		SymbolicExpression buf;
		SymbolicExpression bufRow;
		SymbolicExpression queueLength = universe.integer(0);
		SymbolicExpression emptyQueue;
		SymbolicExpression emptyMessages;
		List<SymbolicExpression> emptyMessagesComponents = new LinkedList<>();
		List<SymbolicExpression> isInitComponents = new LinkedList<>();
		List<SymbolicExpression> queueComponents = new LinkedList<>();
		List<SymbolicExpression> bufRowComponents = new LinkedList<>();
		List<SymbolicExpression> bufComponents = new LinkedList<>();
		List<SymbolicExpression> gcommComponents = new LinkedList<>();
		int int_nprocs;
		CIVLType queueType = model.queueType();
		CIVLType messageType = model.mesageType();
		CIVLType gcommType = model.gcommType();
		SymbolicType dynamicQueueType = queueType.getDynamicType(universe);
		SymbolicType dynamicMessageType = messageType.getDynamicType(universe);
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber number_nprocs = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) nprocs);

		isInit = universe.bool(false);
		emptyMessages = universe.array(dynamicMessageType,
				emptyMessagesComponents);
		queueComponents.add(queueLength);
		queueComponents.add(emptyMessages);
		assert dynamicQueueType instanceof SymbolicTupleType;
		emptyQueue = universe.tuple((SymbolicTupleType) dynamicQueueType,
				queueComponents);
		if (number_nprocs != null) {
			int_nprocs = number_nprocs.intValue();
			// isInit component
			for (int i = 0; i < int_nprocs; i++) {
				isInitComponents.add(isInit);
				bufRowComponents.add(emptyQueue);
			}
			isInitArray = universe.array(isInit.type(), isInitComponents);
			bufRow = universe.array(dynamicQueueType, bufRowComponents);
			for (int i = 0; i < int_nprocs; i++) {
				bufComponents.add(bufRow);
			}
			buf = universe.array(bufRow.type(), bufComponents);
		} else {
			SymbolicCompleteArrayType arrayType;
			NumericSymbolicConstant index;
			SymbolicExpression initFunction;
			SymbolicExpression bufRowFunction;
			SymbolicExpression bufFunction;
			SymbolicType integerType = modelFactory.integerType()
					.getDynamicType(universe);

			arrayType = universe.arrayType(isInit.type(),
					(NumericExpression) nprocs);
			index = (NumericSymbolicConstant) universe.symbolicConstant(
					universe.stringObject("i"), integerType);
			initFunction = universe.lambda(index, isInit);
			isInitArray = universe.arrayLambda(arrayType, initFunction);
			index = (NumericSymbolicConstant) universe.symbolicConstant(
					universe.stringObject("i"), integerType);
			bufRowFunction = universe.lambda(index, emptyQueue);
			arrayType = universe.arrayType(dynamicQueueType,
					(NumericExpression) nprocs);
			bufRow = universe.arrayLambda(arrayType, bufRowFunction);
			bufFunction = universe.lambda(index, bufRow);
			arrayType = universe.arrayType(bufRow.type(),
					(NumericExpression) nprocs);
			buf = universe.arrayLambda(arrayType, bufFunction);
		}
		gcommComponents.add(nprocs);
		gcommComponents.add(isInitArray);
		gcommComponents.add(buf);
		gcomm = universe.tuple(
				(SymbolicTupleType) gcommType.getDynamicType(universe),
				gcommComponents);
		state = primaryExecutor.malloc(source, state, pid, process, lhs,
				scopeExpression, scope, gcommType, gcomm);
		return state;
	}

	/**
	 * Creates a new global communicator object and returns a handle to it. The
	 * global communicator will have size communication places. The global
	 * communicator defines a communication "universe" and encompasses message
	 * buffers and all other components of the state associated to
	 * message-passing. The new object will be allocated in the given scope.
	 * 
	 * typedef struct __gbarrier__ { int nprocs; _Bool in_barrier[]; //
	 * initialized as all false. int num_in_barrier; // initialized as 0. } *
	 * $gbarrier;
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeGbarrierCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression gbarrierObj;
		SymbolicExpression nprocs = argumentValues[1];
		SymbolicExpression numInBarrier = universe.integer(0);
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression procMapArray;
		SymbolicExpression inBarrierArray;
		List<SymbolicExpression> gbarrierComponents = new LinkedList<>();
		int int_nprocs;
		CIVLType gbarrierType = model.gbarrierType();
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber number_nprocs = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) nprocs);

		if (number_nprocs != null) {
			LinkedList<SymbolicExpression> procMapComponents = new LinkedList<>();
			LinkedList<SymbolicExpression> inBarrierComponents = new LinkedList<>();

			int_nprocs = number_nprocs.intValue();
			for (int i = 0; i < int_nprocs; i++) {
				inBarrierComponents.add(universe.bool(false));
				procMapComponents.add(modelFactory.nullProcessValue());
			}
			inBarrierArray = universe.array(universe.booleanType(),
					inBarrierComponents);
			procMapArray = universe.array(modelFactory.processSymbolicType(),
					procMapComponents);
		} else {
			SymbolicCompleteArrayType arrayType;
			NumericSymbolicConstant index;
			SymbolicExpression procMapFunction;
			SymbolicExpression inBarrierFunction;

			arrayType = universe.arrayType(modelFactory.processSymbolicType(),
					(NumericExpression) nprocs);
			index = (NumericSymbolicConstant) universe.symbolicConstant(
					universe.stringObject("i"), universe.integerType());
			procMapFunction = universe.lambda(index,
					modelFactory.nullProcessValue());
			procMapArray = universe.arrayLambda(arrayType, procMapFunction);
			index = (NumericSymbolicConstant) universe.symbolicConstant(
					universe.stringObject("i"), universe.integerType());
			inBarrierFunction = universe.lambda(index, universe.bool(false));
			arrayType = universe.arrayType(universe.booleanType(),
					(NumericExpression) nprocs);
			inBarrierArray = universe.arrayLambda(arrayType, inBarrierFunction);
		}
		gbarrierComponents.add(nprocs);
		gbarrierComponents.add(procMapArray);
		gbarrierComponents.add(inBarrierArray);
		gbarrierComponents.add(numInBarrier);
		gbarrierObj = universe.tuple(
				(SymbolicTupleType) gbarrierType.getDynamicType(universe),
				gbarrierComponents);
		state = primaryExecutor.malloc(source, state, pid, process, lhs,
				scopeExpression, scope, gbarrierType, gbarrierObj);
		return state;
	}

	/**
	 * Creates a new local communicator object and returns a handle to it. The
	 * new communicator will be affiliated with the specified global
	 * communicator. This local communicator handle will be used as an argument
	 * in most message-passing functions. The place must be in [0,size-1] and
	 * specifies the place in the global communication universe that will be
	 * occupied by the local communicator. The local communicator handle may be
	 * used by more than one process, but all of those processes will be viewed
	 * as occupying the same place. Only one call to $comm_create may occur for
	 * each gcomm-place pair. The new object will be allocated in the given
	 * scope.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBarrierCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression gbarrier = argumentValues[1];
		SymbolicExpression place = argumentValues[2];
		SymbolicExpression gbarrierObj;
		SymbolicExpression barrierObj;
		SymbolicExpression procMapArray;
		LinkedList<SymbolicExpression> barrierComponents = new LinkedList<>();
		CIVLSource civlsource = arguments[1].getSource();
		CIVLType barrierType = model.barrierType();
		Evaluation eval;
		int place_num = ((IntegerNumber) universe
				.extractNumber((NumericExpression) place)).intValue();
		int totalPlaces;

		if (place_num < 0) {
			throw new CIVLExecutionException(ErrorKind.OTHER,
					Certainty.CONCRETE, process, "Invalid place " + place_num
							+ " used in $barrier_create().", source);
		}
		eval = this.evaluator.dereference(civlsource, state, process, gbarrier,
				false);
		state = eval.state;
		gbarrierObj = eval.value;
		if (!symbolicUtil.isHeapObjectDefined(gbarrierObj)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.MEMORY_LEAK, Certainty.PROVEABLE, process,
					"The gbarrier object of " + arguments[1] + " is undefined",
					source);

			this.errorLogger.reportError(err);
		}
		totalPlaces = ((IntegerNumber) universe
				.extractNumber((NumericExpression) universe.tupleRead(
						gbarrierObj, zeroObject))).intValue();
		if (place_num >= totalPlaces) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER,
					Certainty.CONCRETE,
					process,
					"Place "
							+ place_num
							+ " used in $barrier_create() exceeds the size of the $gbarrier.",
					source);

			this.errorLogger.reportError(err);
		}
		procMapArray = universe.tupleRead(gbarrierObj, oneObject);
		if (!universe.arrayRead(procMapArray, (NumericExpression) place)
				.equals(modelFactory.nullProcessValue())) {
			throw new CIVLExecutionException(ErrorKind.OTHER,
					Certainty.CONCRETE, process,
					"Attempt to create a barrier using an invalid place.",
					source);
		}

		// TODO report an error if the place exceeds the size of the
		// communicator
		procMapArray = universe.arrayWrite(procMapArray,
				(NumericExpression) place, modelFactory.processValue(pid));
		gbarrierObj = universe.tupleWrite(gbarrierObj, oneObject, procMapArray);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gbarrier, gbarrierObj);
		// builds barrier object
		barrierComponents.add(place);
		barrierComponents.add(gbarrier);
		barrierObj = universe.tuple(
				(SymbolicTupleType) barrierType.getDynamicType(universe),
				barrierComponents);
		state = this.primaryExecutor.malloc(civlsource, state, pid, process,
				lhs, scopeExpression, scope, barrierType, barrierObj);
		return state;
	}

	/**
	 * Adds the message to the appropriate message queue in the communication
	 * universe specified by the comm. The source of the message must equal the
	 * place of the comm.
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
	private State executeBarrierEnter(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = arguments[0].getSource();
		SymbolicExpression barrier = argumentValues[0];
		SymbolicExpression barrierObj;
		SymbolicExpression gbarrier;
		SymbolicExpression gbarrierObj;
		SymbolicExpression inBarrierArray;
		SymbolicExpression nprocs;
		NumericExpression myPlace;
		SymbolicExpression numInBarrier;
		Evaluation eval;
		int numInBarrier_int;
		int nprocs_int;

		eval = evaluator
				.dereference(civlsource, state, process, barrier, false);
		state = eval.state;
		barrierObj = eval.value;
		myPlace = (NumericExpression) universe
				.tupleRead(barrierObj, zeroObject);
		gbarrier = universe.tupleRead(barrierObj, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gbarrier,
				false);
		state = eval.state;
		gbarrierObj = eval.value;
		nprocs = universe.tupleRead(gbarrierObj, zeroObject);
		inBarrierArray = universe.tupleRead(gbarrierObj, twoObject);
		numInBarrier = universe.tupleRead(gbarrierObj, threeObject);
		nprocs_int = symbolicUtil.extractInt(civlsource,
				(NumericExpression) nprocs);
		numInBarrier_int = symbolicUtil.extractInt(civlsource,
				(NumericExpression) numInBarrier);
		numInBarrier_int++;
		if (numInBarrier_int == nprocs_int) {
			LinkedList<SymbolicExpression> inBarrierComponents = new LinkedList<>();

			for (int i = 0; i < nprocs_int; i++) {
				inBarrierComponents.add(universe.falseExpression());
			}
			inBarrierArray = universe.array(universe.booleanType(),
					inBarrierComponents);
			numInBarrier = zero;
		} else {
			numInBarrier = universe.integer(numInBarrier_int);
			inBarrierArray = universe.arrayWrite(inBarrierArray, myPlace,
					universe.trueExpression());
		}
		gbarrierObj = universe.tupleWrite(gbarrierObj, this.twoObject,
				inBarrierArray);
		gbarrierObj = universe.tupleWrite(gbarrierObj, this.threeObject,
				numInBarrier);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gbarrier, gbarrierObj);
		return state;
	}

	/**
	 * Creates a new local communicator object and returns a handle to it. The
	 * new communicator will be affiliated with the specified global
	 * communicator. This local communicator handle will be used as an argument
	 * in most message-passing functions. The place must be in [0,size-1] and
	 * specifies the place in the global communication universe that will be
	 * occupied by the local communicator. The local communicator handle may be
	 * used by more than one process, but all of those processes will be viewed
	 * as occupying the same place. Only one call to $comm_create may occur for
	 * each gcomm-place pair. The new object will be allocated in the given
	 * scope.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression gcommHandle = argumentValues[1];
		SymbolicExpression place = argumentValues[2];
		SymbolicExpression gcomm;
		SymbolicExpression comm;
		SymbolicExpression isInitArray;
		LinkedList<SymbolicExpression> commComponents = new LinkedList<SymbolicExpression>();
		CIVLSource civlsource = arguments[0].getSource();
		CIVLType commType = model.commType();
		Evaluation eval;

		eval = this.evaluator.dereference(civlsource, state, process,
				gcommHandle, false);
		state = eval.state;
		gcomm = eval.value;
		isInitArray = universe.tupleRead(gcomm, oneObject);
		// TODO report an error if the place has already been taken by other
		// processes.
		assert universe.arrayRead(isInitArray, (NumericExpression) place)
				.equals(universe.bool(false));
		// TODO report an error if the place exceeds the size of the
		// communicator
		isInitArray = universe.arrayWrite(isInitArray,
				(NumericExpression) place, universe.bool(true));
		gcomm = universe.tupleWrite(gcomm, oneObject, isInitArray);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gcommHandle, gcomm);
		// builds comm
		commComponents.add(place);
		commComponents.add(gcommHandle);
		comm = universe.tuple(
				(SymbolicTupleType) commType.getDynamicType(universe),
				commComponents);
		state = this.primaryExecutor.malloc(civlsource, state, pid, process,
				lhs, scopeExpression, scope, commType, comm);
		return state;
	}

	/**
	 * Returns the size (number of places) in the global communicator associated
	 * to the given comm.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommSize(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		SymbolicExpression nprocs;
		CIVLSource civlsource = arguments[0].getSource();
		Evaluation eval;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		nprocs = universe.tupleRead(gcomm, zeroObject);
		if (lhs != null) {
			state = this.primaryExecutor.assign(state, pid, process, lhs,
					nprocs);
		}
		return state;
	}

	/**
	 * Returns true iff a matching message exists in the communication universe
	 * specified by the comm. A message matches the arguments if the destination
	 * of the message is the place of the comm, and the sources and tags match.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommProbe(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		NumericExpression source = (NumericExpression) argumentValues[1];
		SymbolicExpression tag = argumentValues[2];
		NumericExpression dest;
		SymbolicExpression queue, queueLength, messages;
		int msgIdx = -1;
		boolean isFind = false;
		CIVLSource civlsource = state.getProcessState(pid).getLocation()
				.getSource();
		Evaluation eval;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		dest = (NumericExpression) universe.tupleRead(comm, zeroObject);
		queue = universe.arrayRead(universe.arrayRead(
				universe.tupleRead(gcomm, twoObject), source), dest);
		queueLength = universe.tupleRead(queue, zeroObject);
		messages = universe.tupleRead(queue, oneObject);
		msgIdx = this.getMatchedMsgIdx(state, pid, messages, queueLength, tag,
				civlsource);
		if (msgIdx >= 0)
			isFind = true;
		if (lhs != null) {
			state = this.stateFactory.setVariable(state,
					((VariableExpression) lhs).variable(), pid,
					universe.bool(isFind));
		}
		return state;
	}

	/**
	 * Finds the first matching message and returns it without modifying the
	 * communication universe. If no matching message exists, returns a message
	 * with source, dest, and tag all negative.
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommSeek(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		NumericExpression source = (NumericExpression) argumentValues[1];
		NumericExpression dest;
		SymbolicExpression tag = argumentValues[2];
		SymbolicExpression message, messages, queue, queueLength;
		CIVLSource civlsource = state.getProcessState(pid).getLocation()
				.getSource();
		Evaluation eval;
		int msgIdx = -1;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		dest = (NumericExpression) universe.tupleRead(comm, zeroObject);
		queue = universe.arrayRead(universe.arrayRead(
				universe.tupleRead(gcomm, twoObject), source), dest);
		queueLength = universe.tupleRead(queue, zeroObject);
		messages = universe.tupleRead(queue, oneObject);
		msgIdx = this.getMatchedMsgIdx(state, pid, messages, queueLength, tag,
				civlsource);
		if (msgIdx == -1)
			message = this.getEmptyMessage(state);
		else
			message = universe.arrayRead(messages, universe.integer(msgIdx));
		if (lhs != null) {
			// state = this.stateFactory.setVariable(state,
			// ((VariableExpression) lhs).variable(), pid, message);
			state = primaryExecutor.assign(state, pid, process, lhs, message);
		}
		return state;
	}

	/**
	 * Finds the first matching message, removes it from the communicator, and
	 * returns the message
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommDequeue(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = state.getProcessState(pid).getLocation()
				.getSource();
		SymbolicExpression message = null;
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		NumericExpression source = (NumericExpression) argumentValues[1];
		NumericExpression tag = (NumericExpression) argumentValues[2];
		NumericExpression dest;
		SymbolicExpression buf;
		SymbolicExpression bufRow = null;
		SymbolicExpression queue = null;
		NumericExpression queueLength = null;
		SymbolicExpression messages = null;
		Evaluation eval;
		int msgIdx;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		buf = universe.tupleRead(gcomm, universe.intObject(2));
		dest = (NumericExpression) universe.tupleRead(comm, zeroObject);
		bufRow = universe.arrayRead(buf, source);
		queue = universe.arrayRead(bufRow, dest);
		queueLength = (NumericExpression) universe.tupleRead(queue, zeroObject);
		messages = universe.tupleRead(queue, oneObject);
		msgIdx = this.getMatchedMsgIdx(state, pid, messages, queueLength, tag,
				civlsource);
		if (msgIdx == -1)
			throw new CIVLExecutionException(ErrorKind.INTERNAL,
					Certainty.CONCRETE, state.getProcessState(pid).name(),
					"Message dequeue fails", civlsource);
		message = universe.arrayRead(messages, universe.integer(msgIdx));
		messages = universe.removeElementAt(messages, msgIdx);
		queueLength = universe.subtract(queueLength, one);
		queue = universe.tupleWrite(queue, zeroObject, queueLength);
		queue = universe.tupleWrite(queue, oneObject, messages);
		bufRow = universe.arrayWrite(bufRow, dest, queue);
		buf = universe.arrayWrite(buf, source, bufRow);
		gcomm = universe.tupleWrite(gcomm, universe.intObject(2), buf);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gcommHandle, gcomm);
		if (lhs != null) {
			state = this.primaryExecutor.assign(state, pid, process, lhs,
					message);
		}
		return state;
	}

	/**
	 * Adds the message to the appropriate message queue in the communication
	 * universe specified by the comm. The source of the message must equal the
	 * place of the comm.
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
	private State executeCommEnqueue(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = arguments[0].getSource();
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression newMessage = argumentValues[1];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		SymbolicExpression source;
		SymbolicExpression dest;
		SymbolicExpression buf;
		SymbolicExpression bufRow;
		SymbolicExpression queue;
		SymbolicExpression queueLength;
		SymbolicExpression messages;
		Evaluation eval;
		int int_queueLength;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		buf = universe.tupleRead(gcomm, universe.intObject(2));
		// TODO checks if source is equal to the place of comm.
		source = universe.tupleRead(newMessage, zeroObject);
		dest = universe.tupleRead(newMessage, oneObject);
		bufRow = universe.arrayRead(buf, (NumericExpression) source);
		queue = universe.arrayRead(bufRow, (NumericExpression) dest);
		queueLength = universe.tupleRead(queue, zeroObject);
		messages = universe.tupleRead(queue, oneObject);
		messages = universe.append(messages, newMessage);
		int_queueLength = symbolicUtil.extractInt(civlsource,
				(NumericExpression) queueLength);
		int_queueLength++;
		queueLength = universe.integer(int_queueLength);
		queue = universe.tupleWrite(queue, zeroObject, queueLength);
		queue = universe.tupleWrite(queue, oneObject, messages);
		bufRow = universe.arrayWrite(bufRow, (NumericExpression) dest, queue);
		buf = universe.arrayWrite(buf, (NumericExpression) source, bufRow);
		gcomm = universe.tupleWrite(gcomm, universe.intObject(2), buf);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gcommHandle, gcomm);
		return state;
	}

	/**
	 * $exit terminates the calling process.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The process ID of the process to be terminated.
	 * @return The state resulting from removing the specified process.
	 */
	private State executeExit(State state, int pid) {
		int atomicPID = stateFactory.processInAtomic(state);

		if (atomicPID == pid) {
			state = stateFactory.releaseAtomicLock(state);
		}
		return stateFactory.terminateProcess(state, pid);
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
		case "$assert":
			state = this.executeAssert(state, pid, process, arguments,
					argumentValues, call.getSource(), call);
			break;
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
		case "$barrier_create":
			state = executeBarrierCreate(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$barrier_enter":
			state = executeBarrierEnter(state, pid, process, arguments,
					argumentValues);
			break;
		case "$barrier_exit":
			// does nothing
			break;
		case "$contains":
			state = executeContains(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$equals":
			state = executeEquals(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$gbarrier_create":
			state = executeGbarrierCreate(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$choose_int_work":
			if (lhs != null)
				state = primaryExecutor.assign(state, pid, process, lhs,
						argumentValues[0]);
			break;
		case "$comm_create":
			state = this.executeCommCreate(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$comm_defined":
			state = this.executeCommDefined(state, pid, process, lhs,
					arguments, argumentValues);
			break;
		case "$comm_dequeue":
			state = executeCommDequeue(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$comm_dequeue_work":
			state = executeCommDequeue(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$comm_enqueue":
			state = executeCommEnqueue(state, pid, process, arguments,
					argumentValues);
			break;
		case "$comm_seek":
			state = this.executeCommSeek(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$comm_probe":
			state = this.executeCommProbe(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$comm_size":
			state = this.executeCommSize(state, pid, process, lhs, arguments,
					argumentValues);
			break;
		case "$exit":// return immediately since no transitions needed after an
			// exit, because the process no longer exists.
			return executeExit(state, pid);
		case "$domain_rectangular":
			state = execute_domain_rectangular(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$domain_get_dim":
			state = execute_domain_get_dim(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$barrier_destroy":
		case "$gbarrier_destroy":
		case "$comm_destroy":
		case "$gcomm_destroy":
		case "$int_iter_destroy":
		case "$free":
			state = executeFree(state, pid, process, arguments, argumentValues,
					call.getSource());
			break;
		case "$gcomm_create":
			state = executeGcommCreate(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$gcomm_defined":
			state = this.executeGcommDefined(state, pid, process, lhs,
					arguments, argumentValues);
			break;
		case "$int_iter_create":
			state = this.executeIntIterCreate(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$int_iter_hasNext":
			state = this.executeIntIterHasNext(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$int_iter_next":
			state = this.executeIntIterNext(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$proc_defined":
			state = this.executeProcDefined(state, pid, process, lhs,
					arguments, argumentValues);
			break;
		case "$scope_defined":
			state = this.executeScopeDefined(state, pid, process, lhs,
					arguments, argumentValues);
			break;
		case "$scope_parent":
			state = this.executeScopeParent(state, pid, process, lhs,
					arguments, argumentValues);
			break;
		case "$wait":// return immediately since target location has been set.
			return executeWait(state, pid, arguments, argumentValues,
					call.getSource(), call.target());
		case "$waitall":
			return executeWaitAll(state, pid, arguments, argumentValues,
					call.getSource(), call.target());
		default:
			throw new CIVLInternalException("Unknown civlc function: " + name,
					call);
		}
		state = stateFactory.setLocation(state, pid, call.target());
		return state;
	}

	private State executeContains(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression first = argumentValues[0], second = argumentValues[1];

		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs,
					symbolicUtil.contains(first, second));
		// Evaluation eval = evaluator.dereference(arguments[0].getSource(),
		// state, process, argumentValues[0], false);
		// int invalidArg = -1;
		//
		// state = eval.state;
		// first = eval.value;
		// eval = evaluator.dereference(arguments[1].getSource(), state,
		// process,
		// argumentValues[1], false);
		// state = eval.state;
		// second = eval.value;
		// if (!symbolicUtil.isInitialized(first))
		// invalidArg = 0;
		// else if (!symbolicUtil.isInitialized(second))
		// invalidArg = 1;
		// if (invalidArg != -1) {
		// SymbolicExpression invalidValue = invalidArg == 0 ? first : second;
		// CIVLExecutionException err = new CIVLExecutionException(
		// ErrorKind.UNDEFINED_VALUE, Certainty.PROVEABLE, process,
		// "The object that "
		// + arguments[invalidArg]
		// + " points to is undefined, which has the value "
		// + symbolicUtil.symbolicExpressionToString(
		// arguments[invalidArg].getSource(), state,
		// invalidValue),
		// symbolicUtil.stateToString(state),
		// arguments[invalidArg].getSource());
		//
		// this.errorLogger.reportError(err);
		// }
		// TODO: TO BE FINISHED
		return state;
	}

	/**
	 * are the object pointed to equal?
	 * 
	 * _Bool $equals(void *x, void *y);
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
	private State executeEquals(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression first, second;
		Evaluation eval = evaluator.dereference(arguments[0].getSource(),
				state, process, argumentValues[0], false);
		int invalidArg = -1;

		state = eval.state;
		first = eval.value;
		eval = evaluator.dereference(arguments[1].getSource(), state, process,
				argumentValues[1], false);
		state = eval.state;
		second = eval.value;
		if (!symbolicUtil.isInitialized(first))
			invalidArg = 0;
		else if (!symbolicUtil.isInitialized(second))
			invalidArg = 1;
		if (invalidArg != -1) {
			SymbolicExpression invalidValue = invalidArg == 0 ? first : second;
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.UNDEFINED_VALUE, Certainty.PROVEABLE, process,
					"The object that "
							+ arguments[invalidArg]
							+ " points to is undefined, which has the value "
							+ symbolicUtil.symbolicExpressionToString(
									arguments[invalidArg].getSource(), state,
									invalidValue),
					symbolicUtil.stateToString(state),
					arguments[invalidArg].getSource());

			this.errorLogger.reportError(err);
		}
		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs,
					universe.equals(first, second));
		return state;
	}

	private State executeWaitAll(State state, int pid, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source,
			Location target) throws UnsatisfiablePathConditionException {
		SymbolicExpression procsPointer = argumentValues[0];
		SymbolicExpression numOfProcs = argumentValues[1];
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber number_nprocs = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) numOfProcs);
		String process = state.getProcessState(pid).name() + "(id=" + pid + ")";

		if (number_nprocs == null) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER, Certainty.PROVEABLE, process,
					"The number of processes for $waitall "
							+ "needs a concrete value.",
					symbolicUtil.stateToString(state), arguments[1].getSource());

			this.errorLogger.reportError(err);
		} else {
			int numOfProcs_int = number_nprocs.intValue();
			BinaryExpression pointerAdd;
			CIVLSource procsSource = arguments[0].getSource();
			Evaluation eval;

			for (int i = 0; i < numOfProcs_int; i++) {
				Expression offSet = modelFactory.integerLiteralExpression(
						procsSource, BigInteger.valueOf(i));
				NumericExpression offSetV = universe.integer(i);
				SymbolicExpression procPointer, proc;
				int pidValue;

				pointerAdd = modelFactory.binaryExpression(procsSource,
						BINARY_OPERATOR.POINTER_ADD, arguments[0], offSet);
				eval = evaluator.pointerAdd(state, pid, process, pointerAdd,
						procsPointer, offSetV);
				procPointer = eval.value;
				state = eval.state;
				eval = evaluator.dereference(procsSource, state, process,
						procPointer, false);
				proc = eval.value;
				state = eval.state;
				pidValue = modelFactory.getProcessId(procsSource, proc);
				state = stateFactory.removeProcess(state, pidValue);
			}
			state = stateFactory.setLocation(state, pid, target);
		}
		return state;
	}

	private State execute_domain_get_dim(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression domain = argumentValues[0];
		@SuppressWarnings("rawtypes")
		SymbolicSequence fields = (SymbolicSequence) domain.argument(0);
		int dim = fields.size();

		if (lhs != null) {
			state = primaryExecutor.assign(state, pid, process, lhs,
					universe.integer(dim));
		}
		return state;
	}

	private State execute_domain_rectangular(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		int dim = arguments.length;
		String name = "$domain-" + dim;
		List<SymbolicType> fieldTypes = new ArrayList<>(dim);
		SymbolicTupleType domainType;
		SymbolicExpression domain;

		for (int i = 0; i < dim; i++)
			fieldTypes.add(argumentValues[i].type());
		domainType = universe
				.tupleType(universe.stringObject(name), fieldTypes);
		domain = universe.tuple(domainType, Arrays.asList(argumentValues));
		if (lhs != null) {
			CIVLDomainType lhsType = (CIVLDomainType) lhs.getExpressionType();

			if (dim != lhsType.dimension()) {
				CIVLExecutionException e = new CIVLExecutionException(
						ErrorKind.OTHER,
						Certainty.PROVEABLE,
						process,
						"The left hand side of $domain_rectangular() doesn't have the same "
								+ "dimension as the number of arguments for $domain_rectangular():\n "
								+ "dimension of left hand side: "
								+ lhsType.dimension()
								+ "\n"
								+ "number of arguments of $domain_rectangular(): "
								+ dim + ".", symbolicUtil.stateToString(state),
						source);

				errorLogger.reportError(e);
			} else
				state = primaryExecutor
						.assign(state, pid, process, lhs, domain);
		}
		return state;
	}

	/**
	 * Tells whether the integer iterator has any more elements.
	 * <code>_Bool $int_iter_hasNext($int_iter iter);</code>
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeIntIterHasNext(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression iterHandle = argumentValues[0];
		SymbolicExpression iterObj;
		CIVLSource civlsource = arguments[0].getSource();
		Evaluation eval;
		NumericExpression size, index;
		SymbolicExpression hasNext;

		eval = evaluator.dereference(civlsource, state, process, iterHandle,
				false);
		state = eval.state;
		iterObj = eval.value;
		size = (NumericExpression) universe.tupleRead(iterObj, zeroObject);
		index = (NumericExpression) universe.tupleRead(iterObj, twoObject);
		hasNext = universe.lessThan(index, size);
		if (lhs != null) {
			state = primaryExecutor.assign(state, pid, process, lhs, hasNext);
		}
		return state;
	}

	/**
	 * Checks if a $comm object is defined, i.e., it doesn't point to the heap
	 * of an invalid scope, implementing the function $comm_defined($comm comm).
	 * 
	 * @param state
	 *            The state where the checking happens.
	 * @param pid
	 *            The ID of the process that this computation belongs to.
	 * @param lhs
	 *            The left hand side expression of this function call.
	 * @param arguments
	 *            The static arguments of the function call.
	 * @param argumentValues
	 *            The symbolic values of the arguments of the function call
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeCommDefined(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression result = this.isValidPointer(argumentValues[0]);

		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, result);
		return state;
	}

	/**
	 * Checks if a $gcomm object is defined, i.e., it doesn't point to the heap
	 * of an invalid scope, implementing the function $gcomm_defined($gcomm
	 * gcomm).
	 * 
	 * @param state
	 *            The state where the checking happens.
	 * @param pid
	 *            The ID of the process that this computation belongs to.
	 * @param lhs
	 *            The left hand side expression of this function call.
	 * @param arguments
	 *            The static arguments of the function call.
	 * @param argumentValues
	 *            The symbolic values of the arguments of the function call
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeGcommDefined(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression result = this.isValidPointer(argumentValues[0]);

		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, result);
		return state;
	}

	/**
	 * Checks if a scope reference is defined, i.e., its id is non-negative.
	 * 
	 * @param state
	 *            The state where the checking happens.
	 * @param pid
	 *            The ID of the process that this computation belongs to.
	 * @param lhs
	 *            The left hand side expression of this function call.
	 * @param arguments
	 *            The static arguments of the function call.
	 * @param argumentValues
	 *            The symbolic values of the arguments of the function call
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeScopeDefined(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression result = modelFactory.isScopeDefined(
				arguments[0].getSource(), argumentValues[0]);

		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, result);
		return state;
	}

	/**
	 * Checks if a process reference is defined, i.e., its id is non-negative.
	 * 
	 * @param state
	 *            The state where the checking happens.
	 * @param pid
	 *            The ID of the process that this computation belongs to.
	 * @param lhs
	 *            The left hand side expression of this function call.
	 * @param arguments
	 *            The static arguments of the function call.
	 * @param argumentValues
	 *            The symbolic values of the arguments of the function call
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeProcDefined(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression result = modelFactory.isProcessDefined(
				arguments[0].getSource(), argumentValues[0]);

		if (lhs != null)
			state = primaryExecutor.assign(state, pid, process, lhs, result);
		return state;
	}

	/**
	 * Checks if a pointer is defined, i.e., it doesn't point to a memory unit
	 * of an invalid scope.
	 * 
	 * @param pointer
	 * @return
	 */
	private SymbolicExpression isValidPointer(SymbolicExpression pointer) {
		int scopeId = symbolicUtil.getDyscopeId(null, pointer);

		if (scopeId >= 0)
			return universe.bool(true);
		else
			return universe.bool(false);
	}

	/**
	 * Executes lhs = $scope_parent($scope s).
	 * 
	 * @param state
	 *            The state where the computation happens.
	 * @param pid
	 *            The ID of the process that the executed function call belongs
	 *            to.
	 * @param lhs
	 *            The left hand side expression of the function call, which is
	 *            to be assigned with the return value.
	 * @param arguments
	 *            The static arguments of the function call.
	 * @param argumentValues
	 *            The symbolic expressions of the arguments of the function
	 *            call.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 *             if the assignment of the left hand side expression fails.
	 */
	private State executeScopeParent(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scopeValue = argumentValues[0];
		Expression scopeExpression = arguments[0];
		CIVLSource source = scopeExpression.getSource();
		int scopeID = modelFactory.getScopeId(source, scopeValue);
		int parentID = state.getParentId(scopeID);
		SymbolicExpression parentScope = modelFactory.scopeValue(parentID);

		if (lhs != null) {
			state = this.primaryExecutor.assign(state, pid, process, lhs,
					parentScope);
		}
		return state;
	}

	/**
	 * Executes the $wait system function call. Only enabled when the waited
	 * process has terminated.
	 * 
	 * * @param state The current state.
	 * 
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
	 * @param target
	 *            The target location of the wait function call.
	 * @return The new state after executing the function call.
	 * @return
	 */
	private State executeWait(State state, int pid, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source,
			Location target) {
		SymbolicExpression procVal = argumentValues[0];
		int joinedPid = modelFactory.getProcessId(arguments[0].getSource(),
				procVal);

		state = stateFactory.setLocation(state, pid, target);
		if (!modelFactory.isPocessIdDefined(joinedPid)
				|| modelFactory.isProcessIdNull(joinedPid))
			return state;
		state = stateFactory.removeProcess(state, joinedPid);
		return state;
	}

	/**
	 * Obtains an array via a certain pointer.
	 * 
	 * @param state
	 *            The current state
	 * @param pointerExpr
	 *            The static representation of the pointer.
	 * @param pointer
	 *            The dynamic representation of the pointer.
	 * @param size
	 *            The size of the array.
	 * @param source
	 *            The source code element for error report.
	 * @return The symbolic representation of the array that the given pointer
	 *         points to.
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression getArrayFromPointer(State state, String process,
			Expression pointerExpr, SymbolicExpression pointer,
			NumericExpression size, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression array = null;
		ReferenceExpression symRef = symbolicUtil.getSymRef(pointer);
		ReferenceKind kind = symRef.referenceKind();
		SymbolicType elementType = evaluator.referencedType(source, state,
				pointer);
		NumericExpression elementSize = symbolicUtil
				.sizeof(source, elementType);
		BooleanExpression pathCondition = state.getPathCondition();
		BooleanExpression zeroSizeClaim = universe.equals(size, zero);
		Reasoner reasoner = universe.reasoner(pathCondition);
		ResultType zeroSizeValid = reasoner.valid(zeroSizeClaim)
				.getResultType();

		if (zeroSizeValid == ResultType.YES) {
			array = universe.emptyArray(elementType);
		} else {
			BooleanExpression oneCountClaim = universe
					.equals(size, elementSize);
			ResultType oneCountValid = reasoner.valid(oneCountClaim)
					.getResultType();

			if (oneCountValid == ResultType.YES) {
				Evaluation eval = evaluator
						.dereference(pointerExpr.getSource(), state, process,
								pointer, false);
				SymbolicExpression element0 = eval.value;

				state = eval.state;
				pathCondition = state.getPathCondition();
				array = universe.array(elementType,
						new Singleton<SymbolicExpression>(element0));
			} else {
				BooleanExpression divisibility = universe.divides(elementSize,
						size);
				ResultType divisibilityValid = reasoner.valid(divisibility)
						.getResultType();
				NumericExpression count;

				if (divisibilityValid != ResultType.YES) {
					Certainty certainty = divisibilityValid == ResultType.MAYBE ? Certainty.MAYBE
							: Certainty.PROVEABLE;
					CIVLExecutionException e = new CIVLExecutionException(
							ErrorKind.OTHER, certainty, process,
							"sizeof element ( " + elementSize
									+ ") does not divide size argument ("
									+ size + ")",
							symbolicUtil.stateToString(state), source);

					errorLogger.reportError(e);
					pathCondition = universe.and(pathCondition, divisibility);
					state = state.setPathCondition(pathCondition);
					reasoner = universe.reasoner(pathCondition);
				}
				count = universe.divide(size, elementSize);
				switch (kind) {
				case ARRAY_ELEMENT: {
					NumericExpression startIndex = ((ArrayElementReference) symRef)
							.getIndex();
					SymbolicExpression arrayPointer = symbolicUtil
							.parentPointer(source, pointer);
					Evaluation eval = evaluator.dereference(source, state,
							process, arrayPointer, false);
					SymbolicExpression originalArray = eval.value;
					NumericExpression endIndex = universe
							.add(startIndex, count);
					state = eval.state;
					array = symbolicUtil.getSubArray(originalArray, startIndex,
							endIndex, state, process, source);
					break;
				}
				case IDENTITY:
					throw new CIVLExecutionException(ErrorKind.POINTER,
							Certainty.MAYBE, process,
							"unable to get concrete count of 0 or 1 from size",
							symbolicUtil.stateToString(state), source);
				case NULL: { // size must be 0
					Certainty certainty = zeroSizeValid == ResultType.MAYBE ? Certainty.MAYBE
							: Certainty.PROVEABLE;
					CIVLExecutionException e = new CIVLExecutionException(
							ErrorKind.POINTER, certainty, process,
							"null pointer only valid with size 0",
							symbolicUtil.stateToString(state), source);

					errorLogger.reportError(e);
					pathCondition = universe.and(pathCondition, zeroSizeClaim);
					state = state.setPathCondition(pathCondition);
					reasoner = universe.reasoner(pathCondition);
					array = universe.emptyArray(elementType);
					break;
				}
				case OFFSET: {
					// either size is zero or size is 1 and offset is 0
					throw new CIVLExecutionException(ErrorKind.POINTER,
							Certainty.MAYBE, process,
							"possible out of bounds pointer",
							symbolicUtil.stateToString(state), source);
				}
				case TUPLE_COMPONENT: {
					throw new CIVLExecutionException(ErrorKind.POINTER,
							Certainty.MAYBE, process,
							"unable to get concrete count of 0 or 1 from size",
							symbolicUtil.stateToString(state), source);
				}
				case UNION_MEMBER:
					throw new CIVLInternalException("dereference union member",
							source);
				default:
					throw new CIVLInternalException("unreachable", source);
				}
			}
		}
		return array;
	}

	/**
	 * Read matched message index from the given messages array. Here
	 * "matched message" means if the given tag is wild card tag, then read the
	 * first message inside the messages array, otherwise the tag should be a
	 * specific tag then read the first message has the same tag inside the
	 * messages array.
	 * 
	 * Other cases like failure of finding a matched message or the tag is
	 * neither a wild card tag nor a valid specific tag will be an execution
	 * exception.
	 * 
	 * @param state
	 *            The current state which emanating the transition being
	 *            executed right now
	 * @param pid
	 *            The pid of the process
	 * @param messagesArray
	 *            The given messages array
	 * @param tag
	 *            The given message tag
	 * @param civlsource
	 * @return The index of a matched message in the given array
	 * @throws UnsatisfiablePathConditionException
	 */
	private int getMatchedMsgIdx(State state, int pid,
			SymbolicExpression messagesArray, SymbolicExpression queueLength,
			SymbolicExpression tag, CIVLSource civlsource)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression message = null;
		NumericExpression numericQueueLength = (NumericExpression) queueLength;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		BooleanExpression isAnyTag = universe.equals(universe.integer(-2), tag);
		BooleanExpression isSpecTag = universe.lessThanEquals(zero,
				(NumericExpression) tag);
		int msgIndex = -1;

		// specific tag
		if (reasoner.isValid(isSpecTag)) {
			NumericExpression iter = zero;
			BooleanExpression iterQueueLengthClaim = universe.lessThan(iter,
					(NumericExpression) queueLength);

			while (reasoner.isValid(iterQueueLengthClaim)) {
				BooleanExpression isTagMatched;

				message = universe.arrayRead(messagesArray, iter);
				isTagMatched = universe
						.equals(universe.tupleRead(message,
								universe.intObject(2)), tag);
				if (reasoner.isValid(isTagMatched)) {
					msgIndex = symbolicUtil.extractInt(null, iter);
					break;
				}
				iter = universe.add(iter, one);
				iterQueueLengthClaim = universe.lessThan(iter,
						numericQueueLength);
			}
		}
		// wild card tag
		else if (reasoner.isValid(isAnyTag)) {
			BooleanExpression queueGTzeroClaim = universe.lessThan(zero,
					numericQueueLength);

			if (reasoner.isValid(queueGTzeroClaim))
				msgIndex = 0;

		}
		// Exception
		else {
			throw new CIVLExecutionException(ErrorKind.INTERNAL,
					Certainty.CONCRETE, state.getProcessState(pid).name(),
					"Unexpected arguments", civlsource);
		}
		return msgIndex;
	}

	private SymbolicExpression getEmptyMessage(State state) {
		SymbolicExpression message;
		CIVLType messageType = model.mesageType();
		CIVLBundleType bundleType = model.bundleType();
		LinkedList<SymbolicExpression> emptyMessageComponents = new LinkedList<SymbolicExpression>();
		StringObject name;
		SymbolicExpression bundle;

		name = universe.stringObject("X_s" + -1 + "v" + -1);
		bundle = universe.symbolicConstant(name,
				bundleType.getDynamicType(universe));
		emptyMessageComponents.add(universe.integer(-1));
		emptyMessageComponents.add(universe.integer(-1));
		emptyMessageComponents.add(universe.integer(-1));
		emptyMessageComponents.add(bundle);
		emptyMessageComponents.add(universe.integer(-1));
		message = this.universe.tuple(
				(SymbolicTupleType) messageType.getDynamicType(universe),
				emptyMessageComponents);
		return message;
	}

	/**
	 * Creates a new iterator for an array of integers, and returns the handle
	 * of the iterator. The new object will be allocated in the given scope.<br>
	 * <code>$int_iter $int_iter_create($scope scope, int *array, int
	 * size);</code>
	 * 
	 * <code>
	 * typedef struct __int_iter__ {<br>
	 * &nbsp;&nbsp;int size;<br>
	 * &nbsp;&nbsp;int content[];<br>
	 * &nbsp;&nbsp;int index; //initialized as 0<br>
	 * } $int_iter;
	 * </code>
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeIntIterCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression intIterObj;
		SymbolicExpression size = argumentValues[2];
		SymbolicExpression currentIndex = universe.integer(0);
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression arrayPointer = argumentValues[1];
		Expression arrayPointerExpression = arguments[1];
		SymbolicExpression intArray;
		LinkedList<SymbolicExpression> intArrayComponents = new LinkedList<>();
		List<SymbolicExpression> intIterComponents = new LinkedList<>();
		int int_size;
		CIVLType intIterType = model.intIterType();
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber number_size = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) size);
		Evaluation eval = evaluator.dereference(source, state, process,
				arrayPointer, false);
		CIVLSource arrayPointerSource = arrayPointerExpression.getSource();

		state = eval.state;
		if (number_size != null)
			int_size = number_size.intValue();
		else
			throw new CIVLInternalException(
					"Cannot extract concrete int value for gbarrier size",
					arguments[1]);
		for (int i = 0; i < int_size; i++) {
			BinaryExpression pointerAdditionExpression = modelFactory
					.binaryExpression(arrayPointerExpression.getSource(),
							BINARY_OPERATOR.POINTER_ADD,
							arrayPointerExpression, modelFactory
									.integerLiteralExpression(
											arrayPointerExpression.getSource(),
											BigInteger.valueOf(i)));
			SymbolicExpression arrayElePointer;

			eval = evaluator.pointerAdd(state, pid, process,
					pointerAdditionExpression, arrayPointer,
					universe.integer(i));
			state = eval.state;
			arrayElePointer = eval.value;
			eval = evaluator.dereference(arrayPointerSource, state, process,
					arrayElePointer, false);
			state = eval.state;
			intArrayComponents.add(eval.value);
		}
		intArray = universe.array(
				modelFactory.integerType().getDynamicType(universe),
				intArrayComponents);
		intIterComponents.add(size);
		intIterComponents.add(intArray);
		intIterComponents.add(currentIndex);
		intIterObj = universe.tuple(
				(SymbolicTupleType) intIterType.getDynamicType(universe),
				intIterComponents);
		state = primaryExecutor.malloc(source, state, pid, process, lhs,
				scopeExpression, scope, intIterType, intIterObj);
		return state;
	}

	/*
	 * Tells whether the integer iterator has any more elements _Bool
	 * $int_iter_hasNext($int_iter iter);
	 * 
	 * Returns the next element in the iterator (and updates the iterator) int
	 * $int_iter_next($int_iter iter);
	 * 
	 * Creates a new iterator for an array of integers, and returns the handle
	 * of the iterator. $int_iter $int_iter_create($scope scope, int *array, int
	 * size);
	 */

	/**
	 * Returns the next element in the iterator (and updates the iterator).
	 * <code>int $int_iter_next($int_iter iter);</code>
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
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeIntIterNext(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression iterHandle = argumentValues[0];
		SymbolicExpression array;
		SymbolicExpression iterObj;
		CIVLSource civlsource = arguments[0].getSource();
		Evaluation eval;
		NumericExpression index;
		SymbolicExpression nextInt;

		eval = evaluator.dereference(civlsource, state, process, iterHandle,
				false);
		state = eval.state;
		iterObj = eval.value;
		array = universe.tupleRead(iterObj, oneObject);
		index = (NumericExpression) universe.tupleRead(iterObj, twoObject);
		nextInt = universe.arrayRead(array, index);
		if (lhs != null) {
			state = primaryExecutor.assign(state, pid, process, lhs, nextInt);
		}
		// updates iterator object
		index = universe.add(index, one);
		iterObj = universe.tupleWrite(iterObj, twoObject, index);
		state = primaryExecutor.assign(source, state, process, iterHandle,
				iterObj);
		return state;
	}
}
