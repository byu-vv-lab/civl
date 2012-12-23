package edu.udel.cis.vsl.civl.ast.value;

import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.civl.ast.value.common.CommonValueFactory;

public class Values {

	public static ValueFactory newValueFactory(TypeFactory typeFactory) {
		return new CommonValueFactory(typeFactory);
	}

}
