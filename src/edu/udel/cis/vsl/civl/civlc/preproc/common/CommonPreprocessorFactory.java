package edu.udel.cis.vsl.civl.civlc.preproc.common;

import java.io.File;

import edu.udel.cis.vsl.civl.civlc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorFactory;

public class CommonPreprocessorFactory implements PreprocessorFactory {

	// @Override
	// public CToken newCToken(Token token, ExpansionHistory expansionHistory,
	// IncludeHistory includeHistory) {
	// return new CommonCToken(token, expansionHistory, includeHistory);
	// }
	//
	// @Override
	// public CToken newCToken(Token token, IncludeHistory includeHistory) {
	// return new CommonCToken(token, includeHistory);
	// }

	@Override
	public Preprocessor newPreprocessor(File[] systemIncludePaths,
			File[] userIncludePaths) {
		return new CommonPreprocessor(systemIncludePaths, userIncludePaths);
	}

	@Override
	public Preprocessor newPreprocessor() {
		return new CommonPreprocessor();
	}

}
