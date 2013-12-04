/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.expression;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.SubscriptExpression;

/**
 * a[i], where "a" is an array and "i" is an expression evaluating to an
 * integer.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonSubscriptExpression extends CommonExpression implements
		SubscriptExpression {

	private LHSExpression array;
	private Expression index;

	/**
	 * a[i], where "a" is an array and "i" is an expression evaluating to an
	 * integer.
	 * 
	 * @param array
	 *            An expression evaluating to an array.
	 * @param index
	 *            An expression evaluating to an integer.
	 */
	public CommonSubscriptExpression(CIVLSource source, LHSExpression array,
			Expression index) {
		super(source);
		this.array = array;
		this.index = index;
	}

	/**
	 * @return The expression for the array.
	 */
	public LHSExpression array() {
		return array;
	}

	/**
	 * @return The expression for the index.
	 */
	public Expression index() {
		return index;
	}

	/**
	 * @param array
	 *            The expression for the array.
	 */
	public void setArray(LHSExpression array) {
		this.array = array;
	}

	/**
	 * @param index
	 *            The expression for the index.
	 */
	public void setIndex(Expression index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return array + "[" + index + "]";
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.SUBSCRIPT;
	}
	
	@Override
	public void calculateDerefs() {
		// TODO Auto-generated method stub
		this.array.calculateDerefs();
		this.index.calculateDerefs();
		this.hasDerefs = this.array.hasDerefs() || 
				this.index.hasDerefs();
	}

	@Override
	public void setPurelyLocal(boolean pl) {
		this.array.setPurelyLocal(pl);
	}

	@Override
	public void purelyLocalAnalysisOfVariables(Scope funcScope) {
		this.array.purelyLocalAnalysisOfVariables(funcScope);
		this.index.purelyLocalAnalysisOfVariables(funcScope);
	}

	@Override
	public void purelyLocalAnalysis() {
		this.array.purelyLocalAnalysis();
		this.index.purelyLocalAnalysis();
	}

}
