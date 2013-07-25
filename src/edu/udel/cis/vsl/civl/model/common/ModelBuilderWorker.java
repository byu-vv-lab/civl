package edu.udel.cis.vsl.civl.model.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.abc.ast.type.IF.PointerType;
import edu.udel.cis.vsl.abc.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType;
import edu.udel.cis.vsl.abc.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.civl.err.CIVLException;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Function;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.SystemFunction;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
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
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType.PRIMITIVE_TYPE;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.expression.CommonExpression;

/**
 * Does the main work translating a single ABC Program to a model.
 * 
 * TODO: translate all conversions to casts.
 * 
 * Break cycles by brekaing up constructions of struct types into two parts.
 * 
 * Add void type and use it.
 * 
 * Make a CIVLSource and CIVLSourceable.  One implementation will
 * be CIVL_ABC_Source which wraps an ABC Source.  Constructors
 * to all CIVL model statements, expressions, ..., must take a source.
 * 
 * Following will implement CIVLSourceable:
 * Function, Identifier, Scope, Expression, Location, Statement, Variable.
 * Type: no, because want two types to be the same (equal).
 * Make CIVLSourceable first argument to every construct. Update
 * model factory as well.  Add method source(ASTNode) in model builder
 * worker to create a CIVLSource from an ASTNode (wrap it).
 * 
 * 
 * 
 * 
 * @author siegel
 * 
 */
public class ModelBuilderWorker {

	// Fields..............................................................

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
	 * Variable accumulates the AST definition node of every function definition
	 * in the AST.
	 */
	private Vector<FunctionDefinitionNode> unprocessedFunctions;

	/**
	 * For each function definition node, the CIVL static scope containing that
	 * definition.
	 */
	private Map<FunctionDefinitionNode, Scope> containingScopes;

	/**
	 * Map containing all call and spawn statements in the model. This is built
	 * up as call statements are processed. On a later pass, we iterate over
	 * this map and set the function fields of the call/spawn statements to the
	 * corresponding model Function object.
	 */
	private Map<CallOrSpawnStatement, FunctionDefinitionNode> callStatements;

	/**
	 * Map from all ABC function definition nodes to corresponding CIVL Function
	 * object.
	 */
	private Map<FunctionDefinitionNode, Function> functionMap;

	/**
	 * Currently being used to store information for a single function, the last
	 * one to be processed. Unclear about how this works. Maps associated AST
	 * label nodes to the corresponding model locations.
	 */
	private Map<LabelNode, Location> labeledLocations;

	/**
	 * Also being used for single function (the one being processed). Maps from
	 * CIVL goto statements to the corresponding label nodes.
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
		setUpSystemFunctions();
	}

	// Helper methods......................................................

	/**
	 * Is the given (static) model type the integer type?
	 * 
	 * @param type
	 *            a static type
	 * @return true iff types is the integer type
	 */
	private boolean isIntegerType(CIVLType type) {
		return type instanceof CIVLPrimitiveType
				&& ((CIVLPrimitiveType) type).primitiveType() == PRIMITIVE_TYPE.INT;
	}

