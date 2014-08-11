package edu.udel.cis.vsl.civl.library.bundle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryEvaluator;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicAnalyzer;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.util.IF.Pair;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericSymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicCompleteArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class LibbundleEvaluator extends BaseLibraryEvaluator implements
		LibraryEvaluator {

	public LibbundleEvaluator(String name, Evaluator evaluator,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			SymbolicAnalyzer symbolicAnalyzer) {
		super(name, evaluator, modelFactory, symbolicUtil, symbolicAnalyzer);
	}

	/* ************************* Public Function *************************** */
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
	public SymbolicExpression civlOperation(State state, String process,
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
		} catch (SARLException e) {
			throw new CIVLInternalException("CIVL Operation " + op
					+ " exception", civlsource);
		}
	}

	/**
	 * Computes the new pointer after adding an increment or decrement offset to
	 * a pointer.
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param pointer
	 *            The pointer before offset
	 * @param offset
	 *            The value of the offset
	 * @param checkOutput
	 *            If the object pointed by the pointer needs checking if it's an
	 *            output variable
	 * @param source
	 *            The CIVL source of the pointer
	 * @return the new pointer with offset
	 * @throws UnsatisfiablePathConditionException
	 */
	public Evaluation pointerAdd(State state, int pid, String process,
			SymbolicExpression pointer, NumericExpression offset,
			boolean checkOutput, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		return this.pointerAddWorker(state, process, pointer, offset,
				checkOutput, source).left;
	}

	/**
	 * Get a sequence of data starting from a pointer.
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param pointer
	 *            The pointer to the start position of a sequence of data
	 * @param count
	 *            The number of cells in the array of data
	 * @param checkOutput
	 *            Flag for check output variable
	 * @param source
	 *            CIVL source of the statement
	 * @return Evaluation contains the sequence of data which is in form of a
	 *         one dimensional array.
	 * @throws UnsatisfiablePathConditionException
	 */
	public Evaluation getDataFrom(State state, String process,
			SymbolicExpression pointer, NumericExpression count,
			boolean checkOutput, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		Map<Integer, NumericExpression> arrayCapacities;
		SymbolicExpression startPtr, endPtr;
		Evaluation eval;
		Pair<Evaluation, Map<Integer, NumericExpression>> ret;

		startPtr = pointer;
		// The pointerAddWorker returns the evaluation containing a new pointer
		// and array information of the whole array. Array information can be
		// reused later.
		// Data stored from "pointer" to "pointer + (count - 1)"
		ret = this.pointerAddWorker(state, process, startPtr,
				universe.subtract(count, one), false, source);
		arrayCapacities = ret.right;
		eval = ret.left;
		endPtr = eval.value;
		// If pointerAddWorker didn't computes array information, do it here.
		// But it's no need to computes the whole information of the array,
		// because pointerAddWorker's not doing it means new pointer and
		// original pointer are in the same dimension.
		if (arrayCapacities == null) {
			arrayCapacities = new HashMap<>();
			arrayCapacities.put(0, one);
		}
		eval.value = this.getDataBetween(eval.state, process, startPtr, endPtr,
				arrayCapacities, source);
		return eval;
	}

	/**
	 * Setting a sequence of data starting from a pointer
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param pointer
	 *            The pointer to the start position
	 * @param count
	 *            The number of cells in the array of data
	 * @param dataArray
	 *            The sequence of data is going to be set
	 * @param checkOutput
	 *            Flag for check output variable
	 * @param source
	 *            CIVL source of the statement
	 * @return A pair of evaluation and pointer.The data in form of an array
	 *         which can be assigned to the returned pointer.
	 * @throws UnsatisfiablePathConditionException
	 */
	public Pair<Evaluation, SymbolicExpression> setDataFrom(State state,
			String process, SymbolicExpression pointer,
			NumericExpression count, SymbolicExpression dataArray,
			boolean checkOutput, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		Map<Integer, NumericExpression> arrayCapacities;
		SymbolicExpression startPtr, endPtr;
		Evaluation eval;
		Pair<Evaluation, Map<Integer, NumericExpression>> ret;
		Pair<Evaluation, SymbolicExpression> eval_and_pointer;

		startPtr = pointer;
		ret = this.pointerAddWorker(state, process, startPtr,
				universe.subtract(count, one), checkOutput, source);
		arrayCapacities = ret.right;
		eval = ret.left;
		endPtr = eval.value;
		if (arrayCapacities == null) {
			arrayCapacities = new HashMap<>();
			arrayCapacities.put(0, one);
		}
		eval_and_pointer = this.setDataBetween(eval.state, process, startPtr,
				endPtr, dataArray, arrayCapacities, source);
		return eval_and_pointer;
	}

	/* *************** Helper functions for library executor ***************** */
	/**
	 * Evaluating for bundle_unpack execution. This function returns the value
	 * of the object and the pointer to that object(the return type is a Pair).
	 * The reason that why this function need
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
	Pair<Evaluation, SymbolicExpression> bundleUnpack(State state,
			String process, SymbolicExpression bundleData,
			SymbolicExpression pointer, CIVLSource civlsource)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression data = bundleData;
		NumericExpression dataSize;
		Evaluation eval;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		Pair<Evaluation, SymbolicExpression> eval_and_pointer;

		dataSize = universe.length(data);
		// If data size is zero, do nothing.
		if (reasoner.isValid(universe.equals(dataSize, zero))) {
			eval = evaluator.dereference(civlsource, state, process, pointer,
					false);
			return new Pair<Evaluation, SymbolicExpression>(eval, pointer);
		} else if (reasoner.isValid(universe.equals(dataSize, one))) {
			// If data size is one,
			eval = new Evaluation(state, universe.arrayRead(data, zero));
			return new Pair<Evaluation, SymbolicExpression>(eval, pointer);
		}
		// eval = this.pointerAdd(state, pid, process, pointer,
		// universe.subtract(dataSize, one), false, civlsource);
		// state = eval.state;
		// eval_and_pointer = this.setDataBetween(state, pid, process, pointer,
		// eval.value, data, civlsource);
		eval_and_pointer = this.setDataFrom(state, process, pointer, dataSize,
				data, false, civlsource);
		return eval_and_pointer;
	}

	/* ******************** Array Operation Helper Function **************** */
	/**
	 * Set all elements between two array element reference pointers with an
	 * array of data.
	 * 
	 * Pre-condition: 1. The start pointer should always be in the left side of
	 * the end pointer or they can point to the same position.<br>
	 * 2. The data array should be a one dimensional array. <br>
	 * 3. The number of elements between the two pointers should more than or
	 * equal to the length of the data array.<br>
	 * 4. Two pointers are pointing to the same array.
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param startPtr
	 *            The start pointer
	 * @param endPtr
	 *            The end pointer
	 * @param dataArray
	 *            The array of data
	 * @param dimCapacities
	 *            The map with dimensions as keys and capacities as values. This
	 *            parameter is optional for optimization, if passing null, this
	 *            function will calculate it.<br>
	 *            Note: the deepest dimension should be marked as zero.
	 * @param source
	 * @return the evaluation contains the state and the array pointed by both
	 *         pointer after setting data
	 * @throws UnsatisfiablePathConditionException
	 */
	Pair<Evaluation, SymbolicExpression> setDataBetween(State state,
			String process, SymbolicExpression startPtr,
			SymbolicExpression endPtr, SymbolicExpression dataArray,
			Map<Integer, NumericExpression> arrayCapacities, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression startPointer, endPointer;
		SymbolicExpression leastCommonArray, oldLeastCommonArray;
		NumericExpression startPos = zero;
		NumericExpression endPos = zero;
		NumericExpression ptrInterval;
		NumericExpression dataSize;
		NumericExpression i, j;
		Evaluation eval;
		BooleanExpression claim;
		boolean sidMatch, vidMatch;
		int dim = 0;
		Map<Integer, NumericExpression> startIndexes;
		Map<Integer, NumericExpression> endIndexes;
		Map<Integer, NumericExpression> commonArrayCapacities;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		// Checking if they are pointing to the same thing
		sidMatch = (symbolicUtil.getDyscopeId(source, startPtr) == symbolicUtil
				.getDyscopeId(source, endPtr));
		vidMatch = (symbolicUtil.getVariableId(source, startPtr) == symbolicUtil
				.getVariableId(source, endPtr));
		if (!(sidMatch && vidMatch))
			throw new CIVLInternalException("Object unmatch exception\n",
					source);
		startPointer = this
				.castToArrayElementReference(state, startPtr, source);
		endPointer = this.castToArrayElementReference(state, endPtr, source);
		startIndexes = this.arrayIndexesByPointer(state, source, startPointer);
		endIndexes = this.arrayIndexesByPointer(state, source, endPointer);
		while (startIndexes.containsKey(dim)) {
			startPos = universe.add(
					startPos,
					universe.multiply(startIndexes.get(dim),
							arrayCapacities.get(dim)));
			endPos = universe.add(
					endPos,
					universe.multiply(endIndexes.get(dim),
							arrayCapacities.get(dim)));
			dim++;
		}
		ptrInterval = universe.subtract(endPos, startPos);
		assert (reasoner.isValid(universe.lessThanEquals(zero, ptrInterval)));
		dim = 1;
		while (!startPointer.equals(endPointer)) {
			startPointer = symbolicUtil.parentPointer(source, startPointer);
			endPointer = symbolicUtil.parentPointer(source, endPointer);
			dim++;
		}
		dataSize = universe.length(dataArray);
		if (!reasoner.isValid(universe.lessThanEquals(dataSize,
				universe.add(ptrInterval, one)))) {
			throw new CIVLInternalException("Out of bound", source);
		}
		oldLeastCommonArray = evaluator.dereference(source, state, process,
				startPointer, false).value;
		if (!(oldLeastCommonArray.type() instanceof SymbolicArrayType)) {
			eval = new Evaluation(state, oldLeastCommonArray);
			return new Pair<>(eval, startPtr);
		}
		commonArrayCapacities = this.arrayCapacities(oldLeastCommonArray,
				source);
		// Direct assignment conditions:
		// 1. start position is zero.
		// 2. Interval between pointers equals to data size.
		// 3. The least common array capacity equals to data size.
		if (reasoner.isValid(universe.equals(startPos, zero))) {
			NumericExpression arrayCapacity = this.arraySize(
					oldLeastCommonArray, source);
			// TODO: array capacity efficiency ?
			claim = universe.and(
					universe.equals(dataSize, universe.add(ptrInterval, one)),
					universe.equals(dataSize, arrayCapacity));
			if (reasoner.isValid(claim)) {
				dataArray = arrayCasting(state, process, dataArray,
						oldLeastCommonArray, commonArrayCapacities, source);
				eval = new Evaluation(state, dataArray);
				return new Pair<Evaluation, SymbolicExpression>(eval,
						startPointer);
			}
		}
		leastCommonArray = arrayFlatten(state, process, oldLeastCommonArray,
				commonArrayCapacities, source);
		i = startPos;
		j = zero;
		claim = universe.lessThan(j, dataSize);
		try {
			while (reasoner.isValid(claim)) {
				leastCommonArray = universe.arrayWrite(leastCommonArray, i,
						universe.arrayRead(dataArray, j));
				i = universe.add(i, one);
				j = universe.add(j, one);
				claim = universe.lessThan(j, dataSize);
			}
		} catch (SARLException e) {
			throw new CIVLInternalException("Out of bound\n", source);
		}

		leastCommonArray = arrayCasting(state, process, leastCommonArray,
				oldLeastCommonArray, arrayCapacities, source);
		eval = new Evaluation(state, leastCommonArray);
		return new Pair<Evaluation, SymbolicExpression>(eval, startPointer);
	}

	/**
	 * Get all elements located between two array element reference pointers.<br>
	 * Note: All elements contains the element pointed by the start pointer but
	 * not exclude the element pointed by the end pointer.
	 * 
	 * Pre-condition: 1. Start pointer should always be in the left side of the
	 * end pointer or they can point to the same position.<br>
	 * 2. The two pointers of course should point to the same array.
	 * 
	 * @author Ziqing Luo
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param startPtr
	 *            The start pointer
	 * @param endPtr
	 *            The end pointer
	 * @param source
	 *            The civl source of either pointer.
	 * @return a list of elements
	 * @throws UnsatisfiablePathConditionException
	 */
	// TODO: rewrite doc
	SymbolicExpression getDataBetween(State state, String process,
			SymbolicExpression startPtr, SymbolicExpression endPtr,
			Map<Integer, NumericExpression> arrayCapacities, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression startPointer, endPointer;
		SymbolicExpression oldLeastCommonArray = null;
		SymbolicExpression flattenLeastComArray;
		NumericExpression startPos = zero;
		NumericExpression endPos = zero;
		NumericExpression dataLength;
		Map<Integer, NumericExpression> startIndexes;
		Map<Integer, NumericExpression> endIndexes;
		Map<Integer, NumericExpression> commonArrayCapacities;
		boolean sidMatch, vidMatch;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		int dim = 0;

		// Checking if both of the pointers are pointing to the same obejct
		sidMatch = (symbolicUtil.getDyscopeId(source, startPtr) == symbolicUtil
				.getDyscopeId(source, endPtr));
		vidMatch = (symbolicUtil.getVariableId(source, startPtr) == symbolicUtil
				.getVariableId(source, endPtr));
		if (!(sidMatch && vidMatch))
			throw new CIVLInternalException("Object unmatch exception\n",
					source);
		// Cast pointers to the form of an array element reference
		startPointer = this
				.castToArrayElementReference(state, startPtr, source);
		endPointer = this.castToArrayElementReference(state, endPtr, source);
		startIndexes = this.arrayIndexesByPointer(state, source, startPointer);
		endIndexes = this.arrayIndexesByPointer(state, source, endPointer);
		while (!startPointer.equals(endPointer)) {
			startPos = universe.add(
					startPos,
					universe.multiply(startIndexes.get(dim),
							arrayCapacities.get(dim)));
			endPos = universe.add(
					endPos,
					universe.multiply(endIndexes.get(dim),
							arrayCapacities.get(dim)));
			dim++;
			startPointer = symbolicUtil.parentPointer(source, startPointer);
			endPointer = symbolicUtil.parentPointer(source, endPointer);
		}
		dataLength = universe.add(universe.subtract(endPos, startPos), one);
		assert (reasoner.isValid(universe.lessThanEquals(zero, dataLength)));
		oldLeastCommonArray = evaluator.dereference(source, state, process,
				startPointer, false).value;
		if (!(oldLeastCommonArray.type() instanceof SymbolicArrayType)) {

			return universe.array(oldLeastCommonArray.type(),
					Arrays.asList(oldLeastCommonArray));
		}
		commonArrayCapacities = this.arrayCapacities(oldLeastCommonArray,
				source);
		// TODO: concrete array not need capacities map, here can do
		// optimization after understanding why array type some times will has
		// incomplete child array type
		flattenLeastComArray = arrayFlatten(state, process,
				oldLeastCommonArray, commonArrayCapacities, source);
		flattenLeastComArray = symbolicAnalyzer.getSubArray(
				flattenLeastComArray, startPos, universe.add(endPos, one),
				state, process, source);
		return flattenLeastComArray;
	}

	SymbolicExpression arrayFlatten(State state, String process,
			SymbolicExpression array,
			Map<Integer, NumericExpression> arrayCapacities,
			CIVLSource civlsource) {
		List<SymbolicExpression> flattenElementList;

		if (array == null)
			throw new CIVLInternalException("parameter array is null.",
					civlsource);
		if (array.isNull())
			return array;
		flattenElementList = this.arrayFlattenList(state, process, array,
				arrayCapacities, civlsource);
		if (flattenElementList.size() > 0) {
			if (flattenElementList.size() == 1
					&& (flattenElementList.get(0).type() instanceof SymbolicArrayType))
				return flattenElementList.get(0);
			else
				return universe.array(flattenElementList.get(0).type(),
						flattenElementList);
		} else if (array instanceof SymbolicArrayType)
			return universe.emptyArray(((SymbolicArrayType) array)
					.elementType());
		else
			return universe.emptyArray(array.type());
	}

	SymbolicExpression arrayCasting(State state, String process,
			SymbolicExpression oldArray, SymbolicExpression targetTypeArray,
			Map<Integer, NumericExpression> arrayCapacities, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		BooleanExpression claim;
		NumericExpression extent, chunkLength, oldArraySize;
		List<SymbolicExpression> elements = new LinkedList<>();
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		if (!(oldArray.type() instanceof SymbolicArrayType))
			throw new CIVLInternalException(
					"Array casting cannot be applied on non-array type object",
					source);
		if (!(targetTypeArray.type() instanceof SymbolicArrayType))
			throw new CIVLInternalException(
					"Array casting cannot cast to non-array type object",
					source);
		extent = universe.length(targetTypeArray);
		oldArraySize = universe.length(oldArray);
		chunkLength = universe.divide(oldArraySize, extent);
		if (reasoner.isValid(universe.equals(chunkLength, one))
				&& (!(((SymbolicArrayType) targetTypeArray.type())
						.elementType() instanceof SymbolicArrayType)))
			return oldArray;
		else {
			NumericExpression i = zero;
			NumericExpression endIndex = chunkLength;
			SymbolicExpression flattenOldArray = arrayFlatten(state, process,
					oldArray, arrayCapacities, source);

			if (!(((SymbolicArrayType) targetTypeArray.type()).elementType() instanceof SymbolicArrayType))
				throw new CIVLInternalException(
						"Array cannot be casted to an non-array type", source);
			claim = universe.lessThan(i, extent);
			while (reasoner.isValid(claim)) {
				SymbolicExpression subArray = symbolicAnalyzer.getSubArray(
						flattenOldArray, universe.multiply(i, chunkLength),
						endIndex, state, process, source);
				SymbolicExpression childArray;

				childArray = arrayCasting(state, process, subArray,
						universe.arrayRead(targetTypeArray, zero),
						arrayCapacities, source);

				elements.add(childArray);
				// update
				i = universe.add(i, one);
				endIndex = universe.add(endIndex, chunkLength);
				claim = universe.lessThan(i, extent);
			}
			return universe.array(elements.get(0).type(), elements);
		}
	}

	private List<SymbolicExpression> arrayFlattenList(State state,
			String process, SymbolicExpression array,
			Map<Integer, NumericExpression> arrayCapacities,
			CIVLSource civlsource) {
		List<SymbolicExpression> flattenElementList = new LinkedList<>();
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		if (array == null)
			throw new CIVLInternalException("parameter array is null.",
					civlsource);
		if (array.isNull())
			return flattenElementList;
		if (!(array.type() instanceof SymbolicCompleteArrayType))
			throw new CIVLInternalException("Cannot flatten imcomplete array",
					civlsource);
		if (!(((SymbolicArrayType) array.type()).elementType() instanceof SymbolicArrayType)) {
			flattenElementList.add(array);
			return flattenElementList;
		}
		// If the array has at least one dimension whose length is non-concrete,
		// using array lambda to flatten it.
		if (this.hasNonConcreteFactor(reasoner, array)) {
			flattenElementList.add(this.arrayLambdaFlatten(state, array,
					arrayCapacities, civlsource));
			return flattenElementList;
		}
		flattenElementList = this.arrayFlattenWorker(state, array, civlsource);
		return flattenElementList;
	}

	// TODO: if returned list size is 1 and element type is an array, the
	// element is an lamda array. the element itself is the flatten array.
	// Recursive helper function for arrayUnrolling, must be private.
	private List<SymbolicExpression> arrayFlattenWorker(State state,
			SymbolicExpression array, CIVLSource civlsource) {
		BooleanExpression pathCondition = state.getPathCondition();
		List<SymbolicExpression> flattenElementList = new LinkedList<>();
		Reasoner reasoner = universe.reasoner(pathCondition);

		if (array.isNull() || array == null)
			throw new CIVLInternalException("parameter array is null.",
					civlsource);

		if (array.type() instanceof SymbolicArrayType) {
			BooleanExpression claim;
			NumericExpression i = universe.zeroInt();
			NumericExpression length = universe.length(array);

			claim = universe.lessThan(i, length);
			if (((SymbolicArrayType) array.type()).elementType() instanceof SymbolicArrayType) {
				while (reasoner.isValid(claim)) {
					SymbolicExpression element = universe.arrayRead(array, i);

					flattenElementList.addAll(arrayFlattenWorker(state,
							element, civlsource));
					// update
					i = universe.add(i, one);
					claim = universe.lessThan(i, length);
				}
			} else {
				while (reasoner.isValid(claim)) {
					SymbolicExpression element = universe.arrayRead(array, i);

					flattenElementList.add(element);
					// update
					i = universe.add(i, one);
					claim = universe.lessThan(i, length);
				}
			}
		} else {
			flattenElementList.add(array);
		}
		return flattenElementList;
	}

	// TODO: need doc, check it carefully
	private SymbolicExpression arrayLambdaFlatten(State state,
			SymbolicExpression array,
			Map<Integer, NumericExpression> arrayCapacities,
			CIVLSource civlsource) {
		SymbolicExpression myArray = array;
		NumericSymbolicConstant index = null;
		SymbolicType elementType = null;
		SymbolicExpression arrayEleFunc = null;
		SymbolicExpression lambdaFunc;
		SymbolicExpression newArray = null;
		SymbolicCompleteArrayType newArrayType;
		int dim;
		NumericExpression capacity = one;
		NumericExpression tempIndex;
		NumericExpression newExtent;

		index = (NumericSymbolicConstant) universe.symbolicConstant(
				universe.stringObject("i"), universe.integerType());
		// From outer to inner. later from inner to outer
		dim = arrayCapacities.size() - 1;
		tempIndex = index;
		newExtent = one;
		while (arrayCapacities.containsKey(dim)) {
			NumericExpression newIndex; // new index is remainder

			capacity = arrayCapacities.get(dim);
			newIndex = universe.divide(tempIndex, capacity);
			newExtent = universe.multiply(newExtent, universe.length(myArray));
			myArray = universe.arrayRead(myArray, newIndex);
			tempIndex = universe.modulo(tempIndex, capacity);
			dim--;
		}
		elementType = myArray.type();
		arrayEleFunc = universe.canonic(myArray);
		lambdaFunc = universe.lambda(index, arrayEleFunc);
		newArrayType = universe.arrayType(elementType, newExtent);
		newArray = universe.arrayLambda(newArrayType, lambdaFunc);
		assert (newArray != null);
		return newArray;
	}

	/* ********************* Private Helper Functions ********************** */
	private boolean hasNonConcreteFactor(Reasoner reasoner,
			SymbolicExpression array) {
		NumericExpression extent;
		SymbolicExpression element = array;
		SymbolicType type = array.type();

		while (type instanceof SymbolicArrayType) {
			extent = universe.length(element);
			if (reasoner.extractNumber(extent) == null)
				return true;
			element = universe.arrayRead(element, zero);
			type = element.type();
		}
		return false;
	}

	private Pair<Evaluation, Map<Integer, NumericExpression>> pointerAddWorker(
			State state, String process, SymbolicExpression pointer,
			NumericExpression offset, boolean checkOutput, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression arrayPtr;
		SymbolicExpression parentPtr;
		ReferenceExpression parentRef;
		NumericExpression extent, index;
		ReferenceExpression ref;
		ReferenceExpression newRef;
		BooleanExpression claim, over, equal, drown;
		Evaluation eval;
		int scopeId, vid;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		claim = universe.equals(offset, zero);
		if (reasoner.isValid(claim))
			return new Pair<>(new Evaluation(state, pointer), null);
		scopeId = symbolicUtil.getDyscopeId(source, pointer);
		vid = symbolicUtil.getVariableId(source, pointer);
		pointer = this.castToArrayElementReference(state, pointer, source);
		ref = symbolicUtil.getSymRef(pointer);
		// Checking if the pointer addition will be out of bound at the current
		// dimension.
		if (ref.isArrayElementReference()) {
			arrayPtr = symbolicUtil.parentPointer(source, pointer);
			index = universe.integer(symbolicUtil
					.getArrayIndex(source, pointer));
			eval = evaluator.dereference(source, state, process, arrayPtr,
					false);
			state = eval.state;
			if (!(eval.value.type() instanceof SymbolicCompleteArrayType))
				throw new CIVLInternalException(
						"Pointer addition on a pointer to incomplete array",
						source);
			extent = ((SymbolicCompleteArrayType) eval.value.type()).extent();
			// beyond the bound
			over = universe.lessThan(extent, universe.add(index, offset));
			// lower than the bound
			drown = universe.lessThan(universe.add(index, offset), zero);
			equal = universe.equals(universe.add(index, offset), extent);
			// out of bound condition:
			// If index + offset > extent, out of bound.
			// If index + offset < 0, out of bound.
			// If index + offset == extent and the parent reference is an array
			// element reference, out of bound.(e.g. int a[2], b[2][2]. &a[2] is
			// a valid pointer, &b[0][2] should be cast to &b[1][0] unless it's
			// a sequence of memory space)
			if (reasoner.isValid(over)
					|| reasoner.isValid(drown)
					|| (reasoner.isValid(equal)
							&& symbolicUtil.getSymRef(arrayPtr)
									.isArrayElementReference() && vid != 0)) {
				NumericExpression newIndex, remainder = index;
				NumericExpression capacity = one;
				SymbolicExpression arrayRootPtr;
				List<NumericExpression> indexes = new LinkedList<>();
				Map<Integer, NumericExpression> dimCapacities;
				int dim = 1;

				// Checking if the array is an allocated memory space
				if (vid == 0)
					throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
							Certainty.PROVEABLE, process,
							"Array out of bound\n", source);
				// Computes remainder
				arrayRootPtr = this.arrayRootPtr(arrayPtr, source);
				eval = evaluator.dereference(source, state, process,
						arrayRootPtr, false);
				state = eval.state;
				dimCapacities = this.arrayCapacities(eval.value, source);
				parentPtr = arrayPtr;
				while (dimCapacities.containsKey(dim)) {
					NumericExpression oldIndex = universe.integer(symbolicUtil
							.getArrayIndex(source, parentPtr));

					capacity = dimCapacities.get(dim);
					remainder = universe.add(remainder,
							universe.multiply(oldIndex, capacity));
					dim++;
					parentPtr = symbolicUtil.parentPointer(source, parentPtr);
				}
				remainder = universe.add(remainder, offset);
				// computes new indexes
				dim--;
				while (dimCapacities.containsKey(dim)) {
					capacity = dimCapacities.get(dim);
					newIndex = universe.divide(remainder, capacity);
					remainder = universe.modulo(remainder, capacity);
					indexes.add(newIndex);
					dim--;
				}
				claim = universe.equals(remainder, zero);
				if (!reasoner.isValid(claim))
					throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
							Certainty.PROVEABLE, process,
							"Array out of bound\n", source);
				newRef = symbolicUtil.updateArrayElementReference(
						(ArrayElementReference) ref, indexes);
				eval = new Evaluation(state, symbolicUtil.makePointer(scopeId,
						vid, newRef));
				return new Pair<>(eval, dimCapacities);
			} else {
				// The (offset + index) < extent at the given dimension,
				// return new pointer easily.
				parentRef = symbolicUtil.getSymRef(arrayPtr);
				newRef = universe.arrayElementReference(parentRef,
						universe.add(index, offset));
				eval = new Evaluation(state, symbolicUtil.makePointer(scopeId,
						vid, newRef));
				return new Pair<>(eval, null);
			}
		} else {
			throw new CIVLExecutionException(ErrorKind.OUT_OF_BOUNDS,
					Certainty.PROVEABLE, process, "Array out of bound\n",
					source);
		}
	}

	// TODO: need a better name
	/**
	 * Cast an pointer to an array element reference pointer if the pointed
	 * obejct is an array.
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param pointer
	 *            The pointer needs being casted
	 * @param source
	 *            The civl source of the pointer
	 * @return The casted pointer
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression castToArrayElementReference(State state,
			SymbolicExpression pointer, CIVLSource source) {
		CIVLType objType;
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		int sid, vid;

		sid = symbolicUtil.getDyscopeId(source, pointer);
		vid = symbolicUtil.getVariableId(source, pointer);
		// If the pointer is pointing to an memory space, then no need to
		// continue casting because there won't be any multi-dimensional array
		// and "&a" and "a" when "a" is a pointer to a memory space is
		// different.
		if (vid == 0)
			return pointer;
		objType = symbolicAnalyzer.typeOfObjByPointer(source, state, pointer);
		while (objType.isArrayType()) {
			ref = universe.arrayElementReference(ref, zero);
			pointer = symbolicUtil.makePointer(sid, vid, ref);
			objType = symbolicAnalyzer.typeOfObjByPointer(source, state,
					pointer);
		}
		return symbolicUtil.makePointer(sid, vid, ref);
	}

	// From outer to inner
	private Map<Integer, NumericExpression> arrayExtents(CIVLSource source,
			SymbolicExpression array) {
		SymbolicExpression element = array;
		SymbolicType type = array.type();
		Map<Integer, NumericExpression> dimExtents = new HashMap<>();
		int dim = 0;

		if (!(type instanceof SymbolicArrayType))
			throw new CIVLInternalException(
					"Cannot get extents from an non-array object", source);
		while (type instanceof SymbolicArrayType) {
			dimExtents.put(dim, universe.length(element));
			dim++;
			element = universe.arrayRead(element, zero);
			type = element.type();
		}
		return dimExtents;
	}

	// From inner to outer (by default, it cannot assume that it starts from the
	// deepest one)
	private Map<Integer, NumericExpression> arrayIndexesByPointer(State state,
			CIVLSource source, SymbolicExpression pointer) {
		Map<Integer, NumericExpression> dimIndexes = new HashMap<>();
		int dim = 0;
		int vid = symbolicUtil.getVariableId(source, pointer);
		ReferenceExpression ref;

		// pointer = this.castToArrayElementReference(state, pointer, source);
		ref = symbolicUtil.getSymRef(pointer);
		while (ref.isArrayElementReference()) {
			dimIndexes.put(dim, ((ArrayElementReference) ref).getIndex());
			dim++;
			pointer = symbolicUtil.parentPointer(source, pointer);
			ref = symbolicUtil.getSymRef(pointer);
			if (vid == 0)
				break;
		}
		return dimIndexes;
	}

	// From inner to outer
	private Map<Integer, NumericExpression> arrayCapacities(
			SymbolicExpression array, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		NumericExpression capacity = one;
		Map<Integer, NumericExpression> dimExtents;
		Map<Integer, NumericExpression> dimCapacities = new HashMap<>();
		int dim;
		int extentIter;

		dimExtents = this.arrayExtents(source, array);
		extentIter = dimExtents.size() - 1;
		dim = 1;
		dimCapacities.put(0, capacity);
		while (dimExtents.containsKey(extentIter - 1)) {
			capacity = universe.multiply(capacity, dimExtents.get(extentIter));
			dimCapacities.put(dim, capacity);
			extentIter--;
			dim++;
		}
		return dimCapacities;
	}

	// TODO: add error check
	private NumericExpression arraySize(SymbolicExpression array,
			CIVLSource source) {
		Map<Integer, NumericExpression> dimExtents;
		NumericExpression size = one;
		int dim = 0;

		dimExtents = this.arrayExtents(source, array);
		while (dimExtents.containsKey(dim)) {
			size = universe.multiply(size, dimExtents.get(dim));
			dim++;
		}

		return size;
	}

	private SymbolicExpression arrayRootPtr(SymbolicExpression arrayPtr,
			CIVLSource source) {
		SymbolicExpression arrayRootPtr = arrayPtr;

		while (symbolicUtil.getSymRef(arrayRootPtr).isArrayElementReference())
			arrayRootPtr = symbolicUtil.parentPointer(source, arrayRootPtr);

		return arrayRootPtr;

	}
}
