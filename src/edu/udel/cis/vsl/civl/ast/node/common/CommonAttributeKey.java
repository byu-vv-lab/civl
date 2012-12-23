package edu.udel.cis.vsl.civl.ast.node.common;

import edu.udel.cis.vsl.civl.ast.node.IF.AttributeKey;

public class CommonAttributeKey implements AttributeKey {

	private String name;

	private int id;

	private Class<Object> attributeClass;

	public CommonAttributeKey(int id, String name, Class<Object> attributeClass) {
		this.id = id;
		this.name = name;
		this.attributeClass = attributeClass;
	}

	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public Class<Object> getAttributeClass() {
		return attributeClass;
	}

	public int getId() {
		return id;
	}

}
