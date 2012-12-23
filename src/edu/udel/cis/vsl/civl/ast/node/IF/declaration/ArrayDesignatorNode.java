package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * An array designator specifies the index of an element of an array being
 * initialized.
 * 
 * @author siegel
 * 
 */
public interface ArrayDesignatorNode extends DesignatorNode {

	/**
	 * Gets the constant expression which yields the index to initialize.
	 * 
	 * @return array index expression
	 */
	ExpressionNode getIndex();

	void setIndex(ExpressionNode expression);
}
