package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public interface LibraryEvaluatorLoader {
	/**
	 * Get the library executor with the given name.
	 */
	LibraryEvaluator getLibraryEvaluator(String name,
			Evaluator primaryEvaluator, ModelFactory modelFacotry,
			SymbolicUtility symbolicUtil);
}
