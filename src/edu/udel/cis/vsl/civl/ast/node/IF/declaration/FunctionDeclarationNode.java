package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;

public interface FunctionDeclarationNode extends OrdinaryDeclarationNode {
	
	@Override
	Function getEntity();
	
	/**
	 * Does the declaration include the "inline" function specifier? Only
	 * applies to function delcarations.
	 * 
	 * For functions only (not objects).
	 * 
	 * @return true if declaration contains "inline"
	 */
	boolean hasInlineFunctionSpecifier();

	void setInlineFunctionSpecifier(boolean value);

	/**
	 * Does the declaration include the "_Noreturn" function specifier? Only
	 * applies to function delcarations.
	 * 
	 * For functions only (not objects).
	 * 
	 * @return true if declaration contains "_Noreturn"
	 */
	boolean hasNoreturnFunctionSpecifier();

	void setNoreturnFunctionSpecifier(boolean value);
	
	SequenceNode<ContractNode> getContract();
	
	void setContract(SequenceNode<ContractNode> contract);
	

}
