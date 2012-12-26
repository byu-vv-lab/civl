/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

/**
 * A string literal.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class StringLiteralExpression extends LiteralExpression {

	private String value;

	/**
	 * A string literal.
	 * 
	 * @param value
	 *            The string.
	 */
	public StringLiteralExpression(String value) {
		this.value = value;
	}
	
	/**
	 * @return The string.
	 */
	public String value() {
		return value;
	}
	
	/**
	 * @param value The string.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
