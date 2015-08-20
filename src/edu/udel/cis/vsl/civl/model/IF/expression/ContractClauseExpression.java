package edu.udel.cis.vsl.civl.model.IF.expression;

public interface ContractClauseExpression extends Expression {
	boolean isCollectiveClause();

	Expression getCollectiveGroup();

	Expression getBody();
}
