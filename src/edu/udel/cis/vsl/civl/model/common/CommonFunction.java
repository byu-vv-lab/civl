/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.model.common.expression.CommonBooleanLiteralExpression;
import edu.udel.cis.vsl.civl.model.common.statement.CommonNoopStatement;

/**
 * A function.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonFunction extends CommonSourceable implements CIVLFunction {

	private Identifier name;
	private Vector<Variable> parameters;
	private CIVLType returnType;
	private Set<Scope> scopes;
	private Scope outerScope;
	private Scope containingScope;
	private Set<Statement> statements;
	private Location startLocation;
	private Set<Location> locations;
	private Expression precondition = null;
	private Expression postcondition = null;
	private Model model;
	protected boolean isSystem = false;

	/**
	 * A function.
	 * 
	 * @param name
	 *            The name of this function.
	 * @param parameters
	 *            The list of parameters.
	 * @param returnType
	 *            The return type of this function.
	 * @param containingScope
	 *            The scope containing this function.
	 * @param startLocation
	 *            The first location in the function.
	 */
	public CommonFunction(CIVLSource source, Identifier name,
			Vector<Variable> parameters, CIVLType returnType,
			Scope containingScope, Location startLocation, ModelFactory factory) {
		super(source);
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.containingScope = containingScope;
		scopes = new HashSet<Scope>();
		outerScope = factory.scope(source, containingScope,
				new LinkedHashSet<Variable>(), this);
		for (Variable variable : parameters) {
			outerScope.addVariable(variable);
		}
		scopes.add(outerScope);
		locations = new LinkedHashSet<Location>();
		this.startLocation = startLocation;
		if (startLocation != null) {
			locations.add(this.startLocation);
		}
		statements = new LinkedHashSet<Statement>();
	}

	/**
	 * @return The name of this function.
	 */
	public Identifier name() {
		return name;
	}

	/**
	 * @return The list of parameters.
	 */
	public Vector<Variable> parameters() {
		return parameters;
	}

	/**
	 * @return The return type of this function.
	 */
	public CIVLType returnType() {
		return returnType;
	}

	/**
	 * @return The set of scopes in this function.
	 */
	public Set<Scope> scopes() {
		return scopes;
	}

	/**
	 * @return The outermost local scope in this function.
	 */
	public Scope outerScope() {
		return outerScope;
	}

	/**
	 * @return The scope containing this function.
	 */
	public Scope containingScope() {
		return containingScope;
	}

	/**
	 * @return The set of statements in this function.
	 */
	public Set<Statement> statements() {
		return statements;
	}

	/**
	 * @return The first location in this function.
	 */
	public Location startLocation() {
		return startLocation;
	}

	/**
	 * @return The set of locations in this function.
	 */
	public Set<Location> locations() {
		return locations;
	}

	/**
	 * @return The precondition for this function. Null if not set.
	 */
	public Expression precondition() {
		return precondition;
	}

	/**
	 * @return The postcondition for this function. Null if not set.
	 */
	public Expression postcondition() {
		return postcondition;
	}
	
	/**
	 * @return The model to which this function belongs.
	 */
	@Override
	public Model model() {
		return model;
	}

	/**
	 * @param statements
	 *            The set of statements in this function.
	 */
	public void setStatements(Set<Statement> statements) {
		this.statements = statements;
	}

	/**
	 * @param startLocation
	 *            The first location in this function.
	 */
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
		if (!locations.contains(startLocation)) {
			locations.add(startLocation);
		}
	}

	/**
	 * @param locations
	 *            The set of locations in this function.
	 */
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	/**
	 * @param location
	 *            The new location to add.
	 */
	public void addLocation(Location location) {
		locations.add(location);
	}

	/**
	 * @param statement
	 *            The new statement to add.
	 */
	public void addStatement(Statement statement) {
		statements.add(statement);
	}

	/**
	 * @param name
	 *            The name of this function.
	 */
	public void setName(Identifier name) {
		this.name = name;
	}

	/**
	 * @param parameters
	 *            The list of parameters.
	 */
	public void setParameters(Vector<Variable> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @param returnType
	 *            The return type of this function.
	 */
	public void setReturnType(CIVLType returnType) {
		this.returnType = returnType;
	}

	/**
	 * @param scopes
	 *            The set of scopes in this function.
	 */
	public void setScopes(Set<Scope> scopes) {
		this.scopes = scopes;
	}

	/**
	 * @param outerScope
	 *            The outermost local scope of this function.
	 */
	public void setOuterScope(Scope outerScope) {
		this.outerScope = outerScope;
	}

	/**
	 * @param containingScope
	 *            The scope containing this function.
	 */
	public void setContainingScope(Scope containingScope) {
		this.containingScope = containingScope;
	}

	/**
	 * @param precondition
	 *            The precondition for this function.
	 */
	public void setPrecondition(Expression precondition) {
		this.precondition = precondition;
	}

	/**
	 * @param postcondition
	 *            The postcondition for this function.
	 */
	public void setPostcondition(Expression postcondition) {
		this.postcondition = postcondition;
	}

	/**
	 * @param model The Model to which this function belongs.
	 */
	@Override
	public void setModel(Model model) {
		this.model = model;
	}
	
	/**
	 * Print the function.
	 * 
	 * @param prefix
	 *            String prefix to print on each line
	 * @param out
	 *            The PrintStream to use for printing.
	 */
	public void print(String prefix, PrintStream out) {
		Iterator<Variable> iter;

		out.println(prefix + "function " + name);
		if (precondition != null) {
			out.println(prefix + "| requires " + precondition);
		}
		if (postcondition != null) {
			out.println(prefix + "| ensures " + postcondition);
		}
		out.println(prefix + "| formal parameters");
		iter = parameters.iterator();
		while (iter.hasNext()) {
			out.println(prefix + "| | " + iter.next().name());
		}
		outerScope.print(prefix + "| ", out);
		if (!isSystem()) {
			out.println(prefix + "| locations (start=" + startLocation.id()
					+ ")");
			for (Location loc : locations) {
				loc.print(prefix + "| | ", out);
			}
		}
		out.flush();
	}

	@Override
	public String toString() {
		String result = name.name() + "(";

		for (int i = 0; i < parameters.size(); i++) {
			if (i != 0) {
				result += ",";
			}
			result += parameters.get(i);
		}
		result += ")";
		return result;
	}

	@Override
	public boolean isSystem() {
		return isSystem;
	}
	
	/**
	 * Remove all locations that satisfiy the following conditions:
	 * 1. has exactly one outgoing statement and
	 * 2. the statement is a no-op with the gard true.
	 * Meanwhile, have to redirect each statement that targets at the no-op location
	 * to the target of the no-op location.
	 * For example, let l(s->l', ...) be a location l with statement s going to l' ...
	 * l1 (s1 -> l2, s2 -> l3), l2 ([true]no-op -> l4), l3(), l(4)
	 * After applying simplify(), should be
	 * l1 (s1 -> l4, s2 -> l3), l3(), l4()
	 */
	@Override
	public void simplify(){
		ArrayList<Location> oldLocations = new ArrayList<Location>(this.locations);
		int count = oldLocations.size();
		
		/**
		 * The index of locations that can be removed
		 */
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		
		for(int i = 0; i < count; i++){
			Location loc = oldLocations.get(i);
			
			Set<Statement> statements = loc.outgoing();
			 
			/**
			 * loc has exactly one statement
			 */
			if(statements.size() == 1){
				for(Statement s: statements){
					/**
					 * The only statement of loc is a no-op statement
					 */
					if(s instanceof CommonNoopStatement){
						Expression guard = s.guard();
						
						/**
						 * The guard of the no-op is true
						 * TODO: can be improved by checking if guard has any side-effect,
						 * e.g., if guard is (x + y < 90) then we still can remove this no-op statement
						 */
						if(guard instanceof CommonBooleanLiteralExpression){
							if(((CommonBooleanLiteralExpression)guard).value()){
								/**
								 * Record the index of loc so that it can be removed later
								 */
								toRemove.add(i);
								
								/**
								 * The target of loc
								 */
								Location target = s.target();
								
								for(int j = 0; j < count; j++){
									/**
									 * Do nothing to the locations that are to be removed
									 */
									if(toRemove.contains(j))
										continue;
									
									Location curLoc = oldLocations.get(j);
									
									/**
									 * For each statement of curLoc \in (this.locations - toRemove)
									 */
									for(Statement curS : curLoc.outgoing()){
										Location curTarget = curS.target();
										
										/**
										 * Redirect the target location so that no-op location is skipped
										 */
										if(curTarget != null && curTarget.id() == loc.id()){
											curS.setTarget(target);//the incoming field is implicitly modified by setTarget()
										}
									}
								}								
							}
						}
					}
				}
			}
		}
		
		Set<Location> newLocations = new LinkedHashSet<Location>();
		for(int k = 0; k < count; k++){
			if(toRemove.contains(k))
				continue;
			newLocations.add(oldLocations.get(k));
		}
		
		this.locations = newLocations;
	}

}
