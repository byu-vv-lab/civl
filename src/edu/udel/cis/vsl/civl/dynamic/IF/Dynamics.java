package edu.udel.cis.vsl.civl.dynamic.IF;

import edu.udel.cis.vsl.civl.dynamic.common.CommonSymbolicUtility;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class Dynamics {

	public static SymbolicUtility newSymbolicUtility(SymbolicUniverse universe,
			ModelFactory modelFactory, CIVLErrorLogger errLogger) {
		return new CommonSymbolicUtility(universe, modelFactory, errLogger);
	}

}
