package edu.udel.cis.vsl.civl.civlc.preproc.IF;

import java.io.File;

/**
 * Factory for producing a Preprocessor and CTokens.
 * 
 * @author siegel
 * 
 */
public interface PreprocessorFactory {

	// /**
	// * Creates new instance by copying fields from old one. The two histories
	// * are set to the given arguments. Both must be non-null.
	// *
	// * @param token
	// * any kind of Token
	// */
	// CToken newCToken(Token token, ExpansionHistory expansionHistory,
	// IncludeHistory includeHistory);
	//
	// /**
	// * Makes a new CToken with trivial expansion history. The include history
	// * must be non-null.
	// *
	// * @param token
	// * any instance of Token whose fields are used to initialize the
	// * new token's fields. The expansion and include histories of the
	// * given token, if present, are ignored.
	// * @param includeHistory
	// * the include history to be used for the new token
	// */
	// CToken newCToken(Token token, IncludeHistory includeHistory);

	/**
	 * Produces a new Preprocessor with the given list of paths for the system
	 * and user includes. The Preprocessor can be used to preprocess multiple
	 * source files.
	 * 
	 * The protocol for searching for included files is a little complicated.
	 * 
	 * 
	 * @param systemIncludePaths
	 *            the sequence of directories to search for angle bracket
	 *            includes
	 * @param userIncludePaths
	 *            the sequence of directories to search for quote includes
	 * @return a new Preprocessor
	 */
	Preprocessor newPreprocessor(File[] systemIncludePaths,
			File[] userIncludePaths);

	/**
	 * Returns a new Preprocessor using the default include paths.
	 * 
	 * @return a new Preprocessor
	 */
	Preprocessor newPreprocessor();

}
