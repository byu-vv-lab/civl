package edu.udel.cis.vsl.civl.ast.value.common;

import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

public abstract class CommonValue implements Value {

	private Type type;

	public CommonValue(Type type) {
		assert type != null;
		this.type = type;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean isScalar() {
		return type.isScalar();
	}

}
