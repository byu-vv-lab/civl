/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

/**
 * Self expression. Returns a reference to the process in which the expression is
 * evaluated.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class SelfExpression extends Expression {

	/**
	 * Self expression. Returns a reference to the process in which the expression
	 * is evaluated.
	 */
	public SelfExpression() {
	}

	@Override
	public String toString() {
		return "me";
	}
}
