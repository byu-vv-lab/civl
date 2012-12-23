package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.value.IF.Value;

/**
 * A "constant" in the sense of the C11 Standard. See C11 Sec. 6.4.4. Note that
 * C uses the word "constant" in a limited way. Character, integer, and floating
 * point literals and enumeration constants are all considered "constants".
 * String literals and compound literals (array, structure, and union literals)
 * are not considered constants.
 * 
 */
public interface ConstantNode extends ExpressionNode {

	/**
	 * Returns the representation of the constant exactly as it occurred in the
	 * source code.
	 * 
	 * @return
	 */
	String getStringRepresentation();

	void setStringRepresentation(String representation);

	Value getConstantValue();
}
