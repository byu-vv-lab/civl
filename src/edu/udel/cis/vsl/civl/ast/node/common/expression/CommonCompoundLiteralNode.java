package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.CompoundInitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonCompoundLiteralNode extends CommonExpressionNode implements
		CompoundLiteralNode {

	public CommonCompoundLiteralNode(Source source, TypeNode typeNode,
			CompoundInitializerNode initializerList) {
		super(source, typeNode, initializerList);
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// return equals(expression);
	// }

	@Override
	protected void printBody(PrintStream out) {
		out.print("CompoundLiteral");
	}

	@Override
	public TypeNode getTypeNode() {
		return (TypeNode) this.child(0);
	}

	@Override
	public CompoundInitializerNode getInitializerList() {
		return (CompoundInitializerNode) this.child(1);
	}

}
