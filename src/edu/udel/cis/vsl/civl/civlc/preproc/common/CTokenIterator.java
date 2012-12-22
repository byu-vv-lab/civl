package edu.udel.cis.vsl.civl.civlc.preproc.common;

import java.util.Iterator;

import edu.udel.cis.vsl.civl.civlc.token.IF.CToken;

/**
 * A simple iterator over CTokens that works by following the "next" fields in
 * the tokens.
 * 
 * @author Stephen F. Siegel, University of Delaware
 * 
 */
public class CTokenIterator implements Iterator<CToken> {

	private CToken theNextToken;

	public CTokenIterator(CToken firstToken) {
		theNextToken = firstToken;
	}

	@Override
	public boolean hasNext() {
		return theNextToken != null;
	}

	@Override
	public CToken next() {
		CToken result = theNextToken;

		theNextToken = theNextToken.getNext();
		return result;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Should not happen");
	}

}
