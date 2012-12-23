package edu.udel.cis.vsl.civl.ast.unit.common;

import java.util.List;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.civl.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.civl.ast.unit.IF.Program;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.ast.unit.IF.UnitFactory;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;

public class CommonUnitFactory implements UnitFactory {

	private NodeFactory nodeFactory;

	private TokenFactory tokenFactory;

	private TypeFactory typeFactory;

	public CommonUnitFactory(NodeFactory nodeFactory,
			TokenFactory tokenFactory, TypeFactory typeFactory) {
		this.nodeFactory = nodeFactory;
		this.tokenFactory = tokenFactory;
		this.typeFactory = typeFactory;
	}

	@Override
	public TranslationUnit newTranslationUnit(ASTNode root)
			throws SyntaxException {
		TranslationUnit unit = new CommonTranslationUnit(this,
				(CommonASTNode) root);

		// do some preparation?
		return unit;
	}

	@Override
	public Program newProgram(List<TranslationUnit> translationUnits) {
		return new CommonProgram(translationUnits);
	}

	@Override
	public NodeFactory getNodeFactory() {
		return nodeFactory;
	}

	@Override
	public TokenFactory getTokenFactory() {
		return tokenFactory;
	}

	@Override
	public TypeFactory getTypeFactory() {
		return typeFactory;
	}
}
