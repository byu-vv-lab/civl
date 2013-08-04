package edu.udel.cis.vsl.civl.model.IF.type;

/**
 * A primitive type. One of: int, bool, real, string.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface CIVLPrimitiveType extends CIVLType {

	public enum PRIMITIVE_TYPE {
		INT, BOOL, REAL, STRING, SCOPE
	};

	/**
	 * @return The actual primitive type (int, bool, real, or string).
	 */
	PRIMITIVE_TYPE primitiveType();

	/**
	 * @param The
	 *            actual primitive type (int, bool, real, or string).
	 */
	void setPrimitiveType(PRIMITIVE_TYPE primitiveType);

}
