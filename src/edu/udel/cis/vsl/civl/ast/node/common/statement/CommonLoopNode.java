package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonLoopNode extends CommonASTNode implements LoopNode {

	private LoopKind loopKind;

	public CommonLoopNode(Source source, LoopKind loopKind,
			ExpressionNode condition, StatementNode body) {
		super(source, condition, body);
		this.loopKind = loopKind;
	}

	@Override
	public ExpressionNode getCondition() {
		return (ExpressionNode) child(0);
	}

	@Override
	public StatementNode getBody() {
		return (StatementNode) child(1);
	}

	@Override
	public LoopKind getKind() {
		return loopKind;
	}

	@Override
	protected void printBody(PrintStream out) {
		switch (loopKind) {
		case WHILE:
			out.print("WhileLoopStatement");
			break;
		case DO_WHILE:
			out.print("DoWhileLoopStatement");
			break;
		case FOR:
			out.print("ForLoopStatement");
			break;
		default:
			throw new RuntimeException("Unreachable");
		}
	}

}
