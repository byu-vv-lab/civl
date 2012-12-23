package edu.udel.cis.vsl.civl.token.IF;

import java.io.File;

import org.antlr.runtime.Token;

/**
 * A post-preprocessor token.
 * 
 * 
 * 
 * @author siegel
 * 
 */
public interface CToken extends Token {

	/**
	 * A record of how this token was formed, through macro expansions, token
	 * concatenation, include directives, etc.
	 * 
	 * @return history of token formation
	 */
	Formation getFormation();

	/**
	 * Returns the file that actually contains the token.
	 * 
	 * @return the source file
	 */
	File getSourceFile();

	/**
	 * Sets the next token in the sequence. The next token, if non-null, should
	 * have index one greater than the index of this token.
	 * 
	 * @param nextToken
	 */
	void setNext(CToken nextToken);

	/**
	 * Returns the "next" token, which, if non-null, should have index one
	 * greater than this token's index. Could be null.
	 * 
	 * @return the next token
	 */
	CToken getNext();

	/**
	 * Sets the inex of this token.
	 * 
	 * @param index
	 */
	void setIndex(int index);

	/**
	 * Returns the index of this token (among its siblings). Tokens are indexed
	 * from 0.
	 * 
	 * @return the index of this token
	 */
	int getIndex();

	/**
	 * Is this token expandable? This is used only for identifiers that could be
	 * macro invocations. The macro expansion procedure is complex and at a
	 * certain phase, a macro identifier becomes non-expandable. It is mostly to
	 * support self-referential macros.
	 * 
	 * Initially, every token is expandable.
	 * 
	 * @return value of expandable bit
	 */
	boolean isExpandable();

	/**
	 * Sets this token's expandable bit to false.
	 */
	void makeNonExpandable();

}
