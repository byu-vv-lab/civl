package edu.udel.cis.vsl.civl.model.IF.type;

import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * A primitive type is a type of which there is only one instance. In addition,
 * there is a single symbolic type corresponding to each primitive type.
 * 
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface CIVLPrimitiveType extends CIVLType {

	public enum PrimitiveTypeKind {
		BOOL, DYNAMIC, HEAP, INT, PROCESS, REAL, SCOPE, STRING
	};

	SymbolicType getSymbolicType();

	/**
	 * @return The actual primitive type (int, bool, real, or string).
	 */
	PrimitiveTypeKind primitiveTypeKind();

}
