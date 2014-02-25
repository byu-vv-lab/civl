package edu.udel.cis.vsl.civl.library;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;

public abstract class CommonLibraryExecutor implements LibraryExecutor {

	/* ************************** Instance Fields ************************** */

	/**
	 * Enable or disable printing. By default true, i.e., enable printing.
	 */
	protected boolean enablePrintf;

	/**
	 * The evaluator for evaluating expressions.
	 */
	protected Evaluator evaluator;

	/**
	 * The symbolic expression of one.
	 */
	protected NumericExpression one;

	protected IntObject oneObject;

	/**
	 * The output stream to be used for printing.
	 */
	protected PrintStream output = System.out;

	/**
	 * The primary executor of the system.
	 */
	protected Executor primaryExecutor;

	protected StateFactory stateFactory;

	/**
	 * The symbolic universe for symbolic computations.
	 */
	protected SymbolicUniverse universe;

	protected NumericExpression zero;

	protected IntObject zeroObject;
	
	protected ModelFactory modelFactory;

	/* **************************** Constructors *************************** */

	protected CommonLibraryExecutor(Executor primaryExecutor,
			PrintStream output, boolean enablePrintf, ModelFactory modelFactory) {
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
		// this.log = evaluator.log();
		this.universe = evaluator.universe();
		this.stateFactory = evaluator.stateFactory();
		this.zero = universe.zeroInt();
		this.one = universe.oneInt();
		this.zeroObject = universe.intObject(0);
		this.oneObject = universe.intObject(1);
		this.enablePrintf = enablePrintf;
		this.output = output;
		this.modelFactory = modelFactory;
	}

}
