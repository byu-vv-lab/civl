package edu.udel.cis.vsl.civl.ast.type;

import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.type.common.CommonTypeFactory;

public class Types {

	public static TypeFactory newTypeFactory() {
		return new CommonTypeFactory();
	}

}
