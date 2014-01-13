package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class adderBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=10: 64 seconds
		ui.run("verify -inputB=10 examples/concurrency/adder.cvl");
	}

}
