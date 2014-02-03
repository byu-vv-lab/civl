package edu.udel.cis.vsl.civl.semantics;

import edu.udel.cis.vsl.civl.semantics.IF.MemoryUnit;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class CommonMemoryUnit implements MemoryUnit {
	protected MemoryUnitKind memoryUnitKind;

	private SymbolicExpression reference;

	public CommonMemoryUnit(SymbolicExpression reference, MemoryUnitKind kind) {
		this.reference = reference;
		this.memoryUnitKind = kind;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof MemoryUnit) {
			MemoryUnit otherUnit = (MemoryUnit) other;

			if (otherUnit.memoryUnitKind() == this.memoryUnitKind()) {
				return this.reference.equals(((MemoryUnit) otherUnit)
						.reference());
			}
		}
		return false;
	}

	@Override
	public MemoryUnitKind memoryUnitKind() {
		return this.memoryUnitKind;
	}

	@Override
	public SymbolicExpression reference() {
		return this.reference;
	}
}
