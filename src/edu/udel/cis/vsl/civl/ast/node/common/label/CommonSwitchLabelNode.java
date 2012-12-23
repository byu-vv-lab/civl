package edu.udel.cis.vsl.civl.ast.node.common.label;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonSwitchLabelNode extends CommonASTNode implements
		SwitchLabelNode {

	private boolean isDefault;

	private StatementNode statement = null;

	public CommonSwitchLabelNode(Source source, ExpressionNode caseExpression) {
		super(source, caseExpression);
		isDefault = false;
	}

	public CommonSwitchLabelNode(Source source) {
		super(source);
		isDefault = true;
	}

	@Override
	public boolean isDefault() {
		return isDefault;
	}

	@Override
	public ExpressionNode getExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		if (isDefault)
			out.print("Default");
		else
			out.print("Case");
	}

	@Override
	public StatementNode getStatement() {
		return statement;
	}

	@Override
	public void setStatement(StatementNode statement) {
		this.statement = statement;
	}

}
