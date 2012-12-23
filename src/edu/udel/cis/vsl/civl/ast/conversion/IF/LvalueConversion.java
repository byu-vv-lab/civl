package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.UnqualifiedObjectType;

public interface LvalueConversion extends Conversion {

	@Override
	ObjectType getOldType();

	@Override
	UnqualifiedObjectType getNewType();

}
