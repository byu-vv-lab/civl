/**
 * 
 */
package edu.udel.cis.vsl.civl.model.common.location;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.common.CommonSourceable;

/**
 * The parent of all locations.
 * 
 * @author Timothy K. Zirkel (zirkel)
 * 
 */
public class CommonLocation extends CommonSourceable implements Location {

	/**
	 * Atomic flags of a location:
	 * <ul>
	 * <li>NONE: no $atomic/$atom boundary;</li>
	 * <li>ENTER/DENTER: the location is the starting point of an $atomic/$atom
	 * block;</li>
	 * <li>LEAVE/DELAVE: the location is the ending point of an $atomic/$atom
	 * block.</li>
	 * </ul>
	 * 
	 * @author Zheng
	 * 
	 */
	public enum AtomicKind {
		NONE, ENTER, LEAVE, DENTER, DLEAVE
	}

	/************************* Instance Fields *************************/

	/**
	 * Store the static analysis result. True iff all outgoing statements from
	 * this location are purely local.
	 */
	private boolean allOutgoingPurelyLocal = false;

	/**
	 * The atomic kind of this location, initialized as NONE.
	 */
	private AtomicKind atomicKind = AtomicKind.NONE;

	/**
	 * The function that this location belongs to
	 */
	private CIVLFunction function;

	/**
	 * The unique id of this location
	 */
	private int id;

	/**
	 * The list of incoming statements, i.e., statements that targeting at this
	 * location.
	 */
	private ArrayList<Statement> incoming = new ArrayList<>();

	/**
	 * Store the static loop analysis result. True iff this location is possible
	 * to form a loop.
	 */
	private boolean loopPossible = false;

	/**
	 * The list of outgoing statements, i.e., statements that has this location
	 * as the source location.
	 */
	private ArrayList<Statement> outgoing = new ArrayList<>();

	/**
	 * The status denoting if this location is purely local, initialized as
	 * false. A location is considered as purely local if it satisfies the
	 * following conditions.
	 * <ol>
	 * <li>it has exactly one incoming edge; (to avoid loop)</li>
	 * <li>if it is not the starting point of an $atomic/$atom block, then all
	 * its outgoing statements should be purely local;</li>
	 * <li>if it is the starting point of an $atomic/$atom bloc, then all
	 * statements reachable within that $atomic/$atom block are purely local.</li>
	 * </ol>
	 */
	private boolean purelyLocal = false;

	/**
	 * The static scope that this location belongs to.
	 */
	private Scope scope;

	/************************** Constructors *************************/

	/**
	 * The parent of all locations.
	 * 
	 * @param source
	 *            The corresponding source (file, line, column, text, etc) of
	 *            this location
	 * @param scope
	 *            The scope containing this location.
	 * @param id
	 *            The unique id of this location.
	 */
	public CommonLocation(CIVLSource source, Scope scope, int id) {
		super(source);
		this.scope = scope;
		this.id = id;
		this.function = scope.function();
	}

	/************************** Methods from Location *************************/

	@Override
	public void addIncoming(Statement statement) {
		incoming.add(statement);
	}

	@Override
	public void addOutgoing(Statement statement) {
		outgoing.add(statement);
	}

	@Override
	public boolean allOutgoingPurelyLocal() {
		return this.allOutgoingPurelyLocal;
	}

	@Override
	public AtomicKind atomicKind() {
		return this.atomicKind;
	}

	@Override
	public boolean enterAtomic() {
		return this.atomicKind == AtomicKind.ENTER;
	}

	@Override
	public boolean enterDatomic() {
		return this.atomicKind == AtomicKind.DENTER;
	}

	@Override
	public CIVLFunction function() {
		return function;
	}

	@Override
	public Statement getIncoming(int i) {
		return incoming.get(i);
	}

	@Override
	public int getNumIncoming() {
		return incoming.size();
	}

	@Override
	public int getNumOutgoing() {
		return outgoing.size();
	}

	@Override
	public Statement getOutgoing(int i) {
		return outgoing.get(i);
	}

