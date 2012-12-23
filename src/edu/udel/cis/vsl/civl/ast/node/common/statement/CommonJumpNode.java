package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.JumpNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonJumpNode extends CommonASTNode implements JumpNode {

	private JumpKind jumpKind;

	public CommonJumpNode(Source source, JumpKind jumpKind) {
		super(source);
		this.jumpKind = jumpKind;
	}

	@Override
	public JumpKind getKind() {
		return jumpKind;
	}

	@Override
	protected void printBody(PrintStream out) {
		switch (jumpKind) {
		case GOTO:
			out.print("GotoStatement");
			break;
		case CONTINUE:
			out.print("ContinueStatement");
			break;
		case BREAK:
			out.print("BreakStatement");
			break;
		case RETURN:
			out.print("ReturnStatement");
			break;
		default:
			throw new RuntimeException("impossible");
		}
	}

}
