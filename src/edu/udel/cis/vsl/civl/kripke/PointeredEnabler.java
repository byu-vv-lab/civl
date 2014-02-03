package edu.udel.cis.vsl.civl.kripke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.NoopStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.statement.WaitStatement;
import edu.udel.cis.vsl.civl.model.common.statement.StatementList;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.MemoryUnit;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * EnablerPOR implements {@link EnablerIF} for CIVL models. Its basic
 * functionality is to obtain the set of enabled transitions of a certain state,
 * using the new POR as discussed in Feb 2014.
 * 
 * @author Manchun Zheng (zmanchun)
 */
public class PointeredEnabler extends Enabler implements
		EnablerIF<State, Transition, TransitionSequence> {

	/* ***************************** Constructors ************************** */

	public PointeredEnabler(TransitionFactory transitionFactory,
			Evaluator evaluator, Executor executor, boolean sPor) {
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

	private ArrayList<ProcessState> ampleProcesses(State state) {
		ArrayList<ProcessState> processes = activeProcesses(state);

		if (processes.size() <= 1)
			return processes;
		else {
			HashMap<Integer, ArrayList<MemoryUnit>> reachableMemUnitsMap = new HashMap<>();
			HashSet<Integer> ampleProcessIDs = new LinkedHashSet<>();
			Stack<Integer> workingProcessIDs = new Stack<>();

			for (ProcessState p : processes) {
				reachableMemUnitsMap.put(p.getPid(),
						reachableMemoryUnits(p, state));
			}
			workingProcessIDs.add(processes.get(0).getPid());
			while (!workingProcessIDs.isEmpty()) {
				int pid = workingProcessIDs.pop();
				Set<MemoryUnit> impactMemUnits = impactMemoryUnits(
						state.getProcessState(pid), state);
				ProcessState thisProc = state.getProcessState(pid);

				ampleProcessIDs.add(pid);
				for (Statement s : thisProc.getLocation().outgoing()) {
					if (s instanceof WaitStatement) {
						int joinID = joinedIDofWait(state, thisProc,
								(WaitStatement) s);

						if (!ampleProcessIDs.contains(joinID)
								&& workingProcessIDs.contains(joinID))
							workingProcessIDs.add(joinID);
					}
				}
				for (ProcessState p : processes) {
					int otherPid = p.getPid();
					ArrayList<MemoryUnit> reachableMemUnitsOfOther = reachableMemUnitsMap
							.get(otherPid);

					if (otherPid == pid || ampleProcessIDs.contains(otherPid)
							|| workingProcessIDs.contains(otherPid))
						continue;
					for (MemoryUnit unit : impactMemUnits) {
						if (reachableMemUnitsOfOther.contains(unit)) {
							workingProcessIDs.add(otherPid);
							break;
						}
					}
				}
			}
			processes = new ArrayList<>();
			for (int pid : ampleProcessIDs) {
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
				if (!guard(state, p.getPid(), s).isFalse()) {
					result.add(p);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param state
	 * @param pid
	 * @param statement
	 * @return
	 */
	private BooleanExpression guard(State state, int pid, Statement statement) {
		return null;
	}

	private ArrayList<MemoryUnit> reachableMemoryUnits(ProcessState p,
			State state) {
		
		return null;

	}

	private Set<MemoryUnit> impactMemoryUnits(ProcessState p, State state) {
		Set<MemoryUnit> memUnits = new HashSet<>();
		int pid = p.getPid();

		for (Statement s : p.getLocation().outgoing()) {
			memUnits.addAll(impactMemoryUnitsOfStatement(s, pid, state));
		}
		return memUnits;
	}

	// TODO equals() method of MemoryUnit must be implemented
	private Set<MemoryUnit> impactMemoryUnitsOfStatement(Statement statement,
			int pid, State state) {
		Set<MemoryUnit> memUnits = new HashSet<>();
		MemoryUnit memUnit = memoryUnit(statement.guard(), pid, state);

		if (memUnit != null) {
			memUnits.add(memUnit);
		}
		if (statement instanceof CallOrSpawnStatement) {
			CallOrSpawnStatement call = (CallOrSpawnStatement) statement;

			// TODO special function calls
			for (Expression argument : call.arguments()) {
				memUnit = memoryUnit(argument, pid, state);
				if (memUnit != null) {
					memUnits.add(memUnit);
				}
			}
		} else if (statement instanceof AssignStatement) {
			AssignStatement assignStatement = (AssignStatement) statement;

			memUnit = memoryUnit(assignStatement.getLhs(), pid, state);
			if (memUnit != null) {
				memUnits.add(memUnit);
			}
			memUnit = memoryUnit(assignStatement.rhs(), pid, state);
			if (memUnit != null) {
				memUnits.add(memUnit);
			}
		} else if (statement instanceof WaitStatement) {
			memUnit = memoryUnit(((WaitStatement) statement).process(), pid,
					state);
			if (memUnit != null) {
				memUnits.add(memUnit);
			}
		} else if (statement instanceof ReturnStatement) {
			ReturnStatement returnStatement = (ReturnStatement) statement;

			if (returnStatement.expression() != null) {
				memUnit = memoryUnit(returnStatement.expression(), pid, state);
				if (memUnit != null) {
					memUnits.add(memUnit);
				}
			}
		} else if (statement instanceof NoopStatement) {
		} else if (statement instanceof MallocStatement) {
			MallocStatement mallocStatement = (MallocStatement) statement;

			memUnit = memoryUnit(mallocStatement.getLHS(), pid, state);
			if (memUnit != null) {
				memUnits.add(memUnit);
			}
			memUnit = memoryUnit(mallocStatement.getHeapPointerExpression(),
					pid, state);
			if (memUnit != null) {
				memUnits.add(memUnit);
			}
			memUnit = memoryUnit(mallocStatement.getSizeExpression(), pid,
					state);
			if (memUnit != null) {
				memUnits.add(memUnit);
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

	private MemoryUnit memoryUnit(Expression expression, int pid, State s) {
		
		
		return null;
	}

}
