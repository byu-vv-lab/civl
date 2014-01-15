package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class blockAdderBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputB=10 -inputW=4: 27 seconds
		ui.run("verify -inputB=10 -inputW=4 examples/concurrency/blockAdder.cvl");
	}

}
