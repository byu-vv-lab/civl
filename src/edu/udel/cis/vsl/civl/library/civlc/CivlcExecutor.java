/**
 * 
 */
package edu.udel.cis.vsl.civl.library.civlc;

import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.Type;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.util.CIVLInternalException;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.StringObject;

/**
 * @author zirkel
 * 
 */
public class CivlcExecutor implements LibraryExecutor {

	private Executor primaryExecutor;

	/**
	 * 
	 */
	public CivlcExecutor(Executor primaryExecutor) {
		this.primaryExecutor = primaryExecutor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor#name()
	 */
	@Override
	public String name() {
		return "civlc";
	}

	/**
	 * Returns the type of the elements being malloc'ed. For example, in the
	 * statement "p=(int*)malloc(n*sizeof(int))", the type returned would be
	 * int.
	 * 
	 * @param mallocStatement
	 *            an invocation of malloc
	 * @return the type of the elements being malloc'ed
	 */
	private Type getMallocType(CallStatement mallocStatement) {
		// TODO
		return null;
	}

	/**
	 * Arguments to malloc are: (0) pointer to heap, (1) integer size. It
	 * returns a pointer to the newly allocated object.
	 * 
	 * Arugment should have from n*sizeof(t) where n is some integer expression
	 * and t is a type. Furthermore, it should be cast to that type. There is
	 * some inference here.
	 * 
	 * The value of the heap should be the heapType, a symbolic type
	 * array-of-(union_i(array-of-t_i)), for some types t_1,t_2,....
	 * 
	 * Since the size argument has been evaluated, search for SIZEOF ? Or should
	 * this have already been done and stuck in the malloc?
	 * 
	 * Look at cast outside of malloc.
	 * 
	 * Other idea: create a new symbolic value "object(size)". Can be an
	 * uninterpreted function. Or struct. It doesn't matter. It can have an
	 * offset reference into it. Malloc returns the offset reference with offset
	 * 0. When that reference is cast to something else, actually change the
	 * value----there is a cascading cast which casts the object(size) to
	 * int[10] or whatever. Hence the cast does have a side effect on the state?
	 * 
	 * @param state
	 *            the pre-state
	 * @param pid
	 *            PID of process excecuting malloc
	 * @param type
	 *            of elements being malloc'ed
	 * @param lhs
	 *            left-hand side of assignment; could be null
	 * @param heapReference
	 *            reference to heap being used to perform malloc
	 * @param size
	 *            number of bytes being malloc'ed
	 * @return the post-state
	 */
	private State executeMalloc(State state, int pid, Type type,
			Expression lhs, SymbolicExpression heapReference,
			NumericExpression size) {
		// TODO
		// get the dynamicType from the type
		// count is size/sizeof(dynamicType)
		// create a new symbolic constant X (?) of type
		// array-of-dynamicType-of-length-count
		// let oldHeap be the value obtained by dereferencing
		// heapReference in state
		// let newHeap be the result of appending to oldHeap
		// the injection of X into the union type
		// assign newHeap to heapReference
		// create reference to element 0 of array?
		// how to create reference to injection?

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor#execute(edu.udel.cis
	 * .vsl.civl.state.State, int,
	 * edu.udel.cis.vsl.civl.model.IF.statement.Statement)
	 */
	@Override
	public State execute(State state, int pid, Statement statement) {
		Identifier name;
		State result = null;
		SymbolicExpression[] arguments;
		CallStatement call;
		Expression lhs;

		if (!(statement instanceof CallStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc: "
					+ statement);
		}
		call = (CallStatement) statement;
		name = call.function().name();
		lhs = call.lhs();
		arguments = new SymbolicExpression[((CallStatement) statement)
				.arguments().size()];
		for (int i = 0; i < ((CallStatement) statement).arguments().size(); i++) {
			arguments[i] = primaryExecutor.evaluator().evaluate(state, pid,
					call.arguments().elementAt(i));
		}
		switch (name.name()) {
		case "$malloc":
			result = executeMalloc(state, pid, getMallocType(call), lhs,
					arguments[0], (NumericExpression) arguments[1]);
			break;
		case "$free":
			// put in stdio? case "printf":
		case "$memcpy":
		case "$message_pack":
		case "$message_source":
		case "$message_tag":
		case "$message_dest":
		case "$message_size":
		case "$message_unpack":
		case "$comm_create":
		case "$comm_destroy":
		case "$comm_nprocs":
		case "$comm_enqueue":
		case "$comm_probe":
		case "$comm_seek":
		case "$comm_dequeue":
		case "$comm_chan_size":
		case "$comm_total_size":
		default:
			throw new CIVLInternalException("Unknown civlc function: " + name
					+ "\n" + statement);
		}
		if (name.name().equals("malloc")) {
			assert arguments.length == 2;

		} else if (name.name().equals("free")) {
			assert arguments.length == 2;

		} else if (name.name().equals("printf")) {
			assert arguments[0] instanceof StringObject;

			System.out.println(arguments[0]);
		} else {
			throw new RuntimeException("Unsupported statement for stdlib: "
					+ statement);
		}
		return result;
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor#containsFunction(java
	 * .lang.String)
	 */
	@Override
	public boolean containsFunction(String name) {
		Set<String> functions = new HashSet<String>();

		functions.add("malloc");
		functions.add("free");
		functions.add("write");
		return functions.contains(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor#initialize(edu.udel
	 * .cis.vsl.civl.state.State)
	 */
	@Override
	public State initialize(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor#wrapUp(edu.udel.cis
	 * .vsl.civl.state.State)
	 */
	@Override
	public State wrapUp(State state) {
		// TODO Auto-generated method stub
		return null;
	}

}
