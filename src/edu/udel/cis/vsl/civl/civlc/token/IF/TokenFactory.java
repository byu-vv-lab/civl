package edu.udel.cis.vsl.civl.civlc.token.IF;

import java.io.File;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.civl.civlc.token.IF.ExecutionCharacter.CharacterKind;

public interface TokenFactory {

	// History creation...

	MacroExpansion newMacroExpansion(CToken startToken, Macro macro, int index);

	Concatenation newConcatenation(List<CToken> tokens);

	/**
	 * Inclusion record for original source file.
	 * 
	 * @param file
	 * @return
	 */
	Inclusion newInclusion(File file);

	Inclusion newInclusion(File file, Token includeToken);

	// Basic token creation...

	CToken newCToken(Token token, Formation formation);

	CToken newCToken(int type, Formation formation);

	// Characters and Strings...

	ExecutionCharacter executionCharacter(CharacterKind kind, int codePoint,
			char[] characters);

	CharacterToken characterToken(CToken token) throws SyntaxException;

	StringToken newStringToken(CToken token) throws SyntaxException;

	StringToken newStringToken(List<CToken> tokens) throws SyntaxException;

	// Source objects...

	Source newSource(CToken token);

	Source newSource(CToken first, CToken last);

	Source join(Source source, CToken token);

	Source join(Source source1, Source source2);

	// Exceptions...

	SyntaxException newSyntaxException(String message, Source source);

	SyntaxException newSyntaxException(String message, CToken token);

	SyntaxException newSyntaxException(UnsourcedException e, Source source);

	SyntaxException newSyntaxException(UnsourcedException e, CToken token);

	UnsourcedException newUnsourcedException(String message);

	// Macros...

	ObjectMacro newObjectMacro(Tree definitionNode, File file);

	FunctionMacro newFunctionMacro(Tree definitionNode, File file);

	// Deprecated...

	// Source newSource(CommonTree tree, CTokenSource tokenSource);

	// SyntaxException newSyntaxException(String message, CommonTree tree,
	// CTokenSource tokenSource);

}
