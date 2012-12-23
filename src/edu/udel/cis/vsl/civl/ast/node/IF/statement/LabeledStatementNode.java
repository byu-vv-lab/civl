package edu.udel.cis.vsl.civl.ast.node.IF.statement;

import edu.udel.cis.vsl.civl.ast.node.IF.label.LabelNode;

public interface LabeledStatementNode extends StatementNode {

	LabelNode getLabel();

	StatementNode getStatement();

}
