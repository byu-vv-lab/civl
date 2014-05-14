package edu.udel.cis.vsl.civl.gui.IF;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.udel.cis.vsl.civl.kripke.IF.CompoundTransition;
import edu.udel.cis.vsl.civl.kripke.IF.Step;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.civl.state.IF.DynamicScope;
import edu.udel.cis.vsl.civl.state.IF.ProcessState;
import edu.udel.cis.vsl.civl.state.IF.StackEntry;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.state.IF.StateFactory;
//import edu.udel.cis.vsl.civl.state.immutable.ImmutableDynamicScope;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;

public class CIVL_GUI extends JFrame implements TreeSelectionListener {

	/* *************************** Fields *************************** */

	private StateFactory stateFactory;

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;

	/**
	 * Only used for GUINODE (see Private Classes) to indicate that it is the
	 * root of the tree. Used by the selection listener so that when clicked the
	 * tree will collapse the children of the root node
	 */
	final int ROOT_NODE = 0;

	/**
	 * Only used for TransitionNode (see Private Classes) to indicate that it is
	 * a node representing a compound transition. It is used by the tree
	 * selection listener so that when clicked it will display the transition's
	 * tree on the right side of the GUI
	 */
	final int TRANSITION_NODE = 1;

	/**
	 * Only used for StateNode (see Private Classes) to indicate that it is a
	 * node representing a State. It is used by the tree selection listener so
	 * that when clicked it will display the state tree on the right side of the
	 * GUI
	 */
	final int STATE_NODE = 2;

	/**
	 * Only used for StatementNode (see Private Classes) to indicate that it is
	 * a node representing a statement. It is used by the three selection
	 * listener so that when clicked it will display the statement as a tree on
	 * the right side of the GUI
	 */
	final int STATEMENT_NODE = 3;

	/**
	 * Only used for StepNode (see Private Classes) to indicate that it is a
	 * node representing a step. It is used by the three selection listener so
	 * that when clicked it will display the target state of the step as a tree
	 * on the right side of the GUI
	 */
	final int STEP_NODE = 4;

	/**
	 * A tree that represents the transitions of the execution. It is drawn once
	 * when the GUI is created
	 */
	JTree transitionTree;

	/**
	 * A tree that represents the selected state. States are drawn when the user
	 * selects a StateNode from the transitionTree on the left side of the GUI
	 */
	JTree stateTree;

	/**
	 * A tree that represents the selected transition. It is drawn when a
	 * TransitionNode is selected from the transitionTree on the left side of
	 * the GUI
	 */
	JTree singleTransTree;

	/**
	 * A tree that represents the selected statement. It is drawn when a
	 * StatementNode is selected from the transitionTree on the left side of the
	 * GUI
	 */
	JTree statementTree;

	/**
	 * The view currently displayed on the left side of the GUI. Only the
	 * transitionTree is ever drawn as the leftView
	 */
	JScrollPane leftView;

	/**
	 * The view currently displayed on the right side of the GUI. This can be
	 * one of the following: stateTree, statementTree, or singleTransTree
	 */
	JScrollPane rightView;

	/**
	 * The split pane that is added to the frame. It contains the leftView and
	 * the rightView
	 */
	JSplitPane split;

	/**
	 * The list of transitions of the execution
	 */
	CompoundTransition[] transitions;

	/* *************************** Constructor *************************** */

