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
import edu.udel.cis.vsl.civl.log.ErrorLog;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.semantics.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.state.StateFactoryIF;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NTReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.TupleComponentReference;
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

	private NumericExpression zero;

	private ErrorLog log;

	public CivlcExecutor(Executor primaryExecutor) {
		this.primaryExecutor = primaryExecutor;
		this.evaluator = primaryExecutor.evaluator();
		this.log = evaluator.log();
		this.universe = evaluator.universe();
		this.stateFactory = evaluator.stateFactory();
		this.zero = universe.zeroInt();
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
		LHSExpression lhs = statement.getLHS();
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
		NumericExpression lengthExpression;
		int length; // num allocated objects in index component of heap
		StringObject newObjectName;
		SymbolicType newObjectType;
		SymbolicExpression newObject;
		SymbolicExpression firstElementPointer; // returned value

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

			log.report(e);
			state = stateFactory.setPathCondition(state,
					universe.and(pathCondition, claim));
		}
		elementCount = universe.divide(mallocSize, elementSize);
		heapField = universe.tupleRead(heapValue, indexObj);
		lengthExpression = universe.length(heapField);
		length = evaluator.extractInt(source, lengthExpression);
		newObjectName = universe.stringObject("H_p" + pid + "s" + sid + "v"
				+ heapVariableId + "i" + index + "l" + length);
		newObjectType = universe.arrayType(statement.getDynamicElementType(),
				elementCount);
		newObject = universe.symbolicConstant(newObjectName, newObjectType);
		heapField = universe.append(heapField, newObject);
		heapValue = universe.tupleWrite(heapValue, indexObj, heapField);
		state = primaryExecutor.assign(source, state, heapPointer, heapValue);
		if (lhs != null) {
			symRef = universe.tupleComponentReference(symRef, indexObj);
			symRef = universe.arrayElementReference(symRef, lengthExpression);
			symRef = universe.arrayElementReference(symRef, zero);
			firstElementPointer = evaluator.setSymRef(heapPointer, symRef);
			state = primaryExecutor
					.assign(state, pid, lhs, firstElementPointer);
		}
		return state;
	}

	private Evaluation getAndCheckHeapObjectPointer(
			SymbolicExpression heapPointer, SymbolicExpression pointer,
			CIVLSource pointerSource, State state) {
		SymbolicExpression objectPointer = evaluator.getParentPointer(pointer);

		if (objectPointer != null) {
			SymbolicExpression fieldPointer = evaluator
					.getParentPointer(objectPointer);

			if (fieldPointer != null) {
				SymbolicExpression actualHeapPointer = evaluator
						.getParentPointer(fieldPointer);

				if (actualHeapPointer != null) {
					BooleanExpression pathCondition = state.pathCondition();
					BooleanExpression claim = universe.equals(
							actualHeapPointer, heapPointer);
					ResultType valid = universe.reasoner(pathCondition)
							.valid(claim).getResultType();
					ReferenceExpression symRef;

					if (valid != ResultType.YES) {
						Certainty certainty = valid == ResultType.NO ? Certainty.PROVEABLE
								: Certainty.MAYBE;
						CIVLStateException e = new CIVLStateException(
								ErrorKind.MALLOC, certainty,
								"Invalid pointer for heap", state,
								pointerSource);

						log.report(e);
						state = stateFactory.setPathCondition(state,
								universe.and(pathCondition, claim));
					}
					symRef = evaluator.getSymRef(pointer);
					if (symRef instanceof ArrayElementReference) {
						NumericExpression index = ((ArrayElementReference) symRef)
								.getIndex();

						if (index.isZero()) {
							return new Evaluation(state, objectPointer);
						}
					}

				}
			}
		}
		{
			CIVLStateException e = new CIVLStateException(ErrorKind.MALLOC,
					Certainty.PROVEABLE, "Invalid pointer for heap", state,
					pointerSource);

			log.report(e);
			state = stateFactory.setPathCondition(state,
					universe.falseExpression());
			return new Evaluation(state, objectPointer);
		}
	}

	private int getMallocIndex(SymbolicExpression pointer) {
		// ref points to element 0 of an array:
		NTReferenceExpression ref = (NTReferenceExpression) evaluator
				.getSymRef(pointer);
		// objectPointer points to array:
		NTReferenceExpression objectPointer = (NTReferenceExpression) ref
				.getParent();
		// fieldPointer points to the field:
		TupleComponentReference fieldPointer = (TupleComponentReference) objectPointer
				.getParent();
		int result = fieldPointer.getIndex().getInt();

		return result;
	}

	// better to get more precise source...
	private State executeFree(State state, int pid, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source) {
		Expression heapPointerExpression = arguments[0];
		CIVLHeapType heapType = (CIVLHeapType) ((CIVLPointerType) heapPointerExpression
				.getExpressionType()).baseType();
		Expression pointerExpression = arguments[1];
		SymbolicExpression heapPointer = argumentValues[0];
		SymbolicExpression firstElementPointer = argumentValues[1];
		SymbolicExpression heapObjectPointer;
		Evaluation eval;
		int index;
		SymbolicExpression undef;

		eval = getAndCheckHeapObjectPointer(heapPointer, firstElementPointer,
				pointerExpression.getSource(), state);
		state = eval.state;
		heapObjectPointer = eval.value;
		index = getMallocIndex(firstElementPointer);
		undef = heapType.getMalloc(index).getUndefinedObject();
		state = primaryExecutor.assign(source, state, heapObjectPointer, undef);

		return state;
	}

	@Override
	public State execute(State state, int pid, Statement statement) {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		CallOrSpawnStatement call;
		Expression lhs;
		int numArgs;

		if (!(statement instanceof CallOrSpawnStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc",
					statement);
		}
		call = (CallOrSpawnStatement) statement;
		numArgs = call.arguments().size();
		name = call.function().name();
		lhs = call.lhs();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = call.arguments().elementAt(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "$free":
			state = executeFree(state, pid, arguments, argumentValues,
					statement.getSource());
			break;
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
			throw new CIVLUnimplementedFeatureException(name.name(), statement);
		default:
			throw new CIVLInternalException("Unknown civlc function: " + name,
					statement);
		}
		return state;
	}

	@Override
	public boolean containsFunction(String name) {
		Set<String> functions = new HashSet<String>();

		functions.add("$malloc");
		functions.add("$free");
		functions.add("$write");
		return functions.contains(name);
	}

	@Override
	public State initialize(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State wrapUp(State state) {
		// TODO Auto-generated method stub
		return null;
	}

}
