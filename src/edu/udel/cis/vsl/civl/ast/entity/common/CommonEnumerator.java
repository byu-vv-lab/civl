package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

public class CommonEnumerator extends CommonOrdinaryEntity implements
		Enumerator {

	private Value value;

	/**
	 * Constructs new Enumerator.
	 * 
	 * @param declaration
	 *            the declaration node for this enumerator
	 * @param type
	 *            the enumeration type to which this enumerator belongs
	 * @param value
	 *            optional constant value; may be null
	 */
	public CommonEnumerator(EnumeratorDeclarationNode declaration,
			EnumerationType type, Value value) {
		super(EntityKind.ENUMERATOR, declaration.getName(), LinkageKind.NONE,
				type);
		this.addDeclaration(declaration);
		this.setDefinition(declaration);
		this.value = value;
	}

	@Override
	public EnumeratorDeclarationNode getDefinition() {
		return (EnumeratorDeclarationNode) super.getDefinition();
	}

	@Override
	public EnumerationType getType() {
		return (EnumerationType) super.getType();
	}

	@Override
	public Value getValue() {
		return value;
	}

}
