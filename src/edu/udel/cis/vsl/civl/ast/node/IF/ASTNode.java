package edu.udel.cis.vsl.civl.ast.node.IF;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.Source;

/**
 * Root of the AST node type hierarchy. All AST nodes implement this interface.
 */
public interface ASTNode {

	/**
	 * The different kind of nodes. Not yet using this, but could if it turns
	 * out to be useful.
	 * 
	 * @author siegel
	 * 
	 */
	public enum NodeKind {
		PAIR,
		SEQUENCE,
		IDENTIFIER,
		PRAGMA,
		STATIC_ASSERTION,
		COMPOUND_INITIALIZER,
		DESIGNATION,
		ARRAY_DESIGNATOR,
		FIELD_DESIGNATOR,
		ENUMERATOR_DECLARATION,
		FIELD_DECLARATION,
		VARIABLE_DECLARATION,
		FUNCTION_DECLARATION,
		FUNCTION_DEFINITION,
		TYPEDEF,
		ALIGNOF,
		ARROW,
		CAST,
		CHARACTER_CONSTANT,
		COMPOUND_LITERAL,
		DOT,
		ENUMERATION_CONSTANT,
		FLOATING_CONSTANT,
		FUNCTION_CALL,
		GENERIC_SELECTION,
		IDENTIFIER_EXPRESSION,
		INTEGER_CONSTANT,
		OPERATOR,
		SIZEOF,
		STRING_LITERAL,
		ORDINARY_LABEL,
		SWITCH_LABEL,
		COMPOUND_STATEMENT,
		DECLARATION_LIST,
		EXPRESSION_STATEMENT,
		FOR,
		WHILE,
		DO_WHILE,
		GOTO,
		IF,
		CONTINUE,
		BREAK,
		RETURN,
		LABELED_STATEMENT,
		SWITCH,
		ARRAY_TYPE,
		ATOMIC_TYPE,
		BASIC_TYPE,
		ENUMERATION_TYPE,
		FUNCTION_TYPE,
		POINTER_TYPE,
		STRUCT_OR_UNION_TYPE,
		TYPEDEF_NAME,
		PROCESS_TYPE,
		SELF,
		SPAWN,
		WAIT,
		ASSERT,
		ASSUME,
		WHEN,
		CHOOSE,
		INVARIANT,
		ENSURES,
		REQUIRES,
		COLLECTIVE,
		RESULT,
		REMOTE_REFERENCE
	};

	/** ID number unique within the AST to which this node belongs. */
	int id();

	void setId(int id);

	/** The parent of this node, or null if this node has no parent. */
	ASTNode parent();

	/** The index of this node among the children of its parent. */
	int childIndex();

	/** Returns the number of children nodes of this AST node. */
	int numChildren();

	/**
	 * Returns the index-th child node of this AST node.
	 * 
	 * @param index
	 * @throws NoSuchElementException
	 *             if index is less than 0 or greater than or equal to the
	 *             number of children
	 */
	ASTNode child(int index) throws NoSuchElementException;

	/** Returns an iterator over the set of children */
	Iterator<ASTNode> children();

	/** Returns a textual representation of this node only. */
	String toString();

	/** Prints a textual representation of this node. */
	void print(String prefix, PrintStream out, boolean includeSource);

	/**
	 * Returns the attribute value associated to the given key, or null if no
	 * value has been set for that key. Note that attribute keys are generated
	 * in the ASTFactory.
	 */
	Object getAttribute(AttributeKey key);

	/**
	 * Sets the attribute value associated to the given key. This method also
	 * checks that the value belongs to the correct class. Note that attribute
	 * keys are generated in the ASTFactory.
	 */
	void setAttribute(AttributeKey key, Object value);

	/**
	 * Returns the source object that locates the origin of this program
	 * construct in the original source code. This is used for reporting
	 * friendly messages to the user.
	 * 
	 * @return source object for this node
	 */
	Source getSource();

	/**
	 * Gets the scope in which this syntactic element occurs.
	 * 
	 * @return the scope
	 */
	Scope getScope();

	/**
	 * Sets the scope of this syntactic element.
	 * 
	 * @param scope
	 *            the scope
	 */
	void setScope(Scope scope);

	void setOwner(TranslationUnit owner);

	TranslationUnit getOwner();

}
