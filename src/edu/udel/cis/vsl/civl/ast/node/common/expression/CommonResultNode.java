package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ResultNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonResultNode extends CommonExpressionNode implements
		ResultNode {

	public CommonResultNode(Source source) {
		super(source);
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("\\result");
	}

}
