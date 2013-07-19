/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.statement;

import java.util.Vector;

import edu.udel.cis.vsl.civl.model.IF.Function;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallStatement;

/**
 * A function call. Either of the form f(x) or else v=f(x).
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonCallStatement extends CommonStatement implements
		CallStatement {

	private LHSExpression lhs = null;
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
	public CommonCallStatement(Location source, Function function,
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
	public CommonCallStatement(Location source, LHSExpression lhs,
			Function function, Vector<Expression> arguments) {
		super(source);
		this.lhs = lhs;
		this.function = function;
		this.arguments = arguments;
	}

	/**
	 * @return The left hand side expression if applicable. Else null.
	 */
	@Override
	public LHSExpression lhs() {
		return lhs;
	}

	/**
	 * @return The function being called.
	 */
	@Override
	public Function function() {
		return function;
	}

	/**
	 * @return The arguments to the function.
	 */
	@Override
	public Vector<Expression> arguments() {
		return arguments;
	}

	/**
	 * @param lhs
	 *            The left hand side expression if applicable. Else null.
	 */
	@Override
	public void setLhs(LHSExpression lhs) {
		this.lhs = lhs;
	}

	/**
	 * @param function
	 *            The function being called.
	 */
	@Override
	public void setFunction(Function function) {
		this.function = function;
	}

	/**
	 * @param arguments
	 *            The arguments to the function.
	 */
	@Override
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
