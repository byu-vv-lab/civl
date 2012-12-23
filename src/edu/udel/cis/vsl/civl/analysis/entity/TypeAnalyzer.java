package edu.udel.cis.vsl.civl.analysis.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumeration;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.StructureOrUnion;
import edu.udel.cis.vsl.civl.ast.entity.IF.TaggedEntity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.AtomicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.BasicTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.EnumerationTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypedefNameNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.UnqualifiedObjectType;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

public class TypeAnalyzer {

	// ***************************** Fields *******************************

	private EntityAnalyzer entityAnalyzer;

	private TypeFactory typeFactory;

	private NodeFactory nodeFactory;

	private EntityFactory entityFactory;

	// ************************** Constructors ****************************

	TypeAnalyzer(EntityAnalyzer entityAnalyzer, TypeFactory typeFactory,
			EntityFactory entityFactory) {
		this.entityAnalyzer = entityAnalyzer;
		this.nodeFactory = entityAnalyzer.nodeFactory;
		this.typeFactory = typeFactory;
		this.entityFactory = entityFactory;
	}

	// ************************* Exported Methods **************************

	Type processTypeNode(TypeNode typeNode) throws SyntaxException {
		return processTypeNode(typeNode, false);
	}

	/**
	 * Process a TypeNode. The Type defined by that type node is computed and
	 * associated to the TypeNode.
	 * 
	 * This method may also entail the creation and/or modification of entities.
	 * For example, if the type node defines a strucuture or union type, or
	 * enumeration type, then the corresponding entities may be created or
	 * completed.
	 * 
	 * @param typeNode
	 * @return
	 * @throws SyntaxException
	 */
	Type processTypeNode(TypeNode typeNode, boolean isParameter)
			throws SyntaxException {
		TypeNodeKind kind = typeNode.kind();
		Type type;

		switch (kind) {
		case VOID:
			type = typeFactory.voidType();
			break;
		case BASIC:
			type = processBasicType((BasicTypeNode) typeNode);
			break;
		case ENUMERATION:
			type = processEnumerationType((EnumerationTypeNode) typeNode);
			break;
		case ARRAY:
			type = processArrayType((ArrayTypeNode) typeNode, isParameter);
			break;
		case STRUCTURE_OR_UNION:
			type = processStructureOrUnionType((StructureOrUnionTypeNode) typeNode);
			break;
		case FUNCTION:
			type = processFunctionType((FunctionTypeNode) typeNode, isParameter);
			break;
		case POINTER:
			type = processPointerType((PointerTypeNode) typeNode);
			break;
		case ATOMIC:
			type = processAtomicType((AtomicTypeNode) typeNode);
			break;
		case TYPEDEF_NAME:
			type = processTypedefName((TypedefNameNode) typeNode);
			break;
		default:
			throw new RuntimeException("Unreachable");
		}
		typeNode.setType(type);
		return type;
	}

	Type processEnumerationType(EnumerationTypeNode node)
			throws SyntaxException {
		Scope scope = node.getScope();
		String tag = node.getName(); // could be null
		SequenceNode<EnumeratorDeclarationNode> enumerators = node
				.enumerators();
		Enumeration enumeration = null;
		EnumerationType enumerationType = null;

		if (node.isRestrictQualified())
			throw error("Use of restrict qualifier with non-pointer type", node);
		if (tag != null) {
			TaggedEntity entity = scope.getLexicalTaggedEntity(tag);

			if (entity != null) {
				if (entity.getEntityKind() != EntityKind.ENUMERATION)
					throw error("Re-use of tag " + tag
							+ " for enumeration.  Previous use was at "
							+ entity.getFirstDeclaration().getSource(), node);
				enumeration = (Enumeration) entity;
				enumerationType = enumeration.getType();
			}
		}
		if (enumeration == null) {
			// create new incomplete enumeration type
			enumerationType = typeFactory.newEnumerationType(tag);
			enumeration = entityFactory.newEnumeration(enumerationType);
			enumerationType.setEntity(enumeration);
			scope.add(enumeration);
		}
		enumeration.addDeclaration(node);
		if (enumerators != null) {
			Iterator<EnumeratorDeclarationNode> enumeratorIter = enumerators
					.childIterator();
			List<Enumerator> enumeratorList = new LinkedList<Enumerator>();

			if (enumerationType.isComplete())
				throw error("Re-definition of enumeration", node);
			enumeration.setDefinition(node);
			while (enumeratorIter.hasNext()) {
				EnumeratorDeclarationNode decl = enumeratorIter.next();
				ExpressionNode constantNode = decl.getValue();
				Value value;
				Enumerator enumerator;

				// TODO: none should be null.  Add 1 using value factory.
				// implement plus.  What is type?  some integer type?
				
				if (constantNode == null) {
					value = null;
				} else {
					if (!constantNode.isConstantExpression())
						throw error(
								"Non-constant expression used in enumerator definition",
								constantNode);
					value = nodeFactory.getConstantValue(constantNode);
				}
				enumerator = entityFactory.newEnumerator(decl, enumerationType,
						value);
				enumeratorList.add(enumerator);
				try {
					scope.add(enumerator);
				} catch (UnsourcedException e) {
					throw error(e, decl);
				}
			}
			enumerationType.complete(enumeratorList);
		}
		{
			boolean constQ = node.isConstQualified();
			boolean volatileQ = node.isVolatileQualified();
			UnqualifiedObjectType unqualifiedType = enumerationType;

			if (node.isAtomicQualified())
				unqualifiedType = typeFactory.atomicType(unqualifiedType);
			if (constQ || volatileQ)
				return typeFactory.qualifiedType(unqualifiedType, constQ,
						volatileQ, false);
			return unqualifiedType;
		}
	}

