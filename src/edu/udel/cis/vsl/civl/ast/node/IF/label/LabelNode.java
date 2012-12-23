package edu.udel.cis.vsl.civl.ast.node.IF.label;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;

public interface LabelNode extends ASTNode {

	/**
	 * The statement which is preceded by a label (but not including the label).
	 * 
	 * @return the statement labeled
	 */
	StatementNode getStatement();

	void setStatement(StatementNode statement);

}
