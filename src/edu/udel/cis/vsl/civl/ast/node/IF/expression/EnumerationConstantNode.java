package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;

public interface EnumerationConstantNode extends ConstantNode {

	IdentifierNode getName();

	void setName(IdentifierNode name);

}
