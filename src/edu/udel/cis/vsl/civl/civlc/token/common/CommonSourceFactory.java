package edu.udel.cis.vsl.civl.civlc.token.common;

import edu.udel.cis.vsl.civl.civlc.token.IF.CToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.Source;
import edu.udel.cis.vsl.civl.civlc.token.IF.UnsourcedException;

public class CommonSourceFactory {

	public Source newSource(CToken token) {
		return new CommonSource(token, token);
	}

	public Source newSource(CToken first, CToken last) {
		return new CommonSource(first, last);
	}

	// public Source newSource(CommonTree tree, CTokenSource tokenSource) {
	// return new CommonSource(tree, tokenSource);
	// }

	public Source join(Source source, CToken token) {
		int firstIndex, lastIndex, index = token.getTokenIndex();
		CToken first, last;

		if (index < 0)
			return source;
		first = source.getFirstToken();
		last = source.getLastToken();
		firstIndex = source.getFirstToken().getTokenIndex();
		lastIndex = source.getLastToken().getTokenIndex();
		if (index < firstIndex)
			return newSource(token, last);
		else if (index > lastIndex)
			return newSource(first, token);
		return source;
	}

	public Source join(Source source1, Source source2) {
		CToken first1 = source1.getFirstToken();
		CToken last1 = source1.getLastToken();
		CToken first2 = source2.getFirstToken();
		CToken last2 = source2.getLastToken();
		int firstIndex1 = first1.getIndex();
		int lastIndex1 = last1.getIndex();
		int firstIndex2 = first2.getIndex();
		int lastIndex2 = last2.getIndex();
		CToken newFirst = (firstIndex1 < firstIndex2 ? first1 : first2);
		CToken newLast = (lastIndex1 > lastIndex2 ? last1 : last2);

		return new CommonSource(newFirst, newLast);
	}


	// public SyntaxException newSyntaxException(String message, CommonTree
	// tree,
	// CTokenSource tokenSource) {
	// Source source = newSource(tree, tokenSource);
	//
	// return new SyntaxException(message, source);
	// }

	public UnsourcedException newUnsourcedException(String message) {
		return new UnsourcedException(message);
	}

	
}
