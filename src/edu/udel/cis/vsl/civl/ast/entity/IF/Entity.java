package edu.udel.cis.vsl.civl.ast.entity.IF;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

/**
 * An entity is an underlying program "conceptual thing" that can be named by an
 * identifier.
 * 
 * 
 * @author siegel
 * 
 */
public interface Entity {

	/**
	 * The different kinds of Entity.
	 * 
	 */
	public static enum EntityKind {
		VARIABLE,
		FUNCTION,
		TYPEDEF,
		STRUCTURE_OR_UNION,
		ENUMERATION,
		ENUMERATOR,
		FIELD,
		LABEL
	};

	/**
	 * The different kinds of linkage an entity may have.
	 */
	public static enum LinkageKind {
		EXTERNAL, INTERNAL, NONE
	};

	/**
	 * The kind of entity this is.
	 * 
	 * If the kind is VARIABLE, this entity may be safely cast to Variable.
	 * 
	 * If the kind is FUNCTION, this entity may be safely cast to Function
	 * 
	 * If the kind is TYPEDEF, this entity may be safely cast to Typedef.
	 * 
	 * If the kind is STRUCTURE_OR_UNION, this entity may be safely cast to
	 * StructureOrUnion.
	 * 
	 * If the kind is ENUMERATION, thien entity may be safely cast to
	 * Enumeration.
	 * 
	 * If the kind is ENUMERATOR, this entity may be safely cast to Enumerator.
	 * An enumerator is an element of an enumeration.
	 * 
	 * If the kind is FIELD, this entity may be safely cast to Field. A "field"
	 * is a member of a structure or union.
	 * 
	 * If the kind is LABEL, this entity may be safely cast to Label.
	 * 
	 * @return the entity kind
	 */
	EntityKind getEntityKind();

	/**
	 * The name of this entity. This is the identifier used in the declaration
	 * of the entity. It can be null in certain situations (e.g., an unnamed
	 * field).
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns an iterator over all the known delcarations of this entity. An
	 * entity may be declared multiple times. This includes the definition.
	 * 
	 * @return iterator over declarations of this entity
	 */
	Iterator<DeclarationNode> getDeclarations();

	DeclarationNode getFirstDeclaration();

	int getNumDeclarations();

	DeclarationNode getDeclaration(int index);

	void addDeclaration(DeclarationNode declaration);

	/**
	 * Returns the definition, i.e., the defining declaration of this entity.
	 * 
	 * @return
	 */
	DeclarationNode getDefinition();

	void setDefinition(DeclarationNode declaration);

	/**
	 * Returns the kind of linkage this entity has.
	 * 
	 * @return
	 */
	LinkageKind getLinkage();

	void setLinkage(LinkageKind linkage);

	/**
	 * Other than Label, every kind of Entity has a type, returned by this
	 * method. For a Label, this returns null.
	 * 
	 * @return
	 */
	Type getType();

	void setType(Type type);

	/**
	 * Is this a system-defined entity (as opposed to a user-defined one)?
	 * Examples include standard types, like size_t. The default is false; it
	 * can be changed using method setIsSystem.
	 */
	boolean isSystem();

	void setIsSystem(boolean value);

}
