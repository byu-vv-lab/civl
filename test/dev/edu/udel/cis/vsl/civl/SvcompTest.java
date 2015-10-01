package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class SvcompTest {
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples", "pthread"),
			"svcomp");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods *************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	// Yes
	// None
	@Test
	public void sync01_true() throws ABCException {
		assertTrue(ui.run("verify", "-svcomp",
				filename("sync01_true-unreach-call.i")));
	}

	// reorder_2_false-unreach-call.i
	@Test
	public void reorder_2_false() throws ABCException {
		assertFalse(ui.run("verify", "-svcomp -showProgram",
				filename("reorder_2_false-unreach-call.i")));
	}

	// sigma_false-unreach-call.i
	@Test
	public void sigma_false() throws ABCException {
		assertFalse(ui.run("verify", "-svcomp",
				filename("sigma_false-unreach-call.i")));
	}

}
