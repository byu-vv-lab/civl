package edu.udel.cis.vsl.civl.semantics.common;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;

public class CommonLibraryExecutorLoader implements LibraryExecutorLoader {

	/* *************************** Instance Fields ************************* */

	/**
	 * The cache of known library executors.
	 */
	private Map<String, LibraryExecutor> libraryExecutorCache = new LinkedHashMap<>();

	/* ***************** Methods from LibraryExecutorLoader **************** */

	@Override
	public LibraryExecutor getLibraryExecutor(String name,
			Executor primaryExecutor, PrintStream output, PrintStream err,
			boolean enablePrintf, boolean statelessPrintf,
			ModelFactory modelFacotry, SymbolicUtility symbolicUtil) {
		LibraryExecutor result = libraryExecutorCache.get(name);

		if (result == null) {
			String aClassName = className(name, "Executor");

			try {
				@SuppressWarnings("unchecked")
				Class<? extends LibraryExecutor> aClass = (Class<? extends LibraryExecutor>) Class
						.forName(aClassName);
				Constructor<? extends LibraryExecutor> constructor = aClass
						.getConstructor(String.class, Executor.class,
								PrintStream.class, PrintStream.class,
								boolean.class, boolean.class,
								ModelFactory.class, SymbolicUtility.class);

				result = constructor.newInstance(name, primaryExecutor, output,
						err, enablePrintf, statelessPrintf, modelFacotry,
						symbolicUtil);
			} catch (Exception e) {
				throw new CIVLInternalException("Unable to load library: "
						+ name + "\n" + e.getMessage(), (CIVLSource) null);
			}
			libraryExecutorCache.put(name, result);
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
		String result = CIVLConfiguration.LIBRARY_PREFIX + library + ".Lib"
				+ library + suffix;

		return result;
	}

}
