package edu.udel.cis.vsl.civl.ast.node.IF.type;

import edu.udel.cis.vsl.civl.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;

/**
 * An enumeration type.
 * 
 * @author siegel
 */
public interface EnumerationTypeNode extends DeclarationNode, TypeNode,
		ExternalDefinitionNode, BlockItemNode {
	/**
	 * Returns the "tag", which is the name of this enumerated type. For
	 * example, in "enum color {...}", "color" is the tag.
	 * 
	 * @return the tag of this enumerated type
	 */
	IdentifierNode getTag();

	/**
	 * Returns the sequence of enumerators for this enumerated type. Each
	 * enumerator consists of a name and optional constant expression. If the
	 * optional constant expression is absent, it will be null.
	 * 
	 * @return the sequence node for the enumerators of this type
	 */
	SequenceNode<EnumeratorDeclarationNode> enumerators();
}
