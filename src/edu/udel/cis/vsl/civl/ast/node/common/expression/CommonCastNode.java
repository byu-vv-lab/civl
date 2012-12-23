package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCastNode extends CommonExpressionNode implements CastNode {

	public CommonCastNode(Source source, TypeNode typeNode,
			ExpressionNode expression) {
		super(source, typeNode, expression);
	}

	@Override
	public TypeNode getCastType() {
		return (TypeNode) child(0);
	}

	@Override
	public ExpressionNode getArgument() {
		return (ExpressionNode) child(1);
	}

	@Override
	public void setCastType(TypeNode typeNode) {
		setChild(0, typeNode);
	}

	@Override
	public void setArgument(ExpressionNode expression) {
		setChild(1, expression);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("CastExpression");
	}

	@Override
	public boolean isConstantExpression() {
		return !getCastType().getType().isVariablyModified()
				&& getArgument().isConstantExpression();
	}

	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonCastNode) {
	// CommonCastNode that = (CommonCastNode) expression;
	// Type thisType = getCastType().getType();
	// Type thatType = that.getCastType().getType();
	// CommonExpressionNode thisArg = (CommonExpressionNode) getArgument();
	// ExpressionNode thatArg = that.getArgument();
	//
	// return thisType.equals(thatType)
	// && thisArg.equivalentConstant(thatArg);
	// }
	// return false;
	// }
}
