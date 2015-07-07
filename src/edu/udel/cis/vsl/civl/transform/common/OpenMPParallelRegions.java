package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpForNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpParallelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpReductionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpExecutableNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpSymbolReductionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpSyncNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpSyncNode.OmpSyncNodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpWorksharingNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpWorksharingNode.OmpWorksharingNodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.DeclarationListNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.util.ExpressionEvaluator;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * 
 * 
 * 
 * @author dwyer
 * 
 */
public class OpenMPParallelRegions  {

	private Map<ASTNode,List<ASTNode>> regionsForParallel;

	public OpenMPParallelRegions(ASTNode rootNode) {
		Map<ASTNode,List<ASTNode>> regionsForParallel = new HashMap<ASTNode,List<ASTNode>>();

		collectRegions(rootNode);
		
		for (ASTNode key : regionsForParallel.keySet()) {
			List<ASTNode> regions = regionsForParallel.get(key);
			System.out.println("For OMP Parallel Region found the following regions:");
			int r = 0;
			for (ASTNode region : regions) {
				System.out.println("------------ region "+(r++)+" ---------------");
				System.out.println("   "+region);
			}
			System.out.println("------------ end regions --------------");

			
		}
	}

	/*
	 * Traverse the method declaration analyzing and simplifying omp constructs
	 */
	private void collectRegions(ASTNode node) {
		if (node instanceof OmpParallelNode) {
			OmpParallelNode opn = (OmpParallelNode) node;	

			collectRegionsForParallel(opn,opn.statementNode());

		} else if (node instanceof OmpExecutableNode) {
			System.out.println("Found non-Parallel OmpExecutableNode: "+node);

		} else if (node != null) {
			// BUG: can get here with null values in parallelfor.c example

			/*
			 * Could match other types here that have no ForLoopNode below them
			 * and skip their traversal to speed things up.
			 */
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				collectRegions(child);
			}
		}
	}

	/*
	 * Initially we will assume that OMP work shares are not nested
	 */
	private void collectRegionsForParallel(ASTNode opn, ASTNode node) {
		if (node instanceof OmpForNode) {
			OmpForNode ompFor = (OmpForNode) node;

		} else if (node instanceof OmpSyncNode) {
			OmpSyncNode syncNode = (OmpSyncNode) node;

		} else if (node instanceof OmpWorksharingNode) {
			OmpWorksharingNode wsNode = (OmpWorksharingNode) node;

		} else if (node != null) {

			// BUG: can get here with null values in parallelfor.c example

			/*
			 * Could match other types here that have no ForLoopNode below them
			 * and skip their traversal to speed things up.
			 */
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				collectRegionsForParallel(opn, child);
			}
		}
	}



}
