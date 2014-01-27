package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.LiteralExpression;

public class CommonUnionLiteralExpression extends CommonExpression implements
		LiteralExpression {
	

	public CommonUnionLiteralExpression(CIVLSource source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.UNION_LITERAL;
	}

}
