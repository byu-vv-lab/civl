package edu.udel.cis.vsl.civl.library.civlc;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.kripke.Enabler;
import edu.udel.cis.vsl.civl.library.CommonLibraryEnabler;
import edu.udel.cis.vsl.civl.library.IF.LibraryEnabler;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluation;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class LibcivlcEnabler extends CommonLibraryEnabler implements
		LibraryEnabler {

	public LibcivlcEnabler(Enabler primaryEnabler, PrintStream output,
			ModelFactory modelFactory) {
		super(primaryEnabler, output, modelFactory);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "civlc";
	}

	@Override
	public Set<Integer> ampleSet(State state, int pid, Statement statement,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap) {
		Identifier name;
		CallOrSpawnStatement call;

		if (!(statement instanceof CallOrSpawnStatement)) {
			throw new CIVLInternalException("Unsupported statement for civlc",
					statement);
		}
		call = (CallOrSpawnStatement) statement;
		name = call.function().name();

		switch (name.name()) {
		case "$comm_enqueue":
		case "$comm_dequeue":
			return ampleSetWork(state, pid, call, reachableMemUnitsMap);
		default:
			return super.ampleSet(state, pid, statement, reachableMemUnitsMap);
		}
	}

	private Set<Integer> ampleSetWork(State state, int pid,
			CallOrSpawnStatement call,
			Map<Integer, Map<SymbolicExpression, Boolean>> reachableMemUnitsMap) {
		int numArgs;
		numArgs = call.arguments().size();
		Expression[] arguments;
		SymbolicExpression[] argumentValues;

		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval = null;

			arguments[i] = call.arguments().get(i);
			try {
				eval = evaluator.evaluate(state, pid, arguments[i]);
			} catch (UnsatisfiablePathConditionException e) {
				return new HashSet<>();
			}
			argumentValues[i] = eval.value;
			state = eval.state;
		}

		switch (call.function().name().name()) {
		case "$comm_enqueue":
		case "$comm_dequeue":
			Set<Integer> ampleSet = new HashSet<>();

			for (int otherPid : reachableMemUnitsMap.keySet()) {
				if (otherPid == pid)
					continue;
				else if (reachableMemUnitsMap.get(otherPid).containsKey(
						argumentValues[0])) {
					ampleSet.add(otherPid);
				}
			}
			return ampleSet;
		default:
		}
		return new HashSet<>();
	}

	@Override
	public Evaluation getGuard(CIVLSource source, State state, int pid,
			CallOrSpawnStatement call) {
		SymbolicExpression[] argumentValues;
		int numArgs;
		BooleanExpression guard;
		List<Expression> arguments = call.arguments();
		String function = call.function().name().name();

		numArgs = call.arguments().size();
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
				guard = getDequeueGuard(state, pid, arguments, argumentValues);
			} catch (UnsatisfiablePathConditionException e) {
				// the error that caused the unsatifiable path condition should
				// already have been reported.
				return new Evaluation(state, universe.falseExpression());
			}
			break;
		case "$comm_add":
		case "free":
		case "$bundle_pack":
		case "$bundle_unpack":
		case "$bundle_size":
		case "$comm_create":
		case "$comm_enqueue":
		case "printf":
		case "$exit":
		case "$memcpy":
		case "$message_pack":
		case "$message_source":
		case "$message_tag":
		case "$message_dest":
		case "$message_size":
		case "$message_unpack":
		case "$comm_destroy":
		case "$comm_size":
		case "$comm_probe":
		case "$comm_seek":
		case "$comm_chan_size":
		case "$comm_total_size":
		case "$gcomm_create":
		case "$heap_create":
		case "$scope_parent":
			guard = universe.trueExpression();
			break;
		case "$wait":
			guard = getWaitGuard(state, pid, arguments, argumentValues);
			break;
		default:
			throw new CIVLInternalException("Unknown civlc function: "
					+ function, source);
		}
		return new Evaluation(state, guard);
	}

	/**
	 * TODO
	 * 
	 * @param state
	 * @param pid
	 * @param arguments
	 *            $comm, source, tag
	 * @param argumentValues
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private BooleanExpression getDequeueGuard(State state, int pid,
			List<Expression> arguments, SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression commHandle = argumentValues[0];
		SymbolicExpression source = argumentValues[1];
		SymbolicExpression tag = argumentValues[2];
		SymbolicExpression comm;
		SymbolicExpression gcommHandle;
		SymbolicExpression gcomm;
		SymbolicExpression dest;
		SymbolicExpression newMessage;
		CIVLSource civlsource = arguments.get(0).getSource();
		boolean enabled = false;
		Evaluation eval;

		eval = evaluator.dereference(civlsource, state, commHandle);
		state = eval.state;
		comm = eval.value;
		gcommHandle = universe.tupleRead(comm, oneObject);
		eval = evaluator.dereference(civlsource, state, gcommHandle);
		state = eval.state;
		gcomm = eval.value;
		dest = universe.tupleRead(comm, zeroObject);
		newMessage = this.getMatchedMessageFromGcomm(pid, gcomm, source, dest,
				tag, civlsource);
		if (newMessage != null)
			enabled = true;
		return universe.bool(enabled);
	}

	/**
	 * TODO: internal function
	 * 
	 * @param pid
	 * @param gcomm
	 * @param source
	 * @param dest
	 * @param tag
	 * @param civlsource
	 * @param returnMessages
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private SymbolicExpression getMatchedMessageFromGcomm(int pid,
			SymbolicExpression gcomm, SymbolicExpression source,
			SymbolicExpression dest, SymbolicExpression tag,
			CIVLSource civlsource) throws UnsatisfiablePathConditionException {
		SymbolicExpression buf;
		SymbolicExpression bufRow;
		SymbolicExpression queue;
		SymbolicExpression queueLength;
		SymbolicExpression messages = null;
		SymbolicExpression nprocs;
		SymbolicExpression message = null;
		int int_source = evaluator.extractInt(civlsource,
				(NumericExpression) source);
		int int_tag = evaluator.extractInt(civlsource, (NumericExpression) tag);
		int int_queueLength;
		int int_nprocs;

		buf = universe.tupleRead(gcomm, universe.intObject(2));
		nprocs = universe.tupleRead(gcomm, zeroObject);
		int_nprocs = evaluator.extractInt(civlsource,
				(NumericExpression) nprocs);
		// specific source and tag
		if (int_source >= 0 && int_tag >= 0) {
			bufRow = universe.arrayRead(buf, (NumericExpression) source);
			queue = universe.arrayRead(bufRow, (NumericExpression) dest);
			messages = universe.tupleRead(queue, oneObject);
			queueLength = universe.tupleRead(queue, zeroObject);
			int_queueLength = evaluator.extractInt(civlsource,
					(NumericExpression) queueLength);
			for (int i = 0; i < int_queueLength; i++) {
				message = universe.arrayRead(messages, universe.integer(i));
				if (universe.tupleRead(message, universe.intObject(2)).equals(
						tag))
					break;
				else
					message = null;
			}
		}
		// specific source but random tag
		if (int_source >= 0 && int_tag == -2) {
			bufRow = universe.arrayRead(buf, (NumericExpression) source);
			queue = universe.arrayRead(bufRow, (NumericExpression) dest);
			messages = universe.tupleRead(queue, oneObject);
			queueLength = universe.tupleRead(queue, zeroObject);
			int_queueLength = evaluator.extractInt(civlsource,
					(NumericExpression) queueLength);
			if (int_queueLength > 0)
				message = universe.arrayRead(messages, zero);
		}
		// random source but specific tag
		if (int_source == -1 && int_tag >= 0) {
			boolean hasTag = false;

			for (int i = 0; i < int_nprocs; i++) {
				bufRow = universe.arrayRead(buf, universe.integer(i));
				queue = universe.arrayRead(bufRow, (NumericExpression) dest);
				messages = universe.tupleRead(queue, oneObject);
				queueLength = universe.tupleRead(queue, zeroObject);
				int_queueLength = evaluator.extractInt(civlsource,
						(NumericExpression) queueLength);
				for (int j = 0; j < int_queueLength; j++) {
					message = universe.arrayRead(messages, universe.integer(j));
					if (universe.tupleRead(message, universe.intObject(2))
							.equals(tag)) {
						hasTag = true;
						break;
					} else
						message = null;
				}
				if (hasTag)
					break;
			}
		}
		// random source and tag
		if (int_source == -1 && int_tag == -2) {
			for (int i = 0; i < int_nprocs; i++) {
				bufRow = universe.arrayRead(buf, universe.integer(i));
				queue = universe.arrayRead(bufRow, (NumericExpression) dest);
				messages = universe.tupleRead(queue, oneObject);
				queueLength = universe.tupleRead(queue, zeroObject);
				int_queueLength = evaluator.extractInt(civlsource,
						(NumericExpression) queueLength);
				if (int_queueLength > 0) {
					message = universe.arrayRead(messages, zero);
					break;
				}
			}
		}
		return message;
	}

	private BooleanExpression getWaitGuard(State state, int pid,
			List<Expression> arguments, SymbolicExpression[] argumentValues) {
		SymbolicExpression joinProcess = argumentValues[0];
		BooleanExpression guard;
		int pidValue;
		Expression joinProcessExpr = arguments.get(0);

		pidValue = modelFactory.getProcessId(joinProcessExpr.getSource(),
				joinProcess);
		if (!state.getProcessState(pidValue).hasEmptyStack())
			guard = universe.falseExpression();
		else
			guard = universe.trueExpression();
		return guard;
	}

}
