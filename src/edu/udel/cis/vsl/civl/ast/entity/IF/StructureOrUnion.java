package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;

public interface StructureOrUnion extends TaggedEntity {

	@Override
	StructureOrUnionType getType();

}
