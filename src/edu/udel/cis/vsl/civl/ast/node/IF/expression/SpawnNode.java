package edu.udel.cis.vsl.civl.ast.node.IF.expression;

/**
 * Represents a "spawn" expression, which has the form "spawn f(e1,...,en)". It
 * is essentially a function call with "spawn" prepended.
 * 
 * @author siegel
 * 
 */
public interface SpawnNode extends ExpressionNode {

	/**
	 * Returns the function call node, which is like removing the "spawn".
	 * 
	 * @return function call node
	 */
	FunctionCallNode getCall();

}
