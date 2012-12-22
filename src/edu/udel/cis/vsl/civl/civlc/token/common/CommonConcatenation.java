package edu.udel.cis.vsl.civl.civlc.token.common;

import java.io.File;
import java.util.ArrayList;

import edu.udel.cis.vsl.civl.civlc.token.IF.CToken;
import edu.udel.cis.vsl.civl.civlc.token.IF.Concatenation;

public class CommonConcatenation implements Concatenation {

	private ArrayList<CToken> constituents;

	public CommonConcatenation(ArrayList<CToken> constituents) {
		assert constituents != null;
		assert constituents.size() >= 1;
		this.constituents = constituents;
	}

	@Override
	public String suffix() {
		String result = " from concatenation of the following "
				+ getNumConstituents() + " tokens:";

		for (CToken token : constituents)
			result += "\n" + token;
		return result;
	}

	@Override
	public File getLastFile() {
		return constituents.get(0).getSourceFile();
	}

	@Override
	public int getNumConstituents() {
		return constituents.size();
	}

	@Override
	public CToken getConstituent(int index) {
		return constituents.get(index);
	}

}
