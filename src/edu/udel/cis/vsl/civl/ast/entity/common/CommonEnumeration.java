package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumeration;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;

public class CommonEnumeration extends CommonTaggedEntity implements
		Enumeration {

	public CommonEnumeration(EnumerationType type) {
		super(EntityKind.ENUMERATION, type.getTag());
		setType(type);
	}

	@Override
	public EnumerationType getType() {
		return (EnumerationType) super.getType();
	}

}
