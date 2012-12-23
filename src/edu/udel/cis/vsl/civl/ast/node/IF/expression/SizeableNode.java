package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

/**
 * A marker interface indicating that this object can be used as an argument to
 * the "sizeof" operator. I.e., this object is either an expression or a type.
 * 
 * @author siegel
 * 
 */
public interface SizeableNode extends ASTNode {

	/**
	 * If this is a type node, returns the conceptual C type associated to the
	 * type node; if this is an expression node, returns the converted type
	 * associated to the expression.
	 * 
	 * @return the C type defined by this type node
	 */
	Type getType();

}
