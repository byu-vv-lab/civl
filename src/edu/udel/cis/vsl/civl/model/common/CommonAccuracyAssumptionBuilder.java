package edu.udel.cis.vsl.civl.model.common;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.AbstractFunction;
import edu.udel.cis.vsl.civl.model.IF.AccuracyAssumptionBuilder;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.AbstractFunctionCallExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.BoundVariableExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.CastExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression.ExpressionKind;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.QuantifiedExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.QuantifiedExpression.Quantifier;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.util.Pair;

public class CommonAccuracyAssumptionBuilder implements
		AccuracyAssumptionBuilder {

	/** The model factory used to create new model components. */
	private ModelFactory factory;

	/** Keep track of all abstract function calls in this assumption. */
	private List<AbstractFunctionCallExpression> calls = new LinkedList<AbstractFunctionCallExpression>();

	/** Keep track of all quantified expressions in this assumption. */
	private List<QuantifiedExpression> quantifiedExpressions = new LinkedList<QuantifiedExpression>();

	public CommonAccuracyAssumptionBuilder(ModelFactory factory) {
		this.factory = factory;
	}

	@Override
	public Fragment accuracyAssumptions(Expression assumption, Scope scope) {
		Fragment newAssumptions = new CommonFragment();

		analyze(assumption);
		newAssumptions = newAssumptions.combineWith(generateAssumptions(scope));
		return newAssumptions;
	}

	private void analyze(Expression expression) {
		// TODO: This is a pretty naive analysis that probably won't hold up for
		// e.g. conjunctions of quantified expressions, etc. Make it more
		// robust.
		switch (expression.expressionKind()) {
		case ABSTRACT_FUNCTION_CALL:
			calls.add((AbstractFunctionCallExpression) expression);
			break;
		case QUANTIFIER:
			quantifiedExpressions.add((QuantifiedExpression) expression);
			analyze(((QuantifiedExpression) expression).expression());
			break;
		case BINARY:
			analyze(((BinaryExpression) expression).left());
			analyze(((BinaryExpression) expression).right());
			break;
		case BOUND_VARIABLE:
			// Might have to eventually do something with these?
			break;
		case DERIVATIVE:
			// TODO: Future examples might have assumptions about the
			// derivative.
			break;
		case ADDRESS_OF:
		case ARRAY_LITERAL:
		case BOOLEAN_LITERAL:
		case CAST:
		case COND:
		case DEREFERENCE:
		case DOT:
		case DYNAMIC_TYPE_OF:
		case INITIAL_VALUE:
		case INTEGER_LITERAL:
		case NULL_LITERAL:
		case REAL_LITERAL:
		case RESULT:
		case SELF:
		case SIZEOF_EXPRESSION:
		case SIZEOF_TYPE:
		case STRING_LITERAL:
		case STRUCT_LITERAL:
		case SUBSCRIPT:
		case UNARY:
		case UNDEFINED_PROC:
		case UNION_LITERAL:
		case VARIABLE:
		default:
			// These shouldn't matter for the analysis.
			break;
		}
	}

	private Fragment generateAssumptions(Scope scope) {
		Fragment newAssumptions = new CommonFragment();

		for (AbstractFunctionCallExpression call : calls) {
			newAssumptions = newAssumptions.combineWith(taylorExpansions(call,
					scope));
		}
		return newAssumptions;
	}

	private Fragment taylorExpansions(AbstractFunctionCallExpression call,
			Scope scope) {
		Fragment taylorExpansions = new CommonFragment();
		List<Expression> arguments = new LinkedList<Expression>(
				call.arguments());

		for (int i = 0; i < arguments.size(); i++) {
			if (matchesPattern(arguments.get(i))) {
				taylorExpansions = taylorExpansions.combineWith(expand(call, i,
						scope));
			}
		}

		return taylorExpansions;
	}

	private boolean matchesPattern(Expression expression) {
		switch (expression.expressionKind()) {
		case BINARY:
			switch (((BinaryExpression) expression).operator()) {
			case TIMES:
				// recognize it if of the form i*x or x*i, where i is a bound
				// variable.
				Expression left = ((BinaryExpression) expression).left();
				Expression right = ((BinaryExpression) expression).right();
				if (left.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return true;
				} else if (right.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return true;
				} else if ((left.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) left).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return true;
				} else if ((right.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) right).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return true;
				}
			default:
				return false;
			}
		default:
			return false;
		}
	}

	private Expression separatedExpression(Expression expression) {
		switch (expression.expressionKind()) {
		case BINARY:
			switch (((BinaryExpression) expression).operator()) {
			case TIMES:
				// recognize it if of the form i*x or x*i, where i is a bound
				// variable.
				Expression left = ((BinaryExpression) expression).left();
				Expression right = ((BinaryExpression) expression).right();
				if (left.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return right;
				} else if (right.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return left;
				} else if ((left.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) left).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return right;
				} else if ((right.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) right).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return left;
				}
			default:
				return null;
			}
		default:
			return null;
		}
	}

	private BoundVariableExpression boundVariable(Expression expression) {
		switch (expression.expressionKind()) {
		case BINARY:
			switch (((BinaryExpression) expression).operator()) {
			case TIMES:
				// recognize it if of the form i*x or x*i, where i is a bound
				// variable.
				Expression left = ((BinaryExpression) expression).left();
				Expression right = ((BinaryExpression) expression).right();
				if (left.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return (BoundVariableExpression) left;
				} else if (right.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return (BoundVariableExpression) right;
				} else if ((left.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) left).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return (BoundVariableExpression) ((CastExpression) left)
							.getExpression();
				} else if ((right.expressionKind() == ExpressionKind.CAST)
						&& ((CastExpression) right).getExpression()
								.expressionKind() == ExpressionKind.BOUND_VARIABLE) {
					return (BoundVariableExpression) ((CastExpression) right)
							.getExpression();
				}
			default:
				return null;
			}
		default:
			return null;
		}
	}

	private Fragment expand(AbstractFunctionCallExpression call, int arg,
			Scope scope) {
		AbstractFunction function = call.function();
		CIVLSource source = function.getSource();
		Fragment result = new CommonFragment();
		Expression originalArgument = call.arguments().get(arg);
		Expression separatedExpression = separatedExpression(originalArgument);
		BoundVariableExpression boundVariableExpression = boundVariable(originalArgument);
		CIVLType boundVariableType;

		for (QuantifiedExpression quant : quantifiedExpressions) {
			if (quant.boundVariableName()
					.equals(boundVariableExpression.name())) {
				Expression expansion0;
				Expression expansion1;

				boundVariableType = quant.boundVariableType();
				// This should usually (always?) be a forall
				assert quant.quantifier() == Quantifier.FORALL;
				// This should usually (always?) be an integer
				assert boundVariableType.isIntegerType();
				expansion0 = expansion(true, call, arg,
						boundVariableExpression.name(), boundVariableType,
						separatedExpression);
				expansion1 = expansion(false, call, arg,
						boundVariableExpression.name(), boundVariableType,
						separatedExpression);
				if (quant.isRange()) {
					result = factory.assumeFragment(source, factory.location(
							source, scope), factory.quantifiedExpression(
							source, quant.quantifier(),
							quant.boundVariableName(), boundVariableType,
							quant.lower(), quant.upper(), expansion0));
					result = result.combineWith(factory.assumeFragment(source,
							factory.location(source, scope), factory
									.quantifiedExpression(source,
											quant.quantifier(),
											quant.boundVariableName(),
											boundVariableType, quant.lower(),
											quant.upper(), expansion1)));
				} else {
					result = factory.assumeFragment(source, factory.location(
							source, scope), factory.quantifiedExpression(
							source, quant.quantifier(),
							quant.boundVariableName(), boundVariableType,
							quant.boundRestriction(), expansion0));
					result = result.combineWith(factory.assumeFragment(source,
							factory.location(source, scope), factory
									.quantifiedExpression(source,
											quant.quantifier(),
											quant.boundVariableName(),
											boundVariableType,
											quant.boundRestriction(),
											expansion1)));
				}
				break;
			}
		}
		result = result.combineWith(bigOFacts(source, separatedExpression,
				scope));
		return result;
	}

	/**
	 * Add big-O facts:
	 * 
	 * h*$O(h) == $O(h*h); 2*$O(h) == $O(h);
	 */
	private Fragment bigOFacts(CIVLSource source, Expression expression,
			Scope scope) {
		Fragment result;
		Expression bigOh = factory.unaryExpression(source,
				UNARY_OPERATOR.BIG_O, expression);
		Expression bigOhh = factory.unaryExpression(source,
				UNARY_OPERATOR.BIG_O, factory.binaryExpression(source,
						BINARY_OPERATOR.TIMES, expression, expression));

		// h*$O(h) == $O(h*h);
		result = factory.assumeFragment(source,
				factory.location(source, scope), factory.binaryExpression(
						source, BINARY_OPERATOR.EQUAL, factory
								.binaryExpression(source,
										BINARY_OPERATOR.TIMES, expression,
										bigOh), bigOhh));
		// 2*$O(h) == $O(h);
		result = result
				.combineWith(factory.assumeFragment(
						source,
						factory.location(source, scope),
						factory.binaryExpression(
								source,
								BINARY_OPERATOR.EQUAL,
								factory.binaryExpression(
										source,
										BINARY_OPERATOR.TIMES,
										factory.castExpression(source, factory
												.realType(), factory
												.integerLiteralExpression(
														source,
														BigInteger.valueOf(2))),
										bigOh), bigOh)));
		return result;
	}

	/** f((i+1)*x) = .... */
	private Expression expansion(boolean isPlus,
			AbstractFunctionCallExpression call, int arg,
			Identifier boundVariable, CIVLType boundVariableType,
			Expression separatedExpression) {
		AbstractFunction function = call.function();
		CIVLSource source = function.getSource();
		List<Expression> originalArguments = call.arguments();
		BoundVariableExpression boundVariableExpression = factory
				.boundVariableExpression(source, boundVariable,
						boundVariableType);
		Expression lhs;
		Expression rhs = null;
		List<Expression> lhsArguments;
		Variable partial = function.parameters().get(arg);
		BINARY_OPERATOR lhsOp;

		lhsArguments = new LinkedList<Expression>(originalArguments);
		if (isPlus) {
			lhsOp = BINARY_OPERATOR.PLUS;
		} else {
			lhsOp = BINARY_OPERATOR.MINUS;
		}
		// Make this f(...,(i+1)*x,...)
		lhsArguments
				.set(arg, factory.binaryExpression(source,
						BINARY_OPERATOR.TIMES, factory.castExpression(source,
								factory.realType(), factory.binaryExpression(
										source, lhsOp, boundVariableExpression,
										factory.integerLiteralExpression(
												source, BigInteger.ONE))),
						separatedExpression));
		lhs = factory.abstractFunctionCallExpression(source, function,
				lhsArguments);
		for (int i = 0; i < function.continuity(); i++) {
			if (i == 0) {
				rhs = call;
			} else {
				Expression derivative;
				Expression newTerm;
				BINARY_OPERATOR op;
				Expression numerator = multiple(source, separatedExpression, i);
				int denominator = factorial(i);
				List<Pair<Variable, IntegerLiteralExpression>> partials = new LinkedList<Pair<Variable, IntegerLiteralExpression>>();

				partials.add(new Pair<Variable, IntegerLiteralExpression>(
						partial, factory.integerLiteralExpression(source,
								BigInteger.valueOf(i))));
				derivative = factory.derivativeCallExpression(source, function,
						partials, originalArguments);
				newTerm = factory
						.binaryExpression(
								source,
								BINARY_OPERATOR.TIMES,
								derivative,
								factory.binaryExpression(
										source,
										BINARY_OPERATOR.DIVIDE,
										numerator,
										factory.castExpression(
												source,
												factory.realType(),
												factory.integerLiteralExpression(
														source,
														BigInteger
																.valueOf(denominator)))));
				if (!isPlus && i % 2 == 1) {
					op = BINARY_OPERATOR.MINUS;
				} else {
					op = BINARY_OPERATOR.PLUS;
				}
				rhs = factory.binaryExpression(source, op, rhs, newTerm);
			}
		}
		rhs = factory.binaryExpression(
				source,
				BINARY_OPERATOR.PLUS,
				rhs,
				factory.unaryExpression(
						source,
						UNARY_OPERATOR.BIG_O,
						multiple(source, separatedExpression,
								function.continuity())));
		return factory
				.binaryExpression(source, BINARY_OPERATOR.EQUAL, lhs, rhs);
	}

	private int factorial(int i) {
		assert i >= 0;
		if (i == 1) {
			return 1;
		} else if (i == 0) {
			return 1;
		}
		return i * factorial(i - 1);
	}

	private Expression multiple(CIVLSource source, Expression operand, int times) {
		assert times > 0;
		if (times == 1) {
			return operand;
		}
		return factory.binaryExpression(source, BINARY_OPERATOR.TIMES, operand,
				multiple(source, operand, times - 1));
	}
}
