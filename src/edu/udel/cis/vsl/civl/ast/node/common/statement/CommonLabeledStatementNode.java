package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonLabeledStatementNode extends CommonASTNode implements
		LabeledStatementNode {

	public CommonLabeledStatementNode(Source source, LabelNode label,
			StatementNode statement) {
		super(source, label, statement);
	}

	@Override
	public LabelNode getLabel() {
		return (LabelNode) child(0);
	}

	@Override
	public StatementNode getStatement() {
		return (StatementNode) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("LabeledStatement");
	}

}
