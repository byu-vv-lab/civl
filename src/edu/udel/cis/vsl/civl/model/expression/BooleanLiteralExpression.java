/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

/**
 * A literal boolean value.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class BooleanLiteralExpression extends LiteralExpression {

	private boolean value;

	/**
	 * A literal boolean value.
	 * 
	 * @param The
	 *            value of this boolean literal.
	 */
	public BooleanLiteralExpression(boolean value) {
		this.value = value;
	}

	/**
	 * @return The value of this boolean literal.
	 */
	public boolean value() {
		return value;
	}

	/**
	 * @param The
	 *            value of this boolean literal.
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (value) {
			return "true";
		}
		return "false";
	}
}
