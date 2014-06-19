package edu.udel.cis.vsl.civl.library.IF;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.util.IF.Pair;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NTReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.TupleComponentReference;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

/**
 * This class provides the common data and operations of library executors.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public abstract class BaseLibraryExecutor extends Library implements
		LibraryExecutor {

	/* ************************** Instance Fields ************************** */

	/**
	 * The evaluator for evaluating expressions.
	 */
	protected Evaluator evaluator;

	/**
	 * The model factory of the system.
	 */
	protected ModelFactory modelFactory;

	/**
	 * The primary executor of the system.
	 */
	protected Executor primaryExecutor;

	/**
	 * The state factory for state-related computation.
	 */
	protected StateFactory stateFactory;

	/**
	 * The static model of the program.
	 */
	protected Model model;

	// protected boolean statelessPrintf;

	protected CIVLErrorLogger errorLogger;

	protected CIVLConfiguration civlConfig;

	/* **************************** Constructors *************************** */

	/**
	 * Creates a new instance of a library executor.
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
	public BaseLibraryExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig) {
		super(name, primaryExecutor.evaluator().universe(), symbolicUtil);
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
		this.stateFactory = evaluator.stateFactory();
		this.civlConfig = civlConfig;
		this.modelFactory = modelFactory;
		this.model = modelFactory.model();
		this.errorLogger = primaryExecutor.errorLogger();
	}

	/* ************************* Protected Methods ************************* */

	/**
	 * Executes the function call "$free(*void)": removes from the heap the
	 * object referred to by the given pointer.
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
	protected State executeFree(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues,
			CIVLSource source) throws UnsatisfiablePathConditionException {
		Expression pointerExpression = arguments[0];
		SymbolicExpression firstElementPointer = argumentValues[0];
		CIVLHeapType heapType = modelFactory.heapType();
		SymbolicExpression heapScopeID = universe.tupleRead(
				firstElementPointer, universe.intObject(0));
		SymbolicExpression heapObjectPointer;
		Evaluation eval;
		int index;
		SymbolicExpression undef;
		SymbolicExpression heapPointer = evaluator.heapPointer(source, state,
				process, heapScopeID);

		eval = getAndCheckHeapObjectPointer(heapPointer, firstElementPointer,
				pointerExpression.getSource(), state, process);
		state = eval.state;
		heapObjectPointer = eval.value;
		index = getMallocIndex(firstElementPointer);
		undef = heapType.getMalloc(index).getUndefinedObject();
		state = primaryExecutor.assign(source, state, process,
				heapObjectPointer, undef);
		return state;
	}

	/* ************************** Private Methods ************************** */

	/**
	 * Obtain a heap object via a certain heap object pointer.
	 * 
	 * @param heapPointer
	 *            The heap pointer.
	 * @param pointer
	 *            The heap object pointer.
	 * @param pointerSource
	 *            The source code element of the pointer.
	 * @param state
	 *            The current state
	 * @return The heap object pointer and the new state if any side effect.
	 */
	private Evaluation getAndCheckHeapObjectPointer(
			SymbolicExpression heapPointer, SymbolicExpression pointer,
			CIVLSource pointerSource, State state, String process) {
		SymbolicExpression objectPointer = symbolicUtil.parentPointer(
				pointerSource, pointer);

		if (objectPointer != null) {
			SymbolicExpression fieldPointer = symbolicUtil.parentPointer(
					pointerSource, objectPointer);

			if (fieldPointer != null) {
				SymbolicExpression actualHeapPointer = symbolicUtil
						.parentPointer(pointerSource, fieldPointer);

				if (actualHeapPointer != null) {
					BooleanExpression pathCondition = state.getPathCondition();
					BooleanExpression claim = universe.equals(
							actualHeapPointer, heapPointer);
					ResultType valid = universe.reasoner(pathCondition)
							.valid(claim).getResultType();
					ReferenceExpression symRef;

					if (valid != ResultType.YES) {
						Certainty certainty = valid == ResultType.NO ? Certainty.PROVEABLE
								: Certainty.MAYBE;
						CIVLExecutionException e = new CIVLExecutionException(
								ErrorKind.MALLOC, certainty, process,
								"Invalid pointer for heap",
								symbolicUtil.stateToString(state),
								pointerSource);

						errorLogger.reportError(e);
						state = state.setPathCondition(universe.and(
								pathCondition, claim));
					}
					symRef = symbolicUtil.getSymRef(pointer);
					if (symRef instanceof ArrayElementReference) {
						NumericExpression index = ((ArrayElementReference) symRef)
								.getIndex();

						if (index.isZero()) {
							return new Evaluation(state, objectPointer);
						}
					}

				}
			}
		}
		{
			CIVLExecutionException e = new CIVLExecutionException(
					ErrorKind.MALLOC, Certainty.PROVEABLE, process,
					"Invalid pointer for heap",
					symbolicUtil.stateToString(state), pointerSource);

			errorLogger.reportError(e);
			state = state.setPathCondition(universe.falseExpression());
			return new Evaluation(state, objectPointer);
		}
	}

	/**
	 * Obtains the field ID in the heap type via a heap-object pointer.
	 * 
	 * @param pointer
	 *            The heap-object pointer.
	 * @return The field ID in the heap type of the heap-object that the given
	 *         pointer refers to.
	 */
	private int getMallocIndex(SymbolicExpression pointer) {
		// ref points to element 0 of an array:
		NTReferenceExpression ref = (NTReferenceExpression) symbolicUtil
				.getSymRef(pointer);
		// objectPointer points to array:
		NTReferenceExpression objectPointer = (NTReferenceExpression) ref
				.getParent();
		// fieldPointer points to the field:
		TupleComponentReference fieldPointer = (TupleComponentReference) objectPointer
				.getParent();
		int result = fieldPointer.getIndex().getInt();

		return result;
	}
	
	/**
	 * Given a symbolic expression of type array of char, returns a string
	 * representation. If it is a concrete array of char consisting of concrete
	 * characters, this will be the obvious string. Otherwise the result is
	 * something readable but unspecified.
	 * 
	 * @throws UnsatisfiablePathConditionException
	 */
	protected Pair<State, StringBuffer> getString(CIVLSource source, State state,
			String process, SymbolicExpression charPointer)
			throws UnsatisfiablePathConditionException {
		if (charPointer.operator() == SymbolicOperator.CONCRETE) {
			SymbolicSequence<?> originalArray;
			int int_arrayIndex;
			StringBuffer result = new StringBuffer();

			if (charPointer.type() instanceof SymbolicArrayType) {
				originalArray = (SymbolicSequence<?>) charPointer.argument(0);
				int_arrayIndex = 0;
			} else {
				SymbolicExpression arrayPointer = symbolicUtil.parentPointer(
						source, charPointer);
				ArrayElementReference arrayRef = (ArrayElementReference) symbolicUtil
						.getSymRef(charPointer);
				NumericExpression arrayIndex = arrayRef.getIndex();
				Evaluation eval = evaluator.dereference(source, state, process,
						arrayPointer, false);

				state = eval.state;
				originalArray = (SymbolicSequence<?>) eval.value.argument(0);
				int_arrayIndex = symbolicUtil.extractInt(source, arrayIndex);
			}
			result = symbolicUtil.charArrayToString(source, originalArray,
					int_arrayIndex, false);
			return new Pair<>(state, result);
		} else
			throw new CIVLUnimplementedFeatureException("non-concrete strings",
					source);
	}
}
