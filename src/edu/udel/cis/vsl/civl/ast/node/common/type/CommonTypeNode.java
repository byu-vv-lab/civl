package edu.udel.cis.vsl.civl.ast.node.common.type;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.token.IF.Source;

public abstract class CommonTypeNode extends CommonASTNode implements TypeNode {

	private TypeNodeKind typeNodeKind;

	private boolean constQualified = false;

	private boolean volatileQualified = false;

	private boolean restrictQualified = false;

	private boolean atomicQualified = false;

	private Type type;

	public CommonTypeNode(Source source, TypeNodeKind kind) {
		super(source);
		this.typeNodeKind = kind;
	}

	public CommonTypeNode(Source source, TypeNodeKind kind, ASTNode child) {
		super(source, child);
		this.typeNodeKind = kind;
	}

	public CommonTypeNode(Source source, TypeNodeKind kind, ASTNode child0,
			ASTNode child1) {
		super(source, child0, child1);
		this.typeNodeKind = kind;
	}

	@Override
	public TypeNodeKind kind() {
		return typeNodeKind;
	}

	@Override
	public boolean isConstQualified() {
		return constQualified;
	}

	@Override
	public void setConstQualified(boolean value) {
		this.constQualified = value;
	}

	@Override
	public boolean isVolatileQualified() {
		return volatileQualified;
	}

	@Override
	public void setVolatileQualified(boolean value) {
		this.volatileQualified = value;
	}

	@Override
	public boolean isRestrictQualified() {
		return restrictQualified;
	}

	@Override
	public void setRestrictQualified(boolean value) {
		this.restrictQualified = value;
	}

	@Override
	public boolean isAtomicQualified() {
		return atomicQualified;
	}

	@Override
	public void setAtomicQualified(boolean value) {
		this.atomicQualified = value;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
	}

	protected String qualifierString() {
		String result = typeId();
		boolean needSeparator = true;

		if (constQualified) {
			if (needSeparator)
				result += ", ";
			result = "const";
			needSeparator = true;
		}
		if (volatileQualified) {
			if (needSeparator)
				result += ", ";
			result += "volatile";
			needSeparator = true;
		}
		if (restrictQualified) {
			if (needSeparator)
				result += ", ";
			result += "restrict";
			needSeparator = true;
		}
		if (atomicQualified) {
			if (needSeparator)
				result += ", ";
			result += "atomic";
		}
		return result;
	}

	protected String typeId() {
		if (type == null)
			return "type=UNKNOWN";
		else
			return "type=" + type.getId();
	}
}
