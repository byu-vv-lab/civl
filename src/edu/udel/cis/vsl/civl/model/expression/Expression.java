/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

import edu.udel.cis.vsl.civl.model.Scope;

/**
 * The parent of all expressions.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Expression {

	private Scope expressionScope = null;

	/**
	 * The parent of all expressions.
	 */
	public Expression() {
	}

	/**
	 * @return The highest scope accessed by this expression. Null if no
	 *         variables accessed.
	 */
	public Scope expressionScope() {
		return expressionScope;
	}

	/**
	 * @param expressionScope
	 *            The highest scope accessed by this expression. Null if no
	 *            variables accessed.
	 */
	public void setExpressionScope(Scope expressionScope) {
		this.expressionScope = expressionScope;
	}

}
