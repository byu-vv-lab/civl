package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.StructureOrUnion;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;

public class CommonStructureOrUnion extends CommonTaggedEntity implements
		StructureOrUnion {

	public CommonStructureOrUnion(StructureOrUnionType type) {
		super(EntityKind.STRUCTURE_OR_UNION, type.getTag());
		setType(type);
	}

	@Override
	public StructureOrUnionType getType() {
		return (StructureOrUnionType) super.getType();
	}

}
