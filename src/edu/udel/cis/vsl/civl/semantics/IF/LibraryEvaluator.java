package edu.udel.cis.vsl.civl.semantics.IF;

import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.state.IF.State;

public interface LibraryEvaluator {
	/**
	 * Gets a guard for a system function. This is an extra guard relating to
	 * the particular system function, and needs to be checked in addition to
	 * the "regular" guard in the transition system.
	 */
	Evaluation evaluateGuard(CIVLSource source, State state, int pid,
			String function, List<Expression> arguments);

}
