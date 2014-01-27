package edu.udel.cis.vsl.civl.model.common.type;

import java.util.Collection;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLUnionType;
import edu.udel.cis.vsl.civl.model.IF.type.UnionMember;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;

public class CommonCIVLUnionType implements CIVLUnionType {

	private Identifier name;

	private UnionMember[] members = null;

	@Override
	public boolean hasState() {
		if (!isComplete())
			throw new CIVLInternalException("Struct not complete",
					(CIVLSource) null);
		for (UnionMember member : members) {
			if (member.type().hasState())
				return true;
		}
		return false;
	}

	@Override
	public Variable getStateVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStateVariable(Variable variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public SymbolicType getDynamicType(SymbolicUniverse universe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDynamicTypeIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNumericType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIntegerType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRealType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPointerType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProcessType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isScopeType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVoidType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHeapType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBundleType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStructType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isArrayType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCharType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "union " + name.toString();
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numberOfMembers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UnionMember getMember(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<UnionMember> members() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void complete(Collection<UnionMember> members) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void complete(UnionMember[] members) {
		// TODO Auto-generated method stub
		
	}

}
