package edu.udel.cis.vsl.civl.library.stdio;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public class LibstdioEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibstdioEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		return "stdio";
	}

}
