package edu.udel.cis.vsl.civl.model.common.expression;

import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.HereOrRootExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

public class CommonHereOrRootExpression extends CommonExpression implements
		HereOrRootExpression {

	private boolean isRoot;

	public CommonHereOrRootExpression(CIVLSource source, boolean isRoot) {
		super(source);
		this.isRoot = isRoot;
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.HERE_OR_ROOT;
	}

	@Override
	public Set<Variable> variableAddressedOf(Scope scope,
			CIVLHeapType heapType, CIVLType commType) {
		return null;
	}

	@Override
	public Set<Variable> variableAddressedOf(CIVLHeapType heapType,
			CIVLType commType) {
		return null;
	}

	@Override
	public boolean isHere() {
		return !this.isRoot;
	}

	@Override
	public boolean isRoot() {
		return this.isRoot;
	}

}
