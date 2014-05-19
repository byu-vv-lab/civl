package edu.udel.cis.vsl.civl.kripke.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

public interface LibraryEnablerLoader {
	/**
	 * Gets the library enabler with the given name.
	 * 
	 * @param name
	 *            The name of the library whose enabler is to be obtained.
	 * @param primaryEnabler
	 *            The CIVL enabler for normal CIVL executions.
	 * @param output
	 *            The print stream to be used in the library enabler.
	 * @param modelFacotry
	 *            The model factory to be used in the library enabler.
	 * @return The library enabler of the given name.
	 */
	LibraryEnabler getLibraryEnabler(String name, Enabler primaryEnabler,
			Evaluator evaluator, TransitionFactory transitionFactory,
			PrintStream output, ModelFactory modelFacotry,
			SymbolicUtility symbolicUtil);
}
