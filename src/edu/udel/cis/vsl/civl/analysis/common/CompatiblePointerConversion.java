package edu.udel.cis.vsl.civl.analysis.common;

import edu.udel.cis.vsl.civl.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

public interface CompatiblePointerConversion extends Conversion {

	@Override
	PointerType getOldType();

	@Override
	PointerType getNewType();
}
