package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeofNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonSizeofNode extends CommonExpressionNode implements
		SizeofNode {

	public CommonSizeofNode(Source source, SizeableNode argument) {
		super(source, argument);
	}

	@Override
	public SizeableNode getArgument() {
		return (SizeableNode) child(0);
	}

	@Override
	public void setArgument(SizeableNode argument) {
		setChild(0, argument);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("SizeOf");
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonSizeofNode) {
	// CommonSizeofNode that = (CommonSizeofNode) expression;
	// Type thisType = getArgument().getType();
	// Type thatType = that.getArgument().getType();
	//
	// return thisType.equals(thatType);
	// }
	// return false;
	// }

	@Override
	public boolean isConstantExpression() {
		return !getArgument().getType().isVariablyModified();
	}

}
