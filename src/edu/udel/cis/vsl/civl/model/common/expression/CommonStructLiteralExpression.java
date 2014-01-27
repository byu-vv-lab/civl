package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.StructLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

public class CommonStructLiteralExpression extends CommonExpression implements
		StructLiteralExpression {

	private Expression[] fields;

	public CommonStructLiteralExpression(CIVLSource source, CIVLType type,
			Expression[] fields) {
		super(source);
		this.fields = fields;
		this.setExpressionType(type);
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.STRUCT_LITERAL;
	}

	@Override
	public Expression[] fields() {
		return this.fields;
	}

	@Override
	public void setFields(Expression[] fields) {
		this.fields = fields;
	}

	@Override
	public CIVLStructType structType() {
		assert this.expressionType instanceof CIVLStructType;
		return (CIVLStructType) this.expressionType;
	}

}
