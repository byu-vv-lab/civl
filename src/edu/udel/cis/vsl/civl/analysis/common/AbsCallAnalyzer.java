package edu.udel.cis.vsl.civl.analysis.common;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.civl.analysis.IF.Analysis;
import edu.udel.cis.vsl.civl.analysis.IF.CodeAnalyzer;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * This analyzer is dedicated for analyzing abs(arg) calls (of math library).
 * The purpose is keep track of the value of the argument arg:
 * 
 * <ul>
 * <li>NONE: function is never called (this is the initial state)</li>
 * <li>+: function is called at least once, and all calls satisfy the argument
 * must be &gt;= 0 (i.e., you could prove that always arg &gt;=0) and at least
 * one call may have an argument that is &gt;0 (i.e., you could not prove that
 * arg =0)</li>
 * <li>0: function is called at least once, and all calls satisfy the argument
 * must be 0 (you could prove that every time arg = 0)</li>
 * <li>-: function is called at least once, and all calls satisfy arg &lt;=0 and
 * at least once call satisfy arg &lt;0 (dual to +)</li>
 * <li>: function is called at least once, at least one call has argument which
 * could be &lt;0 (i.e., you could not prove arg&gt;=0), at least one call has
 * argument which could be &gt;0 (i.e., you could not prove arg&lt;=0)</li>
 * </ul>
 * 
 * @author zmanchun
 *
 */
public class AbsCallAnalyzer extends CommonCodeAnalyzer implements CodeAnalyzer {

	public enum AbsValue {
		NONE, // initial
		GE, // greater or equal to zero
		LE, // less or equal to zero
		ZERO, // zero
		ANY; // wild card

		@Override
		public String toString() {
			switch (this) {
			case NONE:
				return "NONE";
			case GE:
				return "+";
			case LE:
				return "-";
			case ZERO:
				return "0";
			case ANY:
				return "*";
			default:
				return "";
			}
		}
	}

	private Set<CallOrSpawnStatement> unpreprocessedStatements = new HashSet<>();
	private Map<CallOrSpawnStatement, AbsValue> results = new LinkedHashMap<>();
	private SymbolicUniverse universe;
	private NumericExpression zero;

	public AbsCallAnalyzer(SymbolicUniverse universe) {
		this.universe = universe;
		this.zero = universe.zeroInt();
	}

	@Override
	public void staticAnalysis(Statement statement) {

		if (statement instanceof CallOrSpawnStatement) {
			CallOrSpawnStatement call = (CallOrSpawnStatement) statement;
			CIVLFunction function = call.function();

			if (function == null)
				this.unpreprocessedStatements.add(call);
			else if (function.name().name().equals(Analysis.ABS)
					&& function.getSource().getFileName().equals("stdlib.cvl")) {
				results.put(call, AbsValue.NONE);
			}
		}
	}

	private boolean isGeZero(Reasoner reasoner, NumericExpression value) {
		BooleanExpression geZero = universe.lessThanEquals(zero, value);

		return reasoner.isValid(geZero);
	}

	private boolean isLeZero(Reasoner reasoner, NumericExpression value) {
		BooleanExpression leZero = universe.lessThanEquals(value, zero);

		return reasoner.isValid(leZero);
	}

	// private boolean isGtZero(Reasoner reasoner, NumericExpression value) {
	// BooleanExpression gtZero = universe.lessThan(zero, value);
	//
	// return reasoner.isValid(gtZero);
	// }
	//
	// private boolean isLtZero(Reasoner reasoner, NumericExpression value) {
	// BooleanExpression ltZero = universe.lessThan(value, zero);
	//
	// return reasoner.isValid(ltZero);
	// }

	private boolean isZero(Reasoner reasoner, NumericExpression value) {
		BooleanExpression isZero = universe.equals(value, zero);

		return reasoner.isValid(isZero);
	}

	@Override
	public void analyze(State state, int pid, CallOrSpawnStatement statement,
			SymbolicExpression[] argumentValues) {
		AbsValue old = this.results.get(statement);

		if (old != null && old != AbsValue.ANY) {
			Reasoner reasoner = universe.reasoner(state.getPathCondition());
			NumericExpression value = (NumericExpression) argumentValues[0];

			switch (old) {
			case NONE: {
				if (isZero(reasoner, value))
					results.put(statement, AbsValue.ZERO);
				else if (isGeZero(reasoner, value))
					results.put(statement, AbsValue.GE);
				else if (isLeZero(reasoner, value))
					results.put(statement, AbsValue.LE);
				else
					results.put(statement, AbsValue.ANY);
				break;
			}
			case GE: {
				if (!isGeZero(reasoner, value))
					results.put(statement, AbsValue.ANY);
				break;
			}
			case ZERO: {
				if (!isZero(reasoner, value))
					if (isGeZero(reasoner, value))
						results.put(statement, AbsValue.GE);
					else if (isLeZero(reasoner, value))
						results.put(statement, AbsValue.LE);
				break;
			}
			case LE: {
				if (!isLeZero(reasoner, value))
					results.put(statement, AbsValue.ANY);
				break;
			}
			default:
				throw new CIVLInternalException(
						"Unreachable location in abs call analyzer",
						statement.getSource());
			}
		}
		return;
	}

	@Override
	public void printAnalysis(PrintStream out) {
		out.println("\n=== abs call analysis ===");
		if (results.size() > 0) {
			for (Map.Entry<CallOrSpawnStatement, AbsValue> entry : results
					.entrySet()) {
				CallOrSpawnStatement key = entry.getKey();
				AbsValue value = entry.getValue();

				out.println(value + " " + key.getSource().getSummary());
			}
			out.println();
			out.println("+: all calls with the argument >= 0 and at least one call with the argument > 0");
			out.println("-: all calls with the argument < 0 and at least one call with the argument < 0");
			out.println("0: all calls with the argument = 0");
			out.println("*: argument could be anything");
		} else
			out.println("The program doesn't have any reachable abs function call.");
	}

}
