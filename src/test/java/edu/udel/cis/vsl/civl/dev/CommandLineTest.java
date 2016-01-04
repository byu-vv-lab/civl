package edu.udel.cis.vsl.civl.dev;

import edu.udel.cis.vsl.civl.CIVL;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(edu.udel.cis.vsl.civl.DevTests.class)
public class CommandLineTest {

	@Test
	public void empty() {
		CIVL.main(new String[0]);
	}
}
