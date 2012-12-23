package edu.udel.cis.vsl.civl.ast.value.common;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity;
import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.AlignOfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ArrowNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.DotNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeofNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.FloatingType;
import edu.udel.cis.vsl.civl.ast.type.IF.IntegerType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardSignedIntegerType;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardSignedIntegerType.SignedIntKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardUnsignedIntegerType;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardUnsignedIntegerType.UnsignedIntKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.value.IF.AddressValue;
import edu.udel.cis.vsl.civl.ast.value.IF.ArrayElementReference;
import edu.udel.cis.vsl.civl.ast.value.IF.ArrayValue;
import edu.udel.cis.vsl.civl.ast.value.IF.CastValue;
import edu.udel.cis.vsl.civl.ast.value.IF.CharacterValue;
import edu.udel.cis.vsl.civl.ast.value.IF.ComplexValue;
import edu.udel.cis.vsl.civl.ast.value.IF.FunctionReference;
import edu.udel.cis.vsl.civl.ast.value.IF.IntegerValue;
import edu.udel.cis.vsl.civl.ast.value.IF.MemberReference;
import edu.udel.cis.vsl.civl.ast.value.IF.OperatorValue;
import edu.udel.cis.vsl.civl.ast.value.IF.RealFloatingValue;
import edu.udel.cis.vsl.civl.ast.value.IF.StringValue;
import edu.udel.cis.vsl.civl.ast.value.IF.StructureValue;
import edu.udel.cis.vsl.civl.ast.value.IF.TypeValue;
import edu.udel.cis.vsl.civl.ast.value.IF.TypeValue.TypeValueKind;
import edu.udel.cis.vsl.civl.ast.value.IF.UnionValue;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;
import edu.udel.cis.vsl.civl.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.civl.ast.value.IF.VariableReference;
import edu.udel.cis.vsl.civl.token.IF.ExecutionCharacter;
import edu.udel.cis.vsl.civl.token.IF.StringLiteral;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

/**
 * Flyweight pattern is used for those values which are immutable. Compound
 * values are mutable.
 * 
 * @author siegel
 * 
 */
public class CommonValueFactory implements ValueFactory {

	// Fields...

	private TypeFactory typeFactory;

	private Map<Value, Value> valueMap = new HashMap<Value, Value>();

	private StandardSignedIntegerType SINT;

	private IntegerType CHAR;

	private BigInteger MAX_JINT = new BigInteger(
			Integer.toString(Integer.MAX_VALUE));

	private Value SINT_ONE, SINT_ZERO;

	// Constructors...

	public CommonValueFactory(TypeFactory typeFactory) {
		this.typeFactory = typeFactory;
		SINT = typeFactory.signedIntegerType(SignedIntKind.INT);
		CHAR = (IntegerType) typeFactory.basicType(BasicTypeKind.CHAR);
		SINT_ONE = integerValue(SINT, 1);
		SINT_ZERO = integerValue(SINT, 0);
	}

	// Exported methods...

	@Override
	public Value evaluate(ExpressionNode expr) throws SyntaxException {
		try {
			return evaluateHelper(expr);
		} catch (UnsourcedException err) {
			throw error(err, expr);
		}
	}

