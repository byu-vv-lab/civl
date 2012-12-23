package edu.udel.cis.vsl.civl.ast.type.IF;

public interface QualifiedObjectType extends ObjectType {
	
	UnqualifiedObjectType getBaseType();

	/**
	 * Is this a "const" qualified type?
	 * 
	 * @return true iff this is a const qualified type.
	 */
	boolean isConstQualified();

	/**
	 * Is this a "volatile" qualified type?
	 * 
	 * @return true iff this is a volatile qualified type.
	 */
	boolean isVolatileQualified();

	/**
	 * Is this a "restrict" qualified type?
	 * 
	 * @return true iff this is a "restrict" qualified type.
	 */
	boolean isRestrictQualified();

}
