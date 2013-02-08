/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * An assume statement provides an expression which is to be added to the path
 * condition.
 * 
 * @author zirkel
 * 
 */
public class AssumeStatement extends Statement {

	private Expression expression;

	/**
	 * An assume statement.
	 * 
	 * @param source
	 *            The source location for this statement.
	 * @param expression
	 *            The expression being added to the path condition.
	 */
	public AssumeStatement(Location source, Expression expression) {
		super(source);
		this.expression = expression;
	}

	/**
	 * @return The expression being added to the path condition.
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            The expression being added to the path condition.
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
