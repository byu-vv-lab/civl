package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.value.IF.CharacterValue;

/**
 * An instance of this interface represents a single Unicode character occurring
 * as a literal element in a C program.
 * 
 * Following the information in the C11 Standard, the C11 representation of a
 * character literal is interpreted to yield (1) a Unicode character, and (2) a
 * C type. Both pieces of information are included in an instance of this class.
 * 
 * Character constants are discussed in Section 6.4.4.4 of C11. They have a
 * complex structure.
 * 
 * First, there is a possible prefix: either none, L, u, or U.
 * 
 * Second there is a sequence of "c-char" (enclosed in single quotes).
 * 
 * A c-char is either (1) any member of the source character set other than
 * single-quote, backslash, or newline, or (2) an escape sequence.
 * 
 * An escape sequence is either a simple escape sequence, an octal escape
 * sequence, a hexadecimal escape sequence, or a universal character name.
 * 
 * A simple escape sequence is one of the following:
 * 
 * <pre>
 * \'  \"  \?  \\  \a  \b  \f  \n  \r  \t  \v
 * </pre>
 * 
 * An octal escape sequence is a backslash followed by 1, 2, or 3 octal digits.
 * 
 * A hexadecimal escape sequence consists of \x followed by 1 or more
 * hexadecimal digits.
 * 
 * [It seems that if the sequence of c-char has length greater than one, the
 * behavior is implementation-defined, so we are only going to support sequences
 * of length 1.]
 * 
 * Types:
 * 
 * The type is determined from the prefix as follows: int (none), wchar_t (L),
 * char16_t (u), or char32_t (U). wchar_t is an integer type, described in C11
 * 7.19. char16_t and char32_t are described in 7.28; the first is the smallest
 * unsigned integer type with a width of at least 16; the latter is the smallest
 * unsigned integer type with a width of at least 32.
 * 
 * If there is no prefix, the constant is known as an "integer character
 * constant", otherwise it is a "wide character constant".
 * 
 * Note: the type may be null until it is set. This is because the special types
 * (wchar_t, etc.) are not known until typedefs have been processed.
 * 
 * Values:
 * 
 * This part is very confusing and certainly some of it is
 * implementation-dependent. The procedure outlined here seems to be consistent
 * with what is written in the Standard.
 * 
 * For an integer character constant, the procedure is as follows. The code
 * point (which is a non-negative integer) must lie in the range of the type
 * unsigned char. (If not, an exception is thrown.) It is then converted to a
 * value of type char as follows: if the range of char coincides with that of
 * unsigned char, nothing to do. Otherwise, if the value already lies within the
 * range of signed char, nothing to do. Otherwise, subtract (unsignedCharMax+1)
 * from value. Finally, the char value is converted to a value of type int.
 * 
 * Example: if char = unsigned char and has 8 bits, this will yield an int value
 * in the range [0,255]. '\xFF' yields the int value 255.
 * 
 * Example: If char=signed char and has 8 bits and two's complement
 * representation is used for integers, then the int value will lie in the range
 * [-128,127]. '\xFF' yields the int value -1 (255-256=-1).
 * 
 * For wide character constants, the value is determined by one of the functions
 * mbtowc, mbrtoc16, or mbrtoc32. For now, we punt.
 * 
 * @author siegel
 * 
 */
public interface CharacterConstantNode extends ConstantNode {

	CharacterValue getConstantValue();

}
