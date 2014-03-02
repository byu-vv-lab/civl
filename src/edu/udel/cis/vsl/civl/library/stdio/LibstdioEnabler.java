package edu.udel.cis.vsl.civl.library.stdio;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

/**
 * Implementation of the enabler-related logics for system functions declared
 * stdio.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibstdioEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibstdioEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
	}

	@Override
	public String name() {
		return "stdio";
	}

}
