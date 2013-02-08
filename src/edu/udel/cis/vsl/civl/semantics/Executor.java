/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Vector;

import edu.udel.cis.vsl.civl.model.Function;
import edu.udel.cis.vsl.civl.model.Model;
import edu.udel.cis.vsl.civl.model.expression.ArrayIndexExpression;
import edu.udel.cis.vsl.civl.model.expression.Expression;
import edu.udel.cis.vsl.civl.model.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.location.Location;
import edu.udel.cis.vsl.civl.model.statement.AssertStatement;
import edu.udel.cis.vsl.civl.model.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.statement.AssumeStatement;
import edu.udel.cis.vsl.civl.model.statement.CallStatement;
import edu.udel.cis.vsl.civl.model.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.statement.ForkStatement;
import edu.udel.cis.vsl.civl.model.statement.JoinStatement;
import edu.udel.cis.vsl.civl.model.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.statement.Statement;
import edu.udel.cis.vsl.civl.model.variable.Variable;
import edu.udel.cis.vsl.civl.state.DynamicScope;
import edu.udel.cis.vsl.civl.state.Process;
import edu.udel.cis.vsl.civl.state.StackEntry;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.sarl.number.IF.IntegerNumberIF;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicExpressionIF;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicUniverseIF;
import edu.udel.cis.vsl.sarl.symbolic.ideal.BooleanIdealExpression;

