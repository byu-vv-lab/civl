package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.IF.UserInterface;

public class ArithmeticTest {

	/* *************************** Static Fields *************************** */

	private static File rootDir = new File(new File("examples"), "arithmetic");

	private static UserInterface ui = new UserInterface();

	/* *************************** Helper Methods ************************** */

	private void check(String name) {
		assertTrue(ui.run("verify", filename(name)));
	}

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	/* **************************** Test Methods *************************** */

	@Test
	public void algebra() {
		check("algebra.cvl");
	}

	@Test
	public void assoc() {
		assertTrue(ui.run("verify", "-inputB=10", filename("assoc.cvl")));
	}

	@Test
	public void derivative() {
		check("derivative.cvl");
	}

	@Test
	public void diffusion() {
		check("diffusion.cvl");
	}

	@Test
	public void division() {
		check("division.cvl");
	}

	@Test
	public void divisionBad() {
		assertFalse(ui.run("verify", filename("divisionBad.cvl")));
	}

	@Test
	public void laplace() {
		check("laplace.cvl");
	}

	@Test
	public void matmat() {
		assertTrue(ui.run("verify", "-inputBOUND=3", "-simplify=false",
				filename("matmat.cvl")));
	}

	@Test
	public void matmatBad() {
		assertFalse(ui
				.run("verify", "-inputBOUND=3", filename("matmatBad.cvl")));
	}

	@Test
	public void mean() {
		assertTrue(ui.run("verify", "-inputB=10", filename("mean.cvl")));
	}

	@Test
	public void meanBad() {
		assertFalse(ui.run("verify", "-inputB=10", "-min",
				filename("meanBad.cvl")));
	}

	@Test
	public void multiplicationInLoopCondition() {
		assertTrue(ui.run("verify", "-echo",
				filename("multiplicationInLoopCondition.cvl")));
	}

	@Test
	public void math() {
		assertTrue(ui.run("verify", "-echo", filename("math.cvl"))); 
	}

	@Test
	public void exp1() {
		assertFalse(ui.run("run", filename("div0.cvl")));
	}

	@Test
	public void sqrt() {
		assertTrue(ui.run("verify", "-showStates",filename("sqrt.cvl")));
		assertTrue(ui.run("verify", "-DMATH_ELABORATE_ASSUMPTIONS",filename("sqrt.cvl")));
		assertFalse(ui.run("verify", "-DMATH_NO_ASSUMPTIONS", filename("sqrt.cvl")));
	}

	@Test
	public void sqrtBad1() {
		assertFalse(ui.run("verify", filename("sqrtBad1.cvl")));
		assertFalse(ui.run("verify", "-DMATH_ELABORATE_ASSUMPTIONS", filename("sqrtBad1.cvl")));
	}

	@Test
	public void sqrtBad2() {
		assertFalse(ui.run("verify", filename("sqrtBad2.cvl")));
		assertFalse(ui.run("verify", "-DMATH_ELABORATE_ASSUMPTIONS", filename("sqrtBad2.cvl")));
	}

	@Test
	public void quadratic1() {
		assertTrue(ui.run("verify", filename("quadratic1.cvl")));
	}

	@Test
	public void quadratic2() {
		assertTrue(ui.run("verify", filename("quadratic2.cvl")));
	}
}
