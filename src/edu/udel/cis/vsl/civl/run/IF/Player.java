package edu.udel.cis.vsl.civl.run.IF;

import java.io.File;
import java.io.PrintStream;

import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.Kripkes;
import edu.udel.cis.vsl.civl.kripke.IF.StateManager;
import edu.udel.cis.vsl.civl.kripke.IF.Transition;
import edu.udel.cis.vsl.civl.kripke.IF.TransitionFactory;
import edu.udel.cis.vsl.civl.kripke.IF.TransitionSequence;
import edu.udel.cis.vsl.civl.library.IF.Libraries;
import edu.udel.cis.vsl.civl.library.IF.LibraryLoader;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.predicate.IF.StandardPredicate;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.Semantics;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.state.IF.States;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

/**
 * Base class for various tools that require executing a CIVL model. It provides
 * some of the services needed by most such tools. A concrete tool can extend
 * this class, or delegate to an instance of it.
 * 
 * @author Stephen F. Siegel
 * 
 */
public abstract class Player {

	protected GMCConfiguration config;

	protected Model model;

	protected PrintStream out;

	protected PrintStream err;

	protected String sessionName;

	protected ModelFactory modelFactory;

	protected StateFactory stateFactory;

	protected TransitionFactory transitionFactory;

	protected ErrorLog log;

	protected Evaluator evaluator;

	protected EnablerIF<State, Transition, TransitionSequence> enabler;

	protected StandardPredicate predicate;

	protected LibraryLoader libraryLoader;

	protected Executor executor;

	protected StateManager stateManager;

	protected boolean random;

	protected boolean verbose;

	protected boolean debug;

	protected boolean showStates;

	protected boolean showSavedStates;

	protected boolean showTransitions;

	protected String result;

	protected boolean minimize;

	protected int maxdepth;

	protected boolean showAmpleSetWtStates; // false by default

	protected boolean showAmpleSet; // false by default

	protected boolean saveStates; // true by default

	protected boolean simplify; // true by default

	protected boolean solve; // false by default

	protected boolean enablePrintf; // true by default

	protected boolean gui; // false by default, only works with Replay mode.

	protected Preprocessor preprocessor;

	protected boolean statelessPrintf;

	protected SymbolicUtility symbolicUtil;

	protected CIVLErrorLogger errorLogger;

	public Player(GMCConfiguration config, Model model, PrintStream out,
			PrintStream err, Preprocessor preprocessor)
			throws CommandLineException {
		SymbolicUniverse universe;

		this.preprocessor = preprocessor;
		this.config = config;
		this.model = model;
		this.out = out;
		this.err = err;
		this.sessionName = model.name();
		this.modelFactory = model.factory();
		universe = modelFactory.universe();
		this.solve = (Boolean) config.getValueOrDefault(UserInterface.solveO);
		this.log = new ErrorLog(new File("CIVLREP"), sessionName, out);
		this.log.setErrorBound((int) config
				.getValueOrDefault(UserInterface.errorBoundO));
		errorLogger = new CIVLErrorLogger(config, log, universe, solve);
		this.symbolicUtil = Semantics.newSymbolicUtility(universe,
				modelFactory, errorLogger);
		this.stateFactory = States.newImmutableStateFactory(modelFactory,
				config, symbolicUtil);
		this.errorLogger.setSymbolicUtility(symbolicUtil);
		this.libraryLoader = Libraries.newLibraryLoader();
		this.evaluator = Semantics.newEvaluator(modelFactory, stateFactory,
				symbolicUtil, errorLogger);
		this.enablePrintf = (Boolean) config
				.getValueOrDefault(UserInterface.enablePrintfO);
		this.statelessPrintf = (Boolean) config
				.getValueOrDefault(UserInterface.statelessPrintfO);
		this.showAmpleSet = (Boolean) config
				.getValueOrDefault(UserInterface.showAmpleSetO);
		this.showAmpleSetWtStates = (Boolean) config
				.getValueOrDefault(UserInterface.showAmpleSetWtStatesO);
		this.gui = (Boolean) config.getValueOrDefault(UserInterface.guiO);
		this.executor = Semantics.newExecutor(config, modelFactory,
				stateFactory, log, libraryLoader, out, err, this.enablePrintf,
				this.statelessPrintf, evaluator, errorLogger);
		this.random = config.isTrue(UserInterface.randomO);
		this.verbose = config.isTrue(UserInterface.verboseO);
		this.debug = config.isTrue(UserInterface.debugO);
		this.showStates = config.isTrue(UserInterface.showStatesO);
		this.showSavedStates = config.isTrue(UserInterface.showSavedStatesO);
		this.showTransitions = config.isTrue(UserInterface.showTransitionsO);
		this.minimize = config.isTrue(UserInterface.minO);
		this.maxdepth = (int) config.getValueOrDefault(UserInterface.maxdepthO);
		this.saveStates = (Boolean) config
				.getValueOrDefault(UserInterface.saveStatesO);
		this.simplify = (Boolean) config
				.getValueOrDefault(UserInterface.simplifyO);
		this.transitionFactory = new TransitionFactory();
		enabler = Kripkes.newEnabler(transitionFactory, evaluator, executor,
				showAmpleSet, this.showAmpleSetWtStates, this.libraryLoader,
				errorLogger);
		enabler.setDebugOut(out);
		enabler.setDebugging(debug);
		this.predicate = new StandardPredicate(log, universe,
				(Enabler) this.enabler, this.executor);
		this.evaluator.setEnabler((Enabler) this.enabler);
		stateManager = Kripkes.newStateManager(this.transitionFactory,
				(Enabler) enabler, executor, out, verbose, debug, gui,
				showStates, showSavedStates, showTransitions, saveStates,
				simplify, errorLogger);
	}

	public void printResult() {
		out.println(result);
	}

}
