package edu.udel.cis.vsl.civl.state.IF;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.CIVLException.Certainty;
import edu.udel.cis.vsl.civl.model.IF.CIVLException.ErrorKind;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

/**
 * Extends an execution exception with a state at which error occurred.
 * 
 * @author siegel
 * 
 */
public class CIVLNonEmptyHeapException extends CIVLStateException {

	/**
	 * Required by eclipse
	 */
	private static final long serialVersionUID = -5422700931342739728L;
	private SymbolicExpression heapValue;
	private String dyscopeName;
	private int dyscopeID;

	public CIVLNonEmptyHeapException(ErrorKind kind, Certainty certainty,
			State state, String dyscopeName, int dyscopeID,
			SymbolicExpression heapValue, CIVLSource source) {
		super(kind, certainty, "", state, source);
		this.dyscopeName = dyscopeName;
		this.dyscopeID = dyscopeID;
		this.heapValue = heapValue;
	}

	public CIVLSource source() {
		return this.source;
	}

	public State state() {
		return this.state;
	}

	public ErrorKind kind() {
		return this.kind;
	}

	public Certainty certainty() {
		return this.certainty;
	}

	public String message() {
		return this.message;
	}

	public String toString() {
		String result = super.toString() + "\n";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);

		ps.print(state.toString());
		result += baos.toString();
		return result;
	}

	public SymbolicExpression heapValue() {
		return heapValue;
	}

	public String dyscopeName() {
		return dyscopeName;
	}

	public int dyscopeID() {
		return dyscopeID;
	}
}
