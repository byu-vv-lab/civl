package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.common.CommonEvaluator;
import edu.udel.cis.vsl.civl.semantics.common.CommonExecutor;
import edu.udel.cis.vsl.civl.semantics.common.CommonLibraryEvaluatorLoader;
import edu.udel.cis.vsl.civl.semantics.common.CommonLibraryExecutorLoader;
import edu.udel.cis.vsl.civl.semantics.common.CommonTransition;
import edu.udel.cis.vsl.civl.semantics.common.CommonTransitionSequence;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * Entry point of the module civl.semantics.
 * 
 * @author zmanchun
 * 
 */
public class Semantics {

	public static LibraryExecutorLoader newLibraryExecutorLoader() {
		return new CommonLibraryExecutorLoader();
	}

	public static LibraryEvaluatorLoader newLibraryEvaluatorLoader() {
		return new CommonLibraryEvaluatorLoader();
	}

	public static Executor newExecutor(GMCConfiguration config,
			ModelFactory modelFactory, StateFactory stateFactory, ErrorLog log,
			LibraryExecutorLoader loader, Evaluator evaluator,
			CIVLErrorLogger errLogger, CIVLConfiguration civlConfig) {
		return new CommonExecutor(config, modelFactory, stateFactory, log,
				loader, evaluator, errLogger, civlConfig);
	}

	public static Evaluator newEvaluator(ModelFactory modelFactory,
			StateFactory stateFactory, LibraryEvaluatorLoader loader,
			SymbolicUtility symbolicUtil, CIVLErrorLogger errLogger) {
		return new CommonEvaluator(modelFactory, stateFactory, loader,
				symbolicUtil, errLogger);
	}

	/**
	 * Create a new CIVL transition.
	 * 
	 * @param pathCondition
	 *            The path condition that should be used when executing the
	 *            statement of the transition
	 * @param pid
	 *            The process id of the process executing this transition.
	 * @param processIdentifier
	 *            The process identifier of the process executing this
	 *            transition.
	 * @param statement
	 *            The statement corresponding to this transition, which should
	 *            be atomic and deterministic.
	 * @return A new transition with the given path condition and statement.
	 */
	public static CommonTransition newTransition(
			BooleanExpression pathCondition, int pid, int processIdentifier,
			Statement statement) {
		return new CommonTransition(pathCondition, pid, processIdentifier,
				statement);
	}

	/**
	 * Create a new transition sequence.
	 * 
	 * @param state
	 *            The state of the program before this transition sequence
	 *            departs.
	 * @return A new transition sequence.
	 */
	public static TransitionSequence newTransitionSequence(State state) {
		return new CommonTransitionSequence(state);
	}
}