	@Override
	public Statement getSoleOutgoing() {
		int size = outgoing.size();

		if (size >= 1) {
			Statement result = outgoing.iterator().next();

			if (size > 1) {
				throw new CIVLInternalException(
						"Expected 1 outgoing transition but saw " + size,
						result.getSource());
			}
			return result;
		}
		throw new CIVLInternalException(
				"Expected 1 outgoing transition but saw 0 at " + this
						+ " in function " + function, this.getSource());
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public Iterable<Statement> incoming() {
		return incoming;
	}

	@Override
	public boolean isLoopPossible() {
		return this.loopPossible;
	}

	@Override
	public boolean isPurelyLocal() {
		return this.purelyLocal;
	}

	@Override
	public boolean leaveAtomic() {
		return this.atomicKind == AtomicKind.LEAVE;
	}

	@Override
	public boolean leaveDatomic() {
		return this.atomicKind == AtomicKind.DLEAVE;
	}

	@Override
	public void loopAnalysis() {
		if (this.loopPossible) {
			// this is the loop entrance of a loop statement
			// check if it is finite
		} else {
			// this is not the loop entrance of a loop statement, check if it is
			// possible to form a loop by other statements like goto
		}
	}

	@Override
	public Iterable<Statement> outgoing() {
		return outgoing;
	}

	@Override
	public void print(String prefix, PrintStream out) {
		String targetLocation = null;
		String guardString = "(true)";
		String gotoString;

		String headString = null;
		if (this.purelyLocal) {
			headString = prefix + "location " + id() + " (scope: " + scope.id()
					+ ") #";
		} else
			headString = prefix + "location " + id() + " (scope: " + scope.id()
					+ ")";
		if (this.loopPossible)
			headString = headString + "LOOP;";
		switch (this.atomicKind) {
		case ENTER:
			headString = headString + "ENTER_ATOMIC;";
			break;
		case DENTER:
			headString = headString + "ENTER_ATOM;";
			break;
		case LEAVE:
			headString = headString + "LEAVE_ATOMIC;";
			break;
		case DLEAVE:
			headString = headString + "LEAVE_ATOM;";
		default:
		}
		out.println(headString);
		for (Statement statement : outgoing) {
			if (statement.target() != null) {
				targetLocation = "" + statement.target().id();
			}
			if (statement.guard() != null) {
				guardString = "(" + statement.guard() + ")";
			}
			gotoString = prefix + "| " + "when " + guardString + " "
					+ statement + " @ " + statement.getSource().getLocation()
					+ " ;";
			if (targetLocation != null) {
				gotoString += " goto location " + targetLocation;
			}
			out.println(gotoString);
		}
	}

	@Override
	public void purelyLocalAnalysis() {
		// Usually, a location is purely local if it has exactly one outgoing
		// statement that is purely local
		if (incoming.size() != 1) {
			this.purelyLocal = false;
			return;
		}
		// a location that enters an atomic/atom block is considered as purely
		// local only
		// if all the statements that are to be executed in the atomic block are
		// purely local
		if (this.atomicKind == AtomicKind.DENTER) {
			Stack<Integer> atomicFlags = new Stack<Integer>();
			Location newLocation = this;
			Set<Integer> checkedLocations = new HashSet<Integer>();

			do {
				Statement s = newLocation.getOutgoing(0);

				if (s instanceof CallOrSpawnStatement) {
					if (((CallOrSpawnStatement) s).isCall())
						this.purelyLocal = false;
					return;
				}
				checkedLocations.add(newLocation.id());
				if (!s.isPurelyLocal()) {
					this.purelyLocal = false;
					return;
				}
				if (newLocation.enterDatomic())
					atomicFlags.push(1);
				if (newLocation.leaveDatomic())
					atomicFlags.pop();
				newLocation = s.target();
				if (checkedLocations.contains(newLocation.id()))
					newLocation = null;
			} while (newLocation != null && !atomicFlags.isEmpty());
			this.purelyLocal = true;
		} else if (this.atomicKind == AtomicKind.ENTER) {
			Stack<Integer> atomicFlags = new Stack<Integer>();
			Location newLocation = this;
			Set<Integer> checkedLocations = new HashSet<Integer>();

			do {
				Statement s = newLocation.getOutgoing(0);

				if (s instanceof CallOrSpawnStatement) {
					if (((CallOrSpawnStatement) s).isCall())
						this.purelyLocal = false;
					return;
				}
				checkedLocations.add(newLocation.id());
				if (!s.isPurelyLocal()) {
					this.purelyLocal = false;
					return;
				}
				if (newLocation.enterAtomic())
					atomicFlags.push(1);
				if (newLocation.leaveAtomic())
					atomicFlags.pop();
				newLocation = s.target();
				if (checkedLocations.contains(newLocation.id()))
					newLocation = null;
			} while (newLocation != null && !atomicFlags.isEmpty());
			this.purelyLocal = true;
		} else {
			this.purelyLocal = true;
			for (Statement s : this.outgoing) {
				this.purelyLocal = this.purelyLocal && s.isPurelyLocal();
			}
		}
	}

	@Override
	public void purelyLocalAnalysisForOutgoing() {
		// a location that enters an atomic block is considered as atomic only
		// if all the statements that are to be executed in the atomic block are
		// purely local
		if (this.atomicKind == AtomicKind.DENTER) {
			Stack<Integer> atomicFlags = new Stack<Integer>();
			Location newLocation = this;
			Set<Integer> checkedLocations = new HashSet<Integer>();

			do {
				Statement s = newLocation.getOutgoing(0);

				checkedLocations.add(newLocation.id());
				if (!s.isPurelyLocal()) {
					this.allOutgoingPurelyLocal = false;
					return;
				}
				if (newLocation.enterDatomic())
					atomicFlags.push(1);
				if (newLocation.leaveDatomic())
					atomicFlags.pop();
				newLocation = s.target();
				if (checkedLocations.contains(newLocation.id()))
					newLocation = null;
			} while (newLocation != null && !atomicFlags.isEmpty());
			this.allOutgoingPurelyLocal = true;
			return;
		}

		for (Statement s : outgoing) {
			if (!s.isPurelyLocal())
				this.allOutgoingPurelyLocal = false;
		}
		this.allOutgoingPurelyLocal = true;
	}

	@Override
	public void removeIncoming(Statement statement) {
		incoming.remove(statement);
	}

	@Override
	public void removeOutgoing(Statement statement) {
		outgoing.remove(statement);
	}

	@Override
	public Scope scope() {
		return scope;
	}

	@Override
	public void setEnterAtomic(boolean deterministic) {
		if (deterministic)
			this.atomicKind = AtomicKind.DENTER;
		else
			this.atomicKind = AtomicKind.ENTER;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setLeaveAtomic(boolean deterministic) {
		if (deterministic)
			this.atomicKind = AtomicKind.DLEAVE;
		else
			this.atomicKind = AtomicKind.LEAVE;
	}

	// TODO improve the static analysis of loop locations
	@Override
	public void setLoopPossible(boolean possible) {
		this.loopPossible = possible;
	}

	@Override
	public void setScope(Scope scope) {
		this.scope = scope;
		this.function = scope.function();
	}

	/************************** Methods from Object *************************/

	@Override
	public boolean equals(Object that) {
		if (that instanceof CommonLocation) {
			return (((CommonLocation) that).id() == id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Location " + id;
	}
}
