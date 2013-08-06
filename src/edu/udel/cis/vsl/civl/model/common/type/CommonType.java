package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

/**
 * Root of CIVL class hierarchy.
 * 
 * @author siegel
 * 
 */
public abstract class CommonType implements CIVLType {

	private Variable stateVariable = null;

	@Override
	public boolean isNumericType() {
		return false;
	}

	@Override
	public boolean isIntegerType() {
		return false;
	}

	@Override
	public boolean isRealType() {
		return false;
	}

	@Override
	public boolean isPointerType() {
		return false;
	}

	@Override
	public boolean isProcessType() {
		return false;
	}

	@Override
	public boolean isScopeType() {
		return false;
	}

	@Override
	public Variable getStateVariable() {
		return stateVariable;
	}

	@Override
	public void setStateVariable(Variable variable) {
		stateVariable = variable;
	}

}
