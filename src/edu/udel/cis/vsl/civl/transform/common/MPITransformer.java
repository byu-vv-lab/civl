package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode.NodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode.StatementKind;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.IF.BaseTransformer;

/**
 * MPITransformer transforms an AST of an MPI program into an AST of an
 * equivalent CIVL-C program. The translation scheme is described <a
 * href=https://vsl.cis.udel.edu/trac/civl/wiki/MPITransformation>here</a>.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class MPITransformer extends BaseTransformer {

	/* **************************** Static Fields ************************** */
	public static String CODE = "mpi";
	public static String LONG_NAME = "MPITransformer";
	public static String SHORT_DESCRIPTION = "transforms C/MPI program to CIVL-C";

	private static String COMM_WORLD = "MPI_COMM_WORLD";
	private static String GCOMM_WORLD = "GCOMM_WORLD";
	private static String GCOMM_TYPE = "$gcomm";
	private static String COMM_TYPE = "MPI_Comm";
	private static String GCOMM_CREATE = "$gcomm_create";
	private static String COMM_CREATE = "$comm_create";
	private static String GCOMM_DESTROY = "$gcomm_destroy";
	private static String COMM_DESTROY = "$comm_destroy";
	private static String MPI_MAIN = "__main";
	private static String MPI_RANK = "__rank";
	private static String MPI_INIT = "MPI_Init";
	private static String MPI_INIT_NEW = "__MPI_Init";
	private static String MPI_PROCESS = "MPI_Process";
	private static String NPROCS = "__NPROCS";
	private static String NPROCS_BOUND = "__NPROCS_BOUND";
	private static String PROCS = "__procs";
	private static String WAIT = "$wait";
	private static String PROC_TYPE = "$proc";

	/* **************************** Instant Fields ************************* */

	/**
	 * There are new nodes created by the transformer, other than parsing from
	 * some source file. All new nodes share the same source. TODO create an
	 * appropriate source
	 */
	private Source source;

	/* ****************************** Constructor ************************** */
	/**
	 * Creates a new instance of MPITransformer.
	 * 
	 * @param astFactory
	 *            The ASTFactory that will be used to create new nodes.
	 */
	public MPITransformer(ASTFactory astFactory) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory);
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Constructs the function MPI_Process() from the original MPI program. It
	 * is a wrapper of the original MPI program with some additional features: <br>
	 * <code>void MPI_Process(){</code><br>
	 * <code>&nbsp;&nbsp;$comm MPI_COMM_WORLD = $comm_create(...);</code><br>
	 * <code>&nbsp;&nbsp;//SLIGHTLY-MODIFIED ORIGINAL PROGRAM;</code><br>
	 * <code>&nbsp;&nbsp;int a, b, ...;</code><br>
	 * <code>&nbsp;&nbsp;... function(){...}</code><br>
	 * <code>&nbsp;&nbsp;...</code><br>
	 * <code>&nbsp;&nbsp;... __main(){...} // renamed main() to __main()</code><br>
	 * <code>&nbsp;&nbsp;....</code><br>
	 * <code>&nbsp;&nbsp;//ORIGINAL PROGRAM ENDS HERE;</code><br>
	 * <code>&nbsp;&nbsp;__main();</code><br>
	 * <code>&nbsp;&nbsp;$comm_destroy(MPI_COMM_WORLD);</code><br>
	 * <code>}</code>
	 * 
	 * @param root
	 *            The root node of the AST of the original MPI program.
	 * @param includedNodes
	 *            The set of AST nodes that are parsed from header files. These
	 *            nodes will be moved up to the higher scope (i.e., the file
	 *            scope of the final AST). When invoking this function, this
	 *            parameter should be an empty list and this function will
	 *            update this list.
	 * @param vars
	 *            The list of variable declaration nodes that are the arguments
	 *            of the original main function. These variables will be moved
	 *            up to the higher scope (i.e., the file scope of the final AST)
	 *            and become $input variables of the final AST. When invoking
	 *            this function, this parameter should be an empty list and this
	 *            function will update this list.
	 * @return The function definition node of MPI_Process.
	 */
	private FunctionDefinitionNode mpiProcess(SequenceNode<ASTNode> root,
			List<ASTNode> includedNodes, List<VariableDeclarationNode> vars) {
		List<BlockItemNode> items;
		int number;
		ExpressionStatementNode callMain;
		CompoundStatementNode mpiProcessBody;
		List<VariableDeclarationNode> newFormalList;
		SequenceNode<VariableDeclarationNode> formals;
		FunctionTypeNode mpiProcessType;
		FunctionDefinitionNode mpiProcess;
		VariableDeclarationNode commVar = this.commDeclaration();
		ExpressionStatementNode commDestroy = this.commDestroy(COMM_DESTROY,
				COMM_WORLD);

		callMain = nodeFactory.newExpressionStatementNode(nodeFactory
				.newFunctionCallNode(source,
						nodeFactory
								.newIdentifierExpressionNode(source,
										nodeFactory.newIdentifierNode(source,
												MPI_MAIN)),
						new ArrayList<ExpressionNode>(), null));

		// build MPI_Process() function:
		items = new LinkedList<>();
		number = root.numChildren();
		items.add(commVar);
		for (int i = 0; i < number; i++) {
			ASTNode child = root.child(i);
			String sourceFile = child.getSource().getFirstToken()
					.getSourceFile().getName();

			root.removeChild(i);
			if (sourceFile.endsWith(".h")) {
				if (child.nodeKind() == NodeKind.VARIABLE_DECLARATION) {
					VariableDeclarationNode variableDeclaration = (VariableDeclarationNode) child;

					// ignore the MPI_COMM_WORLD declaration in mpi.h.
					if (!variableDeclaration.getName().equals(COMM_WORLD)) {
						includedNodes.add(child);
					}
				} else {
					includedNodes.add(child);
				}
			} else {
				if (child.nodeKind() == NodeKind.FUNCTION_DEFINITION) {
					FunctionDefinitionNode functionNode = (FunctionDefinitionNode) child;
					IdentifierNode functionName = (IdentifierNode) functionNode
							.child(0);

					if (functionName.name().equals("main")) {
						functionName.setName(MPI_MAIN);
						processOriginalMainFunction(functionNode, vars);
					}
				}
				items.add((BlockItemNode) child);
			}
		}
		items.add(callMain);
		items.add(commDestroy);
		mpiProcessBody = nodeFactory.newCompoundStatementNode(root.getSource(),
				items);
		newFormalList = new LinkedList<>();
		newFormalList.add(nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, MPI_RANK),
				nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT)));
		formals = nodeFactory.newSequenceNode(source,
				"FormalParameterDeclarations", newFormalList);
		mpiProcessType = nodeFactory.newFunctionTypeNode(source,
				nodeFactory.newVoidTypeNode(source), formals, true);
		mpiProcess = nodeFactory.newFunctionDefinitionNode(source,
				nodeFactory.newIdentifierNode(source, MPI_PROCESS),
				mpiProcessType, null, mpiProcessBody);
		return mpiProcess;
	}

	/**
	 * Processes the original main function, including:
	 * <ul>
	 * <li>Removes all arguments of the function;</li>
	 * <li>Renames the function as "__main";</li>
	 * <li>Changes the function call <code>MPI_Init(...)</code> to
	 * <code>_MPI_Init()</code>.</li>
	 * </ul>
	 * 
	 * @param mainFunction
	 *            The function definition node representing the original main
	 *            function.
	 * @param vars
	 *            The list of variable declaration nodes that are the arguments
	 *            of the original main function. These variables will be moved
	 *            up to the higher scope (i.e., the file scope of the final AST)
	 *            and become $input variables of the final AST. When invoking
	 *            this function, this parameter should be an empty list and this
	 *            function will update this list.
	 */
	private void processOriginalMainFunction(
			FunctionDefinitionNode mainFunction,
			List<VariableDeclarationNode> vars) {
		FunctionTypeNode functionType = mainFunction.getTypeNode();
		SequenceNode<VariableDeclarationNode> parameters = functionType
				.getParameters();
		int count = parameters.numChildren();
		CompoundStatementNode functionBody = mainFunction.getBody();

		if (count > 0) {
			List<VariableDeclarationNode> newParameters = new ArrayList<>(0);

			for (int k = 0; k < count; k++) {
				VariableDeclarationNode parameter = parameters
						.getSequenceChild(k);

				parameters.removeChild(k);
				parameter.getTypeNode().setInputQualified(true);
				vars.add(parameter);
			}
			functionType.setParameters(nodeFactory.newSequenceNode(source,
					"FormalParameterDeclarations", newParameters));
		}
		count = functionBody.numChildren();
		for (int j = 0; j < count; j++) {
			BlockItemNode block = functionBody.getSequenceChild(j);

			if (block.nodeKind() == NodeKind.STATEMENT) {
				StatementNode statementNode = (StatementNode) block;

				if (statementNode.statementKind() == StatementKind.EXPRESSION) {
					ExpressionStatementNode expressionStatement = (ExpressionStatementNode) statementNode;
					ExpressionNode expression = expressionStatement
							.getExpression();

					if (expression.expressionKind() == ExpressionKind.FUNCTION_CALL) {
						FunctionCallNode functionCall = (FunctionCallNode) expression;

						if (functionCall.getFunction().expressionKind() == ExpressionKind.IDENTIFIER_EXPRESSION) {
							IdentifierExpressionNode functionExpression = (IdentifierExpressionNode) functionCall
									.getFunction();

							if (functionExpression.getIdentifier().name()
									.equals(MPI_INIT)) {
								List<ExpressionNode> arguments = new ArrayList<>(
										0);

								functionExpression.getIdentifier().setName(
										MPI_INIT_NEW);
								functionCall.setChild(1, nodeFactory
										.newSequenceNode(source,
												"ActualParameterList",
												arguments));
							}
						}
					}

				}
			}
		}
	}

	/**
	 * Creates an expression statement node of a function call node for
	 * destroying a communicator, either global or local.
	 * 
	 * @param destroy
	 *            The name of the function call, either
	 *            <code>$gcomm_destroy</code> or <code>$comm_destroy</code>.
	 * @param commName
	 *            The name of the variable of the communicator to be destroy,
	 *            either <code>GCOMM_WORLD</code> or <code>MPI_COMM_WORLD</code>
	 *            .
	 * @return The expression statement node of the function call for destroying
	 *         the specified communicator.
	 */
	private ExpressionStatementNode commDestroy(String destroy, String commName) {
		IdentifierExpressionNode function = nodeFactory
				.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, destroy));
		List<ExpressionNode> arguments = new ArrayList<>(1);

		arguments.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, commName)));
		return nodeFactory.newExpressionStatementNode(nodeFactory
				.newFunctionCallNode(source, function, arguments, null));
	}

	/**
	 * Creates the declaration node for the input variable <code>NPROCS</code>.
	 * 
	 * @return The declaration node of the input variable <code>NPROCS</code>.
	 */
	private VariableDeclarationNode nprocsDeclaration() {
		TypeNode nprocsType = nodeFactory.newBasicTypeNode(source,
				BasicTypeKind.INT);

		nprocsType.setInputQualified(true);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, NPROCS), nprocsType);
	}

	/**
	 * Creates the declaration node for the input variable <code>NPROCS</code>.
	 * 
	 * @return The declaration node of the input variable <code>NPROCS</code>.
	 */
	private VariableDeclarationNode nprocsBoundDeclaration() {
		TypeNode nprocsBoundType = nodeFactory.newBasicTypeNode(source,
				BasicTypeKind.INT);

		nprocsBoundType.setInputQualified(true);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, NPROCS_BOUND),
				nprocsBoundType);
	}

	/**
	 * Creates the declaration node for the variable <code>MPI_COMM_WORLD</code>
	 * , which is of <code>MPI_Comm</code> type and has an initializer to call
	 * <code>$comm_create()</code>. That is:
	 * <code>MPI_Comm MPI_COMM_WORLD = $comm_create($here, GCOMM_WORLD, _rank)</code>
	 * .
	 * 
	 * @return The declaration node of the variable <code>MPI_COMM_WORLD</code>.
	 */
	private VariableDeclarationNode commDeclaration() {
		TypeNode commType;
		List<ExpressionNode> commCreateArgs;
		ExpressionNode commCreate;

		commType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, COMM_TYPE), null);
		commCreateArgs = new ArrayList<>(3);
		commCreateArgs.add(nodeFactory.newHereNode(source));
		commCreateArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, GCOMM_WORLD)));
		commCreateArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, MPI_RANK)));
		commCreate = nodeFactory.newFunctionCallNode(
				source,
				nodeFactory.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, COMM_CREATE)),
				commCreateArgs, null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, COMM_WORLD), commType,
				commCreate);
	}

	/**
	 * Creates the declaration node for the variable <code>GCOMM_WORLD</code> ,
	 * which is of <code>$gcomm</code> type and has an initializer to call
	 * <code>$gcomm_create()</code>. That is:
	 * <code>$gcomm MPI_COMM_WORLD = $gcomm_create($here, NPROCS)</code> .
	 * 
	 * @return The declaration node of the variable <code>GCOMM_WORLD</code>.
	 */
	private VariableDeclarationNode gcommDeclaration() {
		TypeNode gcommType;
		List<ExpressionNode> gcommCreateArgs;
		ExpressionNode gcommCreate;

		gcommType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, GCOMM_TYPE), null);
		gcommCreateArgs = new ArrayList<>(2);
		gcommCreateArgs.add(nodeFactory.newHereNode(source));
		gcommCreateArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, NPROCS)));
		gcommCreate = nodeFactory.newFunctionCallNode(
				source,
				nodeFactory.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, GCOMM_CREATE)),
				gcommCreateArgs, null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, GCOMM_WORLD), gcommType,
				gcommCreate);
	}

	/**
	 * Creates the main function for the final program, which is: <br>
	 * <code>void main(){</code><br>
	 * <code>&nbsp;&nbsp;$proc procs[NPROCS];</code><br>
	 * <code>&nbsp;&nbsp;for(int i = 0; i < NPROCS; i++){</code><br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;procs[i] = $spawn MPI_Process(i);</code><br>
	 * <code>&nbsp;&nbsp;}</code><br>
	 * <code>&nbsp;&nbsp;for(int i = 0; i < NPROCS; i++){</code><br>
	 * <code>&nbsp;&nbsp;&nbsp;&nbsp;$wait(procs[i]);</code><br>
	 * <code>&nbsp;&nbsp;}</code><br>
	 * <code>&nbsp;&nbsp;$gcomm_destroy(GCOMM_WORLD);</code><br>
	 * <code>}</code><br>
	 * 
	 * @return The function definition node representing the main function of
	 *         the final program.
	 * @throws SyntaxException
	 */
	private FunctionDefinitionNode mainFunction() throws SyntaxException {
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();
		TypeNode procsType;
		VariableDeclarationNode procsVar;
		List<VariableDeclarationNode> initialList;
		ForLoopInitializerNode initializerNode;
		List<ExpressionNode> operatorArgs;
		ExpressionNode loopCondition, incrementer, spawnProc, waitProc, leftHandSide;
		List<ExpressionNode> callArgs;
		StatementNode assign;
		ForLoopNode forLoop;
		CompoundStatementNode mainBody;
		LinkedList<VariableDeclarationNode> newFormalList;
		SequenceNode<VariableDeclarationNode> formals;
		FunctionTypeNode mainType;
		FunctionDefinitionNode mainFunction;
		ExpressionStatementNode gcommDestroy = this.commDestroy(GCOMM_DESTROY,
				GCOMM_WORLD);

		// declaring $proc procs[NPROCS];
		procsType = nodeFactory
				.newArrayTypeNode(source,
						nodeFactory.newTypedefNameNode(nodeFactory
								.newIdentifierNode(source, PROC_TYPE), null),
						nodeFactory.newIdentifierExpressionNode(source,
								nodeFactory.newIdentifierNode(source, NPROCS)));
		procsVar = nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, PROCS), procsType);
		items.add(procsVar);

		// first for loop;
		initialList = new LinkedList<>();
		initialList.add(nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, "i"),
				nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT),
				nodeFactory.newIntegerConstantNode(source, "0")));
		initializerNode = nodeFactory.newForLoopInitializerNode(source,
				initialList);
		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, NPROCS)));

		loopCondition = nodeFactory.newOperatorNode(source, Operator.LT,
				operatorArgs);
		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));

		incrementer = nodeFactory.newOperatorNode(source,
				Operator.POSTINCREMENT, operatorArgs);
		callArgs = new ArrayList<>(1);
		callArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));
		spawnProc = nodeFactory.newSpawnNode(source, nodeFactory
				.newFunctionCallNode(source, nodeFactory
						.newIdentifierExpressionNode(source, nodeFactory
								.newIdentifierNode(source, MPI_PROCESS)),
						callArgs, null));
		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, PROCS)));
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));
		leftHandSide = nodeFactory.newOperatorNode(source, Operator.SUBSCRIPT,
				operatorArgs);
		operatorArgs = new LinkedList<>();
		operatorArgs.add(leftHandSide);
		operatorArgs.add(spawnProc);
		assign = nodeFactory.newExpressionStatementNode(nodeFactory
				.newOperatorNode(source, Operator.ASSIGN, operatorArgs));
		forLoop = nodeFactory.newForLoopNode(source, initializerNode,
				loopCondition, incrementer, assign, null);
		items.add(forLoop);

		// second for loop;
		initialList = new LinkedList<>();
		initialList.add(nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, "i"),
				nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT),
				nodeFactory.newIntegerConstantNode(source, "0")));
		initializerNode = nodeFactory.newForLoopInitializerNode(source,
				initialList);
		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, NPROCS)));

		loopCondition = nodeFactory.newOperatorNode(source, Operator.LT,
				operatorArgs);
		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));

		incrementer = nodeFactory.newOperatorNode(source,
				Operator.POSTINCREMENT, operatorArgs);
		callArgs = new ArrayList<>(1);

		operatorArgs = new LinkedList<>();
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, PROCS)));
		operatorArgs.add(nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, "i")));
		callArgs.add(nodeFactory.newOperatorNode(source, Operator.SUBSCRIPT,
				operatorArgs));
		waitProc = nodeFactory.newFunctionCallNode(
				source,
				nodeFactory.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, WAIT)), callArgs,
				null);
		forLoop = nodeFactory.newForLoopNode(source, initializerNode,
				loopCondition, incrementer,
				nodeFactory.newExpressionStatementNode(waitProc), null);
		items.add(forLoop);

		// destroying GCOMM_WROLD;
		items.add(gcommDestroy);

		// constructing the function definition node.
		mainBody = nodeFactory.newCompoundStatementNode(source, items);
		newFormalList = new LinkedList<>();
		newFormalList.add(nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, MPI_RANK),
				nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT)));
		formals = nodeFactory.newSequenceNode(source,
				"FormalParameterDeclarations", newFormalList);
		mainType = nodeFactory.newFunctionTypeNode(source,
				nodeFactory.newVoidTypeNode(source), formals, true);
		mainFunction = nodeFactory.newFunctionDefinitionNode(source,
				nodeFactory.newIdentifierNode(source, "main"), mainType, null,
				mainBody);
		return mainFunction;
	}

	private AssumeNode boundAssumption(String variable, String upperBound)
			throws SyntaxException {
		IdentifierExpressionNode variableExpression = nodeFactory
				.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, variable));
		IdentifierExpressionNode upperBoundExpression = nodeFactory
				.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, upperBound));
		IntegerConstantNode zero = nodeFactory.newIntegerConstantNode(source,
				"0");
		List<ExpressionNode> arguments = new ArrayList<>(2);
		ExpressionNode lowerPart, upperPart;

		arguments.add(zero);
		arguments.add(variableExpression);
		lowerPart = nodeFactory.newOperatorNode(source, Operator.LT, arguments);
		arguments = new ArrayList<>(2);
		variableExpression = nodeFactory.newIdentifierExpressionNode(source,
				nodeFactory.newIdentifierNode(source, variable));
		arguments.add(variableExpression);
		arguments.add(upperBoundExpression);
		upperPart = nodeFactory
				.newOperatorNode(source, Operator.LTE, arguments);
		arguments = new ArrayList<>(2);
		arguments.add(lowerPart);
		arguments.add(upperPart);
		return nodeFactory.newAssumeNode(source,
				nodeFactory.newOperatorNode(source, Operator.LAND, arguments));
	}

	/* ********************* Methods From BaseTransformer ****************** */

	@SuppressWarnings("unchecked")
	@Override
	public AST transform(AST ast) throws SyntaxException {
		ASTNode root = ast.getRootNode();
		AST newAst;
		FunctionDefinitionNode mpiProcess, mainFunction;
		VariableDeclarationNode gcommWorld;
		List<ASTNode> externalList;
		VariableDeclarationNode nprocsVar;
		VariableDeclarationNode nprocsBoundVar;
		SequenceNode<ASTNode> newRootNode;
		List<ASTNode> includedNodes = new ArrayList<ASTNode>();
		List<VariableDeclarationNode> mainParameters = new ArrayList<>();
		int count;
		AssumeNode nprocsAssumption;

		this.source = root.getSource();
		assert this.astFactory == ast.getASTFactory();
		assert this.nodeFactory == astFactory.getNodeFactory();
		ast.release();
		// declaring $input int NPROCS;
		nprocsVar = this.nprocsDeclaration();
		// declaring $input int NPROCS_BOUND;
		nprocsBoundVar = this.nprocsBoundDeclaration();
		// assuming 0 < NPROCS && NPROCS <= NPROCS_BOUND
		nprocsAssumption = this.boundAssumption(NPROCS, NPROCS_BOUND);
		// declaring $gcomm GCOMM_WORLD = $gcomm_create($here, NPROCS);
		gcommWorld = this.gcommDeclaration();
		// defining MPI_Process(_rank){...};
		mpiProcess = this.mpiProcess((SequenceNode<ASTNode>) root,
				includedNodes, mainParameters);
		// defining the main function;
		mainFunction = mainFunction();
		// the translated program is:
		// input variables;
		// gcomm
		// MPI_Process() definition;
		// main function.
		externalList = new LinkedList<>();
		count = includedNodes.size();
		// adding nodes from header files.
		for (int i = 0; i < count; i++) {
			externalList.add(includedNodes.get(i));
		}
		count = mainParameters.size();
		// adding nodes from the arguments of the original main function.
		for (int i = 0; i < count; i++) {
			externalList.add(mainParameters.get(i));
		}
		externalList.add(nprocsVar);
		externalList.add(nprocsBoundVar);
		externalList.add(nprocsAssumption);
		externalList.add(gcommWorld);
		externalList.add(mpiProcess);
		externalList.add(mainFunction);
		newRootNode = nodeFactory.newSequenceNode(null, "TranslationUnit",
				externalList);
		newAst = astFactory.newTranslationUnit(newRootNode);
		return newAst;
	}
}