	/**
	 * Is the given (static) model type the integer or real type?
	 * 
	 * @param type
	 *            a static type
	 * @return true iff type is integer or real
	 */
	private boolean isNumericType(CIVLType type) {
		if (type instanceof CIVLPrimitiveType) {
			PRIMITIVE_TYPE kind = ((CIVLPrimitiveType) type).primitiveType();

			return kind == PRIMITIVE_TYPE.INT || kind == PRIMITIVE_TYPE.REAL;
		}
		return false;
	}

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
		SystemFunction malloc = factory.systemFunction(factory
				.identifier("$malloc"));
		SystemFunction free = factory.systemFunction(factory
				.identifier("$free"));
		SystemFunction printf = factory.systemFunction(factory
				.identifier("printf"));

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
	 * @return the new model Function object
	 */
	private Function processFunction(FunctionDefinitionNode functionNode,
			Scope scope) {
		Function result;
		Identifier name = factory.identifier(functionNode.getName());
		Vector<Variable> parameters = new Vector<Variable>();
		FunctionTypeNode functionTypeNode = functionNode.getTypeNode();
		CIVLType returnType = translateTypeNode(functionTypeNode
				.getReturnType());
		Statement body;

		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		for (int i = 0; i < functionTypeNode.getParameters().numChildren(); i++) {
			CIVLType type = translateTypeNode(functionTypeNode.getParameters()
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
	private void processVariableDeclaration(Scope scope,
			VariableDeclarationNode node) {
		CIVLType type = translateTypeNode(node.getTypeNode());
		Identifier name = factory.identifier(node.getName());
		Variable variable = factory.variable(type, name, scope.numVariables());

		if (type instanceof CIVLArrayType) {
			ExpressionNode extentNode = ((ArrayTypeNode) node.getTypeNode())
					.getExtent();
			Expression extent;

			if (extentNode != null) {
				extent = expression(extentNode, scope);
				variable.setExtent(extent);
			}
		}
		if (node.getTypeNode().isInputQualified()) {
			variable.setIsExtern(true);
		}
		scope.addVariable(variable);
		variable.setNode(node);
	}

	private CIVLType translateBasicType(StandardBasicType basicType,
			Source source) {
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
			Source source) {
		// TODO: break cycles. break into two parts.
		// first create the incomplete type and put in map.
		// then complete it.
		String tag = type.getTag();

		if (type.isUnion())
			throw new CIVLUnimplementedFeatureException("Union types", source);
		// civlc.h defines $proc as struct __proc__
		if ("__proc__".equals(tag))
			return factory.processType();
		// civlc.h defines $heap as struct __heap__
		if ("__heap__".equals(tag))
			return factory.heapType();
		else {
			int numFields = type.getNumFields();
			List<StructField> civlFields = new LinkedList<StructField>();

			for (int i = 0; i < numFields; i++) {
				Field field = type.getField(i);

				String name = field.getName();
				Type fieldType = field.getType();
				CIVLType civlFieldType = translateType(fieldType, source);
				Identifier identifier = factory.identifier(name);
				StructField civlField = factory.structField(identifier,
						civlFieldType);

				civlFields.add(civlField);
			}
			return factory.structType(factory.identifier(tag), civlFields);
		}
	}

	/**
	 * Working on replacing process type with this.
	 * 
	 * @param abcType
	 * @return
	 */
	private CIVLType translateType(Type abcType, Source source) {
		CIVLType result = typeMap.get(abcType);

		if (result == null) {
			TypeKind kind = abcType.kind();

			switch (kind) {
			case ARRAY: {
				ArrayType arrayType = (ArrayType) abcType;

				result = factory.arrayType(translateType(
						arrayType.getElementType(), source));
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
				CIVLType baseType = translateType(referencedType, source);

				result = factory.pointerType(baseType);
				break;
			}
			case PROCESS:
				result = factory.processType();
				break;
			case QUALIFIED:
				result = translateType(
						((QualifiedObjectType) abcType).getBaseType(), source);
				break;
			case STRUCTURE_OR_UNION:
				result = translateStructureOrUnion(
						(StructureOrUnionType) abcType, source);
				break;
			case VOID:
				// TODO: make a CIVL void type
				result = null;
				break;
			case ATOMIC:
			case FUNCTION:
			case ENUMERATION:
				throw new CIVLUnimplementedFeatureException("Enumerated types",
						source);
			default:
				throw new CIVLInternalException("Unknown type: " + abcType,
						source);
			}
			typeMap.put(abcType, result);
		}
		return result;
	}

	private CIVLType translateTypeNode(TypeNode typeNode) {
		return translateType(typeNode.getType(), typeNode.getSource());
	}

	// /**
	// * Translates an AST type node to a Model Type. TODO: Why??? Why not use a
	// * CIVL Type instead?
	// *
	// * @param typeNode
	// * AST type node
	// * @return the corresponding model Type
	// */
	// private CIVLType processType(TypeNode typeNode) {
	// TypeNodeKind kind = typeNode.kind();
	// CIVLType result;
	//
	// // TODO: deal with more types.
	//
	// if (kind == TypeNodeKind.VOID)
	// result = null;
	// else if (kind == TypeNodeKind.BASIC) {
	// switch (((BasicTypeNode) typeNode).getBasicTypeKind()) {
	// case SHORT:
	// case UNSIGNED_SHORT:
	// case INT:
	// case UNSIGNED:
	// case LONG:
	// case UNSIGNED_LONG:
	// case LONG_LONG:
	// case UNSIGNED_LONG_LONG:
	// return factory.integerType();
	// case FLOAT:
	// case DOUBLE:
	// case LONG_DOUBLE:
	// return factory.realType();
	// case BOOL:
	// return factory.booleanType();
	// case CHAR:
	// case DOUBLE_COMPLEX:
	// case FLOAT_COMPLEX:
	// case LONG_DOUBLE_COMPLEX:
	// case SIGNED_CHAR:
	// case UNSIGNED_CHAR:
	// default:
	// throw new CIVLUnimplementedFeatureException("types of kind "
	// + typeNode.kind(), typeNode.getSource());
	// }
	// } else if (typeNode.kind() == TypeNodeKind.ARRAY) {
	// return factory.arrayType(processType(((ArrayTypeNode) typeNode)
	// .getElementType()));
	// } else if (typeNode.kind() == TypeNodeKind.POINTER) {
	// return factory.pointerType(processType(((PointerTypeNode) typeNode)
	// .referencedType()));
	// } else if (typeNode.kind() == TypeNodeKind.TYPEDEF_NAME) {
	// return typedefMap
	// .get(((TypedefNameNode) typeNode).getName().name());
	// } else if (typeNode.kind() == TypeNodeKind.STRUCTURE_OR_UNION) {
	// SequenceNode<FieldDeclarationNode> fieldNodes =
	// ((StructureOrUnionTypeNode) typeNode)
	// .getStructDeclList();
	// List<StructField> fields = new Vector<StructField>();
	// Identifier structName = factory
	// .identifier(((StructureOrUnionTypeNode) typeNode).getTag()
	// .name());
	//
	// for (int i = 0; i < fieldNodes.numChildren(); i++) {
	// FieldDeclarationNode fieldNode = fieldNodes.getSequenceChild(i);
	// Identifier name = factory.identifier(fieldNode.getName());
	// CIVLType type = processType(fieldNode.getTypeNode());
	//
	// fields.add(factory.structField(name, type));
	// }
	// result = factory.structType(structName, fields);
	// } else
	// throw new CIVLUnimplementedFeatureException("types of kind "
	// + typeNode.kind(), typeNode.getSource());
	// return result;
	// }

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
			result = factory.resultExpression();
		} else if (expression instanceof SelfNode) {
			result = factory.selfExpression();
		} else if (expression instanceof CastNode) {
			result = castExpression((CastNode) expression, scope);
		} else
			throw new CIVLUnimplementedFeatureException("expressions of type "
					+ expression.getClass().getSimpleName(),
					expression.getSource());
		result.setNode(expression);
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
				result = factory.binaryExpression(BINARY_OPERATOR.NOT_EQUAL,
						result,
						factory.integerLiteralExpression(BigInteger.ZERO));
			} else if (result.getExpressionType().equals(factory.realType())) {
				result = factory.binaryExpression(BINARY_OPERATOR.NOT_EQUAL,
						result, factory.realLiteralExpression(BigDecimal.ZERO));
			} else {
				throw new CIVLInternalException(
						"Unable to convert expression to boolean type",
						expression.getSource());
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
		CIVLType castType = translateTypeNode(expression.getCastType());
		Expression castExpression = expression(expression.getArgument(), scope);

		result = factory.castExpression(castType, castExpression);
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
					fieldIdentifier.getSource());
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
				factory.dereferenceExpression(struct),
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
		Expression result = factory.dotExpression(struct,
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
			CIVLArrayType arrayType = (CIVLArrayType) type;
			CIVLType elementType = arrayType.baseType();
			Expression zero = factory.integerLiteralExpression(BigInteger.ZERO);
			LHSExpression subscript = factory.subscriptExpression(
					(LHSExpression) expression, zero);
			Expression pointer = factory.addressOfExpression(subscript);
			Scope scope = expression.expressionScope();
			ASTNode node = expression.getNode();

			zero.setExpressionScope(scope);
			subscript.setExpressionScope(scope);
			pointer.setExpressionScope(scope);
			zero.setNode(node);
			subscript.setNode(node);
			pointer.setNode(node);
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
			result = factory.subscriptExpression((LHSExpression) lhs, rhs);
		} else {
			CIVLType rhsType = rhs.getExpressionType();
			Expression pointerExpr, indexExpr;

			if (lhsType instanceof CIVLPointerType) {
				if (!isIntegerType(rhsType))
					throw new CIVLInternalException(
							"Expected expression of integer type",
							rhs.getSource());
				pointerExpr = lhs;
				indexExpr = rhs;
			} else if (isIntegerType(lhsType)) {
				if (!(rhsType instanceof CIVLPointerType))
					throw new CIVLInternalException(
							"Expected expression of pointer type",
							rhs.getSource());
				pointerExpr = rhs;
				indexExpr = lhs;
			} else
				throw new CIVLInternalException(
						"Expected one argument of integer type and one of pointer type",
						subscriptNode.getSource());
			result = factory.binaryExpression(BINARY_OPERATOR.POINTER_ADD,
					pointerExpr, indexExpr);
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
			result = factory.addressOfExpression((LHSExpression) arguments
					.get(0));
			break;
		case DEREFERENCE:
			result = factory.dereferenceExpression(arguments.get(0));
			break;
		case CONDITIONAL:
			result = factory.conditionalExpression(arguments.get(0),
					arguments.get(1), arguments.get(2));
			break;
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
		case PLUS: {
			Expression arg0 = arguments.get(0);
			Expression arg1 = arguments.get(1);
			CIVLType type0 = arg0.getExpressionType();
			CIVLType type1 = arg1.getExpressionType();
			boolean isNumeric0 = isNumericType(type0);
			boolean isNumeric1 = isNumericType(type1);

			if (isNumeric0 && isNumeric1) {
				result = factory.binaryExpression(BINARY_OPERATOR.PLUS, arg0,
						arg1);
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
							"Expected at least one numeric argument",
							expression.getSource());
				if (!(pointer.getExpressionType() instanceof CIVLPointerType))
					throw new CIVLInternalException(
							"Expected expression of pointer type",
							pointer.getSource());
				if (!isIntegerType(offset.getExpressionType()))
					throw new CIVLInternalException(
							"Expected expression of integer type",
							offset.getSource());
				result = factory.binaryExpression(BINARY_OPERATOR.POINTER_ADD,
						pointer, offset);
			}
			break;
		}
		case SUBSCRIPT:
			throw new CIVLInternalException("unreachable",
					expression.getSource());
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
			throw new CIVLUnimplementedFeatureException(
					"Unsupported operator: " + expression.getOperator()
							+ " in expression " + expression);
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

	private Expression constant(ConstantNode constant) {
		LiteralExpression result = null;
		edu.udel.cis.vsl.abc.ast.type.IF.Type convertedType = constant
				.getConvertedType();

		if (convertedType.kind() == TypeKind.PROCESS) {
			assert constant.getStringRepresentation().equals("$self");
			return factory.selfExpression();
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
			result = factory.integerLiteralExpression(BigInteger.valueOf(Long
					.parseLong(constant.getStringRepresentation())));
			break;
		case FLOAT:
		case DOUBLE:
		case LONG_DOUBLE:
			result = factory.realLiteralExpression(BigDecimal.valueOf(Double
					.parseDouble(constant.getStringRepresentation())));
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
			result = factory.booleanLiteralExpression(value);
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
					statement.getSource());
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
					+ statement.getClass().getSimpleName(),
					statement.getSource());
		function.addStatement(result);
		return result;
	}

