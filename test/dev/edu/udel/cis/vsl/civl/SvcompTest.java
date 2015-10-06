package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
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
		assertFalse(ui.run("verify",
				"-svcomp -showProgram=false -input_gen_argc=1",
				filename("reorder_2_false-unreach-call.i")));
	}

	// sigma_false-unreach-call.i
	@Test
	public void sigma_false() throws ABCException {
		assertFalse(ui.run("verify", "-svcomp",
				filename("sigma_false-unreach-call.i")));
	}

	// singleton_false-unreach-call.i
	@Test
	public void singleton_false() throws ABCException {
		assertFalse(ui.run("verify", "-svcomp -checkMemoryLeak=false",
				filename("singleton_false-unreach-call.i")));
	}

	// scull_true-unreach-call.i
	@Ignore
	@Test
	public void scull_true() throws ABCException {
		assertTrue(ui.run("verify", "-svcomp",
				filename("scull_true-unreach-call.i")));
	}

	// sssc12_variant_true-unreach-call.i
	@Test
	public void sssc12_variant_true() throws ABCException {
		// assertTrue(ui.run("verify", "-svcomp -procBound=3 -showProgram",
		// filename("sssc12_variant_true-unreach-call.i")));
		ui.run("replay", "-svcomp -procBound=3 -showTransitions",
				filename("sssc12_variant_true-unreach-call.i"));
	}

	@Test
	public void intPointer() throws ABCException {
		assertTrue(ui.run("verify", "-svcomp -input_svcomp_scale=5",
				filename("intPointer.c")));
	}

	// stack_longest_true-unreach-call.i
	@Test
	public void stack_longest_true() throws ABCException {
		assertTrue(ui.run("verify",
				"-svcomp -showProgram -input_svcomp_scale=5",
				filename("stack_longest_true-unreach-call.i")));
	}

	// mix000_power.oepc_false-unreach-call.i
	@Test
	public void mix000_power() throws ABCException {
		assertFalse(ui.run("verify",
				"-svcomp -showProgram -input_svcomp_scale=5",
				filename("mix000_power.oepc_false-unreach-call.i")));
	}

	// mix000_power.opt_false-unreach-call.i
	@Test
	public void mix000_power_opt() throws ABCException {
		assertFalse(ui.run("verify",
				"-svcomp -showProgram -input_svcomp_scale=5",
				filename("mix000_power.opt_false-unreach-call.i")));
	}
}
