/**
 * 
 */
package edu.udel.cis.vsl.civl.state.immutable;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

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
public class ImmutableProcessState implements ProcessState {

	/**
	 * An iterator that iterates over the elements of an array in reverse order
	 * (i.e., starting with highest-index and moving down to 0).
	 * 
	 * @author siegel
	 * 
	 */
	class ReverseIterator implements Iterator<StackEntry> {

		/************************* Instance Fields *************************/

		/**
		 * The array over which we are iterating.
		 */
		private StackEntry[] array;

		/**
		 * The index of the next element that will be returned by the next call
		 * to method {@link #next()}.
		 */
		private int i = array.length - 1;

		/******************** Package-private Methods ********************/

		/**
		 * Creates a new reverse iterator for the given array.
		 * 
		 * @param array
		 *            array over which to iterate
		 */
		ReverseIterator(StackEntry[] array) {
			this.array = array;
		}

		/****************** Methods from Iterator ****************/

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public StackEntry next() {
			StackEntry result = array[i];

			i--;
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/************************* Instance Fields *************************/

	/**
	 * Number of atomic blocks that are being executing in the process.
	 * Incremented when entering an atomic block, and decremented when leaving
	 * it.
	 */
	private int atomicCount = 0;

	/**
	 * A non-null array. Entry 0 is the TOP of the stack.
	 */
	private StackEntry[] callStack;

	private boolean canonic = false;

	private int hashCode = -1;

	private boolean hashed = false;

	/**
	 * The process ID (pid).
	 */
	private int pid;

	/************************** Constructors *************************/

	/**
	 * A new process state with empty stack.
	 * 
	 * @param id
	 *            The unique process ID.
	 */
	ImmutableProcessState(int pid) {
		this.pid = pid;
		callStack = new ImmutableStackEntry[0];
	}

	ImmutableProcessState(ImmutableProcessState oldProcess, int newPid) {
		this.pid = newPid;
		this.callStack = oldProcess.callStack;
		this.atomicCount = oldProcess.atomicCount;
	}

	ImmutableProcessState(int pid, StackEntry[] stack, int atomicCount) {
		this.pid = pid;
		this.callStack = stack;
		this.atomicCount = atomicCount;
	}

	/******************** Package-private Methods ********************/

	ImmutableProcessState copy() {
		ImmutableStackEntry[] newStack = new ImmutableStackEntry[callStack.length];

		System.arraycopy(callStack, 0, newStack, 0, callStack.length);
		return new ImmutableProcessState(pid, newStack, this.atomicCount);
	}
	
	void makeCanonic() {
		canonic = true;
	}

	ImmutableProcessState pop() {
		ImmutableStackEntry[] newStack = new ImmutableStackEntry[callStack.length - 1];

		System.arraycopy(callStack, 1, newStack, 0, callStack.length - 1);
		return new ImmutableProcessState(pid, newStack, this.atomicCount);
	}

	ImmutableProcessState push(ImmutableStackEntry newStackEntry) {
		ImmutableStackEntry[] newStack = new ImmutableStackEntry[callStack.length + 1];

		System.arraycopy(callStack, 0, newStack, 1, callStack.length);
		newStack[0] = newStackEntry;
		return new ImmutableProcessState(pid, newStack, this.atomicCount);
	}

	ImmutableProcessState replaceTop(ImmutableStackEntry newStackEntry) {
		int length = callStack.length;
		ImmutableStackEntry[] newStack = new ImmutableStackEntry[length];

		System.arraycopy(callStack, 1, newStack, 1, length - 1);
		newStack[0] = newStackEntry;
		return new ImmutableProcessState(pid, newStack, this.atomicCount);
	}

	/**
	 * @param id
	 *            The unique process ID.
	 */
	void setId(int pid) {
		this.pid = pid;
	}

	/******************* Methods from ProcessState *******************/

	@Override
	public int atomicCount() {
		return this.atomicCount;
	}
	
	@Override
	public Iterator<StackEntry> bottomToTopIterator() {
		return new ReverseIterator(callStack);
	}
	
	@Override
	public ProcessState decrementAtomicCount() {
		return new ImmutableProcessState(this.pid, this.callStack,
				this.atomicCount - 1);
	}
	
	@Override
	public int getDyscopeId() {
		return callStack[0].scope();
	}

	@Override
	public Location getLocation() {
		return callStack[0].location();
	}

	@Override
	public int getPid() {
		return pid;
	}
	
	@Override
	public Iterable<StackEntry> getStackEntries() {
		return Arrays.asList(callStack);
	}

	@Override
	public boolean hasEmptyStack() {
		return callStack.length == 0;
	}
	
	@Override
	public boolean inAtomic() {
		return this.atomicCount > 0;
	}
	
	@Override
	public ProcessState incrementAtomicCount() {
		return new ImmutableProcessState(this.pid, this.callStack,
				this.atomicCount + 1);
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

	/**
	 * {@inheritDoc} Look at the first entry on the call stack, but do not remove it.
	 * 
	 * @return {@inheritDoc} The first entry on the call stack. Null if empty.
	 */
	@Override
	public StackEntry peekStack() {
		return callStack[0];
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
	public int stackSize() {
		return callStack.length;
	}
	
	/******************* Methods from Object *******************/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof ImmutableProcessState) {
			ImmutableProcessState that = (ImmutableProcessState) obj;

			if (canonic && that.canonic)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			if (!Arrays.equals(callStack, that.callStack))
				return false;
			if (pid != that.pid)
				return false;
			if (this.atomicCount != that.atomicCount)
				return false;
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if (!hashed) {
			hashCode = this.atomicCount
					^ (31 * (Arrays.hashCode(callStack) ^ (48729 * pid)));
			hashed = true;
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "State of process " + pid + " (call stack length = "
				+ callStack.length + ")";
	}

	/******************** Public Methods ********************/

	public void commit() {
	}
	
	/**
	 * Returns i-th entry on stack, where 0 is the TOP of the stack, and
	 * stackSize-1 is the BOTTOM of the stack.
	 * 
	 * @param i
	 *            int in [0,stackSize-1]
	 * @return i-th entry on stack
	 */
	public StackEntry getStackEntry(int i) {
		return callStack[i];
	}
	
	public boolean isCanonic() {
		return canonic;
	}

	public boolean isMutable() {
		return false;
	}

	public ProcessState setPid(int pid) {
		return new ImmutableProcessState(pid, callStack, this.atomicCount);
	}
	
	public ProcessState setStackEntries(StackEntry[] frames) {
		return new ImmutableProcessState(pid, frames, this.atomicCount);
	}

	public ProcessState setStackEntry(int index, StackEntry frame) {
		int n = callStack.length;
		StackEntry[] newStack = new StackEntry[n];

		System.arraycopy(callStack, 0, newStack, 0, n);
		newStack[index] = frame;
		return new ImmutableProcessState(pid, newStack, this.atomicCount);
	}
	
}
