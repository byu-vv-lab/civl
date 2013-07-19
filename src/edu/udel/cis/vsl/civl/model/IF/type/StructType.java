package edu.udel.cis.vsl.civl.model.IF.type;

import edu.udel.cis.vsl.civl.model.IF.Identifier;

public interface StructType extends Type {

	int numFields();

	StructField getField(int index);

	/**
	 * 
	 * @return A list of the field types in this struct.
	 */
	Iterable<StructField> fields();

	/**
	 * @return The name of this struct.
	 */
	Identifier name();
}
