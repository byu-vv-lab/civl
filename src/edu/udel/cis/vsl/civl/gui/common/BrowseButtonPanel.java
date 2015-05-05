package edu.udel.cis.vsl.civl.gui.common;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BrowseButtonPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	
	private String pathString;	
	
	public BrowseButtonPanel(){
		button = new BrowseButton("Browse");
		button.setText("Browse");
		pathString = "";
		
		this.setLayout(new BorderLayout());
		
		this.add(button, BorderLayout.NORTH);
	}


	public String getPathString() {
		return pathString;
	}


	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

}
