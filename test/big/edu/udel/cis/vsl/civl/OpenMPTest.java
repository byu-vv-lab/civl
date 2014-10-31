package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class OpenMPTest {
	
	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "translation/openmp");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	
	@Test
	public void threadPrivate() {
		assertTrue(ui.run("verify", "-inputTHREADS_BOUND=2", "-inputN_BOUND=4",
				"-enablePrintf=false", filename("fig4.98-threadprivate.cvl")));
	}

}
