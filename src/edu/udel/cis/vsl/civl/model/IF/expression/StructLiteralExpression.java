package edu.udel.cis.vsl.civl.model.IF.expression;

public interface StructLiteralExpression extends LiteralExpression {
	Expression[] fields();

	void setFields(Expression[] fields);
}
