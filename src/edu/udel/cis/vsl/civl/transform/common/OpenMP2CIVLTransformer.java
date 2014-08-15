package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode.NodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpForNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpReductionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpSymbolReductionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpSyncNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CivlForNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.DeclarationListNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.common.CommonIdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.common.omp.CommonOmpForNode;
import edu.udel.cis.vsl.abc.ast.node.common.omp.CommonOmpParallelNode;
import edu.udel.cis.vsl.abc.ast.node.common.omp.CommonOmpWorkshareNode;
import edu.udel.cis.vsl.abc.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.util.IF.Pair;
import edu.udel.cis.vsl.civl.util.IF.Triple;

/**
 * OpenMP2CIVLTransformer transforms an AST of an OpenMP program into an AST of
 * an equivalent CIVL-C program. See {@linkplain #transform(AST)}.
 * 
 * @author Michael Rogers
 * 
 */
public class OpenMP2CIVLTransformer extends CIVLBaseTransformer {

	/* ************************** Public Static Fields *********************** */
	/**
	 * The code (short name) of this transformer.
	 */
	public final static String CODE = "openmp";

	/**
	 * The long name of the transformer.
	 */
	public static String LONG_NAME = "OpenMPTransformer";

	/**
	 * The description of this transformer.
	 */
	public static String SHORT_DESCRIPTION = "transforms C/OpenMP program to CIVL-C";

	/* ************************** Private Static Fields ********************** */

	/**
	 * The name of the identifier of the $omp_gteam variable in the final CIVL
	 * program.
	 */
	private static String GTEAM = "gteam";

	/**
	 * The name of the identifier of the $omp_team variable in the final CIVL
	 * program.
	 */
	private static String TEAM = "team";

	/**
	 * The name of $omp_gteam type in the final CIVL-C program.
	 */
	private static String GTEAM_TYPE = "$omp_gteam";

	/**
	 * The name of $omp_team type in the final CIVL-C program.
	 */
	private static String TEAM_TYPE = "$omp_team";

	/**
	 * The name of the function to create a new $omp_gws object in the final
	 * CIVL-C program.
	 */
	private static String GTEAM_CREATE = "$omp_gteam_create";

	/**
	 * The name of the function to create a new $omp_ws object in the final
	 * CIVL-C program.
	 */
	private static String TEAM_CREATE = "$omp_team_create";

	/**
	 * The name of $omp_gshared type in the final CIVL-C program.
	 */
	private static String GSHARED_TYPE = "$omp_gshared";

	/**
	 * The name of $omp_shared type in the final CIVL-C program.
	 */
	private static String SHARED_TYPE = "$omp_shared";

	/**
	 * The name of the function to create a new $omp_gshared object in the final
	 * CIVL-C program.
	 */
	private static String GSHARED_CREATE = "$omp_gshared_create";

	/**
	 * The name of the function to create a new $omp_shared object in the final
	 * CIVL-C program.
	 */
	private static String SHARED_CREATE = "$omp_shared_create";

	/**
	 * The name of the input variable denoting the number of OpenMP threads in
	 * the final CIVL-C program.
	 */
	private static String NTHREADS = "_nthreads";

	/**
	 * The name of the input variable denoting the number of OpenMP threads in
	 * the final CIVL-C program.
	 */
	private static String THREADMAX = "THREAD_MAX";

	/**
	 * The name of the variable denoting the thread number in the CIVL_C
	 * program.
	 */
	private static String TID = "_tid";
	
	private int tmpCount = 0;
	
	private ArrayList<Triple<String, StatementNode, String>> sharedReplaced = new ArrayList<Triple<String, StatementNode, String>>();
	
	private ArrayList<String> criticalNames = new ArrayList<String>();
	
	/* **************************** Instant Fields ************************* */

	/**
	 * There are new nodes created by the transformer, other than parsing from
	 * some source file. All new nodes share the same source.
	 */
	private Source source;

