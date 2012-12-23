package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;

public interface SwitchNode extends StatementNode {

	/**
	 * The branch condition controlling this switch statement.
	 * 
	 * @return the condition
	 */
	ExpressionNode getCondition();

	/**
	 * Returns the switch statement body: the switch statement has the form
	 * "switch(expression) body".
	 * 
	 * @return
	 */
	StatementNode getBody();

	/**
	 * Returns the sequence of all "case"-labeled statements within this switch
	 * statement's body. This does NOT include the "default" case.
	 * 
	 * Note: these are not children since they are reachable through the switch
	 * statement's body.
	 * 
	 * @return sequence node listing all case-labeled statements in this switch
	 *         statement
	 */
	Iterator<LabeledStatementNode> getCases();

	void addCase(LabeledStatementNode statement);

	/**
	 * Returns the "default"-labeled statement within this switch statement, or
	 * null if there isn't one.
	 * 
	 * @return the default statement or null
	 */
	LabeledStatementNode getDefaultCase();

	void setDefaultCase(LabeledStatementNode statement);

}
