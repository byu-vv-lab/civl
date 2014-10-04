/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.common.CommonSourceable;

/**
 * A partial implementation of interface {@link Expression}. This is the root of
 * the expression implementation hierarchy. All expression classes are
 * sub-classes of this class.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public abstract class CommonExpression extends CommonSourceable implements
		Expression {

	// TODO: add field private SymbolicExpression constantValue
	// with setters and getters. Initially null, this is
	// used by expressions which have a constant value.
	// it is an optimization.

	/**
	 * TODO: what is this? Why isn't it part of the constructor? Don't you know
	 * the scope when you create the new expression? And do we need a setter for
	 * this or can it be immutable?
	 */
	private Scope expressionScope = null;

	/**
	 * TODO: ditto. Don't you know this when you create the expression? Can it
	 * change? Do we need a setter?
	 * 
	 */
	protected CIVLType expressionType = null;

	/**
	 * TODO: what is this?
	 */
	protected boolean hasDerefs;

	/**
	 * TODO: what is this?
	 */
	protected boolean purelyLocal = false;

	/**
	 * @return true iff the expression has at least one dereference
	 */
	public boolean hasDerefs() {
		return hasDerefs;
	}

	/**
	 * The parent of all expressions.
	 */
	public CommonExpression(CIVLSource source) {
		super(source);
	}

	/**
	 * @return The highest scope accessed by this expression. Null if no
	 *         variables accessed.
	 */
	public Scope expressionScope() {
		return expressionScope;
	}

	/**
	 * @param expressionScope
	 *            The highest scope accessed by this expression. Null if no
	 *            variables accessed.
	 */
	public void setExpressionScope(Scope expressionScope) {
		this.expressionScope = expressionScope;
	}

	@Override
	public CIVLType getExpressionType() {
		return expressionType;
	}

	/**
	 * 
	 * @param expressionType
	 *            The type resulting from this expression.
	 */
	@Override
	public void setExpressionType(CIVLType expressionType) {
		this.expressionType = expressionType;
	}

	@Override
	public void calculateDerefs() {
		this.hasDerefs = false;
	}

	@Override
	public void purelyLocalAnalysisOfVariables(Scope funcScope) {

	}

	@Override
	public boolean isPurelyLocal() {
		return this.purelyLocal;
	}

	@Override
	public void purelyLocalAnalysis() {
		this.purelyLocal = !this.hasDerefs;
	}

	@Override
	public void replaceWith(ConditionalExpression oldExpression,
			VariableExpression newExpression) {

	}

	@Override
	public Expression replaceWith(ConditionalExpression oldExpression,
			Expression newExpression) {
		return null;
	}

}
