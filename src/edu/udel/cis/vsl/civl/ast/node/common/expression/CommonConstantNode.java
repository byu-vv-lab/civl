package edu.udel.cis.vsl.civl.ast.node.common.expression;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.token.IF.Source;

public abstract class CommonConstantNode extends CommonExpressionNode implements
		ConstantNode {

	private String representation;

	public CommonConstantNode(Source source, String representation) {
		super(source);
		this.representation = representation;
	}

	public CommonConstantNode(Source source, String representation, Type type) {
		super(source);
		this.representation = representation;
		setInitialType(type);
	}

	@Override
	public String getStringRepresentation() {
		return representation;
	}

	@Override
	public void setStringRepresentation(String representation) {
		this.representation = representation;
	}

	@Override
	public boolean isConstantExpression() {
		return true;
	}

}
