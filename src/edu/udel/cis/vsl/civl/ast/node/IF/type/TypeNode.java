package edu.udel.cis.vsl.civl.ast.node.IF.type;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.SizeableNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

public interface TypeNode extends SizeableNode {

	/**
	 * The different kinds of type names.
	 */
	public static enum TypeNodeKind {
		VOID,
		BASIC,
		ENUMERATION,
		ARRAY,
		STRUCTURE_OR_UNION,
		FUNCTION,
		POINTER,
		ATOMIC,
		TYPEDEF_NAME
	};

	/**
	 * The kind of type name this is. See definition of the enumerated type
	 * TypeNameKind. These kinds partition the set of all type names.
	 * 
	 * If the kind is BASIC, this object can be safely cast to BasicType.
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
	 * @return the kind of this type
	 */
	TypeNodeKind kind();

	/**
	 * Is this a "const" qualified type?
	 * 
	 * @return true iff this is a const qualified type.
	 */
	boolean isConstQualified();

	void setConstQualified(boolean value);

	/**
	 * Is this a "volatile" qualified type?
	 * 
	 * @return true iff this is a volatile qualified type.
	 */
	boolean isVolatileQualified();

	void setVolatileQualified(boolean value);

	/**
	 * Is this a "restrict" qualified type?
	 * 
	 * @return true iff this is a "restrict" qualified type.
	 */
	boolean isRestrictQualified();

	void setRestrictQualified(boolean value);

	/**
	 * Is this an "_Atomic" qualified type?
	 * 
	 * @return true iff this is an "_Atomic" qualified type
	 */
	boolean isAtomicQualified();

	void setAtomicQualified(boolean value);

	/**
	 * Returns the conceptual C type associated to this type node.
	 * 
	 * @return the C type defined by this type node
	 */
	Type getType();

	/**
	 * Sets the type that will be returned by subsequent calls to getType().
	 * 
	 * @param type
	 *            the type to associate to this node
	 */
	void setType(Type type);

}
