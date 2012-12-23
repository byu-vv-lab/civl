package edu.udel.cis.vsl.civl.ast.node.IF.type;

import edu.udel.cis.vsl.civl.ast.type.IF.StandardBasicType.BasicTypeKind;

public interface BasicTypeNode extends TypeNode {

	BasicTypeKind getBasicTypeKind();

}
