/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;
import java.util.Stack;

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
		Location currentLocation;
		ProcessState p;

		assert transition instanceof SimpleTransition;
		if (verbose || debug || showTransitions) {
			out.println();
			out.print(state + " --");
			printTransitionLong(out, transition);
			out.print("--> ");
		}

		pid = ((SimpleTransition) transition).pid();
		p = state.getProcessState(pid);
		currentLocation = p.peekStack().location();

		// TODO make printed transition more precise. Currently, the printed
		// transition is the first statement in the atomic block, and it would
		// be better if it is the last statement of the atomic block.
		// The executing of atomic blocks will have to be moved to Enabler to
		// avoid this issue, which is not straightforward because it returns
		// transitions instead of states.
		if (currentLocation.enterAtomic()) {
			// execute atomic block
			state = executeAtomicBlock(state, pid, currentLocation);
		} else {
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
		if (state.numProcs() > pid) {
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
	 * Execute a sequence of purely local statements of a certain process
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
				// TODO check spawn statement
				// if(debug)
				// {System.out.println("intermediate state:");
				// state.print(System.out);}

				if (newLocation.enterAtomic()) {
					// execute an atomic block
					newState = executeAtomicBlock(newState, pid, newLocation);
				} else {
					Statement s = newLocation.getOutgoing(0);
					BooleanExpression guard = (BooleanExpression) executor
							.evaluator().evaluate(newState, pid, s.guard()).value;
					BooleanExpression newPathCondition = executor.universe()
							.and(newState.getPathCondition(), guard);

					newState = newState.setPathCondition(newPathCondition);
					newState = executor.execute(newState, pid, s);
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
	 * Execute an atomic block, supporting nested atomic blocks. Currently only
	 * consider the case when each location has exactly one outgoing statement.
	 * 
	 * @param state
	 *            The current state
	 * @param pid
	 *            The id of the process being executing
	 * @param location
	 *            The start location of the atomic block <dt>
	 *            <b>Precondition:</b>
	 *            <dd>
	 *            <code> location.enterAtomic() == true && location == state.getProcessState(pid).peekStack().location()</code>
	 * @return The resulting state after executing the atomic block
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeAtomicBlock(State state, int pid, Location location)
			throws UnsatisfiablePathConditionException {
		Stack<Integer> atomicFlags = new Stack<Integer>();// record blocks of
															// atomic statements
		ProcessState p;
		CIVLSource atomicStart = location.getSource();
		Location newLocation = location;
		State newState = state;
		int stateCounter = 0;

		if (newLocation.enterAtomic()) {
			do {
				boolean statementExecuted = false;
				State currentState = newState;

				for (Statement s : newLocation.outgoing()) {
					BooleanExpression newPathCondition;

					if (s instanceof WaitStatement) {
						throw new CIVLStateException(
								ErrorKind.OTHER,
								Certainty.CONCRETE,
								"Wait statement is not allowed in atomic blocks.",
								currentState, newLocation.getSource());
					}

					if (s instanceof ChooseStatement) {
						throw new CIVLInternalException(
								"Non-determinstic function call choose_int is not allowed in atomic blocks.",
								newLocation.getSource());
					}
					newPathCondition = executor.newPathCondition(newState, pid,
							s);
					if (!newPathCondition.isFalse()) {
						if (statementExecuted) {
							// non-determinism detected
							CIVLExecutionException e = new CIVLStateException(
									ErrorKind.OTHER,
									Certainty.CONCRETE,
									"Undesired non-determinism is found in atomic block.",
									currentState, newLocation.getSource());

							throw e;
						}

						newState = newState.setPathCondition(newPathCondition);
						newState = executor.execute(newState, pid, s);
						statementExecuted = true;
					}
				}
				// current location is blocked
				if (!statementExecuted) {
					CIVLExecutionException e = new CIVLStateException(
							ErrorKind.OTHER,
							Certainty.CONCRETE,
							"Undesired blocked location is detected in atomic block.",
							currentState, newLocation.getSource());

					throw e;
				}

				// warning for possible infinite atomic block
				if (stateCounter != 0 && stateCounter % 1024 == 0) {
					out.println("Warning: " + (stateCounter)
							+ " states in atomic block at "
							+ atomicStart.getLocation() + ".");
				}

				stateCounter++;
				p = newState.getProcessState(pid);
				if (newLocation.enterAtomic()) {
					// encounter a new atomic block
					atomicFlags.push(1);
				}
				if (newLocation.leaveAtomic()) {
					// reach the end of the latest atomic block
					atomicFlags.pop();
				}
				if (p != null && !p.hasEmptyStack())
					newLocation = p.peekStack().location();
				else
					newLocation = null;

			} while (newLocation != null && !atomicFlags.isEmpty());

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
