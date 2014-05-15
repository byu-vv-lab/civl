package edu.udel.cis.vsl.civl.library.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.IF.CIVLStateException;
import edu.udel.cis.vsl.civl.err.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.library.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NTReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.TupleComponentReference;

/**
 * This class implements the common logic of library executors.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public abstract class CommonLibraryExecutor extends Library implements
		LibraryExecutor {

	/* ************************** Instance Fields ************************** */

	/**
	 * Enable or disable printing. By default true, i.e., enable printing.
	 */
	protected boolean enablePrintf;

	/**
	 * The evaluator for evaluating expressions.
	 */
	protected Evaluator evaluator;

	/**
	 * The model factory of the system.
	 */
	protected ModelFactory modelFactory;

	/**
	 * The output stream to be used for printing.
	 */
	protected PrintStream output;

	protected PrintStream err;

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

	protected boolean statelessPrintf;

	protected CIVLErrorLogger errorLogger;

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
	protected CommonLibraryExecutor(Executor primaryExecutor,
			PrintStream output, PrintStream err, boolean enablePrintf,
			boolean statelessPrintf, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil) {
		super(primaryExecutor.evaluator().universe(), symbolicUtil);
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
		this.stateFactory = evaluator.stateFactory();
		this.enablePrintf = enablePrintf;
		this.statelessPrintf = statelessPrintf;
		this.output = output;
		this.err = err;
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
	protected State executeFree(State state, int pid, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
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
				heapScopeID);

		eval = getAndCheckHeapObjectPointer(heapPointer, firstElementPointer,
				pointerExpression.getSource(), state);
		state = eval.state;
		heapObjectPointer = eval.value;
		index = getMallocIndex(firstElementPointer);
		undef = heapType.getMalloc(index).getUndefinedObject();
		state = primaryExecutor.assign(source, state, heapObjectPointer, undef);
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
			CIVLSource pointerSource, State state) {
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
						CIVLStateException e = new CIVLStateException(
								ErrorKind.MALLOC, certainty,
								"Invalid pointer for heap", state,
								this.stateFactory, pointerSource);

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
			CIVLStateException e = new CIVLStateException(ErrorKind.MALLOC,
					Certainty.PROVEABLE, "Invalid pointer for heap", state,
					this.stateFactory, pointerSource);

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
}
