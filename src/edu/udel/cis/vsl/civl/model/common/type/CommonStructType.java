/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.type;

import java.util.ArrayList;

import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

/**
 * @author zirkel
 * 
 */
public class CommonStructType extends CommonType implements CIVLStructType {

	private Identifier name;

	private ArrayList<StructField> fields = new ArrayList<StructField>();

	private Variable variable = null;

	/**
	 * A struct type has a sequence of struct fields.
	 * 
	 * @param fields
	 *            A list of struct fields.
	 * 
	 */
	public CommonStructType(Identifier name, Iterable<StructField> fields) {
		int count = 0;

		for (StructField field : fields) {
			this.fields.add(field);
			((CommonStructField) field).setIndex(count);
			count++;
		}
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.model.IF.type.StructType#fields()
	 */
	@Override
	public Iterable<StructField> fields() {
		return fields;
	}

	@Override
	public Identifier name() {
		return name;
	}

	@Override
	public String toString() {
		String result = "struct " + name.toString() + " {\n";

		for (StructField f : fields) {
			result += "  " + f.toString() + "\n";
		}
		result += "}";
		return result;
	}

	@Override
	public int numFields() {
		return fields.size();
	}

	@Override
	public StructField getField(int index) {
		return fields.get(index);
	}

	@Override
	public Variable getVariable() {
		return variable;
	}

	@Override
	public void setVariable(Variable variable) {
		this.variable = variable;
	}

}
