package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * Implementation of {@link CIVLPrimitiveType}.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonPrimitiveType extends CommonType implements
		CIVLPrimitiveType {

	private PrimitiveTypeKind kind;

	private SymbolicType symbolicType;

	/**
	 * One of: int, bool, real, string, ...
	 * 
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public CommonPrimitiveType(PrimitiveTypeKind kind, SymbolicType symbolicType) {
		this.kind = kind;
		this.symbolicType = symbolicType;
	}

	/**
	 * @return The actual primitive type (int, bool, real, or string).
	 */
	public PrimitiveTypeKind primitiveTypeKind() {
		return kind;
	}

	/**
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public void setPrimitiveType(PrimitiveTypeKind primitiveType) {
		this.kind = primitiveType;
	}

	@Override
	public String toString() {
		switch (kind) {
		case INT:
			return "$int";
		case BOOL:
			return "$bool";
		case REAL:
			return "$real";
		case STRING:
			return "$string";
		case SCOPE:
			return "$scope";
		case PROCESS:
			return "$proc";
		case DYNAMIC:
			return "$dynamicType";
		case HEAP:
			return "$heap";
		default:
			throw new CIVLInternalException("Unreachable", (CIVLSource) null);
		}
	}

	@Override
	public boolean isNumericType() {
		return kind == PrimitiveTypeKind.INT || kind == PrimitiveTypeKind.REAL;
	}

	@Override
	public boolean isIntegerType() {
		return kind == PrimitiveTypeKind.INT;
	}

	@Override
	public boolean isRealType() {
		return kind == PrimitiveTypeKind.REAL;
	}

	@Override
	public SymbolicType getSymbolicType() {
		return symbolicType;
	}

	@Override
	public boolean isProcessType() {
		return kind == PrimitiveTypeKind.PROCESS;
	}

	@Override
	public boolean isScopeType() {
		return kind == PrimitiveTypeKind.SCOPE;
	}

	@Override
	public boolean hasState() {
		return false;
	}

	@Override
	public boolean isVoidType() {
		return kind == PrimitiveTypeKind.VOID;
	}
}
