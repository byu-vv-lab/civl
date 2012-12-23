package edu.udel.cis.vsl.civl.ast.entity.IF;

import java.io.PrintStream;
import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.UnsourcedException;

/**
 * A lexical (static) scope in a translation unit. The C11 Standard specifies 4
 * kinds of scopes: FILE, BLOCK, FUNCTION, and FUNCTION_PROTOTYPE.
 * 
 * A scope contains declarations of various kinds of entities. An "entity" is
 * any conceptual thing which can be named using an identifier. An entity may be
 * declared in more than one scope (though the process of "linkage").
 * 
 * Global declarations are in the FILE scope. A BLOCK scope corresponds to a
 * block. The BLOCK scope corresponding to a function body includes the formal
 * parameters as well as the outermost local parameters. The only entities with
 * FUNCTION scope are (standard) labels---the labels that can be used as a
 * target of a "goto" statement. A FUNCTION_PROTOTYPE scope occurs only in a
 * function prototype, i.e., a function delcaration without body; such a scope
 * extends to the end of the declarator for the function prototype.
 * 
 * The set of all scopes in a translation unit forms a tree. The root of the
 * tree is the FILE scope.
 * 
 * There are four kinds of name spaces in a scope: (1) the label namesapce,
 * which consists of all the (standard) label names in the scope; (2) the tag
 * namespace, which consists of all the tags used in struct, union, and
 * enumeration definitions; (3) the "member" namespaces (one namespace for each
 * struct or union containing the field names for that struct or union); and (4)
 * the namespace for "ordinary identifiers"---those declared in an ordinary (not
 * struct or union) declarator, or as an enumeration constant---, this includes
 * typedef names, variables, and functions. For example, the same identifier "X"
 * could be used to denote a label, the tag of a struct, a member of that
 * struct, and a variable, all in the same scope. However, "X" could not denote
 * both a variable and a function in the same scope; nor could it denote both a
 * struct tag and an enumeration tag in the same scope; but two different
 * structs in the same scope can both have a field named "X".
 * 
 * Ordinary entities include: functions, variables (that are not fields),
 * enumeration constants, and typedefs.
 * 
 * Note that "label" means standard label: the kind that is specified by an
 * identifier followed by a colon. Not a "case label" (CASE followed by a
 * constant expression then colon, used in switch statements), and not the
 * "default" label (also used in switch statements).
 * 
 * Note: Entities can have no name. It is OK if two distinct entities have no
 * name; they are not the same entity. It is as if each is given a new name
 * distinct from all other names.
 */
public interface Scope {

	/**
	 * These are the four different kinds of scopes.
	 */
	public enum ScopeKind {
		FILE, BLOCK, FUNCTION, FUNCTION_PROTOTYPE
	};

	// information about this scope in the scope tree...

	/**
	 * Returns the kind of scope this is.
	 * 
	 * @return the scope kind
	 * */
	ScopeKind getScopeKind();

	/**
	 * Returns the ID number of this scope, unique among the scope of its
	 * translation unit.
	 * 
	 * @return the scope ID
	 */
	int getId();

	/**
	 * Sets the ID number of this scope, which should be unique among the ID
	 * numbers of the scopes of its translation unit.
	 * 
	 * @param id
	 *            value to which ID number will be set
	 */
	void setId(int id);

	/**
	 * Returns the translation unit to which this scope belongs.
	 * 
	 * @return the translation unit to which this scope belongs
	 * */
	TranslationUnit getTranslationUnit();

	/**
	 * The parent scope, i.e., the scope directly containing this one. Null if
	 * this is the shared scope.
	 * 
	 * @return the parent scope
	 */
	Scope getParentScope();

	/**
	 * The number of children of this scope in the scope tree.
	 * 
	 * @return the number of children scopes of this scope
	 */
	int getNumChildrenScopes();

	/**
	 * Returns the child scope of this scope, indexed from 0.
	 * 
	 * @param scopeId
	 *            integer between 0 and numChildrenScope-1, inclusive
	 * @return child scope number index
	 * @exception IllegalArgumentException
	 *                if index is out of bounds
	 */
	Scope getChildScope(int scopeId);

	/**
	 * Returns an iterator over the children scope of this scope.
	 * 
	 * @return an iterator over all children scopes
	 */
	Iterator<Scope> getChildrenScopes();

	/**
	 * Returns the depth of this scope as a node in the scope tree. The FILE
	 * scope has depth 0. Its immediate children have depth 1. Etc.
	 * 
	 * @return depth of this scope as node in scope tree
	 */
	int getScopeDepth();

	// Ordinary entities...

	int add(OrdinaryEntity entity) throws UnsourcedException;

