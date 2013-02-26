package edu.udel.cis.vsl.civl.transform.IF;

import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

public interface Transformer {

	/**
	 * Apply a transformation to a translation unit.
	 * 
	 * @param unit
	 *            The translation unit being modified.
	 * @throws SyntaxException
	 *             If it encounters an error in the translation unit or an
	 *             unhandled case.
	 */
	public void transform(TranslationUnit unit) throws SyntaxException;

}
