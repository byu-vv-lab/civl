package edu.udel.cis.vsl.civl.analysis.entity;

import edu.udel.cis.vsl.civl.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.AlignOfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ArrowNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.DotNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.EnumerationConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.GenericSelectionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeofNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ArithmeticType;
import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.AtomicType;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.IntegerType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardSignedIntegerType.SignedIntKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.UnqualifiedObjectType;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

public class ExpressionAnalyzer {

	// ***************************** Fields *******************************

	private EntityAnalyzer entityAnalyzer;

	private ConversionFactory conversionFactory;

	private TypeFactory typeFactory;

	private IntegerType intType;

	// ************************** Constructors ****************************

	ExpressionAnalyzer(EntityAnalyzer entityAnalyzer,
			ConversionFactory conversionFactory, TypeFactory typeFactory) {
		this.entityAnalyzer = entityAnalyzer;
		this.conversionFactory = conversionFactory;
		this.typeFactory = typeFactory;
		this.intType = typeFactory.signedIntegerType(SignedIntKind.INT);
	}

	// ************************* Exported Methods **************************

	/**
	 * Processes an expression node. This method will set the type of node and
	 * the converted type of all of node's children nodes.
	 * 
	 * @param node
	 *            an expression node
	 * @throws SyntaxException
	 */
	void processExpression(ExpressionNode node) throws SyntaxException {
		if (node instanceof AlignOfNode)
			processAlignOf((AlignOfNode) node);
		else if (node instanceof ArrowNode)
			processArrow((ArrowNode) node);
		else if (node instanceof CastNode)
			processCast((CastNode) node);
		else if (node instanceof CompoundLiteralNode)
			processCompoundLiteral((CompoundLiteralNode) node);
		else if (node instanceof ConstantNode)
			processConstant((ConstantNode) node);
		else if (node instanceof DotNode)
			processDot((DotNode) node);
		else if (node instanceof FunctionCallNode)
			processFunctionCall((FunctionCallNode) node);
		else if (node instanceof GenericSelectionNode)
			processGenericSelection((GenericSelectionNode) node);
		else if (node instanceof IdentifierExpressionNode)
			processIdentifierExpression((IdentifierExpressionNode) node);
		else if (node instanceof OperatorNode)
			processOperator((OperatorNode) node);
		else if (node instanceof SizeofNode)
			processSizeof((SizeofNode) node);
		else
			throw error("Unknown expression kind", node);
	}

	/**
	 * Given the type of the left hand side of an assignment, and the expression
	 * which is the right hand side, this method will add any conversions needed
	 * to the right hand side and return the type of the assignment, i.e., the
	 * result of applying lvalue conversion to the left hand side type. This
	 * method may be used for initializations in variable declarations, as well
	 * as simple assignments.
	 * 
	 * @param lhsType
	 *            type of left hand side
	 * @param rhs
	 *            expression
	 * @return type of assignment
	 * @throws UnsourcedException
	 *             if the types are not compatible
	 */
	UnqualifiedObjectType processAssignment(ObjectType lhsType,
			ExpressionNode rhs) throws UnsourcedException {
		UnqualifiedObjectType type = conversionFactory
				.lvalueConversionType(lhsType);

		addStandardConversions(rhs);
		convertRHS(rhs, type);
		return type;
	}

	// ************************ Private Methods ***************************

	private void processAlignOf(AlignOfNode node) throws SyntaxException {
		entityAnalyzer.typeAnalyzer.processTypeNode(node.getArgument());
		node.setInitialType(typeFactory.size_t());
	}

