package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.FrontEnd;
import edu.udel.cis.vsl.abc.antlr2ast.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.parse.IF.CParser;
import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.CTokenSource;
import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import edu.udel.cis.vsl.civl.transform.IF.CIVLTransform;

public class OmpTransformerTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "omp");

	private static UserInterface ui = new UserInterface();

	private File[] systemIncludes, userIncludes;

	private PrintStream out = System.out;

	private File root = new File(new File("examples"), "omp");

	// private static List<String> codes = Arrays.asList("prune", "sef");

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/**
	 * tests an OpenMP program by applying the following transformers in
	 * sequence:
	 * <ol>
	 * <li>OpenMP Pragma transformer;</li>
	 * <li>OpenMP to CIVL transformer;</li>
	 * <li>Pruner;</li>
	 * <li>Side Effect Remover.</li>
	 * </ol>
	 * 
	 * @param filenameRoot
	 *            The file name of the OpenMP program (without extension).
	 * @param debug
	 *            The flag to be set for printing.
	 * @throws ABCException
	 * @throws IOException
	 */
	private void check(String filenameRoot, boolean debug) throws ABCException,
			IOException {
		FrontEnd frontEnd = new FrontEnd();
		Program program;
		CIVLConfiguration config = new CIVLConfiguration();
		File file = new File(root, filenameRoot + ".c");
		Preprocessor preprocessor;
		CTokenSource tokens;
		CParser parser;
		ASTBuilder builder;
		AST ast;

		config.setDebug(debug);
		this.systemIncludes = new File[0];
		this.userIncludes = new File[0];
		preprocessor = frontEnd.getPreprocessor(systemIncludes, userIncludes);
		tokens = preprocessor.outputTokenSource(file);
		parser = frontEnd.getParser(tokens);
		builder = frontEnd.getASTBuilder(parser);
		ast = builder.getTranslationUnit();
		program = frontEnd.getProgramFactory(
				frontEnd.getStandardAnalyzer(Language.CIVL_C)).newProgram(ast);
		if (debug)
			frontEnd.printProgram(out, program);
		CIVLTransform.applyTransformer(program, CIVLTransform.OMP_PRAGMA,
				new ArrayList<String>(0), builder, config);
		if (debug) {
			out.println("======== After applying OpenMP Pragma Transformer ========");
			frontEnd.printProgram(out, program);
		}
		CIVLTransform.applyTransformer(program, CIVLTransform.OMP_SIMPLIFY,
				new ArrayList<String>(0), builder, config);
		if (debug) {
			out.println("======== After applying OpenMP Simplifier ========");
			frontEnd.printProgram(out, program);
		}
		program.applyTransformer("prune");
		if (debug) {
			out.println("======== After applying Pruner ========");
			frontEnd.printProgram(out, program);
		}
		program.applyTransformer("sef");
		if (debug) {
			out.println("======== After applying Side Effect Remover ========");
			frontEnd.printProgram(out, program);
		}
	}

	/* **************************** Test Methods *************************** */

	@Ignore
	@Test
	public void dotProduct_critical1() throws ABCException, IOException {
		assertTrue(ui.run("parse", filename("dotProduct_critical.c")));
	}

	@Test
	public void nested() throws ABCException, IOException {
		assertTrue(ui.run("parse", filename("nested.c")));
	}

	@Test
	public void dotProduct_critical() throws ABCException, IOException {
		check("dotProduct_critical", false);
	}

	@Test
	public void dotProduct_orphan() throws ABCException, IOException {
		check("dotProduct_orphan", false);
	}

	@Test
	public void dotProduct1() throws ABCException, IOException {
		check("dotProduct1", false);
	}

	@Test
	public void matProduct1() throws ABCException, IOException {
		check("matProduct1", false);
	}

	@Test
	public void matProduct2() throws ABCException, IOException {
		check("matProduct2", false);
	}

	@Test
	public void raceCond1() throws ABCException, IOException {
		check("raceCond1", false);
	}

	@Test
	public void raceCond2() throws ABCException, IOException {
		check("raceCond2", false);
	}

	@Test
	public void vecAdd_deadlock() throws ABCException, IOException {
		check("vecAdd_deadlock", false);
	}

	@Test
	public void vecAdd_fix() throws ABCException, IOException {
		check("vecAdd_fix", false);
	}

	@Ignore
	@Test
	public void fig310_mxv_omp() throws ABCException, IOException {
		check("fig3.10-mxv-omp", false);
	}

	@Test
	public void fig498_threadprivate() throws ABCException, IOException {
		check("fig4.98-threadprivate", false);
	}

	@Test
	public void parallelfor() throws ABCException, IOException {
		check("parallelfor", false);
	}
}
