package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode.NodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.abc.ast.type.IF.PointerType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSyntaxException;

public class GeneralTransformer extends CIVLBaseTransformer {

	public final static String CODE = "general";
	public final static String LONG_NAME = "GeneralTransformer";
	public final static String SHORT_DESCRIPTION = "transforms general features of C programs to CIVL-C";

	private final static String MALLOC = "malloc";

	public GeneralTransformer(ASTFactory astFactory,
			List<String> inputVariables, boolean debug) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory, inputVariables,
				debug);
	}

	@Override
	public AST transform(AST unit) throws SyntaxException {
		@SuppressWarnings("unchecked")
		SequenceNode<ASTNode> root = (SequenceNode<ASTNode>) unit.getRootNode();
		AST newAst;
		List<VariableDeclarationNode> inputVars = new ArrayList<>();
		List<ASTNode> newExternalList = new ArrayList<>();

		unit.release();
		processMalloc(root);
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

	private void processMalloc(ASTNode node) {
		if (node instanceof FunctionCallNode) {
			FunctionCallNode funcCall = (FunctionCallNode) node;

			if (funcCall.getFunction().expressionKind() == ExpressionKind.IDENTIFIER_EXPRESSION) {
				IdentifierExpressionNode functionExpression = (IdentifierExpressionNode) funcCall
						.getFunction();
				String functionName = functionExpression.getIdentifier().name();

				if (functionName.equals(MALLOC)) {
					ASTNode parent = funcCall.parent();
					int callIndex = funcCall.childIndex();

					if (!(parent instanceof CastNode)) {
						if (parent instanceof OperatorNode) {
							ExpressionNode lhs = ((OperatorNode) parent)
									.getArgument(0);
							Type type = lhs.getInitialType();
							TypeNode typeNode;
							CastNode castNode;

							if (type.kind() != TypeKind.POINTER)
								throw new CIVLSyntaxException(
										"The left hand side of a malloc call must be of pointer"
												+ " type.", lhs.getSource());
							typeNode = this.typeNode(lhs.getSource(), type);
							parent.removeChild(callIndex);
							castNode = nodeFactory.newCastNode(funcCall.getSource(), typeNode, funcCall);
							parent.setChild(callIndex, castNode);
						}
					}
				}
			}
		} else {
			for (ASTNode child : node.children()) {
				if (child != null) {
					processMalloc(child);
				}
			}
		}

	}

	private TypeNode typeNode(Source source, Type type) {
		switch (type.kind()) {
		case VOID:
			return nodeFactory.newVoidTypeNode(source);
		case BASIC:
			return nodeFactory.newBasicTypeNode(source,
					((StandardBasicType) type).getBasicTypeKind());
		case OTHER_INTEGER:
			return nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT);
		case ARRAY:
			return nodeFactory.newArrayTypeNode(source,
					this.typeNode(source, ((ArrayType) type).getElementType()),
					((ArrayType) type).getVariableSize().copy());
		case POINTER:
			return nodeFactory.newPointerTypeNode(source, this.typeNode(source,
					((PointerType) type).referencedType()), null);
		default:
		}
		return null;
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
			VariableDeclarationNode __argvVar;
			CompoundStatementNode functionBody = mainFunction.getBody();
			String argcName = argcVar.getIdentifier().name();
			String argvName = argvVar.getIdentifier().name();
			String __argcName = "__" + argcName;
			String __argvName = "__" + argvName;
			TypeNode argvType = argvVar.getTypeNode();
			ArgvTypeKind argvTypeKind = analyzeArgvType(argvType);
			Source source = argvVar.getSource();
			TypeNode pointerOfPointerOfChar = nodeFactory
					.newPointerTypeNode(source, nodeFactory.newPointerTypeNode(
							source, nodeFactory.newBasicTypeNode(source,
									BasicTypeKind.CHAR), null), null);

			parameters.removeChild(0);
			parameters.removeChild(1);
			__argcVar.getTypeNode().setInputQualified(true);
			__argcVar.getIdentifier().setName(__argcName);
			inputVars.add(__argcVar);
			__argvVar = inputArgvDeclaration(argvVar, __argvName);
			inputVars.add(__argvVar);
			argcVar.setInitializer(identifierExpression(__argcVar.getSource(),
					__argcName));
			if (argvTypeKind != ArgvTypeKind.POINTER_POINTER_CHAR) {

				argvVar.setTypeNode(pointerOfPointerOfChar.copy());
			}
			argvVar.setInitializer(nodeFactory.newCastNode(
					source,
					pointerOfPointerOfChar.copy(),
					nodeFactory.newIdentifierExpressionNode(source,
							nodeFactory.newIdentifierNode(source, __argvName))));
			functionBody = addNodeToBeginning(functionBody, argvVar);
			functionBody = addNodeToBeginning(functionBody, argcVar);
			mainFunction.setBody(functionBody);
			functionType.setParameters(nodeFactory.newSequenceNode(
					parameters.getSource(), "FormalParameterDeclarations",
					new ArrayList<VariableDeclarationNode>(0)));
		}
		return inputVars;
	}

	/**
	 * Declares <code>$input char __argv[][];</code>.
	 * 
	 * @param oldArgv
	 * @return
	 */
	private VariableDeclarationNode inputArgvDeclaration(
			VariableDeclarationNode oldArgv, String argvNewName) {
		VariableDeclarationNode __argv = oldArgv.copy();
		Source source = oldArgv.getSource();
		TypeNode arrayOfString = nodeFactory.newArrayTypeNode(source,
				nodeFactory.newArrayTypeNode(source, nodeFactory
						.newBasicTypeNode(source, BasicTypeKind.CHAR), null),
				null);

		__argv.getIdentifier().setName(argvNewName);
		arrayOfString.setInputQualified(true);
		__argv.setTypeNode(arrayOfString);
		return __argv;
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
