package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class PthreadTest {
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
	public void pointerProb() throws ABCException {
		assertTrue(ui.run("verify", filename("pointerProb.c")));
	}

}
