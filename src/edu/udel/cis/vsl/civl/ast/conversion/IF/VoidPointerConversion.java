package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

/**
 * One type is a pointer to void, the other a pointer to an object type. Can
 * convert in either direction.
 * 
 * one operand is a pointer to an object type, and the other is a pointer to a
 * qualified or unqualified version of void, and the type pointed to by the left
 * has all the qualifiers of the type pointed to by the right;
 * 
 * 
 * @author siegel
 * 
 */
public interface VoidPointerConversion extends Conversion {

	@Override
	PointerType getOldType();

	@Override
	PointerType getNewType();

}
