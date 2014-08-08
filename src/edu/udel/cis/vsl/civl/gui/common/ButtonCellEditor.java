package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonCellEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	boolean cellEditingStopped;
	private JButton button;
	private JTable parentTable;
	private Action action;
	private boolean isButtonColumnEditor;

	public ButtonCellEditor(JTable parentTable) {
		cellEditingStopped = false;
		button = new JButton("Default");
		button.setText("Default");
		this.parentTable = parentTable;
		
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}

		});
		
		this.parentTable.addMouseListener(this);
	}

	public void setAction(Map<String, Action> map) {
		action = map.get("defaultize");
		button.setAction(action);
	}

	@Override
	public Object getCellEditorValue() {
		if (button.isSelected())
			return true;
		else
			return false;
	}

	// TODO: FIX STOP EDITING ISSUE!!!!!
	// If you drag the mouse off of a button and out of the cell, editing
	// wont stop and the user cannot do anything else with the table until
	// editing ceases

	// TODO: FIX BUTTON ENTANGLEMENT
	// radio buttons from different cells are acting in a group if focus
	// switches into a different cell's radio button directly

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		button.setText("Default");
		return button;
	}

	@Override
	public boolean stopCellEditing() {
		return cellEditingStopped;
	}

	/**
	 * The button has been pressed. Stop editing and invoke the custom Action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = parentTable.convertRowIndexToModel(parentTable
				.getEditingRow());
		fireEditingStopped();
		ActionEvent event = new ActionEvent(parentTable,
				ActionEvent.ACTION_PERFORMED, "" + row);
		action.actionPerformed(event);
	}

	/**
	 * When the mouse is pressed the editor is invoked. If you then then drag
	 * the mouse to another cell before releasing it, the editor is still
	 * active. Make sure editing is stopped when the mouse is released.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (parentTable.isEditing() && parentTable.getCellEditor() == this){
			isButtonColumnEditor = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isButtonColumnEditor && parentTable.isEditing())
			parentTable.getCellEditor().stopCellEditing();
			
		isButtonColumnEditor = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
