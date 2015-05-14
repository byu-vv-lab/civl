package edu.udel.cis.vsl.civl.gui.common;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BrowseButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//implements ActionListener, MouseListener {
	/**
	 * The action that the button will perform
	 */
	private AbstractAction act;
	
	private PathChooser pc;
	
	private String pathString;
	
	public String optName;
	
	private GUI_revamp parent;
	 	
	public BrowseButton(String name, GUI_revamp parent){
		super(name);
		this.optName = name;
		this.parent = parent;
		//this.optName = "";
		setPathString("");
		initAction();
	}
	
	private void initAction(){
		act = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				pc = new PathChooser(pathString, optName, parent);
				setPathString(pc.format());
				//System.out.println(pathString);
				repaint();
			}
		};
		this.setAction(act);
	}

	public String getPathString() {
		return pathString;
	}

	public void setPathString(String pathString) {
		this.pathString = pathString;
	}
}
