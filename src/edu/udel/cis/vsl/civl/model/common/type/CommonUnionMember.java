package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.type.UnionMember;

public class CommonUnionMember implements UnionMember {
	private int index = -1;
	private Identifier name;
	private CIVLType type;

	@Override
	public Identifier name() {
		return this.name;
	}

	@Override
	public CIVLType type() {
		return this.type;
	}

	@Override
	public int index() {
		return this.index;
	}

}
