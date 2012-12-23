package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.Type;

/**
 * A conversion is an implicit operation that may change a value and/or the type
 * of the value. Examples include the usual arithmetic conversions, lvalue
 * conversion, and array and function conversions (which change the type from
 * array of T to pointer to T, and function returning T to pointer to function
 * returning T, respectively).
 * 
 * @author siegel
 * 
 */
public interface Conversion {

	/**
	 * Returns the type of the entity before applying this conversion.
	 * 
	 * @return the pre-conversion type
	 */
	Type getOldType();

	/**
	 * Returns the type of the entity after applying this conversion.
	 * 
	 * @return the post-conversion type
	 */
	Type getNewType();

}
