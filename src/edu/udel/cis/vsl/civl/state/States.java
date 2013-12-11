package edu.udel.cis.vsl.civl.state;

import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.state.common.CommonStateFactory;

public class States {

	public static StateFactory newStateFactory(ModelFactory modelFactory) {
		return new CommonStateFactory(modelFactory);
	}

}
