package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;

public interface Enumeration extends TaggedEntity {

	@Override
	EnumerationType getType();

}
