/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * A noop statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class NoopStatement extends Statement {

	/**
	 * A noop statement.
	 * 
	 * @param source
	 *            The source location for this noop.
	 */
	public NoopStatement(Location source) {
		super(source);
	}
	
	@Override
	public String toString() {
		return "";
	}

}
