package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * A primitive type. One of: int, bool, real, string.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonPrimitiveType extends CommonType implements
		CIVLPrimitiveType {

	private PrimitiveTypeKind primitiveType;

	private SymbolicType symbolicType;

	/**
	 * One of: int, bool, real, string, ...
	 * 
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public CommonPrimitiveType(PrimitiveTypeKind primitiveType,
			SymbolicType symbolicType) {
		this.primitiveType = primitiveType;
		this.symbolicType = symbolicType;
	}

	/**
	 * @return The actual primitive type (int, bool, real, or string).
	 */
	public PrimitiveTypeKind primitiveTypeKind() {
		return primitiveType;
	}

	/**
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public void setPrimitiveType(PrimitiveTypeKind primitiveType) {
		this.primitiveType = primitiveType;
	}

	@Override
	public String toString() {
		switch (primitiveType) {
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
		return primitiveType == PrimitiveTypeKind.INT
				|| primitiveType == PrimitiveTypeKind.REAL;
	}

	@Override
	public boolean isIntegerType() {
		return primitiveType == PrimitiveTypeKind.INT;
	}

	@Override
	public boolean isRealType() {
		return primitiveType == PrimitiveTypeKind.REAL;
	}

	@Override
	public SymbolicType getSymbolicType() {
		return symbolicType;
	}

	@Override
	public boolean isProcessType() {
		return primitiveType == PrimitiveTypeKind.PROCESS;
	}

	@Override
	public boolean isScopeType() {
		return primitiveType == PrimitiveTypeKind.SCOPE;
	}

	@Override
	public boolean hasState() {
		return false;
	}

}
