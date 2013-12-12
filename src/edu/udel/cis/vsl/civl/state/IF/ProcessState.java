package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.location.Location;

public interface ProcessState {

	boolean hasEmptyStack();

	Location location();

	int scope();

	StackEntry peekStack();

	int stackSize();

	StackEntry getStackEntry(int i);

	boolean isPurelyLocalProc();

	int id();
	
	void print(PrintStream out, String prefix);

}
