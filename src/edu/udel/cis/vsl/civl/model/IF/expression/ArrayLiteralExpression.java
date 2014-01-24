package edu.udel.cis.vsl.civl.model.IF.expression;

public interface ArrayLiteralExpression extends LiteralExpression {
	
	Expression[] elements();

	void setElements(Expression[] elements);
}
