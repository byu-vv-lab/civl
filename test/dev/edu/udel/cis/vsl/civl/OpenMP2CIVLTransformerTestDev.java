package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class OpenMP2CIVLTransformerTestDev {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "omp");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void dotProduct1() {
		assertTrue(ui.run("verify ", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("dotProduct1.c")));
	}
	
	@Test
	public void dotProduct1Simplify() {
		assertTrue(ui.run("verify ",
				"-inputTHREAD_MAX=2", filename("dotProduct1.c")));
	}

	@Test
	public void dotProductCritical() {
		assertTrue(ui.run("verify ", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("dotProduct_critical.c")));
	}
	
	@Test
	public void dotProductCriticalSimplify() {
		assertTrue(ui.run("verify ",
				"-inputTHREAD_MAX=2", filename("dotProduct_critical.c")));
	}

	@Test
	public void matProduct1() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("matProduct1.c")));
	}
	
	@Test
	public void matProduct1Simplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("matProduct1.c")));
	}

	@Test
	public void parallelfor() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("parallelfor.c")));
	}
	
	@Test
	public void parallelforSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("parallelfor.c")));
	}

	@Test
	public void raceCond1() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("raceCond1.c")));
	}
	
	@Test
	public void raceCond1Simplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("raceCond1.c")));
	}
	
	@Test
	public void fft() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("fft_openmp.c")));
	}
	
	@Test
	public void fftSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("fft_openmp.c")));
	}
	
	@Test
	public void poisson() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("poisson_openmp.c")));
	}
	
	@Test
	public void poissonSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("poisson_openmp.c")));
	}
	
	@Test
	public void fig310_mxv_omp() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("fig310-mxv-omp.c")));
	}
	
	@Test
	public void fig310_mxv_ompSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("fig310-mxv-omp.c")));
	}
	
	@Test
	public void pi() {
		assertTrue(ui.run("verify", "-ompNoSimplify",
				"-inputTHREAD_MAX=2", filename("pi.c")));
	}
	
	@Test
	public void piSimplify() {
		assertTrue(ui.run("verify",
				"-inputTHREAD_MAX=2", filename("pi.c")));
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui = null;
		rootDir = null;
	}
}
