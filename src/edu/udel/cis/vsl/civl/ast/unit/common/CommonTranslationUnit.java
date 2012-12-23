package edu.udel.cis.vsl.civl.ast.unit.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.LinkageKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.unit.IF.UnitFactory;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

public class CommonTranslationUnit implements TranslationUnit {

	private UnitFactory unitFactory;

	private ASTNode root;

	private int nodeCount;

	private ASTNode[] nodes;

	private Map<String, OrdinaryEntity> internalOrExternalEntityMap = new LinkedHashMap<String, OrdinaryEntity>();

	private ArrayList<OrdinaryEntity> internalEntities = new ArrayList<OrdinaryEntity>();

	private ArrayList<OrdinaryEntity> externalEntities = new ArrayList<OrdinaryEntity>();

	public CommonTranslationUnit(UnitFactory unitFactory, ASTNode root)
			throws SyntaxException {
		this.root = root;
		this.unitFactory = unitFactory;
		initialize();
	}

	@Override
	public UnitFactory getUnitFactory() {
		return unitFactory;
	}

	@Override
	public ASTNode getRootNode() {
		return root;
	}

	@Override
	public long getNumberOfNodes() {
		return nodeCount;
	}

	@Override
	public ASTNode getNode(int id) {
		return nodes[id];
	}

	@Override
	public void print(PrintStream out) {
		print("", out, root, true);
	}

	private void print(String prefix, PrintStream out, ASTNode node,
			boolean includeSource) {
		if (node == null) {
			out.println(prefix + "<absent>");
		} else {
			Iterator<ASTNode> children = node.children();
			int childCount = 0;

			node.print(prefix, out, includeSource);
			out.println();
			prefix += "| ";
			// out.println(prefix + node.getScope());
			while (children.hasNext()) {
				ASTNode child = (ASTNode) children.next();

				if (child == null)
					out.println(prefix + childCount + " <absent>");
				else
					print(prefix, out, child, includeSource);
				childCount++;
			}
		}
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	// supporting methods...

	private void initialize() throws SyntaxException {
		this.nodeCount = 0;
		setIDsAndOwner((ASTNode) root);
		this.nodes = new ASTNode[nodeCount];
		initializeNodeArray((ASTNode) root);
		// ScopeAnalyzer.setScopes(this);
	}

	private void setIDsAndOwner(ASTNode node) {
		Iterator<ASTNode> children;

		if (node == null)
			return;
		node.setId(nodeCount);
		node.setOwner(this);
		nodeCount++;
		children = node.children();
		while (children.hasNext()) {
			ASTNode child = (ASTNode) children.next();

			setIDsAndOwner(child);
		}
	}

	private void initializeNodeArray(ASTNode node) {
		Iterator<ASTNode> children;

		if (node == null)
			return;
		this.nodes[node.id()] = node;
		children = node.children();
		while (children.hasNext()) {
			ASTNode child = children.next();

			initializeNodeArray(child);
		}
	}

	@Override
	public void add(OrdinaryEntity entity) {
		LinkageKind linkage = entity.getLinkage();

		if (linkage == LinkageKind.EXTERNAL)
			externalEntities.add(entity);
		else if (linkage == LinkageKind.INTERNAL)
			internalEntities.add(entity);
		else
			throw new IllegalArgumentException(
					"Can only add entities with internal or external linkage to translation unit: "
							+ entity);
		internalOrExternalEntityMap.put(entity.getName(), entity);
	}

	@Override
	public OrdinaryEntity getInternalOrExternalEntity(String name) {
		return internalOrExternalEntityMap.get(name);
	}

	@Override
	public Iterator<OrdinaryEntity> getInternalEntities() {
		return internalEntities.iterator();
	}

	@Override
	public Iterator<OrdinaryEntity> getExternalEntities() {
		return externalEntities.iterator();
	}

}
