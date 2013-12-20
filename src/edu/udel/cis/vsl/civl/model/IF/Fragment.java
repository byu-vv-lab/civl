package edu.udel.cis.vsl.civl.model.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;

public interface Fragment {

	Location startLocation();

	Statement lastStatement();
	
	void setStartLocation(Location location);
	void setLastStatement(Statement statement);

	/**
	 * Combine two fragment in sequential
	 * 
	 * @param next
	 *            the fragment that comes after the current fragment
	 * @return the sequential combination of both fragments
	 */
	Fragment combineWith(Fragment next);

	/**
	 * Combine this fragment and another fragment in parallel, i.e., merge the
	 * start location, and add the last statement of both fragments as the last
	 * statement of the result fragment
	 * 
	 * @param parallel
	 *            the second fragment to be combined with <dt>
	 *            <b>Preconditions:</b>
	 *            <dd>
	 *            this.startLocation.id() === parallel.startLocation.id()
	 * 
	 * @return the new fragment after the combination
	 */
	Fragment parallelCombineWith(Fragment parallel);

	/**
	 * Check if the fragment is empty
	 * 
	 * @return true iff both the start location and the last statement are null
	 */
	boolean isEmpty();

	/**
	 * Print the fragment
	 * 
	 * @param out
	 *            the print stream
	 */
	void Print(PrintStream out);

	/**
	 * Update the start location with a new location
	 * 
	 * @param newLocation
	 *            the new start location
	 */
	void updateStartLocation(Location newLocation);

}
