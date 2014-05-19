package edu.udel.cis.vsl.civl.library.stdio;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.BaseLibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

/**
 * Implementation of the enabler-related logics for system functions declared
 * stdio.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibstdioEnabler extends BaseLibraryEnabler implements
		LibraryEnabler {

	/* **************************** Constructors *************************** */

	/**
	 * Creates a new instance of the library enabler for stdio.h.
	 * 
	 * @param primaryEnabler
	 *            The enabler for normal CIVL execution.
	 * @param output
	 *            The output stream to be used in the enabler.
	 * @param modelFactory
	 *            The model factory of the system.
	 */
	public LibstdioEnabler(Enabler primaryEnabler, Evaluator evaluator,
			TransitionFactory transitionFactory, PrintStream output,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(primaryEnabler, evaluator, transitionFactory, output,
				modelFactory, symbolicUtil);
	}

	/* ************************ Methods from Library *********************** */

	@Override
	public String name() {
		return "stdio";
	}

}
