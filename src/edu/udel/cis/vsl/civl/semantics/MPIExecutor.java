package edu.udel.cis.vsl.civl.semantics;

import java.io.PrintStream;
import java.util.ArrayList;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.library.mpi.Libmpi;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.MPIModelFactory;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.SystemFunction;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.AssertStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.AssumeStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIRecvStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MallocStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.NoopStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.ReturnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.statement.WaitStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLBundleType;
import edu.udel.cis.vsl.civl.model.common.statement.StatementList;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.semantics.IF.LibraryExecutorLoader;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
import edu.udel.cis.vsl.gmc.ErrorLog;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicUnionType;

public class MPIExecutor extends Executor {

	/* ********************** Instance Field ******************************* */

	private VariableExpression rankExpression;

	private Libmpi mpiExecutor;

	/* ************************** constructor ******************************** */
	/**
	 * Create a new executor.
	 * 
	 * @param model
	 *            The model being executed.
	 * @param symbolicUniverse
	 *            A symbolic universe for creating new values.
	 * @param stateFactory
	 *            A state factory. Used by the Executor to create new processes.
	 * @param prover
	 *            A theorem prover for checking assertions.
	 */
	public MPIExecutor(GMCConfiguration config, ModelFactory modelFactory,
			StateFactory stateFactory, ErrorLog log,
			LibraryExecutorLoader loader, PrintStream output,
			boolean enablePrintf) {
		super(config, modelFactory, stateFactory, log, loader, output,
				enablePrintf);
		this.mpiExecutor = (Libmpi) loader.getLibraryExecutor("mpi", this,
				this.output, this.enablePrintf, this.modelFactory);
		rankExpression = ((MPIModelFactory) modelFactory).rankVariable();
	}

	/*************************** Private methods *****************************/
	/*
	 * private int getRank(State state, int pid, Statement statement) { int
	 * scopeId; int variableId; CIVLSource civlsource = statement.getSource();
	 * scopeId = statement.statementScope().id(); variableId =
	 * state.getVariableValue(, variableId); }
	 */

