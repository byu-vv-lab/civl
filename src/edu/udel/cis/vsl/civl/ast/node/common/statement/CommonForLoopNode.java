package edu.udel.cis.vsl.civl.ast.node.common.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonForLoopNode extends CommonLoopNode implements ForLoopNode {

	public CommonForLoopNode(Source source, ExpressionNode condition,
			StatementNode statement, ForLoopInitializerNode initializer,
			ExpressionNode incrementer, ExpressionNode invariant) {
		super(source, LoopKind.FOR, condition, statement, invariant);
		addChild(initializer);
		addChild(incrementer);
	}

	@Override
	public ForLoopInitializerNode getInitializer() {
		return (ForLoopInitializerNode) child(3);
	}

	@Override
	public ExpressionNode getIncrementer() {
		return (ExpressionNode) child(4);
	}

}
