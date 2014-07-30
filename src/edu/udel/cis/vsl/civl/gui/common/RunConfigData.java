package edu.udel.cis.vsl.civl.gui.common;

import java.io.File;

import edu.udel.cis.vsl.gmc.Option;

/**
 * This class is the container that hold all of the relevant data about a run
 * configuration. The GUI will take this information and save it to a file that
 * can later be parsed and ran.
 * 
 * @author noyes
 * 
 */
public class RunConfigData {
	/**
	 * The name of the <code>RunConfig</code>.
	 */
	private String name;

	/**
	 * The command type for this <code>RunConfig</code>.
	 */
	private CIVL_Command command;

	/**
	 * The options the that have been chosen for this <code>RunConfig</code>.
	 */
	private Option[] options;

	/**
	 * The selected target <code>CIVL</code> file.
	 */
	private File selectedFile;

	public RunConfigData(String name, CIVL_Command command, Option[] options,
			File selectedFile) {
		this.setName(name);
		this.setCommand(command);
		this.setOptions(options);
		this.setSelectedFile(selectedFile);
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public Option[] getOptions() {
		return options;
	}

	public void setOptions(Option[] options) {
		this.options = options;
	}

	public CIVL_Command getCommand() {
		return command;
	}

	public void setCommand(CIVL_Command command) {
		this.command = command;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
