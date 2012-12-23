package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;

public interface Typedef extends OrdinaryEntity {

	@Override
	TypedefDeclarationNode getDefinition();

}
