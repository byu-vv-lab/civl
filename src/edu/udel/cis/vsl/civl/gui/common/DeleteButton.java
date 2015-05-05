package edu.udel.cis.vsl.civl.gui.common;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteButton extends JButton { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//implements ActionListener, MouseListener {
	/**
	 * The action that the button will perform
	 */
	private AbstractAction act;
		
	
	/**
	 * The table this button is in.
	 */
	private CIVLTable table;
	
	public DeleteButton(CIVLTable table){
		setTable(table);
		initAction();
	}
	
	private void initAction(){
		act = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				final DefaultTableModel currFileModel = (DefaultTableModel) table.getModel();
				int modelRow = table.getSelectedRow();
				currFileModel.removeRow(modelRow);
				System.out.println(currFileModel.getRowCount());
				//setTable(table);
				repaint();
			}
		};
		this.setAction(act);
	}

	public CIVLTable getTable() {
		return table;
	}

	public void setTable(CIVLTable table) {
		this.table = table;
	}
}