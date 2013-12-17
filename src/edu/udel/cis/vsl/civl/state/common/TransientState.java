/**
 * 
 */
package edu.udel.cis.vsl.civl.state.common;

import java.io.PrintStream;
import java.util.Arrays;

import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.DynamicScope;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * An instance of State represents the state of a CIVL Model. It encodes:
 * 
 * <ul>
 * <li>a sequence of processes</li>
 * <li>a sequence of dynamic scopes</li>
 * <li>for each pair of processes (p,q), a message buffer (sequence of messages
 * sent from p to q)</li>
 * <li>a path condition</li>
 * </ul>
 * 
 * In addition it has two boolean fields, seen and onStack, for use by the
 * depth-first search algorithm.
 * 
 * An instance begins in a mutable state, and become immutable upon commit. The
 * exception to immutability is the two boolean fields, which have set (and get)
 * methods. This means that states are free to share components in any way they
 * like without causing any problmes. The interface should export any methods
 * which allow the user to modify the state (with the exception of the two
 * boolean fields).
 * 
 * The two boolean fields are not used in the hashCode or equals methods, so are
 * considered "extrinsic data".
 * 
 * Processes and scopes have ID numbers.
 * 
 * @author Stephen F. Siegel (siegel)
 * @author Timothy K. Zirkel (zirkel)
 * @author Tim McClory (tmcclory)
 * 
 */
public class TransientState implements State {

	// Static fields...

	/**
	 * The number of instances of this class that have been created since the
	 * class was loaded.
	 */
	static long instanceCount = 0;

	// Instance fields...

	/**
	 * Is this State mutable?
	 */
	private boolean mutable = true;

	/**
	 * Has the hashcode on this state already been computed?
	 */
	private boolean hashed = false;

	/**
	 * If this is a canonic state (unique representative of its equivalence
	 * class), this field is the unique state ID for that class. Otherwise, it
	 * is -1.
	 */
	private int canonicId = -1;

	/**
	 * The absolutely unique ID number of this state, among all states ever
	 * created in this run of the JVM.
	 */
	private final long instanceId = instanceCount++;

	/**
	 * If the hashcode has been computed, it is cached here.
	 */
	private int hashCode = -1;

	/**
	 * processes[i] contains the process of pid i. some entries may be null.
	 */
	private ProcessState[] processStates;

	/**
	 * The dynamic scopes that exist in this state. The scope at index 0 is
	 * always the system scope.
	 */
	private DynamicScope[] scopes;

	/**
	 * Non-null boolean-valued symbolic expression.
	 */
	private BooleanExpression pathCondition;

	/**
	 * Whether this state has been seen in the DFS search.
	 */
	private boolean seen = false;

	/**
	 * Whether this state is on the DFS search stack.
	 */
	private boolean onStack = false;

	/**
	 * Minimum depth at which this state has been encountered in DFS; used for
	 * finding minimal counterexample.
	 */
	private int depth = -1;

	// Constructors...

	/**
	 * Basic constructor. The arrays are used as fields---the elements are not
	 * copied into a new array. All arguments must be non-null. Seen and onStack
	 * bits are set to false.
	 * 
	 * @param processes
	 * @param scopes
	 * @param buffers
	 * @param pathCondition
	 */
	TransientState(ProcessState[] processes, DynamicScope[] scopes,
			BooleanExpression pathCondition) {
		assert processes != null;
		assert scopes != null;
		assert pathCondition != null;
		this.processStates = processes;
		this.scopes = scopes;
		this.pathCondition = pathCondition;
	}

	// Private methods...

	// Package private methods...

	@Override
	public void commit() {
		// TODO: commit dynamicScopes, processStates
		// when they become transient
		mutable = false;
	}

	void canonize(int canonicId) {
		commit();
		this.canonicId = canonicId;
	}

	public boolean isMutable() {
		return mutable;
	}

	@Override
	public TransientState setPathCondition(BooleanExpression pathCondition) {
		if (mutable) {
			this.pathCondition = pathCondition;
			return this;
		}
		return new TransientState(processStates, scopes, pathCondition);
	}

