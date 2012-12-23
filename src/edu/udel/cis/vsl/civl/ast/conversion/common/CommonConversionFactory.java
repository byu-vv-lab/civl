package edu.udel.cis.vsl.civl.ast.conversion.common;

import edu.udel.cis.vsl.civl.analysis.common.CompatiblePointerConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ArithmeticConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ArrayConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.CompatibleStructureOrUnionConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.civl.ast.conversion.IF.FunctionConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.LvalueConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.NullPointerConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.PointerBoolConversion;
import edu.udel.cis.vsl.civl.ast.conversion.IF.VoidPointerConversion;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ArithmeticType;
import edu.udel.cis.vsl.civl.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.civl.ast.type.IF.AtomicType;
import edu.udel.cis.vsl.civl.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;
import edu.udel.cis.vsl.civl.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.StandardUnsignedIntegerType.UnsignedIntKind;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.UnqualifiedObjectType;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

public class CommonConversionFactory implements ConversionFactory {

	private TypeFactory typeFactory;

	public CommonConversionFactory(TypeFactory typeFactory) {
		this.typeFactory = typeFactory;
	}

	// private SyntaxException error(String message, ASTNode node) {
	// return new SyntaxException(message, node.getSource());
	// }

	private UnsourcedException error(String message) {
		return new UnsourcedException(message);
	}

	@Override
	public TypeFactory getTypeFactory() {
		return typeFactory;
	}

	@Override
	public ArithmeticConversion arithmeticConversion(ArithmeticType oldType,
			ArithmeticType newType) {
		return new CommonArithmeticConversion(oldType, newType);
	}

	@Override
	public CompatibleStructureOrUnionConversion compatibleStructureOrUnionConversion(
			StructureOrUnionType type1, StructureOrUnionType type2) {
		return new CommonCompatibleStructureOrUnionConversion(type1, type2);
	}

	@Override
	public CompatiblePointerConversion compatiblePointerConversion(
			PointerType type1, PointerType type2) {
		return new CommonCompatiblePointerConversion(type1, type2);
	}

	@Override
	public VoidPointerConversion voidPointerConversion(PointerType type1,
			PointerType type2) {
		return new CommonVoidPointerConversion(type1, type2);
	}

	@Override
	public NullPointerConversion nullPointerConversion(ObjectType type1,
			PointerType type2) {
		return new CommonNullPointerConversion(type1, type2);
	}

