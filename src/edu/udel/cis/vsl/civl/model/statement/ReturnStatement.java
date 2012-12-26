/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * A return statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class ReturnStatement extends Statement {

	private Expression expression;

	/**
	 * A return statement.
	 * 
	 * @param source
	 *            The source location for this return statement.
	 * @param expression
	 *            The expression being returned. Null if non-existent.
	 */
	public ReturnStatement(Location source, Expression expression) {
		super(source);
		this.expression = expression;
	}

	/**
	 * @return The expression being returned. Null if non-existent.
	 */
	public Expression expression() {
		return expression;
	}

	/**
	 * @param expression
	 *            The expression being returned. Null if non-existent.
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		if (expression == null) {
			return "return";
		}
		return "return " + expression;
	}

}
