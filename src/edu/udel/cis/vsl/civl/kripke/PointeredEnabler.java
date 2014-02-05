package edu.udel.cis.vsl.civl.kripke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.AssertStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssumeStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.NoopStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.statement.WaitStatement;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.statement.StatementList;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.IF.DynamicScope;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.civl.util.Pair;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * EnablerPOR implements {@link EnablerIF} for CIVL models. Its basic
 * functionality is to obtain the set of enabled transitions of a certain state,
 * using the new POR as discussed in Feb 2014.
 * 
 * @author Manchun Zheng (zmanchun)
 */
public class PointeredEnabler extends Enabler implements
		EnablerIF<State, Transition, TransitionSequence> {

	private enum MemoryUnitsStatus {
		NORMAL, INCOMPLETE
	};

	/* ***************************** Constructors ************************** */

	public PointeredEnabler(TransitionFactory transitionFactory,
			Evaluator evaluator, Executor executor) {
		this.transitionFactory = transitionFactory;
		this.evaluator = evaluator;
		this.executor = executor;
		this.modelFactory = evaluator.modelFactory();
		this.universe = modelFactory.universe();
	}

	/* ************************* Methods from Enabler ********************** */

	/**
	 * The new partial order reduction Compute the set of processes that impact
	 * a set of scopes exclusively accessed by the rest of processes.
	 * 
	 * @param state
	 *            The state to work with.
	 * @return The enabled transitions as an instance of TransitionSequence.
	 */
	@Override
	protected TransitionSequence enabledTransitionsPOR(State state) {
		TransitionSequence transitions = transitionFactory
				.newTransitionSequence(state);
		ArrayList<ProcessState> processStates = new ArrayList<>(
				ampleProcesses(state));

		if (debugging) {
			debugOut.print("ample processes at state " + state.getCanonicId()
					+ ":");
			for (ProcessState p : processStates) {
				debugOut.print(p.getPid() + "\t");
			}
			debugOut.println();
		}
		// Compute the ample set (of transitions)
		for (ProcessState p : processStates) {
			TransitionSequence localTransitions = transitionFactory
					.newTransitionSequence(state);
			int pid = p.getPid();

			for (Statement s : p.getLocation().outgoing()) {
				BooleanExpression newPathCondition = newPathCondition(state,
						pid, s);

				if (!newPathCondition.isFalse()) {
					localTransitions.addAll(enabledTransitionsOfStatement(
							state, s, newPathCondition, pid, null));
				}
			}
			transitions.addAll(localTransitions);
		}
		return transitions;
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Obtain the set of ample processes from a given state.
	 * 
	 * @param state
	 *            The current state.
	 * @return
	 */
	private ArrayList<ProcessState> ampleProcesses(State state) {
		ArrayList<ProcessState> processes = activeProcesses(state);

		if (processes.size() <= 1)
			return processes;
		else {
			HashMap<Integer, HashSet<Integer>> ampleProcessesMap = new HashMap<>();
			int minimalAmpleSetSize = processes.size() + 1;
			int minAmpleSetId = -1;
			HashMap<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap = new HashMap<>();
			HashMap<Integer, Set<SymbolicExpression>> impactMemUnitsMap = new HashMap<>();
			HashSet<Integer> allProcessIDs = new HashSet<>();
			// HashMap<Integer, Set<SymbolicExpression>> writableMemUnitsMap =
			// new HashMap<>();

			for (ProcessState p : processes) {
				int pid = p.getPid();
				Pair<MemoryUnitsStatus, Set<SymbolicExpression>> impactMemUnitsPair = impactMemoryUnits(
						state.getProcessState(pid), state);

				if (impactMemUnitsPair.left == MemoryUnitsStatus.INCOMPLETE) {
					impactMemUnitsMap.put(pid, null);
				} else {
					impactMemUnitsMap.put(pid, impactMemUnitsPair.right);
				}
				reachableMemUnitsMap.put(pid, reachableMemoryUnits(p, state));
				allProcessIDs.add(pid);
			}
			for (ProcessState p : processes) {
				HashSet<Integer> ampleProcessIDs = new LinkedHashSet<>();
				Stack<Integer> workingProcessIDs = new Stack<>();

				workingProcessIDs.add(p.getPid());
				while (!workingProcessIDs.isEmpty()) {
					int pid = workingProcessIDs.pop();
					ProcessState thisProc = state.getProcessState(pid);
					Set<SymbolicExpression> impactMemUnits = impactMemUnitsMap
							.get(pid);
					Map<SymbolicExpression, Boolean> reachableMemUnitsMapOfThis = reachableMemUnitsMap
							.get(pid);

					if (impactMemUnits == null) {
						// The current process is entering an atomic/atom block
						// whose impact memory units can't be computed
						// completely
						ampleProcessIDs = allProcessIDs;
						break;
					}
					ampleProcessIDs.add(pid);
					if (ampleProcessIDs.size() == processes.size())
						break;
					for (Statement s : thisProc.getLocation().outgoing()) {
						if (s instanceof WaitStatement) {
							int joinID = joinedIDofWait(state, thisProc,
									(WaitStatement) s);

							if (!ampleProcessIDs.contains(joinID)
									&& workingProcessIDs.contains(joinID))
								workingProcessIDs.add(joinID);
						}
					}
					for (ProcessState otherP : processes) {
						int otherPid = otherP.getPid();
						Map<SymbolicExpression, Boolean> reachableMemUnitsMapOfOther = reachableMemUnitsMap
								.get(otherPid);

						if (otherPid == pid
								|| ampleProcessIDs.contains(otherPid)
								|| workingProcessIDs.contains(otherPid))
							continue;
						for (SymbolicExpression unit : impactMemUnits) {
							if (reachableMemUnitsMapOfOther.containsKey(unit)) {
								if (reachableMemUnitsMapOfThis.get(unit)
										|| reachableMemUnitsMapOfOther
												.get(unit)) {
									workingProcessIDs.add(otherPid);
									break;
								}
							}
						}
					}
				}
				ampleProcessesMap.put(p.getPid(), ampleProcessIDs);
				if (ampleProcessIDs.size() < minimalAmpleSetSize) {
					minAmpleSetId = p.getPid();
					minimalAmpleSetSize = ampleProcessIDs.size();
				}
				if (minimalAmpleSetSize == 1)
					break;
			}
			processes = new ArrayList<>();
			for (int pid : ampleProcessesMap.get(minAmpleSetId)) {
				processes.add(state.getProcessState(pid));
			}
			return processes;
		}
	}

	/**
	 * Obtain active processes at a given state, i.e., non-null processes with
	 * non-empty stack that have at least one enabled statement.
	 * 
	 * @param state
	 *            The current state.
	 * @return
	 */
	private ArrayList<ProcessState> activeProcesses(State state) {
		ArrayList<ProcessState> result = new ArrayList<>();

		for (ProcessState p : state.getProcessStates()) {
			if (p == null || p.hasEmptyStack())
				continue;
			for (Statement s : p.getLocation().outgoing()) {
				if (!getGuard(s, p.getPid(), state).isFalse()) {
					result.add(p);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Compute the set of reachable memory units that a certain process can
	 * reach at a given state
	 * 
	 * @param p
	 *            The process whose reachable memory units are to be computed.
	 * @param state
	 *            The current state.
	 * @return
	 */
	private Map<SymbolicExpression, Boolean> reachableMemoryUnits(
			ProcessState p, State state) {
		Iterable<? extends StackEntry> callStacks = p.getStackEntries();
		// Set<SymbolicExpression> memUnits = new HashSet<>();
		Set<Integer> checkedDyScopes = new HashSet<>();
		Map<SymbolicExpression, Boolean> memUnitPermissionMap = new HashMap<>();
		Set<Variable> writableVariables = p.getLocation().writableVariables();

		for (StackEntry callStack : callStacks) {
			int dyScopeID = callStack.scope();

			while (dyScopeID >= 0) {
				if (checkedDyScopes.contains(dyScopeID))
					break;
				else {
					DynamicScope dyScope = state.getScope(dyScopeID);
					// Scope stScope = dyScope.lexicalScope();
					int size = dyScope.numberOfValues();

					for (int vid = 0; vid < size; vid++) {
						Variable variable = dyScope.lexicalScope()
								.variable(vid);
						Set<SymbolicExpression> varMemUnits = evaluator
								.memoryUnitOfVariable(dyScope.getValue(vid),
										dyScopeID, vid, state);
						boolean permission = writableVariables
								.contains(variable) ? true : false;

						for (SymbolicExpression unit : varMemUnits) {
							if (memUnitPermissionMap.containsKey(unit)) {
								boolean newPermission = permission
										|| memUnitPermissionMap.get(unit);
								memUnitPermissionMap.put(unit, newPermission);
							} else
								memUnitPermissionMap.put(unit, permission);
							// memUnits.add(unit);
						}
					}
					checkedDyScopes.add(dyScopeID);
					dyScopeID = state.getParentId(dyScopeID);
				}
			}
		}
		return memUnitPermissionMap;
	}

	/**
	 * The impact memory units of a certain process at a given state.
	 * 
	 * @param p
	 *            The process whose impact memory units are to be computed.
	 * @param state
	 *            The current state.
	 * @return
	 */
	private Pair<MemoryUnitsStatus, Set<SymbolicExpression>> impactMemoryUnits(
			ProcessState p, State state) {
		Set<SymbolicExpression> memUnits = new HashSet<>();
		int pid = p.getPid();
		Location pLocation = p.getLocation();

		if (pLocation.enterAtom() || pLocation.enterAtomic()) {
			return impactMemoryUnitsOfAtomicBlock(pLocation, pid, state);
		} else {
			for (Statement s : pLocation.outgoing()) {
				try {
					memUnits.addAll(impactMemoryUnitsOfStatement(s, pid, state));
				} catch (UnsatisfiablePathConditionException e) {
					continue;
				}
			}
		}
		return new Pair<>(MemoryUnitsStatus.NORMAL, memUnits);
	}

	private Pair<MemoryUnitsStatus, Set<SymbolicExpression>> impactMemoryUnitsOfAtomicBlock(
			Location location, int pid, State state) {
		if (location.enterAtom() || location.enterAtomic()) {
			Stack<Integer> atomFlags = new Stack<Integer>();
			Set<Integer> checkedLocations = new HashSet<Integer>();
			Stack<Location> workings = new Stack<Location>();
			Set<SymbolicExpression> memUnits = new HashSet<>();

			workings.add(location);
			// DFS searching for reachable statements inside the $atomic/$atom
			// block
			while (!workings.isEmpty()) {
				Location currentLocation = workings.pop();

				checkedLocations.add(currentLocation.id());
				if (location.enterAtom() && currentLocation.enterAtom())
					atomFlags.push(1);
				if (location.enterAtomic() && currentLocation.enterAtomic())
					atomFlags.push(1);
				if (location.enterAtom() && currentLocation.leaveAtom())
					atomFlags.pop();
				if (location.enterAtomic() && currentLocation.leaveAtomic())
					atomFlags.pop();
				if (atomFlags.isEmpty()) {
					if (location.enterAtom()) {
						if (!currentLocation.enterAtom())
							atomFlags.push(1);
					}
					if (location.enterAtomic()) {
						if (!currentLocation.enterAtomic())
							atomFlags.push(1);
					}
					continue;
				}
				if (currentLocation.getNumOutgoing() > 0) {
					int number = currentLocation.getNumOutgoing();
					for (int i = 0; i < number; i++) {
						Statement s = currentLocation.getOutgoing(i);

						if (s instanceof CallOrSpawnStatement) {
							if (((CallOrSpawnStatement) s).isCall()) {
								return new Pair<MemoryUnitsStatus, Set<SymbolicExpression>>(
										MemoryUnitsStatus.INCOMPLETE, memUnits);
							}
						}
						try {
							memUnits.addAll(impactMemoryUnitsOfStatement(s,
									pid, state));
						} catch (UnsatisfiablePathConditionException e) {

						}
						if (s.target() != null) {
							if (!checkedLocations.contains(s.target().id())) {
								workings.push(s.target());
							}
						}
					}
				}
			}
			return new Pair<MemoryUnitsStatus, Set<SymbolicExpression>>(
					MemoryUnitsStatus.NORMAL, memUnits);
		}
		return null;
	}

	// equals() and hashCode() method of MemoryUnit must be implemented so
	// that Set.contains() can execute correctly.
	/**
	 * Compute the impact memory units of a given statement of a certain process
	 * at the given state.
	 * 
	 * @param statement
	 *            The statement whose impact memory units are to be computed.
	 * @param pid
	 *            The id of the process that owns the statement.
	 * @param state
	 *            The current state.
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private Set<SymbolicExpression> impactMemoryUnitsOfStatement(
			Statement statement, int pid, State state)
			throws UnsatisfiablePathConditionException {
		Set<SymbolicExpression> memUnits = new HashSet<>();
		Set<SymbolicExpression> memUnitsPartial = memoryUnit(statement.guard(),
				pid, state);

		if (memUnitsPartial != null) {
			memUnits.addAll(memUnitsPartial);
		}
		if (statement instanceof AssertStatement) {
			AssertStatement assertStatement = (AssertStatement) statement;
			Expression expression = assertStatement.getExpression();
			Expression[] printfArgs = assertStatement.printfArguments();

			memUnitsPartial = memoryUnit(expression, pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
			if (printfArgs != null) {
				for (Expression argument : printfArgs) {
					memUnitsPartial = memoryUnit(argument, pid, state);
					if (memUnitsPartial != null) {
						memUnits.addAll(memUnitsPartial);
					}
				}
			}
		} else if (statement instanceof AssumeStatement) {
			AssumeStatement assumeStatement = (AssumeStatement) statement;
			Expression expression = assumeStatement.getExpression();

			memUnitsPartial = memoryUnit(expression, pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
		} else if (statement instanceof CallOrSpawnStatement) {
			CallOrSpawnStatement call = (CallOrSpawnStatement) statement;

			// TODO special function calls
			for (Expression argument : call.arguments()) {
				memUnitsPartial = memoryUnit(argument, pid, state);
				if (memUnitsPartial != null) {
					memUnits.addAll(memUnitsPartial);
				}
			}
		} else if (statement instanceof AssignStatement) {
			AssignStatement assignStatement = (AssignStatement) statement;

			memUnitsPartial = memoryUnit(assignStatement.getLhs(), pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
			memUnitsPartial = memoryUnit(assignStatement.rhs(), pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
		} else if (statement instanceof WaitStatement) {
			memUnitsPartial = memoryUnit(((WaitStatement) statement).process(),
					pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
		} else if (statement instanceof ReturnStatement) {
			ReturnStatement returnStatement = (ReturnStatement) statement;

			if (returnStatement.expression() != null) {
				memUnitsPartial = memoryUnit(returnStatement.expression(), pid,
						state);
				if (memUnitsPartial != null) {
					memUnits.addAll(memUnitsPartial);
				}
			}
		} else if (statement instanceof NoopStatement) {
		} else if (statement instanceof MallocStatement) {
			MallocStatement mallocStatement = (MallocStatement) statement;

			memUnitsPartial = memoryUnit(mallocStatement.getLHS(), pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
			memUnitsPartial = memoryUnit(
					mallocStatement.getHeapPointerExpression(), pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
			memUnitsPartial = memoryUnit(mallocStatement.getSizeExpression(),
					pid, state);
			if (memUnitsPartial != null) {
				memUnits.addAll(memUnitsPartial);
			}
		} else if (statement instanceof StatementList) {
			StatementList statementList = (StatementList) statement;

			for (Statement subStatement : statementList.statements()) {
				memUnits.addAll(impactMemoryUnitsOfStatement(subStatement, pid,
						state));
			}
		} else
			throw new CIVLUnimplementedFeatureException("Statement kind",
					statement);

		return memUnits;
	}

	/**
	 * Compute the set of memory units accessed by a given expression of a
	 * certain process at the given state.
	 * 
	 * @param expression
	 *            The expression whose impact memory units are to be computed.
	 * @param pid
	 *            The id of the process that the expression belongs to.
	 * @param state
	 *            The current state.
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private Set<SymbolicExpression> memoryUnit(Expression expression, int pid,
			State state) throws UnsatisfiablePathConditionException {
		Set<SymbolicExpression> memoryUnits = new HashSet<>();

		evaluator.memoryUnitsOfExpression(state, pid, expression, memoryUnits);
		if (debugging) {
			printMemoryUnitsOfExpression(expression, memoryUnits);
		}
		return memoryUnits;
	}

	/**
	 * Print the impact memory units of a expression. Used for debugging.
	 * 
	 * @param expression
	 *            The expression to be printed.
	 * @param memUnits
	 *            The memory units of the expression.
	 */
	private void printMemoryUnitsOfExpression(Expression expression,
			Set<SymbolicExpression> memUnits) {
		debugOut.println(expression.toString() + "\t mem units:");
		for (SymbolicExpression memUnit : memUnits) {
			debugOut.print(memUnit + "\t");
		}
		debugOut.println();
	}

}
