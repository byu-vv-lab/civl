/**
 * 
 */
package edu.udel.cis.vsl.civl.model.statement;

import java.util.Vector;

import edu.udel.cis.vsl.civl.model.Function;
import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.location.Location;

/**
 * A function call. Either of the form f(x) or else v=f(x).
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CallStatement extends Statement {

	private Expression lhs = null;
	private Function function;
	private Vector<Expression> arguments;

	/**
	 * A function call. Either of the form f(x) or else v=f(x).
	 * 
	 * @param source
	 *            The source location for this call statement.
	 * @param function
	 *            The function.
	 * @param arguments
	 *            The arguments to the function.
	 */
	public CallStatement(Location source, Function function,
			Vector<Expression> arguments) {
		super(source);
		this.function = function;
		this.arguments = arguments;
	}

	/**
	 * A function call.
	 * 
	 * @param source
	 *            The source location for this call statement.
	 * @param lhs
	 *            The (optional) left hand side expression. Used when the call
	 *            statement is also an assignment. Null if not applicable.
	 * @param function
	 *            The function.
	 * @param arguments
	 *            The arguments to the function.
	 */
	public CallStatement(Location source, Expression lhs, Function function,
			Vector<Expression> arguments) {
		super(source);
		this.lhs = lhs;
		this.function = function;
		this.arguments = arguments;
	}

	/**
	 * @return The left hand side expression if applicable. Else null.
	 */
	public Expression lhs() {
		return lhs;
	}

	/**
	 * @return The function being called.
	 */
	public Function function() {
		return function;
	}

	/**
	 * @return The arguments to the function.
	 */
	public Vector<Expression> arguments() {
		return arguments;
	}

	/**
	 * @param lhs
	 *            The left hand side expression if applicable. Else null.
	 */
	public void setLhs(Expression lhs) {
		this.lhs = lhs;
	}

	/**
	 * @param function
	 *            The function being called.
	 */
	public void setFunction(Function function) {
		this.function = function;
	}

	/**
	 * @param arguments
	 *            The arguments to the function.
	 */
	public void setArguments(Vector<Expression> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		String result = function.toString();
		
		if (lhs != null) {
			result = lhs + " = " + result;
		}
		return result;
	}

}
