/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * @author zirkel
 *
 */
public class CommonPointerType implements CIVLPointerType {

	private CIVLType baseType;
	
	public CommonPointerType(CIVLType baseType) {
		this.baseType = baseType;
	}
	
	/* (non-Javadoc)
	 * @see edu.udel.cis.vsl.civl.model.IF.type.PointerType#baseType()
	 */
	@Override
	public CIVLType baseType() {
		return baseType;
	}
	
	@Override
	public String toString() {
		return baseType + "*";
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
		return true;
	}

}
