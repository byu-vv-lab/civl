package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.DeclarationListNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonSequenceNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonDeclarationListNode extends
		CommonSequenceNode<VariableDeclarationNode> implements
		DeclarationListNode {

	public CommonDeclarationListNode(Source source,
			List<VariableDeclarationNode> childList) {
		super(source, "DeclarationList", childList);
	}

}
