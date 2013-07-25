/**
 * 
 */
package edu.udel.cis.vsl.civl.transition;

import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * A transition represents a single atomic step of execution in a CIVL model.
 * They are further specialized into simple and synchronous transitions in
 * sub-classes. However, every transition must have a path condition and must
 * belong to one model. The path condition is the path condition that should
 * result after executing the transition.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Transition {

	private BooleanExpression pathCondition;

	private Model model;

	/**
	 * A transition.
	 * 
	 * @param pathCondition
	 *            The path condition that should result after executing the
	 *            transition.
	 */
	public Transition(BooleanExpression pathCondition) {
		this.pathCondition = pathCondition;
	}

	/**
	 * @return The path condition that should result after executing the
	 *         transition.
	 */
	public BooleanExpression pathCondition() {
		return pathCondition;
	}

	/**
	 * @return The model to which this transition belongs.
	 */
	public Model model() {
		return model;
	}

	/**
	 * @param The
	 *            path condition that should result after executing the
	 *            transition.
	 */
	public void setPathCondition(BooleanExpression pathCondition) {
		this.pathCondition = pathCondition;
	}

	/**
	 * @param The
	 *            model to which this transition belongs.
	 */
	public void setModel(Model model) {
		this.model = model;
	}
}
