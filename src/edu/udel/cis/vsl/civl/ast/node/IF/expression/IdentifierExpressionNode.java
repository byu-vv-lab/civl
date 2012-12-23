package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;

/**
 * Represents the use of a variable, enumeration constant???, or function name as
 * an expression.
 * 
 * @author siegel
 * 
 */
public interface IdentifierExpressionNode extends ExpressionNode {

	IdentifierNode getIdentifier();

	void setIdentifier(IdentifierNode identifier);

}
