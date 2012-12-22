package edu.udel.cis.vsl.civl.civlc.token.IF;


public interface StringLiteral {

	public enum StringKind {
		CHAR, WCHAR, CHAR16, CHAR32, UTF_8
	}

	public int getNumCharacters();

	public ExecutionCharacter getCharacter(int index);

	public StringKind getStringKind();

}
