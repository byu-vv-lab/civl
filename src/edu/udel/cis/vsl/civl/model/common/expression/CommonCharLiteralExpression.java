package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.CharLiteralExpression;

public class CommonCharLiteralExpression extends CommonExpression implements
		CharLiteralExpression {

	private char value;

	/**
	 * Create a new char literal expression.
	 * 
	 * @param source
	 */
	public CommonCharLiteralExpression(CIVLSource source, char value) {
		super(source);
		this.value = value;
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.CHAR_LITERAL;
	}

	@Override
	public char value() {
		return this.value;
	}

	@Override
	public void setValue(char value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
