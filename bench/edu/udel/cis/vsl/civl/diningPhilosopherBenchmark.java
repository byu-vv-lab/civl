package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class diningPhilosopherBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=9: 19 seconds
		// -inputB=10: 56 seconds
		ui.run("verify -inputB=9 examples/concurrency/dining.cvl");
	}

}