	/* ******************** Private MPI Executor methods ********************* */
	/**
	 * Performs a blocking send
	 * 
	 * 
	 * int MPI_Send(void *buf, int count, MPI_Datatype datatype, int dest, int
	 * tag, MPI_Comm comm)
	 * 
	 * @param state
	 *            The state of the program
	 * @param pid
	 *            The process id of the currently executing process
	 * @param statement
	 *            The statement to be executed
	 * 
	 * @return The updated state of the program
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeMPI_Send(State state, int pid, LHSExpression lhs,
			MPISendStatement statement)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = statement.getSource();
		int messageSize = -1;
		Evaluation eval = evaluator.evaluate(state, pid,
				(Expression) rankExpression);
		state = eval.state;
		int rank = evaluator.extractInt(civlsource,
				(NumericExpression) eval.value);
		int bundleIndex;
		CIVLBundleType bundleType = state.getScope(0).lexicalScope().model()
				.bundleType();
		SymbolicUnionType bundle = bundleType.getDynamicType(symbolicUniverse);
		// commVariableID and commScopeID
		int commScopeID;
		int commVariableID;
		// MPI_Send arguments
		eval = evaluator.evaluate(state, pid, statement.getCommunicator());
		state = eval.state;
		SymbolicExpression commAddr = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getCount());
		state = eval.state;
		SymbolicExpression count = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getDatatype());
		state = eval.state;
		SymbolicExpression dataType = symbolicUniverse.tupleRead(eval.value,
				symbolicUniverse.intObject(0));
		eval = evaluator.evaluate(state, pid, statement.getDestination());
		state = eval.state;
		SymbolicExpression destination = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getTag());
		state = eval.state;
		SymbolicExpression tag = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getBuffer());
		state = eval.state;
		SymbolicExpression bufAddr = eval.value;
		// used for updating message buffer
		SymbolicExpression messageBuffer;
		SymbolicExpression messageBufferRow;
		SymbolicExpression messageQueue;
		SymbolicExpression messages;
		SymbolicExpression newMessage;
		int queueLength;
		int int_count = evaluator.extractInt(civlsource,
				(NumericExpression) count);
		ArrayList<SymbolicExpression> messageValues = new ArrayList<SymbolicExpression>();
		ArrayList<SymbolicType> messageTypes = new ArrayList<SymbolicType>();
		ArrayList<SymbolicExpression> messageElements = new ArrayList<SymbolicExpression>();
		ArrayList<SymbolicExpression> buf = new ArrayList<SymbolicExpression>();
		SymbolicExpression comm = evaluator.dereference(civlsource, state,
				commAddr).value;
		// create buf array
		SymbolicExpression bufArray = null;
		eval = evaluator.dereference(civlsource, state,
				bufAddr);
		state = eval.state;
		SymbolicExpression bufValue = eval.value;
		SymbolicType bufType = null;
		if(int_count < 0){
			throw new UnsatisfiablePathConditionException();  //TODO: which exception ?!!?!
		}else if(bufValue.isNull() || int_count == 0){
			bufType = symbolicUniverse.integerType();
			buf.add(symbolicUniverse.zeroInt());
			bufArray = symbolicUniverse.array(bufType, buf);	
		}else if((bufValue.isOne() && int_count == 1)){
			buf.add(bufValue);
			bufType = bufValue.type();
			bufArray = symbolicUniverse.array(bufType, buf);
		}
		else{
			for(int i=0; i<int_count; i++){
				SymbolicExpression bufArrayElement = symbolicUniverse.arrayRead(bufValue, 
						symbolicUniverse.integer(i));
				buf.add(bufArrayElement);
			}
			bufType = bufValue.type();
			bufType = ((SymbolicArrayType) bufType).elementType();
			bufArray = symbolicUniverse.array(bufType, buf);
		}
		// message buffer[][] <- comm[2]
		messageBuffer = symbolicUniverse.tupleRead(comm,
				symbolicUniverse.intObject(2));
		// message buffer[rank][]
		messageBufferRow = symbolicUniverse.arrayRead(messageBuffer,
				symbolicUniverse.integer(rank));
		// message queue <- message buffer [rank][destination]
		messageQueue = symbolicUniverse.arrayRead(messageBufferRow,
				(NumericExpression) destination);
		queueLength = evaluator.extractInt(civlsource,
				(NumericExpression) symbolicUniverse.tupleRead(messageQueue,
						symbolicUniverse.intObject(0)));
		// message <- message queue[1]
		messages = symbolicUniverse.tupleRead(messageQueue,
				symbolicUniverse.intObject(1));
		// evaluate message size
		switch (dataType.toString()) {
		case "1": // MPI_INT
			messageSize = int_count * Integer.SIZE;
			break;
		case "2": // MPI_FLOAT
			messageSize = int_count * Float.SIZE;
			break;
		case "3": // MPI_DOUBLE
			messageSize = int_count * Double.SIZE;
			break;
		case "4": // MPI_CHAR
			messageSize = int_count * Character.SIZE;
			break;
		default:
			throw new CIVLUnimplementedFeatureException(dataType.toString()
					+ " in MPIExecutor", civlsource);
		}

		bundleIndex = bundleType.getIndexOf(symbolicUniverse.pureType(bufType));
		bufArray = symbolicUniverse.unionInject(bundle,
				symbolicUniverse.intObject(bundleIndex), bufArray);
		// message values
		messageValues.add(symbolicUniverse.integer(rank));
		messageValues.add(destination);
		messageValues.add(tag);
		messageValues.add(bufArray);
		messageValues.add(symbolicUniverse.integer(messageSize));
		// message types
		messageTypes.add(symbolicUniverse.integer(rank).type());
		messageTypes.add(destination.type());
		messageTypes.add(tag.type());
		messageTypes.add(bundle);
		messageTypes.add(symbolicUniverse.integer(messageSize).type());
		// build new message
		newMessage = symbolicUniverse.tuple(symbolicUniverse.tupleType(
				symbolicUniverse.stringObject("__message__"), messageTypes),
				messageValues);
		// update the message queue with a new message array.
		for (int i = 0; i < evaluator.extractInt(civlsource,
				symbolicUniverse.length(messages)); i++) {
			messageElements.add(symbolicUniverse.arrayRead(messages,
					symbolicUniverse.integer(i)));
		}
		messageElements.add(newMessage);
		messages = symbolicUniverse.array(newMessage.type(), messageElements);
		queueLength = evaluator.extractInt(civlsource,
				(NumericExpression) symbolicUniverse.tupleRead(messageQueue,
						symbolicUniverse.intObject(0)));
		queueLength++;
		messageQueue = symbolicUniverse.tupleWrite(messageQueue,
				symbolicUniverse.intObject(0),
				symbolicUniverse.integer(queueLength));
		messageQueue = symbolicUniverse.tupleWrite(messageQueue,
				symbolicUniverse.intObject(1), messages);
		// update message buffer
		messageBufferRow = symbolicUniverse.arrayWrite(messageBufferRow,
				(NumericExpression) destination, messageQueue);
		messageBuffer = symbolicUniverse.arrayWrite(messageBuffer,
				symbolicUniverse.integer(rank), messageBufferRow);
		// update communicator
		comm = symbolicUniverse.tupleWrite(comm, symbolicUniverse.intObject(2),
				messageBuffer);
		// update state
		// ((MPIModelFactory)this.modelFactory).mpi
		commScopeID = evaluator.getScopeId(civlsource, commAddr);
		commVariableID = evaluator.getVariableId(civlsource, commAddr);
		state = stateFactory.setVariable(state, commVariableID, commScopeID,
				comm);
		//TODO: implement return values
		if (lhs != null) {
			eval = evaluator.evaluate(state, pid, statement.getLeftHandSide());
			state = eval.state;
			SymbolicExpression lhsValue = eval.value;
			state = this.assign(state, pid, lhs, lhsValue);
		}
		return state;
	}

	/**
	 * execute a MPI_Recv statement. Remove a corresponding message form the
	 * message buffer. Assigning message values to buf, MPI_Recv information to
	 * status.
	 * 
	 * int MPI_Recv(void *buf, int count, MPI_Datatype datatype, int source, int
	 * tag, MPI_Comm comm, MPI_Status *status)
	 * 
	 * @param state
	 *            The state of the program
	 * @param pid
	 *            The process id of the currently executing process
	 * @param statement
	 *            The statement to be executed
	 * 
	 * @return The updated state of the program
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeMPI_Recv(State state, int pid, LHSExpression lhs,
			MPIRecvStatement statement)
			throws UnsatisfiablePathConditionException {
		CIVLSource civlsource = statement.getSource();
		int messageSize = -1;
		Evaluation eval = evaluator.evaluate(state, pid, rankExpression);
		state = eval.state;
		int rank = evaluator.extractInt(civlsource,
				(NumericExpression) eval.value);
		// MPI_Recv arguments
		SymbolicExpression buf;
		eval = evaluator.evaluate(state, pid, statement.getCount());
		state = eval.state;
		SymbolicExpression count = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getDatatype());
		state = eval.state;
		SymbolicExpression dataType = symbolicUniverse.tupleRead(eval.value,
				symbolicUniverse.intObject(0));
		eval = evaluator.evaluate(state, pid, statement.getMPISource());
		state = eval.state;
		SymbolicExpression source = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getTag());
		state = eval.state;
		SymbolicExpression tag = eval.value;
		eval = evaluator.evaluate(state, pid, statement.getCommunicator());
		state = eval.state;
		SymbolicExpression commAddr = eval.value;
		eval = evaluator.dereference(civlsource, state, commAddr);
		state = eval.state;
		SymbolicExpression comm = eval.value;
		SymbolicExpression status = null;
		// used for updating message buffer
		SymbolicExpression messageBuffer = null;
		SymbolicExpression messageBufferRow = null;
		SymbolicExpression messageQueue = null;
		SymbolicExpression messages = null;
		SymbolicExpression newMessage = null;
		int queueLength = 0;
		int int_count = evaluator.extractInt(civlsource,
				(NumericExpression) count);
		int int_tag = evaluator.extractInt(civlsource, (NumericExpression) tag);
		int int_source = evaluator.extractInt(civlsource,
				(NumericExpression) source);
		int variableScopeID;
		int variableID;
		boolean hasTag = false;

		// evaluate message size
		switch (dataType.toString()) {
		case "1": // MPI_INT
			messageSize = int_count * Integer.SIZE;
			break;
		case "2": // MPI_FLOAT
			messageSize = int_count * Float.SIZE;
			break;
		case "3": // MPI_DOUBLE;
			messageSize = int_count * Double.SIZE;
			break;
		case "4": // MPI_CHAR
			messageSize = int_count * Character.SIZE;
			break;
		default:
			throw new CIVLUnimplementedFeatureException(dataType.toString()
					+ " in MPIExecutor", civlsource);
		}
		// obtain message
		messageBuffer = symbolicUniverse.tupleRead(comm,
				symbolicUniverse.intObject(2));
		// MPI_ANY_SOURCE && MPI_ANY_TAG
		if (int_source == -1 && int_tag == -2) {
			int nprocs = evaluator.extractInt(civlsource,
					(NumericExpression) symbolicUniverse.tupleRead(comm,
							symbolicUniverse.intObject(0)));
			for (int i = 0; i < nprocs; i++) {
				messageBufferRow = symbolicUniverse.arrayRead(messageBuffer,
						symbolicUniverse.integer(i));
				messageQueue = symbolicUniverse.arrayRead(messageBufferRow,
						symbolicUniverse.integer(rank));
				queueLength = evaluator.extractInt(civlsource,
						(NumericExpression) (symbolicUniverse.tupleRead(
								messageQueue, symbolicUniverse.intObject(0))));
				if (queueLength > 0) {
					messages = symbolicUniverse.tupleRead(messageQueue,
							symbolicUniverse.intObject(1));
					newMessage = symbolicUniverse.arrayRead(messages,
							symbolicUniverse.integer(0));
					source = symbolicUniverse.integer(i);
					break;
				}
			}
		} else if (int_source == -1 && int_tag != -2) {
			// MPI_ANY_SOURCE but not MPI_ANY_TAG
			int nprocs = evaluator.extractInt(civlsource,
					(NumericExpression) symbolicUniverse.tupleRead(comm,
							symbolicUniverse.intObject(0)));
			for (int i = 0; i < nprocs; i++) {
				messageBufferRow = symbolicUniverse.arrayRead(messageBuffer,
						symbolicUniverse.integer(i));
				messageQueue = symbolicUniverse.arrayRead(messageBufferRow,
						symbolicUniverse.integer(rank));
				queueLength = evaluator.extractInt(civlsource,
						(NumericExpression) (symbolicUniverse.tupleRead(
								messageQueue, symbolicUniverse.intObject(0))));
				messages = symbolicUniverse.tupleRead(messageQueue,
						symbolicUniverse.intObject(1));
				for (int j = 0; j < queueLength; j++) {
					newMessage = symbolicUniverse.arrayRead(messages,
							symbolicUniverse.integer(i));
					if (symbolicUniverse.tupleRead(newMessage,
							symbolicUniverse.intObject(2)).equals(tag)) {
						hasTag = true;
						source = symbolicUniverse.integer(i);
						break;
					}
				}
				if (hasTag)
					break;
			}
		} else {
			messageBufferRow = symbolicUniverse.arrayRead(messageBuffer,
					(NumericExpression) source);
			messageQueue = symbolicUniverse.arrayRead(messageBufferRow,
					symbolicUniverse.integer(rank));
			messages = symbolicUniverse.tupleRead(messageQueue,
					symbolicUniverse.intObject(1));
			queueLength = evaluator.extractInt(civlsource,
					(NumericExpression) (symbolicUniverse.tupleRead(
							messageQueue, symbolicUniverse.intObject(0))));
			// MPI_ANY_TAG but not MPI_ANY_SOURCE
			if (int_tag == -2) {
				newMessage = symbolicUniverse.arrayRead(messages,
						symbolicUniverse.integer(0));
				// neither MPI_ANY_TAG nor MPI_ANY_SOURCE
			} else {
				// find the message with the first matched tag.
				for (int i = 0; i < queueLength; i++) {
					newMessage = symbolicUniverse.arrayRead(messages,
							symbolicUniverse.integer(i));
					SymbolicExpression messageTag = symbolicUniverse.tupleRead(
							newMessage, symbolicUniverse.intObject(2));
					if (tag.equals(messageTag)) {
						messages = symbolicUniverse
								.removeElementAt(messages, i);
						break;
					}
				}
			}
		}
		// set buf and status
		buf = symbolicUniverse.tupleRead(newMessage,
				symbolicUniverse.intObject(3));
		//TODO: the buf need to be a array type
		buf = (SymbolicExpression) buf.argument(1);
		assert buf.type() instanceof SymbolicArrayType;
		// create a new tuple
		ArrayList<SymbolicExpression> statusValues = new ArrayList<SymbolicExpression>();
		ArrayList<SymbolicType> statusTypes = new ArrayList<SymbolicType>();
		statusValues.add(source);
		statusValues.add(tag);
		statusValues.add(symbolicUniverse.integer(0));
		statusValues.add(symbolicUniverse.integer(messageSize));
		statusTypes.add(source.type());
		statusTypes.add(tag.type());
		statusTypes.add(symbolicUniverse.integer(0).type());
		statusTypes.add(symbolicUniverse.integer(messageSize).type());
		status = symbolicUniverse.tuple(symbolicUniverse.tupleType(
				symbolicUniverse.stringObject("__MPI_Status"), statusTypes),
				statusValues);
		queueLength--;
		messageQueue = symbolicUniverse.tupleWrite(messageQueue,
				symbolicUniverse.intObject(0),
				symbolicUniverse.integer(queueLength));
		messageQueue = symbolicUniverse.tupleWrite(messageQueue,
				symbolicUniverse.intObject(1), messages);
		messageBufferRow = symbolicUniverse.arrayWrite(messageBufferRow,
				symbolicUniverse.integer(rank), messageQueue);
		messageBuffer = symbolicUniverse.arrayWrite(messageBuffer,
				(NumericExpression) source, messageBufferRow);
		comm = symbolicUniverse.tupleWrite(comm, symbolicUniverse.intObject(2),
				messageBuffer);
		// commVariableID and commScopeID
		variableScopeID = evaluator.getScopeId(civlsource, commAddr);
		variableID = evaluator.getVariableId(civlsource, commAddr);
		// update state
		state = stateFactory.setVariable(state, variableID, variableScopeID,
				comm);
		//TODO:implement mpi return values
		if (lhs != null) {
			eval = evaluator.evaluate(state, pid, statement.getLeftHandSide());
			state = eval.state;
			SymbolicExpression lhsValue = eval.value;
			state = this.assign(state, pid, lhs, lhsValue);
		}
		eval = evaluator.evaluate(state, pid, statement.getBuffer());
		state = eval.state;
		variableScopeID = evaluator.getScopeId(civlsource, eval.value);
		variableID = evaluator.getVariableId(civlsource, eval.value);
		state = stateFactory.setVariable(state, variableID, variableScopeID,
				buf);
		eval = evaluator.evaluate(state, pid, statement.getStatus());
		state = eval.state;
		variableScopeID = evaluator.getScopeId(civlsource, eval.value);
		variableID = evaluator.getVariableId(civlsource, eval.value);
		state = stateFactory.setVariable(state, variableID, variableScopeID,
				status);
		return state;
	}

	/**
	 * Add MPISend and MPIRecv statements execution to the superclass's version.
	 */
	@Override
	protected State executeWork(State state, int pid, Statement statement)
			throws UnsatisfiablePathConditionException {
		LHSExpression lhs = null;
		numSteps++;
		if (statement instanceof CallOrSpawnStatement) {
			lhs = ((CallOrSpawnStatement) statement).lhs();
		}
		if (statement instanceof AssumeStatement) {
			return executeAssume(state, pid, (AssumeStatement) statement);
		} else if (statement instanceof AssertStatement) {
			return executeAssert(state, pid, (AssertStatement) statement);
		} else if (statement instanceof CallOrSpawnStatement) {
			CallOrSpawnStatement call = (CallOrSpawnStatement) statement;

			if (call.isCall())
				return executeCall(state, pid, call);
			else
				return executeSpawn(state, pid, call);
		} else if (statement instanceof AssignStatement) {
			return executeAssign(state, pid, (AssignStatement) statement);
		} else if (statement instanceof WaitStatement) {
			return executeWait(state, pid, (WaitStatement) statement);
		} else if (statement instanceof ReturnStatement) {
			return executeReturn(state, pid, (ReturnStatement) statement);
		} else if (statement instanceof NoopStatement) {
			state = transition(state, state.getProcessState(pid),
					statement.target());

			return state;
		} else if (statement instanceof MallocStatement) {
			return executeMalloc(state, pid, (MallocStatement) statement);
		} else if (statement instanceof StatementList) {
			state = executeStatementList(state, pid, (StatementList) statement,
					null);
			return state;
		} else if (statement instanceof MPISendStatement) {
			state = executeMPI_Send(state, pid, lhs,
					(MPISendStatement) statement);
			state = this.transition(state, state.getProcessState(pid),
					statement.target());
			return state;
		} else if (statement instanceof MPIRecvStatement) {
			state = executeMPI_Recv(state, pid, lhs,
					(MPIRecvStatement) statement);
			state = this.transition(state, state.getProcessState(pid),
					statement.target());
			return state;
		} else if (statement instanceof ChooseStatement) {
			throw new CIVLInternalException("Should be unreachable", statement);
		} else
			throw new CIVLInternalException("Unknown statement kind", statement);
	}

