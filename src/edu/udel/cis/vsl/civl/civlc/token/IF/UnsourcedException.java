package edu.udel.cis.vsl.civl.civlc.token.IF;

/**
 * An exception for which the source (in the source program being compiled) is
 * unknown. The expectation is that this exception will be caught internally by
 * a method lower on the call stack which does know the source, and that method
 * will construct a SyntaxException from this exception, specifying the source.
 * 
 * @author siegel
 * 
 */
public class UnsourcedException extends Exception {

	/**
	 * Eclipse made me do it
	 */
	private static final long serialVersionUID = 1L;

	public UnsourcedException(String message) {
		super(message);
	}

}
