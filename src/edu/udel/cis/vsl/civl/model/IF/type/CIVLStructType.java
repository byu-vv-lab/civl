package edu.udel.cis.vsl.civl.model.IF.type;

import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

public interface CIVLStructType extends CIVLType {

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

	/**
	 * Returns the variable of type {@link CIVLDynamicType} used to hold the
	 * dynamic type of this struct type.
	 * 
	 * @return the dynamic type variable corresponding to this struct type
	 */
	Variable getVariable();

	/**
	 * Sets the variable of type {@link CIVLDynamicType} used to hold the
	 * dynamic type of this struct type.
	 * 
	 * @param variable
	 *            the variable
	 */
	void setVariable(Variable variable);
}
