package edu.udel.cis.vsl.civl.library.IF;

import java.util.List;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;

/**
 * This class provides the common data and operations of library evaluators.
 * 
 * @author Manchun Zheng
 * 
 */
public abstract class BaseLibraryEvaluator extends Library implements
		LibraryEvaluator {
	/**
	 * The evaluator for evaluating expressions.
	 */
	protected Evaluator evaluator;

	/**
	 * The model factory of the system.
	 */
	protected ModelFactory modelFactory;

	/**
	 * The state factory for state-related computation.
	 */
	protected StateFactory stateFactory;

	/* ***************************** Constructor *************************** */

	/**
	 * Creates a new instance of library enabler.
	 * 
	 * @param primaryEnabler
	 *            The enabler for normal CIVL execution.
	 * @param output
	 *            The output stream to be used in the enabler.
	 * @param modelFactory
	 *            The model factory of the system.
	 */
	protected BaseLibraryEvaluator(Evaluator evaluator,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(evaluator.universe(), symbolicUtil);
		this.evaluator = evaluator;
		this.stateFactory = evaluator.stateFactory();
		this.modelFactory = modelFactory;
	}

	/* ******************** Methods from LibraryEvaluator ****************** */

	@Override
	public Evaluation evaluateGuard(CIVLSource source, State state, int pid,
			String function, List<Expression> arguments) {
		return new Evaluation(state, universe.trueExpression());
	}

}