	/**
	 * An if statement.
	 */
	private Statement ifStatement(Function function, Statement lastStatement,
			IfNode statement, Scope scope) {
		return ifStatement(factory.location(scope), function, lastStatement,
				statement, scope);
	}

	private Statement ifStatement(Location location, Function function,
			Statement lastStatement, IfNode statement, Scope scope) {
		Expression expression = expression(statement.getCondition(), scope);
		Statement trueBranch = statement(location, expression, function,
				lastStatement, statement.getTrueBranch(), scope);
		Statement falseBranch;
		Location exitLocation = factory.location(scope);
		Statement result;

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (statement.getFalseBranch() == null) {
			falseBranch = factory.noopStatement(location);
			falseBranch.setGuard(factory.unaryExpression(UNARY_OPERATOR.NOT,
					expression));
			falseBranch.setNode(statement);
		} else {
			falseBranch = statement(location,
					factory.unaryExpression(UNARY_OPERATOR.NOT, expression),
					function, lastStatement, statement.getFalseBranch(), scope);
		}
		function.addLocation(exitLocation);
		trueBranch.setTarget(exitLocation);
		falseBranch.setTarget(exitLocation);
		result = factory.noopStatement(exitLocation);
		result.setNode(statement);
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
		result.setNode(statement);
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
		result.setNode(statement);
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
		Location location = factory.location(scope);
		Expression guard = factory.booleanLiteralExpression(true);

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
		if (result.guard().equals(factory.booleanLiteralExpression(true))) {
			result.setGuard(guard);
		} else if (!guard.equals(factory.booleanLiteralExpression(true))) {
			result.setGuard(factory.binaryExpression(BINARY_OPERATOR.AND,
					guard, result.guard()));
		}
		result.setNode(statement);
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
			arguments.add(expression(callNode.getArgument(i), scope));
		}
		result = factory
				.callOrSpawnStatement(location, isCall, null, arguments);
		result.setLhs(lhs);
		if (systemFunctions.containsKey(functionName)) {
			((CallOrSpawnStatement) result).setFunction(systemFunctions
					.get(functionName));
		} else {
			callStatements.put((CallOrSpawnStatement) result,
					functionDefinition);
		}
		result.setNode(callNode);
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
		Statement result = null;

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
						expression.getSource());
			default:
				// since side-effects have been removed,
				// the only expressions remaining with side-effects
				// are assignments. all others are equivalent to no-op
				result = factory.noopStatement(location);
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
					expressionStatement.getSource());
		result.setNode(expressionStatement);
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
		LHSExpression lhsExpression = (LHSExpression) expression(lhs, scope);

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
	private Statement assign(Location location, LHSExpression lhs,
			ExpressionNode rhs, Scope scope) {
		Statement result = null;

		if (rhs instanceof FunctionCallNode) {
			result = callOrSpawn(location, true, lhs, (FunctionCallNode) rhs,
					scope);
		} else if (rhs instanceof SpawnNode) {
			result = callOrSpawn(location, true, lhs,
					((SpawnNode) rhs).getCall(), scope);
		} else {
			result = factory.assignStatement(location, lhs,
					arrayToPointer(expression(rhs, scope)));
			result.setNode(rhs);
		}
		return result;
	}

	private Statement compoundStatement(Function function,
			Statement lastStatement, CompoundStatementNode statement,
			Scope scope) {
		return compoundStatement(null, null, function, lastStatement,
				statement, scope);

	}

	private Statement compoundStatement(Location location, Expression guard,
			Function function, Statement lastStatement,
			CompoundStatementNode statement, Scope scope) {
		Scope newScope = factory.scope(scope, new LinkedHashSet<Variable>(),
				function);
		boolean usedLocation = false;

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
					Statement newStatement;

					if (usedLocation || location == null) {
						location = factory.location(newScope);
						usedLocation = true;
					} else {
						usedLocation = true;
					}
					newStatement = assign(location,
							factory.variableExpression(newScope
									.getVariable(newScope.numVariables() - 1)),
							(ExpressionNode) init, newScope);
					newStatement.setNode(statement);

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
				Statement newStatement;

				if (usedLocation || location == null) {
					usedLocation = true;
					newStatement = statement(function, lastStatement,
							(StatementNode) node, newScope);
				} else {
					usedLocation = true;
					newStatement = statement(location, guard, function,
							lastStatement, (StatementNode) node, newScope);
				}
				lastStatement = newStatement;
			} else {
				throw new CIVLUnimplementedFeatureException(
						"Unsupported block element", node.getSource());
			}
		}
		if (lastStatement == null) {
			if (location == null) {
				location = factory.location(newScope);
			}
			lastStatement = factory.noopStatement(location);
			function.setStartLocation(location);
		}
		return lastStatement;
	}

	private Statement forLoop(Function function, Statement lastStatement,
			ForLoopNode statement, Scope scope) {
		return forLoop(factory.location(scope),
				factory.booleanLiteralExpression(true), function,
				lastStatement, statement, scope);
	}

	private Statement forLoop(Location location, Expression guard,
			Function function, Statement lastStatement, ForLoopNode statement,
			Scope scope) {
		ForLoopInitializerNode init = statement.getInitializer();
		Statement initStatement = lastStatement;
		Scope newScope = factory.scope(scope, new LinkedHashSet<Variable>(),
				function);
		Statement loopBody;
		Expression condition;
		Statement incrementer;
		Statement loopExit;

		location.setScope(newScope);
		if (init != null) {
			if (init instanceof ExpressionNode) {
				initStatement = expressionStatement(location,
						factory.booleanLiteralExpression(true), function,
						(ExpressionNode) init, scope);
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
					// TODO: Double check this is a variable
					processVariableDeclaration(newScope, declaration);
					if (declaration.getInitializer() != null) {
						initStatement = factory
								.assignStatement(
										location,
										factory.variableExpression(newScope
												.getVariable(newScope
														.numVariables() - 1)),
										expression((ExpressionNode) declaration
												.getInitializer(), newScope));
						initStatement.setGuard(guard);
						initStatement.setNode(init);
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
						init.getSource());
			}
		}
		condition = booleanExpression(statement.getCondition(), newScope);
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
			// Expression[] args = new Expression[3];
			switch (expression.getOperator()) {
			case ASSIGN:
				result = factory.assignStatement(
						location,
						(LHSExpression) expression(expression.getArgument(0),
								scope),
						expression(expression.getArgument(1), scope));
				break;
			case PLUSEQ:
			case MINUSEQ:
			case TIMESEQ:
			case DIVEQ:
			case MODEQ:
				throw new CIVLInternalException(
						"Side-effects should have been removed",
						expression.getSource());
			case BITANDEQ:
			case BITOREQ:
			case BITXOREQ:
			case SHIFTLEFTEQ:
			case SHIFTRIGHTEQ:
				throw new CIVLUnimplementedFeatureException(
						"bit-level operations", expression.getSource());
			default:
				// No effect for ops without assignments.
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
		result.setNode(incrementer);
		return result;
	}

	private Statement whileLoop(Function function, Statement lastStatement,
			LoopNode statement, Scope scope) {
		Statement loopExit;
		Scope newScope = factory.scope(scope, new LinkedHashSet<Variable>(),
				function);
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
				loopEntrance = factory.location(newScope);
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
			loopBody = factory.noopStatement(loopEntrance);
		}
		for (Statement outgoing : loopEntrance.outgoing()) {
			outgoing.setGuard(factory.binaryExpression(BINARY_OPERATOR.AND,
					outgoing.guard(), condition));
		}
		loopBody.setTarget(loopEntrance);
		loopExit = factory.noopStatement(loopEntrance);
		loopExit.setGuard(factory
				.unaryExpression(UNARY_OPERATOR.NOT, condition));
		return loopExit;
	}

	private Statement wait(Function function, Statement lastStatement,
			WaitNode statement, Scope scope) {
		Location location = factory.location(scope);
		Statement result;

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		function.addLocation(location);
		result = factory.joinStatement(location,
				expression(statement.getExpression(), scope));
		result.setNode(statement);
		return result;
	}

	private Statement noop(Function function, Statement lastStatement,
			NullStatementNode statement, Scope scope) {
		Location location = factory.location(scope);

		return noop(location, function, lastStatement, statement, scope);
	}

	private Statement noop(Location location, Function function,
			Statement lastStatement, NullStatementNode statement, Scope scope) {
		Statement result = factory.noopStatement(location);

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		result.setNode(statement);
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
				guard = factory.booleanLiteralExpression(false);
			} else {
				guard = factory.booleanLiteralExpression(true);
			}
		}
		if (lastStatement != null) {
			iter = lastStatement.target().outgoing().iterator();
		} else {
			iter = function.startLocation().outgoing().iterator();
		}
		while (iter.hasNext()) {
			Statement s = iter.next();

			if (s.guard().equals(factory.booleanLiteralExpression(true))) {
				s.setGuard(guard);
			} else if (guard.equals(factory.booleanLiteralExpression(true))) {
				s.setGuard(guard);
			} else {
				s.setGuard(factory.binaryExpression(BINARY_OPERATOR.AND,
						s.guard(), guard));
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

		if (newGuard.equals(factory.booleanLiteralExpression(true))) {
			newGuard = guard;
		} else if (!guard.equals(factory.booleanLiteralExpression(true))) {
			newGuard = factory.binaryExpression(BINARY_OPERATOR.AND, guard,
					newGuard);
		}
		result = statement(location, newGuard, function, lastStatement,
				statement.getBody(), scope);
		return result;
	}

	private Statement choose(Function function, Statement lastStatement,
			ChooseStatementNode statement, Scope scope) {
		Location startLocation = factory.location(scope);
		Location endLocation = factory.location(scope);
		Statement result = factory.noopStatement(endLocation);
		Expression guard = factory.booleanLiteralExpression(true);
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
			Statement caseStatement = statement(startLocation,
					factory.booleanLiteralExpression(true), function,
					lastStatement, statement.getSequenceChild(i), scope);

			caseStatement.setTarget(endLocation);
		}
		Iterator<Statement> iter = startLocation.outgoing().iterator();
		// Compute the guard for the default statement
		while (iter.hasNext()) {
			Expression statementGuard = iter.next().guard();

			if (guard.equals(factory.booleanLiteralExpression(true))) {
				guard = statementGuard;
			} else if (statementGuard.equals(factory
					.booleanLiteralExpression(true))) {
				// Keep current guard
			} else {
				guard = factory.binaryExpression(BINARY_OPERATOR.OR, guard,
						statementGuard);
			}
		}
		if (statement.getDefaultCase() != null) {
			Statement defaultStatement = statement(startLocation,
					factory.unaryExpression(UNARY_OPERATOR.NOT, guard),
					function, lastStatement, statement.getDefaultCase(), scope);

			defaultStatement.setTarget(endLocation);
		}
		result.setNode(statement);
		return result;
	}

	private Statement gotoStatement(Function function, Statement lastStatement,
			GotoNode statement, Scope scope) {
		Location location = factory.location(scope);
		Statement noop = factory.noopStatement(location);
		OrdinaryLabelNode label = ((Label) statement.getLabel().getEntity())
				.getDefinition();

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		gotoStatements.put(noop, label);
		noop.setNode(statement);
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
		result.setNode(statement);
		return result;
	}

	private Statement switchStatement(Function function,
			Statement lastStatement, SwitchNode statement, Scope scope) {
		Location location = factory.location(scope);
		Expression guard = factory.booleanLiteralExpression(true);

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
			SwitchLabelNode label;
			Expression caseGuard;
			Expression combinedGuard;
			Statement caseGoto;

			assert caseStatement.getLabel() instanceof SwitchLabelNode;
			label = (SwitchLabelNode) caseStatement.getLabel();
			caseGuard = factory.binaryExpression(BINARY_OPERATOR.EQUAL,
					condition, expression(label.getExpression(), scope));
			if (!guard.equals(factory.booleanLiteralExpression(true))) {
				combinedGuard = factory.binaryExpression(BINARY_OPERATOR.AND,
						guard, caseGuard);
			} else {
				combinedGuard = caseGuard;
			}
			combinedCaseGuards = factory.binaryExpression(BINARY_OPERATOR.AND,
					caseGuard, combinedCaseGuards);
			caseGoto = factory.noopStatement(location);
			caseGoto.setGuard(combinedGuard);
			caseGoto.setNode(label);
			gotoStatements.put(caseGoto, label);
		}
		if (statement.getDefaultCase() != null) {
			LabelNode label = statement.getDefaultCase().getLabel();
			Statement defaultGoto = factory.noopStatement(location);

			defaultGoto.setGuard(factory.unaryExpression(UNARY_OPERATOR.NOT,
					combinedCaseGuards));
			defaultGoto.setNode(label);
			gotoStatements.put(defaultGoto, label);
		}
		bodyGoto = factory.noopStatement(location);
		bodyGoto.setGuard(factory.booleanLiteralExpression(false));
		result = statement(function, bodyGoto, statement.getBody(), scope);
		return result;
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
		Identifier systemID = factory.identifier("_CIVL_system");
		Function system = factory.function(systemID, new Vector<Variable>(),
				null, null, null);
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

			if (node instanceof VariableDeclarationNode) {
				InitializerNode init = ((VariableDeclarationNode) node)
						.getInitializer();

				processVariableDeclaration(system.outerScope(),
						(VariableDeclarationNode) rootNode.child(i));
				if (init != null) {
					LHSExpression left;
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
			} else if (node instanceof TypedefDeclarationNode) {
				// String typeName = ((TypedefDeclarationNode) node).getName();
				//
				// if (typeName.equals("$proc")) {
				// typedefMap.put(typeName, factory.processType());
				// } else if (typeName.equals("$heap")) {
				// typedefMap.put(typeName, factory.heapType());
				// } else {
				// typedefMap.put(typeName,
				// processType(((TypedefDeclarationNode) node)
				// .getTypeNode()));
				// }
			} else {
				throw new CIVLInternalException("Unsupported declaration type",
						node.getSource());
			}
		}
		if (mainFunction == null) {
			throw new CIVLException("Program must have a main function.",
					rootNode.getSource());
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
