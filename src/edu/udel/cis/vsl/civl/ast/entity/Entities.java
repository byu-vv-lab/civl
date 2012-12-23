package edu.udel.cis.vsl.civl.ast.entity;

import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.entity.common.CommonEntityFactory;

public class Entities {

	public static EntityFactory newEntityFactory() {
		return new CommonEntityFactory();
	}

}