	/**
	 * C11 Sec. 6.5.2.3:
	 * 
	 * "The first operand of the -> operator shall have type ÔÔpointer to
	 * atomic, qualified, or unqualified structureÕÕ or ÔÔpointer to atomic,
	 * qualified, or unqualified unionÕÕ, and the second operand shall name a
	 * member of the type pointed to."
	 * 
	 * "A postfix expression followed by the -> operator and an identifier
	 * designates a member of a structure or union object. The value is that of
	 * the named member of the object to which the first expression points, and
	 * is an lvalue. If the first expression is a pointer to a qualified type,
	 * the result has the so-qualified version of the type of the designated
	 * member."
	 * 
	 * "Accessing a member of an atomic structure or union object results in
	 * undefined behavior."
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processArrow(ArrowNode node) throws SyntaxException {
		IdentifierNode identifier = node.getFieldName();
		ExpressionNode pointerNode = node.getStructurePointer();
		String fieldName = identifier.name();
		StructureOrUnionType structureOrUnionType;
		boolean atomicQ = false, restrictQ = false, constQ = false, volatileQ = false;
		Field field;
		Type tempType, type;
		ObjectType fieldType;

		processExpression(pointerNode);
		addLvalueConversion(pointerNode);
		tempType = pointerNode.getConvertedType();
		if (tempType.kind() != TypeKind.POINTER)
			throw error("Left operand of arrow operator not pointer",
					pointerNode);
		tempType = ((PointerType) tempType).referencedType();
		if (tempType.kind() == TypeKind.QUALIFIED) {
			QualifiedObjectType qType = (QualifiedObjectType) tempType;

			constQ = qType.isConstQualified();
			restrictQ = qType.isRestrictQualified();
			volatileQ = qType.isVolatileQualified();
			tempType = qType.getBaseType();
		}
		if (tempType.kind() == TypeKind.ATOMIC) {
			atomicQ = true;
			tempType = ((AtomicType) tempType).getBaseType();
		}
		if (tempType.kind() != TypeKind.STRUCTURE_OR_UNION)
			throw error(
					"Left operand of arrow operator not pointer to structure or union",
					pointerNode);
		structureOrUnionType = (StructureOrUnionType) tempType;
		field = structureOrUnionType.getField(fieldName);
		if (field == null)
			throw error(
					"Structure or union type " + structureOrUnionType.getTag()
							+ " contains no field named " + fieldName,
					identifier);
		identifier.setEntity(field);
		fieldType = field.getType();
		type = typeFactory.qualify(fieldType, atomicQ, constQ, volatileQ,
				restrictQ);
		node.setInitialType(type);
	}

	private void processCast(CastNode node) throws SyntaxException {
		TypeNode typeNode = node.getCastType();
		ExpressionNode expression = node.getArgument();

		entityAnalyzer.typeAnalyzer.processTypeNode(typeNode);
		processExpression(expression);
		addStandardConversions(expression);
		node.setInitialType(typeNode.getType());
	}

	private void processCompoundLiteral(CompoundLiteralNode node)
			throws SyntaxException {
		Type type = entityAnalyzer.typeAnalyzer.processTypeNode(node
				.getTypeNode());

		if (!(type instanceof ObjectType))
			throw error("Compound literal has non-object type: " + type, node);
		entityAnalyzer.declarationAnalyzer.processInitializer(
				node.getInitializerList(), (ObjectType) type);
		node.setInitialType(type);
	}

	private void processConstant(ConstantNode node) throws SyntaxException {
		if (node instanceof CharacterConstantNode) {
			// type should already be set
		} else if (node instanceof IntegerConstantNode) {
			// type should already be set.
		} else if (node instanceof EnumerationConstantNode) {
			String name = node.getStringRepresentation();
			OrdinaryEntity entity = node.getScope().getLexicalOrdinaryEntity(
					name);
			EntityKind kind;
			EnumerationType type;

			if (entity == null)
				throw error("Undeclared enumeration constant?", node);
			kind = entity.getEntityKind();
			if (kind != EntityKind.ENUMERATOR)
				throw error("Use of " + kind + " " + name
						+ " as enumeration constant?", node);
			type = ((Enumerator) entity).getType();
			node.setInitialType(type);
		} else if (node instanceof FloatingConstantNode) {
			// type should already be set
		} else if (node instanceof StringLiteralNode) {
			// type should already be set
		} else
			throw new RuntimeException("Unknown kind of constant node: " + node);
		if (node.getInitialType() == null)
			throw error("Internal error: did not set type", node);
	}

	/**
	 * C11 Sec. 6.5.2.3:
	 * 
	 * "The first operand of the . operator shall have an atomic, qualified, or
	 * unqualified structure or union type, and the second operand shall name a
	 * member of that type."
	 * 
	 * "A postfix expression followed by the . operator and an identifier
	 * designates a member of a structure or union object. The value is that of
	 * the named member, and is an lvalue if the first expression is an lvalue.
	 * If the first expression has qualified type, the result has the
	 * so-qualified version of the type of the designated member."
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processDot(DotNode node) throws SyntaxException {
		ExpressionNode expression = node.getStructure();
		IdentifierNode identifier = node.getFieldName();
		String fieldName = identifier.name();
		boolean atomicQ = false, restrictQ = false, constQ = false, volatileQ = false;
		StructureOrUnionType structureOrUnionType;
		ObjectType fieldType;
		Type tempType, type;
		Field field;

		processExpression(expression);
		tempType = expression.getType();
		// no lvalue conversion for left operand of . operator:
		if (tempType.kind() == TypeKind.QUALIFIED) {
			QualifiedObjectType qType = (QualifiedObjectType) tempType;

			constQ = qType.isConstQualified();
			restrictQ = qType.isRestrictQualified();
			volatileQ = qType.isVolatileQualified();
			tempType = qType.getBaseType();
		}
		if (tempType.kind() == TypeKind.ATOMIC) {
			atomicQ = true;
			tempType = ((AtomicType) tempType).getBaseType();
		}
		if (tempType.kind() != TypeKind.STRUCTURE_OR_UNION)
			throw error("Left operand of dot operator not structure or union",
					expression);
		structureOrUnionType = (StructureOrUnionType) tempType;
		field = structureOrUnionType.getField(fieldName);
		if (field == null)
			throw error(
					"Structure or union type " + structureOrUnionType.getTag()
							+ " contains no field named " + fieldName,
					identifier);
		identifier.setEntity(field);
		fieldType = field.getType();
		type = typeFactory.qualify(fieldType, atomicQ, constQ, volatileQ,
				restrictQ);
		node.setInitialType(type);
	}

	private void processFunctionCall(FunctionCallNode node)
			throws SyntaxException {
		// TODO: check type agreement, adjust types, etc.
		ExpressionNode functionNode = node.getFunction();
		int numArgs = node.getNumberOfArguments();
		Type tmpType;
		TypeKind tmpKind;
		FunctionType functionType;

		processExpression(functionNode);
		tmpType = functionNode.getType();
		tmpKind = tmpType.kind();
		if (tmpKind == TypeKind.POINTER) {
			tmpType = ((PointerType) tmpType).referencedType();
			tmpKind = tmpType.kind();
		}
		if (tmpKind == TypeKind.FUNCTION)
			functionType = (FunctionType) tmpType;
		else
			throw error(
					"Function expression in function call does not have function "
							+ "type or pointer to function type", functionNode);
		for (int i = 0; i < numArgs; i++) {
			ExpressionNode argument = node.getArgument(i);

			processExpression(argument);
			// conversions?
		}
		node.setInitialType(functionType.getReturnType());
	}

	private void processGenericSelection(GenericSelectionNode node)
			throws SyntaxException {
		// TODO
	}

	private void processIdentifierExpression(IdentifierExpressionNode node)
			throws SyntaxException {
		IdentifierNode identifierNode = node.getIdentifier();
		String name = identifierNode.name();
		OrdinaryEntity entity = node.getScope().getLexicalOrdinaryEntity(name);
		EntityKind kind;

		if (entity == null)
			throw error("Undeclared identifier " + name, node);
		kind = entity.getEntityKind();
		switch (kind) {
		case VARIABLE:
		case FUNCTION:
			break;
		default:
			throw error("Use of " + kind + " " + name + " as expression", node);
		}
		identifierNode.setEntity(entity);
		node.setInitialType(entity.getType());
	}

	private void processOperator(OperatorNode node) throws SyntaxException {
		Operator operator = node.getOperator();
		int numArgs = node.getNumberOfArguments();

		// the following sets the initial type of each argument:
		for (int i = 0; i < numArgs; i++)
			processExpression(node.getArgument(i));
		switch (operator) {
		case ADDRESSOF: // & pointer to object
			processADDRESSOF(node);
			break;
		case ASSIGN: // = standard assignment operator
			processASSIGN(node);
			break;
		case BITAND: // & bit-wise and
		case BITOR: // | bit-wise inclusive or
		case BITXOR: // ^ bit-wise exclusive or
			processBitwise(node);
			break;
		case BITANDEQ: // &= bit-wise and assignment
		case BITOREQ: // |= bit-wise inclusive or assignment
		case BITXOREQ: // ^= bit-wise exclusive or assignment
			processBitwiseAssign(node);
			break;
		case BITCOMPLEMENT: // ~ bit-wise complement
			processBITCOMPLEMENT(node);
			break;
		case COMMA: // : the comma operator
			processCOMMA(node);
			break;
		case CONDITIONAL: // ?: the conditional operator
			processCONDITIONAL(node);
			break;
		case DEREFERENCE: // * pointer dereference
			processDEREFERENCE(node);
			break;
		case DIVEQ: // /= division assignment
		case MODEQ: // %= integer modulus assignment
		case TIMESEQ: // *= multiplication assignment
			processTIMESEQorDIVEQorMODEQ(node);
			break;
		case EQUALS: // == equality
		case NEQ: // != not equals
			processEqualityOperator(node);
			break;
		case LAND: // && logical and
		case LOR: // || logical or
		case NOT: // ! logical not
			processLANDorLORorNOT(node);
			break;
		case GT: // > greater than
		case GTE: // >= greater than or equals
		case LT: // < less than
		case LTE: // <= less than or equals
			processRelational(node);
			break;
		case MINUS: // - binary subtraction (numbers and pointers)
			processMINUS(node);
			break;
		case PLUS: // + binary addition: numeric or pointer
			processPLUS(node);
			break;
		case MINUSEQ: // -= subtraction assignment
		case PLUSEQ: // += addition assignment
			processPLUSEQorMINUSEQ(node);
			break;
		case POSTDECREMENT: // -- decrement after expression
		case POSTINCREMENT: // ++ increment after expression
			processPostfixOperators(node);
			break;
		case PREDECREMENT: // -- decrement before expression
		case PREINCREMENT: // ++ increment before expression
			processPrefixOperators(node);
			break;
		case SHIFTLEFT: // << shift left
		case SHIFTRIGHT: // >> shift right
			processSHIFTLEFTorSHIFTRIGHT(node);
			break;
		case SHIFTLEFTEQ: // <<= shift left assignment
		case SHIFTRIGHTEQ: // >>= shift right assignment
			processSHIFTLEFTEQorSHIFTRIGHTEQ(node);
			break;
		case SUBSCRIPT: // [] array subscript
			processSUBSCRIPT(node);
			break;
		case DIV: // / numerical division
		case MOD: // % integer modulus
		case TIMES: // * numeric multiplication
			processTIMESorDIVorMOD(node);
			break;
		case UNARYMINUS: // - numeric negative
		case UNARYPLUS: // + numeric no-op
			processUNARAYPLUSorUNARYMINUS(node);
			break;
		default:
			throw new RuntimeException("Unknown operator: " + operator);
		}
	}

	private void processSizeof(SizeofNode node) throws SyntaxException {
		SizeableNode argument = node.getArgument();

		if (argument instanceof TypeNode) {
			entityAnalyzer.typeAnalyzer.processTypeNode((TypeNode) argument);
		} else if (argument instanceof ExpressionNode) {
			processExpression((ExpressionNode) argument);
		} else {
			assert false;
		}
		node.setInitialType(typeFactory.size_t());
	}

	// Operators...

	private void processADDRESSOF(OperatorNode node) {
		ExpressionNode arg0 = node.getArgument(0);

		node.setInitialType(typeFactory.pointerType(arg0.getType()));
	}

	/**
	 * Processes a simple assignment of the form lhs = rhs.
	 * 
	 * @param node
	 *            an OperatorNode with operator ASSIGN
	 * @throws SyntaxException
	 *             if there is a type incompatibility between the two sides
	 */
	private void processASSIGN(OperatorNode node) throws SyntaxException {
		ExpressionNode rhs = node.getArgument(1);
		Type type = assignmentType(node);

		addStandardConversions(rhs);
		try {
			convertRHS(rhs, type);
		} catch (UnsourcedException e) {
			throw error(e, node);
		}
		node.setInitialType(type);
	}

