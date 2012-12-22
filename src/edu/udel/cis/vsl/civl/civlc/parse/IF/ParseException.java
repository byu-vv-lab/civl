package edu.udel.cis.vsl.civl.civlc.parse.IF;

import org.antlr.runtime.Token;

public class ParseException extends Exception {

	/**
	 * Eclipse made me do it
	 */
	private static final long serialVersionUID = -5965287375421467649L;

	private Token token;

	public ParseException(String msg, Token token) {
		super(token == null ? msg : msg + "\nAt " + token);
		this.token = token;
	}

	public ParseException(String msg) {
		super(msg);
	}

	public Token getToken() {
		return token;
	}
}
