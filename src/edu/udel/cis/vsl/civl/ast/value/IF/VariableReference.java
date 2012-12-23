package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;

public interface VariableReference extends AddressValue {

	Variable getVariable();

}
