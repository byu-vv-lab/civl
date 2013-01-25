/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * A join statement, to wait for another process to complete.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class JoinStatement extends Statement {

	private Expression process;

	/**
	 * A join statement, to wait for another process to complete.
	 * 
	 * @param source
	 *            The source location for this join.
	 * @param process
	 *            A reference to the process.
	 */
	public JoinStatement(Location source, Expression process) {
		super(source);
		this.process = process;
	}

	/**
	 * @return The process.
	 */
	public Expression process() {
		return process;
	}

	/**
	 * @param process
	 *            The process.
	 */
	public void setProcess(Expression process) {
		this.process = process;
	}
	
	@Override
	public String toString() {
		return "wait " + process;
	}

}
