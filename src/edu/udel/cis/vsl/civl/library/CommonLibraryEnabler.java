package edu.udel.cis.vsl.civl.library;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;

public abstract class CommonLibraryEnabler implements LibraryEnabler {

	protected Enabler primaryEnabler;

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

	protected StateFactory stateFactory;

	/**
	 * The symbolic universe for symbolic computations.
	 */
	protected SymbolicUniverse universe;

	protected NumericExpression zero;

	protected IntObject zeroObject;

	protected ModelFactory modelFactory;

	protected CommonLibraryEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		this.primaryEnabler = primaryEnabler;
		this.evaluator = primaryEnabler.evaluator();
		this.universe = evaluator.universe();
		this.stateFactory = evaluator.stateFactory();
		this.zero = universe.zeroInt();
		this.one = universe.oneInt();
		this.zeroObject = universe.intObject(0);
		this.oneObject = universe.intObject(1);
		this.output = output;
		this.modelFactory = modelFactory;
	}

	@Override
	public Evaluation getGuard(CIVLSource source, State state, int pid,
			CallOrSpawnStatement call) {
		return new Evaluation(state, universe.trueExpression());
	}

	@Override
	public Set<Integer> ampleSet(State state, int pid, Statement statement,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap) {
		return new HashSet<>();
	}

}
