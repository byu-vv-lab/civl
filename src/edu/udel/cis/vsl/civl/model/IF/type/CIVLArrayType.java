package edu.udel.cis.vsl.civl.model.IF.type;

/**
 * The type for an array of T.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface CIVLArrayType extends CIVLType {

	/**
	 * @return The type of elements in this array.
	 */
	public CIVLType baseType();

	/**
	 * @param baseType
	 *            The type of elements in this array.
	 */
	public void setBaseType(CIVLType baseType);
	
}
