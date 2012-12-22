package edu.udel.cis.vsl.civl.civlc.preproc.IF;

import org.antlr.runtime.Token;

public class PreprocessorRuntimeException extends RuntimeException {
	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = -5474369169753584154L;

	/**
	 * Token where error occurred. May be null. If non-null, a few words
	 * describing it are appended to the message.
	 */
	private Token token;

	public PreprocessorRuntimeException(String msg, Token token) {
		super(token == null ? msg : msg + "\nAt " + token);
		this.token = token;
	}

	public PreprocessorRuntimeException(String msg) {
		super(msg);
	}

	public Token getToken() {
		return token;
	}

}
