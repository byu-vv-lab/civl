package edu.udel.cis.vsl.civl.civlc.parse.IF;

import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.civl.civlc.parse.common.CivlCParser;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.CTokenSource;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.token.IF.Source;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

public interface CParser {

	Preprocessor getPreprocessor();

	CTokenSource getTokenSource();

	TokenStream getTokenStream();

	CivlCParser getParser();

	/**
	 * Returns the ANTLR CommonTree resulting from parsing the input, after some
	 * "post-processing" has been done to the tree to fill in some fields.
	 * 
	 * @return
	 * @throws ParseException
	 */
	CommonTree getTree() throws ParseException;

	/**
	 * Given a node in the parse tree, return a source object for it.
	 * 
	 * @param tree
	 *            node in tree returned by method getTree()
	 * @return a source object describing the origin of that node in the source
	 *         code
	 */
	Source source(CommonTree tree);

	SyntaxException newSyntaxException(String message, CommonTree tree);

}
