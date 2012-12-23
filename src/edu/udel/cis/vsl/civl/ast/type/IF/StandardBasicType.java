package edu.udel.cis.vsl.civl.ast.type.IF;

/**
 * An instance of this class represents a "standard basic type". The standard
 * basic types are: the "char" type, the standard signed and unsigned integer
 * types, and the floating types. See C11 Sec. 6.2.5 for the definition of
 * "basic type".
 * 
 * Sec. 6.7.2 of the C11 Standard covers "type specifiers". Each standard basic
 * type is specified by a sequence of type specifiers. The order in which these
 * specifiers appear does not matter, but the multiplicity does (e.g.
 * "long long int" specifies a different type than "long int"). Hence the
 * sequence may be thought of as a multiset (set with multiplicity). This is the
 * way it is described in the Standard.
 * 
 * A standard basic type may have more than one multiset that specifies it. For
 * example "long" and "long int" specify the same type.
 * 
 * The basic multisets comprise the following elements, with multiplicity:
 * 
 * char, short, int, long, float, double, signed, unsigned, bool, complex.
 * 
 * The allowable multisets are defined in C11 Sec. 6.7.2.2, and are as follows.
 * For a line with more than one multiset, each multiset on the line specifies
 * the same type.
 * 
 * <pre>
 * Ñ char
 * Ñ signed char
 * Ñ unsigned char
 * Ñ short, signed short, short int, or signed short int
 * Ñ unsigned short, or unsigned short int
 * Ñ int, signed, or signed int
 * Ñ unsigned, or unsigned int
 * Ñ long, signed long, long int, or signed long int
 * Ñ unsigned long, or unsigned long int
 * Ñ long long, signed long long, long long int, or signed long long int
 * Ñ unsigned long long, or unsigned long long int
 * Ñ float
 * Ñ double
 * Ñ long double
 * Ñ _Bool
 * Ñ float _Complex
 * Ñ double _Complex
 * Ñ long double _Complex
 * </pre>
 * 
 * @author siegel
 */
public interface StandardBasicType extends ArithmeticType {

	public static enum BasicTypeKind {
		CHAR,
		SIGNED_CHAR,
		UNSIGNED_CHAR,
		SHORT,
		UNSIGNED_SHORT,
		INT,
		UNSIGNED,
		LONG,
		UNSIGNED_LONG,
		LONG_LONG,
		UNSIGNED_LONG_LONG,
		FLOAT,
		DOUBLE,
		LONG_DOUBLE,
		BOOL,
		FLOAT_COMPLEX,
		DOUBLE_COMPLEX,
		LONG_DOUBLE_COMPLEX
	};

	/**
	 * Returns the basic type kind.
	 * 
	 * @return the basic type kind
	 */
	BasicTypeKind getBasicTypeKind();

}
