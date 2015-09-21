package edu.udel.cis.vsl.civl.model.IF.expression;

import java.util.List;

public interface ContractClauseExpression extends Expression {
	public enum ContractKind {
		REQUIRES, ENSURES
	};

	boolean isCollectiveClause();

	Expression getCollectiveGroup();

	Expression getBody();

	ContractKind contractKind();

	List<SystemFunctionCallExpression> getContractCalls();

	@Override
	ContractClauseExpression replaceWith(ConditionalExpression oldExpr,
			Expression newExpr);
}
