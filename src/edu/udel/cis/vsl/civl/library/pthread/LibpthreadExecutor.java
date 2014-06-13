package edu.udel.cis.vsl.civl.library.pthread;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;

public class LibpthreadExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {
	
	private IntObject fourObject;

	public LibpthreadExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			CIVLConfiguration civlConfig) {
		super(name, primaryExecutor, modelFactory, symbolicUtil, civlConfig);
		this.fourObject = universe.intObject(4);
	}

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, statement);
	}

	private State executeWork(State state, int pid,
			CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		int numArgs;
		CIVLSource source = statement.getSource();
		LHSExpression lhs = statement.lhs();
		int processIdentifier = state.getProcessState(pid).identifier();
		String process = "p" + processIdentifier + " (id = " + pid + ")";

		numArgs = statement.arguments().size();
		name = statement.function().name();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;
			
			arguments[i] = statement.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "pthread_mutex_lock":
			state = execute_pthread_mutex_lock(state, pid, process, lhs,
					arguments, argumentValues, source);
			break;
		default:
		}
		state = stateFactory.setLocation(state, pid, statement.target());
		return state;
	}

	/**
	 * * typedef struct { int count; $proc owner; int lock; int prioceiling;
	 * pthread_mutexattr_t *attr; } pthread_mutex_t;
	 * 
	 * @param state
	 * @param pid
	 * @param process
	 * @param lhs
	 * @param arguments
	 * @param argumentValues
	 * @param source
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State execute_pthread_mutex_lock(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		Evaluation eval;
		CIVLSource mutexSource = arguments[0].getSource();
		SymbolicExpression mutex_pointer = argumentValues[0];
		SymbolicExpression mutex;
		@SuppressWarnings("unused")
		SymbolicExpression mutex_attr;
		SymbolicExpression mutex_attr_pointer;
		@SuppressWarnings("unused")
		NumericExpression mutex_type;
		SymbolicExpression pidValue = modelFactory.processValue(pid);

		// TODO: check the case for "return EOWNERDEAD".
		eval = evaluator.dereference(mutexSource, state, process,
				mutex_pointer, false);
		state = eval.state;
		mutex = eval.value;
		mutex_attr_pointer = universe.tupleRead(mutex, fourObject);
		mutexSource = arguments[0].getSource();
		eval = evaluator.dereference(mutexSource, state, process, mutex_attr_pointer, false);
		state = eval.state;
		mutex_attr = eval.value;
		eval = evaluator.dereference(mutexSource, state, process, mutex_attr_pointer, false);
		mutex_attr = eval.value;
		state = eval.state;
		mutex = universe.tupleWrite(mutex, twoObject, one);
		mutex = universe.tupleWrite(mutex, oneObject, pidValue);
		state = primaryExecutor.assign(mutexSource, state, process,
				mutex_pointer, mutex);
		if (lhs != null) {
			
			state = primaryExecutor.assign(state, pid, process, lhs, zero);
		}
		return state;
	}
}
