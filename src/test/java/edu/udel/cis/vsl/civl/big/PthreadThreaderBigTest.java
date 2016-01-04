package edu.udel.cis.vsl.civl.big;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import org.junit.experimental.categories.Category;

@Category(edu.udel.cis.vsl.civl.BigTests.class)
public class PthreadThreaderBigTest {
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples", "pthread"),
			"threader");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods *************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void qrcu_true() throws ABCException {
		assertTrue(ui.run("verify", "-svcomp16", filename("qrcu_true.c")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
