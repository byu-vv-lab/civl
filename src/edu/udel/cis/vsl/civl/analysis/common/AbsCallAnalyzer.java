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
 * +: Y (pc && arg &gt; 0) +: N (pc -&gt; arg &lt;=0) +: ? (none of the above)
 * -: Y -: N -: ? 0: Y 0: N 0: ?
 * 
 * @author zmanchun
 *
 */
public class AbsCallAnalyzer extends CommonCodeAnalyzer implements CodeAnalyzer {
	public enum AbsValue {
		NONE, // initial
		GE, // greater than or equal to zero
		GT, // greater than
		LE, // less than or equal to zero
		LT, // less than
		ZERO, // zero
		GTLT, // greater or less than
		ANY; // wild card

		@Override
		public String toString() {
			switch (this) {
			case NONE:
				return "NONE";
			case GE:
				return "-:N 0:? +:?";
			case GT:
				return "-:N 0:N +:Y";
			case LE:
				return "-:? 0:? +:N";
			case LT:
				return "-:Y 0:N +:N";
			case GTLT:
				return "-:? 0:N +:?";
			case ZERO:
				return "-:N 0:Y +:N";
			case ANY:
				return "-:? 0:? +:?";
			default:
				return "";
			}
		}
	}

	private Set<CallOrSpawnStatement> unpreprocessedStatements = new HashSet<>();
	private Map<CallOrSpawnStatement, AbsValue> results = new LinkedHashMap<>();
	private SymbolicUniverse universe;
	private NumericExpression zero;
	private Reasoner reasoner;
	private Reasoner pcReasoner;

	public AbsCallAnalyzer(SymbolicUniverse universe) {
		this.universe = universe;
		this.zero = universe.zeroInt();
		this.pcReasoner = universe.reasoner(universe.trueExpression());
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

	private boolean isGeZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression geZero = universe.lessThanEquals(zero, value);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(geZero);
	}

	private boolean isLeZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression leZero = universe.lessThanEquals(value, zero);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(leZero);
	}

	private boolean isGtZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression gtZero = universe.lessThan(zero, value);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(gtZero);
	}

	private boolean isLtZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression ltZero = universe.lessThan(value, zero);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(ltZero);
	}

	private boolean isZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression isZero = universe.equals(value, zero);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(isZero);
	}

	private boolean isGtLtZero(BooleanExpression pathCondition,
			NumericExpression value) {
		BooleanExpression nonZero = universe.neq(value, zero);

		return !pcReasoner.isValid(universe.not(pathCondition))
				&& reasoner.isValid(nonZero);
	}

	@Override
	public void analyze(State state, int pid, CallOrSpawnStatement statement,
			SymbolicExpression[] argumentValues) {
		AbsValue old = this.results.get(statement);

		if (old != null && old != AbsValue.ANY) {
			BooleanExpression pc = state.getPathCondition();
			NumericExpression value = (NumericExpression) argumentValues[0];

			reasoner = universe.reasoner(pc);
			switch (old) {
			case NONE: {
				if (isZero(pc, value))
					results.put(statement, AbsValue.ZERO);
				else if (isGtZero(pc, value))
					results.put(statement, AbsValue.GT);
				else if (isGeZero(pc, value))
					results.put(statement, AbsValue.GE);
				else if (isLtZero(pc, value))
					results.put(statement, AbsValue.LT);
				else if (isLeZero(pc, value))
					results.put(statement, AbsValue.LE);
				else if (isGtLtZero(pc, value))
					results.put(statement, AbsValue.GTLT);
				else
					results.put(statement, AbsValue.ANY);
				break;
			}
			case GE: {
				if (!isGeZero(pc, value))
					results.put(statement, AbsValue.ANY);
				break;
			}
			case ZERO: {
				if (!isZero(pc, value))
					if (isGeZero(pc, value))
						results.put(statement, AbsValue.GE);
					else if (isLeZero(pc, value))
						results.put(statement, AbsValue.LE);
				break;
			}
			case LE: {
				if (!isLeZero(pc, value))
					results.put(statement, AbsValue.ANY);
				break;
			}
			case GT: {
				if (!isGtZero(pc, value))
					if (isGeZero(pc, value))
						results.put(statement, AbsValue.GE);
					else if (isGtLtZero(pc, value))
						results.put(statement, AbsValue.GTLT);
					else
						results.put(statement, AbsValue.ANY);
				break;
			}
			case LT: {
				if (!isLtZero(pc, value))
					if (isLeZero(pc, value))
						results.put(statement, AbsValue.LE);
					else if (isGtLtZero(pc, value))
						results.put(statement, AbsValue.GTLT);
					else
						results.put(statement, AbsValue.ANY);
				break;
			}
			case GTLT: {
				if (!isGtLtZero(pc, value))
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
			out.println("+: all calls with the argument > 0");
			out.println("-: all calls with the argument < 0");
			out.println("0: all calls with the argument = 0");
			out.println("Y: Yes");
			out.println("N: No");
			out.println("?: Maybe");
			// out.println("*: argument could be anything");
		} else
			out.println("The program doesn't have any reachable abs function call.");
	}

}
