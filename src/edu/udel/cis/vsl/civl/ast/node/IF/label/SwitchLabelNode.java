package edu.udel.cis.vsl.civl.ast.node.IF.label;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * Represents a label in a "switch" statement of the form
 * "case constant-expression:" or "default:".
 * 
 * @author siegel
 * 
 */
public interface SwitchLabelNode extends LabelNode {

	boolean isDefault();

	ExpressionNode getExpression();

}
