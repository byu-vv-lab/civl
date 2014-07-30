package edu.udel.cis.vsl.civl.gui.common;

import javax.swing.table.*;

import java.util.*;

import javax.swing.*;

/**
 * This class is used to ensure that the first column of the
 * <code>CIVLTable</code>(JTable) is not editable, as we do not want users to be
 * able to change the option names in the table where selected options are
 * displayed. In addition to this, it contains a custom cell renderer and a
 * custom cell editor to override undesirable JTable behavior.
 * 
 * @author noyes
 * 
 */
public class CIVLTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5345179673948036576L;

	/**
	 * The custom renderer to be used for cells that have a boolean value.
	 */
	private BooleanCellRenderer bcr;
	
	/**
	 * The custom editor to be used for cells that have a boolean value.
	 */
	private BooleanCellEditor bce;
		
	private int[] editableCols;

	CIVLTable(int[] editableCols) {
		super();
		bcr = new BooleanCellRenderer();
		bce = new BooleanCellEditor();
		this.editableCols = editableCols;
	}

	public TableCellEditor getCellEditor(int row, int column) {
		Object value = super.getValueAt(row, column);
		if (value instanceof Boolean) {
			return bce;
		}
		if (value instanceof Date) {
			return getDefaultEditor(Date.class);
		}
		if (value instanceof Integer) {
			return getDefaultEditor(Integer.class);
		}
		// no special case
		return super.getCellEditor(row, column);
	}

	public TableCellRenderer getCellRenderer(int row, int column) {
		Object value = super.getValueAt(row, column);
		if (value instanceof Boolean) {
			bcr.setDefaultValue((Boolean) value);
			return bcr;
		}
		if (value instanceof Date) {
			return getDefaultRenderer(Date.class);
		}
		if (value instanceof Integer) {
			return getDefaultRenderer(Integer.class);
		}
		// no special case
		return super.getCellRenderer(row, column);
	}

	
	public boolean isCellEditable(int row, int col) {		
		boolean found = false;
		for(int i = 0; i<editableCols.length; i++){
			if(col == editableCols[i]){
				found = true;
				break;
			}
		}
		
		return found;
	}

}