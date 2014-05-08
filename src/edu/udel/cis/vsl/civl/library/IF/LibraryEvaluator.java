package edu.udel.cis.vsl.civl.library.IF;

import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.state.IF.State;

public interface LibraryEvaluator {
	
	Evaluation evaluate(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException;
}