	/**
	 * Should apply conversions to result.
	 * 
	 * @param expr
	 * @return
	 * @throws SyntaxException
	 * @throws UnsourcedException
	 */
	private Value evaluateHelper(ExpressionNode expr) throws SyntaxException,
			UnsourcedException {
		if (expr instanceof AlignOfNode) {
			return alignofValue(((AlignOfNode) expr).getArgument().getType());
		} else if (expr instanceof ArrowNode) {
			ArrowNode arrowNode = (ArrowNode) expr;
			ExpressionNode structOrUnionPointer = arrowNode
					.getStructurePointer();
			IdentifierNode fieldIdentifier = arrowNode.getFieldName();
			Field field = (Field) fieldIdentifier.getEntity();
			Value structOrUnionValue = evaluateDereference(structOrUnionPointer);

			return evaluateMemberAccess(structOrUnionValue, field);
		} else if (expr instanceof CastNode) {
			CastNode castNode = (CastNode) expr;

			return evaluateCast(castNode.getCastType().getType(),
					evaluate(castNode.getArgument()));
		} else if (expr instanceof CharacterConstantNode) {
			return ((CharacterConstantNode) expr).getConstantValue();
		} else if (expr instanceof CompoundLiteralNode) {
			return evaluateCompoundLiteral((CompoundLiteralNode) expr);
		} else if (expr instanceof ConstantNode) {
			return ((ConstantNode) expr).getConstantValue();
		} else if (expr instanceof DotNode) {
			DotNode dotNode = (DotNode) expr;
			ExpressionNode structOrUnion = dotNode.getStructure();
			IdentifierNode fieldIdentifier = dotNode.getFieldName();
			Field field = (Field) fieldIdentifier.getEntity();
			Value structOrUnionValue = evaluate(structOrUnion);

			return evaluateMemberAccess(structOrUnionValue, field);
		} else if (expr instanceof OperatorNode) {
			OperatorNode opNode = (OperatorNode) expr;
			Operator operator = opNode.getOperator();

			if (operator == Operator.ADDRESSOF)
				return addressValue(opNode.getArgument(0));
			else { // evaluate arguments and call apply
				int numArgs = opNode.getNumberOfArguments();
				Value[] argValues = new Value[numArgs];
				Type type = expr.getInitialType();

				for (int i = 0; i < numArgs; i++) {
					ExpressionNode arg = opNode.getArgument(i);
					Value argValue = evaluate(arg);

					argValues[i] = applyConversions(arg, argValue);
				}
				return apply(type, operator, argValues);
			}
		} else if (expr instanceof SizeofNode) {
			return sizeofValue((((SizeofNode) expr).getArgument()).getType());
		} else if (expr instanceof StringLiteralNode) {
			return ((StringLiteralNode) expr).getConstantValue();
		}
		return null;
	}

	@Override
	public AddressValue addressValue(ExpressionNode lhs) throws SyntaxException {
		if (lhs instanceof IdentifierExpressionNode) {
			Entity entity = ((IdentifierExpressionNode) lhs).getIdentifier()
					.getEntity();
			EntityKind kind = entity.getEntityKind();

			if (kind == EntityKind.VARIABLE) {
				return variableReference((Variable) entity);
			} else if (kind == EntityKind.FUNCTION) {
				return functionReference((Function) entity);
			} else {
				throw error("Operand of & not variable or function identifier",
						lhs);
			}
		} else if (lhs instanceof DotNode) {
			AddressValue structureOrUnionReference = addressValue(((DotNode) lhs)
					.getStructure());
			Field field = (Field) ((DotNode) lhs).getFieldName().getEntity();

			return memberReference(structureOrUnionReference, field);
		} else if (lhs instanceof OperatorNode) {
			OperatorNode opNode = (OperatorNode) lhs;
			Operator operator = ((OperatorNode) lhs).getOperator();

			if (operator == Operator.SUBSCRIPT) {
				AddressValue arrayReference = addressValue(opNode
						.getArgument(0));
				Value index = evaluate(opNode.getArgument(1));

				return arrayElementReference(arrayReference, index);
			} else if (operator == Operator.DEREFERENCE) {
				return (AddressValue) evaluate(opNode.getArgument(0));
			}
		}
		throw error("Cannot take address of expression", lhs);
	}

	@Override
	public IntegerValue integerValue(IntegerType type, BigInteger integerValue) {
		return (IntegerValue) canonic(new CommonIntegerValue(type, integerValue));
	}

	@Override
	public IntegerValue integerValue(IntegerType type, int intValue) {
		return integerValue(type, BigInteger.valueOf(intValue));
	}

	@Override
	public RealFloatingValue realFloatingValue(FloatingType type, int radix,
			BigInteger wholePartValue, BigInteger fractionPartValue,
			int fractionLength, BigInteger exponentValue) {
		return (RealFloatingValue) canonic(new CommonRealFloatingValue(type,
				radix, wholePartValue, fractionPartValue, fractionLength,
				exponentValue));
	}

	@Override
	public ComplexValue complexValue(FloatingType type,
			RealFloatingValue realPart, RealFloatingValue imaginaryPart) {
		return (ComplexValue) canonic(new CommonComplexValue(type, realPart,
				imaginaryPart));
	}

	@Override
	public TypeValue sizeofValue(Type type) {
		return (TypeValue) canonic(new CommonTypeValue(typeFactory.size_t(),
				TypeValueKind.SIZEOF, type));
	}

