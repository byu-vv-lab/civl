/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;

/**
 * @author zirkel
 * 
 */
public class CommonHeapType implements CIVLHeapType {

	/**
	 * A heap type.
	 */
	public CommonHeapType() {
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

	@Override
	public String toString() {
		return "$heap";
	}

}
