package edu.udel.cis.vsl.civl.ast.value.IF;

public interface ArrayElementReference extends AddressValue {

	Value getIndex();

	AddressValue getArrayReference();

}
