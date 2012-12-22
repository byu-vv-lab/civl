package edu.udel.cis.vsl.civl.civlc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.civl.civlc.util.ANTLRUtils;
import edu.udel.cis.vsl.civl.civlc.parse.Parse;
import edu.udel.cis.vsl.civl.civlc.parse.IF.CParser;
import edu.udel.cis.vsl.civl.civlc.parse.IF.ParseException;
import edu.udel.cis.vsl.civl.civlc.preproc.Preprocess;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.civl.civlc.preproc.IF.PreprocessorFactory;
import edu.udel.cis.vsl.civl.civlc.token.IF.SyntaxException;

public class CIVLC {

	// TODO:
	// add -D support. Need to create a token with "source" the command line.
	// may treat command line as (virtual) file called "commandline"?

	public static void main(String[] args) throws PreprocessorException,
			ParseException, SyntaxException, FileNotFoundException {
		String infileName = null;
		String outfileName = null;
		// the following are updated by -I
		ArrayList<File> systemIncludeList = new ArrayList<File>();
		// the following are updated by -iquote
		ArrayList<File> userIncludeList = new ArrayList<File>();
		PreprocessorFactory preprocessorFactory;
		Preprocessor preprocessor;
		File infile;
		PrintStream out;
		File[] systemIncludes, userIncludes;
		boolean preprocOnly = false;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];

			if (arg.startsWith("-o")) {
				String name;

				if (arg.length() == 2) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -o");
					name = args[i];
				} else {
					name = arg.substring(2);
				}
				if (outfileName == null)
					outfileName = name;
				else
					throw new IllegalArgumentException(
							"More than one use of -o");
			} else if (arg.startsWith("-I")) {
				String name;

				if (arg.length() == 2) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -I");
					name = args[i];
				} else {
					name = arg.substring(2);
				}
				systemIncludeList.add(new File(name));
			} else if (arg.startsWith("-iquote")) {
				String name;

				if (arg.length() == "-iquote".length()) {
					i++;
					if (i >= args.length)
						throw new IllegalArgumentException(
								"Filename must follow -iquote");
					name = args[i];
				} else {
					name = arg.substring("-iquote".length());
				}
				userIncludeList.add(new File(name));
			} else if (arg.equals("-E")) {
				preprocOnly = true;
			} else if (arg.startsWith("-")) {
				throw new IllegalArgumentException(
						"Unknown command line option: " + arg);
			} else {
				if (infileName == null)
					infileName = arg;
				else
					throw new IllegalArgumentException(
							"More than one input file specified (previous was "
									+ infileName + "): " + arg);
			}
		}
		if (infileName == null)
			throw new IllegalArgumentException("No input file specified");
		infile = new File(infileName);
		userIncludes = userIncludeList.toArray(new File[0]);
		systemIncludes = systemIncludeList.toArray(new File[0]);
		if (outfileName == null)
			out = System.out;
		else
			out = new PrintStream(new File(outfileName));
		preprocessorFactory = Preprocess.newPreprocessorFactory();
		preprocessor = preprocessorFactory.newPreprocessor(systemIncludes,
				userIncludes);
		if (preprocOnly) {
			preprocessor.printOutput(out, infile);
		} else {
			CParser parser = Parse.newCParser(preprocessor, infile);
			CommonTree tree = parser.getTree();
			
			ANTLRUtils.printTree(out, tree);
			out.println();
			out.flush();
		}
	}
}
