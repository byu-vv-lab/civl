package edu.udel.cis.vsl.civl.ast.unit;

import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.unit.IF.UnitFactory;
import edu.udel.cis.vsl.civl.ast.unit.common.CommonUnitFactory;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;

public class Units {

	public static UnitFactory newUnitFactory(NodeFactory nodeFactory,
			TokenFactory tokenFactory, TypeFactory typeFactory) {
		return new CommonUnitFactory(nodeFactory, tokenFactory, typeFactory);
	}

}
