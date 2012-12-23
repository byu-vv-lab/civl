package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

public interface ExpressionStatementNode extends StatementNode {

	ExpressionNode getExpression();
}
