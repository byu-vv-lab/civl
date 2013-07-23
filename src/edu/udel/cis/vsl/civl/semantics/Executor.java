/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.log.ErrorLog;
import edu.udel.cis.vsl.civl.model.IF.Function;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.SystemFunction;
import edu.udel.cis.vsl.civl.model.IF.expression.DotExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SubscriptExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.AssertStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssumeStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.JoinStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.ArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.PointerType;
import edu.udel.cis.vsl.civl.model.IF.type.StructType;
import edu.udel.cis.vsl.civl.model.IF.type.Type;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;
import edu.udel.cis.vsl.civl.state.DynamicScope;
import edu.udel.cis.vsl.civl.state.Process;
import edu.udel.cis.vsl.civl.state.StackEntry;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.ValidityResult;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * An executor is used to execute a Chapel statement. The basic method provided
 * takes a state and a statement, and modifies the state according to the
 * semantics of that statement.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Executor {

	/** The model being executed */
	private Model model;

	/** The symbolic universe used to maange all symbolic expressions. */
	private SymbolicUniverse symbolicUniverse;

	/** The factory used to produce and manipulate model states. */
	private StateFactoryIF stateFactory;

	/** The Evaluator used to evaluate expressions. */
	private Evaluator evaluator;

	// /**
	// * List of all final states encountered. A final state is a normal
	// * termination state, where the system function returns. Not sure why we
	// are
	// * doing this---not being used now.
	// */
	// private Vector<State> finalStates = new Vector<State>();

	/**
	 * Log used to record property violations encountered as the model is
	 * executed.
	 */
	private ErrorLog log;

	/**
	 * The type of the symbolic expressions used as values for variable of type
	 * $proc.
	 */
	private SymbolicType processType;

	/**
	 * The loader used to find Executors for system functions declared in
	 * libraries.
	 */
	private LibraryExecutorLoader loader;

	/**
	 * Create a new executor.
	 * 
	 * @param model
	 *            The model being executed.
	 * @param symbolicUniverse
	 *            A symbolic universe for creating new values.
	 * @param stateFactory
	 *            A state factory. Used by the Executor to create new processes.
	 * @param prover
	 *            A theorem prover for checking assertions.
	 */
	public Executor(Model model, SymbolicUniverse symbolicUniverse,
			StateFactoryIF stateFactory, ErrorLog log,
			LibraryExecutorLoader loader) {
		this.model = model;
		this.symbolicUniverse = symbolicUniverse;
		this.stateFactory = stateFactory;
		this.evaluator = new Evaluator(symbolicUniverse, log);
		this.log = log;
		this.loader = loader;
		this.processType = evaluator.processType();
	}

	/**
	 * Create a new executor with null library loader.
	 * 
	 * @param model
	 *            The model being executed.
	 * @param symbolicUniverse
	 *            A symbolic universe for creating new values.
	 * @param stateFactory
	 *            A state factory. Used by the Executor to create new processes.
	 * @param prover
	 *            A theorem prover for checking assertions.
	 */
	public Executor(Model model, SymbolicUniverse symbolicUniverse,
			StateFactoryIF stateFactory, ErrorLog log) {
		this(model, symbolicUniverse, stateFactory, log, null);
	}

	// Helper methods...

	private State assign(State state, SymbolicExpression pointer,
			SymbolicExpression value) {
		int vid = evaluator.getVariableId(pointer);
		int sid = evaluator.getScopeId(pointer);
		ReferenceExpression symRef = evaluator.getSymRef(pointer);
		SymbolicExpression oldVariableValue = evaluator.dereference(state,
				pointer);
		SymbolicExpression newVariableValue = symbolicUniverse.assign(
				oldVariableValue, symRef, value);
		State result = stateFactory.setVariable(state, vid, sid,
				newVariableValue);

		return result;
	}

	private State assign(State state, int pid, LHSExpression lhs,
			SymbolicExpression value) {
		SymbolicExpression pointer = evaluator.reference(state, pid, lhs);

		return assign(state, pointer, value);
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
	private State executeAssign(State state, int pid, AssignStatement statement) {
		Process process = state.process(pid);
		SymbolicExpression value = evaluator.evaluate(state, pid,
				statement.rhs());

		state = assign(state, pid, statement.getLhs(), value);
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
	public State executeChoose(State state, int pid, ChooseStatement statement,
			SymbolicExpression value) {
		Process process = state.process(pid);
		state = assign(state, pid, statement.getLhs(), value);
		state = transition(state, process, statement.target());
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
	private State executeCall(State state, int pid,
			CallOrSpawnStatement statement) {
		if (statement.function() instanceof SystemFunction) {
			LibraryExecutor executor = loader.getLibraryExecutor(
					((SystemFunction) statement.function()).getLibrary(), this);

			state = executor.execute(state, pid, statement);
		} else {
			Function function = statement.function();
			SymbolicExpression[] arguments;

			arguments = new SymbolicExpression[statement.arguments().size()];
			for (int i = 0; i < statement.arguments().size(); i++)
				arguments[i] = evaluator.evaluate(state, pid, statement
						.arguments().get(i));
			state = stateFactory.pushCallStack(state, pid, function, arguments);
		}
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
	private State executeSpawn(State state, int pid,
			CallOrSpawnStatement statement) {
		Process process = state.process(pid);
		Function function = statement.function();
		SymbolicExpression[] arguments;
		int newPid;

		arguments = new SymbolicExpression[statement.arguments().size()];
		for (int i = 0; i < statement.arguments().size(); i++)
			arguments[i] = evaluator.evaluate(state, pid, statement.arguments()
					.get(i));
		state = stateFactory.addProcess(state, function, arguments, pid);
		// Find the new process's id.
		newPid = pid;
		for (Process p : state.processes()) {
			if (p.id() > newPid) {
				newPid = p.id();
			}
		}
		if (statement.lhs() != null)
			state = assign(state, pid, statement.lhs(),
					evaluator.makeProcVal(newPid));
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
	private State executeJoin(State state, int pid, JoinStatement statement) {
		SymbolicExpression procVal = evaluator.evaluate(state, pid,
				statement.process());
		int joinedPid = evaluator.getPid(procVal);

		state = transition(state, state.process(pid), statement.target());
		state = stateFactory.removeProcess(state, joinedPid);
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
	private State executeReturn(State state, int pid, ReturnStatement statement) {
		Expression returnExpression = statement.expression();
		SymbolicExpression returnValue = returnExpression == null ? null
				: evaluator.evaluate(state, pid, returnExpression);
		Process process = state.process(pid);

		state = stateFactory.popCallStack(state, pid);
		if (!process.hasEmptyStack()) {
			StackEntry returnContext = process.peekStack();
			Location returnLocation = returnContext.location();
			CallOrSpawnStatement call = (CallOrSpawnStatement) returnLocation
					.getSoleOutgoing();

			if (call.lhs() != null)
				state = assign(state, pid, call.lhs(), returnValue);
			state = stateFactory.setLocation(state, pid, call.target());
		}
		// state = stateFactory.canonic(state);
		return state;
	}

	public State executeAssume(State state, int pid, AssumeStatement statement) {
		SymbolicExpression assumeExpression = evaluator.evaluate(state, pid,
				statement.getExpression());

		state = stateFactory.setPathCondition(state, symbolicUniverse.and(
				(BooleanExpression) state.pathCondition(),
				(BooleanExpression) assumeExpression));
		state = transition(state, state.process(pid), statement.target());
		return state;
	}

	public State executeAssert(State state, int pid, AssertStatement statement) {
		SymbolicExpression assertExpression = evaluator.evaluate(state, pid,
				statement.getExpression());
		Reasoner reasoner = symbolicUniverse.reasoner((BooleanExpression) state
				.pathCondition());
		ValidityResult valid = reasoner
				.valid((BooleanExpression) assertExpression);
		
		if (valid.getResultType() != ResultType.YES) {
			Certainty certainty;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);

			if (valid.getResultType() == ResultType.NO) {
				certainty = Certainty.PROVEABLE;
			} else {
				certainty = Certainty.MAYBE;
			}
			state.print(ps);
			log.report(new CIVLStateException(ErrorKind.ASSERTION_VIOLATION,
					certainty, "Cannot prove assertion holds: "
							+ statement.toString() + "\n  Path condition: "
							+ state.pathCondition() + "\n  Assertion: "
							+ assertExpression + "\n", state, statement
							.getSource()));

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
			return executeAssume(state, pid, (AssumeStatement) statement);
		} else if (statement instanceof AssertStatement) {
			return executeAssert(state, pid, (AssertStatement) statement);
		} else if (statement instanceof CallOrSpawnStatement) {
			CallOrSpawnStatement call = (CallOrSpawnStatement) statement;

			if (call.isCall())
				return executeCall(state, pid, call);
			else
				return executeSpawn(state, pid, call);
		} else if (statement instanceof AssignStatement) {
			return executeAssign(state, pid, (AssignStatement) statement);
		} else if (statement instanceof JoinStatement) {
			return executeJoin(state, pid, (JoinStatement) statement);
		} else if (statement instanceof ReturnStatement) {
			return executeReturn(state, pid, (ReturnStatement) statement);
		}
		// Otherwise, this is a noop.
		process = state.process(pid);
		state = transition(state, process, statement.target());
		// state = stateFactory.canonic(state);
		return state;
	}

	// /**
	// * Write a value to a variable.
	// *
	// * @param state
	// * The state of the program.
	// * @param pid
	// * The process id of the currently executing process.
	// * @param target
	// * The location where the value should be stored. This should be
	// * an ArrayIndexExpression or a VariableExpression.
	// * @param value
	// * An expression for the new value to write.
	// * @return A new state with the value of the target variable modified.
	// */
	// private State writeValue(State state, int pid, Expression target,
	// Expression value) {
	// State result = writeValue(state, pid, target,
	// evaluator.evaluate(state, pid, value));
	//
	// // result = stateFactory.canonic(result);
	// return result;
	// }

	// /**
	// * Write a value to a variable.
	// *
	// * @param state
	// * The state of the program.
	// * @param pid
	// * The process id of the currently executing process.
	// * @param target
	// * The location where the value should be stored. This should be
	// * an ArrayIndexExpression, a VariableExpression, a pointer
	// * dereference, or a DotExpression.
	// * @param symbolicValue
	// * The new symbolic value to write.
	// * @return A new state with the value of the target variable modified.
	// */
	// private State writeValue(State state, int pid, LHSExpression target,
	// SymbolicExpression symbolicValue) {
	// if (target instanceof VariableExpression) {
	// Variable variable = ((VariableExpression) target).variable();
	//
	// state = stateFactory.setVariable(state, variable, pid,
	// symbolicValue);
	// } else if (target instanceof SubscriptExpression) {
	// SymbolicExpression newValue = arrayWriteValue(state, pid,
	// (SubscriptExpression) target, symbolicValue);
	// SymbolicExpression currentArrayValue = evaluator.evaluate(state,
	// pid, ((SubscriptExpression) target).array());
	// Variable writeLocation = evaluator.getVariable(state, pid, target);
	//
	// // state = stateFactory.setVariable(state,
	// // baseArray(scope, (ArrayIndexExpression) target), pid,
	// // newValue);
	// if (currentArrayValue.type().equals(evaluator.pointerType())) {
	// int writeScopeID = evaluator.getPointerTargetScope(state, pid,
	// currentArrayValue);
	// int variableID = evaluator.getPointerTargetVariableID(state,
	// pid, currentArrayValue);
	//
	// state = stateFactory.setVariable(state,
	// state.getScope(writeScopeID).lexicalScope()
	// .getVariable(variableID), writeScopeID, pid,
	// newValue);
	// } else {
	// state = stateFactory.setVariable(state, writeLocation, pid,
	// newValue);
	// }
	// } else if (target instanceof UnaryExpression) {
	// Variable variable = evaluator.getVariable(state, pid, target);
	// int scopeID = evaluator.getPointerTargetScope(state, pid, target);
	//
	// state = stateFactory.setVariable(state, variable, scopeID, pid,
	// symbolicValue);
	// } else if (target instanceof DotExpression) {
	// Variable variable = evaluator.getVariable(state, pid,
	// ((DotExpression) target).struct());
	// SymbolicExpression structValue = evaluator.evaluate(state, pid,
	// ((DotExpression) target).struct());
	// Identifier field = ((DotExpression) target).field();
	// StructType structType;
	// IntObject index = null;
	// SymbolicExpression newValue;
	//
	// assert variable.type() instanceof StructType;
	// structType = (StructType) variable.type();
	// for (int i = 0; i < structType.fields().size(); i++) {
	// if (structType.fields().get(i).name().equals(field)) {
	// index = symbolicUniverse.intObject(i);
	// break;
	// }
	// }
	// assert index != null;
	// newValue = symbolicUniverse.tupleWrite(structValue, index,
	// symbolicValue);
	// state = stateFactory.setVariable(state, variable, pid, newValue);
	// } else if (target instanceof ArrowExpression) {
	// Variable variable = evaluator.getVariable(state, pid,
	// ((ArrowExpression) target).structPointer());
	// DynamicScope structScope;
	// SymbolicExpression pointerValue = evaluator.evaluate(state, pid,
	// ((ArrowExpression) target).structPointer());
	// int scopeID = evaluator.getPointerTargetScope(state, pid,
	// pointerValue);
	// int variableID = evaluator.getPointerTargetVariableID(state, pid,
	// pointerValue);
	// SymbolicExpression structValue = evaluator.dereference(state, pid,
	// pointerValue);
	// Identifier field = ((ArrowExpression) target).field();
	// StructType structType;
	// IntObject index = null;
	// SymbolicExpression newValue;
	//
	// assert variable.type() instanceof PointerType;
	// assert ((PointerType) variable.type()).baseType() instanceof StructType;
	// structType = (StructType) ((PointerType) variable.type())
	// .baseType();
	// for (int i = 0; i < structType.fields().size(); i++) {
	// if (structType.fields().get(i).name().equals(field)) {
	// index = symbolicUniverse.intObject(i);
	// break;
	// }
	// }
	// assert index != null;
	// newValue = symbolicUniverse.tupleWrite(structValue, index,
	// symbolicValue);
	// structScope = state.getScope(scopeID);
	// // state = stateFactory.setVariable(state, variable, pid, newValue);
	// state = stateFactory.setVariable(state, structScope.lexicalScope()
	// .getVariable(variableID), scopeID, pid, newValue);
	// }
	// // TODO: Throw some sort of exception otherwise.
	// // state = stateFactory.canonic(state);
	// return state;
	// }

	// /**
	// * Determine the symbolic value that results from writing to an array
	// * position.
	// *
	// * @param state
	// * The state of the program.
	// * @param pid
	// * The process ID of the currently executing process.
	// * @param arrayIndex
	// * The expression for the index in the array being modified.
	// * @param value
	// * The value being written to the array at the specified index.
	// * @return A new symbolic value for the array.
	// */
	// private SymbolicExpression arrayWriteValue(State state, int pid,
	// SubscriptExpression arrayIndex, SymbolicExpression value) {
	// SymbolicExpression result = null;
	// SymbolicExpression array = evaluator.evaluate(state, pid,
	// arrayIndex.array());
	// SymbolicExpression index = evaluator.evaluate(state, pid,
	// arrayIndex.index());
	//
	// if (!(index instanceof NumericExpression)) {
	// log.report(new ExecutionException(ErrorKind.OTHER,
	// Certainty.CONCRETE,
	// "An array index must evaluate to a numeric expression."));
	// return symbolicUniverse.nullExpression();
	// }
	// // while (array.type().equals(evaluator.pointerType())) {
	// // array = evaluator.dereference(state, pid, array);
	// // }
	// if (arrayIndex.array() instanceof SubscriptExpression) {
	// result = arrayWriteValue(state, pid,
	// (SubscriptExpression) arrayIndex.array(),
	// symbolicUniverse.arrayWrite(array,
	// (NumericExpression) index, value));
	// } else if (arrayIndex.array() instanceof ArrowExpression) {
	// SymbolicExpression pointerValue = evaluator.evaluate(state, pid,
	// ((ArrowExpression) arrayIndex.array()).structPointer());
	// int scopeID = evaluator.getPointerTargetScope(state, pid,
	// pointerValue);
	// int variableID = evaluator.getPointerTargetVariableID(state, pid,
	// pointerValue);
	// SymbolicExpression struct = evaluator.dereference(state, pid,
	// pointerValue);
	// SymbolicExpression field;
	// IntObject fieldIndex = null;
	// StructType structType = (StructType) state.getScope(scopeID)
	// .lexicalScope().getVariable(variableID).type();
	// Type fieldType;
	//
	// for (int i = 0; i < structType.fields().size(); i++) {
	// if (structType.fields().get(i).name()
	// .equals(((ArrowExpression) arrayIndex.array()).field())) {
	// fieldIndex = symbolicUniverse.intObject(i);
	// break;
	// }
	// }
	// assert fieldIndex != null;
	// fieldType = structType.fields().get(fieldIndex.getInt()).type();
	// field = symbolicUniverse.reasoner(
	// (BooleanExpression) state.pathCondition()).simplify(
	// symbolicUniverse.tupleRead(struct, fieldIndex));
	// if (fieldType instanceof ArrayType) {
	// result = symbolicUniverse.arrayWrite(field,
	// (NumericExpression) index, value);
	// } else if (fieldType instanceof PointerType) {
	// int fieldScopeID;
	// int fieldVariableID;
	// SymbolicExpression fieldTarget;
	//
	// fieldScopeID = evaluator.getPointerTargetScope(state, pid,
	// field);
	// fieldVariableID = evaluator.getPointerTargetVariableID(state,
	// pid, field);
	// fieldTarget = state.getScope(fieldScopeID).getValue(
	// fieldVariableID);
	// result = symbolicUniverse.arrayWrite(fieldTarget,
	// (NumericExpression) index, value);
	// } else {
	// throw new RuntimeException("Unable to get array: " + arrayIndex);
	// }
	// // result = symbolicUniverse.tupleWrite(struct, fieldIndex,
	// // fieldWriteResult);
	// } else {
	// if (array.type().equals(evaluator.pointerType())) {
	// array = evaluator.dereference(state, pid, array);
	// }
	// result = symbolicUniverse.arrayWrite(array,
	// (NumericExpression) index, value);
	// }
	// assert result != null;
	// return result;
	// }

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

	// /**
	// *
	// * @return The final states of the program.
	// */
	// public Collection<State> finalStates() {
	// return finalStates;
	// }

	/**
	 * @return The state factory associated with this executor.
	 */
	public StateFactoryIF stateFactory() {
		return stateFactory;
	}

	/**
	 * @return The symbolic universe associated with this executor.
	 */
	public SymbolicUniverse universe() {
		return symbolicUniverse;
	}

	/**
	 * @return The evaluator used by this executor.
	 * @return
	 */
	public Evaluator evaluator() {
		return evaluator;
	}

	// String pidPrefix() {
	// return pidPrefix;
	// }
}
