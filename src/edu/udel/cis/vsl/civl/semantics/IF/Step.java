package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.state.IF.State;

public interface Step {
	State start();

	Statement statement();

	State target();

	void setTarget(State target);
}
