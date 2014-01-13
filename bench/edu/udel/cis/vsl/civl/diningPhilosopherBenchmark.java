package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class diningPhilosopherBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=10: 27 seconds
		// -inputB=11: 89 seconds
		ui.run("verify -inputB=11 examples/concurrency/dining.cvl");
	}

}
