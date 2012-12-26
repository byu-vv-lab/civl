package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.SelfNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonSelfNode extends CommonConstantNode implements SelfNode {

	public CommonSelfNode(Source source, ObjectType processType) {
		super(source, "\\self", processType);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("\\self");
	}

}
