package edu.udel.cis.vsl.civl.ast.type.common;

import edu.udel.cis.vsl.civl.ast.type.IF.IntegerType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public abstract class CommonIntegerType extends CommonObjectType implements
		IntegerType {

	public CommonIntegerType(TypeKind kind) {
		super(kind);
	}

	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isFloating() {
		return false;
	}

	@Override
	public boolean inRealDomain() {
		return true;
	}

	@Override
	public boolean inComplexDomain() {
		return false;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public boolean compatibleWith(Type type) {
		return equals(type);
	}

	@Override
	public boolean isVariablyModified() {
		return false;
	}

	@Override
	public boolean isScalar() {
		return true;
	}

}
