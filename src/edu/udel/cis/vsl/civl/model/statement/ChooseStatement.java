/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * A choose statement is of the form x = choose(n);
 * 
 * When a choose statement is executed, the left hand side will be assigned a
 * new symbolic constant. A bound on the values of that symbolic constant will
 * be added to the path condition.
 * 
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class ChooseStatement extends AssignStatement {

	private int chooseID;
	
	/**
	 * A choose statement is of the form x = choose(n);
	 * 
	 * When a choose statement is executed, the left hand side will be assigned
	 * a new symbolic constant. A bound on the values of that symbolic constant
	 * will be added to the path condition.
	 * 
	 * @param source
	 *            The source location for this choose statement.
	 * @param lhs
	 *            The left hand side of the choose statement.
	 * @param argument
	 *            The argument to choose().
	 */
	public ChooseStatement(Location source, Expression lhs, Expression argument, int chooseID) {
		super(source, lhs, argument);
		this.chooseID = chooseID;
	}

	@Override
	public String toString() {
		return getLhs() + " = choose(" + rhs() + ")";
	}
	
	/**
	 * @return A unique ID for this choose.
	 */
	public int chooseID() {
		return chooseID;
	}
}
