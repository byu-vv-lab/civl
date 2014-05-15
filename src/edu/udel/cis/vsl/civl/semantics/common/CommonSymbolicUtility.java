package edu.udel.cis.vsl.civl.semantics.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import edu.udel.cis.vsl.civl.err.IF.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.log.IF.CIVLErrorLogger;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.util.IF.Singleton;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NTReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicCompleteArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class CommonSymbolicUtility implements SymbolicUtility {

	private SymbolicUniverse universe;

	private ModelFactory modelFactory;

	private IntObject zeroObj;

	private IntObject oneObj;

	private IntObject twoObj;

	private NumericExpression zero;

	private CIVLErrorLogger errorLogger;

	private SymbolicExpression sizeofFunction;

	private SymbolicTupleType dynamicType;

	/**
	 * The pointer value is a triple <s,v,r> where s identifies the dynamic
	 * scope, v identifies the variable within that scope, and r identifies a
	 * point within that variable. The type of s is scopeType, which is just a
	 * tuple wrapping a single integer which is the dynamic scope ID number. The
	 * type of v is integer; it is the (static) variable ID number for the
	 * variable in its scope. The type of r is ReferenceExpression from SARL.
	 */
	private SymbolicTupleType pointerType;

	/**
	 * Map from symbolic type to a canonic symbolic expression of that type.
	 */
	private Map<SymbolicType, SymbolicExpression> typeExpressionMap = new HashMap<>();

	/**
	 * TODO ???
	 */
	private Map<SymbolicType, NumericExpression> sizeofDynamicMap = new HashMap<>();

	public CommonSymbolicUtility(SymbolicUniverse universe,
			ModelFactory modelFactory, CIVLErrorLogger errLogger) {
		SymbolicType dynamicToIntType;

		this.universe = universe;
		this.modelFactory = modelFactory;
		this.errorLogger = errLogger;
		pointerType = modelFactory.pointerSymbolicType();
		dynamicType = modelFactory.dynamicSymbolicType();
		dynamicToIntType = universe.functionType(new Singleton<SymbolicType>(
				dynamicType), universe.integerType());
		sizeofFunction = universe.symbolicConstant(
				universe.stringObject("SIZEOF"), dynamicToIntType);
		sizeofFunction = universe.canonic(sizeofFunction);
		this.zeroObj = (IntObject) universe.canonic(universe.intObject(0));
		this.oneObj = (IntObject) universe.canonic(universe.intObject(1));
		this.twoObj = (IntObject) universe.canonic(universe.intObject(2));
		zero = (NumericExpression) universe.canonic(universe.integer(0));

	}

	@Override
	public int extractInt(CIVLSource source, NumericExpression expression) {
		IntegerNumber result = (IntegerNumber) universe
				.extractNumber(expression);

		// TODO make expression
		if (result == null)
			throw new CIVLInternalException(
					"Unable to extract concrete int from " + expression, source);
		return result.intValue();
	}

	@Override
	public int getScopeId(CIVLSource source, SymbolicExpression pointer) {
		return modelFactory.getScopeId(source,
				universe.tupleRead(pointer, zeroObj));
	}

	@Override
	public SymbolicExpression parentPointer(CIVLSource source,
			SymbolicExpression pointer) {
		ReferenceExpression symRef = getSymRef(pointer);

		if (symRef instanceof NTReferenceExpression)
			return setSymRef(pointer,
					((NTReferenceExpression) symRef).getParent());
		throw new CIVLInternalException("Expected non-trivial pointer: "
				+ pointer, source);
	}

	@Override
	public ReferenceExpression getSymRef(SymbolicExpression pointer) {
		SymbolicExpression result = universe.tupleRead(pointer, twoObj);

		assert result instanceof ReferenceExpression;
		return (ReferenceExpression) result;
	}

	@Override
	public SymbolicExpression setSymRef(SymbolicExpression pointer,
			ReferenceExpression symRef) {
		return universe.tupleWrite(pointer, twoObj, symRef);
	}

	@Override
	public int getVariableId(CIVLSource source, SymbolicExpression pointer) {
		return extractIntField(source, pointer, oneObj);
	}

	@Override
	public int extractIntField(CIVLSource source, SymbolicExpression tuple,
			IntObject fieldIndex) {
		NumericExpression field = (NumericExpression) universe.tupleRead(tuple,
				fieldIndex);

		return this.extractInt(source, field);
	}

	@Override
	public SymbolicExpression getSubArray(SymbolicExpression array,
			NumericExpression startIndex, NumericExpression endIndex,
			State state, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		// if startIndex is zero and endIndex is length, return array
		// verify startIndex >=0 and endIndex<= Length
		// if startIndex==endIndex return emptyArray
		// else if startIndex and endIndex are concrete, create concrete array
		// else need array lambdas or subsequence operation: todo
		BooleanExpression pathCondition = state.getPathCondition();
		Reasoner reasoner = universe.reasoner(pathCondition);
		NumericExpression length = universe.length(array);
		SymbolicArrayType arrayType = (SymbolicArrayType) array.type();
		SymbolicType elementType = arrayType.elementType();

		if (reasoner.isValid(universe.equals(zero, startIndex))
				&& reasoner.isValid(universe.equals(length, endIndex)))
			return array;
		else {
			BooleanExpression claim = universe.lessThanEquals(zero, startIndex);
			ResultType valid = reasoner.valid(claim).getResultType();

			if (valid != ResultType.YES) {
				state = errorLogger.logError(source, state, claim, valid,
						ErrorKind.OUT_OF_BOUNDS, "negative start index");
				pathCondition = state.getPathCondition();
				reasoner = universe.reasoner(pathCondition);
			}
			claim = universe.lessThanEquals(endIndex, length);
			valid = reasoner.valid(claim).getResultType();
			if (valid != ResultType.YES) {
				state = errorLogger.logError(source, state, claim, valid,
						ErrorKind.OUT_OF_BOUNDS,
						"end index exceeds length of array");
				pathCondition = state.getPathCondition();
				reasoner = universe.reasoner(pathCondition);
			}
			claim = universe.lessThanEquals(startIndex, endIndex);
			valid = reasoner.valid(claim).getResultType();
			if (valid != ResultType.YES) {
				state = errorLogger.logError(source, state, claim, valid,
						ErrorKind.OUT_OF_BOUNDS,
						"start index greater than end index");
				pathCondition = state.getPathCondition();
				reasoner = universe.reasoner(pathCondition);
			}
			if (reasoner.isValid(universe.equals(startIndex, endIndex))) {
				return universe.emptyArray(elementType);
			} else {
				IntegerNumber concreteStart = (IntegerNumber) reasoner
						.extractNumber(startIndex);
				IntegerNumber concreteEnd = (IntegerNumber) reasoner
						.extractNumber(endIndex);

				if (concreteStart != null && concreteEnd != null) {
					int startInt = concreteStart.intValue();
					int endInt = concreteEnd.intValue();
					LinkedList<SymbolicExpression> valueList = new LinkedList<SymbolicExpression>();

					for (int i = startInt; i < endInt; i++)
						valueList.add(universe.arrayRead(array,
								universe.integer(i)));
					return universe.array(elementType, valueList);
				}
			}
		}
		throw new CIVLInternalException("Unable to extract sub-array", source);
	}

	@Override
	public NumericExpression sizeof(CIVLSource source, SymbolicType type) {
		NumericExpression result = sizeofDynamicMap.get(type);

		if (result == null) {

			if (type.isBoolean())
				result = modelFactory.booleanType().getSizeof();
			else if (type == modelFactory.dynamicSymbolicType())
				result = modelFactory.dynamicType().getSizeof();
			else if (type.isInteger())
				result = modelFactory.integerType().getSizeof();
			else if (type == modelFactory.processSymbolicType())
				result = modelFactory.processType().getSizeof();
			else if (type.isReal())
				result = modelFactory.realType().getSizeof();
			else if (type == modelFactory.scopeSymbolicType())
				result = modelFactory.scopeType().getSizeof();
			else if (type instanceof SymbolicCompleteArrayType) {
				SymbolicCompleteArrayType arrayType = (SymbolicCompleteArrayType) type;

				result = sizeof(source, arrayType.elementType());
				result = universe.multiply(arrayType.extent(),
						(NumericExpression) result);
			} else if (type instanceof SymbolicArrayType) {
				throw new CIVLInternalException(
						"sizeof applied to incomplete array type", source);
			} else {
				// wrap the type in an expression of type dynamicTYpe
				SymbolicExpression typeExpr = expressionOfType(type);

				result = (NumericExpression) universe.apply(sizeofFunction,
						new Singleton<SymbolicExpression>(typeExpr));
			}
			sizeofDynamicMap.put(type, result);
		}
		return result;
	}

	@Override
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

	@Override
	public SymbolicExpression sizeofFunction() {
		return this.sizeofFunction;
	}

	@Override
	public SymbolicExpression initialHeapValue() {
		return modelFactory.heapType().getInitialValue();
	}

	@Override
	public SymbolicExpression makePointer(int scopeId, int varId,
			ReferenceExpression symRef) {
		SymbolicExpression scopeField = modelFactory.scopeValue(scopeId);
		SymbolicExpression varField = universe.integer(varId);
		SymbolicExpression result = universe.tuple(
				pointerType,
				Arrays.asList(new SymbolicExpression[] { scopeField, varField,
						symRef }));

		return result;
	}
}
