package edu.udel.cis.vsl.civl.library.concurrency;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.dynamic.IF.SymbolicUtility;
import edu.udel.cis.vsl.civl.library.common.BaseLibraryExecutor;
import edu.udel.cis.vsl.civl.log.IF.CIVLExecutionException;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.ModelConfiguration;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryEvaluatorLoader;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;
import edu.udel.cis.vsl.civl.semantics.IF.SymbolicAnalyzer;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SARLException;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.number.Number;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.expr.Expressions;

public class LibconcurrencyExecutor extends BaseLibraryExecutor implements
		LibraryExecutor {

	/* **************************** Constructors *************************** */

	/**
	 * Creates a new instance of the library executor for concurrency.cvh.
	 * 
	 * @param name
	 *            The name of the library, which is concurrency.
	 * @param primaryExecutor
	 *            The executor for normal CIVL execution.
	 * @param modelFactory
	 *            The model factory of the system.
	 * @param symbolicUtil
	 *            The symbolic utility to be used.
	 * @param civlConfig
	 *            The CIVL configuration configured by the user.
	 */
	public LibconcurrencyExecutor(String name, Executor primaryExecutor,
			ModelFactory modelFactory, SymbolicUtility symbolicUtil,
			SymbolicAnalyzer symbolicAnalyzer, CIVLConfiguration civlConfig,
			LibraryExecutorLoader libExecutorLoader,
			LibraryEvaluatorLoader libEvaluatorLoader) {
		super(name, primaryExecutor, modelFactory, symbolicUtil,
				symbolicAnalyzer, civlConfig, libExecutorLoader,
				libEvaluatorLoader);
	}

	/* ******************** Methods from LibraryExecutor ******************* */

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, statement);
	}

	/* ************************** Private Methods ************************** */

	/**
	 * Executes a system function call, updating the left hand side expression
	 * with the returned value if any.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param call
	 *            The function call statement to be executed.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeWork(State state, int pid, CallOrSpawnStatement call)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		LHSExpression lhs;
		int numArgs;
		String process = state.getProcessState(pid).name() + "(id=" + pid + ")";

		numArgs = call.arguments().size();
		name = call.function().name();
		lhs = call.lhs();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = call.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "$barrier_create":
			state = executeBarrierCreate(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$barrier_enter":
			state = executeBarrierEnter(state, pid, process, arguments,
					argumentValues);
			break;
		case "$barrier_exit":
			// does nothing
			break;
		case "$gbarrier_create":
			state = executeGbarrierCreate(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		case "$barrier_destroy":
		case "$gbarrier_destroy":
			state = executeFree(state, pid, process, arguments, argumentValues,
					call.getSource());
			break;
		case "$gcollect_checker_create":
			state = executeGcollectCheckerCreate(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$gcollect_checker_destroy":
			state = executeGcollectCheckerDestroy(state, pid, process,
					arguments, argumentValues, call.getSource());
			break;
		case "$collect_checker_create":
			state = executeCollectCheckerCreate(state, pid, process, lhs,
					arguments, argumentValues, call.getSource());
			break;
		case "$collect_checker_destroy":
			state = this.executeFree(state, pid, process, arguments,
					argumentValues, call.getSource());
			break;
		case "$collect_check":
			state = executeCollectCheck(state, pid, process, lhs, arguments,
					argumentValues, call.getSource());
			break;
		default:
			throw new CIVLUnimplementedFeatureException("the function " + name
					+ " of library concurrency.cvh", call.getSource());
		}
		state = stateFactory.setLocation(state, pid, call.target(),
				call.lhs() != null);
		return state;
	}

	/**
	 * Creates a new local communicator object and returns a handle to it. The
	 * new communicator will be affiliated with the specified global
	 * communicator. This local communicator handle will be used as an argument
	 * in most message-passing functions. The place must be in [0,size-1] and
	 * specifies the place in the global communication universe that will be
	 * occupied by the local communicator. The local communicator handle may be
	 * used by more than one process, but all of those processes will be viewed
	 * as occupying the same place. Only one call to $comm_create may occur for
	 * each gcomm-place pair. The new object will be allocated in the given
	 * scope.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param lhs
	 *            The left hand side expression of the call, which is to be
	 *            assigned with the returned value of the function call. If NULL
	 *            then no assignment happens.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBarrierCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression gbarrier = argumentValues[1];
		SymbolicExpression place = argumentValues[2];
		SymbolicExpression gbarrierObj;
		SymbolicExpression barrierObj;
		SymbolicExpression procMapArray;
		LinkedList<SymbolicExpression> barrierComponents = new LinkedList<>();
		CIVLSource civlsource = arguments[1].getSource();
		CIVLType barrierType = typeFactory
				.systemType(ModelConfiguration.BARRIER_TYPE);
		Evaluation eval;
		int place_num = ((IntegerNumber) universe
				.extractNumber((NumericExpression) place)).intValue();
		NumericExpression totalPlaces;
		BooleanExpression claim;
		Reasoner reasoner = universe.reasoner(state.getPathCondition());
		ResultType resultType;

		if (place_num < 0) {
			throw new CIVLExecutionException(ErrorKind.OTHER,
					Certainty.CONCRETE, process, "Invalid place " + place_num
							+ " used in $barrier_create().", source);
		}
		eval = this.evaluator.dereference(civlsource, state, process, gbarrier,
				false);
		state = eval.state;
		gbarrierObj = eval.value;
		totalPlaces = (NumericExpression) universe.tupleRead(gbarrierObj,
				zeroObject);
		claim = universe.lessThanEquals(universe.integer(place_num),
				totalPlaces);
		resultType = reasoner.valid(claim).getResultType();
		if (!resultType.equals(ResultType.YES)) {
			CIVLExecutionException err = new CIVLExecutionException(
					ErrorKind.OTHER,
					Certainty.CONCRETE,
					process,
					"Place "
							+ place_num
							+ " used in $barrier_create() exceeds the size of the $gbarrier.",
					source);

			this.errorLogger.reportError(err);
			this.errorLogger
					.logError(
							source,
							state,
							process,
							symbolicAnalyzer.stateToString(state),
							claim,
							resultType,
							ErrorKind.OTHER,
							"Place "
									+ place_num
									+ " used in $barrier_create() exceeds the size of the $gbarrier.");
		}
		procMapArray = universe.tupleRead(gbarrierObj, oneObject);
		if (!universe.arrayRead(procMapArray, (NumericExpression) place)
				.equals(modelFactory.nullProcessValue())) {
			throw new CIVLExecutionException(ErrorKind.OTHER,
					Certainty.CONCRETE, process,
					"Attempt to create a barrier using an invalid place.",
					source);
		}

		// TODO report an error if the place exceeds the size of the
		// communicator
		procMapArray = universe.arrayWrite(procMapArray,
				(NumericExpression) place, modelFactory.processValue(pid));
		gbarrierObj = universe.tupleWrite(gbarrierObj, oneObject, procMapArray);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gbarrier, gbarrierObj);
		// builds barrier object
		barrierComponents.add(place);
		barrierComponents.add(gbarrier);
		barrierObj = universe.tuple(
				(SymbolicTupleType) barrierType.getDynamicType(universe),
				barrierComponents);
		state = this.primaryExecutor.malloc(civlsource, state, pid, process,
				lhs, scopeExpression, scope, barrierType, barrierObj);
		return state;
	}

	/**
	 * Adds the message to the appropriate message queue in the communication
	 * universe specified by the comm. The source of the message must equal the
	 * place of the comm.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeBarrierEnter(State state, int pid, String process,
			Expression[] arguments, SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = arguments[0].getSource();
		SymbolicExpression barrier = argumentValues[0];
		SymbolicExpression barrierObj;
		SymbolicExpression gbarrier;
		SymbolicExpression gbarrierObj;
		SymbolicExpression inBarrierArray;
		SymbolicExpression nprocs;
		NumericExpression myPlace;
		SymbolicExpression numInBarrier;
		Evaluation eval;
		int numInBarrier_int;
		int nprocs_int;

		eval = evaluator
				.dereference(civlsource, state, process, barrier, false);
		state = eval.state;
		barrierObj = eval.value;
		myPlace = (NumericExpression) universe
				.tupleRead(barrierObj, zeroObject);
		gbarrier = universe.tupleRead(barrierObj, oneObject);
		eval = evaluator.dereference(civlsource, state, process, gbarrier,
				false);
		state = eval.state;
		gbarrierObj = eval.value;
		nprocs = universe.tupleRead(gbarrierObj, zeroObject);
		inBarrierArray = universe.tupleRead(gbarrierObj, twoObject);
		numInBarrier = universe.tupleRead(gbarrierObj, threeObject);
		nprocs_int = symbolicUtil.extractInt(civlsource,
				(NumericExpression) nprocs);
		numInBarrier_int = symbolicUtil.extractInt(civlsource,
				(NumericExpression) numInBarrier);
		numInBarrier_int++;
		if (numInBarrier_int == nprocs_int) {
			LinkedList<SymbolicExpression> inBarrierComponents = new LinkedList<>();

			for (int i = 0; i < nprocs_int; i++) {
				inBarrierComponents.add(universe.falseExpression());
			}
			inBarrierArray = universe.array(universe.booleanType(),
					inBarrierComponents);
			numInBarrier = zero;
		} else {
			numInBarrier = universe.integer(numInBarrier_int);
			inBarrierArray = universe.arrayWrite(inBarrierArray, myPlace,
					universe.trueExpression());
		}
		gbarrierObj = universe.tupleWrite(gbarrierObj, this.twoObject,
				inBarrierArray);
		gbarrierObj = universe.tupleWrite(gbarrierObj, this.threeObject,
				numInBarrier);
		state = this.primaryExecutor.assign(civlsource, state, process,
				gbarrier, gbarrierObj);
		return state;
	}

	/**
	 * Creates a new global barrier object and returns a handle to it. The
	 * global barrier will have size number of processes. The global barrier
	 * defines a barrier "universe" and encompasses the status of processes
	 * associated with the barrier. The new object will be allocated in the
	 * given scope.
	 * 
	 * typedef struct __gbarrier__ { int nprocs; _Bool in_barrier[]; //
	 * initialized as all false. int num_in_barrier; // initialized as 0. } *
	 * $gbarrier;
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The ID of the process that the function call belongs to.
	 * @param lhs
	 *            The left hand side expression of the call, which is to be
	 *            assigned with the returned value of the function call. If NULL
	 *            then no assignment happens.
	 * @param arguments
	 *            The static representation of the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The dynamic representation of the arguments of the function
	 *            call.
	 * @param source
	 *            The source code element to be used for error report.
	 * @return The new state after executing the function call.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeGbarrierCreate(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression gbarrierObj;
		NumericExpression nprocs = (NumericExpression) argumentValues[1];
		SymbolicExpression numInBarrier = universe.integer(0);
		SymbolicExpression scope = argumentValues[0];
		Expression scopeExpression = arguments[0];
		SymbolicExpression procMapArray;
		SymbolicExpression inBarrierArray;
		CIVLType gbarrierType = typeFactory
				.systemType(ModelConfiguration.GBARRIER_TYPE);
		BooleanExpression context = state.getPathCondition();

		inBarrierArray = symbolicUtil.newArray(context, universe.booleanType(),
				nprocs, this.falseValue);
		procMapArray = symbolicUtil.newArray(context,
				typeFactory.processSymbolicType(), nprocs,
				modelFactory.nullProcessValue());
		gbarrierObj = universe.tuple((SymbolicTupleType) gbarrierType
				.getDynamicType(universe), Arrays.asList(nprocs, procMapArray,
				inBarrierArray, numInBarrier));
		state = primaryExecutor.malloc(source, state, pid, process, lhs,
				scopeExpression, scope, gbarrierType, gbarrierObj);
		return state;
	}

	/**
	 * Executes the system function
	 * <code>$gcollect_checker $gcollect_checker_create($scope scope);</code>,
	 * it creates a <code>$gcollect_checker</code> object and returns a handle
	 * to that object.
	 * 
	 * @param state
	 *            The current state
	 * @param pid
	 *            The PID of the process
	 * @param process
	 *            The {@link String} identifier of the process
	 * @param lhs
	 *            The Left-hand side expression
	 * @param arguments
	 *            {@link Expressions} of arguments of the function call
	 * @param argumentValues
	 *            {@link SymbolicExpressions} of arguments of the function call
	 * @param source
	 *            {@link CIVLSource} of the function call statement
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeGcollectCheckerCreate(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		// incomplete $collect_record array
		SymbolicExpression imcompRecordsArray;
		SymbolicExpression gcollectChecker;
		CIVLType gcollectCheckerType;
		CIVLType collectRecordType;

		gcollectCheckerType = this.typeFactory
				.systemType(ModelConfiguration.GCOLLECT_CHECKER_TYPE);
		collectRecordType = this.typeFactory
				.systemType(ModelConfiguration.COLLECT_RECORD_TYPE);
		imcompRecordsArray = universe.emptyArray(collectRecordType
				.getDynamicType(universe));
		// make initial values of fields of gcollect_checker ready
		gcollectChecker = universe.tuple(
				(SymbolicTupleType) gcollectCheckerType
						.getDynamicType(universe), Arrays.asList(zero,
						imcompRecordsArray));
		state = this.primaryExecutor.malloc(source, state, pid, process, lhs,
				arguments[0], scope, gcollectCheckerType, gcollectChecker);
		return state;
	}

	private State executeCollectCheckerCreate(State state, int pid,
			String process, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		SymbolicExpression gchecker = argumentValues[1];
		SymbolicExpression checker;
		CIVLType collectCheckerType;

		collectCheckerType = typeFactory
				.systemType(ModelConfiguration.COLLECT_CHECKER_TYPE);
		checker = universe
				.tuple((SymbolicTupleType) collectCheckerType
						.getDynamicType(universe), Arrays.asList(gchecker));
		state = primaryExecutor.malloc(source, state, pid, process, lhs,
				arguments[0], scope, collectCheckerType, checker);
		return state;
	}

	private State executeGcollectCheckerDestroy(State state, int pid,
			String process, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression gcheckerHandle = argumentValues[0];
		SymbolicExpression gchecker;
		NumericExpression records_length;
		BooleanExpression claim;
		ResultType resultType;
		Reasoner reasoner;
		Evaluation eval;

		eval = evaluator.dereference(arguments[0].getSource(), state, process,
				gcheckerHandle, false);
		state = eval.state;
		gchecker = eval.value;
		records_length = (NumericExpression) universe.tupleRead(gchecker,
				zeroObject);
		reasoner = universe.reasoner(state.getPathCondition());
		claim = universe.equals(records_length, zero);
		resultType = reasoner.valid(claim).getResultType();
		if (!resultType.equals(ResultType.YES)) {
			errorLogger
					.logError(
							source,
							state,
							process,
							symbolicAnalyzer.stateToString(state),
							claim,
							resultType,
							ErrorKind.MPI_ERROR,
							"There are records remaining in the collective operation checker which means collective "
									+ "operations are not executed right for all processes.\n");
			// TODO: is always MPI error ?
		}
		state = this.executeFree(state, pid, process, arguments,
				argumentValues, source);
		return state;
	}

	private State executeCollectCheck(State state, int pid, String process,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues, CIVLSource source)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression checkhandle = argumentValues[0];
		SymbolicExpression place = argumentValues[1];
		SymbolicExpression nprocs = argumentValues[2];
		SymbolicExpression routine_tag = argumentValues[3];
		SymbolicExpression root = argumentValues[4];
		SymbolicExpression numTypes = argumentValues[5];
		SymbolicExpression typesPtr = argumentValues[6];
		SymbolicExpression types;
		SymbolicExpression check, gcheckHandle, gcheck;
		SymbolicExpression records, tail_record;
		SymbolicExpression marksArray; // marks array of a record
		SymbolicExpression modifiedRecord = null;
		BooleanExpression markedElement; // element of a marks array
		BooleanExpression claim;
		NumericExpression records_length;
		NumericExpression numMarked; // number of marked processes in one record
		Number temp;
		IntegerNumber int_numTypes;
		Reasoner reasoner;
		Evaluation eval;
		ResultType resultType;

		// Decides "numTypes", it must be a concrete number.
		reasoner = universe.reasoner(state.getPathCondition());
		temp = reasoner.extractNumber((NumericExpression) numTypes);
		if (temp == null)
			throw new CIVLInternalException(
					"Collective operation checker must know the number of datatypes of a record.\n",
					source);
		int_numTypes = (IntegerNumber) temp;
		assert int_numTypes.intValue() > 0 && int_numTypes.intValue() < 3 : "CIVL currently only support 1 or 2 "
				+ "datatypes in one collective record. (e.g. MPI_Alltoallw() is not supported)\n";
		eval = evaluator.dereference(source, state, process, typesPtr, false);
		state = eval.state;
		types = eval.value;
		// Step 1: If the process if the first process to create a new record ?
		// By checking if the process is marked in the record in tail
		eval = evaluator
				.dereference(source, state, process, checkhandle, false);
		state = eval.state;
		check = eval.value;
		gcheckHandle = universe.tupleRead(check, zeroObject);
		eval = evaluator.dereference(source, state, process, gcheckHandle,
				false);
		state = eval.state;
		gcheck = eval.value;
		records_length = (NumericExpression) universe.tupleRead(gcheck,
				zeroObject);
		claim = universe.equals(records_length, zero);
		resultType = reasoner.valid(claim).getResultType();
		records = universe.tupleRead(gcheck, oneObject);
		if (!resultType.equals(ResultType.YES)) {
			tail_record = universe.arrayRead(records,
					universe.subtract(records_length, one));
			marksArray = universe.tupleRead(tail_record, universe.intObject(6));
			markedElement = (BooleanExpression) universe.arrayRead(marksArray,
					(NumericExpression) place);
			resultType = reasoner.valid(markedElement).getResultType();
		}
		if (resultType.equals(ResultType.YES)) {
			// create a new record and insert into the checker (which actually
			// has a record queue)
			SymbolicExpression newRecord;
			SymbolicExpression newMarks;
			List<SymbolicExpression> newRecordComponents = new LinkedList<>();
			CIVLType collectRecordType = typeFactory
					.systemType(ModelConfiguration.COLLECT_RECORD_TYPE);

			newMarks = symbolicUtil.newArray(state.getPathCondition(),
					universe.booleanType(), (NumericExpression) nprocs,
					this.falseValue);
			newMarks = universe.arrayWrite(newMarks, (NumericExpression) place,
					this.trueValue);
			newRecordComponents.add(routine_tag);
			newRecordComponents.add(root);
			newRecordComponents.add(numTypes);
			if (int_numTypes.intValue() == 1)
				newRecordComponents.add(universe.arrayRead(types, zero));
			else {
				newRecordComponents.add(universe.integer(-1));
				newRecordComponents.add(universe.arrayRead(types, zero));
				newRecordComponents.add(universe.arrayRead(types, one));
			}
			newRecordComponents.add(newMarks);
			newRecordComponents.add(one);
			newRecord = universe.tuple((SymbolicTupleType) collectRecordType
					.getDynamicType(universe), newRecordComponents);
			// insert new record, skip records-match checking, then it's
			// necessary to check if it gonna dequeue (case:nprocs == 1)
			records = universe.append(records, newRecord);
			records_length = universe.add(records_length, one);
			modifiedRecord = newRecord;
		} else {
			// The process is not the first one to the record, then check if the
			// record is matched and marked itself
			SymbolicExpression unmarked_record = null;
			NumericExpression loopIdf = zero; // symbolic loop identifier
			boolean isMarked = true;
			boolean isMatched = true;

			while (isMarked) {
				try {
					unmarked_record = universe.arrayRead(records, loopIdf);
					marksArray = universe.tupleRead(
							unmarked_record, universe.intObject(6));
					markedElement = (BooleanExpression) universe.arrayRead(
							marksArray, (NumericExpression) place);
					if (reasoner.valid(markedElement).getResultType()
							.equals(ResultType.NO))
						isMarked = false;
					else
						loopIdf = universe.add(loopIdf, one);
				} catch (SARLException e) {
					throw new CIVLInternalException(
							"Unexpected Collective operation checking exception.\n",
							source);
				}
			}
			assert unmarked_record != null : "Unexpected Collective operation checking exception.\n";
			claim = universe.equals(
					universe.tupleRead(unmarked_record, zeroObject),
					routine_tag);
			resultType = reasoner.valid(claim).getResultType();
			if (resultType.equals(ResultType.YES)) {
				claim = universe.equals(
						universe.tupleRead(unmarked_record, oneObject), root);
				resultType = reasoner.valid(claim).getResultType();
				if (resultType.equals(ResultType.YES)) {
					BooleanExpression extraClaim;

					claim = universe
							.equals(universe.tupleRead(unmarked_record,
									twoObject), one);
					if (int_numTypes.intValue() == 1) {
						extraClaim = universe.equals(
								universe.tupleRead(unmarked_record,
										universe.intObject(3)),
								universe.arrayRead(types, zero));
						claim = extraClaim;
					} else {
						claim = universe.equals(
								universe.tupleRead(unmarked_record,
										universe.intObject(4)),
								universe.arrayRead(types, zero));
						extraClaim = universe.equals(
								universe.tupleRead(unmarked_record,
										universe.intObject(5)),
								universe.arrayRead(types, one));
						claim = universe.and(claim, extraClaim);
					}
					resultType = reasoner.valid(claim).getResultType();
					if (!resultType.equals(ResultType.YES))
						isMatched = false;
				} else
					isMatched = false;
			} else
				isMatched = false;
			if (!isMatched) {
				// checking un-passed
				errorLogger.logError(source, state, process,
						symbolicAnalyzer.stateToString(state), claim,
						resultType, ErrorKind.MPI_ERROR,
						"Collective operation mismatched.\n");
			} else {
				SymbolicExpression marked_record;
				// checking passed, mark process itself

				marksArray = universe.tupleRead(
						unmarked_record, universe.intObject(6));
				marksArray = universe.arrayWrite(marksArray,
						(NumericExpression) place, trueValue);
				numMarked = (NumericExpression) universe.tupleRead(
						unmarked_record, universe.intObject(7));
				numMarked = universe.add(numMarked, one);
				marked_record = universe.tupleWrite(unmarked_record,
						universe.intObject(6), marksArray);
				marked_record = universe.tupleWrite(marked_record,
						universe.intObject(7), numMarked);
				records = universe.arrayWrite(records, loopIdf, marked_record);
				modifiedRecord = marked_record;
			}
		}
		// Step 2: check if it needs to dequeue a record
		assert modifiedRecord != null : "Internal error";
		numMarked = (NumericExpression) universe.tupleRead(modifiedRecord,
				universe.intObject(7));
		claim = universe.equals(numMarked, nprocs);
		resultType = reasoner.valid(claim).getResultType();
		assert !resultType.equals(ResultType.MAYBE) : "Number of marked processes in record should be concrete.";
		if (resultType.equals(ResultType.YES)) {
			records = universe.removeElementAt(records, 0);
			records_length = universe.subtract(records_length, one);
		}
		gcheck = universe.tupleWrite(gcheck, this.zeroObject, records_length);
		gcheck = universe.tupleWrite(gcheck, oneObject, records);
		state = primaryExecutor.assign(source, state, process, gcheckHandle,
				gcheck);
		return state;
	}
}
