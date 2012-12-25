package edu.udel.cis.vsl.civl.ast.entity.IF;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;

/**
 * Represents a function entity.
 * 
 * @author siegel
 * 
 */
public interface Function extends OrdinaryEntity {

	@Override
	FunctionType getType();

	boolean isInlined();

	void setIsInlined(boolean value);

	boolean doesNotReturn();

	void setDoesNotReturn(boolean value);

	@Override
	FunctionDefinitionNode getDefinition();

	/**
	 * Returns the function scope associated to this function. This is the scope
	 * in which the ordinary labels are declared.
	 * 
	 * @return the function scope associated to this function
	 */
	Scope getScope();
	
	Iterator<ExpressionNode> getPreconditions();
	
	Iterator<ExpressionNode> getPostconditions();
	
	void addPrecondition(ExpressionNode expression);
	
	void addPostcondition(ExpressionNode expression);

	// TODO: perhaps more information is needed. About each parameter:
	// does it have static extent? What is the extent (constant
	// or expression)?

	/*
	 * The word "static" may appear between the brackets in certain situations.
	 * See C11 6.7.6.3(7) for its meaning. It can only be used in the
	 * declaration of a function parameter.
	 */
	// boolean hasStaticExtent();

}
