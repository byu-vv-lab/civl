package edu.udel.cis.vsl.civl.civlc.preproc.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.civl.civlc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.civl.civlc.preproc.common.PreprocessorParser.file_return;
import edu.udel.cis.vsl.civl.token.Tokens;
import edu.udel.cis.vsl.civl.token.IF.Macro;
import edu.udel.cis.vsl.civl.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.civlc.util.ANTLRUtils;

/**
 * The class provides easy access to all services exported by the preproc module
 * (a la Façade Pattern). It includes a main method which preprocesses the file
 * and sends result to stdout.
 * 
 * TODO: support -D, i.e., object macros defined at command line
 * 
 * @author Stephen F. Siegel, University of Delaware
 * 
 */
public class CommonPreprocessor implements Preprocessor {

	public final static boolean debug = true;

	/**
	 * Default value for system include path list.
	 */
	private static File[] defaultSystemIncludes = new File[] {};

	/**
	 * Default value for user include path list. Currently, it consists of one
	 * directory, namely, the working directory.
	 */
	private static File[] defaultUserIncludes = new File[] { new File(
			System.getProperty("user.dir")) };

	private File[] systemIncludePaths;

	private File[] userIncludePaths;

	/**
	 * The macros generated from reading the implicit include files.
	 */
	private Map<String, Macro> implicitMacros;

	private TokenFactory tokenFactory = Tokens.newTokenFactory();

	public CommonPreprocessor(File[] systemIncludePaths, File[] userIncludePaths) {
		this.systemIncludePaths = systemIncludePaths;
		this.userIncludePaths = userIncludePaths;
	}

	public CommonPreprocessor() {
		this.systemIncludePaths = defaultSystemIncludes;
		this.userIncludePaths = defaultUserIncludes;
	}

	@Override
	public TokenFactory getTokenFactory() {
		return tokenFactory;
	}

	/**
	 * Read these files to get their macros. Store the macros and use them as
	 * the starting point when parsing any subsequent file.
	 * 
	 * @param implicitIncludes
	 * @throws PreprocessorException
	 */
	@Override
	public void setImplicitIncludes(File[] implicitIncludes)
			throws PreprocessorException {
		this.implicitMacros = new HashMap<String, Macro>();
		for (File file : implicitIncludes) {
			CommonCTokenSource tokenSource = outputTokenSource(file,
					implicitMacros, tokenFactory);
			Token token;

			do {
				token = tokenSource.nextToken();
			} while (token.getType() != PreprocessorLexer.EOF);
		}
	}

	/**
	 * Returns a lexer for the given preprocessor source file. The lexer removes
	 * all occurrences of backslash-newline, scans and tokenizes the input to
	 * produce a sequence of tokens in the preprocessor grammar. It does not
	 * execute the preprocessor directives.
	 * 
	 * @param file
	 *            a preprocessor source file
	 * @return a lexer for the given file
	 * @throws IOException
	 *             if an I/O error occurs while reading the file
	 */
	@Override
	public PreprocessorLexer lexer(File file) throws PreprocessorException {
		try {
			CharStream charStream = new FilteredCharStream(new ANTLRFileStream(
					file.getAbsolutePath()));

			return new PreprocessorLexer(charStream);
		} catch (IOException e) {
			throw new PreprocessorException(
					"I/O error occurred while scanning " + file + ":\n" + e);
		}
	}

	/**
	 * Prints the results of lexical analysis of the source file. Mainly useful
	 * for debugging.
	 * 
	 * @param out
	 *            a PrintStream to which the output should be sent
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if any kind of exception comes from ANTLR's lexer, including
	 *             a file which does not conform lexically to the preprocessor
	 *             grammar
	 */
	@Override
	public void lex(PrintStream out, File file) throws PreprocessorException {
		out.println("Lexical analysis of " + file + ":");
		try {
			PreprocessorLexer lexer = lexer(file);
			int numErrors;

			PreprocessorUtils.printTokenSource(out, lexer);
			numErrors = lexer.getNumberOfSyntaxErrors();

			if (numErrors != 0)
				throw new PreprocessorException(numErrors
						+ " syntax errors occurred while scanning " + file);
		} catch (RuntimeException e) {
			throw new PreprocessorException(e.getMessage());
		}
	}

	/**
	 * Returns a parser for the given preprocessor source file.
	 * 
	 * @param file
	 *            a preprocessor source file
	 * @return a parser for that file
	 * @throws PreprocessorException
	 *             if an I/O error occurs in attempting to open the file
	 */
	@Override
	public PreprocessorParser parser(File file) throws PreprocessorException {
		PreprocessorLexer lexer = lexer(file);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		return new PreprocessorParser(tokenStream);
	}

