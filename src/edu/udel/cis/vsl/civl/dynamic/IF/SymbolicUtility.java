package edu.udel.cis.vsl.civl.dynamic.IF;

import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

/**
 * A SymbolicUtility provides all the common operations of symbolic expressions.
 * 
 * @author Manchun Zheng
 * 
 */
public interface SymbolicUtility {
	/**
	 * 
	 * Gets a Java concrete int from a symbolic expression or throws exception.
	 * 
	 * @param expression
	 *            a numeric expression expected to hold concrete int value
	 * @return the concrete int
	 * @throws CIVLInternalException
	 *             if a concrete integer value cannot be extracted
	 */
	int extractInt(CIVLSource source, NumericExpression expression);

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
	SymbolicExpression parentPointer(CIVLSource source,
			SymbolicExpression pointer);

	/**
	 * Given a pointer value, returns the symbolic reference component of that
	 * value. The "symRef" refers to a sub-structure of the variable pointed to.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the symRef component
	 */
	ReferenceExpression getSymRef(SymbolicExpression pointer);

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
	SymbolicExpression setSymRef(SymbolicExpression pointer,
			ReferenceExpression symRef);

	/**
	 * Given a pointer value, returns the dynamic scope ID component of that
	 * pointer value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the dynamic scope ID component of that pointer value
	 */
	int getDyscopeId(CIVLSource source, SymbolicExpression pointer);

	/**
	 * Given a pointer value, returns the variable ID component of that value.
	 * 
	 * @param pointer
	 *            a pointer value
	 * @return the variable ID component of that value
	 */
	int getVariableId(CIVLSource source, SymbolicExpression pointer);

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
	int extractIntField(CIVLSource source, SymbolicExpression tuple,
			IntObject fieldIndex);

	/**
	 * Compute the symbolic representation of the size of a given symbolic type.
	 * 
	 * @param source
	 *            The source code element to be used in the error report (if
	 *            any).
	 * @param type
	 *            The symbolic type whose size is to evaluated.
	 * @return The symbolic representation of the symbolic type.
	 */
	NumericExpression sizeof(CIVLSource source, SymbolicType type);

	/**
	 * Returns the abstract function <code>sizeof()</code>.
	 * 
	 * @return The abstract function <code>sizeof()</code>.
	 */
	SymbolicExpression sizeofFunction();

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
	SymbolicExpression expressionOfType(SymbolicType type);

	// /**
	// * Returns the initial value of a(n) (empty) heap.
	// *
	// * @return The initial value of a(n) (empty) heap.
	// */
	// SymbolicExpression initialHeapValue();

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
	 * @return a pointer value as specified by the 3 components
	 */
	SymbolicExpression makePointer(int scopeId, int varId,
			ReferenceExpression symRef);

	/**
	 * Constructs the string representation of an array of characters.
	 * 
	 * @param source
	 * @param charArray
	 * @param startIndex
	 * @param forPrint
	 * @return
	 */
	StringBuffer charArrayToString(CIVLSource source,
			SymbolicSequence<?> charArray, int startIndex, boolean forPrint);

	int getArrayIndex(CIVLSource source, SymbolicExpression charPointer);

	/* ***************** Arrays Operations Utilities ******************** */
	/**
	 * Recursively updates the array references for an multi-dimensional array
	 * by using a set of indexes and a given reference to an array element. e.g.
	 * If the arrayReference is a[x][y], then the size of newIndexes should be
	 * 2. And newIndexes[0] corresponds to update the x, newIndexes[1]
	 * corresponds to update the y, and so forth.
	 * 
	 * @author Ziqing Luo
	 * @param arrayReference
	 *            An reference to an array
	 * @param newIndexes
	 *            indexes for referencing the element
	 * @return the new arrayElementReference
	 */
	ReferenceExpression updateArrayElementReference(
			ArrayElementReference arrayReference,
			List<NumericExpression> newIndexes);

	/* *************************** Heap Operations ************************* */
	/**
	 * Checks if a heap is empty, i.e., either it is the SARL null expression or
	 * all heap objects it holds are marked as INVALID (already deallocated).
	 * 
	 * @param heapValue
	 *            The value of the heap to be checked.
	 * @return True iff the heap has null value or is empty.
	 */
	boolean isEmptyHeap(SymbolicExpression heapValue);

