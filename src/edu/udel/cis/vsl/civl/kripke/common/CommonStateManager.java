/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke.common;

import java.io.PrintStream;
import java.util.List;

import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.IF.CIVLStateException;
import edu.udel.cis.vsl.civl.err.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.kripke.IF.StateManager;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.location.Location.AtomicKind;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.Executor.StateStatusKind;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.transition.CompoundTransition;
import edu.udel.cis.vsl.civl.transition.SimpleTransition;
import edu.udel.cis.vsl.civl.transition.Step;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.util.IF.Printable;

/**
 * @author Timothy K. Zirkel (zirkel)
 * @author Manchun Zheng (zmanchun)
 * @author Stephen F. Siegel (siegel)
 * 
 */
public class CommonStateManager implements StateManager {

	/**
	 * The enabling status of a process at a state.
	 * 
	 * @author Manchun Zheng
	 * 
	 */
	static enum EnabledStatus {
		BLOCKED, DETERMINISTIC, LOOP_POSSIBLE, NONDETERMINISTIC, NONE, TERMINATED
	}

	/**
	 * A helper class to keep track of analysis result of states in the sense
	 * that if they are allowed to be executed further.
	 * 
	 * @author Manchun Zheng
	 * 
	 */
	private class StateStatus {
		/**
		 * The enabling status of the current process at the current state.
		 */
		EnabledStatus enabledStatus;

		/**
		 * The result of the enabling analysis: i.e., whether the process is
		 * allowed to execute more.
		 */
		boolean possibleToExecute;

		/**
		 * The current enabled transition of the current process. Not NULL only
		 * when the process is allowed to execute more.
		 */
		SimpleTransition enabledTransition;

		/**
		 * Keep track of the number of incomplete atom blocks.
		 */
		int atomCount;

		StateStatus(boolean possible, SimpleTransition transition,
				int atomCount, EnabledStatus status) {
			this.possibleToExecute = possible;
			this.enabledTransition = transition;
			this.atomCount = atomCount;
			this.enabledStatus = status;
		}
	}

	/* *************************** Instance Fields ************************* */

	/**
	 * The unique enabler instance used by the system
	 */
	private CommonEnabler enabler;

	/**
	 * The unique executor instance used by the system
	 */
	private Executor executor;

	/**
	 * The flag to turn on/off printing of debugging information.
	 */
	private boolean debug = false;

	/**
	 * The maximal number of processes at a state, initialized as 0.
	 */
	private int maxProcs = 0;

	/**
	 * The output stream to be used in this class to print states, transitions,
	 * warnings, etc.
	 */
	private PrintStream out = null;

	/**
	 * Save states during search?
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#saveStatesO}
	 */
	private boolean saveStates = true;

	/**
	 * Print saved states (i.e., canonicalized states)?
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#showSavedStatesO}
	 */
	private boolean showSavedStates = false;

	/**
	 * Print all states (including states that are not saved)?
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#showStatesO}
	 */
	private boolean showStates = false;

	/**
	 * Print transitions?
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#showTransitionsO}
	 */
	private boolean showTransitions = false;

	/**
	 * Simplify state returned by nextState?
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#simplifyO}
	 */
	private boolean simplify = true;

	/**
	 * The unique state factory used by the system.
	 */
	private StateFactory stateFactory;

	/**
	 * Turn on/off verbose mode.
	 * {@link edu.udel.cis.vsl.civl.run.IF.UserInterface#verboseO}
	 */
	private boolean verbose = false;

	/**
	 * The object whose toString() method will be used to print the periodic
	 * update. The toString method of this object should print a short
	 * (one-line) message on the state of the search.
	 */
	private Printable updater;

	/**
	 * If true, print a short one-line update message on the state of the search
	 * at the next opportunity, and then set this flag back to false. This flag
	 * is typically set by a separate thread. Access to this thread is protected
	 * by the lock on this StateManager.
	 */
	private boolean printUpdate = false;

