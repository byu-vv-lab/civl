package edu.udel.cis.vsl.civl.ast.type.IF;

import java.util.Iterator;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.entity.IF.StructureOrUnion;

/**
 * A structure or union type. Such a type is specified by (1) a bit which says
 * whether this is a structure or a union, (2) a tag (which is a string which
 * names the type), and (3) a sequence of Fields, which are the members of the
 * type. The type may be incomplete (the fields have not yet been specified) or
 * complete (the fields have been specified).
 * 
 * @author siegel
 * 
 */
public interface StructureOrUnionType extends UnqualifiedObjectType {

	/**
	 * Returns the tag of this type. The tag is the string that occurs in the
	 * declaration. For example, in a declaration "struct foo {...}", the tag is
	 * "foo".
	 * 
	 * @return the tag of this type
	 */
	String getTag();

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
	 * Returns the number of fields (members) of this structure or union type.
	 * 
	 * @exception RuntimeException
	 *                if this type is not yet complete
	 * @return the number of fields in this type
	 */
	int getNumFields();

	/**
	 * Returns the index-th field in this structure or union type.
	 * 
	 * @param index
	 *            an integer between 0 and the number of fields minus 1,
	 *            inclusive
	 * @return the index-th field
	 * @exception RuntimeException
	 *                if this type is not yet complete
	 */
	Field getField(int index);
	
	Field getField(String fieldName);

	/**
	 * Returns an iterator over the fields in this type, or null if this type is
	 * not yet complete.
	 * 
	 * @return an iterator over the fields, in order, or null
	 */
	Iterator<Field> getFields();

	/**
	 * Completes this structure of union type by specifying its contents, i.e.,
	 * the list of fields.
	 * 
	 * @exception RuntimeException
	 *                if this type is already complete
	 * 
	 * @param fields
	 *            an ordered list of fields
	 */
	void complete(List<Field> fields);

	StructureOrUnion getEntity();

	void setEntity(StructureOrUnion entity);

}
