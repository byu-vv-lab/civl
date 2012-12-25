package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * An "ensures" clause in a procedure contract.
 * 
 * @author siegel
 * 
 */
public interface EnsuresNode extends ContractNode {

	ExpressionNode getExpression();

}
