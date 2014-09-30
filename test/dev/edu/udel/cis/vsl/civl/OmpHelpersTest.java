package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class OmpHelpersTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "library/omp");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void teams() {
		assertTrue(ui.run("run", filename("teams.cvl")));
	}

	@Test
	public void shared() {
		assertTrue(ui.run("run", filename("shared.cvl")));
	}

	@Test
	public void read() {
		assertTrue(ui.run("run", filename("read.cvl")));
	}

	@Test
	public void write() {
		assertTrue(ui.run("run", filename("write.cvl")));
	}

	@Ignore
	@Test
	public void exp1() {
		assertTrue(ui.run("run", filename("div0.cvl")));
	}
}
