package edu.udel.cis.vsl.civl.ast.type.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.type.IF.IntegerType;

public class ArithmeticConversionType extends CommonIntegerType {

	public final static int classCode = ArithmeticConversionType.class
			.hashCode();

	private IntegerType type1;

	private IntegerType type2;

	public ArithmeticConversionType(IntegerType type1, IntegerType type2) {
		super(TypeKind.OTHER_INTEGER);

		this.type1 = type1;
		this.type2 = type2;
	}

	@Override
	public boolean isEnumeration() {
		return false;
	}

	@Override
	public String toString() {
		return "ArithmeticConversionType[" + type1.getId() + ", "
				+ type2.getId() + "]";
	}

	@Override
	public void print(String prefix, PrintStream out, boolean abbrv) {
		out.print(this);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof ArithmeticConversionType) {
			ArithmeticConversionType that = (ArithmeticConversionType) object;

			return type1.equals(that.type1) && type2.equals(that.type2)
					|| type2.equals(that.type1) && type1.equals(that.type2);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return classCode + type1.hashCode() + type2.hashCode();
	}

}
