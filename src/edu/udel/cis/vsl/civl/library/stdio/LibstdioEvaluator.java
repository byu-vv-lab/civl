package edu.udel.cis.vsl.civl.library.stdio;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.BaseLibraryEvaluator;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;

public class LibstdioEvaluator extends BaseLibraryEvaluator implements
		LibraryEvaluator {

	public LibstdioEvaluator(Evaluator evaluator, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil) {
		super(evaluator, modelFactory, symbolicUtil);
	}

	@Override
	public String name() {
		return "stdio";
	}

}
