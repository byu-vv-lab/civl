package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.Arrays;
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
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class GeneralTransformer extends CIVLBaseTransformer {

	public static String CODE = "general";
	public static String LONG_NAME = "GeneralTransformer";
	public static String SHORT_DESCRIPTION = "transforms general features of C programs to CIVL-C";

	public GeneralTransformer(ASTFactory astFactory) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory);
	}

	@Override
	public AST transform(AST unit) throws SyntaxException {
		@SuppressWarnings("unchecked")
		SequenceNode<ASTNode> root = (SequenceNode<ASTNode>) unit.getRootNode();
		AST newAst;
		List<VariableDeclarationNode> inputVars = new ArrayList<>();
		List<ASTNode> newExternalList = new ArrayList<>();

		unit.release();
		for (ASTNode child : root) {
			if (child.nodeKind() == NodeKind.FUNCTION_DEFINITION) {
				FunctionDefinitionNode functionNode = (FunctionDefinitionNode) child;
				IdentifierNode functionName = (IdentifierNode) functionNode
						.child(0);

				if (functionName.name().equals("main")) {
					inputVars = processMainFunction(functionNode);
				}
			}
		}
		if (inputVars.size() > 0) {
			for (ASTNode inputVar : inputVars) {
				newExternalList.add(inputVar);
			}
			for (ASTNode child : root) {
				newExternalList.add(child);
				child.parent().removeChild(child.childIndex());
			}
			root = nodeFactory.newSequenceNode(root.getSource(),
					"TranslationUnit", newExternalList);
		}
		newAst = astFactory.newAST(root);
		return newAst;
	}

	/**
	 * Processes the original main function, including:
	 * <ul>
	 * <li>Removes all arguments of the function;</li>
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
	 * @throws SyntaxException
	 */
	private List<VariableDeclarationNode> processMainFunction(
			FunctionDefinitionNode mainFunction) throws SyntaxException {
		List<VariableDeclarationNode> inputVars = new ArrayList<>();
		FunctionTypeNode functionType = mainFunction.getTypeNode();
		SequenceNode<VariableDeclarationNode> parameters = functionType
				.getParameters();
		int count = parameters.numChildren();

		if (count != 0 && count != 2) {
			if (count == 1) {
				if (parameters.getSequenceChild(0).getTypeNode().typeNodeKind() != TypeNodeKind.VOID)
					throw new SyntaxException(
							"The main function should have 0 or 2 parameters instead of "
									+ count, mainFunction.getSource());
			} else
				throw new SyntaxException(
						"The main function should have 0 or 2 parameters instead of "
								+ count, mainFunction.getSource());
		}
		if (count == 2) {
			VariableDeclarationNode argcVar = parameters.getSequenceChild(0);
			VariableDeclarationNode argvVar = parameters.getSequenceChild(1);
			VariableDeclarationNode __argcVar = argcVar.copy();
			VariableDeclarationNode __argvVar = argvVar.copy();
			CompoundStatementNode functionBody = mainFunction.getBody();
			String argcName = argcVar.getIdentifier().name();
			String argvName = argvVar.getIdentifier().name();
			String __argcName = "__" + argcName;
			String __argvName = "__" + argvName;
			TypeNode argvType = argvVar.getTypeNode();
			ArgvTypeKind argvTypeKind = analyzeArgvType(argvType);
			Source argvSource = argvVar.getSource();
			// TypeNode argvType = nodeFactory
			// .newArrayTypeNode(argvSource, nodeFactory.newArrayTypeNode(
			// argvSource, nodeFactory.newBasicTypeNode(
			// argvSource, BasicTypeKind.CHAR), null),
			// identifierExpression(argvSource, __argcName));

			parameters.removeChild(0);
			parameters.removeChild(1);
			__argcVar.getTypeNode().setInputQualified(true);
			__argcVar.getIdentifier().setName(__argcName);
			inputVars.add(__argcVar);
			__argvVar.getTypeNode().setInputQualified(true);
			__argvVar.getIdentifier().setName(__argvName);
			// __argvVar.setTypeNode(argvType);
			inputVars.add(__argvVar);
			argcVar.setInitializer(identifierExpression(__argcVar.getSource(),
					__argcName));

			if (argvTypeKind == ArgvTypeKind.POINTER_POINTER_CHAR)
				argvVar.setInitializer(identifierExpression(
						__argvVar.getSource(), __argvName));
			else if (argvTypeKind == ArgvTypeKind.ARRAY_POINTER_CHAR) {
				Source source = argvVar.getSource();
				ForLoopInitializerNode initializerNode = nodeFactory
						.newForLoopInitializerNode(source, Arrays
								.asList(nodeFactory.newVariableDeclarationNode(
										source, nodeFactory.newIdentifierNode(
												source, "i"), nodeFactory
												.newBasicTypeNode(source,
														BasicTypeKind.INT),
										nodeFactory.newIntegerConstantNode(
												source, "0"))));
				ExpressionNode loopCondition = nodeFactory.newOperatorNode(
						source, Operator.LT, Arrays.asList(
								this.identifierExpression(source, "i"),
								this.identifierExpression(source, argcName)));
				ExpressionNode incrementer = nodeFactory.newOperatorNode(
						source, Operator.POSTINCREMENT,
						Arrays.asList(this.identifierExpression(source, "i")));
				ExpressionNode assignArgvVar = nodeFactory
						.newOperatorNode(source, Operator.ASSIGN, Arrays
								.asList((ExpressionNode) nodeFactory
										.newOperatorNode(source,
												Operator.SUBSCRIPT,
												Arrays.asList(
														identifierExpression(
																argvSource,
																argvName),
														identifierExpression(
																source, "i"))),
										nodeFactory.newOperatorNode(source,
												Operator.SUBSCRIPT,
												Arrays.asList(
														identifierExpression(
																argvSource,
																__argvName),
														identifierExpression(
																source, "i")))));
				ForLoopNode forLoop = nodeFactory.newForLoopNode(source,
						initializerNode, loopCondition, incrementer,
						nodeFactory.newExpressionStatementNode(assignArgvVar),
						null);

				if (!inputVariableNames.contains(__argcName)) {
					throw new SyntaxException(
							"Please specify the input variable __argc (e.g. \"-input__argc=5\")"
									+ " which is used to update argc", source);
				}
				functionBody = addNodeToBeginning(functionBody, forLoop);
			}
			functionBody = addNodeToBeginning(functionBody, argvVar);
			functionBody = addNodeToBeginning(functionBody, argcVar);
			mainFunction.setBody(functionBody);
			functionType.setParameters(nodeFactory.newSequenceNode(
					parameters.getSource(), "FormalParameterDeclarations",
					new ArrayList<VariableDeclarationNode>(0)));
		}
		return inputVars;
	}

	public enum ArgvTypeKind {
		POINTER_POINTER_CHAR, ARRAY_POINTER_CHAR, ARRAY_ARRAY_CAHR
	};

	private ArgvTypeKind analyzeArgvType(TypeNode type) throws SyntaxException {
		TypeNodeKind typeKind = type.typeNodeKind();

		switch (typeKind) {
		case POINTER:
			return ArgvTypeKind.POINTER_POINTER_CHAR;
		case ARRAY:
			ArrayTypeNode arrayType = (ArrayTypeNode) type;
			TypeNode arrayEleType = arrayType.getElementType();
			TypeKind arrayEleTypeKind = arrayEleType.getType().kind();

			if (arrayEleTypeKind == TypeKind.POINTER)
				return ArgvTypeKind.ARRAY_POINTER_CHAR;
			else if (arrayEleTypeKind == TypeKind.ARRAY)
				return ArgvTypeKind.ARRAY_ARRAY_CAHR;
		default:
			throw new SyntaxException("Invalid type " + type.getType()
					+ " for argv of main.", null);
		}
	}

	private CompoundStatementNode addNodeToBeginning(
			CompoundStatementNode compoundNode, BlockItemNode node) {
		int numChildren = compoundNode.numChildren();
		List<BlockItemNode> nodeList = new ArrayList<>(numChildren + 1);

		nodeList.add(node);
		for (int i = 0; i < numChildren; i++) {
			BlockItemNode child = compoundNode.getSequenceChild(i);

			nodeList.add(child);
			compoundNode.removeChild(i);
		}
		return nodeFactory.newCompoundStatementNode(compoundNode.getSource(),
				nodeList);
	}

}
