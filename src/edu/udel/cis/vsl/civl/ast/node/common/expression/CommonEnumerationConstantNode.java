package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.EnumerationConstantNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonEnumerationConstantNode extends CommonConstantNode implements
		EnumerationConstantNode {

	public CommonEnumerationConstantNode(Source source, IdentifierNode name) {
		super(source, name.name());
		addChild(name);
	}

	@Override
	public IdentifierNode getName() {
		return (IdentifierNode) child(0);
	}

	@Override
	public void setName(IdentifierNode name) {
		setChild(0, name);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("EnumerationConstant");
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonEnumerationConstantNode) {
	// CommonEnumerationConstantNode that = (CommonEnumerationConstantNode)
	// expression;
	// Enumerator thisEnumerator = (Enumerator) getName().getEntity();
	// Enumerator thatEnumerator = (Enumerator) that.getName().getEntity();
	//
	// return (thisEnumerator == thatEnumerator);
	// }
	// return false;
	// }

}
