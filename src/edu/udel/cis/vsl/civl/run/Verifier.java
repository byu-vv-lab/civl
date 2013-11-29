package edu.udel.cis.vsl.civl.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.kripke.StateManager;
import edu.udel.cis.vsl.civl.library.CommonLibraryExecutorLoader;
import edu.udel.cis.vsl.civl.log.CIVLLogEntry;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.predicate.StandardPredicate;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactory;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.DfsSearcher;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.ExcessiveErrorException;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.gmc.StateManagerIF;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public class Verifier {

	private GMCConfiguration config;

	private Model model;

	private PrintStream out;

	private String sessionName;

	private ModelFactory modelFactory;

	private StateFactoryIF stateFactory;

	private TransitionFactory transitionFactory;

	private ErrorLog log;

	private Evaluator evaluator;

	private EnablerIF<State, Transition, TransitionSequence> enabler;

	private StandardPredicate predicate;

	private LibraryExecutorLoader loader;

	private Executor executor;

	private StateManagerIF<State, Transition> stateManager;

	private DfsSearcher<State, Transition, TransitionSequence> searcher;

	// for now, random execution handled here, later implement
	// in GMC
	private boolean random;

	private boolean verbose;

	private boolean debug;

	private boolean showStates;

	private boolean showAllStates;

	private boolean showTransitions;

	private String result;

	public Verifier(GMCConfiguration config, Model model, PrintStream out)
			throws CommandLineException {
		SymbolicUniverse universe;

		this.config = config;
		this.model = model;
		this.out = out;
		this.sessionName = model.name();
		this.modelFactory = model.factory();
		universe = modelFactory.universe();
		this.stateFactory = new StateFactory(modelFactory);
		this.transitionFactory = new TransitionFactory();
		this.log = new ErrorLog(new File("CIVLREP"), sessionName, out);
		this.evaluator = new Evaluator(config, modelFactory, stateFactory, log);
		this.predicate = new StandardPredicate(log, universe, evaluator);
		this.loader = new CommonLibraryExecutorLoader();
		this.log.setErrorBound(config.getIntValue("errorBound"));
		this.executor = new Executor(config, modelFactory, stateFactory, log,
				loader);
		this.random = Boolean.TRUE.equals(config.getBooleanValue("random"));
		this.verbose = Boolean.TRUE.equals(config.getBooleanValue("verbose"));
		this.debug = Boolean.TRUE.equals(config.getBooleanValue("debug"));
		this.showStates = Boolean.TRUE.equals(config
				.getBooleanValue("showStates"));
		this.showAllStates = Boolean.TRUE.equals(config
				.getBooleanValue("showAllStates"));
		this.showTransitions = Boolean.TRUE.equals(config
				.getBooleanValue("showTransitions"));

		if (this.random) {
			long seed;
			String seedString = config.getStringValue("seed");

			if (seedString == null)
				seed = System.currentTimeMillis();
			else
				try {
					seed = new Long(seedString);
				} catch (NumberFormatException e) {
					throw new CommandLineException(
							"Expected long value for seed, saw: " + seedString);
				}
			out.println("Random execution with seed " + seed + ".");
			enabler = new Enabler(transitionFactory, evaluator, executor,
					random, new Random(seed));
		} else {
			enabler = new Enabler(transitionFactory, evaluator, executor);
		}
		stateManager = new StateManager(executor);
		if (verbose || debug) {
			// TODO: give state manager separate verbose and debug modes
			((StateManager) stateManager).setDebugOut(out);
		}
		searcher = new DfsSearcher<State, Transition, TransitionSequence>(
				enabler, stateManager, predicate);
		if (debug)
			searcher.setDebugOut(out);
		searcher.setName(sessionName);
		log.setSearcher(searcher);
	}

	/**
	 * Prints only those metrics specific to this Verifier. General metrics,
	 * including time, memory, symbolic expressions, etc., are dealt with in the
	 * general UserInterface class.
	 */
	public void printStats() {
		out.print("   maxProcs            : ");
		out.println(((StateManager) stateManager).maxProcs());
		out.print("   statesSeen          : ");
		out.println(searcher.numStatesSeen());
		out.print("   statesMatched       : ");
		out.println(searcher.numStatesMatched());
		out.print("   transitions         : ");
		out.println(searcher.numTransitions());
	}

	public void printResult() {
		out.println(result);
	}

	public boolean run() {
		State initialState = stateFactory.initialState(model);
		boolean violationFound = false;

		try {
			while (true) {
				boolean workRemains;

				if (violationFound)
					workRemains = searcher.proceedToNewState() ? searcher
							.search() : false;
				else
					workRemains = searcher.search(initialState);
				if (!workRemains)
					break;
				log.report(new CIVLLogEntry(config, predicate.getViolation()));
				violationFound = true;
			}
		} catch (ExcessiveErrorException e) {
			violationFound = true;
			out.println("Error bound exceeded: search terminated");
		} catch (Exception e) {
			violationFound = true;
			out.println(e);
			e.printStackTrace(out);
			out.println();
		}
		if (violationFound || log.numEntries() > 0) {
			result = "The program MAY NOT be correct.  See " + log.getLogFile();
			try {
				log.save();
			} catch (FileNotFoundException e) {
				System.err.println("Failed to print log file "
						+ log.getLogFile());
			}
		} else {
			result = "The standard properties hold for all executions.";
		}
		return !violationFound && log.numEntries() == 0;
	}
}
