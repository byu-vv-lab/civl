package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DeleteButtonCellRenderer implements TableCellRenderer {
	
	/**
	 * The component that will be rendered by the renderer.
	 */
	private DeleteButtonPanel component;

	public DeleteButtonCellRenderer(CIVLTable table) {
		component = new DeleteButtonPanel(table);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean isInit, int row, int column) {

		return component;
	}
	
}
