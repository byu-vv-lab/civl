/**
 * 
 */
package edu.udel.cis.vsl.civl.semantics.common;

import edu.udel.cis.vsl.civl.model.IF.location.Location.AtomicKind;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

/**
 * A simple transition involves a single atomic statement in one process. It is
 * to be contrasted with a synchronous transition, which involves two statements
 * executing together in two different processes.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonTransition implements Transition {

	private BooleanExpression pathCondition;

	// private Model model;

	protected int pid;

	protected int processIdentifier;

	protected Statement statement;

	/**
	 * A simple transition involves a single atomic statement in one process. It
	 * is to be contrasted with a synchronous transition, which involves two
	 * statements executing together in two different processes.
	 * 
	 * @param pathCondition
	 *            The path condition that should be used when executing
	 *            statement
	 * @param pid
	 *            The process id of the process executing this transition.
	 * @param processIdentifier
	 *            The process identifier of the process executing this
	 *            transition.
	 * @param statement
	 *            The statement corresponding to this transition.
	 */
	public CommonTransition(BooleanExpression pathCondition, int pid,
			int processIdentifier, Statement statement) {
		this.pathCondition = pathCondition;
		this.pid = pid;
		this.statement = statement;
		this.processIdentifier = processIdentifier;
	}

	/**
	 * @return The process id of the process executing this transition.
	 */
	public int pid() {
		return pid;
	}

	/**
	 * @return The statement corresponding to this transition.
	 */
	public Statement statement() {
		return statement;
	}

	/**
	 * @param pid
	 *            The process id of the process executing this transition.
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @param statement
	 *            The statement corresponding to this transition.
	 */
	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		String result = "p" + processIdentifier + ": ";

		result += statement.toStepString(AtomicKind.NONE, pid, false);
		return result;
	}

	public int processIdentifier() {
		return this.processIdentifier;
	}

	@Override
	public BooleanExpression pathCondition() {
		return this.pathCondition;
	}

}
