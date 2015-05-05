package edu.udel.cis.vsl.civl.gui.common;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IntegerPanel extends JPanel{
	
	private JSpinner spinner;
	
	private int val;
	
	public IntegerPanel(){
		super();
		SpinnerModel model = new SpinnerNumberModel();
		setSpinner(new JSpinner(model));
		spinner.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				val = (int) spinner.getValue();
			}
			
		});
		
		spinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				val = (int) spinner.getValue();
				
			}
			
		});
		
		this.add(spinner);
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	/**
	 * Updates the data contained in this <code>RadioPanel</code>.
	 * 
	 * @param selectedVal
	 *            The newly selected value.
	 * @param isSelected
	 *            Is the component selected?
	 * @param table
	 *            The table that we are updating data in.
	 */
	public void updateData(int val, boolean isSelected, JTable table) {
		this.val = val;
		this.spinner.setValue(val);
		/*
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getSelectionForeground());
		}
		*/
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}
	
}
