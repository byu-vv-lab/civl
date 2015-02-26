package edu.udel.cis.vsl.civl.run.common;

import edu.udel.cis.vsl.civl.run.IF.CommandLine;
import edu.udel.cis.vsl.gmc.GMCConfiguration;

public abstract class BaseCommandLine implements CommandLine {
	protected String commandString;
	protected CommandKind commandKind;
	protected CommandKind commandArg;
	protected GMCConfiguration gmcConfig;

	@Override
	public GMCConfiguration gmcConfig() {
		return this.gmcConfig;
	}

	@Override
	public void setGMCConfig(GMCConfiguration config) {
		this.gmcConfig = config;
	}

	@Override
	public void setCommandString(String string) {
		this.commandString = string;
	}

	@Override
	public String getCommandString() {
		return this.commandString;
	}

	@Override
	public void setCommandKind(CommandKind cmd) {
		this.commandKind = cmd;
	}

	@Override
	public CommandKind commandKind() {
		return this.commandKind;
	}

	@Override
	public void setCommandArg(CommandKind arg) {
		this.commandArg = arg;
	}

	@Override
	public CommandKind commandArg() {
		return this.commandArg;
	}

}
