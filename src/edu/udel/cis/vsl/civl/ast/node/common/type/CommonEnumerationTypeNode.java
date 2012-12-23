package edu.udel.cis.vsl.civl.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumeration;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonEnumerationTypeNode extends CommonTypeNode implements
		EnumerationTypeNode {

	boolean isDefinition = false;

	Enumeration entity = null;

	public CommonEnumerationTypeNode(Source source, IdentifierNode tag,
			SequenceNode<EnumeratorDeclarationNode> enumerators) {
		super(source, TypeNodeKind.ENUMERATION, tag, enumerators);
	}

	@Override
	public IdentifierNode getIdentifier() {
		return (IdentifierNode) child(0);
	}

	@Override
	public void setIdentifier(IdentifierNode identifier) {
		setChild(0, identifier);
	}

	@Override
	public boolean isDefinition() {
		return isDefinition;
	}

	@Override
	public void setIsDefinition(boolean value) {
		isDefinition = value;
	}

	@Override
	public IdentifierNode getTag() {
		return (IdentifierNode) child(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<EnumeratorDeclarationNode> enumerators() {
		return (SequenceNode<EnumeratorDeclarationNode>) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Enumeration[" + qualifierString() + "]");
	}

	@Override
	public String getName() {
		IdentifierNode tag = getTag();

		if (tag == null)
			return null;
		else
			return tag.name();
	}

	@Override
	public Enumeration getEntity() {
		return entity;
	}

	public void setEntity(Enumeration entity) {
		this.entity = entity;
	}

}
