package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.value.IF.RealFloatingValue;

public interface FloatingConstantNode extends ConstantNode {

	String wholePart();

	String fractionPart();

	String exponent();

	@Override
	RealFloatingValue getConstantValue();

}
