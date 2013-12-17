/**
 * 
 */
package edu.udel.cis.vsl.civl.state.common;

import java.io.PrintStream;
import java.util.Arrays;

import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;

/**
 * An instance of Process represents the state of a process (thread of
 * execution) in a Chapel model. The process has an id.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * @author Timothy J. McClory (tmcclory)
 * 
 */
public class CommonProcessState implements ProcessState {

	private boolean hashed = false;

	boolean canonic = false;

	private int hashCode = -1;

	/**
	 * The process ID (pid).
	 */
	private int pid;

	/**
	 * A non-null array. Entry 0 is the TOP of the stack.
	 */
	private StackEntry[] callStack;

	/**
	 * A new process state with empty stack.
	 * 
	 * @param id
	 *            The unique process ID.
	 */
	CommonProcessState(int pid) {
		this.pid = pid;
		callStack = new CommonStackEntry[0];
	}

	CommonProcessState(int pid, StackEntry[] stack) {
		assert stack != null;
		this.pid = pid;
		callStack = stack;
	}

	CommonProcessState(CommonProcessState oldProcess, int newPid) {
		this.pid = newPid;
		this.callStack = oldProcess.callStack;
	}

	/**
	 * @return The unique process ID.
	 */
	@Override
	public int getPid() {
		return pid;
	}

	/**
	 * @param id
	 *            The unique process ID.
	 */
	void setId(int pid) {
		this.pid = pid;
	}

	CommonProcessState copy() {
		CommonStackEntry[] newStack = new CommonStackEntry[callStack.length];

		System.arraycopy(callStack, 0, newStack, 0, callStack.length);
		return new CommonProcessState(pid, newStack);
	}

	@Override
	public boolean hasEmptyStack() {
		return callStack.length == 0;
	}

	/**
	 * @return The current location of this process.
	 */
	@Override
	public Location getLocation() {
		return callStack[0].location();
	}

	/**
	 * @return The id of the current dynamic scope of this process.
	 */
	@Override
	public int getDyscopeId() {
		return callStack[0].scope();
	}

	/**
	 * Look at the first entry on the call stack, but do not remove it.
	 * 
	 * @return The first entry on the call stack. Null if empty.
	 */

	@Override
	public StackEntry peekStack() {
		return callStack[0];
	}

	@Override
	public int stackSize() {
		return callStack.length;
	}

	/**
	 * Returns i-th entry on stack, where 0 is the TOP of the stack, and
	 * stackSize-1 is the BOTTOM of the stack.
	 * 
	 * @param i
	 *            int in [0,stackSize-1]
	 * @return i-th entry on stack
	 */
	@Override
	public StackEntry getStackEntry(int i) {
		return callStack[i];
	}

	CommonProcessState pop() {
		CommonStackEntry[] newStack = new CommonStackEntry[callStack.length - 1];

		System.arraycopy(callStack, 1, newStack, 0, callStack.length - 1);
		return new CommonProcessState(pid, newStack);
	}

	CommonProcessState push(CommonStackEntry newStackEntry) {
		CommonStackEntry[] newStack = new CommonStackEntry[callStack.length + 1];

		System.arraycopy(callStack, 0, newStack, 1, callStack.length);
		newStack[0] = newStackEntry;
		return new CommonProcessState(pid, newStack);
	}

	CommonProcessState replaceTop(CommonStackEntry newStackEntry) {
		int length = callStack.length;
		CommonStackEntry[] newStack = new CommonStackEntry[length];

		System.arraycopy(callStack, 1, newStack, 1, length - 1);
		newStack[0] = newStackEntry;
		return new CommonProcessState(pid, newStack);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (!hashed) {
			final int prime = 31;

			hashCode = 1;
			hashCode = prime * hashCode + Arrays.hashCode(callStack);
			hashCode = prime * hashCode + pid;
			hashed = true;
		}
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof CommonProcessState) {
			CommonProcessState that = (CommonProcessState) obj;

			if (canonic && that.canonic)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			if (!Arrays.equals(callStack, that.callStack))
				return false;
			if (pid != that.pid)
				return false;
			return true;
		}
		return false;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		out.println(prefix + "process " + pid + " call stack");
		for (int i = 0; i < callStack.length; i++) {
			StackEntry frame = callStack[i];

			out.println(prefix + "| " + frame);
		}
		out.flush();
	}

	@Override
	public String toString() {
		return "State of process " + pid + " (call stack length = "
				+ callStack.length + ")";
	}

	@Override
	public boolean isPurelyLocalProc() {
		Iterable<Statement> stmts = this.callStack[0].location().outgoing();

		for (Statement s : stmts) {
			if (!s.isPurelyLocal())
				return false;
		}

		return true;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void commit() {
	}

	@Override
	public boolean isCanonic() {
		return true;
	}

	@Override
	public int getCanonicId() {
		return 0;
	}

	@Override
	public ProcessState setPid(int pid) {
		return new CommonProcessState(pid, callStack);
	}

	@Override
	public ProcessState setStackEntry(int index, StackEntry frame) {
		int n = callStack.length;
		StackEntry[] newStack = new StackEntry[n];

		System.arraycopy(callStack, 0, newStack, 0, n);
		newStack[index] = frame;
		return new CommonProcessState(pid, newStack);
	}

	@Override
	public ProcessState setStackEntries(StackEntry[] frames) {
		return new CommonProcessState(pid, frames);
	}
}
