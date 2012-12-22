package edu.udel.cis.vsl.civl.civlc.token.common;

import java.util.ArrayList;

import edu.udel.cis.vsl.civl.civlc.token.IF.ExecutionCharacter;
import edu.udel.cis.vsl.civl.civlc.token.IF.StringLiteral;

public class CommonStringLiteral implements StringLiteral {

	private StringKind stringKind;

	private ArrayList<ExecutionCharacter> characters;

	public CommonStringLiteral(StringKind kind,
			ArrayList<ExecutionCharacter> characters) {
		this.stringKind = kind;
		this.characters = characters;
	}

	@Override
	public int getNumCharacters() {
		return characters.size();
	}

	@Override
	public ExecutionCharacter getCharacter(int index) {
		return characters.get(index);
	}

	@Override
	public StringKind getStringKind() {
		return stringKind;
	}

	@Override
	public String toString() {
		return characters.toString();
	}

}
