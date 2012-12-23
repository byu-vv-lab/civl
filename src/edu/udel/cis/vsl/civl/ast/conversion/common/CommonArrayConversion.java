package edu.udel.cis.vsl.civl.ast.conversion.common;

import edu.udel.cis.vsl.civl.ast.conversion.IF.ArrayConversion;
import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonArrayConversion extends CommonConversion implements
		ArrayConversion {

	public CommonArrayConversion(Type oldType, PointerType newType) {
		super(oldType, newType);
	}

	@Override
	public ArrayType getOldType() {
		return (ArrayType) super.getOldType();
	}

	@Override
	public PointerType getNewType() {
		return (PointerType) super.getNewType();
	}

	@Override
	public String toString() {
		return "ArrayConversion[" + getOldType().getId() + ", "
				+ getNewType().getId() + "]";
	}
}
