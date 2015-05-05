package edu.udel.cis.vsl.civl.gui.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.civl.config.IF.CIVLConstants;
import edu.udel.cis.vsl.civl.run.IF.CommandLine;
import edu.udel.cis.vsl.civl.run.IF.UserInterface;
import edu.udel.cis.vsl.civl.run.common.CIVLCommand;
import edu.udel.cis.vsl.civl.run.common.NormalCommandLine;
import edu.udel.cis.vsl.civl.run.common.NormalCommandLine.NormalCommandKind;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.gmc.GMCSection;
import edu.udel.cis.vsl.gmc.Option;

/**
 * This class is the container that hold all of the relevant data about a run
 * configuration. The GUI will take this information and save it to a file that
 * can later be parsed and ran.
 * 
 * @author noyes
 * 
 */
public class RunConfigDataNode extends DefaultMutableTreeNode implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the <code>RunConfig</code>.
	 */
	private String name;
	
	//TODO: erase this, we want multiple files not just one
	/**
	 * The selected target <code>CIVL</code> file.
	 */
	private File selectedFile;
	
	/**
	 * The selected target <code>CIVL</code> files.
	 */
	private ArrayList<File> selectedFiles;

	/**
	 * The list of input values for this Run Configuration.
	 */
	private ArrayList<CIVL_Input> inputs;

	/**
	 * Marks whether unsaved changes have been made to the RunConfigDataNode.
	 */
	private boolean changed;

	/**
	 * An array that stores all of the Option values
	 */
	private Object[] values;
	
	/**
	 * A GMC config that is needed for the file
	 */
	private GMCConfiguration gmcConfig;
	
	/**
	 * Is this RunConfigDataNode brand new?(true) Has it been modified in any
	 * way?(false)
	 */
	private boolean brandNew;

	/**
	 * The directory to which the RunConfigDataNode is serialized to.
	 */
	private String serializeDestination;
	
	/**
	 * The NormalCommandKind associated with this RunConfigurationDataNode
	 */
	public NormalCommandKind commandKind;
	
	public boolean tableChanged;

	// Temporary Values of all fields that can be saved to their permanent
	// counterparts.
	// TODO: change to private or possibly delete these
	transient private String temp_name;
	transient private File temp_selectedFile;
	transient private CIVL_Input[] temp_inputs;
	transient private Object[] temp_values;

	// TODO: add documentation to constructor
	public RunConfigDataNode(NormalCommandKind commandKind) {
		SortedMap<String, Option> map = null;
		this.selectedFiles = new ArrayList<File>();
		this.inputs = new ArrayList<CIVL_Input>();
		
		if(commandKind.equals(NormalCommandKind.RUN)){
			map = CIVLCommand.getRunOptions();
			System.out.println(map.values().size());
		}
		
		else if(commandKind.equals(NormalCommandKind.VERIFY)){
			map = CIVLCommand.getVerifyOrCompareOptions();
		}
		
		else if(commandKind.equals(NormalCommandKind.SHOW)){
			map = CIVLCommand.getShowOptions();
		}
		
		
		GMCConfiguration c = new GMCConfiguration(map.values());
		this.setGmcConfig(c);
		this.commandKind = commandKind;

		int size = CIVLConstants.getAllOptions().length;
		this.setValues(new Object[size]); //DEPRECIATED
		this.setChanged(false);
		this.brandNew = true;		
		this.tableChanged = false;
	}
	
	/**
	 * Checks if the RunConfigDataNode has unsaved data and returns true if it
	 * does.
	 * 
	 * @return Whether or not there is unsaved data present in the
	 *         RunConfigDataNode.
	 */
	public boolean checkForUnsavedData() {
		boolean changedData = false;
		if (name != temp_name)
			changedData = true;

		if (selectedFile != temp_selectedFile)
			changedData = true;

		if (values != temp_values)
			changedData = true;

		return changedData;
	}

	/**
	 * Saves the unsaved changes to the RunConfigDataNode, if desired.
	 * 
	 * @param saveConfig
	 *            True if the changes are to be saved, false otherwise.
	 */
	public void saveChanges(boolean saveConfig) {
		/*
		if (saveConfig) {
			if (temp_name != null) {
				name = temp_name;
				this.setUserObject(name);
			}
			if (temp_selectedFile != null)
				selectedFile = temp_selectedFile;
			if (temp_inputs != null)
				inputs = temp_inputs;
			if (temp_values != null)
				values = temp_values;

			changed = false;
		} else {
			temp_name = null;
			temp_selectedFile = null;
			temp_inputs = null;
			temp_values = null;
			changed = false;
			System.out.println("Changes not saved to the config: " + name);
		}
		*/
		if(saveConfig){
			
		}
	}

	/**
	 * Serializes the RunConfigDataNode so that it can be accessed later.
	 */
	public void serialize() {
		try {
			// TODO: make this save in a user-specified location
			FileOutputStream fileOut = new FileOutputStream(
					serializeDestination + "/" + name);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in "
					+ serializeDestination);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public RunConfigDataNode deserialize() {
		RunConfigDataNode config = null;

		try {
			FileInputStream fileIn = new FileInputStream(serializeDestination
					+ "/" + name);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			config = (RunConfigDataNode) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Deserialized RunConfig...");
			System.out.println("Name: " + config.name);
			for(int i = 0; i < config.getSelectedFiles().size(); i++){
				System.out.println("File: " + config.getSelectedFiles().get(i));
			}
			System.out.println("Command: " + config.commandKind);
			return config;

		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("RunConfigDataNode class not found");
			c.printStackTrace();
			return null;
		}
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
	
	public ArrayList<File> getSelectedFiles() {
		return selectedFiles;
	}

	public void setSelectedFiles(ArrayList<File> selectedFiles) {
		this.selectedFiles = selectedFiles;
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

	public ArrayList<CIVL_Input> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<CIVL_Input> inputs) {
		this.inputs = inputs;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public boolean isBrandNew() {
		return brandNew;
	}

	public void setBrandNew(boolean brandNew) {
		this.brandNew = brandNew;

	}

	// Getters/Setters for temporary fields

	public String getTemp_name() {
		return temp_name;
	}

	public void setTemp_name(String temp_name) {
		changed = true;
		this.temp_name = temp_name;
	}

	public File getTemp_selectedFile() {
		return temp_selectedFile;
	}

	public void setTemp_selectedFile(File temp_selectedFile) {
		changed = true;
		this.temp_selectedFile = temp_selectedFile;
	}

	public CIVL_Input[] getTemp_inputs() {
		return temp_inputs;
	}

	public void setTemp_inputs(CIVL_Input[] temp_inputs) {
		changed = true;
		this.temp_inputs = temp_inputs;
	}

	public Object[] getTemp_values() {
		return temp_values;
	}

	public void setTemp_values(Object[] temp_values) {
		changed = true;
		this.temp_values = temp_values;
	}

	public String getSerializeDestination() {
		return serializeDestination;
	}

	public void setSerializeDestination(String serializeDestination) {
		this.serializeDestination = serializeDestination;
	}

	public GMCConfiguration getGmcConfig() {
		return gmcConfig;
	}

	public void setGmcConfig(GMCConfiguration gmcConfig) {
		this.gmcConfig = gmcConfig;
	}
}
