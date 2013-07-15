/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.civl.model.IF.Sourceable;

/**
 * @author zirkel
 *
 */
public abstract class CommonSourceable implements Sourceable {

	private ASTNode node;
		
	/* (non-Javadoc)
	 * @see edu.udel.cis.vsl.civl.model.IF.Sourceable#getNode()
	 */
	@Override
	public ASTNode getNode() {
		return node;
	}

	/* (non-Javadoc)
	 * @see edu.udel.cis.vsl.civl.model.IF.Sourceable#getSource()
	 */
	@Override
	public Source getSource() {
		return node.getSource();
	}

	/* (non-Javadoc)
	 * @see edu.udel.cis.vsl.civl.model.IF.Sourceable#setNode(edu.udel.cis.vsl.abc.ast.node.IF.ASTNode)
	 */
	@Override
	public void setNode(ASTNode node) {
		this.node = node;
	}

}
