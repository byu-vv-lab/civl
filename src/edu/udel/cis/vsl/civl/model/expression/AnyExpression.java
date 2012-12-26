/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

/**
 * An expression used to receive any tag and store it into a tag variable.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class AnyExpression extends Expression {

	private Expression tag;

	/**
	 * An expression used to receive any tag and store it into a tag variable.
	 * 
	 * @param tag
	 *            The location to store the tag.
	 */
	public AnyExpression(Expression tag) {
		this.tag = tag;
	}

	/**
	 * @return The location to store the tag.
	 */
	public Expression tag() {
		return tag;
	}

	/**
	 * @param tag
	 *            The location to store the tag.
	 */
	public void setTag(Expression tag) {
		this.tag = tag;
	}
	
	@Override
	public String toString() {
		return "any(" + tag + ")";
	}

}
