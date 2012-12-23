package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

/**
 * A Field is a member of a structure or union. A Field is determined by the
 * following (1) its name, which is obtained by method getName() in the parent
 * interface Entity, (2) its type, which must be an object type, and is obtained
 * by method getType(), and (3) its optional bit width, obtained by method
 * getBitWidth. Note that any of these 3 things may be null (but not all three)
 * in an instance.
 * 
 * @author siegel
 * 
 */
public interface Field extends Entity {

	/**
	 * Returns the index of this field in the list of members of the structure
	 * or union. Members are indexed starting from 0.
	 * 
	 * @return field index in structure or union
	 */
	int getMemberIndex();

	/**
	 * Returns the (sole) declaration of this field in the AST.
	 */
	FieldDeclarationNode getDefinition();

	/**
	 * Returns the type of the field, or null if no type is specified. A field
	 * with a non-null type must have an object type.
	 */
	ObjectType getType();

	/**
	 * Returns the integer bit width constant value, or null if a bit width is
	 * not specified.
	 * 
	 * @return bit width or null
	 */
	Value getBidWidth();

	/**
	 * Returns the structure or union entity to which this field belongs.
	 * 
	 * @return the StructureOrUnion object to which this Field belongs
	 */
	StructureOrUnion getStructureOrUnion();
}
