/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Label;
import edu.udel.cis.vsl.abc.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ResultNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SelfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SpawnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssertNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.DeclarationListNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.GotoNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.NullStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WaitNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.BasicTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.model.IF.Function;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelBuilder;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.ArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.Type;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

/**
 * Class to provide translation from an AST to a model.
 * 
 * @author zirkeltk
 * 
 */
public class CommonModelBuilder implements ModelBuilder {

	private ModelFactory factory;
	private Vector<FunctionDefinitionNode> unprocessedFunctions;
	private Map<FunctionDefinitionNode, Scope> containingScopes;
	private Map<CallStatement, FunctionDefinitionNode> callStatements;
	private Map<FunctionDefinitionNode, Function> functionMap;
	private Map<LabelNode, Statement> labeledStatements;
	private Map<Statement, LabelNode> gotoStatements;

	/**
	 * The model builder translates the AST into a CIVL model.
	 */
	public CommonModelBuilder() {
		factory = new CommonModelFactory();
	}

	/**
	 * @return The model factory used by this model builder.
	 */
	public ModelFactory factory() {
		return factory;
	}

	/**
	 * Build the model.
	 * 
	 * @param unit
	 *            The translation unit for the AST.
	 * @return The model.
	 */
	public Model buildModel(TranslationUnit unit) {
		Model model;
		Identifier systemID = factory.identifier("_CIVL_system");
		Function system = factory.function(systemID, new Vector<Variable>(),
				null, null, null);
		ASTNode rootNode = unit.getRootNode();
		Location returnLocation;
		Statement returnStatement;
		FunctionDefinitionNode mainFunction = null;
		Statement mainBody;
		Vector<Statement> initializations = new Vector<Statement>();

		containingScopes = new LinkedHashMap<FunctionDefinitionNode, Scope>();
		callStatements = new LinkedHashMap<CallStatement, FunctionDefinitionNode>();
		functionMap = new LinkedHashMap<FunctionDefinitionNode, Function>();
		unprocessedFunctions = new Vector<FunctionDefinitionNode>();
		for (int i = 0; i < rootNode.numChildren(); i++) {
			ASTNode node = rootNode.child(i);

			if (node instanceof VariableDeclarationNode) {
				InitializerNode init = ((VariableDeclarationNode) node)
						.getInitializer();

				processVariableDeclaration(system.outerScope(),
						(VariableDeclarationNode) rootNode.child(i));
				if (init != null) {
					Expression left;
					Expression right;
					Location location = factory.location(system.outerScope());

					left = factory
							.variableExpression(system
									.outerScope()
									.getVariable(
											system.outerScope().numVariables() - 1));
					right = expression((ExpressionNode) init,
							system.outerScope());
					if (!initializations.isEmpty()) {
						initializations.lastElement().setTarget(location);
					}
					initializations.add(factory.assignStatement(location, left,
							right));
					system.addLocation(location);
					system.addStatement(initializations.lastElement());
				}
			} else if (node instanceof FunctionDefinitionNode) {
				if (((FunctionDefinitionNode) node).getName().equals("main")) {
					mainFunction = (FunctionDefinitionNode) node;
				} else {
					unprocessedFunctions.add((FunctionDefinitionNode) node);
					containingScopes.put((FunctionDefinitionNode) node,
							system.outerScope());
				}
			} else if (node instanceof FunctionDeclarationNode) {
				// Do we need to keep track of these for any reason?
			} else {
				throw new RuntimeException("Unsupported declaration type: "
						+ node);
			}
		}
		if (mainFunction == null) {
			throw new RuntimeException("Program must have a main function.");
		}
		labeledStatements = new LinkedHashMap<LabelNode, Statement>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		if (!initializations.isEmpty()) {
			system.setStartLocation(initializations.firstElement().source());
			mainBody = statement(system, initializations.lastElement(),
					mainFunction.getBody(), system.outerScope());
		} else {
			mainBody = statement(system, null, mainFunction.getBody(),
					system.outerScope());
		}
		if (!(mainBody instanceof ReturnStatement)) {
			returnLocation = factory.location(system.outerScope());
			returnStatement = factory.returnStatement(returnLocation, null);
			if (mainBody != null) {
				mainBody.setTarget(returnLocation);
			} else {
				system.setStartLocation(returnLocation);
			}
			system.addLocation(returnLocation);
			system.addStatement(returnStatement);
		}
		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledStatements.get(gotoStatements.get(s)).source());
		}
		model = factory.model(system);
		while (!unprocessedFunctions.isEmpty()) {
			FunctionDefinitionNode functionDefinition = unprocessedFunctions
					.remove(0);
			Function newFunction = processFunction(functionDefinition,
					containingScopes.get(functionDefinition));
			SequenceNode<ContractNode> contract = functionDefinition
					.getContract();
			Expression precondition = null;
			Expression postcondition = null;

			if (contract != null) {
				for (int i = 0; i < contract.numChildren(); i++) {
					ContractNode contractComponent = contract
							.getSequenceChild(i);
					Expression componentExpression;

					if (contractComponent instanceof EnsuresNode) {
						componentExpression = expression(
								((EnsuresNode) contractComponent)
										.getExpression(),
								newFunction.outerScope());
						if (postcondition == null) {
							postcondition = componentExpression;
						} else {
							postcondition = factory.binaryExpression(
									BINARY_OPERATOR.AND, postcondition,
									componentExpression);
						}
					} else {
						componentExpression = expression(
								((RequiresNode) contractComponent)
										.getExpression(),
								newFunction.outerScope());
						if (precondition == null) {
							precondition = componentExpression;
						} else {
							precondition = factory.binaryExpression(
									BINARY_OPERATOR.AND, precondition,
									componentExpression);
						}
					}
				}
			}
			if (precondition != null) {
				newFunction.setPrecondition(precondition);
			}
			if (postcondition != null) {
				newFunction.setPostcondition(postcondition);
			}
			model.addFunction(newFunction);
			functionMap.put(functionDefinition, newFunction);
		}
		for (CallStatement statement : callStatements.keySet()) {
			statement
					.setFunction(functionMap.get(callStatements.get(statement)));
		}
		return model;
	}

	private Function processFunction(FunctionDefinitionNode functionNode,
			Scope scope) {
		Function result;
		Identifier name = factory.identifier(functionNode.getName());
		Vector<Variable> parameters = new Vector<Variable>();
		FunctionTypeNode functionTypeNode = functionNode.getTypeNode();
		Type returnType = processType(functionTypeNode.getReturnType());
		Statement body;

		labeledStatements = new LinkedHashMap<LabelNode, Statement>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		for (int i = 0; i < functionTypeNode.getParameters().numChildren(); i++) {
			Type type = processType(functionTypeNode.getParameters()
					.getSequenceChild(i).getTypeNode());
			Identifier variableName = factory.identifier(functionTypeNode
					.getParameters().getSequenceChild(i).getName());

			parameters.add(factory.variable(type, variableName,
					parameters.size()));
		}
		result = factory.function(name, parameters, returnType, scope, null);
		body = statement(result, null, functionNode.getBody(),
				result.outerScope());
		if (!(body instanceof ReturnStatement)) {
			Location returnLocation = factory.location(result.outerScope());
			ReturnStatement returnStatement = factory.returnStatement(
					returnLocation, null);

			body.setTarget(returnLocation);
			result.addLocation(returnLocation);
			result.addStatement(returnStatement);
		}
		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledStatements.get(gotoStatements.get(s)).source());
		}
		return result;
	}

	private void processVariableDeclaration(Scope scope,
			VariableDeclarationNode node) {
		Type type = processType(node.getTypeNode());
		Identifier name = factory.identifier(node.getName());
		Variable variable = factory.variable(type, name, scope.numVariables());

		if (type instanceof ArrayType) {
			ExpressionNode extentNode = ((ArrayTypeNode) node.getTypeNode())
					.getExtent();
			Expression extent;

			if (extentNode != null) {
				extent = expression(extentNode, scope);
				variable.setExtent(extent);
			}
		}
		scope.addVariable(variable);
	}

	private Type processType(TypeNode typeNode) {
		Type result = null;

		// TODO: deal with more types.
		if (typeNode.kind() == TypeNodeKind.BASIC) {
			switch (((BasicTypeNode) typeNode).getBasicTypeKind()) {
			case SHORT:
			case UNSIGNED_SHORT:
			case INT:
			case UNSIGNED:
			case LONG:
			case UNSIGNED_LONG:
			case LONG_LONG:
			case UNSIGNED_LONG_LONG:
				return factory.integerType();
			case FLOAT:
			case DOUBLE:
			case LONG_DOUBLE:
				return factory.realType();
			case BOOL:
				return factory.booleanType();
			case CHAR:
				break;
			case DOUBLE_COMPLEX:
				break;
			case FLOAT_COMPLEX:
				break;
			case LONG_DOUBLE_COMPLEX:
				break;
			case SIGNED_CHAR:
				break;
			case UNSIGNED_CHAR:
				break;
			default:
				break;
			}
		} else if (typeNode.kind() == TypeNodeKind.PROCESS) {
			return factory.processType();
		} else if (typeNode.kind() == TypeNodeKind.ARRAY) {
			return factory.arrayType(processType(((ArrayTypeNode) typeNode)
					.getElementType()));
		}
		return result;
	}

	/* *********************************************************************
	 * Expressions
	 * *********************************************************************
	 */

	/**
	 * Translate an expression from the CIVL AST to the CIVL model.
	 * 
	 * @param expression
	 *            The expression being translated.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	public Expression expression(ExpressionNode expression, Scope scope) {
		Expression result = null;

		if (expression instanceof OperatorNode) {
			result = operator((OperatorNode) expression, scope);
		} else if (expression instanceof IdentifierExpressionNode) {
			result = variableExpression((IdentifierExpressionNode) expression,
					scope);
		} else if (expression instanceof ConstantNode) {
			result = constant((ConstantNode) expression);
		} else if (expression instanceof ResultNode) {
			result = factory.resultExpression();
		} else if (expression instanceof SelfNode) {
			result = factory.selfExpression();
		}
		return result;
	}

	/**
	 * Translate an operator expression from the CIVL AST to the CIVL model.
	 * 
	 * @param expression
	 *            The operator expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	public Expression operator(OperatorNode expression, Scope scope) {
		int numArgs = expression.getNumberOfArguments();
		List<Expression> arguments = new Vector<Expression>();
		Expression result = null;

		for (int i = 0; i < numArgs; i++) {
			arguments.add(expression(expression.getArgument(i), scope));
		}
		// TODO: Bitwise ops, =, {%,/,*,+,-}=, pointer ops, comma, ?
		if (numArgs < 1 || numArgs > 3) {
			throw new RuntimeException("Unsupported number of arguments: "
					+ numArgs + " in expression " + expression);
		}
		switch (expression.getOperator()) {
		case DIV:
			result = factory.binaryExpression(BINARY_OPERATOR.DIVIDE,
					arguments.get(0), arguments.get(1));
			break;
		case EQUALS:
			result = factory.binaryExpression(BINARY_OPERATOR.EQUAL,
					arguments.get(0), arguments.get(1));
			break;
		case GT:
			result = factory.binaryExpression(BINARY_OPERATOR.LESS_THAN,
					arguments.get(1), arguments.get(0));
			break;
		case GTE:
			result = factory.binaryExpression(BINARY_OPERATOR.LESS_THAN_EQUAL,
					arguments.get(1), arguments.get(0));
			break;
		case LAND:
			result = factory.binaryExpression(BINARY_OPERATOR.AND,
					arguments.get(0), arguments.get(1));
			break;
		case LOR:
			result = factory.binaryExpression(BINARY_OPERATOR.OR,
					arguments.get(0), arguments.get(1));
			break;
		case LT:
			result = factory.binaryExpression(BINARY_OPERATOR.LESS_THAN,
					arguments.get(0), arguments.get(1));
			break;
		case LTE:
			result = factory.binaryExpression(BINARY_OPERATOR.LESS_THAN_EQUAL,
					arguments.get(0), arguments.get(1));
			break;
		case MINUS:
			result = factory.binaryExpression(BINARY_OPERATOR.MINUS,
					arguments.get(0), arguments.get(1));
			break;
		case MOD:
			result = factory.binaryExpression(BINARY_OPERATOR.MODULO,
					arguments.get(0), arguments.get(1));
			break;
		case NEQ:
			result = factory.binaryExpression(BINARY_OPERATOR.NOT_EQUAL,
					arguments.get(0), arguments.get(1));
			break;
		case NOT:
			result = factory.unaryExpression(UNARY_OPERATOR.NOT,
					arguments.get(0));
			break;
		case PLUS:
			result = factory.binaryExpression(BINARY_OPERATOR.PLUS,
					arguments.get(0), arguments.get(1));
			break;
		case SUBSCRIPT:
			result = factory.arrayIndexExpression(arguments.get(0),
					arguments.get(1));
			break;
		case TIMES:
			result = factory.binaryExpression(BINARY_OPERATOR.TIMES,
					arguments.get(0), arguments.get(1));
			break;
		case UNARYMINUS:
			result = factory.unaryExpression(UNARY_OPERATOR.NEGATIVE,
					arguments.get(0));
			break;
		case UNARYPLUS:
			result = arguments.get(0);
			break;
		default:
			throw new RuntimeException("Unsupported operator: "
					+ expression.getOperator() + " in expression " + expression);
		}
		return result;
	}

	private VariableExpression variableExpression(
			IdentifierExpressionNode identifier, Scope scope) {
		VariableExpression result = null;
		Identifier name = factory.identifier(identifier.getIdentifier().name());

		if (scope.variable(name) == null) {
			throw new RuntimeException("No such variable "
					+ identifier.getSource());
		}
		result = factory.variableExpression(scope.variable(name));
		return result;
	}

	private LiteralExpression constant(ConstantNode constant) {
		LiteralExpression result = null;

		if (constant instanceof IntegerConstantNode) {
			result = factory
					.integerLiteralExpression(((IntegerConstantNode) constant)
							.getConstantValue().getIntegerValue());
		} else if (constant instanceof StringLiteralNode) {
			result = factory
					.stringLiteralExpression(((StringLiteralNode) constant)
							.getStringRepresentation());
		} else if (constant instanceof FloatingConstantNode) {
			result = factory.realLiteralExpression(BigDecimal
					.valueOf(((FloatingConstantNode) constant)
							.getConstantValue().getDoubleValue()));
		}
		return result;
	}

	/* *********************************************************************
	 * Statements
	 * *********************************************************************
	 */

	/**
	 * Takes a statement node and returns the appropriate model statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param statement
	 *            The statement node.
	 * @param scope
	 *            The scope containing this statement.
	 * @return The model representation of this statement.
	 */
	private Statement statement(Function function, Statement lastStatement,
			StatementNode statement, Scope scope) {
		Statement result = null;

		if (statement instanceof AssumeNode) {
			result = assume(function, lastStatement, (AssumeNode) statement,
					scope);
		} else if (statement instanceof AssertNode) {
			result = assertStatement(function, lastStatement,
					(AssertNode) statement, scope);
		} else if (statement instanceof ExpressionStatementNode) {
			result = expressionStatement(function, lastStatement,
					(ExpressionStatementNode) statement, scope);
		} else if (statement instanceof CompoundStatementNode) {
			result = compoundStatement(function, lastStatement,
					(CompoundStatementNode) statement, scope);
		} else if (statement instanceof ForLoopNode) {
			result = forLoop(function, lastStatement, (ForLoopNode) statement,
					scope);
		} else if (statement instanceof WaitNode) {
			result = wait(function, lastStatement, (WaitNode) statement, scope);
		} else if (statement instanceof NullStatementNode) {
			result = noop(function, lastStatement,
					(NullStatementNode) statement, scope);
		} else if (statement instanceof WhenNode) {
			result = when(function, lastStatement, (WhenNode) statement, scope);
		} else if (statement instanceof ChooseStatementNode) {
			result = choose(function, lastStatement,
					(ChooseStatementNode) statement, scope);
		} else if (statement instanceof GotoNode) {
			result = gotoStatement(function, lastStatement,
					(GotoNode) statement, scope);
		} else if (statement instanceof LabeledStatementNode) {
			result = labeledStatement(function, lastStatement,
					(LabeledStatementNode) statement, scope);
		} else if (statement instanceof ReturnNode) {
			result = returnStatement(function, lastStatement,
					(ReturnNode) statement, scope);
		}
		function.addStatement(result);
		return result;
	}

	/**
	 * An assume statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param statement
	 *            The statement node.
	 * @param scope
	 *            The scope containing this statement.
	 * @return The model representation of this statement.
	 */
	private Statement assume(Function function, Statement lastStatement,
			AssumeNode statement, Scope scope) {
		Statement result;
		Expression expression = expression(statement.getExpression(), scope);
		Location location = factory.location(scope);

		result = factory.assumeStatement(location, expression);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	/**
	 * An assert statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param statement
	 *            The statement node.
	 * @param scope
	 *            The scope containing this statement.
	 * @return The model representation of this statement.
	 */
	private Statement assertStatement(Function function,
			Statement lastStatement, AssertNode statement, Scope scope) {
		Statement result;
		Expression expression = expression(statement.getExpression(), scope);
		Location location = factory.location(scope);

		function.addLocation(location);
		result = factory.assertStatement(location, expression);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	/**
	 * Takes an expression statement and converts it to a model representation
	 * of that statement. Currently supported expressions for expression
	 * statements are spawn, assign, function call, increment, decrement. Any
	 * other expressions have no side effects and thus result in a no-op.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param statement
	 *            The statement node.
	 * @param scope
	 *            The scope containing this statement.
	 * @return The model representation of this statement.
	 */
	private Statement expressionStatement(Function function,
			Statement lastStatement, ExpressionStatementNode statement,
			Scope scope) {
		Statement result = null;
		Location location = factory.location(scope);

		function.addLocation(location);
		if (statement.getExpression() instanceof OperatorNode) {
			OperatorNode expression = (OperatorNode) statement.getExpression();
			switch (expression.getOperator()) {
			case ASSIGN:
				result = assign(location, expression.getArgument(0),
						expression.getArgument(1), scope);
				break;
			case POSTINCREMENT:
			case PREINCREMENT:
				Expression incrementVariable = expression(
						expression.getArgument(0), scope);

				result = factory
						.assignStatement(
								location,
								incrementVariable,
								factory.binaryExpression(
										BINARY_OPERATOR.PLUS,
										incrementVariable,
										factory.integerLiteralExpression(BigInteger.ONE)));
				break;
			case POSTDECREMENT:
			case PREDECREMENT:
				Expression decrementVariable = expression(
						expression.getArgument(0), scope);

				result = factory
						.assignStatement(
								location,
								decrementVariable,
								factory.binaryExpression(
										BINARY_OPERATOR.PLUS,
										decrementVariable,
										factory.integerLiteralExpression(BigInteger.ONE)));
				break;
			default:
				result = factory.noopStatement(location);
			}
		} else if (statement.getExpression() instanceof SpawnNode) {
			FunctionCallNode call = ((SpawnNode) statement.getExpression())
					.getCall();
			Expression name = factory
					.stringLiteralExpression(((IdentifierExpressionNode) call
							.getFunction()).getIdentifier().name());
			Vector<Expression> arguments = new Vector<Expression>();

			for (int i = 0; i < call.getNumberOfArguments(); i++) {
				arguments.add(expression(call.getArgument(i), scope));
			}
			result = factory.forkStatement(location, name, arguments);
		} else if (statement.getExpression() instanceof FunctionCallNode) {
			Vector<Expression> arguments = new Vector<Expression>();
			ExpressionNode functionExpression = ((FunctionCallNode) statement
					.getExpression()).getFunction();
			FunctionDefinitionNode functionDefinition = null;

			if (functionExpression instanceof IdentifierExpressionNode) {
				OrdinaryEntity functionEntity = functionExpression.getScope()
						.getLexicalOrdinaryEntity(
								((IdentifierExpressionNode) functionExpression)
										.getIdentifier().name());

				if (functionEntity instanceof edu.udel.cis.vsl.abc.ast.entity.IF.Function) {
					functionDefinition = ((edu.udel.cis.vsl.abc.ast.entity.IF.Function) functionEntity)
							.getDefinition();
				} else {
					// TODO: handle this.
				}
			} else {
				// TODO: handle this. Need to get the entity.
			}
			for (int i = 0; i < ((FunctionCallNode) statement.getExpression())
					.getNumberOfArguments(); i++) {
				arguments.add(expression(((FunctionCallNode) statement
						.getExpression()).getArgument(i), scope));
			}
			result = factory.callStatement(location, null, arguments);
			callStatements.put((CallStatement) result, functionDefinition);
		}
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	/**
	 * Sometimes an assignment is actually modeled as a fork or function call
	 * with an optional left hand side argument. Catch these cases.
	 * 
	 * @param location
	 *            The start location for this assign.
	 * @param lhs
	 *            AST expression for the left hand side of the assignment.
	 * @param rhs
	 *            AST expression for the right hand side of the assignment.
	 * @param scope
	 *            The scope containing this assignment.
	 * @return The model representation of the assignment, which might also be a
	 *         fork statement or function call.
	 */
	private Statement assign(Location location, ExpressionNode lhs,
			ExpressionNode rhs, Scope scope) {
		Expression lhsExpression = expression(lhs, scope);

		return assign(location, lhsExpression, rhs, scope);
	}

	/**
	 * Sometimes an assignment is actually modeled as a fork or function call
	 * with an optional left hand side argument. Catch these cases.
	 * 
	 * @param location
	 *            The start location for this assign.
	 * @param lhs
	 *            Model expression for the left hand side of the assignment.
	 * @param rhs
	 *            AST expression for the right hand side of the assignment.
	 * @param scope
	 *            The scope containing this assignment.
	 * @return The model representation of the assignment, which might also be a
	 *         fork statement or function call.
	 */
	private Statement assign(Location location, Expression lhs,
			ExpressionNode rhs, Scope scope) {
		Statement result = null;

		if (rhs instanceof FunctionCallNode) {
			FunctionDefinitionNode definition = findFunctionDefinition(((FunctionCallNode) rhs)
					.getFunction());
			Vector<Expression> arguments = new Vector<Expression>();

			for (int i = 0; i < ((FunctionCallNode) rhs).getNumberOfArguments(); i++) {
				arguments.add(expression(
						((FunctionCallNode) rhs).getArgument(i), scope));
			}
			result = factory.callStatement(location, null, arguments);
			((CallStatement) result).setLhs(lhs);
			callStatements.put((CallStatement) result, definition);
		} else if (rhs instanceof SpawnNode) {
			FunctionDefinitionNode definition = findFunctionDefinition(((SpawnNode) rhs)
					.getCall().getFunction());
			Vector<Expression> arguments = new Vector<Expression>();
			StringLiteralExpression functionName = factory
					.stringLiteralExpression(definition.getName());

			for (int i = 0; i < ((SpawnNode) rhs).getCall()
					.getNumberOfArguments(); i++) {
				arguments.add(expression(((SpawnNode) rhs).getCall()
						.getArgument(i), scope));
			}
			result = factory.forkStatement(location, lhs, functionName,
					arguments);
		} else {
			result = factory.assignStatement(location, lhs,
					expression(rhs, scope));
		}
		return result;
	}

	private FunctionDefinitionNode findFunctionDefinition(
			ExpressionNode function) {
		FunctionDefinitionNode result = null;

		if (function instanceof IdentifierExpressionNode) {
			Entity functionEntity = function.getScope()
					.getLexicalOrdinaryEntity(
							((IdentifierExpressionNode) function)
									.getIdentifier().name());

			assert functionEntity != null;
			assert functionEntity instanceof edu.udel.cis.vsl.abc.ast.entity.IF.Function;
			result = ((edu.udel.cis.vsl.abc.ast.entity.IF.Function) functionEntity)
					.getDefinition();
		} else {
			// TODO: Figure out more complicated function references.
		}
		return result;
	}

	private Statement compoundStatement(Function function,
			Statement lastStatement, CompoundStatementNode statement,
			Scope scope) {
		Scope newScope = factory.scope(scope, new LinkedHashSet<Variable>(),
				function);
		// TODO: Handle everything that can be in here.
		for (int i = 0; i < statement.numChildren(); i++) {
			BlockItemNode node = statement.getSequenceChild(i);

			if (node instanceof VariableDeclarationNode) {
				InitializerNode init = ((VariableDeclarationNode) node)
						.getInitializer();
				processVariableDeclaration(newScope,
						(VariableDeclarationNode) node);
				if (init != null) {
					// TODO: Handle compound initializers
					Location location = factory.location(newScope);
					Statement newStatement = assign(location,
							factory.variableExpression(newScope
									.getVariable(newScope.numVariables() - 1)),
							(ExpressionNode) init, newScope);

					if (lastStatement != null) {
						lastStatement.setTarget(location);
						function.addLocation(location);
					} else {
						function.setStartLocation(location);
					}
					lastStatement = newStatement;
				}
			} else if (node instanceof FunctionDeclarationNode) {
				unprocessedFunctions.add((FunctionDefinitionNode) node);
				containingScopes.put((FunctionDefinitionNode) node, newScope);
			} else if (node instanceof StatementNode) {
				Statement newStatement = statement(function, lastStatement,
						(StatementNode) node, newScope);
				lastStatement = newStatement;
			} else {
				throw new RuntimeException("Unsupported block element: " + node);
			}
		}
		return lastStatement;
	}

	private Statement forLoop(Function function, Statement lastStatement,
			ForLoopNode statement, Scope scope) {
		ForLoopInitializerNode init = statement.getInitializer();
		Statement initStatement = lastStatement;
		Scope newScope = factory.scope(scope, new LinkedHashSet<Variable>(),
				function);
		Statement loopBody;
		Expression condition;
		Statement incrementer;
		Statement loopExit;

		if (init != null) {
			if (init instanceof ExpressionNode) {
				// TODO: Take care of the special cases here
			} else if (init instanceof DeclarationListNode) {
				for (int i = 0; i < ((DeclarationListNode) init).numChildren(); i++) {
					VariableDeclarationNode declaration = ((DeclarationListNode) init)
							.getSequenceChild(i);
					// TODO: Double check this is a variable
					processVariableDeclaration(newScope, declaration);
					if (declaration.getInitializer() != null) {
						Location initLocation = factory.location(newScope);

						initStatement = factory
								.assignStatement(
										initLocation,
										factory.variableExpression(newScope
												.getVariable(newScope
														.numVariables() - 1)),
										expression((ExpressionNode) declaration
												.getInitializer(), newScope));
						if (lastStatement != null) {
							lastStatement.setTarget(initLocation);
							function.addLocation(initLocation);
						} else {
							lastStatement = initStatement;
							function.setStartLocation(initLocation);
						}
					}
				}
			} else {
				throw new RuntimeException(
						"A for loop initializer must be an expression or a declaration list. "
								+ init);
			}
		}
		condition = expression(statement.getCondition(), newScope);
		loopBody = statement(function, initStatement, statement.getBody(),
				newScope);
		for (Statement outgoing : initStatement.target().outgoing()) {
			outgoing.setGuard(factory.binaryExpression(BINARY_OPERATOR.AND,
					outgoing.guard(), condition));
		}
		incrementer = forLoopIncrementer(function, loopBody,
				statement.getIncrementer(), newScope);
		incrementer.setTarget(initStatement.target());
		loopExit = factory.noopStatement(initStatement.target());
		loopExit.setGuard(factory
				.unaryExpression(UNARY_OPERATOR.NOT, condition));
		return loopExit;
	}

	private Statement forLoopIncrementer(Function function,
			Statement lastStatement, ExpressionNode incrementer, Scope scope) {
		Location location = factory.location(scope);
		Statement result;

		function.addLocation(location);
		// TODO: Handle other possibilites
		if (incrementer instanceof OperatorNode) {
			OperatorNode expression = (OperatorNode) incrementer;
			switch (expression.getOperator()) {
			case ASSIGN:
				result = factory.assignStatement(location,
						expression(expression.getArgument(0), scope),
						expression(expression.getArgument(1), scope));
				break;
			case POSTINCREMENT:
			case PREINCREMENT:
				Expression incrementVariable = expression(
						expression.getArgument(0), scope);

				result = factory
						.assignStatement(
								location,
								incrementVariable,
								factory.binaryExpression(
										BINARY_OPERATOR.PLUS,
										incrementVariable,
										factory.integerLiteralExpression(BigInteger.ONE)));
				break;
			case POSTDECREMENT:
			case PREDECREMENT:
				Expression decrementVariable = expression(
						expression.getArgument(0), scope);

				result = factory
						.assignStatement(
								location,
								decrementVariable,
								factory.binaryExpression(
										BINARY_OPERATOR.PLUS,
										decrementVariable,
										factory.integerLiteralExpression(BigInteger.ONE)));
				break;
			default:
				result = factory.noopStatement(location);
			}
		} else {
			result = factory.noopStatement(location);
		}
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	private Statement wait(Function function, Statement lastStatement,
			WaitNode statement, Scope scope) {
		Location location = factory.location(scope);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		function.addLocation(location);
		return factory.joinStatement(location,
				expression(statement.getExpression(), scope));
	}

	private Statement noop(Function function, Statement lastStatement,
			NullStatementNode statement, Scope scope) {
		Location location = factory.location(scope);
		Statement result = factory.noopStatement(location);

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	private Statement when(Function function, Statement lastStatement,
			WhenNode statement, Scope scope) {
		Statement result = statement(function, lastStatement,
				statement.getBody(), scope);
		Expression guard = expression(statement.getGuard(), scope);

		result.setGuard(guard);
		return result;
	}

	private Statement choose(Function function, Statement lastStatement,
			ChooseStatementNode statement, Scope scope) {
		Location startLocation = factory.location(scope);
		Location endLocation = factory.location(scope);
		Statement result = factory.noopStatement(endLocation);
		Expression guard = factory.booleanLiteralExpression(true);

		if (lastStatement != null) {
			lastStatement.setTarget(startLocation);
		} else {
			function.setStartLocation(startLocation);
		}
		for (int i = 0; i < statement.numChildren(); i++) {
			Statement entry = factory.noopStatement(startLocation);
			Statement caseStatement = statement(function, entry,
					statement.getSequenceChild(i), scope);

			caseStatement.setTarget(endLocation);
			for (Statement s : entry.target().outgoing()) {
				guard = factory.binaryExpression(BINARY_OPERATOR.AND, guard,
						s.guard());
			}
		}
		if (statement.getDefaultCase() != null) {
			Statement entry = factory.noopStatement(startLocation);
			Statement defaultStatement = statement(function, entry,
					statement.getDefaultCase(), scope);

			for (Statement s : entry.target().outgoing()) {
				s.setGuard(factory.binaryExpression(BINARY_OPERATOR.AND,
						factory.unaryExpression(UNARY_OPERATOR.NOT, guard),
						s.guard()));
			}
			defaultStatement.setTarget(endLocation);
		}
		return result;
	}

	private Statement gotoStatement(Function function, Statement lastStatement,
			GotoNode statement, Scope scope) {
		Location location = factory.location(scope);
		Statement noop = factory.noopStatement(location);
		OrdinaryLabelNode label = ((Label) statement.getLabel().getEntity())
				.getDefinition();

		gotoStatements.put(noop, label);
		return noop;
	}

	private Statement labeledStatement(Function function,
			Statement lastStatement, LabeledStatementNode statement, Scope scope) {
		Statement result = statement(function, lastStatement,
				statement.getStatement(), scope);

		labeledStatements.put(statement.getLabel(), result);
		return result;
	}

	private Statement returnStatement(Function function,
			Statement lastStatement, ReturnNode statement, Scope scope) {
		Statement result;
		Expression expression = null;
		Location location = factory.location(scope);

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (statement.getExpression() != null) {
			expression = expression(statement.getExpression(), scope);
		}
		result = factory.returnStatement(location, expression);
		return result;
	}
}
