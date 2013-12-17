package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public interface State {

	/**
	 * Is this state mutable? If so, then "set" methods for dynamic scopes,
	 * process states, and path condition may actually modify this state. If
	 * not, those methods will always produce new states. The methods to set the
	 * seen bit, on stack bit, and depth will always modify this state, as they
	 * are extrinsic data.
	 * 
	 * @return true iff this state is mutable
	 */
	boolean isMutable();

	/**
	 * Is this state the canonic representative from its equivalence class?
	 * 
	 * @return true iff this state is the canonic representative from its
	 *         equivalence class
	 */
	boolean isCanonic();

	/**
	 * Makes this state immutable (and all of its subcomponents).
	 */
	void commit();

	/**
	 * Returns the instance ID of this State. The is obtained from a static
	 * counter that is incremented every time a state is instantiated.
	 * 
	 * @return this state's instance ID
	 */
	long getInstanceId();

	/**
	 * @return the number of dynamic scopes in this state, including nulls
	 */
	int numScopes();

	/**
	 * @return the number of process states in this state, including nulls.
	 */
	int numProcs();

	/**
	 * Returns the canonicID of this state. Returns -1 if it is not canonic.
	 * 
	 * @return canonicID of this state
	 */
	int getCanonicId();

	/**
	 * @return The system scope id.
	 */
	int rootScopeID();

	/**
	 * @return The path condition.
	 */
	BooleanExpression getPathCondition();

	/**
	 * @return Whether this state has been seen in the depth first search.
	 */
	boolean seen();

	/**
	 * @return Whether this state is on the DFS stack.
	 */
	boolean onStack();

	/**
	 * @param seen
	 *            Whether this state has been seen in the depth first search.
	 */
	void setSeen(boolean seen);

	/**
	 * @param onStack
	 *            Whether this state is on the DFS stack.
	 */
	void setOnStack(boolean onStack);

	/**
	 * Gets the dynamic scope ID of the parent of the dynamic scope with the
	 * given ID. If the dynamic scope with the given ID is the root scope (which
	 * has no parent), the result is undefined.
	 * 
	 * @param scopeId
	 *            a dynamic scope ID which fits in the range handled by this
	 *            state
	 * @return dynamic scope ID of the parent of the dynamic scope specified by
	 *         given ID
	 */
	int getParentId(int scopeId);

	int getScopeId(int pid, Variable variable);

	SymbolicExpression getVariableValue(int scopeId, int variableId);

	SymbolicExpression valueOf(int pid, Variable variable);

	void print(PrintStream out);

	String identifier();

	@Override
	String toString();

	void setDepth(int value);

	int getDepth();

	ProcessState getProcessState(int pid);

	DynamicScope getScope(int id);

	Iterable<ProcessState> getProcessStates();

	State setPathCondition(BooleanExpression pathCondition);

	State setProcessStates(ProcessState[] processStates);

	State setProcessState(int index, ProcessState processState);

	State setScopes(DynamicScope[] scopes);

	State setScope(int index, DynamicScope scope);

}
