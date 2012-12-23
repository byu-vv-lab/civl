package edu.udel.cis.vsl.civl.ast.node.common.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonReturnNode extends CommonJumpNode implements ReturnNode {

	public CommonReturnNode(Source source, ExpressionNode expression) {
		super(source, JumpKind.RETURN);
		addChild(expression);
	}

	@Override
	public ExpressionNode getExpression() {
		return (ExpressionNode) child(0);
	}

}
