package edu.udel.cis.vsl.civl.model.common;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.abc.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.expression.CommonVariableExpression;
import edu.udel.cis.vsl.civl.model.common.variable.CommonVariable;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.object.StringObject;

/**
 * Maintains the information, e.g. labeled location, goto statements,
 * continue/break statement stack, that is required in the translation of the
 * definition of a function from ABC AST to CIVL model
 * 
 * @author zheng
 */
public class FunctionInfo {

	/**
	 * The current function that is being processed
	 */
	private CIVLFunction function;

	/**
	 * This fields maps ABC label nodes to the corresponding CIVL locations.
	 */
	private Map<LabelNode, Location> labeledLocations;

	/**
	 * Maps from CIVL "goto" statements to the corresponding label nodes.
	 */
	private Map<Statement, LabelNode> gotoStatements;

	/**
	 * The stack of queues of conditional expression.
	 */
	private Stack<ArrayDeque<ConditionalExpression>> conditionalExpressions;

	/**
	 * The number of conditional expressions that have been encountered, used to
	 * create temporal variable.
	 */
	private int conditionalExpressionCounter = 0;

	/**
	 * Used to keep track of continue statements in nested loops. Each entry on
	 * the stack corresponds to a particular loop. The statements in the set for
	 * that entry are noops which need their target set to the appropriate
	 * location at the end of the loop processing.
	 */
	private Stack<Set<Statement>> continueStatements;

	/**
	 * Used to keep track of break statements in nested loops/switches. Each
	 * entry on the stack corresponds to a particular loop or switch. The
	 * statements in the set for that entry are noops which need their target
	 * set to the appropriate location at the end of the loop or switch
	 * processing.
	 */
	private Stack<Set<Statement>> breakStatements;

	/**
	 * The symbolic universe
	 */
	private SymbolicUniverse universe;

	/**
	 * The factory used to create new Model components.
	 */
	private ModelFactory factory;

	/**
	 * Constructor
	 * 
	 * @param function
	 *            the CIVL function object that is being processed
	 * @param universe
	 *            The symbolic universe
	 * @param factory
	 *            The model factory
	 */
	public FunctionInfo(CIVLFunction function, SymbolicUniverse universe,
			ModelFactory factory) {
		this.function = function;
		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		continueStatements = new Stack<Set<Statement>>();
		breakStatements = new Stack<Set<Statement>>();
		conditionalExpressions = new Stack<ArrayDeque<ConditionalExpression>>();
		this.universe = universe;
		this.factory = factory;
	}

	/**
	 * Get the CIVL function that is being processed
	 * 
	 * @return The current function
	 */
	public CIVLFunction function() {
		return this.function;
	}

	/**
	 * Generate a temporal variable for translating away conditional expression
	 * 
	 * @param scope
	 *            The scope of the temporal variable
	 * @param source
	 *            The CIVL source of the conditional expression
	 * @param type
	 *            The CIVL type of the conditional expression
	 * @return The variable expression referring to the temporal variable
	 */
	public VariableExpression tempVariable(Scope scope, CIVLSource source,
			CIVLType type) {
		String name = "$V" + this.conditionalExpressionCounter++;
		int vid = scope.numVariables();
		StringObject stringObject = (StringObject) universe.canonic(universe
				.stringObject(name));
		Variable variable = new CommonVariable(source, type,
				new CommonIdentifier(source, stringObject), vid);
		VariableExpression result = new CommonVariableExpression(source,
				variable);

		scope.addVariable(variable);
		((CommonVariableExpression) result).setExpressionType(variable.type());
		return result;
	}

	/**
	 * Add a new conditional expression
	 * 
	 * @param expression
	 *            The new conditional expression
	 */
	public void addConditionalExpression(ConditionalExpression expression) {
		this.conditionalExpressions.peek().add(expression);
	}