	/**
	 * Scans and parses the given preprocessor source file, sending a textual
	 * description of the resulting tree to out. This does not execute any
	 * preprocessor directives. It is useful mainly for debugging.
	 * 
	 * @param out
	 *            print stream to which the tree representation of the file will
	 *            be sent
	 * @param file
	 *            a preprocessor source file.
	 * @throws PreprocessorException
	 *             if the file does not conform to the preprocessor grammar, or
	 *             an I/O error occurs in reading the file
	 */
	@Override
	public void parse(PrintStream out, File file) throws PreprocessorException {
		try {
			PreprocessorParser parser = parser(file);
			file_return fileReturn = parser.file();
			int numErrors = parser.getNumberOfSyntaxErrors();
			Tree tree;

			if (numErrors != 0)
				throw new PreprocessorException(numErrors
						+ " syntax errors occurred while scanning " + file);
			out.println("AST for " + file + ":");
			out.flush();
			tree = (Tree) fileReturn.getTree();
			ANTLRUtils.printTree(out, tree);
		} catch (RecognitionException e) {
			throw new PreprocessorException(
					"Recognition error while preprocessing:\n" + e);
		} catch (RuntimeException e) {
			e.printStackTrace(System.err);
			throw new PreprocessorException(e.toString());
		}
	}

	private CommonCTokenSource outputTokenSource(File file,
			Map<String, Macro> macroMap, TokenFactory tokenFactory)
			throws PreprocessorException {
		PreprocessorParser parser = parser(file);
		CommonCTokenSource tokenSource = new CommonCTokenSource(file, parser,
				systemIncludePaths, userIncludePaths, macroMap, tokenFactory);

		return tokenSource;
	}

	/**
	 * Given a preprocessor source file, this returns a Token Source that emits
	 * the tokens resulting from preprocessing the file.
	 * 
	 * @param file
	 * @return a token source for the token resulting from preprocessing the
	 *         file
	 * @throws PreprocessorException
	 *             if an I/O error occurs
	 */
	@Override
	public CommonCTokenSource outputTokenSource(File file)
			throws PreprocessorException {
		Map<String, Macro> macroMap = new HashMap<String, Macro>();

		if (implicitMacros != null)
			macroMap.putAll(implicitMacros);
		return outputTokenSource(file, macroMap, tokenFactory);
	}

	/**
	 * Prints the list of tokens that result from preprocessing the file. One
	 * token is printed per line, along with information on the origin of that
	 * token. Useful mainly for debugging.
	 * 
	 * @param out
	 *            where to send output list
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if the file fails to adhere to the preprocessor grammar, or
	 *             an I/O occurs
	 */
	@Override
	public void printOutputTokens(PrintStream out, File file)
			throws PreprocessorException {
		CommonCTokenSource source = outputTokenSource(file);

		out.println("Post-preprocessing token stream for " + file + ":\n");
		PreprocessorUtils.printTokenSource(out, source);
		out.flush();
	}

	/**
	 * Prints the result of preprocessing the file.
	 * 
	 * @param out
	 *            where to send the output
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if the file fails to adhere to the preprocessor grammar, or
	 *             an I/O occurs
	 */
	@Override
	public void printOutput(PrintStream out, File file)
			throws PreprocessorException {
		CommonCTokenSource source = outputTokenSource(file);

		PreprocessorUtils.sourceTokenSource(out, source);
		out.flush();
	}

	/**
	 * Prints the result of preprocessing the file, but surrounding the output
	 * with some lines to clearly delineate the beginning and ending of the
	 * output, and specifying the file name.
	 * 
	 * @param out
	 *            where to send the output
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if the file fails to adhere to the preprocessor grammar, or
	 *             an I/O occurs
	 */
	@Override
	public void printOutputDebug(PrintStream out, File file)
			throws PreprocessorException {
		out.println("Post-preprocessing output for " + file + ":\n");
		out.println("----------------------------------->");
		printOutput(out, file);
		out.println("<-----------------------------------");
		out.flush();
	}

	/**
	 * Show the processing of the file in stages. Useful for debugging.
	 * 
	 * @param out
	 *            where to print the output
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if there is an I/O error the source file does not conform to
	 *             the preprocessor syntax
	 */
	@Override
	public void debug(PrintStream out, File file) throws PreprocessorException {
		PreprocessorUtils.source(out, file);
		out.println();
		lex(out, file);
		out.println();
		parse(out, file);
		out.println();
		printOutputTokens(out, file);
		out.println();
		printOutputDebug(out, file);
		out.println();
	}

	/**
	 * This main method is just here for simple tests. The real main method is
	 * in the main class, ABC.java.
	 */
	public final static void main(String[] args) throws PreprocessorException {
		String filename = args[0];
		CommonPreprocessor p = new CommonPreprocessor();
		File file = new File(filename);

		if (debug)
			p.debug(System.out, file);
		else
			p.printOutput(System.out, file);
	}
}