	@Override
	public PointerBoolConversion pointerBoolConversion(PointerType oldType) {
		return new CommonPointerBoolConversion(oldType,
				typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL));
	}

	@Override
	public LvalueConversion lvalueConversion(ObjectType type) {
		UnqualifiedObjectType result = lvalueConversionType(type);

		if (result.equals(type))
			return null;
		return new CommonLvalueConversion(type, result);
	}

	@Override
	public UnqualifiedObjectType lvalueConversionType(ObjectType type) {
		TypeKind kind = type.kind();

		if (kind == TypeKind.QUALIFIED)
			return lvalueConversionType(((QualifiedObjectType) type)
					.getBaseType());
		if (kind == TypeKind.ATOMIC)
			return lvalueConversionType(((AtomicType) type).getBaseType());
		return (UnqualifiedObjectType) type;
	}

	@Override
	public ArrayConversion arrayConversion(ArrayType type) {
		return new CommonArrayConversion(type, typeFactory.pointerType(type
				.getElementType()));
	}

	@Override
	public FunctionConversion functionConversion(FunctionType type) {
		return new CommonFunctionConversion(type, typeFactory.pointerType(type));
	}

	private void checkQualifierConsistency(PointerType type1,
			PointerType type2, boolean checkBaseCompatibility)
			throws UnsourcedException {
		Type base1 = type1.referencedType();
		Type base2 = type2.referencedType();

		// any qualifier on base1 must also occur in base2...
		if (base1 instanceof QualifiedObjectType) {
			QualifiedObjectType qualified1 = (QualifiedObjectType) base1;
			QualifiedObjectType qualified2;

			if (!(base2 instanceof QualifiedObjectType))
				throw error("Referenced type of left-hand of assignment lacks qualifiers of right-hand side");
			qualified2 = (QualifiedObjectType) base2;
			if (qualified1.isConstQualified() && !qualified2.isConstQualified())
				throw error("Type referenced by pointer on left-hand side of assignment"
						+ " lacks const qualifier occurring on right-hand side");
			if (qualified1.isRestrictQualified()
					&& !qualified2.isRestrictQualified())
				throw error("Type referenced by pointer on left-hand side of assignment"
						+ " lacks restrict qualifier occurring on right-hand side");
			if (qualified1.isVolatileQualified()
					&& !qualified2.isVolatileQualified())
				throw error("Type referenced by pointer on left-hand side of assignment"
						+ " lacks volatile qualifier occurring on right-hand side");
			base1 = qualified1.getBaseType();
			base2 = qualified2.getBaseType();
		}
		if (base1 instanceof AtomicType) {
			if (!(base2 instanceof AtomicType))
				throw error("Type referenced by pointer on left-hand side of assigment "
						+ "lacks atomic qualifier occurring on right-hand side");
			base1 = ((AtomicType) base1).getBaseType();
			base2 = ((AtomicType) base2).getBaseType();
		}
		if (checkBaseCompatibility && !base1.compatibleWith(base2))
			throw error("Type referenced by pointer on left-hand side of assignment "
					+ "is incompatible with corresponding type on right-hand side");
	}

	@Override
	public boolean isNullPointerConstant(ExpressionNode node) {
		if (node instanceof CastNode) {
			CastNode castNode = (CastNode) node;
			Type castType = castNode.getCastType().getType();

			if (castType instanceof PointerType
					&& ((PointerType) castType).referencedType().kind() == TypeKind.VOID)
				node = castNode.getArgument();
			else
				return false;
		}
		if (node instanceof IntegerConstantNode)
			return ((IntegerConstantNode) node).getConstantValue()
					.getIntegerValue().signum() == 0;
		return false;
	}

	@Override
	public boolean isPointerToVoid(PointerType type) {
		Type base = type.referencedType();

		if (base instanceof QualifiedObjectType) {
			base = ((QualifiedObjectType) base).getBaseType();
		}
		if (base instanceof AtomicType) {
			base = ((AtomicType) base).getBaseType();
		}
		return base.kind() == TypeKind.VOID;
	}

	@Override
	public boolean isPointerToObject(PointerType type) {
		return type.referencedType() instanceof ObjectType;
	}

	@Override
	public Conversion assignmentConversion(ExpressionNode rhs, Type newType)
			throws UnsourcedException {
		Type oldType = rhs.getConvertedType();

		if (newType.equals(oldType))
			return null;
		if (oldType instanceof ArithmeticType
				&& newType instanceof ArithmeticType) {
			return arithmeticConversion((ArithmeticType) oldType,
					(ArithmeticType) newType);
		}
		if (oldType instanceof StructureOrUnionType
				&& newType instanceof StructureOrUnionType) {
			StructureOrUnionType type1 = (StructureOrUnionType) oldType;
			StructureOrUnionType type2 = (StructureOrUnionType) newType;

			if (!type1.compatibleWith(type2))
				throw error("Assignment to incompatible structure or union type");
			return new CommonCompatibleStructureOrUnionConversion(type1, type2);
		}
		if (newType instanceof PointerType && isNullPointerConstant(rhs))
			return nullPointerConversion((ObjectType) oldType,
					(PointerType) newType);
		if (oldType instanceof PointerType && newType instanceof PointerType) {
			PointerType type1 = (PointerType) oldType;
			PointerType type2 = (PointerType) newType;

			if (isPointerToObject(type1) && isPointerToVoid(type2)
					|| isPointerToObject(type2) && isPointerToVoid(type1)) {
				checkQualifierConsistency(type1, type2, false);
				return voidPointerConversion(type1, type2);
			}
			checkQualifierConsistency(type1, type2, true);
			return new CommonCompatiblePointerConversion(type1, type2);
		}
		throw error("No conversion from type of right hand side to that of left:\n"
				+ oldType + "\n" + newType);
	}

}