	SymbolicExpression rangeOfDomainAt(SymbolicExpression domain, int index);

	SymbolicExpression initialValueOfRange(SymbolicExpression range,
			boolean isLast);

	BooleanExpression isInRange(SymbolicExpression value,
			SymbolicExpression domain, int index);

	SymbolicExpression rangeIncremental(SymbolicExpression value,
			SymbolicExpression range);

	SymbolicExpression getLowOfDomainAt(SymbolicExpression domain, int index);

	NumericExpression getRangeSize(SymbolicExpression range);

	NumericExpression getLowOfRange(SymbolicExpression range);

	NumericExpression getHighOfRange(SymbolicExpression range);

	NumericExpression getStepOfRange(SymbolicExpression range);

	boolean isInitialized(SymbolicExpression value);

	SymbolicExpression contains(SymbolicExpression first,
			SymbolicExpression second);

	SymbolicExpression nullPointer();

	boolean isNullPointer(SymbolicExpression pointer);

	boolean isHeapObjectDefined(SymbolicExpression heapObj);

	/**
	 * Is the given pointer pointing to a memory space that is part of a heap?
	 * 
	 * @param pointer
	 * @return
	 */
	boolean isPointerToHeap(SymbolicExpression pointer);

	// /**
	// * Gets the pointer to the heap of the given scope.
	// *
	// * @param source
	// * The source code information for error report.
	// * @param state
	// * The state where this operation happens.
	// * @param process
	// * The information of the process that triggers this operation,
	// * for the purpose of error report.
	// * @param scopeValue
	// * The scope value
	// * @return The pointer to the heap of the given scope.
	// * @throws UnsatisfiablePathConditionException
	// * if the given scope is not concrete or not a valid scope.
	// */
	// SymbolicExpression heapPointer(CIVLSource source, State state,
	// String process, SymbolicExpression scopeValue)
	// throws UnsatisfiablePathConditionException;

	/**
	 * <p>
	 * Returns a pointer to a heap object which is involved by the given
	 * pointer.
	 * </p>
	 * 
	 * @param pointer
	 *            A valid pointer that points to some part of a heap.
	 * @return A pointer to a heap object that is involved by the given pointer.
	 */
	SymbolicExpression heapObjectPointer(SymbolicExpression pointer);

	/**
	 * Is the given pointer pointing to the first element of a heap cell?
	 * 
	 * @param source
	 * @param pointer
	 * @return
	 */
	boolean isHeapObjectPointer(CIVLSource source, SymbolicExpression pointer);

	ReferenceExpression referenceOfPointer(SymbolicExpression pointer);

	SymbolicExpression makePointer(SymbolicExpression objectPointer,
			ReferenceExpression reference);

	/**
	 * Checks if a pointer is defined, i.e., it doesn't point to a memory unit
	 * of an invalid scope.
	 * 
	 * @param pointer
	 * @return
	 */
	boolean isValidPointer(SymbolicExpression pointer);

	boolean isValidRefOf(ReferenceExpression ref, SymbolicExpression value);

	/**
	 * Returns the undefined pointer of CIVL, which is a tuple <-2, -2, NULL>
	 * 
	 * @return The undefined pointer.
	 */
	SymbolicExpression undefinedPointer();

	SymbolicConstant invalidHeapObject(SymbolicType heapObjectType);

	boolean isInvalidHeapObject(SymbolicExpression heapObject);

	boolean isUndefinedPointer(SymbolicExpression value);

	boolean isDisjointWith(SymbolicExpression pointer1,
			SymbolicExpression pointer2);

	/**
	 * Creates an array of the given length, with each element being the given
	 * value.
	 * 
	 * @param context
	 *            The context, usually the path condition, of the state where
	 *            this function is called.
	 * @param length
	 *            The length of the array, could be non-concrete.
	 * @param eleValue
	 *            The value of the element of the array.
	 * @return A new array of the given length, which has each element being the
	 *         given value.
	 */
	SymbolicExpression newArray(BooleanExpression context,
			NumericExpression length, SymbolicExpression eleValue);

	ReferenceExpression referenceOfHeapObjectPointer(SymbolicExpression pointer);
}
