package edu.udel.cis.vsl.civl.run.IF;

import edu.udel.cis.vsl.gmc.GMCConfiguration;

public interface CommandLine {
	public enum CommandLineKind {
		NORMAL, COMPARE
	}

	public enum CommandKind {
		CONFIG, COMPARE, GUI, HELP, REPLAY, RUN, SHOW, VERIFY
	}

	CommandLineKind commandLineKind();

	void setCommandString(String string);

	String getCommandString();

	CommandKind commandKind();

	/**
	 * null unless command kind is HELP.
	 * 
	 * @return
	 */
	CommandKind commandArg();

	void setCommandKind(CommandKind cmd);

	void setCommandArg(CommandKind arg);

	GMCConfiguration gmcConfig();

	void setGMCConfig(GMCConfiguration config);

}
