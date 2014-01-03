/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.CIVLExecutionException;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.statement.WaitStatement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.transition.ChooseTransition;
import edu.udel.cis.vsl.civl.transition.SimpleTransition;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.gmc.StateManagerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class StateManager implements StateManagerIF<State, Transition> {

	private Executor executor;

	private PrintStream out = null;

	private StateFactory stateFactory;

	private int maxProcs = 0;

	private boolean showStates = false;

	private boolean showSavedStates = false;

	private boolean showTransitions = false;

	private boolean debug = false;

	private boolean verbose = false;

	/**
	 * Save states during search?
	 */
	private boolean saveStates = true;

	/**
	 * Simplify state returned by nextState?
	 */
	private boolean simplify = true;

	public StateManager(Executor executor) {
		this.executor = executor;
		this.stateFactory = executor.stateFactory();
	}

	public void setOutputStream(PrintStream out) {
		this.out = out;
	}

	public PrintStream getOutputStream() {
		return out;
	}

	public void setShowStates(boolean value) {
		this.showStates = value;
	}

	public boolean getShowStates() {
		return showStates;
	}

	public void setShowSavedStates(boolean value) {
		this.showSavedStates = value;
	}

	public boolean getShowSavedStates() {
		return showSavedStates;
	}

	public void setShowTransitions(boolean value) {
		this.showTransitions = value;
	}

	public boolean getShowTransitions() {
		return showTransitions;
	}

	public void setDebug(boolean value) {
		this.debug = value;
	}

	public boolean getDebug() {
		return debug;
	}

	public void setVerbose(boolean value) {
		this.verbose = value;
	}

	public boolean getVerbose() {
		return verbose;
	}

	public void setSaveStates(boolean value) {
		this.saveStates = value;
	}

	public boolean getSaveStates() {
		return saveStates;
	}

	public void setSimplify(boolean value) {
		simplify = value;
	}

	public boolean getSimplify() {
		return simplify;
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

	private State nextStateWork(State state, Transition transition)
			throws UnsatisfiablePathConditionException {
		int pid;
		Statement statement;
		int numProcs;
		ProcessState p;
		Location currentLocation;

		assert transition instanceof SimpleTransition;
		if (verbose || debug || showTransitions) {
			out.println();
			out.print(state + " --");
			printTransitionLong(out, transition);
			out.print("--> ");
		}
		pid = ((SimpleTransition) transition).pid();
		p = state.getProcessState(pid);
		currentLocation = p.getLocation();
		switch (currentLocation.atomicKind()) {
		case ENTER:
			assert !stateFactory.lockedByAtomic(state)
					|| stateFactory.processInAtomic(state).getPid() == pid;
			state = executeStatement(state, currentLocation,
					currentLocation.getOutgoing(0), pid);
			p = state.getProcessState(pid).incrementAtomicCount();
			state = stateFactory.setProcessState(state, p, pid);
			state = stateFactory.getAtomicLock(state, pid);
			break;
		case LEAVE:
			assert stateFactory.processInAtomic(state).getPid() == pid;
			state = executeStatement(state, currentLocation,
					currentLocation.getOutgoing(0), pid);
			p = state.getProcessState(pid).decrementAtomicCount();
			state = stateFactory.setProcessState(state, p, pid);
			if (!p.inAtomic()) {
				state = stateFactory.releaseAtomicLock(state);
			}
			break;
		case DENTER:
			state = executeDAtomicBlock(state, pid, currentLocation);
			break;
		case DLEAVE:
			// error
			throw new CIVLInternalException("Unreachable",
					currentLocation.getSource());
		default:
			// execute a normal transition
			state = state.setPathCondition(((SimpleTransition) transition)
					.pathCondition());
			statement = ((SimpleTransition) transition).statement();
			if (transition instanceof ChooseTransition) {
				assert statement instanceof ChooseStatement;
				state = executor.executeChoose(state, pid,
						(ChooseStatement) statement,
						((ChooseTransition) transition).value());
			} else {
				state = executor.execute(state, pid, statement);
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
					state = executePurelyLocalStatements(state, pid,
							newLocation);
				}
			}
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
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeDAtomicBlock(State state, int pid, Location location) {
		// // record blocks of atomic statements
		// Stack<Integer> atomicFlags = new Stack<Integer>();
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
			} catch (UnsatisfiablePathConditionException e1) {
				throw new CIVLStateException(
						ErrorKind.OTHER,
						Certainty.CONCRETE,
						"Undesired blocked location is detected in $atom block.",
						newState, newLocation.getSource());
			}
		} else {
			throw new CIVLStateException(ErrorKind.OTHER, Certainty.CONCRETE,
					"Undesired blocked location is detected in $atom block.",
					newState, newLocation.getSource());
		}
		do {
			boolean statementExecuted = false;
			State currentState = newState;

			switch (newLocation.atomicKind()) {
			case DENTER:
				newState = executeDAtomicBlock(newState, pid, newLocation);
				stateCounter++;
				statementExecuted = true;
				break;
			case DLEAVE:
				assert (newLocation.getNumOutgoing() == 1);
				newState = executeStatement(newState, newLocation,
						newLocation.getOutgoing(0), pid);
				return newState;
			default:
				for (Statement s : newLocation.outgoing()) {
					State temp = executeStatement(newState, newLocation, s, pid);

					if (statementExecuted && temp != null) {
						throw new CIVLStateException(
								ErrorKind.OTHER,
								Certainty.CONCRETE,
								"Undesired non-determinism is found in $atom block.",
								newState, newLocation.getSource());
					}
					if (temp != null) {
						statementExecuted = true;
						newState = temp;
					}
				}
			}
			// current location is blocked
			if (!statementExecuted) {
				throw new CIVLStateException(
						ErrorKind.OTHER,
						Certainty.CONCRETE,
						"Undesired blocked location is detected in $atom block.",
						currentState, newLocation.getSource());
			}
			// warning for possible infinite atomic block
			if (stateCounter != 0 && stateCounter % 1024 == 0) {
				out.println("Warning: " + (stateCounter)
						+ " states in atomic block at "
						+ atomicStart.getLocation() + ".");
			}
			stateCounter++;
			p = newState.getProcessState(pid);
			if (p != null && !p.hasEmptyStack())
				newLocation = p.getLocation();
			else {
				throw new CIVLInternalException("Unreachable",
						newLocation.getSource());
			}
		} while (true);
	}

	/**
	 * Execute a statement from a certain state and return the resulting state
	 * 
	 * @param state
	 *            The state to execute the statement with
	 * @param location
	 *            The location of the statement, satisfying that
	 *            <code>s.source() == location</code>.
	 * @param s
	 *            The statement to be executed
	 * @param pid
	 *            The id of the process that the statement <code>s</code>
	 *            belongs to. Precondition:
	 *            <code>state.getProcessState(pid).getLocation() == location</code>
	 * @return
	 */
	private State executeStatement(State state, Location location, Statement s,
			int pid) {
		State newState = null;
		BooleanExpression pathCondition = executor.newPathCondition(state, pid,
				s);

		if (!pathCondition.isFalse()) {
			try {
				if (s instanceof ChooseStatement) {
					throw new CIVLStateException(
							ErrorKind.OTHER,
							Certainty.CONCRETE,
							"Undesired non-determinism is found in $atom block.",
							state, location.getSource());
				} else if (s instanceof WaitStatement) {
					Evaluation eval = executor.evaluator().evaluate(
							state.setPathCondition(pathCondition), pid,
							((WaitStatement) s).process());
					int pidValue = executor.modelFactory().getProcessId(
							((WaitStatement) s).process().getSource(),
							eval.value);

					if (pidValue < 0) {
						CIVLExecutionException e = new CIVLStateException(
								ErrorKind.INVALID_PID,
								Certainty.PROVEABLE,
								"Unable to call $wait on a process that has already been the target of a $wait.",
								state, s.getSource());

						executor.evaluator().reportError(e);
						// TODO: recover: add a no-op transition
						throw e;
					}
					if (state.getProcessState(pidValue).hasEmptyStack()) {
						newState = state.setPathCondition(pathCondition);
						newState = executor.execute(newState, pid, s);
					} else
						return null;
				} else {
					newState = state.setPathCondition(pathCondition);
					newState = executor.execute(newState, pid, s);
				}
			} catch (UnsatisfiablePathConditionException e) {
				// nothing to do: don't add this transition
				return null;
			}
		}

		return newState;
	}

	/**
	 * Execute a sequence of purely local statements of a certain process TODO
	 * move to enabler
	 * 
	 * @param state
	 *            The state to start with
	 * @param pid
	 *            id of the executing process
	 * @param location
	 *            The start location of the execution
	 * @return The resulting state
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executePurelyLocalStatements(State state, int pid,
			Location location) throws UnsatisfiablePathConditionException {
		Location newLocation = location;
		ProcessState p = state.getProcessState(pid);
		State newState = state;

		if (newLocation.isPurelyLocal()) {
			while (newLocation != null && newLocation.isPurelyLocal()) {
				switch (newLocation.atomicKind()) {
				case NONE:
					boolean executed = false;
					State oldState = newState;

					for (Statement s : newLocation.outgoing()) {
						State temp = executeStatement(newState, newLocation, s,
								pid);

						if (executed && temp != null) {
							// finds non-determinism, go back to previous state
							return oldState;
						}
						if (temp != null) {
							executed = true;
							newState = temp;
						}
					}
					break;
				case DENTER:
					newState = executeDAtomicBlock(newState, pid, newLocation);
					break;
				case ENTER:
					return newState;
				default:
					throw new CIVLInternalException("Unreachable",
							newLocation.getSource());
				}
				p = newState.getProcessState(pid);
				if (p != null && !p.hasEmptyStack())
					newLocation = p.peekStack().location();
				else
					newLocation = null;
			}
			return newState;
		}
		return null;
	}

	/**
	 * @return The maximum number of processes in any state encountered by this
	 *         state manager.
	 */
	public int maxProcs() {
		return maxProcs;
	}

	@Override
	public boolean onStack(State state) {
		return state.onStack();
	}

	@Override
	public void printAllStatesLong(PrintStream arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printAllStatesShort(PrintStream arg0) {
		// TODO Auto-generated method stub

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
	public void setOnStack(State state, boolean value) {
		state.setOnStack(value);
	}

	@Override
	public void setSeen(State state, boolean value) {
		state.setSeen(value);
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

	@Override
	public int getDepth(State state) {
		return state.getDepth();
	}

	@Override
	public void setDepth(State state, int value) {
		state.setDepth(value);
	}

}
