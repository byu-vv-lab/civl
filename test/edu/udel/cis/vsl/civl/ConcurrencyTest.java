package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.PrintStream;

import org.junit.Test;

public class ConcurrencyTest {

	private static File rootDir = new File("examples/concurrency");

	private PrintStream out = System.out;

	private void test(boolean expected, String filename) {
		File file = new File(rootDir, filename);
		// boolean result = CIVL.verify(true, false, file, out);
		boolean result = CIVL.verify(file, out);

		out.println();
		if (expected)
			assertTrue(result);
		else
			assertFalse(result);
	}

	@Test
	public void adder() {
		test(true, "adder.cvl");
	}

	@Test
	public void locks() {
		test(false, "locks.cvl");
	}

	@Test
	public void spawn() {
		test(true, "spawn.cvl");
	}

	@Test
	public void barier() {
		test(true, "barrier.cvl");
	}

	@Test
	public void barrier2() {
		test(true, "barrier2.cvl");
	}

	@Test
	public void dining() {
		test(true, "dining.cvl");
	}

	@Test
	public void bank() {
		test(true, "bank.cvl");
	}

	@Test
	public void outOfOrderLocks() {
		test(false, "outOfOrderLocks.cvl");
	}

}