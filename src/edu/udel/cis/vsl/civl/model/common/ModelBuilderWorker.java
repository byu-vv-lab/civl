package edu.udel.cis.vsl.civl.model.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.Field;
import edu.udel.cis.vsl.abc.ast.entity.IF.Label;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ArrowNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.DotNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ResultNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SelfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SpawnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
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
import edu.udel.cis.vsl.abc.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.NullStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WaitNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.abc.ast.type.IF.PointerType;
import edu.udel.cis.vsl.abc.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType;
import edu.udel.cis.vsl.abc.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.ast.value.IF.IntegerValue;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.CToken;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.err.CIVLException;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Function;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.SystemFunction;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.expression.CommonExpression;

/**
 * Does the main work translating a single ABC Program to a model.
 * 
 * TODO: translate all conversions to casts.
 * 
 * @author siegel
 */
public class ModelBuilderWorker {

	// Fields..............................................................

	private TokenFactory tokenFactory;

	/**
	 * The factory used to create new Model components.
	 */
	private ModelFactory factory;

	/**
	 * The ABC AST being translated by this model builder worker.
	 */
	private Program program;

	/**
	 * The model being constructed by this worker
	 */
	private Model model;

	/**
	 * The outermost scope of the model, root of the static scope tree, known as
	 * the "system scope".
	 */
	private Scope systemScope;

	/**
	 * This field accumulates the AST definition node of every function
	 * definition in the AST.
	 */
	private Vector<FunctionDefinitionNode> unprocessedFunctions;

	/**
	 * For each function definition node, the CIVL static scope containing that
	 * definition.
	 */
	private Map<FunctionDefinitionNode, Scope> containingScopes;

	/**
	 * Map whose key set contains all call/spawn statements in the model. The
	 * value associated to the key is the ABC function definition node. This is
	 * built up as call statements are processed. On a later pass, we iterate
	 * over this map and set the function fields of the call/spawn statements to
	 * the corresponding model Function object.
	 */
	private Map<CallOrSpawnStatement, FunctionDefinitionNode> callStatements;

	/**
	 * Map from all ABC function definition nodes to corresponding CIVL Function
	 * object.
	 */
	private Map<FunctionDefinitionNode, Function> functionMap;

	/**
	 * This fields stores information for a single function, the current one
	 * being processed. It maps ABC label nodes to the corresponding CIVL
	 * locations.
	 */
	private Map<LabelNode, Location> labeledLocations;

	/**
	 * Also being used for single function (the one being processed). Maps from
	 * CIVL "goto" statements to the corresponding label nodes.
	 */
	private Map<Statement, LabelNode> gotoStatements;

	/**
	 * Mapping from ABC types to corresponding CIVL types.
	 */
	private Map<Type, CIVLType> typeMap = new HashMap<Type, CIVLType>();

	/**
	 * 
	 */
	private Map<String, Function> systemFunctions;

	// Constructors........................................................

	/**
	 * Constructs new instance of CommonModelBuilder, creating instance of
	 * ModelFactory in the process, and sets up system functions.
	 * 
	 */
	public ModelBuilderWorker(ModelFactory factory, Program program) {
		this.factory = factory;
		this.program = program;
		this.tokenFactory = program.getTokenFactory();
		setUpSystemFunctions();
	}

	// Helper methods......................................................

	private CIVLSource sourceOf(Source abcSource) {
		return new ABC_CIVLSource(abcSource);
	}

	private CIVLSource sourceOfToken(CToken token) {
		return sourceOf(tokenFactory.newSource(token));
	}

	private CIVLSource sourceOf(ASTNode node) {
		return sourceOf(node.getSource());
	}

	private CIVLSource sourceOfBeginning(ASTNode node) {
		return sourceOfToken(node.getSource().getFirstToken());
	}

	private CIVLSource sourceOfEnd(ASTNode node) {
		return sourceOfToken(node.getSource().getLastToken());
	}

	private CIVLSource sourceOfSpan(Source abcSource1, Source abcSource2) {
		return sourceOf(tokenFactory.join(abcSource1, abcSource2));
	}

	private CIVLSource sourceOfSpan(ASTNode node1, ASTNode node2) {
		return sourceOfSpan(node1.getSource(), node2.getSource());
	}

	private CIVLSource sourceOfSpan(CIVLSource source1, CIVLSource source2) {
		return sourceOfSpan(((ABC_CIVLSource) source1).getABCSource(),
				((ABC_CIVLSource) source2).getABCSource());
	}

	private boolean isTrue(Expression expression) {
		return expression instanceof BooleanLiteralExpression
				&& ((BooleanLiteralExpression) expression).value();
	}

	// private boolean isFalse(Expression expression) {
	// return expression instanceof BooleanLiteralExpression
	// && !((BooleanLiteralExpression) expression).value();
	// }

	/**
	 * Creates system function objects and associates them to particular
	 * libraries. This should be replaced with a general technique for creating
	 * the system function objects.
	 * 
	 * Don't understand why these are created when their decls will already
	 * occur in the AST. When those decls are processed, won't they lead to the
	 * construction of these system functions?
	 */
	private void setUpSystemFunctions() {
		// actually the source object should be taken from
		// the header file civlc.h
		SystemFunction malloc = factory.systemFunction(factory.identifier(
				factory.systemSource(), "$malloc"));
		SystemFunction free = factory.systemFunction(factory.identifier(
				factory.systemSource(), "$free"));
		SystemFunction printf = factory.systemFunction(factory.identifier(
				factory.systemSource(), "printf"));

		malloc.setLibrary("civlc");
		free.setLibrary("civlc");
		printf.setLibrary("civlc");
		systemFunctions = new LinkedHashMap<String, Function>();
		systemFunctions.put("$malloc", malloc);
		systemFunctions.put("$free", free);
		systemFunctions.put("printf", printf);
	}

