package edu.udel.cis.vsl.civl.ast.conversion.common;

import edu.udel.cis.vsl.civl.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonConversion implements Conversion {

	private final static int classCode = CommonConversion.class.hashCode();

	private Type oldType;

	private Type newType;

	public CommonConversion(Type oldType, Type newType) {
		assert oldType != null;
		assert newType != null;
		this.oldType = oldType;
		this.newType = newType;
	}

	@Override
	public Type getOldType() {
		return oldType;
	}

	@Override
	public Type getNewType() {
		return newType;
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof CommonConversion
				&& oldType.equals(((CommonConversion) object).getOldType())
				&& newType.equals(((CommonConversion) object).getNewType());

	}

	@Override
	public int hashCode() {
		return classCode + oldType.hashCode() + newType.hashCode();
	}

}
