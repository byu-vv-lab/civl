package edu.udel.cis.vsl.civl.semantics.IF;

import java.util.Set;

import edu.udel.cis.vsl.civl.err.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.kripke.IF.Enabler;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.WaitStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.civl.util.IF.Pair;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public interface Evaluator {

	/**
	 * Given a pointer value, dereferences it in the given state to yield the
	 * symbolic expression value stored at the referenced location.
	 * 
	 * @param state
	 *            a CIVL model state
	 * @param pointer
	 *            a pointer value which refers to some sub-structure in the
	 *            state
	 * @return the value pointed to
	 * @throws UnsatisfiablePathConditionException
	 */
	Evaluation dereference(CIVLSource source, State state,
			SymbolicExpression pointer)
			throws UnsatisfiablePathConditionException;

	/**
	 * Evaluates the expression and returns the result, which is a symbolic
	 * expression value.
	 * 
	 * If a potential error is encountered while evaluating the expression (e.g.
	 * possible division by 0 in x/y), the error is logged, a correcting side
	 * effect (e.g. y!=0) is added to the path condition, and execution
	 * continues. It is possible for the side effect to make the path condition
	 * unsatisfiable. When this happens, an UnsatisfiablePathConditionException
	 * is thrown.
	 * 
	 * @param state
	 *            the state in which the evaluation takes place
	 * @param pid
	 *            the PID of the process which is evaluating the expression
	 * @param expression
	 *            the (static) expression being evaluated
	 * @return the result of the evaluation
	 * @throws UnsatisfiablePathConditionException
	 *             if a side effect that results from evaluating the expression
	 *             causes the path condition to become unsatisfiable
	 */
	Evaluation evaluate(State state, int pid, Expression expression)
			throws UnsatisfiablePathConditionException;

	/**
	 * Evaluate the size of a CIVL type.
	 * 
	 * TODO is this necessarily public?
	 * 
	 * @param source
	 *            The source code element to be used for error report.
	 * @param state
	 *            The state where the evaluation happens.
	 * @param pid
	 *            The ID of the process that triggers the evaluation.
	 * @param type
	 *            The CIVL type whose size is to be evaluated.
	 * @return the result of the evaluation, including the symbolic expression
	 *         of the size of the type and a state
	 * @throws UnsatisfiablePathConditionException
	 */
	Evaluation evaluateSizeofType(CIVLSource source, State state, int pid,
			CIVLType type) throws UnsatisfiablePathConditionException;

	/**
	 * Given a pointer to char, returns the symbolic expression of type array of
	 * char which is the string pointed to.
	 * 
	 * The method will succeed if any of the following holds: (1) the pointer
	 * points to element 0 of an array of char. In that case, it is just assumed
	 * that the string is the whole array. (2) the pointer points to element i
	 * of an array of char, where i is a concrete positive integer and the array
	 * length is also concrete. In that case, the elements of the array are
	 * scanned starting from position i until the first null charcter is
	 * reached, or the end of the array is reached, and the string is construted
	 * from those scanned characters (including the null character). In other
	 * situations, this method may fail, in which case it throws an exception.
	 * 
	 * @param state
	 *            the state in which this evaluation is taking place
	 * @param source
	 *            the source information used to report errors
	 * @param charPointer
	 *            a symbolic expression which is a pointer to a char
	 * @throws CIVLUnimplementedFeatureException
	 *             if it is not possible to extract the string expression.
	 * @return the symbolic expression which is an array of type char
	 *         representing the string pointed to
	 * @throws UnsatisfiablePathConditionException
	 *             of something goes wrong evaluating the string
	 */
	Evaluation getStringExpression(State state, CIVLSource source,
			SymbolicExpression charPointer)
			throws UnsatisfiablePathConditionException;

	/**
	 * Calculate the ID of the process that a given wait statement is waiting
	 * for.
	 * 
	 * @param state
	 *            The current state.
	 * @param p
	 *            The process that the wait statement belongs to.
	 * @param wait
	 *            The wait statement to be checked.
	 * @return The ID of the process that the wait statement is waiting for.
	 */
	int joinedIDofWait(State state, ProcessState p, WaitStatement wait);

	/**
	 * Compute the reachable memory units of an expression recursively.
	 * 
	 * @param state
	 *            The state where the computation happens.
	 * @param pid
	 *            The ID of the process that the expression belongs to.
	 * @param expression
	 *            The expression whose impact memory units are to be computed.
	 * @param memoryUnits
	 *            The set of memory units reachable by the expression.
	 * @throws UnsatisfiablePathConditionException
	 */
	boolean memoryUnitsOfExpression(State state, int pid,
			Expression expression, Set<SymbolicExpression> memoryUnits)
			throws UnsatisfiablePathConditionException;

	/**
	 * Compute reachable memory units by referencing a variable at a certain
	 * state. For example, given int x = 9; int* y = &x; int *z = y; Then the
	 * reachable memory units from z is {&z, &y, &x}.
	 * 
	 * @param variableValue
	 *            The value of the variable.
	 * @param dyScopeID
	 *            The dynamic scope id of the variable.
	 * @param vid
	 *            The id of the variable in the scope.
	 * @param state
	 *            The state where the computation happens.
	 * @return The set of memory units that reachable from the given variable.
	 */
	Set<SymbolicExpression> memoryUnitsOfVariable(
			SymbolicExpression variableValue, int dyScopeID, int vid,
			State state);

	/**
	 * The model factory should be the unqiue one used in the system.
	 * 
	 * @return The model factory of the evaluator.
	 */
	ModelFactory modelFactory();

	/**
	 * Creates a pointer value by evaluating a left-hand-side expression in the
	 * given state.
	 * 
	 * @param state
	 *            a CIVL model state
	 * @param pid
	 *            the process ID of the process in which this evaluation is
	 *            taking place
	 * @param operand
	 *            the left hand side expression we are taking the address of
	 * @return the pointer value
	 * @throws UnsatisfiablePathConditionException
	 */
	Evaluation reference(State state, int pid, LHSExpression operand)
			throws UnsatisfiablePathConditionException;

	/**
	 * Returns the dynamic type pointed to by a pointer. Can be used even if the
	 * pointer can't be dereferenced (because it points off the end of an
	 * object, for example).
	 * 
	 * @param source
	 *            The source code element to be used in the error report.
	 * @param state
	 *            The state where the computation happens.
	 * @param pointer
	 *            The symbolic representation of the pointer whose type is to be
	 *            computed.
	 * @return The symbolic type that the given pointer is pointing to.
	 */
	SymbolicType referencedType(CIVLSource source, State state,
			SymbolicExpression pointer);

	/**
	 * The state factory should be the unique one used in the system.
	 * 
	 * @return The state factory of the evaluator.
	 */
	StateFactory stateFactory();

	/**
	 * The symbolic universe should be the unique one used in the system.
	 * 
	 * @return The symbolic universe of the evaluator.
	 */
	SymbolicUniverse universe();

	SymbolicExpression heapPointer(CIVLSource source, State state,
			SymbolicExpression scopeValue)
			throws UnsatisfiablePathConditionException;

	SymbolicExpression heapValue(CIVLSource source, State state,
			SymbolicExpression scopeValue)
			throws UnsatisfiablePathConditionException;

	void setEnabler(Enabler enabler);

	Pair<State, CIVLFunction> evaluateFunctionExpression(State state, int pid,
			Expression functionExpression)
			throws UnsatisfiablePathConditionException;

	Evaluation pointerAdd(State state, int pid, BinaryExpression expression,
			SymbolicExpression pointer, NumericExpression offset)
			throws UnsatisfiablePathConditionException;

	SymbolicUtility symbolicUtility();
}
