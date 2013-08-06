package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * The type for an array of T.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonArrayType extends CommonType implements CIVLArrayType {

	private CIVLType elementType;

	/**
	 * The type for an array of T.
	 * 
	 * @param elementType
	 *            The type of the elements of this array.
	 */
	public CommonArrayType(CIVLType elementType) {
		this.elementType = elementType;
	}

	/**
	 * @return The type of elements in this array.
	 */
	public CIVLType elementType() {
		return elementType;
	}

	@Override
	public String toString() {
		return elementType + "[]";
	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public boolean hasState() {
		return elementType.hasState();
	}

}
