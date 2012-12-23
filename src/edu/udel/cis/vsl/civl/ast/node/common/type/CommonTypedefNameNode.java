package edu.udel.cis.vsl.civl.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypedefNameNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonTypedefNameNode extends CommonTypeNode implements
		TypedefNameNode {

	public CommonTypedefNameNode(Source source, IdentifierNode name) {
		super(source, TypeNodeKind.TYPEDEF_NAME, name);
	}

	@Override
	public IdentifierNode getName() {
		return (IdentifierNode) child(0);
	}

	@Override
	public void setName(IdentifierNode name) {
		setChild(0, name);
	}

	@Override
	protected void printBody(PrintStream out) {
		String qualifiers = qualifierString();

		out.print("TypedefName");
		if (!qualifiers.isEmpty())
			out.print("[" + qualifiers + "]");
	}

}
