package edu.udel.cis.vsl.civl.state.IF;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public interface DynamicScope {

	boolean isMutable();

	boolean isCanonic();

	void commit();

	int getCanonicId();

	Scope lexicalScope();

	SymbolicExpression getValue(int vid);

	DynamicScope setValue(int vid, SymbolicExpression value);

	DynamicScope setValues(SymbolicExpression[] values);

	Iterable<SymbolicExpression> getValues();

	/**
	 * How many processes can reach this dynamic scope? A process p can reach a
	 * dynamic scope d iff there is a path starting from a dynamic scope which
	 * is referenced in a frame on p's call stack to d, following the "parent"
	 * edges in the scope tree.
	 * 
	 * @return the number of processes which can reach this dynamic scope
	 */
	int numberOfReachers();

	/**
	 * Is this dynamic scope reachable by the process with the given PID?
	 * 
	 * @param pid
	 * @return true iff this dynamic scope is reachable from the process with
	 *         pid PID
	 */
	boolean reachableByProcess(int pid);

	void print(PrintStream out, int id, String prefix);

}
