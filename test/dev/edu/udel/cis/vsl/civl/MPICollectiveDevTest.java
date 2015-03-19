package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class MPICollectiveDevTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(
			new File(new File("examples"), "mpi"), "collective");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void bcast_bug1() {
		assertFalse(ui.run("verify -input_NPROCS=2", filename("bcast_bug1.c")));
	}

	@Test
	public void bcast_bug2() {
		assertFalse(ui.run("verify -input_NPROCS=2", filename("bcast_bug2.c")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
