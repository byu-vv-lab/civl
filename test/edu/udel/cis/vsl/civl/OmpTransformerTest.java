package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import edu.udel.cis.vsl.abc.ABC;
import edu.udel.cis.vsl.abc.Activator;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.civl.run.UserInterface;
import edu.udel.cis.vsl.civl.transform.CIVLTransform;

public class OmpTransformerTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "omp");

	private static UserInterface ui = new UserInterface();

	private File[] systemIncludes, userIncludes;

	private PrintStream out = System.out;

	private File root = new File(new File("examples"), "omp");

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	private void check(String filenameRoot) throws ABCException, IOException {
		Activator frontEnd;
		Program program;

		this.systemIncludes = new File[0];
		this.userIncludes = new File[0];
		frontEnd = ABC.activator(new File(root, filenameRoot + ".c"),
				systemIncludes, userIncludes, Language.CIVL_C);
		program = frontEnd.showTranslation(out);

		CIVLTransform.applyTransformer(program, CIVLTransform.OMP_PRAGMA,
				new ArrayList<String>(0), frontEnd.getASTBuilder());
		out.println("======== After applying OpenMP Pragma Transformer ========");
		frontEnd.printProgram(out, program);

		CIVLTransform.applyTransformer(program, CIVLTransform.OMP,
				new ArrayList<String>(0), frontEnd.getASTBuilder());
		out.println("======== After applying OpenMP to CIVL Transformer ========");
		frontEnd.printProgram(out, program);

		program.applyTransformer("prune");
		out.println("======== After applying Pruner ========");
		frontEnd.printProgram(out, program);

		program.applyTransformer("sef");
		out.println("======== After applying Side Effect Remover ========");
		frontEnd.printProgram(out, program);
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void dotProduct_critical1() throws ABCException, IOException {
		assertTrue(ui.run("parse", "-echo", filename("dotProduct_critical.c")));
	}

	@Test
	public void nested() throws ABCException, IOException {
		assertTrue(ui.run("parse", "-echo", filename("nested.c"),
				"-input__argc=2"));
	}

	@Test
	public void dotProduct_critical() throws ABCException, IOException {
		check("dotProduct_critical");
	}

	@Test
	public void dotProduct_orphan() throws ABCException, IOException {
		check("dotProduct_orphan");
	}

	@Test
	public void dotProduct1() throws ABCException, IOException {
		check("dotProduct1");
	}

	@Test
	public void matProduct1() throws ABCException, IOException {
		check("matProduct1");
	}

	@Test
	public void matProduct2() throws ABCException, IOException {
		check("matProduct2");
	}

	@Test
	public void raceCond1() throws ABCException, IOException {
		check("raceCond1");
	}

	@Test
	public void raceCond2() throws ABCException, IOException {
		check("raceCond2");
	}

	@Test
	public void vecAdd_deadlock() throws ABCException, IOException {
		check("vecAdd_deadlock");
	}

	@Test
	public void vecAdd_fix() throws ABCException, IOException {
		check("vecAdd_fix");
	}

	@Test
	public void fig310_mxv_omp() throws ABCException, IOException {
		check("fig3.10-mxv-omp");
	}

	@Test
	public void fig498_threadprivate() throws ABCException, IOException {
		check("fig4.98-threadprivate");
	}

	@Test
	public void parallelfor() throws ABCException, IOException {
		check("parallelfor");
	}
}
