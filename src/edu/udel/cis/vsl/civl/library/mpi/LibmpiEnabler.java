package edu.udel.cis.vsl.civl.library.mpi;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

/**
 * Implementation of the enabler-related logics for system functions declared
 * mpi.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibmpiEnabler extends BaseLibraryEnabler implements LibraryEnabler {

	public LibmpiEnabler(String name, Enabler primaryEnabler, Evaluator evaluator,
			TransitionFactory transitionFactory, PrintStream output,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(name, primaryEnabler, evaluator, transitionFactory, output,
				modelFactory, symbolicUtil);
	}
}