	/* ****************************** Constructor ************************** */
	/**
	 * Creates a new instance of OpenMP2CIVLTransformer.
	 * 
	 * @param astFactory
	 *            The ASTFactory that will be used to create new nodes.
	 */
	public OpenMP2CIVLTransformer(ASTFactory astFactory,
			List<String> inputVariables, CIVLConfiguration config) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory, inputVariables,
				config);
	}

	/* *************************** Private Methods ************************* */

	/**
	 * Creates the declaration node for the input variable
	 * <code>THREAD_MAX</code>.
	 * 
	 * @return The declaration node of the input variable
	 *         <code>THREAD_MAX</code>.
	 */
	private VariableDeclarationNode threadMaxDeclaration() {
		TypeNode nthreadsType = nodeFactory.newBasicTypeNode(source,
				BasicTypeKind.INT);

		nthreadsType.setInputQualified(true);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, THREADMAX), nthreadsType);
	}

	/**
	 * Creates the declaration node for the variable <code>gteam</code> , which
	 * is of <code>$omp_gteam</code> type and has an initializer to call
	 * <code>$omp_gteam_create()</code>. That is:
	 * <code>$omp_gteam gteam = $omp_gteam_create($here, NTHREADS)</code> .
	 * 
	 * @return The declaration node of the variable <code>gteam</code>.
	 */
	private VariableDeclarationNode gteamDeclaration() {
		TypeNode gteamType;
		ExpressionNode gteamCreate;

		gteamType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, GTEAM_TYPE), null);
		gteamCreate = nodeFactory.newFunctionCallNode(
				source,
				this.identifierExpression(source, GTEAM_CREATE),
				Arrays.asList(nodeFactory.newHereNode(source),
						this.identifierExpression(source, NTHREADS)), null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, GTEAM), gteamType,
				gteamCreate);
	}

	/**
	 * Creates the declaration node for the variable <code>team</code> , which
	 * is of <code>$omp_team</code> type and has an initializer to call
	 * <code>$omp_team_create()</code>. That is:
	 * <code>$omp_team team = $omp_team_create($here, gteam, _tid)</code> .
	 * 
	 * @return The declaration node of the variable <code>_gws</code>.
	 */
	private VariableDeclarationNode teamDeclaration() {
		TypeNode teamType;
		ExpressionNode teamCreate;

		teamType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, TEAM_TYPE), null);
		teamCreate = nodeFactory.newFunctionCallNode(
				source,
				this.identifierExpression(source, TEAM_CREATE),
				Arrays.asList(nodeFactory.newHereNode(source),
						this.identifierExpression(source, GTEAM),
						this.identifierExpression(source, TID)), null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, TEAM), teamType,
				teamCreate);
	}

	private VariableDeclarationNode gsharedDeclaration(String variable) {
		TypeNode gsharedType;
		ExpressionNode gsharedCreate;

		ExpressionNode addressOf = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF,
				Arrays.asList(this.identifierExpression(source, variable)));

		gsharedType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, GSHARED_TYPE), null);
		gsharedCreate = nodeFactory.newFunctionCallNode(source, this
				.identifierExpression(source, GSHARED_CREATE), Arrays.asList(
				this.identifierExpression(source, GTEAM), addressOf), null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, variable + "_gshared"),
				gsharedType, gsharedCreate);
	}

	private VariableDeclarationNode sharedDeclaration(String variable) {
		TypeNode sharedType;
		ExpressionNode sharedCreate;

		sharedType = nodeFactory.newTypedefNameNode(
				nodeFactory.newIdentifierNode(source, SHARED_TYPE), null);
		sharedCreate = nodeFactory
				.newFunctionCallNode(source, this.identifierExpression(source,
						SHARED_CREATE), Arrays.asList(this
						.identifierExpression(source, TEAM), this
						.identifierExpression(source, variable + "_gshared")),
						null);
		return nodeFactory.newVariableDeclarationNode(source,
				nodeFactory.newIdentifierNode(source, variable + "_shared"),
				sharedType, sharedCreate);
	}

	private ExpressionStatementNode destroy(String type, String object) {
		ExpressionNode function = this.identifierExpression(source, "$omp_"
				+ type + "_destroy");

		return nodeFactory.newExpressionStatementNode(nodeFactory
				.newFunctionCallNode(source, function, Arrays.asList(this
						.identifierExpression(source, object)), null));
	}

	private ExpressionStatementNode barrierAndFlush(String object) {
		ExpressionNode function = this.identifierExpression(source,
				"$omp_barrier_and_flush");

		return nodeFactory.newExpressionStatementNode(nodeFactory
				.newFunctionCallNode(source, function, Arrays.asList(this
						.identifierExpression(source, object)), null));
	}

	private ExpressionStatementNode write(String variable, String sharedName) {
		ExpressionNode function = this.identifierExpression(source,
				"$omp_write");
		ExpressionNode addressOfVar = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF,
				Arrays.asList(this.identifierExpression(source, variable)));
		ExpressionNode addressOfTmp = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF,
				Arrays.asList(this.identifierExpression(source, "tmp")));
		return nodeFactory
				.newExpressionStatementNode(nodeFactory.newFunctionCallNode(
						source, function, Arrays.asList(
								this.identifierExpression(source, sharedName
										+ "_shared"), addressOfVar,
								addressOfTmp), null));
	}

	private ExpressionStatementNode read(String variable, String sharedName, String tmpName) {
		ExpressionNode function = this
				.identifierExpression(source, "$omp_read");
		ExpressionNode addressOfVar = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF,
				Arrays.asList(this.identifierExpression(source, variable)));
		ExpressionNode addressOfTmp = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF, 
				Arrays.asList(this.identifierExpression(source, tmpName)));
		return nodeFactory
				.newExpressionStatementNode(nodeFactory.newFunctionCallNode(
						source, function, Arrays.asList(
								this.identifierExpression(source, sharedName 
										+ "_shared"), addressOfVar, 
									addressOfTmp), null));
	}
	
	private ExpressionStatementNode applyAssoc(String variable, String operation) {
		ExpressionNode function = this
				.identifierExpression(source, "$omp_apply_assoc");
		ExpressionNode addressOfVar = nodeFactory.newOperatorNode(source,
				Operator.ADDRESSOF,
				Arrays.asList(this.identifierExpression(source, "_" + variable)));
		if(operation.equals("PLUSEQ")){
			operation = "CIVL_SUM";
		}
		return nodeFactory
				.newExpressionStatementNode(nodeFactory.newFunctionCallNode(
						source, function, Arrays.asList(
								this.identifierExpression(source, variable 
										+ "_shared"), this.identifierExpression(source, operation), addressOfVar), null));
	}

	/* ********************* Methods From BaseTransformer ****************** */

	/**
	 * 
	 * 
	 */
	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<ExternalDefinitionNode> root = ast.getRootNode();
		AST newAst;
		List<ExternalDefinitionNode> externalList;
		VariableDeclarationNode threadMax;
		SequenceNode<ExternalDefinitionNode> newRootNode;
		List<ExternalDefinitionNode> includedNodes = new ArrayList<>();
		List<VariableDeclarationNode> mainParameters = new ArrayList<>();
		int count;
		Triple<List<ExternalDefinitionNode>, List<ExternalDefinitionNode>, List<VariableDeclarationNode>> result;

		this.source = root.getSource();
		assert this.astFactory == ast.getASTFactory();
		assert this.nodeFactory == astFactory.getNodeFactory();
		ast.release();

		// declaring $input int THREAD_MAX;
		threadMax = this.threadMaxDeclaration();

		if (!this.inputVariableNames.contains(THREADMAX)) {
			throw new SyntaxException(
					"Please specify the number of processes (e.g., -inputTHREAD_MAX=5)",
					source);
		}

		replaceOMPPragmas(root, null, null, null, null);

		result = this.program(root);
		includedNodes = result.second;
		mainParameters = result.third;

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
		externalList.add(threadMax);
		for(String name : criticalNames){
			externalList.add(nodeFactory.newVariableDeclarationNode(source, 
					nodeFactory.newIdentifierNode(source, name), nodeFactory
					.newBasicTypeNode(source, BasicTypeKind.BOOL), 
					nodeFactory.newBooleanConstantNode(source, false)));
		}
		externalList.addAll(result.first);
		newRootNode = nodeFactory.newSequenceNode(null, "TranslationUnit",
				externalList);
		newAst = astFactory.newAST(newRootNode);

		return newAst;
	}

	@SuppressWarnings("unchecked")
	private void replaceOMPPragmas(ASTNode node,
			SequenceNode<IdentifierExpressionNode> privateIDs,
			SequenceNode<IdentifierExpressionNode> sharedIDs,
			SequenceNode<IdentifierExpressionNode> reductionIDs,
			SequenceNode<IdentifierExpressionNode> firstPrivateIDs)
			throws SyntaxException {
		if (node instanceof CommonOmpParallelNode) {
			List<BlockItemNode> items;
			CompoundStatementNode pragmaBody;
			VariableDeclarationNode gteamVar;
			SequenceNode<IdentifierExpressionNode> sharedList;
			SequenceNode<IdentifierExpressionNode> privateList;
			SequenceNode<IdentifierExpressionNode> firstPrivateList;
			SequenceNode<IdentifierExpressionNode> reductionList = null;
			SequenceNode<OmpReductionNode> ompReductionNode;
			Iterable<ASTNode> children;
			items = new LinkedList<>();
			children = node.children();

			// int _nthreads = 1+$choose_int(THREAD_MAX);
			VariableDeclarationNode nthreads;
			ExpressionNode add;

			add = nodeFactory.newOperatorNode(source, Operator.PLUS, Arrays
					.asList(nodeFactory.newIntegerConstantNode(source, "1"),
							nodeFactory.newFunctionCallNode(source,
									this.identifierExpression(source,
											"$choose_int"), Arrays.asList(this
											.identifierExpression(source,
													THREADMAX)), null)));

			nthreads = nodeFactory.newVariableDeclarationNode(source,
					nodeFactory.newIdentifierNode(source, "_nthreads"),
					nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT),
					add);
			items.add(nthreads);

			VariableDeclarationNode threadRange;
			threadRange = nodeFactory.newVariableDeclarationNode(source, 
					nodeFactory.newIdentifierNode(source, "thread_range"), 
					nodeFactory.newRangeTypeNode(source), nodeFactory.newRegularRangeNode(source, nodeFactory.newIntegerConstantNode(
							source, "0"),  nodeFactory.newOperatorNode(
									source, Operator.MINUS, 
									Arrays.asList(this.identifierExpression(source, "_nthreads"), 
											nodeFactory.newIntegerConstantNode(source, "1")))));
			items.add(threadRange);
			
			
			
			VariableDeclarationNode loopDomain;
			loopDomain = nodeFactory.newVariableDeclarationNode(source,
					nodeFactory.newIdentifierNode(source, "dom"),
					nodeFactory.newDomainTypeNode(source), nodeFactory.
					newCastNode(source, nodeFactory.newDomainTypeNode(source), 
							this.identifierExpression(source, "thread_range")));
			items.add(loopDomain);

			// Declaring $omp_gteam gteam = $omp_gteam_create($here, nthreads);
			gteamVar = this.gteamDeclaration();
			items.add(gteamVar);

			sharedList = ((CommonOmpParallelNode) node).sharedList();
			privateList = ((CommonOmpParallelNode) node).privateList();
			firstPrivateList = ((CommonOmpParallelNode) node).firstprivateList();
			
			OmpSymbolReductionNode reductionNode = null;
			ompReductionNode = ((CommonOmpParallelNode) node).reductionList();
			if (ompReductionNode != null) {
				node.removeChild(6);
				reductionNode = (OmpSymbolReductionNode) ompReductionNode.child(0);
				reductionList = (SequenceNode<IdentifierExpressionNode>) reductionNode.child(0);
			}

			// Declaring $omp_gshared x_gshared = $omp_gshared_create(gteam, &x)
			// for each shared variable "x"
			if(sharedList != null){
				node.removeChild(0);
				for (ASTNode child : sharedList.children()) {
					VariableDeclarationNode gsharedVar;
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();

					gsharedVar = this.gsharedDeclaration(c.name());
					items.add(gsharedVar);
				}
			}
			CivlForNode cfn;
			ForLoopInitializerNode initializerNode;

			initializerNode = nodeFactory.newForLoopInitializerNode(source,
					Arrays.asList(nodeFactory.newVariableDeclarationNode(
							source, nodeFactory.newIdentifierNode(source,
									"_tid"), nodeFactory.newBasicTypeNode(
									source, BasicTypeKind.INT))));

			List<BlockItemNode> parForItems = new LinkedList<>();

			// $omp_team team = $omp_team_create($here, gteam, _tid);
			parForItems.add(teamDeclaration());

			// Declare $omp_shared x_shared = $omp_shared_create(team,
			// x_gshared)
			// for each shared variable "x"
			if (sharedList != null) {
				for (ASTNode child : sharedList.children()) {
					VariableDeclarationNode sharedVar;
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();

					sharedVar = this.sharedDeclaration(c.name());
					parForItems.add(sharedVar);
				}
			}
			
			//Add firstprivate variable declarations
			if(firstPrivateList != null){
				node.removeChild(2);
				for (ASTNode child : firstPrivateList.children()) {
					VariableDeclarationNode firstPrivate = addPrivateVariable(
							(IdentifierExpressionNode) child, "first");
					parForItems.add(firstPrivate);
				}
			}
			
			//Add reduction variable declarations
			if(reductionList != null){
				for (ASTNode child : reductionList.children()) {
					VariableDeclarationNode localPrivate = addPrivateVariable(
							(IdentifierExpressionNode) child, "reduction");
					parForItems.add(localPrivate);
				}
			}

			// Declare local copies of the private variables
			if (privateList != null) {
				node.removeChild(1);
				for (ASTNode child : privateList.children()) {
					VariableDeclarationNode localPrivate = addPrivateVariable(
							(IdentifierExpressionNode) child, "regular");
					parForItems.add(localPrivate);
				}
			}

			int i = 0;
			for (ASTNode child : children) {
				node.removeChild(i);
				parForItems.add((BlockItemNode) child);
				i++;
			}

			// $omp_barrier_and_flush(team);
			parForItems.add(barrierAndFlush(TEAM));

			// $omp_shared_destroy(x_shared);
			// for each shared variable "x"
			if(sharedList != null){
				for (ASTNode child : sharedList.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();

					parForItems.add(destroy("shared", c.name() + "_shared"));
				}
			}

			// $omp_team_destroy(team);
			parForItems.add(destroy(TEAM, TEAM));

			StatementNode parForBody;
			parForBody = nodeFactory.newCompoundStatementNode(source,
					parForItems);

			cfn = nodeFactory.newCivlForNode(source, true,
					(DeclarationListNode) initializerNode, 
					this.identifierExpression(source, "dom"),
							parForBody, null);

			items.add(cfn);

			// $omp_shared_destroy(x_gshared);
			// for each shared variable "x"
			if(sharedList != null){
				for (ASTNode child : sharedList.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();

					items.add(destroy("gshared", c.name() + "_gshared"));
				}
			}

			// $omp_gteam_destroy(gteam);
			items.add(destroy("gteam", "gteam"));

			pragmaBody = nodeFactory.newCompoundStatementNode(source, items);

			int index = node.childIndex();
			ASTNode parent = node.parent();
			parent.setChild(index, pragmaBody);
			children = pragmaBody.children();

			for (ASTNode child : children) {
				replaceOMPPragmas(child, privateList, sharedList, reductionList, firstPrivateList);
			}
		} else if (node instanceof CommonOmpForNode) {
			ForLoopInitializerNode initializerNode;
			SequenceNode<OmpReductionNode> ompReductionNode;
			SequenceNode<IdentifierExpressionNode> reductionList = null;
			List<BlockItemNode> items;
			List<BlockItemNode> forItems;
			CompoundStatementNode pragmaBody;
			Iterable<ASTNode> children = node.children();
			items = new LinkedList<>();
			forItems = new LinkedList<>();
			VariableDeclarationNode loopDomain;
			int collapseLevel = ((OmpForNode) node).collapse();
			collapseLevel = 1;
			ASTNode body = null;
			ArrayList<Pair<ASTNode, ASTNode>> ranges = new ArrayList<Pair<ASTNode, ASTNode>>();
			ArrayList<IdentifierNode> loopVariables = new ArrayList<IdentifierNode>();
			SequenceNode<IdentifierExpressionNode> firstPrivateList;
			
			ForLoopNode currentLoop = null;
			OperatorNode initializer;
			OperatorNode condition;
			OmpSymbolReductionNode reductionNode = null;
			ompReductionNode = ((CommonOmpForNode) node).reductionList();
			if (ompReductionNode != null) {
				node.removeChild(6);
				reductionNode = (OmpSymbolReductionNode) ompReductionNode.child(0);
				reductionList = (SequenceNode<IdentifierExpressionNode>) reductionNode.child(0);
			}
			
			firstPrivateList = ((CommonOmpForNode) node).firstprivateList();

			for(int i = 0; i<collapseLevel; i++){
				if(i == 0){				
					currentLoop = (ForLoopNode) node.child(7);
					body = currentLoop.child(1);
				} else {
					currentLoop = (ForLoopNode) currentLoop.getBody();
					body = currentLoop.child(1);
				}
				initializer = (OperatorNode) currentLoop.child(3);
				condition = (OperatorNode) currentLoop.getCondition();
				
				ranges.add(new Pair<ASTNode, ASTNode>(initializer.child(1), condition.child(1)));
				loopVariables.add((IdentifierNode) initializer.child(0).child(0));
			}
			
			children = body.children();
			loopDomain = nodeFactory.newVariableDeclarationNode(source,
					nodeFactory.newIdentifierNode(source, "loop_domain"),
					nodeFactory.newDomainTypeNode(source));
			items.add(loopDomain);
			ExpressionNode ompArriveLoop = nodeFactory.newCastNode(source,
					nodeFactory.newDomainTypeNode(source,
							nodeFactory.newIntegerConstantNode(source, "0")),
					nodeFactory.newFunctionCallNode(source, this
							.identifierExpression(source, "$omp_arrive_loop"),
							Arrays.asList(this.identifierExpression(source,
									TEAM), this.identifierExpression(source,
									"loop_domain")), null));

			IntegerConstantNode domainLevel;
			if(collapseLevel == 1){
				domainLevel = nodeFactory.newIntegerConstantNode(source, "1");
			} else {
				domainLevel = nodeFactory.newIntegerConstantNode(source, String.valueOf(collapseLevel));
			}

			VariableDeclarationNode myIters;

			myIters = nodeFactory.newVariableDeclarationNode(source,
					nodeFactory.newIdentifierNode(source, "my_iters"), 
					nodeFactory.newDomainTypeNode(source, domainLevel), 
					ompArriveLoop);

			items.add(myIters);
			
			//Add firstprivate variable declarations
			if(firstPrivateList != null){
				node.removeChild(2);
				for (ASTNode child : firstPrivateList.children()) {
					VariableDeclarationNode firstPrivate = addPrivateVariable(
							(IdentifierExpressionNode) child, "first");
					items.add(firstPrivate);
				}
				firstPrivateIDs = firstPrivateList;
			}
			
			//Add reduction variable declarations
			if(reductionList != null){
				for (ASTNode child : reductionList.children()) {
					VariableDeclarationNode localPrivate = addPrivateVariable(
							(IdentifierExpressionNode) child, "reduction");
					items.add(localPrivate);
				}
				reductionIDs = reductionList;
			}
			CivlForNode cfn;

			// for loop;

			
			List<VariableDeclarationNode> declarations = new ArrayList<VariableDeclarationNode>();
			for(IdentifierNode var : loopVariables){
				declarations.add(nodeFactory.newVariableDeclarationNode(
						source, var.copy(), nodeFactory.newBasicTypeNode(
										source, BasicTypeKind.INT)));
			}

			
			initializerNode = nodeFactory
					.newForLoopInitializerNode(source, declarations);


			for(int i = 0; i<collapseLevel; i++){
				if(i == 0){				
					currentLoop = (ForLoopNode) node.child(7);
				} else {
					currentLoop = (ForLoopNode) currentLoop.getBody();
				}
			}
			
			int i = 0;
			for (ASTNode child : children) {
				body.removeChild(i);
				forItems.add((BlockItemNode) child);
				i++;
			}

			StatementNode forBody;
			forBody = nodeFactory.newCompoundStatementNode(source, forItems);

			cfn = nodeFactory.newCivlForNode(
					source,
					true,
					(DeclarationListNode) initializerNode,
					nodeFactory.newIdentifierExpressionNode(source,
							nodeFactory.newIdentifierNode(source, "my_iters")),
					forBody, null);

			items.add(cfn);
			
			if(reductionList != null){
				for (ASTNode child : reductionList.children()) {
					String name = ((IdentifierNode) child.child(0)).name();
					String operator = reductionNode.operator().name();
					items.add(applyAssoc(name, operator));
				}
			}

			// $barrier_and_flush(team);
			if(!((CommonOmpForNode) node).nowait()){
				items.add(barrierAndFlush(TEAM));
			}

			pragmaBody = nodeFactory.newCompoundStatementNode(source, items);
			children = pragmaBody.children();
			int index = node.childIndex();
			ASTNode parent = node.parent();
			parent.setChild(index, pragmaBody);

			for (ASTNode child : children) {
				replaceOMPPragmas(child, privateIDs, sharedIDs, reductionIDs, firstPrivateIDs);
			}

		} else if (node instanceof OmpSyncNode) {
			String syncKind = ((OmpSyncNode) node).ompSyncNodeKind().toString();
			CompoundStatementNode body;
			LinkedList<BlockItemNode> items = new LinkedList<>();
			if (syncKind.equals("MASTER")) {
				List<ExpressionNode> operands = new ArrayList<ExpressionNode>();
				operands.add(nodeFactory.newIdentifierExpressionNode(source,
						nodeFactory.newIdentifierNode(source, "_tid")));
				operands.add(nodeFactory.newIntegerConstantNode(source, "0"));
				int i = 0;
				for (ASTNode child : node.children()) {
					node.removeChild(i);
					items.add((BlockItemNode) child);
					i++;
				}
				body = nodeFactory.newCompoundStatementNode(source, items);
				IfNode ifStatement = nodeFactory.newIfNode(source, nodeFactory
						.newOperatorNode(source, Operator.EQUALS, operands),
						body);
				int index = node.childIndex();
				ASTNode parent = node.parent();
				parent.setChild(index, ifStatement);
			} else if (syncKind.equals("BARRIER")) {
				ExpressionStatementNode barrierAndFlush = barrierAndFlush(TEAM);
				int index = node.childIndex();
				ASTNode parent = node.parent();
				parent.setChild(index, barrierAndFlush);
			} else if (syncKind.equals("CRITICAL")) {
				ExpressionNode notCritical = nodeFactory.newOperatorNode(source,
						Operator.NOT, Arrays.asList(this.identifierExpression(
								source, "_critical")));
				ExpressionStatementNode criticalTrue = nodeFactory.newExpressionStatementNode(nodeFactory.newOperatorNode(source, Operator.ASSIGN, 
						Arrays.asList(this.identifierExpression(source, 
								"_critical"), nodeFactory.newBooleanConstantNode(source, true))));

				items.add(nodeFactory.newWhenNode(source, notCritical, criticalTrue));
				int i = 0;
				for (ASTNode child : node.children()) {
					node.removeChild(i);
					items.add((BlockItemNode) child);
					i++;
				}
				ExpressionStatementNode criticalFalse = nodeFactory.newExpressionStatementNode(nodeFactory.newOperatorNode(source, Operator.ASSIGN, 
						Arrays.asList(this.identifierExpression(source, 
								"_critical"), nodeFactory.newBooleanConstantNode(source, false))));
				items.add(criticalFalse);
				
				body = nodeFactory.newCompoundStatementNode(source, items);
				criticalNames.add("_critical");
				int index = node.childIndex();
				ASTNode parent = node.parent();
				parent.setChild(index, body);
			}
		} else if (node instanceof CommonOmpWorkshareNode) {
			Iterable<ASTNode> children = node.children();
			String workshareKind = ((CommonOmpWorkshareNode) node)
					.ompWorkshareNodeKind().toString();
			CompoundStatementNode body;
			LinkedList<BlockItemNode> items = new LinkedList<>();
			if (workshareKind.equals("SECTIONS")) {
				privateIDs = ((CommonOmpWorkshareNode) node).privateList();
				firstPrivateIDs = ((CommonOmpWorkshareNode) node).firstprivateList();
				int numberSections = 0;
				CompoundStatementNode pragmaBody = (CompoundStatementNode) node
						.child(7);
				ArrayList<LinkedList<BlockItemNode>> sectionsChildren = new ArrayList<LinkedList<BlockItemNode>>();
				for (ASTNode child : pragmaBody.children()) {
					if (child instanceof CommonOmpWorkshareNode) {
						if (((CommonOmpWorkshareNode) child)
								.ompWorkshareNodeKind().toString()
								.equals("SECTION")) {
							LinkedList<BlockItemNode> sectionItems = new LinkedList<>();
							int i = 0;
							for (ASTNode sectionChild : child.children()) {
								child.removeChild(i);
								sectionItems.add((BlockItemNode) sectionChild);
								i++;
							}
							sectionsChildren.add(sectionItems);
							numberSections++;
						}
					}
				}

				ExpressionNode ompArriveSections = nodeFactory
						.newFunctionCallNode(source, this.identifierExpression(
								source, "$omp_arrive_sections"), Arrays.asList(
								this.identifierExpression(source, TEAM),
								this.identifierExpression(source,
										String.valueOf(numberSections))), null);

				VariableDeclarationNode my_secs;
				my_secs = nodeFactory
						.newVariableDeclarationNode(source, nodeFactory
								.newIdentifierNode(source, "my_secs"),
								nodeFactory.newDomainTypeNode(source,
										nodeFactory.newIntegerConstantNode(
												source, "1")),
								ompArriveSections);
				items.add(my_secs);

				// Declare local copies of the private variables
				if (privateIDs != null) {
					node.removeChild(1);
					for (ASTNode child : privateIDs.children()) {
						VariableDeclarationNode localPrivate = addPrivateVariable(
								(IdentifierExpressionNode) child, "regular");
						items.add(localPrivate);
					}
				}
				
				//Add firstprivate variable declarations
				if(firstPrivateIDs != null){
					node.removeChild(2);
					for (ASTNode child : firstPrivateIDs.children()) {
						VariableDeclarationNode firstPrivate = addPrivateVariable(
								(IdentifierExpressionNode) child, "first");
						items.add(firstPrivate);
					}
				}
				
				CivlForNode cfn;

				List<BlockItemNode> forItems = new LinkedList<>();

				// for loop;
				ForLoopInitializerNode initializerNode = nodeFactory
						.newForLoopInitializerNode(source, Arrays
								.asList(nodeFactory.newVariableDeclarationNode(
										source, nodeFactory.newIdentifierNode(
												source, "i"), nodeFactory
												.newBasicTypeNode(source,
														BasicTypeKind.INT))));

				StatementNode forBody;
				StatementNode switchBody;
				List<BlockItemNode> switchItems = new LinkedList<>();
				int caseNumber = 0;
				for (LinkedList<BlockItemNode> tempChildren : sectionsChildren) {
					StatementNode caseBody;
					List<BlockItemNode> caseItems = tempChildren;
					caseItems.add(nodeFactory.newBreakNode(source));
					caseBody = nodeFactory.newCompoundStatementNode(source,
							caseItems);
					SwitchLabelNode labelDecl = nodeFactory
							.newCaseLabelDeclarationNode(source, nodeFactory
									.newIntegerConstantNode(source,
											String.valueOf(caseNumber)),
									caseBody);
					switchItems.add(nodeFactory.newLabeledStatementNode(source,
							labelDecl, caseBody));
				}
				switchBody = nodeFactory.newCompoundStatementNode(source,
						switchItems);
				forItems.add(nodeFactory.newSwitchNode(source,
						this.identifierExpression(source, "i"), switchBody));
				forBody = nodeFactory
						.newCompoundStatementNode(source, forItems);

				cfn = nodeFactory.newCivlForNode(source, true,
						(DeclarationListNode) initializerNode, nodeFactory
								.newIdentifierExpressionNode(source,
										nodeFactory.newIdentifierNode(source,
												"my_secs")), forBody, null);
				items.add(cfn);

				if(!((CommonOmpWorkshareNode) node).nowait()){
					items.add(barrierAndFlush(TEAM));
				}

				CompoundStatementNode sectionBody = nodeFactory
						.newCompoundStatementNode(source, items);

				int index = node.childIndex();
				ASTNode parent = node.parent();
				parent.setChild(index, sectionBody);

				for (ASTNode child : children) {
					replaceOMPPragmas(child, privateIDs, sharedIDs, reductionIDs, firstPrivateIDs);
				}

			}
			if (workshareKind.equals("SINGLE")) {

				ExpressionNode arriveSingle = nodeFactory
						.newFunctionCallNode(source, this.identifierExpression(
								source, "$omp_arrive_single"),
								Arrays.asList(this.identifierExpression(source,
										TEAM)), null);
				items.add(nodeFactory
						.newVariableDeclarationNode(source, nodeFactory
								.newIdentifierNode(source, "owner"),
								nodeFactory.newBasicTypeNode(source,
										BasicTypeKind.INT), arriveSingle));

				List<ExpressionNode> operands = new ArrayList<ExpressionNode>();
				operands.add(this.identifierExpression(source, "owner"));
				operands.add(this.identifierExpression(source, "_tid"));
				int i = 0;
				CompoundStatementNode ifBody;
				LinkedList<BlockItemNode> ifItems = new LinkedList<>();
				for (ASTNode child : node.children()) {
					node.removeChild(i);
					ifItems.add((BlockItemNode) child);
					i++;
				}
				ifBody = nodeFactory.newCompoundStatementNode(source, ifItems);

				IfNode ifStatement = nodeFactory.newIfNode(source, nodeFactory
						.newOperatorNode(source, Operator.EQUALS, operands),
						ifBody);
				items.add(ifStatement);
				items.add(barrierAndFlush(TEAM));
				body = nodeFactory.newCompoundStatementNode(source, items);

				int index = node.childIndex();
				ASTNode parent = node.parent();
				parent.setChild(index, body);

				for (ASTNode child : children) {
					replaceOMPPragmas(child, privateIDs, sharedIDs, reductionIDs, firstPrivateIDs);
				}

			}
		} else if (node instanceof IdentifierNode) {
			if (privateIDs != null) {
				for (ASTNode child : privateIDs.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();
					if (c.name().equals(((IdentifierNode) node).name())) {
						((IdentifierNode) node).setName("_"
								+ ((IdentifierNode) node).name());
					}
				}
			}
			if (firstPrivateIDs != null) {
				for (ASTNode child : privateIDs.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();
					if (c.name().equals(((IdentifierNode) node).name())) {
						((IdentifierNode) node).setName("_"
								+ ((IdentifierNode) node).name());
					}
				}
			}
			if (reductionIDs != null) {
				for (ASTNode child : reductionIDs.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();
					if (c.name().equals(((IdentifierNode) node).name())) {
						((IdentifierNode) node).setName("_"
								+ ((IdentifierNode) node).name());
					}
				}
			}
			if (sharedIDs != null
					&& ((IdentifierNode) node).getEntity() != null) {
				for (ASTNode child : sharedIDs.children()) {
					IdentifierNode c = ((IdentifierExpressionNode) child)
							.getIdentifier();
					if (c.name().equals(((IdentifierNode) node).name())) {
						ASTNode parent = getParentOfID((IdentifierNode) node);
						boolean sameName = false;
						for(Triple<String, StatementNode, String> tempName : sharedReplaced){
							if(tempName.first.equals(((IdentifierNode) node).name())){
								if(tempName.second.equals(parent)){
									sameName = true;
									((IdentifierNode) node).setName(tempName.third);
								}
							}
						}
						if(!sameName){
							sharedRead((IdentifierNode) node, (StatementNode) parent);
						}
//						int index = parent.childIndex();
//						ASTNode parentNode = parent.parent();
//						parentNode.setChild(index, readBody);

					}
				}
			}
			
		} else if(node instanceof ExpressionStatementNode 
				&& sharedIDs != null 
				&& node.child(0) instanceof OperatorNode 
				&& ((OperatorNode)node.child(0)).getOperator().toString()
						.equals("ASSIGN")) {			
			VariableDeclarationNode temp = containsSharedWrite(
					(OperatorNode) node.child(0), sharedIDs, 0, 
					new ArrayList<String>());
			replaceOMPPragmas(temp.child(2), privateIDs, sharedIDs, reductionIDs, firstPrivateIDs);

		} else if (node != null) {
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				replaceOMPPragmas(child, privateIDs, sharedIDs, reductionIDs, firstPrivateIDs);
			}
		}

	}
	
	private VariableDeclarationNode addPrivateVariable (IdentifierExpressionNode node, String privateKind) throws SyntaxException{
		VariableDeclarationNode privateVariable;

		IdentifierNode c = node.getIdentifier();
		TypeNode privateType = ((VariableDeclarationNode) c
				.getEntity().getFirstDeclaration()).getTypeNode()
				.copy();
		IdentifierNode privateIdentifer = nodeFactory
				.newIdentifierNode(source, "_" + c.name());
		if(privateKind.equals("first")){
			privateVariable = nodeFactory.newVariableDeclarationNode(
				source, privateIdentifer, privateType, this.identifierExpression(source, c.name()));
		} else if(privateKind.equals("reduction")){ 
			privateVariable = nodeFactory.newVariableDeclarationNode(
					source, privateIdentifer, privateType, nodeFactory.newIntegerConstantNode(source, "0"));
		} else {
			privateVariable = nodeFactory.newVariableDeclarationNode(
					source, privateIdentifer, privateType);
		}
		return privateVariable;
	}

	private ASTNode getParentOfID(IdentifierNode node) {
		ASTNode parent = node.parent();
		while (!(parent instanceof StatementNode)) {
			parent = parent.parent();
		}
		return parent;
	}

	private CompoundStatementNode sharedRead(IdentifierNode node,
			StatementNode parentStatement) {
		List<BlockItemNode> items = new LinkedList<>();
		CompoundStatementNode bodyRead;

		VariableDeclarationNode temp;
		Type currentType = node.getEntity().getType();
		int nodesDeep = 0;
		while (currentType instanceof ArrayType) {
			currentType = ((ArrayType) currentType).getElementType();
			nodesDeep++;
		}

		TypeNode tempType = ((VariableDeclarationNode) node.getEntity()
				.getFirstDeclaration()).getTypeNode().copy();
		IdentifierNode tempID = nodeFactory.newIdentifierNode(source, "tmp" + String.valueOf(tmpCount));
		temp = nodeFactory.newVariableDeclarationNode(source, tempID, tempType);

		items.add(temp);
		if (nodesDeep == 0) {
			items.add(read(node.name(), node.name(), "tmp" + String.valueOf(tmpCount)));
		} else {
			ASTNode parent = node.parent();
			StringBuilder k = new StringBuilder();
			k.append(node.name());
			while (nodesDeep > 0) {
				parent = parent.parent();
				if (parent instanceof OperatorNode) {
					OperatorNode on = (OperatorNode) parent;
					k.append("["
							+ ((IntegerConstantNode) on.child(1))
									.getStringRepresentation() + "]");
				}
				nodesDeep--;
			}
			items.add(read(k.toString(), node.name(), "tmp" + String.valueOf(tmpCount)));
		}

		String origName = node.name();
		node.setName("tmp"+ String.valueOf(tmpCount));
		Triple<String, StatementNode, String> tempTriple = new Triple<>(
				origName, parentStatement, "tmp"+ String.valueOf(tmpCount));
		//Pair parentAndVarName = new Pair(parent)
		//sharedReplaced.add(node, new Pair<StatementNode, String>(parentStatement, "tmp"));
		sharedReplaced.add(tempTriple);
		tmpCount++;
		int index = parentStatement.childIndex();
		ASTNode parent = parentStatement.parent();
		parent.removeChild(index);
		items.add(parentStatement);


		bodyRead = nodeFactory.newCompoundStatementNode(source, items);
		parent.setChild(index, bodyRead);
		return bodyRead;
	}

	private VariableDeclarationNode containsSharedWrite(OperatorNode node, 
			SequenceNode<IdentifierExpressionNode> sharedIDs, int nodesDeep,
			ArrayList<String> arrayIndices){

		IdentifierExpressionNode origNode = null;
		if (node.child(0) instanceof IdentifierExpressionNode) {
			origNode = (IdentifierExpressionNode) node.child(0);

			for (ASTNode child : sharedIDs.children()) {
				IdentifierNode c = ((IdentifierExpressionNode) child)
						.getIdentifier();
				if (c.name().equals(
						((CommonIdentifierNode) origNode.child(0)).name())) {
					// Translate write access
					IdentifierNode in = (IdentifierNode) node.child(0).child(0);
					VariableDeclarationNode temp = null;
					Type currentType = in.getEntity().getType();
					while (currentType instanceof ArrayType) {
						currentType = ((ArrayType) currentType)
								.getElementType();
					}

					BasicTypeKind baseTypeKind = ((StandardBasicType) currentType)
					.getBasicTypeKind();
					int indexChild = node.child(1).childIndex();
					ASTNode parentRHS = node.child(1).parent();
					ExpressionNode initializer = (ExpressionNode) parentRHS
							.removeChild(indexChild);
							//(ExpressionNode) node.child(1).copy();
					if(!(in.getEntity().getType() instanceof ArrayType)){
						temp = (VariableDeclarationNode) in.getEntity()
								.getDefinition().copy();

						temp.getIdentifier().setName("tmp");
						temp.setInitializer(initializer);
					} else {
						temp = nodeFactory.newVariableDeclarationNode(source,
								nodeFactory.newIdentifierNode(source, "tmp"),
								nodeFactory.newBasicTypeNode(source,
										baseTypeKind), initializer);
					}

					List<BlockItemNode> items = new LinkedList<>();
					;
					CompoundStatementNode bodyWrite;
					items.add(temp);

					if (nodesDeep == 0) {
						items.add(write(c.name(), c.name()));
					} else {
						StringBuilder k = new StringBuilder();
						k.append(c.name());
						int j = 0;
						while (j < (nodesDeep - 1)) {
							k.append("[" + arrayIndices.get(j) + "]");
							j++;
						}
						items.add(write(k.toString(), c.name()));
					}

					bodyWrite = nodeFactory.newCompoundStatementNode(source,
							items);
					ASTNode expNode = node.parent();
					for (int i = 0; i < nodesDeep; i++) {
						expNode = expNode.parent();
					}
					int index = expNode.childIndex();
					ASTNode parent = expNode.parent();
					parent.setChild(index, bodyWrite);
					return temp;
				}
			}
		} else if (node.child(0) instanceof OperatorNode) {
			if (((OperatorNode) node.child(0)).getOperator().toString()
					.equals("SUBSCRIPT")) {
				OperatorNode array = (OperatorNode) node.child(0);
				arrayIndices.add(((IntegerConstantNode) array.child(1))
						.getStringRepresentation());
			}
			containsSharedWrite((OperatorNode) node.child(0), sharedIDs,
					nodesDeep + 1, arrayIndices);
		}
		return null;
	}

	private Triple<List<ExternalDefinitionNode>, List<ExternalDefinitionNode>, List<VariableDeclarationNode>> program(
			SequenceNode<ExternalDefinitionNode> root) {
		List<ExternalDefinitionNode> includedNodes = new ArrayList<>();
		List<VariableDeclarationNode> vars = new ArrayList<>();
		List<ExternalDefinitionNode> items;
		int number;
		items = new LinkedList<>();
		number = root.numChildren();
		for (int i = 0; i < number; i++) {
			ExternalDefinitionNode child = root.getSequenceChild(i);
			String sourceFile = child.getSource().getFirstToken()
					.getSourceFile().getName();

			root.removeChild(i);
			if (sourceFile.equals("omp.cvl")) {
				includedNodes.add(child);
			} else if (sourceFile.equals("stdio.cvl")) {
				includedNodes.add(child);
			} else if (sourceFile.endsWith(".h") || sourceFile.endsWith(".cvh")) {
				if (child.nodeKind() == NodeKind.VARIABLE_DECLARATION) {
					VariableDeclarationNode variableDeclaration = (VariableDeclarationNode) child;
					if (sourceFile.equals("stdio.h")) {
						// keep variable declaration nodes from stdio, i.e.,
						// stdout, stdin, etc.
						items.add(variableDeclaration);
					}
				} else {
					includedNodes.add(child);
				}
			} else {
				if (child.nodeKind() == NodeKind.VARIABLE_DECLARATION) {
					VariableDeclarationNode variable = (VariableDeclarationNode) child;

					if (variable.getTypeNode().isInputQualified()
							|| variable.getTypeNode().isOutputQualified()) {
						vars.add(variable);
						continue;
					}
				}
				items.add(child);
			}
		}
		return new Triple<>(items, includedNodes, vars);
	}
}
