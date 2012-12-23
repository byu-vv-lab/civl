package edu.udel.cis.vsl.civl.config.IF;

import java.math.BigInteger;

/**
 * Configuration constants.
 * 
 * numbers of bits in types, etc.
 * 
 * unsigned char unsigned short int unsigned int unsigned long int unsigned long
 * long int
 * 
 * signed versions of above, low and high
 * 
 * @author siegel
 * 
 */
public interface Configuration {

	BigInteger unsignedCharMax();

	BigInteger unsignedShortIntMax();

	BigInteger unsignedIntMax();

	BigInteger unsignedLongIntMax();

	BigInteger unsignedLongLongIntMax();

	BigInteger signedCharMin();

	BigInteger signedCharMax();

	BigInteger signedShortIntMin();

	BigInteger signedShortIntMax();

	BigInteger signedIntMin();

	BigInteger signedIntMax();

	BigInteger signedLongIntMin();

	BigInteger signedLongIntMax();

	BigInteger signedLongLongIntMin();

	BigInteger signedLongLongIntMax();

	BigInteger charMin();

	BigInteger charMax();

	boolean inRangeUnsignedChar(BigInteger value);

	boolean inRangeUnsignedShort(BigInteger value);

	boolean inRangeUnsignedInt(BigInteger value);

	boolean inRangeUnsignedLongInt(BigInteger value);

	boolean inRangeUnsignedLongLongInt(BigInteger value);

	boolean inRangeSignedChar(BigInteger value);

	boolean inRangeSignedShort(BigInteger value);

	boolean inRangeSignedInt(BigInteger value);

	boolean inRangeSignedLongInt(BigInteger value);

	boolean inRangeSignedLongLongInt(BigInteger value);
}