	@Override
	public TypeValue alignofValue(Type type) {
		return (TypeValue) canonic(new CommonTypeValue(typeFactory.size_t(),
				TypeValueKind.ALIGNOF, type));
	}

	@Override
	public CastValue castValue(Type castType, Value argument) {
		return (CastValue) canonic(new CommonCastValue(castType, argument));
	}

	@Override
	public OperatorValue operatorValue(Type type, Operator operator,
			Value[] arguments) {
		return (OperatorValue) canonic(new CommonOperatorValue(type, operator,
				arguments));
	}

	@Override
	public StructureValue newStructureValue(StructureOrUnionType type) {
		return new CommonStructureValue(type);
	}

	@Override
	public UnionValue newUnionValue(StructureOrUnionType unionType,
			Field field, Value memberValue) {
		return (UnionValue) canonic(new CommonUnionValue(unionType, field,
				memberValue));
	}

	@Override
	public ArrayValue newArrayValue(ArrayType type) {
		return new CommonArrayValue(type);
	}

	@Override
	public Answer isZero(Value value) {
		return value.isZero();
	}

	@Override
	public CharacterValue characterValue(ExecutionCharacter character) {
		IntegerType type;

		switch (character.getCharacterKind()) {
		case CHAR:
			type = CHAR;
			break;
		case WCHAR:
			type = typeFactory.wchar_t();
			break;
		case CHAR16:
			type = typeFactory.char16_t();
			break;
		case CHAR32:
			type = typeFactory.char32_t();
			break;
		default:
			throw new RuntimeException("unreachable");
		}
		return (CharacterValue) canonic(new CommonCharacterValue(type,
				character));
	}

	@Override
	/**
	 * Precondition: string literal should already have the \0 appended.
	 * In particular, it has at least one character.
	 * 
	 */
	public StringValue stringValue(StringLiteral literal) {
		int length = literal.getNumCharacters();
		IntegerValue size = integerValue(SINT, length);
		IntegerType characterType;
		ArrayType type;

		switch (literal.getStringKind()) {
		case CHAR:
		case UTF_8:
			characterType = CHAR;
			break;
		case WCHAR:
			characterType = typeFactory.wchar_t();
			break;
		case CHAR16:
			characterType = typeFactory.char16_t();
			break;
		case CHAR32:
			characterType = typeFactory.char32_t();
			break;
		default:
			throw new RuntimeException("unreachable");
		}
		type = typeFactory.arrayType(characterType, size);
		return (StringValue) canonic(new CommonStringValue(type, literal));
	}

	public VariableReference variableReference(Variable variable) {
		PointerType pointerType = typeFactory.pointerType(variable.getType());

		return (VariableReference) canonic(new CommonVariableReference(
				pointerType, variable));
	}

	public FunctionReference functionReference(Function function) {
		PointerType pointerType = typeFactory.pointerType(function.getType());

		return (FunctionReference) canonic(new CommonFunctionReference(
				pointerType, function));
	}

	public ArrayElementReference arrayElementReference(
			AddressValue arrayReference, Value index) {
		PointerType arrayReferenceType = arrayReference.getType();
		ArrayType arrayType = (ArrayType) arrayReferenceType.referencedType();
		// might need to strip qualifiers?
		ObjectType elementType = arrayType.getElementType();
		PointerType elementReferenceType = typeFactory.pointerType(elementType);

		return (ArrayElementReference) canonic(new CommonArrayElementReference(
				elementReferenceType, arrayReference, index));
	}

	public MemberReference memberReference(
			AddressValue structureOrUnionReference, Field field) {
		ObjectType memberType = field.getType();
		PointerType memberReferenceType = typeFactory.pointerType(memberType);

		return (MemberReference) canonic(new CommonMemberReference(
				memberReferenceType, structureOrUnionReference, field));
	}

	// Helper methods.......................................................

	private Value canonic(Value value) {
		Value result = valueMap.get(value);

		if (result == null) {
			valueMap.put(value, value);
			return value;
		}
		return result;
	}

	/**
	 * Given expression e of pointer type, evaluate *e. If e had type
	 * [qualified] pointer to t, resulting value has type t.
	 * 
	 * @param pointerExpression
	 * @return
	 */
	private Value evaluateDereference(ExpressionNode pointerExpression)
			throws SyntaxException {
		return null; // how can this happen in a constant expression?
	}

