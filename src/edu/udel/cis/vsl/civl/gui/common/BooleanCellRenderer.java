package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.ButtonGroup;
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
public class BooleanCellRenderer implements TableCellRenderer {
	private Component component;
	private Boolean defaultValue;
	private JRadioButton rb_true;
	private JRadioButton rb_false;
	private ButtonGroup group;
	
	 public BooleanCellRenderer(){
	    	rb_true = new JRadioButton("true", true);
	    	rb_false = new JRadioButton("false", false);
	    	group = new ButtonGroup();
	    	group.add(rb_true);
	    	group.add(rb_false);
	    	defaultValue = false;	    	
	    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		component = new JPanel();
		
		((JPanel)component).add(rb_true);
		((JPanel)component).add(rb_false);
		if(defaultValue){
			rb_true.setSelected(true);
			rb_false.setSelected(false);
		} else if(!defaultValue){
			rb_false.setSelected(true);
			rb_true.setSelected(false);
		}
		return component;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
}
