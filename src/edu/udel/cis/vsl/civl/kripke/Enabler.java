package edu.udel.cis.vsl.civl.kripke;

import java.io.PrintWriter;

import edu.udel.cis.vsl.civl.model.statement.ChooseStatement;
import edu.udel.cis.vsl.civl.model.statement.JoinStatement;
import edu.udel.cis.vsl.civl.model.statement.Statement;
import edu.udel.cis.vsl.civl.semantics.Evaluator;
import edu.udel.cis.vsl.civl.state.Process;
import edu.udel.cis.vsl.civl.state.State;
import edu.udel.cis.vsl.civl.transition.Transition;
import edu.udel.cis.vsl.civl.transition.TransitionFactory;
import edu.udel.cis.vsl.civl.transition.TransitionSequence;
import edu.udel.cis.vsl.gmc.EnablerIF;
import edu.udel.cis.vsl.sarl.number.IF.IntegerNumberIF;
import edu.udel.cis.vsl.sarl.number.IF.NumberIF;
import edu.udel.cis.vsl.sarl.prove.IF.TheoremProverIF;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicExpressionIF;
import edu.udel.cis.vsl.sarl.symbolic.IF.SymbolicUniverseIF;
import edu.udel.cis.vsl.sarl.util.TernaryResult.ResultType;

public class Enabler implements
		EnablerIF<State, Transition, TransitionSequence> {

	private TransitionFactory transitionFactory;
	private boolean debugging = false;
	private PrintWriter debugOut = new PrintWriter(System.out);
	private SymbolicUniverseIF universe;
	private TheoremProverIF prover;
	private Evaluator evaluator;
	private long enabledTransitionSets = 0;
	private long ampleSets = 0;

	public Enabler(TransitionFactory transitionFactory,
			SymbolicUniverseIF universe, TheoremProverIF prover,
			Evaluator evaluator) {
		this.transitionFactory = transitionFactory;
		this.prover = prover;
		this.evaluator = evaluator;
		this.universe = universe;
	}

	@Override
	public boolean debugging() {
		return debugging;
	}

	@Override
	public TransitionSequence enabledTransitions(State state) {
		if (debugging && enabledTransitionSets % 1000 == 0) {
			System.out.println("Ample transition sets: " + ampleSets + "/"
					+ enabledTransitionSets);
		}
		return enabledTransitionsPOR(state);
	}

	/**
	 * Attempts to form an ample set from the enabled transitions of the given
	 * process, from the given state. If this is not possible, returns all
	 * transitions.
	 */
	private TransitionSequence enabledTransitionsPOR(State state) {
		TransitionSequence transitions = transitionFactory
				.newTransitionSequence(state);

		enabledTransitionSets++;
		for (Process p : state.processes()) {
			TransitionSequence localTransitions = transitionFactory
					.newTransitionSequence(state);
			boolean allLocal = true;

			// A process with an empty stack has no current location.
			if (p == null || p.hasEmptyStack()) {
				continue;
			}
			for (Statement s : p.location().outgoing()) {
				SymbolicExpressionIF newPathCondition = newPathCondition(state,
						p.id(), state.pathCondition(), s);
				int statementScope = p.scope();

				if (s.statementScope() != null) {
					while (!state.getScope(statementScope).lexicalScope()
							.equals(s.statementScope())) {
						statementScope = state.getParentId(statementScope);
					}
				}
				if (state.getScope(statementScope).numberOfReachers() > 1) {
					allLocal = false;
				}
				if (newPathCondition != null) {
					if (s instanceof ChooseStatement) {
						SymbolicExpressionIF argument = evaluator.evaluate(
								state, p.id(), ((ChooseStatement) s).rhs());
						Integer upper = extractInt(universe.simplifier(
								newPathCondition).simplify(argument));

						for (int i = 0; i < upper.intValue(); i++) {
							localTransitions.add(transitionFactory
									.newChooseTransition(newPathCondition,
											p.id(), s,
											universe.concreteExpression(i)));
						}
						continue;
					} else if (s instanceof JoinStatement) {
						SymbolicExpressionIF pidExpression = evaluator
								.evaluate(state, p.id(),
										((JoinStatement) s).process());
						IntegerNumberIF pidNumber;

						// TODO: Throw exception if not the right type.
						pidNumber = (IntegerNumberIF) universe
								.extractNumber(pidExpression);
						if (!state.process(pidNumber.intValue())
								.hasEmptyStack()) {
							continue;
						}
					}
					localTransitions.add(transitionFactory.newSimpleTransition(
							newPathCondition, p.id(), s));
				}
			}
			if (allLocal && localTransitions.size() > 0) {
				ampleSets++;
				return localTransitions;
			} else {
				transitions.addAll(localTransitions);
			}
		}
		return transitions;
	}

	/**
	 * 
	 * @param expression
	 *            A symbolic expression.
	 * @return A concrete integer if one can be extracted. Else null.
	 */
	private Integer extractInt(SymbolicExpressionIF expression) {
		NumberIF number = universe.extractNumber(expression);
		Integer intValue;

		assert number instanceof IntegerNumberIF;
		intValue = ((IntegerNumberIF) number).intValue();
		return intValue;
	}

	/**
	 * Given a state, a process, a path condition, and a statement, check if the
	 * statement's guard is satisfiable under the path condition. If it is,
	 * return the conjunction of the path condition and the guard. This will be
	 * the new path condition. Otherwise, return null.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The id of the currently executing process.
	 * @param pathCondition
	 *            The path condition.
	 * @param statement
	 *            The statement.
	 * @return The new path condition. Null if the guard is not satisfiable
	 *         under the path condition.
	 */
	private SymbolicExpressionIF newPathCondition(State state, int pid,
			SymbolicExpressionIF pathCondition, Statement statement) {
		SymbolicExpressionIF newPathCondition = null;
		SymbolicExpressionIF guard = evaluator.evaluate(state, pid,
				statement.guard());
		ResultType result = prover.valid(pathCondition, guard);
		ResultType negResult = prover.valid(pathCondition, universe.not(guard));

		// System.out.println("Enabler.newPathCondition() : Process " + pid
		// + " is at " + state.process(pid).peekStack().location());
		if (result == ResultType.YES) {
			newPathCondition = pathCondition;
		} else if (negResult == ResultType.YES) {
			return null;
		} else {
			newPathCondition = universe.and(pathCondition, guard);
		}
		return newPathCondition;
	}

	@Override
	public PrintWriter getDebugOut() {
		return debugOut;
	}

	@Override
	public boolean hasNext(TransitionSequence transitionSequence) {
		return !transitionSequence.isEmpty();
	}

	@Override
	public Transition next(TransitionSequence transitionSequence) {
		return transitionSequence.remove();
	}

	@Override
	public Transition peek(TransitionSequence transitionSequence) {
		return transitionSequence.peek();
	}

	@Override
	public void print(PrintWriter out, TransitionSequence transitionSequence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printFirstTransition(PrintWriter arg0, TransitionSequence arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printRemaining(PrintWriter arg0, TransitionSequence arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDebugOut(PrintWriter debugOut) {
		this.debugOut = debugOut;
	}

	@Override
	public void setDebugging(boolean debugging) {
		this.debugging = debugging;
	}

	@Override
	public State source(TransitionSequence transitionSequence) {
		return transitionSequence.state();
	}

}
