/**
 * 
 */
package edu.udel.cis.vsl.civl.library.civlc;

import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.civl.err.CIVLException;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLStateException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;
import edu.udel.cis.vsl.sarl.IF.object.StringObject;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

/**
 * @author zirkel
 * 
 */
public class CivlcExecutor implements LibraryExecutor {

	private Executor primaryExecutor;

	private Evaluator evaluator;

	private SymbolicUniverse universe;

	private StateFactoryIF stateFactory;

	/**
	 * 
	 */
	public CivlcExecutor(Executor primaryExecutor) {
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
		this.universe = evaluator.universe();
		this.stateFactory = evaluator.stateFactory();
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
	public State executeMalloc(State state, int pid, MallocStatement statement) {
		CIVLSource source = statement.getSource();
		int sid = state.process(pid).scope();
		int index = statement.getMallocId();
		IntObject indexObj = universe.intObject(index);
		Evaluation eval;
		SymbolicExpression heapPointer;
		int heapVariableId;
		ReferenceExpression symRef;
		SymbolicExpression heapValue;
		NumericExpression mallocSize, elementSize;
		BooleanExpression pathCondition, claim;
		ResultType validity;
		NumericExpression elementCount;
		SymbolicExpression heapField;
		int length; // num allocated objects in index component of heap
		StringObject newObjectName;
		SymbolicType newObjectType;
		SymbolicExpression newObject;

		eval = evaluator.evaluate(state, pid,
				statement.getHeapPointerExpression());
		state = eval.state;
		heapPointer = eval.value;
		eval = evaluator.dereference(source, state, heapPointer);
		state = eval.state;
		heapValue = eval.value;
		heapVariableId = evaluator.getVariableId(source, heapPointer);
		symRef = evaluator.getSymRef(heapPointer);
		if (!symRef.isIdentityReference())
			throw new CIVLException("heap used as internal structure", source);
		eval = evaluator.evaluate(state, pid, statement.getSizeExpression());
		state = eval.state;
		mallocSize = (NumericExpression) eval.value;
		eval = evaluator.evaluateSizeofType(source, state, pid,
				statement.getStaticElementType());
		state = eval.state;
		elementSize = (NumericExpression) eval.value;
		pathCondition = state.pathCondition();
		claim = universe.divides(elementSize, mallocSize);
		validity = universe.reasoner(pathCondition).valid(claim)
				.getResultType();
		if (validity != ResultType.YES) {
			Certainty certainty = validity == ResultType.NO ? Certainty.PROVEABLE
					: Certainty.MAYBE;
			CIVLStateException e = new CIVLStateException(ErrorKind.MALLOC,
					certainty,
					"Size argument to $malloc is not multiple of element size",
					eval.state, source);

			evaluator.log().report(e);
			state = stateFactory.setPathCondition(state,
					universe.and(pathCondition, claim));
		}
		elementCount = universe.divide(mallocSize, elementSize);
		heapField = universe.tupleRead(heapValue, indexObj);
		length = evaluator.extractInt(source, universe.length(heapField));
		newObjectName = universe.stringObject("H_p" + pid + "s" + sid + "v"
				+ heapVariableId + "i" + index + "l" + length);
		newObjectType = universe.arrayType(statement.getDynamicElementType(),
				elementCount);
		newObject = universe.symbolicConstant(newObjectName, newObjectType);
		heapField = universe.append(heapField, newObject);
		heapValue = universe.tupleWrite(heapValue, indexObj, heapField);
		state = primaryExecutor.assign(source, state, heapPointer, heapValue);
		return state;
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
		CallOrSpawnStatement call;
		Expression lhs;

		if (!(statement instanceof CallOrSpawnStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc",
					statement);
		}
		call = (CallOrSpawnStatement) statement;
		name = call.function().name();
		lhs = call.lhs();
		arguments = new SymbolicExpression[((CallOrSpawnStatement) statement)
				.arguments().size()];
		for (int i = 0; i < ((CallOrSpawnStatement) statement).arguments()
				.size(); i++) {
			Evaluation eval = primaryExecutor.evaluator().evaluate(state, pid,
					call.arguments().elementAt(i));
			arguments[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		// case "$malloc":
		// result = executeMalloc(state, pid, getMallocType(call), lhs,
		// arguments[0], (NumericExpression) arguments[1]);
		// break;
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
			break;
		default:
			throw new CIVLInternalException("Unknown civlc function: " + name,
					statement);
		}
		if (name.name().equals("free")) {
			assert arguments.length == 2;

		} else if (name.name().equals("printf")) {
			assert arguments[0] instanceof StringObject;

			System.out.println(arguments[0]);
		} else {
			throw new CIVLUnimplementedFeatureException(name.name(),
					statement.getSource());
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
