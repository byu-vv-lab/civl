package edu.udel.cis.vsl.civl.model.IF.type;

import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

/**
 * Parent of all types.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public interface CIVLType {

	/**
	 * If this type contains any array with non-constant extent, it "has state"
	 * in the sense that the dynamic type may depend on the state.
	 * 
	 * @return true iff type contains array with non-constant extent
	 */
	boolean hasState();

	/**
	 * If a type is defined using a struct, union, or typedef, and it contains
	 * state, it may have to be evaluated and stored in a variable of type
	 * CIVLDynamicType. For such a type, this method returns the corresponding
	 * variable. For other types, it returns null.
	 * 
	 * @return the state variable associated to this type or null
	 */
	Variable getStateVariable();

	/**
	 * Sets this type's state variable to the given variable
	 * 
	 * @param variable
	 *            a variable of type CIVLDynamicType used to store the dynamic
	 *            type resulting from evaluating this type in a state
	 */
	void setStateVariable(Variable variable);

	boolean isNumericType();

	boolean isIntegerType();

	boolean isRealType();

	boolean isPointerType();

	boolean isProcessType();

	boolean isScopeType();

}
