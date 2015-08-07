package edu.udel.cis.vsl.civl.semantics.common;

import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.semantics.IF.NoopTransition;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

public class CommonNoopTransition extends CommonTransition implements
		NoopTransition {

	private Location target;

	public CommonNoopTransition(BooleanExpression pathCondition, int pid,
			int processIdentifier, Location target, boolean symplifyState,
			AtomicLockAction atomicLockAction) {
		super(pathCondition, pid, processIdentifier, null, symplifyState,
				atomicLockAction);
		this.target = target;
	}

	@Override
	public Location target() {
		return this.target;
	}

	@Override
	public TransitionKind transitionKind() {
		return TransitionKind.NOOP;
	}
}
