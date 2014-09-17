package edu.udel.cis.vsl.civl.gui.common;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.udel.cis.vsl.civl.config.IF.CIVLConstants;

/**
 * This class is the container that hold all of the relevant data about a run
 * configuration. The GUI will take this information and save it to a file that
 * can later be parsed and ran.
 * 
 * @author noyes
 * 
 */
public class RunConfigDataNode extends DefaultMutableTreeNode {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the <code>RunConfig</code>.
	 */
	private String name;

	/**
	 * The command type for this <code>RunConfig</code>.
	 */
	private CIVL_Command command;

	/**
	 * The selected target <code>CIVL</code> file.
	 */
	private File selectedFile;

	/**
	 * The list of input values for this Run Configuration.
	 */
	private CIVL_Input[] inputs;

	/**
	 * Marks whether unsaved changes have been made to the RunConfigDataNode.
	 */
	private boolean changed;

	/**
	 * An array that stores all of the Option values
	 */
	private Object[] values;

	// Temporary Values of all fields that can be saved to their permanent
	// counterparts.
	public String temp_name;
	public CIVL_Command temp_command;
	public File temp_selectedFile;
	public CIVL_Input[] temp_inputs;
	public Object[] temp_values;

	public RunConfigDataNode(CIVL_Command command) {
		super();
		int size = CIVLConstants.getAllOptions().length;
		this.setValues(new Object[size]);
		this.command = command;
		this.setChanged(false);
	}

	/**
	 * Saves the unsaved changes to the RunConfigDataNode, if desired.
	 * 
	 * @param saveConfig
	 *            True if the changes are to be saved, false otherwise.
	 */
	public void saveChanges(boolean saveConfig) {
		if (saveConfig) {
			name = temp_name;
			command = temp_command;
			selectedFile = temp_selectedFile;
			inputs = temp_inputs;
			values = temp_values;
			changed = false;
		} else {
			name = null;
			command = null;
			selectedFile = null;
			inputs = null;
			values = null;
			changed = false;
			System.out.println("Changes not saved to the config: " + name);
		}
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
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

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public CIVL_Input[] getInputs() {
		return inputs;
	}

	public void setInputs(CIVL_Input[] inputs) {
		this.inputs = inputs;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

}
