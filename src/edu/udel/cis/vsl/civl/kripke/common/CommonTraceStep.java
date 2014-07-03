package edu.udel.cis.vsl.civl.kripke.common;

import java.util.ArrayList;
import java.util.List;

import edu.udel.cis.vsl.civl.kripke.IF.AtomicStep;
import edu.udel.cis.vsl.civl.kripke.IF.TraceStep;
import edu.udel.cis.vsl.civl.state.IF.State;

/**
 * This represents a trace executing from a certain state for a certain process.
 * TODO: cleaner way of setPostState();
 * 
 * @author Manchun Zheng
 * 
 */
public class CommonTraceStep implements TraceStep {

	/* *************************** Instance Fields ************************* */

	/**
	 * The list of atomic steps contained in this trace in sequential order.
	 */
	private List<AtomicStep> steps;

	/**
	 * The identifier of the process that this trace belongs to.
	 */
	private int processIdentifier;
	
	private State finalState = null;

	/* ***************************** Constructors ************************** */

	/**
	 * Creates a new instance of trace step for a given process.
	 * 
	 * @param processIdentifier
	 *            The identifier of the process of this trace.
	 */
	public CommonTraceStep(int processIdentifier) {
		steps = new ArrayList<>();
		this.processIdentifier = processIdentifier;
	}

	/* *********************** Methods from TraceStep ********************* */

	@Override
	public State postState() {
		return steps.get(steps.size() - 1).getPostState();
	}

	@Override
	public void addAtomicStep(AtomicStep step) {
		this.steps.add(step);
	}

	@Override
	public void setPostState(State state) {
		steps.get(steps.size() - 1).setPostState(state);
	}

	@Override
	public int getNumAtomicSteps() {
		return steps.size();
	}

	@Override
	public int processIdentifier() {
		return this.processIdentifier;
	}

	@Override
	public List<AtomicStep> getAtomicSteps() {
		return this.steps;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		boolean first = true;

		result.append("p");
		result.append(this.processIdentifier);
		result.append(":\n");
		for (AtomicStep step : this.steps) {
			if (first)
				first = false;
			else
				result.append("\n");
			result.append("| ");
			result.append(step.toString());
		}
		return result.toString();
	}

	@Override
	public void complete() {
		// TODO Auto-generated method stub
		
	}

	public State getFinalState() {
		return finalState;
	}

	public void setFinalState(State finalState) {
		this.finalState = finalState;
	}

}
