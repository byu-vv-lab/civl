package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;

public interface CompatibleStructureOrUnionConversion extends Conversion {

	@Override
	StructureOrUnionType getOldType();

	@Override
	StructureOrUnionType getNewType();

}
