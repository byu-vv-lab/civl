package edu.udel.cis.vsl.civl.ast.type.IF;

import java.io.PrintStream;

/**
 * An instance of Type represents a C type. A type is a conceptual entity, not a
 * syntactic element. This class is the root of the type hierarchy for all types
 * used to represent C types.
 * 
 * Summary of type hierarchy:
 * 
 * <pre>
 * Type
 * | FunctionType
 * | ObjectType
 * | | UnqualifiedObjectType
 * | | | ArithmeticType, ArrayType, AtomicType, StandardBasicType, ...
 * | | QualifiedObjectType (one or more of const, volatile, restrict)
 * </pre>
 * 
 * Note: AtomicType and QualifiedType constructors take an UnqualifiedType.
 * 
 * @author siegel
 * 
 */
public interface Type {

	/**
	 * The different kinds of types.
	 */
	public static enum TypeKind {
		VOID,
		BASIC,
		OTHER_INTEGER,
		ENUMERATION,
		ARRAY,
		STRUCTURE_OR_UNION,
		FUNCTION,
		POINTER,
		ATOMIC,
		QUALIFIED
	};

	/**
	 * The kind of type this is. See definition of the enumerated type
	 * TypeNameKind. These kinds partition the set of all type names.
	 * 
	 * If the kind is BASIC, this object can be safely cast to
	 * StandardBasicType.
	 * 
	 * If the kind is OTHER_INTEGER, the object can be safely cast to
	 * IntegerType, but not to StandardBasicType.
	 * 
	 * If the kind is ENUMERATION, this object can be safely cast to
	 * EnumerationType.
	 * 
	 * If the kind is ARRAY, this object can be safely cast to ArrayType.
	 * 
	 * If the kind is STRUCTURE_OR_UNION, this object can be safely cast to
	 * StructureOrUnionType.
	 * 
	 * If the kind is FUNCTION, this object can be safely cast to FunctionType.
	 * 
	 * If the kind is POINTER, this object can be safely cast to PointerType.
	 * 
	 * If the kind is ATOMIC, this object can be safely cast to AtomicType.
	 * 
	 * If the kind if QUALIFIED, this object can be safely cast to
	 * QualifiedObjectType.
	 * 
	 * @return the kind of this type
	 */
	TypeKind kind();

	/**
	 * Is this type a "VM" type (variable modified type)?
	 * 
	 * @return true iff this type is a VM type
	 */
	boolean isVariablyModified();

	/**
	 * Is this type "compatible" with the given type? See C11 Sec. 6.2.7 for the
	 * definition of "compatible".
	 * 
	 * @param type
	 *            the type to compare with this one for compatibility
	 * @return true iff the two types are compatible
	 */
	boolean compatibleWith(Type type);

	/**
	 * The types created by the type factories are given unique id numbers. This
	 * method returns the id number of this type.
	 * 
	 * @return
	 */
	int getId();

	/**
	 * Prints the type in a tree-formatted style. The prefix string is prepended
	 * to each line of output other than the first. Output for structure or
	 * union types may leave out the fields by setting abbrv to true.
	 * 
	 * @param prefix
	 *            string to preprend to lines after first
	 * @param out
	 *            PrintStream to which output should be sent
	 * @param abbrv
	 *            if true, abbreviate representations of structure or union
	 *            types by leaving out their fields
	 */
	void print(String prefix, PrintStream out, boolean abbrv);

	/**
	 * C11 6.2.4(21):
	 * 
	 * "Arithmetic types and pointer types are collectively called scalar types."
	 * 
	 * @return true iff type is scalar
	 */
	boolean isScalar();

}
