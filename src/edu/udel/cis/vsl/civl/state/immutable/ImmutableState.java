/**
 * 
 */
package edu.udel.cis.vsl.civl.state.immutable;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.DynamicScope;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
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
 * The class is mostly immutable. The exception to immutability is the two
 * boolean fields, which have set (and get) methods. This means that states are
 * free to share components in any way they like without causing any problmes.
 * The interface should export any methods which allow the user to modify the
 * state (with the exception of the two boolean fields).
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
public class ImmutableState implements State {

	/**
	 * The number of instances of this class that have been created since the
	 * class was loaded.
	 */
	static long instanceCount = 0;

	/**
	 * A simple class implementing Iterable, backed by the array of process
	 * states. It is needed because this class must implement a method to return
	 * an Iterable over ProcessState. We have a field which is an array of
	 * ImmutableProcessState. This is the easiest way to get an Iterable of the
	 * right type. Only one needs to be created, so once it is created it is
	 * cached. (Due to Immutable Pattern.)
	 * 
	 * @author siegel
	 * 
	 */
	class ProcessStateIterable implements Iterable<ProcessState> {
		class ProcessStateIterator implements Iterator<ProcessState> {
			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < processStates.length;
			}

			@Override
			public ProcessState next() {
				ProcessState result = processStates[pos];

				pos++;
				return result;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public Iterator<ProcessState> iterator() {
			return new ProcessStateIterator();
		}
	}

	private Iterable<ProcessState> processStateIterable = null;

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
	private ImmutableProcessState[] processStates;

	/**
	 * The dynamic scopes that exist in this state. The scope at index 0 is
	 * always the system scope.
	 */
	private ImmutableDynamicScope[] scopes;

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

	private boolean procHashed = false;

	private int procHashCode = -1;

	private boolean scopeHashed = false;

	private int scopeHashCode = -1;

	/************************ Static Methods *************************/

	static ImmutableState newState(ImmutableState state,
			ImmutableProcessState[] processStates,
			ImmutableDynamicScope[] scopes, BooleanExpression pathCondition) {
		ImmutableState result = new ImmutableState(
				processStates == null ? state.processStates : processStates,
				scopes == null ? state.scopes : scopes,
				pathCondition == null ? state.pathCondition : pathCondition);

		if (processStates == null && state.procHashed) {
			result.procHashed = true;
			result.procHashCode = state.procHashCode;
		}
		if (scopes == null && state.scopeHashed) {
			result.scopeHashed = true;
			result.scopeHashCode = state.scopeHashCode;
		}
		return result;
	}

	/**
	 * Basic constructor. The arrays are used as fields---the elements are not
	 * copied into a new array. All arguments must be non-null. Seen and onStack
	 * bits are set to false.
	 * 
	 * @param processStates
	 * @param scopes
	 * @param buffers
	 * @param pathCondition
	 */
	ImmutableState(ImmutableProcessState[] processStates,
			ImmutableDynamicScope[] scopes, BooleanExpression pathCondition) {
		assert processStates != null;
		assert scopes != null;
		assert pathCondition != null;
		this.processStates = processStates;
		this.scopes = scopes;
		this.pathCondition = pathCondition;
	}

	/**
	 * Returns the instance ID of this State. The is obtained from a static
	 * counter that is incremented every time a state is instantiated.
	 * 
	 * @return this state's instance ID
	 */
	public long getInstanceId() {
		return instanceId;
	}

