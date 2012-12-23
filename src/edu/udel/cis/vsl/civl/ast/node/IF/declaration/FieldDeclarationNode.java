package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * Represents a declaration of a field (member) in a struct or union type.
 * 
 * @author siegel
 * 
 */
public interface FieldDeclarationNode extends DeclarationNode {

	/**
	 * The type of the field being declared. This may be null.
	 */
	TypeNode getTypeNode();

	void setTypeNode(TypeNode type);

	/**
	 * Returns the bit field width. This is a constant expression. It is
	 * optional. If there is no identifier, the bit width must be there, but it
	 * can also be there with an identifier. If absent, this method returns
	 * null.
	 */
	ExpressionNode getBitFieldWidth();

	void setBitFieldWidth(ExpressionNode width);

	@Override
	Field getEntity();

}
