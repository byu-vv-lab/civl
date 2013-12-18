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
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
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
	/**
	 * The factory used to create new Model components.
	 */
	private ModelFactory factory;

	/**
	 * The symbolic universe
	 */
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
	 * The name of the model (i.e., core name of the cvl file)
	 */
	private String modelName;

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

	/**
	 * Store temporary information of the function being processed
	 */
	private FunctionInfo functionInfo;

	/**
	 * The function definition node of the main function
	 */
	private FunctionDefinitionNode mainFunctionNode = null;

	/* *********************************************************************
	 * Constructors
	 * *********************************************************************
	 */
	/**
	 * Constructs new instance of CommonModelBuilder, creating instance of
	 * ModelFactory in the process, and sets up system functions.
	 * 
	 * @param config
	 *            the GMC configuration
	 * @param factory
	 *            the model factory
	 * @param program
	 *            the program
	 * @param name
	 *            name of the model, i.e. the file name without .cvl extension
	 */
	public ModelBuilderWorker(GMCConfiguration config, ModelFactory factory,
			Program program, String name) {
		this.config = config;
		this.inputInitMap = config.getMapValue(UserInterface.inputO);
		this.factory = factory;
		this.program = program;
		this.factory.setTokenFactory(program.getTokenFactory());
		this.modelName = name;
		this.heapType = factory.heapType(name);
		this.bundleType = factory.newBundleType();
		this.universe = factory.universe();
	}

	/* *********************************************************************
	 * Translating ABC Type into CIVL Type
	 * *********************************************************************
	 */

	/**
	 * Translate ABC basic types into CIVL types
	 * 
	 * @param source
	 *            The CIVL source
	 * @param basicType
	 *            The basic ABC type
	 * @return CIVL type
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

	/**
	 * Translate ABC struct or union type into CIVL type
	 * 
	 * @param source
	 *            The CIVL source
	 * @param scope
	 *            The scope
	 * @param type
	 *            The ABC struct or union type
	 * @return CIVL type of struct or union
	 */
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
				Identifier identifier = factory
						.identifier(factory.sourceOf(field.getDefinition()
								.getIdentifier()), name);
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
	 * @param source
	 *            The CIVL source
	 * @param scope
	 *            The scope
	 * @param abcType
	 *            The ABC type
	 * @return The CIVL type
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

	/**
	 * Translate the extent of an array type to an expression
	 * 
	 * @param source
	 *            The CIVL source
	 * @param arrayType
	 *            The array type
	 * @param scope
	 *            The scope
	 * @return the expression representing the extent
	 * */
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
	 * @param type
	 *            The CIVL type to be checked
	 * @return True of False
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

	/**
	 * Translate a TypeNode object from the AST into a CIVLType object
	 * 
	 * @param typeNode
	 *            The type node
	 * @param scope
	 *            The scope
	 * @return the CIVL type representing the TypeNode
	 */
	private CIVLType translateTypeNode(TypeNode typeNode, Scope scope) {
		return translateABCType(factory.sourceOf(typeNode), scope,
				typeNode.getType());
	}

	/* *********************************************************************
	 * Translate AST Node into CIVL Expression
	 * *********************************************************************
	 */

	/**
	 * Translate an ExpressionNode object in the AST into a CIVL Expression
	 * object
	 * 
	 * @param expressionNode
	 *            The expression node
	 * @param scope
	 *            The scope
	 * @param translateConversions
	 *            The translation conversions
	 * @return the CIVL Expression object
	 */
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
			result = factory.resultExpression(factory.sourceOf(expressionNode));
		} else if (expressionNode instanceof SelfNode) {
			result = factory.selfExpression(factory.sourceOf(expressionNode));
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
					factory.sourceOf(expressionNode));
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

					result = factory.nullPointerExpression(newCIVLType, scope,
							source);
				} else if (conversion instanceof PointerBoolConversion) {
					// pointer type to boolean type: p!=NULL
					result = factory.binaryExpression(source,
							BINARY_OPERATOR.NOT_EQUAL, result, factory
									.nullPointerExpression(
											(CIVLPointerType) result
													.getExpressionType(),
											scope, source));
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
		Expression result = factory.castExpression(factory.sourceOf(castNode),
				castType, castExpression);

		return result;
	}

	/**
	 * Translate a SizeofNode object from AST into a CIVL expression object
	 * 
	 * @param sizeofNode
	 *            The size of node
	 * @param scope
	 *            The scope
	 * @return the CIVL Sizeof expression
	 */
	private Expression translateSizeofNode(SizeofNode sizeofNode, Scope scope) {
		SizeableNode argNode = sizeofNode.getArgument();
		CIVLSource source = factory.sourceOf(sizeofNode);
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
		Expression result = factory.dotExpression(factory.sourceOf(arrowNode),
				factory.dereferenceExpression(
						factory.sourceOf(arrowNode.getStructurePointer()),
						struct), getFieldIndex(arrowNode.getFieldName()));

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
		Expression result = factory.dotExpression(factory.sourceOf(dotNode),
				struct, getFieldIndex(dotNode.getFieldName()));

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
		CIVLSource source = factory.sourceOf(subscriptNode);
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
		CIVLSource source = factory.sourceOf(operatorNode);
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
			ConditionalExpression expression = factory.conditionalExpression(
					source, arguments.get(0), arguments.get(1),
					arguments.get(2));

			functionInfo.addConditionalExpression(expression);
			result = expression;
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

	/**
	 * Translate an IdentifierExpressionNode object from the AST into a CIVL
	 * VariableExpression object.
	 * 
	 * @param identifierNode
	 *            The identifier node
	 * @param scope
	 *            The scope
	 * @return The CIVL VariableExpression object corresponding to the
	 *         IdentifierExpressionNode
	 */
	private Expression translateIdentifierNode(
			IdentifierExpressionNode identifierNode, Scope scope) {
		CIVLSource source = factory.sourceOf(identifierNode);
		Identifier name = factory.identifier(source, identifierNode
				.getIdentifier().name());
		VariableExpression result;

		if (scope.variable(name) == null) {
			throw new CIVLInternalException("No such variable ", source);
		}
		result = factory.variableExpression(source, scope.variable(name));
		return result;
	}

	/**
	 * Translate a ConstantNode into a CIVL literal expression
	 * 
	 * @param constantNode
	 *            The constant node
	 * @return a CIVL literal expression representing the constant node
	 */
	private Expression translateConstantNode(ConstantNode constantNode) {
		CIVLSource source = factory.sourceOf(constantNode);
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

	/**
	 * Translate a QuantifiedExpressionNode from AST into a CIVL Quantified
	 * expression
	 * 
	 * @param expressionNode
	 *            The quantified expression node
	 * @param scope
	 *            The scope
	 * @return the CIVL QuantifiedExpression
	 */
	private Expression translateQuantifiedExpressionNode(
			QuantifiedExpressionNode expressionNode, Scope scope) {
		QuantifiedExpression result;
		Quantifier quantifier;
		Variable variable;
		Expression restriction;
		Expression quantifiedExpression;
		CIVLSource source = factory.sourceOf(expressionNode.getSource());
		// TODO: Think about the best way to add the quantified variable. In
		// theory we want a scope just for the quantified expression, but this
		// creates certain problems. What scope should the location be in? What
		// if we have a conjunction of quantified statements? For now, we will
		// add to the existing scope, but this is unsatisfactory.

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
	 * Given a StatementNode, return a Fragment representing it. Takes a
	 * statement node where the start location and extra guard are defined
	 * elsewhere and returns the appropriate model statement.
	 * 
	 * @param guard
	 *            An extra component of the guard beyond that described in the
	 *            statement.
	 * @param scope
	 *            The scope containing this statement.
	 * @param statementNode
	 *            The statement node.
	 * @return The fragment representation of this statement.
	 */
	private Fragment translateStatementNode(Expression guard, Scope scope,
			StatementNode statementNode) {
		Fragment result = null;

		CIVLSource source = factory.sourceOfBeginning(statementNode);

		if (guard == null)
			guard = new CommonBooleanLiteralExpression(source, true);

		functionInfo.addConditionalExpressionQueue();

		// TODO replace if-else branches with switch, need to some appropriate
		// modification in ABC to support this.
		if (statementNode instanceof AssumeNode) {
			result = translateAssumeNode(guard, scope,
					(AssumeNode) statementNode);
		} else if (statementNode instanceof AssertNode) {
			result = translateAssertNode(guard, scope,
					(AssertNode) statementNode);
		} else if (statementNode instanceof ExpressionStatementNode) {
			result = translateExpressionStatementNode(guard, scope,
					((ExpressionStatementNode) statementNode).getExpression());
		} else if (statementNode instanceof CompoundStatementNode) {
			result = translateCompoundStatementNode(guard, scope,
					(CompoundStatementNode) statementNode);
		} else if (statementNode instanceof ForLoopNode) {
			result = translateForLoopNode(guard, scope,
					(ForLoopNode) statementNode);
		} else if (statementNode instanceof LoopNode) {
			result = translateWhileNode(guard, scope, (LoopNode) statementNode);
		} else if (statementNode instanceof IfNode) {
			result = translateIfNode(guard, scope, (IfNode) statementNode);
		} else if (statementNode instanceof WaitNode) {
			result = translateWaitNode(guard, scope, (WaitNode) statementNode);
		} else if (statementNode instanceof NullStatementNode) {
			result = translateNullStatementNode(guard, scope,
					(NullStatementNode) statementNode);
		} else if (statementNode instanceof WhenNode) {
			result = translateWhenNode(guard, scope, (WhenNode) statementNode);
		} else if (statementNode instanceof ChooseStatementNode) {
			result = translateChooseNode(guard, scope,
					(ChooseStatementNode) statementNode);
		} else if (statementNode instanceof GotoNode) {
			result = translateGotoNode(guard, scope, (GotoNode) statementNode);
		} else if (statementNode instanceof LabeledStatementNode) {
			result = translateLabelStatementNode(guard, scope,
					(LabeledStatementNode) statementNode);
		} else if (statementNode instanceof ReturnNode) {
			result = translateReturnNode(guard, scope,
					(ReturnNode) statementNode);
		} else if (statementNode instanceof SwitchNode) {
			result = translateSwitchNode(guard, scope,
					(SwitchNode) statementNode);
		} else if (statementNode instanceof JumpNode) {
			result = translateJumpNode(guard, scope, (JumpNode) statementNode);
		} else
			throw new CIVLUnimplementedFeatureException("statements of type "
					+ statementNode.getClass().getSimpleName(),
					factory.sourceOf(statementNode));

		if (functionInfo.hasConditionalExpressions() == true) {
			result = functionInfo.refineConditionalExpressionOfStatement(
					result.lastStatement, result.startLocation);
		}

		functionInfo.popConditionaExpressionStack();

		return result;
	}

	/**
	 * Translate a jump node (i.e., a break or continue statement) into a
	 * fragment
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param jumpNode
	 *            The jump node
	 * @return The fragment of the break or continue statement
	 */
	private Fragment translateJumpNode(Expression guard, Scope scope,
			JumpNode jumpNode) {
		Location location = factory.location(
				factory.sourceOfBeginning(jumpNode), scope);
		Statement result = factory.noopStatement(factory.sourceOf(jumpNode),
				location, guard);

		if (jumpNode.getKind() == JumpKind.CONTINUE) {
			functionInfo.peekContinueStatck().add(result);
		} else if (jumpNode.getKind() == JumpKind.BREAK) {
			functionInfo.peekBreakStatck().add(result);
		} else {
			throw new CIVLInternalException(
					"Jump nodes other than BREAK and CONTINUE should be handled seperately.",
					factory.sourceOf(jumpNode.getSource()));
		}
		return new Fragment(result);
	}

	/**
	 * Translate an IfNode (i.e., an if-else statement) into a fragment
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param ifNode
	 *            The if node
	 * @return The fragment of the if-else statements
	 */
	private Fragment translateIfNode(Expression guard, Scope scope,
			IfNode ifNode) {
		Expression expression = translateExpressionNode(ifNode.getCondition(),
				scope, true);
		Fragment beforeCondition = null, trueBranch, trueBranchBody, falseBranch, falseBranchBody, exit, result;
		Location exitLocation = factory.location(factory.sourceOfEnd(ifNode),
				scope);
		Location location = factory.location(factory.sourceOfBeginning(ifNode),
				scope);
		Map.Entry<Fragment, Expression> refineConditional = functionInfo
				.refineConditionalExpression(scope, guard, expression);

		beforeCondition = refineConditional.getKey();
		expression = refineConditional.getValue();
		expression = factory.booleanExpression(expression);

		trueBranch = new Fragment(factory.noopStatement(
				factory.sourceOfBeginning(ifNode.getTrueBranch()), location,
				expression));
		falseBranch = new Fragment(factory.noopStatement(factory
				.sourceOfEnd(ifNode), location, factory.unaryExpression(
				expression.getSource(), UNARY_OPERATOR.NOT, expression)));

		trueBranchBody = translateStatementNode(
				factory.booleanLiteralExpression(
						factory.sourceOf(ifNode.getTrueBranch()), true), scope,
				ifNode.getTrueBranch());

		trueBranch = trueBranch.combineWith(trueBranchBody);

		if (ifNode.getFalseBranch() != null) {
			falseBranchBody = translateStatementNode(
					factory.booleanLiteralExpression(expression.getSource(),
							true), scope, ifNode.getFalseBranch());
			falseBranch = falseBranch.combineWith(falseBranchBody);
		}

		result = trueBranch.parallelCombineWith(falseBranch);

		if (beforeCondition != null)
			result = beforeCondition.combineWith(result);

		exit = new Fragment(factory.noopStatement(factory.sourceOfEnd(ifNode),
				exitLocation, null));

		return result.combineWith(exit);
	}

	/**
	 * Translate an assume node into a fragment of CIVL statements
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope containing this statement.
	 * @param assumeNode
	 *            The scope containing this statement.
	 * @return the fragment
	 */
	private Fragment translateAssumeNode(Expression guard, Scope scope,
			AssumeNode assumeNode) {
		Expression expression = translateExpressionNode(
				assumeNode.getExpression(), scope, true);
		Location location = factory.location(
				factory.sourceOfBeginning(assumeNode), scope);
		Statement assumeStatement = factory.assumeStatement(
				factory.sourceOf(assumeNode), location, expression, guard);

		return new Fragment(location, assumeStatement);
	}

	/**
	 * Translate an assert statement into a fragment of CIVL statements
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope containing this statement.
	 * @param assertNode
	 *            The AST node for the assert statement
	 * @return the fragment
	 */
	private Fragment translateAssertNode(Expression guard, Scope scope,
			AssertNode assertNode) {
		Expression expression = translateExpressionNode(
				assertNode.getExpression(), scope, true);
		Location location = factory.location(
				factory.sourceOfBeginning(assertNode), scope);
		Statement assertStatement = factory.assertStatement(
				factory.sourceOf(assertNode), location, expression, guard);

		return new Fragment(location, assertStatement);
	}

	/**
	 * Takes an expression statement and converts it to a fragment of that
	 * statement. Currently supported expressions for expression statements are
	 * spawn, assign, function call, increment, decrement. Any other expressions
	 * have no side effects and thus result in a no-op.
	 * 
	 * @param guard
	 *            An extra guard associated with this statement.
	 * @param scope
	 *            The scope containing this statement.
	 * @param expressionNode
	 *            The expression node
	 * @return the fragment representing the expression node
	 */
	private Fragment translateExpressionStatementNode(Expression guard,
			Scope scope, ExpressionNode expressionNode) {
		Fragment result;

		Location location = factory.location(
				factory.sourceOfBeginning(expressionNode), scope);

		if (expressionNode instanceof OperatorNode) {
			OperatorNode operatorNode = (OperatorNode) expressionNode;

			switch (operatorNode.getOperator()) {
			case ASSIGN:
				result = translateAssignNode(guard, scope, operatorNode);
				break;
			case POSTINCREMENT:
			case PREINCREMENT:
			case POSTDECREMENT:
			case PREDECREMENT:
				throw new CIVLInternalException("Side-effect not removed: ",
						factory.sourceOf(operatorNode));
			default:
				// since side-effects have been removed,
				// the only expressions remaining with side-effects
				// are assignments. all others are equivalent to no-op
				Statement noopStatement = factory.noopStatement(
						factory.sourceOf(operatorNode), location, guard);
				result = new Fragment(location, noopStatement);
			}
		} else if (expressionNode instanceof SpawnNode) {
			result = translateSpawnNode(guard, scope,
					(SpawnNode) expressionNode);
		} else if (expressionNode instanceof FunctionCallNode) {
			result = translateFunctionCallNode(guard, scope,
					(FunctionCallNode) expressionNode);
		} else
			throw new CIVLInternalException(
					"expression statement of this kind",
					factory.sourceOf(expressionNode));

		return result;
	}

	/**
	 * Translate a function call node into a fragment containing the call
	 * statement
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param functionCallNode
	 *            The function call node
	 * @return the fragment containing the function call statement
	 */
	private Fragment translateFunctionCallNode(Expression guard, Scope scope,
			FunctionCallNode functionCallNode) {
		Location location = factory.location(
				factory.sourceOfBeginning(functionCallNode), scope);

		Statement callStatement = callOrSpawnStatement(location, scope,
				functionCallNode, null, true);
		if (guard != null)
			callStatement.setGuard(guard);

		return new Fragment(location, callStatement);
	}

	/**
	 * Translate a spawn node into a fragment containing the spawn statement
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param spawnNode
	 *            The spawn node
	 * @return The fragment of the spawn statement
	 */
	private Fragment translateSpawnNode(Expression guard, Scope scope,
			SpawnNode spawnNode) {
		Statement spawnStatement;
		Location location = factory.location(
				factory.sourceOfBeginning(spawnNode), scope);

		spawnStatement = callOrSpawnStatement(location, scope,
				spawnNode.getCall(), null, false);
		if (guard != null)
			spawnStatement.setGuard(guard);

		return new Fragment(location, spawnStatement);
	}

	/**
	 * Translate a FunctionCall node into a call or spawn statement
	 * 
	 * @param location
	 *            The location
	 * @param scope
	 *            The scope
	 * @param callNode
	 *            The function call node
	 * @param lhs
	 *            The left-hand-side expression
	 * @param isCall
	 *            True when the node is a call node, otherwise the node is a
	 *            spawn node
	 * @return the CallOrSpawnStatement
	 */
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
					factory.sourceOf(callNode));
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
		result = factory.callOrSpawnStatement(factory.sourceOf(callNode),
				location, isCall, null, arguments, null);
		result.setLhs(lhs);
		callStatements.put(result, callee);
		return result;
	}

	/**
	 * Sometimes an assignment is actually modeled as a fork or function call
	 * with an optional left hand side argument. Catch these cases.
	 * 
	 * @param guard
	 *            The guard
	 * @param assignNode
	 *            The assign node <dt><b>Preconditions:</b>
	 *            <dd>
	 *            assignNode.getOperator() == ASSIGN
	 * @param scope
	 *            The scope containing this assignment.
	 * @return The model representation of the assignment, which might also be a
	 *         fork statement or function call.
	 */
	private Fragment translateAssignNode(Expression guard, Scope scope,
			OperatorNode assignNode) {
		ExpressionNode lhs = assignNode.getArgument(0);
		ExpressionNode rhs = assignNode.getArgument(1);
		Expression leftExpression = translateExpressionNode(lhs, scope, true);
		Statement assignStatement;
		Location location = factory.location(factory.sourceOfBeginning(lhs),
				scope);

		if (!(leftExpression instanceof LHSExpression))
			throw new CIVLInternalException("expected LHS expression, not "
					+ leftExpression, factory.sourceOf(lhs));

		assignStatement = assignStatement(factory.sourceOfSpan(lhs, rhs),
				location, (LHSExpression) leftExpression, rhs, scope);

		if (guard != null)
			assignStatement.setGuard(guard);
		return new Fragment(location, assignStatement);
	}

	/**
	 * Sometimes an assignment is actually modeled as a fork or function call
	 * with an optional left hand side argument. Catch these cases.
	 * 
	 * @param source
	 *            the CIVL source information of the assign node
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
									scope, true)), null);
		return result;
	}

	/**
	 * Translate a cast node into a malloc statement
	 * 
	 * @param source
	 *            The CIVL source
	 * @param location
	 *            The location
	 * @param lhs
	 *            The left-hand-side expression
	 * @param castNode
	 *            The node of the malloc statement
	 * @param scope
	 *            The scope
	 * @return the malloc statement
	 */
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
				heapPointerExpression, sizeExpression, mallocId, null);

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
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param statementNode
	 *            The compound statement node
	 * @return the fragment of the compound statement node
	 */
	private Fragment translateCompoundStatementNode(Expression guard,
			Scope scope, CompoundStatementNode statementNode) {
		Scope newScope = factory.scope(factory.sourceOf(statementNode), scope,
				new LinkedHashSet<Variable>(), functionInfo.function());
		Location location = factory.location(
				factory.sourceOfBeginning(statementNode), newScope);
		// indicates whether the location argument has been used:
		boolean usedLocation = false;
		Fragment result = new Fragment();

		for (int i = 0; i < statementNode.numChildren(); i++) {
			BlockItemNode node = statementNode.getSequenceChild(i);
			Fragment fragment = translateASTNode(node, newScope,
					usedLocation ? null : location, guard);

			if (fragment != null) {
				usedLocation = true;
				result = result.combineWith(fragment);
			}
		}

		return result;
	}

	/**
	 * Translate a for loop node into a fragment
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param forLoopNode
	 *            The for loop node
	 * @return the fragment representing the for loop
	 */
	private Fragment translateForLoopNode(Expression guard, Scope scope,
			ForLoopNode forLoopNode) {
		ForLoopInitializerNode initNode = forLoopNode.getInitializer();
		Fragment initFragment = new Fragment();
		Scope newScope = factory.scope(factory.sourceOf(forLoopNode), scope,
				new LinkedHashSet<Variable>(), functionInfo.function());
		Fragment result;
		Location location = factory.location(
				factory.sourceOfBeginning(forLoopNode), newScope);

		if (initNode != null) {
			if (initNode instanceof ExpressionNode) {
				initFragment = translateExpressionStatementNode(guard, scope,
						(ExpressionNode) initNode);
			} else if (initNode instanceof DeclarationListNode) {
				for (int i = 0; i < ((DeclarationListNode) initNode)
						.numChildren(); i++) {
					VariableDeclarationNode declaration = ((DeclarationListNode) initNode)
							.getSequenceChild(i);
					Variable variable = translateVariableDeclarationNode(
							declaration, newScope);
					Fragment fragment = translateVariableInitializationNode(
							declaration, variable, location, newScope, guard);

					initFragment = initFragment.combineWith(fragment);
				}
			} else {
				throw new CIVLInternalException(
						"A for loop initializer must be an expression or a declaration list.",
						factory.sourceOf(initNode));
			}
		}

		result = composeLoopFragment(newScope, forLoopNode.getCondition(),
				forLoopNode.getBody(), forLoopNode.getIncrementer(), guard);

		if (initFragment != null) {
			result = initFragment.combineWith(result);
		}

		return result;
	}

	/**
	 * Helper method for translating for-loop and while-loop statement nodes
	 * Translate a loop structure into a fragment of CIVL statements
	 * 
	 * @param loopScope
	 *            the scope of the loop
	 * @param conditionNode
	 *            the condition which is an expression node
	 * @param loopBodyNode
	 *            the body of the loop which is a statement node
	 * @param incrementerNode
	 *            the incrementer which is an expression node, null for while
	 *            loop
	 * @param guard
	 *            The guard
	 * @return the fragment of the loop structure
	 */
	private Fragment composeLoopFragment(Scope loopScope,
			ExpressionNode conditionNode, StatementNode loopBodyNode,
			ExpressionNode incrementerNode, Expression guard) {
		Expression condition = translateExpressionNode(conditionNode,
				loopScope, true);
		Set<Statement> continues, breaks;
		Fragment beforeCondition, loopEntrance, loopBody, incrementer = null, loopExit, result;
		Location continueLocation;
		Map.Entry<Fragment, Expression> refineConditional = functionInfo
				.refineConditionalExpression(loopScope, guard, condition);

		beforeCondition = refineConditional.getKey();
		condition = refineConditional.getValue();
		condition = factory.booleanExpression(condition);

		Location loopEntranceLocation = factory.location(
				factory.sourceOf(conditionNode.getSource()), loopScope);
		loopEntrance = new Fragment(loopEntranceLocation,
				factory.noopStatement(
						factory.sourceOf(conditionNode.getSource()),
						loopEntranceLocation, condition));
		if (beforeCondition != null)
			loopEntrance = beforeCondition.combineWith(loopEntrance);

		functionInfo.addContinueSet(new LinkedHashSet<Statement>());
		functionInfo.addBreakSet(new LinkedHashSet<Statement>());

		loopBody = translateStatementNode(null, loopScope, loopBodyNode);
		continues = functionInfo.popContinueStack();

		// if there is no incrementer statement, continue statements will go to
		// the loop entrance/exit location
		if (incrementerNode != null) {
			incrementer = translateExpressionStatementNode(null, loopScope,
					incrementerNode);
			continueLocation = incrementer.startLocation;
		} else
			continueLocation = loopEntrance.startLocation;

		for (Statement s : continues) {
			s.setTarget(continueLocation);
		}

		// the loop entrance location is the same as the loop exit location
		loopExit = new Fragment(factory.noopStatement(condition.getSource(),
				loopEntranceLocation, factory.unaryExpression(
						condition.getSource(), UNARY_OPERATOR.NOT, condition)));

		// incrementer comes after the loop body
		if (incrementer != null)
			loopBody = loopBody.combineWith(incrementer);

		// loop entrance comes before the loop body, P.S. loopExit is "combined"
		// implicitly because its start location is the same as loopEntrance
		loopBody = loopBody.combineWith(loopEntrance);

		// initially loop entrance comes before the loopBody. Now we'll have
		// loopBody -> loopEntrance -> loopBody and the loop is formed.
		result = loopEntrance.combineWith(loopBody);

		// break statements will go out of the loop, and thus is considered as
		// one of the last statement of the fragment
		breaks = functionInfo.popBreakStack();
		if (breaks.size() > 0) {
			StatementSet lastStatements = new StatementSet();

			lastStatements.add(loopExit.lastStatement);
			for (Statement s : breaks) {
				lastStatements.add(s);
			}
			result.lastStatement = lastStatements;
		} else {
			result.lastStatement = loopExit.lastStatement;
		}

		return result;
	}

	/**
	 * Translate a while node to a fragment of CIVL statements
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param loopNode
	 *            The while loop node
	 * @return the fragment of the while loop
	 */
	private Fragment translateWhileNode(Expression guard, Scope scope,
			LoopNode loopNode) {
		Scope newScope = factory.scope(factory.sourceOf(loopNode), scope,
				new LinkedHashSet<Variable>(), functionInfo.function());

		return composeLoopFragment(newScope, loopNode.getCondition(),
				loopNode.getBody(), null, guard);
	}

	/**
	 * Translate a Wait node to a fragment of a CIVL join statement
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param waitNode
	 *            The wait node
	 * @return the fragment of the wait statement
	 */
	private Fragment translateWaitNode(Expression guard, Scope scope,
			WaitNode waitNode) {
		CIVLSource source = factory.sourceOf(waitNode);
		Location location = factory.location(
				factory.sourceOfBeginning(waitNode), scope);

		return new Fragment(factory.joinStatement(source, location,
				translateExpressionNode(waitNode.getExpression(), scope, true),
				guard));
	}

	/**
	 * Translate a null statement node into a fragment of a no-op statement
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param nullStatementNode
	 *            The null statement node
	 * @return the fragment of the null statement (i.e. no-op statement)
	 */
	private Fragment translateNullStatementNode(Expression guard, Scope scope,
			NullStatementNode nullStatementNode) {
		Location location = factory.location(
				factory.sourceOfBeginning(nullStatementNode), scope);

		return new Fragment(factory.noopStatement(
				factory.sourceOf(nullStatementNode), location, guard));
	}

	/**
	 * Translate a when node into a fragment of a when statement
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param whenNode
	 *            The when node
	 * @return the fragment of the when statement
	 */
	private Fragment translateWhenNode(Expression guard, Scope scope,
			WhenNode whenNode) {
		Expression newGuard = translateExpressionNode(whenNode.getGuard(),
				scope, true);

		newGuard = factory.booleanExpression(newGuard);
		if (factory.isTrue(newGuard)) {
			newGuard = guard;
		} else if (!factory.isTrue(guard)) {
			newGuard = factory.binaryExpression(
					factory.sourceOfSpan(guard.getSource(),
							newGuard.getSource()), BINARY_OPERATOR.AND, guard,
					newGuard);
		}

		return translateStatementNode(newGuard, scope, whenNode.getBody());
	}

	/**
	 * Translate a choose node into a fragment that has multiple outgoing
	 * statements from its start location
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param chooseStatementNode
	 *            The choose statement node
	 * @return the fragment of the choose statements
	 */
	private Fragment translateChooseNode(Expression guard, Scope scope,
			ChooseStatementNode chooseStatementNode) {
		Location startLocation = factory.location(
				factory.sourceOfBeginning(chooseStatementNode), scope);
		Location endLocation = factory.location(
				factory.sourceOfEnd(chooseStatementNode), scope);
		Fragment exit = new Fragment(factory.noopStatement(
				endLocation.getSource(), endLocation, guard));
		int defaultOffset = 0;
		Fragment result = new Fragment();
		Iterator<Statement> iter;

		if (guard == null)
			guard = factory.booleanLiteralExpression(startLocation.getSource(),
					true);

		if (chooseStatementNode.getDefaultCase() != null) {
			defaultOffset = 1;
		}

		// TODO Can a caseNode has conditional expressions (a?b:c) ?
		for (int i = 0; i < chooseStatementNode.numChildren() - defaultOffset; i++) {
			StatementNode childNode = chooseStatementNode.getSequenceChild(i);
			Fragment caseFragment = translateStatementNode(
					factory.booleanLiteralExpression(
							factory.sourceOfBeginning(childNode), true), scope,
					childNode);

			// make all case fragment to start at the same location
			caseFragment.updateStartLocation(startLocation);
			// combine all case fragments as branches of the start location
			result = result.parallelCombineWith(caseFragment);
		}

		iter = startLocation.outgoing().iterator();

		// Compute the guard for the default statement
		while (iter.hasNext()) {
			Expression statementGuard = iter.next().guard();

			if (factory.isTrue(guard)) {
				guard = statementGuard;
			} else if (factory.isTrue(statementGuard)) {
				// Keep current guard
			} else {
				guard = factory.binaryExpression(
						factory.sourceOfSpan(guard.getSource(),
								statementGuard.getSource()),
						BINARY_OPERATOR.OR, guard, statementGuard);
			}
		}
		if (chooseStatementNode.getDefaultCase() != null) {
			Fragment defaultFragment = translateStatementNode(
					factory.unaryExpression(guard.getSource(),
							UNARY_OPERATOR.NOT, guard), scope,
					chooseStatementNode.getDefaultCase());

			// update the start location of default fragment
			defaultFragment.updateStartLocation(startLocation);
			// combine the default fragment as a branch of the start location
			result = result.parallelCombineWith(defaultFragment);
		}

		// combine all branches with the exit fragment
		result = result.combineWith(exit);
		return result;
	}

	/**
	 * Translate goto statement, since the labeled location might not have been
	 * processed, record the no-op statement and the label to be complete later
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param gotoNode
	 *            The goto node
	 * @return The fragment of the goto statement
	 */
	private Fragment translateGotoNode(Expression guard, Scope scope,
			GotoNode gotoNode) {
		OrdinaryLabelNode label = ((Label) gotoNode.getLabel().getEntity())
				.getDefinition();
		Location location = factory.location(
				factory.sourceOfBeginning(gotoNode), scope);
		Statement noop = factory.noopStatement(factory.sourceOf(gotoNode),
				location, guard);

		functionInfo.putToGotoStatements(noop, label);

		return new Fragment(noop);
	}

	/**
	 * Translate labeled statements
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param labelStatementNode
	 *            The label statement node
	 * @return The fragment of the label statement
	 */
	private Fragment translateLabelStatementNode(Expression guard, Scope scope,
			LabeledStatementNode labelStatementNode) {
		Fragment result = translateStatementNode(guard, scope,
				labelStatementNode.getStatement());

		functionInfo.putToLabeledLocations(labelStatementNode.getLabel(),
				result.startLocation);
		return result;
	}

	/**
	 * Translate return statements
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param returnNode
	 *            The return node
	 * @return The fragment of the return statement
	 */
	private Fragment translateReturnNode(Expression guard, Scope scope,
			ReturnNode returnNode) {
		Location location = factory.location(
				factory.sourceOfBeginning(returnNode), scope);
		Statement result;
		Expression expression;

		if (returnNode.getExpression() != null) {
			expression = translateExpressionNode(returnNode.getExpression(),
					scope, true);
		} else
			expression = null;
		result = factory.returnStatement(factory.sourceOf(returnNode),
				location, expression, guard);
		return new Fragment(result);
	}

	/**
	 * Translate switch block into a fragment
	 * 
	 * @param guard
	 *            The guard
	 * @param scope
	 *            The scope
	 * @param switchNode
	 *            The switch node
	 * @return The fragment of the switch statements
	 */
	private Fragment translateSwitchNode(Expression guard, Scope scope,
			SwitchNode switchNode) {
		Fragment result = new Fragment();
		Iterator<LabeledStatementNode> cases = switchNode.getCases();
		Expression condition = translateExpressionNode(
				switchNode.getCondition(), scope, true);
		// Collect case guards to determine guard for default case.
		Expression combinedCaseGuards = guard;
		Fragment bodyGoto;
		Set<Statement> breaks;
		Location location = factory.location(
				factory.sourceOfBeginning(switchNode), scope);

		functionInfo.addBreakSet(new LinkedHashSet<Statement>());
		while (cases.hasNext()) {
			LabeledStatementNode caseStatement = cases.next();
			SwitchLabelNode label;
			Expression caseGuard;
			Expression combinedGuard;
			Fragment caseGoto;

			assert caseStatement.getLabel() instanceof SwitchLabelNode;
			label = (SwitchLabelNode) caseStatement.getLabel();
			caseGuard = factory
					.binaryExpression(
							factory.sourceOf(label.getExpression()),
							BINARY_OPERATOR.EQUAL,
							condition,
							translateExpressionNode(label.getExpression(),
									scope, true));
			if (!factory.isTrue(guard)) {
				combinedGuard = factory.binaryExpression(
						factory.sourceOfSpan(guard.getSource(),
								caseGuard.getSource()), BINARY_OPERATOR.AND,
						guard, caseGuard);
			} else {
				combinedGuard = caseGuard;
			}
			if (factory.isTrue(combinedCaseGuards)) {
				combinedCaseGuards = caseGuard;
			} else {
				combinedCaseGuards = factory.binaryExpression(factory
						.sourceOfSpan(caseGuard.getSource(),
								combinedCaseGuards.getSource()),
						BINARY_OPERATOR.OR, caseGuard, combinedCaseGuards);
			}
			caseGoto = new Fragment(factory.noopStatement(
					factory.sourceOfBeginning(caseStatement), location,
					combinedGuard));
			result = result.parallelCombineWith(caseGoto);
			functionInfo.putToGotoStatements(caseGoto.lastStatement, label);
		}
		if (switchNode.getDefaultCase() != null) {
			LabelNode label = switchNode.getDefaultCase().getLabel();
			Fragment defaultGoto = new Fragment(factory.noopStatement(factory
					.sourceOf(switchNode.getDefaultCase()), location, factory
					.unaryExpression(factory.sourceOfBeginning(switchNode
							.getDefaultCase()), UNARY_OPERATOR.NOT,
							combinedCaseGuards)));

			result = result.parallelCombineWith(defaultGoto);
			functionInfo.putToGotoStatements(defaultGoto.lastStatement, label);
		}

		bodyGoto = new Fragment(factory.noopStatement(location.getSource(),
				location,
				factory.booleanLiteralExpression(location.getSource(), false)));
		result = result.combineWith(bodyGoto);

		bodyGoto = translateStatementNode(null, scope, switchNode.getBody());
		result = result.combineWith(bodyGoto);

		breaks = functionInfo.popBreakStack();
		if (breaks.size() > 0) {
			StatementSet switchExits = new StatementSet();

			switchExits.add(result.lastStatement);
			for (Statement s : breaks) {
				switchExits.add(s);
			}
			result.lastStatement = switchExits;
		}
		return result;
	}

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
					factory.sourceOf(node));
		result = functionMap.get(entity);
		if (result == null) {
			CIVLSource nodeSource = factory.sourceOf(node);
			String functionName = entity.getName();
			CIVLSource identifierSource = factory
					.sourceOf(node.getIdentifier());
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
					factory.sourceOf(functionTypeNode.getReturnType()), scope,
					functionType.getReturnType());
			SequenceNode<VariableDeclarationNode> abcParameters = functionTypeNode
					.getParameters();
			int numParameters = abcParameters.numChildren();

			for (int i = 0; i < numParameters; i++) {
				VariableDeclarationNode decl = abcParameters
						.getSequenceChild(i);
				CIVLType type = translateABCType(factory.sourceOf(decl), scope,
						functionType.getParameterType(i));
				CIVLSource source = factory.sourceOf(decl.getIdentifier());
				Identifier variableName = factory.identifier(source,
						decl.getName());

				parameters.add(factory.variable(source, type, variableName,
						parameters.size()));
			}
			if (entity.getDefinition() == null) { // system function
				Source declSource = node.getIdentifier().getSource();
				CToken token = declSource.getFirstToken();
				File file = token.getSourceFile();
				String fileName = file.getName(); // fileName will be something
													// like "stdlib.h" or
													// "civlc.h"
				String libName;

				if (!fileName.contains("."))
					throw new CIVLInternalException("Malformed file name "
							+ fileName + " containing system function "
							+ functionName, nodeSource);

				libName = fileNameWithoutExtension(fileName);
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
						postcondition = factory.binaryExpression(factory
								.sourceOfSpan(postcondition.getSource(),
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
						precondition = factory.binaryExpression(factory
								.sourceOfSpan(precondition.getSource(),
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
	 * 
	 * @param functionNode
	 *            the function definition node in the AST
	 * @param function
	 *            the corresponding CIVL function (only not null for system
	 *            function)
	 * @param initializationFragment
	 *            the fragment of initialization statements, only not null for
	 *            system function
	 */
	private void translateFunctionDefinitionNode(
			FunctionDefinitionNode functionNode, CIVLFunction function,
			Fragment initializationFragment) {
		Entity entity = functionNode.getEntity();
		CIVLFunction result;
		Fragment body;
		StatementNode functionBodyNode;

		if (function == null)
			result = functionMap.get(entity);
		else
			result = function;

		if (result == null)
			throw new CIVLInternalException("Did not process declaration",
					factory.sourceOf(functionNode));

		if (function == null)
			functionInfo = new FunctionInfo(result, this.universe, this.factory);
		functionBodyNode = functionNode.getBody();
		Scope scope = result.outerScope();
		body = translateStatementNode(null, scope, functionBodyNode);

		if (body == null || !(body.lastStatement instanceof ReturnStatement)) {
			CIVLSource endSource = factory.sourceOfEnd(functionNode.getBody());
			Location returnLocation = factory.location(endSource,
					result.outerScope());
			Fragment returnFragment = new Fragment(factory.returnStatement(
					endSource, returnLocation, null, null));

			if (body != null)
				body = body.combineWith(returnFragment);
			else
				body = returnFragment;
		}

		if (initializationFragment != null) {
			body = initializationFragment.combineWith(body);
		}

		functionInfo.completeFunction(body);
	}

	/**
	 * Processes a variable declaration. Adds the new variable to the given
	 * scope.
	 * 
	 * @param scope
	 *            the Model scope in which the variable declaration occurs
	 * @param node
	 *            the AST variable declaration node.
	 * @return The variable
	 */
	private Variable translateVariableDeclarationNode(
			VariableDeclarationNode node, Scope scope) {
		CIVLType type = translateTypeNode(node.getTypeNode(), scope);
		CIVLSource source = factory.sourceOf(node.getIdentifier());
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
	 * Translate the initializer node of a variable declaration node in to a
	 * fragment of an assign statement
	 * 
	 * @param node
	 *            The variable declaration node
	 * @param variable
	 *            The variable
	 * @param location
	 *            The location
	 * @param scope
	 *            The scope
	 * @param guard
	 *            The guard
	 * @return The fragment
	 */
	private Fragment translateVariableInitializationNode(
			VariableDeclarationNode node, Variable variable, Location location,
			Scope scope, Expression guard) {
		Fragment initFragment = null;
		InitializerNode init = node.getInitializer();

		if (init != null) {
			Statement assignStatement;

			if (!(init instanceof ExpressionNode))
				throw new CIVLUnimplementedFeatureException(
						"Non-expression initializer", factory.sourceOf(init));

			if (location == null)
				location = factory.location(factory.sourceOfBeginning(node),
						scope);

			assignStatement = factory
					.assignStatement(
							factory.sourceOf(node),
							location,
							factory.variableExpression(factory.sourceOf(init),
									variable),
							translateExpressionNode((ExpressionNode) init,
									scope, true), guard);
			initFragment = new Fragment(assignStatement);

			if (functionInfo.hasConditionalExpressions()) {
				initFragment = functionInfo
						.refineConditionalExpressionOfStatement(
								assignStatement, location);
			}
		}

		return initFragment;
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

	/**
	 * Calculate the index of a field in a struct type
	 * 
	 * @param fieldIdentifier
	 *            The identifier of the field
	 * @return The index of the field
	 */
	private int getFieldIndex(IdentifierNode fieldIdentifier) {
		Entity entity = fieldIdentifier.getEntity();
		EntityKind kind = entity.getEntityKind();

		if (kind == EntityKind.FIELD) {
			Field field = (Field) entity;

			return field.getMemberIndex();
		} else {
			throw new CIVLInternalException(
					"getFieldIndex given identifier that does not correspond to field: ",
					factory.sourceOf(fieldIdentifier));
		}
	}

	// how to process indiviual block elements?
	// int x: INTEGER or STRING -> universe.integer
	// real x: INTEGER or DOUBLE or STRING -> universe.real
	// String x: STRING
	// boolean x : BOOLEAN
	// else no can do yet
	/**
	 * Translate command line constants into CIVL literal expression
	 * 
	 * @param variable
	 *            The variable
	 * @param constant
	 *            The constant value object
	 * @return the literal expression of the constant
	 * @throws CommandLineException
	 */
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
		Variable variable = translateVariableDeclarationNode(node, scope);
		CIVLType type = variable.type();
		Fragment result = null, initialization;
		IdentifierNode identifier = node.getIdentifier();
		CIVLSource source = factory.sourceOf(node);

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
				sourceLocation = factory.location(
						factory.sourceOfBeginning(node), scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					source, sourceLocation, factory.variableExpression(
							factory.sourceOf(identifier), variable), rhs, null));
			sourceLocation = null;
		}

		initialization = translateVariableInitializationNode(node, variable,
				sourceLocation, scope, null);

		if (result == null)
			result = initialization;
		else
			result = result.combineWith(initialization);

		return result;
	}

	/**
	 * Translate type node that is typedef, struct or union
	 * 
	 * @param sourceLocation
	 *            The location
	 * @param scope
	 *            The scope
	 * @param typeNode
	 *            The type node
	 * @return the fragment
	 */
	private Fragment translateCompoundTypeNode(Location sourceLocation,
			Scope scope, TypeNode typeNode) {
		Fragment result = null;
		String prefix;
		String tag;

		CIVLType type = translateTypeNode(typeNode, scope);
		CIVLSource civlSource = factory.sourceOf(typeNode);

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
				sourceLocation = factory.location(
						factory.sourceOfBeginning(typeNode), scope);
			result = new Fragment(sourceLocation, factory.assignStatement(
					civlSource, sourceLocation, lhs, rhs, null));
		}

		return result;
	}

	/**
	 * Complete bundle type
	 */
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

	/**
	 * @param fileName
	 *            The name of a certain file
	 * @return File name without extension
	 */
	private String fileNameWithoutExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		String libName;

		libName = fileName.substring(0, dotIndex);
		return libName;
	}

	// Exported methods....................................................

	/**
	 * @return The model factory used by this model builder.
	 */
	public ModelFactory factory() {
		return factory;
	}

	/**
	 * @return the configuration
	 */
	public GMCConfiguration getConfiguration() {
		return config;
	}

	/**
	 * @param node
	 *            The AST node
	 * @param scope
	 *            The scope
	 * @param location
	 *            The location
	 * @param guard
	 *            The guard
	 * @return The fragment of statements translated from the AST node
	 */
	private Fragment translateASTNode(ASTNode node, Scope scope,
			Location location, Expression guard) {
		Fragment result = null;

		if (node instanceof VariableDeclarationNode
				|| node instanceof StructureOrUnionTypeNode
				|| node instanceof TypedefDeclarationNode) {
			if (node instanceof VariableDeclarationNode)
				try {
					result = translateVariableDeclarationNode(location, scope,
							(VariableDeclarationNode) node);
				} catch (CommandLineException e) {
					throw new CIVLInternalException(
							"Saw input variable outside of root scope",
							factory.sourceOf(node));
				}
			else if (node instanceof StructureOrUnionTypeNode)
				result = translateCompoundTypeNode(location, scope,
						(StructureOrUnionTypeNode) node);
			else if (node instanceof TypedefDeclarationNode)
				result = translateCompoundTypeNode(location, scope,
						((TypedefDeclarationNode) node).getTypeNode());
			else
				throw new CIVLInternalException("unreachable",
						factory.sourceOf(node));
		} else if (node instanceof FunctionDefinitionNode) {
			if (((FunctionDefinitionNode) node).getName().equals("main")) {
				mainFunctionNode = (FunctionDefinitionNode) node;
			} else
				translateFunctionDeclarationNode(
						(FunctionDeclarationNode) node, scope);
		} else if (node instanceof FunctionDeclarationNode) {
			translateFunctionDeclarationNode((FunctionDeclarationNode) node,
					scope);
		} else if (scope.id() != systemScope.id()
				&& node instanceof StatementNode) {
			result = translateStatementNode(guard, scope, (StatementNode) node);
		} else if (scope.id() == systemScope.id() && node instanceof AssumeNode) {
			Fragment assumeFragment = translateAssumeNode(guard, scope,
					(AssumeNode) node);

			assumeFragment = functionInfo
					.refineConditionalExpressionOfStatement(
							assumeFragment.lastStatement,
							assumeFragment.startLocation);

			result = assumeFragment;
		} else {
			if (scope.id() == systemScope.id())
				throw new CIVLInternalException("Unsupported declaration type",
						factory.sourceOf(node));
			else
				throw new CIVLUnimplementedFeatureException(
						"Unsupported block element", factory.sourceOf(node));
		}

		return result;
	}

	/**
	 * Build the CIVL model from the AST
	 * 
	 * @throws CommandLineException
	 */
	public void buildModel() throws CommandLineException {
		Identifier systemID = factory.identifier(factory.systemSource(),
				"_CIVL_system");
		CIVLFunction system = factory.function(
				factory.sourceOf(program.getAST().getRootNode()), systemID,
				new ArrayList<Variable>(), null, null, null);
		ASTNode rootNode = program.getAST().getRootNode();
		Fragment initialization = new Fragment();

		systemScope = system.outerScope();
		callStatements = new LinkedHashMap<CallOrSpawnStatement, Function>();
		functionMap = new LinkedHashMap<Function, CIVLFunction>();
		unprocessedFunctions = new ArrayList<FunctionDefinitionNode>();
		functionInfo = new FunctionInfo(system, universe, factory);

		functionInfo.addConditionalExpressionQueue();
		for (int i = 0; i < rootNode.numChildren(); i++) {
			ASTNode node = rootNode.child(i);
			Fragment fragment = translateASTNode(node, systemScope, null, null);

			if (fragment != null)
				initialization = initialization.combineWith(fragment);
		}
		functionInfo.popConditionaExpressionStack();

		if (mainFunctionNode == null) {
			throw new CIVLException("Program must have a main function.",
					factory.sourceOf(rootNode));
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

		// translate main function, using system as the CIVL function object,
		// and combining initialization statements with its body
		translateFunctionDefinitionNode(mainFunctionNode, system,
				initialization);

		while (!unprocessedFunctions.isEmpty()) {
			FunctionDefinitionNode functionDefinition = unprocessedFunctions
					.remove(0);

			translateFunctionDefinitionNode(functionDefinition, null, null);
		}
		for (CallOrSpawnStatement statement : callStatements.keySet()) {
			statement
					.setFunction(functionMap.get(callStatements.get(statement)));
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
		model.setName(modelName);
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

		for (CIVLFunction f : model.functions()) {
			// purely local statements/locations can only be
			// identified after ALL variables have been
			// checked for being purely local or not

			for (Location loc : f.locations()) {

				for (Statement s : loc.outgoing()) {
					s.purelyLocalAnalysis();
				}

				loc.purelyLocalAnalysis();
			}
		}
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
}