	private Value evaluateCast(Type castType, Value value)
			throws UnsourcedException {
		// TODO: cast concrete numeric types if you can, pointer types, ...
		return canonic(new CommonCastValue(castType, value));
	}

	private Value evaluateCompoundLiteral(CompoundLiteralNode node) {
		// TODO
		return null;
	}

	private Value apply(Type type, Operator operator, Value[] args)
			throws UnsourcedException {
		switch (operator) {
		case BITAND: // & bit-wise and</li>
		case BITCOMPLEMENT: // ~ bit-wise complement</li>
		case BITOR: // | bit-wise inclusive or</li>
		case BITXOR: // ^ bit-wise exclusive or</li>
		case CONDITIONAL: // ?: the conditional operator</li>
		case DEREFERENCE: // * pointer dereference</li>
		case DIV: // / numerical division</li>
		case EQUALS: // == equality</li>
		case GT: // > greater than</li>
		case GTE: // >= greater than or equals</li>
		case LAND: // && logical and</li>
		case LOR: // || logical or</li>
		case LT: // < less than</li>
		case LTE: // <= less than or equals</li>
		case MINUS: // - binary subtraction (numbers and pointers)</li>
		case MOD: // % integer modulus</li>
		case NEQ: // != not equals</li>
		case NOT: // ! logical not</li>
		case PLUS: // + binary addition, numeric or pointer</li>
		case SHIFTLEFT: // << shift left</li>
		case SHIFTRIGHT: // >> shift right</li>
		case SUBSCRIPT: // [] array subscript</li>
		case TIMES: // * numeric multiplication</li>
		case UNARYMINUS: // - numeric negative</li>
		case UNARYPLUS: // + numeric no-op</li>
		}
		// TODO: handle specials cases for all of above
		return canonic(new CommonOperatorValue(type, operator, args));
	}

	private Value applyConversions(ExpressionNode expr, Value value) {
		// TODO
		return null;
	}

	/**
	 * 
	 * C11 Sec. 6.3.1.2: "When any scalar value is converted to _Bool, the
	 * result is 0 if the value compares equal to 0; otherwise, the result is
	 * 1."
	 * 
	 * C11 Sec. 6.3.1.3:
	 * 
	 * <blockquote> When a value with integer type is converted to another
	 * integer type other than _Bool, if the value can be represented by the new
	 * type, it is unchanged.
	 * 
	 * Otherwise, if the new type is unsigned, the value is converted by
	 * repeatedly adding or subtracting one more than the maximum value that can
	 * be represented in the new type until the value is in the range of the new
	 * type.
	 * 
	 * Otherwise, the new type is signed and the value cannot be represented in
	 * it; either the result is implementation-defined or an
	 * implementation-defined signal is raised. </blockquote>
	 * 
	 * 
	 * @param value
	 * @param newType
	 * @return
	 */
	private Value evaluateIntegerConversion(Value value, IntegerType newType) {
		IntegerType oldType = (IntegerType) value.getType();

		if (oldType.equals(newType))
			return value;
		if (value instanceof IntegerValue) {
			BigInteger intVal = ((IntegerValue) value).getIntegerValue();

			// first: if intVal representable in new type, that's it:
			if (newType instanceof StandardSignedIntegerType) {
				StandardSignedIntegerType newSigned = (StandardSignedIntegerType) newType;

				if (newSigned.inMinimumRange(intVal)) {
					return integerValue(newType, intVal);
				} else if (oldType instanceof StandardSignedIntegerType) {
					SignedIntKind newKind = newSigned.getIntKind();
					SignedIntKind oldKind = ((StandardSignedIntegerType) oldType)
							.getIntKind();

					if (newKind.compareTo(oldKind) > 0)
						return integerValue(newType, intVal);
				}
			} else if (newType instanceof StandardUnsignedIntegerType) {
				StandardUnsignedIntegerType newSigned = (StandardUnsignedIntegerType) newType;

				if (newSigned.inMinimumRange(intVal)) {
					return integerValue(newType, intVal);
				} else if (oldType instanceof StandardUnsignedIntegerType) {
					UnsignedIntKind newKind = newSigned.getIntKind();
					UnsignedIntKind oldKind = ((StandardUnsignedIntegerType) oldType)
							.getIntKind();

					if (newKind.compareTo(oldKind) > 0)
						return integerValue(newType, intVal);
				}
			}
		}
		return castValue(newType, value);
	}

