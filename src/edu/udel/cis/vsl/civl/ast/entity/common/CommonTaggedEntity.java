package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.TaggedEntity;

public class CommonTaggedEntity extends CommonEntity implements TaggedEntity {

	public CommonTaggedEntity(EntityKind kind, String name) {
		super(kind, name, LinkageKind.NONE);
	}

}