	/**
	 * C11 Sec. 6.5.3.3 says the argument must have integer type, and
	 * 
	 * <blockquote> The result of the ~ operator is the bitwise complement of
	 * its (promoted) operand (that is, each bit in the result is set if and
	 * only if the corresponding bit in the converted operand is not set). The
	 * integer promotions are performed on the operand, and the result has the
	 * promoted type. If the promoted type is an unsigned type, the expression
	 * ~E is equivalent to the maximum value representable in that type minus E.
	 * </blockquote>
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processBITCOMPLEMENT(OperatorNode node) throws SyntaxException {
		node.setInitialType(doIntegerPromotion(node.getArgument(0)));
	}

	/**
	 * See Sec. 6.5.17.
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processCOMMA(OperatorNode node) throws SyntaxException {
		node.setInitialType(addStandardConversions(node.getArgument(1)));
	}

	/**
	 * From C11 Sec. 6.5.15:
	 * 
	 * <blockquote> The first operand shall have scalar type.
	 * 
	 * One of the following shall hold for the second and third operands:
	 * <ul>
	 * <li>both operands have arithmetic type;</li>
	 * <li>both operands have the same structure or union type;</li>
	 * <li>both operands have void type;</li>
	 * <li>both operands are pointers to qualified or unqualified versions of
	 * compatible types;</li>
	 * <li>one operand is a pointer and the other is a null pointer constant; or
	 * </li>
	 * <li>one operand is a pointer to an object type and the other is a pointer
	 * to a qualified or unqualified version of void.</li>
	 * </ul>
	 * 
	 * <p>
	 * If both the second and third operands have arithmetic type, the result
	 * type that would be determined by the usual arithmetic conversions, were
	 * they applied to those two operands, is the type of the result. If both
	 * the operands have structure or union type, the result has that type. If
	 * both operands have void type, the result has void type.
	 * </p>
	 * 
	 * <p>
	 * If both the second and third operands are pointers or one is a null
	 * pointer constant and the other is a pointer, the result type is a pointer
	 * to a type qualified with all the type qualifiers of the types referenced
	 * by both operands. Furthermore, if both operands are pointers to
	 * compatible types or to differently qualified versions of compatible
	 * types, the result type is a pointer to an appropriately qualified version
	 * of the composite type; if one operand is a null pointer constant, the
	 * result has the type of the other operand; otherwise, one operand is a
	 * pointer to void or a qualified version of void, in which case the result
	 * type is a pointer to an appropriately qualified version of void.
	 * </p>
	 * 
	 * </blockquote>
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processCONDITIONAL(OperatorNode node) throws SyntaxException {
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		ExpressionNode arg2 = node.getArgument(2);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);
		Type type2 = addStandardConversions(arg2);
		Type type;

		if (!isScalar(type0))
			throw error(
					"First argument of conditional operator has non-scalar type: "
							+ type0, arg0);
		if (type1 instanceof ArithmeticType && type2 instanceof ArithmeticType) {
			type = typeFactory.usualArithmeticConversion(
					(ArithmeticType) type1, (ArithmeticType) type2);
		} else if (type1 instanceof StructureOrUnionType) {
			if (!type1.equals(type2))
				throw error(
						"Operands of conditional operator have incompatible types",
						node);
			type = type1;
		} else if (type1.kind() == TypeKind.VOID
				&& type2.kind() == TypeKind.VOID) {
			type = type1;
		} else if (conversionFactory.isNullPointerConstant(arg1)
				&& type2 instanceof PointerType) {
			type = type2;
		} else if (conversionFactory.isNullPointerConstant(arg2)
				&& type1 instanceof PointerType) {
			type = type1;
		} else if (type1 instanceof PointerType && type2 instanceof PointerType) {
			PointerType p0 = (PointerType) type1;
			PointerType p1 = (PointerType) type2;
			boolean atomicQ = false, constQ = false, volatileQ = false, restrictQ = false;
			Type base0 = p0.referencedType();
			Type base1 = p1.referencedType();

			if (base0 instanceof QualifiedObjectType) {
				QualifiedObjectType q0 = (QualifiedObjectType) base0;

				constQ = q0.isConstQualified();
				volatileQ = q0.isVolatileQualified();
				restrictQ = q0.isRestrictQualified();
				base0 = q0.getBaseType();
			}
			if (base0 instanceof AtomicType) {
				atomicQ = true;
				base0 = ((AtomicType) base0).getBaseType();
			}
			if (base1 instanceof QualifiedObjectType) {
				QualifiedObjectType q1 = (QualifiedObjectType) base1;

				constQ = constQ || q1.isConstQualified();
				volatileQ = volatileQ || q1.isVolatileQualified();
				restrictQ = restrictQ || q1.isRestrictQualified();
				base1 = q1.getBaseType();
			}
			if (base1 instanceof AtomicType) {
				atomicQ = true;
				base1 = ((AtomicType) base1).getBaseType();
			}
			if (base0.kind() == TypeKind.VOID || base1.kind() == TypeKind.VOID)
				type = base0;
			else if (base0.compatibleWith(base1))
				type = typeFactory.compositeType(base0, base1);
			else
				throw error("Incompatible pointer types in conditional:\n"
						+ type1 + "\n" + type2, node);
			type = typeFactory.pointerType(type);
			if (atomicQ)
				type = typeFactory.atomicType((PointerType) type);
			type = typeFactory.qualify((ObjectType) type, constQ, volatileQ,
					restrictQ);
		} else {
			throw error(
					"Incompatible types for second and third arguments of conditional operator:\n"
							+ type1 + "\n" + type2, node);
		}
		node.setInitialType(type);
	}

	/**
	 * Complete processing of PLUS node.
	 * 
	 * Cases: pointer + integer, integer + pointer, arithmetic + arithmetic,
	 * 
	 * TODO: consider actually adding information to the node to say what kind
	 * of addition it is (arithmetic, pointer)
	 * 
	 * @param node
	 */
	private void processPLUS(OperatorNode node) throws SyntaxException {
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (type0 instanceof ArithmeticType && type1 instanceof ArithmeticType)
			node.setInitialType(doUsualArithmetic(arg0, arg1));
		else if (isPointerToCompleteObjectType(type0)
				&& type1 instanceof IntegerType)
			node.setInitialType(type0);
		else if (type0 instanceof IntegerType
				&& isPointerToCompleteObjectType(type1))
			node.setInitialType(type1);
		else
			throw error("Arguments cannot be added", node);
	}

