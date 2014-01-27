package edu.udel.cis.vsl.civl.model.IF.expression;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;

public interface StructLiteralExpression extends LiteralExpression {
	Expression[] fields();

	void setFields(Expression[] fields);
	
	CIVLStructType structType();
}
