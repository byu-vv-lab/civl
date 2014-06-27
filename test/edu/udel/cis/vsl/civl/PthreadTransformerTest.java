package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class PthreadTransformerTest {
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"),
			"translation/pthread");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods *************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void bigshot_s_false() throws ABCException {
		assertFalse(ui.run("verify", filename("bigshot_s_false.c"),
				"-enablePrintf=false"));
	}

	@Test
	public void bug1() throws ABCException {
		assertFalse(ui.run("verify", filename("bug1.c"), "-enablePrintf=false"));
	}

	@Test
	public void bug3() throws ABCException {
		assertFalse(ui.run("verify", filename("bug3.c"), "-enablePrintf=false"));
	}

	@Test
	public void bug4() throws ABCException {
		assertFalse(ui.run("verify", filename("bug4.c"), "-enablePrintf=true"));
	}

	@Ignore
	@Test
	public void mpithreads_threads() throws ABCException {
		assertTrue(ui.run("verify", filename("mpithreads_threads.c"),
				"-enablePrintf=false"));
	}
}