	/**
	 * Constructs a new CIVL_GUI using a list of transitions
	 * 
	 * @param transitions
	 *            the array of transitions of the execution
	 */
	public CIVL_GUI(CompoundTransition[] transitions, StateFactory stateFactory) {
		this.transitions = transitions;
		this.stateFactory = stateFactory;
		initComponents();
		setPreferredSize(new Dimension(1500, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/* *************************** Methods *************************** */

	/**
	 * Initialize the components of the CIVL GUI
	 */
	void initComponents() {
		leftView = drawTransitions();
		rightView = drawState(transitions[0].getStep(0).start());
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftView, rightView);
		add(split); // To the CIVL_GUI JFrame
	}

	/**
	 * Draws the ImmutableState object to a JTree of nodes, and then makes a
	 * pane with that tree and returns the view of that tree
	 * 
	 * @param state
	 *            The State object that is drawn
	 * @return JScrollPane rightView with stateTree as its component with the
	 *         new state drawn
	 */
	JScrollPane drawState(State state) {
		int numDyscopes = state.numScopes();
		DynamicScope[] dyscopes = new DynamicScope[numDyscopes];

		// Create an array of dyscopes
		for (int i = 0; i < state.numScopes(); i++) {
			dyscopes[i] = state.getScope(i);
		}

		// Make an array of nodes corresponding to the dyscopes of the state
		DefaultMutableTreeNode[] treeNodes = new DefaultMutableTreeNode[dyscopes.length];
		for (int i = 0; i < dyscopes.length; i++) {
			treeNodes[i] = new DefaultMutableTreeNode("d"
					+ dyscopes[i].identifier() + " (id=" + i + ", static="
					+ dyscopes[i].lexicalScope().id() + ")");
		}

		// For each dyscope
		// This comes first because Variables should be listed
		// before the current dyscopes children dyscopes
		for (int i = 0; i < dyscopes.length; i++) {

			// Create a Variables node and add it to the dyscope's node
			DefaultMutableTreeNode variables = new DefaultMutableTreeNode(
					"Variables");
			treeNodes[i].add(variables);

			// Keep track of which variable we're on with vid
			int vid = 0;

			// For each variable value of the dyscope
			for (SymbolicExpression s : dyscopes[i].getValues()) {
				Variable var = dyscopes[i].lexicalScope().variable(vid);
				String variableName = var.name().name();
				DefaultMutableTreeNode variableNode = new DefaultMutableTreeNode(
						variableName
								+ " = "
								+ stateFactory.symbolicExpressionToString(
										var.getSource(), state, s));
				if (!(variableName == "__heap" && s.isNull())) {
					variables.add(variableNode);
					// vid++; this statement should be moved to be outside of
					// the if block.
				}
				vid++;
			}
		}

		// For each dyscope
		// This comes second so that the child dyscopes are listed under
		// the current dyscopes variables
		for (int i = 0; i < dyscopes.length; i++) {
			// If the dyscope isn't the root dyscope (ie parentID != -1) add
			// it's node to the node corresponding to it's parent dyscope
			int parentID = state.getParentId(i);
			if (parentID != -1) {
				treeNodes[parentID].add(treeNodes[i]);
			}
		}

		// The path condition
		DefaultMutableTreeNode pathCond = new DefaultMutableTreeNode(
				"Path Condition: " + state.getPathCondition().toString());

		// The process states of the state
		DefaultMutableTreeNode procs = new DefaultMutableTreeNode(
				"Process States");
		DefaultMutableTreeNode procNode;
		String output = "";

		// Add the process states
		for (ProcessState p : state.getProcessStates()) {
			// p.identifier()
			for (StackEntry s : p.getStackEntries()) {
				output += s.toString() + "\n";
			}
			procNode = new DefaultMutableTreeNode(output);
			procs.add(procNode);
		}

		// Create the root node of the entire tree
		GUINODE top = new GUINODE(state.toString());
		top.collapsed = false;

		// Node for the dyscopes of the state
		DefaultMutableTreeNode dy = new DefaultMutableTreeNode("Dyscopes");

		// Add the root dyscope to the dyscopes node
		dy.add(treeNodes[0]);

		// Add the path condition to the root of the tree
		top.add(pathCond);

		// Add the dyscopes to the root of the tree
		top.add(dy);

		// Add the process states to the root of the tree
		top.add(procs);

		// Make the stateTree a JTree with the top as the root
		stateTree = new JTree(top);
		stateTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Expand all nodes by default
		for (int i = 0; i < stateTree.getRowCount(); i++) {
			stateTree.expandRow(i);
		}

		// Listen for the root node to be selected
		// Collapse the root nodes children
		stateTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				try {
					GUINODE n = (GUINODE) stateTree
							.getLastSelectedPathComponent();
					if (n == null)
						return;
					if (n.getID() == ROOT_NODE) {
						if (!n.isCollapsed()) {
							for (int i = stateTree.getRowCount() - 1; i > 0; i--) {
								stateTree.collapseRow(i);
							}
							n.collapsed = true;
						} else {
							for (int i = 0; i < stateTree.getRowCount(); i++) {
								stateTree.expandRow(i);
							}
							n.collapsed = false;
						}
					}
				} catch (Exception ex) {
					return;
				}

			}

		});

