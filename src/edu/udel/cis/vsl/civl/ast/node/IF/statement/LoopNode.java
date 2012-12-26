package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * Root of type hierarchy for every kind of loop statement. Every such statement
 * has at least a condition specifying when to stay in the loop, and a body.
 * 
 * @author siegel
 * 
 */
public interface LoopNode extends StatementNode {

	public static enum LoopKind {
		WHILE, DO_WHILE, FOR
	};

	/**
	 * The condition which controls when to stay in or exit the loop. For WHILE
	 * and FOR loops, evaluated before entering body; for DO_WHILE, evaluated
	 * after each execution of body.
	 * 
	 * @return the loop condition
	 */
	ExpressionNode getCondition();

	/**
	 * The loop body.
	 * 
	 * @return the loop body
	 */
	StatementNode getBody();

	ExpressionNode getInvariant();

	/**
	 * What kind of loop is this?
	 * 
	 * @return the loop kind
	 */
	LoopKind getKind();

}
