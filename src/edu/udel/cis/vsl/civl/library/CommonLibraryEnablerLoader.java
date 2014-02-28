package edu.udel.cis.vsl.civl.library;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnablerLoader;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;

public class CommonLibraryEnablerLoader implements LibraryEnablerLoader {

	private Map<String, LibraryEnabler> libraryEnablerCache = new LinkedHashMap<String, LibraryEnabler>();

	private final static String classPrefix = "edu.udel.cis.vsl.civl.library.";

	public CommonLibraryEnablerLoader() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public LibraryEnabler getLibraryEnabler(String name,
			Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFacotry) {
		LibraryEnabler result = libraryEnablerCache.get(name);

		if (result == null) {
			String aClassName = classPrefix + name + "." + "Lib" + name + "Enabler";

			try {
				Class<? extends LibraryEnabler> aClass = (Class<? extends LibraryEnabler>) Class
						.forName(aClassName);
				Constructor<? extends LibraryEnabler> constructor = aClass
						.getConstructor(Enabler.class, PrintStream.class,
								 ModelFactory.class);

				result = constructor.newInstance(primaryEnabler, output,
						modelFacotry);
			} catch (Exception e) {
				throw new CIVLInternalException("Unable to load library: "
						+ name + "\n" + e.getMessage(), (CIVLSource) null);
			}
			libraryEnablerCache.put(name, result);
		}
		return result;
	}

}
