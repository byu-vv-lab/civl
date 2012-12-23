package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Label;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;

public class CommonLabel extends CommonEntity implements Label {

	public CommonLabel(OrdinaryLabelNode declaration) {
		super(EntityKind.LABEL, declaration.getName(), LinkageKind.NONE);
		addDeclaration(declaration);
		setDefinition(declaration);
	}

	@Override
	public OrdinaryLabelNode getDefinition() {
		return (OrdinaryLabelNode) super.getDefinition();
	}

}
