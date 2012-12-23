package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;

/**
 * A field designation is used in an initializer for a struct or union.
 * 
 * @author siegel
 * 
 */
public interface FieldDesignatorNode extends DesignatorNode {

	/**
	 * The name of field being designated for initialization.
	 * 
	 * @return the field name
	 */
	IdentifierNode getField();

	void setField(IdentifierNode name);
}
