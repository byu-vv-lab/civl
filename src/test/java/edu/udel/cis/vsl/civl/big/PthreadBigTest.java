package edu.udel.cis.vsl.civl.big;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import org.junit.experimental.categories.Category;

@Category(edu.udel.cis.vsl.civl.BigTests.class)
public class PthreadBigTest {
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples", "pthread"),
			"esbmc");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods *************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void fib_bench_longer_true() throws ABCException {
		assertTrue(ui.run("verify", "-svcomp16", "-inputNUM=6",
				filename("fib_bench_longer_true-unreach-call.c")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
