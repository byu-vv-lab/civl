package edu.udel.cis.vsl.civl.run.common;

import java.io.Serializable;

import edu.udel.cis.vsl.civl.run.IF.CommandLine;
import edu.udel.cis.vsl.gmc.GMCConfiguration;

public abstract class BaseCommandLine implements CommandLine, Serializable {
	protected String commandString;
	protected CommandLineKind commandlineKind;
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

}
