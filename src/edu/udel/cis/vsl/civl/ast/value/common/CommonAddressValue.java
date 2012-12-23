package edu.udel.cis.vsl.civl.ast.value.common;

import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.value.IF.AddressValue;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory.Answer;

public abstract class CommonAddressValue extends CommonValue implements
		AddressValue {

	CommonAddressValue(PointerType type) {
		super(type);
	}

	@Override
	public PointerType getType() {
		return (PointerType) super.getType();
	}

	@Override
	public Answer isZero() {
		return Answer.NO;
	}

}
