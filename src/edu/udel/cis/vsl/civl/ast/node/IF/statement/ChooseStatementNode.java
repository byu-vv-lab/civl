package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.SequenceNode;

/**
 * A "choose" statement has the form "choose { s1 ... sn }", where each si is a
 * statement. It represents nondeterministic choice. Typically the si is a
 * "when" statement, but every statement has a guard, whether implicit or
 * explicit.
 * 
 * Basically wraps the sequence of statements s1, ..., sn.
 * 
 * @author siegel
 * 
 */
public interface ChooseStatementNode extends StatementNode,
		SequenceNode<StatementNode> {

}