	/**
	 * Processes a binary minus operator expression. From C11 Sec. 6.5.6:
	 * 
	 * <blockquote>
	 * 
	 * For subtraction, one of the following shall hold:
	 * <ul>
	 * <li>both operands have arithmetic type;</li>
	 * <li>both operands are pointers to qualified or unqualified versions of
	 * compatible complete object types; or</li>
	 * <li>the left operand is a pointer to a complete object type and the right
	 * operand has integer type.</li>
	 * </ul>
	 * 
	 * </blockquote>
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processMINUS(OperatorNode node) throws SyntaxException {
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (type0 instanceof ArithmeticType && type1 instanceof ArithmeticType)
			node.setInitialType(doUsualArithmetic(arg0, arg1));
		else if (isPointerToCompleteObjectType(type0)
				&& type1 instanceof IntegerType)
			node.setInitialType(type0);
		else if (pointerToCompatibleComplete(type0, type1))
			node.setInitialType(typeFactory.ptrdiff_t());
		else
			throw error("Arguments cannot be subtracted", node);
	}

	/**
	 * Processes a += or -= expression. From C11 Sec. 6.5.16.2:
	 * 
	 * <blockquote> For the operators += and -= only, either the left operand
	 * shall be an atomic, qualified, or unqualified pointer to a complete
	 * object type, and the right shall have integer type; or the left operand
	 * shall have atomic, qualified, or unqualified arithmetic type, and the
	 * right shall have arithmetic type. </blockquote>
	 * 
	 * Note: this is almost equivalent to "lhs = lhs + rhs" which results in the
	 * following conversions:
	 * 
	 * <pre>
	 * lhs = (C->L)((L->C)lhs + (R->C)rhs)
	 * </pre>
	 * 
	 * where L is the type of the left hand side (after lvalue conversion), R is
	 * the type of the right hand side (after lvalue conversion) and C is the
	 * type resulting from the "usual arithmetic conversions" applied to L and
	 * R. Hence in the worst case there are 3 conversions, but we don't have a
	 * place to put them all in the unexpanded form (i.e., there's no place for
	 * the L->C conversion since that term is not in the AST).
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processPLUSEQorMINUSEQ(OperatorNode node)
			throws SyntaxException {
		Type type = assignmentType(node);
		ExpressionNode rhs = node.getArgument(1);
		Type rightType = addStandardConversions(rhs);

		if (isPointerToCompleteObjectType(type)
				&& rightType instanceof IntegerType)
			; // pointer addition: nothing to do
		else if (type instanceof ArithmeticType
				&& rightType instanceof ArithmeticType)
			doArithmeticCompoundAssign((ArithmeticType) type, rhs);
		else
			throw error("Inappropriate arguments to += operator.  "
					+ "Argument types:\n" + type + "\n" + rightType, node);
		node.setInitialType(type);
	}

	private void processTIMESorDIVorMOD(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0), type1 = addStandardConversions(arg1);

		if (operator == Operator.MOD) {
			if (!(type0 instanceof IntegerType))
				throw error("Arguments to % must have integer type", arg0);
			if (!(type1 instanceof IntegerType))
				throw error("Arguments to % must have integer type", arg1);
		} else {
			if (!(type0 instanceof ArithmeticType))
				throw error("Arguments to " + operator
						+ " must have arithmetic type", arg0);
			if (!(type1 instanceof ArithmeticType))
				throw error("Arguments to " + operator
						+ " must have arithmetic type", arg1);
		}
		node.setInitialType(doUsualArithmetic(arg0, arg1));
	}

	private void processTIMESEQorDIVEQorMODEQ(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		Type type = assignmentType(node);
		ExpressionNode lhs = node.getArgument(0);
		ExpressionNode rhs = node.getArgument(1);
		Type rightType = addStandardConversions(rhs);

		if (operator == Operator.MOD) {
			if (!(type instanceof IntegerType))
				throw error("Arguments to % must have integer type", lhs);
			if (!(rightType instanceof IntegerType))
				throw error("Arguments to % must have integer type", rhs);
		} else {
			if (!(type instanceof ArithmeticType))
				throw error("Arguments to " + operator
						+ " must have arithmetic type", lhs);
			if (!(rightType instanceof ArithmeticType))
				throw error("Arguments to " + operator
						+ " must have arithmetic type", rhs);
		}
		doArithmeticCompoundAssign((ArithmeticType) type, rhs);
		node.setInitialType(type);
	}

	/**
	 * From C11 Sec. 6.5.7:
	 * 
	 * <blockquote> Each of the operands shall have integer type.
	 * 
	 * The integer promotions are performed on each of the operands. The type of
	 * the result is that of the promoted left operand. If the value of the
	 * right operand is negative or is greater than or equal to the width of the
	 * promoted left operand, the behavior is undefined. </blockquote>
	 * 
	 * @param node
	 */
	private void processSHIFTLEFTorSHIFTRIGHT(OperatorNode node)
			throws SyntaxException {
		node.setInitialType(doIntegerPromotion(node.getArgument(0)));
		doIntegerPromotion(node.getArgument(1));
	}

