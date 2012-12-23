package edu.udel.cis.vsl.civl.civlc.antlr2ast.impl;

import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

public class SimpleScope {

	private SimpleScope parent;

	private boolean isFunctionScope;

	private Map<String, TypeNode> typedefMap = new HashMap<String, TypeNode>();

	SimpleScope(SimpleScope parent, boolean isFunctionScope) {
		this.parent = parent;
		this.isFunctionScope = isFunctionScope;
	}

	SimpleScope(SimpleScope parent) {
		this(parent, false);
	}

	void putMapping(String name, TypeNode node) {
		typedefMap.put(name, node);
	}

	TypeNode getReferencedType(String name) {
		return typedefMap.get(name);
	}

	SimpleScope getParent() {
		return parent;
	}

	boolean isFunctionScope() {
		return isFunctionScope;
	}
}
