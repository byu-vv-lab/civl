package edu.udel.cis.vsl.civl.civlc.preproc;

import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorFactory;
import edu.udel.cis.vsl.civl.civlc.preproc.common.CommonPreprocessorFactory;

public class Preprocess {

	public static PreprocessorFactory newPreprocessorFactory() {
		return new CommonPreprocessorFactory();
	}

}
