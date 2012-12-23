package edu.udel.cis.vsl.civl.ast.node.IF.statement;

public interface JumpNode extends StatementNode {

	public static enum JumpKind {
		GOTO, CONTINUE, BREAK, RETURN
	};

	JumpKind getKind();

}
