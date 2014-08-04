package edu.udel.cis.vsl.civl.dynamic.common;

import java.util.List;

import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * This class implements operations on a heap. A heap consists of a fixed number
 * (however, the number may vary from models to models) of heap fields, while a
 * heap field is composed of a dynamic number of heap objects of type array of
 * type T.
 * <ul>
 * <li>each dyscope has a heap by default, as the 0 variable of that dyscope;</li>
 * <li>the initial value of a heap is a SARL NULL expression (not JAVA's NULL);</li>
 * <li>a heap has the type &lt;arrays of arrays of type T1, arrays of arrays of
 * type T2, ...>, corresponding to malloc statements and handle objects used in
 * the model;</li>
 * <li>a heap field has the type arrays of array of type T;</li>
 * <li>a heap object has the type array of type T, which is
 * allocated/deallocated by malloc(handle create)/deallocate(handle destroy)
 * methods;</li>
 * <li>a heap object pointer is a pointer to a heap object, which has type
 * pointer to array of type T, and has the form <code>&di$heap&lt;j,k></code>,
 * where <code>i</code> is the dyscope ID of the heap, <code>j</code> is the
 * index of the heap field that the heap object belongs to, and <code>k</code>
 * is the index of the heap object in the heap field.</li>
 * <li></li>
 * </ul>
 * This class is part of the symbolic utility, the purpose of which is to factor
 * out the code related to heap.
 * 
 * @author Manchun Zheng
 * 
 */
public class HeapAnalyzer {

	/**
	 * The symoblic utility to be used.
	 */
	private CommonSymbolicUtility symbolicUtil;

	/**
	 * Creates a new instance of a heap analyzer.
	 * 
	 * @param util
	 *            The symbolic utility to be used.
	 */
	HeapAnalyzer(CommonSymbolicUtility util) {
		this.symbolicUtil = util;
	}

	/**
	 * <p>
	 * Returns a pointer to a heap object which is involved by the given
	 * pointer. For example,
	 * <code>p = (int*) $malloc($root, sizeof(int)*5)</code> allocates a heap
	 * object that has the type array of int with length 5, and the value of
	 * <code>p</code> is a pointer pointing to the first element of the heap
	 * object.
	 * </p>
	 * 
	 * @param pointer
	 *            A valid pointer that points to some part of a heap.
	 * @return A pointer to a heap object that is involved by the given pointer.
	 */
	SymbolicExpression heapObjectPointer(SymbolicExpression pointer) {
		ReferenceExpression ref = symbolicUtil.getSymRef(pointer);
		List<ReferenceExpression> ancestors = symbolicUtil.ancestorsOfRef(ref);

		return symbolicUtil.setSymRef(pointer, ancestors.get(1));
	}
}