	/**
	 * Returns an array consisting of the processes in this state. The Process
	 * at entry i is the state of the process with PID i. Some entries may be
	 * null.
	 * 
	 * Modifications to this array cannot affect the state.
	 * 
	 * 
	 * @return Copy the set of processes in this state.
	 */
	public ImmutableProcessState[] copyProcessStates() {
		ImmutableProcessState[] newProcesses = new ImmutableProcessState[processStates.length];

		System.arraycopy(processStates, 0, newProcesses, 0,
				processStates.length);
		return newProcesses;
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

	/**
	 * @return Copy the set of processes in this state.
	 */
	public ImmutableProcessState[] copyAndExpandProcesses() {
		ImmutableProcessState[] newProcesses = new ImmutableProcessState[processStates.length + 1];

		System.arraycopy(processStates, 0, newProcesses, 0,
				processStates.length);
		return newProcesses;
	}

	/**
	 * @return Copy the set of scopes in this state.
	 */
	public ImmutableDynamicScope[] copyScopes() {
		ImmutableDynamicScope[] newScopes = new ImmutableDynamicScope[scopes.length];

		System.arraycopy(scopes, 0, newScopes, 0, scopes.length);
		return newScopes;
	}

	/**
	 */
	public ImmutableDynamicScope[] copyAndExpandScopes() {
		ImmutableDynamicScope[] newScopes = new ImmutableDynamicScope[scopes.length + 1];

		System.arraycopy(scopes, 0, newScopes, 0, scopes.length);
		return newScopes;
	}

	public boolean isCanonic() {
		return canonicId >= 0;
	}

	/**
	 * Returns the canonicID of this state. Returns -1 if it is not canonic.
	 * 
	 * @return canonicID of this state
	 */
	public int getCanonicId() {
		return canonicId;
	}

	void makeCanonic(int canonicId, SymbolicUniverse universe) {
		// process states and dynamic scopes,etc., are always canonic.
		pathCondition = (BooleanExpression) universe.canonic(pathCondition);
		this.canonicId = canonicId;
	}

	/**
	 * @param pid
	 *            A process ID.
	 * @return The process associated with the ID. if non-existent.
	 * 
	 */
	@Override
	public ImmutableProcessState getProcessState(int pid) {
		return processStates[pid];
	}

	/**
	 * @return The system scope.
	 */
	public ImmutableDynamicScope rootScope() {
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
	public ImmutableDynamicScope getScope(int id) {
		return scopes[id];
	}

	@Override
	public int getParentId(int scopeId) {
		return getScope(scopeId).parent();
	}

	public ImmutableDynamicScope getScope(int pid, Variable variable) {
		int scopeId = getProcessState(pid).getDyscopeId();
		Scope variableScope = variable.scope();
		ImmutableDynamicScope scope;

		while (scopeId >= 0) {
			scope = getScope(scopeId);
			if (scope.lexicalScope() == variableScope)
				return scope;
			scopeId = getParentId(scopeId);
		}
		throw new IllegalArgumentException("Variable not in scope: " + variable);
	}

	@Override
	public int getScopeId(int pid, Variable variable) {
		int scopeId = getProcessState(pid).getDyscopeId();
		Scope variableScope = variable.scope();
		DynamicScope scope;

		while (scopeId >= 0) {
			scope = getScope(scopeId);
			if (scope.lexicalScope() == variableScope)
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
			if (!procHashed) {
				procHashCode = Arrays.hashCode(processStates);
				procHashed = true;
			}
			if (!scopeHashed) {
				scopeHashCode = Arrays.hashCode(scopes);
				scopeHashed = true;
			}
			hashCode = pathCondition.hashCode() ^ procHashCode ^ scopeHashCode;
			hashed = true;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof ImmutableState) {
			ImmutableState that = (ImmutableState) object;

			if (canonicId >= 0 && that.canonicId >= 0)
				return false;
			if (hashed && that.hashed && hashCode != that.hashCode)
				return false;
			if (!pathCondition.equals(that.pathCondition))
				return false;
			if (procHashed && that.procHashed
					&& procHashCode != that.procHashCode)
				return false;
			if (scopeHashed && that.scopeHashed
					&& scopeHashCode != that.scopeHashCode)
				return false;
			if (!Arrays.equals(processStates, that.processStates))
				return false;
			if (!Arrays.equals(scopes, that.scopes))
				return false;
			return true;
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
			ImmutableDynamicScope scope = (ImmutableDynamicScope) scopes[i];

			if (scope == null)
				out.println("| | scope " + i + ": null");
			else
				scope.print(out, "" + i, "| | ");
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
		if (processStateIterable == null) {
			processStateIterable = new ProcessStateIterable();
		}
		return processStateIterable;
	}

	public boolean isMutable() {
		return false;
	}

	@Override
	public void commit() {
	}

	@Override
	public ImmutableState setPathCondition(BooleanExpression pathCondition) {
		ImmutableState result = new ImmutableState(processStates, scopes,
				pathCondition);

		if (scopeHashed) {
			result.scopeHashed = true;
			result.scopeHashCode = scopeHashCode;
		}
		if (procHashed) {
			result.procHashed = true;
			result.procHashCode = procHashCode;
		}
		return result;
	}

	public ImmutableState setProcessStates(ImmutableProcessState[] processStates) {
		ImmutableState result = new ImmutableState(processStates, scopes,
				pathCondition);

		if (scopeHashed) {
			result.scopeHashed = true;
			result.scopeHashCode = scopeHashCode;
		}
		return result;
	}

	public ImmutableState setProcessState(int index,
			ImmutableProcessState processState) {
		int n = processStates.length;
		ImmutableProcessState[] newProcessStates = new ImmutableProcessState[n];

		System.arraycopy(processStates, 0, newProcessStates, 0, n);
		newProcessStates[index] = processState;
		return new ImmutableState(newProcessStates, scopes, pathCondition);
	}

	public ImmutableState setScopes(ImmutableDynamicScope[] scopes) {
		ImmutableState result = new ImmutableState(processStates, scopes,
				pathCondition);

		if (procHashed) {
			result.procHashed = true;
			result.procHashCode = procHashCode;
		}
		return result;
	}

	public ImmutableState setScope(int index, ImmutableDynamicScope scope) {
		int n = scopes.length;
		ImmutableDynamicScope[] newScopes = new ImmutableDynamicScope[n];

		System.arraycopy(scopes, 0, newScopes, 0, n);
		newScopes[index] = scope;
		return new ImmutableState(processStates, newScopes, pathCondition);
	}

	@Override
	public int numberOfReachers(int sid) {
		return getScope(sid).numberOfReachers();
	}

	@Override
	public boolean reachableByProcess(int sid, int pid) {
		return getScope(sid).reachableByProcess(pid);
	}

	@Override
	public State setVariable(int vid, int scopeId, SymbolicExpression value) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
