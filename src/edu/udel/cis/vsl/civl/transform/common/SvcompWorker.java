package edu.udel.cis.vsl.civl.transform.common;

import java.util.Arrays;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.transform.IF.SvcompTransformer;

public class SvcompWorker extends BaseWorker {

	private final static String VERIFIER_ATOMIC = "__VERIFIER_atomic";

	public SvcompWorker(ASTFactory astFactory) {
		super(SvcompTransformer.LONG_NAME, astFactory);
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> rootNode = ast.getRootNode();

		ast.release();
		this.processVerifierFunctions(rootNode);
		ast = astFactory.newAST(rootNode, ast.getSourceFiles());
		return ast;
	}

	private void processVerifierFunctions(SequenceNode<BlockItemNode> root) {
		for (BlockItemNode item : root) {
			if (item == null)
				continue;

			if (item instanceof FunctionDefinitionNode) {
				FunctionDefinitionNode funcDef = (FunctionDefinitionNode) item;

				if (funcDef.getName().startsWith(VERIFIER_ATOMIC)) {
					CompoundStatementNode body = funcDef.getBody();
					BlockItemNode atomicStmt;

					body.remove();
					atomicStmt = this.nodeFactory.newAtomicStatementNode(
							body.getSource(), false, body);
					body = this.nodeFactory.newCompoundStatementNode(
							body.getSource(), Arrays.asList(atomicStmt));
					funcDef.setBody(body);
				}
			}
		}
	}
}
