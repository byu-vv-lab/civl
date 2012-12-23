package edu.udel.cis.vsl.civl.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonVoidTypeNode extends CommonTypeNode {

	public CommonVoidTypeNode(Source source) {
		super(source, TypeNodeKind.VOID);
	}

	@Override
	protected void printBody(PrintStream out) {
		String qualifiers = qualifierString();

		out.print("VoidType");
		if (!qualifiers.isEmpty())
			out.print("[" + qualifiers + "]");
	}

}
