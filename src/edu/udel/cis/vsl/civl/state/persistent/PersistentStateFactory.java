package edu.udel.cis.vsl.civl.state.persistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class PersistentStateFactory implements StateFactory {

	// *************************** Fields *****************************

	private ModelFactory modelFactory;

	private int stateCount = 0;

	private SymbolicUniverse universe;

	private Map<PersistentObject, PersistentObject> canonicMap = new HashMap<>(
			1000000);

	private Reasoner trueReasoner;

	private long initialNumStateInstances = PersistentState.getInstanceCount();

	private SymbolicExpression nullExpression;

	private BooleanExpression trueExpression;

	// *************************** Constructors ***********************

	/**
	 * Factory to create all state objects.
	 */
	public PersistentStateFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
		this.universe = modelFactory.universe();
		this.trueReasoner = universe.reasoner(universe.trueExpression());
		this.nullExpression = universe.nullExpression();
		this.trueExpression = universe.trueExpression();

	}

	// ************************* Helper Methods ***********************

	@SuppressWarnings("unchecked")
	private <T extends PersistentObject> T canonicObj(T obj) {
		return (T) obj.canonize(universe, canonicMap);
		// note special handling for state since we are adding
		// canonic ID.
	}

	private PersistentProcessState newProcessState(int id, CallStack stack,
			int atomicCount) {
		return new PersistentProcessState(id, stack, atomicCount);
	}

	private ArrayList<ValueVector> initialVectors = new ArrayList<>();

	private ValueVector initialValues(Scope lexicalScope, int dynamicScopeId) {
		int numVars = lexicalScope.numVariables();
		ValueVector vector;

		while (numVars >= initialVectors.size())
			initialVectors.add(null);
		vector = initialVectors.get(numVars);
		if (vector == null) {
			vector = new ValueVector(nullExpression, numVars);
			vector = canonicObj(vector);
			initialVectors.set(numVars, vector);
		}
		return vector;
	}

	private PersistentDynamicScope newDynamicScope(Scope lexicalScope,
			int parent, ValueVector vector, IntSet reachers) {
		return new PersistentDynamicScope(lexicalScope, parent, vector,
				reachers);
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
	private PersistentDynamicScope initialDynamicScope(Scope lexicalScope,
			int parent, int dynamicScopeId, IntSet reachers) {
		return newDynamicScope(lexicalScope, parent,
				initialValues(lexicalScope, dynamicScopeId), reachers);
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
	private PersistentStackEntry stackEntry(Location location, int scope) {
		// TODO: canonize these too?
		return new PersistentStackEntry(location, scope);
	}

	@Override
	public SymbolicUniverse symbolicUniverse() {
		return universe;
	}

	@Override
	public PersistentState canonic(State state) {
		PersistentState result = canonicObj((PersistentState) state);

		if (result.getCanonicId() < 0) {
			result.setCanonicId(stateCount);
			stateCount++;
		}
		return result;
	}

	@Override
	public PersistentState initialState(Model model) {
		PersistentState state = new PersistentState(new ProcStateVector(),
				new DyscopeTree(), trueExpression);
		CIVLFunction function = model.system();
		int numArgs = function.parameters().size();
		SymbolicExpression[] arguments = new SymbolicExpression[numArgs];

		// TODO: how to initialize the arguments to system function?
		state = addProcess(state, function, arguments, -1);
		state = this.setVariable(state, 0, 0, modelFactory.processValue(-1));
		return canonic(state);
	}

	@Override
	public PersistentState setVariable(State state, Variable variable, int pid,
			SymbolicExpression value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState setVariable(State state, int vid, int scopeId,
			SymbolicExpression value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState addProcess(State state, CIVLFunction function,
			SymbolicExpression[] arguments, int callerPid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState removeProcess(State state, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState setLocation(State state, int pid, Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState pushCallStack(State state, int pid,
			CIVLFunction function, SymbolicExpression[] arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState popCallStack(State state, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState simplify(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getNumStateInstances() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumStatesSaved() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PersistentState collectScopes(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean lockedByAtomic(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PersistentProcessState processInAtomic(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState getAtomicLock(State state, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState releaseAtomicLock(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentState setProcessState(State state, ProcessState p, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
