package edu.udel.cis.vsl.civl.kripke.common;

import java.util.ArrayList;
import java.util.List;

import edu.udel.cis.vsl.civl.kripke.IF.AtomicStep;
import edu.udel.cis.vsl.civl.kripke.IF.TraceStep;
import edu.udel.cis.vsl.civl.state.IF.State;

public class NullTraceStep implements TraceStep {

	private State state;

	public NullTraceStep(State state) {
		this.state = state;
	}

	@Override
	public State result() {
		return state;
	}

	@Override
	public void addAtomicStep(AtomicStep step) {
	}

	@Override
	public void setResult(State state) {
		this.state = state;
	}

	@Override
	public int getNumOfSteps() {
		return 0;
	}

	@Override
	public int processIdentifier() {
		return -1;
	}

	@Override
	public List<AtomicStep> getAtomicSteps() {
		return new ArrayList<AtomicStep>();
	}
}
