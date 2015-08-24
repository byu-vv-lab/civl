package edu.udel.cis.vsl.civl.model.IF.expression;

public interface ContractClauseExpression extends Expression {
	public enum ContractKind {
		REQUIRES, ENSURES
	};

	public enum ClauseKind {
		MESSAGE_BUFFER, EXPRESSION;
	};

	boolean isCollectiveClause();

	Expression getCollectiveGroup();

	Expression getBody();

	ContractKind contractKind();

	ClauseKind clauseKind();

	@Override
	ContractClauseExpression replaceWith(ConditionalExpression oldExpr,
			Expression newExpr);
}
