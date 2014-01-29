package edu.udel.cis.vsl.civl.model.IF.expression;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;

public interface ArrayLiteralExpression extends LiteralExpression {

	Expression[] elements();

	void setElements(Expression[] elements);

	CIVLArrayType arrayType();
	
	CIVLType elementType();
}
