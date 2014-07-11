package edu.udel.cis.vsl.civl.gui.common;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class StartFrame extends JFrame {	
	public boolean gotoOptions;
	
	public StartFrame(boolean visible){
		gotoOptions = false;
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
		setJMenuBar(initMenus());
		setSize(50,50);
	}
	
	public JMenuBar initMenus(){
		JMenu menu = new JMenu("File");
		JMenuBar menuBar = new JMenuBar();
		JMenuItem newRunConfigMenu = new JMenuItem("New Run Configuration");
		JMenuItem openRunConfigMenu = new JMenuItem("Open Run Configuration");		
		openRunConfigMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String examplesPath = "/Users/noyes/Documents/workspace/TestGUI/RunConfigurations";
            	File start = new File(examplesPath);           	
            	final JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(start);                
                //parser for open file and adding to source code window
                chooser.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	File selected = chooser.getSelectedFile();
                    }
                });               
                chooser. showOpenDialog(null);
            }
        });
		
		newRunConfigMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gotoOptions = true;
            }

        });
		
		menu.add(newRunConfigMenu);
		menu.add(openRunConfigMenu);
		menuBar.add(menu);		
		
		return menuBar;
	}
	
	
	public static void main(String[] args) {
		StartFrame launch = new StartFrame(true);

	}

}
