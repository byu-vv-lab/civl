package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;

/**
 * The root of the declaration type hierarchy. According to C11, "A declaration
 * specifies the interpretation and attributes of a set of identifiers." An
 * object of this type specifies a declaration for exactly one identifier.
 * 
 * A DeclarationNode does not correspond exactly to the notion of "declaration"
 * in the C Standard. For example, in the C Standard, a static assertion is a
 * kind of declaration. This seems more like a grammatical convenience (since a
 * static assertion can appear almost anywhere a declaration can) then a logical
 * organization of the concepts, as a static assertion does not specify
 * "the interpretation and attributes of a set of identifiers".
 * 
 * Every Declaration occurs in exactly one Scope.
 * 
 * @author siegel
 * 
 */
public interface DeclarationNode extends ASTNode {

	/**
	 * The identifier being declared. May be null.
	 * 
	 * @return the identifier being declared
	 */
	IdentifierNode getIdentifier();

	/**
	 * The name of the identifier being declared. Could be null. If the
	 * identifier is not null, this is the same as getIdentifier().name().
	 * 
	 * @return name of identifier being declared, or null if the declaration is
	 *         anonymous
	 */
	String getName();

	/**
	 * Sets the identifier being declared.
	 * 
	 * @param identifier
	 *            the identifier being declared
	 */
	void setIdentifier(IdentifierNode identifier);

	/**
	 * Is this the defining declaration of the identifier? According to C11, "A
	 * definition of an identifier is a declaration for that identifier that: Ñ
	 * for an object, causes storage to be reserved for that object; Ñ for a
	 * function, includes the function body; Ñ for an enumeration constant, is
	 * the (only) declaration of the identifier; Ñ for a typedef name, is the
	 * first (or only) declaration of the identifier."
	 * 
	 * @return true if this is the identifier's definition.
	 * 
	 */
	boolean isDefinition();

	/**
	 * Sets the "isDefinition" field to the given boolean value.
	 * 
	 * @param value
	 *            new value for "isDefinition"
	 */
	void setIsDefinition(boolean value);

	/**
	 * Returns the entity whose existence is declared by this declaration.
	 * 
	 * @return the entity declared by this declarations
	 */
	Entity getEntity();

}
