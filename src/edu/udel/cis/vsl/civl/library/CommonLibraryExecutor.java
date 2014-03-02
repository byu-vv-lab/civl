package edu.udel.cis.vsl.civl.library;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.library.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;

/**
 * This class implements the common logic of library executors.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public abstract class CommonLibraryExecutor extends Library implements
		LibraryExecutor {

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
	 * The model factory of the system.
	 */
	protected ModelFactory modelFactory;

	/**
	 * The symbolic expression of one.
	 */
	protected NumericExpression one;

	/**
	 * The symbolic object of integer one.
	 */
	protected IntObject oneObject;

	/**
	 * The output stream to be used for printing.
	 */
	protected PrintStream output = System.out;

	/**
	 * The primary executor of the system.
	 */
	protected Executor primaryExecutor;

	/**
	 * The state factory for state-related computation.
	 */
	protected StateFactory stateFactory;

	/**
	 * The symbolic universe for symbolic computations.
	 */
	protected SymbolicUniverse universe;

	/**
	 * The symbolic expression of zero.
	 */
	protected NumericExpression zero;

	/**
	 * The symbolic object of integer zero.
	 */
	protected IntObject zeroObject;

	/* **************************** Constructors *************************** */

	/**
	 * Creates a new instance of a library executor.
	 * 
	 * @param primaryExecutor
	 *            The executor for normal CIVL execution.
	 * @param output
	 *            The output stream to be used in the enabler.
	 * @param enablePrintf
	 *            If printing is enabled for the printf function.
	 * @param modelFactory
	 *            The model factory of the system.
	 */
	protected CommonLibraryExecutor(Executor primaryExecutor,
			PrintStream output, boolean enablePrintf, ModelFactory modelFactory) {
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
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
