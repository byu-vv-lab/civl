package edu.udel.cis.vsl.civl.model.IF.expression;

//TODO: plz add java doc here.
public interface DomainGuardExpression extends Expression {

	Expression domain();

	int dimension();

	Expression variableAt(int index);

	/**
	 * The counter variable for iterating a literal domain step by step.
	 * 
	 * @return the variable expression of the counter.
	 */
	VariableExpression getLiteralDomCounter();
}
