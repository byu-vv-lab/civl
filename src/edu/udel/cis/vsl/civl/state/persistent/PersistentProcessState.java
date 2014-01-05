package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;
import java.util.Iterator;

import com.github.krukow.clj_ds.PersistentStack;
import com.github.krukow.clj_ds.Persistents;

import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;

public class PersistentProcessState implements ProcessState {

	/************************ Instance Fields ************************/

	/**
	 * Has the hashcode been computed and cached?
	 */
	private boolean hashed = false;

	/**
	 * The hashcode of this object. Since it is immutable, we can cache it. If
	 * the hash code has not yet been computed, this will be -1.
	 */
	private int hashCode = -1;

	/**
	 * Is this object the unique representative of its equivalence class? Used
	 * for the Flyweight Pattern, to flyweight these objects.
	 */
	private boolean canonic = false;

	/**
	 * The process ID (pid).
	 */
	private int pid;

	/**
	 * The call stack, represented as a persistent stack of stack entries.
	 * Method {@link PersistentStack#minus()} is the "pop": "removes" top entry.
	 * Method {@link PersistentStack#plus(Object)} is the "push": "adds" entry
	 * to top of stack. Method {@link PersistentStack#peek()} is the "peek":
	 * returns top entry on stack.
	 * 
	 */
	private PersistentStack<PersistentStackEntry> callStack;

	/**
	 * Number of atomic blocks that are being executing in the process.
	 * Incremented when entering an atomic block, and decremented when leaving
	 * it.
	 */
	private int atomicCount = 0;

	/************************** Constructors *************************/

	/**
	 * Constructs new PersistentProcessState with given fields. The fields are
	 * not cloned.
	 * 
	 * @param pid
	 *            the process ID, a nonnegative int
	 * @param callStack
	 *            the call stack
	 * @param atomicCount
	 *            the atomic count
	 */
	PersistentProcessState(int pid,
			PersistentStack<PersistentStackEntry> callStack, int atomicCount) {
		this.pid = pid;
		this.callStack = callStack;
		this.atomicCount = atomicCount;
	}

	/**
	 * Constructs new process state with given pid, empty call stack, and atomic
	 * count of 0.
	 * 
	 * @param pid
	 *            the process ID, a nonnegative int
	 */
	PersistentProcessState(int pid) {
		this(pid, Persistents.<PersistentStackEntry> vector(), 0);
	}

	/******************** Package-private Methods ********************/

	/**
	 * Declares this object to be the unique representative of its equivalence
	 * class under the "equals" method relation. Used for Flyweight Pattern.
	 */
	void makeCanonic() {
		// make stack entries flyweighted?
		canonic = true;
	}

	/**
	 * Is this object the unique representative of its equivalence class under
	 * the "equals" method relation?
	 * 
	 * @return true iff this is canonic
	 */
	boolean isCanonic() {
		return canonic;
	}

	/**
	 * Returns process state equivalent to this one except that PID has given
	 * value.
	 * 
	 * @param pid
	 *            a nonnegative int, the process ID
	 * @return process state equivalent to this but with new pid
	 */
	PersistentProcessState setPid(int pid) {
		return new PersistentProcessState(pid, callStack, atomicCount);
	}

	/**
	 * Returns process state equivalent to this one except that call stack has
	 * given value.
	 * 
	 * @param callStack
	 *            the new call stack
	 * @return process state equivalent to this but with new call stack
	 */
	PersistentProcessState setCallStack(
			PersistentStack<PersistentStackEntry> callStack) {
		return new PersistentProcessState(pid, callStack, atomicCount);
	}

	/**
	 * Returns process state equivalent to this one except that atomic count has
	 * given value.
	 * 
	 * @param atomicCount
	 *            a nonnegative int, the new value for atomic count
	 * @return process state equivalent to this but with new atomic count
	 */
	PersistentProcessState setAtomicCount(int atomicCount) {
		return new PersistentProcessState(pid, callStack, atomicCount);
	}

	/**
	 * Returns process state obtained by popping the call stack (i.e., removing
	 * the top entry). Note that this is not modified, since it is immutable.
	 * 
	 * Behavior is undefined if call stack is empty.
	 * 
	 * @return process state equivalent to this one but with top entry removed
	 *         from call stack
	 */
	PersistentProcessState pop() {
		return new PersistentProcessState(pid, callStack.minus(), atomicCount);
	}

	/**
	 * Returns process state obtained by pushing the given frame onto the call
	 * stack. Note that this is not modified, since it is immutable.
	 * 
	 * @return process state equivalent to this one but with new entry pushed
	 *         onto top of stack
	 */
	PersistentProcessState push(PersistentStackEntry newStackEntry) {
		return new PersistentProcessState(pid, callStack.plus(newStackEntry),
				atomicCount);
	}

	/**
	 * Returns process state obtained by replacing top entry on stack with
	 * specified entry. Note that this is not modified, since it is immutable.
	 * Equivalent to doing a pop, then push.
	 * 
	 * @return process state equivalent to this one but with top stack entry
	 *         replaced by given one
	 */
	PersistentProcessState replaceTop(PersistentStackEntry newStackEntry) {
		return new PersistentProcessState(pid, callStack.minus().plus(
				newStackEntry), atomicCount);
	}

	/*********************** Methods from Object *********************/

	@Override
	public int hashCode() {
		if (!hashed) {
			hashCode = callStack.hashCode() ^ (514229 * pid)
					^ (39916801 * atomicCount);
			hashed = true;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof PersistentProcessState) {
			PersistentProcessState that = (PersistentProcessState) obj;

			if (canonic && that.canonic)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			return pid == that.pid && atomicCount == that.atomicCount
					&& callStack.equals(that.callStack);
		}
		return false;
	}

	@Override
	public String toString() {
		return "State of process " + pid + " (call stack length = "
				+ callStack.size() + ")";
	}

	/******************* Methods from ProcessState *******************/

	@Override
	public boolean hasEmptyStack() {
		return callStack.isEmpty();
	}

	@Override
	public Location getLocation() {
		return callStack.peek().location();
	}

	@Override
	public int getPid() {
		return pid;
	}

	@Override
	public int getDyscopeId() {
		return callStack.peek().scope();
	}

	@Override
	public StackEntry peekStack() {
		return callStack.peek();
	}

	@Override
	public int stackSize() {
		return callStack.size();
	}

	@Override
	public Iterable<PersistentStackEntry> getStackEntries() {
		return callStack;
	}

	@Override
	public Iterator<PersistentStackEntry> bottomToTopIterator() {
		return callStack.iterator();
	}

	@Override
	public boolean isPurelyLocalProc() {
		// TODO: this result should be stored in the location.
		for (Statement s : getLocation().outgoing())
			if (!s.isPurelyLocal())
				return false;
		return true;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		int numFrames = callStack.size();
		StackEntry[] entries = callStack.toArray(new StackEntry[numFrames]);

		out.println(prefix + "process " + pid + " call stack");
		for (int i = numFrames - 1; i >= 0; i--)
			out.println(prefix + "| " + entries[i]);
		out.flush();
	}

	@Override
	public PersistentProcessState incrementAtomicCount() {
		return new PersistentProcessState(pid, callStack, atomicCount + 1);
	}

	@Override
	public PersistentProcessState decrementAtomicCount() {
		return new PersistentProcessState(pid, callStack, atomicCount - 1);
	}

	@Override
	public boolean inAtomic() {
		return atomicCount > 0;
	}

	@Override
	public int atomicCount() {
		return atomicCount;
	}

}
