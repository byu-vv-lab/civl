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

import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableCellEditor;

public class BooleanCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 1L;
	private JRadioButton rb_true;
	private JRadioButton rb_false;
	private JPanel radioPanel;
	boolean cellEditingStopped;
	private ButtonGroup group;

	public BooleanCellEditor() {
		rb_true = new JRadioButton("true");
		rb_false = new JRadioButton("false");
		radioPanel = new JPanel();
		group = new ButtonGroup();
		group.add(rb_true);
		group.add(rb_false);
		cellEditingStopped = true;

	}

	@Override
	public Object getCellEditorValue() {
		if (rb_true.isSelected())
			return true;
		else
			return false;
	}

	// TODO: FIX STOP EDITING ISSUE!!!!!
	// TODO: FIX BUTTON ENTANGLEMENT
	// buttons from different cells are acting in a group if focus switches into
	// a new cell into the radio button directly
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		rb_true.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					fireEditingStopped();
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					fireEditingStopped();
				}
			}
		});

		rb_false.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					fireEditingStopped();

				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					fireEditingStopped();
				}
			}
		});

		radioPanel.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				fireEditingStopped();

			}

			@Override
			public void focusLost(FocusEvent e) {
				fireEditingStopped();
			}

		});

		radioPanel.add(rb_true);
		radioPanel.add(rb_false);
		return radioPanel;
	}

	@Override
	public boolean stopCellEditing() {
		return cellEditingStopped;
	}

}
