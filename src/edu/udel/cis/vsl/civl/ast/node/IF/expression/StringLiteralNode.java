package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.value.IF.StringValue;

public interface StringLiteralNode extends ExpressionNode {

	/**
	 * Returns the string literal exactly as it occurred in the source code,
	 * with escape sequences, etc.
	 * 
	 * @return original representation in source code
	 */
	String getStringRepresentation();

	void setStringRepresentation(String representation);

	/**
	 * The "executed" version of the string in Java: escape sequences have been
	 * replace with the appropriate Java characters, etc.
	 * 
	 * @return string after interpretation
	 */
	StringValue getConstantValue();

}
