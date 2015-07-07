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
import edu.udel.cis.vsl.civl.util.IF.Pair;

/**
 * A parallel region is a program fragment defined by an AST subtree rooted at a single 
 * starting statement and delimited by a set of possible ending statements.   Thus a region
 * is given by a pair, (stmt_s, Set<stmt_e>), with an interpretation that an execution path beginning
 * at stmt_s and ending at some associated stmt_e is included in the region.  A region may include
 * multiple execution paths.
 * 
 * This class computes a set of regions for a given OpenMP parallel statement and makes that relation
 * available through getter methods.
 * 
 * @author dwyer
 * 
 */
public class OpenMPParallelRegions  {

	private Map<ASTNode,List<Pair<ASTNode,List<ASTNode>>>> regionsForParallel;

	public OpenMPParallelRegions(ASTNode rootNode) {
		Map<ASTNode, List<Pair<ASTNode, List<ASTNode>>>> regionsForParallel = new HashMap<ASTNode,List<Pair<ASTNode,List<ASTNode>>>>();

		collectRegions(rootNode);
		
		for (ASTNode key : regionsForParallel.keySet()) {
			List<Pair<ASTNode,List<ASTNode>>> regions = regionsForParallel.get(key);
			System.out.println("For OMP Parallel Region found the following regions:");
			int r = 0;
			for (Pair<ASTNode,List<ASTNode>> region : regions) {
				System.out.println("------------ region "+(r++)+" ---------------");
				System.out.println("   "+region);
			}
			System.out.println("------------ end regions --------------");

			
		}
	}

	/*
	 */
	private void collectRegions(ASTNode node) {
		if (node instanceof OmpParallelNode) {
			OmpParallelNode opn = (OmpParallelNode) node;
			
			List<Pair<ASTNode, List<ASTNode>>> setForParallel = new ArrayList<Pair<ASTNode,List<ASTNode>>>();
			regionsForParallel.put(opn, setForParallel);

			collectRegionsForParallel(opn,opn.statementNode());

		} else if (node instanceof OmpExecutableNode) {
			System.out.println("Found non-Parallel OmpExecutableNode: "+node);

		} else if (node != null) {
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				collectRegions(child);
			}
		}
	}

	/*
	 * There are two phases of this calculation.
	 * 
	 * 1) Compute the current region via DFS.
	 * 	  A single region is terminated by an implicit or explicit barrier.
	 * 
	 * 2) Compute followon regions rooted at the frontier of the current region.
	 *    Since this computation is done for an OpenMP parallel statement there is an 
	 *    implicit barrier ending any final regions.
	 * 
	 */
	private List<ASTNode> collectRegionsForParallel(ASTNode opn, ASTNode node) {
		return null;
	}
	
	/*
	 * Recursively traverse the node and build up the set of frontier nodes, i.e.,
	 * the statements following implicit/explicit barriers.
	 */
	private List<ASTNode> buildRegion(ASTNode node) {
		if (node instanceof OmpForNode) {
			OmpForNode ompFor = (OmpForNode) node;
			if (!ompFor.nowait()) {
				List<ASTNode> frontier = new ArrayList<ASTNode>();
				frontier.add(successor(ompFor));
			}

		} else if (node instanceof OmpSyncNode) {
			OmpSyncNode syncNode = (OmpSyncNode) node;

		} else if (node instanceof OmpWorksharingNode) {
			OmpWorksharingNode wsNode = (OmpWorksharingNode) node;
			
		} else if (node instanceof StatementNode) {
			StatementNode sNode = (StatementNode) node;

		}
		
		if (node != null) {
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				buildRegion(child);
			}
		}
		return null;
	}

	/* Computes the successor statement in the AST for a given node
	 * If the node has a successor among its siblings (i.e., the next child of its parent) then return it.
	 * Otherwise continue up the AST until a successor can be found.
	 */
	private ASTNode successor(ASTNode node) {
		if (node instanceof StatementNode) {
			ASTNode parent = node.parent();
			int idx = 0;
			
			while ((idx = getChildIndex(parent, node))+1 >= parent.numChildren()) {
				node = parent; // now we look for the successor of the parent
				parent = node.parent();
			}
			return parent.child(idx+1);

		} else {
			assert false : "Expected statement node, but was called with "+node;
		}
		return null;
	}
	
	/*
	 * Returns the index of "child" in the children of "node"; -1 if "child" is
	 * not one of "node"'s children.
	 */
	private int getChildIndex(ASTNode node, ASTNode child) {
		for (int childIndex = 0; childIndex < node.numChildren(); childIndex++) {
			if (node.child(childIndex) == child)
				return childIndex;
		}
		return -1;
	}

}
