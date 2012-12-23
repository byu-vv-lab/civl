package edu.udel.cis.vsl.civl.ast.type.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.type.IF.StandardBasicType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonBasicType extends CommonObjectType implements
		StandardBasicType {

	private static int classCode = CommonBasicType.class.hashCode();

	private BasicTypeKind basicTypeKind;

	public CommonBasicType(BasicTypeKind basicTypeKind) {
		super(TypeKind.BASIC);
		this.basicTypeKind = basicTypeKind;
	}

	@Override
	public BasicTypeKind getBasicTypeKind() {
		return basicTypeKind;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public boolean isVariablyModified() {
		return false;
	}

	public boolean isSigned() {
		switch (basicTypeKind) {
		case SIGNED_CHAR:
		case SHORT:
		case INT:
		case LONG:
		case LONG_LONG:
			return true;
		default:
			return false;
		}
	}

	public boolean isUnsigned() {
		switch (basicTypeKind) {
		case BOOL:
		case UNSIGNED_CHAR:
		case UNSIGNED_SHORT:
		case UNSIGNED:
		case UNSIGNED_LONG:
		case UNSIGNED_LONG_LONG:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean isInteger() {
		return isSigned() || isUnsigned();
	}

	@Override
	public boolean isFloating() {
		switch (basicTypeKind) {
		case FLOAT:
		case DOUBLE:
		case LONG_DOUBLE:
		case FLOAT_COMPLEX:
		case DOUBLE_COMPLEX:
		case LONG_DOUBLE_COMPLEX:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean isEnumeration() {
		return false;
	}

	@Override
	public boolean inRealDomain() {
		switch (basicTypeKind) {
		case FLOAT_COMPLEX:
		case DOUBLE_COMPLEX:
		case LONG_DOUBLE_COMPLEX:
			return false;
		default:
			return true;
		}
	}

	@Override
	public boolean inComplexDomain() {
		return !inRealDomain();
	}

	@Override
	public int hashCode() {
		return classCode + basicTypeKind.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof CommonBasicType) {
			CommonBasicType that = (CommonBasicType) object;

			return that.basicTypeKind == this.basicTypeKind;
		}
		return false;
	}

	@Override
	public boolean compatibleWith(Type type) {
		return equals(type);
	}

	@Override
	public String toString() {
		return basicTypeKind.toString();
	}

	@Override
	public void print(String prefix, PrintStream out, boolean abbrv) {
		out.print(this);
	}

	@Override
	public boolean isScalar() {
		return true;
	}

}
