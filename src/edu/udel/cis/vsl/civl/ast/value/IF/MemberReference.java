package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.entity.IF.Field;

public interface MemberReference extends AddressValue {

	Field getField();

	AddressValue getStructureOrUnionReference();

}
