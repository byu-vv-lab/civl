package edu.udel.cis.vsl.civl.library.stdlib;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

/**
 * Implementation of the enabler-related logics for system functions declared
 * stdlib.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibstdlibEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibstdlibEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
	}

	@Override
	public String name() {
		return "stdlib";
	}

}
