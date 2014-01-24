package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.ArrayLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

public class CommonArrayLiteralExpression extends CommonExpression implements
		ArrayLiteralExpression {

	private Expression[] elements;

	public CommonArrayLiteralExpression(CIVLSource source, CIVLType type,
			Expression[] elements) {
		super(source);
		this.elements = elements;
		this.setExpressionType(type);
	}

	@Override
	public ExpressionKind expressionKind() {
		// TODO Auto-generated method stub
		return ExpressionKind.ARRAY_LITERAL;
	};

	@Override
	public String toString() {
		String result = "{";

		if (elements != null) {
			for (Expression element : elements) {
				result += element.toString() + ", ";
			}
			result = result.substring(0, result.length() - 3);
			result += " ";
		}
		result += "}";
		return result;
	}

	@Override
	public Expression[] elements() {
		return this.elements;
	}

	@Override
	public void setElements(Expression[] elements) {
		this.elements = elements;
	}

}
