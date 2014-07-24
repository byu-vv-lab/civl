package edu.udel.cis.vsl.civl.library.scope;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryEvaluator;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;

public class LibscopeEvaluator extends BaseLibraryEvaluator implements
		LibraryEvaluator {

	public LibscopeEvaluator(String name, Evaluator evaluator,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(name, evaluator, modelFactory, symbolicUtil);
	}

}
