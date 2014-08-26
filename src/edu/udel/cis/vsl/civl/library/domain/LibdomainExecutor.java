package edu.udel.cis.vsl.civl.library.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicAnalyzer;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class LibdomainExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	@SuppressWarnings("unused")
	private static int DECOMP_ALL = 0;
	@SuppressWarnings("unused")
	private static int DECOMP_RANDOM = 1;
	@SuppressWarnings("unused")
	private static int DECOMP_ROUND_ROBIN = 2;

	public LibdomainExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			SymbolicAnalyzer symbolicAnalyzer, CIVLConfiguration civlConfig) {
		super(name, primaryExecutor, modelFactory, symbolicUtil,
				symbolicAnalyzer, civlConfig);
	}

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement call)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		LHSExpression lhs;
		int numArgs;
		String process = state.getProcessState(pid).name() + "(id=" + pid + ")";

		numArgs = call.arguments().size();
		name = call.function().name();
		lhs = call.lhs();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = call.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "$domain_partition":
			state = execute_domain_partition(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		}
		state = stateFactory.setLocation(state, pid, call.target());
		return state;
	}

	private State execute_domain_partition(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression domain = argumentValues[0];
		NumericExpression strategy = (NumericExpression) argumentValues[1];
		NumericExpression numParts = (NumericExpression) argumentValues[2];
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber strategy_num = (IntegerNumber) reasoner
				.extractNumber(strategy), numParts_num = (IntegerNumber) reasoner
				.extractNumber(numParts);
		@SuppressWarnings("unused")
		int strategy_int, numParts_int;
		List<SymbolicExpression> subDomains;
		SymbolicType domainElementType = symbolicUtil
				.getDomainElementType(domain);

		if (strategy_num == null) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER, Certainty.PROVEABLE, process,
					"$domain_partition requires a concrete strategy argument",
					symbolicAnalyzer.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		if (numParts_num == null) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER,
					Certainty.PROVEABLE,
					process,
					"$domain_partition requires a concrete number of partitions argument",
					symbolicAnalyzer.stateToString(state), source);

			this.errorLogger.reportError(err);
			return state;
		}
		strategy_int = strategy_num.intValue();
		numParts_int = numParts_num.intValue();
		// TODO other strategy
		subDomains = this.domainPartition_round_robin(domain, numParts_int,
				domainElementType);

		SymbolicTupleType resultType = universe.tupleType(universe
				.stringObject("$domain_decomposition"), Arrays.asList(universe
				.integerType(), universe.arrayType(
				universe.arrayType(domainElementType), numParts)));
		SymbolicExpression result = universe.tuple(
				resultType,
				Arrays.asList(numParts, universe.array(
						universe.arrayType(domainElementType), subDomains)));
		if (lhs != null)
			state = this.primaryExecutor.assign(state, pid, process, lhs,
					result);
		return state;
	}

	private List<SymbolicExpression> domainPartition_round_robin(
			SymbolicExpression domain, int number,
			SymbolicType domainElementType) {
		List<List<SymbolicExpression>> partitions = new ArrayList<>(number);
		List<SymbolicExpression> current = symbolicUtil.getDomainInit(domain);
		boolean init = true;
		int id = 0;
		List<SymbolicExpression> result = new LinkedList<>();
		// int dim = current.size();

		for (int i = 0; i < number; i++)
			partitions.add(new LinkedList<SymbolicExpression>());
		do {
			if (init)
				init = false;
			else
				current = symbolicUtil.getNextInDomain(domain, current);
			List<SymbolicType> varTypes = new LinkedList<>();

			for (int i = 0; i < current.size(); i++)
				varTypes.add(current.get(i).type());
			SymbolicExpression element = universe.tuple(
					(SymbolicTupleType) domainElementType, current);
			partitions.get(id).add(element);
			id = (id + 1) % number;
		} while (symbolicUtil.domainHasNext(domain, current).isTrue());
		for (int i = 0; i < number; i++) {
			List<SymbolicExpression> elementsI = partitions.get(i);
			List<SymbolicType> eleTypes = new LinkedList<>();

			for (int j = 0; j < elementsI.size(); j++)
				eleTypes.add(elementsI.get(j).type());
			// SymbolicTupleType domainType = universe.tupleType(
			// universe.stringObject("$domain(" + dim
			// + ")"), eleTypes);
			result.add(universe.array(domainElementType, elementsI));
		}
		return result;
	}
}
