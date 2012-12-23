package edu.udel.cis.vsl.civl.ast.unit.IF;

import java.io.PrintStream;
import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;

/**
 * An abstract representation of a C "translation unit"---the thing that results
 * from translating a file (which may in turn include other files).
 * 
 * The clone method returns a new AST identical to this one, with all new nodes.
 * The tokens and attributed referenced by the nodes are not cloned; those
 * references are just copied.
 * 
 * @author siegel
 * 
 */
public interface TranslationUnit {

	/** Returns the UnitFactory responsible for creating this translation unit. */
	UnitFactory getUnitFactory();

	/** Returns the root node of the tree. */
	ASTNode getRootNode();

	/** The number of nodes in the tree. */
	long getNumberOfNodes();

	/**
	 * Returns the node with the given id number, The id must lie between 0 and
	 * n-1, inclusive, where n is the number of nodes.
	 */
	ASTNode getNode(int id);

	/** Pretty-prints the entire tree */
	void print(PrintStream out);

	/**
	 * Dissolves this AST. The nodes will be untouched, except they will become
	 * "free"--no longer owned by any AST. They can therefore be modified.
	 */
	void release();

	OrdinaryEntity getInternalOrExternalEntity(String name);

	Iterator<OrdinaryEntity> getInternalEntities();

	Iterator<OrdinaryEntity> getExternalEntities();

	void add(OrdinaryEntity entity);
}
