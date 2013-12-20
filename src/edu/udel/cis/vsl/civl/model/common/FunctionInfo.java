package edu.udel.cis.vsl.civl.model.common;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.abc.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;

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
	 * Constructor
	 * 
	 * @param function
	 *            the CIVL function object that is being processed
	 * @param universe
	 *            The symbolic universe
	 * @param factory
	 *            The model factory
	 */
	public FunctionInfo(CIVLFunction function) {
		this.function = function;
		labeledLocations = new LinkedHashMap<LabelNode, Location>();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		continueStatements = new Stack<Set<Statement>>();
		breakStatements = new Stack<Set<Statement>>();
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
	 * A new loop or switch is just encountered
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
		ArrayDeque<Location> workingLocations = new ArrayDeque<Location>();
		Location location;

		// start from the start location of the fragment
		workingLocations.add(functionBody.startLocation());
		function.setStartLocation(functionBody.startLocation());

		for (Statement s : gotoStatements.keySet()) {
			s.setTarget(labeledLocations.get(gotoStatements.get(s)));
		}

		while (workingLocations.size() > 0) {
			// use first-in-first-out order to traverse locations so that they
			// are in natural order of the location id's
			location = workingLocations.pollFirst();
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
							workingLocations.add(newLocation);
						}
					}
				}
			}
		}
	}
}
