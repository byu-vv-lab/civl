package edu.udel.cis.vsl.civl.token.common;

import edu.udel.cis.vsl.civl.token.IF.CToken;
import edu.udel.cis.vsl.civl.token.IF.Formation;
import edu.udel.cis.vsl.civl.token.IF.StringLiteral;
import edu.udel.cis.vsl.civl.token.IF.StringToken;

public class CommonStringToken extends CommonCToken implements StringToken {

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = 6839260551000953066L;

	private StringLiteral literal;

	public CommonStringToken(int type, Formation formation,
			StringLiteral literal) {
		super(type, formation);
		this.literal = literal;
	}

	public CommonStringToken(CToken token, Formation formation,
			StringLiteral data) {
		super(token, formation);
		this.literal = data;
	}

	@Override
	public StringLiteral getStringLiteral() {
		return literal;
	}

}
