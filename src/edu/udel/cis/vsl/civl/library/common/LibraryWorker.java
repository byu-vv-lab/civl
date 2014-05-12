package edu.udel.cis.vsl.civl.library.common;

import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

public abstract class LibraryWorker extends Library {

	protected ModelFactory modelFactory;
	protected Executor executor;
	protected Evaluator evaluator;

	protected LibraryWorker(Executor executor, SymbolicUniverse universe) {
		super(universe);
		this.executor = executor;
		this.evaluator = executor.evaluator();
		this.modelFactory = executor.modelFactory();
	}
}
