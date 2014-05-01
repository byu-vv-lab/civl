package edu.udel.cis.vsl.civl.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.immutable.ImmutableDynamicScope;
import edu.udel.cis.vsl.civl.transition.CompoundTransition;
import edu.udel.cis.vsl.civl.transition.Step;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class CIVL_GUI extends JFrame implements TreeSelectionListener {
	private static final long serialVersionUID = 1L;
	private final int TRANSITION_NODE = 0;
	private final int STATE_NODE = 1;
	private final int STATEMENT_NODE = 2;
	private JTree stateTree;
	private JTree transitionTree;
	private JTree singleTransTree;
	private JScrollPane transitionView;
	private JScrollPane stateView;
	private JSplitPane split;
	private CompoundTransition[] transitions;

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
		stateView = drawSingleTransition(transitions[0],0);

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

			int parentID = state.getParentId(i);

			if (parentID != -1) {
				treeNodes[parentID].add(treeNodes[i]);
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
		for (int i = 0; i < stateTree.getRowCount(); i++) {
			stateTree.expandRow(i);
		}

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
		for (int i = 0; i < transitions.length; i++) {
			TransitionNode transitionNode = new TransitionNode("Transition: "
					+ i + ", Process: p" + transitions[i].processIdentifier(),
					transitions[i]);
			int index = 0;
			for (Step s : transitions[i].getSteps()) {
				DefaultMutableTreeNode stepNode = new DefaultMutableTreeNode(
						"Step " + index + ": " + s.toString());
				transitionNode.add(stepNode);
				StateNode startNode = new StateNode("Starting State: "
						+ s.start().toString(), s.start());
				StateNode targetNode = new StateNode("Target State: "
						+ s.target().toString(), s.target());
				StatementNode statementNode = new StatementNode("Statement: "
						+ s.statement().toString(), s.statement());
				stepNode.add(startNode);
				stepNode.add(targetNode);
				stepNode.add(statementNode);
				index++;
			}
			top.add(transitionNode);
		}
		// Create a tree that allows one selection at a time.
		transitionTree = new JTree(top);
		transitionTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		transitionTree.addTreeSelectionListener(this);

		// Create the view of the tree and set their preferred size
		JScrollPane transitionView = new JScrollPane(transitionTree);
		transitionView.setPreferredSize(new Dimension(600, 500));
		return transitionView;
	}

	public JScrollPane drawSingleTransition(CompoundTransition t, int num) {
		TransitionNode top = new TransitionNode("Transition: " + num
				+ ", Process: p" + t.processIdentifier(), t);
		int index = 0;
		for (Step s : t.getSteps()) {
			DefaultMutableTreeNode stepNode = new DefaultMutableTreeNode(
					"Step = " + index + ": " + s.toString());
			top.add(stepNode);
			StateNode startNode = new StateNode("Starting State: "
					+ s.start().toString(), s.start());
			StateNode targetNode = new StateNode("Target State: "
					+ s.target().toString(), s.target());
			StatementNode statementNode = new StatementNode("Statement: "
					+ s.statement().toString(), s.statement());
			stepNode.add(startNode);
			stepNode.add(targetNode);
			stepNode.add(statementNode);
			index++;
		}
		// Create a tree that allows one selection at a time.
		singleTransTree = new JTree(top);
		singleTransTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the view of the tree and set their preferred size
		JScrollPane transitionView = new JScrollPane(singleTransTree);
		transitionView.setPreferredSize(new Dimension(600, 500));
		return transitionView;

	}

	private abstract class GUINODE extends DefaultMutableTreeNode {
		private static final long serialVersionUID = 1L;

		public GUINODE(String name) {
			super(name);
		}

		public abstract int getID();

	}

	private class TransitionNode extends GUINODE {
		private static final long serialVersionUID = 1L;
		public final int id = TRANSITION_NODE;
		protected CompoundTransition transition;

		public TransitionNode(String name, CompoundTransition t) {
			super(name);
			transition = t;
		}

		public CompoundTransition getTransition() {
			return transition;
		}

		public int getID() {
			return id;
		}
	}

	private class StateNode extends GUINODE {
		private static final long serialVersionUID = 1L;
		public final int id = STATE_NODE;
		protected State state;

		public StateNode(String name, State s) {
			super(name);
			state = s;

		}

		public State getState() {
			return state;
		}

		public int getID() {
			return id;
		}
	}

	private class StatementNode extends GUINODE {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public final int id = STATEMENT_NODE;
		protected Statement statement;

		public StatementNode(String name, Statement s) {
			super(name);
			statement = s;
		}

		@SuppressWarnings("unused")
		public Statement getStatement() {
			return statement;
		}

		public int getID() {
			return id;
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		try {
			TransitionNode n = (TransitionNode) transitionTree
					.getLastSelectedPathComponent();
			if (n == null)
				return;
			split.remove(split.getRightComponent());
			transitionView = drawSingleTransition(n.getTransition(), 1);
			split.setRightComponent(transitionView);
		} catch (Exception e1) {
			try {
				StateNode n = (StateNode) transitionTree
						.getLastSelectedPathComponent();
				if (n == null)
					return;
				split.remove(split.getRightComponent());
				stateView = drawState(n.getState());
				split.setRightComponent(stateView);
			} catch (Exception e2) {
				return;
			}
		}
	}
}
