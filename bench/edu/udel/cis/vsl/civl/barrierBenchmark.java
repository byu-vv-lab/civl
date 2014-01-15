package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class barrierBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=7: 26 seconds
		ui.run("verify -inputB=8 examples/concurrency/barrier.cvl");
	}

}
