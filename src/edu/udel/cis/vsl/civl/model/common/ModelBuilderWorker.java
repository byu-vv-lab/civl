package edu.udel.cis.vsl.civl.model.common;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.abc.ast.conversion.IF.ArithmeticConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.ArrayConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.CompatiblePointerConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.CompatibleStructureOrUnionConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.FunctionConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.LvalueConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.NullPointerConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.PointerBoolConversion;
import edu.udel.cis.vsl.abc.ast.conversion.IF.VoidPointerConversion;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.Field;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
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
import edu.udel.cis.vsl.abc.ast.node.IF.expression.QuantifiedExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ResultNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SelfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.SizeofNode;
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
import edu.udel.cis.vsl.abc.ast.node.IF.statement.JumpNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.JumpNode.JumpKind;
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
import edu.udel.cis.vsl.abc.ast.type.IF.FunctionType;
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
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.QuantifiedExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.QuantifiedExpression.Quantifier;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLBundleType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType.PrimitiveTypeKind;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.expression.CommonBooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.common.expression.CommonExpression;
import edu.udel.cis.vsl.civl.model.common.statement.StatementSet;
import edu.udel.cis.vsl.civl.model.common.type.CommonType;
import edu.udel.cis.vsl.civl.run.UserInterface;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

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

	private SymbolicUniverse universe;

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
	private ArrayList<FunctionDefinitionNode> unprocessedFunctions;

	/**
	 * Map whose key set contains all call/spawn statements in the model. The
	 * value associated to the key is the ABC function definition node. This is
	 * built up as call statements are processed. On a later pass, we iterate
	 * over this map and set the function fields of the call/spawn statements to
	 * the corresponding model Function object.
	 */
	private Map<CallOrSpawnStatement, Function> callStatements;

	/**
	 * Map from ABC Function entity to corresponding CIVL Function.
	 */
	private Map<Function, CIVLFunction> functionMap;

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
	 * Used to give names to anonymous structs and unions.
	 */
	private int anonymousStructCounter = 0;

	/**
	 * List of all malloc statements in the program.
	 */
	private ArrayList<MallocStatement> mallocStatements = new ArrayList<MallocStatement>();

	/**
	 * The types that may be part of a bundle.
	 */
	private LinkedList<CIVLType> bundleableTypeList = new LinkedList<CIVLType>();

	/**
	 * The types that may not be part of a bundle.
	 */
	private LinkedList<CIVLType> unbundleableTypeList = new LinkedList<CIVLType>();

	/** Used to shortcut checking whether circular types are bundleable. */
	private List<CIVLType> bundleableEncountered = new LinkedList<CIVLType>();

	/**
	 * The unique type for a heap.
	 */
	private CIVLHeapType heapType;

	/**
	 * The unique type for a bundle.
	 */
	private CIVLBundleType bundleType;

	/**
	 * The unique type for a message.
	 */
	private CIVLType messageType;

	/**
	 * The unique type for a queue.
	 */
	private CIVLType queueType;

	/**
	 * The unique type for a comm.
	 */
	private CIVLType commType;

	/**
	 * Used to keep track of continue statements in nested loops. Each entry on
	 * the stack corresponds to a particular loop. The statements in the set for
	 * that entry are noops which need their target set to the appropriate
	 * location at the end of the loop processing.
	 */
	private Stack<Set<Statement>> continueStatements = new Stack<Set<Statement>>();

	/**
	 * Used to keep track of break statements in nested loops/switches. Each
	 * entry on the stack corresponds to a particular loop or switch. The
	 * statements in the set for that entry are noops which need their target
	 * set to the appropriate location at the end of the loop or switch
	 * processing.
	 */
	private Stack<Set<Statement>> breakStatements = new Stack<Set<Statement>>();

	/**
	 * Configuration information for the generic model checker.
	 */
	private GMCConfiguration config;

	/**
	 * The map formed from parsing the command line for "-input" options that
	 * specifies an initial constant value for some input variables. May be null
	 * if no "-input"s appeared on the command line.
	 */
	private Map<String, Object> inputInitMap;

	/**
	 * Set containing the names of all input variables that were initialized
	 * from a commandline argument. This is used at the end of the building
	 * process to determine if there were any command line arguments that were
	 * not used. This usually indicates an error.
	 */
	private Set<String> initializedInputs = new HashSet<String>();

	/* *********************************************************************
	 * Constructors
	 * *********************************************************************
	 */
	/**
	 * Constructs new instance of CommonModelBuilder, creating instance of
	 * ModelFactory in the process, and sets up system functions.
	 * 
	 */
	public ModelBuilderWorker(GMCConfiguration config, ModelFactory factory,
			Program program) {
		this.config = config;
		this.inputInitMap = config.getMapValue(UserInterface.inputO);
		this.factory = factory;
		this.program = program;
		this.tokenFactory = program.getTokenFactory();
		this.heapType = factory.heapType("model");
		this.bundleType = factory.newBundleType();
		this.universe = factory.universe();
	}

	/* *********************************************************************
	 * Helper methods
	 * *********************************************************************
	 */

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

	/* *********************************************************************
	 * Translating ABC Type into CIVL Type
	 * *********************************************************************
	 */
	private CIVLType translateABCBasicType(CIVLSource source,
			StandardBasicType basicType) {
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

	private CIVLType translateABCStructureOrUnionType(CIVLSource source,
			Scope scope, StructureOrUnionType type) {
		String tag = type.getTag();

		if (tag == null) {
			if (type.isStruct())
				tag = "__struct_" + anonymousStructCounter + "__";
			else
				tag = "__union_" + anonymousStructCounter + "__";
			anonymousStructCounter++;
		}
		if (type.isUnion())
			throw new CIVLUnimplementedFeatureException("Union types", source);
		// civlc.h defines $proc as struct __proc__, etc.
		if ("__proc__".equals(tag))
			return factory.processType();
		if ("__heap__".equals(tag))
			return heapType;
		if ("__dynamic__".equals(tag))
			return factory.dynamicType();
		if ("__bundle__".equals(tag))
			return bundleType;
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
				CIVLType civlFieldType = translateABCType(source, scope,
						fieldType);
				Identifier identifier = factory.identifier(sourceOf(field
						.getDefinition().getIdentifier()), name);
				StructField civlField = factory.structField(identifier,
						civlFieldType);

				civlFields[i] = civlField;
			}
			result.complete(civlFields);
			if ("__message__".equals(tag))
				messageType = result;
			if ("__queue__".equals(tag))
				queueType = result;
			if ("__comm__".equals(tag))
				commType = result;
			return result;
		}
	}

	/**
	 * Working on replacing process type with this.
	 * 
	 * @param abcType
	 * @return
	 */
	private CIVLType translateABCType(CIVLSource source, Scope scope,
			Type abcType) {
		CIVLType result = typeMap.get(abcType);

		if (result == null) {
			TypeKind kind = abcType.kind();

			switch (kind) {
			case ARRAY: {
				ArrayType arrayType = (ArrayType) abcType;
				CIVLType elementType = translateABCType(source, scope,
						arrayType.getElementType());
				Expression extent = arrayExtent(source, arrayType, scope);

				if (extent != null)
					result = factory.completeArrayType(elementType, extent);
				else
					result = factory.incompleteArrayType(elementType);
				break;
			}
			case BASIC:
				result = translateABCBasicType(source,
						(StandardBasicType) abcType);
				break;
			case HEAP:
				result = heapType;
				break;
			case OTHER_INTEGER:
				result = factory.integerType();
				break;
			case POINTER: {
				PointerType pointerType = (PointerType) abcType;
				Type referencedType = pointerType.referencedType();
				CIVLType baseType = translateABCType(source, scope,
						referencedType);

				result = factory.pointerType(baseType);
				break;
			}
			case PROCESS:
				result = factory.processType();
				break;
			case SCOPE:
				result = factory.scopeType();
				break;
			case QUALIFIED:
				result = translateABCType(source, scope,
						((QualifiedObjectType) abcType).getBaseType());
				break;
			case STRUCTURE_OR_UNION:
				result = translateABCStructureOrUnionType(source, scope,
						(StructureOrUnionType) abcType);
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

	private Expression arrayExtent(CIVLSource source, ArrayType arrayType,
			Scope scope) {
		Expression result;

		if (arrayType.isComplete()) {
			ExpressionNode variableSize = arrayType.getVariableSize();

			if (variableSize != null) {
				result = translateExpressionNode(variableSize, scope, true);
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
	 * Returns false if a type contains a bundle or void (but void* is ok).
	 * 
	 */
	private boolean bundleableType(CIVLType type) {
		boolean result = true;

		if (bundleableEncountered.contains(type)) {
			// We are in a recursive evaluation that has already encountered
			// this type.
			// E.g. a struct foo with a field of type struct foo, etc.
			// If this type is not bundleable, that will be determined
			// elsewhere.
			return true;
		} else {
			bundleableEncountered.add(type);
		}
		if (type.isBundleType()) {
			result = false;
		} else if (type.isPointerType()) {
			if (((CIVLPointerType) type).baseType().isVoidType()) {
				// void* is bundleable, so catch this before checking base type
				result = true;
			} else {
				result = bundleableType(((CIVLPointerType) type).baseType());
			}
		} else if (type.isVoidType()) {
			result = false;
		} else if (type.isArrayType()) {
			result = bundleableType(((CIVLArrayType) type).elementType());
		} else if (type.isStructType()) {
			for (StructField f : ((CIVLStructType) type).fields()) {
				result = result && bundleableType(f.type());
				if (!result)
					break;
			}
		}
		// Heaps and primitive types can be bundled.
		bundleableEncountered.remove(type);
		return result;
	}

	private CIVLType translateTypeNode(TypeNode typeNode, Scope scope) {
		return translateABCType(sourceOf(typeNode), scope, typeNode.getType());
	}

	/* *********************************************************************
	 * Translate AST Node into CIVL Expression
	 * *********************************************************************
	 */
	private Expression nullPointerExpression(CIVLPointerType pointerType,
			Scope scope, CIVLSource source) {
		Expression zero = factory.integerLiteralExpression(source,
				BigInteger.ZERO);
		Expression result;

		zero.setExpressionScope(scope);
		result = factory.castExpression(source, pointerType, zero);
		result.setExpressionScope(scope);
		return result;
	}

	private Expression translateExpressionNode(ExpressionNode expressionNode,
			Scope scope, boolean translateConversions) {
		Expression result;

		if (expressionNode instanceof OperatorNode) {
			result = translateOperatorNode((OperatorNode) expressionNode, scope);
		} else if (expressionNode instanceof IdentifierExpressionNode) {
			result = translateIdentifierNode(
					(IdentifierExpressionNode) expressionNode, scope);
		} else if (expressionNode instanceof ConstantNode) {
			result = translateConstantNode((ConstantNode) expressionNode);
		} else if (expressionNode instanceof DotNode) {
			result = translateDotNode((DotNode) expressionNode, scope);
		} else if (expressionNode instanceof ArrowNode) {
			result = translateArrowNode((ArrowNode) expressionNode, scope);
		} else if (expressionNode instanceof ResultNode) {
			result = factory.resultExpression(sourceOf(expressionNode));
		} else if (expressionNode instanceof SelfNode) {
			result = factory.selfExpression(sourceOf(expressionNode));
		} else if (expressionNode instanceof CastNode) {
			result = translateCastNode((CastNode) expressionNode, scope);
		} else if (expressionNode instanceof SizeofNode) {
			result = translateSizeofNode((SizeofNode) expressionNode, scope);
		} else if (expressionNode instanceof QuantifiedExpressionNode) {
			result = translateQuantifiedExpressionNode(
					(QuantifiedExpressionNode) expressionNode, scope);
		} else
			throw new CIVLUnimplementedFeatureException("expressions of type "
					+ expressionNode.getClass().getSimpleName(),
					sourceOf(expressionNode));
		if (translateConversions) {
			// apply conversions
			CIVLSource source = result.getSource();
			int numConversions = expressionNode.getNumConversions();

			for (int i = 0; i < numConversions; i++) {
				Conversion conversion = expressionNode.getConversion(i);
				Type oldType = conversion.getOldType();
				Type newType = conversion.getNewType();
				// Arithmetic, Array, CompatibleStructureOrUnion,
				// Function, Lvalue, NullPointer, PointerBool, VoidPointer

				if (conversion instanceof ArithmeticConversion) {
					CIVLType oldCIVLType = translateABCType(source, scope,
							oldType);
					CIVLType newCIVLType = translateABCType(source, scope,
							newType);

					// need equals on Types
					if (oldCIVLType.isIntegerType()
							&& newCIVLType.isIntegerType()
							|| oldCIVLType.isRealType()
							&& newCIVLType.isRealType()) {
						// nothing to do
					} else {
						result = factory.castExpression(source, newCIVLType,
								result);
					}
				} else if (conversion instanceof CompatiblePointerConversion) {
					// nothing to do
				} else if (conversion instanceof ArrayConversion) {
					// we will ignore this one here because we want
					// to keep it as array in subscript expressions
				} else if (conversion instanceof CompatibleStructureOrUnionConversion) {
					// think about this
					throw new CIVLUnimplementedFeatureException(
							"compatible structure or union conversion", source);
				} else if (conversion instanceof FunctionConversion) {
					throw new CIVLUnimplementedFeatureException(
							"function pointers", source);
				} else if (conversion instanceof LvalueConversion) {
					// nothing to do since ignore qualifiers anyway
				} else if (conversion instanceof NullPointerConversion) {
					// result is a null pointer to new type
					CIVLPointerType newCIVLType = (CIVLPointerType) translateABCType(
							source, scope, newType);

					result = nullPointerExpression(newCIVLType, scope, source);
				} else if (conversion instanceof PointerBoolConversion) {
					// pointer type to boolean type: p!=NULL
					result = factory.binaryExpression(
							source,
							BINARY_OPERATOR.NOT_EQUAL,
							result,
							nullPointerExpression((CIVLPointerType) result
									.getExpressionType(), scope, source));
				} else if (conversion instanceof VoidPointerConversion) {
					// void*->T* or T*->void*
					// ignore, pointer types are all the same
				} else
					throw new CIVLInternalException("Unknown conversion: "
							+ conversion, source);
			}
		}
		return result;
	}

	/**
	 * Translate an expression from the CIVL AST to the CIVL model. The
	 * resulting expression will always be boolean-valued. If the expression
	 * evaluates to a numeric type, the result will be the equivalent of
	 * expression==0. Used for evaluating expression in conditions.
	 * 
	 * @param expressionNode
	 * @param scope
	 */
	private Expression booleanExpression(ExpressionNode expressionNode,
			Scope scope) {
		Expression result = translateExpressionNode(expressionNode, scope, true);

		if (!result.getExpressionType().equals(factory.booleanType())) {
			if (result.getExpressionType().equals(factory.integerType())) {
				result = factory.binaryExpression(sourceOf(expressionNode),
						BINARY_OPERATOR.NOT_EQUAL, result, factory
								.integerLiteralExpression(
										sourceOf(expressionNode),
										BigInteger.ZERO));
			} else if (result.getExpressionType().equals(factory.realType())) {
				result = factory.binaryExpression(sourceOf(expressionNode),
						BINARY_OPERATOR.NOT_EQUAL, result, factory
								.realLiteralExpression(
										sourceOf(expressionNode),
										BigDecimal.ZERO));
			} else {
				throw new CIVLInternalException(
						"Unable to convert expression to boolean type",
						sourceOf(expressionNode));
			}
		}
		return result;
	}

	/**
	 * Translate a cast expression from the CIVL AST to the CIVL model.
	 * 
	 * @param castNode
	 *            The cast expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression translateCastNode(CastNode castNode, Scope scope) {
		CIVLType castType = translateTypeNode(castNode.getCastType(), scope);
		ExpressionNode argumentNode = castNode.getArgument();
		Expression castExpression = translateExpressionNode(argumentNode,
				scope, true);
		Expression result = factory.castExpression(sourceOf(castNode),
				castType, castExpression);

		return result;
	}

	private Expression translateSizeofNode(SizeofNode sizeofNode, Scope scope) {
		SizeableNode argNode = sizeofNode.getArgument();
		CIVLSource source = sourceOf(sizeofNode);
		Expression result;

		if (argNode instanceof TypeNode) {
			CIVLType type = translateTypeNode((TypeNode) argNode, scope);

			result = factory.sizeofTypeExpression(source, type);
		} else if (argNode instanceof ExpressionNode) {
			Expression argument = translateExpressionNode(
					(ExpressionNode) argNode, scope, true);

			result = factory.sizeofExpressionExpression(source, argument);
		} else
			throw new CIVLInternalException("Unknown kind of SizeofNode: "
					+ sizeofNode, source);
		result.setExpressionScope(scope);
		return result;
	}

	/**
	 * Translate a struct pointer field reference from the CIVL AST to the CIVL
	 * model.
	 * 
	 * @param arrowNode
	 *            The arrow expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression translateArrowNode(ArrowNode arrowNode, Scope scope) {
		Expression struct = translateExpressionNode(
				arrowNode.getStructurePointer(), scope, true);
		Expression result = factory.dotExpression(
				sourceOf(arrowNode),
				factory.dereferenceExpression(
						sourceOf(arrowNode.getStructurePointer()), struct),
				getFieldIndex(arrowNode.getFieldName()));

		return result;
	}

	/**
	 * Translate a struct field reference from the CIVL AST to the CIVL model.
	 * 
	 * @param dotNode
	 *            The dot node to be translated.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression translateDotNode(DotNode dotNode, Scope scope) {
		Expression struct = translateExpressionNode(dotNode.getStructure(),
				scope, true);
		Expression result = factory.dotExpression(sourceOf(dotNode), struct,
				getFieldIndex(dotNode.getFieldName()));

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
	 * @param array
	 *            any CIVL expression e
	 * @return either the original expression or &e[0]
	 */
	private Expression arrayToPointer(Expression array) {
		CIVLType type = array.getExpressionType();

		if (type instanceof CIVLArrayType) {
			CIVLSource source = array.getSource();
			CIVLArrayType arrayType = (CIVLArrayType) type;
			CIVLType elementType = arrayType.elementType();
			Expression zero = factory.integerLiteralExpression(source,
					BigInteger.ZERO);
			LHSExpression subscript = factory.subscriptExpression(source,
					(LHSExpression) array, zero);
			Expression pointer = factory.addressOfExpression(source, subscript);
			Scope scope = array.expressionScope();

			zero.setExpressionScope(scope);
			subscript.setExpressionScope(scope);
			pointer.setExpressionScope(scope);
			((CommonExpression) zero).setExpressionType(factory.integerType());
			((CommonExpression) subscript).setExpressionType(elementType);
			((CommonExpression) pointer).setExpressionType(factory
					.pointerType(elementType));
			return pointer;
		}
		return array;
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
	private Expression translateSubscriptNode(OperatorNode subscriptNode,
			Scope scope) {
		CIVLSource source = sourceOf(subscriptNode);
		ExpressionNode leftNode = subscriptNode.getArgument(0);
		ExpressionNode rightNode = subscriptNode.getArgument(1);
		Expression lhs = translateExpressionNode(leftNode, scope, true);
		Expression rhs = translateExpressionNode(rightNode, scope, true);
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
	 * @param operatorNode
	 *            The operator expression.
	 * @param scope
	 *            The (static) scope containing the expression.
	 * @return The model representation of the expression.
	 */
	private Expression translateOperatorNode(OperatorNode operatorNode,
			Scope scope) {
		CIVLSource source = sourceOf(operatorNode);
		Operator operator = operatorNode.getOperator();

		if (operator == Operator.SUBSCRIPT)
			return translateSubscriptNode(operatorNode, scope);

		int numArgs = operatorNode.getNumberOfArguments();
		List<Expression> arguments = new ArrayList<Expression>();
		Expression result = null;

		for (int i = 0; i < numArgs; i++) {
			arguments.add(translateExpressionNode(operatorNode.getArgument(i),
					scope, true));
		}
		// TODO: Bitwise ops, =, {%,/,*,+,-}=, pointer ops, comma, ?
		if (numArgs < 1 || numArgs > 3) {
			throw new RuntimeException("Unsupported number of arguments: "
					+ numArgs + " in expression " + operatorNode);
		}
		switch (operatorNode.getOperator()) {
		case ADDRESSOF:
			result = factory.addressOfExpression(source,
					(LHSExpression) arguments.get(0));
			break;
		case BIG_O:
			result = factory.unaryExpression(source, UNARY_OPERATOR.BIG_O,
					arguments.get(0));
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
					"Unsupported operator: " + operatorNode.getOperator()
							+ " in expression " + operatorNode);
		}
		return result;
	}

	private Expression translateIdentifierNode(
			IdentifierExpressionNode identifierNode, Scope scope) {
		CIVLSource source = sourceOf(identifierNode);
		Identifier name = factory.identifier(source, identifierNode
				.getIdentifier().name());
		VariableExpression result;

		if (scope.variable(name) == null) {
			throw new CIVLInternalException("No such variable ", source);
		}
		result = factory.variableExpression(source, scope.variable(name));
		return result;
	}

	private Expression translateConstantNode(ConstantNode constantNode) {
		CIVLSource source = sourceOf(constantNode);
		Type convertedType = constantNode.getConvertedType();
		Expression result;

		if (convertedType.kind() == TypeKind.PROCESS) {
			assert constantNode.getStringRepresentation().equals("$self");
			result = factory.selfExpression(source);
		} else if (convertedType.kind() == TypeKind.OTHER_INTEGER) {
			result = factory.integerLiteralExpression(source, BigInteger
					.valueOf(Long.parseLong(constantNode
							.getStringRepresentation())));
		} else if (convertedType.kind() == TypeKind.BASIC) {
			switch (((StandardBasicType) convertedType).getBasicTypeKind()) {
			case SHORT:
			case UNSIGNED_SHORT:
			case INT:
			case UNSIGNED:
			case LONG:
			case UNSIGNED_LONG:
			case LONG_LONG:
			case UNSIGNED_LONG_LONG:
				result = factory.integerLiteralExpression(source, BigInteger
						.valueOf(Long.parseLong(constantNode
								.getStringRepresentation())));
				break;
			case FLOAT:
			case DOUBLE:
			case LONG_DOUBLE:
				result = factory.realLiteralExpression(source, BigDecimal
						.valueOf(Double.parseDouble(constantNode
								.getStringRepresentation())));
				break;
			case BOOL:
				boolean value;

				if (constantNode instanceof IntegerConstantNode) {
					BigInteger integerValue = ((IntegerConstantNode) constantNode)
							.getConstantValue().getIntegerValue();

					if (integerValue.intValue() == 0) {
						value = false;
					} else {
						value = true;
					}
				} else {
					value = Boolean.parseBoolean(constantNode
							.getStringRepresentation());
				}
				result = factory.booleanLiteralExpression(source, value);
				break;
			default:
				throw new CIVLUnimplementedFeatureException("type "
						+ convertedType, source);
			}
		} else
			throw new CIVLUnimplementedFeatureException(
					"type " + convertedType, source);
		return result;
	}

	private Expression translateQuantifiedExpressionNode(
			QuantifiedExpressionNode expressionNode, Scope scope) {
		QuantifiedExpression result;
		Quantifier quantifier;
		Variable variable;
		Expression restriction;
		Expression quantifiedExpression;
		CIVLSource source = sourceOf(expressionNode.getSource());
		// TODO: Think about the best way to add the quantified variable. In
		// theory we want a scope just for the quantified expression, but this
		// creates certain problems. What scope should the location be in? What
		// if we have a conjunction of quantified statements? For now, we will
		// add to the existing scope, but this is unsatisfactory.

		// Scope newScope = factory.scope(source, scope,
		// new LinkedHashSet<Variable>(), scope.function());

		switch (expressionNode.quantifier()) {
		case EXISTS:
			quantifier = Quantifier.EXISTS;
			break;
		case FORALL:
			quantifier = Quantifier.FORALL;
			break;
		case UNIFORM:
			quantifier = Quantifier.UNIFORM;
			break;
		default:
			throw new CIVLUnimplementedFeatureException("quantifier "
					+ expressionNode.quantifier(), source);
		}
		// TODO: create unique name for quantified variable
		variable = translateVariableDeclarationNode(expressionNode.variable(),
				scope);
		variable.setIsBound(true);
		restriction = translateExpressionNode(expressionNode.restriction(),
				scope, true);
		quantifiedExpression = translateExpressionNode(
				expressionNode.expression(), scope, true);
		result = factory.quantifiedExpression(source, quantifier, variable,
				restriction, quantifiedExpression);
		return result;
	}

	/* *********************************************************************
	 * Statements
	 * *********************************************************************
	 */

	/**
	 * Takes a statement node where the start location and extra guard are
	 * defined elsewhere and returns the appropriate model statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param location
	 *            The start location of the statement.
	 * @param guard
	 *            An extra component of the guard beyond that described in the
	 *            statement.
	 * @param scope
	 *            The scope containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param statementNode
	 *            The statement node.
	 * @return The model representation of this statement.
	 */
	private Statement translateStatementNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, StatementNode statementNode) {
		Statement result;
		CIVLSource source = sourceOfBeginning(statementNode);

		if (guard == null)
			guard = new CommonBooleanLiteralExpression(source, true);

		// TODO replace if-else branches with switch, need to some appropriate
		// modification in ABC to support this.
		if (statementNode instanceof AssumeNode) {
			result = translateAssumeNode(function, location, guard, scope,
					lastStatement, (AssumeNode) statementNode);
		} else if (statementNode instanceof AssertNode) {
			result = translateAssertNode(function, location, guard, scope,
					lastStatement, (AssertNode) statementNode);
		} else if (statementNode instanceof ExpressionStatementNode) {
			result = translateExpressionStatementNode(function, location,
					guard, scope, lastStatement,
					((ExpressionStatementNode) statementNode).getExpression());
		} else if (statementNode instanceof CompoundStatementNode) {
			result = translateCompoundStatementNode(function, location, guard,
					scope, lastStatement, (CompoundStatementNode) statementNode);
		} else if (statementNode instanceof ForLoopNode) {
			result = translateForLoopNode(function, location, guard, scope,
					lastStatement, (ForLoopNode) statementNode);
		} else if (statementNode instanceof LoopNode) {
			result = translateWhileNode(function, location, scope,
					lastStatement, (LoopNode) statementNode);
		} else if (statementNode instanceof IfNode) {
			result = translateIfNode(function, location, guard, scope,
					lastStatement, (IfNode) statementNode);
		} else if (statementNode instanceof WaitNode) {
			result = translateWaitNode(function, location, guard, scope,
					lastStatement, (WaitNode) statementNode);
		} else if (statementNode instanceof NullStatementNode) {
			result = translateNullStatementNode(function, location, guard,
					scope, lastStatement, (NullStatementNode) statementNode);
		} else if (statementNode instanceof WhenNode) {
			result = translateWhenNode(function, location, guard, scope,
					lastStatement, (WhenNode) statementNode);
		} else if (statementNode instanceof ChooseStatementNode) {
			result = translateChooseNode(function, location, guard, scope,
					lastStatement, (ChooseStatementNode) statementNode);
		} else if (statementNode instanceof GotoNode) {
			result = translateGotoNode(function, location, guard, scope,
					lastStatement, (GotoNode) statementNode);
		} else if (statementNode instanceof LabeledStatementNode) {
			result = translateLabelStatementNode(function, location, guard,
					scope, lastStatement, (LabeledStatementNode) statementNode);
		} else if (statementNode instanceof ReturnNode) {
			result = translateReturnNode(function, lastStatement,
					(ReturnNode) statementNode, scope);
		} else if (statementNode instanceof SwitchNode) {
			result = translateSwitchNode(function, location, guard, scope,
					lastStatement, (SwitchNode) statementNode);
		} else if (statementNode instanceof JumpNode) {
			result = translateJumpNode(function, location, guard, scope,
					lastStatement, (JumpNode) statementNode);
		} else
			throw new CIVLUnimplementedFeatureException("statements of type "
					+ statementNode.getClass().getSimpleName(),
					sourceOf(statementNode));

		function.addStatement(result);
		return result;
	}

	/**
	 * A break or continue statement;
	 */
	private Statement translateJumpNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, JumpNode jumpNode) {
		Statement result;

		if (location == null)
			location = factory.location(sourceOfBeginning(jumpNode), scope);
		result = factory.noopStatement(sourceOf(jumpNode), location);
		function.addLocation(location);
		if (jumpNode.getKind() == JumpKind.CONTINUE) {
			continueStatements.peek().add(result);
		} else if (jumpNode.getKind() == JumpKind.BREAK) {
			breakStatements.peek().add(result);
		} else {
			throw new CIVLInternalException(
					"Jump nodes other than BREAK and CONTINUE should be handled seperately.",
					sourceOf(jumpNode.getSource()));
		}
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	private Statement translateIfNode(CIVLFunction function, Location location,
			Expression guard, Scope scope, Statement lastStatement,
			IfNode ifNode) {
		Expression expression = translateExpressionNode(ifNode.getCondition(),
				scope, true);
		Statement trueBranch, trueBranchBody, falseBranch, falseBranchBody, result;
		Location exitLocation = factory.location(sourceOfEnd(ifNode), scope);
		Location trueBranchBodyLocation = factory.location(
				sourceOf(ifNode.getTrueBranch()), scope);

		if (location == null)
			location = factory.location(sourceOfBeginning(ifNode), scope);
		trueBranch = factory.noopStatement(
				sourceOfBeginning(ifNode.getTrueBranch()), location);
		falseBranch = factory.noopStatement(sourceOfEnd(ifNode), location);
		trueBranchBody = translateStatementNode(
				function,
				trueBranchBodyLocation,
				factory.booleanLiteralExpression(
						sourceOf(ifNode.getTrueBranch()), true), scope,
				trueBranch, ifNode.getTrueBranch());
		trueBranch.setGuard(expression);
		function.addLocation(location);
		function.addLocation(trueBranchBodyLocation);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		falseBranch.setGuard(factory.unaryExpression(expression.getSource(),
				UNARY_OPERATOR.NOT, expression));
		if (ifNode.getFalseBranch() == null) {
			falseBranchBody = falseBranch;
		} else {
			Location falseBranchLocation = factory.location(
					sourceOf(ifNode.getFalseBranch()), scope);

			falseBranchBody = translateStatementNode(function,
					falseBranchLocation, factory.booleanLiteralExpression(
							expression.getSource(), true), scope, falseBranch,
					ifNode.getFalseBranch());
		}
		function.addLocation(exitLocation);
		trueBranchBody.setTarget(exitLocation);
		falseBranchBody.setTarget(exitLocation);
		result = factory.noopStatement(sourceOfEnd(ifNode), exitLocation);
		return result;
	}

	/**
	 * An assume statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param location
	 *            The source location of the assume statement
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param assumeNode
	 *            The AST node for the assume statement
	 * @return The model representation of this statement.
	 */
	private Statement translateAssumeNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, AssumeNode assumeNode) {
		Statement result;
		Expression expression = translateExpressionNode(
				assumeNode.getExpression(), scope, true);

		if (location == null)
			location = factory.location(sourceOfBeginning(assumeNode), scope);
		result = factory.assumeStatement(sourceOf(assumeNode), location,
				expression);
		if (guard != null)
			result.setGuard(guard);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
			function.addLocation(location);
		} else if (function != null) {
			function.setStartLocation(location);
		}

		return result;
	}

	/**
	 * An assert statement.
	 * 
	 * @param function
	 *            The function containing this statement.
	 * @param location
	 *            The source location of the assert statement
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope containing this statement.
	 * @param lastStatement
	 *            The previous statement. Null if this is the first statement in
	 *            a function.
	 * @param assertNode
	 *            The AST node for the assert statement
	 * @return The model representation of this statement.
	 */
	private Statement translateAssertNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, AssertNode assertNode) {
		Expression expression = translateExpressionNode(
				assertNode.getExpression(), scope, true);
		Statement result;

		if (location == null)
			location = factory.location(sourceOfBeginning(assertNode), scope);
		result = factory.assertStatement(sourceOf(assertNode), location,
				expression);
		if (guard != null)
			result.setGuard(guard);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
			function.addLocation(location);
		} else if (function != null) {
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
	private Statement translateExpressionStatementNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, ExpressionNode expressionNode) {
		Statement result;

		if (location == null)
			location = factory.location(sourceOfBeginning(expressionNode),
					scope);
		if (expressionNode instanceof OperatorNode) {
			OperatorNode operatorNode = (OperatorNode) expressionNode;

			switch (operatorNode.getOperator()) {
			case ASSIGN:
				result = translateAssignNode(function, location, guard, scope,
						lastStatement, operatorNode);
				break;
			case POSTINCREMENT:
			case PREINCREMENT:
			case POSTDECREMENT:
			case PREDECREMENT:
				throw new CIVLInternalException("Side-effect not removed: ",
						sourceOf(operatorNode));
			default:
				// since side-effects have been removed,
				// the only expressions remaining with side-effects
				// are assignments. all others are equivalent to no-op
				result = factory
						.noopStatement(sourceOf(operatorNode), location);
				if (guard != null)
					result.setGuard(guard);

				if (lastStatement != null) {
					lastStatement.setTarget(location);
					function.addLocation(location);
				} else if (function != null) {
					function.setStartLocation(location);
				}
			}
		} else if (expressionNode instanceof SpawnNode) {
			result = translateSpawnNode(function, location, guard, scope,
					lastStatement, (SpawnNode) expressionNode);

			// FunctionCallNode call = ((SpawnNode) expressionNode).getCall();
			//
			// result = callOrSpawn(location, false, null, call, scope);
		} else if (expressionNode instanceof FunctionCallNode) {
			result = translateFunctionCallNode(function, location, guard,
					scope, lastStatement, (FunctionCallNode) expressionNode);
			// result = callOrSpawn(location, true, null,
			// (FunctionCallNode) expressionNode, scope);
		} else
			throw new CIVLInternalException(
					"expression statement of this kind",
					sourceOf(expressionNode));

		return result;
	}

	private Statement translateFunctionCallNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, FunctionCallNode functionCallNode) {
		Statement result;

		result = callOrSpawnStatement(location, scope, functionCallNode, null,
				true);
		if (guard != null)
			result.setGuard(guard);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
			function.addLocation(location);
		} else if (function != null) {
			function.setStartLocation(location);
		}

		return result;
	}

	private Statement translateSpawnNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, SpawnNode spawnNode) {
		Statement result;

		result = callOrSpawnStatement(location, scope, spawnNode.getCall(),
				null, false);
		if (guard != null)
			result.setGuard(guard);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
			function.addLocation(location);
		} else if (function != null) {
			function.setStartLocation(location);
		}

		return result;
	}

	private CallOrSpawnStatement callOrSpawnStatement(Location location,
			Scope scope, FunctionCallNode callNode, LHSExpression lhs,
			boolean isCall) {
		ArrayList<Expression> arguments = new ArrayList<Expression>();
		ExpressionNode functionExpression = ((FunctionCallNode) callNode)
				.getFunction();
		CallOrSpawnStatement result;
		Function callee;

		if (isMallocCall(callNode))
			throw new CIVLException(
					"$malloc can only occur in a cast expression",
					sourceOf(callNode));
		if (functionExpression instanceof IdentifierExpressionNode) {
			callee = (Function) ((IdentifierExpressionNode) functionExpression)
					.getIdentifier().getEntity();
		} else
			throw new CIVLUnimplementedFeatureException(
					"Function call must use identifier for now: "
							+ functionExpression.getSource());
		for (int i = 0; i < callNode.getNumberOfArguments(); i++) {
			Expression actual = translateExpressionNode(
					callNode.getArgument(i), scope, true);

			// TODO: once you translate conversions, you will do this
			// there and can delete the following line:
			actual = arrayToPointer(actual);
			arguments.add(actual);
		}
		result = factory.callOrSpawnStatement(sourceOf(callNode), location,
				isCall, null, arguments);
		result.setLhs(lhs);
		callStatements.put(result, callee);
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
	private Statement translateAssignNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, OperatorNode assignNode) {
		ExpressionNode lhs = assignNode.getArgument(0);
		ExpressionNode rhs = assignNode.getArgument(1);
		Expression leftExpression = translateExpressionNode(lhs, scope, true);
		Statement result;

		if (location == null) {
			location = factory.location(sourceOfBeginning(lhs), scope);
		}
		if (!(leftExpression instanceof LHSExpression))
			throw new CIVLInternalException("expected LHS expression, not "
					+ leftExpression, sourceOf(lhs));

		result = assignStatement(sourceOfSpan(lhs, rhs), location,
				(LHSExpression) leftExpression, rhs, scope);

		if (guard != null)
			result.setGuard(guard);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
			function.addLocation(location);
		} else if (function != null) {
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
	 *            Model expression for the left hand side of the assignment.
	 * @param rhsNode
	 *            AST expression for the right hand side of the assignment.
	 * @param scope
	 *            The scope containing this assignment.
	 * @return The model representation of the assignment, which might also be a
	 *         fork statement or function call.
	 */
	private Statement assignStatement(CIVLSource source, Location location,
			LHSExpression lhs, ExpressionNode rhsNode, Scope scope) {
		Statement result = null;

		if (isCompleteMallocExpression(rhsNode))
			result = mallocStatement(source, location, lhs, (CastNode) rhsNode,
					scope);
		else if (rhsNode instanceof FunctionCallNode)
			result = callOrSpawnStatement(location, scope,
					(FunctionCallNode) rhsNode, lhs, true);
		else if (rhsNode instanceof SpawnNode)
			result = callOrSpawnStatement(location, scope,
					((SpawnNode) rhsNode).getCall(), lhs, false);
		else
			result = factory
					.assignStatement(
							lhs.getSource(),
							location,
							lhs,
							arrayToPointer(translateExpressionNode(rhsNode,
									scope, true)));
		return result;
	}

	private MallocStatement mallocStatement(CIVLSource source,
			Location location, LHSExpression lhs, CastNode castNode, Scope scope) {
		CIVLType pointerType = translateTypeNode(castNode.getCastType(), scope);
		FunctionCallNode callNode = (FunctionCallNode) castNode.getArgument();
		int mallocId = mallocStatements.size();
		Expression heapPointerExpression;
		Expression sizeExpression;
		CIVLType elementType;
		MallocStatement result;

		if (!pointerType.isPointerType())
			throw new CIVLException(
					"result of $malloc not cast to pointer type", source);
		elementType = ((CIVLPointerType) pointerType).baseType();
		heapPointerExpression = translateExpressionNode(
				callNode.getArgument(0), scope, true);
		sizeExpression = translateExpressionNode(callNode.getArgument(1),
				scope, true);
		result = factory.mallocStatement(source, location, lhs, elementType,
				heapPointerExpression, sizeExpression, mallocId);

		mallocStatements.add(result);
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
	 * @param function
	 * @param location
	 * @param guard
	 * @param scope
	 * @param lastStatement
	 * @param statementNode
	 * @return
	 */
	private Statement translateCompoundStatementNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, CompoundStatementNode statementNode) {
		Scope newScope = factory.scope(sourceOf(statementNode), scope,
				new LinkedHashSet<Variable>(), function);
		// indicates whether the location argument has been used:
		boolean usedLocation = false;

		for (int i = 0; i < statementNode.numChildren(); i++) {
			BlockItemNode node = statementNode.getSequenceChild(i);

			if (node instanceof VariableDeclarationNode
					|| node instanceof StructureOrUnionTypeNode
					|| node instanceof TypedefDeclarationNode) {
				Fragment fragment;

				if (node instanceof VariableDeclarationNode)
					try {
						fragment = translateVariableDeclarationNode(
								usedLocation ? null : location, newScope,
								(VariableDeclarationNode) node);
					} catch (CommandLineException e) {
						throw new CIVLInternalException(
								"Saw input variable outside of root scope",
								sourceOf(node));
					}
				else if (node instanceof StructureOrUnionTypeNode)
					fragment = translateCompoundTypeNode(usedLocation ? null
							: location, newScope,
							(StructureOrUnionTypeNode) node);
				else if (node instanceof TypedefDeclarationNode)
					fragment = translateCompoundTypeNode(usedLocation ? null
							: location, newScope,
							((TypedefDeclarationNode) node).getTypeNode());
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
				translateFunctionDeclarationNode(
						(FunctionDeclarationNode) node, newScope);
				// unprocessedFunctions.add((FunctionDefinitionNode) node);
				// containingScopes.put((FunctionDefinitionNode) node,
				// newScope);
			} else if (node instanceof StatementNode) {
				Statement newStatement;

				if (usedLocation || location == null) {
					newStatement = translateStatementNode(function, null,
							guard, newScope, lastStatement,
							(StatementNode) node);
				} else {
					newStatement = translateStatementNode(function, location,
							guard, newScope, lastStatement,
							(StatementNode) node);
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
				location = factory.location(sourceOfBeginning(statementNode),
						newScope);
			}
			lastStatement = factory.noopStatement(location.getSource(),
					location);
			function.setStartLocation(location);
		}
		return lastStatement;
	}

	/**
	 * 
	 * @param location
	 *            start location for the for loop
	 * @param guard
	 * @param function
	 * @param lastStatement
	 * @param forLoopNode
	 * @param scope
	 * @return
	 */
	private Statement translateForLoopNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, ForLoopNode forLoopNode) {
		ForLoopInitializerNode init = forLoopNode.getInitializer();
		Statement initStatement = lastStatement;
		Scope newScope = factory.scope(sourceOf(forLoopNode), scope,
				new LinkedHashSet<Variable>(), function);
		Statement loopBody;
		Expression condition;
		Location loopEntranceLocation;
		Statement loopEntrance;
		Statement incrementer;
		Statement loopExit;
		Location incrementerLocation;
		Set<Statement> continues;
		Set<Statement> breaks;

		if (location == null)
			location = factory.location(sourceOfBeginning(forLoopNode), scope);

		location.setScope(newScope);
		if (init != null) {
			if (init instanceof ExpressionNode) {
				initStatement = translateExpressionStatementNode(function,
						location, guard, scope, lastStatement,
						(ExpressionNode) init);
				if (lastStatement == null)
					lastStatement = initStatement;
			} else if (init instanceof DeclarationListNode) {
				for (int i = 0; i < ((DeclarationListNode) init).numChildren(); i++) {
					VariableDeclarationNode declaration = ((DeclarationListNode) init)
							.getSequenceChild(i);
					translateVariableDeclarationNode(declaration, newScope);
					if (declaration.getInitializer() != null) {
						initStatement = factory.assignStatement(
								sourceOf(init),
								location,
								factory.variableExpression(sourceOf(declaration
										.getIdentifier()),
										newScope.getVariable(newScope
												.numVariables() - 1)),
								translateExpressionNode(
										(ExpressionNode) declaration
												.getInitializer(), newScope,
										true));
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
		condition = booleanExpression(forLoopNode.getCondition(), newScope);
		loopEntranceLocation = factory.location(sourceOf(forLoopNode
				.getCondition().getSource()), newScope);
		loopEntrance = factory.noopStatement(sourceOf(forLoopNode
				.getCondition().getSource()), loopEntranceLocation);
		loopEntrance.setGuard(condition);
		initStatement.setTarget(loopEntranceLocation);
		function.addLocation(loopEntranceLocation);
		continueStatements.add(new LinkedHashSet<Statement>());
		breakStatements.add(new LinkedHashSet<Statement>());
		loopBody = translateStatementNode(function, null, null, newScope,
				loopEntrance, forLoopNode.getBody());
		continues = continueStatements.pop();
		breaks = breakStatements.pop();
		incrementerLocation = factory.location(
				sourceOfBeginning(forLoopNode.getIncrementer()), newScope);
		for (Statement s : continues) {
			s.setTarget(incrementerLocation);
		}
		incrementer = forLoopIncrementer(incrementerLocation, function,
				loopBody, forLoopNode.getIncrementer(), newScope);
		incrementer.setTarget(initStatement.target());
		loopExit = factory.noopStatement(condition.getSource(),
				initStatement.target());
		loopExit.setGuard(factory.unaryExpression(condition.getSource(),
				UNARY_OPERATOR.NOT, condition));
		if (breaks.size() > 0) {
			StatementSet loopExits = new StatementSet();
			loopExits.add(loopExit);
			for (Statement s : breaks) {
				loopExits.add(s);
			}
			return loopExits;
		}
		return loopExit;
	}

	// TODO: merge it with assign statement
	private Statement forLoopIncrementer(Location location,
			CIVLFunction function, Statement lastStatement,
			ExpressionNode incrementerNode, Scope scope) {
		CIVLSource source = sourceOf(incrementerNode);
		Statement result;

		function.addLocation(location);
		// TODO: why can't this be treated like any statement?
		if (incrementerNode instanceof OperatorNode) {
			OperatorNode expression = (OperatorNode) incrementerNode;
			switch (expression.getOperator()) {
			case ASSIGN: {
				LHSExpression lhs = (LHSExpression) translateExpressionNode(
						expression.getArgument(0), scope, true);
				Expression rhs = translateExpressionNode(
						expression.getArgument(1), scope, true);

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

	private Statement translateWhileNode(CIVLFunction function,
			Location location, Scope scope, Statement lastStatement,
			LoopNode loopNode) {
		CIVLSource source = sourceOf(loopNode);
		Statement loopEntrance;
		Statement loopExit;

		if (location == null)
			location = factory.location(sourceOfBeginning(loopNode), scope);

		Scope newScope = factory.scope(source, scope,
				new LinkedHashSet<Variable>(), function);
		location.setScope(newScope);
		Statement loopBody;
		Expression condition;

		Set<Statement> continues;
		Set<Statement> breaks;

		condition = booleanExpression(loopNode.getCondition(), newScope);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		loopEntrance = factory.noopStatement(sourceOf(loopNode.getCondition()
				.getSource()), location);
		loopEntrance.setGuard(condition);
		continueStatements.add(new LinkedHashSet<Statement>());
		breakStatements.add(new LinkedHashSet<Statement>());
		loopBody = translateStatementNode(function, null, null, newScope,
				loopEntrance, loopNode.getBody());
		continues = continueStatements.pop();
		breaks = breakStatements.pop();
		for (Statement s : continues) {
			s.setTarget(location);
		}
		function.addLocation(location);
		assert location != null;
		loopBody.setTarget(location);
		loopExit = factory.noopStatement(location.getSource(), location);
		loopExit.setGuard(factory.unaryExpression(condition.getSource(),
				UNARY_OPERATOR.NOT, condition));
		if (breaks.size() > 0) {
			StatementSet loopExits = new StatementSet();
			loopExits.add(loopExit);
			for (Statement s : breaks) {
				loopExits.add(s);
			}
			return loopExits;
		}
		return loopExit;
	}

	private Statement translateWaitNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, WaitNode waitNode) {
		CIVLSource source = sourceOf(waitNode);
		Statement result;

		if (location == null)
			location = factory.location(sourceOfBeginning(waitNode), scope);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		function.addLocation(location);
		result = factory.joinStatement(source, location,
				translateExpressionNode(waitNode.getExpression(), scope, true));
		return result;
	}

	private Statement translateNullStatementNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, NullStatementNode nullStatementNode) {

		if (location == null)
			location = factory.location(sourceOfBeginning(nullStatementNode),
					scope);

		Statement result = factory.noopStatement(sourceOf(nullStatementNode),
				location);
		result.setGuard(guard);

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		return result;
	}

	private Statement translateWhenNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, WhenNode whenNode) {
		Expression newGuard = booleanExpression(whenNode.getGuard(), scope);
		Statement result;

		if (isTrue(newGuard)) {
			newGuard = guard;
		} else if (!isTrue(guard)) {
			newGuard = factory.binaryExpression(
					sourceOfSpan(guard.getSource(), newGuard.getSource()),
					BINARY_OPERATOR.AND, guard, newGuard);
		}
		result = translateStatementNode(function, location, newGuard, scope,
				lastStatement, whenNode.getBody());
		return result;
	}

	private Statement translateChooseNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, ChooseStatementNode chooseStatementNode) {
		Location startLocation = location;
		Location endLocation = factory.location(
				sourceOfEnd(chooseStatementNode), scope);
		Statement result = factory.noopStatement(endLocation.getSource(),
				endLocation);
		int defaultOffset = 0;

		if (guard == null)
			guard = factory.booleanLiteralExpression(startLocation.getSource(),
					true);
		if (location == null)
			location = factory.location(sourceOfBeginning(chooseStatementNode),
					scope);
		startLocation = location;

		if (lastStatement != null) {
			lastStatement.setTarget(startLocation);
		} else {
			function.setStartLocation(startLocation);
		}
		function.addLocation(startLocation);
		if (chooseStatementNode.getDefaultCase() != null) {
			defaultOffset = 1;
		}
		for (int i = 0; i < chooseStatementNode.numChildren() - defaultOffset; i++) {
			StatementNode childNode = chooseStatementNode.getSequenceChild(i);
			Statement caseStatement = translateStatementNode(function,
					startLocation, factory.booleanLiteralExpression(
							sourceOfBeginning(childNode), true), scope,
					lastStatement, childNode);

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
		if (chooseStatementNode.getDefaultCase() != null) {
			Statement defaultStatement = translateStatementNode(function,
					startLocation, factory.unaryExpression(guard.getSource(),
							UNARY_OPERATOR.NOT, guard), scope, lastStatement,
					chooseStatementNode.getDefaultCase());

			defaultStatement.setTarget(endLocation);
		}
		return result;
	}

	private Statement translateGotoNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, GotoNode gotoNode) {
		Statement noop;
		OrdinaryLabelNode label = ((Label) gotoNode.getLabel().getEntity())
				.getDefinition();

		if (location == null)
			location = factory.location(sourceOfBeginning(gotoNode), scope);
		noop = factory.noopStatement(sourceOf(gotoNode), location);
		if (guard != null)
			noop.setGuard(guard);
		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		gotoStatements.put(noop, label);

		return noop;
	}

	private Statement translateLabelStatementNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, LabeledStatementNode labelStatementNode) {
		Statement result = translateStatementNode(function, location, guard,
				scope, lastStatement, labelStatementNode.getStatement());

		if (lastStatement != null) {
			labeledLocations.put(labelStatementNode.getLabel(),
					lastStatement.target());
		} else {
			labeledLocations.put(labelStatementNode.getLabel(),
					function.startLocation());
		}
		return result;
	}

	private Statement translateReturnNode(CIVLFunction function,
			Statement lastStatement, ReturnNode returnNode, Scope scope) {
		Location location = factory.location(sourceOfBeginning(returnNode),
				scope);
		Statement result;
		Expression expression;

		function.addLocation(location);
		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		if (returnNode.getExpression() != null) {
			expression = translateExpressionNode(returnNode.getExpression(),
					scope, true);
		} else
			expression = null;
		result = factory.returnStatement(sourceOf(returnNode), location,
				expression);
		return result;
	}

	private Statement translateSwitchNode(CIVLFunction function,
			Location location, Expression guard, Scope scope,
			Statement lastStatement, SwitchNode switchNode) {
		Statement result = null;
		Iterator<LabeledStatementNode> cases = switchNode.getCases();
		Expression condition = translateExpressionNode(
				switchNode.getCondition(), scope, true);
		/** Collect case guards to determine guard for default case. */
		Expression combinedCaseGuards = guard;
		Statement bodyGoto;
		Set<Statement> breaks;

		if (location == null)
			location = factory.location(sourceOfBeginning(switchNode), scope);

		if (lastStatement != null) {
			lastStatement.setTarget(location);
		} else {
			function.setStartLocation(location);
		}
		function.addLocation(location);
		breakStatements.add(new LinkedHashSet<Statement>());
		while (cases.hasNext()) {
			LabeledStatementNode caseStatement = cases.next();
			// CIVLSource caseSource = sourceOf(caseStatement);
			SwitchLabelNode label;
			Expression caseGuard;
			Expression combinedGuard;
			Statement caseGoto;

			assert caseStatement.getLabel() instanceof SwitchLabelNode;
			label = (SwitchLabelNode) caseStatement.getLabel();
			caseGuard = factory
					.binaryExpression(
							sourceOf(label.getExpression()),
							BINARY_OPERATOR.EQUAL,
							condition,
							translateExpressionNode(label.getExpression(),
									scope, true));
			if (!isTrue(guard)) {
				combinedGuard = factory.binaryExpression(
						sourceOfSpan(guard.getSource(), caseGuard.getSource()),
						BINARY_OPERATOR.AND, guard, caseGuard);
			} else {
				combinedGuard = caseGuard;
			}
			if (isTrue(combinedCaseGuards)) {
				combinedCaseGuards = caseGuard;
			} else {
				combinedCaseGuards = factory.binaryExpression(
						sourceOfSpan(caseGuard.getSource(),
								combinedCaseGuards.getSource()),
						BINARY_OPERATOR.OR, caseGuard, combinedCaseGuards);
			}
			caseGoto = factory.noopStatement(sourceOfBeginning(caseStatement),
					location);
			caseGoto.setGuard(combinedGuard);
			gotoStatements.put(caseGoto, label);
		}
		if (switchNode.getDefaultCase() != null) {
			LabelNode label = switchNode.getDefaultCase().getLabel();
			Statement defaultGoto = factory.noopStatement(
					sourceOf(switchNode.getDefaultCase()), location);

			defaultGoto.setGuard(factory.unaryExpression(
					sourceOfBeginning(switchNode.getDefaultCase()),
					UNARY_OPERATOR.NOT, combinedCaseGuards));
			gotoStatements.put(defaultGoto, label);
		}
		bodyGoto = factory.noopStatement(location.getSource(), location);
		bodyGoto.setGuard(factory.booleanLiteralExpression(
				bodyGoto.getSource(), false));
		result = translateStatementNode(function, null, null, scope, bodyGoto,
				switchNode.getBody());
		breaks = breakStatements.pop();
		if (breaks.size() > 0) {
			StatementSet switchExits = new StatementSet();

			switchExits.add(result);
			for (Statement s : breaks) {
				switchExits.add(s);
			}
			return switchExits;
		}
		return result;
	}

	// ///////////////////////////////////
	/**
	 * Processes a function declaration node (whether or not node is also a
	 * definition node).
	 * 
	 * Let F be the ABC Function Entity corresponding to this function
	 * declaration.
	 * 
	 * First, see if there is already a CIVL Function CF corresponding to F. If
	 * not, create one and add it to the modelm and map(s). This may be an
	 * ordinary or a system function. (It is a system function if F does not
	 * have any definition.)
	 * 
	 * Process the contract (if any) and add it to whatever is already in the
	 * contract fields of CF.
	 * 
	 * If F is a function definition, add to lists of unprocessed function
	 * defintitions: unprocessedFunctions.add(node); containingScopes.put(node,
	 * scope);. Function bodies will be processed at a later pass.
	 * 
	 * @param node
	 *            any ABC function declaration node
	 * @param scope
	 *            the scope in which the function declaration occurs
	 * @return the CIVL Function (whether newly created or old)
	 */
	private CIVLFunction translateFunctionDeclarationNode(
			FunctionDeclarationNode node, Scope scope) {
		Function entity = node.getEntity();
		SequenceNode<ContractNode> contract = node.getContract();
		CIVLFunction result;

		if (entity == null)
			throw new CIVLInternalException("Unresolved function declaration",
					sourceOf(node));
		result = functionMap.get(entity);
		if (result == null) {
			CIVLSource nodeSource = sourceOf(node);
			String functionName = entity.getName();
			CIVLSource identifierSource = sourceOf(node.getIdentifier());
			Identifier functionIdentifier = factory.identifier(
					identifierSource, functionName);
			ArrayList<Variable> parameters = new ArrayList<Variable>();
			// type should come from entity, not this type node.
			// if it has a definition node, should probably use that one.
			FunctionType functionType = entity.getType();

			// TODO: deal with parameterized functions....

			FunctionTypeNode functionTypeNode = (FunctionTypeNode) node
					.getTypeNode();
			CIVLType returnType = translateABCType(
					sourceOf(functionTypeNode.getReturnType()), scope,
					functionType.getReturnType());
			SequenceNode<VariableDeclarationNode> abcParameters = functionTypeNode
					.getParameters();
			int numParameters = abcParameters.numChildren();

			for (int i = 0; i < numParameters; i++) {
				VariableDeclarationNode decl = abcParameters
						.getSequenceChild(i);
				CIVLType type = translateABCType(sourceOf(decl), scope,
						functionType.getParameterType(i));
				CIVLSource source = sourceOf(decl.getIdentifier());
				Identifier variableName = factory.identifier(source,
						decl.getName());

				parameters.add(factory.variable(source, type, variableName,
						parameters.size()));
			}
			if (entity.getDefinition() == null) { // system function
				Source declSource = node.getIdentifier().getSource();
				CToken token = declSource.getFirstToken();
				File file = token.getSourceFile();
				String fileName = file.getName();
				// fileName will be something like "stdlib.h" or "civlc.h"
				int dotIndex = fileName.lastIndexOf('.');
				String libName;

				if (dotIndex < 0)
					throw new CIVLInternalException("Malformed file name "
							+ fileName + " containing system function "
							+ functionName, nodeSource);
				libName = fileName.substring(0, dotIndex);
				result = factory.systemFunction(nodeSource, functionIdentifier,
						parameters, returnType, scope, libName);
			} else { // regular function
				result = factory.function(nodeSource, functionIdentifier,
						parameters, returnType, scope, null);
				unprocessedFunctions.add(entity.getDefinition());
			}
			// model.addFunction(result);
			functionMap.put(entity, result);
		}
		// result is now defined and in the model
		if (contract != null) {
			Expression precondition = result.precondition();
			Expression postcondition = result.postcondition();

			for (int i = 0; i < contract.numChildren(); i++) {
				ContractNode contractComponent = contract.getSequenceChild(i);
				Expression componentExpression;

				if (contractComponent instanceof EnsuresNode) {
					componentExpression = translateExpressionNode(
							((EnsuresNode) contractComponent).getExpression(),
							result.outerScope(), true);
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
					componentExpression = translateExpressionNode(
							((RequiresNode) contractComponent).getExpression(),
							result.outerScope(), true);
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
			if (precondition != null)
				result.setPrecondition(precondition);
			if (postcondition != null)
				result.setPostcondition(postcondition);
		}
		return result;
	}

	/**
	 * Processes the function body of a function definition node. At least one
	 * function declaration for this function should have been processed
	 * already, so the corresponding CIVL function should already exist.
	 */
	private void translateFunctionDefinitionNode(
			FunctionDefinitionNode functionNode) {
		Entity entity = functionNode.getEntity();
		CIVLFunction result = functionMap.get(entity);
		Statement body;

		if (result == null)
			throw new CIVLInternalException("Did not process declaration",
					sourceOf(functionNode));
		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		StatementNode functionBodyNode = functionNode.getBody();
		Scope scope = result.outerScope();
		body = translateStatementNode(result, null, null, scope, null,
				functionBodyNode);
		if (!(body instanceof ReturnStatement)) {
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
	private Variable translateVariableDeclarationNode(
			VariableDeclarationNode node, Scope scope) {
		CIVLType type = translateTypeNode(node.getTypeNode(), scope);
		CIVLSource source = sourceOf(node.getIdentifier());
		Identifier name = factory.identifier(source, node.getName());

		int vid = scope.numVariables();
		Variable variable = factory.variable(source, type, name, vid);
		scope.addVariable(variable);

		if (node.getTypeNode().isInputQualified()) {
			variable.setIsInput(true);
		}
		return variable;
	}

	/**
	 * Is the ABC expression node a call to the function "$malloc"?
	 * 
	 * @param node
	 *            an expression node
	 * @return true iff node is a function call to node to a function named
	 *         "$malloc"
	 */
	private boolean isMallocCall(ExpressionNode node) {
		if (node instanceof FunctionCallNode) {
			ExpressionNode functionNode = ((FunctionCallNode) node)
					.getFunction();

			if (functionNode instanceof IdentifierExpressionNode) {
				String functionName = ((IdentifierExpressionNode) functionNode)
						.getIdentifier().name();

				if ("$malloc".equals(functionName))
					return true;
			}
		}
		return false;
	}

	/**
	 * Is the ABC expression node an expression of the form
	 * <code>(t)$malloc(...)</code>? I.e., a cast expression for which the
	 * argument is a malloc call?
	 * 
	 * @param node
	 *            an expression node
	 * @return true iff this is a cast of a malloc call
	 */
	private boolean isCompleteMallocExpression(ExpressionNode node) {
		if (node instanceof CastNode) {
			ExpressionNode argumentNode = ((CastNode) node).getArgument();

			return isMallocCall(argumentNode);
		}
		return false;
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

	// how to process indiviual block elements?
	// int x: INTEGER or STRING -> universe.integer
	// real x: INTEGER or DOUBLE or STRING -> universe.real
	// String x: STRING
	// boolean x : BOOLEAN
	// else no can do yet

	private LiteralExpression constant(Variable variable, Object constant)
			throws CommandLineException {
		CIVLType type = variable.type();
		CIVLSource source = variable.getSource();

		if (type instanceof CIVLPrimitiveType) {
			PrimitiveTypeKind kind = ((CIVLPrimitiveType) type)
					.primitiveTypeKind();

			switch (kind) {
			case BOOL:
				if (constant instanceof Boolean)
					return factory.booleanLiteralExpression(source,
							(boolean) constant);
				else
					throw new CommandLineException(
							"Expected boolean value for variable " + variable
									+ " but saw " + constant);
			case INT:
				if (constant instanceof Integer)
					return factory.integerLiteralExpression(source,
							new BigInteger(((Integer) constant).toString()));
				if (constant instanceof String)
					return factory.integerLiteralExpression(source,
							new BigInteger((String) constant));
				else
					throw new CommandLineException(
							"Expected integer value for variable " + variable
									+ " but saw " + constant);
			case REAL:
				if (constant instanceof Integer)
					return factory.realLiteralExpression(source,
							new BigDecimal(((Integer) constant).toString()));
				if (constant instanceof Double)
					return factory.realLiteralExpression(source,
							new BigDecimal(((Double) constant).toString()));
				if (constant instanceof String)
					return factory.realLiteralExpression(source,
							new BigDecimal((String) constant));
				else
					throw new CommandLineException(
							"Expected real value for variable " + variable
									+ " but saw " + constant);
			case STRING:
				throw new CIVLUnimplementedFeatureException("Strings");
				// case DYNAMIC:
				// case PROCESS:
				// case SCOPE:
				// case VOID:
			default:
			}
		}
		throw new CIVLUnimplementedFeatureException(
				"Specification of initial value not of integer, real, or boolean type",
				variable);
	}

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
	 * @throws CommandLineException
	 *             if an intializer for an input variable specified on the
	 *             command line does not have a type compatible with the
	 *             variable
	 */
	private Fragment translateVariableDeclarationNode(Location sourceLocation,
			Scope scope, VariableDeclarationNode node)
			throws CommandLineException {
		InitializerNode init = node.getInitializer();
		Variable variable = translateVariableDeclarationNode(node, scope);
		CIVLType type = variable.type();
		Fragment result = null;
		IdentifierNode identifier = node.getIdentifier();
		CIVLSource source = sourceOf(node);

		if (variable.isInput() || type instanceof CIVLArrayType
				|| type instanceof CIVLStructType || type.isHeapType()) {
			Expression rhs = null;

			if (variable.isInput() && inputInitMap != null) {
				String name = variable.name().name();
				Object value = inputInitMap.get(name);

				if (value != null) {
					rhs = constant(variable, value);
					initializedInputs.add(name);
				}
			}
			if (rhs == null)
				rhs = factory.initialValueExpression(source, variable);
			if (sourceLocation == null)
				sourceLocation = factory.location(sourceOfBeginning(node),
						scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					source, sourceLocation,
					factory.variableExpression(sourceOf(identifier), variable),
					rhs));
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
			statement = assignStatement(sourceOf(node), sourceLocation,
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

	private Fragment translateCompoundTypeNode(Location sourceLocation,
			Scope scope, TypeNode typeNode) {
		Fragment result = null;
		String prefix;
		String tag;

		CIVLType type = translateTypeNode(typeNode, scope);
		CIVLSource civlSource = sourceOf(typeNode);

		if (typeNode instanceof StructureOrUnionTypeNode) {
			prefix = "__struct_";
			if (((StructureOrUnionTypeNode) typeNode).getStructDeclList() == null)
				return result;
			if (!(type instanceof CIVLStructType))
				throw new CIVLInternalException("unexpected type: " + type,
						civlSource);
			else {
				tag = ((CIVLStructType) type).name().name();
			}
		} else {
			prefix = "__typedef_";
			tag = ((TypedefDeclarationNode) typeNode.parent()).getName();
		}

		if (type.hasState()) {
			Variable variable;

			String name = prefix + tag + "__";
			Identifier identifier = factory.identifier(civlSource, name);
			int vid = scope.numVariables();

			variable = factory.variable(civlSource, factory.dynamicType(),
					identifier, vid);
			scope.addVariable(variable);
			type.setStateVariable(variable);

			LHSExpression lhs = factory
					.variableExpression(civlSource, variable);
			Expression rhs = factory.dynamicTypeOfExpression(civlSource, type);

			if (sourceLocation == null)
				sourceLocation = factory.location(sourceOfBeginning(typeNode),
						scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					civlSource, sourceLocation, lhs, rhs));
		}

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
	private void addToFunction(Fragment fragment, CIVLFunction function,
			boolean addFirstLocation, ArrayList<Statement> list) {
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

	private void completeBundleType() {
		Map<SymbolicType, Integer> dynamicTypeMap = new LinkedHashMap<SymbolicType, Integer>();
		int dynamicTypeCount = 0;

		for (CIVLType type : bundleableTypeList) {
			SymbolicType dynamicType = type.getDynamicType(universe);
			Integer id = dynamicTypeMap.get(dynamicType);

			if (id == null) {
				id = dynamicTypeCount;
				dynamicTypeMap.put(dynamicType, id);
				dynamicTypeCount++;
			}
			((CommonType) type).setDynamicTypeIndex(id);
		}
		factory.complete(bundleType, dynamicTypeMap.keySet());
	}

	// Exported methods....................................................

	/**
	 * @return The model factory used by this model builder.
	 */
	public ModelFactory factory() {
		return factory;
	}

	/**
	 * Returns the configuration.
	 * 
	 * @return the configuration
	 */
	public GMCConfiguration getConfiguration() {
		return config;
	}

	/**
	 * Build the model.
	 * 
	 * @param unit
	 *            The translation unit for the AST.
	 * @return The model.
	 * @throws CommandLineException
	 */
	public void buildModel() throws CommandLineException {
		Identifier systemID = factory.identifier(factory.systemSource(),
				"_CIVL_system");
		CIVLFunction system = factory.function(sourceOf(program.getAST()
				.getRootNode()), systemID, new ArrayList<Variable>(), null,
				null, null);
		ASTNode rootNode = program.getAST().getRootNode();
		Location returnLocation;
		Statement returnStatement;
		FunctionDefinitionNode mainFunction = null;
		Statement mainBody;
		ArrayList<Statement> initializations = new ArrayList<Statement>();

		systemScope = system.outerScope();
		callStatements = new LinkedHashMap<CallOrSpawnStatement, Function>();
		functionMap = new LinkedHashMap<Function, CIVLFunction>();
		unprocessedFunctions = new ArrayList<FunctionDefinitionNode>();
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
					fragment = translateCompoundTypeNode(null, systemScope,
							((TypedefDeclarationNode) node).getTypeNode());
				else if (node instanceof StructureOrUnionTypeNode)
					fragment = this.translateCompoundTypeNode(null,
							systemScope, (StructureOrUnionTypeNode) node);
				else
					throw new RuntimeException("unreachable");
				if (fragment != null) {
					// add locations and statements to fragment and
					// statements to initializations:
					if (!initializations.isEmpty())
						initializations.get(initializations.size() - 1)
								.setTarget(fragment.startLocation);
					addToFunction(fragment, system, true, initializations);
				}
			} else if (node instanceof FunctionDefinitionNode) {
				if (((FunctionDefinitionNode) node).getName().equals("main")) {
					mainFunction = (FunctionDefinitionNode) node;
				} else
					translateFunctionDeclarationNode(
							(FunctionDeclarationNode) node, systemScope);
			} else if (node instanceof FunctionDeclarationNode) {
				translateFunctionDeclarationNode(
						(FunctionDeclarationNode) node, systemScope);
			} else if (node instanceof AssumeNode) {
				Location location = factory.location(sourceOfBeginning(node),
						systemScope);

				Statement assumeStmt = translateAssumeNode(null, location,
						null, systemScope, null, (AssumeNode) node);

				// lastStatement not updated because null
				// startLocation not set because function null
				if (!initializations.isEmpty())
					initializations.get(initializations.size() - 1).setTarget(
							assumeStmt.source());
				initializations.add(assumeStmt);
				system.addLocation(assumeStmt.source());
				system.addStatement(assumeStmt);
			} else {
				throw new CIVLInternalException("Unsupported declaration type",
						sourceOf(node));
			}
		}
		if (mainFunction == null) {
			throw new CIVLException("Program must have a main function.",
					sourceOf(rootNode));
		}
		if (inputInitMap != null) {
			// if commandline specified input variables that do not
			// exist, throw exception...
			Set<String> commandLineVars = new HashSet<String>(
					inputInitMap.keySet());

			commandLineVars.removeAll(initializedInputs);
			if (!commandLineVars.isEmpty()) {
				String msg = "Program contains no input variables named ";
				boolean first = true;

				for (String name : commandLineVars) {
					if (first)
						first = false;
					else
						msg += ", ";
					msg += name;
				}
				throw new CommandLineException(msg);
			}
		}
		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		if (!initializations.isEmpty()) {
			system.setStartLocation(initializations.get(0).source());
			mainBody = translateStatementNode(system, null, null,
					system.outerScope(),
					initializations.get(initializations.size() - 1),
					mainFunction.getBody());
		} else {
			mainBody = translateStatementNode(system, null, null,
					system.outerScope(), null, mainFunction.getBody());
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
		while (!unprocessedFunctions.isEmpty()) {
			FunctionDefinitionNode functionDefinition = unprocessedFunctions
					.remove(0);

			translateFunctionDefinitionNode(functionDefinition);
		}
		for (CallOrSpawnStatement statement : callStatements.keySet()) {
			statement
					.setFunction(functionMap.get(callStatements.get(statement)));
		}
		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledLocations.get(gotoStatements.get(s)));
		}
		factory.completeHeapType(heapType, mallocStatements);
		for (Type t : typeMap.keySet()) {
			CIVLType thisType = typeMap.get(t);
			if (bundleableType(thisType)) {
				bundleableTypeList.add(thisType);
			} else {
				unbundleableTypeList.add(thisType);
			}
		}
		completeBundleType();
		model = factory.model(system.getSource(), system);
		model.setMessageType(messageType);
		model.setQueueType(queueType);
		model.setCommType(commType);
		// add all functions to model except main:
		for (CIVLFunction f : functionMap.values())
			model.addFunction(f);
		((CommonModel) model).setMallocStatements(mallocStatements);
		for (CIVLFunction f : model.functions()) {
			f.simplify();
			// identify all purely local variables
			f.purelyLocalAnalysis();
			f.setModel(model);
			for (Statement s : f.statements()) {
				s.setModel(model);
				s.calculateDerefs();
			}
		}

		// CommonAssignStatement a;

		for (CIVLFunction f : model.functions()) {
			// purely local statements/locations can only be
			// identified after ALL variables have been
			// checked for being purely local or not
			// for (Statement s : f.statements()) {
			// s.purelyLocalAnalysis();
			// }

			for (Location loc : f.locations()) {

				for (Statement s : loc.outgoing()) {
					s.purelyLocalAnalysis();
				}

				loc.purelyLocalAnalysis();
			}
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
