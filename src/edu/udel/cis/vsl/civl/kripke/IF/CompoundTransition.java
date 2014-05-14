package edu.udel.cis.vsl.civl.kripke.IF;

import edu.udel.cis.vsl.civl.state.IF.State;

public interface CompoundTransition extends Transition {
	Step getStep(int index);

	Iterable<Step> getSteps();

	int processIdentifier();

	int getNumOfSteps();

	void addStep(Step step);

	void updateFinalState(State state);
}
