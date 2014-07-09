package edu.udel.cis.vsl.civl.library.civlc;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.IF.BaseLibraryEvaluator;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluator;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluator;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.object.IntObject;

public class LibcivlcEvaluator extends BaseLibraryEvaluator implements
		LibraryEvaluator {

	private IntObject twoObject = universe.intObject(2);
	private NumericExpression minusOne = universe.integer(-1);
	private NumericExpression minusTwo = universe.integer(-2);

	public LibcivlcEvaluator(String name, Evaluator evaluator,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil) {
		super(name, evaluator, modelFactory, symbolicUtil);
	}

	@Override
	public Evaluation evaluateGuard(CIVLSource source, State state, int pid,
			String function, List<Expression> arguments)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression[] argumentValues;
		int numArgs;
		BooleanExpression guard;
		int processIdentifier = state.getProcessState(pid).identifier();
		String process = "p" + processIdentifier + " (id = " + pid + ")";

		numArgs = arguments.size();
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval = null;

			try {
				eval = evaluator.evaluate(state, pid, arguments.get(i));
			} catch (UnsatisfiablePathConditionException e) {
				// the error that caused the unsatifiable path condition should
				// already have been reported.
				return new Evaluation(state, universe.falseExpression());
			}
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (function) {
		case "$comm_dequeue":
			try {
				guard = getDequeueGuard(state, pid, process, arguments,
						argumentValues);
			} catch (UnsatisfiablePathConditionException e) {
				// the error that caused the unsatifiable path condition should
				// already have been reported.
				return new Evaluation(state, universe.falseExpression());
			}
			break;
		case "$wait":
			guard = getWaitGuard(state, pid, arguments, argumentValues);
			break;
		case "$waitall":
			guard = getWaitAllGuard(state, pid, arguments, argumentValues);
			break;
		case "$barrier_exit":
			try {
				guard = getBarrierExitGuard(state, pid, process, arguments,
						argumentValues);
			} catch (UnsatisfiablePathConditionException e) {
				// the error that caused the unsatifiable path condition should
				// already have been reported.
				return new Evaluation(state, universe.falseExpression());
			}
			break;
		case "$bundle_pack":
		case "$bundle_size":
		case "$bundle_unpack":
		case "$barrier_create":
		case "$barrier_enter":
		case "$barrier_destroy":
		case "$equals":
		case "$contains":
		case "$gbarrier_create":
		case "$gbarrier_destroy":
		case "$comm_create":
		case "$comm_defined":
		case "$comm_enqueue":
		case "$comm_probe":
		case "$comm_seek":
		case "$comm_size":
		case "$exit":
		case "$comm_destroy":
		case "$gcomm_destroy":
		case "$free":
		case "$gcomm_create":
		case "$gcomm_defined":
		case "$int_iter_create":
		case "$int_iter_hasNext":
		case "$int_iter_next":
		case "$proc_defined":
		case "$scope_parent":
		case "$scope_defined":
		case "$int_iter_destroy":
		case "$domain_rectangular":
		case "$domain_get_dim":
		case "$choose_int":
			guard = universe.trueExpression();
			break;
		default:
			throw new CIVLInternalException("Unknown civlc function: "
					+ function, source);
		}
		return new Evaluation(state, guard);
	}

	/**
	 * void $waitall($proc *procs, int numProcs);
	 * 
	 * @param state
	 * @param pid
	 * @param arguments
	 * @param argumentValues
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private BooleanExpression getWaitAllGuard(State state, int pid,
			List<Expression> arguments, SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression procsPointer = argumentValues[0];
		SymbolicExpression numOfProcs = argumentValues[1];
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		IntegerNumber number_nprocs = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) numOfProcs);
		String process = state.getProcessState(pid).name() + "(id=" + pid + ")";

		if (number_nprocs == null) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER, Certainty.PROVEABLE, process,
					"The number of processes for $waitall "
							+ "needs a concrete value.",
					symbolicUtil.stateToString(state), arguments.get(1)
							.getSource());

			this.errorLogger.reportError(err);
			return this.falseValue;
		} else {
			int numOfProcs_int = number_nprocs.intValue();
			BinaryExpression pointerAdd;
			CIVLSource procsSource = arguments.get(0).getSource();
			Evaluation eval;

			for (int i = 0; i < numOfProcs_int; i++) {
				Expression offSet = modelFactory.integerLiteralExpression(
						procsSource, BigInteger.valueOf(i));
				NumericExpression offSetV = universe.integer(i);
				SymbolicExpression procPointer, proc;
				int pidValue;

				pointerAdd = modelFactory.binaryExpression(procsSource,
						BINARY_OPERATOR.POINTER_ADD, arguments.get(0), offSet);
				eval = evaluator.pointerAdd(state, pid, process, pointerAdd,
						procsPointer, offSetV);
				procPointer = eval.value;
				state = eval.state;
				eval = evaluator.dereference(procsSource, state, process,
						procPointer, false);
				proc = eval.value;
				state = eval.state;
				pidValue = modelFactory.getProcessId(procsSource, proc);
				if (!state.getProcessState(pidValue).hasEmptyStack())
					return this.falseValue;
			}
		}
		return this.trueValue;
	}

	/**
	 * Computes the guard of $barrier_exit($barrier), i.e., when the
	 * corresponding cell of in_barrier array in $gbarrier is false.
	 * 
	 * @param state
	 * @param pid
	 * @param arguments
	 * @param argumentValues
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private BooleanExpression getBarrierExitGuard(State state, int pid,
			String process, List<Expression> arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		CIVLSource source = arguments.get(0).getSource();
		SymbolicExpression barrier = argumentValues[0];
		NumericExpression myPlace;
		SymbolicExpression barrierObj;
		SymbolicExpression gbarrier;
		SymbolicExpression gbarrierObj;
		Evaluation eval = evaluator.dereference(source, state, process,
				barrier, false);
		SymbolicExpression inBarrierArray;
		SymbolicExpression meInBarrier;

		state = eval.state;
		barrierObj = eval.value;
		myPlace = (NumericExpression) universe
				.tupleRead(barrierObj, zeroObject);
		gbarrier = universe.tupleRead(barrierObj, oneObject);
		eval = evaluator.dereference(source, state, process, gbarrier, false);
		state = eval.state;
		gbarrierObj = eval.value;
		inBarrierArray = universe.tupleRead(gbarrierObj, twoObject);
		meInBarrier = universe.arrayRead(inBarrierArray, myPlace);
		if (meInBarrier.isTrue())
			return universe.falseExpression();
		return universe.trueExpression();
	}

	/**
	 * <p>
	 * Generate the a predicate stands for the guard of $comm_dequeue(). To
	 * evaluate if there is any message available, we add 4 predicates which
	 * will cover all valid situations together and each individual predicate
	 * may cause different results. The 4 predicates are: <br>
	 * 1. (source == -1 && tag == -2) <br>
	 * 2. (source >= 0 && tag == -2) <br>
	 * 3. (source == -1 && tag >= 0 ) <br>
	 * 4. (source >= 0 && tag >= 0 )
	 * 
	 * The returned predicate will be in a form as: (predicate1 &&
	 * (evaluate(predicate1)) ||...|| predicate4 && (evaluate(predicate4)))
	 * </p>
	 * 
	 * @author Ziqing Luo
	 * @param state
	 *            The current state
	 * @param pid
	 *            The process id
	 * @param arguments
	 *            Expressions of arguments of the "$comm_dequeue()"function:
	 *            $comm, source, tag.
	 * @param argumentValues
	 *            Symbolic Expressions of arguments of the
	 *            "$comm_dequeue()"function.
	 * @return A predicate which is the guard of the function $comm_dequeue().
	 * @throws UnsatisfiablePathConditionException
	 */
	private BooleanExpression getDequeueGuard(State state, int pid,
			String process, List<Expression> arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression source = argumentValues[1];
		SymbolicExpression tag = argumentValues[2];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		SymbolicExpression dest;
		BooleanExpression sourceGTEzero, isAnySource, isAnyTag, tagGTEzero;
		BooleanExpression guard;
		List<BooleanExpression> predicates = new LinkedList<>();
		CIVLSource civlsource = arguments.get(0).getSource();
		Evaluation eval;

		eval = evaluator.dereference(civlsource, state, process, commHandle,
				false);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gcommHandle,
				false);
		state = eval.state;
		gcomm = eval.value;
		dest = universe.tupleRead(comm, zeroObject);
		sourceGTEzero = universe.lessThanEquals(zero,
				(NumericExpression) source);
		tagGTEzero = universe.lessThanEquals(zero, (NumericExpression) tag);
		isAnySource = universe.equals(source, minusOne);
		isAnyTag = universe.equals(tag, minusTwo);
		predicates = new LinkedList<>();
		predicates.add(universe.and(isAnySource, isAnyTag));
		predicates.add(universe.and(isAnySource, tagGTEzero));
		predicates.add(universe.and(sourceGTEzero, isAnyTag));
		predicates.add(universe.and(sourceGTEzero, tagGTEzero));
		guard = dequeueGuardGenerator(civlsource, state, predicates, gcomm,
				source, dest, tag);
		return guard;
	}

	/**
	 * <p>
	 * This function checks all message channels (messages receiving buffers) to
	 * seek for available sources. If there are at least one message specified
	 * by "tag" argument in the channel specified by the "source" argument(and
	 * other arguments of course, but here only "source" will make any
	 * difference), the "source" is available.
	 * </p>
	 * <p>
	 * Precondition: The "predicate" argument shall be able to determine weather
	 * the "source" or "tag" is wild card or valid specific symbolic expression.
	 * </p>
	 * 
	 * @author Ziqing Luo
	 * @param predicate
	 *            Context conditions which helps determining weather the source
	 *            or tag is a wild card. This argument shall be able to
	 *            certainly prove: if the "source" belongs to [0, infinity) or
	 *            {-1} and if the "tag" belongs to [0, infinity) or {-2}.
	 * @param gcomm
	 *            The global communicator
	 * @param source
	 *            The argument "source" which indicates some message
	 *            channels(message queue in our implementation).
	 * @param dest
	 *            The argument "dest" which indicates the receiving process
	 *            itself.
	 * @param tag
	 *            The argument "tag" which indicates some messages have the same
	 *            tag.
	 * @param civlsource
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	List<SymbolicExpression> getAllPossibleSources(State state,
			BooleanExpression predicate, SymbolicExpression gcomm,
			SymbolicExpression source, SymbolicExpression dest,
			SymbolicExpression tag, CIVLSource civlsource)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression buf;
		SymbolicExpression bufRow;
		SymbolicExpression queue;
		SymbolicExpression queueLength;
		SymbolicExpression messages = null;
		SymbolicExpression message = null;
		BooleanExpression newPathConditions = universe.and(predicate,
				state.getPathCondition());
		Reasoner reasoner = universe.reasoner(newPathConditions);
		IntegerNumber sourceNumber = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) source);
		IntegerNumber tagNumber = (IntegerNumber) reasoner
				.extractNumber((NumericExpression) tag);
		List<SymbolicExpression> results = new LinkedList<>();
		boolean isWildcardSource = false, isWildcardTag = false;

		if (newPathConditions.equals(universe.falseExpression()))
			return results;

		if (sourceNumber != null && sourceNumber.intValue() == -1)
			isWildcardSource = true;
		if (tagNumber != null && tagNumber.intValue() == -2)
			isWildcardTag = true;

		buf = universe.tupleRead(gcomm, universe.intObject(2));
		// non-wild card source and tag
		if (!isWildcardSource && !isWildcardTag) {
			BooleanExpression iterLTQueueLengthClaim;
			NumericExpression iter = universe.integer(0);

			bufRow = universe.arrayRead(buf, (NumericExpression) source);
			queue = universe.arrayRead(bufRow, (NumericExpression) dest);
			messages = universe.tupleRead(queue, oneObject);
			queueLength = universe.tupleRead(queue, zeroObject);
			iterLTQueueLengthClaim = universe.lessThan(iter,
					(NumericExpression) queueLength);
			while (reasoner.isValid(iterLTQueueLengthClaim)) {
				BooleanExpression tagMatchClaim;

				message = universe.arrayRead(messages, iter);
				tagMatchClaim = universe.equals(
						universe.tupleRead(message, twoObject), tag);
				if (reasoner.isValid(tagMatchClaim)) {
					results.add(source);
					break;
				}
				iter = universe.add(iter, one);
				iterLTQueueLengthClaim = universe.lessThan(iter,
						(NumericExpression) queueLength);
			}
		}// non-wild card source and any_tag
		else if (!isWildcardSource && isWildcardTag) {
			bufRow = universe.arrayRead(buf, (NumericExpression) source);
			queue = universe.arrayRead(bufRow, (NumericExpression) dest);
			messages = universe.tupleRead(queue, oneObject);
			queueLength = universe.tupleRead(queue, zeroObject);
			if (reasoner.isValid(universe.lessThan(zero,
					(NumericExpression) queueLength)))
				results.add(source);
		} // any source and non-wild card tag
		else if (isWildcardSource && !isWildcardTag) {
			NumericExpression nprocs = (NumericExpression) universe.tupleRead(
					gcomm, zeroObject);
			NumericExpression iter = universe.zeroInt();
			NumericExpression queueIter = universe.zeroInt();
			BooleanExpression queueIterLTlengthClaim;
			BooleanExpression iterLTnprocsClaim = universe.lessThan(iter,
					nprocs);

			while (reasoner.isValid(iterLTnprocsClaim)) {
				bufRow = universe.arrayRead(buf, iter);
				queue = universe.arrayRead(bufRow, (NumericExpression) dest);
				messages = universe.tupleRead(queue, oneObject);
				queueLength = universe.tupleRead(queue, zeroObject);
				queueIterLTlengthClaim = universe.lessThan(queueIter,
						(NumericExpression) queueLength);
				while (reasoner.isValid(queueIterLTlengthClaim)) {
					BooleanExpression tagMatchClaim;

					message = universe.arrayRead(messages, queueIter);
					tagMatchClaim = universe.equals(
							universe.tupleRead(message, twoObject), tag);
					if (reasoner.isValid(tagMatchClaim)) {
						results.add(iter);
						break;
					}
					queueIter = universe.add(queueIter, one);
					queueIterLTlengthClaim = universe.lessThan(queueIter,
							(NumericExpression) queueLength);
				}
				iter = universe.add(iter, one);
				iterLTnprocsClaim = universe.lessThan(iter, nprocs);
			}
		} else if (isWildcardSource && isWildcardTag) {
			NumericExpression nprocs = (NumericExpression) universe.tupleRead(
					gcomm, zeroObject);
			NumericExpression iter = universe.zeroInt();
			BooleanExpression iterLTnprocsClaim = universe.lessThan(iter,
					nprocs);

			while (reasoner.isValid(iterLTnprocsClaim)) {
				bufRow = universe.arrayRead(buf, iter);
				queue = universe.arrayRead(bufRow, (NumericExpression) dest);
				messages = universe.tupleRead(queue, oneObject);
				queueLength = universe.tupleRead(queue, zeroObject);
				if (reasoner.isValid(universe.lessThan(zero,
						(NumericExpression) queueLength))) {
					results.add(iter);
				}
				iter = universe.add(iter, one);
				iterLTnprocsClaim = universe.lessThan(iter, nprocs);
			}
		}
		return results;
	}

	/**
	 * Computes the guard of $wait.
	 * 
	 * @param state
	 * @param pid
	 * @param arguments
	 * @param argumentValues
	 * @return
	 */
	private BooleanExpression getWaitGuard(State state, int pid,
			List<Expression> arguments, SymbolicExpression[] argumentValues) {
		SymbolicExpression joinProcess = argumentValues[0];
		BooleanExpression guard;
		int pidValue;
		Expression joinProcessExpr = arguments.get(0);

		if (joinProcess.operator() != SymbolicOperator.CONCRETE) {
			String process = state.getProcessState(pid).name() + "(id=" + pid
					+ ")";
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER, Certainty.PROVEABLE, process,
					"The argument of $wait should be concrete, but the actual value is "
							+ joinProcess + ".",
					symbolicUtil.stateToString(state),
					joinProcessExpr.getSource());

			this.errorLogger.reportError(err);
		}
		pidValue = modelFactory.getProcessId(joinProcessExpr.getSource(),
				joinProcess);
		if (modelFactory.isPocessIdDefined(pidValue)
				&& !modelFactory.isProcessIdNull(pidValue)
				&& !state.getProcessState(pidValue).hasEmptyStack())
			guard = universe.falseExpression();
		else
			guard = universe.trueExpression();
		return guard;
	}
	/**
	 * <p>
	 * Combining the given predicates and the results of evaluation on those
	 * predicates for the <code>$comm_dequeue()</code>.
	 * </p>
	 * 
	 * @author Ziqing Luo
	 * @param civlsource
	 *            The CIVL program source of the statement.
	 * @param state
	 *            Current state
	 * @param predicates
	 *            The set of predicates
	 * @param gcomm
	 *            The global communicator
	 * @param source
	 *            The source from where "$comm_dequeue" receives messages.
	 * @param dest
	 *            The destination which is the receiver itself
	 * @param tag
	 *            The message tag
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private BooleanExpression dequeueGuardGenerator(CIVLSource civlsource,
			State state, Iterable<BooleanExpression> predicates,
			SymbolicExpression gcomm, SymbolicExpression source,
			SymbolicExpression dest, SymbolicExpression tag)
			throws UnsatisfiablePathConditionException {
		Iterator<BooleanExpression> predIter = predicates.iterator();
		BooleanExpression predicate;
		BooleanExpression guardComponent;
		BooleanExpression guard = universe.falseExpression();
		BooleanExpression hasMsg;

		do {
			predicate = predIter.next();
			hasMsg = universe.bool(!getAllPossibleSources(state, predicate,
					gcomm, source, dest, tag, civlsource).isEmpty());
			guardComponent = universe.and(predicate, hasMsg);
			guard = universe.or(guard, guardComponent);
		} while (predIter.hasNext());

		return guard;
	}
}
