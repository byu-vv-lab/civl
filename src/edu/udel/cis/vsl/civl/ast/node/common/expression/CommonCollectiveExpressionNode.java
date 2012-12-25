package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.CollectiveExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCollectiveExpressionNode extends CommonExpressionNode
		implements CollectiveExpressionNode {

	public CommonCollectiveExpressionNode(Source source,
			ExpressionNode processPointerExpression,
			ExpressionNode lengthExpression, ExpressionNode body) {
		super(source, processPointerExpression, lengthExpression, body);
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public ExpressionNode getProcessPointerExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	public ExpressionNode getLengthExpression() {
		return (ExpressionNode) child(1);
	}

	@Override
	public ExpressionNode getBody() {
		return (ExpressionNode) child(2);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("CollectiveExpression");
	}

}
