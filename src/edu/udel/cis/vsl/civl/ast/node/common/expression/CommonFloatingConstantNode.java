package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.civl.ast.value.IF.RealFloatingValue;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonFloatingConstantNode extends CommonConstantNode implements
		FloatingConstantNode {

	private String wholePart;

	private String fractionPart;

	private String exponent;

	public CommonFloatingConstantNode(Source source, String representation,
			String wholePart, String fractionPart, String exponent,
			RealFloatingValue value) {
		super(source, representation, value.getType());
		this.wholePart = wholePart;
		this.fractionPart = fractionPart;
		this.exponent = exponent;
		setConstantValue(value);
	}

	@Override
	public RealFloatingValue getConstantValue() {
		return (RealFloatingValue) super.getConstantValue();
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(toString());
	}

	@Override
	public String toString() {
		return "FloatingConstant[radix=" + getConstantValue().getRadix()
				+ ", significand=" + wholePart + "." + fractionPart
				+ ", exponent=" + exponent + ", doubleValue="
				+ getConstantValue().getDoubleValue() + "]";
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonFloatingConstantNode) {
	// return getDoubleValue() == ((CommonFloatingConstantNode) expression)
	// .getDoubleValue();
	// }
	// return false;
	// }

	@Override
	public String wholePart() {
		return wholePart;
	}

	@Override
	public String fractionPart() {
		return fractionPart;
	}

	@Override
	public String exponent() {
		return exponent;
	}
}
