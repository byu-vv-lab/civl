/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import edu.udel.cis.vsl.civl.model.expression.ArrayIndexExpression;
import edu.udel.cis.vsl.civl.model.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.RealLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.expression.VariableExpression;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.sarl.number.real.RealNumberFactory;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicExpressionIF;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicUniverseIF;

/**
 * An evaluator is used to evaluate expressions.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Evaluator {

	private SymbolicUniverseIF symbolicUniverse;
	private RealNumberFactory numberFactory = new RealNumberFactory();

	/**
	 * An evaluator is used to evaluate expressions.
	 * 
	 * @param symbolicUniverse
	 *            The symbolic universe for the expressions.
	 */
	public Evaluator(SymbolicUniverseIF symbolicUniverse) {
		this.symbolicUniverse = symbolicUniverse;
	}

	/**
	 * Evaluate a generic expression. One of the overloaded evaluate methods for
	 * specific expressions should always be used instead.
	 */
	public SymbolicExpressionIF evaluate(State state, int pid,
			Expression expression) {
		SymbolicExpressionIF result = null;

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
			result = symbolicUniverse.canonicalizeTree(symbolicUniverse
					.tree(result));
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			ArrayIndexExpression expression) {
		SymbolicExpressionIF array = evaluate(state, pid, expression.array());
		SymbolicExpressionIF index = evaluate(state, pid, expression.index());
		SymbolicExpressionIF simplifiedIndex = symbolicUniverse.simplifier(
				state.pathCondition()).simplify(index);

		return symbolicUniverse.arrayRead(array, simplifiedIndex);
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			BinaryExpression expression) {
		SymbolicExpressionIF left = evaluate(state, pid, expression.left());
		SymbolicExpressionIF right = evaluate(state, pid, expression.right());

		switch (expression.operator()) {
		case PLUS:
			return symbolicUniverse.add(left, right);
		case MINUS:
			return symbolicUniverse.subtract(left, right);
		case TIMES:
			return symbolicUniverse.multiply(left, right);
		case DIVIDE:
			return symbolicUniverse.divide(left, right);
		case LESS_THAN:
			return symbolicUniverse.lessThan(left, right);
		case LESS_THAN_EQUAL:
			return symbolicUniverse.lessThanEquals(left, right);
		case EQUAL:
			return symbolicUniverse.equals(left, right);
		case NOT_EQUAL:
			return symbolicUniverse.not(symbolicUniverse.equals(left, right));
		case AND:
			return symbolicUniverse.and(left, right);
		case OR:
			return symbolicUniverse.or(left, right);
		case MODULO:
			return symbolicUniverse.modulo(left, right);
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			BooleanLiteralExpression expression) {
		return symbolicUniverse.concreteExpression(expression.value());
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			IntegerLiteralExpression expression) {
		return symbolicUniverse.concreteExpression(expression.value()
				.intValue());
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			RealLiteralExpression expression) {
		return symbolicUniverse.concreteExpression(numberFactory
				.rational(expression.value().toPlainString()));
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
	public SymbolicExpressionIF evaluate(State state, int pid,
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			UnaryExpression expression) {
		switch (expression.operator()) {
		case NEGATIVE:
			return symbolicUniverse.minus(evaluate(state, pid,
					expression.operand()));
		case NOT:
			return symbolicUniverse.not(evaluate(state, pid,
					expression.operand()));
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
	public SymbolicExpressionIF evaluate(State state, int pid,
			VariableExpression expression) {
		SymbolicExpressionIF currentValue = state.valueOf(pid,
				expression.variable());

		return symbolicUniverse.simplifier(state.pathCondition()).simplify(
				currentValue);
	}

}
