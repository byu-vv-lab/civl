package edu.udel.cis.vsl.civl.civlc.preproc.IF;

import java.util.Map;

import org.antlr.runtime.TokenSource;

import edu.udel.cis.vsl.civl.civlc.token.IF.CToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.Macro;
import edu.udel.cis.vsl.civl.civlc.token.IF.TokenFactory;

/**
 * Extends ANTLR's TokenSource interface but adding some additional
 * functionality: getting the macro information, and methods to get the number
 * of tokens produced so far and to retrieve any token produced so far by index.
 * 
 * Here are the methods specified in ANTLR's TokenSource interface:
 * 
 * <pre>
 * * Return a Token object from your input stream (usually a CharStream).
 * * Do not fail/return upon lexing error; keep chewing on the characters
 * * until you get a good one; errors are not passed through to the parser.
 * 	public Token nextToken();
 * 
 *  * Where are you getting tokens from? normally the implication will simply
 *  * ask lexers input stream.
 * 	public String getSourceName();
 * </pre>
 * 
 * @author siegel
 * 
 */
public interface CTokenSource extends TokenSource {

	/**
	 * Returns a map in which a key is the name of a macro (object or function)
	 * and the value associated to that key is the Macro object.
	 * 
	 * @return current macro map
	 */
	Map<String, Macro> getMacroMap();

	/**
	 * The number of tokens produced by this token source so far.
	 * 
	 * @return number of tokens produced at this time
	 */
	int getNumTokens();

	/**
	 * Returns the index-th token produced (indexed from 0).
	 * 
	 * @param index
	 *            an integer in the range [0,numTokens-1]
	 * @return the index-th token produced
	 */
	CToken getToken(int index);

	TokenFactory getTokenFactory();

}
