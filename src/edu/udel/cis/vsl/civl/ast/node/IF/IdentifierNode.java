package edu.udel.cis.vsl.civl.ast.node.IF;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;

/**
 * An identifier can denote: (1) an object, (2) a function, (3) a tag of a
 * structure, union, or enumeration, (4) a member of a structure, union, or
 * enumeration, (5) a typedef name, or (6) a label name.
 * 
 * Each of these is represented by a different subtype of Identifier. Each
 * subtype provides an appropriate method to get the declaration of the
 * identifier.
 * 
 * @author siegel
 * 
 */
public interface IdentifierNode extends ASTNode {

	/** The name of the identifier */
	String name();

	void setName(String name);

	Entity getEntity();

	void setEntity(Entity entity);

	Iterator<DeclarationNode> getDeclarations();

	// is this necessary?
	void addDeclaration(DeclarationNode declaration);

	/** Returns the declaration that defines this identifier. */
	DeclarationNode getDefinition();

	void setDefinition(DeclarationNode declaration);


}