	/**
	 * Returns the ordinary entity (variable/function/enumeration
	 * constant/typedef) in this scope with the given name, or null if there is
	 * not one. This does not look in ancestor or descendant scopes.
	 * 
	 * @param name
	 *            the name of the ordinary entity
	 * @return the ordinary entity in this scope with that name or null if none
	 *         exists
	 */
	OrdinaryEntity getOrdinaryEntity(String name);

	/**
	 * Performs search for ordinary entity with given name using lexical
	 * scoping: if entity is not found in this scope, search the parent scope,
	 * etc. Returns first occurrence of ordinary entity with this name,
	 * searching in that order. If not found all the way up the scopes, returns
	 * null.
	 */
	OrdinaryEntity getLexicalOrdinaryEntity(String name);

	/**
	 * Returns the number of variables declared in this scope. The set of
	 * variables is a subset of the set of entities. Variables include global
	 * variables, local variables declared in any block scope, including formal
	 * parameters to a function. They do not include members of structures or
	 * unions (aka "fields"). They do not include enumeration constants.
	 * 
	 * Note this does not include variables in ancestors or descendants of this
	 * scope.
	 * 
	 * @return the number of variables declared in this scope
	 */
	int getNumVariables();

	/**
	 * 
	 * The variables in this scope are assigned variable ID numbers 0, 1, 2,
	 * ..., at completion time. This method returns the variable with the given
	 * ID. Note that the variable ID is not necessarily the same as the entity
	 * ID.
	 */
	Variable getVariable(int index);

	/**
	 * Returns an iterator over the variables in this scope, in order of
	 * increasing variable ID.
	 * 
	 * @return an iterator over the variables in this scope.
	 */
	Iterator<Variable> getVariables();

	/**
	 * Returns the number of functions in this scope. Note this does not include
	 * functions in ancestors of this scope. The functions are of course a
	 * subset of the set of Entities.
	 */
	int getNumFunctions();

	/**
	 * The functions in this scope are assigned ID numbers 0, 1, 2, ..., at
	 * completion time. This method returns the function with the given ID.
	 */
	Function getFunction(int index);

	/**
	 * Returns an iterator over the functions defined in this scope, in order of
	 * increasing function ID.
	 */
	Iterator<Function> getFunctions();

	// Tagged entities (enumerations, structures, and unions)...

	int add(TaggedEntity entity) throws SyntaxException;

	/**
	 * Returns the tagged entity (struct/union/enumeration) in this scope with
	 * the given tag, or null if there is not one. This does not look in parent
	 * or descendant scopes.
	 * 
	 * Once you get the tagged entity, you can get its member entities
	 * (enumeration constants or fields). Those member entities are also part of
	 * this scope. The enumeration constants share the same name space as
	 * ordinary identifiers in this scope, but the fields are in their own
	 * entity-specific namespace.
	 * 
	 * @param tag
	 * @return the tagged entity in this scope with that name or null if none
	 *         exists
	 */
	TaggedEntity getTaggedEntity(String tag);

	/**
	 * Performs search for struct/union/enumeration entity with given tag using
	 * lexical scoping: if entity is not found in this scope, search the parent
	 * scope, etc. Returns first occurrence of entity with this tag, searching
	 * in that order. If not found in any ancestor, returns null.
	 */
	TaggedEntity getLexicalTaggedEntity(String tag);

	// Labels...

	int add(Label label) throws UnsourcedException;

	boolean contains(Label label);

	/**
	 * Gets the label declared in this scope with the given name. Labels only
	 * exist in a FUNCTION scope. A Label is an entity.
	 * 
	 * @param name
	 *            the label name
	 * @return the label in this scope with that name or null if none exists
	 */
	Label getLabel(String name);

	/**
	 * Finds the label with the given name in this or any ancestor scope.
	 * 
	 * @param name
	 *            label name
	 * @return label with name or null
	 */
	Label getLexicalLabel(String name);

	/**
	 * Returns iterator over all (standard) labels declared in this scope.
	 * 
	 * @return
	 */
	Iterator<Label> getLabels();

	/**
	 * Returns the number of (standard) labels declared in this scope.
	 * 
	 * @return number of labels in this scope
	 */
	int getNumLabels();

	/**
	 * Gets the label with the given label ID.
	 * 
	 * @param labelId
	 *            the label's id
	 * @return label
	 * @exeption IllegalArgumentException if the labelId is less than 0 or
	 *           greater than or equal to the number of labels in this scope
	 */
	Label getLabel(int labelId);

	/**
	 * Prints complete description of this scope, with each line preceded by the
	 * string prefix.
	 * 
	 * @param prefix
	 *            any string
	 * @param out
	 *            PrintStream to which output is directed
	 */
	void print(String prefix, PrintStream out);

	/**
	 * Prints complete description of this scope.
	 * 
	 * @param out
	 *            PrintStream to which output is directed
	 */
	void print(PrintStream out);

}
