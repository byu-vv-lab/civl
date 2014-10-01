package edu.udel.cis.vsl.civl.model.IF.expression;

/**
 * This is the guard to be checked for CIVL for loops ($for and $parfor). It has
 * the form of (i, j, k, ... : dom), where i, j, k ... are iteration variables
 * and dom is the domain expression. The number of iteration variables should be
 * equal to the dimension of the domain. A domain guard expression is evaluated
 * to be true if and only if (i, j, k, ...) is an element of the domain.
 * 
 * @author Manchun Zheng (zmanchun)
 * */
public interface DomainGuardExpression extends Expression {

	/**
	 * Returns the domain expression.
	 * 
	 * @return The domain expression.
	 */
	Expression domain();

	/**
	 * Returns the dimension of the domain expression.
	 * 
	 * @return The dimension of the domain expression.
	 */
	int dimension();

	/**
	 * Returns the iteration variable of the given index.
	 * 
	 * @param index
	 *            The index of the iteration variable to be returned.
	 * @return The iteration variable of the given index.
	 */
	Expression variableAt(int index);

	/**
	 * The counter variable for iterating a literal domain step by step.
	 * 
	 * @return the variable expression of the counter.
	 */
	VariableExpression getLiteralDomCounter();
}
