package edu.udel.cis.vsl.civl.civlc.preproc.IF;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;

import edu.udel.cis.vsl.civl.token.IF.TokenFactory;

public interface Preprocessor {

	/**
	 * Read these files to get their macros. Store the macros and use them as
	 * the starting point when parsing any subsequent file.
	 * 
	 * @param implicitIncludes
	 * @throws PreprocessorException
	 */
	void setImplicitIncludes(File[] implicitIncludes)
			throws PreprocessorException;

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
	Lexer lexer(File file) throws PreprocessorException;

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
	void lex(PrintStream out, File file) throws PreprocessorException;

	/**
	 * Returns a parser for the given preprocessor source file.
	 * 
	 * @param file
	 *            a preprocessor source file
	 * @return a parser for that file
	 * @throws PreprocessorException
	 *             if an I/O error occurs in attempting to open the file
	 */
	Parser parser(File file) throws PreprocessorException;

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
	void parse(PrintStream out, File file) throws PreprocessorException;

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
	CTokenSource outputTokenSource(File file) throws PreprocessorException;

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
	void printOutputTokens(PrintStream out, File file)
			throws PreprocessorException;

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
	void printOutput(PrintStream out, File file) throws PreprocessorException;

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
	void printOutputDebug(PrintStream out, File file)
			throws PreprocessorException;

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
	void debug(PrintStream out, File file) throws PreprocessorException;

	TokenFactory getTokenFactory();

}
