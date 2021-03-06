package edu.udel.cis.vsl.civl.dev;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import org.junit.experimental.categories.Category;

/**
 * Tests involving order of accuracy work by Tim Zirkel.
 * 
 * Some interesting facts: some of the queries never get a conclusive result,
 * but the tests nonetheless succeed. Why?
 * 
 * Some of the queries can be resolved by CVC3, but not by any other prover.
 * 
 * @author zirkel
 *
 */
@Category(edu.udel.cis.vsl.civl.DevTests.class)
public class AccuracyTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "accuracy");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void derivative() {
		assertTrue(ui.run("verify", "-inputnum_elements=5",
				filename("derivative.cvl")));
	}

	@Test
	public void derivativeBad() {
		assertFalse(ui.run("verify", "-inputnum_elements=5",
				filename("derivativeBad.cvl")));
	}

	@Test
	public void secondDerivative() {
		assertTrue(ui.run("verify", "-inputnum_elements=5",
				filename("secondDerivative.cvl")));
	}

	@Test
	public void secondDerivativeBad() {
		assertFalse(ui.run("verify", "-inputnum_elements=5",
				filename("secondDerivativeBad.cvl")));
	}

	@Test
	public void diffusion() {
		assertTrue(ui.run("verify", "-inputn=4", filename("diffusion.cvl")));
	}

	@Test
	public void laplace2d() {
		assertTrue(ui.run("verify", "-inputrows=3", "-inputcols=3",
				filename("laplace2d.cvl")));
	}

	@Test
	public void derivativeBackward() {
		assertTrue(ui.run("verify", "-inputnum_elements=5",
				filename("derivativeBackward.cvl")));
	}

	@Test
	public void upwindFirstOrder() {
		assertTrue(ui.run("verify", "-showProverQueries", "-inputn=4",
				filename("upwindFirstOrder.cvl")));
	}

	@Test
	public void upwindSecondOrder() {
		assertTrue(ui.run("verify", "-inputn=5",
				filename("upwindSecondOrder.cvl")));
	}

	@Test
	public void upwindThirdOrder() {
		assertTrue(ui.run("verify", "-inputn=5",
				filename("upwindSecondOrder.cvl")));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
