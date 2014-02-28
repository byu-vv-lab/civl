/**
 * 
 */
package edu.udel.cis.vsl.civl.library.stdlib;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.library.CommonLibraryExecutor;
import edu.udel.cis.vsl.civl.library.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * Executor for stdlib function calls.
 * 
 * @author zirkel
 * 
 */
public class Libstdlib extends CommonLibraryExecutor implements LibraryExecutor {

	/**
	 * Executor for stdlib function calls.
	 */
	public Libstdlib(Executor primaryExecutor, PrintStream output,
			boolean enablePrintf, ModelFactory modelFactory) {
		super(primaryExecutor, output, enablePrintf, modelFactory);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.library.IF.LibraryExecutor#name()
	 */
	@Override
	public String name() {
		return "stdlib";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.library.IF.LibraryExecutor#execute(edu.udel.cis
	 *      .vsl.civl.state.State, int,
	 *      edu.udel.cis.vsl.civl.model.IF.statement.Statement)
	 */
	@Override
	public State execute(State state, int pid, Statement statement) {
		// Identifier name;
		// State result = null;

		throw new CIVLUnimplementedFeatureException(
				"stdlib not yet implemented", statement);

		// if (!(statement instanceof CallOrSpawnStatement)) {
		// throw new RuntimeException("Unsupported statement for stdlib: "
		// + statement);
		// }
		// name = ((CallOrSpawnStatement) statement).function().name();
		// if (name.name().equals("malloc")) {
		// // Vector<SymbolicExpression> heapElements = new
		// // Vector<SymbolicExpression>();
		//
		// } else if (name.name().equals("free")) {
		//
		// } else {
		// throw new RuntimeException("Unsupported statement for stdlib: "
		// + statement);
		// }
		// return result;
		// // TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.library.IF.LibraryExecutor#initialize(edu.udel
	 *      .cis.vsl.civl.state.State)
	 */
	@Override
	public State initialize(State state) {
		// TODO Auto-generated method stub
		return state;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.library.IF.LibraryExecutor#wrapUp(edu.udel.cis
	 *      .vsl.civl.state.State)
	 */
	@Override
	public State wrapUp(State state) {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public BooleanExpression getGuard(State state, int pid, Statement statement) {
		// TODO Auto-generated method stub
		return null;
	}

}
