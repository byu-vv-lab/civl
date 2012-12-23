package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonTypedefDeclarationNode extends CommonDeclarationNode
		implements TypedefDeclarationNode {

	public CommonTypedefDeclarationNode(Source source,
			IdentifierNode identifier, TypeNode type) {
		super(source, identifier, type);
	}

	@Override
	public Typedef getEntity() {
		return (Typedef) super.getEntity();
	}

	@Override
	public TypeNode getTypeNode() {
		return (TypeNode) child(1);
	}

	@Override
	public void setTypeNode(TypeNode type) {
		setChild(1, type);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Typedef");
	}

}
