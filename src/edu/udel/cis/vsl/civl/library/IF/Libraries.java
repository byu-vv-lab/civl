package edu.udel.cis.vsl.civl.library.IF;

import edu.udel.cis.vsl.civl.library.common.CommonLibraryLoader;

public class Libraries {
	public static LibraryLoader newLibraryLoader(){
		return new CommonLibraryLoader();
	}
}
