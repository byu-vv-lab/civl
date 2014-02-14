package edu.udel.cis.vsl.civl.kripke;

import java.util.ArrayList;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * EnablerPOR implements {@link EnablerIF} for CIVL models. Its basic
 * functionality is to obtain the set of enabled transitions of a certain state,
 * using the new POR as discussed in Feb 2014.
 * 
 * @author Manchun Zheng (zmanchun)
 */
public class PointeredEnabler extends Enabler implements
		EnablerIF<State, Transition, TransitionSequence> {

	/* ***************************** Constructors ************************** */

	/**
	 * Create a new instance of enabler that implements the POR based on impact
	 * memory units.
	 * 
	 * @param transitionFactory
	 *            The unique transition factory used in the system to create
	 *            transitions.
	 * @param evaluator
	 *            The unique evaluator used in the system to evaluate
	 *            expressions at a given state.
	 * @param executor
	 *            The unique executor used in the system to execute statements
	 *            at a certain state.
	 * @param showAmpleSet
	 *            The option to enable/disable the printing of ample sets at
	 *            each state.
	 */
	public PointeredEnabler(TransitionFactory transitionFactory,
			Evaluator evaluator, Executor executor, boolean showAmpleSet) {
		this.transitionFactory = transitionFactory;
		this.evaluator = evaluator;
		this.executor = executor;
		this.showAmpleSet = showAmpleSet;
		this.modelFactory = evaluator.modelFactory();
		this.universe = modelFactory.universe();
	}

	/* ************************* Methods from Enabler ********************** */

	/**
	 * The partial order reduction computes the set of processes that impact a
	 * set of memory units exclusively accessed by other processes.
	 * 
	 * @param state
	 *            The state to work with.
	 * @return The enabled transitions as an instance of TransitionSequence.
	 */
	@Override
	protected TransitionSequence enabledTransitionsPOR(State state) {
		TransitionSequence transitions = transitionFactory
				.newTransitionSequence(state);
		AmpleSetWorker ampleWorker = new AmpleSetWorker(state, this, evaluator,
				debugging, debugOut);
		ArrayList<ProcessState> processStates = new ArrayList<>(
				ampleWorker.ampleProcesses());// compute ample processes

		if (debugging || showAmpleSet) {
			debugOut.print("ample processes at state " + state.getCanonicId()
					+ ":");
			for (ProcessState p : processStates) {
				debugOut.print(p.getPid() + "\t");
			}
			debugOut.println();
		}
		// Compute the ample set (of transitions)
		for (ProcessState p : processStates) {
			TransitionSequence localTransitions = transitionFactory
					.newTransitionSequence(state);
			int pid = p.getPid();

			for (Statement s : p.getLocation().outgoing()) {
				BooleanExpression newPathCondition = newPathCondition(state,
						pid, s);
				if (!newPathCondition.isFalse()) {
					localTransitions.addAll(enabledTransitionsOfStatement(
							state, s, newPathCondition, pid, null));
				}
			}
			transitions.addAll(localTransitions);
		}
		return transitions;
	}

}
