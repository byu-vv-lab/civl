package edu.udel.cis.vsl.civl.model.common.type;

import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLDomainType;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class CommonDomainType extends CommonType implements CIVLDomainType {

	private int dimension;

	public CommonDomainType(int dim, SymbolicUniverse universe) {
		// String name = "$domain-" + dim;
		// List<SymbolicType> types = new ArrayList<>(dim);

		this.dimension = dim;
		// for (int i = 0; i < dim; i++)
		// types.add(universe.integerType());
		// this.dynamicType = (SymbolicType)
		// universe.canonic(universe.tupleType(
		// universe.stringObject(name), types));
	}

	public CommonDomainType(SymbolicUniverse universe) {
		this.dimension = -1;
	}

	@Override
	public boolean hasState() {
		return false;
	}

	@Override
	public SymbolicType getDynamicType(SymbolicUniverse universe) {
		throw new CIVLInternalException("no dynamic type specified for "
				+ toString(), (CIVLSource) null);
	}

	@Override
	public int dimension() {
		return this.dimension;
	}

	@Override
	public String toString() {
		if (this.dimension == -1)
			return "$domain";
		else {
			String result = "$domain";

			return result + "(" + this.dimension + ")";
		}
	}

}
