/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import edu.udel.cis.vsl.civl.model.IF.expression.ArrayIndexExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.RealLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.number.real.RealNumberFactory;

/**
 * An evaluator is used to evaluate expressions.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Evaluator {

	private SymbolicUniverse symbolicUniverse;
	private RealNumberFactory numberFactory = new RealNumberFactory();

	/**
	 * An evaluator is used to evaluate expressions.
	 * 
	 * @param symbolicUniverse
	 *            The symbolic universe for the expressions.
	 */
	public Evaluator(SymbolicUniverse symbolicUniverse) {
		this.symbolicUniverse = symbolicUniverse;
	}

	/**
	 * Evaluate a generic expression. One of the overloaded evaluate methods for
	 * specific expressions should always be used instead.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			Expression expression) {
		SymbolicExpression result = null;

		if (expression instanceof ArrayIndexExpression) {
			result = evaluate(state, pid, (ArrayIndexExpression) expression);
		} else if (expression instanceof BinaryExpression) {
			result = evaluate(state, pid, (BinaryExpression) expression);
		} else if (expression instanceof BooleanLiteralExpression) {
			result = evaluate(state, pid, (BooleanLiteralExpression) expression);
		} else if (expression instanceof IntegerLiteralExpression) {
			result = evaluate(state, pid, (IntegerLiteralExpression) expression);
		} else if (expression instanceof RealLiteralExpression) {
			result = evaluate(state, pid, (RealLiteralExpression) expression);
		} else if (expression instanceof StringLiteralExpression) {
			result = evaluate(state, pid, (StringLiteralExpression) expression);
		} else if (expression instanceof UnaryExpression) {
			result = evaluate(state, pid, (UnaryExpression) expression);
		} else if (expression instanceof VariableExpression) {
			result = evaluate(state, pid, (VariableExpression) expression);
		}
		if (result != null) {
			result = (SymbolicExpression) symbolicUniverse.canonic(result);
		}
		return result;
	}

	/**
	 * Evaluate an array index expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The array index expression.
	 * @return A symbolic expression for an array read.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			ArrayIndexExpression expression) {
		SymbolicExpression array = evaluate(state, pid, expression.array());
		SymbolicExpression index = evaluate(state, pid, expression.index());
		// TODO: simplify index?

		return symbolicUniverse.arrayRead(array, (NumericExpression) index);
	}

	/**
	 * Evaluate a binary expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The binary expression.
	 * @return A symbolic expression for the binary operation.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			BinaryExpression expression) {
		SymbolicExpression left = evaluate(state, pid, expression.left());
		SymbolicExpression right = evaluate(state, pid, expression.right());

		// TODO: Check all expression types.
		switch (expression.operator()) {
		case PLUS:
			return symbolicUniverse.add((NumericExpression) left,
					(NumericExpression) right);
		case MINUS:
			return symbolicUniverse.subtract((NumericExpression) left,
					(NumericExpression) right);
		case TIMES:
			return symbolicUniverse.multiply((NumericExpression) left,
					(NumericExpression) right);
		case DIVIDE:
			return symbolicUniverse.divide((NumericExpression) left,
					(NumericExpression) right);
		case LESS_THAN:
			return symbolicUniverse.lessThan((NumericExpression) left,
					(NumericExpression) right);
		case LESS_THAN_EQUAL:
			return symbolicUniverse.lessThanEquals((NumericExpression) left,
					(NumericExpression) right);
		case EQUAL:
			return symbolicUniverse.equals(left, right);
		case NOT_EQUAL:
			return symbolicUniverse.not(symbolicUniverse.equals(left, right));
		case AND:
			return symbolicUniverse.and((BooleanExpression) left,
					(BooleanExpression) right);
		case OR:
			return symbolicUniverse.or((BooleanExpression) left,
					(BooleanExpression) right);
		case MODULO:
			return symbolicUniverse.modulo((NumericExpression) left,
					(NumericExpression) right);
		}
		return null;
	}

	/**
	 * Evaluate a boolean literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The boolean literal expression.
	 * @return The symbolic representation of the boolean literal expression.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			BooleanLiteralExpression expression) {
		return symbolicUniverse.bool(expression.value());
	}

	/**
	 * Evalute an integer literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The integer literal expression.
	 * @return The symbolic representation of the integer literal expression.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			IntegerLiteralExpression expression) {
		return symbolicUniverse.integer(expression.value().intValue());
	}

	/**
	 * Evaluate a real literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The real literal expression.
	 * @return The symbolic representation of the real literal expression.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			RealLiteralExpression expression) {
		return symbolicUniverse.number(symbolicUniverse
				.numberObject(numberFactory.rational(expression.value()
						.toPlainString())));
	}

	/**
	 * Evaluate a string literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The string literal expression.
	 * @return The symbolic representation of the string literal expression.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			StringLiteralExpression expression) {

		// TODO: Figure this out.
		// Right now, strings are intercepted in the executor.
		// They are used in write statements, and are
		// just passed to the PrintStream.
		return null;
	}

	/**
	 * Evaluate a unary expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The unary expression.
	 * @return The symbolic representation of the unary expression.
	 */
	public SymbolicExpression evaluate(State state, int pid,
			UnaryExpression expression) {
		switch (expression.operator()) {
		case NEGATIVE:
			return symbolicUniverse.minus((NumericExpression) evaluate(state,
					pid, expression.operand()));
		case NOT:
			return symbolicUniverse.not((BooleanExpression) evaluate(state,
					pid, expression.operand()));
		}
		return null;
	}

	/**
	 * Evaluate a variable expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The variable expression.
	 * @return
	 */
	public SymbolicExpression evaluate(State state, int pid,
			VariableExpression expression) {
		SymbolicExpression currentValue = state.valueOf(pid,
				expression.variable());

		return symbolicUniverse.simplifier(
				(BooleanExpression) state.pathCondition()).apply(currentValue);
	}

}
