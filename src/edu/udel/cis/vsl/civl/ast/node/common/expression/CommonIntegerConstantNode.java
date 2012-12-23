package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.civl.ast.value.IF.IntegerValue;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonIntegerConstantNode extends CommonConstantNode implements
		IntegerConstantNode {

	public CommonIntegerConstantNode(Source source, String representation,
			IntegerValue value) {
		super(source, representation, value.getType());
		setConstantValue(value);
	}

	@Override
	public IntegerValue getConstantValue() {
		return (IntegerValue) super.getConstantValue();
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(toString());
	}

	@Override
	public String toString() {
		return "IntegerConstant[value=" + getConstantValue().getIntegerValue()
				+ "]";
	}

}
