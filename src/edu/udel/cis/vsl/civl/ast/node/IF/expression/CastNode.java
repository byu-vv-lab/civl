package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * A type cast expression. Has form "(typeName)expr". The type to which the
 * expression is being cast (typeName) is obtained by the getCastType() method .
 * 
 * See C11 Sec. 6.5.4.
 * 
 * @author siegel
 * 
 */
public interface CastNode extends ExpressionNode {

	TypeNode getCastType();

	ExpressionNode getArgument();

	void setCastType(TypeNode type);

	void setArgument(ExpressionNode expression);

}
