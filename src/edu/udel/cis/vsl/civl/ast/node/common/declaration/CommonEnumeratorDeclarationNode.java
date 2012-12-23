package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonEnumeratorDeclarationNode extends CommonDeclarationNode
		implements EnumeratorDeclarationNode {

	public CommonEnumeratorDeclarationNode(Source source,
			IdentifierNode identifier, ExpressionNode value) {
		super(source, identifier, value);
	}

	@Override
	public ExpressionNode getValue() {
		return (ExpressionNode) child(1);
	}

	@Override
	public void setValue(ExpressionNode value) {
		setChild(1, value);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Enumerator");
	}

	@Override
	public Enumerator getEntity() {
		return (Enumerator) super.getEntity();
	}

}
