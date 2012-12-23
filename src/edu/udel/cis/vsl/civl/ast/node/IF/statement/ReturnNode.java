package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

public interface ReturnNode extends JumpNode {

	/**
	 * The expression to return. May be null.
	 * 
	 * @return expression to return or null
	 */
	ExpressionNode getExpression();
}