	/**
	 * Translate a condition that contains conditional expressions in to an
	 * if-else statement
	 * 
	 * @param scope
	 *            The scope of the expression
	 * @param guard
	 *            The guard
	 * @param expression
	 *            The expression
	 * @return The if-else fragment and the expression without conditional
	 *         expressions
	 */
	public Map.Entry<Fragment, Expression> refineConditionalExpression(
			Scope scope, Expression guard, Expression expression) {
		Fragment beforeCondition = null;

		while (hasConditionalExpressions()) {
			ConditionalExpression conditionalExpression = pollConditionaExpression();
			VariableExpression variable = tempVariable(scope,
					conditionalExpression.getSource(),
					conditionalExpression.getExpressionType());

			beforeCondition = factory.conditionalExpressionToIf(guard,
					variable, conditionalExpression);
			if (expression == conditionalExpression)
				expression = variable;
			else
				expression.replaceWith(conditionalExpression, variable);
		}

		return new AbstractMap.SimpleEntry<Fragment, Expression>(
				beforeCondition, expression);
	}

	/**
	 * Translate a condition that contains conditional expressions in to an
	 * if-else statement
	 * 
	 * @param scope
	 *            The scope of the expression
	 * @param guard
	 *            The guard
	 * @param expression
	 *            The expression
	 * @return The if-else fragment and the expression without conditional
	 *         expressions
	 */

	/**
	 * Translate away conditional expressions from a statement. First create a
	 * temporal variable, then replace the conditional expression with the
	 * temporal variable (recursively), then an if-else statement is created to
	 * update the value of the temporal variable, and combine it with the
	 * original statement without condition expressions.
	 * 
	 * @param statement
	 *            The statement that contains conditional expressions
	 * @param oldLocation
	 *            The source location of statement
	 * @return The fragment includes the equivalent if-else statement and the
	 *         modified statement without conditional expressions
	 */
	public Fragment refineConditionalExpressionOfStatement(Statement statement,
			Location oldLocation) {
		Fragment result = new Fragment();

		while (hasConditionalExpressions()) {
			ConditionalExpression conditionalExpression = pollConditionaExpression();
			VariableExpression variable = tempVariable(
					statement.statementScope(),
					conditionalExpression.getSource(),
					conditionalExpression.getExpressionType());
			Fragment ifElse = factory.conditionalExpressionToIf(
					statement.guard(), variable, conditionalExpression);

			statement.replaceWith(conditionalExpression, variable);

			result = result.combineWith(ifElse);
		}

		result = result.combineWith(new Fragment(oldLocation, statement));

		return result;
	}

	/*
	 * if (functionInfo.hasConditionalExpressions() == true) { Statement
	 * newStatement = result.lastStatement; Location oldLocation =
	 * result.startLocation;
	 * 
	 * result = new Fragment();
	 * 
	 * while (functionInfo.hasConditionalExpressions()) { ConditionalExpression
	 * conditionalExpression = functionInfo .pollConditionaExpression();
	 * VariableExpression variable = functionInfo.tempVariable(
	 * newStatement.statementScope(), conditionalExpression.getSource(),
	 * conditionalExpression.getExpressionType()); Fragment ifElse =
	 * factory.conditionalExpressionToIf( newStatement.guard(), variable,
	 * conditionalExpression);
	 * 
	 * newStatement.replaceWith(conditionalExpression, variable);
	 * 
	 * result = result.combineWith(ifElse); }
	 * 
	 * result = result .combineWith(new Fragment(oldLocation, newStatement)); }
	 */
	// public void increaseConditionalExpressionCounter(){
	// this.conditionalExpressionCounter++;
	// }

	/**
	 * Add a new queue to store conditional expression. This is invoked at the
	 * beginning of translating each new statement node, expression node,
	 * variable declaration node, etc.
	 */
	public void addConditionalExpressionQueue() {
		conditionalExpressions.add(new ArrayDeque<ConditionalExpression>());
	}

	/**
	 * Pop the queue of conditional expressions from the stack. This is invoked
	 * at the end of translating each new statement node, expression node,
	 * variable declaration node, etc.
	 */
	public void popConditionaExpressionStack() {
		conditionalExpressions.pop();
	}

	// public ConditionalExpression peekConditionaExpression() {
	// return conditionalExpressions.peek().peek();
	// }