	@Override
	public LibraryExecutor libraryExecutor(CallOrSpawnStatement statement) {
		String library;

		assert statement.function() instanceof SystemFunction;
		library = ((SystemFunction) statement.function()).getLibrary();
		switch (library) {
		case "civlc":
			return civlcExecutor;
		case "stdio":
			return stdioExecutor;
		case "mpi":
			return mpiExecutor;
		default:
			throw new CIVLInternalException("Unknown library: " + library,
					statement);
		}
	}

	/* *********************** public methods ********************************* */

//	/**
//	 * Get the guard of MPIRecvStatement. When receiving messages with any tag
//	 * from any source, the guard is "There is at least one message buffer which
//	 * belongs to the process itself has at least one message".
//	 * 
//	 * when receiving messages with any tag from a specific source, the guard is
//	 * " There is at least one message in the specific message buffer".
//	 * 
//	 * when receiving messages with specific tag from any source, the guard is "
//	 * There is at least one message buffer which belongs to the process itself
//	 * has at least one message with the specific tag".
//	 * 
//	 * when receiving message with specific tag from specific source, the guard
//	 * is "There is at least one message with the specific tag in the specific
//	 * buffer"
//	 * 
//	 * @param state
//	 *            The state of the program
//	 * @param pid
//	 *            The process id of the currently executing process
//	 * @param statement
//	 *            The statement to be executed
//	 * 
//	 * @return The updated state of the program
//	 * @throws UnsatisfiablePathConditionException
//	 */
//	public BooleanExpression getMPIRecvGuard(State state, int pid,
//			MPIRecvStatement statement)
//			throws UnsatisfiablePathConditionException {
//		CIVLSource civlsource = statement.getSource();
//		Evaluation eval = evaluator.evaluate(state, pid,
//				statement.getCommunicator());
//		state = eval.state;
//		SymbolicExpression commAddr = eval.value;
//		eval = evaluator.dereference(civlsource, state, commAddr);
//		state = eval.state;
//		SymbolicExpression comm = eval.value;
//		eval = evaluator.evaluate(state, pid, statement.getTag());
//		state = eval.state;
//		SymbolicExpression tag = eval.value;
//		eval = evaluator.evaluate(state, pid, statement.getMPISource());
//		state = eval.state;
//		SymbolicExpression source = eval.value;
//		eval = evaluator.evaluate(state, pid, rankExpression);
//		state = eval.state;
//		int rank = evaluator.extractInt(civlsource,
//				(NumericExpression) eval.value);
//		int queueLength = -1;
//		SymbolicExpression buf; // buf has type $queue[][]
//		SymbolicExpression bufRow; // buf[source], has type $queue[]
//		SymbolicExpression queue; // particular $queue for this source and dest
//		SymbolicExpression messages;
//		boolean enabled = false;
//		int int_tag = evaluator.extractInt(civlsource, (NumericExpression) tag);
//		int int_source = evaluator.extractInt(civlsource,
//				(NumericExpression) source);
//		int nprocs = evaluator.extractInt(civlsource,
//				(NumericExpression) symbolicUniverse.tupleRead(comm,
//						symbolicUniverse.intObject(1)));
//
//		buf = symbolicUniverse.tupleRead(comm, symbolicUniverse.intObject(3));
//		// MPI_ANY_SOURCE && MPI_ANY_TAG
//		if (int_source == -1 && int_tag == -2) {
//			for (int i = 0; i < nprocs; i++) {
//				bufRow = symbolicUniverse.arrayRead(buf,
//						symbolicUniverse.integer(i));
//				queue = symbolicUniverse.arrayRead(bufRow,
//						symbolicUniverse.integer(rank));
//				queueLength = evaluator.extractInt(civlsource,
//						(NumericExpression) symbolicUniverse.tupleRead(queue,
//								symbolicUniverse.intObject(0)));
//				if (queueLength > 0) {
//					source = symbolicUniverse.integer(i);
//					enabled = true;
//					break;
//				}
//			}
//			// MPI_ANY_SOURCE but not MPI_ANY_TAG
//		} else if (int_source == -1 && int_tag != -2) {
//			for (int i = 0; i < nprocs; i++) {
//				bufRow = symbolicUniverse.arrayRead(buf,
//						symbolicUniverse.integer(i));
//				queue = symbolicUniverse.arrayRead(bufRow,
//						symbolicUniverse.integer(rank));
//				queueLength = evaluator.extractInt(civlsource,
//						(NumericExpression) symbolicUniverse.tupleRead(queue,
//								symbolicUniverse.intObject(0)));
//				messages = symbolicUniverse.tupleRead(queue,
//						symbolicUniverse.intObject(1));
//				for (int j = 0; j < queueLength; j++) {
//					if (symbolicUniverse.arrayRead(messages,
//							symbolicUniverse.integer(j)).equals(tag)) {
//						enabled = true;
//						source = symbolicUniverse.integer(i);
//						break;
//					}
//				}
//				if (enabled)
//					break;
//			}
//		} else {
//			bufRow = symbolicUniverse
//					.arrayRead(buf, (NumericExpression) source);
//			queue = symbolicUniverse.arrayRead(bufRow,
//					symbolicUniverse.integer(rank));
//			queueLength = evaluator.extractInt(civlsource,
//					(NumericExpression) symbolicUniverse.tupleRead(queue,
//							symbolicUniverse.intObject(0)));
//			messages = symbolicUniverse.tupleRead(queue,
//					symbolicUniverse.intObject(1));
//			if (int_tag == -2) {
//				if (queueLength > 0)
//					enabled = true;
//			} else {
//				for (int i = 0; i < queueLength; i++) {
//					if (symbolicUniverse.tupleRead(
//							symbolicUniverse.arrayRead(messages,
//									symbolicUniverse.integer(i)),
//							symbolicUniverse.intObject(2)).equals(tag)) {
//						// We have a message with the right tag!
//						enabled = true;
//						break;
//					}
//				}
//			}
//		}
//		return symbolicUniverse.bool(enabled);
//	}

