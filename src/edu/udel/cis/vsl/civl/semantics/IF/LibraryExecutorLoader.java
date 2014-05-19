package edu.udel.cis.vsl.civl.semantics.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public interface LibraryExecutorLoader {
	
	/**
	 * Get the library executor with the given name.
	 */
	LibraryExecutor getLibraryExecutor(String name, Executor primaryExecutor,
			PrintStream output, PrintStream err, boolean enablePrintf,
			boolean statelessPrintf, ModelFactory modelFacotry,
			SymbolicUtility symbolicUtil);

}
