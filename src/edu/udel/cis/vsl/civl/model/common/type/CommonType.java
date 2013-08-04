package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

/**
 * Root of CIVL class hierarchy.
 * 
 * @author siegel
 * 
 */
public class CommonType implements CIVLType {

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

	@Override
	public boolean isProcessType() {
		return false;
	}

	@Override
	public boolean isScopeType() {
		return false;
	}

}
