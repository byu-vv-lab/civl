package edu.udel.cis.vsl.civl.token;

import edu.udel.cis.vsl.civl.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.token.common.CommonTokenFactory;

public class Tokens {

	public static TokenFactory newTokenFactory() {
		return new CommonTokenFactory();
	}

}
