package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.github.krukow.clj_ds.PersistentVector;
import com.github.krukow.clj_ds.Persistents;

import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class DyscopeTree extends CIVLVector<PersistentDynamicScope> {

	private final static int classCode = DyscopeTree.class.hashCode();

	DyscopeTree(PersistentVector<PersistentDynamicScope> scopes) {
		super(scopes);
	}

	DyscopeTree() {
		super();
	}

	@Override
	protected int computeHashCode() {
		return classCode ^ super.hashCode();
	}

	@Override
	protected boolean computeEquals(PersistentObject obj) {
		return obj instanceof DyscopeTree && super.computeEquals(obj);
	}

	@Override
	protected void canonizeChildren(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		int size = values.size();

		for (int i = 0; i < size; i++) {
			PersistentDynamicScope scope = values.get(i);

			if (!scope.isCanonic())
				values = values.plusN(i, scope.canonize(universe, canonicMap));
		}
	}

	@Override
	protected DyscopeTree canonize(SymbolicUniverse universe,
			Map<PersistentObject, PersistentObject> canonicMap) {
		return (DyscopeTree) super.canonize(universe, canonicMap);
	}

	void print(PrintStream out, String prefix) {
		int numScopes = size();

		out.println(prefix + "Dynamic scopes");
		for (int i = 0; i < numScopes; i++) {
			PersistentDynamicScope scope = get(i);

			if (scope == null)
				out.println(prefix + "| scope " + i + ": null");
			else
				scope.print(out, "" + i, prefix + "| ");
		}
	}

	/**
	 * Returns a dyscope tree obtained by renumbering the scopes in this tree
	 * according to the given map on dyscope ID numbers.
	 * 
	 * oldToNew maps the old dyscope IDs to the new IDs. The length of this map
	 * must be the number of dynamic scopes in this dyscope tree. A dyscope
	 * which is being removed will have its ID mapped to -1.
	 * 
	 * The following operations are performed:
	 * 
	 * Reorder the scopes. Some of the scopes may be removed.
	 * 
	 * Update the parent dyscope IDs in each scope.
	 * 
	 * Update the value vectors in each scope: since values may include scope
	 * references, those references need to be updated.
	 * 
	 * As usual, this method does not modify this dyscope tree. If any
	 * modifications are reuqired, a new dyscope tree is returned, a la the
	 * Immutable Pattern.
	 * 
	 * @param oldToNew
	 *            mapping of old dyscope IDs to new
	 * @return a dyscope tree with scopes renumbered as specified
	 */
	DyscopeTree renumberScopes(int[] oldToNew, ModelFactory modelFactory) {
		// TODO: do this in-place instead?
		SymbolicUniverse universe = modelFactory.universe();
		PersistentVector<PersistentDynamicScope> newVector = Persistents
				.vector();
		int size = size();
		Map<SymbolicExpression, SymbolicExpression> substitutionMap = new HashMap<>(
				size);

		assert size == oldToNew.length;
		for (int i = 0; i < size; i++) {
			int newId = oldToNew[i];

			if (newId != i) {
				SymbolicExpression oldValue = modelFactory.scopeValue(i);
				SymbolicExpression newValue = modelFactory.scopeValue(newId);

				substitutionMap.put(oldValue, newValue);
			}
		}
		for (int i = 0; i < size; i++) {
			PersistentDynamicScope scope = get(i);

			scope = scope.setParent(oldToNew[scope.getParent()]);
			scope = scope.setValueVector(scope.getValueVector().substitute(
					substitutionMap, universe,
					scope.lexicalScope().variablesWithScoperefs()));
			newVector = newVector.plus(scope);
		}
		return new DyscopeTree(newVector);
	}

}
