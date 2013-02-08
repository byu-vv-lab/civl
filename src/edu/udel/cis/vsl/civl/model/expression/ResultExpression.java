/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

/**
 * This expression is only used in an ensures clause of a function contract to
 * refer to the returned value.
 * 
 * @author zirkel
 * 
 */
public class ResultExpression extends Expression {

	/**
	 * This expression is only used in an ensures clause of a function contract
	 * to refer to the returned value.
	 */
	public ResultExpression() {
		
	}

	public String toString() {
		return "\\result";
	}
}
