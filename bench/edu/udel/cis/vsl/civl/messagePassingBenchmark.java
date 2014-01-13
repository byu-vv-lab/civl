package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class messagePassingBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputNPROCS=7 -simplify=false: 56 seconds
		ui.run("verify -inputNPROCS=7 -simplify=false examples/messagePassing/ring.cvl");
	}

}
