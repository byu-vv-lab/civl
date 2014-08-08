package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * This class is used to override the default behavior for rendering a boolean
 * value in a JTable. From a JCheckBox(default) to a JComboBox consisting of the
 * boolean values true and false.
 * 
 * @author noyes
 * 
 */
public class ButtonCellRenderer implements TableCellRenderer {
	private Component component;
	private JTable parentTable;
	private Action action;
	
	 public ButtonCellRenderer(JTable parentTable){
		 component = new JButton("Default");
		 	//((JButton) component).setText("Default");
		 	this.parentTable = parentTable;
		 }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return component;
	}
	
	public void setAction(Map<String,Action> map){
		
	}
}