	/**
	 * Translates a function definition.
	 * 
	 * @param functionNode
	 *            the function definition AST node
	 * @param scope
	 *            the model scope in which the function definition occurs
	 * @return the new CIVL Function object
	 */
	private Function processFunction(FunctionDefinitionNode functionNode,
			Scope scope) {
		Function result;
		Identifier name = factory.identifier(
				sourceOf(functionNode.getIdentifier()), functionNode.getName());
		Vector<Variable> parameters = new Vector<Variable>();
		FunctionTypeNode functionTypeNode = functionNode.getTypeNode();
		CIVLType returnType = translateTypeNode(
				functionTypeNode.getReturnType(), scope);
		Statement body;
		SequenceNode<VariableDeclarationNode> abcParameters = functionTypeNode
				.getParameters();
		int numParameters = abcParameters.numChildren();

		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		for (int i = 0; i < numParameters; i++) {
			VariableDeclarationNode decl = abcParameters.getSequenceChild(i);
			CIVLType type = translateTypeNode(decl.getTypeNode(), scope);
			CIVLSource source = sourceOf(decl.getIdentifier());
			Identifier variableName = factory
					.identifier(source, decl.getName());

			parameters.add(factory.variable(source, type, variableName,
					parameters.size()));
		}
		result = factory.function(sourceOf(functionNode), name, parameters,
				returnType, scope, null);
		body = statement(result, null, functionNode.getBody(),
				result.outerScope());
		if (!(body instanceof ReturnStatement)) {
			// would like to get source of closing "}"?
			CIVLSource endSource = sourceOfEnd(functionNode.getBody());
			Location returnLocation = factory.location(endSource,
					result.outerScope());
			ReturnStatement returnStatement = factory.returnStatement(
					endSource, returnLocation, null);

			body.setTarget(returnLocation);
			result.addLocation(returnLocation);
			result.addStatement(returnStatement);
		}
		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledLocations.get(gotoStatements.get(s)));
		}
		return result;
	}

	/**
	 * Processes a variable declaration. Adds the new variable to the given
	 * scope.
	 * 
	 * @param scope
	 *            the Model scope in which the variable declaration occurs
	 * @param node
	 *            the AST variable declaration node.
	 */
	private Variable processVariableDeclaration(Scope scope,
			VariableDeclarationNode node) {
		CIVLType type = translateTypeNode(node.getTypeNode(), scope);
		Identifier name = factory.identifier(sourceOf(node.getIdentifier()),
				node.getName());
		Variable variable = factory.variable(sourceOf(node.getIdentifier()),
				type, name, scope.numVariables());

		if (node.getTypeNode().isInputQualified()) {
			variable.setIsExtern(true);
		}
		scope.addVariable(variable);
		return variable;
	}

	private CIVLType translateBasicType(StandardBasicType basicType,
			CIVLSource source) {
		switch (basicType.getBasicTypeKind()) {
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
		case DOUBLE_COMPLEX:
		case FLOAT_COMPLEX:
		case LONG_DOUBLE_COMPLEX:
		case SIGNED_CHAR:
		case UNSIGNED_CHAR:
		default:
			throw new CIVLUnimplementedFeatureException("types of kind "
					+ basicType.kind(), source);
		}
	}

	private CIVLType translateStructureOrUnion(StructureOrUnionType type,
			Scope scope, CIVLSource source) {
		String tag = type.getTag();

		if (type.isUnion())
			throw new CIVLUnimplementedFeatureException("Union types", source);
		// civlc.h defines $proc as struct __proc__, etc.
		if ("__proc__".equals(tag))
			return factory.processType();
		if ("__heap__".equals(tag))
			return factory.heapType();
		if ("__scope__".equals(tag))
			return factory.scopeType();
		if ("__dynamic__".equals(tag))
			return factory.dynamicType();
		else {
			CIVLStructType result = factory.structType(factory.identifier(
					source, tag));
			int numFields = type.getNumFields();
			StructField[] civlFields = new StructField[numFields];

			typeMap.put(type, result);
			for (int i = 0; i < numFields; i++) {
				Field field = type.getField(i);
				String name = field.getName();
				Type fieldType = field.getType();
				CIVLType civlFieldType = translateType(fieldType, scope, source);
				Identifier identifier = factory.identifier(sourceOf(field
						.getDefinition().getIdentifier()), name);
				StructField civlField = factory.structField(identifier,
						civlFieldType);

				civlFields[i] = civlField;
			}
			result.complete(civlFields);
			return result;
		}
	}

	private Expression getExtent(CIVLSource source, ArrayType arrayType,
			Scope scope) {
		Expression result;

		if (arrayType.isComplete()) {
			ExpressionNode variableSize = arrayType.getVariableSize();

			if (variableSize != null) {
				result = expression(variableSize, scope);
			} else {
				IntegerValue constantSize = arrayType.getConstantSize();

				if (constantSize != null)
					result = factory.integerLiteralExpression(source,
							constantSize.getIntegerValue());
				else
					throw new CIVLInternalException(
							"Complete array type has neither constant size nor variable size: "
									+ arrayType, source);
			}
		} else
			result = null;
		return result;
	}

	/**
	 * Working on replacing process type with this.
	 * 
	 * @param abcType
	 * @return
	 */
	private CIVLType translateType(Type abcType, Scope scope, CIVLSource source) {
		CIVLType result = typeMap.get(abcType);

		if (result == null) {
			TypeKind kind = abcType.kind();

			switch (kind) {
			case ARRAY: {
				ArrayType arrayType = (ArrayType) abcType;
				CIVLType elementType = translateType(
						arrayType.getElementType(), scope, source);
				Expression extent = getExtent(source, arrayType, scope);

				if (extent != null)
					result = factory.completeArrayType(elementType, extent);
				else
					result = factory.incompleteArrayType(elementType);
				break;
			}
			case BASIC:
				result = translateBasicType((StandardBasicType) abcType, source);
				break;
			case HEAP:
				result = factory.heapType();
				break;
			case OTHER_INTEGER:
				result = factory.integerType();
				break;
			case POINTER: {
				PointerType pointerType = (PointerType) abcType;
				Type referencedType = pointerType.referencedType();
				CIVLType baseType = translateType(referencedType, scope, source);

				result = factory.pointerType(baseType);
				break;
			}
			case PROCESS:
				result = factory.processType();
				break;
			case QUALIFIED:
				result = translateType(
						((QualifiedObjectType) abcType).getBaseType(), scope,
						source);
				break;
			case STRUCTURE_OR_UNION:
				result = translateStructureOrUnion(
						(StructureOrUnionType) abcType, scope, source);
				// type already entered into map, so just return:
				return result;
			case VOID:
				result = factory.voidType();
				break;
			case ATOMIC:
			case FUNCTION:
			case ENUMERATION:
				throw new CIVLUnimplementedFeatureException("Type " + abcType,
						source);
			default:
				throw new CIVLInternalException("Unknown type: " + abcType,
						source);
			}
			typeMap.put(abcType, result);
		}
		return result;
	}

	private CIVLType translateTypeNode(TypeNode typeNode, Scope scope) {
		return translateType(typeNode.getType(), scope, sourceOf(typeNode));
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
	private Expression expression(ExpressionNode expression, Scope scope) {
		Expression result;

		if (expression instanceof OperatorNode) {
			result = operator((OperatorNode) expression, scope);
		} else if (expression instanceof IdentifierExpressionNode) {
			result = variableExpression((IdentifierExpressionNode) expression,
					scope);
		} else if (expression instanceof ConstantNode) {
			result = constant((ConstantNode) expression);
		} else if (expression instanceof DotNode) {
			result = dotExpression((DotNode) expression, scope);
		} else if (expression instanceof ArrowNode) {
			result = arrowExpression((ArrowNode) expression, scope);
		} else if (expression instanceof ResultNode) {
			result = factory.resultExpression(sourceOf(expression));
		} else if (expression instanceof SelfNode) {
			result = factory.selfExpression(sourceOf(expression));
		} else if (expression instanceof CastNode) {
			result = castExpression((CastNode) expression, scope);
		} else
			throw new CIVLUnimplementedFeatureException("expressions of type "
					+ expression.getClass().getSimpleName(),
					sourceOf(expression));
		return result;
	}

	/**
	 * Translate an expression from the CIVL AST to the CIVL model. The
	 * resulting expression will always be boolean-valued. If the expression
	 * evaluates to a numeric type, the result will be the equivalent of
	 * expression==0. Used for evaluating expression in conditions.
	 * 
	 * @param expression
	 * @param scope
	 */
	private Expression booleanExpression(ExpressionNode expression, Scope scope) {
		Expression result = expression(expression, scope);

		if (!result.getExpressionType().equals(factory.booleanType())) {
			if (result.getExpressionType().equals(factory.integerType())) {
				result = factory.binaryExpression(sourceOf(expression),
						BINARY_OPERATOR.NOT_EQUAL, result, factory
								.integerLiteralExpression(sourceOf(expression),
										BigInteger.ZERO));
			} else if (result.getExpressionType().equals(factory.realType())) {
				result = factory.binaryExpression(sourceOf(expression),
						BINARY_OPERATOR.NOT_EQUAL, result, factory
								.realLiteralExpression(sourceOf(expression),
										BigDecimal.ZERO));
			} else {
				throw new CIVLInternalException(
						"Unable to convert expression to boolean type",
						sourceOf(expression));
			}
		}
		return result;
	}

	/**
	 * Translate a cast expression from the CIVL AST to the CIVL model.
	 * 
	 * @param expression
	 *            The cast expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression castExpression(CastNode expression, Scope scope) {
		Expression result;
		CIVLType castType = translateTypeNode(expression.getCastType(), scope);
		Expression castExpression = expression(expression.getArgument(), scope);

		result = factory.castExpression(sourceOf(expression), castType,
				castExpression);
		return result;
	}

	private int getFieldIndex(IdentifierNode fieldIdentifier) {
		Entity entity = fieldIdentifier.getEntity();
		EntityKind kind = entity.getEntityKind();

		if (kind == EntityKind.FIELD) {
			Field field = (Field) entity;

			return field.getMemberIndex();
		} else {
			throw new CIVLInternalException(
					"getFieldIndex given identifier that does not correspond to field: ",
					sourceOf(fieldIdentifier));
		}
	}

	/**
	 * Translate a struct pointer field reference from the CIVL AST to the CIVL
	 * model.
	 * 
	 * @param expression
	 *            The arrow expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression arrowExpression(ArrowNode expression, Scope scope) {
		Expression struct = expression(expression.getStructurePointer(), scope);
		Expression result = factory.dotExpression(
				sourceOf(expression),
				factory.dereferenceExpression(
						sourceOf(expression.getStructurePointer()), struct),
				getFieldIndex(expression.getFieldName()));

		return result;
	}

	/**
	 * Translate a struct field reference from the CIVL AST to the CIVL model.
	 * 
	 * @param expression
	 *            The dot expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression dotExpression(DotNode expression, Scope scope) {
		Expression struct = expression(expression.getStructure(), scope);
		Expression result = factory.dotExpression(sourceOf(expression), struct,
				getFieldIndex(expression.getFieldName()));

		return result;
	}

	// note: argument to & should never have array type

	/**
	 * If the given CIVL expression e has array type, this returns the
	 * expression &e[0]. Otherwise returns e unchanged.
	 * 
	 * This method should be called on every LHS expression e except in the
	 * following cases: (1) e is the first argument to the SUBSCRIPT operator
	 * (i.e., e occurs in the context e[i]), or (2) e is the argument to the
	 * "sizeof" operator.
	 * 
	 * @param expression
	 *            any CIVL expression e
	 * @return either the original expression or &e[0]
	 */
	private Expression arrayToPointer(Expression expression) {
		CIVLType type = expression.getExpressionType();

		if (type instanceof CIVLArrayType) {
			CIVLSource source = expression.getSource();
			CIVLArrayType arrayType = (CIVLArrayType) type;
			CIVLType elementType = arrayType.elementType();
			Expression zero = factory.integerLiteralExpression(source,
					BigInteger.ZERO);
			LHSExpression subscript = factory.subscriptExpression(source,
					(LHSExpression) expression, zero);
			Expression pointer = factory.addressOfExpression(source, subscript);
			Scope scope = expression.expressionScope();

			zero.setExpressionScope(scope);
			subscript.setExpressionScope(scope);
			pointer.setExpressionScope(scope);
			((CommonExpression) zero).setExpressionType(factory.integerType());
			((CommonExpression) subscript).setExpressionType(elementType);
			((CommonExpression) pointer).setExpressionType(factory
					.pointerType(elementType));
			return pointer;
		}
		return expression;
	}

	/**
	 * Translates an AST subscript node e1[e2] to a CIVL expression. The result
	 * will either be a CIVL subscript expression (if e1 has array type) or a
	 * CIVL expression of the form *(e1+e2) or *(e2+e1).
	 * 
	 * @param subscriptNode
	 *            an AST node with operator SUBSCRIPT
	 * @param scope
	 *            scope in which this expression occurs
	 * @return the equivalent CIVL expression
	 */
	private Expression subscript(OperatorNode subscriptNode, Scope scope) {
		CIVLSource source = sourceOf(subscriptNode);
		ExpressionNode leftNode = subscriptNode.getArgument(0);
		ExpressionNode rightNode = subscriptNode.getArgument(1);
		Expression lhs = expression(leftNode, scope);
		Expression rhs = expression(rightNode, scope);
		CIVLType lhsType = lhs.getExpressionType();
		Expression result;

		if (lhsType instanceof CIVLArrayType) {
			if (!(lhs instanceof LHSExpression))
				throw new CIVLInternalException(
						"Expected expression with array type to be LHS",
						lhs.getSource());
			result = factory.subscriptExpression(source, (LHSExpression) lhs,
					rhs);
		} else {
			CIVLType rhsType = rhs.getExpressionType();
			Expression pointerExpr, indexExpr;

			if (lhsType instanceof CIVLPointerType) {
				if (!rhsType.isIntegerType())
					throw new CIVLInternalException(
							"Expected expression of integer type",
							rhs.getSource());
				pointerExpr = lhs;
				indexExpr = rhs;
			} else if (lhsType.isIntegerType()) {
				if (!(rhsType instanceof CIVLPointerType))
					throw new CIVLInternalException(
							"Expected expression of pointer type",
							rhs.getSource());
				pointerExpr = rhs;
				indexExpr = lhs;
			} else
				throw new CIVLInternalException(
						"Expected one argument of integer type and one of pointer type",
						source);
			result = factory.dereferenceExpression(source, factory
					.binaryExpression(source, BINARY_OPERATOR.POINTER_ADD,
							pointerExpr, indexExpr));
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
	private Expression operator(OperatorNode expression, Scope scope) {
		CIVLSource source = sourceOf(expression);
		Operator operator = expression.getOperator();

		if (operator == Operator.SUBSCRIPT)
			return subscript(expression, scope);

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
		case ADDRESSOF:
			result = factory.addressOfExpression(source,
					(LHSExpression) arguments.get(0));
			break;
		case DEREFERENCE:
			result = factory.dereferenceExpression(source, arguments.get(0));
			break;
		case CONDITIONAL:
			result = factory.conditionalExpression(source, arguments.get(0),
					arguments.get(1), arguments.get(2));
			break;
		case DIV:
			result = factory.binaryExpression(source, BINARY_OPERATOR.DIVIDE,
					arguments.get(0), arguments.get(1));
			break;
		case EQUALS:
			result = factory.binaryExpression(source, BINARY_OPERATOR.EQUAL,
					arguments.get(0), arguments.get(1));
			break;
		case GT:
			result = factory.binaryExpression(source,
					BINARY_OPERATOR.LESS_THAN, arguments.get(1),
					arguments.get(0));
			break;
		case GTE:
			result = factory.binaryExpression(source,
					BINARY_OPERATOR.LESS_THAN_EQUAL, arguments.get(1),
					arguments.get(0));
			break;
		case LAND:
			result = factory.binaryExpression(source, BINARY_OPERATOR.AND,
					arguments.get(0), arguments.get(1));
			break;
		case LOR:
			result = factory.binaryExpression(source, BINARY_OPERATOR.OR,
					arguments.get(0), arguments.get(1));
			break;
		case LT:
			result = factory.binaryExpression(source,
					BINARY_OPERATOR.LESS_THAN, arguments.get(0),
					arguments.get(1));
			break;
		case LTE:
			result = factory.binaryExpression(source,
					BINARY_OPERATOR.LESS_THAN_EQUAL, arguments.get(0),
					arguments.get(1));
			break;
		case MINUS:
			result = factory.binaryExpression(source, BINARY_OPERATOR.MINUS,
					arguments.get(0), arguments.get(1));
			break;
		case MOD:
			result = factory.binaryExpression(source, BINARY_OPERATOR.MODULO,
					arguments.get(0), arguments.get(1));
			break;
		case NEQ:
			result = factory.binaryExpression(source,
					BINARY_OPERATOR.NOT_EQUAL, arguments.get(0),
					arguments.get(1));
			break;
		case NOT:
			result = factory.unaryExpression(source, UNARY_OPERATOR.NOT,
					arguments.get(0));
			break;
		case PLUS: {
			Expression arg0 = arguments.get(0);
			Expression arg1 = arguments.get(1);
			CIVLType type0 = arg0.getExpressionType();
			CIVLType type1 = arg1.getExpressionType();
			boolean isNumeric0 = type0.isNumericType();
			boolean isNumeric1 = type1.isNumericType();

			if (isNumeric0 && isNumeric1) {
				result = factory.binaryExpression(source, BINARY_OPERATOR.PLUS,
						arg0, arg1);
				break;
			} else {
				Expression pointer, offset;

				if (isNumeric1) {
					pointer = arrayToPointer(arg0);
					offset = arg1;
				} else if (isNumeric0) {
					pointer = arrayToPointer(arg1);
					offset = arg0;
				} else
					throw new CIVLInternalException(
							"Expected at least one numeric argument", source);
				if (!(pointer.getExpressionType() instanceof CIVLPointerType))
					throw new CIVLInternalException(
							"Expected expression of pointer type",
							pointer.getSource());
				if (!offset.getExpressionType().isIntegerType())
					throw new CIVLInternalException(
							"Expected expression of integer type",
							offset.getSource());
				result = factory.binaryExpression(source,
						BINARY_OPERATOR.POINTER_ADD, pointer, offset);
			}
			break;
		}
		case SUBSCRIPT:
			throw new CIVLInternalException("unreachable", source);
		case TIMES:
			result = factory.binaryExpression(source, BINARY_OPERATOR.TIMES,
					arguments.get(0), arguments.get(1));
			break;
		case UNARYMINUS:
			result = factory.unaryExpression(source, UNARY_OPERATOR.NEGATIVE,
					arguments.get(0));
			break;
		case UNARYPLUS:
			result = arguments.get(0);
			break;
		default:
			throw new CIVLUnimplementedFeatureException(
					"Unsupported operator: " + expression.getOperator()
							+ " in expression " + expression);
		}
		return result;
	}

	private VariableExpression variableExpression(
			IdentifierExpressionNode identifier, Scope scope) {
		CIVLSource source = sourceOf(identifier);
		Identifier name = factory.identifier(source, identifier.getIdentifier()
				.name());
		VariableExpression result;

		if (scope.variable(name) == null) {
			throw new CIVLInternalException("No such variable ", source);
		}
		result = factory.variableExpression(source, scope.variable(name));
		return result;
	}

	private Expression constant(ConstantNode constant) {
		CIVLSource source = sourceOf(constant);
		Type convertedType = constant.getConvertedType();
		LiteralExpression result;

		if (convertedType.kind() == TypeKind.PROCESS) {
			assert constant.getStringRepresentation().equals("$self");
			return factory.selfExpression(source);
		}
		assert convertedType.kind() == TypeKind.BASIC;
		switch (((StandardBasicType) convertedType).getBasicTypeKind()) {
		case SHORT:
		case UNSIGNED_SHORT:
		case INT:
		case UNSIGNED:
		case LONG:
		case UNSIGNED_LONG:
		case LONG_LONG:
		case UNSIGNED_LONG_LONG:
			result = factory.integerLiteralExpression(source,
					BigInteger.valueOf(Long.parseLong(constant
							.getStringRepresentation())));
			break;
		case FLOAT:
		case DOUBLE:
		case LONG_DOUBLE:
			result = factory.realLiteralExpression(source, BigDecimal
					.valueOf(Double.parseDouble(constant
							.getStringRepresentation())));
			break;
		case BOOL:
			boolean value;

			if (constant instanceof IntegerConstantNode) {
				BigInteger integerValue = ((IntegerConstantNode) constant)
						.getConstantValue().getIntegerValue();

				if (integerValue.intValue() == 0) {
					value = false;
				} else {
					value = true;
				}
			} else {
				value = Boolean
						.parseBoolean(constant.getStringRepresentation());
			}
			result = factory.booleanLiteralExpression(source, value);
			break;
		default:
			throw new RuntimeException(
					"Unsupported converted type for expression: " + constant);
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
		Statement result;

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
		} else if (statement instanceof LoopNode) {
			result = whileLoop(function, lastStatement, (LoopNode) statement,
					scope);
		} else if (statement instanceof IfNode) {
			result = ifStatement(function, lastStatement, (IfNode) statement,
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
		} else if (statement instanceof SwitchNode) {
			result = switchStatement(function, lastStatement,
					(SwitchNode) statement, scope);
		} else
			throw new CIVLInternalException("Unknown statement kind",
					sourceOf(statement));
		function.addStatement(result);
		return result;
	}

	/**
	 * Takes a statement node where the start location and extra guard are
	 * defined elsewhere and returns the appropriate model statement.
	 * 
	 * @param location
	 *            The start location of the statement.
	 * @param guard
	 *            An extra component of the guard beyond that described in the
	 *            statement.
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
	private Statement statement(Location location, Expression guard,
			Function function, Statement lastStatement,
			StatementNode statement, Scope scope) {
		Statement result;

		if (statement instanceof AssumeNode) {
			result = assume(function, lastStatement, (AssumeNode) statement,
					scope);
		} else if (statement instanceof AssertNode) {
			result = assertStatement(function, lastStatement,
					(AssertNode) statement, scope);
		} else if (statement instanceof ExpressionStatementNode) {
			result = expressionStatement(location, guard, function,
					lastStatement, (ExpressionStatementNode) statement, scope);
		} else if (statement instanceof CompoundStatementNode) {
			result = compoundStatement(location, guard, function,
					lastStatement, (CompoundStatementNode) statement, scope);
		} else if (statement instanceof ForLoopNode) {
			result = forLoop(location, guard, function, lastStatement,
					(ForLoopNode) statement, scope);
		} else if (statement instanceof LoopNode) {
			result = whileLoop(function, lastStatement, (LoopNode) statement,
					scope);
		} else if (statement instanceof IfNode) {
			result = ifStatement(location, function, lastStatement,
					(IfNode) statement, scope);
		} else if (statement instanceof WaitNode) {
			result = wait(function, lastStatement, (WaitNode) statement, scope);
		} else if (statement instanceof NullStatementNode) {
			result = noop(location, function, lastStatement,
					(NullStatementNode) statement, scope);
		} else if (statement instanceof WhenNode) {
			result = when(location, guard, function, lastStatement,
					(WhenNode) statement, scope);
		} else if (statement instanceof ChooseStatementNode) {
			result = choose(function, lastStatement,
					(ChooseStatementNode) statement, scope);
		} else if (statement instanceof GotoNode) {
			result = gotoStatement(function, lastStatement,
					(GotoNode) statement, scope);
		} else if (statement instanceof LabeledStatementNode) {
			result = labeledStatement(location, guard, function, lastStatement,
					(LabeledStatementNode) statement, scope);
		} else if (statement instanceof ReturnNode) {
			result = returnStatement(function, lastStatement,
					(ReturnNode) statement, scope);
		} else if (statement instanceof SwitchNode) {
			result = switchStatement(location, guard, function, lastStatement,
					(SwitchNode) statement, scope);
		} else
			throw new CIVLUnimplementedFeatureException("statements of type "
					+ statement.getClass().getSimpleName(), sourceOf(statement));
		function.addStatement(result);
		return result;
	}

	/**
	 * An if statement.
	 */
	private Statement ifStatement(Function function, Statement lastStatement,
			IfNode statement, Scope scope) {
		return ifStatement(
				factory.location(sourceOfBeginning(statement), scope),
				function, lastStatement, statement, scope);
	}

	private Statement ifStatement(Location location, Function function,
			Statement lastStatement, IfNode statement, Scope scope) {
		Expression expression = expression(statement.getCondition(), scope);
		Statement trueBranch = statement(location, expression, function,
				lastStatement, statement.getTrueBranch(), scope);
		Location exitLocation = factory.location(sourceOfEnd(statement), scope);
		Statement falseBranch;
		Statement result;

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (statement.getFalseBranch() == null) {
			falseBranch = factory.noopStatement(sourceOfEnd(statement),
					location);
			falseBranch.setGuard(factory.unaryExpression(
					expression.getSource(), UNARY_OPERATOR.NOT, expression));
		} else {
			falseBranch = statement(location, factory.unaryExpression(
					expression.getSource(), UNARY_OPERATOR.NOT, expression),
					function, lastStatement, statement.getFalseBranch(), scope);
		}
		function.addLocation(exitLocation);
		trueBranch.setTarget(exitLocation);
		falseBranch.setTarget(exitLocation);
		result = factory.noopStatement(sourceOfEnd(statement), exitLocation);
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
		Location location = factory.location(sourceOfBeginning(statement),
				scope);

		result = factory.assumeStatement(sourceOf(statement), location,
				expression);
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
		Expression expression = expression(statement.getExpression(), scope);
		Location location = factory.location(sourceOfBeginning(statement),
				scope);
		Statement result;

		function.addLocation(location);
		result = factory.assertStatement(sourceOf(statement), location,
				expression);
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
		CIVLSource start = sourceOfBeginning(statement);
		Location location = factory.location(start, scope);
		Expression guard = factory.booleanLiteralExpression(start, true);

		function.addLocation(location);
		return expressionStatement(location, guard, function, lastStatement,
				statement, scope);
	}

	/**
	 * Takes an expression statement and converts it to a model representation
	 * of that statement. Currently supported expressions for expression
	 * statements are spawn, assign, function call, increment, decrement. Any
	 * other expressions have no side effects and thus result in a no-op.
	 * 
	 * @param location
	 *            The start location for this statement.
	 * @param guard
	 *            An extra guard associated with this statement.
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
	private Statement expressionStatement(Location location, Expression guard,
			Function function, Statement lastStatement,
			ExpressionStatementNode statement, Scope scope) {
		Statement result = null;

		result = expressionStatement(location, guard, function,
				statement.getExpression(), scope);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (isTrue(result.guard())) {
			result.setGuard(guard);
		} else if (!isTrue(guard)) {
			result.setGuard(factory.binaryExpression(guard.getSource(),
					BINARY_OPERATOR.AND, guard, result.guard()));
		}
		return result;
	}

	private CallOrSpawnStatement callOrSpawn(Location location, boolean isCall,
			LHSExpression lhs, FunctionCallNode callNode, Scope scope) {
		Vector<Expression> arguments = new Vector<Expression>();
		ExpressionNode functionExpression = ((FunctionCallNode) callNode)
				.getFunction();
		FunctionDefinitionNode functionDefinition;
		String functionName;
		CallOrSpawnStatement result;

		if (functionExpression instanceof IdentifierExpressionNode) {
			edu.udel.cis.vsl.abc.ast.entity.IF.Function callee = (edu.udel.cis.vsl.abc.ast.entity.IF.Function) ((IdentifierExpressionNode) functionExpression)
					.getIdentifier().getEntity();
			functionName = callee.getName();
			functionDefinition = callee.getDefinition();
		} else {
			throw new CIVLUnimplementedFeatureException(
					"Function call must use identifier for now: "
							+ functionExpression.getSource());
		}
		for (int i = 0; i < callNode.getNumberOfArguments(); i++) {
			arguments.add(arrayToPointer(expression(callNode.getArgument(i),
					scope)));
		}
		result = factory.callOrSpawnStatement(sourceOf(callNode), location,
				isCall, null, arguments);
		result.setLhs(lhs);
		if (systemFunctions.containsKey(functionName)) {
			((CallOrSpawnStatement) result).setFunction(systemFunctions
					.get(functionName));
		} else {
			callStatements.put((CallOrSpawnStatement) result,
					functionDefinition);
		}
		return result;
	}

	/**
	 * Create a statement from an expression.
	 * 
	 * @param location
	 * @param guard
	 * @param function
	 * @param lastStatement
	 * @param expression
	 * @param scope
	 */
	private Statement expressionStatement(Location location, Expression guard,
			Function function, ExpressionNode expressionStatement, Scope scope) {
		Statement result;

		if (expressionStatement instanceof OperatorNode) {
			OperatorNode expression = (OperatorNode) expressionStatement;
			switch (expression.getOperator()) {
			case ASSIGN:
				result = assign(location, expression.getArgument(0),
						expression.getArgument(1), scope);
				break;
			case POSTINCREMENT:
			case PREINCREMENT:
			case POSTDECREMENT:
			case PREDECREMENT:
				throw new CIVLInternalException("Side-effect not removed: ",
						sourceOf(expression));
			default:
				// since side-effects have been removed,
				// the only expressions remaining with side-effects
				// are assignments. all others are equivalent to no-op
				result = factory.noopStatement(sourceOf(expression), location);
			}
		} else if (expressionStatement instanceof SpawnNode) {
			FunctionCallNode call = ((SpawnNode) expressionStatement).getCall();

			result = callOrSpawn(location, false, null, call, scope);
		} else if (expressionStatement instanceof FunctionCallNode) {
			result = callOrSpawn(location, true, null,
					(FunctionCallNode) expressionStatement, scope);
		} else
			throw new CIVLInternalException(
					"expression statement of this kind",
					sourceOf(expressionStatement));
		result.setGuard(guard);
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

		if (!(lhsExpression instanceof LHSExpression))
			throw new CIVLInternalException("expected LHS expression, not "
					+ lhsExpression, sourceOf(lhs));
		return assign(sourceOfSpan(lhs, rhs), location,
				(LHSExpression) lhsExpression, rhs, scope);
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
	private Statement assign(CIVLSource source, Location location,
			LHSExpression lhs, ExpressionNode rhs, Scope scope) {
		Statement result = null;

		if (rhs instanceof FunctionCallNode) {
			result = callOrSpawn(location, true, lhs, (FunctionCallNode) rhs,
					scope);
		} else if (rhs instanceof SpawnNode) {
			result = callOrSpawn(location, false, lhs,
					((SpawnNode) rhs).getCall(), scope);
		} else {
			result = factory.assignStatement(lhs.getSource(), location, lhs,
					arrayToPointer(expression(rhs, scope)));
		}
		return result;
	}

	private Statement compoundStatement(Function function,
			Statement lastStatement, CompoundStatementNode statement,
			Scope scope) {
		return compoundStatement(null, null, function, lastStatement,
				statement, scope);

	}

	private Variable newStructOrUnionStateVariable(Scope scope, CIVLType type,
			CIVLSource source) {
		Variable result;

		if (type instanceof CIVLStructType) {
			String tag = ((CIVLStructType) type).name().name();
			String name = "__struct_" + tag + "__";
			Identifier identifier = factory.identifier(source, name);
			int vid = scope.numVariables();

			result = factory.variable(source, factory.dynamicType(),
					identifier, vid);
		} else {
			throw new CIVLInternalException("unexpected type: " + type, source);
		}
		scope.addVariable(result);
		type.setStateVariable(result);
		return result;
	}

	private Variable newTypedefStateVariable(Scope scope, String tag,
			CIVLType type, CIVLSource source) {
		Variable result;
		String name = "__typedef_" + tag + "__";
		Identifier identifier = factory.identifier(source, name);
		int vid = scope.numVariables();

		result = factory.variable(source, factory.dynamicType(), identifier,
				vid);
		scope.addVariable(result);
		type.setStateVariable(result);
		return result;
	}

	// how to process indiviual block elements?

	/**
	 * Translates a variable declaration node. If the given sourceLocation is
	 * non-null, it is used as the source location for the new statement(s).
	 * Otherwise a new location is generated and used. This method may return
	 * null if no statements are generated as a result of processing the
	 * declaration.
	 * 
	 * @param sourceLocation
	 *            location to use as origin of new statements or null
	 * @param scope
	 *            CIVL scope in which this declaration appears
	 * @param node
	 *            the ABC variable declaration node to translate
	 * @return the pair consisting of the (new or given) start location of the
	 *         generated fragment and the last statement in the sequence of
	 *         statements generated by translating this declaration node, or
	 *         null if no statements are generated
	 */
	private Fragment translateVariableDeclarationNode(Location sourceLocation,
			Scope scope, VariableDeclarationNode node) {
		InitializerNode init = node.getInitializer();
		Variable variable = processVariableDeclaration(scope, node);
		CIVLType type = variable.type();
		Fragment result = null;
		IdentifierNode identifier = node.getIdentifier();
		CIVLSource source = sourceOf(node);

		if (type instanceof CIVLArrayType || type instanceof CIVLStructType) {
			if (sourceLocation == null)
				sourceLocation = factory.location(sourceOfBeginning(node),
						scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					source, sourceLocation,
					factory.variableExpression(sourceOf(identifier), variable),
					factory.initialValueExpression(source, variable)));
			sourceLocation = null;
		}
		if (init != null) {
			Statement statement;

			if (!(init instanceof ExpressionNode))
				throw new CIVLUnimplementedFeatureException(
						"Non-expression initializer", sourceOf(init));
			if (sourceLocation == null)
				sourceLocation = factory.location(sourceOfBeginning(node),
						scope);
			statement = assign(sourceOf(node), sourceLocation,
					factory.variableExpression(sourceOf(identifier), variable),
					(ExpressionNode) init, scope);
			if (result == null)
				result = new Fragment(sourceLocation, statement);
			else {
				result.lastStatement.setTarget(sourceLocation);
				result.lastStatement = statement;
			}
		}
		return result;
	}

	private Fragment translateStructureOrUnionTypeNode(Location sourceLocation,
			Scope scope, StructureOrUnionTypeNode node) {
		Fragment result = null;

		if (node.getStructDeclList() != null) {
			CIVLType type = translateTypeNode(node, scope);
			CIVLSource civlSource = sourceOf(node);

			if (type.hasState()) {
				Variable variable = newStructOrUnionStateVariable(scope, type,
						civlSource);
				LHSExpression lhs = factory.variableExpression(civlSource,
						variable);
				Expression rhs = factory.dynamicTypeOfExpression(civlSource,
						type);

				if (sourceLocation == null)
					sourceLocation = factory.location(sourceOfBeginning(node),
							scope);
				result = new Fragment(sourceLocation, factory.assignStatement(
						civlSource, sourceLocation, lhs, rhs));
			}
		}
		return result;
	}

	private Fragment translateTypedefNode(Location sourceLocation, Scope scope,
			TypedefDeclarationNode node) {
		TypeNode typeNode = node.getTypeNode();
		CIVLType type = translateTypeNode(typeNode, scope);
		Fragment result = null;

		if (type.hasState()) {
			CIVLSource civlSource = sourceOf(node);
			String tag = node.getName();
			Variable variable = newTypedefStateVariable(scope, tag, type,
					civlSource);
			LHSExpression lhs = factory
					.variableExpression(civlSource, variable);
			Expression rhs = factory.dynamicTypeOfExpression(civlSource, type);

			if (sourceLocation == null)
				sourceLocation = factory.location(sourceOfBeginning(node),
						scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					civlSource, sourceLocation, lhs, rhs));
		}
		return result;
	}

	/**
	 * Translates a compound statement.
	 * 
	 * Tagged entities can have state and require special handling. The method
	 * {@link CIVLType#hasState} in {@link CIVLType} will return
	 * <code>true</code> for any type which contains an array with extent which
	 * is not constant. We associate to these types a state variable that can be
	 * set and get.
	 * 
	 * When perusing compound statements or external defs, when you come across
	 * a typedef, or complete struct or union def, construct the CIVL type
	 * <code>t</code> as usual. If <code>t.hasState()</code>, insert into the
	 * model at the current scope a variable <code>__struct_foo__</code>,
	 * <code>__union_foo__</code>, or <code>__typedef_foo__</code> of type
	 * "CIVL dynamic type". Set the state variable in <code>t</code> to this
	 * variable. At the point of definition, insert a model assignment
	 * statement, <code>__struct_foo__ = DynamicTypeOf(t)</code> (for example).
	 * 
	 * When processing a variable decl: if variable has compound type (array or
	 * struct), insert statement (into beginning of current compound statement)
	 * saying "v = InitialValue[v]". then insert the variable's initializer if
	 * present.
	 * 
	 * @param location
	 * @param guard
	 * @param function
	 * @param lastStatement
	 * @param statement
	 * @param scope
	 * @return
	 */
	private Statement compoundStatement(Location location, Expression guard,
			Function function, Statement lastStatement,
			CompoundStatementNode statement, Scope scope) {
		Scope newScope = factory.scope(sourceOf(statement), scope,
				new LinkedHashSet<Variable>(), function);
		// indicates whether the location argument has been used:
		boolean usedLocation = false;

		for (int i = 0; i < statement.numChildren(); i++) {
			BlockItemNode node = statement.getSequenceChild(i);

			if (node instanceof VariableDeclarationNode
					|| node instanceof StructureOrUnionTypeNode
					|| node instanceof TypedefDeclarationNode) {
				Fragment fragment;

				if (node instanceof VariableDeclarationNode)
					fragment = translateVariableDeclarationNode(
							usedLocation ? null : location, newScope,
							(VariableDeclarationNode) node);
				else if (node instanceof StructureOrUnionTypeNode)
					fragment = translateStructureOrUnionTypeNode(
							usedLocation ? null : location, newScope,
							(StructureOrUnionTypeNode) node);
				else if (node instanceof TypedefDeclarationNode)
					fragment = translateTypedefNode(usedLocation ? null
							: location, newScope, (TypedefDeclarationNode) node);
				else
					throw new CIVLInternalException("unreachable",
							sourceOf(node));
				if (fragment != null) {
					usedLocation = true;
					if (lastStatement != null) {
						lastStatement.setTarget(fragment.startLocation);
						function.addLocation(fragment.startLocation);
					} else {
						function.setStartLocation(fragment.startLocation);
					}
					lastStatement = fragment.lastStatement;
				}
			} else if (node instanceof FunctionDeclarationNode) {
				unprocessedFunctions.add((FunctionDefinitionNode) node);
				containingScopes.put((FunctionDefinitionNode) node, newScope);
			} else if (node instanceof StatementNode) {
				Statement newStatement;

				if (usedLocation || location == null) {
					newStatement = statement(function, lastStatement,
							(StatementNode) node, newScope);
				} else {
					newStatement = statement(location, guard, function,
							lastStatement, (StatementNode) node, newScope);
				}
				lastStatement = newStatement;
				usedLocation = true;
			} else {
				throw new CIVLUnimplementedFeatureException(
						"Unsupported block element", sourceOf(node));
			}
		}
		if (lastStatement == null) {
			if (location == null) {
				location = factory.location(sourceOfBeginning(statement),
						newScope);
			}
			lastStatement = factory.noopStatement(location.getSource(),
					location);
			function.setStartLocation(location);
		}
		return lastStatement;
	}

	private Statement forLoop(Function function, Statement lastStatement,
			ForLoopNode statement, Scope scope) {
		CIVLSource startSource = sourceOfBeginning(statement);

		return forLoop(factory.location(startSource, scope),
				factory.booleanLiteralExpression(startSource, true), function,
				lastStatement, statement, scope);
	}

	/**
	 * 
	 * @param location
	 *            start location for the for loop
	 * @param guard
	 * @param function
	 * @param lastStatement
	 * @param statement
	 * @param scope
	 * @return
	 */
	private Statement forLoop(Location location, Expression guard,
			Function function, Statement lastStatement, ForLoopNode statement,
			Scope scope) {
		ForLoopInitializerNode init = statement.getInitializer();
		Statement initStatement = lastStatement;
		Scope newScope = factory.scope(sourceOf(statement), scope,
				new LinkedHashSet<Variable>(), function);
		Statement loopBody;
		Expression condition;
		Statement incrementer;
		Statement loopExit;

		location.setScope(newScope);
		if (init != null) {
			if (init instanceof ExpressionNode) {
				initStatement = expressionStatement(location,
						factory.booleanLiteralExpression(sourceOf(init), true),
						function, (ExpressionNode) init, scope);
				initStatement.setGuard(guard);
				if (lastStatement != null) {
					lastStatement.setTarget(location);
					function.addLocation(location);
				} else {
					lastStatement = initStatement;
					function.setStartLocation(location);
				}
			} else if (init instanceof DeclarationListNode) {
				for (int i = 0; i < ((DeclarationListNode) init).numChildren(); i++) {
					VariableDeclarationNode declaration = ((DeclarationListNode) init)
							.getSequenceChild(i);
					processVariableDeclaration(newScope, declaration);
					if (declaration.getInitializer() != null) {
						initStatement = factory.assignStatement(
								sourceOf(init),
								location,
								factory.variableExpression(sourceOf(declaration
										.getIdentifier()),
										newScope.getVariable(newScope
												.numVariables() - 1)),
								expression((ExpressionNode) declaration
										.getInitializer(), newScope));
						initStatement.setGuard(guard);
						if (lastStatement != null) {
							lastStatement.setTarget(location);
							function.addLocation(location);
						} else {
							lastStatement = initStatement;
							function.setStartLocation(location);
						}
					}
				}
			} else {
				throw new CIVLInternalException(
						"A for loop initializer must be an expression or a declaration list.",
						sourceOf(init));
			}
		}
		condition = booleanExpression(statement.getCondition(), newScope);
		loopBody = statement(function, initStatement, statement.getBody(),
				newScope);
		for (Statement outgoing : initStatement.target().outgoing()) {
			Expression outGuard = outgoing.guard();
			CIVLSource augmentedSource = sourceOfSpan(outGuard.getSource(),
					condition.getSource());

			outgoing.setGuard(factory.binaryExpression(augmentedSource,
					BINARY_OPERATOR.AND, outGuard, condition));
		}
		incrementer = forLoopIncrementer(function, loopBody,
				statement.getIncrementer(), newScope);
		incrementer.setTarget(initStatement.target());
		loopExit = factory.noopStatement(condition.getSource(),
				initStatement.target());
		loopExit.setGuard(factory.unaryExpression(condition.getSource(),
				UNARY_OPERATOR.NOT, condition));
		return loopExit;
	}

	private Statement forLoopIncrementer(Function function,
			Statement lastStatement, ExpressionNode incrementer, Scope scope) {
		Location location = factory.location(sourceOfBeginning(incrementer),
				scope);
		CIVLSource source = sourceOf(incrementer);
		Statement result;

		function.addLocation(location);
		// TODO: why can't this be treated like any statement?
		if (incrementer instanceof OperatorNode) {
			OperatorNode expression = (OperatorNode) incrementer;
			switch (expression.getOperator()) {
			case ASSIGN: {
				LHSExpression lhs = (LHSExpression) expression(
						expression.getArgument(0), scope);
				Expression rhs = expression(expression.getArgument(1), scope);

				result = factory.assignStatement(source, location, lhs, rhs);
				break;
			}
			case PLUSEQ:
			case MINUSEQ:
			case TIMESEQ:
			case DIVEQ:
			case MODEQ:
				throw new CIVLInternalException(
						"Side-effects should have been removed", source);
			case BITANDEQ:
			case BITOREQ:
			case BITXOREQ:
			case SHIFTLEFTEQ:
			case SHIFTRIGHTEQ:
				throw new CIVLUnimplementedFeatureException(
						"bit-level operations", source);
			default:
				// No effect for ops without assignments.
				result = factory.noopStatement(source, location);
			}
		} else {
			result = factory.noopStatement(source, location);
		}
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	private Statement whileLoop(Function function, Statement lastStatement,
			LoopNode statement, Scope scope) {
		CIVLSource source = sourceOf(statement);
		Statement loopExit;
		Scope newScope = factory.scope(source, scope,
				new LinkedHashSet<Variable>(), function);
		Statement loopBody;
		Expression condition;
		Location loopEntrance;

		condition = booleanExpression(statement.getCondition(), newScope);
		loopBody = statement(function, lastStatement, statement.getBody(),
				newScope);
		if (lastStatement != null) {
			if (lastStatement.target() == null) {
				// If the loop body is an empty block, the result of evaluating
				// the
				// loop body will be lastStatement. When this happens,
				// lastStatement!=null, but lastStatement.target()==null (since
				// it hasn't been set yet). If that's the case, make a new
				// location.
				loopEntrance = factory.location(sourceOfBeginning(statement),
						newScope);
				lastStatement.setTarget(loopEntrance);
				function.addLocation(loopEntrance);
			} else {
				loopEntrance = lastStatement.target();
			}
		} else {
			loopEntrance = function.startLocation();
		}
		assert loopEntrance != null;
		if (loopBody.equals(lastStatement)) {
			loopBody = factory.noopStatement(source, loopEntrance);
		}
		for (Statement outgoing : loopEntrance.outgoing()) {
			outgoing.setGuard(factory.binaryExpression(
					sourceOfSpan(outgoing.guard().getSource(),
							condition.getSource()), BINARY_OPERATOR.AND,
					outgoing.guard(), condition));
		}
		loopBody.setTarget(loopEntrance);
		loopExit = factory
				.noopStatement(loopEntrance.getSource(), loopEntrance);
		loopExit.setGuard(factory.unaryExpression(condition.getSource(),
				UNARY_OPERATOR.NOT, condition));
		return loopExit;
	}

	private Statement wait(Function function, Statement lastStatement,
			WaitNode statement, Scope scope) {
		CIVLSource source = sourceOf(statement);
		Location location = factory.location(sourceOfBeginning(statement),
				scope);
		Statement result;

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		function.addLocation(location);
		result = factory.joinStatement(source, location,
				expression(statement.getExpression(), scope));
		return result;
	}

	private Statement noop(Function function, Statement lastStatement,
			NullStatementNode statement, Scope scope) {
		Location location = factory.location(sourceOfBeginning(statement),
				scope);

		return noop(location, function, lastStatement, statement, scope);
	}

	private Statement noop(Location location, Function function,
			Statement lastStatement, NullStatementNode statement, Scope scope) {
		Statement result = factory.noopStatement(sourceOf(statement), location);

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
		Expression guard = booleanExpression(statement.getGuard(), scope);
		Iterator<Statement> iter;

		// A $true or $false guard is translated as 1 or 0, but this causes
		// trouble later.
		if (guard instanceof IntegerLiteralExpression) {
			if (((IntegerLiteralExpression) guard).value().intValue() == 0) {
				guard = factory.booleanLiteralExpression(guard.getSource(),
						false);
			} else {
				guard = factory.booleanLiteralExpression(guard.getSource(),
						true);
			}
		}
		if (lastStatement != null) {
			iter = lastStatement.target().outgoing().iterator();
		} else {
			iter = function.startLocation().outgoing().iterator();
		}
		while (iter.hasNext()) {
			Statement s = iter.next();

			if (isTrue(s.guard())) {
				s.setGuard(guard);
			} else if (isTrue(guard)) {
				s.setGuard(s.guard()); // argument was just "guard" -sfs
			} else {
				s.setGuard(factory.binaryExpression(
						sourceOfSpan(s.guard().getSource(), guard.getSource()),
						BINARY_OPERATOR.AND, s.guard(), guard));
			}
		}
		result.setGuard(guard);
		return result;
	}

	private Statement when(Location location, Expression guard,
			Function function, Statement lastStatement, WhenNode statement,
			Scope scope) {
		Expression newGuard = booleanExpression(statement.getGuard(), scope);
		Statement result;

		if (isTrue(newGuard)) {
			newGuard = guard;
		} else if (!isTrue(guard)) {
			newGuard = factory.binaryExpression(
					sourceOfSpan(guard.getSource(), newGuard.getSource()),
					BINARY_OPERATOR.AND, guard, newGuard);
		}
		result = statement(location, newGuard, function, lastStatement,
				statement.getBody(), scope);
		return result;
	}

	private Statement choose(Function function, Statement lastStatement,
			ChooseStatementNode statement, Scope scope) {
		Location startLocation = factory.location(sourceOfBeginning(statement),
				scope);
		Location endLocation = factory.location(sourceOfEnd(statement), scope);
		Statement result = factory.noopStatement(endLocation.getSource(),
				endLocation);
		Expression guard = factory.booleanLiteralExpression(
				startLocation.getSource(), true);
		int defaultOffset = 0;

		if (lastStatement != null) {
			lastStatement.setTarget(startLocation);
		} else {
			function.setStartLocation(startLocation);
		}
		function.addLocation(startLocation);
		if (statement.getDefaultCase() != null) {
			defaultOffset = 1;
		}
		for (int i = 0; i < statement.numChildren() - defaultOffset; i++) {
			StatementNode childNode = statement.getSequenceChild(i);
			Statement caseStatement = statement(startLocation,
					factory.booleanLiteralExpression(
							sourceOfBeginning(childNode), true), function,
					lastStatement, childNode, scope);

			caseStatement.setTarget(endLocation);
		}

		Iterator<Statement> iter = startLocation.outgoing().iterator();

		// Compute the guard for the default statement
		while (iter.hasNext()) {
			Expression statementGuard = iter.next().guard();

			if (isTrue(guard)) {
				guard = statementGuard;
			} else if (isTrue(statementGuard)) {
				// Keep current guard
			} else {
				guard = factory.binaryExpression(
						sourceOfSpan(guard.getSource(),
								statementGuard.getSource()),
						BINARY_OPERATOR.OR, guard, statementGuard);
			}
		}
		if (statement.getDefaultCase() != null) {
			Statement defaultStatement = statement(startLocation,
					factory.unaryExpression(guard.getSource(),
							UNARY_OPERATOR.NOT, guard), function,
					lastStatement, statement.getDefaultCase(), scope);

			defaultStatement.setTarget(endLocation);
		}
		return result;
	}

	private Statement gotoStatement(Function function, Statement lastStatement,
			GotoNode statement, Scope scope) {
		Location location = factory.location(sourceOfBeginning(statement),
				scope);
		Statement noop = factory.noopStatement(sourceOf(statement), location);
		OrdinaryLabelNode label = ((Label) statement.getLabel().getEntity())
				.getDefinition();

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		gotoStatements.put(noop, label);
		return noop;
	}

	private Statement labeledStatement(Function function,
			Statement lastStatement, LabeledStatementNode statement, Scope scope) {
		Statement result = statement(function, lastStatement,
				statement.getStatement(), scope);

		if (lastStatement != null) {
			labeledLocations.put(statement.getLabel(), lastStatement.target());
		} else {
			labeledLocations
					.put(statement.getLabel(), function.startLocation());
		}
		return result;
	}

	private Statement labeledStatement(Location location, Expression guard,
			Function function, Statement lastStatement,
			LabeledStatementNode statement, Scope scope) {
		Statement result = statement(location, guard, function, lastStatement,
				statement.getStatement(), scope);

		if (lastStatement != null) {
			labeledLocations.put(statement.getLabel(), lastStatement.target());
		} else {
			labeledLocations
					.put(statement.getLabel(), function.startLocation());
		}
		return result;
	}

	private Statement returnStatement(Function function,
			Statement lastStatement, ReturnNode statement, Scope scope) {
		Location location = factory.location(sourceOfBeginning(statement),
				scope);
		Statement result;
		Expression expression;

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (statement.getExpression() != null) {
			expression = expression(statement.getExpression(), scope);
		} else
			expression = null;
		result = factory.returnStatement(sourceOf(statement), location,
				expression);
		return result;
	}

	private Statement switchStatement(Function function,
			Statement lastStatement, SwitchNode statement, Scope scope) {
		Location location = factory.location(sourceOfBeginning(statement),
				scope);
		Expression guard = factory.booleanLiteralExpression(
				location.getSource(), true);

		return switchStatement(location, guard, function, lastStatement,
				statement, scope);
	}

	private Statement switchStatement(Location location, Expression guard,
			Function function, Statement lastStatement, SwitchNode statement,
			Scope scope) {
		Statement result = null;
		Iterator<LabeledStatementNode> cases = statement.getCases();
		Expression condition = expression(statement.getCondition(), scope);
		/** Collect case guards to determine guard for default case. */
		Expression combinedCaseGuards = guard;
		Statement bodyGoto;

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		while (cases.hasNext()) {
			LabeledStatementNode caseStatement = cases.next();
			// CIVLSource caseSource = sourceOf(caseStatement);
			SwitchLabelNode label;
			Expression caseGuard;
			Expression combinedGuard;
			Statement caseGoto;

			assert caseStatement.getLabel() instanceof SwitchLabelNode;
			label = (SwitchLabelNode) caseStatement.getLabel();
			caseGuard = factory.binaryExpression(
					sourceOf(label.getExpression()), BINARY_OPERATOR.EQUAL,
					condition, expression(label.getExpression(), scope));
			if (!isTrue(guard)) {
				combinedGuard = factory.binaryExpression(
						sourceOfSpan(guard.getSource(), caseGuard.getSource()),
						BINARY_OPERATOR.AND, guard, caseGuard);
			} else {
				combinedGuard = caseGuard;
			}
			combinedCaseGuards = factory.binaryExpression(
					sourceOfSpan(caseGuard.getSource(),
							combinedCaseGuards.getSource()),
					BINARY_OPERATOR.AND, caseGuard, combinedCaseGuards);
			caseGoto = factory.noopStatement(sourceOfBeginning(caseStatement),
					location);
			caseGoto.setGuard(combinedGuard);
			gotoStatements.put(caseGoto, label);
		}
		if (statement.getDefaultCase() != null) {
			LabelNode label = statement.getDefaultCase().getLabel();
			Statement defaultGoto = factory.noopStatement(
					sourceOf(statement.getDefaultCase()), location);

			defaultGoto.setGuard(factory.unaryExpression(
					sourceOfBeginning(statement.getDefaultCase()),
					UNARY_OPERATOR.NOT, combinedCaseGuards));
			gotoStatements.put(defaultGoto, label);
		}
		bodyGoto = factory.noopStatement(location.getSource(), location);
		bodyGoto.setGuard(factory.booleanLiteralExpression(
				bodyGoto.getSource(), false));
		result = statement(function, bodyGoto, statement.getBody(), scope);
		return result;
	}

	/**
	 * Adds the locations and statements in a sequence of statements to a
	 * function. Also adds the statements to the list.
	 * 
	 * @param fragment
	 * @param function
	 * @param addFirstLocation
	 */
	private void addToFunction(Fragment fragment, Function function,
			boolean addFirstLocation, Vector<Statement> list) {
		Statement statement = fragment.startLocation.getSoleOutgoing();

		if (addFirstLocation)
			function.addLocation(fragment.startLocation);
		while (statement != null) {
			Location location = statement.target();

			list.add(statement);
			function.addStatement(statement);
			if (location == null)
				break;
			function.addLocation(location);
			if (statement == fragment.lastStatement)
				break;
			statement = location.getSoleOutgoing();
		}
	}

	// Exported methods....................................................

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
	public void buildModel() {
		Identifier systemID = factory.identifier(factory.systemSource(),
				"_CIVL_system");
		Function system = factory.function(sourceOf(program.getAST()
				.getRootNode()), systemID, new Vector<Variable>(), null, null,
				null);
		ASTNode rootNode = program.getAST().getRootNode();
		Location returnLocation;
		Statement returnStatement;
		FunctionDefinitionNode mainFunction = null;
		Statement mainBody;
		Vector<Statement> initializations = new Vector<Statement>();

		systemScope = system.outerScope();
		containingScopes = new LinkedHashMap<FunctionDefinitionNode, Scope>();
		callStatements = new LinkedHashMap<CallOrSpawnStatement, FunctionDefinitionNode>();
		functionMap = new LinkedHashMap<FunctionDefinitionNode, Function>();
		unprocessedFunctions = new Vector<FunctionDefinitionNode>();
		for (int i = 0; i < rootNode.numChildren(); i++) {
			ASTNode node = rootNode.child(i);

			if (node instanceof VariableDeclarationNode
					|| node instanceof TypedefDeclarationNode
					|| node instanceof StructureOrUnionTypeNode) {
				Fragment fragment;

				if (node instanceof VariableDeclarationNode)
					fragment = translateVariableDeclarationNode(null,
							systemScope, (VariableDeclarationNode) node);
				else if (node instanceof TypedefDeclarationNode)
					fragment = translateTypedefNode(null, systemScope,
							(TypedefDeclarationNode) node);
				else if (node instanceof StructureOrUnionTypeNode)
					fragment = this.translateStructureOrUnionTypeNode(null,
							systemScope, (StructureOrUnionTypeNode) node);
				else
					throw new RuntimeException("unreachable");
				if (fragment != null) {
					// add locations and statements to fragment and
					// statements to initializations:
					if (!initializations.isEmpty())
						initializations.lastElement().setTarget(
								fragment.startLocation);
					addToFunction(fragment, system, true, initializations);
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
				// nothing to do
			} else {
				throw new CIVLInternalException("Unsupported declaration type",
						sourceOf(node));
			}
		}
		if (mainFunction == null) {
			throw new CIVLException("Program must have a main function.",
					sourceOf(rootNode));
		}
		labeledLocations = new LinkedHashMap<LabelNode, Location>();
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
			returnLocation = factory.location(
					sourceOfEnd(mainFunction.getBody()), system.outerScope());
			returnStatement = factory.returnStatement(
					returnLocation.getSource(), returnLocation, null);
			if (mainBody != null) {
				mainBody.setTarget(returnLocation);
			} else {
				system.setStartLocation(returnLocation);
			}
			system.addLocation(returnLocation);
			system.addStatement(returnStatement);
		}
		model = factory.model(system.getSource(), system);
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
									sourceOfSpan(postcondition.getSource(),
											componentExpression.getSource()),
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
									sourceOfSpan(precondition.getSource(),
											componentExpression.getSource()),
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
		for (CallOrSpawnStatement statement : callStatements.keySet()) {
			statement
					.setFunction(functionMap.get(callStatements.get(statement)));
		}
		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledLocations.get(gotoStatements.get(s)));
		}
	}

	public Model getModel() {
		return model;
	}
}

/**
 * A fragment of a CIVL model. Consists of a start location and a last
 * statement. Why not always generate next location.
 * 
 * @author siegel
 * 
 */
class Fragment {

	public Location startLocation;

	public Statement lastStatement;

	public Fragment(Location startLocation, Statement lastStatement) {
		this.startLocation = startLocation;
		this.lastStatement = lastStatement;
	}
}
