package edu.udel.cis.vsl.civl.model.common.statement;

import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression.ClauseKind;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression.ContractKind;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.ContractStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class CollectiveContractStatement extends CommonStatement implements
		ContractStatement {
	protected ContractClauseExpression body;

	private String libraryName;

	public CollectiveContractStatement(CIVLSource civlSource, Scope hscope,
			Scope lscope, Location source, Expression guard,
			ContractClauseExpression contract, String libraryName) {
		super(civlSource, hscope, lscope, source, guard);
		this.body = contract;
		this.libraryName = libraryName;
	}

	@Override
	public ContractClauseExpression getExpression() {
		return body;
	}

	@Override
	public String libraryName() {
		return libraryName;
	}

	@Override
	protected void calculateConstantValueWork(SymbolicUniverse universe) {
		this.body.calculateConstantValue(universe);
	}

	@Override
	public ContractKind getContractKind() {
		return body.contractKind();
	}

	@Override
	public ClauseKind getCluaseKind() {
		return body.clauseKind();
	}

	@Override
	public boolean isCollectiveContract() {
		return body.isCollectiveClause();
	}

	@Override
	public Statement replaceWith(ConditionalExpression oldExpression,
			Expression newExpression) {
		ContractClauseExpression newBody = this.body.replaceWith(oldExpression,
				newExpression);

		return new CollectiveContractStatement(this.getSource(),
				this.statementScope, this.lowestScope, this.source(),
				this.guard(), newBody, libraryName);
	}

	@Override
	public Set<Variable> variableAddressedOf(Scope scope) {
		return this.body.variableAddressedOf(scope);
	}

	@Override
	public Set<Variable> variableAddressedOf() {
		return this.body.variableAddressedOf();
	}

	@Override
	public StatementKind statementKind() {
		return StatementKind.CONTRACT;
	}

	@Override
	public String toString() {
		return this.body.toString();
	}
}
