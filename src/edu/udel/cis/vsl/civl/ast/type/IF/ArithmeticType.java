package edu.udel.cis.vsl.civl.ast.type.IF;

/**
 * The arithmetic types are "char", the signed and unsigned integer types,
 * enumeration types, and the floating types (which include the real and the
 * complex floating types).
 * 
 * The arithmetic type hierarchy can best be described by the following outline,
 * which defines a directed acyclic graph. Note that it is not a tree, as the
 * IntegerType and BasicType categories have in their intersection the type char
 * and the SignedOrUnisgnedIntegerTypes.
 * 
 * <pre>
 * UnqualifiedObjectType
 * | ArithmeticType
 * | | IntegerType
 * | | | char
 * | | | EnumerationType
 * | | | SignedOrUnsignedIntegerType
 * | | BasicType
 * | | | char
 * | | | SignedOrUnsignedIntegerType
 * | | | FloatingType
 * </pre>
 * 
 * 
 * @author siegel
 */
public interface ArithmeticType extends UnqualifiedObjectType {

	/**
	 * Is this an integer type? Note that this includes "signed char" and
	 * "unsigned char".
	 * 
	 * This method is equivalent to isSigned() || isUnsigned, but is provided
	 * for convenience.
	 * 
	 * @return true iff this is a signed or unsigned integer type
	 */
	boolean isInteger();

	/**
	 * Is this a floating type? This includes the three real floating types and
	 * the three complex floating types. If true, this object can be safely cast
	 * to FloatingType.
	 * 
	 * @return true iff this is a floating type
	 */
	boolean isFloating();

	/**
	 * Is this an enumeration type? If true, this object can be safely cast to
	 * EnumerationType.
	 * 
	 * @return true iff this is an enumeration type
	 */
	boolean isEnumeration();

	/**
	 * Is this type in the real domain? The arithmetic types are partitioned
	 * into the real and the complex domains. The complex domain includes the
	 * three complex types. Everything else (enumerations, signed and unsigned
	 * integer types, char) falls under the real domain.
	 * 
	 * @return true iff this type is in the real domain
	 */
	boolean inRealDomain();

	/**
	 * Is this type in the complex domain? The arithmetic types are partitioned
	 * into the real and the complex domains. The complex domain includes the
	 * three complex types. Everything else (enumerations, signed and unsigned
	 * integer types, char) falls under the real domain.
	 * 
	 * @return true iff this type is in the complex domain
	 */
	boolean inComplexDomain();
}
