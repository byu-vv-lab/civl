/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * 
 * @author zirkel
 * 
 */
public class CommonPointerType extends CommonType implements CIVLPointerType {

	private CIVLType baseType;

	public CommonPointerType(CIVLType baseType) {
		this.baseType = baseType;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	public boolean isPointerType() {
		return true;
	}

	@Override
	public Scope getRegion() {
		// TODO Auto-generated method stub
		return null;
	}

}
