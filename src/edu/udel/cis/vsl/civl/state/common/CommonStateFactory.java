package edu.udel.cis.vsl.civl.state.common;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * Factory to create all state objects.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * @author Timothy J. McClory (tmcclory)
 * 
 */
public class CommonStateFactory implements StateFactory {

	// *************************** Fields *****************************

	private ModelFactory modelFactory;

	private int stateCount = 0;

	private SymbolicUniverse universe;

	private Map<CommonDynamicScope, CommonDynamicScope> scopeMap = new HashMap<CommonDynamicScope, CommonDynamicScope>();

	private Map<CommonProcessState, CommonProcessState> processMap = new HashMap<CommonProcessState, CommonProcessState>();

	private Map<State, State> stateMap = new HashMap<State, State>();

	private Reasoner trueReasoner;

	// *************************** Constructors ***********************

	/**
	 * Factory to create all state objects.
	 */
	public CommonStateFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
		this.universe = modelFactory.universe();
		this.trueReasoner = universe.reasoner(universe.trueExpression());
	}

	// ************************* Helper Methods ***********************

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
	private CommonDynamicScope canonic(CommonDynamicScope scope) {
		CommonDynamicScope old = scopeMap.get(scope);

		if (old == null) {
			scope.canonic = true;
			scopeMap.put(scope, scope);
			return scope;
		}
		return old;
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
	private CommonProcessState canonic(CommonProcessState process) {
		CommonProcessState old = processMap.get(process);

		if (old == null) {
			process.canonic = true;
			processMap.put(process, process);
			return process;
		}
		return old;
	}

	private CommonProcessState process(int id, StackEntry[] stack) {
		return canonic(new CommonProcessState(id, stack));
	}

	private SymbolicExpression[] initialValues(Scope lexicalScope,
			int dynamicScopeId) {
		// TODO: special handling for input variables in root scope?

		SymbolicExpression[] values = new SymbolicExpression[lexicalScope
				.variables().size()];

		for (int i = 0; i < values.length; i++) {
			values[i] = universe.nullExpression();
		}
		return values;
	}

	private CommonDynamicScope dynamicScope(Scope lexicalScope, int parent,
			SymbolicExpression[] variableValues, BitSet reachers) {
		return canonic(new CommonDynamicScope(lexicalScope, parent,
				variableValues, reachers));
	}

	/**
	 * A dynamic scope.
	 * 
	 * @param lexicalScope
	 *            The lexical scope corresponding to this dynamic scope.
	 * @param parent
	 *            The parent of this dynamic scope. -1 only for the topmost
	 *            dynamic scope.
	 * @return A new dynamic scope.
	 */
	private CommonDynamicScope dynamicScope(Scope lexicalScope, int parent,
			int dynamicScopeId, BitSet reachers) {
		return dynamicScope(lexicalScope, parent, initialValues(// state,
				lexicalScope, dynamicScopeId), reachers);
	}

	/**
	 * Create a new call stack entry.
	 * 
	 * @param location
	 *            The location to go to after returning from this call.
	 * @param scope
	 *            The dynamic scope the process is in before the call.
	 * @param lhs
	 *            The location to store the return value. Null if non-existent.
	 */
	private CommonStackEntry stackEntry(Location location, int scope) {
		return new CommonStackEntry(location, scope);
	}

	private State collectScopes(State state) {
		int oldNumScopes = state.numScopes();
		int[] oldToNew = numberScopes((CommonState) state);
		boolean change = false;
		int newNumScopes = 0;
		CommonState newState;

		for (int i = 0; i < oldNumScopes; i++) {
			int id = oldToNew[i];

			if (id >= 0)
				newNumScopes++;
			if (!change && id != i)
				change = true;
		}
		if (!change)
			return state;

		CommonDynamicScope[] newScopes = new CommonDynamicScope[newNumScopes];
		int numProcs = state.numProcs();
		ProcessState[] newProcesses = new ProcessState[numProcs];

		for (int i = 0; i < oldNumScopes; i++) {
			int newId = oldToNew[i];

			if (newId >= 0) {
				CommonDynamicScope oldScope = (CommonDynamicScope) ((CommonState) state)
						.getScope(i);
				int oldParent = oldScope.parent();
				int newParent = (oldParent < 0 ? oldParent
						: oldToNew[oldParent]);
				CommonDynamicScope newScope = (oldParent == newParent ? oldScope
						: canonic(oldScope.changeParent(newParent)));

				newScopes[newId] = newScope;
			}
		}
		for (int pid = 0; pid < numProcs; pid++) {
			ProcessState oldProcess = ((CommonState) state)
					.getProcessState(pid);
			int stackSize = oldProcess.stackSize();
			StackEntry[] newStack = new StackEntry[stackSize];
			boolean stackChange = false;

			for (int j = 0; j < stackSize; j++) {
				StackEntry oldFrame = oldProcess.getStackEntry(j);
				int oldScope = oldFrame.scope();
				int newScope = oldToNew[oldScope];

				if (oldScope == newScope) {
					newStack[j] = oldFrame;
				} else {
					stackChange = true;
					newStack[j] = stackEntry(oldFrame.location(), newScope);
				}
			}
			if (stackChange)
				newProcesses[pid] = process(pid, newStack);
			else
				newProcesses[pid] = oldProcess;
		}
		newState = new CommonState(newProcesses, newScopes,
				state.getPathCondition());
		// Need to go through the pointers and canonicalize scope references
		newScopes = updateScopeReferencesInScopes(newState, oldToNew);
		newState = new CommonState(newProcesses, newScopes,
				state.getPathCondition());
		return newState;
	}

	/**
	 * Numbers the reachable dynamic scopes in a state in a canonical way.
	 * Scopes are numbered from 0 up, in the order in which they are encountered
	 * by iterating over the processes by increasing ID, iterating over the
	 * process' call stack frames from index 0 up, iterating over the parent
	 * scopes from the scope referenced by the frame.
	 * 
	 * Unreachable scopes are assigned the number -1.
	 * 
	 * Returns an array which of length numScopes in which the element at
	 * position i is the new ID number for the scope whose old ID number is i.
	 * Does not modify anything.
	 * 
	 * @param state
	 *            a state
	 * @return an array mapping old scope IDs to new.
	 */
	private int[] numberScopes(CommonState state) {
		int numScopes = state.numScopes();
		int numProcs = state.numProcs();
		int[] oldToNew = new int[numScopes];

		// the root dyscope is forced to be 0
		oldToNew[0] = 0;

		int nextScopeId = 1;
		for (int i = 1; i < numScopes; i++)
			oldToNew[i] = -1;
		for (int pid = 0; pid < numProcs; pid++) {
			ProcessState process = state.getProcessState(pid);
			int stackSize;

			if (process == null)
				continue;
			stackSize = process.stackSize();
			// start at bottom of stack so system scope in proc 0
			// is reached first
			for (int i = stackSize - 1; i >= 0; i--) {
				int dynamicScopeId = process.getStackEntry(i).scope();

				while (oldToNew[dynamicScopeId] < 0) {
					oldToNew[dynamicScopeId] = nextScopeId;
					nextScopeId++;
					dynamicScopeId = state.getParentId(dynamicScopeId);
					if (dynamicScopeId < 0)
						break;
				}
			}
		}
		return oldToNew;
	}

	// *********************** Exported Methods ***********************

	@Override
	public long getNumStateInstances() {
		return CommonState.instanceCount;
	}

	@Override
	public int getNumStatesSaved() {
		return stateMap.size();
	}

	@Override
	public SymbolicUniverse symbolicUniverse() {
		return universe;
	}

	@Override
	public State canonic(State state) {
		State old = stateMap.get(state);

		if (old == null) {
			((CommonState) state).setCanonicId(stateCount);
			stateCount++;
			stateMap.put(state, state);
			return state;
		}
		return old;
	}

	@Override
	public State initialState(Model model) {
		CommonState state = new CommonState(new CommonProcessState[0],
				new CommonDynamicScope[0], universe.trueExpression());
		CIVLFunction function = model.system();
		int numArgs = function.parameters().size();
		SymbolicExpression[] arguments = new SymbolicExpression[numArgs];

		// TODO: how to initialize the arguments to system function?
		state = addProcess(state, function, arguments, -1);
		return canonic(state);
	}

	/**
	 * Update the value of a dynamic variable in the state.
	 * 
	 * @param state
	 *            The old state.
	 * @param variable
	 *            The dynamic variable to update.
	 * @param pid
	 *            The pid of the process containing the variable.
	 * @param value
	 *            The new value of the dynamic variable.
	 * @return A new state that is the old state modified by updating the value
	 *         of the variable.
	 */
	@Override
	public State setVariable(State state, Variable variable, int pid,
			SymbolicExpression value) {
		int scopeId = state.getScopeId(pid, variable);

		return setVariable(state, variable.vid(), scopeId, value);
	}

	/**
	 * Update the value of a dynamic variable in the state.
	 * 
	 * @param state
	 *            The old state.
	 * @param variable
	 *            The dynamic variable to update.
	 * @param scopeID
	 *            The ID of the scope containing the variable. This version of
	 *            the method is useful when setting the target of a pointer. For
	 *            a variable in the current lexical scope, use the version of
	 *            the method without this argument.
	 * @param value
	 *            The new value of the dynamic variable.
	 * @return A new state that is the old state modified by updating the value
	 *         of the variable.
	 */
	@Override
	public State setVariable(State state, int vid, int scopeId,
			SymbolicExpression value) {
		CommonDynamicScope oldScope = (CommonDynamicScope) ((CommonState) state)
				.getScope(scopeId);
		CommonDynamicScope[] newScopes = ((CommonState) state).copyScopes();
		SymbolicExpression[] newValues = oldScope.copyValues();
		CommonDynamicScope newScope;

		newValues[vid] = value;
		newScope = dynamicScope(oldScope.lexicalScope(), oldScope.parent(),
				newValues, oldScope.reachers());
		newScopes[scopeId] = newScope;
		return new CommonState((CommonState) state, newScopes);
	}

	@Override
	public CommonState addProcess(State state, CIVLFunction function,
			SymbolicExpression[] arguments, int callerPid) {
		int numProcs = state.numProcs();
		CommonProcessState[] newProcesses;

		newProcesses = ((CommonState) state).copyAndExpandProcesses();
		newProcesses[numProcs] = process(numProcs, new CommonStackEntry[0]);
		state = new CommonState((CommonState) state, newProcesses);
		return pushCallStack2((CommonState) state, numProcs, function,
				arguments, callerPid);
	}

	@Override
	public State removeProcess(State state, int pid) {
		int numProcs = state.numProcs();
		ProcessState[] newProcesses = new ProcessState[numProcs - 1];
		CommonDynamicScope[] newScopes = null;

		for (int i = 0; i < pid; i++)
			newProcesses[i] = ((CommonState) state).getProcessState(i);
		{
			int[] oldToNewPidMap = new int[numProcs];

			for (int i = pid; i < numProcs - 1; i++)
				newProcesses[i] = canonic(new CommonProcessState(
						(CommonProcessState) ((CommonState) state)
								.getProcessState(i + 1),
						i));
			for (int i = 0; i < pid; i++)
				oldToNewPidMap[i] = i;
			oldToNewPidMap[pid] = -1;
			for (int i = pid + 1; i < numProcs; i++)
				oldToNewPidMap[i] = i - 1;
			newScopes = updateProcessReferencesInScopes(state, oldToNewPidMap);
		}
		state = new CommonState((CommonState) state, newProcesses, newScopes,
				null);
		return collectScopes((state));
	}

	private Map<SymbolicExpression, SymbolicExpression> procSubMap(
			int[] oldToNewPidMap) {
		int size = oldToNewPidMap.length;
		Map<SymbolicExpression, SymbolicExpression> result = new HashMap<SymbolicExpression, SymbolicExpression>(
				size);

		for (int i = 0; i < size; i++) {
			SymbolicExpression oldVal = modelFactory.processValue(i);
			SymbolicExpression newVal = modelFactory
					.processValue(oldToNewPidMap[i]);

			result.put(oldVal, newVal);
		}
		return result;
	}

	private Map<SymbolicExpression, SymbolicExpression> scopeSubMap(
			int[] oldToNewSidMap) {
		int size = oldToNewSidMap.length;
		Map<SymbolicExpression, SymbolicExpression> result = new HashMap<SymbolicExpression, SymbolicExpression>(
				size);

		for (int i = 0; i < size; i++) {
			SymbolicExpression oldVal = modelFactory.scopeValue(i);
			SymbolicExpression newVal = modelFactory
					.scopeValue(oldToNewSidMap[i]);

			result.put(oldVal, newVal);
		}
		return result;
	}

	/**
	 * Searches the dynamic scopes in the given state for any process reference
	 * value, and returns a new array of scopes equivalent to the old except
	 * that those process reference values have been replaced with new specified
	 * values. Used for garbage collection and canonicalization of PIDs.
	 * 
	 * Also updates the reachable BitSet in each DynamicScope: create a new
	 * BitSet called newReachable. iterate over all entries in old BitSet
	 * (reachable). If old entry is position i is true, set oldToNewPidMap[i] to
	 * true in newReachable (assuming oldToNewPidMap[i]>=0).
	 * 
	 * The method returns null if no changes were made.
	 * 
	 * @param state
	 *            a state
	 * @param oldToNewPidMap
	 *            array of length state.numProcs in which element at index i is
	 *            the new PID of the process whose old PID is i. A negative
	 *            value indicates that the process of (old) PID i is to be
	 *            removed.
	 * @return new dyanmic scopes or null
	 */
	private CommonDynamicScope[] updateProcessReferencesInScopes(State state,
			int[] oldToNewPidMap) {
		Map<SymbolicExpression, SymbolicExpression> procSubMap = procSubMap(oldToNewPidMap);
		CommonDynamicScope[] newScopes = null;
		int numScopes = state.numScopes();

		for (int i = 0; i < numScopes; i++) {
			CommonDynamicScope dynamicScope = (CommonDynamicScope) ((CommonState) state)
					.getScope(i);
			Scope staticScope = dynamicScope.lexicalScope();
			Collection<Variable> procrefVariableIter = staticScope
					.variablesWithProcrefs();
			SymbolicExpression[] newValues = null;
			BitSet oldBitSet = dynamicScope.reachers();
			BitSet newBitSet = updateBitSet(oldBitSet, oldToNewPidMap);

			for (Variable variable : procrefVariableIter) {
				int vid = variable.vid();
				SymbolicExpression oldValue = dynamicScope.getValue(vid);
				SymbolicExpression newValue = universe.substitute(oldValue,
						procSubMap);

				if (oldValue != newValue) {
					if (newValues == null)
						newValues = dynamicScope.copyValues();
					newValues[vid] = newValue;
				}
			}
			if (newValues != null || newBitSet != oldBitSet) {
				if (newScopes == null) {
					newScopes = new CommonDynamicScope[numScopes];
					for (int j = 0; j < i; j++)
						newScopes[j] = (CommonDynamicScope) ((CommonState) state)
								.getScope(j);
				}
				if (newValues == null)
					newScopes[i] = canonic(dynamicScope
							.changeReachers(newBitSet));
				else
					newScopes[i] = dynamicScope(staticScope,
							dynamicScope.parent(), newValues, newBitSet);
			} else if (newScopes != null) {
				newScopes[i] = dynamicScope;
			}
		}
		return newScopes;
	}

	/**
	 * Searches the dynamic scopes in the given state for any scope reference
	 * value, and returns a new array of scopes equivalent to the old except
	 * that those scope reference values have been replaced with new specified
	 * values. Used for garbage collection and canonicalization of scope IDs.
	 * 
	 * The method returns null if no changes were made.
	 * 
	 * @param state
	 *            a state
	 * @param oldToNewSidMap
	 * 
	 * @return new dynamic scopes
	 */
	private CommonDynamicScope[] updateScopeReferencesInScopes(
			CommonState state, int[] oldToNewSidMap) {
		Map<SymbolicExpression, SymbolicExpression> scopeSubMap = scopeSubMap(oldToNewSidMap);
		CommonDynamicScope[] newScopes = null;
		int numScopes = state.numScopes();

		newScopes = new CommonDynamicScope[numScopes];
		for (int i = 0; i < numScopes; i++) {
			CommonDynamicScope dynamicScope = (CommonDynamicScope) state
					.getScope(i);
			Scope staticScope = dynamicScope.lexicalScope();
			Collection<Variable> pointerVariableIter = staticScope
					.variablesWithPointers();
			SymbolicExpression[] newValues = null;
			// BitSet oldBitSet = dynamicScope.reachers();
			// BitSet newBitSet = updateBitSet(oldBitSet, oldToNewPidMap);

			for (Variable variable : pointerVariableIter) {
				int vid = variable.vid();
				SymbolicExpression oldValue = dynamicScope.getValue(vid);

				if (oldValue != null && !oldValue.isNull()) {
					SymbolicExpression newValue = universe.substitute(oldValue,
							scopeSubMap);

					if (oldValue != newValue) {
						if (newValues == null)
							newValues = dynamicScope.copyValues();
						newValues[vid] = newValue;
					}
				}
			}
			if (newValues != null) {
				if (newScopes == null) {
					newScopes = new CommonDynamicScope[numScopes];
					for (int j = 0; j < i; j++)
						newScopes[j] = (CommonDynamicScope) state.getScope(j);
				}
				newScopes[i] = dynamicScope(staticScope, dynamicScope.parent(),
						newValues, dynamicScope.reachers());
			} else if (newScopes != null) {
				newScopes[i] = dynamicScope;
			}
		}
		assert newScopes != null;
		return newScopes;
	}

	/**
	 * Given a BitSet indexed by process IDs, and a map of old PIDs to new PIDs,
	 * returns a BitSet equivalent to original but indexed using the new PIDs.
	 * 
	 * If no changes are made, the original BitSet (oldBitSet) is returned.
	 * 
	 * @param oldBitSet
	 * @param oldToNewPidMap
	 *            array of length state.numProcs in which element at index i is
	 *            the new PID of the process whose old PID is i. A negative
	 *            value indicates that the process of (old) PID i is to be
	 *            removed.
	 * @return
	 */
	private BitSet updateBitSet(BitSet oldBitSet, int[] oldToNewPidMap) {
		BitSet newBitSet = null;
		int length = oldBitSet.length();

		for (int i = 0; i < length; i++) {
			boolean flag = oldBitSet.get(i);

			if (flag) {
				int newIndex = oldToNewPidMap[i];

				if (newIndex >= 0) {
					if (newBitSet == null)
						newBitSet = new BitSet(length);
					newBitSet.set(newIndex);
				}
			}
		}
		if (newBitSet == null)
			return oldBitSet;
		return newBitSet;
	}

	/**
	 * Procedure:
	 * 
	 * <ol>
	 * <li>get the current dynamic scope ds0 of the process. Let ss0 be the
	 * static scope associated to ds0.</li>
	 * <li>Let ss1 be the static scope of the new location to move to.</li>
	 * <li>Compute the join (youngest common ancestor) of ss0 and ss1. Also save
	 * the sequence of static scopes from join to ss1.</li>
	 * <li>Iterate UP over dynamic scopes from ds0 up (using parent field) to
	 * the first dynamic scope whose static scope is join.</li>
	 * <li>Iterate DOWN from join to ss1, creating NEW dynamic scopes along the
	 * way.</li>
	 * <li>Set the frame pointer to the new dynamic scope corresponding to ss1,
	 * and set the location to the given location.</li>
	 * <li>Remove all unreachable scopes.</li>
	 * </ol>
	 * 
	 * TODO: update reachable
	 * 
	 * @param state
	 * @param pid
	 * @param location
	 * @return
	 */
	@Override
	public State setLocation(State state, int pid, Location location) {
		CommonProcessState[] processArray = ((CommonState) state).processes();
		int dynamicScopeId = ((CommonState) state).getProcessState(pid)
				.getDyscopeId();
		CommonDynamicScope dynamicScope = (CommonDynamicScope) ((CommonState) state)
				.getScope(dynamicScopeId);
		Scope ss0 = dynamicScope.lexicalScope();
		Scope ss1 = location.scope();

		if (ss0 == ss1) {
			processArray[pid] = canonic(((CommonProcessState) ((CommonState) state)
					.getProcessState(pid)).replaceTop(stackEntry(location,
					dynamicScopeId)));
			return new CommonState((CommonState) state, processArray);
		} else {
			Scope[] joinSequence = joinSequence(ss0, ss1);
			Scope join = joinSequence[0];

			// iterate UP...
			while (dynamicScope.lexicalScope() != join) {
				dynamicScopeId = state.getParentId(dynamicScopeId);
				if (dynamicScopeId < 0)
					throw new RuntimeException("State is inconsistent");
				dynamicScope = (CommonDynamicScope) ((CommonState) state)
						.getScope(dynamicScopeId);
			}
			if (joinSequence.length == 1) {
				processArray[pid] = canonic(((CommonProcessState) ((CommonState) state)
						.getProcessState(pid)).replaceTop(stackEntry(location,
						dynamicScopeId)));
				state = new CommonState((CommonState) state, processArray);
			} else {
				// iterate DOWN, adding new dynamic scopes...
				int oldNumScopes = state.numScopes();
				int newNumScopes = oldNumScopes + joinSequence.length - 1;
				int index = 0;
				CommonDynamicScope[] newScopes = new CommonDynamicScope[newNumScopes];
				CommonProcessState process = processArray[pid];

				for (; index < oldNumScopes; index++)
					newScopes[index] = (CommonDynamicScope) ((CommonState) state)
							.getScope(index);
				for (int i = 1; i < joinSequence.length; i++) {
					// only this process can reach the new dyscope
					BitSet reachers = new BitSet(processArray.length);

					reachers.set(pid);
					newScopes[index] = dynamicScope(joinSequence[i],
							dynamicScopeId, index, reachers);
					dynamicScopeId = index;
					index++;
				}
				process = canonic(process.replaceTop(stackEntry(location,
						dynamicScopeId)));
				setReachablesForProc(newScopes, process);
				processArray[pid] = process;
				state = new CommonState(processArray, newScopes,
						state.getPathCondition());
			}
			return collectScopes((state));
		}
	}

	/**
	 * Given an array of dynamic scopes and a process state, computes the actual
	 * dynamic scopes reachable from that process and modifies the array as
	 * necessary by replacing a dynamic scope with a scope that is equivalent
	 * except for the corrected bit set.
	 * 
	 * @param dynamicScopes
	 *            an array of dynamic scopes, to be modified
	 * @param process
	 *            a process state
	 */
	private void setReachablesForProc(CommonDynamicScope[] dynamicScopes,
			CommonProcessState process) {
		int stackSize = process.stackSize();
		int numScopes = dynamicScopes.length;
		boolean reached[] = new boolean[numScopes];
		int pid = process.getPid();

		for (int i = 0; i < stackSize; i++) {
			StackEntry frame = process.getStackEntry(i);
			int id = frame.scope();

			while (id >= 0) {
				if (reached[id])
					break;
				reached[id] = true;
				id = dynamicScopes[id].parent();
			}
		}
		for (int j = 0; j < numScopes; j++) {
			CommonDynamicScope scope = dynamicScopes[j];
			BitSet bitSet = scope.reachers();

			if (bitSet.get(pid) != reached[j]) {
				BitSet newBitSet = (BitSet) bitSet.clone();

				newBitSet.flip(pid);
				dynamicScopes[j] = canonic(dynamicScopes[j]
						.changeReachers(newBitSet));
			}
		}
	}

	/**
	 * Given two static scopes, this method computes a non-empty sequence of
	 * scopes with the following properties:
	 * <ul>
	 * <li>The first (0-th) element of the sequence is the join of scope1 and
	 * scope2.</li>
	 * <li>The last element is scope2.</li>
	 * <li>For each i (0<=i<length-1), the i-th element is the parent of the
	 * (i+1)-th element.</li>
	 * </ul>
	 * 
	 * @param scope1
	 *            a static scope
	 * @param scope2
	 *            a static scope
	 * @return join sequence as described above
	 * 
	 * @exception IllegalArgumentException
	 *                if the scopes do not have a common ancestor
	 */
	private Scope[] joinSequence(Scope scope1, Scope scope2) {
		if (scope1 == scope2)
			return new Scope[] { scope2 };
		for (Scope scope1a = scope1; scope1a != null; scope1a = scope1a
				.parent())
			for (Scope scope2a = scope2; scope2a != null; scope2a = scope2a
					.parent())
				if (scope1a.equals(scope2a)) {
					Scope join = scope2a;
					int length = 1;
					Scope[] result;
					Scope s;

					for (s = scope2; s != join; s = s.parent())
						length++;
					result = new Scope[length];
					s = scope2;
					for (int i = length - 1; i >= 0; i--) {
						result[i] = s;
						s = s.parent();
					}
					return result;
				}
		throw new IllegalArgumentException("No common scope:\n" + scope1 + "\n"
				+ scope2);
	}

	/**
	 * Push a new entry on the call stack for a process.
	 * 
	 * @param state
	 *            The old state.
	 * @param process
	 *            The pid of the process making the call.
	 * @param location
	 *            The location of the function in the new stack frame.
	 * @param lexicalScope
	 *            The lexical scope corresponding to the new dynamic scope.
	 * @param parentScope
	 *            The id of the parent dynamic scope.
	 * @return A new state that is the same as the old state with the given
	 *         process having a new entry on its call stack.
	 */
	@Override
	public State pushCallStack(State state, int pid, CIVLFunction function,
			SymbolicExpression[] arguments) {
		return pushCallStack2((CommonState) state, pid, function, arguments,
				pid);
	}

	/**
	 * General method for pushing a frame onto a call stack, whether or not the
	 * call stack is for a new process (and therefore empty).
	 * 
	 * @param state
	 *            the initial state
	 * @param pid
	 *            the PID of the process whose stack is to be modified; this
	 *            stack may be empty
	 * @param function
	 *            the called function that will be pushed onto the stack
	 * @param arguments
	 *            the arguments to the function
	 * @param callerPid
	 *            the PID of the process that is creating the new frame. For an
	 *            ordinary function call, this will be the same as pid. For a
	 *            "spawn" command, callerPid will be different from pid and
	 *            process pid will be new and have an empty stack. Exception: if
	 *            callerPid is -1 then the new dynamic scope will have no
	 *            parent; this is used for pushing the original system function,
	 *            which has no caller
	 * @return new stack with new frame on call stack of process pid
	 */
	private CommonState pushCallStack2(CommonState state, int pid,
			CIVLFunction function, SymbolicExpression[] arguments, int callerPid) {
		Scope containingStaticScope = function.containingScope();
		Scope functionStaticScope = function.outerScope();
		CommonProcessState[] newProcesses = state.processes();
		int numScopes = state.numScopes();
		SymbolicExpression[] values;
		CommonDynamicScope[] newScopes;
		int sid;
		int containingDynamicScopeId;
		BitSet bitSet = new BitSet(newProcesses.length);

		if (callerPid >= 0) {
			ProcessState caller = state.getProcessState(callerPid);
			CommonDynamicScope containingDynamicScope;

			if (caller.stackSize() == 0)
				throw new IllegalArgumentException(
						"Calling process has empty stack: " + callerPid);
			containingDynamicScopeId = caller.getDyscopeId();
			while (containingDynamicScopeId >= 0) {
				containingDynamicScope = (CommonDynamicScope) state
						.getScope(containingDynamicScopeId);
				if (containingStaticScope == containingDynamicScope
						.lexicalScope())
					break;
				containingDynamicScopeId = state
						.getParentId(containingDynamicScopeId);
			}
			if (containingDynamicScopeId < 0)
				throw new IllegalArgumentException(
						"Called function not visible:\nfunction: " + function
								+ "\npid: " + pid + "\ncallerPid:" + callerPid
								+ "\narguments: " + arguments);
		} else {
			containingDynamicScopeId = -1;
		}
		newScopes = state.copyAndExpandScopes();
		sid = numScopes;
		values = initialValues(functionStaticScope, sid);
		for (int i = 0; i < arguments.length; i++)
			if (arguments[i] != null)
				values[i] = arguments[i];
		bitSet.set(pid);
		newScopes[sid] = dynamicScope(functionStaticScope,
				containingDynamicScopeId, values, bitSet);
		{
			int id = containingDynamicScopeId;
			CommonDynamicScope scope;

			while (id >= 0) {
				scope = newScopes[id];
				bitSet = newScopes[id].reachers();
				if (bitSet.get(pid))
					break;
				bitSet = (BitSet) bitSet.clone();
				bitSet.set(pid);
				newScopes[id] = canonic(scope.changeReachers(bitSet));
				id = scope.parent();
			}
		}
		newProcesses[pid] = canonic(((CommonProcessState) state
				.getProcessState(pid)).push(stackEntry(null, sid)));
		state = new CommonState(newProcesses, newScopes,
				state.getPathCondition());
		state = (CommonState) setLocation(state, pid, function.startLocation());
		state = (CommonState) collectScopes(state);
		return state;
	}

	@Override
	public State popCallStack(State state, int pid) {
		ProcessState process = ((CommonState) state).getProcessState(pid);
		CommonProcessState[] processArray = ((CommonState) state).processes();
		CommonDynamicScope[] newScopes = ((CommonState) state).copyScopes();

		processArray[pid] = canonic(((CommonProcessState) process).pop());
		setReachablesForProc(newScopes, processArray[pid]);
		state = new CommonState((CommonState) state, processArray, newScopes,
				null);
		return collectScopes(state);
	}

	/**
	 * Update the path condition of a state.
	 * 
	 * @param state
	 *            The old state.
	 * @param pathCondition
	 *            The new path condition.
	 * @return A new state that is the same as the old state but with the new
	 *         path condition.
	 */
	// @Override
	// public State setPathCondition(State state, BooleanExpression
	// pathCondition) {
	// return new CommonState((CommonState) state, pathCondition);
	// }

	private boolean nsat(BooleanExpression p) {
		return trueReasoner.isValid(universe.not(p));
	}

	@Override
	public State simplify(State state) {
		// TODO: room for optimization here.
		// don't create new things unless something changes.
		int numScopes = state.numScopes();
		CommonDynamicScope[] newDynamicScopes = new CommonDynamicScope[numScopes];
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		BooleanExpression newPathCondition;
		CommonState newState;

		for (int i = 0; i < numScopes; i++) {
			CommonDynamicScope oldScope = (CommonDynamicScope) ((CommonState) state)
					.getScope(i);
			int numVars = oldScope.numberOfVariables();
			SymbolicExpression[] newVariableValues = new SymbolicExpression[numVars];

			for (int j = 0; j < numVars; j++) {
				SymbolicExpression oldValue = oldScope.getValue(j);
				SymbolicExpression newValue = reasoner.simplify(oldValue);

				newVariableValues[j] = newValue;
			}
			newDynamicScopes[i] = oldScope
					.changeVariableValues(newVariableValues);
		}
		newPathCondition = reasoner.getReducedContext();
		// TODO: do this here or when you produce new path condition?
		if (nsat(newPathCondition))
			newPathCondition = universe.falseExpression();
		newState = new CommonState((CommonState) state, null, newDynamicScopes,
				newPathCondition);
		return newState;
	}

}
