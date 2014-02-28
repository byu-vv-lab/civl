package edu.udel.cis.vsl.civl.library.mpi;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public class LibmpiEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibmpiEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "mpi";
	}

}
