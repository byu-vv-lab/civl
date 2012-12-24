package edu.udel.cis.vsl.civl.ast.node.IF.expression;

public interface CollectiveExpressionNode extends ExpressionNode {

	ExpressionNode getProcessPointerExpression();

	ExpressionNode getLengthExpression();

	ExpressionNode getBody();

}
