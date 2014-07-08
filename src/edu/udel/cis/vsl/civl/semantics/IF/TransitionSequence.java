/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics.IF;

import java.util.Collection;
import java.util.LinkedList;

import edu.udel.cis.vsl.civl.state.IF.State;

/**
 * A transition sequence is a linked list of transitions and the state from
 * which they depart.
 * 
 * TODO: don't extend LinkedList
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class TransitionSequence {

	/**
	 * All the transitions emanating from a certain state.
	 */
	private LinkedList<Transition> transitions;

	/**
	 * This is the state from which all the transitions in the sequence emanate.
	 */
	private State state;

	/**
	 * The number of elements removed from this sequence since it was created.
	 */
	private int numRemoved = 0;

	/**
	 * Create an empty transition sequence.
	 * 
	 * @param state
	 *            The state of the program before this transition departs.
	 */
	public TransitionSequence(State state) {
		this.state = state;
		this.transitions = new LinkedList<Transition>();
	}

	public Transition remove() {
		Transition result = transitions.remove();

		numRemoved++;
		return result;
	}

	/**
	 * Returns the number of transitions removed from this sequence since it was
	 * first created.
	 * 
	 * @return the number of transitions removed
	 */
	public int numRemoved() {
		return numRemoved;
	}

	/** The source state from which all transitions in this sequence depart. */
	public State state() {
		return state;
	}

	/**
	 * Adds transitions to this sequence.
	 * 
	 * @param transitions
	 *            The transitions to be added to this sequence.
	 */
	public void addAll(Collection<Transition> transitions) {
		this.transitions.addAll(transitions);
	}

	/**
	 * Returns
	 * @return
	 */
	public int size() {
		return this.transitions.size();
	}

	public boolean isEmpty() {
		return this.transitions.isEmpty();
	}

	public Transition peek() {
		return this.transitions.peek();
	}
}
