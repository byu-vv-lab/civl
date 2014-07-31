package edu.udel.cis.vsl.civl.gui.common;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableCellEditor;

public class BooleanCellEditor extends AbstractCellEditor implements TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Boolean> jComboBox = new JComboBox<Boolean>();
    boolean cellEditingStopped = false;

    @Override
    public Object getCellEditorValue() {
        return jComboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {           
        jComboBox = new JComboBox<Boolean>();
        jComboBox.addItem(true);
        jComboBox.addItem(false);
        jComboBox.setSelectedItem(true);

        jComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fireEditingStopped(); //rare index out of range issue here!!!!
                }
            }
        });
        
        jComboBox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                cellEditingStopped = false;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                cellEditingStopped = true;
                fireEditingCanceled();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        return jComboBox;
    }

    @Override
    public boolean stopCellEditing() {
        return cellEditingStopped;
    }
}
