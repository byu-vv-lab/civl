package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

public interface AssumeNode extends StatementNode {

	/**
	 * The argument to the assume statement.
	 * 
	 * @return the assumed expression
	 */
	ExpressionNode getExpression();
}
