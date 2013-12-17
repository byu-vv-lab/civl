package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.location.Location;

public interface ProcessState {

	/**
	 * Is this process state mutable?
	 * 
	 * @return true iff this is mutable
	 */
	boolean isMutable();

	/**
	 * Makes this process state immutable.
	 */
	void commit();

	/**
	 * Is this processes state canonic (for the Flyweight Pattern)?
	 * 
	 * @return true iff this is canonic
	 */
	boolean isCanonic();

	/**
	 * Returns the canonic ID of this process state.
	 * 
	 * @return the canonic ID of this process state, or -1 if it is not canonic
	 */
	int getCanonicId();

	/**
	 * Does this process state have an empty call stack?
	 * 
	 * @return true iff the call stack is empty
	 */
	boolean hasEmptyStack();

	/**
	 * Returns the location at the top of the call stack of this process, or
	 * null if the call stack is empty.
	 * 
	 * @return location at top of call stack or null
	 */
	Location getLocation();

	/**
	 * Returns the process ID (pid) of this process state. Within a fixed state,
	 * every process is assigned an integer ID which is unique. It does not
	 * necessarily stay the same from state to state though.
	 * 
	 * @return the PID of the process
	 */
	int getPid();

	/**
	 * Returns a process state identical to this one but with the pid changed to
	 * the given one. If mutable this method may modify and return this process
	 * state; otherwise a new process state is returned.
	 * 
	 * @param pid
	 *            the new pid
	 * @return a process state like this one but with the new pid
	 */
	ProcessState setPid(int pid);

	/**
	 * The ID of the dynamic scope of the top frame on the call stack. Undefined
	 * behavior if call stack is empty
	 * 
	 * @return the dyscope id of the dyscope on the top frame of the call stack
	 */
	int getDyscopeId();

	/**
	 * Returns the top frame on the call stack. Undefined behavior if call stack
	 * is empty.
	 * 
	 * @return top frame on call stack
	 */
	StackEntry peekStack();

	/**
	 * Returns the length of the call stack.
	 * 
	 * @return the length of the call stack
	 */
	int stackSize();

	/**
	 * Returns the i-th entry (activation frame) on the call stack. Call stack
	 * entries are indexed from 0, where 0 is the TOP of the stack, and
	 * stackSize-1 is the BOTTOM of the stack. Undefined behavior if i is out of
	 * range.
	 * 
	 * @param i
	 *            int in [0,stackSize-1]
	 * @return i-th entry on stack
	 */
	StackEntry getStackEntry(int i);

	// /**
	// * Returns an iterable object over the entries in this stack
	// * @return
	// */
	// Iterable<StackEntry> getStackEntries();

	ProcessState setStackEntry(int index, StackEntry frame);

	ProcessState setStackEntries(StackEntry[] frames);

	boolean isPurelyLocalProc();

	void print(PrintStream out, String prefix);

}
