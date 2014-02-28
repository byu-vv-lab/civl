package edu.udel.cis.vsl.civl.library.IF;

import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public interface LibraryEnabler extends Library {
	/**
	 * Get a system guard for a system function. This is an extra guard relating
	 * to the particular system function, and needs to be checked in addition to
	 * the "regular" guard in the transition system.
	 */
	Evaluation getGuard(CIVLSource source, State state, int pid,
			CallOrSpawnStatement call);
	
	Set<Integer> ampleSet(State state, int pid, Statement statement,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap);
}
