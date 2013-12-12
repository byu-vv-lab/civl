package edu.udel.cis.vsl.civl.state.IF;

import edu.udel.cis.vsl.civl.model.IF.location.Location;

public interface StackEntry {

	Location location();

	/**
	 * @return The dynamic scope of the process at the time of the function
	 *         call.
	 */
	int scope();
}
