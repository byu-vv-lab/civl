package edu.udel.cis.vsl.civl.ast.node.IF.expression;

import edu.udel.cis.vsl.civl.ast.conversion.IF.Conversion;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;

/**
 * A node representing any kind of C expression. This is the root of a type
 * hierarchy of expression nodes.
 * 
 * This extends InitializerNode because an expression can be used as an
 * initializer for a scalar variable.
 * 
 * This extends SizeableNode because an expression can be used as an argument to
 * the "sizeof" operator.
 * 
 * This extends ForLoopInitializerNode to indicate that an expression can be
 * used as the first clause of a "for" loop (as can a variable declaration).
 * 
 * @author siegel
 * 
 */
public interface ExpressionNode extends InitializerNode, SizeableNode,
		ForLoopInitializerNode {

	/**
	 * Returns the number of conversions in the chain leading from the initial
	 * type of the expression to the final converted type.
	 * 
	 * The type of an expression may go through a number of type conversions
	 * before arriving at its final "converted type". These conversions depend
	 * upon the context in which the expression occurs. The sequence of
	 * conversions leading from the initial type to the final converted type
	 * form a chain in which the "newType" of conversion i equals the "oldType"
	 * of conversion i+1 for each i. The oldType of conversion 0 is the original
	 * type of the expression; the newType of the last conversion is the
	 * converted type of the expression.
	 * 
	 * This method returns the total number of conversions in that chain. The
	 * method will return a nonnegative integer. If there are no conversions,
	 * this method returns 0 and the initial and final types are equal.
	 * 
	 * @return the number of type conversions between the original type and the
	 *         converted type
	 */
	int getNumConversions();

	/**
	 * Returns the index-th conversion in the chain of types for this
	 * expression.
	 * 
	 * @param index
	 *            an integer in the range [0,numTypes-1]
	 */
	Conversion getConversion(int index);

	/**
	 * Returns the initial type of the expression. This is the type the
	 * expression has independent of any context in which the expression occurs.
	 * 
	 * @return initial type of expression
	 */
	Type getInitialType();

	/**
	 * Sets the initial type of the expression. This must be set before any
	 * conversions are added.
	 * 
	 * @param type
	 *            the type that will be the initial type of this expression
	 */
	void setInitialType(Type type);

	/**
	 * Returns the final converted type of the expression. This is the type the
	 * expression has after going through all conversions in its conversion
	 * sequence (if any). If there are no conversions, it is the same as the
	 * initial type. Otherwise, it is the newType of the last conversion.
	 * 
	 * @return the final coverted type of the expression
	 */
	Type getConvertedType();

	/**
	 * Adds a conversion to the sequence of conversions for this expression. The
	 * added conversion must satisfy the following, else an
	 * IllegalArgumentException will be thrown: (1) if this is the first
	 * conversion (index 0) to be added, the old type of the conversion must
	 * equal the initial type; (2) if this is not the first conversion (index >
	 * 0) to be added, the old type of the converesion must equals the newType
	 * of the previous conversion.
	 * 
	 * @param conversion
	 *            the conversion to add to the conversion chain for this
	 *            expression
	 */
	void addConversion(Conversion conversion);

	/**
	 * Is this expression a "constant expression" in the sense of the C11
	 * Standard?
	 * 
	 * @return true iff this expression is a constant expression
	 */
	boolean isConstantExpression();

}
