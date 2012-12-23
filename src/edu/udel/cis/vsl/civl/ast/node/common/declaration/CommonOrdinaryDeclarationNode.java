package edu.udel.cis.vsl.civl.ast.node.common.declaration;

import edu.udel.cis.vsl.civl.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.OrdinaryDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.civl.token.IF.Source;

public abstract class CommonOrdinaryDeclarationNode extends
		CommonDeclarationNode implements OrdinaryDeclarationNode {

	private boolean externStorage = false;

	private boolean staticStorage = false;

	/**
	 * Constructor for declarator-based declarations that are not function
	 * definitions (including function prototypes).
	 * 
	 * @param source
	 * @param identifier
	 * @param type
	 * @param initializer
	 */
	public CommonOrdinaryDeclarationNode(Source source,
			IdentifierNode identifier, TypeNode type) {
		super(source, identifier, type);
	}

	@Override
	public TypeNode getTypeNode() {
		return (TypeNode) child(1);
	}

	@Override
	public void setTypeNode(TypeNode type) {
		setChild(1, type);
	}

	@Override
	public boolean hasExternStorage() {
		return externStorage;
	}

	@Override
	public void setExternStorage(boolean value) {
		externStorage = value;
	}

	@Override
	public boolean hasStaticStorage() {
		return staticStorage;
	}

	@Override
	public void setStaticStorage(boolean value) {
		staticStorage = value;
	}

}
