/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * An assert statement.
 * 
 * @author zirkel
 * 
 */
public class AssertStatement extends Statement {

	private boolean isCollective = false;
	private Expression expression;

	/**
	 * @param source
	 *            The source location for this statement.
	 * @param expression
	 *            The expression being checked.
	 */
	public AssertStatement(Location source, Expression expression) {
		super(source);
		this.expression = expression;
	}

	/**
	 * @return Whether this is a collective assertion.
	 */
	public boolean isCollective() {
		return isCollective;
	}

	/**
	 * @return The expression being checked.
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param isCollective
	 *            Whether this is a collective assertion.
	 */
	public void setCollective(boolean isCollective) {
		this.isCollective = isCollective;
	}

	/**
	 * @param expression
	 *            The expression being checked.
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
