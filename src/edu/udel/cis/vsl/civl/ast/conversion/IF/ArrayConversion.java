package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

public interface ArrayConversion extends Conversion {

	@Override
	ArrayType getOldType();

	@Override
	PointerType getNewType();

}
