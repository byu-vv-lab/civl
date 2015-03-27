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
	@SuppressWarnings("unused")
	private SymbolicExpression heapValue;

	public CIVLNonEmptyHeapException(ErrorKind kind, Certainty certainty,
			String message, State state, CIVLSource source) {
		super(kind, certainty, message, state, source);
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
}
