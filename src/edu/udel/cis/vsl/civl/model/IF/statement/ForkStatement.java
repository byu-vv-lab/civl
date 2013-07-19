/**
 * 
 */
package edu.udel.cis.vsl.civl.model.IF.statement;

import java.util.Vector;

import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;

/**
 * A fork statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface ForkStatement extends Statement {

	/**
	 * @return Expression for place where the process reference will be stored.
	 *         Null if non-existent.
	 */
	LHSExpression lhs();

	/**
	 * @return The function that is started in the new process.
	 */
	Expression function();

	/**
	 * @return The arguments to the function.
	 */
	Vector<Expression> arguments();

	/**
	 * @param lhs
	 *            Expression for place where the process reference will be
	 *            stored. Null if non-existent.
	 */
	void setLhs(LHSExpression lhs);

	/**
	 * @param function
	 *            The function that is started in the new process.
	 */
	void setFunction(Expression function);

	/**
	 * @param arguments
	 *            The arguments to the function.
	 */
	void setArguments(Vector<Expression> arguments);

}