	/**
	 * Add checking for guard of MPIRecvStatement to the superclass's version.
	 */
	public BooleanExpression newPathCondition(State state, int pid,
			Statement statement) {
		try {
			Evaluation eval = evaluator.evaluate(state, pid, statement.guard());
			BooleanExpression pathCondition = eval.state.getPathCondition();
			BooleanExpression guard = (BooleanExpression) eval.value;
			Reasoner reasoner = evaluator.universe().reasoner(pathCondition);

			if (statement instanceof CallOrSpawnStatement) {
				if (((CallOrSpawnStatement) statement).function() instanceof SystemFunction) {
					LibraryExecutor libraryExecutor = libraryExecutor((CallOrSpawnStatement) statement);

					guard = evaluator.universe().and(guard,
							libraryExecutor.getGuard(state, pid, statement));
				}
			}
			if (reasoner.isValid(guard))
				return pathCondition;
			if (reasoner.isValid(evaluator.universe().not(guard)))
				return evaluator.universe().falseExpression();
			return evaluator.universe().and(pathCondition, guard);
		} catch (UnsatisfiablePathConditionException e) {
			return evaluator.universe().falseExpression();
		}
	}

	// // /**
	// // * Add checking for guard of MPIRecvStatement to the superclass's
	// version.
	// // */
	// // @Override
	// // public BooleanExpression newPathCondition(State state, int pid,
	// // Statement statement) {
	// // try {
	// // Evaluation eval = evaluator.evaluate(state, pid, statement.guard());
	// // BooleanExpression pathCondition = eval.state.getPathCondition();
	// // BooleanExpression guard = (BooleanExpression) eval.value;
	// // Reasoner reasoner = evaluator.universe().reasoner(pathCondition);
	// //
	// // if (statement instanceof CallOrSpawnStatement) {
	// // if (((CallOrSpawnStatement) statement).function() instanceof
	// // SystemFunction) {
	// // LibraryExecutor libraryExecutor =
	// libraryExecutor((CallOrSpawnStatement)
	// // statement);
	// //
	// // guard = evaluator.universe().and(guard,
	// // libraryExecutor.getGuard(state, pid, statement));
	// // }
	// // }
	// // if (statement instanceof MPIRecvStatement) {
	// // guard = evaluator.universe().and(
	// // guard,
	// // this.getMPIRecvGuard(state, pid,
	// // (MPIRecvStatement) statement));
	// // }
	// //
	// // if (reasoner.isValid(guard))
	// // return pathCondition;
	// // if (reasoner.isValid(evaluator.universe().not(guard)))
	// // return evaluator.universe().falseExpression();
	// // return evaluator.universe().and(pathCondition, guard);
	// // } catch (UnsatisfiablePathConditionException e) {
	// // return evaluator.universe().falseExpression();
	// // }
	// // }
	// >>>>>>> .r579
}
