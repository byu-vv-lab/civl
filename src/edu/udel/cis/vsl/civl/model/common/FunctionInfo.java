package edu.udel.cis.vsl.civl.model.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.abc.ast.node.IF.label.LabelNode;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;

public class FunctionInfo {

	private CIVLFunction function;
	
	/**
	 * This fields stores information for a single function, the current one
	 * being processed. It maps ABC label nodes to the corresponding CIVL
	 * locations.
	 */
	private Map<LabelNode, Location> labeledLocations;

	/**
	 * Also being used for single function (the one being processed). Maps from
	 * CIVL "goto" statements to the corresponding label nodes.
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
	
	public FunctionInfo(CIVLFunction function){
		this.function = function;
		labeledLocations = new LinkedHashMap<LabelNode, Location> ();
		gotoStatements = new LinkedHashMap<Statement, LabelNode>();
		continueStatements = new Stack<Set<Statement>>();
		breakStatements = new Stack<Set<Statement>>();
	}
	
	public CIVLFunction function(){
		return this.function;
	}
	
	public void addContinueSet(Set<Statement> statementSet){
		this.continueStatements.add(statementSet);
	}
	
	public Set<Statement> peekContinueStatck(){
		return this.continueStatements.peek();
	}
	
	public Set<Statement> popContinueStack(){
		return this.continueStatements.pop();
	}
	
	public void addBreakSet(Set<Statement> statementSet){
		this.breakStatements.add(statementSet);
	}
	
	public Set<Statement> popBreakStack(){
		return this.breakStatements.pop();
	}
	
	public Set<Statement> peekBreakStatck(){
		return this.breakStatements.peek();
	}
	
	public  Map<Statement, LabelNode> gotoStatements(){
		return this.gotoStatements;
	}
	
	public void putToGotoStatements(Statement statement, LabelNode labelNode){
		this.gotoStatements.put(statement, labelNode);	
	}
	
	
	public Map<LabelNode, Location> labeledLocations(){
		return this.labeledLocations;
	}
	
	public void putToLabeledLocations(LabelNode labelNode, Location location){
		this.labeledLocations.put(labelNode, location);	
	}
	
	public void completeFunction(Fragment functionBody){
		Stack<Location> workingLocations = new Stack<Location>();
		Location location;
		
		workingLocations.add(functionBody.startLocation);
		function.setStartLocation(functionBody.startLocation);
		
		while(workingLocations.size() > 0){
			location = workingLocations.pop();
			function.addLocation(location);
						
			if(location.getNumOutgoing() > 0){
				for(Statement statement : location.outgoing()){
					Location newLocation = statement.target();
					
					function.addStatement(statement);
					if(newLocation != null){
						if(!function.locations().contains(newLocation)){
							workingLocations.push(newLocation);
						}
					}
				}
			}
		}
		
		//function.print("", System.out);
	}
}