		// Create the view of the tree and set its preferred size
		rightView = new JScrollPane(stateTree);
		rightView.setPreferredSize(new Dimension(500, 500));
		return rightView;
	}

	/**
	 * Draws the transitions array to a JTree of nodes, and then makes a pane
	 * with that tree and returns the view of that tree
	 * 
	 * @return JScrollPane leftView with transitionTree as its component with
	 *         the new state drawn
	 */
	JScrollPane drawTransitions() {
		// The root of the tree
		GUINODE top = new GUINODE("Transitions");

		top.add(new StateNode("State: 0", transitions[0].getStep(0).start()));

		// For each transition
		for (int i = 0; i < transitions.length; i++) {
			StringBuffer transitionName = new StringBuffer();
			transitionName.append("p" + transitions[i].processIdentifier()
					+ ": ");

			for (Step s : transitions[i].getSteps()) {
				if (transitionName.length() < 50) {
					transitionName.append(s.statement().toString() + "; ");
				} else {
					transitionName.append("...");
					break;
				}
			}
			TransitionNode transitionNode = new TransitionNode(
					transitionName.toString(), transitions[i], i);

			// What step you're on
			int index = 0;

			// For each step in the transition
			for (Step s : transitions[i].getSteps()) {
				StepNode stepNode = new StepNode("Step " + index + ": "
						+ s.toString(), s);

				// Add the step to the transition
				transitionNode.add(stepNode);

				// The executed statement
				StatementNode statementNode = new StatementNode(s.statement()
						.toString(), s.statement());

				// Add in the correct order
				stepNode.add(statementNode);

				// Move to next step
				index++;
			}

			// Add transition to the root node
			top.add(transitionNode);
		}

		// Make the transition tree with root node top
		transitionTree = new JTree(top);
		transitionTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// CIVL_GUI is the listener for transition trees
		transitionTree.addTreeSelectionListener(this);

		// Create the view of the tree and set its preferred size
		leftView = new JScrollPane(transitionTree);
		leftView.setPreferredSize(new Dimension(600, 500));
		return leftView;
	}

	/**
	 * Draws the Statement object to a JTree of nodes, and then makes a pane
	 * with that tree and returns the view of that tree
	 * 
	 * @param statement
	 *            The Statement object that is drawn
	 * @return JScrollPane rightView with statementTree as its component with
	 *         the new state drawn
	 */

	JScrollPane drawStatement(Statement statement) {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Statement");

		// Add the source location summary
		top.add(new DefaultMutableTreeNode(statement.getSource().getSummary()));

		// Add the source location
		top.add(new DefaultMutableTreeNode("Source:" + statement.source().id()));

		// Add the guard
		top.add(new DefaultMutableTreeNode("Guard: "
				+ statement.guard().toString()));

		// Add the target location
		top.add(new DefaultMutableTreeNode("Target: " + statement.target().id()));

		// Make the statement treee
		statementTree = new JTree(top);
		statementTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the view of the tree and set its preferred size
		rightView = new JScrollPane(statementTree);
		rightView.setPreferredSize(new Dimension(600, 500));
		return rightView;
	}

	/* *************************** Private Classes *************************** */

	/**
	 * A GUINODE will be used only for the root nodes of any tree in this GUI.
	 * The reason a GUINODE is used is to be able to collapse all nodes when the
	 * root node is clicked
	 * 
	 * @author Ben
	 * 
	 */
	class GUINODE extends DefaultMutableTreeNode {
		static final long serialVersionUID = 1L;
		final int id = ROOT_NODE;
		boolean collapsed;

		public GUINODE(String name) {
			super(name);
			collapsed = true;
		}

		int getID() {
			return id;
		}

		boolean isCollapsed() {
			return collapsed;
		}

	}

	/**
	 * Node corresponding to a transition.
	 * 
	 * @author Ben
	 * 
	 */
	class TransitionNode extends GUINODE {
		static final long serialVersionUID = 1L;
		final int id = TRANSITION_NODE;
		CompoundTransition transition;
		int num;

		TransitionNode(String name, CompoundTransition t, int num) {
			super(name);
			transition = t;
			this.num = num;
		}

		CompoundTransition getTransition() {
			return transition;
		}

		int getID() {
			return id;
		}

		int getNum() {
			return num;
		}

	}

	/**
	 * Node corresponding to a State
	 * 
	 * @author Ben
	 * 
	 */
	private class StateNode extends GUINODE {
		static final long serialVersionUID = 1L;
		final int id = STATE_NODE;
		State state;

		StateNode(String name, State s) {
			super(name);
			state = s;

		}

		State getState() {
			return state;
		}

		int getID() {
			return id;
		}

	}

	/**
	 * Node corresponding to a Step
	 * 
	 * @author Ben
	 */
	class StepNode extends GUINODE {
		static final long serialVersionUID = 1L;
		final int id = STEP_NODE;
		Step step;

		public StepNode(String name, Step s) {
			super(name);
			step = s;
		}

		Step getStep() {
			return step;
		}

		int getID() {
			return id;
		}

	}

	/**
	 * Node corresponding to a Statement
	 * 
	 * @author Ben
	 * 
	 */
	class StatementNode extends GUINODE {
		static final long serialVersionUID = 1L;
		final int id = STATEMENT_NODE;
		Statement statement;

		StatementNode(String name, Statement s) {
			super(name);
			statement = s;
		}

		Statement getStatement() {
			return statement;
		}

		int getID() {
			return id;
		}

	}

	/* ***************** TreeSelectionListener Methods ***************** */

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		try {
			// Get the last selected node
			GUINODE n = (GUINODE) transitionTree.getLastSelectedPathComponent();
			// If no node selected do nothing
			if (n == null)
				return;

			// If node is a root node collapse all of the roots children
			if (n.getID() == ROOT_NODE) {
				if (!n.isCollapsed()) {
					for (int i = transitionTree.getRowCount() - 1; i > 0; i--) {
						transitionTree.collapseRow(i);
					}
					n.collapsed = true;
				} else {
					for (int i = 0; i < transitionTree.getRowCount(); i++) {
						transitionTree.expandRow(i);
					}
					n.collapsed = false;
				}
			}

			// If node is a transition node draw the single transition to the
			// right side of the GUI
			else if (n.getID() == TRANSITION_NODE) {
				try {
					TransitionNode t = (TransitionNode) n;
					split.remove(split.getRightComponent());
					rightView = drawState(t.transition.getStep(
							t.transition.getNumOfSteps() - 1).target());
					split.setRightComponent(rightView);
				} catch (Exception tranEX) {
					tranEX.printStackTrace();
				}
			}

			// If node is a state node draw the state to the right side of the
			// GUI
			else if (n.getID() == STATE_NODE) {
				try {
					StateNode s = (StateNode) n;
					split.remove(split.getRightComponent());
					rightView = drawState(s.getState());
					split.setRightComponent(rightView);
				} catch (Exception stateEX) {
					stateEX.printStackTrace();
				}
			}

			// If node is a step node draw the target state of the step to the
			// right side of the gui
			else if (n.getID() == STEP_NODE) {
				try {
					StepNode s = (StepNode) n;
					split.remove(split.getRightComponent());
					rightView = drawState(s.getStep().target());
					split.setRightComponent(rightView);
				} catch (Exception stepEX) {
					return;
				}
			}

			// If node is a statement node draw the statement to the right side
			// of the GUI
			else if (n.getID() == STATEMENT_NODE) {
				try {
					StatementNode s = (StatementNode) n;
					split.remove(split.getRightComponent());
					rightView = drawStatement(s.getStatement());
					split.setRightComponent(rightView);
				} catch (Exception statementEX) {
					statementEX.printStackTrace();
				}
			}
		}

		// If the node wasn't a GUINODE (or a node extending from GUINODE)
		// Do nothing
		catch (Exception ex) {
			return;
		}
	}
}
