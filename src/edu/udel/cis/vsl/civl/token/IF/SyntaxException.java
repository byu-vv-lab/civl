package edu.udel.cis.vsl.civl.token.IF;

public class SyntaxException extends Exception {

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = -2355680870938982989L;

	private Source source;

	public SyntaxException(String message, Source source) {
		super(source == null ? message : message + "\nAt "
				+ source.getSummary());
		assert source != null;
		this.source = source;
	}

	public SyntaxException(UnsourcedException oldException, Source newSource) {
		this(oldException.getMessage(), newSource);
	}

	public Source getSource() {
		return source;
	}

}
