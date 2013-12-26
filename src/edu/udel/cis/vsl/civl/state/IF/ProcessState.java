package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;
import java.util.Iterator;

import edu.udel.cis.vsl.civl.model.IF.location.Location;

/**
 * A ProcessState represents the state of a process (thread of execution) in a
 * CIVL model. The process has an integer ID number, the PID, unique among the
 * processes in the state.
 * 
 * The state of the process is essentially a call stack. The entries on the
 * stack are "activation frames", instances of {@link StackEntry}.
 * 
 * @author siegel
 * 
 */
public interface ProcessState {

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
	 * Returns an iterable object over the entries in this stack from top to
	 * bottom. Order is fixed (will be the same each time this method is
	 * called), but not specified.
	 * 
	 * @return the entries in the stack
	 */
	Iterable<StackEntry> getStackEntries();

	/**
	 * Returns an iterator over the entries in the call stack from the bottom to
	 * the top.
	 * 
	 * @return iterator from bottom to top
	 */
	Iterator<StackEntry> bottomToTopIterator();

	/**
	 * Determines whether the process state is "purely local". This means: TODO
	 * 
	 * @return true iff this process state is purely local
	 */
	boolean isPurelyLocalProc();

	/**
	 * Prints a human-readable form of this process state.
	 * 
	 * @param out
	 *            print stream to which the output is sent
	 * @param prefix
	 *            a string to prepend to each line of output
	 */
	void print(PrintStream out, String prefix);

}
