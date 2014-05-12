package edu.udel.cis.vsl.civl.kripke.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.kripke.common.CommonStateManager;
import edu.udel.cis.vsl.civl.kripke.common.PointeredEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryLoader;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;

public class Kripkes {
	public static Enabler newEnabler(TransitionFactory transitionFactory,
			Evaluator evaluator, Executor executor, boolean showAmpleSet,
			boolean showAmpleSetWtStates, LibraryLoader libLoader) {
		return new PointeredEnabler(transitionFactory, evaluator, executor,
				showAmpleSet, showAmpleSetWtStates, libLoader);
	}

	public static StateManager newStateManager(Executor executor,
			PrintStream out, boolean verbose, boolean debug, boolean gui,
			boolean showStates, boolean showSavedStates,
			boolean showTransitions, boolean saveStates, boolean simplify) {
		return new CommonStateManager(executor, out, verbose, debug, gui,
				showStates, showSavedStates, showTransitions, saveStates,
				simplify);
	}
}
