package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.value.IF.StringValue;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonStringLiteralNode extends CommonConstantNode implements
		StringLiteralNode {

	public CommonStringLiteralNode(Source source, String representation,
			StringValue stringValue) {
		super(source, representation, stringValue.getType());
		setConstantValue(stringValue);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("StringLiteral[" + getConstantValue() + "]");
	}

	@Override
	public StringValue getConstantValue() {
		return (StringValue) super.getConstantValue();
	}
}
