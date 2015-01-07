package edu.udel.cis.vsl.civl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.FrontEnd;
import edu.udel.cis.vsl.abc.TranslationTask;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorRuntimeException;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class Cuda2CIVLTransformTest {

	// TOOD: add cuda.h to ABC's text/include. It should not
	// contain implementation code, only header code. It should
	// not have any CIVL code.
	@Ignore
	@Test
	public void sum() throws ABCException, IOException {
		TranslationTask config = null;
		FrontEnd frontEnd;

		config = new TranslationTask(Language.CIVL_C, new File(new File(
				new File("examples"), "cuda"), "sum.cu"));
		config.setPrettyPrint(true);
		config.setVerbose(false);
		frontEnd = new FrontEnd();
		frontEnd.showTranslation(config);
		config.getOut().close();
	}

	// TODO: is this necessary?
	public static void main(String[] args) {
		TranslationTask config = null;
		FrontEnd frontEnd;
		PrintStream err = System.err;

		// TODO: use Java's File constructor to create the File
		// path in a platform-independent way (i.e., instead of using
		// '/')
		config = new TranslationTask(Language.CIVL_C, new File(
				"../CIVL/examples/translation/cuda", "sum.cu"));
		config.setPrettyPrint(true);
		config.setVerbose(false);
		frontEnd = new FrontEnd();
		try {
			frontEnd.showTranslation(config);
		} catch (PreprocessorException e) {
			err.println(e.getMessage());
			err.flush();
			System.exit(2);
		} catch (PreprocessorRuntimeException e) {
			err.println(e.getMessage());
			err.flush();
			System.exit(2);
		} catch (ParseException e) {
			err.println(e.getMessage());
			err.flush();
			System.exit(3);
		} catch (SyntaxException e) {
			err.println(e.getMessage());
			err.flush();
			System.exit(4);
		} catch (IOException e) {
			err.println(e.getMessage());
			err.flush();
			System.exit(5);
		}
		config.getOut().close();
	}
}