	/**
	 * Recall from C11 Sec. 6.5.16:
	 * 
	 * <blockquote> An assignment operator stores a value in the object
	 * designated by the left operand. An assignment expression has the value of
	 * the left operand after the assignment, but is not an lvalue. The type of
	 * an assignment expression is the type the left operand would have after
	 * lvalue conversion. The side effect of updating the stored value of the
	 * left operand is sequenced after the value computations of the left and
	 * right operands. The evaluations of the operands are unsequenced.
	 * </blockquote>
	 * 
	 * and
	 * 
	 * <blockquote> For the other operators, the left operand shall have atomic,
	 * qualified, or unqualified arithmetic type, and (considering the type the
	 * left operand would have after lvalue conversion) each operand shall have
	 * arithmetic type consistent with those allowed by the corresponding binary
	 * operator. </blockquote>
	 * 
	 * @param node
	 *            expression node with operator SHIFTLEFTEQ or SHIFTRIGHTEQ
	 * @throws SyntaxException
	 */
	private void processSHIFTLEFTEQorSHIFTRIGHTEQ(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = arg0.getConvertedType();
		Conversion conversion;
		Type type;

		if (!(type0 instanceof ObjectType))
			throw error("First argument to " + operator
					+ " has non-object type: " + type0, arg0);
		conversion = conversionFactory.lvalueConversion((ObjectType) type0);
		if (conversion == null)
			type = type0;
		else
			type = conversion.getNewType();
		if (!(type instanceof IntegerType))
			throw error("First argument to " + operator
					+ " has non-integer type: " + type0, arg0);
		addStandardConversions(arg1);
		doIntegerPromotion(arg1);
		node.setInitialType(type);
	}

