package edu.udel.cis.vsl.civl.gui.common;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

import edu.udel.cis.vsl.civl.run.common.NormalCommandLine.NormalCommandKind;

/**
 * This class is basic wrapper class that represents the node that corresponds to a certain command(RUN, SHOW etc.)
 * @author StevenNoyes
 *
 */
public class CommandNode extends DefaultMutableTreeNode implements Serializable {
	public NormalCommandKind commandKind;
	
	public CommandNode(String name, NormalCommandKind commandKind){
		super(name);
		this.commandKind = commandKind;
	}
}
