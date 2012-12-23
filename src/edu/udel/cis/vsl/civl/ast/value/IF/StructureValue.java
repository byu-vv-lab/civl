package edu.udel.cis.vsl.civl.ast.value.IF;

import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;

/**
 * 
 * @author siegel
 * 
 */
public interface StructureValue extends CompoundValue {

	@Override
	StructureOrUnionType getType();

	Value getMember(Field field);

	Value getMember(int fieldIndex);

	void setMember(Field field, Value memberValue);

	void setMember(int index, Value memberValue);
}
