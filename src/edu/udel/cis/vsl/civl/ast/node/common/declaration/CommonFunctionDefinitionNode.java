package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public class CommonFunctionDefinitionNode extends CommonFunctionDeclarationNode
		implements FunctionDefinitionNode {

	public CommonFunctionDefinitionNode(Source source,
			IdentifierNode identifier, TypeNode type,
			CompoundStatementNode statement) {
		super(source, identifier, type);
		addChild(statement);
	}

	@Override
	public CompoundStatementNode getBody() {
		return (CompoundStatementNode) child(2);
	}

	@Override
	public void setBody(CompoundStatementNode statement) {
		setChild(2, statement);
	}

	@Override
	protected void printKind(PrintStream out) {
		out.print("FunctionDefinition");
	}

}
