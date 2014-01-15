package edu.udel.cis.vsl.civl;

import edu.udel.cis.vsl.civl.run.UserInterface;

public class messagePassingBenchmark {
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		// -inputNPROCS=5 -simplify=false: 17 seconds
		// -inputNPROCS=6 -simplify=false: 68 seconds
		ui.run("verify -inputNPROCS=5 -simplify=false examples/messagePassing/ring.cvl");
	}

}
