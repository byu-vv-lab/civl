package edu.udel.cis.vsl.civl.library.pthread;

import java.io.PrintStream;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

public class LibpthreadEnabler extends BaseLibraryEnabler implements
		LibraryEnabler {

	public LibpthreadEnabler(String name, Enabler primaryEnabler,
			Evaluator evaluator, TransitionFactory transitionFactory,
			PrintStream output, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil) {
		super(name, primaryEnabler, evaluator, transitionFactory, output, modelFactory,
				symbolicUtil);
	}

}
