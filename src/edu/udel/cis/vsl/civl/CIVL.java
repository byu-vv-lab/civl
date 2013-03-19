package edu.udel.cis.vsl.civl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.udel.cis.vsl.abc.analysis.Analysis;
import edu.udel.cis.vsl.abc.antlr2ast.Antlr2AST;
import edu.udel.cis.vsl.abc.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.abc.parse.IF.CParser;
import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.parse.Parse;
import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorFactory;
import edu.udel.cis.vsl.abc.preproc.Preprocess;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.common.SideEffectRemover;
import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.kripke.StateManager;
import edu.udel.cis.vsl.civl.log.ErrorLog;
import edu.udel.cis.vsl.civl.model.Models;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelBuilder;
import edu.udel.cis.vsl.civl.predicate.Deadlock;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactory;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.DfsSearcher;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.gmc.StateManagerIF;
import edu.udel.cis.vsl.gmc.StatePredicateIF;
import edu.udel.cis.vsl.sarl.SARL;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.prove.TheoremProver;

public class CIVL {

	private static SymbolicUniverse universe = SARL.newStandardUniverse();
	private static ModelBuilder modelBuilder = Models.newModelBuilder();
	private static TheoremProver prover = universe.prover();

	// TODO:
	// add -D support. Need to create a token with "source" the command line.
	// may treat command line as (virtual) file called "commandline"?

	public static void main(String[] args) throws PreprocessorException,
			ParseException, SyntaxException, FileNotFoundException {
		String infileName = null;
		String outfileName = null;
		// the following are updated by -I
		ArrayList<File> systemIncludeList = new ArrayList<File>();
		// the following are updated by -iquote
		ArrayList<File> userIncludeList = new ArrayList<File>();
		PreprocessorFactory preprocessorFactory;
		Preprocessor preprocessor;
		File infile;
		PrintStream out;
		File[] systemIncludes, userIncludes;
		boolean preprocOnly = false;
		ErrorLog log = new ErrorLog(new PrintWriter(System.out),
				new java.io.File("."));

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];

			if (arg.startsWith("-o")) {
				String name;

				if (arg.length() == 2) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -o");
					name = args[i];
				} else {
					name = arg.substring(2);
				}
				if (outfileName == null)
					outfileName = name;
				else
					throw new IllegalArgumentException(
							"More than one use of -o");
			} else if (arg.startsWith("-I")) {
				String name;

				if (arg.length() == 2) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -I");
					name = args[i];
				} else {
					name = arg.substring(2);
				}
				systemIncludeList.add(new File(name));
			} else if (arg.startsWith("-iquote")) {
				String name;

				if (arg.length() == "-iquote".length()) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -iquote");
					name = args[i];
				} else {
					name = arg.substring("-iquote".length());
				}
				userIncludeList.add(new File(name));
			} else if (arg.equals("-E")) {
				preprocOnly = true;
			} else if (arg.startsWith("-")) {
				throw new IllegalArgumentException(
						"Unknown command line option: " + arg);
			} else {
				if (infileName == null)
					infileName = arg;
				else
					throw new IllegalArgumentException(
							"More than one input file specified (previous was "
									+ infileName + "): " + arg);
			}
		}
		if (infileName == null)
			throw new IllegalArgumentException("No input file specified");
		infile = new File(infileName);
		userIncludes = userIncludeList.toArray(new File[0]);
		systemIncludes = systemIncludeList.toArray(new File[0]);
		if (outfileName == null)
			out = System.out;
		else
			out = new PrintStream(new File(outfileName));
		preprocessorFactory = Preprocess.newPreprocessorFactory();
		preprocessor = preprocessorFactory.newPreprocessor(systemIncludes,
				userIncludes);
		if (preprocOnly) {
			preprocessor.printOutput(out, infile);
		} else {
			CParser parser = Parse.newCParser(preprocessor, infile);
			TranslationUnit unit = Antlr2AST.buildAST(parser, out);
			SideEffectRemover sideEffectRemover = new SideEffectRemover();
			StateFactoryIF stateFactory = new StateFactory(universe);
			Model model;
			TransitionFactory transitionFactory = new TransitionFactory();
			Evaluator evaluator = new Evaluator(universe);
			EnablerIF<State, Transition, TransitionSequence> enabler = new Enabler(
					transitionFactory, universe, prover, evaluator);
			StatePredicateIF<State> predicate = new Deadlock(universe,
					evaluator);
			Executor executor;
			StateManagerIF<State, Transition> stateManager;
			DfsSearcher<State, Transition, TransitionSequence> searcher;
			State initialState;
			double startTime = System.currentTimeMillis(), endTime;
			boolean result;
			String bar = "===================";

			out.println(bar + " AST " + bar + "\n");
			unit.print(out);
			out.println();
			sideEffectRemover.transform(unit);
			Analysis.performStandardAnalysis(unit);
			out.println(bar + " Analyzed AST " + bar + "\n");
			unit.print(out);
			out.println("\n\n" + bar + " Symbol Table " + bar + "\n");
			unit.getRootNode().getScope().print(out);
			out.println("\n\n" + bar + " Types " + bar + "\n");
			unit.getUnitFactory().getTypeFactory().printTypes(out);
			out.println();
			model = modelBuilder.buildModel(unit);
			out.println(bar + " Model " + bar + "\n");
			model.print(out);
			out.println();
			initialState = stateFactory.initialState(model);
			executor = new Executor(model, universe, stateFactory, log);
			stateManager = new StateManager(executor);
			searcher = new DfsSearcher<State, Transition, TransitionSequence>(
					enabler, stateManager, predicate);
			searcher.setDebugOut(new PrintWriter(out));
			result = searcher.search(initialState);
			endTime = System.currentTimeMillis();
			out.println();
			out.println(bar + " Stats " + bar + "\n");
			printStats(out, searcher, startTime, endTime,
					((StateManager) stateManager).maxProcs());
			if (result || log.numReports() > 0) {
				out.println("The program MAY NOT be correct.");
			} else {
				out.println("The specified properties hold for all executions.");
			}
			out.flush();
		}
	}

	public static void printStats(PrintStream out,
			DfsSearcher<State, Transition, TransitionSequence> searcher,
			double startTime, double endTime, int maxProcs) {
		long numStatesMatched = searcher.numStatesMatched();
		long numStatesSeen = searcher.numStatesSeen();
		long transitionsExecuted = searcher.numTransitions();
		long numProverValidCalls = prover.numValidCalls();
		long numCVC3Calls = prover.numInternalValidCalls();
		long heapSize = Runtime.getRuntime().totalMemory();

		out.print("   maxProcs            :");
		out.println(maxProcs);
		out.print("   statesSeen          : ");
		out.println(numStatesSeen);
		out.print("   statesMatched       : ");
		out.println(numStatesMatched);
		out.print("   transitionsExecuted : ");
		out.println(transitionsExecuted);
		out.print("   proverValidCalls    : ");
		out.println(numProverValidCalls);
		out.print("   CVC3ValidCalls      : ");
		out.println(numCVC3Calls);
		out.print("   memory              : ");
		out.println(heapSize);
		out.print("   elapsedTime         : ");
		out.println((endTime - startTime) / 1000.0);
	}
}
