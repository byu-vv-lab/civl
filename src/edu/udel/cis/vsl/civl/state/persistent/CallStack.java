package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;

import com.github.krukow.clj_ds.PersistentStack;
import com.github.krukow.clj_ds.PersistentVector;
import com.github.krukow.clj_ds.Persistents;

import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class CallStack extends PersistentObject implements
		Iterable<PersistentStackEntry> {

	private final static int classCode = CallStack.class.hashCode();

	final static CallStack emptyStack = new CallStack();

	/**
	 * The call stack, represented as a persistent stack of stack entries.
	 * Method {@link PersistentStack#minus()} is the "pop": "removes" top entry.
	 * Method {@link PersistentStack#plus(Object)} is the "push": "adds" entry
	 * to top of stack. Method {@link PersistentStack#peek()} is the "peek":
	 * returns top entry on stack.
	 * 
	 */
	private PersistentVector<PersistentStackEntry> entries;

	CallStack(PersistentVector<PersistentStackEntry> entries) {
		this.entries = entries;
	}

	CallStack() {
		this.entries = Persistents.<PersistentStackEntry> vector();
	}

	@Override
	protected int computeHashCode() {
		return classCode ^ entries.hashCode();
	}

	@Override
	protected boolean computeEquals(PersistentObject obj) {
		return obj instanceof CallStack
				&& entries.equals(((CallStack) obj).entries);
	}

	PersistentStack<PersistentStackEntry> getEntries() {
		return entries;
	}

	@Override
	protected void canonizeChildren(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		// nothing to do
	}

	@Override
	protected CallStack canonize(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		return (CallStack) super.canonize(universe, canonicMap);
	}

	public CallStack pop() {
		return new CallStack(entries.minus());
	}

	public PersistentStackEntry peek() {
		return entries.peek();
	}

	public CallStack push(PersistentStackEntry entry) {
		return new CallStack(entries.plus(entry));
	}

	public CallStack replaceTop(PersistentStackEntry entry) {
		if (entry == entries.peek())
			return this;
		return new CallStack(entries.minus().plus(entry));
	}

	public int size() {
		return entries.size();
	}

	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public Iterator<PersistentStackEntry> iterator() {
		return entries.iterator();
	}

	public void print(PrintStream out, String prefix) {
		int numFrames = entries.size();

		out.println(prefix + " call stack");
		for (int i = numFrames - 1; i >= 0; i--)
			out.println(prefix + "| " + entries.get(i));
		out.flush();
	}

	CallStack renumberScopes(int[] oldToNew) {
		int size = size();
		PersistentVector<PersistentStackEntry> newEntries = entries;

		for (int i = 0; i < size; i++) {
			PersistentStackEntry oldEntry = entries.get(i);
			PersistentStackEntry newEntry = oldEntry.setScope(oldToNew[oldEntry
					.scope()]);

			if (oldEntry != newEntry)
				newEntries = newEntries.plusN(i, newEntry);
		}
		return newEntries == entries ? this : new CallStack(newEntries);
	}

}
