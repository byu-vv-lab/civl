/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.common.location.CommonLocation.AtomicKind;
import edu.udel.cis.vsl.civl.model.common.statement.StatementList;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.Executor.StateStatusKind;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.transition.ChooseTransition;
import edu.udel.cis.vsl.civl.transition.SimpleTransition;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.util.Pair;
import edu.udel.cis.vsl.gmc.StateManagerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class StateManager implements StateManagerIF<State, Transition> {

	/***************************** Instance Fields ***************************/

	private Executor executor;

	private boolean debug = false;

	private int maxProcs = 0;

	private PrintStream out = null;

	/**
	 * Save states during search?
	 */
	private boolean saveStates = true;

	private boolean showSavedStates = false;

	private boolean showStates = false;

	private boolean showTransitions = false;

	/**
	 * Simplify state returned by nextState?
	 */
	private boolean simplify = true;

	private StateFactory stateFactory;

	private boolean verbose = false;

	/***************************** Constructor ***************************/

	public StateManager(Executor executor) {
		this.executor = executor;
		this.stateFactory = executor.stateFactory();
	}

	/***************************** Private Methods ***************************/

	/**
	 * Execute an deterministic atomic block ($atom), supporting nested atomic
	 * blocks. Currently only consider the case when each location has exactly
	 * one outgoing statement.
	 * 
	 * Precondition:
	 * <code> location.enterAtomic() == true && location == state.getProcessState(pid).peekStack().location()</code>
	 * 
	 * @param state
	 *            The current state
	 * @param pid
	 *            The id of the process being executing
	 * @param location
	 *            The start location of the atomic block
	 * 
	 * @return The resulting state after executing the atomic block
	 */
	private State executeAtomBlock(State state, int pid, Location location,
			boolean print) throws UnsatisfiablePathConditionException {
		ProcessState p;
		CIVLSource atomicStart = location.getSource();
		Location newLocation = location;
		State newState = state;
		int stateCounter = 0;
		BooleanExpression newPathCondition;
		Statement start = location.getOutgoing(0);

		assert location.enterDatomic() == true
				&& location.id() == state.getProcessState(pid).getLocation()
						.id() && location.getNumOutgoing() == 1;
		newPathCondition = executor.newPathCondition(newState, pid, start);
		if (!newPathCondition.isFalse()) {
			newState = newState.setPathCondition(newPathCondition);
			try {
				newState = executor.execute(newState, pid, start);
				newLocation = newState.getProcessState(pid).getLocation();
				if (print) {
					printStatement(start, AtomicKind.DENTER,
							newState.getProcessState(pid));
				}
			} catch (UnsatisfiablePathConditionException e1) {
				executor.evaluator()
						.reportError(
								new CIVLStateException(
										ErrorKind.OTHER,
										Certainty.CONCRETE,
										"Undesired blocked location is detected in $atom block.",
										newState, newLocation.getSource()));
				throw new UnsatisfiablePathConditionException();
			}
		} else {
			executor.evaluator().reportError(
					new CIVLStateException(ErrorKind.OTHER, Certainty.CONCRETE,
							"Execution blocked in $atom", newState, newLocation
									.getSource()));
			throw new UnsatisfiablePathConditionException();
		}
		do {
			boolean statementExecuted = false;
			State currentState = newState;
			Statement executedStatement = null;

			switch (newLocation.atomicKind()) {
			case DENTER:
				newState = executeAtomBlock(newState, pid, newLocation, print);
				stateCounter++;
				statementExecuted = true;
				break;
			case DLEAVE:
				assert (newLocation.getNumOutgoing() == 1);
				newState = executor.executeStatement(newState, newLocation,
						newLocation.getOutgoing(0), pid).right;
				executedStatement = newLocation.getOutgoing(0);
				if (print) {
					printStatement(executedStatement, AtomicKind.DLEAVE,
							newState.getProcessState(pid));
				}
				assert newState != null;
				return newState;
			default:
				for (Statement s : newLocation.outgoing()) {
					Pair<StateStatusKind, State> temp = executor
							.executeStatement(newState, newLocation, s, pid);

					switch (temp.left) {
					case NONDETERMINISTIC:
						executor.evaluator()
								.reportError(
										new CIVLStateException(
												ErrorKind.OTHER,
												Certainty.CONCRETE,
												"Undesired non-determinism is found in $atom block.",
												newState, newLocation
														.getSource()));
						throw new UnsatisfiablePathConditionException();
					case NORMAL:
						if (statementExecuted) {
							executor.evaluator()
									.reportError(
											new CIVLStateException(
													ErrorKind.OTHER,
													Certainty.CONCRETE,
													"Undesired non-determinism is found in $atom block.",
													newState, newLocation
															.getSource()));
							throw new UnsatisfiablePathConditionException();
						}
						statementExecuted = true;
						newState = temp.right;
						executedStatement = s;
						break;
					default:// current statement is blocked, continue to try
							// executing another statement from the same
							// location
						continue;
					}
				}
			}
			// current location is blocked
			if (!statementExecuted) {
				executor.evaluator()
						.reportError(
								new CIVLStateException(
										ErrorKind.OTHER,
										Certainty.CONCRETE,
										"Undesired blocked location is detected in $atom block.",
										currentState, newLocation.getSource()));
				throw new UnsatisfiablePathConditionException();
			}
			// warning for possible infinite atomic block
			if (stateCounter != 0 && stateCounter % 1024 == 0) {
				System.out.println("Warning: " + (stateCounter)
						+ " states in atomic block at "
						+ atomicStart.getLocation() + ".");
			}
			stateCounter++;
			p = newState.getProcessState(pid);
			if (print && executedStatement != null) {
				printStatement(executedStatement, newLocation.atomicKind(), p);
			}
			if (p != null && !p.hasEmptyStack())
				newLocation = p.getLocation();
			else {
				throw new CIVLInternalException("Unreachable",
						newLocation.getSource());
			}
		} while (true);
	}

	/**
	 * Execute a sequence of purely local statements or statements defined in an
	 * $atomic block of a certain process
	 * 
	 * @param state
	 *            The state to start with
	 * @param pid
	 *            id of the executing process
	 * @param location
	 *            The start location of the execution
	 * @param atomic
	 *            True iff executing statements in an atomic block; false iff
	 *            executing statements found to be purely local
	 * @return The resulting state
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeAtomicOrPurelyLocalStatements(State state, int pid,
			Location location, boolean atomic, boolean print)
			throws UnsatisfiablePathConditionException {
		Location pLocation = location;
		ProcessState p = state.getProcessState(pid);
		State newState = state;
		Statement executedStatement = null;

		assert atomic || pLocation.isPurelyLocal();

		while ((!atomic && pLocation != null && pLocation.isPurelyLocal())
				|| (atomic && pLocation != null)) {
			if (pLocation.isLoopPossible()) {
				return newState;
			}
			executedStatement = null;
			switch (pLocation.atomicKind()) {
			case NONE:
				boolean executed = false;
				State oldState = newState;

				for (Statement s : pLocation.outgoing()) {
					Pair<StateStatusKind, State> temp = executor
							.executeStatement(oldState, pLocation, s, pid);

					switch (temp.left) {
					case NONDETERMINISTIC:
						// finds non-determinism, go back to previous state
						return oldState;
					case NORMAL:
						if (executed) {
							// finds non-determinism, go back to previous
							// state
							return oldState;
						}
						executed = true;
						newState = temp.right;
						executedStatement = s;
						break;
					default:// BLOCKED, continue to try executing next
							// statement
						continue;
					}
				}
				if (!executed) {// blocked
					oldState = stateFactory.releaseAtomicLock(oldState);
					return oldState;
				}
				break;
			case DENTER:
				newState = executeAtomBlock(newState, pid, pLocation, print);
				break;
			case ENTER:
				if (atomic) {
					assert !stateFactory.lockedByAtomic(newState)
							|| stateFactory.processInAtomic(newState).getPid() == pid;
					newState = executor.executeStatement(newState, pLocation,
							pLocation.getOutgoing(0), pid).right;
					p = newState.getProcessState(pid).incrementAtomicCount();
					newState = stateFactory.setProcessState(newState, p, pid);
					newState = stateFactory.getAtomicLock(newState, pid);
					executedStatement = pLocation.getOutgoing(0);
				} else {
					newState = executeAtomicOrPurelyLocalStatements(newState,
							pid, pLocation, true, print);
				}
				break;
			case LEAVE:
				if (!atomic)
					throw new CIVLInternalException("Unreachable",
							pLocation.getSource());
				assert stateFactory.processInAtomic(newState).getPid() == pid;
				newState = executor.executeStatement(newState, pLocation,
						pLocation.getOutgoing(0), pid).right;
				p = newState.getProcessState(pid).decrementAtomicCount();
				executedStatement = pLocation.getOutgoing(0);
				newState = stateFactory.setProcessState(newState, p, pid);
				if (!p.inAtomic()) {
					newState = stateFactory.releaseAtomicLock(newState);
					if (print) {
						printStatement(executedStatement, AtomicKind.LEAVE, p);
					}
					return newState;
				}
				break;
			default:
				throw new CIVLInternalException("Unreachable",
						pLocation.getSource());
			}
			p = newState.getProcessState(pid);
			if (print && executedStatement != null) {
				printStatement(executedStatement, pLocation.atomicKind(), p);
			}
			if (p != null && !p.hasEmptyStack())
				pLocation = p.peekStack().location();
			else
				pLocation = null;
		}
		return newState;
	}

	private State nextStateWork(State state, Transition transition)
			throws UnsatisfiablePathConditionException {
		int pid;
		Statement statement;
		int numProcs;
		ProcessState p;
		Location currentLocation;
		boolean printTransitions = verbose || debug || showTransitions;

		assert transition instanceof SimpleTransition;
		pid = ((SimpleTransition) transition).pid();
		p = state.getProcessState(pid);
		currentLocation = p.getLocation();
		switch (currentLocation.atomicKind()) {
		case ENTER:
			if (printTransitions) {
				out.println();
				out.print(state + ", proc ");
				out.println(pid + ":");
			}
			state = executeAtomicOrPurelyLocalStatements(state, pid,
					currentLocation, true, printTransitions);
			break;
		case LEAVE:
			if (printTransitions) {
				out.println();
				out.print(state + ", proc ");
				out.println(pid + ":");
			}
			state = executeAtomicOrPurelyLocalStatements(state, pid,
					currentLocation, true, printTransitions);
			break;
		case DENTER:
			if (printTransitions) {
				out.println();
				out.print(state + ", proc ");
				out.println(pid + ":");
			}
			state = executeAtomBlock(state, pid, currentLocation,
					printTransitions);
			break;
		case DLEAVE:
			throw new CIVLInternalException("Unreachable",
					currentLocation.getSource());
		default:// execute a normal transition
			if (printTransitions) {
				out.println();
				out.print(state + ", ");
				printTransitionLong(out, transition);
				out.println(";");
			}
			state = state.setPathCondition(((SimpleTransition) transition)
					.pathCondition());
			statement = ((SimpleTransition) transition).statement();
			if (transition instanceof ChooseTransition) {
				if (statement instanceof StatementList) {
					state = executor.executeStatementList(state, pid,
							(StatementList) statement,
							((ChooseTransition) transition).value());
				} else {
					assert statement instanceof ChooseStatement;
					state = executor.executeChoose(state, pid,
							(ChooseStatement) statement,
							((ChooseTransition) transition).value());
				}
			} else {
				state = executor.execute(state, pid, statement);
			}
			// sometimes the execution might allow the process to grab the
			// atomic lock
			if (executor.stateFactory().lockedByAtomic(state)) {
				currentLocation = state.getProcessState(pid).getLocation();
				state = executeAtomicOrPurelyLocalStatements(state, pid,
						currentLocation, true, printTransitions);
			}
		}
		// do nothing when process pid terminates and is removed from the state
		if (!stateFactory.lockedByAtomic(state) && state.numProcs() > pid) {
			p = state.getProcessState(pid);
			if (p != null && !p.hasEmptyStack()) {
				Location newLocation = p.peekStack().location();

				// execute purely local statements of the current process
				// greedily
				if (newLocation != null && newLocation.isPurelyLocal()) {
					state = executeAtomicOrPurelyLocalStatements(state, pid,
							newLocation, false, printTransitions);
				}
			}
		}
		if (printTransitions) {
			out.print("--> ");
		}

		state = stateFactory.collectScopes(state);
		// TODO: try this simplification out, see how it works:
		if (simplify) {
			state = stateFactory.simplify(state);
		}
		if (saveStates) {
			state = stateFactory.canonic(state);
		} else {
			state.commit();
		}
		if (verbose || debug || showTransitions) {
			out.println(state);
		}
		if (debug || verbose || showStates || showSavedStates) {
			out.println();
			state.print(out);
		}
		numProcs = state.numProcs();
		if (numProcs > maxProcs)
			maxProcs = numProcs;
		return state;
	}

	private void printStatement(Statement s, AtomicKind atomicKind,
			ProcessState p) {
		out.print("  " + s.source().id() + "->");
		if (s.target() != null)
			out.print(s.target().id() + ": ");
		else
			out.print("RET: ");
		if (atomicKind == AtomicKind.ENTER) {
			out.print("ENTER_ATOMIC ");
			out.print(p.atomicCount() - 1);
		} else if (atomicKind == AtomicKind.LEAVE) {
			out.print("EXIT_ATOMIC ");
			out.print(p.atomicCount());
		} else if (atomicKind == AtomicKind.DENTER)
			out.print("ENTER_ATOM");
		else if (atomicKind == AtomicKind.DLEAVE)
			out.print("EXIT_ATOM");
		else
			out.print(s.toString());
		if (s.getSource() != null)
			out.print(" at " + s.getSource().getSummary());
		else if (s.source().getSource() != null)
			out.print(" at " + s.source().getSource().getSummary());
		out.println(";");
	}
	
	/***************************** Methods from StateManagerIF<State, Transition> ***************************/

	@Override
	public int getDepth(State state) {
		return state.getDepth();
	}

	@Override
	public State nextState(State state, Transition transition) {
		try {
			return nextStateWork(state, transition);
		} catch (UnsatisfiablePathConditionException e) {
			// problem is the interface requires an actual State
			// be returned. There is no concept of executing a
			// transition and getting null or an exception.
			// since the error has been logged, just stutter:
			return state;
		}

	}

	@Override
	public boolean onStack(State state) {
		return state.onStack();
	}

	@Override
	public void printAllStatesLong(PrintStream arg0) {

	}

	@Override
	public void printAllStatesShort(PrintStream arg0) {

	}

	@Override
	public void printStateLong(PrintStream out, State state) {
		state.print(out);
	}

	@Override
	public void printStateShort(PrintStream out, State state) {
		out.print(state.toString());
	}

	@Override
	public void printTransitionLong(PrintStream out, Transition transition) {
		out.print(transition.toString());
	}

	@Override
	public void printTransitionShort(PrintStream out, Transition transition) {
		out.print(transition.toString());
	}

	@Override
	public boolean seen(State state) {
		return state.seen();
	}

	@Override
	public void setDepth(State state, int value) {
		state.setDepth(value);
	}

	@Override
	public void setOnStack(State state, boolean value) {
		state.setOnStack(value);
	}

	@Override
	public void setSeen(State state, boolean value) {
		state.setSeen(value);
	}

	/***************************** Public Methods ***************************/

	public boolean getDebug() {
		return debug;
	}

	/**
	 * Returns the number of objects of type State that have been instantiated
	 * since this JVM started.
	 * 
	 * @return the number of states instantiated
	 */
	public long getNumStateInstances() {
		return stateFactory.getNumStateInstances();
	}

	/**
	 * Returns the number of states saved, i.e., made canonic.
	 * 
	 * @return the number of canonic states
	 */
	public int getNumStatesSaved() {
		return stateFactory.getNumStatesSaved();
	}

	public PrintStream getOutputStream() {
		return out;
	}

	public boolean getSaveStates() {
		return saveStates;
	}

	public boolean getShowSavedStates() {
		return showSavedStates;
	}

	public boolean getShowStates() {
		return showStates;
	}

	public boolean getShowTransitions() {
		return showTransitions;
	}

	public boolean getSimplify() {
		return simplify;
	}

	public boolean getVerbose() {
		return verbose;
	}

	/**
	 * @return The maximum number of processes in any state encountered by this
	 *         state manager.
	 */
	public int maxProcs() {
		return maxProcs;
	}

	public void setDebug(boolean value) {
		this.debug = value;
	}

	public void setSaveStates(boolean value) {
		this.saveStates = value;
	}

	public void setShowSavedStates(boolean value) {
		this.showSavedStates = value;
	}

	public void setShowStates(boolean value) {
		this.showStates = value;
	}

	public void setShowTransitions(boolean value) {
		this.showTransitions = value;
	}

	public void setSimplify(boolean value) {
		simplify = value;
	}

	public void setOutputStream(PrintStream out) {
		this.out = out;
	}

	public void setVerbose(boolean value) {
		this.verbose = value;
	}

}
