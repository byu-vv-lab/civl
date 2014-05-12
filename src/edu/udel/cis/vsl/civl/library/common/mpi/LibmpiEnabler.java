package edu.udel.cis.vsl.civl.library.common.mpi;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.common.CommonEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.common.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

/**
 * Implementation of the enabler-related logics for system functions declared
 * mpi.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibmpiEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibmpiEnabler(CommonEnabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
	}

	@Override
	public String name() {
		return "mpi";
	}

}
