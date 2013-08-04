/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import edu.udel.cis.vsl.civl.err.CIVLExecutionException;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.log.ErrorLog;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.AddressOfExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.BooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.CastExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.DereferenceExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.DotExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.DynamicTypeOfExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression.ExpressionKind;
import edu.udel.cis.vsl.civl.model.IF.expression.InitialValueExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.RealLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ResultExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SelfExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.StringLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SubscriptExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLCompleteArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType.PrimitiveTypeKind;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.type.StructField;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.civl.util.Singleton;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NTReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.OffsetReference;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.number.real.RealNumberFactory;

/**
 * An evaluator is used to evaluate expressions.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Evaluator {

	// Fields..............................................................

	private ModelFactory modelFactory;

	private StateFactoryIF stateFactory;

	private SymbolicUniverse universe;

	private RealNumberFactory numberFactory = new RealNumberFactory();

	/**
	 * The process type is a tuple with one component which has integer type. It
	 * simply wraps a process ID number.
	 */
	private SymbolicTupleType processType;

	/**
	 * Map from symbolic type to a canonic symbolic expression of that type.
	 */
	private Map<SymbolicType, SymbolicExpression> typeExpressionMap = new HashMap<SymbolicType, SymbolicExpression>();

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

	private SymbolicTupleType dynamicType;

	private SymbolicExpression nullPointer;

	private ErrorLog log;

	private IntObject zeroObj;

	private IntObject oneObj;

	private IntObject twoObj;

	private NumericExpression zero;

	private NumericExpression one;

	private NumericExpression zeroR;

	private ReferenceExpression identityReference;

	// /**
	// * Name used for symbolic constants used for encapsulating symbolic types.
	// * There will be one symbolic constant for each type, but all of these
	// * symbolic constants will have the same name.
	// */
	// private StringObject typeName;

	// Constructors........................................................

	/**
	 * An evaluator is used to evaluate expressions.
	 * 
	 * @param symbolicUniverse
	 *            The symbolic universe for the expressions.
	 */
	public Evaluator(ModelFactory modelFactory, StateFactoryIF stateFactory,
			ErrorLog log) {
		// List<SymbolicType> scopeTypeList = new Vector<SymbolicType>();
		List<SymbolicType> pointerComponents = new Vector<SymbolicType>();
		// List<SymbolicType> processTypeList = new Vector<SymbolicType>();

		this.modelFactory = modelFactory;
		this.stateFactory = stateFactory;
		this.universe = stateFactory.symbolicUniverse();
		// processTypeList.add(universe.integerType());
		processType = (SymbolicTupleType) modelFactory.processType()
				.getSymbolicType();
		// processType = (SymbolicTupleType)
		// universe.canonic(universe.tupleType(
		// universe.stringObject("process"), processTypeList));
		// scopeTypeList.add(universe.integerType());
		// can get this from model factory...
		scopeType = (SymbolicTupleType) modelFactory.scopeType()
				.getSymbolicType();
		// scopeType = (SymbolicTupleType) universe.canonic(universe.tupleType(
		// universe.stringObject("scope"), scopeTypeList));
		dynamicType = (SymbolicTupleType) modelFactory.dynamicType()
				.getSymbolicType();
		pointerComponents.add(scopeType);
		pointerComponents.add(universe.integerType());
		pointerComponents.add(universe.referenceType());
		pointerType = (SymbolicTupleType) universe.canonic(universe.tupleType(
				universe.stringObject("pointer"), pointerComponents));
		this.log = log;
		zeroObj = (IntObject) universe.canonic(universe.intObject(0));
		oneObj = (IntObject) universe.canonic(universe.intObject(1));
		twoObj = (IntObject) universe.canonic(universe.intObject(2));
		identityReference = (ReferenceExpression) universe.canonic(universe
				.identityReference());
		zero = (NumericExpression) universe.canonic(universe.integer(0));
		zeroR = (NumericExpression) universe.canonic(universe.zeroReal());
		one = (NumericExpression) universe.canonic(universe.integer(1));
		nullPointer = universe.canonic(makePointer(-1, -1,
				universe.nullReference()));
		// typeName = universe.stringObject("TYPE");
	}

	// Helper methods......................................................

	private NumericExpression zeroOf(CIVLSource source, CIVLType type) {
		if (type instanceof CIVLPrimitiveType) {
			if (((CIVLPrimitiveType) type).primitiveTypeKind() == PrimitiveTypeKind.INT)
				return zero;
			if (((CIVLPrimitiveType) type).primitiveTypeKind() == PrimitiveTypeKind.REAL)
				return zeroR;
		}
		throw new CIVLInternalException("Expected integer or real type, not "
				+ type, source);
	}

	private Certainty certaintyOf(CIVLSource source, ResultType resultType) {
		if (resultType == ResultType.NO)
			return Certainty.PROVEABLE;
		if (resultType == ResultType.MAYBE)
			return Certainty.MAYBE;
		throw new CIVLInternalException(
				"This method should only be called with result type of NO or MAYBE",
				source);
	}

	// private Certainty certaintyOf(ValidityResult result) {
	// return certaintyOf(result.getResultType());
	// }

	private SymbolicType symbolicType(CIVLSource source, CIVLType type) {
		SymbolicType result;

		if (type instanceof CIVLPrimitiveType) {
			switch (((CIVLPrimitiveType) type).primitiveTypeKind()) {
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
		} else if (type instanceof CIVLArrayType) {
			// what about extent?
			result = universe.arrayType(symbolicType(source,
					((CIVLArrayType) type).elementType()));
		} else if (type instanceof CIVLPointerType) {
			result = pointerType;
		} else
			throw new CIVLInternalException("Cannot find symbolic type for "
					+ type, source);
		// TODO: what about record types?
		// So far, this is only used in evaluation of casts.
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
	private int extractInt(CIVLSource source, NumericExpression expression) {
		IntegerNumber result = (IntegerNumber) universe
				.extractNumber(expression);

		if (result == null)
			throw new CIVLInternalException(
					"Unable to extract concrete int from " + expression, source);
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
	private int extractIntField(CIVLSource source, SymbolicExpression tuple,
			IntObject fieldIndex) {
		NumericExpression field = (NumericExpression) universe.tupleRead(tuple,
				fieldIndex);

		return extractInt(source, field);
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
		return universe.tuple(scopeType, new Singleton<SymbolicExpression>(
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
	private int getSid(CIVLSource source, SymbolicExpression scopeVal) {
		return extractIntField(source, scopeVal, zeroObj);
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
	 * Tells whether the given symbolic expression is a pointer value.
	 * 
	 * @param pointer
	 *            any symbolic expression
	 * @return true iff the expression is a pointer value
	 */
	private boolean isPointer(SymbolicExpression pointer) {
		return pointer.type() == pointerType;
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
	 * Given a non-trivial pointer, i.e., a pointer to some location inside an
	 * object, returns the parent pointer. For example, a pointer to an array
	 * element returns the pointer to the array.
	 * 
	 * @param pointer
	 *            non-trivial pointer
	 * @return pointer to parent
	 * @throws CIVLInternalException
	 *             if pointer is trivial
	 */
	private SymbolicExpression parentPointer(CIVLSource source,
			SymbolicExpression pointer) {
		ReferenceExpression symRef = getSymRef(pointer);

		if (symRef instanceof NTReferenceExpression)
			return setSymRef(pointer,
					((NTReferenceExpression) symRef).getParent());
		throw new CIVLInternalException("Expected non-trivial pointer: "
				+ pointer, source);
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
	private Evaluation dereference(State state, int pid, Expression operand) {
		Evaluation eval = evaluate(state, pid, operand);

		return dereference(operand.getSource(), eval.state, eval.value);
	}

	/**
	 * Evaluates pointer addition. Pointer addition involves the addition of a
	 * pointer expression and an integer.
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            the PID of the process evaluating the pointer addition
	 * @param expression
	 *            the pointer addition expression
	 * @param pointer
	 *            the result of evaluating argument 0 of expression
	 * @param offset
	 *            the result of evaluating argument 1 of expression
	 * @return the result of evaluating the sum of the pointer and the integer
	 */

	// TODO: need to know if the type of the array is complete or
	// incomplete.
	// Need the type of the array. isn't this in the symbolic array
	// type?

	private Evaluation pointerAdd(State state, int pid,
			BinaryExpression expression, SymbolicExpression pointer,
			NumericExpression offset) {
		ReferenceExpression symRef = getSymRef(pointer);

		if (symRef.isArrayElementReference()) {
			SymbolicExpression arrayPointer = parentPointer(
					expression.getSource(), pointer);
			Evaluation eval = dereference(expression.getSource(), state,
					arrayPointer);
			// eval.value is now a symbolic expression of array type.
			SymbolicArrayType arrayType = (SymbolicArrayType) eval.value.type();
			ArrayElementReference arrayElementRef = (ArrayElementReference) symRef;
			NumericExpression oldIndex = arrayElementRef.getIndex();
			NumericExpression newIndex = universe.add(oldIndex, offset);

			if (arrayType.isComplete()) { // check bounds
				NumericExpression length = universe.length(eval.value);
				BooleanExpression claim = universe.and(
						universe.lessThanEquals(zero, newIndex),
						universe.lessThanEquals(newIndex, length));
				BooleanExpression assumption = eval.state.pathCondition();
				ResultType resultType = universe.reasoner(assumption)
						.valid(claim).getResultType();

				if (resultType != ResultType.YES) {
					CIVLStateException e = new CIVLStateException(
							ErrorKind.OUT_OF_BOUNDS, certaintyOf(
									expression.getSource(), resultType),
							"Pointer addition resulted in out of bounds array index:\nindex = "
									+ newIndex + "\nlength = " + length,
							eval.state, expression.getSource());
					log.report(e);
					eval.state = stateFactory.setPathCondition(eval.state,
							universe.and(assumption, claim));
				}
			}
			eval.value = setSymRef(pointer, universe.arrayElementReference(
					arrayElementRef.getParent(), newIndex));
			return eval;
		} else if (symRef.isOffsetReference()) {
			OffsetReference offsetRef = (OffsetReference) symRef;
			NumericExpression oldOffset = offsetRef.getOffset();
			NumericExpression newOffset = universe.add(oldOffset, offset);
			BooleanExpression claim = universe.and(
					universe.lessThanEquals(zero, newOffset),
					universe.lessThanEquals(newOffset, one));
			BooleanExpression assumption = state.pathCondition();
			ResultType resultType = universe.reasoner(assumption).valid(claim)
					.getResultType();
			Evaluation eval;

			if (resultType != ResultType.YES) {
				CIVLStateException e = new CIVLStateException(
						ErrorKind.OUT_OF_BOUNDS, certaintyOf(
								expression.getSource(), resultType),
						"Pointer addition resulted in out of bounds object pointer:\noffset = "
								+ newOffset, state, expression.getSource());
				log.report(e);
				state = stateFactory.setPathCondition(state,
						universe.and(assumption, claim));
			}
			eval = new Evaluation(state, setSymRef(pointer,
					universe.offsetReference(offsetRef.getParent(), newOffset)));
			return eval;
		} else
			throw new CIVLUnimplementedFeatureException(
					"Pointer addition for anything other than array elements or variables",
					expression);
	}

	/**
	 * Evaluates pointer subtraction.
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            the PID of the process performing this evaluation
	 * @param expression
	 *            the pointer subtraction expression
	 * @param p1
	 *            the result of evaluating argument 0 of expression; should be a
	 *            pointer
	 * @param p2
	 *            the result of evaluating argument 1 of expression; should be a
	 *            pointer
	 * @return the integer symbolic expression resulting from subtracting the
	 *         two pointers together with the post-state if side-effects
	 *         occurred
	 */
	private Evaluation pointerSubtract(State state, int pid,
			BinaryExpression expression, SymbolicExpression p1,
			SymbolicExpression p2) {
		throw new CIVLUnimplementedFeatureException("pointer subtraction",
				expression);
	}

	/**
	 * Evaluates an address-of expression "&e".
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of the process performing the evaluation
	 * @param expression
	 *            the address-of expression
	 * @return the symbolic expression of pointer type resulting from evaluating
	 *         the address of the argument
	 */
	private Evaluation evaluateAddressOf(State state, int pid,
			AddressOfExpression expression) {
		return reference(state, pid, expression.operand());
	}

	/**
	 * Evaluates a dereference expression "*e".
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of the process performing the evaluation
	 * @param expression
	 *            the dereference expression
	 * @return the symbolic expression value that result from dereferencing the
	 *         pointer value argument
	 */
	private Evaluation evaluateDereference(State state, int pid,
			DereferenceExpression expression) {
		return dereference(state, pid, expression.pointer());
	}

	/**
	 * <p>
	 * General method for evaluating "short-circuited" conditional expressions
	 * that may involve logged side-effects on the path condition. These include
	 * expressions of the form <code>c?t:f</code>, <code>p&&q</code>, and
	 * <code>p||q</code>. The latter two are a special case of the first:
	 * <ul>
	 * <li><code>p&&q</code> is equivalent to <code>p?q:false</code></li>
	 * <li><code>p||q</code> is equivalent to <code>p?true:q</code></li>
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Say the path condition is <code>p</code> and the expression is
	 * <code>(c?t:f)</code>.
	 * </p>
	 * 
	 * <p>
	 * If <code>c</code> is valid (assuming <code>p</code>), the result is just
	 * the result of evaluating <code>t</code>. If <code>!c</code> is valid, the
	 * result is just the result of evaluating <code>f</code>. The subtle case
	 * is where neither of those is valid, in which case, proceed as follows:
	 * </p>
	 * 
	 * <p>
	 * When evaluating <code>t</code>, assume <code>c</code> holds. When
	 * evaluating <code>f</code>, assume <code>!c</code> holds. Say
	 * <code>eval(p&&c, t)</code> results in <code>(p1,v1)</code> and
	 * <code>eval(p&&!c,f)</code> results in <code>(p2,v2)</code>. Then return
	 * <code>(p1||p2, (c?v1:v2))</code>.
	 * </p>
	 * 
	 * <p>
	 * Example: <code>x==0 ? 1/w + y/(1-x) : 1/z + y/x</code>, <code>p</code>=
	 * <code>true</code>. <code>eval(p&&c, t)</code> yields
	 * <code>(x==0 && w!=0, 1/w+y/(1-x))</code> together with a logged warning
	 * that <code>w!=0</code> has been assumed. <code>eval(p&&!c,f)</code>
	 * yields <code>(x!=0 && z!=0, 1/z+y/x)</code> together with a logged
	 * warning that <code>z!=0</code> has been assumed. The resulting path
	 * condition is <code>(x==0 && w!=0) || (x!=0 && z!=0)</code>.
	 * </p>
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of process evaluating this expression
	 * @param condition
	 *            the boolean conditional expression <code>c</code>
	 * @param trueBranch
	 *            the sub-expression which becomes the value if <code>c</code>
	 *            evaluates to <code>true</code>
	 * @param falseBranch
	 *            the sub-expression which becomes the value if <code>c</code>
	 *            evaluates to <code>false</code>
	 * @return the evaluation with the properly updated state and the
	 *         conditional value
	 */
	private Evaluation evaluateConditional(State state, int pid,
			Expression condition, Expression trueBranch, Expression falseBranch) {
		Evaluation eval = evaluate(state, pid, condition);
		BooleanExpression c = (BooleanExpression) eval.value;
		BooleanExpression assumption = eval.state.pathCondition();
		Reasoner reasoner = universe.reasoner(assumption);

		if (reasoner.isValid(c))
			return evaluate(eval.state, pid, trueBranch);
		if (reasoner.isValid(universe.not(c)))
			return evaluate(eval.state, pid, falseBranch);
		else {
			State s1 = stateFactory.setPathCondition(eval.state,
					universe.and(assumption, c));
			State s2 = stateFactory.setPathCondition(eval.state,
					universe.and(assumption, universe.not(c)));
			Evaluation eval1 = evaluate(s1, pid, trueBranch);
			Evaluation eval2 = evaluate(s2, pid, falseBranch);

			eval.state = stateFactory.setPathCondition(
					eval.state,
					universe.or(eval1.state.pathCondition(),
							eval2.state.pathCondition()));
			eval.value = universe.cond(c, eval1.value, eval2.value);
			return eval;
		}
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
	private Evaluation evaluateCond(State state, int pid,
			ConditionalExpression expression) {
		return evaluateConditional(state, pid, expression.getCondition(),
				expression.getTrueBranch(), expression.getFalseBranch());
	}

	/**
	 * Evaluates a short-circuit "and" expression "p&&q".
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of the process evaluating this expression
	 * @param expression
	 *            the and expression
	 * @return the result of applying the AND operator to the two arguments
	 *         together with the post-state whose path condition may contain the
	 *         side-effects resulting from evaluation
	 */
	private Evaluation evaluateAnd(State state, int pid,
			BinaryExpression expression) {
		Evaluation eval = evaluate(state, pid, expression.left());
		BooleanExpression p = (BooleanExpression) eval.value;
		BooleanExpression assumption = eval.state.pathCondition();
		Reasoner reasoner = universe.reasoner(assumption);

		if (reasoner.isValid(p))
			return evaluate(eval.state, pid, expression.right());
		if (reasoner.isValid(universe.not(p))) {
			eval.value = universe.falseExpression();
			return eval;
		} else {
			State s1 = stateFactory.setPathCondition(eval.state,
					universe.and(assumption, p));
			Evaluation eval1 = evaluate(s1, pid, expression.right());
			BooleanExpression pc = universe.or(eval1.state.pathCondition(),
					universe.and(assumption, universe.not(p)));

			eval.state = stateFactory.setPathCondition(eval.state, pc);
			eval.value = universe.and(p, (BooleanExpression) eval1.value);
			return eval;
		}
	}

	/**
	 * Evaluates a short-circuit "or" expression "p||q".
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of the process evaluating this expression
	 * @param expression
	 *            the OR expression
	 * @return the result of applying the OR operator to the two arguments
	 *         together with the post-state whose path condition may contain the
	 *         side-effects resulting from evaluation
	 */
	private Evaluation evaluateOr(State state, int pid,
			BinaryExpression expression) {
		Evaluation eval = evaluate(state, pid, expression.left());
		BooleanExpression p = (BooleanExpression) eval.value;
		BooleanExpression assumption = eval.state.pathCondition();
		Reasoner reasoner = universe.reasoner(assumption);

		if (reasoner.isValid(p)) {
			eval.value = universe.trueExpression();
			return eval;
		}
		if (reasoner.isValid(universe.not(p))) {
			return evaluate(eval.state, pid, expression.right());
		} else {
			State s1 = stateFactory.setPathCondition(eval.state,
					universe.and(assumption, universe.not(p)));
			Evaluation eval1 = evaluate(s1, pid, expression.right());
			BooleanExpression pc = universe.or(eval1.state.pathCondition(),
					universe.and(assumption, p));

			eval.state = stateFactory.setPathCondition(eval.state, pc);
			eval.value = universe.or(p, (BooleanExpression) eval1.value);
			return eval;
		}
	}

	/**
	 * Evaluate a "dot" expression used to navigate to a field in a record,
	 * "e.f".
	 * 
	 * @param state
	 *            The state of the model
	 * @param pid
	 *            The pid of the process evaluating this expression
	 * @param expression
	 *            The dot expression
	 * @return The symbolic expression resulting from evaluating the expression
	 *         together with the post-state which may incorporate side-effects
	 *         resulting from the evaluation
	 */
	private Evaluation evaluateDot(State state, int pid,
			DotExpression expression) {
		Evaluation eval = evaluate(state, pid, expression.struct());
		SymbolicExpression structValue = eval.value;
		int fieldIndex = expression.fieldIndex();

		eval.value = universe.tupleRead(structValue,
				universe.intObject(fieldIndex));
		return eval;
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
	private Evaluation evaluateSubscript(State state, int pid,
			SubscriptExpression expression) {
		Evaluation eval = evaluate(state, pid, expression.array());
		SymbolicExpression array = eval.value;
		SymbolicArrayType arrayType = (SymbolicArrayType) array.type();
		NumericExpression index;

		eval = evaluate(state, pid, expression.index());
		index = (NumericExpression) eval.value;
		if (arrayType.isComplete()) {
			NumericExpression length = universe.length(array);
			BooleanExpression assumption = eval.state.pathCondition();
			BooleanExpression claim = universe.and(
					universe.lessThanEquals(zero, index),
					universe.lessThan(index, length));
			ResultType resultType = universe.reasoner(assumption).valid(claim)
					.getResultType();

			if (resultType != ResultType.YES) {
				CIVLStateException e = new CIVLStateException(
						ErrorKind.OUT_OF_BOUNDS, certaintyOf(
								expression.getSource(), resultType),
						"Out of bounds array index:\nindex = " + index
								+ "\nlength = " + length, eval.state,
						expression.getSource());

				log.report(e);
				eval.state = stateFactory.setPathCondition(state,
						universe.and(assumption, claim));
			}
		}
		eval.value = universe.arrayRead(array, index);
		return eval;
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
	private Evaluation evaluateBinary(State state, int pid,
			BinaryExpression expression) {
		BINARY_OPERATOR operator = expression.operator();

		if (operator == BINARY_OPERATOR.AND)
			return evaluateAnd(state, pid, expression);
		if (operator == BINARY_OPERATOR.OR)
			return evaluateOr(state, pid, expression);
		else {
			Evaluation eval = evaluate(state, pid, expression.left());
			SymbolicExpression left = eval.value;
			SymbolicExpression right;

			eval = evaluate(state, pid, expression.right());
			right = eval.value;
			switch (expression.operator()) {
			case PLUS:
				eval.value = universe.add((NumericExpression) left,
						(NumericExpression) right);
				break;
			case MINUS:
				eval.value = universe.subtract((NumericExpression) left,
						(NumericExpression) right);
				break;
			case TIMES:
				eval.value = universe.multiply((NumericExpression) left,
						(NumericExpression) right);
				break;
			case DIVIDE: {
				BooleanExpression assumption = eval.state.pathCondition();
				NumericExpression denominator = (NumericExpression) right;
				BooleanExpression claim = universe.neq(
						zeroOf(expression.getSource(),
								expression.getExpressionType()), denominator);
				ResultType resultType = universe.reasoner(assumption)
						.valid(claim).getResultType();

				if (resultType != ResultType.YES) {
					CIVLExecutionException e = new CIVLStateException(
							ErrorKind.DIVISION_BY_ZERO, certaintyOf(
									expression.getSource(), resultType),
							"Division by zero", eval.state,
							expression.getSource());

					log.report(e);
					eval.state = stateFactory.setPathCondition(eval.state,
							universe.and(assumption, claim));
				}
				eval.value = universe.divide((NumericExpression) left,
						denominator);
				break;
			}
			case LESS_THAN:
				eval.value = universe.lessThan((NumericExpression) left,
						(NumericExpression) right);
				break;
			case LESS_THAN_EQUAL:
				eval.value = universe.lessThanEquals((NumericExpression) left,
						(NumericExpression) right);
				break;
			case EQUAL:
				eval.value = universe.equals(left, right);
				break;
			case NOT_EQUAL:
				eval.value = universe.neq(left, right);
				break;
			case MODULO: {
				BooleanExpression assumption = eval.state.pathCondition();
				NumericExpression denominator = (NumericExpression) right;
				BooleanExpression claim = universe.neq(
						zeroOf(expression.getSource(),
								expression.getExpressionType()), denominator);
				ResultType resultType = universe.reasoner(assumption)
						.valid(claim).getResultType();

				if (resultType != ResultType.YES) {
					CIVLExecutionException e = new CIVLStateException(
							ErrorKind.DIVISION_BY_ZERO, certaintyOf(
									expression.getSource(), resultType),
							"Modulus denominator is zero", eval.state,
							expression.getSource());

					log.report(e);
					eval.state = stateFactory.setPathCondition(eval.state,
							universe.and(assumption, claim));
				}
				eval.value = universe.modulo((NumericExpression) left,
						denominator);
				break;
			}
			case POINTER_ADD:
				eval = pointerAdd(state, pid, expression, left,
						(NumericExpression) right);
				break;
			case POINTER_SUBTRACT:
				eval = pointerSubtract(state, pid, expression, left, right);
				break;
			case AND:
			case OR:
				throw new CIVLInternalException("unreachable", expression);
			default:
				throw new CIVLUnimplementedFeatureException("Operator "
						+ expression.operator(), expression);
			}
			return eval;
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
	private Evaluation evaluateBooleanLiteral(State state, int pid,
			BooleanLiteralExpression expression) {
		return new Evaluation(state, universe.bool(expression.value()));
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
	private Evaluation evaluateCast(State state, int pid,
			CastExpression expression) {
		// TODO: many cases to deal with here. Go through
		// all the conversions in ABC, as these will be
		// translated to casts. In particular, need to
		// be able to cast integers to pointers to handle
		// NULL=(void*)0. From one pointer type to another.
		Expression arg = expression.getExpression();
		CIVLType argType = arg.getExpressionType();
		Evaluation eval = evaluate(state, pid, arg);
		SymbolicExpression value = eval.value;
		// SymbolicType startType = value.type();
		CIVLType castType = expression.getCastType();
		SymbolicType endType = symbolicType(expression.getSource(), castType);

		if (argType.isIntegerType() && castType.isPointerType()) {
			// only good cast is from 0 to null pointer
			BooleanExpression assumption = eval.state.pathCondition();
			BooleanExpression claim = universe.equals(zero, value);
			ResultType resultType = universe.reasoner(assumption).valid(claim)
					.getResultType();

			if (resultType != ResultType.YES) {
				log.report(new CIVLStateException(ErrorKind.INVALID_CAST,
						certaintyOf(expression.getSource(), resultType),
						"Cast from non-zero integer to pointer", eval.state,
						expression.getSource()));
				eval.state = stateFactory.setPathCondition(eval.state,
						universe.and(assumption, claim));
			}
			eval.value = nullPointer;
			return eval;
		} else if (argType.isPointerType() && castType.isPointerType()) {
			// pointer to pointer: for now...no change.
			return eval;
		}
		try {
			eval.value = universe.cast(endType, eval.value);
		} catch (SARLException e) {
			CIVLStateException error = new CIVLStateException(
					ErrorKind.INVALID_CAST, Certainty.NONE,
					"SARL could not cast: " + e, eval.state,
					expression.getSource());

			log.report(error);
			throw error;
		}
		return eval;
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
	private Evaluation evaluateIntegerLiteral(State state, int pid,
			IntegerLiteralExpression expression) {
		return new Evaluation(state, universe.integer(expression.value()
				.intValue()));
	}

	private Evaluation evaluateSelf(State state, int pid,
			SelfExpression expression) {
		return new Evaluation(state, makeProcVal(pid));
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
	private Evaluation evaluateRealLiteral(State state, int pid,
			RealLiteralExpression expression) {
		return new Evaluation(state, universe.number(universe
				.numberObject(numberFactory.rational(expression.value()
						.toPlainString()))));
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
	private Evaluation evaluateStringLiteral(State state, int pid,
			StringLiteralExpression expression) {
		return new Evaluation(state, universe.stringExpression(expression
				.value()));
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
	private Evaluation evaluateUnary(State state, int pid,
			UnaryExpression expression) {
		Evaluation eval = evaluate(state, pid, expression.operand());

		switch (expression.operator()) {
		case NEGATIVE:
			eval.value = universe.minus((NumericExpression) eval.value);
			break;
		case NOT:
			eval.value = universe.not((BooleanExpression) eval.value);
			break;
		default:
			throw new CIVLInternalException("Unknown unary operator "
					+ expression.operator(), expression);
		}
		return eval;
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
	private Evaluation evaluateVariable(State state, int pid,
			VariableExpression expression) {
		BooleanExpression pathCondition = state.pathCondition();
		SymbolicExpression value = state.valueOf(pid, expression.variable());

		if (value == null || value.isNull()) {
			CIVLExecutionException e = new CIVLStateException(
					ErrorKind.UNDEFINED_VALUE, Certainty.PROVEABLE,
					"Attempt to read uninitialized variable", state,
					expression.getSource());

			log.report(e);
			// unrecoverable error:
			throw e;
		}
		value = universe.reasoner(pathCondition).simplify(value);
		return new Evaluation(state, value);
	}

	private Evaluation evaluateResult(State state, int pid,
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

	private SymbolicType getDynamicType(State state, int pid, CIVLType type,
			CIVLSource source, boolean computeStructs) {
		SymbolicType result;

		if (type instanceof CIVLPrimitiveType) {
			result = ((CIVLPrimitiveType) type).getSymbolicType();
		} else if (type instanceof CIVLArrayType) {
			CIVLArrayType arrayType = (CIVLArrayType) type;
			SymbolicType elementSymbolicType = getDynamicType(state, pid,
					arrayType.elementType(), source, computeStructs);

			if (arrayType.isComplete()) {
				NumericExpression length = (NumericExpression) evaluate(state,
						pid, ((CIVLCompleteArrayType) arrayType).extent());
				result = universe.arrayType(elementSymbolicType, length);
			} else {
				result = universe.arrayType(elementSymbolicType);
			}
		} else if (type instanceof CIVLPointerType) {
			result = pointerType;
		} else if (type instanceof CIVLStructType) {
			// TODO: note: the variable must be set in the model builder
			CIVLStructType structType = (CIVLStructType) type;

			if (computeStructs) {
				int numFields = structType.numFields();
				LinkedList<SymbolicType> componentTypes = new LinkedList<SymbolicType>();

				for (int i = 0; i < numFields; i++) {
					StructField field = structType.getField(i);
					SymbolicType componentType = getDynamicType(state, pid,
							field.type(), source, computeStructs);

					componentTypes.add(componentType);
				}
				result = universe.tupleType(structType.name().stringObject(),
						componentTypes);
			} else {
				Variable variable = structType.getVariable();
				SymbolicExpression value = state.valueOf(pid, variable);
				result = getType(source, value);
			}
		} else
			throw new CIVLInternalException("Unreachable", source);
		return result;
	}

	private SymbolicExpression computeInitialValue(State s, LHSExpression expr,
			SymbolicType dynamicType) {
		// TODO
		CIVLSource source = expr.getSource();
		CIVLType type = expr.getExpressionType();

		if (type instanceof CIVLPrimitiveType) {
			// an "undefined" symbolic constant of that type?

		} else if (type instanceof CIVLArrayType) {

		} else if (type instanceof CIVLPointerType) {
			// same as primitive type
		} else if (type instanceof CIVLStructType) {

		} else
			throw new CIVLInternalException("Unreachable", source);
		return null;

		// if dynamicType is a tuple type, create the concrete tuple of that
		// type with each component obtained by evaluating
		// computeInitialValue(s, dotExpression(), componentType)
		// if dynamicType is an array type, create a unique symbolic constant
		// name based on the LHS expression and a symbolic constant with that
		// name and type dynamicType (no recursion in this case)
	}

	private Evaluation evaluateDynamicTypeOf(State state, int pid,
			DynamicTypeOfExpression expression) {
		// TODO
		return null;
	}

	private Evaluation evaluateInitialValue(State state, int pid,
			InitialValueExpression expression) {
		// TODO
		return null;
	}

	// Exported methods...

	public ModelFactory modelFactory() {
		return modelFactory;
	}

	public StateFactoryIF stateFactory() {
		return stateFactory;
	}

	public SymbolicUniverse universe() {
		return universe;
	}

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

	public SymbolicExpression nullPointer() {
		return nullPointer;
	}

	/**
	 * Tells whether the given symbolic expression is the null pointer value.
	 * 
	 * @param pointer
	 *            any symbolic expression
	 * @return true iff the expression is the null pointer value
	 */
	public boolean isNullPointer(SymbolicExpression pointer) {
		return isPointer(pointer) && getSymRef(pointer).isNullReference();
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
		return universe.tuple(processType, new Singleton<SymbolicExpression>(
				universe.integer(pid)));
	}

	/**
	 * Given a process value (aka "procVal", a symbolic expression of process
	 * type), extracts and returns the concrete integer PID.
	 * 
	 * @param procVal
	 *            an expression created by method {@link #makeProcVal}.
	 * @return the concrete integer PID wrapped by the procVal
	 */
	public int getPid(CIVLSource source, SymbolicExpression procVal) {
		return extractIntField(source, procVal, zeroObj);
	}

	/**
	 * Given a pointer value, returns the dynamic scope ID component of that
	 * pointer value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the dynamic scope ID component of that pointer value
	 */
	public int getScopeId(CIVLSource source, SymbolicExpression pointer) {
		return getSid(source, universe.tupleRead(pointer, zeroObj));
	}

	/**
	 * Given a pointer value, returns the variable ID component of that value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the variable ID component of that value
	 */
	public int getVariableId(CIVLSource source, SymbolicExpression pointer) {
		return extractIntField(source, pointer, oneObj);
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
	public Evaluation reference(State state, int pid, LHSExpression operand) {
		Evaluation result;

		if (operand instanceof VariableExpression) {
			Variable variable = ((VariableExpression) operand).variable();
			int sid = state.getScopeId(pid, variable);
			int vid = variable.vid();

			result = new Evaluation(state, makePointer(sid, vid,
					identityReference));
		} else if (operand instanceof SubscriptExpression) {
			Evaluation refEval = reference(state, pid,
					((SubscriptExpression) operand).array());
			SymbolicExpression arrayPointer = refEval.value;
			ReferenceExpression oldSymRef = getSymRef(arrayPointer);
			NumericExpression index;
			ReferenceExpression newSymRef;

			result = evaluate(refEval.state, pid,
					((SubscriptExpression) operand).index());
			index = (NumericExpression) result.value;
			newSymRef = universe.arrayElementReference(oldSymRef, index);
			result.value = setSymRef(arrayPointer, newSymRef);
		} else if (operand instanceof DereferenceExpression) {
			result = evaluate(state, pid,
					((DereferenceExpression) operand).pointer());
		} else if (operand instanceof DotExpression) {
			Evaluation eval = reference(state, pid,
					(LHSExpression) ((DotExpression) operand).struct());
			SymbolicExpression structPointer = eval.value;
			ReferenceExpression oldSymRef = getSymRef(structPointer);
			int index = ((DotExpression) operand).fieldIndex();
			ReferenceExpression newSymRef = universe.tupleComponentReference(
					oldSymRef, universe.intObject(index));

			eval.value = setSymRef(structPointer, newSymRef);
			result = eval;
		} else
			throw new CIVLInternalException("Unknown kind of LHSExpression",
					operand);
		return result;
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
	public Evaluation dereference(CIVLSource source, State state,
			SymbolicExpression pointer) {
		// how to figure out if pointer is null pointer?
		int sid = getScopeId(source, pointer);
		int vid = getVariableId(source, pointer);
		ReferenceExpression symRef = getSymRef(pointer);
		SymbolicExpression variableValue = state.getScope(sid).getValue(vid);
		Evaluation result = new Evaluation(state, universe.dereference(
				variableValue, symRef));

		return result;
	}

	/**
	 * Given a symbolic type, returns a canonic symbolic expression which
	 * somehow wraps that type so it can be used as a value. Nothing should be
	 * assumed about the symbolic expression. To extract the type from such an
	 * expression, use method {@link #getType}.
	 * 
	 * @param type
	 *            a symbolic type
	 * @return a canonic symbolic expression wrapping that type
	 */
	public SymbolicExpression expressionOfType(SymbolicType type) {
		SymbolicExpression result;

		type = (SymbolicType) universe.canonic(type);
		result = typeExpressionMap.get(type);
		if (result == null) {
			SymbolicExpression id = universe.integer(type.id());

			result = universe.canonic(universe.tuple(dynamicType,
					new Singleton<SymbolicExpression>(id)));
			typeExpressionMap.put(type, result);
		}
		return result;
	}

	/**
	 * Given a symbolic expression returned by the method
	 * {@link #expressionOfType}, this extracts the type that was used to create
	 * that expression. If the given expression is not an expression that was
	 * created by {@link #expressionOfType}, the behavior is undefined.
	 * 
	 * @param expr
	 *            a symbolic expression returned by method
	 *            {@link #expressionOfType}
	 * @return the symbolic type used to create that expression
	 */
	public SymbolicType getType(CIVLSource source, SymbolicExpression expr) {
		int id = extractIntField(source, expr, zeroObj);

		return (SymbolicType) universe.objectWithId(id);
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
	public Evaluation evaluate(State state, int pid, Expression expression) {
		ExpressionKind kind = expression.expressionKind();
		Evaluation result;

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
		case DYNAMIC_TYPE_OF:
			result = evaluateDynamicTypeOf(state, pid,
					(DynamicTypeOfExpression) expression);
			break;
		case INITIAL_VALUE:
			result = evaluateInitialValue(state, pid,
					(InitialValueExpression) expression);
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
