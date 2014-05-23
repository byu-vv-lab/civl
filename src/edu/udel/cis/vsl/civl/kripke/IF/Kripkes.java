package edu.udel.cis.vsl.civl.kripke.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.common.CommonLibraryEnablerLoader;
import edu.udel.cis.vsl.civl.kripke.common.CommonStateManager;
import edu.udel.cis.vsl.civl.kripke.common.PointeredEnabler;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;

public class Kripkes {
	public static Enabler newEnabler(TransitionFactory transitionFactory,
			Evaluator evaluator, Executor executor, boolean showAmpleSet,
			boolean showAmpleSetWtStates, LibraryEnablerLoader libLoader,
			CIVLErrorLogger errorLogger) {
		return new PointeredEnabler(transitionFactory, evaluator, executor,
				showAmpleSet, showAmpleSetWtStates, libLoader, errorLogger);
	}

	public static LibraryEnablerLoader newLibraryEnablerLoader() {
		return new CommonLibraryEnablerLoader();
	}

	public static StateManager newStateManager(Enabler enabler,
			Executor executor, PrintStream out, boolean verbose, boolean debug,
			boolean showStates, boolean showSavedStates,
			boolean showTransitions, boolean saveStates, boolean simplify,
			CIVLErrorLogger errorLogger) {
		return new CommonStateManager(enabler, executor, out, verbose, debug,
				showStates, showSavedStates, showTransitions, saveStates,
				simplify, errorLogger);
	}
}
