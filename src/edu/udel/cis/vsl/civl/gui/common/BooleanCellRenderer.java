package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.JComboBox;
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
	private Boolean defaultValue = true;

	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		component = new JComboBox<Boolean>();
		((JComboBox<Boolean>) component).addItem(true);
		((JComboBox<Boolean>) component).addItem(false);
		((JComboBox<Boolean>) component).setSelectedItem(defaultValue);
		return component;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
}
