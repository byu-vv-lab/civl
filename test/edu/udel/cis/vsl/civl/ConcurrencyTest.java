package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class ConcurrencyTest {

	private static File rootDir = new File("examples/concurrency");
	private PrintStream out = System.out;

	@Test
	public void testAdder() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "adder.cvl");
		boolean result = CIVL.check(true, file, out);
		assertFalse(result);
	}

	@Test
	public void testLocks() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "locks.cvl");
		boolean result = CIVL.check(file, out);
		assertTrue(result);
	}

	@Test
	public void testSpawn() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "spawn.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testBarrier() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "barrier.cvl");
		boolean result = CIVL.check(true, file, out);
		assertFalse(result);
	}

	@Test
	public void testBarrier2() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "barrier2.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testDining() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "dining.cvl");
		boolean result = CIVL.check(file, out);
		assertTrue(result);
	}

}