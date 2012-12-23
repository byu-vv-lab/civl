package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.StaticAssertionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonStaticAssertionNode extends CommonASTNode implements
		StaticAssertionNode {

	public CommonStaticAssertionNode(Source source, ExpressionNode expression,
			StringLiteralNode message) {
		super(source, expression, message);
	}

	@Override
	public ExpressionNode getExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	public StringLiteralNode getMessage() {
		return (StringLiteralNode) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("StaticAssertion");
	}

}
