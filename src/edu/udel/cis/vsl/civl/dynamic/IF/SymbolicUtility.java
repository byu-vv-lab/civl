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

	/**
	 * <p>
	 * Given a pointer to a certain element of some array, returns the index of
	 * the element that the pointer points to.
	 * </p>
	 * 
	 * <p>
	 * Precondition: pointer must point to an element of some array.
	 * </p>
	 * 
	 * @param source
	 *            The source code information for error report.
	 * @param pointer
	 *            The pointer checks.
	 * @return The index of the element that the pointer points to.
	 */
	int getArrayIndex(CIVLSource source, SymbolicExpression pointer);

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

	/**
	 * Checks if a heap is empty, i.e., either it is the SARL null expression or
	 * all heap objects it holds are marked as INVALID (already deallocated).
	 * 
	 * @param heapValue
	 *            The value of the heap to be checked.
	 * @return True iff the heap has null value or is empty.
	 */
	boolean isEmptyHeap(SymbolicExpression heapValue);

	/**
	 * <p>
	 * Computes the range of the i-th element of a given domain. For example, if
	 * domain is {(1, 4, 1), (9, 4, -2)} and index is 1, then the returned value
	 * should be (9, 4, -2).
	 * </p>
	 * 
	 * <p>
	 * Precondition: domain must be a valid domain value, and index must be
	 * greater or equal to 0 and less than the size of domain.
	 * </p>
	 * 
	 * @param domain
	 *            The domain whose index-th range is to be computed.
	 * @param index
	 *            The index of the range to be computed in the domain.
	 * @return The index-th range of the domain, which has the form (low, high,
	 *         step).
	 */
	SymbolicExpression rangeOfDomainAt(SymbolicExpression domain, int index);

	/**
	 * Computes the initial value of the index-th range of a given domain. *
	 * 
	 * @param range
	 *            The range value.
	 * @param index
	 *            The index of the range.
	 * @param dimension
	 *            The number of ranges that the domain contains.
	 * @return The initial value of the index-th range of a given domain.
	 */
	SymbolicExpression initialValueOfRange(SymbolicExpression range, int index,
			int dimension);

	/**
	 * Checks if the given value is within the index-th range of a certain
	 * domain.
	 * 
	 * @param value
	 *            The value to be tested if it is in range.
	 * @param domain
	 *            The domain that the value wants to test with.
	 * @param index
	 *            The index of the range that the test bases on.
	 * @return True iff the given value is within the index-th range of a
	 *         certain domain.
	 */
	BooleanExpression isInRange(SymbolicExpression value,
			SymbolicExpression domain, int index);

	/**
	 * Increments a certain value based on the step of a given range.
	 * 
	 * @param value
	 *            The value to be incremented.
	 * @param range
	 *            The range whose step is to be used.
	 * @return The result of incremental.
	 */
	SymbolicExpression rangeIncremental(SymbolicExpression value,
			SymbolicExpression range);

	/**
	 * Computes the lower bound of the index-th range of a domain.
	 * 
	 * @param domain
	 *            The given domain.
	 * @param index
	 *            The index of the range.
	 * @return The lower bound of the index-th range of the domain.
	 */
	SymbolicExpression getLowOfDomainAt(SymbolicExpression domain, int index);

	/**
	 * Computes the size of a range, that is the number of values covered by the
	 * range.
	 * 
	 * @param range
	 *            The range.
	 * @return The number of values covered by the range
	 */
	NumericExpression getRangeSize(SymbolicExpression range);

	/**
	 * Computes the lower bound of a certain range.
	 * 
	 * @param range
	 *            The range.
	 * @return The lower bound of the range.
	 */
	NumericExpression getLowOfRange(SymbolicExpression range);

	/**
	 * Computes the upper bound of a certain range.
	 * 
	 * @param range
	 *            The range.
	 * @return The upper bound of the range.
	 */
	NumericExpression getHighOfRange(SymbolicExpression range);

	/**
	 * Computes the step of a range.
	 * 
	 * @param range
	 *            The range.
	 * @return The step of the range.
	 */
	NumericExpression getStepOfRange(SymbolicExpression range);

	/**
	 * Checks if a given value is initialized.
	 * 
	 * @param value
	 *            The value to be checked.
	 * @return True iff the value is already initialized.
	 */
	boolean isInitialized(SymbolicExpression value);

	/**
	 * Checks if the object that the first pointer points to contains that of
	 * the send pointer.
	 * 
	 * @param first
	 *            The first pointer.
	 * @param second
	 *            The second pointer.
	 * @return True iff the object that the first pointer points to contains
	 *         that of the send pointer.
	 */
	SymbolicExpression contains(SymbolicExpression first,
			SymbolicExpression second);

	/**
	 * Returns the NULL pointer of CIVL.
	 * 
	 * @return The NULL pointer of CIVL.
	 */
	SymbolicExpression nullPointer();

	/**
	 * Checks if a given pointer is a NULL pointer.
	 * 
	 * @param pointer
	 *            The pointer to be checked.
	 * @return True iff the given pointer is NULL.
	 */
	boolean isNullPointer(SymbolicExpression pointer);

	/**
	 * Is the given pointer pointing to a memory space that is part of a heap?
	 * 
	 * @param pointer
	 * @return
	 */
	boolean isPointerToHeap(SymbolicExpression pointer);

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
	 * Is the given pointer pointing to the first element of a heap object?
	 * 
	 * @param source
	 *            The source code information for error report.
	 * @param pointer
	 *            The pointer to be checked.
	 * @return True iff the given pointer is pointing to the first element of a
	 *         heap object.
	 */
	boolean isHeapAtomicObjectPointer(CIVLSource source,
			SymbolicExpression pointer);

	/**
	 * Computes the reference expression of a pointer. If the pointer is
	 * pointing to some part of the heap, then the reference expression is the
	 * reference expression w.r.t to the corresponding heap atomic object;
	 * otherwise, it is the original reference expression of the pointer.
	 * 
	 * @param pointer
	 *            The pointer to whose reference is to be computed.
	 * @return The reference expression of the pointer w.r.t to the object it
	 *         points to.
	 */
	ReferenceExpression referenceOfPointer(SymbolicExpression pointer);

	/**
	 * Constructs a pointer by combining a pointer to an object, either a heap
	 * atomic object or a normal object, and a reference expression w.r.t that
	 * object.
	 * 
	 * @param objectPointer
	 * @param reference
	 * @return
	 */
	SymbolicExpression makePointer(SymbolicExpression objectPointer,
			ReferenceExpression reference);

	/**
	 * Checks if a pointer is valid for dereference, i.e., it isn't NULL, nor it
	 * isn't pointing to a deallocated memory space, nor it doesn't point to a
	 * memory unit of an invalid scope.
	 * 
	 * @param pointer
	 *            The pointer to be checked.
	 * @return True iff the pointer is valid, i.e., can be dereferenced.
	 */
	boolean isValidPointer(SymbolicExpression pointer);

	/**
	 * Checks if the given reference is valid for a certain object.
	 * 
	 * @param ref
	 *            The reference.
	 * @param object
	 *            The object.
	 * @return True iff the given reference expression is applicable for the
	 *         given object.
	 */
	boolean isValidRefOf(ReferenceExpression ref, SymbolicExpression object);

	/**
	 * Returns the undefined pointer of CIVL, which is a tuple <-2, -2, NULL>.
	 * In CIVL, a pointer becomes undefined when the memory space it points to
	 * get deallocated.
	 * 
	 * @return The undefined pointer.
	 */
	SymbolicExpression undefinedPointer();

	/**
	 * Constructs an invalid heap object of a certain type. A heap object
	 * becomes invalid when it gets deallocated.
	 * 
	 * @param heapObjectType
	 *            The type of the heap object.
	 * @return The invalid heap object of the given type.
	 */
	SymbolicConstant invalidHeapObject(SymbolicType heapObjectType);

	/**
	 * Is this heap object invalid?
	 * 
	 * @param heapObject
	 *            The heap object.
	 * @return True iff the given heap object is invalid.
	 */
	boolean isInvalidHeapObject(SymbolicExpression heapObject);

	/**
	 * Is this an undefined pointer?
	 * 
	 * @param pointer
	 *            The pointer.
	 * @return True iff the given pointer is undefined.
	 */
	boolean isUndefinedPointer(SymbolicExpression pointer);

	/**
	 * Checks if the objects that the two pointers point to have any
	 * intersection.
	 * 
	 * @param pointer1
	 *            The first pointer.
	 * @param pointer2
	 *            The second pointer.
	 * @return True iff there is no intersection between the objects that the
	 *         given two pointers point to.
	 */
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

	/**
	 * Computes the reference expression of a given heap pointer w.r.t the
	 * corresponding heap memory unit.
	 * 
	 * @param heapPointer
	 *            The heap pointer.
	 * @return The reference expression of a given pointer w.r.t the
	 *         corresponding heap memory unit.
	 */
	ReferenceExpression referenceToHeapMemUnit(SymbolicExpression heapPointer);
}
