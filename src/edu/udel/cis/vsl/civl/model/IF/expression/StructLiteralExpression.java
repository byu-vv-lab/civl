package edu.udel.cis.vsl.civl.model.IF.expression;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructOrUnionType;

public interface StructLiteralExpression extends LiteralExpression {
	Expression[] fields();

	void setFields(Expression[] fields);
	
	CIVLStructOrUnionType structType();
}
