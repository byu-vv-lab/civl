package edu.udel.cis.vsl.civl.civlc.token;

import edu.udel.cis.vsl.civl.civlc.token.IF.TokenFactory;
import edu.udel.cis.vsl.civl.civlc.token.common.CommonTokenFactory;

public class Tokens {

	public static TokenFactory newTokenFactory() {
		return new CommonTokenFactory();
	}

}