	/**
	 * @return The earliest conditional expression in the latest queue in the
	 *         stack of conditional expression queues
	 */
	public ConditionalExpression pollConditionaExpression() {
		return conditionalExpressions.peek().pollFirst();
	}

	/**
	 * @return True iff the latest queue is empty
	 */
	public boolean hasConditionalExpressions() {
		if (!conditionalExpressions.peek().isEmpty())
			return true;
		return false;
	}

	/**
	 * Add a set of statements to the continue statement stack <dt>
	 * <b>Preconditions:</b>
	 * <dd>
	 * A new loop is just encountered
	 * 
	 * @param statementSet
	 *            an empty set of statement
	 */
	public void addContinueSet(Set<Statement> statementSet) {
		this.continueStatements.add(statementSet);
	}

	/**
	 * Peek the continue stack, called only when processing a jump node with
	 * continue kind
	 * 
	 * @return the set of continue statements on the top of the stack
	 */
	public Set<Statement> peekContinueStatck() {
		return this.continueStatements.peek();
	}

	/**
	 * Pop the set of continue statements from the stack
	 * 
	 * @return the set of continue statements from the top of the stack
	 */
	public Set<Statement> popContinueStack() {
		return this.continueStatements.pop();
	}

	/**
	 * Add a set of statements to the break statement stack <dt>
	 * <b>Preconditions:</b>
	 * <dd>
	 * A new loop is just encountered
	 * 
	 * @param statementSet
	 *            an empty set of statements
	 */
	public void addBreakSet(Set<Statement> statementSet) {
		this.breakStatements.add(statementSet);
	}

	/**
	 * Pop the set of break statements from the stack
	 * 
	 * @return the set of break statements from the top of the stack
	 */
	public Set<Statement> popBreakStack() {
		return this.breakStatements.pop();
	}

	/**
	 * Peek the break stack, called only when processing a jump node with break
	 * kind
	 * 
	 * @return the set of break statements on the top of the stack
	 */
	public Set<Statement> peekBreakStatck() {
		return this.breakStatements.peek();
	}

	/**
	 * Return the map of goto statements
	 * 
	 * @return mapping from goto statement to label node
	 */
	public Map<Statement, LabelNode> gotoStatements() {
		return this.gotoStatements;
	}

	/**
	 * Add a mapping of a goto statement and a label node to the map of goto
	 * statements
	 * 
	 * @param statement
	 *            The goto statement
	 * @param labelNode
	 *            The label node of the target of the goto statement
	 */
	public void putToGotoStatements(Statement statement, LabelNode labelNode) {
		this.gotoStatements.put(statement, labelNode);
	}

	/**
	 * Return the map of labeled locations
	 * 
	 * @return mapping from labeled node to locations
	 */
	public Map<LabelNode, Location> labeledLocations() {
		return this.labeledLocations;
	}

	/**
	 * Add a mapping from a label node to a location
	 * 
	 * @param labelNode
	 *            The label node
	 * @param location
	 *            The location corresponding to the label node
	 */
	public void putToLabeledLocations(LabelNode labelNode, Location location) {
		this.labeledLocations.put(labelNode, location);
	}

	/**
	 * Complete the function with a fragment
	 * 
	 * @param functionBody
	 *            a fragment translated from the body of the function
	 */
	public void completeFunction(Fragment functionBody) {
		Stack<Location> workingLocations = new Stack<Location>();
		Location location;

		// start from the start location of the fragment
		workingLocations.add(functionBody.startLocation);
		function.setStartLocation(functionBody.startLocation);

		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledLocations.get(gotoStatements.get(s)));
		}

		while (workingLocations.size() > 0) {
			location = workingLocations.pop();
			function.addLocation(location);

			if (location.getNumOutgoing() > 0) {
				// for each statement in the outgoing set of a location, add
				// itself to function, and add its target location into the
				// working stack if it hasn't been encountered before.
				for (Statement statement : location.outgoing()) {
					Location newLocation = statement.target();

					function.addStatement(statement);
					if (newLocation != null) {
						if (!function.locations().contains(newLocation)) {
							workingLocations.push(newLocation);
						}
					}
				}
			}
		}
	}
}
