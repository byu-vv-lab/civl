package edu.udel.cis.vsl.civl.model.IF.statement;

import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression.ClauseKind;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression.ContractKind;

public interface ContractStatement extends Statement {
	ContractClauseExpression getExpression();

	ContractKind getContractKind();

	ClauseKind getCluaseKind();

	boolean isCollectiveContract(); // if the contract is a instance of the
									// sub-clasee CollectiveContractStatement

	String libraryName();
}
