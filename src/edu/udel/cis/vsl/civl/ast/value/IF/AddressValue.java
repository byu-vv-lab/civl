package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.PointerType;

/**
 * A value of the form "&(lhs)", where lhs is a left hand side expression. For
 * example "&(x)" or "&(a[i].vel)".
 * 
 * @author siegel
 * 
 */
public interface AddressValue extends Value {

	@Override
	PointerType getType();

}