	/**
	 * Number of calls to method {@link #nextState(State, Transition)}
	 */
	private int nextStateCalls = 0;

	/**
	 * Keep track of the maximal canonic ID of states. Since
	 * {@link StateFactory#canonic(State)} is only called when savedState option
	 * is enabled, this is only updated when savedState option is enabled. The
	 * motivation to have this field is to allow the state manager to print only
	 * new states in -savedStates mode, for better user experiences.
	 */
	private int maxCanonicId = -1;

	/**
	 * True iff gui mode is enabled.
	 */
	private boolean guiMode = false;

	/**
	 * The compound transition established by the current nextStep call. TODO:
	 * get rid of being an instance field, creating a new class NextStateWorker.
	 */
	private CompoundTransition compoundTransition;

	/* ***************************** Constructor *************************** */

	/**
	 * 
	 * @param executor
	 *            The unique executor to by used in the system.
	 */
	public CommonStateManager(Executor executor, PrintStream out,
			boolean verbose, boolean debug, boolean gui, boolean showStates,
			boolean showSavedStates, boolean showTransitions,
			boolean saveStates, boolean simplify) {
		this.executor = executor;
		this.enabler = (CommonEnabler) executor.enabler();
		this.stateFactory = executor.stateFactory();
		this.out = out;
		this.verbose = verbose;
		this.debug = debug;
		this.guiMode = gui;
		this.showStates = showStates;
		this.showSavedStates = showSavedStates;
		this.showTransitions = showTransitions;
		this.saveStates = saveStates;
		this.simplify = simplify;
	}

	/* *************************** Private Methods ************************* */
	/**
	 * Execute a transition (obtained by the enabler) of a state. When the
	 * corresponding process is in atomic/atom execution, continue to execute
	 * more statements as many as possible. Also execute more statements if
	 * possible.
	 * 
	 * @param state
	 *            The current state
	 * @param transition
	 *            The transition to be executed.
	 * @return the resulting state after execute
	 * @throws UnsatisfiablePathConditionException
	 */
	private State nextStateWork(State state, Transition transition)
			throws UnsatisfiablePathConditionException {
		int pid;
		int numProcs;
		boolean printTransitions = verbose || debug || showTransitions;
		int oldMaxCanonicId = this.maxCanonicId;
		int processIdentifier;
		SimpleTransition firstTransition;
		State oldState = state;

		assert transition instanceof SimpleTransition;
		pid = ((SimpleTransition) transition).pid();
		processIdentifier = ((SimpleTransition) transition).processIdentifier();
		firstTransition = (SimpleTransition) transition;
		// procState = state.getProcessState(pid);
		// currentLocation = procState.getLocation();
		if (this.guiMode)
			this.compoundTransition = new CompoundTransition(pid,
					processIdentifier);
		state = executor.execute(state, pid, firstTransition);
		if (printTransitions) {
			printTransitionPrefix(oldState, processIdentifier);
			printStatement(oldState, state, firstTransition, AtomicKind.NONE,
					processIdentifier, false);
		}
		if (this.guiMode) {
			this.compoundTransition.addStep(new Step(oldState, state,
					firstTransition.statement()));
		}
		{
			StateStatus stateStatus = possibleToExecuteMore(state, pid, 0);

			while (stateStatus.possibleToExecute) {
				assert stateStatus.enabledTransition != null;
				assert stateStatus.enabledStatus == EnabledStatus.DETERMINISTIC;
				assert stateStatus.atomCount >= 0;

				state = executor.execute(state, pid,
						stateStatus.enabledTransition);
				if (printTransitions) {
					printStatement(oldState, state,
							stateStatus.enabledTransition, AtomicKind.NONE,
							processIdentifier, false);
				}
				if (this.guiMode) {
					this.compoundTransition.addStep(new Step(oldState, state,
							stateStatus.enabledTransition.statement()));
				}
				oldState = state;
				if (this.showStates)
					stateFactory.printState(out, state);
				stateStatus = possibleToExecuteMore(state, pid,
						stateStatus.atomCount);
			}
			assert stateStatus.atomCount == 0;
			assert stateStatus.enabledStatus != EnabledStatus.DETERMINISTIC;
			if (stateStatus.enabledStatus == EnabledStatus.BLOCKED) {
				if (stateFactory.lockedByAtomic(state)) {
					state = stateFactory.releaseAtomicLock(state);
				}
			}
		}
		if (printTransitions) {
			out.print("--> ");
		}
		if (saveStates) {
			state = stateFactory.canonic(state);
			if (this.guiMode)
				this.compoundTransition.updateFinalState(state);
			this.maxCanonicId = state.getCanonicId();
		} else {
			state = stateFactory.collectProcesses(state);
			state = stateFactory.collectScopes(state);
			if (simplify)
				state = stateFactory.simplify(state);
			state.commit();
		}
		if (verbose || debug || showTransitions) {
			out.println(state);
		}
		if (debug
				|| verbose
				|| (!saveStates && showStates)
				|| (saveStates && showStates && this.maxCanonicId > oldMaxCanonicId)
				|| (saveStates && showSavedStates && this.maxCanonicId > oldMaxCanonicId)) {
			// in -savedStates mode, only print new states.
			out.println();
			// state.print(out);
			this.stateFactory.printState(out, state);
		}
		numProcs = state.numProcs();
		if (numProcs > maxProcs)
			maxProcs = numProcs;
		return state;

	}

