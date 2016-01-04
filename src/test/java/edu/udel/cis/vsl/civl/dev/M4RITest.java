package edu.udel.cis.vsl.civl.dev;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import org.junit.experimental.categories.Category;

@Category(edu.udel.cis.vsl.civl.DevTests.class)
public class M4RITest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File(new File("examples"), "omp"), "m4ri");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void m4ri() {
		assertTrue(ui.run("show", "-showProgram", "-ompNoSimplify", "-input_omp_thread_max=1",
				"-userIncludePath=examples/omp/m4ri/m4ri:examples/omp/m4ri",
				"-sysIncludePath=examples/omp/m4ri/m4ri:examples/omp/m4ri",
				filename("tests/test_colswap.c")));
		
	}
	
	
}
