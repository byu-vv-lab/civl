package edu.udel.cis.vsl.civl.ast.node.IF.type;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;

public interface TypedefNameNode extends TypeNode {

	IdentifierNode getName();

	void setName(IdentifierNode name);

}
