package edu.udel.cis.vsl.civl.semantics.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.common.CommonEvaluator;
import edu.udel.cis.vsl.civl.semantics.common.CommonExecutor;
import edu.udel.cis.vsl.civl.semantics.common.CommonLibraryExecutorLoader;
import edu.udel.cis.vsl.civl.semantics.common.CommonSymbolicUtility;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

/**
 * Entry point of the module civl.semantics.
 * 
 * @author zmanchun
 * 
 */
public class Semantics {

	public static LibraryExecutorLoader newLibraryExecutorLoader() {
		return new CommonLibraryExecutorLoader();
	}

	public static Executor newExecutor(GMCConfiguration config,
			ModelFactory modelFactory, StateFactory stateFactory, ErrorLog log,
			LibraryExecutorLoader loader, PrintStream output, PrintStream err,
			boolean enablePrintf, boolean statelessPrintf, Evaluator evaluator,
			CIVLErrorLogger errLogger) {
		return new CommonExecutor(config, modelFactory, stateFactory, log,
				loader, output, err, enablePrintf, statelessPrintf, evaluator,
				errLogger);
	}

	public static Evaluator newEvaluator(ModelFactory modelFactory,
			StateFactory stateFactory, SymbolicUtility symbolicUtil,
			CIVLErrorLogger errLogger) {
		return new CommonEvaluator(modelFactory, stateFactory, symbolicUtil,
				errLogger);
	}

	public static SymbolicUtility newSymbolicUtility(SymbolicUniverse universe,
			ModelFactory modelFactory, CIVLErrorLogger errLogger) {
		return new CommonSymbolicUtility(universe, modelFactory, errLogger);
	}
}
