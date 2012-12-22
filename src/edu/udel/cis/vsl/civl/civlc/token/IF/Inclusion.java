package edu.udel.cis.vsl.civl.civlc.token.IF;

import java.io.File;

import org.antlr.runtime.Token;

public interface Inclusion extends Formation {

	/**
	 * Returns the file which was #include-ed.
	 * 
	 * @return the included file
	 */
	File getFile();
	
	/**
	 * Returns the token containing the file name in the include directive. The
	 * token text will have the form "<foo.c>" or "\"foo.c\"". You can get the
	 * line number and so on from this token.
	 * 
	 * @return the token containing the file name from the include directive
	 */
	Token getIncludeToken();



}
