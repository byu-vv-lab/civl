package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.type.Type;

public class CommonStructField implements StructField {

	private int index = -1;
	private Identifier name;
	private Type type;

	public CommonStructField(Identifier name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Identifier name() {
		return name;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public String toString() {
		return name + " : " + type;
	}

	void setIndex(int index) {
		this.index = index;
	}

}
