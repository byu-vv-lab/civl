package edu.udel.cis.vsl.civl.ast.type.common;

import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public abstract class CommonType implements Type {

	private TypeKind kind;

	private int id = -1;

	public CommonType(TypeKind kind) {
		this.kind = kind;
	}

	@Override
	public TypeKind kind() {
		return kind;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * A "variably modified" (VM) type is a declarator type which in the nested
	 * sequence of declarators has a VLA type, or any type derived from a VM
	 * type. I.e.: a VLA is a VM; a pointer to a VM is a VM; a function
	 * returning a VM is a VM; an array with a VM element type is a VM.
	 * 
	 * Implement this in all concrete subclasses.
	 */
	@Override
	public abstract boolean isVariablyModified();

	@Override
	public String toString() {
		return "Type[kind=" + kind + "]";
	}

}
