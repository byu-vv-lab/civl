package edu.udel.cis.vsl.civl.kripke.common;

import edu.udel.cis.vsl.civl.kripke.IF.AtomicStep;
import edu.udel.cis.vsl.civl.model.IF.location.Location.AtomicKind;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.civl.state.IF.State;

/**
 * This represents an atomic execution step, which represents the execution of
 * exactly one transition.
 * 
 * @author Manchun Zheng
 * 
 */
public class CommonAtomicStep implements AtomicStep {

	/* *************************** Instance Fields ************************* */

	/**
	 * The state after executing the transition
	 */
	private State result;

	/**
	 * The transition executed during this step
	 */
	private Transition transition;

	/* ***************************** Constructors ************************** */

	/**
	 * <p>
	 * Creates a new instance of an atomic step.
	 * </p>
	 * <p>
	 * Precondition: there exists a state in the state space such that executing
	 * <code>transition</code> from that state resulting in <code>target</code>
	 * state.
	 * </p>
	 * 
	 * @param target
	 * @param transition
	 */
	public CommonAtomicStep(State target, Transition transition) {
		this.result = target;
		this.transition = transition;
	}

	/* *********************** Methods from AtomicStep ********************* */

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
