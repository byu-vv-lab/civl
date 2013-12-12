package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.common.DynamicScope;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public interface State {

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
	 * @return the number of processes in this state, including nulls.
	 */
	int numProcs();

	boolean isCanonic();

	/**
	 * Returns the canonicID of this state. Returns -1 if it is not canonic.
	 * 
	 * @return canonicID of this state
	 */
	int getId();

	/**
	 * @return The system scope id.
	 */
	int rootScopeID();

	/**
	 * @return The path condition.
	 */
	BooleanExpression pathCondition();

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

	int getParentId(int scopeId);

	int getScopeId(int pid, Variable variable);

	SymbolicExpression getVariableValue(int scopeId, int variableId);

	SymbolicExpression valueOf(int pid, Variable variable);

	void print(PrintStream out);

	String identifier();

	String toString();

	void setDepth(int value);

	int getDepth();

	ProcessState process(int pid);

	DynamicScope getScope(int id);

	// get rid of me?
	ProcessState[] processes();
}
