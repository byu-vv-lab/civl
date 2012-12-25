package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.RemoteExpressionNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonRemoteExpressionNode extends CommonExpressionNode implements
		RemoteExpressionNode {

	public CommonRemoteExpressionNode(Source source,
			ExpressionNode processExpression,
			IdentifierExpressionNode identifierNode) {
		super(source, processExpression, identifierNode);

	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public ExpressionNode getProcessExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	public IdentifierExpressionNode getIdentifierNode() {
		return (IdentifierExpressionNode) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("RemoteExpression");
	}
}