/**
 * An executor is used to execute a Chapel statement. The basic method provided
 * takes a state and a statement, and modifies the state according to the
 * semantics of that statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Executor {

	private Model model;
	private SymbolicUniverseIF symbolicUniverse;
	private StateFactoryIF stateFactory;
	private Evaluator evaluator;
	private Vector<State> finalStates = new Vector<State>();

	/**
	 * Create a new executor.
	 * 
	 * @param symbolicUniverse
	 *            A symbolic universe for creating new values.
	 * @param stateFactory
	 *            A state factory. Used by the Executor to create new processes.
	 */
	public Executor(Model model, SymbolicUniverseIF symbolicUniverse,
			StateFactoryIF stateFactory) {
		this.model = model;
		this.symbolicUniverse = symbolicUniverse;
		this.stateFactory = stateFactory;
		this.evaluator = new Evaluator(symbolicUniverse);
	}

	/**
	 * Create a new executor.
	 * 
	 * @param symbolicUniverse
	 *            A symbolic universe for creating new values.
	 * @param stateFactory
	 *            A state factory. Used by the Executor to create new processes.
	 * @param out
	 *            A PrintStream to use for write statements.
	 */
	public Executor(Model model, SymbolicUniverseIF symbolicUniverse,
			StateFactoryIF stateFactory, PrintStream out) {
		this.model = model;
		this.symbolicUniverse = symbolicUniverse;
		this.stateFactory = stateFactory;
		this.evaluator = new Evaluator(symbolicUniverse);
	}

	/**
	 * Execute an assignment statement. The state will be updated such that the
	 * value of the DynamicVariable has the expression corresponding to the
	 * right hand side of the assignment, and the location of the state will be
	 * updated to the target location of the assignment.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            An assignment statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, AssignStatement statement) {
		Process process = state.process(pid);

		state = writeValue(state, pid, statement.getLhs(), statement.rhs());
		state = transition(state, process, statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Execute a choose statement. This is like an assignment statement where
	 * the variable gets assigned a particular value between 0 and arg-1,
	 * inclusive. The value is assigned for each transition from the choose
	 * source location by the Enabler.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            A choose statement to be executed.
	 * @param value
	 *            The value assigned to the variable for this particular
	 *            transition. This concrete value should be provided by the
	 *            enabler.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, ChooseStatement statement,
			SymbolicExpressionIF value) {
		Process process = state.process(pid);
		// String newConstantName = chooseVariable + statement.chooseID();
		// SymbolicConstantIF newConstant = symbolicUniverse
		// .getOrCreateSymbolicConstant(newConstantName,
		// symbolicUniverse.integerType());
		// SymbolicExpressionIF newConstantExpression = symbolicUniverse
		// .symbolicConstantExpression(newConstant);
		// TODO: Testing using the enabler to get concrete values instead of
		// setting up the
		// PC here. OK to delete this?
		// SymbolicExpressionIF lowerBound = symbolicUniverse.lessThanEquals(
		// symbolicUniverse.zeroInt(), newConstantExpression);
		// SymbolicExpressionIF upperBound = symbolicUniverse.lessThan(
		// newConstantExpression,
		// evaluator.evaluate(state, pid, statement.rhs()));

		state = writeValue(state, pid, statement.getLhs(), value);
		// state = stateFactory.setPathCondition(
		// state,
		// symbolicUniverse.and(state.pathCondition(),
		// symbolicUniverse.and(lowerBound, upperBound)));
		state = transition(state, process, statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Execute a call statement. The state will be updated such that the process
	 * is at the start location of the function, a new dynamic scope for the
	 * function is created, and function parameters in the new scope have the
	 * values that are passed as arguments.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            A call statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, CallStatement statement) {
		Function function = statement.function();
		SymbolicExpressionIF[] arguments;

		arguments = new SymbolicExpressionIF[statement.arguments().size()];
		for (int i = 0; i < statement.arguments().size(); i++) {
			SymbolicExpressionIF expression = evaluator.evaluate(state, pid,
					statement.arguments().get(i));

			arguments[i] = expression;
		}
		state = stateFactory.pushCallStack(state, pid, function, arguments);
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Execute a fork statement. The state will be updated with a new process
	 * whose start location is the beginning of the forked function.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            A fork statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, ForkStatement statement) {
		Process process = state.process(pid);
		Function function = null;
		SymbolicExpressionIF[] arguments;
		int newPid;

		for (Function f : model.functions()) {
			// Note: The function is a string literal expression
			if (f.name()
					.name()
					.equals(((StringLiteralExpression) statement.function())
							.value())) {
				function = f;
				break;
			}
		}
		// TODO: Throw exception if function not found.
		arguments = new SymbolicExpressionIF[statement.arguments().size()];
		for (int i = 0; i < statement.arguments().size(); i++) {
			arguments[i] = evaluator.evaluate(state, pid, statement.arguments()
					.get(i));
		}
		state = stateFactory.addProcess(state, function, arguments, pid);
		// Find the new process's id.
		newPid = pid;
		for (Process p : state.processes()) {
			if (p.id() > newPid) {
				newPid = p.id();
			}
		}
		if (statement.lhs() != null) {
			state = writeValue(state, pid, statement.lhs(),
					symbolicUniverse.concreteExpression(newPid));
		}
		state = transition(state, process, statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Execute a join statement. The state will be updated to no longer have the
	 * joined process.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            The join statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, JoinStatement statement) {
		SymbolicExpressionIF pidExpression = evaluator.evaluate(state, pid,
				statement.process());
		IntegerNumberIF pidNumber;

		// TODO: Throw exception if not the right type.
		pidNumber = (IntegerNumberIF) symbolicUniverse
				.extractNumber(pidExpression);
		state = stateFactory.removeProcess(state, pidNumber.intValue());
		state = transition(state, state.process(pid), statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Execute a return statement.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            The return statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, ReturnStatement statement) {
		Process process;
		StackEntry returnContext;
		Location returnLocation;
		CallStatement call;
		SymbolicExpressionIF returnExpression = null;

		if (state.process(pid).peekStack().location().function().name().name()
				.equals("_CVT_system")) {
			if (!finalStates.contains(state)) {
				finalStates.add(state);
			}
		}
		if (statement.expression() != null) {
			returnExpression = evaluator.evaluate(state, pid,
					statement.expression());
		}
		state = stateFactory.popCallStack(state, pid);
		process = state.process(pid);
		if (!process.hasEmptyStack()) {
			returnContext = process.peekStack();
			returnLocation = returnContext.location();
			// Note: the location of the function call should have exactly one
			// outgoing statement, which is a call statement.
			// TODO: Verify this, throw an exception if it's not the case.
			call = (CallStatement) returnLocation.outgoing().iterator().next();
			if (call.lhs() != null) {
				state = writeValue(state, pid, call.lhs(), returnExpression);
			}
			state = stateFactory.setLocation(state, pid, call.target());
		}
		// state = stateFactory.canonic(state);
		return state;
	}

	public State execute(State state, int pid, AssumeStatement statement) {
		SymbolicExpressionIF assumeExpression = evaluator.evaluate(state, pid,
				statement.getExpression());

		state = stateFactory.setPathCondition(state,
				symbolicUniverse.and(state.pathCondition(), assumeExpression));
		state = transition(state, state.process(pid), statement.target());
		return state;
	}

	public State execute(State state, int pid, AssertStatement statement) {
		SymbolicExpressionIF assertExpression = evaluator.evaluate(state, pid,
				statement.getExpression());

		// TODO Handle error reporting in a nice way.
		if (!(assertExpression instanceof BooleanIdealExpression)
				|| ((BooleanIdealExpression) assertExpression).toString()
						.equals("false")) {
			throw new RuntimeException("Assertion may be violated: "
					+ statement.toString() + "\n  Expected: true\n  Actual: "
					+ assertExpression);
		}
		state = transition(state, state.process(pid), statement.target());
		return state;
	}

	/**
	 * Execute a generic statement. All statements except a Choose should be
	 * handled by this method.
	 * 
	 * @param State
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param statement
	 *            The statement to be executed.
	 * @return The updated state of the program.
	 */
	public State execute(State state, int pid, Statement statement) {
		Process process;

		if (statement instanceof AssumeStatement) {
			return execute(state, pid, (AssumeStatement) statement);
		} else if (statement instanceof AssertStatement) {
			return execute(state, pid, (AssertStatement) statement);
		} else if (statement instanceof CallStatement) {
			return execute(state, pid, (CallStatement) statement);
		} else if (statement instanceof AssignStatement) {
			return execute(state, pid, (AssignStatement) statement);
		} else if (statement instanceof ForkStatement) {
			return execute(state, pid, (ForkStatement) statement);
		} else if (statement instanceof JoinStatement) {
			return execute(state, pid, (JoinStatement) statement);
		} else if (statement instanceof ReturnStatement) {
			return execute(state, pid, (ReturnStatement) statement);
		}
		// Otherwise, this is a noop.
		process = state.process(pid);
		state = transition(state, process, statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Write a value to a variable.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param target
	 *            The location where the value should be stored. This should be
	 *            an ArrayIndexExpression or a VariableExpression.
	 * @param value
	 *            An expression for the new value to write.
	 * @return A new state with the value of the target variable modified.
	 */
	private State writeValue(State state, int pid, Expression target,
			Expression value) {
		State result = writeValue(state, pid, target,
				evaluator.evaluate(state, pid, value));

		// result = stateFactory.canonic(result);
		return result;
	}

	/**
	 * Write a value to a variable.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The process id of the currently executing process.
	 * @param target
	 *            The location where the value should be stored. This should be
	 *            an ArrayIndexExpression or a VariableExpression.
	 * @param symbolicValue
	 *            The new symbolic value to write.
	 * @return A new state with the value of the target variable modified.
	 */
	private State writeValue(State state, int pid, Expression target,
			SymbolicExpressionIF symbolicValue) {
		DynamicScope scope = state.getScope(state.process(pid).scope());

		if (target instanceof VariableExpression) {
			Variable variable = ((VariableExpression) target).variable();

			state = stateFactory.setVariable(state, variable, pid,
					symbolicValue);
		} else if (target instanceof ArrayIndexExpression) {
			SymbolicExpressionIF array = evaluator.evaluate(state, pid,
					((ArrayIndexExpression) target).array());
			SymbolicExpressionIF index = evaluator.evaluate(state, pid,
					((ArrayIndexExpression) target).index());

			state = stateFactory.setVariable(state,
					baseArray(scope, (ArrayIndexExpression) target), pid,
					symbolicUniverse.arrayWrite(array, index, symbolicValue));
		}
		// TODO: Throw some sort of exception otherwise.
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * Get the variable at the base of a (possibly multi-dimensional) array.
	 * 
	 * @param scope
	 *            The dynamic scope containing this array reference.
	 * @param expression
	 *            The array index expression.
	 * @return The variable corresponding to the base of this array.
	 */
	private Variable baseArray(DynamicScope scope,
			ArrayIndexExpression expression) {
		if (expression.array() instanceof ArrayIndexExpression) {
			return baseArray(scope, ((ArrayIndexExpression) expression.array()));
		} else if (expression.array() instanceof VariableExpression) {
			return ((VariableExpression) expression.array()).variable();
		}
		return null;
	}

	/**
	 * Transition a process from one location to another. If the new location is
	 * in a different scope, create a new scope or move to the parent scope as
	 * necessary.
	 * 
	 * @param state
	 *            The old state.
	 * @param process
	 *            The process undergoing the transition.
	 * @param target
	 *            The end location of the transition.
	 * @return A new state where the process is at the target location.
	 */
	private State transition(State state, Process process, Location target) {
		state = stateFactory.setLocation(state, process.id(), target);
		// state = stateFactory.canonic(state);
		return state;
	}

	/**
	 * 
	 * @return The final states of the program.
	 */
	public Collection<State> finalStates() {
		return finalStates;
	}

	/**
	 * @return The state factory associated with this executor.
	 */
	public StateFactoryIF stateFactory() {
		return stateFactory;
	}
}
