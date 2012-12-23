package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonSequenceNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCompoundStatementNode extends
		CommonSequenceNode<BlockItemNode> implements CompoundStatementNode {

	public CommonCompoundStatementNode(Source source,
			List<BlockItemNode> childList) {
		super(source, "CompoundStatement", childList);
	}

}
