package edu.udel.cis.vsl.civl.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;

public class BrowseButton extends JButton { /**
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
	
	/**
	 * The table this button is in.
	 */
	private JTable table;
	
	public BrowseButton(String name){
		super(name);
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
				pc = new PathChooser(pathString);
				setPathString(pc.save());
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
