package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class LanguageFeaturesTest {

	private static File rootDir = new File("examples/languageFeatures");
	private PrintStream out = System.out;

	@Test
	public void testMalloc() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "malloc.cvl");
		boolean result = CIVL.check(true, file, out);
		assertFalse(result);
	}

	@Test
	public void testAssume() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "assume.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testArrays() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "arrays.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testChoose() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "choose.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testMinimal() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "minimal.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testPointers() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "pointers.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testSideEffects() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "sideEffects.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testFor() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "for.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testCompare() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "compare.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testEmptyBlock() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "emptyWhen.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testCast() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "cast.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testSelf() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "self.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testNonbooleanCondition() throws IOException,
			PreprocessorException, ParseException, SyntaxException {
		File file = new File(rootDir, "nonbooleanCondition.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testStruct() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "struct.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testArrayPointer() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "arrayPointer.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testScoping() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "scoping.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testStructArray() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "structArray.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testStructStruct() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "structStruct.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testDynamicStruct() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "dynamicStruct.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}

	@Test
	public void testSizeOf() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "sizeOf.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
	@Test
	public void testDuffs() throws IOException, PreprocessorException,
			ParseException, SyntaxException {
		File file = new File(rootDir, "duffs.cvl");
		boolean result = CIVL.check(file, out);
		assertFalse(result);
	}
	
}