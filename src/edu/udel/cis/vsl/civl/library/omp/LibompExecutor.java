package edu.udel.cis.vsl.civl.library.omp;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;

public class LibompExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	public LibompExecutor(String name, Executor primaryExecutor,
			PrintStream output, PrintStream err, boolean enablePrintf,
			boolean statelessPrintf, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil) {
		super(name, primaryExecutor, output, err, enablePrintf,
				statelessPrintf, modelFactory, symbolicUtil);
	}

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		// TODO Auto-generated method stub
		return null;
	}
}
