package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

/**
 * The declaration of an enumerator (enumeration constant) within an enumeration
 * type definition.
 * 
 * @author siegel
 * 
 */
public interface EnumeratorDeclarationNode extends DeclarationNode {

	/**
	 * Each enumerator in an enumeration type definition may have an optional
	 * constant value specified. This method returns the constanat expression
	 * for that value, if the value is present. Otherwise, returns null
	 * 
	 * @return the specified constant value for this enumerator, else null
	 */
	ExpressionNode getValue();

	void setValue(ExpressionNode value);

	@Override
	Enumerator getEntity();

}
