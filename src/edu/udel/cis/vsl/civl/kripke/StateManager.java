/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.state.common.CommonState;
import edu.udel.cis.vsl.civl.state.common.Process;
import edu.udel.cis.vsl.civl.transition.ChooseTransition;
import edu.udel.cis.vsl.civl.transition.SimpleTransition;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.gmc.StateManagerIF;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class StateManager implements StateManagerIF<CommonState, Transition> {

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
	public CommonState nextState(CommonState state, Transition transition) {
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

	private CommonState nextStateWork(CommonState state, Transition transition)
			throws UnsatisfiablePathConditionException {
		int pid;
		Statement statement;
		int numProcs;

		assert transition instanceof SimpleTransition;
		if (verbose || debug || showTransitions) {
			out.println();
			out.print(state + " --");
			printTransitionLong(out, transition);
			out.print("--> ");
		}

		pid = ((SimpleTransition) transition).pid();
		state = stateFactory.setPathCondition(state,
				((SimpleTransition) transition).pathCondition());
		statement = ((SimpleTransition) transition).statement();
		if (transition instanceof ChooseTransition) {
			assert statement instanceof ChooseStatement;
			state = executor.executeChoose(state, pid,
					(ChooseStatement) statement,
					((ChooseTransition) transition).value());
		} else {
			state = executor.execute(state, pid, statement);
		}

		// do nothing when process pid terminates and is removed from the state
		if (state.numProcs() > pid) {
			Process p = state.process(pid);
			if (p != null && !p.hasEmptyStack()) {

				Location newLoc = p.peekStack().location();

				while (newLoc != null && newLoc.isPurelyLocal()) {
					// TODO check spawn statement
					// exactly one statement in newLoc.outgoing()
					//if(debug)
//					{System.out.println("intermediate state:");
//					state.print(System.out);}
					
					Statement s = newLoc.getOutgoing(0);
					BooleanExpression guard = (BooleanExpression) executor
							.evaluator().evaluate(state, p.id(), s.guard()).value;
					BooleanExpression newPathCondition = executor.universe()
							.and(state.pathCondition(), guard);
					state = stateFactory.setPathCondition(state,
							newPathCondition);
					state = executor.execute(state, pid, s);
					
					p = state.process(pid);
					if (p != null && !p.hasEmptyStack())
						newLoc = p.peekStack().location();
					else
						newLoc = null;
				}
			}
		}

		// TODO: Maybe make a loop here for $atomic/Dstep transitions. We could
		// loop over the transitions and then just simplify and canonic once at
		// the end. This could greatly increase efficiency.
		// TODO: try this simplification out, see how it works:

		if (simplify) {
			state = stateFactory.simplify(state);
		}

		if (saveStates) {
			state = stateFactory.canonic(state);
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
	 * @return The maximum number of processes in any state encountered by this
	 *         state manager.
	 */
	public int maxProcs() {
		return maxProcs;
	}

	@Override
	public boolean onStack(CommonState state) {
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
	public void printStateLong(PrintStream out, CommonState state) {
		state.print(out);
	}

	@Override
	public void printStateShort(PrintStream out, CommonState state) {
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
	public boolean seen(CommonState state) {
		return state.seen();
	}

	@Override
	public void setOnStack(CommonState state, boolean value) {
		state.setOnStack(value);
	}

	@Override
	public void setSeen(CommonState state, boolean value) {
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
	public int getDepth(CommonState state) {
		return state.getDepth();
	}

	@Override
	public void setDepth(CommonState state, int value) {
		state.setDepth(value);
	}

}
