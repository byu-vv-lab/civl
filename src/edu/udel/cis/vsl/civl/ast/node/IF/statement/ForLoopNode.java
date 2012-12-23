package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * A for loop, in addition to the expression and body that all loops possess,
 * has an initializer and incrementer.
 * 
 * The initializer can be either an expression or a declaration.
 * 
 * See C11 Sec. 6.8.5.
 * 
 * @author siegel
 * 
 */
public interface ForLoopNode extends LoopNode {

	/** Either: expression or list of declaration. */
	ForLoopInitializerNode getInitializer();

	ExpressionNode getIncrementer();

}
