package edu.udel.cis.vsl.civl.ast.value.IF;

import java.math.BigInteger;

import edu.udel.cis.vsl.civl.ast.type.IF.IntegerType;

public interface IntegerValue extends Value {

	@Override
	IntegerType getType();

	BigInteger getIntegerValue();

}
