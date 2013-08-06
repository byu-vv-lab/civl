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
		BOOL, DYNAMIC, HEAP, INT, PROCESS, REAL, SCOPE, STRING, VOID
	};

	/**
	 * Returns the symbolic type used to represent values of this primitive
	 * type. May be null (only in the case of {@link PrimitiveTypeKind.VOID}.
	 * 
	 * @return the symbolic type corresponding to this primitive type
	 */
	SymbolicType getSymbolicType();

	/**
	 * @return The kind of this primitive type, an element of the enumerated
	 *         type
	 */
	PrimitiveTypeKind primitiveTypeKind();

}
