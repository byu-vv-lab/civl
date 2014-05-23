package edu.udel.cis.vsl.civl.kripke.common;

import java.util.ArrayList;
import java.util.List;

import edu.udel.cis.vsl.civl.kripke.IF.AtomicStep;
import edu.udel.cis.vsl.civl.kripke.IF.TraceStep;
import edu.udel.cis.vsl.civl.state.IF.State;

public class CommonTraceStep implements TraceStep {

	private List<AtomicStep> steps;

	private int processIdentifier;

	public CommonTraceStep(int processIdentifier) {
		steps = new ArrayList<>();
		this.processIdentifier = processIdentifier;
	}


	@Override
	public State result() {
		return steps.get(steps.size() - 1).result();
	}

	@Override
	public void addAtomicStep(AtomicStep step) {
		this.steps.add(step);
	}

	@Override
	public void setResult(State state) {
		steps.get(steps.size() - 1).setResult(state);
	}

	@Override
	public int getNumOfSteps() {
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

}
