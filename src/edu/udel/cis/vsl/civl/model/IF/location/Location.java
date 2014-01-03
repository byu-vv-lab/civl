/**
 * 
 */
package edu.udel.cis.vsl.civl.model.IF.location;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.Sourceable;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.common.location.CommonLocation.AtomicKind;

/**
 * The parent of all locations.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface Location extends Sourceable {
	/**
	 * @return The unique ID number of this location.
	 */
	public int id();

	/**
	 * @return The scope of this location.
	 */
	public Scope scope();

	/**
	 * @return The function containing this location.
	 */
	public CIVLFunction function();

	/**
	 * @return The set of incoming statements.
	 */
	public Iterable<Statement> incoming();

	/**
	 * @return The set of outgoing statements.
	 */
	public Iterable<Statement> outgoing();

	/**
	 * @return The number of outgoing statements.
	 */
	public int getNumOutgoing();

	/**
	 * @return The number of incoming statements.
	 */
	public int getNumIncoming();

	/**
	 * 
	 * @param i
	 *            index of the statement
	 * @return The i'th outgoing statement
	 */
	public Statement getOutgoing(int i);

	/**
	 * 
	 * @param i
	 *            index of the statement
	 * @return The i'th incoming statement
	 */
	public Statement getIncoming(int i);

	/**
	 * Returns the sole outgoing statement from this location.
	 * 
	 * @return the outgoing statement
	 * @throws CIVLInternalException
	 *             if the number of outgoing statements from this location is
	 *             not 1
	 */
	public Statement getSoleOutgoing();

	/**
	 * Set the unique ID number of this location.
	 * 
	 * @param id
	 *            The unique ID number of this location.
	 */
	public void setId(int id);

	/**
	 * @param scope
	 *            The scope of this location.
	 */
	public void setScope(Scope scope);

	/**
	 * @param statement
	 *            A new incoming statement.
	 */
	public void addIncoming(Statement statement);

	/**
	 * @param statement
	 *            A new outgoing statement.
	 */
	public void addOutgoing(Statement statement);

	/**
	 * Print this location and all outgoing transitions.
	 * 
	 * @param prefix
	 *            The prefix string for all lines of this printout.
	 * @param out
	 *            The PrintStream to use for printing this location.
	 */
	public void print(String prefix, PrintStream out);

	/**
	 * 
	 * @return true iff the location is purely local
	 */
	public boolean isPurelyLocal();

	/**
	 * Analyze if the location is purely local
	 */
	public void purelyLocalAnalysis();

	/**
	 * Remove a certain outgoing statement
	 * 
	 * @param statement
	 *            The outgoing statement to be removed
	 */
	void removeOutgoing(Statement statement);

	/**
	 * Remove a certain incoming statement
	 * 
	 * @param statement
	 *            The incoming statement to be removed
	 */
	void removeIncoming(Statement statement);

	/**
	 * This location is the start location of a certain atomic block
	 * 
	 * @param deterministic
	 *            True iff the atomic block is a $datomic block
	 */
	void setEnterAtomic(boolean deterministic);

	/**
	 * This location is the end location of a certain atomic block
	 * 
	 * @param deterministic
	 *            True iff the atomic block is a $datomic block
	 */
	void setLeaveAtomic(boolean deterministic);

	/**
	 * Check if the location is entering a deterministic atomic block.
	 * 
	 * @return true iff the location is entering a deterministic atomic block.
	 * 
	 */
	boolean enterDatomic();

	/**
	 * Check if the location is entering a general atomic block.
	 * 
	 * @return true iff the location is entering a general atomic block.
	 * 
	 */
	boolean enterAtomic();

	/**
	 * 
	 * @return true iff the location is leaving a deterministic atomic block
	 */
	boolean leaveDatomic();
	
	/**
	 * 
	 * @return true iff the location is leaving a general atomic block
	 */
	boolean leaveAtomic();
	
	AtomicKind atomicKind();
}
