package edu.udel.cis.vsl.civl.ast.node.IF.expression;

public interface RemoteExpressionNode extends ExpressionNode {

	ExpressionNode getProcessExpression();

	IdentifierExpressionNode getIdentifierNode();

}
