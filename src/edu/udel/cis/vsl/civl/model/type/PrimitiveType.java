package edu.udel.cis.vsl.civl.model.type;

/**
 * A primitive type. One of: int, bool, real, string.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class PrimitiveType extends Type {

	public enum PRIMITIVE_TYPE {
		INT, BOOL, REAL, STRING
	};

	private PRIMITIVE_TYPE primitiveType;

	/**
	 * One of: int, bool, real, string.
	 * 
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public PrimitiveType(PRIMITIVE_TYPE primitiveType) {
		this.primitiveType = primitiveType;
	}

	/**
	 * @return The actual primitive type (int, bool, real, or string).
	 */
	public PRIMITIVE_TYPE primitiveType() {
		return primitiveType;
	}

	/**
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	public void setPrimitiveType(PRIMITIVE_TYPE primitiveType) {
		this.primitiveType = primitiveType;
	}
	
	@Override
	public String toString() {
		switch(primitiveType) {
		case INT:
			return "int";
		case BOOL:
			return "bool";
		case REAL:
			return "real";
		default:
			return "string";
		}
	}

}
