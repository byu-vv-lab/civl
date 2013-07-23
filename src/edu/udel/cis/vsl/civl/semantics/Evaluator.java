/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.log.ErrorLog;
import edu.udel.cis.vsl.civl.model.IF.expression.AddressOfExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.CastExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.DereferenceExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.DotExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression.ExpressionKind;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.RealLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ResultExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SelfExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SubscriptExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.type.ArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.PrimitiveType;
import edu.udel.cis.vsl.civl.model.IF.type.Type;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.number.real.RealNumberFactory;
import edu.udel.cis.vsl.sarl.util.SingletonSet;

/**
 * An evaluator is used to evaluate expressions.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Evaluator {

	// Fields...

	private SymbolicUniverse universe;

	private RealNumberFactory numberFactory = new RealNumberFactory();

	/**
	 * The process type is a tuple with one component which has integer type. It
	 * simply wraps a process ID number.
	 */
	private SymbolicTupleType processType;

	/**
	 * The scope type is a tuple with one component which has integer type. It
	 * simply wraps a scope ID number.
	 */
	private SymbolicTupleType scopeType;

	/**
	 * The pointer value is a triple <s,v,r> where s identifies the dynamic
	 * scope, v identifies the variable within that scope, and r identifies a
	 * point within that variable. The type of s is scopeType, which is just a
	 * tuple wrapping a single integer which is the dynamic scope ID number. The
	 * type of v is integer; it is the (static) variable ID number for the
	 * variable in its scope. The type of r is ReferenceExpression from SARL.
	 */
	private SymbolicTupleType pointerType;

	private ErrorLog log;

	// private String pidPrefix = "PID_";

	// private String scopePrefix = "Scope_";

	private IntObject zeroObj;

	private IntObject oneObj;

	private IntObject twoObj;

	private ReferenceExpression identityReference;

	// Constructors...

	/**
	 * An evaluator is used to evaluate expressions.
	 * 
	 * @param symbolicUniverse
	 *            The symbolic universe for the expressions.
	 */
	public Evaluator(SymbolicUniverse symbolicUniverse, ErrorLog log) {
		List<SymbolicType> scopeTypeList = new Vector<SymbolicType>();
		List<SymbolicType> pointerComponents = new Vector<SymbolicType>();
		List<SymbolicType> processTypeList = new Vector<SymbolicType>();

		this.universe = symbolicUniverse;
		processTypeList.add(symbolicUniverse.integerType());
		processType = symbolicUniverse.tupleType(
				symbolicUniverse.stringObject("process"), processTypeList);
		scopeTypeList.add(symbolicUniverse.integerType());
		scopeType = symbolicUniverse.tupleType(
				symbolicUniverse.stringObject("scope"), scopeTypeList);
		pointerComponents.add(scopeType);
		pointerComponents.add(symbolicUniverse.integerType());
		pointerComponents.add(symbolicUniverse.referenceType());
		pointerType = symbolicUniverse.tupleType(
				symbolicUniverse.stringObject("pointer"), pointerComponents);
		this.log = log;
		zeroObj = (IntObject) universe.canonic(universe.intObject(0));
		oneObj = (IntObject) universe.canonic(universe.intObject(1));
		twoObj = (IntObject) universe.canonic(universe.intObject(2));
		identityReference = universe.identityReference();

	}

	// Helper methods...

	private SymbolicType symbolicType(Type type) {
		SymbolicType result = null;
		if (type instanceof PrimitiveType) {
			switch (((PrimitiveType) type).primitiveType()) {
			case BOOL:
				result = universe.booleanType();
				break;
			case INT:
				result = universe.integerType();
				break;
			case REAL:
				result = universe.realType();
				break;
			case STRING:
				result = universe.arrayType(universe.characterType());
				break;
			default:
				throw new CIVLUnimplementedFeatureException(
						"Unsupported primitive type: " + type);
			}
		} else if (type instanceof ArrayType) {
			// what about extent?
			result = universe.arrayType(symbolicType(((ArrayType) type)
					.baseType()));
		}
		// what about record types? Where is this used?
		return result;
	}

	/**
	 * Gets a Java conrete int from a symbolic expression or throws exception.
	 * 
	 * @param expression
	 *            a numeric expression expected to hold concrete int value
	 * @return the concrete int
	 * @throws CIVLInternalException
	 *             if a concrete integer value cannot be extracted
	 */
	private int extractInt(NumericExpression expression) {
		IntegerNumber result = (IntegerNumber) universe
				.extractNumber(expression);

		if (result == null)
			throw new CIVLInternalException(
					"Unable to extract concrete int from " + expression,
					(Source) null);
		return result.intValue();
	}

	/**
	 * Gets a concrete Java int from the field of a symbolic expression of tuple
	 * type or throws exception.
	 * 
	 * @param tuple
	 *            symbolic expression of tuple type
	 * @param fieldIndex
	 *            index of a field in that tuple
	 * @return the concrete int value of that field
	 * @throws CIVLInternalException
	 *             if a concrete integer value cannot be extracted
	 */
	private int extractIntField(SymbolicExpression tuple, IntObject fieldIndex) {
		NumericExpression field = (NumericExpression) universe.tupleRead(tuple,
				fieldIndex);

		return extractInt(field);
	}

	/**
	 * Given a dynamic scope ID number, returns the scope value ("scopeVal")
	 * which is a symbolic expression wrapping that int in a tuple of type
	 * scopeType.
	 * 
	 * @param sid
	 *            a nonnegative integer
	 * @return symbolic expression of type scopeType wrapping sid
	 */
	private SymbolicExpression makeScopeVal(int sid) {
		return universe.tuple(scopeType, new SingletonSet<SymbolicExpression>(
				universe.integer(sid)));
	}

	/**
	 * Given a dynamic scope value ("scopeVal"), extracts the concrete integer
	 * scope ID number and returns it.
	 * 
	 * @param scopeVal
	 *            an expression created by method {@link #makeScopeVal}.
	 * @return the concrete integer scope ID wrapped by the scopeVal
	 */
	private int getSid(SymbolicExpression scopeVal) {
		return extractIntField(scopeVal, zeroObj);
	}

	/**
	 * Makes a pointer value from the given dynamic scope ID, variable ID, and
	 * symbolic reference value.
	 * 
	 * @param scopeId
	 *            ID number of a dynamic scope
	 * @param varId
	 *            ID number of a variable within that scope
	 * @param symRef
	 *            a symbolic reference to a point within the variable
	 * @return a pointer value as specified by the 3 componentss
	 */
	private SymbolicExpression makePointer(int scopeId, int varId,
			ReferenceExpression symRef) {
		SymbolicExpression scopeField = makeScopeVal(scopeId);
		SymbolicExpression varField = universe.integer(varId);
		SymbolicExpression result = universe.tuple(
				pointerType,
				Arrays.asList(new SymbolicExpression[] { scopeField, varField,
						symRef }));

		return result;
	}

	/**
	 * Given a pointer value, returns the dynamic scope ID component of that
	 * pointer value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the dynamic scope ID component of that pointer value
	 */
	public int getScopeId(SymbolicExpression pointer) {
		return getSid(universe.tupleRead(pointer, zeroObj));
	}

	/**
	 * Given a pointer value, returns the variable ID component of that value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the variable ID component of that value
	 */
	public int getVariableId(SymbolicExpression pointer) {
		return extractIntField(pointer, oneObj);
	}

	/**
	 * Given a pointer value, returns the symbolic reference component of that
	 * value. The "symRef" refers to a sub-structure of the variable pointed to.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the symRef component
	 */
	public ReferenceExpression getSymRef(SymbolicExpression pointer) {
		return (ReferenceExpression) universe.tupleRead(pointer, twoObj);
	}

	/**
	 * Returns the pointer value obtained by replacing the symRef component of
	 * the given pointer value with the given symRef.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @param symRef
	 *            a symbolic refererence expression
	 * @return the pointer obtained by modifying the given one by replacing its
	 *         symRef field with the given symRef
	 */
	private SymbolicExpression setSymRef(SymbolicExpression pointer,
			ReferenceExpression symRef) {
		return universe.tupleWrite(pointer, twoObj, symRef);
	}

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
	 */
	public SymbolicExpression reference(State state, int pid,
			LHSExpression operand) {
		SymbolicExpression result;

		if (operand instanceof VariableExpression) {
			Variable variable = ((VariableExpression) operand).variable();
			int sid = state.getScopeId(pid, variable);
			int vid = variable.vid();

			result = makePointer(sid, vid, identityReference);
		} else if (operand instanceof SubscriptExpression) {
			SymbolicExpression arrayPointer = reference(state, pid,
					((SubscriptExpression) operand).array());
			ReferenceExpression oldSymRef = getSymRef(arrayPointer);
			NumericExpression index = (NumericExpression) evaluate(state, pid,
					((SubscriptExpression) operand).index());
			ReferenceExpression newSymRef = universe.arrayElementReference(
					oldSymRef, index);

			result = setSymRef(arrayPointer, newSymRef);
		} else if (operand instanceof DereferenceExpression) {
			result = evaluate(state, pid,
					((DereferenceExpression) operand).pointer());
		} else if (operand instanceof DotExpression) {
			SymbolicExpression structPointer = reference(state, pid,
					(LHSExpression) ((DotExpression) operand).struct());
			ReferenceExpression oldSymRef = getSymRef(structPointer);
			int index = ((DotExpression) operand).fieldIndex();
			ReferenceExpression newSymRef = universe.tupleComponentReference(
					oldSymRef, universe.intObject(index));

			result = setSymRef(structPointer, newSymRef);
		} else
			throw new CIVLInternalException("Unknown kind of LHSExpression",
					operand);
		return result;
	}

	/**
	 * Given an expression of pointer type, evaluates that expression in the
	 * given state to get a pointer value, and then dereferences that to yield
	 * the value pointed to.
	 * 
	 * @param state
	 *            a CIVL model state
	 * @param pid
	 *            PID of the process in which this evaluation occurs
	 * @param operand
	 *            an expression of pointer type
	 * @return the referenced value
	 */
	private SymbolicExpression dereference(State state, int pid,
			Expression operand) {
		SymbolicExpression pointer = evaluate(state, pid, operand);

		return dereference(state, pointer);
	}

	private SymbolicExpression pointerAdd(State state, int pid,
			BinaryExpression expression, SymbolicExpression pointer,
			NumericExpression offset) {
		// TODO
		return null;
	}

	private SymbolicExpression pointerSubtract(State state, int pid,
			BinaryExpression expression, SymbolicExpression p1,
			SymbolicExpression p2) {
		// TODO
		return null;
	}

	// individual evaluation methods....

	private SymbolicExpression evaluateAddressOf(State state, int pid,
			AddressOfExpression expression) {
		return reference(state, pid, expression.operand());
	}

	private SymbolicExpression evaluateDereference(State state, int pid,
			DereferenceExpression expression) {
		return dereference(state, pid, expression.pointer());
	}

	/**
	 * Evaluates a conditional expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            the pid of the currently executing process.
	 * @param expression
	 *            The conditional expression.
	 * @return A symbolic expression for the result of the conditional.
	 */
	private SymbolicExpression evaluateCond(State state, int pid,
			ConditionalExpression expression) {
		SymbolicExpression condition = evaluate(state, pid,
				expression.getCondition());
		SymbolicExpression trueBranch = evaluate(state, pid,
				expression.getTrueBranch());
		SymbolicExpression falseBranch = evaluate(state, pid,
				expression.getFalseBranch());

		assert condition instanceof BooleanExpression;
		return universe.cond((BooleanExpression) condition, trueBranch,
				falseBranch);
	}

	/**
	 * Evaluate a reference to a struct field.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The dot expression.
	 * @return The symbolic expression for reading the struct field.
	 */
	private SymbolicExpression evaluateDot(State state, int pid,
			DotExpression expression) {
		SymbolicExpression structValue = evaluate(state, pid,
				expression.struct());
		int fieldIndex = expression.fieldIndex();
		SymbolicExpression result = universe.tupleRead(structValue,
				universe.intObject(fieldIndex));

		return result;
	}

	/**
	 * Evaluate a subscript expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The array index expression.
	 * @return A symbolic expression for an array read.
	 */
	private SymbolicExpression evaluateSubscript(State state, int pid,
			SubscriptExpression expression) {
		SymbolicExpression array = evaluate(state, pid, expression.array());
		NumericExpression index = (NumericExpression) evaluate(state, pid,
				expression.index());

		return universe.arrayRead(array, index);
	}

	/**
	 * Evaluate a binary expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The binary expression.
	 * @return A symbolic expression for the binary operation.
	 */
	private SymbolicExpression evaluateBinary(State state, int pid,
			BinaryExpression expression) {
		SymbolicExpression left = evaluate(state, pid, expression.left());
		SymbolicExpression right = evaluate(state, pid, expression.right());

		// TODO: Check all expression types.
		switch (expression.operator()) {
		case PLUS:
			return universe.add((NumericExpression) left,
					(NumericExpression) right);
		case MINUS:
			return universe.subtract((NumericExpression) left,
					(NumericExpression) right);
		case TIMES:
			return universe.multiply((NumericExpression) left,
					(NumericExpression) right);
		case DIVIDE:
			return universe.divide((NumericExpression) left,
					(NumericExpression) right);
		case LESS_THAN:
			return universe.lessThan((NumericExpression) left,
					(NumericExpression) right);
		case LESS_THAN_EQUAL:
			return universe.lessThanEquals((NumericExpression) left,
					(NumericExpression) right);
		case EQUAL:
			return universe.equals(left, right);
		case NOT_EQUAL:
			return universe.not(universe.equals(left, right));
		case AND:
			return universe.and((BooleanExpression) left,
					(BooleanExpression) right);
		case OR:
			return universe.or((BooleanExpression) left,
					(BooleanExpression) right);
		case MODULO:
			return universe.modulo((NumericExpression) left,
					(NumericExpression) right);
		case POINTER_ADD:
			return pointerAdd(state, pid, expression, left,
					(NumericExpression) right);
		case POINTER_SUBTRACT:
			return pointerSubtract(state, pid, expression, left, right);
		default:
			throw new CIVLUnimplementedFeatureException("Operator "
					+ expression.operator(), expression.getSource());
		}
	}

	/**
	 * Evaluate a boolean literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The boolean literal expression.
	 * @return The symbolic representation of the boolean literal expression.
	 */
	private SymbolicExpression evaluateBooleanLiteral(State state, int pid,
			BooleanLiteralExpression expression) {
		return universe.bool(expression.value());
	}

	/**
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The cast expression.
	 * @return The symbolic representation of the cast expression.
	 */
	private SymbolicExpression evaluateCast(State state, int pid,
			CastExpression expression) {
		SymbolicExpression uncastExpression = evaluate(state, pid,
				expression.getExpression());
		Type castType = expression.getCastType();
		SymbolicType symbolicType = symbolicType(castType);
		SymbolicExpression result;

		if (castType == null)
			throw new CIVLInternalException("Null cast type", expression);
		result = universe.cast(symbolicType, uncastExpression);
		return result;
	}

	/**
	 * Evalute an integer literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The integer literal expression.
	 * @return The symbolic representation of the integer literal expression.
	 */
	private SymbolicExpression evaluateIntegerLiteral(State state, int pid,
			IntegerLiteralExpression expression) {
		return universe.integer(expression.value().intValue());
	}

	private SymbolicExpression evaluateSelf(State state, int pid,
			SelfExpression expression) {
		return makeProcVal(pid);
	}

	/**
	 * Evaluate a real literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The real literal expression.
	 * @return The symbolic representation of the real literal expression.
	 */
	private SymbolicExpression evaluateRealLiteral(State state, int pid,
			RealLiteralExpression expression) {
		return universe.number(universe.numberObject(numberFactory
				.rational(expression.value().toPlainString())));
	}

	/**
	 * Evaluate a string literal expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The string literal expression.
	 * @return The symbolic representation of the string literal expression.
	 */
	private SymbolicExpression evaluateStringLiteral(State state, int pid,
			StringLiteralExpression expression) {
		return universe.stringExpression(expression.value());
	}

	/**
	 * Evaluate a unary expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The unary expression.
	 * @return The symbolic representation of the unary expression.
	 */
	private SymbolicExpression evaluateUnary(State state, int pid,
			UnaryExpression expression) {
		switch (expression.operator()) {
		case NEGATIVE:
			return universe.minus((NumericExpression) evaluate(state, pid,
					expression.operand()));
		case NOT:
			return universe.not((BooleanExpression) evaluate(state, pid,
					expression.operand()));
			// case ADDRESSOF:
			// return reference(state, pid, expression.operand());
			// case DEREFERENCE:
			// return dereference(state, pid, expression.operand());
		default:
			throw new CIVLInternalException("Unknown unary operator "
					+ expression.operator(), expression);
		}
	}

	/**
	 * Evaluate a variable expression.
	 * 
	 * @param state
	 *            The state of the program.
	 * @param pid
	 *            The pid of the currently executing process.
	 * @param expression
	 *            The variable expression.
	 * @return
	 */
	private SymbolicExpression evaluateVariable(State state, int pid,
			VariableExpression expression) {
		SymbolicExpression currentValue = state.valueOf(pid,
				expression.variable());

		if (currentValue == null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);

			state.print(ps);
			log.report(new CIVLStateException(ErrorKind.UNDEFINED_VALUE,
					Certainty.PROVEABLE,
					"Attempt to read unitialized variable", state, expression
							.getSource()));
			return universe.nullExpression();
		}
		return universe.reasoner((BooleanExpression) state.pathCondition())
				.simplify(currentValue);
	}

	private SymbolicExpression evaluateResult(State state, int pid,
			ResultExpression expression) {
		// TODO
		// this is used in a contract post-condition as a variable to
		// refer to the result returned by a function. $result.
		// get rid of ResultExpression and instead create a variable
		// in the outermost scope of any function with non-void
		// return type, store the result of return in that variable.
		// Add method in Function to get that variable. (and set it?)
		// Model builder will translate $result to that variable.
		throw new CIVLUnimplementedFeatureException(
				"$result not yet implemented: " + expression.getSource());
	}

	// Exported methods...

	/**
	 * Returns the log used by this evaluator to record an property violations
	 * encountered.
	 * 
	 * @return the error log
	 */
	public ErrorLog log() {
		return log;
	}

	/**
	 * Returns the pointer type: the type of the symbolic expressions used to
	 * represent pointer values.
	 * 
	 * @return the pointer type
	 */
	public SymbolicType pointerType() {
		return pointerType;
	}

	/**
	 * Returns the process type: the type of the symbolic expressions used as
	 * values assigned to variables of type <code>$proc</code>.
	 * 
	 * @return the process type
	 */
	public SymbolicType processType() {
		return processType;
	}

	/**
	 * Given a process ID number, returns the process value ("procVal") which is
	 * a symbolic expression wrapping that int in a tuple of type
	 * <code>processType.</code>
	 * 
	 * @param pid
	 *            a nonnegative integer
	 * @return symbolic expression of type processType wrapping pid
	 */
	public SymbolicExpression makeProcVal(int pid) {
		return universe.tuple(processType,
				new SingletonSet<SymbolicExpression>(universe.integer(pid)));
	}

	/**
	 * Given a process value (aka "procVal", a symbolic expression of process
	 * type), extracts and returns the concrete integer PID.
	 * 
	 * @param procVal
	 *            an expression created by method {@link #makeProcVal}.
	 * @return the concrete integer PID wrapped by the procVal
	 */
	public int getPid(SymbolicExpression procVal) {
		return extractIntField(procVal, zeroObj);
	}

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
	 */
	public SymbolicExpression dereference(State state,
			SymbolicExpression pointer) {
		int sid = getScopeId(pointer);
		int vid = getVariableId(pointer);
		ReferenceExpression symRef = getSymRef(pointer);
		SymbolicExpression variableValue = state.getScope(sid).getValue(vid);
		SymbolicExpression result = universe.dereference(variableValue, symRef);

		return result;
	}

	/**
	 * Evaluates the expression and returns the result, which is a symbolic
	 * expression value.
	 * 
	 * @param state
	 *            the state in which the evaluation takes place
	 * @param pid
	 *            the PID of the process which is evaluating the expression
	 * @param expression
	 *            the (static) expression being evaluated
	 * @return the result of the evaluation
	 */
	public SymbolicExpression evaluate(State state, int pid,
			Expression expression) {
		ExpressionKind kind = expression.expressionKind();
		SymbolicExpression result;

		switch (kind) {
		case ADDRESS_OF:
			result = evaluateAddressOf(state, pid,
					(AddressOfExpression) expression);
			break;
		case BINARY:
			result = evaluateBinary(state, pid, (BinaryExpression) expression);
			break;
		case BOOLEAN_LITERAL:
			result = evaluateBooleanLiteral(state, pid,
					(BooleanLiteralExpression) expression);
			break;
		case CAST:
			result = evaluateCast(state, pid, (CastExpression) expression);
			break;
		case COND:
			result = evaluateCond(state, pid,
					(ConditionalExpression) expression);
			break;
		case DEREFERENCE:
			result = evaluateDereference(state, pid,
					(DereferenceExpression) expression);
			break;
		case DOT:
			result = evaluateDot(state, pid, (DotExpression) expression);
			break;
		case INTEGER_LITERAL:
			result = evaluateIntegerLiteral(state, pid,
					(IntegerLiteralExpression) expression);
			break;
		case REAL_LITERAL:
			result = evaluateRealLiteral(state, pid,
					(RealLiteralExpression) expression);
			break;
		case RESULT:
			result = evaluateResult(state, pid, (ResultExpression) expression);
			break;
		case SELF:
			result = evaluateSelf(state, pid, (SelfExpression) expression);
			break;
		case STRING_LITERAL:
			result = evaluateStringLiteral(state, pid,
					(StringLiteralExpression) expression);
			break;
		case SUBSCRIPT:
			result = evaluateSubscript(state, pid,
					(SubscriptExpression) expression);
			break;
		case UNARY:
			result = evaluateUnary(state, pid, (UnaryExpression) expression);
			break;
		case VARIABLE:
			result = evaluateVariable(state, pid,
					(VariableExpression) expression);
			break;
		default:
			throw new CIVLInternalException("Unknown kind of expression: "
					+ kind, expression.getSource());
		}
		// make canonic?
		return result;
	}

}
