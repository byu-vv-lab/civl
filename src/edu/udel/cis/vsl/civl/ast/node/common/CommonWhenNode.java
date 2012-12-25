package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonWhenNode extends CommonASTNode implements WhenNode {

	public CommonWhenNode(Source source, ExpressionNode guard,
			StatementNode body) {
		super(source, guard, body);
	}

	@Override
	public ExpressionNode getGuard() {
		return (ExpressionNode) child(0);
	}

	@Override
	public StatementNode getBody() {
		return (StatementNode) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("When");
	}

}
