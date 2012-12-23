package edu.udel.cis.vsl.civl.ast.conversion.common;

import edu.udel.cis.vsl.civl.ast.conversion.IF.CompatibleStructureOrUnionConversion;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;

public class CommonCompatibleStructureOrUnionConversion extends
		CommonConversion implements CompatibleStructureOrUnionConversion {

	public CommonCompatibleStructureOrUnionConversion(
			StructureOrUnionType type1, StructureOrUnionType type2) {
		super(type1, type2);
	}

	@Override
	public StructureOrUnionType getOldType() {
		return (StructureOrUnionType) super.getOldType();
	}

	@Override
	public StructureOrUnionType getNewType() {
		return (StructureOrUnionType) super.getNewType();
	}

}
