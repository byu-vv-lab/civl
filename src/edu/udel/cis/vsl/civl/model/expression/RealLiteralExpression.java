/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

import java.math.BigDecimal;

/**
 * A real literal.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class RealLiteralExpression extends LiteralExpression {

	private BigDecimal value;

	/**
	 * A real literal.
	 * 
	 * @param value
	 *            The (arbitrary precision) real value.
	 */
	public RealLiteralExpression(BigDecimal value) {
		this.value = value;
	}

	/**
	 * @return The (arbitrary precision) real value.
	 */
	public BigDecimal value() {
		return value;
	}

	/**
	 * @param value
	 *            The (arbitrary precision) real value.
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}

}
