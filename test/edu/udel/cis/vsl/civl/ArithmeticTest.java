package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class ArithmeticTest {

	private static File rootDir = new File("examples/arithmetic");
	private PrintStream out = System.out;

	@Test
	public void testDiffusion() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "diffusion_seq.cvl");
		boolean result = CIVL.check(true, file, out);
		assertFalse(result);
	}

	@Test
	public void testMatmat() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "matmat.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testAssoc() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "assoc.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testAlgebra() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "algebra.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

}