	/**
	 * C11 Sec. 6.5.8: <blockquote> One of the following shall hold:
	 * <ul>
	 * <li>both operands have real type; or</li>
	 * <li>both operands are pointers to qualified or unqualified versions of
	 * compatible object types.</li>
	 * </ul>
	 * 
	 * If both of the operands have arithmetic type, the usual arithmetic
	 * conversions are performed.
	 * 
	 * Each of the operators < (less than), > (greater than), <= (less than or
	 * equal to), and >= (greater than or equal to) shall yield 1 if the
	 * specified relation is true and 0 if it is false.) The result has type
	 * int. </blockquote>
	 * 
	 * @param node
	 *            an expression node for one of the operators LT, GT, LTE, or
	 *            GTE.
	 */
	private void processRelational(OperatorNode node) throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (type0 instanceof ArithmeticType && type1 instanceof ArithmeticType) {
			if (!((ArithmeticType) type0).inRealDomain())
				throw error("Argument to relational operator " + operator
						+ " must have real type", arg0);
			if (!((ArithmeticType) type1).inRealDomain())
				throw error("Argument to relational operator " + operator
						+ " must have real type", arg1);
			doUsualArithmetic(arg0, arg1);
		} else if (pointerToCompatibleObject(type0, type1)) {
			// nothing to do
		} else
			throw error("Illegal arguments to operator " + operator, node);
		node.setInitialType(intType);
	}

	/**
	 * 6.5.2.1: "One of the expressions shall have type ÔÔpointer to complete
	 * object typeÕÕ, the other expression shall have integer type, and the
	 * result has type ÔÔtypeÕÕ."
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processSUBSCRIPT(OperatorNode node) throws SyntaxException {
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (!(type1 instanceof IntegerType))
			throw error("Subscript does not have integer type:\n" + type1, arg1);
		if (isPointerToCompleteObjectType(type0))
			node.setInitialType(((PointerType) type0).referencedType());
		else
			throw error(
					"First argument to subscript operator not pointer to complete object type:\n"
							+ type0, arg0);
	}

	private void processBitwise(OperatorNode node) throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (!(type0 instanceof IntegerType))
			throw error("Argument to bitwise operator " + operator
					+ " must have integer type", arg0);
		if (!(type1 instanceof IntegerType))
			throw error("Argument to bitwise operator " + operator
					+ " must have integer type", arg1);
		node.setInitialType(doUsualArithmetic(arg0, arg1));
	}

	private void processBitwiseAssign(OperatorNode node) throws SyntaxException {
		Operator operator = node.getOperator();
		Type type = assignmentType(node);
		ExpressionNode lhs = node.getArgument(0);
		ExpressionNode rhs = node.getArgument(1);
		Type rightType = addStandardConversions(rhs);

		if (!(type instanceof IntegerType))
			throw error("Argument to bitwise operator " + operator
					+ " must have integer type", lhs);
		if (!(rightType instanceof IntegerType))
			throw error("Argument to bitwise operator " + operator
					+ " must have integer type", rhs);
		doArithmeticCompoundAssign((ArithmeticType) type, rhs);
		node.setInitialType(type);
	}

	/**
	 * Each operand must have "scalar" type, i.e., arithmetic or pointer. Result
	 * has type int (0 or 1).
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processLANDorLORorNOT(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		Type type0 = addStandardConversions(arg0);

		if (!isScalar(type0))
			throw error("Argument to logical operator " + operator
					+ " does not have scalar type; type is " + type0, arg0);
		if (node.getNumberOfArguments() > 1) {
			ExpressionNode arg1 = node.getArgument(1);
			Type type1 = addStandardConversions(arg1);

			if (!isScalar(type1))
				throw error("Argument to logical operator " + operator
						+ " does not have scalar type; type is " + type1, arg1);
		}
		node.setInitialType(intType);
	}

	/**
	 * 
	 * From C11 Sec. 6.5.9:
	 * 
	 * <blockquote> One of the following shall hold:
	 * <ul>
	 * <li>both operands have arithmetic type;</li>
	 * <li>both operands are pointers to qualified or unqualified versions of
	 * compatible types;</li>
	 * <li>one operand is a pointer to an object type and the other is a pointer
	 * to a qualified or unqualified version of void; or</li>
	 * <li>one operand is a pointer and the other is a null pointer constant.</li>
	 * </ul>
	 * 
	 * <p>
	 * The == (equal to) and != (not equal to) operators are analogous to the
	 * relational operators except for their lower precedence.108) Each of the
	 * operators yields 1 if the specified relation is true and 0 if it is
	 * false. The result has type int. For any pair of operands, exactly one of
	 * the relations is true.
	 * </p>
	 * 
	 * <p>
	 * If both of the operands have arithmetic type, the usual arithmetic
	 * conversions are performed. Values of complex types are equal if and only
	 * if both their real parts are equal and also their imaginary parts are
	 * equal. Any two values of arithmetic types from different type domains are
	 * equal if and only if the results of their conversions to the (complex)
	 * result type determined by the usual arithmetic conversions are equal.
	 * </p>
	 * 
	 * <p>
	 * Otherwise, at least one operand is a pointer. If one operand is a pointer
	 * and the other is a null pointer constant, the null pointer constant is
	 * converted to the type of the pointer. If one operand is a pointer to an
	 * object type and the other is a pointer to a qualified or unqualified
	 * version of void, the former is converted to the type of the latter.
	 * </p>
	 * 
	 * <p>
	 * Two pointers compare equal if and only if both are null pointers, both
	 * are pointers to the same object (including a pointer to an object and a
	 * subobject at its beginning) or function, both are pointers to one past
	 * the last element of the same array object, or one is a pointer to one
	 * past the end of one array object and the other is a pointer to the start
	 * of a different array object that happens to immediately follow the first
	 * array object in the address space.
	 * </p>
	 * 
	 * <p>
	 * For the purposes of these operators, a pointer to an object that is not
	 * an element of an array behaves the same as a pointer to the first element
	 * of an array of length one with the type of the object as its element
	 * type.
	 * </p>
	 * </blockquote>
	 * 
	 * 
	 * @param node
	 */
	private void processEqualityOperator(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg0 = node.getArgument(0);
		ExpressionNode arg1 = node.getArgument(1);
		Type type0 = addStandardConversions(arg0);
		Type type1 = addStandardConversions(arg1);

		if (type0 instanceof ArithmeticType && type1 instanceof ArithmeticType) {
			doUsualArithmetic(arg0, arg1);
		} else if (pointerToCompatibleTypes(type0, type1)) {
			// no conversions necessary
		} else if (type0 instanceof PointerType
				&& conversionFactory.isNullPointerConstant(arg1)) {
			arg1.addConversion(conversionFactory.nullPointerConversion(
					(ObjectType) type1, (PointerType) type0));
		} else if (type1 instanceof PointerType
				&& conversionFactory.isNullPointerConstant(arg0)) {
			arg0.addConversion(conversionFactory.nullPointerConversion(
					(ObjectType) type0, (PointerType) type1));
		} else if (type0 instanceof PointerType && type1 instanceof PointerType) {
			PointerType p0 = (PointerType) type0;
			PointerType p1 = (PointerType) type1;

			if (conversionFactory.isPointerToObject(p0)
					&& conversionFactory.isPointerToVoid(p1)) {
				arg0.addConversion(conversionFactory.voidPointerConversion(p0,
						p1));
			} else if (conversionFactory.isPointerToObject(p1)
					&& conversionFactory.isPointerToVoid(p0)) {
				arg0.addConversion(conversionFactory.voidPointerConversion(p0,
						p1));
			} else
				throw error("Incompatible pointer types for operator "
						+ operator + ":\n" + type0 + "\n" + type1, node);
		} else
			throw error("Incompatible types for operator " + operator + ":\n"
					+ type0 + "\n" + type1, node);
		node.setInitialType(intType);
	}

	/**
	 * In both cases: the operand must be arithmetic, and the integer promotions
	 * are performed. The type is the promoted type.
	 * 
	 * @param node
	 *            expression node for unary + or - operator
	 */
	private void processUNARAYPLUSorUNARYMINUS(OperatorNode node)
			throws SyntaxException {
		Operator operator = node.getOperator();
		ExpressionNode arg = node.getArgument(0);
		Type type = addStandardConversions(arg);

		if (!(type instanceof ArithmeticType))
			throw error("Argument to unary operator " + operator
					+ " has non-arithmetic type: " + type, node);
		if (type instanceof IntegerType)
			type = doIntegerPromotion(arg);
		node.setInitialType(type);
	}

	/**
	 * 
	 * 6.5.2.4.
	 * 
	 * The operand of the postfix increment or decrement operator shall have
	 * atomic, qualified, or unqualified real or pointer type, and shall be a
	 * modifiable lvalue.
	 * 
	 * No lvalue conversion is performed. However, array and function
	 * conversions are performed.
	 * 
	 * @param node
	 */
	private void processPostfixOperators(OperatorNode node)
			throws SyntaxException {
		ExpressionNode arg = node.getArgument(0);
		Type type, baseType;

		addArrayConversion(arg);
		addFunctionConversion(arg);
		type = arg.getConvertedType();
		baseType = stripQualifiers(type);
		if (baseType instanceof ArithmeticType) {
			if (!((ArithmeticType) baseType).inRealDomain())
				throw error("Cannot apply ++ or -- to complex type", node);
		} else if (baseType instanceof PointerType) {
			// nothing to check
		} else
			throw error("Cannot apply ++ or -- to type: " + baseType, node);
		node.setInitialType(type);
	}

	/**
	 * No difference from postfix operators for purposes of type analysis.
	 * 
	 * @param node
	 * @throws SyntaxException
	 */
	private void processPrefixOperators(OperatorNode node)
			throws SyntaxException {
		processPostfixOperators(node);
	}

	private void processDEREFERENCE(OperatorNode node) throws SyntaxException {
		ExpressionNode arg = node.getArgument(0);
		Type type = addStandardConversions(arg);

		if (!(type instanceof PointerType))
			throw error("Argument to * has non-pointer type: " + type, node);
		node.setInitialType(((PointerType) type).referencedType());
	}

	// Helper functions...

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}

	/**
	 * Given unqualified type, determines whether it is "scalar" (arithmetic or
	 * pointer type).
	 * 
	 * @param type
	 *            unqualified, non-atomic type
	 * @return true if scalar, false otherwise
	 */
	private boolean isScalar(Type type) {
		return type instanceof ArithmeticType || type instanceof PointerType;
	}

	private void addArrayConversion(ExpressionNode node) {
		Type oldType = node.getConvertedType();

		if (oldType instanceof ArrayType) {
			Conversion conversion = conversionFactory
					.arrayConversion((ArrayType) oldType);

			node.addConversion(conversion);
		}
	}

	private void addFunctionConversion(ExpressionNode node) {
		Type oldType = node.getConvertedType();

		if (oldType instanceof FunctionType) {
			Conversion conversion = conversionFactory
					.functionConversion((FunctionType) oldType);

			node.addConversion(conversion);
		}
	}

	private void addLvalueConversion(ExpressionNode node) {
		Type oldType = node.getConvertedType();

		if (oldType instanceof ObjectType) {
			Conversion conversion = conversionFactory
					.lvalueConversion((ObjectType) oldType);

			if (conversion != null)
				node.addConversion(conversion);
		}
	}

	/**
	 * Applies array conversion, function conversion, and lvalue conversion to
	 * the given expression. The node is updates as necessary by adding any
	 * nontrivial conversions to the node's conversion list.
	 * 
	 * @param node
	 *            an expression node
	 * @return the post-coversion type of the expression
	 */
	private Type addStandardConversions(ExpressionNode node) {
		addArrayConversion(node);
		addFunctionConversion(node);
		addLvalueConversion(node);
		return node.getConvertedType();
	}

	private Type stripQualifiers(Type type) {
		if (type instanceof QualifiedObjectType)
			type = ((QualifiedObjectType) type).getBaseType();
		if (type instanceof AtomicType)
			type = ((AtomicType) type).getBaseType();
		return type;
	}

	/**
	 * Given an unqualified, non-atomic type, tells whether the type is a
	 * pointer to a complete object type.
	 * 
	 * @param type
	 * @return
	 */
	private boolean isPointerToCompleteObjectType(Type type) {
		if (type instanceof PointerType) {
			Type baseType = ((PointerType) type).referencedType();

			if (baseType instanceof ObjectType
					&& ((ObjectType) baseType).isComplete())
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * Returns true iff both types are pointer types, and the types pointed to
	 * are qualified or unqualified versions of compatible types.
	 * 
	 * @param type0
	 *            any type
	 * @param type1
	 *            any type
	 * @return true iff condition above holds
	 */
	private boolean pointerToCompatibleTypes(Type type0, Type type1) {
		if (type0 instanceof PointerType && type1 instanceof PointerType) {
			Type base0 = stripQualifiers(((PointerType) type0).referencedType());
			Type base1 = stripQualifiers(((PointerType) type1).referencedType());

			return base0.compatibleWith(base1);
		}
		return false;
	}

	/**
	 * Returns true iff both types are pointer types, and the types pointed to
	 * are qualified or unqualified versions of compatible object types.
	 * 
	 * @param type0
	 *            any type
	 * @param type1
	 *            any type
	 * @return true iff condition above holds
	 */
	private boolean pointerToCompatibleObject(Type type0, Type type1) {
		if (type0 instanceof PointerType && type1 instanceof PointerType) {
			Type base0 = stripQualifiers(((PointerType) type0).referencedType());
			Type base1 = stripQualifiers(((PointerType) type1).referencedType());

			return base0 instanceof ObjectType && base1 instanceof ObjectType
					&& base0.compatibleWith(base1);
		}
		return false;
	}

	/**
	 * Returns true iff both types are pointer types, and the types pointed to
	 * are qualified or unqualified versions of compatible complete object
	 * types.
	 * 
	 * @param type0
	 *            any type
	 * @param type1
	 *            any type
	 * @return true iff condition above holds
	 */
	private boolean pointerToCompatibleComplete(Type type0, Type type1) {
		if (type0 instanceof PointerType && type1 instanceof PointerType) {
			Type base0 = stripQualifiers(((PointerType) type0).referencedType());
			Type base1 = stripQualifiers(((PointerType) type1).referencedType());

			return base0 instanceof ObjectType && base1 instanceof ObjectType
					&& ((ObjectType) base0).isComplete()
					&& ((ObjectType) base1).isComplete()
					&& base0.compatibleWith(base1);
		}
		return false;
	}

	/**
	 * Given two expression with arithmetic type, computes the common type
	 * resulting from the "usual arithmetic conversions", adds conversions as
	 * needed to the two expressions, and returns the common type.
	 * 
	 * This method does not perform the standard conversions (lvalue, array,
	 * function). If you want those, do them first, then invoke this method.
	 * 
	 * @param arg0
	 *            expression of arithmetic type
	 * @param arg1
	 *            expression of arithmetic type
	 * @return the common type resulting from the usual arithmetic conversions
	 */
	private ArithmeticType doUsualArithmetic(ExpressionNode arg0,
			ExpressionNode arg1) {
		ArithmeticType a0 = (ArithmeticType) arg0.getConvertedType();
		ArithmeticType a1 = (ArithmeticType) arg1.getConvertedType();
		ArithmeticType type = typeFactory.usualArithmeticConversion(a0, a1);

		if (!type.equals(a0))
			arg0.addConversion(conversionFactory.arithmeticConversion(a0, type));
		if (!type.equals(a1))
			arg1.addConversion(conversionFactory.arithmeticConversion(a1, type));
		return type;
	}

	/**
	 * Given an assignment expression (for a simple or compound assignment),
	 * this method computes the type of the expression. The type of the
	 * expression is the result of applying lvalue conversion to the left hand
	 * side. The expression is not modified.
	 * 
	 * @param assignExpression
	 * @return the type of the assignment expression
	 * @throws SyntaxException
	 *             if the type of the left hand side is not an object type
	 */
	private Type assignmentType(OperatorNode assignExpression)
			throws SyntaxException {
		ExpressionNode leftNode = assignExpression.getArgument(0);
		Type leftType = leftNode.getType();
		Conversion leftConversion;

		if (!(leftType instanceof ObjectType))
			throw error(
					"Left argument of assignment does not have object type",
					leftNode);
		leftConversion = conversionFactory
				.lvalueConversion((ObjectType) leftType);
		return leftConversion == null ? leftType : leftConversion.getNewType();
	}

	/**
	 * Given (1) the type of a (simple or compound) assignment expression, and
	 * (2) the right hand side argument of that assignment expression, this
	 * method adds an implicit arithmetic conversion to the rhs argument if one
	 * is needed. The conversion is to the type resulting from applying the
	 * "usual arithmetic conversions" to the two types.
	 * 
	 * Recall that the type of an assignment expression if the type that results
	 * from applying lvalue conversion to the left hand side.
	 * 
	 * @param assignmentType
	 *            the type of the assignment expression
	 * @param rightNode
	 *            the right hand side argument of the assignment expression
	 */
	private void doArithmeticCompoundAssign(ArithmeticType assignmentType,
			ExpressionNode rightNode) {
		ArithmeticType a1 = (ArithmeticType) rightNode.getConvertedType();
		ArithmeticType commonType = typeFactory.usualArithmeticConversion(
				assignmentType, a1);

		if (!commonType.equals(a1))
			rightNode.addConversion(conversionFactory.arithmeticConversion(a1,
					commonType));
	}

	private void convertRHS(ExpressionNode rightNode, Type type)
			throws UnsourcedException {
		Conversion rightConversion = conversionFactory.assignmentConversion(
				rightNode, type);

		if (rightConversion != null)
			rightNode.addConversion(rightConversion);
	}

	/**
	 * Given an expression node of integer type, performs the standard
	 * conversions and then the integer promotion, adding conversions as
	 * necessary to the node.
	 * 
	 * @param node
	 *            an expression node
	 * @return the post-conversion type of the expression
	 * @throws SyntaxException
	 *             if the node does not have integer type
	 */
	private IntegerType doIntegerPromotion(ExpressionNode node)
			throws SyntaxException {
		Type type = addStandardConversions(node);

		if (type instanceof IntegerType) {
			IntegerType promotedType = typeFactory
					.integerPromotion((IntegerType) type);

			if (promotedType.equals(type))
				return (IntegerType) type;
			else {
				node.addConversion(conversionFactory.arithmeticConversion(
						(IntegerType) type, promotedType));
				return promotedType;
			}
		} else {
			throw error("Expected expression of integer type", node);
		}
	}

}
