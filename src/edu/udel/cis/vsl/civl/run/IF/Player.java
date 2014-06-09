package edu.udel.cis.vsl.civl.run.IF;

import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.debugO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.enablePrintfO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.errorBoundO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.guiO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.maxdepthO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.minO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.randomO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.saveStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showAmpleSetO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showAmpleSetWtStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showSavedStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showTransitionsO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.simplifyO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.solveO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.statelessPrintfO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.verboseO;

import java.io.File;
import java.io.PrintStream;

import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.Dynamics;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.Kripkes;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnablerLoader;
import edu.udel.cis.vsl.civl.kripke.IF.StateManager;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.predicate.IF.StandardPredicate;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluatorLoader;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;
import edu.udel.cis.vsl.civl.semantics.IF.Semantics;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionSequence;
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

	// protected PrintStream out;
	//
	// protected PrintStream err;

	protected String sessionName;

	protected ModelFactory modelFactory;

	protected StateFactory stateFactory;

	protected TransitionFactory transitionFactory;

	protected ErrorLog log;

	protected Evaluator evaluator;

	protected EnablerIF<State, Transition, TransitionSequence> enabler;

	protected StandardPredicate predicate;

	protected LibraryEnablerLoader libraryEnablerLoader;

	protected LibraryExecutorLoader libraryExecutorLoader;

	protected LibraryEvaluatorLoader libraryEvaluatorLoader;

	protected Executor executor;

	protected StateManager stateManager;

	protected boolean random;

	// protected boolean verbose;

	// protected boolean debug;

	// protected boolean showStates;

	// protected boolean showSavedStates;

	// protected boolean showTransitions;

	protected String result;

	protected boolean minimize;

	protected int maxdepth;

	// protected boolean showAmpleSetWtStates; // false by default

	// protected boolean showAmpleSet; // false by default

	// protected boolean saveStates; // true by default

	// protected boolean simplify; // true by default

	protected boolean solve; // false by default

	// protected boolean enablePrintf; // true by default

	protected boolean gui; // false by default, only works with Replay mode.

	protected Preprocessor preprocessor;

	// protected boolean statelessPrintf;

	protected SymbolicUtility symbolicUtil;

	protected CIVLErrorLogger errorLogger;

	protected CIVLConfiguration civlConfig = new CIVLConfiguration();

	public Player(GMCConfiguration config, Model model, PrintStream out,
			PrintStream err, Preprocessor preprocessor)
			throws CommandLineException {
		SymbolicUniverse universe;

		this.preprocessor = preprocessor;
		this.config = config;
		this.model = model;
		civlConfig.setOut(out);
		civlConfig.setErr(err);
		civlConfig.setDebug((Boolean) config.getValueOrDefault(debugO));
		civlConfig.setEnablePrintf((Boolean) config
				.getValueOrDefault(enablePrintfO));
		civlConfig.setSaveStates((Boolean) config
				.getValueOrDefault(saveStatesO));
		civlConfig.setShowAmpleSet((Boolean) config
				.getValueOrDefault(showAmpleSetO));
		civlConfig.setShowAmpleSetWtStates((Boolean) config
				.getValueOrDefault(showAmpleSetWtStatesO));
		civlConfig.setShowSavedStates((Boolean) config
				.getValueOrDefault(showSavedStatesO));
		civlConfig.setShowStates((Boolean) config
				.getValueOrDefault(showStatesO));
		civlConfig.setShowTransitions((Boolean) config
				.getValueOrDefault(showTransitionsO));
		civlConfig.setSimplify((Boolean) config.getValueOrDefault(simplifyO));
		civlConfig.setStatelessPrintf((Boolean) config
				.getValueOrDefault(statelessPrintfO));
		civlConfig.setVerbose((Boolean) config.getValueOrDefault(verboseO));
		this.sessionName = model.name();
		this.modelFactory = model.factory();
		universe = modelFactory.universe();
		this.solve = (Boolean) config.getValueOrDefault(solveO);
		this.log = new ErrorLog(new File("CIVLREP"), sessionName, out);
		this.log.setErrorBound((int) config.getValueOrDefault(errorBoundO));
		this.errorLogger = new CIVLErrorLogger(config, log, universe, solve);
		this.symbolicUtil = Dynamics.newSymbolicUtility(universe, modelFactory,
				errorLogger);
		this.stateFactory = States.newImmutableStateFactory(modelFactory,
				config);
		this.libraryEvaluatorLoader = Semantics.newLibraryEvaluatorLoader();
		this.evaluator = Semantics.newEvaluator(modelFactory, stateFactory,
				libraryEvaluatorLoader, symbolicUtil, errorLogger);

		this.gui = (Boolean) config.getValueOrDefault(guiO);
		this.libraryExecutorLoader = Semantics.newLibraryExecutorLoader();
		this.executor = Semantics.newExecutor(config, modelFactory,
				stateFactory, log, libraryExecutorLoader, evaluator,
				errorLogger, civlConfig);
		this.random = config.isTrue(randomO);
		this.minimize = config.isTrue(minO);
		this.maxdepth = (int) config.getValueOrDefault(maxdepthO);

		this.transitionFactory = new TransitionFactory();
		this.libraryEnablerLoader = Kripkes.newLibraryEnablerLoader();
		enabler = Kripkes.newEnabler(transitionFactory, stateFactory,
				evaluator, this.libraryEnablerLoader, errorLogger, civlConfig);
		// enabler.setDebugOut(out);
		// enabler.setDebugging(debug);
		this.predicate = new StandardPredicate(log, universe,
				(Enabler) this.enabler, this.executor);
		stateManager = Kripkes.newStateManager((Enabler) enabler, executor,
				errorLogger, civlConfig);
	}

	public void printResult() {
		civlConfig.out().println(result);
	}

}
