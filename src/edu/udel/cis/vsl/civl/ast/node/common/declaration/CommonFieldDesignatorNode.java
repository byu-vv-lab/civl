package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDesignatorNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonFieldDesignatorNode extends CommonASTNode implements
		FieldDesignatorNode {

	public CommonFieldDesignatorNode(Source source, IdentifierNode field) {
		super(source, field);
	}

	@Override
	public IdentifierNode getField() {
		return (IdentifierNode) child(0);
	}

	@Override
	public void setField(IdentifierNode name) {
		setChild(0, name);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Field");
	}

}
