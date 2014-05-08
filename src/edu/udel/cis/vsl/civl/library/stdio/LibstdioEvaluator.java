package edu.udel.cis.vsl.civl.library.stdio;

import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.library.CommonLibraryEvaluator;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.State;

public class LibstdioEvaluator extends CommonLibraryEvaluator {
	private StdioLibraryWorker worker;

	public LibstdioEvaluator(Executor executor, ModelFactory modelFactory) {
		super(executor.evaluator().universe());
		worker = new StdioLibraryWorker(executor.evaluator(), executor,
				modelFactory);
	}

	@Override
	public Evaluation evaluate(State state, int pid,
			CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return worker.evaluateWork(state, pid, statement);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "stdio";
	}

}
