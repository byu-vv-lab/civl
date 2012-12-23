package edu.udel.cis.vsl.civl.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonNullStatementNode extends CommonASTNode implements
		StatementNode {

	public CommonNullStatementNode(Source source) {
		super(source);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("NullStatement");
	}

}
