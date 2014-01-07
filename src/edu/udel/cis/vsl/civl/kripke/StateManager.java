/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.common.statement.StatementList;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.transition.ChooseTransition;
import edu.udel.cis.vsl.civl.transition.SimpleTransition;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.gmc.StateManagerIF;

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
		boolean printTransitions = verbose || debug || showTransitions;

		assert transition instanceof SimpleTransition;
		pid = ((SimpleTransition) transition).pid();
		p = state.getProcessState(pid);
		currentLocation = p.getLocation();
		switch (currentLocation.atomicKind()) {
		case ENTER:
			out.println();
			out.print(state + " --proc");
			out.println(pid+ ":");
			state = executor.executeAtomicStatements(state, pid,
					currentLocation, true, printTransitions);
			break;
		case LEAVE:
			out.println();
			out.print(state + " --proc");
			out.println(pid+ ":");
			state = executor.executeAtomicStatements(state, pid,
					currentLocation, true, printTransitions);
			break;
		case DENTER:
			out.println();
			out.print(state + " --proc");
			out.println(pid+ ":");
			state = executor.executeDAtomicBlock(state, pid, currentLocation, printTransitions);
			break;
		case DLEAVE:
			throw new CIVLInternalException("Unreachable",
					currentLocation.getSource());
		default:// execute a normal transition
			if (printTransitions) {
				out.println();
				out.print(state + " --");
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
				state = executor.executeAtomicStatements(state, pid,
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
					state = executor.executeAtomicStatements(state, pid,
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

	// /**
	// * Execute a sequence of purely local statements of a certain process TODO
	// * move to enabler
	// *
	// * @param state
	// * The state to start with
	// * @param pid
	// * id of the executing process
	// * @param location
	// * The start location of the execution
	// * @return The resulting state
	// * @throws UnsatisfiablePathConditionException
	// */
	// private State executePurelyLocalStatements(State state, int pid,
	// Location location) throws UnsatisfiablePathConditionException {
	// Location newLocation = location;
	// ProcessState p = state.getProcessState(pid);
	// State newState = state;
	//
	// if (newLocation.isPurelyLocal()) {
	// while (newLocation != null && newLocation.isPurelyLocal()) {
	// switch (newLocation.atomicKind()) {
	// case NONE:
	// boolean executed = false;
	// State oldState = newState;
	//
	// for (Statement s : newLocation.outgoing()) {
	// Entry<StateStatusKind, State> temp = executor
	// .executeStatement(newState, newLocation, s, pid);
	//
	// switch (temp.getKey()) {
	// case NONDETERMINISTIC:
	// // finds non-determinism, go back to previous state
	// return oldState;
	// case NORMAL:
	// if (executed) {
	// // finds non-determinism, go back to previous
	// // state
	// return oldState;
	// }
	// executed = true;
	// newState = temp.getValue();
	// break;
	// default:// BLOCKED, continue to try executing next
	// // statement
	// continue;
	// }
	// }
	// if (!executed)// blocked
	// return oldState;
	// break;
	// case DENTER:
	// newState = executor.executeDAtomicBlock(newState, pid,
	// newLocation);
	// break;
	// case ENTER:
	// return newState;
	// default:
	// throw new CIVLInternalException("Unreachable",
	// newLocation.getSource());
	// }
	// p = newState.getProcessState(pid);
	// if (p != null && !p.hasEmptyStack())
	// newLocation = p.peekStack().location();
	// else
	// newLocation = null;
	// }
	// return newState;
	// }
	// return null;
	// }

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
