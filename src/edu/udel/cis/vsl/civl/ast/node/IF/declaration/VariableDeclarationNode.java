package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;
import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;

/**
 * A declaration of a C "object".
 * 
 * @author siegel
 * 
 */
public interface VariableDeclarationNode extends OrdinaryDeclarationNode {

	@Override
	Variable getEntity();

	/**
	 * Does the declaration include the "auto" storage class specifier?
	 * 
	 * For objects only, not functions. See comments for hasRegisterStorage.
	 * 
	 * @return true if declaration contains "auto"
	 */
	boolean hasAutoStorage();

	void setAutoStorage(boolean value);

	/**
	 * Does the declaration include the "register" storage class specifier?.
	 * 
	 * C11 6.9(2): "The storage-class specifiers auto and register shall not
	 * appear in the declaration specifiers in an external declaration."
	 * 
	 * C11 6.7.1(7): "The declaration of an identifier for a function that has
	 * block scope shall have no explicit storage-class specifier other than
	 * extern."
	 * 
	 * Since the only remaining kinds of scopes are function prototype and
	 * function, neither of which can contain function declarations, I conclude
	 * that auto and register can never occur in a function declaration.
	 * 
	 * Ergo: this is for objects only, not functions.
	 * 
	 * @return true if declaration contains "register"
	 */
	boolean hasRegisterStorage();

	void setRegisterStorage(boolean value);

	/**
	 * Optional initializer for the object being declared.
	 * 
	 * For objects only (not functions).
	 * 
	 * @return the initializer for the new object, or null if no initializer is
	 *         present
	 */
	InitializerNode getInitializer();

	void setInitializer(InitializerNode initializer);

	/**
	 * Does the declaration include the "_Thread_local" storage class specifier?
	 * 
	 * Used for "objects" only (not functions).
	 * 
	 * @return true if declaration contains "_Thread_local"
	 */
	boolean hasThreadLocalStorage();

	void setThreadLocalStorage(boolean value);

	/**
	 * An object declaration may contain any number of alignment specifiers.
	 * These have the form "_Alignas ( Type )" and
	 * "_Alignas ( constant-expression )". This method returns the types
	 * occurring in the first form (if any).
	 * 
	 * For objects only (not functions).
	 * 
	 * @return type alignments
	 */
	SequenceNode<TypeNode> typeAlignmentSpecifiers();

	void setTypeAlignmentSpecifiers(SequenceNode<TypeNode> specifiers);

	/**
	 * An object declaration may contain any number of alignment specifiers.
	 * These have the form "_Alignas ( Type )" and
	 * "_Alignas ( constant-expression )". This method returns the constant
	 * expressions occurring in the second form (if any).
	 * 
	 * For objects only (not functions).
	 * 
	 * @return constant alignments
	 */
	SequenceNode<ExpressionNode> constantAlignmentSpecifiers();

	void setConstantAlignmentSpecifiers(SequenceNode<ExpressionNode> specifiers);

}
