package edu.udel.cis.vsl.civl.kripke.IF;

import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.util.IF.Printable;
import edu.udel.cis.vsl.gmc.StateManagerIF;

public interface StateManager extends StateManagerIF<State, Transition> {

	// /**
	// * Set the field out.
	// *
	// * @param out
	// * The output stream to be used.
	// */
	// void setOutputStream(PrintStream out);

	/**
	 * Set the field verbose.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	void setVerbose(boolean value);

	/**
	 * Set the field showStates.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	void setShowStates(boolean value);

	/**
	 * Set the field showSavedStates.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	void setShowSavedStates(boolean value);

	/**
	 * Set the field showTransitions.
	 * 
	 * @param value
	 *            The value to be used.
	 */
	void setShowTransitions(boolean value);

	/**
	 * Returns the number of objects of type State that have been instantiated
	 * since this JVM started.
	 * 
	 * @return the number of states instantiated
	 */
	long getNumStateInstances();

	/**
	 * Returns the number of states saved, i.e., made canonic.
	 * 
	 * @return the number of canonic states
	 */
	int getNumStatesSaved();

	/**
	 * @return The maximum number of processes in any state encountered by this
	 *         state manager.
	 */
	int maxProcs();

	/**
	 * Print an update message at your earliest possible convenience.
	 */
	void printUpdate();

	/**
	 * Set the field savedStates.
	 * 
	 * @param updater
	 *            The value to be used.
	 */
	void setUpdater(Printable updater);
}
