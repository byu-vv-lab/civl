package edu.udel.cis.vsl.civl.kripke.IF;

import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.EnablerIF;

public interface Enabler extends
		EnablerIF<State, Transition, TransitionSequence> {

	/**
	 * Evaluates the guard of a system function call.
	 * 
	 * @param source
	 * @param state
	 * @param pid
	 * @param library
	 * @param function
	 * @param arguments
	 * @return
	 */
	Evaluation getSystemGuard(CIVLSource source, State state, int pid,
			String library, String function, List<Expression> arguments);
	
	/**
	 * Computes the guard of a statement. Since we have SystemGuardExpression
	 * and WaitGuardExpression, we don't need to compute the guard for system
	 * function calls and wait statements explicitly, which are now handled by
	 * the evaluator.
	 * 
	 * @param statement
	 *            The statement whose guard is to computed.
	 * @param pid
	 *            The ID of the process that the statement belongs to.
	 * @param state
	 *            The current state that the computation happens.
	 * @return The symbolic expression of the guard of the given statement.
	 */
	Evaluation getGuard(Statement statement, int pid, State state);
}
