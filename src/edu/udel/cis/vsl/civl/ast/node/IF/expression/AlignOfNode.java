package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * The _Alignof(typename) operator. See C11 Sec. 6.5.3.4. Results in an integer
 * constant.
 * 
 * @author siegel
 * 
 */
public interface AlignOfNode extends ExpressionNode {

	TypeNode getArgument();

	void setArgument(TypeNode type);

}
