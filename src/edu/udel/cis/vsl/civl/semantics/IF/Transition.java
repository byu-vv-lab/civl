package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;

public interface Transition {

	BooleanExpression pathCondition();

	Statement statement();

	int pid();

	int processIdentifier();

}
