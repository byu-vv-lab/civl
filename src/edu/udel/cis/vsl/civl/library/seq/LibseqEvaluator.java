package edu.udel.cis.vsl.civl.library.seq;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryEvaluator;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;

public class LibseqEvaluator extends BaseLibraryEvaluator implements
		LibraryEvaluator {

	public LibseqEvaluator(String name, Evaluator evaluator,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(name, evaluator, modelFactory, symbolicUtil);
	}

}