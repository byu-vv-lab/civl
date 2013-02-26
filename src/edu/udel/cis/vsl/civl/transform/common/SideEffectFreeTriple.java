package edu.udel.cis.vsl.civl.transform.common;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;

/**
 * A side effect free triple is used by the side effect remover. When removing
 * side effects from an expression, the result is a side effect free version of
 * the expression, combined with (possibly empty) lists of statements that
 * should come before or after the use of the expression.
 * 
 * @author zirkel
 */
public class SideEffectFreeTriple {

	private List<BlockItemNode> before, after;
	private ExpressionNode expression;

	/**
	 * 
	 * @param before
	 *            The block items that come before this expression.
	 * @param expression
	 *            The side effect free expression.
	 * @param after
	 *            The block items that come after this expression.
	 */
	public SideEffectFreeTriple(List<BlockItemNode> before,
			ExpressionNode expression, List<BlockItemNode> after) {
		this.before = before;
		this.expression = expression;
		this.after = after;
	}

	/**
	 * @return The block items that come before this expression.
	 */
	public List<BlockItemNode> getBefore() {
		return before;
	}

	/**
	 * @return The block items that come after this expression.
	 */
	public List<BlockItemNode> getAfter() {
		return after;
	}

	/**
	 * @return The side effect free expression.
	 */
	public ExpressionNode getExpression() {
		return expression;
	}

	/**
	 * @param before
	 *            The block items that come before this expression.
	 */
	public void setBefore(List<BlockItemNode> before) {
		this.before = before;
	}

	/**
	 * @param after
	 *            The block items that come after this expression.
	 */
	public void setAfter(List<BlockItemNode> after) {
		this.after = after;
	}

	/**
	 * @param expression
	 *            The side effect free expression.
	 */
	public void setExpression(ExpressionNode expression) {
		this.expression = expression;
	}

}
