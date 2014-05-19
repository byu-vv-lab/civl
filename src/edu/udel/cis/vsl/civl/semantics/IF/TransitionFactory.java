/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.common.CommonCompoundTransition;
import edu.udel.cis.vsl.civl.semantics.common.CommonSingleTransition;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * A factory to create transitions and transition sequences.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class TransitionFactory {

	/**
	 * A factory to create transitions and transition sequences.
	 */
	public TransitionFactory() {
	}

	/**
	 * Create a new simple transition.
	 * 
	 * @param pathCondition
	 *            The path condition that should be used when executing the
	 *            statement
	 * @param pid
	 *            The process id of the process executing this transition.
	 * @param processIdentifier
	 *            The process identifier of the process executing this
	 *            transition.
	 * @param statement
	 *            The statement corresponding to this transition.
	 * @return A new simple transition with the given path condition and
	 *         statement.
	 */
	public CommonSingleTransition newSimpleTransition(
			BooleanExpression pathCondition, int pid, int processIdentifier,
			Statement statement) {
		return new CommonSingleTransition(pathCondition, pid,
				processIdentifier, statement);
	}

	public CompoundTransition newCompoundTransition(int pid,
			int processIdentifier) {
		return new CommonCompoundTransition(pid, processIdentifier);
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

	public CompoundTransition[] newCompoundTransitionArray(int size) {
		return new CommonCompoundTransition[size];
	}

}