	@Override
	public TransientState setProcessStates(ProcessState[] processStates) {
		if (mutable) {
			this.processStates = processStates;
			return this;
		}
		return new TransientState(processStates, scopes, pathCondition);
	}

	@Override
	public TransientState setScopes(DynamicScope[] scopes) {
		if (mutable) {
			this.scopes = scopes;
			return this;
		}
		return new TransientState(processStates, scopes, pathCondition);
	}

	@Override
	public TransientState setProcessState(int index, ProcessState processState) {
		int oldLength = this.processStates.length;

		if (index < oldLength) {
			if (processStates[index] == processState)
				return this;
			if (mutable) {
				processStates[index] = processState;
				return this;
			}
		}
		// index>=oldLength or immutable
		ProcessState[] newProcessStates = new ProcessState[index < oldLength ? oldLength
				: index + 1];

		System.arraycopy(this.processStates, 0, newProcessStates, 0, oldLength);
		newProcessStates[index] = processState;
		if (mutable) {
			this.processStates = newProcessStates;
			return this;
		}
		return new TransientState(newProcessStates, this.scopes,
				this.pathCondition);
	}

	@Override
	public TransientState setScope(int index, DynamicScope scope) {
		int oldLength = this.scopes.length;

		if (index < oldLength) {
			if (scopes[index] == scope)
				return this;
			if (mutable) {
				scopes[index] = scope;
				return this;
			}
		}
		// index>=oldLength or immutable
		DynamicScope[] newScopes = new DynamicScope[index < oldLength ? oldLength
				: index + 1];

		System.arraycopy(this.scopes, 0, newScopes, 0, oldLength);
		newScopes[index] = scope;
		if (mutable) {
			this.scopes = newScopes;
			return this;
		}
		return new TransientState(this.processStates, newScopes,
				this.pathCondition);
	}

	// Public methods...

	/**
	 * Returns the instance ID of this State. The is obtained from a static
	 * counter that is incremented every time a state is instantiated.
	 * 
	 * @return this state's instance ID
	 */
	@Override
	public long getInstanceId() {
		return instanceId;
	}

	/**
	 * The number of scopes, including blanks.
	 * 
	 * @return
	 */
	@Override
	public int numScopes() {
		return scopes.length;
	}

	/**
	 * The number of processes, including blanks.
	 * 
	 * @return
	 */
	@Override
	public int numProcs() {
		return processStates.length;
	}

	@Override
	public boolean isCanonic() {
		return canonicId >= 0;
	}

	/**
	 * Returns the canonicID of this state. Returns -1 if it is not canonic.
	 * 
	 * @return canonicID of this state
	 */
	@Override
	public int getCanonicId() {
		return canonicId;
	}

	/**
	 * @param pid
	 *            A process ID.
	 * @return The process associated with the ID. if non-existent.
	 * 
	 */
	@Override
	public ProcessState getProcessState(int pid) {
		return processStates[pid];
	}

	/**
	 * @return The system scope.
	 */
	public DynamicScope rootScope() {
		return scopes[0];
	}

	/**
	 * @return The system scope id.
	 * 
	 */
	@Override
	public int rootScopeID() {
		return 0;
	}

	/**
	 * @return The path condition.
	 */
	@Override
	public BooleanExpression getPathCondition() {
		return pathCondition;
	}

	/**
	 * @return Whether this state has been seen in the depth first search.
	 */
	@Override
	public boolean seen() {
		return seen;
	}

	/**
	 * @return Whether this state is on the DFS stack.
	 */
	@Override
	public boolean onStack() {
		return onStack;
	}

	/**
	 * @param seen
	 *            Whether this state has been seen in the depth first search.
	 */
	@Override
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * @param onStack
	 *            Whether this state is on the DFS stack.
	 */
	@Override
	public void setOnStack(boolean onStack) {
		this.onStack = onStack;
	}

