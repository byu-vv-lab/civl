package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SpawnNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonSpawnNode extends CommonExpressionNode implements SpawnNode {

	public CommonSpawnNode(Source source, FunctionCallNode callNode) {
		super(source, callNode);
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public FunctionCallNode getCall() {
		return (FunctionCallNode) child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("spawn");
	}

}
