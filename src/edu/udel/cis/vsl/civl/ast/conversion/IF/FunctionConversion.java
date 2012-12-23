package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

public interface FunctionConversion extends Conversion {

	@Override
	FunctionType getOldType();

	@Override
	PointerType getNewType();

}
