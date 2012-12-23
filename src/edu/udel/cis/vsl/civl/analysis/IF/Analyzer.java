package edu.udel.cis.vsl.civl.analysis.IF;

import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

public interface Analyzer {

	void analyze(TranslationUnit unit) throws SyntaxException;

}