	/**
	 * analyzes if the current process is allowed to execute one more transition
	 * at the given state. Conditions for a process p at a state s to execute
	 * more:
	 * <ul>
	 * <li>p is about to enter an atom block or p is already in some atom
	 * blocks:
	 * <ul>
	 * <li>the size of enabled(p, s) should be exactly 1;</li>
	 * <li>otherwise, an error will be reported.</li>
	 * </ul>
	 * </li> or
	 * <li>p is currently holding the atomic lock:
	 * <ol>
	 * <li>the current location of p has exactly one incoming statement;</li>
	 * <li>the size of enabled(p, s) should be exactly 1.</li>
	 * </ol>
	 * </li> or
	 * <li>the current location of p is purely local;
	 * <ul>
	 * <li>the size of enabled(p, s) is exactly 1.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the current process.
	 * @param atomCount
	 *            The number of incomplete atom blocks.
	 * @return
	 */
	private StateStatus possibleToExecuteMore(State state, int pid,
			int atomCount) {
		List<SimpleTransition> enabled;
		ProcessState procState = state.getProcessState(pid);
		Location pLocation;
		boolean inAtomic = false;
		boolean inAtom = false;

		if (procState == null || procState.hasEmptyStack())
			return new StateStatus(false, null, atomCount,
					EnabledStatus.TERMINATED);
		else
			pLocation = procState.getLocation();
		if (pLocation == null)
			return new StateStatus(false, null, atomCount,
					EnabledStatus.TERMINATED);
		enabled = enabler.enabledTransitionsOfProcess(state, pid);
		if (pLocation.enterAtom()) {
			atomCount++;
		} else if (pLocation.leaveAtom()) {
			inAtom = true;
			atomCount--;
		}
		if (inAtom || atomCount > 0) {
			// in atom execution
			if (enabled.size() == 1) {
				return new StateStatus(true, enabled.get(0), atomCount,
						EnabledStatus.DETERMINISTIC);
			} else if (enabled.size() > 1) {// non deterministic
				reportError(StateStatusKind.NONDETERMINISTIC, state, pLocation);
				return new StateStatus(false, null, atomCount,
						EnabledStatus.NONDETERMINISTIC);
			} else {// blocked
				return new StateStatus(false, null, atomCount,
						EnabledStatus.BLOCKED);
			}
		} else {
			int pidInAtomic = stateFactory.processInAtomic(state);

			if (pidInAtomic != -1) { // some other process is holding the atomic
										// lock.
				if (pidInAtomic != pid) {
					throw new CIVLStateException(
							ErrorKind.OTHER,
							Certainty.CONCRETE,
							"There is another process other than the current process holding the atomic lock.",
							state, stateFactory, pLocation.getSource());
				} else { // the process is in atomic execution

					if (pLocation.getNumIncoming() > 1) // possible loop, save
														// state
						return new StateStatus(false, null, atomCount,
								EnabledStatus.LOOP_POSSIBLE);
					inAtomic = true;
				}
			}
			if (inAtomic || pLocation.isPurelyLocal()) {
				if (enabled.size() == 1)
					return new StateStatus(true, enabled.get(0), atomCount,
							EnabledStatus.DETERMINISTIC);
				else if (enabled.size() > 1) {// blocking
					return new StateStatus(false, null, atomCount,
							EnabledStatus.NONDETERMINISTIC);
				} else {
					return new StateStatus(false, null, atomCount,
							EnabledStatus.BLOCKED);
				}
			}
			return new StateStatus(false, null, atomCount, EnabledStatus.NONE);
		}
	}

