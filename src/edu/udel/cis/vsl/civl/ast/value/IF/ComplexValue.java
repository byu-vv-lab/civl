package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.FloatingType;

public interface ComplexValue extends Value {

	@Override
	FloatingType getType();

	RealFloatingValue getRealPart();

	RealFloatingValue getImaginaryPart();

}
