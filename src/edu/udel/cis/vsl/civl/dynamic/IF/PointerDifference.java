package edu.udel.cis.vsl.civl.dynamic.IF;

import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * PointerDifference represents the value of the result of a pointer
 * subtraction. With this kind of symbolic value, pointer arithmetic can be
 * represented as "pointer +/- numeric offset" or
 * "pointer +/- PointerDifference".
 * 
 * A pointer difference consists of 2 pointers, one can be a NULL pointer;
 * 
 * The pointer arithmetic operation is valid if and only if it matched one of
 * the following patterns:<br>
 * <li>
 * ptr0 + PointerDifference(ptr1 - ptr0); <br>
 * ptr0 - PointerDifference(ptr0 - ptr1); <br>
 * </li> <br>
 * The type of this representation is originally integer or reference which
 * varies with casting.
 * 
 * @author ziqingluo
 *
 */
public interface PointerDifference {
	SymbolicExpression getSubtrachend();

	SymbolicExpression getMinuend();

	void setId(int id);
}
