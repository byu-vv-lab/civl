/**
 * 
 */
package edu.udel.cis.vsl.civl.model.IF.type;


/**
 * Type of a pointer.
 * 
 * @author zirkel
 */
public interface CIVLPointerType extends CIVLType {

	/** Returns the type of element pointed to. Result could be "void", as in C */
	CIVLType baseType();
}
