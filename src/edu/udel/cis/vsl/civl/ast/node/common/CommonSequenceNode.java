package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonSequenceNode<T extends ASTNode> extends CommonASTNode
		implements SequenceNode<T> {

	/**
	 * A name you would like to use when printing this node. Else "Sequence"
	 * will be used.
	 */
	private String name;

	@SuppressWarnings("unchecked")
	public CommonSequenceNode(Source source, String name, List<T> childList) {
		super(source, (List<ASTNode>) childList);
		this.name = name;
	}

	// public CommonSequenceNode(Source source, List<T> childList) {
	// this(source, "Sequence", childList);
	// }

	@Override
	public void addSequenceChild(T child) {
		addChild(child);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getSequenceChild(int i) {
		return (T) child(i);
	}

	@Override
	public void setSequenceChild(int i, T child) {
		setChild(i, child);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> childIterator() {
		return (Iterator<T>) children();
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(name);
	}

}
