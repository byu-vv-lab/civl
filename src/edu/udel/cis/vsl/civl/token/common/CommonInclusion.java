package edu.udel.cis.vsl.civl.token.common;

import java.io.File;

import org.antlr.runtime.Token;

import edu.udel.cis.vsl.civl.token.IF.Inclusion;

public class CommonInclusion implements Inclusion {

	/**
	 * The file included. Always non-null;
	 */
	private File file;

	/**
	 * The token containing the file name in the include directive that named
	 * the file. Will be null for the original file (which wasn't included from
	 * anything).
	 */
	private Token includeToken;

	public CommonInclusion(File file) {
		assert file != null;
		this.file = file;
		this.includeToken = null;
	}

	public CommonInclusion(File file, Token includeToken) {
		assert file != null;
		this.file = file;
		this.includeToken = includeToken;
	}

	@Override
	public String suffix() {
		if (includeToken != null)
			return " included from " + includeToken;
		else
			return "";
	}

	@Override
	public File getLastFile() {
		return file;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public Token getIncludeToken() {
		return includeToken;
	}

}
