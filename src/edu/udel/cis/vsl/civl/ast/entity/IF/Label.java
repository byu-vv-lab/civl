package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;

public interface Label extends Entity {

	OrdinaryLabelNode getDefinition();
}
