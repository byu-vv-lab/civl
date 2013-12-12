package edu.udel.cis.vsl.civl.state.IF;

import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.state.common.StackEntry;

public interface ProcessState {

	boolean hasEmptyStack();

	Location location();

	int scope();

	StackEntry peekStack();

	int stackSize();

	StackEntry getStackEntry(int i);

	boolean isPurelyLocalProc();

	int id();

}
