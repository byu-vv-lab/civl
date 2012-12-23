package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.civl.ast.value.IF.CharacterValue;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCharacterConstantNode extends CommonConstantNode implements
		CharacterConstantNode {

	public CommonCharacterConstantNode(Source source, String representation,
			CharacterValue value) {
		super(source, representation, value.getType());
		setConstantValue(value);
	}

	@Override
	public String toString() {
		return "CharacterConstant[" + getConstantValue() + "]";
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(this);
	}

	@Override
	public CharacterValue getConstantValue() {
		return (CharacterValue) super.getConstantValue();
	}

}
