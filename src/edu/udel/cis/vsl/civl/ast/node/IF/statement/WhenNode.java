package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * A "when" statement has the form "when (guard) body", where guard is a
 * boolean-valued expression and body is a statement.
 * 
 * @author siegel
 * 
 */
public interface WhenNode extends StatementNode {

	/**
	 * The guard: a boolean expression which must hold in order for the body to
	 * be executed.
	 * 
	 * @return the guard
	 */
	ExpressionNode getGuard();

	/**
	 * The body of the "when" statement.
	 * 
	 * @return
	 */
	StatementNode getBody();

}