	/**
	 * Print a step of a statement, in the following form:
	 * <code>src->dst: statement at file:location text;</code>For example,<br>
	 * <code>32->17: sum = (sum+(3*i)) at f0:20.14-24 "sum += 3*i";</code><br>
	 * When the atomic lock variable is changed during executing the statement,
	 * then the corresponding information is printed as well. For example,<br>
	 * <code>13->6: ($ATOMIC_LOCK_VAR = $self) x = 0 at f0:30.17-22
	 "x = 0";</code>
	 * 
	 * @param s
	 *            The statement that has been executed in the current step.
	 * @param atomicKind
	 *            The atomic kind of the source location of the statement.
	 * @param atomCount
	 *            The atomic/atom count of the process that the statement
	 *            belongs to.
	 * @param atomicLockVarChanged
	 *            True iff the atomic lock variable is changed during the
	 *            execution of the statement.
	 */
	private void printStatement(State currentState, State newState,
			SimpleTransition transition, AtomicKind atomicKind, int atomCount,
			boolean atomicLockVarChanged) {
		out.print(transition.statement().toStepString(atomicKind, atomCount,
				atomicLockVarChanged));
	}

	/**
	 * Print the prefix of a transition.
	 * 
	 * @param printTransitions
	 *            True iff each step is to be printed.
	 * @param state
	 *            The source state of the transition.
	 * @param processIdentifier
	 *            The identifier of the process that this transition associates
	 *            with.
	 */
	private void printTransitionPrefix(State state, int processIdentifier) {
		out.print(state + ", proc ");
		out.println(processIdentifier + ":");
	}

	/**
	 * Print the updated status.
	 */
	private void printUpdateWork() {
		updater.print(out);
		out.flush();
	}

	/**
	 * Report error message for $atom block execution, when
	 * <ol>
	 * <li>non-determinism is detected, or</li>
	 * <li>a blocked location is encountered.</li>
	 * </ol>
	 * 
	 * @param kind
	 *            The status kind of the error.
	 * @param state
	 *            The state that the error occurs.
	 * @param location
	 *            The location that the error occurs.
	 */
	private void reportError(StateStatusKind kind, State state,
			Location location) {
		switch (kind) {
		case NONDETERMINISTIC:
			executor.evaluator().reportError(
					new CIVLStateException(ErrorKind.OTHER, Certainty.CONCRETE,
							"Non-determinism is encountered in $atom block.",
							state, this.stateFactory, location.getSource()));
			break;
		case BLOCKED:
			executor.evaluator().reportError(
					new CIVLStateException(ErrorKind.OTHER, Certainty.CONCRETE,
							"Blocked location is encountered in $atom block.",
							state, this.stateFactory, location.getSource()));
			break;
		default:
		}
	}

	/* ********************* Methods from StateManagerIF ******************* */

	@Override
	public int getDepth(State state) {
		return state.getDepth();
	}

