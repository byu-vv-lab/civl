package edu.udel.cis.vsl.civl.ast.node.IF;

import java.util.Iterator;


/**
 * A node in which all children have type T.
 */
public interface SequenceNode<T extends ASTNode> extends ASTNode {

	/** Add a child element at index numChildren() */
	void addSequenceChild(T child);

	/** Returns the child at position i */
	T getSequenceChild(int i);

	/** Sets the child at index i to the T child */
	void setSequenceChild(int i, T child);

	/** Returns an iterator over the children of this node. */
	Iterator<T> childIterator();

}
