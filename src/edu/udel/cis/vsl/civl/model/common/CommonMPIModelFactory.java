package edu.udel.cis.vsl.civl.model.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.MPIModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIBarrierStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIIrecvStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIIsendStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIRecvStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPIWaitStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPIBarrierStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPIIrecvStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPIIsendStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPIRecvStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPISendStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPIWaitStatement;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

/**
 * This class translates MPI function call nodes into the corresponding MPI
 * Statements.
 * 
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 */
public class CommonMPIModelFactory extends CommonModelFactory implements
		MPIModelFactory {

	/* *************************** Static Fields *************************** */

	/**
	 * The function name of MPI Send.
	 */
	static final String MPI_SEND = "MPI_Send";

	/**
	 * The function name of MPI Receive.
	 */
	static final String MPI_RECV = "MPI_Recv";

	/**
	 * The function name of MPI Barrier.
	 */
	static final String MPI_BARRIER = "MPI_Barrier";

	/**
	 * The function name of MPI Isend.
	 */
	static final String MPI_ISEND = "MPI_Isend";

	/**
	 * The function name of MPI Ireceive.
	 */
	static final String MPI_IRECV = "MPI_Irecv";

	/**
	 * The function name of MPI Wait.
	 */
	static final String MPI_WAIT = "MPI_Wait";

	static final String RANK = "$RANK";

	static final String MPI_COMM_WORLD = "MPI_COMM_WORLD";

	static final String MPI_START = "$MPI_START";

	static final String PROCS = "$PROCS";

	/* ************************** Instance Fields ************************** */

	private VariableExpression rankVariable;

	private VariableExpression startVariable;

	private VariableExpression procsVariable;

	/* **************************** Constructors *************************** */

	/**
	 * Create a new instance of MPIStatementFactory.
	 * 
	 * @param factory
	 *            The model factory to be used for translating AST nodes.
	 */
	public CommonMPIModelFactory(SymbolicUniverse universe) {
		super(universe);
	}

	/* ********************** Package-private Methods ********************** */

	/**
	 * Translate a MPI_Send function call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement}.
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Send
	 *         statement.
	 * 
	 */
	@Override
	public MPISendStatement mpiSendStatement(CIVLSource source,
			Location location, Scope scope, LHSExpression lhs,
			ArrayList<Expression> arguments) {
		CommonMPISendStatement sendStatement = new CommonMPISendStatement(
				source, location, lhs, arguments);

		sendStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return sendStatement;
	}

	/**
	 * Translate a MPI_Recv functionn call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPIRecvStatement}
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Recv
	 *         statement.
	 */
	MPIRecvStatement translateMPI_RECV(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments) {
		CommonMPIRecvStatement recvStatement = new CommonMPIRecvStatement(
				source, location, lhs, arguments);

		recvStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return recvStatement;
	}

	/**
	 * Translate a MPI_Isend functionn call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPIIsendStatement}
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Isend
	 *         statement.
	 */
	MPIIsendStatement translateMPI_Isend(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments) {
		CommonMPIIsendStatement isendStatement = new CommonMPIIsendStatement(
				source, location, lhs, arguments);

		isendStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return isendStatement;
	}

	/**
	 * Translate a MPI_Irecv functionn call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPIIrecvStatement}
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Irecv
	 *         statement.
	 */
	MPIIrecvStatement translateMPI_Irecv(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments) {
		CommonMPIIrecvStatement irecvStatement = new CommonMPIIrecvStatement(
				source, location, lhs, arguments);

		irecvStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return irecvStatement;
	}

	/**
	 * Translate a MPI_Wait functionn call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPIWaitStatement}
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Wait
	 *         statement.
	 */
	MPIWaitStatement translateMPI_Wait(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments){
		CommonMPIWaitStatement waitStatement = new CommonMPIWaitStatement(
				source, location, lhs, arguments);

		waitStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return waitStatement;
	}
	
	/**
	 * Translate a MPI_Barrier functionn call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPIBarrierStatement}
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Barrier
	 *         statement.
	 */
	MPIBarrierStatement translateMPI_BArrier(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments){
		//MPI_Barrier just have one argument--communicator
		CommonMPIBarrierStatement barrierStatement = new CommonMPIBarrierStatement(
				source, location, lhs, arguments.get(0));

		barrierStatement.setStatementScope(join(lhs.expressionScope(), scope));
		return barrierStatement;
	}
	/* ************************* private methods *************************** */
	

	@Override
	public Location location(Scope scope) {
		return location(systemSource(), scope);
	}

	@Override
	public Variable variable(CIVLType type, Identifier name, int vid) {
		return variable(systemSource(), type, name, vid);
	}

	@Override
	public Identifier identifier(String name) {
		return identifier(systemSource(), name);
	}

	@Override
	public Scope scope(Scope parent, LinkedHashSet<Variable> variables,
			CIVLFunction function) {
		return scope(systemSource(), parent, variables, function);
	}

	@Override
	public IntegerLiteralExpression integerLiteralExpression(BigInteger value) {
		return integerLiteralExpression(systemSource(), value);
	}

	@Override
	public AssignStatement assignStatement(Location source, LHSExpression lhs,
			Expression rhs, boolean isInitialization) {
		return assignStatement(systemSource(), source, lhs, rhs,
				isInitialization);
	}

	@Override
	public VariableExpression variableExpression(Variable variable) {
		return variableExpression(systemSource(), variable);
	}

	@Override
	public CallOrSpawnStatement callOrSpawnStatement(Location source,
			boolean isCall, CIVLFunction function, List<Expression> arguments) {
		return callOrSpawnStatement(systemSource(), source, isCall, function,
				arguments, null);
	}

	@Override
	public void createRankVariable(Scope scope, int vid) {
		Variable rankVariable = this.variable(this.integerType(),
				this.identifier(RANK), vid);
		this.rankVariable = this.variableExpression(rankVariable);
		scope.addVariable(rankVariable);
	}

	@Override
	public VariableExpression rankVariable() {
		return this.rankVariable;
	}

	@Override
	public void createStartVariable(Scope scope, int vid) {
		Variable startVariable = this.variable(this.integerType(),
				this.identifier(MPI_START), vid);
		this.startVariable = this.variableExpression(startVariable);
		scope.addVariable(startVariable);
	}

	@Override
	public VariableExpression startVariable() {
		return this.startVariable;
	}

	@Override
	public void createProcsVariable(Scope scope, int vid, Expression nprocs) {
		CIVLArrayType arrayType = completeArrayType(processType(), nprocs);
		Variable procsVariable = this.variable(arrayType,
				this.identifier(PROCS), vid);
		this.procsVariable = this.variableExpression(procsVariable);
		scope.addVariable(procsVariable);
	}

	@Override
	public VariableExpression procsVariable() {
		return this.procsVariable;
	}
}
