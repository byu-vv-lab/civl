package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;

import com.github.krukow.clj_ds.PersistentSet;
import com.github.krukow.clj_ds.PersistentVector;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.DynamicScope;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class PersistentDynamicScope implements DynamicScope {

	/************************* Static Fields *************************/

	private static boolean debug = false;

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
	 * Non-null static scope to which this dynamic scope is associated.
	 */
	private Scope lexicalScope;

	/**
	 * The dynamic scope ID of the parent dynamic scope of this dynamic scope.
	 * If this is the root dynamic scope, it has no parent, and this will be -1.
	 */
	private int parent;

	/**
	 * Non-null array of variable values. The symbolic expression in position i
	 * is the value of the variable of index i. May contain null values.
	 */
	private PersistentVector<SymbolicExpression> variableValues;

	/**
	 * Sets of PIDs of processes that can reach this dynamic scope. How to do a
	 * persistent set of Integers?
	 */
	private PersistentSet<Integer> reachers;

	/************************** Constructors *************************/

	/**
	 * Creates new PersistentDynamicScope using the given fields. The data is
	 * not cloned, i.e., the given fields become the fields of this object.
	 * 
	 * @param lexicalScope
	 *            the lexical (static) scope of which this dynamic scope is an
	 *            instance
	 * @param parent
	 *            the dynamic scope ID of the parent dynamic scope, or -1 if
	 *            this is the root dynamic scope
	 * @param variableValues
	 *            the values to assign to each variable in the static scope;
	 *            must have size equal to the number of variables in the static
	 *            scope; must not contain any null values (but may contain the
	 *            symbolic expression NULL, which is not null)
	 * @param reachers
	 *            the set of PIDs of the processes which can "reach" this
	 *            dynamic scope starting from a dynamic scope references from a
	 *            frame in the proc's call stack and following the parent edges
	 *            in the dyscope tree
	 */
	PersistentDynamicScope(Scope lexicalScope, int parent,
			PersistentVector<SymbolicExpression> variableValues,
			PersistentSet<Integer> reachers) {
		assert variableValues != null
				&& variableValues.size() == lexicalScope.numVariables();
		this.lexicalScope = lexicalScope;
		this.parent = parent;
		this.variableValues = variableValues;
		this.reachers = reachers;
	}

	/******************** Package-private Methods ********************/

	/**
	 * Is this object the canonical representative of its equivalence class
	 * under the "equals" method?
	 * 
	 * @return true iff this is canonic
	 */
	boolean isCanonic() {
		return canonic;
	}

	/**
	 * Declares this object to be the unique representative of its equivalence
	 * class under the "equals" method. In addition, make all of its "children"
	 * fields canonic.
	 * 
	 * @param universe
	 *            the symbolic universe that will be used to "canonize" the
	 *            variable values
	 */
	void makeCanonic(SymbolicUniverse universe) {
		int numVars = variableValues.size();

		canonic = true;
		for (int i = 0; i < numVars; i++) {
			SymbolicExpression value = variableValues.get(i);

			if (!value.isCanonic())
				variableValues = variableValues.plusN(i,
						universe.canonic(value));
		}
	}

	/**
	 * Returns the dyscope ID of the parent of this dynamic scope, or -1 if this
	 * dyscope is the root scope (and therefore has no parent).
	 * 
	 * @return dyscope ID of parent dyscope or -1
	 */
	int getParent() {
		return parent;
	}

	/**
	 * Returns the set of PIDs of the processes which can "reach" this dynamic
	 * scope. A process can reach this dyscope in a state if there is a path (in
	 * the directed graph in which the nodes are the dyscopes and the edges are
	 * the parent edges) starting from a dyscope which is referenced from one of
	 * the frames in the proc's call stack to this dyscope.
	 * 
	 * Since it is an immutable structure no one has to know if it is a copy or
	 * the field itself.
	 * 
	 * @return set of PIDs of procs which can reach this dyscope
	 */
	PersistentSet<Integer> getReachers() {
		return reachers;
	}

	/**
	 * Returns the vector variable values for this dyscope. Since it is an
	 * immutable structure no one has to know if it is a copy or the field
	 * itself.
	 * 
	 * @return the variable values
	 */
	PersistentVector<SymbolicExpression> getVariableValues() {
		return variableValues;
	}

	/**
	 * Returns a PersistentDynamicScope which is the same as this one except
	 * that the parent field has the given value. This is the ImmutablePattern
	 * at work.
	 * 
	 * @param newParent
	 *            new value for parent field
	 * @return dynamic scope like this one but with new parent value
	 */
	PersistentDynamicScope setParent(int newParent) {
		if (newParent == parent)
			return this;
		return newParent == parent ? this : new PersistentDynamicScope(
				lexicalScope, newParent, variableValues, reachers);
	}

	/**
	 * Returns a PersistentDynamicScope which is the same as this one except
	 * that the reachers field has the given value. This is the ImmutablePattern
	 * at work.
	 * 
	 * @param reachers
	 *            new value for reachers field
	 * @return dynamic scope like this one but with new reachers value
	 */
	PersistentDynamicScope setReachers(PersistentSet<Integer> reachers) {
		return reachers == this.reachers ? this : new PersistentDynamicScope(
				lexicalScope, parent, variableValues, reachers);
	}

	/**
	 * Returns a PersistentDynamicScope which is the same as this one except
	 * that the variableValues field has the given value. This is the
	 * ImmutablePattern at work.
	 * 
	 * @param newVariableValues
	 *            new value for variableValues field
	 * @return dynamic scope like this one but with new variableValues field
	 */
	PersistentDynamicScope setVariableValues(
			PersistentVector<SymbolicExpression> newVariableValues) {
		return newVariableValues == variableValues ? this
				: new PersistentDynamicScope(lexicalScope, parent,
						newVariableValues, reachers);
	}

	/**
	 * Returns the number of variables in this dynamic scope, which is the same
	 * as the number in the static scope.
	 * 
	 * @return number of variables
	 */
	int numberOfVariables() {
		return variableValues.size();
	}

	/**
	 * How many processes can reach this dynamic scope? A process p can reach a
	 * dynamic scope d iff there is a path starting from a dynamic scope which
	 * is referenced in a frame on p's call stack to d, following the "parent"
	 * edges in the scope tree.
	 * 
	 * @return the number of processes which can reach this dynamic scope
	 */
	int numberOfReachers() {
		return reachers.size();
	}

	/**
	 * Is this dynamic scope reachable by the process with the given PID?
	 * 
	 * @param pid
	 *            PID of a process; a nonnegative integer
	 * @return true iff this dynamic scope is reachable from the process with
	 *         pid PID
	 */
	boolean reachableByProcess(int pid) {
		return reachers.contains(pid);
	}

	/**
	 * Prints human readable representation of this dynamic scope.
	 * 
	 * @param out
	 *            print stream to which the output should be sent
	 * @param id
	 *            some string which should be used to identify this dynamic
	 *            scope
	 * @param prefix
	 *            a string which should be inserted at the beginning of each
	 *            line of output
	 */
	void print(PrintStream out, String id, String prefix) {
		int numVars = lexicalScope.numVariables();
		boolean first = true;

		out.println(prefix + "scope " + id + " (parent=" + parent + ", static="
				+ lexicalScope.id() + ")");
		out.print(prefix + "| reachers: ");
		for (Integer j : reachers) {
			if (first)
				first = false;
			else
				out.print(",");
			out.print(j);
		}
		out.println();
		for (int i = 0; i < numVars; i++) {
			Variable variable = lexicalScope.variable(i);
			SymbolicExpression value = variableValues.get(i);

			out.print(prefix + "| " + variable.name() + " = ");
			if (debug)
				out.println(value.toStringBufferLong());
			else
				out.println(value + " : " + value.type());
		}
		out.flush();
	}

	/*********************** Methods from Object *********************/

	@Override
	public int hashCode() {
		if (!hashed) {
			hashCode = lexicalScope.hashCode() ^ (1017 * parent)
					^ variableValues.hashCode() ^ reachers.hashCode();
			hashed = true;
		}
		return hashCode;
	}

	/**
	 * Is this dynamic scope equal to the give object? It is equal iff: obj is
	 * an instance of PersistentDynamicScope, the lexical scopes are equal, the
	 * parents are equal integers, the variable values are equal as vectors, and
	 * the reachers are equal as sets.
	 * 
	 * @return true iff the given obj is a persistent dynamic scope equal to
	 *         this one
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof PersistentDynamicScope) {
			PersistentDynamicScope that = (PersistentDynamicScope) obj;

			if (canonic && that.canonic)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			return lexicalScope.equals(that.lexicalScope)
					&& parent == that.parent
					&& variableValues.equals(that.variableValues)
					&& reachers.equals(that.reachers);
		}
		return false;
	}

	@Override
	public String toString() {
		return "DynamicScope[static=" + lexicalScope.id() + ", parent="
				+ parent + "]";
	}

	/******************** Methods from DynamicScope *******************/

	@Override
	public SymbolicExpression getValue(int vid) {
		return variableValues.get(vid);
	}

	@Override
	public Scope lexicalScope() {
		return lexicalScope;
	}

	@Override
	public PersistentDynamicScope setValue(int vid, SymbolicExpression value) {
		return new PersistentDynamicScope(lexicalScope, parent,
				variableValues.plusN(vid, value), reachers);
	}

	@Override
	public Iterable<SymbolicExpression> getValues() {
		return variableValues;
	}

	@Override
	public void print(PrintStream out, String prefix) {
		print(out, "", prefix);
	}
}
