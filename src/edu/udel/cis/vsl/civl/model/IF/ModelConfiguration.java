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

	/* Domain decomposition strategies */
	/**
	 * ALL, corresponds to the enumerator ALL of the enumeration type
	 * $domain_decomposition.
	 */
	public static final int DECOMP_ALL = 0;

	/**
	 * RANDOM, corresponds to the enumerator ALL of the enumeration type
	 * $domain_decomposition.
	 */
	public static final int DECOMP_RANDOM = 1;

	/**
	 * ROUND_ROBIN, corresponds to the enumerator ALL of the enumeration type
	 * $domain_decomposition.
	 */
	public static final int DECOMP_ROUND_ROBIN = 2;

	/* System variables */

	/**
	 * The name of the atomic lock variable of the system scope.
	 */
	public static final String ATOMIC_LOCK_VARIABLE = "_atomic_lock_var";

	/**
	 * The name of the time count variable, which is incremented by the system
	 * function $next_time_count() of civlc.cvh.
	 */
	public static final String TIME_COUNT_VARIABLE = "_time_count_var";

	/**
	 * The variable to store broken time information for the time library. This
	 * variable is needed because some functions of time.h returns a pointer to
	 * it.
	 */
	public static final String BROKEN_TIME_VARIABLE = "_broken_time_var";

	/**
	 * The name of the heap variable of each scope.
	 */
	public static final String HEAP_VAR = "_heap";

	/**
	 * The index of the heap variable in the scope.
	 */
	public static final int heapVariableIndex = 0;

	/**
	 * The name of the file system variable, created when stdio transformation
	 * is performed.
	 */
	public static final String FILE_SYSTEM_TYPE = "CIVL_filesystem";

	/* Types */

	/**
	 * The name of the $range type.
	 */
	public static final String RANGE_TYPE = "$range";

	/**
	 * The name of _pthread_gpool_t type, which is the object type of the handle
	 * $pthread_gpool, and is used by pthread.cvl.
	 */
	public static final String PTHREAD_GPOOL = "_pthread_gpool_t";

	/**
	 * The name of _pthread_poo_t type, which is the object type of the handle
	 * $pthread_pool, and is used by pthread.cvl.
	 */
	public static final String PTHREAD_POOL = "_pthread_pool_t";

	/**
	 * The name of __barrier__ type, which is the object type of the handle
	 * $barrier.
	 */
	public static final String BARRIER_TYPE = "__barrier__";

	/**
	 * The name of __comm__ type, which is the object type of the handle $comm.
	 */
	public static final String COMM_TYPE = "__comm__";

	/**
	 * The name of __gbarrier__ type, which is the object type of the handle
	 * $gbarrier.
	 */
	public static final String GBARRIER_TYPE = "__gbarrier__";

	/**
	 * The name of __gcomm__ type, which is the object type of the handle
	 * $gcomm.
	 */
	public static final String GCOMM_TYPE = "__gcomm__";

	/**
	 * The name of __int_iter__ type, which is the object type of the handle
	 * $int_iter.
	 */
	public static final String INT_ITER_TYPE = "__int_iter__";

	/**
	 * The file type $file.
	 */
	public static final String REAL_FILE_TYPE = "$file";

	/**
	 * The file reference type FILE.
	 */
	public static final String FILE_STREAM_TYPE = "FILE";

	/**
	 * The tm type, used by time.h.
	 */
	public static final String TM_TYPE = "tm";

	/* libraries */

	/**
	 * The name of the time.h library.
	 */
	public static final String TIME_LIB = "time.h";

	/* Functions */

	public static final String NEXT_TIME_COUNT = "$next_time_count";
}
