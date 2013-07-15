package edu.udel.cis.vsl.civl.model.IF;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public interface Sourceable {

	/**
	 * 
	 * @return The AST node corresponding to this model component.
	 */
	ASTNode getNode();

	/**
	 * 
	 * @return The source information from the input file corresponding to this
	 *         model component.
	 */
	Source getSource();

	/**
	 * 
	 * @param node
	 *            The AST node corresponding to this model component.
	 */
	void setNode(ASTNode node);
	
}
