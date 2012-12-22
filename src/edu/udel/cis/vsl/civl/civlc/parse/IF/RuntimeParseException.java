package edu.udel.cis.vsl.civl.civlc.parse.IF;

import org.antlr.runtime.Token;

public class RuntimeParseException extends RuntimeException {

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = -2754044970326749601L;

	private Token token;

	public RuntimeParseException(String msg, Token token) {
		super(token == null ? msg : msg + "\nAt " + token);
		this.token = token;
	}

	public RuntimeParseException(String msg) {
		super(msg);
	}

	public Token getToken() {
		return token;
	}
}
