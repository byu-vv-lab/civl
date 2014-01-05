package edu.udel.cis.vsl.civl.state.persistent;

import java.io.PrintStream;
import java.util.Map;

import com.github.krukow.clj_ds.PersistentVector;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class PersistentState implements State {

	/************************* Static Fields *************************/

	/**
	 * The number of instances of this class that have been created since the
	 * class was loaded.
	 */
	static long instanceCount = 0;

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
	 * The process states. Entry at position i ontains the state of process of
	 * pid i. Some entries may be null.
	 */
	private PersistentVector<PersistentProcessState> processStates;

	/**
	 * The dynamic scopes that exist in this state. The scope at position 0 is
	 * always the system scope.
	 */
	private PersistentVector<PersistentDynamicScope> scopes;

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

	/************************ Static Methods *************************/

	static PersistentState newState(PersistentState state,
			PersistentVector<PersistentProcessState> processStates,
			PersistentVector<PersistentDynamicScope> scopes,
			BooleanExpression pathCondition) {
		PersistentState result = new PersistentState(
				processStates == null ? state.processStates : processStates,
				scopes == null ? state.scopes : scopes,
				pathCondition == null ? state.pathCondition : pathCondition);

		return result;
	}

	/************************** Constructors *************************/

	/**
	 * Constructs new instance with given fields. Nothing is cloned.
	 * 
	 * @param processStates
	 *            the process states; element at position i is state of process
	 *            of PID i; some entries may be null
	 * @param scopes
	 *            the dynamic scopes; element at position i is dyscope of
	 *            dyscope ID i; no null entries allowed
	 * @param pathCondition
	 *            non-null boolean valued symbolic expression
	 */
	PersistentState(PersistentVector<PersistentProcessState> processStates,
			PersistentVector<PersistentDynamicScope> scopes,
			BooleanExpression pathCondition) {
		this.processStates = processStates;
		this.scopes = scopes;
		this.pathCondition = pathCondition;
	}

	/******************** Package-private Methods ********************/

	/**
	 * Returns the instance ID of this State. The is obtained from a static
	 * counter that is incremented every time a state is instantiated.
	 * 
	 * @return this state's instance ID
	 */
	long getInstanceId() {
		return instanceId;
	}

	boolean isCanonic() {
		return canonicId >= 0;
	}

	/**
	 * Returns the canonicID of this state. Returns -1 if it is not canonic.
	 * 
	 * @return canonicID of this state
	 */
	int getCanonicId() {
		return canonicId;
	}

	/**
	 * Implements the flyweight pattern: if there already exists a scope which
	 * is equivalent to the given scope, return that one, otherwise, add scope
	 * to table and return it.
	 * 
	 * @param map
	 *            the map used to record the scopes
	 * @param expression
	 *            the scope to be flyweighted
	 * @return the unique representative of the scope or the scope itself
	 */
	private PersistentDynamicScope canonic(PersistentDynamicScope scope,
			Map<PersistentDynamicScope, PersistentDynamicScope> scopeMap,
			SymbolicUniverse universe) {
		PersistentDynamicScope canonicScope = scopeMap.get(scope);

		if (canonicScope == null) {
			scope.makeCanonic(universe);
			scopeMap.put(scope, scope);
			return scope;
		}
		return canonicScope;
	}

	/**
	 * Implements the flyweight pattern: if there already exists a process which
	 * is equivalent to the given process, return that one, otherwise, add
	 * process to table and return it.
	 * 
	 * @param map
	 *            the map used to record the processes
	 * @param expression
	 *            the process to be flyweighted
	 * @return the unique representative of the process or the process itself
	 */
	private PersistentProcessState canonic(PersistentProcessState processState,
			Map<PersistentProcessState, PersistentProcessState> processMap) {
		PersistentProcessState canonicProcessState = processMap
				.get(processState);

		if (canonicProcessState == null) {
			processState.makeCanonic();
			processMap.put(processState, processState);
			return processState;
		}
		return canonicProcessState;
	}

	void makeCanonic(int canonicId, SymbolicUniverse universe,
			Map<PersistentDynamicScope, PersistentDynamicScope> scopeMap,
			Map<PersistentProcessState, PersistentProcessState> processMap) {
		int numProcs = processStates.size();
		int numScopes = scopes.size();

		pathCondition = (BooleanExpression) universe.canonic(pathCondition);
		for (int i = 0; i < numProcs; i++) {
			PersistentProcessState processState = processStates.get(i);

			if (!processState.isCanonic())
				processStates = processStates.plusN(i,
						canonic(processState, processMap));
		}
		for (int i = 0; i < numScopes; i++) {
			PersistentDynamicScope scope = scopes.get(i);

			if (!scope.isCanonic())
				scopes = scopes.plusN(i, canonic(scope, scopeMap, universe));
		}
		this.canonicId = canonicId;
	}

	PersistentDynamicScope getScope(int pid, Variable variable) {
		int scopeId = getProcessState(pid).getDyscopeId();
		Scope variableScope = variable.scope();
		PersistentDynamicScope scope;

		while (scopeId >= 0) {
			scope = getScope(scopeId);
			if (scope.lexicalScope() == variableScope)
				return scope;
			scopeId = getParentId(scopeId);
		}
		throw new IllegalArgumentException("Variable not in scope: " + variable);
	}

	/*********************** Methods from Object *********************/

	/*********************** Methods from State **********************/

	@Override
	public String identifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() {
	}

	@Override
	public int numScopes() {
		return scopes.size();
	}

	@Override
	public int numProcs() {
		return processStates.size();
	}

	@Override
	public int rootScopeID() {
		return 0;
	}

	@Override
	public BooleanExpression getPathCondition() {
		return pathCondition;
	}

	@Override
	public boolean seen() {
		return seen;
	}

	@Override
	public boolean onStack() {
		return onStack;
	}

	@Override
	public void setSeen(boolean value) {
		this.seen = value;
	}

	@Override
	public void setOnStack(boolean onStack) {
		this.onStack = onStack;
	}

	@Override
	public int getParentId(int scopeId) {
		return getScope(scopeId).getParent();

	}

	@Override
	public int getScopeId(int pid, Variable variable) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SymbolicExpression getVariableValue(int scopeId, int variableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SymbolicExpression valueOf(int pid, Variable variable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(PrintStream out) {
		// TODO Auto-generated method stub

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
	public PersistentProcessState getProcessState(int pid) {
		return processStates.get(pid);
	}

	@Override
	public PersistentDynamicScope getScope(int id) {
		return scopes.get(id);
	}

	@Override
	public Iterable<? extends ProcessState> getProcessStates() {
		return processStates;
	}

	@Override
	public State setPathCondition(BooleanExpression pathCondition) {
		return new PersistentState(processStates, scopes, pathCondition);
	}

	@Override
	public int numberOfReachers(int sid) {
		return scopes.get(sid).numberOfReachers();
	}

	@Override
	public boolean reachableByProcess(int sid, int pid) {
		return scopes.get(sid).reachableByProcess(pid);
	}

	@Override
	public PersistentState setVariable(int vid, int scopeId,
			SymbolicExpression value) {
		// TODO Auto-generated method stub
		return null;
	}

}
