package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;
import java.util.Map;

import com.github.krukow.clj_ds.PersistentVector;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class ValueVector extends CIVLVector<SymbolicExpression> {

	public static boolean debug = false;

	private final static int classCode = ValueVector.class.hashCode();

	ValueVector(PersistentVector<SymbolicExpression> variableValues) {
		super(variableValues);
	}

	ValueVector(SymbolicExpression value, int multiplicity) {
		super(value, multiplicity);
	}

	public ValueVector set(int index, SymbolicExpression value) {
		return new ValueVector(setVector(index, value));
	}

	@Override
	protected int computeHashCode() {
		return classCode ^ super.hashCode();
	}

	@Override
	protected boolean computeEquals(PersistentObject that) {
		return that instanceof ValueVector && super.computeEquals(that);

	}

	@Override
	protected void canonizeChildren(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		int numVars = values.size();

		for (int i = 0; i < numVars; i++) {
			SymbolicExpression value = values.get(i);

			if (!value.isCanonic())
				values = values.plusN(i, universe.canonic(value));
		}
	}

	@Override
	protected ValueVector canonize(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		return (ValueVector) super.canonize(universe, canonicMap);
	}

	void print(PrintStream out, String prefix, Scope lexicalScope) {
		int numVars = values.size();

		for (int i = 0; i < numVars; i++) {
			Variable variable = lexicalScope.variable(i);
			SymbolicExpression value = values.get(i);

			out.print(prefix + "| " + variable.name() + " = ");
			if (debug)
				out.println(value.toStringBufferLong());
			else
				out.println(value + " : " + value.type());
		}
	}

	ValueVector substitute(Map<SymbolicExpression, SymbolicExpression> map,
			SymbolicUniverse universe) {
		int numValues = values.size();
		PersistentVector<SymbolicExpression> newValues = values;

		for (int i = 0; i < numValues; i++) {
			SymbolicExpression oldValue = values.get(i);
			SymbolicExpression newValue = universe.substitute(oldValue, map);

			if (newValue != oldValue)
				newValues = newValues.plusN(i, newValue);
		}
		return newValues == values ? this : new ValueVector(newValues);
	}
	
	ValueVector substitute(Map<SymbolicExpression, SymbolicExpression> map,
			SymbolicUniverse universe, Iterable<? extends Variable> variables) {
		PersistentVector<SymbolicExpression> newValues = values;

		for (Variable variable : variables) {
			int index = variable.vid();
			SymbolicExpression oldValue = values.get(index);
			SymbolicExpression newValue = universe.substitute(oldValue, map);

			if (newValue != oldValue)
				newValues = newValues.plusN(index, newValue);
		}
		return newValues == values ? this : new ValueVector(newValues);
	}

}
