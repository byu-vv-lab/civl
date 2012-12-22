package edu.udel.cis.vsl.civl.civlc.token.IF;

import org.antlr.runtime.Token;

public interface MacroExpansion extends Formation {

	CToken getStartToken();

	Macro getMacro();

	int getReplacementTokenIndex();

	Token getReplacementToken();

}
