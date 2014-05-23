package edu.udel.cis.vsl.civl.kripke.common;

import edu.udel.cis.vsl.civl.kripke.IF.AtomicStep;
import edu.udel.cis.vsl.civl.model.IF.location.Location.AtomicKind;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.civl.state.IF.State;

/**
 * This represents a statement executed from a state to a new state.
 * 
 * @author Manchun Zheng
 * 
 */
public class CommonAtomicStep implements AtomicStep {
	private State result;
	private Transition transition;

	public CommonAtomicStep(State target, Transition transition) {
		this.result = target;
		this.transition = transition;
	}

	public Transition transition() {
		return transition;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(" - ");
		result.append(transition.statement().toStepString(AtomicKind.NONE,
				transition.pid(), false));
		result.append(" -> ");
		result.append(this.result.toString());
		return result.toString();
	}

	@Override
	public void setResult(State state) {
		this.result = state;
	}

	@Override
	public State result() {
		return this.result;
	}

	@Override
	public Statement statement() {
		return transition.statement();
	}
}
