package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.value.IF.IntegerValue;

public interface IntegerConstantNode extends ConstantNode {

	@Override
	IntegerValue getConstantValue();

}
