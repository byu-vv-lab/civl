package edu.udel.cis.vsl.civl.model.common.statement;

import java.util.ArrayList;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;

public class StatementList implements Statement {
	/**
	 * The list of statements to be executed sequentially
	 */
	private ArrayList<Statement> statements;

	public StatementList() {
		statements = new ArrayList<Statement>();
	}

	public StatementList(ArrayList<Statement> stmts) {
		statements = stmts;
	}

	public StatementList(Statement statement) {
		statements = new ArrayList<Statement>();
		this.statements.add(statement);
	}

	public ArrayList<Statement> statements() {
		return this.statements;
	}

	public void add(Statement statement) {
		this.statements.add(statement);
	}

	@Override
	public CIVLSource getSource() {
		CIVLSource result = null;

		if (!statements.isEmpty()) {
			result = statements.get(0).getSource();
			if (result.getLocation() == "CIVL System object") {
				if (statements.size() > 1) {
					result = statements.get(1).getSource();
				}
			}
		}
		return result;
	}

	@Override
	public Location source() {
		if (!statements.isEmpty()) {
			return statements.get(0).source();
		}
		return null;
	}

	@Override
	public Location target() {
		if (!statements.isEmpty()) {
			return statements.get(statements.size() - 1).target();
		}
		return null;
	}

	@Override
	public Expression guard() {
		if (!statements.isEmpty()) {
			return statements.get(0).guard();
		}
		return null;
	}

	@Override
	public Model model() {
		return null;
	}

	@Override
	public void setSource(Location source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTarget(Location target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGuard(Expression guard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setModel(Model model) {
		// TODO Auto-generated method stub
	}

	@Override
	public Scope statementScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatementScope(Scope statementScope) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasDerefs() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void calculateDerefs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void purelyLocalAnalysisOfVariables(Scope funcScope) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPurelyLocal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void purelyLocalAnalysis() {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceWith(ConditionalExpression oldExpression,
			VariableExpression newExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public Statement replaceWith(ConditionalExpression oldExpression,
			Expression newExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String result = "";
		for (Statement s : statements) {
			result = result + s.toString() + ";";
		}
		return result;
	}
}
