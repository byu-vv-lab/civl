package edu.udel.cis.vsl.civl.gui.common;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DeleteButtonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton button;
	
	public DeleteButtonPanel(CIVLTable table){
		button = new DeleteButton(table);
		button.setText("Delete");
		
		this.setLayout(new BorderLayout());
		
		this.add(button, BorderLayout.NORTH);
	}
}
