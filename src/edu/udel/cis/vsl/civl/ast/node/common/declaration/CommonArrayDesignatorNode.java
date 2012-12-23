package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ArrayDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonArrayDesignatorNode extends CommonASTNode implements
		ArrayDesignatorNode {

	public CommonArrayDesignatorNode(Source source, ExpressionNode index) {
		super(source, index);
	}

	@Override
	public ExpressionNode getIndex() {
		return (ExpressionNode) child(0);
	}

	@Override
	public void setIndex(ExpressionNode expression) {
		setChild(0, expression);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("ArrayIndex");
	}

}