	Type processStructureOrUnionType(StructureOrUnionTypeNode node)
			throws SyntaxException {
		Scope scope = node.getScope();
		String tag = node.getName(); // could be null
		SequenceNode<FieldDeclarationNode> fieldDecls = node
				.getStructDeclList();
		StructureOrUnion structureOrUnion = null;
		StructureOrUnionType structureOrUnionType = null;

		if (node.isRestrictQualified())
			throw error("Use of restrict qualifier with non-pointer type", node);
		if (tag != null) {
			TaggedEntity entity = scope.getLexicalTaggedEntity(tag);

			if (entity != null) {
				if (entity.getEntityKind() != EntityKind.STRUCTURE_OR_UNION)
					throw error("Re-use of tag " + tag
							+ " for structure or union.  Previous use was at "
							+ entity.getFirstDeclaration().getSource(), node);
				structureOrUnion = (StructureOrUnion) entity;
				structureOrUnionType = structureOrUnion.getType();
			}
		}
		if (structureOrUnion == null) {
			// create new incomplete structure or union type
			structureOrUnionType = typeFactory.newStructureOrUnionType(
					node.isStruct(), tag);
			structureOrUnion = entityFactory
					.newStructureOrUnion(structureOrUnionType);
			structureOrUnion.addDeclaration(node);
			structureOrUnionType.setEntity(structureOrUnion);
			scope.add(structureOrUnion);
		} else {
			structureOrUnion.addDeclaration(node);
		}
		if (fieldDecls != null) {
			Iterator<FieldDeclarationNode> fieldIter = fieldDecls
					.childIterator();
			List<Field> fieldList = new LinkedList<Field>();

			if (structureOrUnionType.isComplete()) {
				DeclarationNode definition = structureOrUnion.getDefinition();
				String message = "";

				if (definition != null)
					message = "Original definition at "
							+ definition.getSource();
				throw error("Re-definition of structure or union.  " + message,
						node);
			}
			structureOrUnion.setDefinition(node);
			while (fieldIter.hasNext()) {
				FieldDeclarationNode decl = fieldIter.next();
				TypeNode fieldTypeNode = decl.getTypeNode();
				ExpressionNode bitWidthExpression = decl.getBitFieldWidth();
				Value bitWidth;
				ObjectType fieldType;
				Field field;

				if (fieldTypeNode == null)
					fieldType = null;
				else {
					Type tempType = processTypeNode(fieldTypeNode);

					if (!(tempType instanceof ObjectType))
						throw error(
								"Non-object type for structure or union member",
								fieldTypeNode);
					fieldType = (ObjectType) tempType;
				}
				if (bitWidthExpression == null) {
					bitWidth = null;
				} else {
					if (!bitWidthExpression.isConstantExpression())
						throw error(
								"Non-constant expression used for bit width in field declaration",
								bitWidthExpression);
					bitWidth = nodeFactory.getConstantValue(bitWidthExpression);
				}
				field = entityFactory.newField(decl, fieldType, bitWidth,
						structureOrUnion);
				fieldList.add(field);
			}
			structureOrUnionType.complete(fieldList);
		}
		{
			boolean constQ = node.isConstQualified();
			boolean volatileQ = node.isVolatileQualified();
			UnqualifiedObjectType unqualifiedType = structureOrUnionType;

			if (node.isAtomicQualified())
				unqualifiedType = typeFactory.atomicType(unqualifiedType);
			if (constQ || volatileQ)
				return typeFactory.qualifiedType(unqualifiedType, constQ,
						volatileQ, false);
			return unqualifiedType;
		}
	}

