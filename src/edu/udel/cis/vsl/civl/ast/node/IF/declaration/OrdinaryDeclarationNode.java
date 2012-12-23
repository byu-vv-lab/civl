package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * A declaration of a variable or function via a C "declarator". In addition to
 * the identifier (common to all declarations), this also specifies a type and
 * storage information, function specifiers, and alignment specifiers.
 * 
 * Note that this is not used to declare members of structures or unions
 * ("fields"). A FieldDeclaration is used for that.
 * 
 * @author siegel
 * 
 */
public interface OrdinaryDeclarationNode extends BlockItemNode,
		DeclarationNode, ExternalDefinitionNode {

	/**
	 * The type of the thing being declared. This may be null: e.g., in a
	 * function declaration, the parameter types do not necessarily have to be
	 * declared.
	 */
	TypeNode getTypeNode();

	void setTypeNode(TypeNode type);

	/**
	 * Does the declaration include the "extern" storage class specifier?
	 * 
	 * For functions and objects.
	 * 
	 * @return true if declaration contains "extern"
	 */
	boolean hasExternStorage();

	void setExternStorage(boolean value);

	/**
	 * Does the declaration include the "static" storage class specifier?
	 * 
	 * For functions and objects.
	 * 
	 * @return true if declaration contains "static"
	 */
	boolean hasStaticStorage();

	void setStaticStorage(boolean value);

}
