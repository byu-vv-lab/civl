package edu.udel.cis.vsl.civl.civlc.preproc.IF;

import org.antlr.runtime.Token;

public class PreprocessorException extends Exception {

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Token where error occurred. May be null. If non-null, a few words
	 * describing it are appended to the message.
	 */
	private Token token;

	public PreprocessorException(String msg, Token token) {
		super(token == null ? msg : msg + "\nAt " + token);
		this.token = token;
	}

	public PreprocessorException(String msg) {
		super(msg);
	}

	public Token getToken() {
		return token;
	}

}
