package edu.udel.cis.vsl.civl.ast.entity.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public class CommonFunction extends CommonOrdinaryEntity implements Function {

	private boolean isInlined, doesNotReturn;

	private List<ExpressionNode> preconditions = new LinkedList<ExpressionNode>();

	private List<ExpressionNode> postconditions = new LinkedList<ExpressionNode>();

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

	@Override
	public Iterator<ExpressionNode> getPreconditions() {
		return preconditions.iterator();
	}

	@Override
	public Iterator<ExpressionNode> getPostconditions() {
		return postconditions.iterator();
	}

	@Override
	public void addPrecondition(ExpressionNode expression) {
		preconditions.add(expression);
	}

	@Override
	public void addPostcondition(ExpressionNode expression) {
		postconditions.add(expression);
	}

}
