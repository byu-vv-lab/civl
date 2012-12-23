package edu.udel.cis.vsl.civl.ast.node.common.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.GotoNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonGotoNode extends CommonJumpNode implements GotoNode {

	public CommonGotoNode(Source source, IdentifierNode label) {
		super(source, JumpKind.GOTO);
		addChild(label);
	}

	@Override
	public IdentifierNode getLabel() {
		return (IdentifierNode) child(0);
	}

}
