package edu.udel.cis.vsl.civl.semantics.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.library.IF.LibraryLoader;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.common.CommonEvaluator;
import edu.udel.cis.vsl.civl.semantics.common.CommonExecutor;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.GMCConfiguration;

/**
 * Entry point of the module civl.semantics.
 * @author zmanchun
 *
 */
public class Semantics {
	public static Executor newExecutor(GMCConfiguration config,
			ModelFactory modelFactory, StateFactory stateFactory, ErrorLog log,
			LibraryLoader loader, PrintStream output, PrintStream err,
			boolean enablePrintf, boolean statelessPrintf, Evaluator evaluator) {
		return new CommonExecutor(config, modelFactory, stateFactory, log,
				loader, output, err, enablePrintf, statelessPrintf, evaluator);
	}

	public static Evaluator newEvaluator(GMCConfiguration config,
			ModelFactory modelFactory, StateFactory stateFactory, ErrorLog log,
			LibraryLoader libLoader) {
		return new CommonEvaluator(config, modelFactory, stateFactory, log,
				libLoader);
	}

}