	private Value evaluateAndorOr(Operator operator, ExpressionNode expr1,
			ExpressionNode expr2) throws SyntaxException {
		boolean isAnd = operator == Operator.LAND;
		Value v1 = evaluate(expr1);

		return null;
	}

	/**
	 * Evaluates plus in case where both types are integers. NOTE types of these
	 * values must be converted types from expression.
	 * 
	 * @param a1
	 * @param a2
	 * @return
	 */
	private Value evaluateBinaryIntegerOperator(Operator operator, Value a1,
			Value a2) {
		IntegerType type1 = (IntegerType) a1.getType();
		IntegerType type;

		switch (operator) {
		case PLUS:
		case TIMES:
		case MINUS:
		case DIV:
		case SHIFTLEFT:
		case SHIFTRIGHT:
			type = type1;
			break;
		case EQUALS:
		case LTE:
		case GTE:
		case NEQ:
		case LT:
		case GT:
			type = SINT;
			break;
		default:
			throw new RuntimeException(
					"This method should not be called with operator "
							+ operator);
		}
		if (a1.equals(a2)) {
			switch (operator) {
			case EQUALS:
			case LTE:
			case GTE:
				return SINT_ONE;
			case NEQ:
			case LT:
			case GT:
				return SINT_ZERO;
			default:
			}
		}
		if (a1 instanceof IntegerValue && a2 instanceof IntegerValue) {
			BigInteger v1 = ((IntegerValue) a1).getIntegerValue();
			BigInteger v2 = ((IntegerValue) a2).getIntegerValue();
			BigInteger v3 = null;

			switch (operator) {
			case PLUS:
				v3 = v1.add(v2);
				break;
			case TIMES:
				v3 = v1.multiply(v2);
				break;
			case MINUS:
				v3 = v1.subtract(v2);
				break;
			case DIV:
				v3 = v1.divide(v2);
				break;
			case EQUALS:
				v3 = BigInteger.ZERO;
				break;
			case NEQ:
				v3 = BigInteger.ONE;
				break;
			case LT:
				v3 = v1.compareTo(v2) < 0 ? BigInteger.ONE : BigInteger.ZERO;
				break;
			case GT:
				v3 = v1.compareTo(v2) > 0 ? BigInteger.ONE : BigInteger.ZERO;
				break;
			case LTE:
				v3 = v1.compareTo(v2) <= 0 ? BigInteger.ONE : BigInteger.ZERO;
				break;
			case GTE:
				v3 = v1.compareTo(v2) >= 0 ? BigInteger.ONE : BigInteger.ZERO;
				break;
			case SHIFTLEFT:
			case SHIFTRIGHT:
				if (v1.signum() >= 0 && v2.signum() >= 0
						&& MAX_JINT.compareTo(v2) >= 0) {
					int v2small = v2.intValue();

					if (operator == Operator.SHIFTLEFT)
						v3 = v1.shiftLeft(v2small);
					else
						v3 = v1.shiftRight(v2small);
				}
				break;
			default:
			}
			if (v3 != null && type instanceof StandardSignedIntegerType
					&& ((StandardSignedIntegerType) type).inMinimumRange(v3)
					|| type instanceof StandardUnsignedIntegerType
					&& ((StandardUnsignedIntegerType) type).inMinimumRange(v3)) {
				return integerValue(type, v3);
			}
		}
		return operatorValue(type, operator, new Value[] { a1, a2 });
	}

	private Value evaluateMemberAccess(Value structureOrUnionValue, Field field)
			throws UnsourcedException {
		if (structureOrUnionValue instanceof StructureValue) {
			return ((StructureValue) structureOrUnionValue).getMember(field);
		}
		if (structureOrUnionValue instanceof UnionValue) {
			UnionValue unionValue = (UnionValue) structureOrUnionValue;
			Field unionField = unionValue.getField();

			if (!unionField.equals(field))
				throw new UnsourcedException(
						"Union value field differs from requested field:\n"
								+ unionField + "\n" + field);
			return unionValue.getMemberValue();
		}
		// TODO: need to create a new dot value.
		throw new UnsourcedException(
				"Cannot evaluate structure or union value: "
						+ structureOrUnionValue);
	}

	private SyntaxException error(String message, ASTNode node) {
		return new SyntaxException(message, node.getSource());
	}

	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return new SyntaxException(e, node.getSource());
	}

}
