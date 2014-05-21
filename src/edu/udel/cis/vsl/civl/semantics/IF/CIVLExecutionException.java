package edu.udel.cis.vsl.civl.semantics.IF;

import edu.udel.cis.vsl.civl.model.IF.CIVLException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Sourceable;

public class CIVLExecutionException extends CIVLException {

	/**
	 * Added by Eclipse.
	 */
	private static final long serialVersionUID = 1L;

	private StringBuffer stateString = null;

	private Certainty certainty;

	private ErrorKind kind;

	/**
	 * Constructs new CIVLException with given fields.
	 * 
	 * @param kind
	 *            the kind of error
	 * @param certainty
	 *            the certainty with which this is known to be an error in the
	 *            program being verified
	 * @param message
	 *            a message explaining the error
	 * @param source
	 *            the source code element associated to the error; may be null
	 */
	public CIVLExecutionException(ErrorKind kind, Certainty certainty,
			String message, CIVLSource source) {
		super(message, source);
		assert kind != null;
		assert certainty != null;
		this.kind = kind;
		this.certainty = certainty;
	}

	public CIVLExecutionException(ErrorKind kind, Certainty certainty,
			String message, StringBuffer stateString, CIVLSource source) {
		super(message, source);
		assert kind != null;
		assert certainty != null;
		this.stateString = stateString;
		this.kind = kind;
		this.certainty = certainty;
	}

	public CIVLExecutionException(ErrorKind kind, Certainty certainty,
			String message, Sourceable sourceable) {
		this(kind, certainty, message, sourceable.getSource());
	}

	public Certainty certainty() {
		return certainty;
	}

	public ErrorKind kind() {
		return kind;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("CIVL execution error (kind: ");
		result.append(kind);
		result.append(", certainty: ");
		result.append(certainty);
		result.append(")\n");
		result.append(super.toString());
		if (this.stateString != null) {
			result.append("\n");
			result.append(this.stateString);
		}
		return result.toString();
	}
}
