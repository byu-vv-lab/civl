package edu.udel.cis.vsl.civl.ast.node.IF.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;

public interface FunctionDefinitionNode extends FunctionDeclarationNode {

	/**
	 * Returns the body of the function, a compound statement. The value
	 * returned is non-null iff this declaration is the definition of the
	 * function.
	 * 
	 * @return the function body, or null if not a definition
	 */
	CompoundStatementNode getBody();

	void setBody(CompoundStatementNode statement);

}
