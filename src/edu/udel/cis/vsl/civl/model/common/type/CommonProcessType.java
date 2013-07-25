package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.ProcessType;

/**
 * The type of a process.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonProcessType implements ProcessType {

	/**
	 * The type of a process.
	 */
	public CommonProcessType() {
	}

	@Override
	public String toString() {
		return "process";
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
