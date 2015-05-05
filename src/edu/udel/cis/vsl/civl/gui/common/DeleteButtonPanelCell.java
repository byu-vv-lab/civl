package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class DeleteButtonPanelCell extends AbstractCellEditor implements
		TableCellEditor, TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	DeleteButtonPanel dbp;

	public DeleteButtonPanelCell(CIVLTable table) {
		dbp = new DeleteButtonPanel(table);
	}

	@Override
	public Object getCellEditorValue() {
		return "Delete";
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return dbp;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return dbp;
	}
}
