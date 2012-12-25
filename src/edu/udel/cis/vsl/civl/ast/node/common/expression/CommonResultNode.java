package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonResultNode extends CommonExpressionNode {

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
