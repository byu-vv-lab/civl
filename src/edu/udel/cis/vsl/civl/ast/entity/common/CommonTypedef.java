package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonTypedef extends CommonOrdinaryEntity implements Typedef {

	public CommonTypedef(String name, Type type) {
		super(EntityKind.TYPEDEF, name, LinkageKind.NONE, type);
	}

	@Override
	public TypedefDeclarationNode getDefinition() {
		return (TypedefDeclarationNode) super.getDefinition();
	}

}
