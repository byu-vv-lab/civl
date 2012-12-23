package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonFieldDeclarationNode extends CommonDeclarationNode implements
		FieldDeclarationNode {

	public CommonFieldDeclarationNode(Source source, IdentifierNode identifier,
			TypeNode type) {
		super(source, identifier, type);
	}

	public CommonFieldDeclarationNode(Source source, IdentifierNode identifier,
			TypeNode type, ExpressionNode width) {
		super(source, identifier, type, width);
	}

	@Override
	public TypeNode getTypeNode() {
		return (TypeNode) child(1);
	}

	@Override
	public void setTypeNode(TypeNode typeNode) {
		setChild(1, typeNode);
	}

	@Override
	public ExpressionNode getBitFieldWidth() {
		if (numChildren() > 2)
			return (ExpressionNode) child(2);
		return null;
	}

	@Override
	public void setBitFieldWidth(ExpressionNode width) {
		setChild(2, width);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("FieldDeclaration");
	}

	@Override
	public Field getEntity() {
		return (Field) super.getEntity();
	}

}
