package edu.udel.cis.vsl.civl.ast.node.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.Source;

public abstract class CommonASTNode implements ASTNode {

	private int id = -1;

	private TranslationUnit owner = null;

	private ASTNode parent;

	private int childIndex = -1;

	private ArrayList<ASTNode> children;

	private Source source;

	private ArrayList<Object> attributes = null;

	private Scope scope;

	public CommonASTNode(Source source,
			Iterator<? extends ASTNode> childIterator) {
		int childCount = 0;

		this.source = source;
		children = new ArrayList<ASTNode>();
		while (childIterator.hasNext()) {
			CommonASTNode child = (CommonASTNode) childIterator.next();

			children.add(child);
			if (child != null) {
				child.parent = this;
				child.childIndex = childCount;
			}
			childCount++;
		}
	}

	public CommonASTNode(Source source,
			Iterable<? extends ASTNode> childCollection) {
		this(source, childCollection.iterator());
	}

	public CommonASTNode(Source source, ASTNode[] childArray) {
		this(source, Arrays.asList(childArray).iterator());
	}

	public CommonASTNode(Source source) {
		this.source = source;
		children = new ArrayList<ASTNode>();
	}

	public CommonASTNode(Source source, ASTNode child) {
		this(source, new ASTNode[] { child });
	}

	public CommonASTNode(Source source, ASTNode child0, ASTNode child1) {
		this(source, new ASTNode[] { child0, child1 });
	}

	public CommonASTNode(Source source, ASTNode child0, ASTNode child1,
			ASTNode child2) {
		this(source, new ASTNode[] { child0, child1, child2 });
	}

	public CommonASTNode(Source source, ASTNode child0, ASTNode child1,
			ASTNode child2, ASTNode child3) {
		this(source, new ASTNode[] { child0, child1, child2, child3 });
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setOwner(TranslationUnit owner) {
		this.owner = owner;
	}

	@Override
	public TranslationUnit getOwner() {
		return owner;
	}

	@Override
	public ASTNode parent() {
		return parent;
	}

	@Override
	public int childIndex() {
		return childIndex;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}

	@Override
	public int numChildren() {
		return children.size();
	}

	@Override
	public ASTNode child(int index) throws NoSuchElementException {
		return children.get(index);
	}

	@Override
	public Iterator<ASTNode> children() {
		return children.iterator();
	}

	@Override
	public void print(String prefix, PrintStream out, boolean includeSource) {
		out.print(prefix);
		if (childIndex >= 0)
			out.print(childIndex);
		out.print("[" + id + "]: ");
		printBody(out);
		if (scope != null) {
			out.print(" (scope " + scope.getId() + ")");
		} else {
			out.print(" (scope UNKNOWN)");
		}
		if (includeSource) {
			out.println();
			out.print(prefix + "| source: " + source.getSummary());
		}
		printExtras(prefix + "| ", out);
	}

	protected abstract void printBody(PrintStream out);

	protected void printExtras(String prefix, PrintStream out) {

	}

	@Override
	public Object getAttribute(AttributeKey key) {
		if (attributes == null)
			return null;
		else {
			int id = ((CommonAttributeKey) key).getId();

			if (id >= attributes.size())
				return null;
			return attributes.get(id);
		}
	}

	@Override
	public void setAttribute(AttributeKey key, Object value) {
		int id = ((CommonAttributeKey) key).getId();
		Class<Object> attributeClass = key.getAttributeClass();
		int size;

		if (!(attributeClass.isInstance(value)))
			throw new IllegalArgumentException("Attribute "
					+ ((CommonAttributeKey) key).getAttributeName()
					+ " has type  " + attributeClass + " but given " + value
					+ " of type " + value.getClass());
		if (attributes == null)
			attributes = new ArrayList<Object>();
		size = attributes.size();
		while (id >= size) {
			attributes.add(null);
			size++;
		}
		attributes.set(id, value);
	}

	@Override
	public Source getSource() {
		return source;
	}

	protected void addChild(ASTNode child) {
		int index = numChildren();

		children.add(child);
		if (child != null) {
			((CommonASTNode) child).parent = this;
			((CommonASTNode) child).childIndex = index;
		}
	}

	public void setChild(int index, ASTNode child) {
		int numChildren = children.size();

		while (index >= numChildren) {
			children.add(null);
			numChildren++;
		}
		children.set(index, child);
		if (child != null) {
			((CommonASTNode) child).parent = this;
			((CommonASTNode) child).childIndex = index;
		}
	}

	@Override
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public Scope getScope() {
		return scope;
	}

	@Override
	public String toString() {
		return "Node[" + id + "]";
	}

}
