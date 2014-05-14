package edu.udel.cis.vsl.civl.transform.common;

import java.util.List;

import edu.udel.cis.vsl.abc.antlr2ast.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.transform.IF.BaseTransformer;

/**
 * 
 * @author zmanchun
 *
 */
public abstract class CIVLBaseTransformer extends BaseTransformer {

	protected List<String> inputVariableNames;

	protected ASTBuilder astBuilder;
	
	protected boolean debug;

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

	public void setInputVars(List<String> inputVars) {
		this.inputVariableNames = inputVars;
	}

	public void setASTBuilder(ASTBuilder astBuilder) {
		this.astBuilder = astBuilder;
	}

}
