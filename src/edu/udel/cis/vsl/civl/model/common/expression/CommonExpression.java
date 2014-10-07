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
	 * The highest scope accessed by this expression. NULL if no variable is
	 * accessed.
	 */
	private Scope expressionScope = null;

	/**
	 * The type of this expression.
	 * 
	 */
	protected CIVLType expressionType = null;

	/**
	 * Does this expression contains any dereference operation?
	 */
	protected boolean hasDerefs;

	/**
	 * Is this expression purely local? An expression is purely local if ...
	 */
	protected boolean purelyLocal = false;

	/**
	 * The parent of all expressions.
	 */
	public CommonExpression(CIVLSource source, Scope scope, CIVLType type) {
		super(source);
		this.expressionScope = scope;
		this.expressionType = type;
	}

	/**
	 * @return true iff the expression has at least one dereference
	 */
	public boolean hasDerefs() {
		return hasDerefs;
	}

	/**
	 * @return The highest scope accessed by this expression. Null if no
	 *         variables accessed.
	 */
	public Scope expressionScope() {
		return expressionScope;
	}

	// /**
	// * @param expressionScope
	// * The highest scope accessed by this expression. Null if no
	// * variables accessed.
	// */
	// public void setExpressionScope(Scope expressionScope) {
	// this.expressionScope = expressionScope;
	// }

	@Override
	public CIVLType getExpressionType() {
		return expressionType;
	}

	// /**
	// *
	// * @param expressionType
	// * The type resulting from this expression.
	// */
	// @Override
	// public void setExpressionType(CIVLType expressionType) {
	// this.expressionType = expressionType;
	// }

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
