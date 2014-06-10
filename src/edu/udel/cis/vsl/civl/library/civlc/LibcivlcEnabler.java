package edu.udel.cis.vsl.civl.library.civlc;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.kripke.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryEnabler;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.SystemFunction;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.FunctionPointerExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.civl.semantics.IF.TransitionFactory;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;

/**
 * Implementation of the enabler-related logics for system functions declared
 * civlc.h.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibcivlcEnabler extends BaseLibraryEnabler implements
		LibraryEnabler {

	private static String chooseIntWork = "$choose_int_work";
	private FunctionPointerExpression chooseIntWorkPointer;

	/* **************************** Constructors *************************** */
	/**
	 * Creates a new instance of the library enabler for civlc.h.
	 * 
	 * @param primaryEnabler
	 *            The enabler for normal CIVL execution.
	 * @param output
	 *            The output stream to be used in the enabler.
	 * @param modelFactory
	 *            The model factory of the system.
	 */
	public LibcivlcEnabler(String name, Enabler primaryEnabler,
			Evaluator evaluator, TransitionFactory transitionFactory,
			PrintStream output, ModelFactory modelFactory,
			SymbolicUtility symbolicUtil) {
		super(name, primaryEnabler, evaluator, transitionFactory, output,
				modelFactory, symbolicUtil);

		CIVLSource source = modelFactory.model().getSource();
		SystemFunction chooseIntWorkFunction = modelFactory.systemFunction(
				source,
				modelFactory.identifier(source, chooseIntWork),
				Arrays.asList(modelFactory.variable(source,
						modelFactory.integerType(),
						modelFactory.identifier(source, "n"), 0)),
				modelFactory.integerType(), modelFactory.model().system()
						.containingScope(), "civlc");

		chooseIntWorkPointer = modelFactory.functionPointerExpression(source,
				chooseIntWorkFunction);

	}

	/* ********************* Methods from LibraryEnabler ******************* */

	@Override
	public Set<Integer> ampleSet(State state, int pid,
			CallOrSpawnStatement statement,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap) {
		Identifier name;
		CallOrSpawnStatement call;

		if (!(statement instanceof CallOrSpawnStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc",
					statement);
		}
		call = (CallOrSpawnStatement) statement;
		name = call.function().name();
		switch (name.name()) {
		case "$comm_enqueue":
		case "$comm_dequeue":
			return ampleSetWork(state, pid, call, reachableMemUnitsMap);
		default:
			return super.ampleSet(state, pid, statement, reachableMemUnitsMap);
		}
	}

	@Override
	public List<Transition> enabledTransitions(State state,
			CallOrSpawnStatement call, BooleanExpression pathCondition,
			int pid, int processIdentifier, Statement assignAtomicLock)
			throws UnsatisfiablePathConditionException {
		String functionName = call.function().name().name();
		CallOrSpawnStatement callWorker;
		List<Expression> arguments = call.arguments();
		List<Transition> localTransitions = new ArrayList<>();
		Statement transitionStatement;
		String process = "p" + processIdentifier + " (id = " + pid + ")";

		switch (functionName) {
		case "$choose_int":
			Evaluation eval = evaluator.evaluate(
					state.setPathCondition(pathCondition), pid,
					arguments.get(0));
			IntegerNumber upperNumber = (IntegerNumber) universe.reasoner(
					eval.state.getPathCondition()).extractNumber(
					(NumericExpression) eval.value);
			int upper;

			if (upperNumber == null) {
				throw new CIVLExecutionException(ErrorKind.INTERNAL,
						Certainty.NONE, process,
						"Argument to $choose_int not concrete: " + eval.value,
						symbolicUtil.stateToString(state), arguments.get(0)
								.getSource());
			}
			upper = upperNumber.intValue();
			for (int i = 0; i < upper; i++) {
				Expression workerArg = modelFactory.integerLiteralExpression(
						arguments.get(0).getSource(), BigInteger.valueOf(i));

				callWorker = modelFactory.callOrSpawnStatement(
						call.getSource(), null, true,
						Arrays.asList(workerArg), null);
				callWorker.setTargetTemp(call.target());
				callWorker.setFunction(chooseIntWorkPointer);
				callWorker.setLhs(call.lhs());
				if (assignAtomicLock != null) {
					transitionStatement = modelFactory.statmentList(
							assignAtomicLock, callWorker);
				} else {
					transitionStatement = callWorker;
				}
				localTransitions.add(transitionFactory.newSimpleTransition(
						pathCondition, pid, processIdentifier,
						transitionStatement));
			}
			break;
		default:
			return super.enabledTransitions(state, call, pathCondition, pid,
					processIdentifier, assignAtomicLock);
		}
		return localTransitions;
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Computes the ample set process ID's from a system function call.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the system function call belongs
	 *            to.
	 * @param call
	 *            The system function call statement.
	 * @param reachableMemUnitsMap
	 *            The map of reachable memory units of all active processes.
	 * @return
	 */
	private Set<Integer> ampleSetWork(State state, int pid,
			CallOrSpawnStatement call,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap) {
		int numArgs;
		numArgs = call.arguments().size();
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		String function = call.function().name().name();
		CIVLSource source = call.getSource();
		Set<Integer> ampleSet = new HashSet<>();

		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval = null;

			arguments[i] = call.arguments().get(i);
			try {
				eval = evaluator.evaluate(state, pid, arguments[i]);
			} catch (UnsatisfiablePathConditionException e) {
				return new HashSet<>();
			}
			argumentValues[i] = eval.value;
			state = eval.state;
		}

		switch (function) {
		// case "$barrier_enter":
		// try {
		// SymbolicExpression barrier = evaluator.evaluate(state, pid,
		// arguments[0]).value;
		// SymbolicExpression barrierObj = evaluator.dereference(source, state,
		// barrier).value;
		// SymbolicExpression gbarrier = universe.tupleRead(barrierObj,
		// oneObject);
		// SymbolicExpression gbarrierObj = evaluator.dereference(source, state,
		// gbarrier).value;
		// SymbolicExpression procMapArray = universe.tupleRead(gbarrierObj,
		// oneObject);
		// SymbolicSequence<?> procMapElements = (SymbolicSequence<?>)
		// procMapArray.argument(0);
		// int count = procMapElements.size();
		//
		// for(int i = 0; i < count; i++){
		// SymbolicExpression processValue = procMapElements.get(i);
		// int otherPid = modelFactory.getProcessId(source, processValue);
		//
		// if(pid != otherPid){
		// ampleSet.add(otherPid);
		// }
		// }
		// } catch (UnsatisfiablePathConditionException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// return ampleSet;
		case "$comm_dequeue":
		case "$comm_enqueue":
			Set<SymbolicExpression> handleObjMemUnits = new HashSet<>();

			try {
				evaluator.memoryUnitsOfExpression(state, pid, arguments[0],
						handleObjMemUnits);
			} catch (UnsatisfiablePathConditionException e) {
				handleObjMemUnits.add(argumentValues[0]);
			}
			for (SymbolicExpression memUnit : handleObjMemUnits) {
				for (int otherPid : reachableMemUnitsMap.keySet()) {
					if (otherPid == pid || ampleSet.contains(otherPid))
						continue;
					else if (reachableMemUnitsMap.get(otherPid).containsKey(
							memUnit)) {
						ampleSet.add(otherPid);
					}
				}
			}
			return ampleSet;
		default:
			throw new CIVLInternalException("Unreachable" + function, source);
		}
	}

}
