/**
 * 
 */
package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
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

	private PrintStream debugOut = null;

	private StateFactoryIF stateFactory;

	private int maxProcs = 0;

	public StateManager(Executor executor) {
		this.executor = executor;
		this.stateFactory = executor.stateFactory();
	}

	public void setDebugOut(PrintStream debugOut) {
		this.debugOut = debugOut;
	}

	@Override
	public State nextState(State state, Transition transition) {
		int pid;
		Statement statement;
		State newState;

		assert transition instanceof SimpleTransition;
		pid = ((SimpleTransition) transition).pid();
		newState = stateFactory.setPathCondition(state,
				((SimpleTransition) transition).pathCondition());
		statement = ((SimpleTransition) transition).statement();
		if (transition instanceof ChooseTransition) {
			assert statement instanceof ChooseStatement;
			newState = executor.executeChoose(state, pid,
					(ChooseStatement) statement,
					((ChooseTransition) transition).value());
		} else {
			newState = executor.execute(state, pid, statement);
		}
		newState = stateFactory.canonic(newState);
		if (debugOut != null) {
			newState.print(debugOut);
		}
		if (newState.numProcs() > maxProcs) {
			maxProcs = newState.numProcs();
		}
		return newState;
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
	public void printStateLong(PrintStream arg0, State arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printStateShort(PrintStream arg0, State arg1) {
		arg0.print(arg1.toString());
	}

	@Override
	public void printTransitionLong(PrintStream arg0, Transition arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printTransitionShort(PrintStream arg0, Transition arg1) {
		// TODO Auto-generated method stub

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

}
