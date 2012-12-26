package edu.udel.cis.vsl.civl.model.type;

/**
 * The type for an array of T.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class ArrayType extends Type {

	private Type baseType;

	/**
	 * The type for an array of T.
	 * 
	 * @param baseType
	 *            The type of the elements of this array.
	 */
	public ArrayType(Type baseType) {
		this.baseType = baseType;
	}

	/**
	 * @return The type of elements in this array.
	 */
	public Type baseType() {
		return baseType;
	}

	/**
	 * @param baseType
	 *            The type of elements in this array.
	 */
	public void setBaseType(Type baseType) {
		this.baseType = baseType;
	}
	
	@Override
	public String toString() {
		return baseType + "[]";
	}

}
