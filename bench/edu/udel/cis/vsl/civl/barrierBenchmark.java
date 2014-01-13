package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class barrierBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=9: 71 seconds
		// -inputB=8: 23 seconds
		ui.run("verify -inputB=9 examples/concurrency/barrier.cvl");
	}

}
