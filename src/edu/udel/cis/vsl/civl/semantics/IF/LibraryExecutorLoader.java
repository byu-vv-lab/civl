package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public interface LibraryExecutorLoader {

	/**
	 * Get the library executor with the given name.
	 */
	LibraryExecutor getLibraryExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFacotry, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig);

}
