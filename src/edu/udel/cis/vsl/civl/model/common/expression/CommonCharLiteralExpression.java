package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.CharLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

public class CommonCharLiteralExpression extends CommonExpression implements
		CharLiteralExpression {

	private char value;

	/**
	 * Create a new char literal expression.
	 * 
	 * @param source
	 */
	public CommonCharLiteralExpression(CIVLSource source, CIVLType type,
			char value) {
		super(source);
		this.value = value;
		this.expressionType = type;
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
