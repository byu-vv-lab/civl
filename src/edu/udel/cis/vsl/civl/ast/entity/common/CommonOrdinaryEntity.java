package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonOrdinaryEntity extends CommonEntity implements
		OrdinaryEntity {

	public CommonOrdinaryEntity(EntityKind kind, String name,
			LinkageKind linkage, Type type) {
		super(kind, name, linkage);
		setType(type);
	}

}
