package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.token.IF.StringLiteral;

public interface StringValue extends Value {

	StringLiteral getLiteral();

	@Override
	ArrayType getType();

}
