package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * The type for an array of T.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonArrayType implements CIVLArrayType {

	private CIVLType baseType;

	/**
	 * The type for an array of T.
	 * 
	 * @param baseType
	 *            The type of the elements of this array.
	 */
	public CommonArrayType(CIVLType baseType) {
		this.baseType = baseType;
	}

	/**
	 * @return The type of elements in this array.
	 */
	public CIVLType baseType() {
		return baseType;
	}

	/**
	 * @param baseType
	 *            The type of elements in this array.
	 */
	public void setBaseType(CIVLType baseType) {
		this.baseType = baseType;
	}

	@Override
	public String toString() {
		return baseType + "[]";
	}

	@Override
	public boolean isNumericType() {
		return false;
	}

	@Override
	public boolean isIntegerType() {
		return false;
	}

	@Override
	public boolean isRealType() {
		return false;
	}

	@Override
	public boolean isPointerType() {
		return false;
	}

}
