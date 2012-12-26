package edu.udel.cis.vsl.civl.analysis.common;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.analysis.IF.Analyzer;
import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

/**
 * Given an AST, determines and sets scope of every node.
 * 
 * @author siegel
 * 
 */
public class ScopeAnalyzer implements Analyzer {

	public static void setScopes(TranslationUnit ast, EntityFactory scopeFactory)
			throws SyntaxException {
		(new ScopeAnalyzer(scopeFactory)).analyze(ast);
	}

	private EntityFactory scopeFactory;

	private int currentId = 0;

	public ScopeAnalyzer(EntityFactory scopeFactory) {
		this.scopeFactory = scopeFactory;
	}

	@Override
	public void analyze(TranslationUnit unit) throws SyntaxException {
		ASTNode root = unit.getRootNode();

		processNode(root, null, null);
		setIds(root.getScope());
	}

	private void processNode(ASTNode node, Scope parentScope,
			Scope functionScope) throws SyntaxException {

		if (node.getScope() != null)
			return;
		if (parentScope == null) {
			parentScope = scopeFactory.newScope(ScopeKind.FILE, null, node);
		} else if (node instanceof FunctionDefinitionNode) {
			FunctionDefinitionNode funcNode = (FunctionDefinitionNode) node;
			CompoundStatementNode body = funcNode.getBody();
			SequenceNode<ContractNode> contract = funcNode.getContract();
			FunctionTypeNode funcTypeNode = (FunctionTypeNode) funcNode
					.getTypeNode();
			SequenceNode<VariableDeclarationNode> paramsNode = funcTypeNode
					.getParameters();

			functionScope = parentScope = scopeFactory.newScope(
					ScopeKind.FUNCTION, parentScope, node);
			if (paramsNode != null)
				processNode(paramsNode, functionScope, functionScope);
			if (contract != null) {
				Scope contractScope = scopeFactory.newScope(ScopeKind.CONTRACT,
						functionScope, node);

				processNode(contract, contractScope, functionScope);
			}
			processNode(body, parentScope, functionScope);
		} else if (node instanceof CompoundStatementNode) {
			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
		} else if (node instanceof SwitchNode) {
			ASTNode body = ((SwitchNode) node).getBody();
			Scope bodyScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			bodyScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					body);
			processChildren(body, bodyScope, functionScope);
		} else if (node instanceof IfNode) {
			ASTNode trueBranch = ((IfNode) node).getTrueBranch();
			ASTNode falseBranch = ((IfNode) node).getFalseBranch();
			Scope trueBranchScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			trueBranchScope = scopeFactory.newScope(ScopeKind.BLOCK,
					parentScope, trueBranch);
			processChildren(trueBranch, trueBranchScope, functionScope);
			if (falseBranch != null) {
				Scope falseBranchScope = scopeFactory.newScope(ScopeKind.BLOCK,
						parentScope, falseBranch);

				processChildren(falseBranch, falseBranchScope, functionScope);
			}
		} else if (node instanceof LoopNode) {
			ASTNode body = ((LoopNode) node).getBody();
			Scope bodyScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			bodyScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					body);
			processChildren(body, bodyScope, functionScope);
		} else if (node instanceof FunctionDeclarationNode) {
			// children: ident, type, contract.
			// type children: returnType, parameters
			// put ident type return type in current scope
			// create child scope prototypescope.
			// put parameters in prototype scope.
			// create child of prototypescope contract scope.
			// put contract in there.
			FunctionDeclarationNode declNode = (FunctionDeclarationNode) node;
			FunctionTypeNode typeNode = declNode.getTypeNode();
			ASTNode parameters = typeNode.getParameters();
			SequenceNode<ContractNode> contract = declNode.getContract();

			if (parameters != null || contract != null) {
				Scope prototypeScope = scopeFactory.newScope(
						ScopeKind.FUNCTION_PROTOTYPE, parentScope, parameters);

				if (parameters != null)
					processNode(parameters, prototypeScope, functionScope);
				if (contract != null) {
					Scope contractScope = scopeFactory.newScope(
							ScopeKind.CONTRACT, prototypeScope, contract);

					processNode(contract, contractScope, prototypeScope);
				}
			}
		} else if (node instanceof OrdinaryLabelNode) {
			parentScope = functionScope;
		}
		processChildren(node, parentScope, functionScope);
	}

	private void setIds(Scope scope) {
		if (scope.getId() >= 0) {
			return;
		} else {
			Iterator<Scope> children = scope.getChildrenScopes();

			scope.setId(currentId);
			currentId++;
			while (children.hasNext())
				setIds(children.next());
		}
	}

	private void processChildren(ASTNode node, Scope parent, Scope functionScope)
			throws SyntaxException {
		Iterator<ASTNode> children = node.children();

		assert parent != null;
		node.setScope(parent);
		while (children.hasNext()) {
			ASTNode child = children.next();

			if (child != null)
				processNode(child, parent, functionScope);
		}
	}

}
