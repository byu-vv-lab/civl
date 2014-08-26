package edu.udel.cis.vsl.civl.model.IF.expression;

public interface RecDomainLiteralExpression extends LiteralExpression {
	Expression rangeAt(int index);
	
	int dimension();
}
