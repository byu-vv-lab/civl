package edu.udel.cis.vsl.civl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class ConcurrencyTest {

	private static UserInterface ui = new UserInterface();

	private static File rootDir = new File(new File("examples"), "concurrency");

	private static String filename(String name) {
		return new File(rootDir, name).getPath();
	}

	private PrintStream out = System.out;

	@Test
	public void adder() {
		assertTrue(ui.run("verify", filename("adder.cvl"), "-inputB=5"));
	}

	@Test
	public void adderBad() {
		assertFalse(ui.run("verify", filename("adderBad.cvl"), "-inputB=4",
				"-min"));
		assertFalse(ui.run("replay", filename("adderBad.cvl")));
	}

	@Test
	public void blockAdder() {
		assertTrue(ui.run("verify", "-inputB=6", "-inputW=3",
				filename("blockAdder.cvl")));
	}

	@Test
	public void blockAdderBad() {
		assertFalse(ui.run("verify", "-inputB=6", "-inputW=3",
				filename("blockAdderBad.cvl"), "-min"));
		assertFalse(ui.run("replay", filename("blockAdderBad.cvl")));
	}

	@Test
	public void bank() {
		assertTrue(ui.run("verify", filename("bank.cvl")));
	}

	@Test
	public void barrier() {
		assertTrue(ui.run("verify", "-inputB=4", filename("barrier.cvl")));
	}

	@Test
	public void barrierBad() {
		assertFalse(ui.run("verify", "-min", "-inputB=4",
				filename("barrierBad.cvl")));
		assertFalse(ui.run("replay", filename("barrierBad.cvl"), "-id=0"));
		assertFalse(ui.run("replay", filename("barrierBad.cvl"), "-id=1"));
	}

	@Test
	public void barrier2() {
		assertTrue(ui.run("verify", filename("barrier2.cvl")));
	}

	@Test
	public void dining() {
		assertTrue(ui.run("verify", "-inputB=4", filename("dining.cvl")));
	}

	@Test
	public void diningBad() {
		assertFalse(ui.run("verify", "-inputB=4", filename("diningBad.cvl"),
				"-min"));
		assertFalse(ui.run("replay", filename("diningBad.cvl")));
	}

	@Test
	public void locks() {
		assertFalse(ui.run("verify", filename("locks.cvl")));
	}

	@Test
	public void spawn() {
		assertTrue(ui.run("verify", filename("spawn.cvl")));
	}

	@Test
	public void outOfOrderLocks() {
		assertFalse(ui.run("verify", filename("outOfOrderLocks.cvl")));
	}

}