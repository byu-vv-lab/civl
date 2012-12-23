package edu.udel.cis.vsl.civl.civlc.preproc.common;

import java.io.File;

import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.civl.token.IF.Formation;

/**
 * This class would be better named CppSourceFileInfo or something like that.
 * 
 * A class which records information related to each source file that is parsed
 * by the C preprocessor.
 * 
 * @author siegel
 * 
 */
public class PreprocessorSourceFileInfo {

	private Formation history;

	private PreprocessorParser parser;

	private Tree tree;

	private Tree position;

	public PreprocessorSourceFileInfo(Formation history,
			PreprocessorParser parser, Tree tree, Tree position) {
		this.history = history;
		this.parser = parser;
		this.tree = tree;
		this.position = position;
	}

	public Formation getIncludeHistory() {
		return history;
	}

	public File getFile() {
		return history.getLastFile();
	}

	public PreprocessorParser getParser() {
		return parser;
	}

	public Tree getTree() {
		return tree;
	}

	public Tree getPosition() {
		return position;
	}

	public void setPosition(Tree position) {
		this.position = position;
	}
}
