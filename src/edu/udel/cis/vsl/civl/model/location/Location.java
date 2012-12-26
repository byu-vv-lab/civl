/**
 * 
 */
package edu.udel.cis.vsl.civl.model.location;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.civl.model.Function;
import edu.udel.cis.vsl.civl.model.Scope;
import edu.udel.cis.vsl.civl.model.statement.Statement;

/**
 * The parent of all locations.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class Location {

	private int id;
	private Scope scope;
	private Set<Statement> incoming = new HashSet<Statement>();
	private Set<Statement> outgoing = new HashSet<Statement>();
	private Function function;

	/**
	 * The parent of all locations.
	 * 
	 * @param scope
	 *            The scope containing this location.
	 * @param id
	 *            The unique id of this location.
	 */
	public Location(Scope scope, int id) {
		this.scope = scope;
		this.id = id;
		this.function = scope.function();
	}

	/**
	 * @return The unique ID number of this location.
	 */
	public int id() {
		return id;
	}

	/**
	 * @return The scope of this location.
	 */
	public Scope scope() {
		return scope;
	}

	/**
	 * @return The function containing this location.
	 */
	public Function function() {
		return function;
	}

	/**
	 * @return The set of incoming statements.
	 */
	public Set<Statement> incoming() {
		return incoming;
	}

	/**
	 * @return The set of outgoing statements.
	 */
	public Set<Statement> outgoing() {
		return outgoing;
	}

	/**
	 * Set the unique ID number of this location.
	 * 
	 * @param id
	 *            The unique ID number of this location.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param scope
	 *            The scope of this location.
	 */
	public void setScope(Scope scope) {
		this.scope = scope;
		this.function = scope.function();
	}

	/**
	 * @param incoming
	 *            The set of incoming statements.
	 */
	public void setIncoming(Set<Statement> incoming) {
		this.incoming = incoming;
	}

	/**
	 * @param outgoing
	 *            The set of outgoing statements.
	 */
	public void setOutgoing(Set<Statement> outgoing) {
		this.outgoing = outgoing;
	}

	/**
	 * @param statement
	 *            A new incoming statement.
	 */
	public void addIncoming(Statement statement) {
		incoming.add(statement);
	}

	/**
	 * @param statement
	 *            A new outgoing statement.
	 */
	public void addOutgoing(Statement statement) {
		outgoing.add(statement);
	}

	/**
	 * Print this location and all outgoing transitions.
	 * 
	 * @param prefix
	 *            The prefix string for all lines of this printout.
	 * @param out
	 *            The PrintStream to use for printing this location.
	 */
	public void print(String prefix, PrintStream out) {
		String targetLocation;
		String guardString = "(true)";
		String gotoString;

		out.println(prefix + "location " + id() + " (scope: " + scope.id()
				+ ")");
		for (Statement statement : outgoing) {
			if (statement.target() != null) {
				targetLocation = "" + statement.target().id();
			} else {
				targetLocation = "<null>";
			}
			if (statement.guard() != null) {
				guardString = "(" + statement.guard() + ")";
			}
			gotoString = prefix + "| " + "when " + guardString + " "
					+ statement + "; goto location " + targetLocation;
			out.println(gotoString);
		}
	}

	@Override
	public String toString() {
		return "Location " + id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((incoming == null) ? 0 : incoming.hashCode());
		result = prime * result
				+ ((outgoing == null) ? 0 : outgoing.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (id != other.id)
			return false;
		if (incoming == null) {
			if (other.incoming != null)
				return false;
		} else if (!incoming.equals(other.incoming))
			return false;
		if (outgoing == null) {
			if (other.outgoing != null)
				return false;
		} else if (!outgoing.equals(other.outgoing))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

}
