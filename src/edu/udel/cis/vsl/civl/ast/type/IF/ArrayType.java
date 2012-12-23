package edu.udel.cis.vsl.civl.ast.type.IF;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

/**
 * Represents an array type. See C11 Sec. 6.7.6.2.
 * 
 * Note that in certain contexts type qualifiers may also occur between the
 * square brackets in the array type designation. These will be determined by
 * calling the appropriate qualifier methods in TypeNode.
 * 
 * An array type is an object type specified by an element type and the extent.
 * The element type must be a complete object type. For the extent, there are 4
 * possibilities:
 * 
 * NOTE: the keyword "static" can only appear in the outermost part of the
 * declaration of a function parameter, and since the types are converted to
 * pointer, "static" does not ultimately occur in an array type. On the other
 * hand "*" can occur in any number of internal array types in a function
 * parameter, so it can ultimately be part of an array type.
 * 
 * (0) the extent is not specified (i.e., is null): then the array type is an
 * "incomplete type".
 * 
 * (1) the extent is "*": this is a "VLA type of unspecified size". It is
 * nevertheless a complete type. This can only be used in declarations or type
 * names in function prototype scope (i.e., in a function declaration that is
 * not part of a function definition).
 * 
 * (2) the extent is an integer constant expression AND the element type has
 * known constant size: then the array type is "not a VLA" type. It is complete.
 * (Note: an object type has "known constant size" iff it is not incomplete AND
 * not a VLA (Variable Length Array) type.)
 * 
 * (3) otherwise, the extent is an expression which is not a constant
 * expression. This is a VLA type. It is complete. If it occurs in function
 * prototype scope, it is treated as if it were "*" (i.e., the expression is not
 * used).
 * 
 * 
 * @author siegel
 * 
 */
public interface ArrayType extends UnqualifiedObjectType {

	/**
	 * The type of the elements. This is required and must be non-null.
	 * 
	 * @return the element type
	 */
	ObjectType getElementType();

	/**
	 * The expression appearing in square brackets that specifies the length of
	 * the array. This is optional. If absent, this method will return null.
	 * 
	 * @return the array extent expression
	 */
	ExpressionNode getVariableSize();

	/**
	 * If this array has a known constant extent, it is returned by this method.
	 * Otherwise, this method returns null.
	 * 
	 * @return the known constant extent or null
	 */
	Value getConstantSize();

	/**
	 * Is this a Variable Length Array (VLA) type?
	 * 
	 * @return true iff this is a VLA type
	 */
	boolean isVariableLengthArrayType();

	/**
	 * In C11, a star ("*") may appear between the square brackets instead of an
	 * integer expression. The star represents "a variable length array type of
	 * unspecified size, which can only be used in declarations or type names
	 * with function prototype scope." See C11 Sec. 6.7.6.2(4).
	 * 
	 * @return true if a "*" occurs between the brackets
	 */
	boolean hasUnspecifiedVariableLength();

}
