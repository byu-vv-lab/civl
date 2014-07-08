/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.common.CommonTransition;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * A factory to create transitions and transition sequences.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class TransitionFactory {

	/* ***************************** Constructors ************************** */

	/**
	 * A factory to create transitions and transition sequences.
	 */
	public TransitionFactory() {
	}
	
	/* *************************** Public Methods ************************** */

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
	public CommonTransition newTransition(BooleanExpression pathCondition,
			int pid, int processIdentifier, Statement statement) {
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
	public TransitionSequence newTransitionSequence(State state) {
		return new TransitionSequence(state);
	}
}
