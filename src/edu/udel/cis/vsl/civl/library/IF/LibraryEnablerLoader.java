package edu.udel.cis.vsl.civl.library.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public interface LibraryEnablerLoader {
	/**
	 * Get the library enabler with the given name.
	 */
	LibraryEnabler getLibraryEnabler(String name, Enabler primaryEnabler,
			PrintStream output, ModelFactory modelFacotry);
}
