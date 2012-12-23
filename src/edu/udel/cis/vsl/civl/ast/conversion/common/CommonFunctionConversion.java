package edu.udel.cis.vsl.civl.ast.conversion.common;

import edu.udel.cis.vsl.civl.ast.conversion.IF.FunctionConversion;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

public class CommonFunctionConversion extends CommonConversion implements
		FunctionConversion {

	public CommonFunctionConversion(FunctionType oldType, PointerType newType) {
		super(oldType, newType);
	}

	@Override
	public FunctionType getOldType() {
		return (FunctionType) super.getOldType();
	}

	@Override
	public PointerType getNewType() {
		return (PointerType) super.getNewType();
	}

	@Override
	public String toString() {
		return "FunctionConversion[" + getOldType().getId() + ", "
				+ getNewType().getId() + "]";
	}

}