	// ************************** Private Methods *****************************

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}

	private Type processBasicType(BasicTypeNode node) throws SyntaxException {
		UnqualifiedObjectType unqualifiedType = typeFactory.basicType(node
				.getBasicTypeKind());
		boolean constQ = node.isConstQualified();
		boolean volatileQ = node.isVolatileQualified();

		if (node.isRestrictQualified())
			throw error("restrict qualifier used with basic type", node);
		if (node.isAtomicQualified())
			unqualifiedType = typeFactory.atomicType(unqualifiedType);
		if (constQ || volatileQ)
			return typeFactory.qualifiedType(unqualifiedType, constQ,
					volatileQ, false);
		else
			return unqualifiedType;
	}

	/**
	 * 
	 * C11 6.7.6.2(1): "The element type shall not be an incomplete or function
	 * type. The optional type qualifiers and the keyword static shall appear
	 * only in a declaration of a function parameter with an array type, and
	 * then only in the outermost array type derivation."
	 * 
	 * @param node
	 * @return
	 * @throws SyntaxException
	 */
	private ObjectType processArrayType(ArrayTypeNode node, boolean isParameter)
			throws SyntaxException {
		TypeNode elementTypeNode = node.getElementType(); // non-null
		Type tempElementType = processTypeNode(elementTypeNode);
		ObjectType elementType;
		ExpressionNode sizeExpression;
		boolean constQ = node.isConstQualified();
		boolean volatileQ = node.isVolatileQualified();
		boolean restrictQ = node.isRestrictQualified();

		if (!(tempElementType instanceof ObjectType))
			throw error("Non-object type used for element type of array type",
					elementTypeNode);
		elementType = (ObjectType) tempElementType;
		if (!isParameter && !elementType.isComplete())
			throw error("Element type of array type is not complete",
					elementTypeNode);
		// C11 6.7.3(3):
		// "The type modified by the _Atomic qualifier shall not be an
		// array type or a function type."
		if (node.isAtomicQualified())
			throw error("_Atomic qualifier used with array type", node);
		// C11 6.7.3(9):
		// "If the specification of an array type includes any type qualifiers,
		// the element type is so-qualified, not the array type."
		elementType = typeFactory.qualify(elementType, constQ, volatileQ,
				restrictQ);
		if (restrictQ
				&& elementType instanceof QualifiedObjectType
				&& ((QualifiedObjectType) elementType).getBaseType().kind() != TypeKind.POINTER)
			throw error("Use of restrict qualifier with non-pointer type", node);
		if (isParameter) {
			PointerType pointerType = typeFactory.pointerType(elementType);
			UnqualifiedObjectType unqualifiedType = (node.hasAtomicInBrackets() ? typeFactory
					.atomicType(pointerType) : pointerType);

			return typeFactory.qualify(unqualifiedType,
					node.hasConstInBrackets(), node.hasVolatileInBrackets(),
					node.hasRestrictInBrackets());
		}
		if (node.hasAtomicInBrackets() || node.hasConstInBrackets()
				|| node.hasVolatileInBrackets() || node.hasRestrictInBrackets())
			throw error("Type qualifiers in [...] in an array declarator "
					+ "can only appear in a parameter declaration",
					elementTypeNode);
		if (node.hasUnspecifiedVariableLength()) // "*"
			return typeFactory.unspecifiedVariableLengthArrayType(elementType);
		sizeExpression = node.getExtent();
		if (sizeExpression == null)
			return typeFactory.incompleteArrayType(elementType);
		entityAnalyzer.expressionAnalyzer.processExpression(sizeExpression);
		if (sizeExpression.isConstantExpression()) {
			Value size = nodeFactory.getConstantValue(sizeExpression);

			return typeFactory.arrayType(elementType, size);
		}
		// C11 6.7.6.2(5): "If the size is an expression that is not an integer
		// constant expression: if it occurs in a declaration at function
		// prototype scope, it is treated as if it were replaced by *"
		if (node.getScope().getScopeKind() == ScopeKind.FUNCTION_PROTOTYPE)
			return typeFactory.unspecifiedVariableLengthArrayType(elementType);
		return typeFactory.variableLengthArrayType(elementType, sizeExpression);
	}

	private Type processPointerType(PointerTypeNode node)
			throws SyntaxException {
		TypeNode referencedTypeNode = node.referencedType();
		Type referencedType = processTypeNode(referencedTypeNode);
		UnqualifiedObjectType unqualifiedType = typeFactory
				.pointerType(referencedType);

		if (node.isAtomicQualified())
			unqualifiedType = typeFactory.atomicType(unqualifiedType);
		return typeFactory.qualify(unqualifiedType, node.isConstQualified(),
				node.isVolatileQualified(), node.isRestrictQualified());
	}

	private Type processAtomicType(AtomicTypeNode node) throws SyntaxException {
		// C11 6.7.2.4(3): "The type name in an atomic type specifier shall not
		// refer to an array type, a function type, an atomic type, or a
		// qualified type."
		Type baseType = processTypeNode(node.getBaseType());
		TypeKind kind = baseType.kind();

		if (kind == TypeKind.ARRAY)
			throw error(
					"Type name used in atomic type specifier refers to an array type",
					node);
		if (kind == TypeKind.FUNCTION)
			throw error(
					"Type name used in atomic type specifier refers to a function type",
					node);
		if (kind == TypeKind.ATOMIC)
			throw error(
					"Type name used in atomic type specifier refers to an atomic type",
					node);
		if (kind == TypeKind.QUALIFIED)
			throw error(
					"Type name used in atomic type specifier refers to a qualified type",
					node);
		return typeFactory.atomicType((UnqualifiedObjectType) baseType);
	}

	private Type processTypedefName(TypedefNameNode typeNode)
			throws SyntaxException {
		String name = typeNode.getName().name();
		Scope scope = typeNode.getScope();
		OrdinaryEntity entity = scope.getLexicalOrdinaryEntity(name);
		EntityKind kind = entity.getEntityKind();
		Typedef typedef;

		if (kind != EntityKind.TYPEDEF)
			throw error(
					"Internal error: typedef name does not refer to typedef",
					typeNode);
		typedef = (Typedef) entity;
		return typedef.getType();
	}

	private boolean onlyVoid(SequenceNode<VariableDeclarationNode> parameters) {
		if (parameters.numChildren() == 1) {
			VariableDeclarationNode decl = parameters.getSequenceChild(0);

			if (decl != null && decl.getIdentifier() == null) {
				TypeNode typeNode = decl.getTypeNode();

				return typeNode != null && typeNode.kind() == TypeNodeKind.VOID
						&& !typeNode.isAtomicQualified()
						&& !typeNode.isConstQualified()
						&& !typeNode.isRestrictQualified()
						&& !typeNode.isVolatileQualified();
			}
		}
		return false;
	}

	private Type processFunctionType(FunctionTypeNode node, boolean isParameter)
			throws SyntaxException {
		Type result;
		TypeNode returnTypeNode = node.getReturnType();
		SequenceNode<VariableDeclarationNode> parameters = node.getParameters();
		int numParameters = parameters.numChildren();
		boolean isDefinition = node.parent() instanceof FunctionDefinitionNode;
		boolean fromIdentifierList = node.hasIdentifierList();
		boolean hasVariableArgs = node.hasVariableArgs();
		Type tempReturnType = processTypeNode(returnTypeNode);
		ObjectType returnType;

		// "A function declarator shall not specify a return type that is a
		// function type or an array type."
		if (!(tempReturnType instanceof ObjectType))
			throw error(
					"Return type in function declaration is not an object type",
					returnTypeNode);
		if (tempReturnType instanceof ArrayType)
			throw error("Return type in function declaration is an array type",
					returnTypeNode);
		returnType = (ObjectType) tempReturnType;
		if (fromIdentifierList && !isDefinition && numParameters == 0) {
			// no information know about parameters
			result = typeFactory.functionType(returnType);
		} else {
			List<ObjectType> parameterTypes = new LinkedList<ObjectType>();

			if (hasVariableArgs || !onlyVoid(parameters)) {
				Iterator<VariableDeclarationNode> parameterIter = parameters
						.childIterator();

				while (parameterIter.hasNext()) {
					VariableDeclarationNode decl = parameterIter.next();
					TypeNode parameterTypeNode;

					entityAnalyzer.declarationAnalyzer
							.processVariableDeclaration(decl, true);
					parameterTypeNode = decl.getTypeNode();
					if (parameterTypeNode == null)
						throw error("No type specified for function parameter",
								decl);
					// TODO: check alignment specifiers, storage specifiers.
					parameterTypes
							.add((ObjectType) parameterTypeNode.getType());
				}
			}
			result = typeFactory.functionType(returnType, fromIdentifierList,
					parameterTypes, hasVariableArgs);
		}
		if (isParameter)
			result = typeFactory.pointerType(result);
		return result;
	}

}
