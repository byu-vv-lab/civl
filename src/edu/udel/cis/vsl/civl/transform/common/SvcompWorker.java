package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AtomicNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.parse.IF.CParser;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.transform.IF.SvcompTransformer;

public class SvcompWorker extends BaseWorker {

	private final static String VERIFIER_ATOMIC = "__VERIFIER_atomic";

	private final static String VERIFIER_ATOMIC_BEGIN = "__VERIFIER_atomic_begin";

	private final static String VERIFIER_ATOMIC_END = "__VERIFIER_atomic_end";



	public SvcompWorker(ASTFactory astFactory) {
		super(SvcompTransformer.LONG_NAME, astFactory);
		this.identifierPrefix = "_" + SvcompTransformer.CODE;
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> rootNode = ast.getRootNode();

		ast.release();
		this.processVerifierFunctions(rootNode);
		
		ast = astFactory.newAST(rootNode, ast.getSourceFiles());
		// ast.prettyPrint(System.out, false);
		return ast;
	}

	private void processVerifierFunctions(SequenceNode<BlockItemNode> root) {
		for (BlockItemNode item : root) {
			if (item == null)
				continue;

			if (item instanceof FunctionDefinitionNode) {
				FunctionDefinitionNode funcDef = (FunctionDefinitionNode) item;

				if (!funcDef.getName().startsWith(VERIFIER_ATOMIC)) {
					// CompoundStatementNode body = funcDef.getBody();
					// BlockItemNode atomicStmt;
					//
					// body.remove();
					// atomicStmt = this.nodeFactory.newAtomicStatementNode(
					// body.getSource(), false, body);
					// body = this.nodeFactory.newCompoundStatementNode(
					// body.getSource(), Arrays.asList(atomicStmt));
					// funcDef.setBody(body);
					// } else {
					process_atomic_begin_end(funcDef.getBody());
				}
			} 
		}
	}

	private void process_atomic_begin_end(ASTNode node) {
		if (node instanceof CompoundStatementNode) {
			CompoundStatementNode body = (CompoundStatementNode) node;
			List<BlockItemNode> newItems = new LinkedList<>();
			int atomicCount = 0;
			List<BlockItemNode> atomicItems = new LinkedList<>();
			boolean changed = false;

			for (BlockItemNode item : body) {
				if (item instanceof ExpressionStatementNode) {
					ExpressionNode expression = ((ExpressionStatementNode) item)
							.getExpression();

					if (expression instanceof FunctionCallNode) {
						if (is_atomic_begin_call((FunctionCallNode) expression)) {
							atomicCount++;
							changed = true;
						} else if (is_atomic_end_call((FunctionCallNode) expression)) {
							atomicCount--;
							if (atomicCount == 0 && atomicItems.size() > 0) {
								this.releaseNodes(atomicItems);
								newItems.add(this.nodeFactory.newAtomicStatementNode(
										this.newSource("$atomic",
												CParser.ATOMIC),
										false,
										this.nodeFactory.newCompoundStatementNode(
												this.newSource(
														"body-of-atomic-begin-end",
														CParser.COMPOUND_STATEMENT),
												atomicItems)));
								atomicItems = new LinkedList<>();
							}
						} else {
							if (this.is_atomic_call((FunctionCallNode) expression)) {
								ASTNode parent = item.parent();
								int itemIndex = item.childIndex();
								AtomicNode atomicNode;

								item.remove();
								atomicNode = this.nodeFactory
										.newAtomicStatementNode(
												item.getSource(),
												false,
												nodeFactory
														.newCompoundStatementNode(
																item.getSource(),
																Arrays.asList(item)));
								parent.setChild(itemIndex, atomicNode);
								item = atomicNode;
							}
							if (atomicCount > 0)
								atomicItems.add(item);
							else
								newItems.add(item);
						}
					} else {
						if (atomicCount > 0)
							atomicItems.add(item);
						else
							newItems.add(item);
					}
				} else {
					process_atomic_begin_end(item);
					if (atomicCount > 0)
						atomicItems.add(item);
					else
						newItems.add(item);
				}
			}
			if (changed) {
				this.releaseNodes(newItems);

				CompoundStatementNode newBody = this.nodeFactory
						.newCompoundStatementNode(body.getSource(), newItems);

				body.parent().setChild(body.childIndex(), newBody);
			}
		} else {
			for (ASTNode child : node.children()) {
				if (child == null)
					continue;
				process_atomic_begin_end(child);
			}
		}
	}

	// private void process_atomic_begin_end_work()

	private boolean is_atomic_call(FunctionCallNode call) {
		ExpressionNode function = call.getFunction();

		if (function instanceof IdentifierExpressionNode) {
			String name = ((IdentifierExpressionNode) function).getIdentifier()
					.name();

			if (name.startsWith(VERIFIER_ATOMIC))
				return true;
		}
		return false;
	}

	private boolean is_atomic_begin_call(FunctionCallNode call) {
		return this.is_callee_name_equals(call, VERIFIER_ATOMIC_BEGIN);
	}

	private boolean is_atomic_end_call(FunctionCallNode call) {
		return this.is_callee_name_equals(call, VERIFIER_ATOMIC_END);
	}

}
