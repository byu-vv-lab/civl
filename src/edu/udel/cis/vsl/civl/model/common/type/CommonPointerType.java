/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * 
 * @author zirkel
 * 
 */
public class CommonPointerType extends CommonType implements CIVLPointerType {

	private CIVLType baseType;

	private SymbolicType symbolicPointerType;

	public CommonPointerType(CIVLType baseType, SymbolicType pointerType) {
		this.baseType = baseType;
		this.symbolicPointerType = pointerType;
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

	@Override
	public SymbolicType getSymbolicType() {
		return symbolicPointerType;
	}

	@Override
	public boolean hasState() {
		return false;
	}

}
