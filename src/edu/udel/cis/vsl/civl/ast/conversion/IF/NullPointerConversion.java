package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

/**
 * Conversion of a null pointer constant to any pointer type.
 * 
 * "An integer constant expression with the value 0, or such an expression cast
 * to type void *, is called a null pointer constant. If a null pointer constant
 * is converted to a pointer type, the resulting pointer, called a null pointer,
 * is guaranteed to compare unequal to a pointer to any object or function."
 * 
 * "Conversion of a null pointer to another pointer type yields a null pointer
 * of that type. Any two null pointers shall compare equal."
 * 
 * @author siegel
 * 
 */
public interface NullPointerConversion extends Conversion {

	/**
	 * An integer type or void*.
	 */
	@Override
	ObjectType getOldType();

	/**
	 * A pointer type.
	 */
	@Override
	PointerType getNewType();

}
