/**
 * 
 */
package edu.udel.cis.vsl.civl.model.expression;

import edu.udel.cis.vsl.civl.model.variable.Variable;

/**
 * A use of a variable in an expression.
 * 
 * @author Timothy K. Zirkel (zirkel)
 *
 */
public class VariableExpression extends Expression {

	Variable variable;
	
	/**
	 * A use of a variable in an expression.
	 * 
	 * @param variable The variable.
	 */
	public VariableExpression(Variable variable) {
		this.variable = variable;
	}

	/**
	 * @return The variable
	 */
	public Variable variable() {
		return variable;
	}

	/**
	 * @param variable The variable.
	 */
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return variable.name().name();
	}

}
