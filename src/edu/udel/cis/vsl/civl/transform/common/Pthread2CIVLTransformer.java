package edu.udel.cis.vsl.civl.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

//TODO: add arguments to pthread_exit();

public class Pthread2CIVLTransformer extends CIVLBaseTransformer {
	/* ************************** Public Static Fields *********************** */
	/**
	 * The code (short name) of this transformer.
	 */
	public final static String CODE = "pthread";

	/**
	 * The long name of the transformer.
	 */
	public final static String LONG_NAME = "PthreadTransformer";

	/**
	 * The description of this transformer.
	 */
	public final static String SHORT_DESCRIPTION = "transforms C/Pthread program to CIVL-C";

	/* **************************** Instant Fields ************************* */

	/**
	 * There are new nodes created by the transformer, other than parsing from
	 * some source file. All new nodes share the same source.
	 */
	private Source source;

	/* ****************************** Constructor ************************** */
	/**
	 * Creates a new instance of MPITransformer.
	 * 
	 * @param astFactory
	 *            The ASTFactory that will be used to create new nodes.
	 */
	public Pthread2CIVLTransformer(ASTFactory astFactory, boolean debug) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory, debug);
	}

	/* *************************** Private Methods ************************* */

	private void processRoot(ASTNode root) throws SyntaxException {
		for (ASTNode node : root.children()) {
			if (node == null)
				continue;
			if (node instanceof FunctionDefinitionNode)
				processFunctionDefinition((FunctionDefinitionNode) node);
		}
	}

	private void processFunctionDefinition(FunctionDefinitionNode function)
			throws SyntaxException {
		String name = function.getName();
		TypeNode returnType = function.getTypeNode().getReturnType();

		if (name.equals("main"))
			return;
		if (this.isVoidPointer(returnType)) {
			function.getBody().addSequenceChild(this.returnNull());
		}
	}

	private ReturnNode returnNull() throws SyntaxException {
		ExpressionNode nullNode = nodeFactory.newCastNode(
				source,
				nodeFactory.newPointerTypeNode(source,
						nodeFactory.newVoidTypeNode(source), null),
				nodeFactory.newIntegerConstantNode(source, "0"));

		return nodeFactory.newReturnNode(source, nullNode);
	}

	private boolean isVoidPointer(TypeNode type) {
		if (type.kind() == TypeNodeKind.POINTER) {
			PointerTypeNode pointer = (PointerTypeNode) type;

			if (pointer.referencedType().kind() == TypeNodeKind.VOID)
				return true;
		}
		return false;
	}

	/* ********************* Methods From BaseTransformer ****************** */

	@Override
	public AST transform(AST ast) throws SyntaxException {
		ASTNode root = ast.getRootNode();

		this.source = getMainSource(root);
		assert this.astFactory == ast.getASTFactory();
		assert this.nodeFactory == astFactory.getNodeFactory();
		ast.release();
		processRoot(root);
		return astFactory.newAST(root);
	}
}
