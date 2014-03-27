package edu.udel.cis.vsl.civl.gui;
	

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.udel.cis.vsl.civl.kripke.StateManager;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.immutable.ImmutableDynamicScope;
import edu.udel.cis.vsl.civl.state.immutable.ImmutableState;

public class CIVL_GUI extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;
    private JTextPane textView;
    private JScrollPane treeView;
    private JPanel left, right;
	
	/**
	 * Constructor for the CIVL GUI
	 */
	public CIVL_GUI(ImmutableState state, StateManager manager) {
       
		//initialize components of this CIVL GUI
        initComponents(state);
        
        //Make the GUI visible
        pack();
        setVisible(true);
        
    }
	
	/**
	 * Initialize the components of the CIVL GUI
	 * 
	 * @param state
	 * @param manager
	 */
	private void initComponents(ImmutableState state) {
        
		//Make the GUI split vertically down the middle
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		//Specify panels for the left and right half of the GUI
		left = new JPanel();
		right = new JPanel();
		//draw the transitions
        //textView = drawTransitions(manager);
        //draw the state
        treeView = drawState(state);

        //Add the views to the panels
		left.add(textView);
		right.add(treeView);
		
		//Add the panels to the Frame
		add(left);
		add(right);

	}
    

    /**
     * Draws the ImmutableState object to a JTree of nodes, and then makes a
     * pane with that tree and returns the view of that tree
     * 
     * @param state
     * @return JScrollPane
     */
    public JScrollPane drawState(ImmutableState state) {
    	
    	int numDyscopes = state.numScopes();
    	ImmutableDynamicScope[] dyscopes = new ImmutableDynamicScope[numDyscopes];
    	for(int i = 0; i < state.numScopes(); i++) {
    		dyscopes[i] = state.getScope(i);
    	}

    	DefaultMutableTreeNode[] treeNodes = new DefaultMutableTreeNode[dyscopes.length];

    	//Make an array of nodes corresponding to the dyscopes of the state
        for(int i = 0; i < dyscopes.length; i++) {
        	treeNodes[i] = new DefaultMutableTreeNode(dyscopes[i].toString());
        }
        
        for(int i = 0; i<dyscopes.length;i++) {
        	int parentID = state.getParentId(i);
        	
        	if(parentID != -1) {
        		treeNodes[parentID].add(treeNodes[i]);
        	}
        }
        DefaultMutableTreeNode procs = new DefaultMutableTreeNode("Process States");
        for(ProcessState p : state.getProcessStates()) {
        	DefaultMutableTreeNode procNode = new DefaultMutableTreeNode(p.toString());
        	procs.add(procNode);
        }
        
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("State: " + state.getCanonicId());
        
        top.add(treeNodes[0]);
        top.add(procs);
    	//Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        
      //Create the view of the tree and set their preferred size
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setPreferredSize(new Dimension(300,500));        
        return treeView;
    }
    
    /**
     * Draw the transitions of the execution of the program to a JTextPane
     * 
     * @param manager
     * @return JTextPane
     */
//    public JTextPane drawTransitions(ImmutableState[] states, Transition[] transitions) {
//    	for(int i = 0; i < states.length; i++) {
//    		System.out.println(states[i].toString());
//    		System.out.println(transitions[i].toString());
//    	}
//    }
    
    /**
     * This method will redraw a new state once it is clicked in the transitions
     * TODO:Implement
     */
//    public void stateClickedEvent() {
//    	right.removeAll();
//    	treeView = drawState(newStateClicked);
//    	right.add(treeView);
//    	right.repaint();
//    }

    

}
