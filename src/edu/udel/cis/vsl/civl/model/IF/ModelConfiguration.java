package edu.udel.cis.vsl.civl.model.IF;

/**
 * This file contains the constants used by the model builder/translator, which
 * reflects the translation strategy of CIVL. For example, for every scope, the
 * heap variable is added as the variable with index 0.
 * 
 * @author zmanchun
 *
 */
public final class ModelConfiguration {
	/**
	 * The name of the atomic lock variable
	 */
	public static final String ATOMIC_LOCK_VARIABLE = "__atomic_lock_var";
	/**
	 * The name of the heap variable
	 */
	public static final String HEAP_VAR = "__heap";
	public static final int heapVariableIndex = 0;
}
