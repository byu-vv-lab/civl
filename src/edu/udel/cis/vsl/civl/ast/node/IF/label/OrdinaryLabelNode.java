package edu.udel.cis.vsl.civl.ast.node.IF.label;

import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Label;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;

/**
 * 
 * A label may precede a statement, as in "l1: x=1;". An instance of this class
 * is used to represent the "l1". It contains a reference to the statement which
 * it precedes.
 * 
 * @author siegel
 * 
 */
public interface OrdinaryLabelNode extends LabelNode, DeclarationNode {

	@Override
	Label getEntity();

	/**
	 * The function in which this label occurs. A label has "function scope".
	 * 
	 * @return
	 */
	Function getFunction();

	void setFunction(Function function);

	/**
	 * The statement this label precedes (this may be null at first). Note that
	 * this is not a child, else the AST would not be a tree.
	 */
	StatementNode getStatement();

	void setStatement(StatementNode statement);

}
