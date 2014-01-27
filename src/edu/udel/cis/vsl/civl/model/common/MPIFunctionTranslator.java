package edu.udel.cis.vsl.civl.model.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.MPIModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.BinaryExpression.BINARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.UnaryExpression.UNARY_OPERATOR;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

public class MPIFunctionTranslator extends FunctionTranslator {

	// private static final String MPI_Process_Name = "MPI_Process";
	private MPIModelFactory mpiFactory;
	private CIVLFunction mpiProcessFunction;

	MPIFunctionTranslator(ModelBuilderWorker modelBuilder,
			MPIModelFactory mpiFactory, CIVLFunction function) {
		super(modelBuilder, mpiFactory, function);
		this.mpiFactory = mpiFactory;
	}

	/**
	 * The root function is the system function, which has the equivalent
	 * functionality as the function below.<br>
	 * 
	 * <pre>
	 * <code>
	 * void init() {
	 *   for (int i=0; i<NPROCS; i++)
	 *     __procs[i] = $spawn MPI_Process(i);
	 *   __MPI_Comm_World = $comm_create(NPROCS, __procs);
	 *   __start=1;
	 * }
	 * void finalize() {
	 *   for (int i=0; i<NPROCS; i++)
	 *     $wait __procs[i];
	 * }
	 * void main() {
	 *   $atomic{
	 *     init();
	 *     finalize();
	 *   }
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param systemScope
	 *            The root scope.
	 * @param numberOfProcs
	 *            The expression of the number of processes. This may be a
	 *            integer literal for most cases.
	 */
	public void translateRootFunction(Scope systemScope,
			Expression numberOfProcs) {
		Fragment result;
		Fragment spawnPhase = spawnMpiProcesses(systemScope, numberOfProcs);
		Location assignStartLocation = mpiFactory.location(systemScope);
		Fragment waitPhase = waitMpiProcesses(systemScope, numberOfProcs);
		Location returnLocation = mpiFactory.location(function().outerScope());
		Fragment returnFragment = mpiFactory.returnFragment(mpiFactory
				.systemSource(), returnLocation, null, functionInfo()
				.function());
		Fragment assignStartFragment;

		mpiFactory.createStartVariable(systemScope, systemScope.numVariables());
		assignStartFragment = new CommonFragment(mpiFactory.assignStatement(
				assignStartLocation, mpiFactory.startVariable(),
				mpiFactory.integerLiteralExpression(BigInteger.valueOf(0)),
				false));
		// TODO initialize MPI_COMM_WORLD
		result = spawnPhase.combineWith(assignStartFragment);
		result = result.combineWith(waitPhase);
		result = result.combineWith(returnFragment);
	}

	/**
	 * 
	 * @param scope
	 * @param numberOfProcs
	 * @return
	 */
	private Fragment spawnMpiProcesses(Scope scope, Expression numberOfProcs) {
		Scope newScope = mpiFactory.scope(scope, new LinkedHashSet<Variable>(),
				functionInfo().function());
		Fragment initFragment, result;
		Location location = mpiFactory.location(newScope);
		Variable iVariable = mpiFactory.variable(mpiFactory.integerType(),
				mpiFactory.identifier("i"), newScope.numVariables());
		VariableExpression iVariableExpression = mpiFactory
				.variableExpression(iVariable);
		Expression condition = mpiFactory.binaryExpression(
				mpiFactory.systemSource(), BINARY_OPERATOR.LESS_THAN,
				iVariableExpression, numberOfProcs);
		Location loopEntranceLocation = mpiFactory.location(newScope), loopBodyLocation = mpiFactory
				.location(newScope);
		Fragment loopEntrance = new CommonFragment(loopEntranceLocation,
				mpiFactory.loopBranchStatement(mpiFactory.systemSource(),
						loopEntranceLocation, condition, true));
		Fragment loopBody, incrementer;
		Statement loopExit = mpiFactory.loopBranchStatement(condition
				.getSource(), loopEntranceLocation, mpiFactory.unaryExpression(
				condition.getSource(), UNARY_OPERATOR.NOT, condition), false);
		ArrayList<Expression> arguments = new ArrayList<>();

		newScope.addVariable(iVariable);
		initFragment = new CommonFragment(mpiFactory.assignStatement(location,
				iVariableExpression,
				mpiFactory.integerLiteralExpression(BigInteger.valueOf(0)),
				true));
		arguments.add(iVariableExpression);
		loopBody = new CommonFragment(mpiFactory.callOrSpawnStatement(
				loopBodyLocation, false, mpiProcessFunction, arguments));
		incrementer = new CommonFragment(mpiFactory.assignStatement(mpiFactory
				.location(newScope), iVariableExpression, mpiFactory
				.binaryExpression(mpiFactory.systemSource(),
						BINARY_OPERATOR.PLUS, iVariableExpression,
						mpiFactory.integerLiteralExpression(BigInteger
								.valueOf(1))), false));
		result = composeLoop(initFragment, loopEntrance, loopBody, incrementer,
				loopExit);
		result = initFragment.combineWith(result);
		return result;
	}

	private Fragment waitMpiProcesses(Scope scope, Expression numberOfProcs) {
		return null;// TODO TBC
	}

	private Fragment composeLoop(Fragment initFragment, Fragment loopEntrance,
			Fragment loopBody, Fragment incrementer, Statement loopExit) {
		Fragment result;

		// incrementer comes after the loop body
		if (incrementer != null)
			loopBody = loopBody.combineWith(incrementer);
		// loop entrance comes before the loop body, P.S. loopExit is "combined"
		// implicitly because its start location is the same as loopEntrance
		loopBody = loopBody.combineWith(loopEntrance);
		// initially loop entrance comes before the loopBody. Now we'll have
		// loopBody -> loopEntrance -> loopBody and the loop is formed.
		result = loopEntrance.combineWith(loopBody);
		result.setLastStatement(loopExit);
		return result;

	}

	/**
	 * Translate a function call node into a fragment containing the call
	 * statement
	 * 
	 * @param scope
	 *            The scope
	 * @param functionCallNode
	 *            The function call node
	 * @return the fragment containing the function call statement
	 */
	@Override
	protected Statement translateFunctionCall(Scope scope, Location location,
			LHSExpression lhs, FunctionCallNode functionCallNode, boolean isCall) {
		CIVLSource source = modelFactory().sourceOfBeginning(functionCallNode);
		String functionName = ((IdentifierExpressionNode) functionCallNode
				.getFunction()).getIdentifier().name();
		ArrayList<Expression> arguments = new ArrayList<Expression>();

		for (int i = 0; i < functionCallNode.getNumberOfArguments(); i++) {
			Expression actual = translateExpressionNode(
					functionCallNode.getArgument(i), scope, true);

			actual = arrayToPointer(actual);
			arguments.add(actual);
		}
		switch (functionName) {
		// TODO once MPI Statement implementation is
		// done, translate mpi function call node here to the corresponding MPI
		// Statement.
		case CommonMPIModelFactory.MPI_SEND:
			return mpiFactory.mpiSendStatement(source, location, scope, lhs,
					arguments);
			// case MPIStatementFactory.MPI_RECV:
			// break;
			// case MPIStatementFactory.MPI_IRECV:
			// break;
			// case MPIStatementFactory.MPI_ISEND:
			// break;
			// case MPIStatementFactory.MPI_BARRIER:
			// break;
			// case MPIStatementFactory.MPI_WAIT:
			// break;
		default:
			return callOrSpawnStatement(location, scope, functionCallNode, lhs,
					arguments, isCall);
		}
	}

}
