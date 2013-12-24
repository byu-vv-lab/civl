package edu.udel.cis.vsl.civl.model.common;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.common.statement.StatementSet;

//TODO: Make this CommonFragment, create an interface Fragment in model.IF, and have
// CommonFragment implement Fragment
/**
 * A fragment of a CIVL model. Consists of a start location and a last
 * statement. Why not always generate next location.
 * 
 * @author siegel
 * 
 */
public class CommonFragment implements Fragment {

	/**
	 * The start location of the fragment
	 */
	public Location startLocation;

	/**
	 * The last statement of the fragment
	 */
	public Statement lastStatement;

	/**
	 * Constructor: create an empty fragment
	 */
	public CommonFragment() {

	}

	/**
	 * Constructor
	 * 
	 * @param statement
	 *            use <code>statement</code> to create a new fragment, with the
	 *            start location being the source location of
	 *            <code>statement</code> and the last statement being
	 *            <code>statement</code>
	 */
	public CommonFragment(Statement statement) {
		this.startLocation = statement.source();
		this.lastStatement = statement;
	}

	/**
	 * Constructor
	 * 
	 * @param startLocation
	 *            the start location
	 * @param lastStatement
	 *            the last statement
	 */
	public CommonFragment(Location startLocation, Statement lastStatement) {
		this.startLocation = startLocation;
		this.lastStatement = lastStatement;
	}

	@Override
	public Fragment combineWith(Fragment next) {
		if (next == null || next.isEmpty())
			return this;

		if (this.isEmpty())
			return next;

		this.lastStatement.setTarget(next.startLocation());
		return new CommonFragment(this.startLocation, next.lastStatement());
	}

	@Override
	public Fragment parallelCombineWith(Fragment parallel) {
		StatementSet newLastStatement = new StatementSet();

		if (parallel == null || parallel.isEmpty())
			return this;
		if (this.isEmpty())
			return parallel;

		assert this.startLocation.id() == parallel.startLocation().id();

		if (lastStatement instanceof StatementSet) {
			Set<Statement> statements = ((StatementSet) lastStatement)
					.statements();

			for (Statement s : statements) {
				newLastStatement.add(s);
			}
		} else {
			newLastStatement.add(lastStatement);
		}

		if (parallel.lastStatement() instanceof StatementSet) {
			Set<Statement> statements = ((StatementSet) parallel
					.lastStatement()).statements();

			for (Statement s : statements) {
				newLastStatement.add(s);
			}
		} else {
			newLastStatement.add(parallel.lastStatement());
		}

		return new CommonFragment(this.startLocation, newLastStatement);
	}

	@Override
	public boolean isEmpty() {
		if (startLocation == null && lastStatement == null)
			return true;
		return false;
	}

	@Override
	public void Print(PrintStream out) {
		out.println(this.toString());
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "========Empty=========\r\n";
		String result = "=================\r\n";
		Stack<Location> workings = new Stack<Location>();
		Set<Integer> locationIds = new HashSet<Integer>();

		workings.push(this.startLocation);
		locationIds.add(this.startLocation.id());

		while (!workings.isEmpty()) {
			Location location = workings.pop();

			result += "Location " + location.id() + "\r\n";

			if (location.getNumOutgoing() > 0) {
				for (Statement s : location.outgoing()) {
					result += "when(" + s.guard() + ") " + s + " goto ";
					if (s.target() == null) {
						result += "null";
					} else {
						result += "Location " + s.target().id();
						if (!locationIds.contains(s.target().id())) {
							workings.push(s.target());
							locationIds.add(s.target().id());
						}
					}
				}
				result += "\r\n";
			}
		}

		result += "last statement: " + this.lastStatement + " at Location "
				+ this.lastStatement.source().id() + " "
				+ this.lastStatement.getSource() + "\r\n";

		return result;

	}

	@Override
	public void updateStartLocation(Location newLocation) {
		if (isEmpty())
			return;

		int oldLocationId = this.startLocation.id();
		int number = startLocation.getNumOutgoing();

		Stack<Location> workings = new Stack<Location>();
		Set<Integer> locationIds = new HashSet<Integer>();

		workings.push(startLocation);
		locationIds.add(startLocation.id());

		// TODO: Explain why/how we're doing this loop.
		while (!workings.isEmpty()) {
			Location location = workings.pop();

			if (location.getNumOutgoing() > 0) {
				number = location.getNumOutgoing();
				for (int i = 0; i < number; i++) {
					Statement s = location.getOutgoing(i);

					if (s.source().id() == oldLocationId) {
						s.setSource(newLocation);
					}
					if (s.target() != null) {
						if (s.target().id() == oldLocationId) {
							s.setTarget(newLocation);
						}
						if (!locationIds.contains(s.target().id())) {
							workings.push(s.target());
							locationIds.add(s.target().id());
						}
					}
				}
			}
		}

		this.startLocation = newLocation;
	}

	@Override
	public Location startLocation() {
		return startLocation;
	}

	@Override
	public Statement lastStatement() {
		return lastStatement;
	}

	@Override
	public void setStartLocation(Location location) {
		this.startLocation = location;
	}

	@Override
	public void setLastStatement(Statement statement) {
		this.lastStatement = statement;
	}

	@Override
	public void makeAtomic() {
		this.startLocation.setEnterAtomic(true);
		if (this.lastStatement != null) {
			if (lastStatement instanceof StatementSet) {
				for(Statement s : ((StatementSet)lastStatement).statements()){
					s.source().setLeaveAtomic(true);
				}
			} else
				this.lastStatement.source().setLeaveAtomic(true);
		}
	}
}
