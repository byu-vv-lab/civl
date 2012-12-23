package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * Compound literals are used to represent literal array, structure, and union
 * values. See C11 Sec. 6.5.2.5 and Sec. 6.7.9.
 * 
 * @author siegel
 * 
 */
public interface CompoundLiteralNode extends ExpressionNode {

	TypeNode getTypeNode();

	CompoundInitializerNode getInitializerList();

}
