package edu.udel.cis.vsl.civl.ast.entity.IF;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

/**
 * An object entity.
 * 
 * @author siegel
 * 
 */
public interface Variable extends OrdinaryEntity {

	public static enum StorageDurationKind {
		STATIC, THREAD, AUTOMATIC, ALLOCATED
	};

	StorageDurationKind getStorageDuration();

	void setStorageDuration(StorageDurationKind duration);

	/**
	 * Optional initializer for the object being declared.
	 * 
	 * @return the initializer for the new object, or null if no initializer is
	 *         present
	 */
	InitializerNode getInitializer();

	void setInitializer(InitializerNode initializer);

	/**
	 * An object declaration may contain any number of alignment specifiers.
	 * These have the form "_Alignas ( Type )" and
	 * "_Alignas ( constant-expression )". This method returns the types
	 * occurring in the first form (if any).
	 * 
	 * @return type alignments
	 */
	Iterator<Type> getTypeAlignments();

	void addTypeAlignment(Type type);

	/**
	 * An object declaration may contain any number of alignment specifiers.
	 * These have the form "_Alignas ( Type )" and
	 * "_Alignas ( constant-expression )". This method returns the constant
	 * expressions occurring in the second form (if any).
	 * 
	 * @return constant alignments
	 */
	Iterator<Value> getConstantAlignments();

	void addConstantAlignment(Value constant);

	@Override
	VariableDeclarationNode getDefinition();

	@Override
	ObjectType getType();

}
