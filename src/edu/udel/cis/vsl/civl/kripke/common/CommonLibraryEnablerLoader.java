package edu.udel.cis.vsl.civl.kripke.common;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.err.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnablerLoader;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

public class CommonLibraryEnablerLoader implements LibraryEnablerLoader {

	/* **************************** Static Fields ************************** */

	/**
	 * The prefix of the full name of the class of a library enabler/executor.
	 */
	private final static String CLASS_PREFIX = "edu.udel.cis.vsl.civl.library.";

	/* *************************** Instance Fields ************************* */

	/**
	 * The cache of known library enablers.
	 */
	private Map<String, LibraryEnabler> libraryEnablerCache = new LinkedHashMap<>();

	/* ********************* Methods from LibraryLoader ******************** */

	@Override
	public LibraryEnabler getLibraryEnabler(String name,
			Enabler primaryEnabler, Evaluator evaluator,
			TransitionFactory transitionFactory, PrintStream output,
			ModelFactory modelFacotry, SymbolicUtility symbolicUtil) {
		LibraryEnabler result = libraryEnablerCache.get(name);

		if (result == null) {
			String aClassName = this.className(name, "Enabler");

			try {
				@SuppressWarnings("unchecked")
				Class<? extends LibraryEnabler> aClass = (Class<? extends LibraryEnabler>) Class
						.forName(aClassName);
				Constructor<? extends LibraryEnabler> constructor = aClass
						.getConstructor(Enabler.class, Evaluator.class,
								TransitionFactory.class, PrintStream.class,
								ModelFactory.class, SymbolicUtility.class);

				result = constructor.newInstance(primaryEnabler, evaluator,
						transitionFactory, output, modelFacotry, symbolicUtil);
			} catch (Exception e) {
				throw new CIVLInternalException("Unable to load library: "
						+ name + "\n" + e.getMessage(), (CIVLSource) null);
			}
			libraryEnablerCache.put(name, result);
		}
		return result;
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Computes the full name of the class of an enabler/executor of a library.
	 * 
	 * @param library
	 *            The name of the library.
	 * @param suffix
	 *            "Enabler" or "Executor", depending on whether the enabler or
	 *            executor is to be used.
	 * @return The full name of the class of the enabler or executor of the
	 *         given library.
	 */
	private String className(String library, String suffix) {
		String result = CLASS_PREFIX + library + ".Lib" + library + suffix;

		return result;
	}
}
