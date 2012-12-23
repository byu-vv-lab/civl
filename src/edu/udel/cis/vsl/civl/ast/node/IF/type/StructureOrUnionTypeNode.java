package edu.udel.cis.vsl.civl.ast.node.IF.type;

import edu.udel.cis.vsl.civl.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;

public interface StructureOrUnionTypeNode extends TypeNode, DeclarationNode,
		ExternalDefinitionNode, BlockItemNode {
	/**
	 * Is this a struct, not a union?
	 * 
	 * @return true if struct, false if union
	 */
	boolean isStruct();

	/**
	 * Is this a union, not a struct?
	 * 
	 * @return true if union, false if struct
	 */
	boolean isUnion();

	/**
	 * Returns the tag for this struct or union type. The tag is the name
	 * associated to the type.
	 * 
	 * @return
	 */
	IdentifierNode getTag();

	/**
	 * Returns the sequence node for the list of members (fields) for this
	 * struct or union type.
	 * 
	 * @return sequence node for member declarations
	 */
	SequenceNode<FieldDeclarationNode> getStructDeclList();
}
