package edu.udel.cis.vsl.civl.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.immutable.ImmutableDynamicScope;
import edu.udel.cis.vsl.civl.transition.CompoundTransition;
import edu.udel.cis.vsl.civl.transition.Step;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class CIVL_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree stateTree;
	private JTree transitionTree;
	private JScrollPane transitionView;
	private JScrollPane stateView;
	private JSplitPane split;
	protected CompoundTransition[] transitions;

	/**
	 * Constructor for the CIVL GUI
	 */
	public CIVL_GUI(CompoundTransition[] transitions) {

		this.transitions = transitions;

		// initialize components of this CIVL GUI
		initComponents();

		setPreferredSize(new Dimension(1500, 1000));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Make the GUI visible
		pack();
		setVisible(true);

	}

	/**
	 * Initialize the components of the CIVL GUI
	 * 
	 * @param state
	 * @param manager
	 */
	private void initComponents() {

		// draw the transitions
		transitionView = drawTransitions();
		// draw the state
		stateView = drawState(transitions[0].getStep(0).start());

		// Add the views to the panels
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, transitionView,
				stateView);

		// Add the split pane to the Frame
		add(split);

	}

	/**
	 * Draws the ImmutableState object to a JTree of nodes, and then makes a
	 * pane with that tree and returns the view of that tree
	 * 
	 * @param state
	 * @return JScrollPane
	 */
	public JScrollPane drawState(State state) {

		int numDyscopes = state.numScopes();
		ImmutableDynamicScope[] dyscopes = new ImmutableDynamicScope[numDyscopes];
		for (int i = 0; i < state.numScopes(); i++) {
			dyscopes[i] = (ImmutableDynamicScope) state.getScope(i);
		}

		DefaultMutableTreeNode[] treeNodes = new DefaultMutableTreeNode[dyscopes.length];

		// Make an array of nodes corresponding to the dyscopes of the state
		for (int i = 0; i < dyscopes.length; i++) {
			treeNodes[i] = new DefaultMutableTreeNode("dyscope d"
					+ dyscopes[i].identifier() + " (id=" + i + ", static="
					+ dyscopes[i].lexicalScope().id() + ")");
		}

		for (int i = 0; i < dyscopes.length; i++) {
			int parentID = state.getParentId(i);

			if (parentID != -1) {
				treeNodes[parentID].add(treeNodes[i]);
			}

			DefaultMutableTreeNode variables = new DefaultMutableTreeNode(
					"Variables");
			treeNodes[i].add(variables);
			int vid = 0;
			for (SymbolicExpression s : dyscopes[i].getValues()) {
				String variableName = dyscopes[i].lexicalScope().variable(vid)
						.name().name();
				vid++;
				DefaultMutableTreeNode variableNode = new DefaultMutableTreeNode(
						variableName + " = " + s.toString());
				variables.add(variableNode);
			}

		}
		DefaultMutableTreeNode procs = new DefaultMutableTreeNode(
				"Process States");
		DefaultMutableTreeNode procNode;
		String output = "";
		for (ProcessState p : state.getProcessStates()) {
			for (StackEntry s : p.getStackEntries()) {
				output += s.toString() + "\n";
			}
			procNode = new DefaultMutableTreeNode(output);
			procs.add(procNode);
		}

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("State: "
				+ state.getCanonicId());
		DefaultMutableTreeNode dy = new DefaultMutableTreeNode("Dyscopes");
		dy.add(treeNodes[0]);
		top.add(dy);
		top.add(procs);
		// Create a tree that allows one selection at a time.
		stateTree = new JTree(top);
		stateTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the view of the tree and set their preferred size
		JScrollPane treeView = new JScrollPane(stateTree);
		treeView.setPreferredSize(new Dimension(500, 500));
		return treeView;
	}

	/**
	 * Creates a tree containing the transitions of the execution
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane drawTransitions() {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Transitions");
		for(int i = 0; i < transitions.length;i++) {
			DefaultMutableTreeNode tNode = new DefaultMutableTreeNode("Transition: " + i);
			for(Step s : transitions[i].getSteps()) {
				tNode.add(new DefaultMutableTreeNode(s.toString()));
			}
			//+ " - " + s.statement().toString() + " -> " + new stateButton(s.target()))
			top.add(tNode);
		}
		// Create a tree that allows one selection at a time.
		transitionTree = new JTree(top);
		transitionTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the view of the tree and set their preferred size
		JScrollPane transitionView = new JScrollPane(transitionTree);
		transitionView.setPreferredSize(new Dimension(600, 500));
		return transitionView;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		split.remove(stateView);
//		stateView = drawState(((stateButton) e.getSource()).getState());
//		split.add(stateView);
//
//	}
//
//	@SuppressWarnings("serial")
//	private class stateButton extends JButton {
//		State state;
//
//		public stateButton(State state) {
//			super("State: " + state.getCanonicId());
//			this.state = state;
//
//		}
//
//		public State getState() {
//			return state;
//		}
//	}

}
