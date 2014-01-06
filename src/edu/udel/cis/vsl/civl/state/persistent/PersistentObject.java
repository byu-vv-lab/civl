package edu.udel.cis.vsl.civl.state.persistent;

import java.util.Map;

import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public abstract class PersistentObject {

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

	protected abstract int computeHashCode();

	protected abstract boolean computeEquals(PersistentObject that);

	@Override
	public int hashCode() {
		if (!hashed) {
			hashCode = computeHashCode();
			hashed = true;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof PersistentObject) {
			PersistentObject that = (PersistentObject) obj;

			if (canonic && that.canonic)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			return computeEquals(that);
		}
		return false;
	}

	/**
	 * Is this object the canonical representative of its equivalence class
	 * under the "equals" method?
	 * 
	 * @return true iff this is canonic
	 */
	boolean isCanonic() {
		return canonic;
	}

	protected abstract void canonizeChildren(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap);

	protected PersistentObject canonize(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		if (canonic)
			return this;
		else {
			PersistentObject canonicObject = canonicMap.get(this);

			if (canonicObject == null) {
				canonizeChildren(universe, canonicMap);
				canonic = true;
				canonicMap.put(this, this);
				canonicObject = this;
			}
			return canonicObject;
		}
	}

}
