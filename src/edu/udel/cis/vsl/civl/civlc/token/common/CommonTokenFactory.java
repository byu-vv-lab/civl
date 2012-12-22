package edu.udel.cis.vsl.civl.civlc.token.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.civl.civlc.token.IF.CToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.CharacterToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.Concatenation;
import edu.udel.cis.vsl.civl.civlc.token.IF.ExecutionCharacter;
import edu.udel.cis.vsl.civl.civlc.token.IF.ExecutionCharacter.CharacterKind;
import edu.udel.cis.vsl.civl.civlc.token.IF.Formation;
import edu.udel.cis.vsl.civl.civlc.token.IF.FunctionMacro;
import edu.udel.cis.vsl.civl.civlc.token.IF.Inclusion;
import edu.udel.cis.vsl.civl.civlc.token.IF.Macro;
import edu.udel.cis.vsl.civl.civlc.token.IF.MacroExpansion;
import edu.udel.cis.vsl.civl.civlc.token.IF.ObjectMacro;
import edu.udel.cis.vsl.civl.civlc.token.IF.Source;
import edu.udel.cis.vsl.civl.civlc.token.IF.StringLiteral;
import edu.udel.cis.vsl.civl.civlc.token.IF.StringToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.civlc.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.civlc.token.IF.UnsourcedException;

public class CommonTokenFactory implements TokenFactory {

	private CommonCharacterFactory characterFactory;

	private CommonSourceFactory sourceFactory;

	public CommonTokenFactory() {
		characterFactory = new CommonCharacterFactory(this);
		sourceFactory = new CommonSourceFactory();
	}

	@Override
	public CToken newCToken(Token token, Formation formation) {
		return new CommonCToken(token, formation);
	}

	@Override
	public CToken newCToken(int type, Formation formation) {
		return new CommonCToken(type, formation);
	}

	@Override
	public Concatenation newConcatenation(List<CToken> tokens) {
		return new CommonConcatenation(new ArrayList<CToken>(tokens));
	}

	@Override
	public Inclusion newInclusion(File file, Token includeToken) {
		return new CommonInclusion(file, includeToken);
	}

	@Override
	public Inclusion newInclusion(File file) {
		return new CommonInclusion(file);
	}

	@Override
	public ExecutionCharacter executionCharacter(CharacterKind kind,
			int codePoint, char[] characters) {
		return characterFactory.executionCharacter(kind, codePoint, characters);
	}

	@Override
	public CharacterToken characterToken(CToken token) throws SyntaxException {
		return characterFactory.characterToken(token);
	}

	/**
	 * 
	 * @param type
	 *            usually PreprocessorParser.STRING_LITERAL
	 * @return
	 * @throws SyntaxException
	 */
	@Override
	public StringToken newStringToken(CToken token) throws SyntaxException {
		StringLiteral data = characterFactory.stringLiteral(token);

		return new CommonStringToken(token, token.getFormation(), data);
	}

	/**
	 * Precondition: tokens has length at least 2.
	 */
	@Override
	public StringToken newStringToken(List<CToken> tokens)
			throws SyntaxException {
		int type = tokens.get(0).getType();
		CommonStringLiteral data = characterFactory.stringLiteral(tokens);
		Concatenation concatenation = newConcatenation(tokens);
		CommonStringToken result = new CommonStringToken(type, concatenation,
				data);

		return result;
	}

	@Override
	public Source newSource(CToken token) {
		return sourceFactory.newSource(token);
	}

	@Override
	public Source newSource(CToken first, CToken last) {
		return sourceFactory.newSource(first, last);
	}

	@Override
	public Source join(Source source, CToken token) {
		return sourceFactory.join(source, token);
	}

	@Override
	public Source join(Source source1, Source source2) {
		return sourceFactory.join(source1, source2);
	}

	@Override
	public SyntaxException newSyntaxException(String message, Source source) {
		return new SyntaxException(message, source);
	}

	@Override
	public SyntaxException newSyntaxException(UnsourcedException e,
			Source source) {
		return new SyntaxException(e, source);
	}

	@Override
	public UnsourcedException newUnsourcedException(String message) {
		return new UnsourcedException(message);
	}

	@Override
	public SyntaxException newSyntaxException(String message, CToken token) {
		return newSyntaxException(message, newSource(token));
	}

	@Override
	public SyntaxException newSyntaxException(UnsourcedException e, CToken token) {
		return newSyntaxException(e, newSource(token));
	}

	@Override
	public ObjectMacro newObjectMacro(Tree definitionNode, File file) {
		return new CommonObjectMacro(definitionNode, file);
	}

	@Override
	public FunctionMacro newFunctionMacro(Tree definitionNode, File file) {
		return new CommonFunctionMacro(definitionNode, file);
	}

	@Override
	public MacroExpansion newMacroExpansion(CToken startToken, Macro macro,
			int index) {
		return new CommonMacroExpansion(startToken, macro, index);
	}

}
