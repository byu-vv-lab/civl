/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * An assignment statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class AssignStatement extends Statement {

	private Expression lhs;
	private Expression rhs;

	/**
	 * An assignment statement.
	 * 
	 * @param source
	 *            The source location for this statement.
	 * @param lhs
	 *            The left hand side of the assignment.
	 * @param rhs
	 *            The right hand side of the assignment.
	 */
	public AssignStatement(Location source, Expression lhs, Expression rhs) {
		super(source);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	/**
	 * @return The left hand side of the assignment.
	 */
	public Expression getLhs() {
		return lhs;
	}

	/**
	 * @return The right hand side of the assignment.
	 */
	public Expression rhs() {
		return rhs;
	}

	/**
	 * @param lhs
	 *            The left hand side of the assignment.
	 */
	public void setLhs(Expression lhs) {
		this.lhs = lhs;
	}

	/**
	 * @param rhs
	 *            The right hand side of the assignment.
	 */
	public void setRhs(Expression rhs) {
		this.rhs = rhs;
	}
	
	@Override
	public String toString() {
		return lhs + " = " + rhs;
	}

}
