package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory.Answer;

public interface Value {

	Type getType();

	/**
	 * A scalar value is a value of scalar type or a value of union type for
	 * which the single union member is scalar.
	 * 
	 * @return
	 */
	boolean isScalar();

	/**
	 * Can only be asked of scalar values.
	 * 
	 * @return
	 */
	Answer isZero();

}
