package edu.udel.cis.vsl.civl.semantics;

import edu.udel.cis.vsl.civl.state.common.CommonState;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * Represents the result of evaluating something that returns a symbolic type,
 * but in two parts: the (possibly) new state resulting from side-effects
 * arising from the evaluation, and the symbolic type resulting from the
 * evaluation.
 * 
 * @author siegel
 * 
 */
public class TypeEvaluation {

	public CommonState state;

	public SymbolicType type;

	public TypeEvaluation(CommonState state, SymbolicType type) {
		this.state = state;
		this.type = type;
	}

	@Override
	public String toString() {
		return "[" + state + ", " + type + "]";
	}

}
