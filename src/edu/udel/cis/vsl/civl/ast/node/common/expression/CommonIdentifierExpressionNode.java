package edu.udel.cis.vsl.civl.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonIdentifierExpressionNode extends CommonExpressionNode
		implements IdentifierExpressionNode {

	public CommonIdentifierExpressionNode(Source source,
			IdentifierNode identifier) {
		super(source, identifier);
		assert identifier != null;
	}

	@Override
	public IdentifierNode getIdentifier() {
		return (IdentifierNode) child(0);
	}

	@Override
	public void setIdentifier(IdentifierNode identifier) {
		setChild(0, identifier);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("IdentifierExpression");
	}

	// @Override
	// public boolean equivalentConstant(ExpressionNode expression) {
	// if (expression instanceof CommonIdentifierExpressionNode) {
	// CommonIdentifierExpressionNode that = (CommonIdentifierExpressionNode)
	// expression;
	// Entity thisEntity = getIdentifier().getEntity();
	// Entity thatEntity = that.getIdentifier().getEntity();
	//
	// return thisEntity.getEntityKind() == EntityKind.ENUMERATOR
	// && thisEntity == thatEntity;
	// }
	// return false;
	// }

	@Override
	public boolean isConstantExpression() {
		Entity entity = getIdentifier().getEntity();

		return entity.getEntityKind() == EntityKind.ENUMERATOR;
	}

}
