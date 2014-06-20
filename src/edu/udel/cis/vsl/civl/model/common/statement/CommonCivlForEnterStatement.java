package edu.udel.cis.vsl.civl.model.common.statement;

import java.util.List;
import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CivlForEnterStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

public class CommonCivlForEnterStatement extends CommonStatement implements
		CivlForEnterStatement {

	@SuppressWarnings("unused")
	private Expression domain;

	@SuppressWarnings("unused")
	private List<Variable> loopVariables;

	public CommonCivlForEnterStatement(Expression dom, List<Variable> variables) {
		this.domain = dom;
		this.setLoopVariables(variables);
	}

	@Override
	public Statement replaceWith(ConditionalExpression oldExpression,
			Expression newExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variable> variableAddressedOf(Scope scope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variable> variableAddressedOf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatementKind statementKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression domain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variable> loopVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLoopVariables(List<Variable> loopVariables) {
		this.loopVariables = loopVariables;
	}

}
