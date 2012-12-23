package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonFunction extends CommonOrdinaryEntity implements Function {

	private boolean isInlined, doesNotReturn;

	public CommonFunction(String name, LinkageKind linkage, Type type) {
		super(EntityKind.FUNCTION, name, linkage, type);
	}

	@Override
	public boolean isInlined() {
		return isInlined;
	}

	@Override
	public void setIsInlined(boolean value) {
		this.isInlined = value;
	}

	@Override
	public boolean doesNotReturn() {
		return doesNotReturn;
	}

	@Override
	public void setDoesNotReturn(boolean value) {
		this.doesNotReturn = value;
	}

	@Override
	public FunctionDefinitionNode getDefinition() {
		return (FunctionDefinitionNode) super.getDefinition();
	}

	@Override
	public Scope getScope() {
		return getDefinition().getScope();
	}

	@Override
	public FunctionType getType() {
		return (FunctionType) super.getType();
	}

}
