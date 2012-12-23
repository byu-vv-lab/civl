package edu.udel.cis.vsl.civl.ast.node.IF;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;

/**
 * A static assertion has syntax
 * "_Static_assert ( constant-expression , string-literal )". It may appear
 * anywhere an external definition may appear, i.e., in the outermost file
 * scope. It is an assertion which is checked statically. The constant
 * expression is evaluated. If it is 0, a violation is reported.
 * 
 * @author siegel
 * 
 */
public interface StaticAssertionNode extends ExternalDefinitionNode,
		BlockItemNode {

	ExpressionNode getExpression();

	StringLiteralNode getMessage();

}
