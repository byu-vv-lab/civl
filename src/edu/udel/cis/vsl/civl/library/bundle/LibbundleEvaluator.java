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
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicAnalyzer;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.util.IF.Pair;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericSymbolicConstant;
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
		Map<Integer, NumericExpression> arrayElementsSizes;
		SymbolicExpression startPtr, endPtr;
		Evaluation eval;
		// The reason of using map here is that we can use a "int dim;" variable
		// to control while loops and the condition can easily all be
		// "isContainsKey(dim)".
		Pair<Evaluation, Map<Integer, NumericExpression>> ret;

		startPtr = pointer;
		// The pointerAddWorker returns the evaluation containing a new pointer
		// and array information of the whole array. Array information can be
		// reused later.
		// Data stored from "pointer" to "pointer + (count - 1)"
		ret = evaluator.evaluatePointerAdd(state, process, startPtr,
				universe.subtract(count, one), false, source);
		arrayElementsSizes = ret.right;
		eval = ret.left;
		endPtr = eval.value;
		// If pointerAddWorker didn't computes array information, do it here.
		// But it's no need to computes the whole information of the array,
		// because pointerAddWorker's not doing it means new pointer and
		// original pointer are in the same dimension.
		if (arrayElementsSizes == null) {
			arrayElementsSizes = new HashMap<>();
			arrayElementsSizes.put(0, one);
		}
		try {
			eval.value = this.getDataBetween(eval.state, process, startPtr,
					endPtr, arrayElementsSizes, source);
			return eval;
		} catch (CIVLInternalException e) {
			throw new CIVLInternalException(
					"Error happend in getDataBetween()", source);
		}
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
		Map<Integer, NumericExpression> getArrayElementsSizes;
		SymbolicExpression startPtr, endPtr;
		Evaluation eval;
		Pair<Evaluation, Map<Integer, NumericExpression>> ret;
		Pair<Evaluation, SymbolicExpression> eval_and_pointer;

		startPtr = pointer;
		ret = evaluator.evaluatePointerAdd(state, process, startPtr,
				universe.subtract(count, one), checkOutput, source);
		getArrayElementsSizes = ret.right;
		eval = ret.left;
		endPtr = eval.value;
		if (getArrayElementsSizes == null) {
			getArrayElementsSizes = new HashMap<>();
			getArrayElementsSizes.put(0, one);
		}
		try {
			eval_and_pointer = this.setDataBetween(eval.state, process,
					startPtr, endPtr, dataArray, getArrayElementsSizes, source);
			return eval_and_pointer;
		} catch (CIVLInternalException e) {
			throw new CIVLInternalException(
					"Error happend in getDataBetween()", source);
		}
	}

	/* *************** Helper functions for library executor ***************** */
	/**
	 * Evaluating for bundle_unpack execution. This function returns the value
	 * of the object and the pointer to that object(the return type is a Pair).
	 * The reason that why this function need. <br>
	 * Note: Data in bundle is in the form of a unrolled one dimensional array.
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

		// Since bundle unpack is called by executeBundleUnpack, it has no need
		// to check pointer validation here.
		dataSize = universe.length(data);
		// If data size is zero, do nothing.
		if (reasoner.isValid(universe.equals(dataSize, zero))) {
			eval = evaluator.dereference(civlsource, state, process, pointer,
					false);
			return new Pair<Evaluation, SymbolicExpression>(eval, pointer);
		} else if (reasoner.isValid(universe.equals(dataSize, one))) {
			// If data size is one, reading array to get the element
			eval = new Evaluation(state, universe.arrayRead(data, zero));
			pointer = symbolicAnalyzer.castToArrayElementReference(state,
					pointer, civlsource);
			return new Pair<Evaluation, SymbolicExpression>(eval, pointer);
		}
		// If data size larger than one, return an array and the corresponding
		// pointer.
		eval_and_pointer = this.setDataFrom(state, process, pointer, dataSize,
				data, false, civlsource);
		return eval_and_pointer;
	}

	/* ******************** Array Operation Helper Function **************** */
	/**
	 * Setting a sequence of data between two array element references. Returns
	 * the settled new array and the pointer to that array.
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param startPtr
	 *            The pointer to the start position
	 * @param endPtr
	 *            The pointer to the end position
	 * @param dataArray
	 *            The sequence of data which is going to be set
	 * @param arrayElementsSizes
	 *            The capacity information of the array pointed by the startPtr
	 *            or endPtr(These two pointers point to the same object).<br>
	 *            Note: Here capacity information of an array means that for one
	 *            cell in each dimension of an array how many non-array elements
	 *            it can hold. e.g. For array <code>int a[2][2];</code>, the one
	 *            cell in deepest dimension can only hold one element while one
	 *            cell in the second deepest dimension can hold 2 elements. Here
	 *            we use 0 marking (which is key in the given map) the deepest
	 *            dimension and 1 marking the second deepest dimension and so
	 *            forth.
	 * @param source
	 *            The CIVL source of the start pointer.
	 * @return the settled new array and the pointer to that array.
	 * @throws UnsatisfiablePathConditionException
	 */
	private Pair<Evaluation, SymbolicExpression> setDataBetween(State state,
			String process, SymbolicExpression startPtr,
			SymbolicExpression endPtr, SymbolicExpression dataArray,
			Map<Integer, NumericExpression> arrayElementsSizes,
			CIVLSource source) throws UnsatisfiablePathConditionException {
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
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		// Checking if they are pointing to the same thing
		sidMatch = (symbolicUtil.getDyscopeId(source, startPtr) == symbolicUtil
				.getDyscopeId(source, endPtr));
		vidMatch = (symbolicUtil.getVariableId(source, startPtr) == symbolicUtil
				.getVariableId(source, endPtr));
		if (!(sidMatch && vidMatch))
			throw new CIVLInternalException("Object unmatch exception\n",
					source);
		startPointer = symbolicAnalyzer.castToArrayElementReference(state,
				startPtr, source);
		endPointer = symbolicAnalyzer.castToArrayElementReference(state,
				endPtr, source);
		startIndexes = symbolicAnalyzer.arrayIndexesByPointer(state, source,
				startPointer, false);
		endIndexes = symbolicAnalyzer.arrayIndexesByPointer(state, source,
				endPointer, false);
		while (!startPointer.equals(endPointer)) {
			startPos = universe.add(
					startPos,
					universe.multiply(startIndexes.get(dim),
							arrayElementsSizes.get(dim)));
			endPos = universe.add(
					endPos,
					universe.multiply(endIndexes.get(dim),
							arrayElementsSizes.get(dim)));
			dim++;
			startPointer = symbolicUtil.parentPointer(source, startPointer);
			endPointer = symbolicUtil.parentPointer(source, endPointer);
		}
		ptrInterval = universe.subtract(endPos, startPos);
		assert (reasoner.isValid(universe.lessThanEquals(zero, ptrInterval)));
		dim = 1;
		dataSize = universe.length(dataArray);
		if (!reasoner.isValid(universe.lessThanEquals(dataSize,
				universe.add(ptrInterval, one)))) {
			throw new CIVLInternalException("out of bound", source);
		}
		oldLeastCommonArray = evaluator.dereference(source, state, process,
				startPointer, false).value;
		// If the result of dereferencing is not an array type, then the
		// dataSize should only be one.
		if (!(oldLeastCommonArray.type() instanceof SymbolicArrayType)) {
			if (!reasoner.isValid(universe.equals(dataSize, one)))
				throw new CIVLInternalException("out of bound", source);
			eval = new Evaluation(state, oldLeastCommonArray);
			return new Pair<>(eval, startPtr);
		}
		// Direct assignment conditions:
		// 1. start position is zero.
		// 2. Interval between pointers equals to data size.
		// 3. The least common array capacity equals to data size.
		if (reasoner.isValid(universe.equals(startPos, zero))) {
			NumericExpression arrayCapacity = this.arraySize(
					oldLeastCommonArray, source);

			claim = universe.and(
					universe.equals(dataSize, universe.add(ptrInterval, one)),
					universe.equals(dataSize, arrayCapacity));
			if (reasoner.isValid(claim)) {
				dataArray = arrayCasting(state, process, dataArray,
						oldLeastCommonArray, source);
				eval = new Evaluation(state, dataArray);
				return new Pair<Evaluation, SymbolicExpression>(eval,
						startPointer);
			}
		}
		leastCommonArray = arrayFlatten(state, process, oldLeastCommonArray,
				source);
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
				oldLeastCommonArray, source);
		eval = new Evaluation(state, leastCommonArray);
		return new Pair<Evaluation, SymbolicExpression>(eval, startPointer);
	}

	/**
	 * Get sequence of data between two array element references. Returns the
	 * sequence of data which is in form of an one dimensional array.
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param startPtr
	 *            The pointer to the start position
	 * @param endPtr
	 *            The pointer to the end position
	 * @param arrayElementsSizes
	 *            same as the same argument in {@link #setDataBetween(State,
	 *            String, SymbolicExpression, SymbolicExpression,
	 *            SymbolicExpression, Map<Integer, NumericExpression>,
	 *            CIVLSource)}
	 * @param source
	 *            The CIVL source of start pointer.
	 * @return a sequence of data which is in form of an one dimensional array.
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression getDataBetween(State state, String process,
			SymbolicExpression startPtr, SymbolicExpression endPtr,
			Map<Integer, NumericExpression> arrayElementsSizes,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		SymbolicExpression startPointer, endPointer;
		SymbolicExpression oldLeastCommonArray = null;
		SymbolicExpression flattenedLeastComArray;
		NumericExpression startPos = zero;
		NumericExpression endPos = zero;
		NumericExpression dataLength;
		Map<Integer, NumericExpression> startIndexes;
		Map<Integer, NumericExpression> endIndexes;
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
		startPointer = symbolicAnalyzer.castToArrayElementReference(state,
				startPtr, source);
		endPointer = endPtr;
		startIndexes = symbolicAnalyzer.arrayIndexesByPointer(state, source,
				startPointer, false);
		endIndexes = symbolicAnalyzer.arrayIndexesByPointer(state, source,
				endPointer, false);
		// If sizes of the two sets are not equal which means endPointer is
		// still pointing to a array type component. Then we need cast it.
		if (startIndexes.size() != endIndexes.size()) {
			endPointer = symbolicAnalyzer.castToArrayElementReference(state,
					endPtr, source);
			endIndexes = symbolicAnalyzer.arrayIndexesByPointer(state, source,
					endPointer, false);
		}
		while (!startPointer.equals(endPointer)) {
			startPos = universe.add(
					startPos,
					universe.multiply(startIndexes.get(dim),
							arrayElementsSizes.get(dim)));
			endPos = universe.add(
					endPos,
					universe.multiply(endIndexes.get(dim),
							arrayElementsSizes.get(dim)));
			dim++;
			startPointer = symbolicUtil.parentPointer(source, startPointer);
			endPointer = symbolicUtil.parentPointer(source, endPointer);
		}
		dataLength = universe.add(universe.subtract(endPos, startPos), one);
		assert (reasoner.isValid(universe.lessThanEquals(zero, dataLength)));
		oldLeastCommonArray = evaluator.dereference(source, state, process,
				startPointer, false).value;
		if (!(oldLeastCommonArray.type() instanceof SymbolicArrayType)) {
			if (!reasoner.isValid(universe.equals(dataLength, one)))
				throw new CIVLInternalException("out of bound", source);
			return universe.array(oldLeastCommonArray.type(),
					Arrays.asList(oldLeastCommonArray));
		}
		flattenedLeastComArray = arrayFlatten(state, process,
				oldLeastCommonArray, source);
		try {
			// TODO: thow null pointer exception is bug in get sub array
			flattenedLeastComArray = symbolicAnalyzer.getSubArray(
					flattenedLeastComArray, startPos,
					universe.add(endPos, one), state, process, source);
		} catch (java.lang.NullPointerException e) {
			throw new CIVLInternalException("Get subarray from index:"
					+ startPos
					+ " to "
					+ endPos
					+ " on array:"
					+ symbolicAnalyzer.symbolicExpressionToString(source,
							state, flattenedLeastComArray), source);
		}
		return flattenedLeastComArray;
	}

	/**
	 * Flatten the given array. Here flatten means converting a nested array
	 * (which represents multiple dimensional array in CIVL) to an one
	 * dimensional array.
	 * 
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param array
	 *            The array which is going to be flatten
	 * @param civlsource
	 *            The CIVL source the array or the pointer to the array
	 * @return the flatten array
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression arrayFlatten(State state, String process,
			SymbolicExpression array, CIVLSource civlsource)
			throws UnsatisfiablePathConditionException {
		List<SymbolicExpression> flattenElementList;
		Map<Integer, NumericExpression> arrayElementsSizes;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		if (array == null)
			throw new CIVLInternalException("parameter 'array' is null.",
					civlsource);
		if (array.isNull())
			return array;
		// If the array is already a one-dimensional array no matter if the
		// length is concrete or non-concrete, return it directly.
		if (!(((SymbolicArrayType) array.type()).elementType() instanceof SymbolicArrayType))
			return array;
		// If the array has at least one dimension whose length is non-concrete,
		// using array lambda to flatten it.
		if (this.hasNonConcreteExtent(reasoner, array)) {
			arrayElementsSizes = symbolicUtil.getArrayElementsSizes(array,
					civlsource);
			return this.arrayLambdaFlatten(state, array, arrayElementsSizes,
					civlsource);
		}
		flattenElementList = this.arrayFlattenWorker(state, array, civlsource);
		if (flattenElementList.size() > 0) {
			assert (!(flattenElementList.get(0).type() instanceof SymbolicArrayType));
			return universe.array(flattenElementList.get(0).type(),
					flattenElementList);
		} else if (array instanceof SymbolicArrayType)
			return universe.emptyArray(((SymbolicArrayType) array)
					.elementType());
		else
			return universe.emptyArray(array.type());
	}

	/**
	 * Cast an array to another array. The two arrays before and after casting
	 * must be able to hold same number of non-array elements.<br>
	 * e.g. For arrays <code>int a[2][2]; int b[4]; int c[5]</code>, a and b can
	 * be casted into each other but both of them can not be casted to c.
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param process
	 *            The information of the process
	 * @param oldArray
	 *            The array before casting
	 * @param targetTypeArray
	 *            The array has the type which is the target type of casting
	 * @param source
	 *            The CIVL source of the oldArray or the pointer to OldArray
	 * @return casted array
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression arrayCasting(State state, String process,
			SymbolicExpression oldArray, SymbolicExpression targetTypeArray,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		BooleanExpression claim;
		NumericExpression extent, chunkLength, oldArraySize;
		List<SymbolicExpression> elements = new LinkedList<>();
		Reasoner reasoner = universe.reasoner(state.getPathCondition());

		if (!(oldArray.type() instanceof SymbolicCompleteArrayType))
			throw new CIVLInternalException(
					"Array casting cannot be applied on non-array type object or incomplete array",
					source);
		if (!(targetTypeArray.type() instanceof SymbolicCompleteArrayType))
			throw new CIVLInternalException(
					"Array casting cannot cast to non-array type object or incomplete array type",
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
					oldArray, source);

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
						universe.arrayRead(targetTypeArray, zero), source);

				elements.add(childArray);
				// update
				i = universe.add(i, one);
				endIndex = universe.add(endIndex, chunkLength);
				claim = universe.lessThan(i, extent);
			}
			return universe.array(elements.get(0).type(), elements);
		}
	}

	/* ********************* Private Helper Functions ********************** */
	/**
	 * Recursively flatten the given array. Only can be used on arrays have
	 * concrete lengths.
	 */
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

	/**
	 * Helper function for
	 * {@link #arrayFlatten(State, String, SymbolicExpression, CIVLSource)}.
	 * Used for dealing with arrays have non-concrete lengths.
	 */
	private SymbolicExpression arrayLambdaFlatten(State state,
			SymbolicExpression array,
			Map<Integer, NumericExpression> arrayElementsSizes,
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
		dim = arrayElementsSizes.size() - 1;
		tempIndex = index;
		newExtent = one;
		while (arrayElementsSizes.containsKey(dim)) {
			NumericExpression newIndex; // new index is remainder

			capacity = arrayElementsSizes.get(dim);
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

	/**
	 * Helper function for
	 * {@link #arrayFlatten(State , String, SymbolicExpression , CIVLSource)}.
	 * Returns true if and only if there is at least one array (in nested arrays
	 * ) has non-concrete length.
	 */
	private boolean hasNonConcreteExtent(Reasoner reasoner,
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

	/**
	 * Computes the size of the given array. Here size means the number of
	 * non-array elements that the given array can hold.
	 * 
	 * @param array
	 *            Target array
	 * @param source
	 *            CIVL source of the array or the pointer to the array
	 * @return the size of the array
	 */
	private NumericExpression arraySize(SymbolicExpression array,
			CIVLSource source) {
		Map<Integer, NumericExpression> dimExtents;
		NumericExpression size = one;
		int dim = 0;

		dimExtents = symbolicUtil.arrayExtents(source, array);
		while (dimExtents.containsKey(dim)) {
			size = universe.multiply(size, dimExtents.get(dim));
			dim++;
		}
		return size;
	}
}