	@Override
	public State nextState(State state, Transition transition) {
		nextStateCalls++;
		if (nextStateCalls % 100 == 0) {
			synchronized (this) {
				if (printUpdate) {
					printUpdateWork();
					printUpdate = false;
				}
			}
		}
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
	public Object[] nextStateForUi(State state, Transition transition) {
		Object[] results = new Object[2];

		nextStateCalls++;
		if (nextStateCalls % 100 == 0) {
			synchronized (this) {
				if (printUpdate) {
					printUpdateWork();
					printUpdate = false;
				}
			}
		}
		try {
			results[0] = nextStateWork(state, transition);
		} catch (UnsatisfiablePathConditionException e) {
			// problem is the interface requires an actual State
			// be returned. There is no concept of executing a
			// transition and getting null or an exception.
			// since the error has been logged, just stutter:
			results[0] = state;
		}
		results[1] = this.compoundTransition;
		return results;
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
		this.stateFactory.printState(out, state);
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

	/* ************************ Other Public Methods *********************** */

	/**
	 * 
	 * @return the debugging option, true if under debug mode, otherwise false.
	 */
	public boolean getDebug() {
		return debug;
	}

	@Override
	public long getNumStateInstances() {
		return stateFactory.getNumStateInstances();
	}

	@Override
	public int getNumStatesSaved() {
		return stateFactory.getNumStatesSaved();
	}

	/**
	 * The whole system should be using the same print stream to print
	 * information in different components.
	 * 
	 * @return the output stream used by the state manager
	 */
	public PrintStream getOutputStream() {
		return out;
	}

	/**
	 * -saveStates is always true in depth first search.
	 * 
	 * @return the value of the option -saveStates
	 */
	public boolean getSaveStates() {
		return saveStates;
	}

	/**
	 * -showSavedStates is false by default
	 * 
	 * @return the value of the option -showSavedStates
	 */
	public boolean getShowSavedStates() {
		return showSavedStates;
	}

	/**
	 * -showStates is false by default
	 * 
	 * @return the value of the option -showStates
	 */
	public boolean getShowStates() {
		return showStates;
	}

	/**
	 * -showTransitions is false by default
	 * 
	 * @return the value of the option -showTransitions
	 */
	public boolean getShowTransitions() {
		return showTransitions;
	}

	/**
	 * -simplify is true by default
	 * 
	 * @return the value of the option -simplify
	 */
	public boolean getSimplify() {
		return simplify;
	}

	/**
	 * The updater, see also {@link #updater}.
	 * 
	 * @return the updater.
	 */
	public Printable getUpdater() {
		return updater;
	}

	/**
	 * -verbose is false by default
	 * 
	 * @return the value of the option -verbose
	 */
	public boolean getVerbose() {
		return verbose;
	}

	@Override
	public int maxProcs() {
		return maxProcs;
	}

	/**
	 * Set the field debug.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	public void setDebug(boolean value) {
		this.debug = value;
	}

	public void setGuiMode(boolean value) {
		this.guiMode = value;
	}

	/**
	 * Set the field savedStates.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	public void setSaveStates(boolean value) {
		this.saveStates = value;
	}

	@Override
	public void setShowSavedStates(boolean value) {
		this.showSavedStates = value;
	}

	@Override
	public void setShowStates(boolean value) {
		this.showStates = value;
	}

	@Override
	public void setShowTransitions(boolean value) {
		this.showTransitions = value;
	}

	/**
	 * Set the field simplify.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	public void setSimplify(boolean value) {
		simplify = value;
	}

	/**
	 * Set the field savedStates.
	 * 
	 * @param updater
	 *            The value to be used.
	 */
	public void setUpdater(Printable updater) {
		this.updater = updater;
	}

	public void setOutputStream(PrintStream out) {
		this.out = out;
	}

	@Override
	public void setVerbose(boolean value) {
		this.verbose = value;
	}

	@Override
	public synchronized void printUpdate() {
		printUpdate = true;
	}

}
