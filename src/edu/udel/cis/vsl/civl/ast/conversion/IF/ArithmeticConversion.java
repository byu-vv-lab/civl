package edu.udel.cis.vsl.civl.ast.conversion.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.ArithmeticType;

/**
 * Represents a conversion from any arithmetic type to another arithmetic type
 * (not just the "usual arithmetic conversions"). See C11 Sec. 6.3.1.
 * 
 * @author siegel
 * 
 */
public interface ArithmeticConversion extends Conversion {

	@Override
	ArithmeticType getOldType();

	@Override
	ArithmeticType getNewType();

}
