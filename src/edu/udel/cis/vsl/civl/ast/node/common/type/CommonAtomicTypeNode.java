package edu.udel.cis.vsl.civl.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.type.AtomicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonAtomicTypeNode extends CommonTypeNode implements
		AtomicTypeNode {

	public CommonAtomicTypeNode(Source source, TypeNode baseType) {
		super(source, TypeNodeKind.ATOMIC, baseType);
	}

	@Override
	public TypeNode getBaseType() {
		return (TypeNode) child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Atomic[" + qualifierString() + "]");
	}

}
