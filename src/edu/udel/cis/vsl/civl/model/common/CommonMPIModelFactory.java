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
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLArrayType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPISendStatement;
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

		sendStatement.setStatementScope(join(scope, lhs.expressionScope()));
		return sendStatement;
	}

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
