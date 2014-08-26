package edu.udel.cis.vsl.civl.library.bundle;

import java.util.HashMap;
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
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

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
			eval.value = getDataBetween(eval.state, process, startPtr, endPtr,
					arrayElementsSizes, source);
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
}
