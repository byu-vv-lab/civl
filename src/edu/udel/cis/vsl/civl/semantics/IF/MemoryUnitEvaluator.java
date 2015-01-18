package edu.udel.cis.vsl.civl.semantics.IF;

import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.expression.MemoryUnitExpression;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public interface MemoryUnitEvaluator {
	State evaluates(State state, int pid, MemoryUnitExpression memUnit,
			Set<SymbolicExpression> result)
			throws UnsatisfiablePathConditionException;
}
