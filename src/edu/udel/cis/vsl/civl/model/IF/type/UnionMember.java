package edu.udel.cis.vsl.civl.model.IF.type;

import edu.udel.cis.vsl.civl.model.IF.Identifier;

/**
 * A member in a union has a name and a type.
 * 
 * @author zmanchun
 * 
 */
public interface UnionMember {
	Identifier name();

	CIVLType type();

	int index();
}
