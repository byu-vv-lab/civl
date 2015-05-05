package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class BrowseButtonCellEditor extends AbstractCellEditor implements TableCellEditor {
	/**
	 * The component that will be edited by the editor.
	 */
	private BrowseButtonPanel component;
	
	/**
	 * Differentiates between sysIncludePath and userIncludePath
	 */
	private String optName;

	public BrowseButtonCellEditor(String optName) {
		this.setOptName(optName);
		component = new BrowseButtonPanel();
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		return component;
	}

	@Override
	public Object getCellEditorValue() {
		return component.getPathString();
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}
	
}
