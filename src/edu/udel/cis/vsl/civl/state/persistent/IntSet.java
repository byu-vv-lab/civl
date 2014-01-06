package edu.udel.cis.vsl.civl.state.persistent;

import java.util.Iterator;
import java.util.Map;

import com.github.krukow.clj_ds.PersistentSet;

import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class IntSet extends PersistentObject implements Iterable<Integer> {

	private final static int classCode = IntSet.class.hashCode();

	private PersistentSet<Integer> set;

	IntSet(PersistentSet<Integer> set) {
		this.set = set;
	}

	@Override
	protected int computeHashCode() {
		return classCode ^ set.hashCode();
	}

	@Override
	protected boolean computeEquals(PersistentObject that) {
		return that instanceof IntSet && set.equals(((IntSet) that).set);
	}

	@Override
	protected void canonizeChildren(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		// nothing to do
	}

	@Override
	protected IntSet canonize(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		return (IntSet) super.canonize(universe, canonicMap);
	}

	int size() {
		return set.size();
	}

	boolean contains(int value) {
		return set.contains(value);
	}

	@Override
	public Iterator<Integer> iterator() {
		return set.iterator();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = false;

		buf.append('{');
		for (Integer j : this) {
			if (first)
				first = false;
			else
				buf.append(',');
			buf.append(j);
		}
		buf.append('}');
		return buf.toString();
	}

}