	/**
	 * Given the id of a scope, return that dynamic scope.
	 * 
	 * @param id
	 *            The dynamic scope id number.
	 * @return The corresponding dynamic scope.
	 */
	@Override
	public DynamicScope getScope(int id) {
		return scopes[id];
	}

	@Override
	public int getParentId(int scopeId) {
		// TODO: change to state component
		return ((CommonDynamicScope) getScope(scopeId)).parent();
	}

	public DynamicScope getScope(int pid, Variable variable) {
		int scopeId = getProcessState(pid).getDyscopeId();
		DynamicScope scope;

		while (scopeId >= 0) {
			scope = getScope(scopeId);
			if (scope.lexicalScope().variables().contains(variable))
				return scope;
			scopeId = getParentId(scopeId);
		}
		throw new IllegalArgumentException("Variable not in scope: " + variable);
	}

	@Override
	public int getScopeId(int pid, Variable variable) {
		int scopeId = getProcessState(pid).getDyscopeId();
		DynamicScope scope;

		while (scopeId >= 0) {
			scope = getScope(scopeId);
			if (scope.lexicalScope().variables().contains(variable))
				return scopeId;
			scopeId = getParentId(scopeId);
		}
		throw new IllegalArgumentException("Variable not in scope: " + variable);
	}

	@Override
	public SymbolicExpression getVariableValue(int scopeId, int variableId) {
		DynamicScope scope = getScope(scopeId);

		return scope.getValue(variableId);
	}

	@Override
	public SymbolicExpression valueOf(int pid, Variable variable) {
		DynamicScope scope = getScope(pid, variable);
		int variableID = scope.lexicalScope().getVid(variable);

		return scope.getValue(variableID);
	}

	@Override
	public int hashCode() {
		if (!hashed) {
			hashCode = pathCondition.hashCode()
					^ Arrays.hashCode(processStates) ^ Arrays.hashCode(scopes);
			if (!mutable)
				hashed = true;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof TransientState) {
			TransientState that = (TransientState) object;

			if (canonicId >= 0 && that.canonicId >= 0)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			return pathCondition.equals(that.pathCondition)
					&& Arrays.equals(processStates, that.processStates)
					&& Arrays.equals(scopes, that.scopes);
		}
		return false;
	}

	// Structure:
	// State 45
	// 1. Scopes
	// 1.1. scope 45 (: null) (parent = 46)
	// 1.1.1. x = 27
	// 2. Processes
	// 2.1. process 0 (: null) or call stack
	// 2.1.1 [location=locationID,scope=dynamicScopeId] (top)
	// 3. Buffers
	// 3.1. 3->4
	// 3.1.1 Message 0
	// 3.1.1.1 tag=
	// 3.1.1.2 data=
	@Override
	public void print(PrintStream out) {
		int numScopes = numScopes();
		int numProcs = numProcs();

		out.print("State " + identifier());
		out.println();
		out.println("| Path condition");
		out.println("| | " + pathCondition);
		out.println("| Dynamic scopes");
		for (int i = 0; i < numScopes; i++) {
			DynamicScope scope = scopes[i];

			if (scope == null)
				out.println("| | scope " + i + ": null");
			else
				scope.print(out, i, "| | ");
		}
		out.println("| Process states");
		for (int i = 0; i < numProcs; i++) {
			ProcessState process = processStates[i];

			if (process == null)
				out.println("| | process " + i + ": null");
			else
				process.print(out, "| | ");
		}
		out.flush();
	}

	/**
	 * Returns a string of the form instanceId:canonicId. The instanceId alone
	 * uniquely identifies the state, but the canonicId is also useful, though
	 * it is only used for canonic states.
	 * 
	 * @return the string instanceId:canonicId
	 */
	@Override
	public String identifier() {
		return instanceId + ":" + canonicId;
	}

	@Override
	public String toString() {
		return "State " + identifier();
	}

	@Override
	public void setDepth(int value) {
		this.depth = value;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public Iterable<ProcessState> getProcessStates() {
		return Arrays.asList(processStates);
	}

}
