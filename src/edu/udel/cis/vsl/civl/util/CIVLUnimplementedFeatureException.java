package edu.udel.cis.vsl.civl.util;

import edu.udel.cis.vsl.abc.token.IF.Source;

public class CIVLUnimplementedFeatureException extends RuntimeException {

	/** Generated ID. */
	private static final long serialVersionUID = -4225986290508573575L;

	/** Source code element that led to this exception. */
	private Source source;

	public CIVLUnimplementedFeatureException(Source source, String feature) {
		super("This feature is not yet implemented: " + feature + ":\n"
				+ source);
	}

	public CIVLUnimplementedFeatureException(String feature) {
		super("This feature is not yet implemented: " + feature);
	}

	public Source getSource() {
		return source;
	}

}
