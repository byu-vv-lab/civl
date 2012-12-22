package edu.udel.cis.vsl.civl.civlc.token.IF;

import java.io.File;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

/**
 * Utility class providing static methods dealing with Token objects.
 * 
 * @author siegel
 * 
 */
public class TokenUtils {

	/**
	 * The maximum number of tokens that will be printed when summarizing a
	 * range of tokens. If the number of tokens exceeds this bound, the ellipsis
	 * will be used in the summary.
	 */
	public final static int summaryBound = 10;

	/**
	 * A utility function to extract the filename, line number, and character
	 * index of a token of any type, and return a string representation of this
	 * in a consistent way.
	 * 
	 * @param token
	 *            any instance of Token
	 * @return string explaining where the token came from
	 */
	public static String location(Token token) {
		String filename = getShortFilename(token);
		int line = token.getLine();
		int pos = token.getCharPositionInLine();

		return filename + " " + line + "." + pos;
	}

	public static String getShortFilename(Token token) {
		String filename;
		int separatorIndex;

		if (token instanceof CToken) {
			CToken ppToken = (CToken) token;
			File file = ppToken.getSourceFile();

			filename = file.getName();
		} else {
			CharStream stream = token.getInputStream();

			if (stream == null)
				filename = "<unknown file>";
			else
				filename = stream.getSourceName();
		}
		separatorIndex = filename.lastIndexOf(File.pathSeparatorChar);
		if (separatorIndex >= 0 && separatorIndex < filename.length() - 1)
			filename = filename.substring(separatorIndex + 1);
		return filename;
	}

	public static String summarizeRangeLocation(CToken first, CToken last) {
		String result;
		String filename1 = getShortFilename(first);
		String filename2 = getShortFilename(last);
		int line1 = first.getLine();
		int pos1 = first.getCharPositionInLine();
		String endPosition;
		int line2, pos2;
		CToken next = last.getNext();

		if (next != null) {
			int line3 = next.getLine();
			int pos3 = next.getCharPositionInLine();

			if (pos3 == 0) {
				line2 = line3 - 1;
				if (line2 == last.getLine()) {
					pos2 = last.getCharPositionInLine()
							+ last.getText().length();
				} else {
					pos2 = -1;
				}
			} else {
				line2 = line3;
				pos2 = pos3;
			}
		} else {
			line2 = last.getLine();
			pos2 = last.getCharPositionInLine() + last.getText().length();
		}
		if (pos2 >= 0) {
			endPosition = line2 + "." + pos2;
		} else {
			endPosition = line2 + ".EOL";
		}
		if (filename1.equals(filename2)) {
			if (line1 == line2) {
				if (pos1 == pos2)
					result = filename1 + ":" + line1 + "." + pos1;
				else
					result = filename1 + ":" + line1 + "." + pos1 + "-" + pos2;
			} else {
				result = filename1 + ":" + line1 + "." + pos1 + "-"
						+ endPosition;
			}
		} else {
			result = filename1 + ":" + line1 + "." + pos1 + "-" + filename2
					+ ":" + endPosition;
		}
		return result;
	}

	public static String summarizeRange(CToken first, CToken last) {
		String result = summarizeRangeLocation(first, last);
		String excerpt = "";
		int tokenCount = 0;
		CToken token = first;

		while (token != null && token != last && tokenCount < summaryBound - 1) {
			excerpt += token.getText();
			token = token.getNext();
			tokenCount++;
		}
		if (token != null) {
			if (token != last)
				excerpt += " ... ";
			excerpt += last.getText();
		}
		excerpt = quoteText(excerpt);
		result = result + " " + excerpt;
		return result;
	}

	/**
	 * A utility function to return the text of a token surrounded by double
	 * quotes, with newlines, returns and tabs replaced by escape sequences.
	 * 
	 * @param token
	 *            any instance of Token
	 * @return the text of the token, nicely formatted, in quotes
	 */
	public static String quotedText(Token token) {
		String txt = token.getText();

		if (txt != null)
			return quoteText(txt);
		return "<no text>";
	}

	private static String quoteText(String text) {
		String txt = text.replaceAll("\n", "\\\\n");

		txt = txt.replaceAll("\r", "\\\\r");
		txt = txt.replaceAll("\t", "\\\\t");
		return "\"" + txt + "\"";
	}

}
