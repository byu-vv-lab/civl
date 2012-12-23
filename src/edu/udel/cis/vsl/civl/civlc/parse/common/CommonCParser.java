package edu.udel.cis.vsl.civl.civlc.parse.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.civl.civlc.parse.IF.CParser;
import edu.udel.cis.vsl.civl.civlc.parse.IF.ParseException;
import edu.udel.cis.vsl.civl.civlc.parse.IF.RuntimeParseException;
import edu.udel.cis.vsl.civl.civlc.preproc.Preprocess;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.CTokenSource;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorFactory;
import edu.udel.cis.vsl.civl.token.IF.CToken;
import edu.udel.cis.vsl.civl.token.IF.Source;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.token.common.CommonCToken;
import edu.udel.cis.vsl.civl.civlc.util.ANTLRUtils;

public class CommonCParser implements CParser {

	private Preprocessor preprocessor;

	private CTokenSource tokenSource;

	private TokenStream stream;

	private CivlCParser parser;

	private TokenFactory tokenFactory;

	public CommonCParser(Preprocessor preprocessor, CTokenSource tokenSource) {
		this.preprocessor = preprocessor;
		this.tokenSource = tokenSource;
		this.tokenFactory = tokenSource.getTokenFactory();
		this.stream = new CommonTokenStream(tokenSource);
		this.parser = new CivlCParser(stream);
	}

	public CommonCParser(Preprocessor preprocessor, File file)
			throws PreprocessorException {
		this(preprocessor, preprocessor.outputTokenSource(file));
	}

	@Override
	public Preprocessor getPreprocessor() {
		return preprocessor;
	}

	@Override
	public CTokenSource getTokenSource() {
		return tokenSource;
	}

	@Override
	public TokenStream getTokenStream() {
		return stream;
	}

	@Override
	public CivlCParser getParser() {
		return parser;
	}

	/**
	 * Returns the ANTLR CommonTree resulting from parsing the input, after some
	 * "post-processing" has been done to the tree to fill in some fields.
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Override
	public CommonTree getTree() throws ParseException {
		try {
			CommonTree tree = (CommonTree) parser.translationUnit().getTree();

			postProcessTree(tree);
			return tree;
		} catch (RecognitionException e) {
			throw new ParseException(e.getMessage(), e.token);
		} catch (RuntimeParseException e) {
			throw new ParseException(e.getMessage(), e.getToken());
		}
	}

	/**
	 * Sets some fields of the tokens that occur in the tree.
	 * 
	 * I know ANTLR is supposed to do this but I don't think it does it right.
	 * First, the tokenIndexes are not always what I expect. For some reason,
	 * ANTLR's CommonTokenStream sets the index of the last token (EOF) to be
	 * one higher that it should be, so there is a gap in the indexes between
	 * the penultimate token and the last token. I introduced my own "index"
	 * field to CToken (which extends CommonToken) and set it myself correctly.
	 * 
	 * Second, ANTLR is supposed to find the range of tokens spanned by each
	 * node in the tree (by examining all descendants of the node). However:
	 * first, the code that does this uses ANTLR's tokenIndex, and I want to do
	 * it using my index. Second, the ANTLR code is only correct under the
	 * assumption that the token indices are non-decreasing as child index
	 * increases, i.e., the token index of child i is less than or equal to that
	 * of child i+1, for all i, for all nodes. (Hence it only has to examine the
	 * first and last child.) There is no reason that assumption has to hold. So
	 * I compute this correctly (and using CToken indexes) and re-set the
	 * "tokenStartIndex" and "tokenStopIndex" fields of each tree node.
	 * 
	 * @param tree
	 */
	private static void postProcessTree(CommonTree tree) {
		initPostProcess(tree);
		completePostProcess(tree);
	}

	/**
	 * Mark all nodes as "not yet visited"---indicating by the magic number -999
	 * for tokenStartIndex and tokenStopIndex.
	 * 
	 * @param tree
	 */
	private static void initPostProcess(CommonTree tree) {
		int numChildren = tree.getChildCount();

		tree.setTokenStartIndex(-999);
		tree.setTokenStopIndex(-999);
		for (int i = 0; i < numChildren; i++)
			initPostProcess((CommonTree) tree.getChild(i));
	}

	/**
	 * Compute the actual start and stop index of each node in the tree.
	 * 
	 * If there are no CToken occurring in a node or any of its descendances the
	 * start and stop index of that node will both be set to -1.
	 * 
	 * @param tree
	 */
	private static void completePostProcess(CommonTree tree) {
		if (tree.getTokenStartIndex() != -999)
			return;
		else {
			int numChildren = tree.getChildCount();
			CommonToken token = (CommonToken) tree.getToken();
			int min, max;

			if (token instanceof CommonCToken) {
				min = max = ((CommonCToken) token).getIndex();
			} else {
				min = max = -1;
			}
			for (int i = 0; i < numChildren; i++) {
				CommonTree child = (CommonTree) tree.getChild(i);
				int childMin, childMax;

				completePostProcess(child);
				childMin = child.getTokenStartIndex();
				childMax = child.getTokenStopIndex();
				if (childMin >= 0 && (min < 0 || childMin < min))
					min = childMin;
				if (childMax >= 0 && (max < 0 || childMax > max))
					max = childMax;
			}
			tree.setTokenStartIndex(min);
			tree.setTokenStopIndex(max);
		}
	}

	/**
	 * Combines all steps of the parsing process, starting from a File and
	 * returning a post-processed tree.
	 */
	public static CommonTree parse(Preprocessor preprocessor, File file)
			throws PreprocessorException, ParseException {
		CommonCParser treeParser = new CommonCParser(preprocessor, file);

		return treeParser.getTree();
	}

	public static void printTree(PrintStream out, CommonTree tree) {
		ANTLRUtils.printTree(out, tree);
	}

	public static void printTree(PrintStream out, Preprocessor preprocessor,
			File file) throws PreprocessorException, ParseException {
		printTree(out, parse(preprocessor, file));
	}

	@Override
	public Source source(CommonTree tree) {
		CToken firstToken = null, lastToken = null;
		CTokenSource tokenSource = getTokenSource();

		int start = tree.getTokenStartIndex();
		int stop = tree.getTokenStopIndex();

		if (start >= 0)
			firstToken = tokenSource.getToken(start);
		if (stop >= 0)
			lastToken = tokenSource.getToken(stop);
		if (firstToken == null)
			if (lastToken == null)
				throw new IllegalArgumentException(
						"No tokens associated to tree node " + tree);
			else
				firstToken = lastToken;
		else if (lastToken == null)
			lastToken = firstToken;
		return tokenFactory.newSource(firstToken, lastToken);
	}

	/**
	 * A simple main method that should just be used for simple tests. Use the
	 * main method in class ABC to get all the bells and whistles.
	 * 
	 * @param args
	 * @throws PreprocessorException
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void main(String[] args) throws PreprocessorException,
			ParseException, IOException {
		String filename = args[0];
		File file = new File(filename);
		PreprocessorFactory preprocessorFactory = Preprocess
				.newPreprocessorFactory();
		Preprocessor preprocessor = preprocessorFactory.newPreprocessor();

		printTree(System.out, preprocessor, file);
	}

	@Override
	public SyntaxException newSyntaxException(String message, CommonTree tree) {
		return tokenFactory.newSyntaxException(message, source(tree));
	}

}
