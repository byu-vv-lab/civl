/**
 * 
 */
package edu.udel.cis.vsl.civl.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import edu.udel.cis.vsl.civl.model.expression.AnyExpression;
import edu.udel.cis.vsl.civl.model.expression.ArrayIndexExpression;
import edu.udel.cis.vsl.civl.model.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.RealLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.SelfExpression;
import edu.udel.cis.vsl.civl.model.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.location.Location;
import edu.udel.cis.vsl.civl.model.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.statement.CallStatement;
import edu.udel.cis.vsl.civl.model.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.statement.ForkStatement;
import edu.udel.cis.vsl.civl.model.statement.JoinStatement;
import edu.udel.cis.vsl.civl.model.statement.NoopStatement;
import edu.udel.cis.vsl.civl.model.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.type.ArrayType;
import edu.udel.cis.vsl.civl.model.type.PrimitiveType;
import edu.udel.cis.vsl.civl.model.type.PrimitiveType.PRIMITIVE_TYPE;
import edu.udel.cis.vsl.civl.model.type.ProcessType;
import edu.udel.cis.vsl.civl.model.type.Type;
import edu.udel.cis.vsl.civl.model.variable.Variable;

/**
 * The factory to create all model components. Usually this is the only way
 * model components will be created.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class ModelFactory {

	/* Keep a unique number to identify locations. */
	private int locationID = 0;
	/* Keep a set of used identifiers for fly-weighting purposes. */
	private Map<String, Identifier> identifiers;
	/* Make one of each primitive type. */
	private PrimitiveType integerType = new PrimitiveType(PRIMITIVE_TYPE.INT);
	private PrimitiveType booleanType = new PrimitiveType(PRIMITIVE_TYPE.BOOL);
	private PrimitiveType realType = new PrimitiveType(PRIMITIVE_TYPE.REAL);
	private PrimitiveType stringType = new PrimitiveType(PRIMITIVE_TYPE.STRING);
	private ProcessType processType = new ProcessType();
	/* Make one canonical true and false. */
	private BooleanLiteralExpression trueExpression = new BooleanLiteralExpression(
			true);
	private BooleanLiteralExpression falseExpression = new BooleanLiteralExpression(
			false);
	/* Keep a unique number to identify scopes. */
	private int scopeID = 0;
	private int chooseID = 0;

	/**
	 * The factory to create all model components. Usually this is the only way
	 * model components will be created.
	 */
	public ModelFactory() {
		identifiers = new HashMap<String, Identifier>();
	}

	/**
	 * Create a new model.
	 * 
	 * @param system
	 *            The designated outermost function, called "System."
	 */
	public Model model(Function system) {
		return new Model(this, system);
	}

	/**
	 * Create a new scope.
	 * 
	 * @param parent
	 *            The containing scope of this scope. Only null for the
	 *            outermost scope of the designated "System" function.
	 * @param variables
	 *            The set of variables in this scope.
	 * @param function
	 *            The function containing this scope.
	 * @return A new scope
	 */
	public Scope scope(Scope parent, Set<Variable> variables, Function function) {
		Scope newScope = new Scope(parent, variables, scopeID++);
		if (parent != null) {
			parent.addChild(newScope);
		}
		newScope.setFunction(function);
		return newScope;
	}

	/**
	 * Get an identifier with the given name.
	 * 
	 * @param name
	 *            The name of this identifier.
	 */
	public Identifier identifier(String name) {
		if (!identifiers.containsKey(name)) {
			identifiers.put(name, new Identifier(name));
		}
		return identifiers.get(name);
	}

	/**
	 * Create a new variable.
	 * 
	 * @param type
	 *            The type of this variable.
	 * @param name
	 *            The name of this variable.
	 * @param vid
	 *            The index of this variable in its scope.
	 */
	public Variable variable(Type type, Identifier name, int vid) {
		return new Variable(type, name, vid);
	}

	/**
	 * Create a new function.
	 * 
	 * @param name
	 *            The name of this function.
	 * @param parameters
	 *            The list of parameters.
	 * @param returnType
	 *            The return type of this function.
	 * @param containingScope
	 *            The scope containing this function.
	 * @param startLocation
	 *            The first location in the function.
	 * @return The new function.
	 */
	public Function function(Identifier name, Vector<Variable> parameters,
			Type returnType, Scope containingScope, Location startLocation) {
		return new Function(name, parameters, returnType, containingScope,
				startLocation, this);
	}

	/**
	 * Create a new location.
	 * 
	 * @param scope
	 *            The scope containing this location.
	 * @return The new location.
	 */
	public Location location(Scope scope) {
		return new Location(scope, locationID++);
	}

	/* *********************************************************************
	 * Types
	 * *********************************************************************
	 */

	/**
	 * Get the integer primitive type.
	 * 
	 * @return The integer primitive type.
	 */
	public PrimitiveType integerType() {
		return integerType;
	}

	/**
	 * Get the real primitive type.
	 * 
	 * @return The real primitive type.
	 */
	public PrimitiveType realType() {
		return realType;
	}

	/**
	 * Get the boolean primitive type.
	 * 
	 * @return The boolean primitive type.
	 */
	public PrimitiveType booleanType() {
		return booleanType;
	}

	/**
	 * Get the string primitive type.
	 * 
	 * @return The string primitive type.
	 */
	public PrimitiveType stringType() {
		return stringType;
	}

	/**
	 * Get the process type.
	 * 
	 * @return The process type.
	 */
	public ProcessType processType() {
		return processType;
	}

	/**
	 * Get a new array type.
	 * 
	 * @param baseType
	 *            The type of each element in the array.
	 * @return A new array type with the given base type.
	 */
	public ArrayType arrayType(Type baseType) {
		return new ArrayType(baseType);
	}

	/* *********************************************************************
	 * Expressions
	 * *********************************************************************
	 */

	/**
	 * An any(tag) expression.
	 * 
	 * @param tag
	 *            Expression for the place where the tag should be stored.
	 * @return The any expression.
	 */
	public AnyExpression anyExpression(Expression tag) {
		return new AnyExpression(tag);
	}

	/**
	 * A unary expression. One of {-,!}.
	 * 
	 * @param operator
	 *            The unary operator.
	 * @param operand
	 *            The expression to which the operator is applied.
	 * @return The unary expression.
	 */
	public UnaryExpression unaryExpression(UNARY_OPERATOR operator,
			Expression operand) {
		UnaryExpression result = new UnaryExpression(operator, operand);

		result.setExpressionScope(operand.expressionScope());
		return result;
	}

	/**
	 * A binary expression. One of {+,-,*,\,<,<=,==,!=,&&,||,%}
	 * 
	 * @param operator
	 *            The binary operator.
	 * @param left
	 *            The left operand.
	 * @param right
	 *            The right operand.
	 * @return The binary expression.
	 */
	public BinaryExpression binaryExpression(BINARY_OPERATOR operator,
			Expression left, Expression right) {
		BinaryExpression result = new BinaryExpression(operator, left, right);

		result.setExpressionScope(join(left.expressionScope(),
				right.expressionScope()));
		return result;
	}

	/**
	 * A boolean literal expression.
	 * 
	 * @param value
	 *            True or false.
	 * @return The boolean literal expression.
	 */
	public BooleanLiteralExpression booleanLiteralExpression(boolean value) {
		if (value) {
			return trueExpression;
		} else {
			return falseExpression;
		}
	}

	/**
	 * An integer literal expression.
	 * 
	 * @param value
	 *            The (arbitrary precision) integer value.
	 * @return The integer literal expression.
	 */
	public IntegerLiteralExpression integerLiteralExpression(BigInteger value) {
		return new IntegerLiteralExpression(value);
	}

	/**
	 * A real literal expression.
	 * 
	 * @param value
	 *            The (arbitrary precision) real value.
	 * @return The real literal expression.
	 */
	public RealLiteralExpression realLiteralExpression(BigDecimal value) {
		return new RealLiteralExpression(value);
	}

	/**
	 * A string literal expression.
	 * 
	 * @param value
	 *            The string.
	 * @return The string literal expression.
	 */
	public StringLiteralExpression stringLiteralExpression(String value) {
		return new StringLiteralExpression(value);
	}

	/**
	 * An expression for an array index operation. e.g. a[i]
	 * 
	 * @param array
	 *            An expression evaluating to an array.
	 * @param index
	 *            An expression evaluating to an integer.
	 * @return The array index expression.
	 */
	public ArrayIndexExpression arrayIndexExpression(Expression array,
			Expression index) {
		ArrayIndexExpression result = new ArrayIndexExpression(array, index);

		result.setExpressionScope(join(array.expressionScope(),
				index.expressionScope()));
		return result;
	}

	/**
	 * A self expression. Used to referenced the current process.
	 * 
	 * @return A new self expression.
	 */
	public SelfExpression selfExpression() {
		return new SelfExpression();
	}

	/**
	 * A variable expression.
	 * 
	 * @param variable
	 *            The variable being referenced.
	 * @return The variable expression.
	 */
	public VariableExpression variableExpression(Variable variable) {
		VariableExpression result = new VariableExpression(variable);

		// Don't need to worry about the expression scope of constants.
		if (!variable.isConst()) {
			result.setExpressionScope(variable.scope());
		}
		return result;
	}

	/* *********************************************************************
	 * Statements
	 * *********************************************************************
	 */

	/**
	 * An assignment statement.
	 * 
	 * @param source
	 *            The source location for this statement.
	 * @param lhs
	 *            The left hand side of the assignment.
	 * @param rhs
	 *            The right hand side of the assignment.
	 * @return A new assignment statement.
	 */
	public AssignStatement assignStatement(Location source, Expression lhs,
			Expression rhs) {
		AssignStatement result = new AssignStatement(source, lhs, rhs);

		result.setStatementScope(join(lhs.expressionScope(),
				rhs.expressionScope()));
		return result;
	}

	/**
	 * A function call.
	 * 
	 * @param source
	 *            The source location for this call statement.
	 * @param function
	 *            The function.
	 * @param arguments
	 *            The arguments to the function.
	 * @return A new call statement.
	 */
	public CallStatement callStatement(Location source, Function function,
			Vector<Expression> arguments) {
		CallStatement result = new CallStatement(source, function, arguments);
		Scope statementScope = null;

		for (Expression arg : arguments) {
			statementScope = join(statementScope, arg.expressionScope());
		}
		result.setStatementScope(statementScope);
		return result;
	}

	/**
	 * A choose statement is of the form x = choose(n);
	 * 
	 * When a choose statement is executed, the left hand side will be assigned
	 * a new symbolic constant. A bound on the values of that symbolic constant
	 * will be added to the path condition.
	 * 
	 * @param source
	 *            The source location for this statement.
	 * @param lhs
	 *            The left hand side of the choose statement.
	 * @param argument
	 *            The argument to choose().
	 * @return A new choose statement.
	 */
	public ChooseStatement chooseStatement(Location source, Expression lhs,
			Expression argument) {
		ChooseStatement result = new ChooseStatement(source, lhs, argument,
				chooseID++);

		result.setStatementScope(join(lhs.expressionScope(),
				argument.expressionScope()));
		return result;
	}

	/**
	 * A fork statement. Used to spawn a new process.
	 * 
	 * @param source
	 *            The source location for this fork statement.
	 * @param function
	 *            An expression evaluating to a function.
	 * @param arguments
	 *            The arguments to the function.
	 * @return A new fork statement.
	 */
	public ForkStatement forkStatement(Location source, Expression function,
			Vector<Expression> arguments) {
		ForkStatement result = new ForkStatement(source, null, function,
				arguments);
		Scope statementScope = null;

		for (Expression arg : arguments) {
			statementScope = join(statementScope, arg.expressionScope());
		}
		result.setStatementScope(statementScope);
		return result;
	}

	/**
	 * A fork statement. Used to spawn a new process.
	 * 
	 * @param source
	 *            The source location for this fork statement.
	 * @param lhs
	 *            Expression for place where the process reference will be
	 *            stored. Null if non-existent.
	 * @param function
	 *            An expression evaluating to a function.
	 * @param arguments
	 *            The arguments ot the function.
	 * @return A new fork statement.
	 */
	public ForkStatement forkStatement(Location source, Expression lhs,
			Expression function, Vector<Expression> arguments) {
		ForkStatement result = new ForkStatement(source, lhs, function,
				arguments);
		Scope statementScope = lhs.expressionScope();

		for (Expression arg : arguments) {
			statementScope = join(statementScope, arg.expressionScope());
		}
		result.setStatementScope(statementScope);
		return result;
	}

	/**
	 * A join statement. Used to wait for a process to complete.
	 * 
	 * @param source
	 *            The source location for this join statement.
	 * @param process
	 *            An expression evaluating to a process.
	 * @return A new join statement.
	 */
	public JoinStatement joinStatement(Location source, Expression process) {
		JoinStatement result = new JoinStatement(source, process);

		result.setStatementScope(process.expressionScope());
		return result;
	}

	/**
	 * A noop statement.
	 * 
	 * @param source
	 *            The source location for this noop statement.
	 * @return A new noop statement.
	 */
	public NoopStatement noopStatement(Location source) {
		return new NoopStatement(source);
	}

	/**
	 * A return statement.
	 * 
	 * @param source
	 *            The source location for this return statement.
	 * @param expression
	 *            The expression being returned. Null if non-existent.
	 * @return A new return statement.
	 */
	public ReturnStatement returnStatement(Location source,
			Expression expression) {
		ReturnStatement result = new ReturnStatement(source, expression);

		if (expression != null) {
			result.setStatementScope(expression.expressionScope());
		}
		return result;
	}

	/**
	 * @param s0
	 *            A scope. May be null.
	 * @param s1
	 *            A scope. May be null.
	 * @return The scope that is the join, or least common ancestor in the scope
	 *         tree, of s0 and s1. Null if both are null. If exactly one of s0
	 *         and s1 are null, returns the non-null scope.
	 */
	private Scope join(Scope s0, Scope s1) {
		Vector<Scope> s0Ancestors = new Vector<Scope>();
		Scope s0Ancestor = s0;
		Scope s1Ancestor = s1;

		if (s0 == null) {
			return s1;
		} else if (s1 == null) {
			return s0;
		}
		s0Ancestors.add(s0Ancestor);
		while (s0Ancestor.parent() != null) {
			s0Ancestor = s0Ancestor.parent();
			s0Ancestors.add(s0Ancestor);
		}
		while (true) {
			if (s0Ancestors.contains(s1Ancestor)) {
				return s1Ancestor;
			}
			s1Ancestor = s1Ancestor.parent();
		}
	}

}
