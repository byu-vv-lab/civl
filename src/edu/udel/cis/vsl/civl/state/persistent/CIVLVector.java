package edu.udel.cis.vsl.civl.state.persistent;

import java.util.ArrayList;
import java.util.Iterator;

import com.github.krukow.clj_ds.PersistentVector;
import com.github.krukow.clj_ds.Persistents;

public abstract class CIVLVector<T> extends PersistentObject implements
		Iterable<T> {

	protected PersistentVector<T> values;

	CIVLVector(PersistentVector<T> values) {
		this.values = values;
	}

	CIVLVector() {
		this.values = Persistents.vector();
	}

	CIVLVector(T value, int multiplicity) {
		ArrayList<T> vals = new ArrayList<T>(multiplicity);

		for (int i = 0; i < multiplicity; i++)
			vals.add(value);
		values = Persistents.<T> vector(vals);
	}

	public PersistentVector<T> getValues() {
		return values;
	}

	public int size() {
		return values.size();
	}

	public T get(int index) {
		return values.get(index);
	}

	PersistentVector<T> setVector(int index, T value) {
		// TODO: add here short circuit if old value == new?
		return values.plusN(index, value);
	}

	public Iterator<T> iterator() {
		return values.iterator();
	}

	@Override
	protected int computeHashCode() {
		return values.hashCode();
	}

	@Override
	protected boolean computeEquals(PersistentObject that) {
		return that instanceof CIVLVector
				&& values.equals(((CIVLVector<?>) that).values);
	}

	// protected abstract T canonizeElement(SymbolicUniverse universe,
	// Map<PersistentObject, PersistentObject> canonicMap, T element);
	//
	// @Override
	// protected void canonizeChildren(SymbolicUniverse universe,
	// Map<PersistentObject, PersistentObject> canonicMap) {
	// int size = values.size();
	//
	// for (int i = 0; i < size; i++) {
	// T value = values.get(i);
	//
	// if (!value.isCanonic())
	// variableValues = variableValues.plusN(i,
	// universe.canonic(value));
	// }
	// }

	// @Override
	// protected ValueVector canonize(SymbolicUniverse universe,
	// Map<PersistentObject, PersistentObject> canonicMap) {
	// return (ValueVector) super.canonize(universe, canonicMap);
	// }

	// void print(PrintStream out, String prefix) {
	// int size = values.size();
	//
	// for (int i = 0; i < size; i++) {
	// Variable variable = lexicalScope.variable(i);
	// SymbolicExpression value = variableValues.get(i);
	//
	// out.print(prefix + "| " + variable.name() + " = ");
	// if (debug)
	// out.println(value.toStringBufferLong());
	// else
	// out.println(value + " : " + value.type());
	// }
	// }
}
