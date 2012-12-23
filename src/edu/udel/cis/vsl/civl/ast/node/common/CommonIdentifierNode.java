package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonIdentifierNode extends CommonASTNode implements
		IdentifierNode {

	private String name;

	private Entity entity;

	private List<DeclarationNode> declarations;

	private DeclarationNode definition;

	public CommonIdentifierNode(Source source, String name) {
		super(source);
		this.name = name;
	}

	@Override
	public void printBody(PrintStream out) {
		out.print("Identifier[" + name + "]");
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public Iterator<DeclarationNode> getDeclarations() {
		return declarations.iterator();
	}

	@Override
	public void addDeclaration(DeclarationNode declaration) {
		declarations.add(declaration);
	}

	@Override
	public DeclarationNode getDefinition() {
		return definition;
	}

	@Override
	public void setDefinition(DeclarationNode declaration) {
		this.definition = declaration;
	}

}
