package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class MPITranslationTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"),
			"messagePassing");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void ring1() {
		assertTrue(ui.run("verify", filename("ring1.c"), "-input__NPROCS=2",
				"-mpi=true", "-showModel", "-showTransitions"));
	}
}
