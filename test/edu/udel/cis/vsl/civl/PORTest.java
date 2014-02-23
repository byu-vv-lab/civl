package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class PORTest {

	/* **************************** Static Fields **************************** */

	private static File rootDir = new File(new File("examples"), "por");

	private static UserInterface ui = new UserInterface();

	/* **************************** Helper Methods *************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/****************************** Test Methods *****************************/

	@Test
	public void atomic0() {
		assertFalse(ui.run("verify", filename("atomic0.cvl")));
	}
	
	@Test
	public void atomic1() {
		assertFalse(ui.run("verify", filename("atomic1.cvl")));
	}

	@Test
	public void trade3() {
		assertFalse(ui.run("verify", filename("trade3.cvl")));
	}
	
	@Test
	public void trade4() {
		assertFalse(ui.run("verify", filename("trade4.cvl")));
	}
	
	
}