package edu.udel.cis.vsl.civl.ast.conversion;

import edu.udel.cis.vsl.civl.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.civl.ast.conversion.common.CommonConversionFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;

public class Conversions {

	public static ConversionFactory newConversionFactory(TypeFactory typeFactory) {
		return new CommonConversionFactory(typeFactory);
	}

}
