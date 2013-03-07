package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.Analysis;
import edu.udel.cis.vsl.abc.antlr2ast.Antlr2AST;
import edu.udel.cis.vsl.abc.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.abc.parse.Parse;
import edu.udel.cis.vsl.abc.parse.IF.CParser;
import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.Preprocess;
import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorFactory;
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

public class ArraysTest {

	private static File rootDir = new File("examples");
	private static SymbolicUniverse universe = SARL.newIdealUniverse();
	private static ModelBuilder modelBuilder = Models.newModelBuilder();
	private static TheoremProver prover = universe.prover();
	private PrintStream out = System.out;

	@Test
	public void testArrays() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		PreprocessorFactory preprocessorFactory = Preprocess
				.newPreprocessorFactory();
		Preprocessor preprocessor = preprocessorFactory.newPreprocessor();
		File infile = new File(rootDir, "arrays.cvl");
		CParser parser = Parse.newCParser(preprocessor, infile);
		TranslationUnit unit = Antlr2AST.buildAST(parser, out);
		SideEffectRemover sideEffectRemover = new SideEffectRemover();
		StateFactoryIF stateFactory = new StateFactory(universe);
		Model model;
		TransitionFactory transitionFactory = new TransitionFactory();
		Evaluator evaluator = new Evaluator(universe);
		EnablerIF<State, Transition, TransitionSequence> enabler = new Enabler(
				transitionFactory, universe, prover, evaluator);
		StatePredicateIF<State> predicate = new Deadlock(universe, evaluator);
		Executor executor;
		StateManagerIF<State, Transition> stateManager;
		DfsSearcher<State, Transition, TransitionSequence> searcher;
		State initialState;
		ErrorLog log = new ErrorLog(new PrintWriter(System.out),
				new java.io.File("."));

		sideEffectRemover.transform(unit);
		Analysis.performStandardAnalysis(unit);
		model = modelBuilder.buildModel(unit);
		initialState = stateFactory.initialState(model);
		executor = new Executor(model, universe, stateFactory, log);
		stateManager = new StateManager(executor);
		searcher = new DfsSearcher<State, Transition, TransitionSequence>(
				enabler, stateManager, predicate);
		searcher.setDebugOut(new PrintWriter(out));
		assertTrue(searcher.search(initialState));
	}

}