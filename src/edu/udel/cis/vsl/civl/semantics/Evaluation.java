package edu.udel.cis.vsl.civl.semantics;

import edu.udel.cis.vsl.civl.state.common.CommonState;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * Represents the result of evaluating an expression in two parts: the
 * (possibly) new state resulting from side-effects arising from the evaluation,
 * and the value resulting from the evaluation.
 * 
 * @author siegel
 * 
 */
public class Evaluation {

	public CommonState state;

	public SymbolicExpression value;

	public Evaluation(CommonState state, SymbolicExpression value) {
		this.state = state;
		this.value = value;
	}

	@Override
	public String toString() {
		return "[" + state + ", " + value + "]";
	}

}
