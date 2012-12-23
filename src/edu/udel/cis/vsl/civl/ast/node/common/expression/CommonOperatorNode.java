package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonOperatorNode extends CommonExpressionNode implements
		OperatorNode {

	private Operator operator;

	public CommonOperatorNode(Source source, Operator operator,
			List<ExpressionNode> arguments) {
		super(source, arguments);
		this.operator = operator;
	}

	@Override
	public Operator getOperator() {
		return operator;
	}

	@Override
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	public int getNumberOfArguments() {
		return this.numChildren();
	}

	@Override
	public ExpressionNode getArgument(int index) {
		return (ExpressionNode) child(index);
	}

	@Override
	public void setArgument(int index, ExpressionNode value) {
		setChild(index, value);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("OperatorExpression[operator=" + operator + "]");
	}

	private boolean hasConstantOperator() {
		switch (operator) {
		case ASSIGN:
		case BITANDEQ:
		case BITOREQ:
		case BITXOREQ:
		case DIVEQ:
		case MINUSEQ:
		case MODEQ:
		case PLUSEQ:
		case POSTDECREMENT:
		case POSTINCREMENT:
		case PREDECREMENT:
		case PREINCREMENT:
		case SHIFTLEFTEQ:
		case SHIFTRIGHTEQ:
		case TIMESEQ:
			return false;
		default:
			return true;
		}
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonOperatorNode) {
	// CommonOperatorNode that = (CommonOperatorNode) expression;
	// int numArgs = getNumberOfArguments();
	//
	// if (!hasConstantOperator() || !operator.equals(that.operator))
	// return false;
	// if (numArgs != that.getNumberOfArguments())
	// return false;
	// for (int i = 0; i < numArgs; i++) {
	// CommonExpressionNode thisArg = (CommonExpressionNode) this
	// .getArgument(i);
	// CommonExpressionNode thatArg = (CommonExpressionNode) that
	// .getArgument(i);
	//
	// if (!thisArg.equivalentConstant(thatArg))
	// return false;
	// }
	// return true;
	// }
	// return false;
	// }

	@Override
	public boolean isConstantExpression() {
		int numArgs = getNumberOfArguments();

		if (!hasConstantOperator())
			return false;
		for (int i = 0; i < numArgs; i++) {
			if (!getArgument(i).isConstantExpression())
				return false;
		}
		return true;
	}

}
