package edu.udel.cis.vsl.civl.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.transform.IF.BaseTransformer;

public abstract class CIVLBaseTransformer extends BaseTransformer {

	protected CIVLBaseTransformer(String code, String longName,
			String shortDescription, ASTFactory astFactory) {
		super(code, longName, shortDescription, astFactory);
	}

	/**
	 * Creates an identifier expression node with a given name.
	 * 
	 * @param name
	 *            The name of the identifier.
	 * @return
	 */
	protected ExpressionNode identifierExpression(Source source, String name) {
		return nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, name));
	}
}
