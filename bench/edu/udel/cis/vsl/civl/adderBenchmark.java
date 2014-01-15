package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class adderBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=7: 12 seconds
		// -inputB=8: 39 seconds
		ui.run("verify -inputB=8 examples/concurrency/adder.cvl");
	}

}
