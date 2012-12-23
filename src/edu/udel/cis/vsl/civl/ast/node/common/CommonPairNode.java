package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.PairNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonPairNode<S extends ASTNode, T extends ASTNode> extends
		CommonASTNode implements PairNode<S, T> {

	public CommonPairNode(Source source, S left, T right) {
		super(source, left, right);
	}

	@SuppressWarnings("unchecked")
	@Override
	public S getLeft() {
		return (S) child(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getRight() {
		return (T) child(1);
	}

	@Override
	public void setLeft(S child) {
		setChild(0, child);
	}

	@Override
	public void setRight(T child) {
		setChild(1, child);
	}

	@Override
	protected void printBody(PrintStream out) {
	}

}
