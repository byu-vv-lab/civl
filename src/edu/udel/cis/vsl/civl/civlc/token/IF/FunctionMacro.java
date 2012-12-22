package edu.udel.cis.vsl.civl.civlc.token.IF;

import org.antlr.runtime.Token;


public interface FunctionMacro extends Macro {

	int getNumFormals();

	Token getFormal(int index);

	/**
	 * Given i, 0<=i<n, where n is the number of replacement tokens, let t be
	 * the i-th replacement token. Returns -1 if t is not an identifier equal to
	 * one of the formal parameter identifiers. Otherwise, returns the index of
	 * that formal parameter. This is to faciliate substitution of actuals for
	 * formals.
	 * 
	 * @param i
	 *            integer in [0,numReplacementTokens)
	 * @return -1 or index of matching formal parameter in [0,numFormals)
	 */
	int getReplacementFormalIndex(int i);

}
