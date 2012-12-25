package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonSequenceNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonChooseStatementNode extends
		CommonSequenceNode<StatementNode> implements ChooseStatementNode {

	private LabeledStatementNode defaultCase = null;

	public CommonChooseStatementNode(Source source,
			List<StatementNode> childList) {
		super(source, "Choose", childList);
	}

	@Override
	public LabeledStatementNode getDefaultCase() {
		return defaultCase;
	}

	@Override
	public void setDefaultCase(LabeledStatementNode statement) {
		this.defaultCase = statement;
	}

}
