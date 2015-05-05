package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BrowseButtonCellRenderer implements TableCellRenderer {
	
	/**
	 * The component that will be rendered by the renderer.
	 */
	private JPanel component;
	
	/**
	 * Differentiates between sysIncludePath and userIncludePath
	 */
	private String optName;

	public BrowseButtonCellRenderer(String optName) {
		this.optName = optName;
		component = new BrowseButtonPanel();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean isInit, int row, int column) {

		return component;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}
	
}
