package edu.udel.cis.vsl.civl.model;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.CastNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.civl.ast.node.IF.expression.SpawnNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.AssertNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.WaitNode;
import edu.udel.cis.vsl.civl.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.civl.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.civl.token.IF.SyntaxException;

public class SideEffectRemover {

	private NodeFactory factory;

	public void removeSideEffects(TranslationUnit unit) throws SyntaxException {
		ASTNode rootNode = unit.getRootNode();

		factory = unit.getUnitFactory().getNodeFactory();
		for (int i = 0; i < rootNode.numChildren(); i++) {
			ASTNode node = rootNode.child(i);

			// For now, assume initializers for global variables are side effect
			// free (SEF).
			if (node instanceof FunctionDefinitionNode) {
				removeSideEffects((FunctionDefinitionNode) node);
			}
		}
	}

	private void removeSideEffects(FunctionDefinitionNode function)
			throws SyntaxException {
		function.setBody(processCompoundStatement(function.getBody()));
	}

	private CompoundStatementNode processCompoundStatement(
			CompoundStatementNode statement) throws SyntaxException {
		CompoundStatementNode result;
		List<BlockItemNode> items = new Vector<BlockItemNode>();

		for (int i = 0; i < statement.numChildren(); i++) {
			BlockItemNode item = statement.getSequenceChild(i);

			if (item instanceof FunctionDefinitionNode) {
				removeSideEffects((FunctionDefinitionNode) item);
				items.add(item);
			} else if (item instanceof StatementNode) {
				items.add(processStatement((StatementNode) item));
			} else {
				// Variable initializers might not be SEF. Handle this later.
				// Other types of block items should be SEF.
				items.add(item);
			}
		}
		result = factory.newCompoundStatementNode(statement.getSource(), items);
		return result;
	}

	private StatementNode processStatement(StatementNode statement)
			throws SyntaxException {
		StatementNode result = null;

		if (statement instanceof AssertNode) {
			result = assertStatement((AssertNode) statement);
		} else if (statement instanceof AssumeNode) {
			result = assumeStatement((AssumeNode) statement);
		} else if (statement instanceof ChooseStatementNode) {
			result = chooseStatement((ChooseStatementNode) statement);
		} else if (statement instanceof CompoundStatementNode) {
			result = processCompoundStatement((CompoundStatementNode) statement);
		} else if (statement instanceof ExpressionStatementNode) {
			result = expressionStatement((ExpressionStatementNode) statement);
		} else if (statement instanceof ForLoopNode) {
			result = loopStatement((ForLoopNode) statement);
		} else if (statement instanceof IfNode) {
			result = ifStatement((IfNode) statement);
		} else if (statement instanceof LabeledStatementNode) {
			result = labeledStatement((LabeledStatementNode) statement);
		} else if (statement instanceof LoopNode) {
			result = loopStatement((LoopNode) statement);
		} else if (statement instanceof ReturnNode) {
			result = returnStatement((ReturnNode) statement);
		} else if (statement instanceof SwitchNode) {
			result = switchStatement((SwitchNode) statement);
		} else if (statement instanceof WaitNode) {
			result = waitStatement((WaitNode) statement);
		} else if (statement instanceof WhenNode) {
			result = whenStatement((WhenNode) statement);
		} else {
			result = statement;
		}
		return result;
	}

	private StatementNode chooseStatement(ChooseStatementNode statement)
			throws SyntaxException {
		ChooseStatementNode result;
		StatementNode defaultCase = statement.getDefaultCase();
		Vector<StatementNode> statements = new Vector<StatementNode>();
		Iterator<StatementNode> iterator = statement.childIterator();

		while (iterator.hasNext()) {
			statements.add(processStatement(iterator.next()));
		}
		result = factory.newChooseStatementNode(statement.getSource(),
				statements);
		if (defaultCase != null) {
			defaultCase = processStatement(defaultCase);
			assert defaultCase instanceof LabeledStatementNode;
			result.setDefaultCase((LabeledStatementNode) defaultCase);
		}
		return result;
	}

	private StatementNode switchStatement(SwitchNode statement)
			throws SyntaxException {
		StatementNode result;

		if (!isSEF(statement.getCondition())) {
			throw new RuntimeException(
					"Side effects in a switch condition are not supported. "
							+ statement.getCondition());
		}
		result = factory
				.newSwitchNode(statement.getSource(), statement.getCondition(),
						processStatement(statement.getBody()));
		return result;
	}

	private StatementNode returnStatement(ReturnNode statement)
			throws SyntaxException {
		StatementNode result;

		if (isSEF(statement.getExpression())) {
			result = statement;
		} else {
			Vector<BlockItemNode> newStatements = new Vector<BlockItemNode>();
			SideEffectFreeTriple triple = processExpression(statement
					.getExpression());

			if (!triple.getAfter().isEmpty()) {
				throw new RuntimeException(
						"Side effects that modify the state after a return statement are not supported. "
								+ statement);
			} else {
				newStatements.addAll(triple.getBefore());
				newStatements.add(factory.newReturnNode(statement.getSource(),
						triple.getExpression()));
				result = factory.newCompoundStatementNode(
						statement.getSource(), newStatements);
			}
		}
		return result;
	}

	private StatementNode waitStatement(WaitNode statement)
			throws SyntaxException {
		StatementNode result;

		if (isSEF(statement.getExpression())) {
			result = statement;
		} else {
			Vector<BlockItemNode> newStatements = new Vector<BlockItemNode>();
			SideEffectFreeTriple triple = processExpression(statement
					.getExpression());

			newStatements.addAll(triple.getBefore());
			newStatements.add(factory.newWaitNode(statement.getSource(),
					triple.getExpression()));
			newStatements.addAll(triple.getAfter());
			result = factory.newCompoundStatementNode(statement.getSource(),
					newStatements);
		}
		return result;
	}

	private StatementNode labeledStatement(LabeledStatementNode statement)
			throws SyntaxException {
		StatementNode target = processStatement(statement.getStatement());

		return factory.newLabeledStatementNode(statement.getSource(),
				statement.getLabel(), target);
	}

	private StatementNode ifStatement(IfNode statement) throws SyntaxException {
		StatementNode trueBranch = processStatement(statement.getTrueBranch());
		StatementNode falseBranch = null;

		if (statement.getFalseBranch() != null) {
			falseBranch = processStatement(statement.getFalseBranch());
		}
		if (!isSEF(statement.getCondition())) {
			throw new RuntimeException(
					"Side effects in a condition are not supported. "
							+ statement.getCondition());
		}
		return factory.newIfNode(statement.getSource(),
				statement.getCondition(), trueBranch, falseBranch);
	}

	private StatementNode whenStatement(WhenNode statement)
			throws SyntaxException {
		StatementNode result;

		result = factory.newWhenNode(statement.getSource(),
				statement.getGuard(), processStatement(statement.getBody()));
		return result;
	}

	private StatementNode assertStatement(AssertNode statement)
			throws SyntaxException {
		StatementNode result;

		if (isSEF(statement.getExpression())) {
			result = statement;
		} else {
			Vector<BlockItemNode> newStatements = new Vector<BlockItemNode>();
			SideEffectFreeTriple triple = processExpression(statement
					.getExpression());

			newStatements.addAll(triple.getBefore());
			newStatements.add(factory.newAssertNode(statement.getSource(),
					triple.getExpression()));
			newStatements.addAll(triple.getAfter());
			result = factory.newCompoundStatementNode(statement.getSource(),
					newStatements);
		}
		return result;
	}

	private StatementNode assumeStatement(AssumeNode statement)
			throws SyntaxException {
		StatementNode result;

		if (isSEF(statement.getExpression())) {
			result = statement;
		} else {
			Vector<BlockItemNode> newStatements = new Vector<BlockItemNode>();
			SideEffectFreeTriple triple = processExpression(statement
					.getExpression());

			newStatements.addAll(triple.getBefore());
			newStatements.add(factory.newAssumeNode(statement.getSource(),
					triple.getExpression()));
			newStatements.addAll(triple.getAfter());
			result = factory.newCompoundStatementNode(statement.getSource(),
					newStatements);
		}
		return result;
	}

	private StatementNode expressionStatement(ExpressionStatementNode statement)
			throws SyntaxException {
		StatementNode result = null;
		ExpressionNode expression = statement.getExpression();

		if (isSEF(expression)) {
			result = statement;
		} else {
			if (expression instanceof FunctionCallNode) {
				boolean sideEffectFree = true;

				for (int i = 0; i < ((FunctionCallNode) expression)
						.getNumberOfArguments(); i++) {
					sideEffectFree = sideEffectFree
							&& isSEF(((FunctionCallNode) expression)
									.getArgument(i));
				}
				if (sideEffectFree) {
					result = statement;
				} else {
					Vector<BlockItemNode> before = new Vector<BlockItemNode>();
					Vector<BlockItemNode> after = new Vector<BlockItemNode>();
					Vector<ExpressionNode> arguments = new Vector<ExpressionNode>();
					Vector<BlockItemNode> blockItems = new Vector<BlockItemNode>();

					for (int i = 0; i < ((FunctionCallNode) expression)
							.getNumberOfArguments(); i++) {
						ExpressionNode originalArgument = ((FunctionCallNode) expression)
								.getArgument(i);

						if (isSEF(originalArgument)) {
							arguments.add(originalArgument);
						} else {
							SideEffectFreeTriple triple = processExpression(originalArgument);

							before.addAll(triple.getBefore());
							arguments.add(triple.getExpression());
							after.addAll(triple.getAfter());
						}
					}
					blockItems.addAll(before);
					blockItems.add(factory.newExpressionStatementNode(factory
							.newFunctionCallNode(statement.getSource(),
									((FunctionCallNode) expression)
											.getFunction(), arguments)));
					blockItems.addAll(after);
					result = factory.newCompoundStatementNode(
							statement.getSource(), blockItems);
				}
			} else if (expression instanceof SpawnNode) {
				FunctionCallNode call = ((SpawnNode) expression).getCall();
				boolean sideEffectFree = true;

				for (int i = 0; i < call.getNumberOfArguments(); i++) {
					sideEffectFree = sideEffectFree
							&& isSEF(call.getArgument(i));
				}
				if (sideEffectFree) {
					result = statement;
				} else {
					Vector<BlockItemNode> before = new Vector<BlockItemNode>();
					Vector<BlockItemNode> after = new Vector<BlockItemNode>();
					Vector<ExpressionNode> arguments = new Vector<ExpressionNode>();
					Vector<BlockItemNode> blockItems = new Vector<BlockItemNode>();

					for (int i = 0; i < call.getNumberOfArguments(); i++) {
						ExpressionNode originalArgument = call.getArgument(i);

						if (isSEF(originalArgument)) {
							arguments.add(originalArgument);
						} else {
							SideEffectFreeTriple triple = processExpression(originalArgument);

							before.addAll(triple.getBefore());
							arguments.add(triple.getExpression());
							after.addAll(triple.getAfter());
						}
					}
					blockItems.addAll(before);
					blockItems.add(factory.newExpressionStatementNode(factory
							.newSpawnNode(
									statement.getSource(),
									factory.newFunctionCallNode(
											statement.getSource(),
											call.getFunction(), arguments))));
					blockItems.addAll(after);
					result = factory.newCompoundStatementNode(
							statement.getSource(), blockItems);
				}
			} else if (expression instanceof OperatorNode) {
				OperatorNode.Operator operator = ((OperatorNode) expression)
						.getOperator();

				switch (operator) {
				case PREINCREMENT:
				case POSTINCREMENT:
					ExpressionNode add;
					Vector<ExpressionNode> addArguments = new Vector<ExpressionNode>();
					Vector<ExpressionNode> incrementArguments = new Vector<ExpressionNode>();

					addArguments
							.add(((OperatorNode) expression).getArgument(0));
					addArguments.add(factory.newIntegerConstantNode(
							expression.getSource(), "1"));
					add = factory.newOperatorNode(expression.getSource(),
							Operator.PLUS, addArguments);
					incrementArguments.add(((OperatorNode) expression)
							.getArgument(0));
					incrementArguments.add(add);
					result = factory.newExpressionStatementNode(factory
							.newOperatorNode(expression.getSource(),
									Operator.ASSIGN, incrementArguments));
					break;
				case PREDECREMENT:
				case POSTDECREMENT:
					ExpressionNode subtract;
					Vector<ExpressionNode> subtractArguments = new Vector<ExpressionNode>();
					Vector<ExpressionNode> decrementArguments = new Vector<ExpressionNode>();

					subtract = factory.newOperatorNode(expression.getSource(),
							Operator.PLUS, subtractArguments);
					decrementArguments.add(((OperatorNode) expression)
							.getArgument(0));
					decrementArguments.add(subtract);
					result = factory.newExpressionStatementNode(factory
							.newOperatorNode(expression.getSource(),
									Operator.ASSIGN, decrementArguments));
					break;
				case ASSIGN:
					ExpressionNode rhs = ((OperatorNode) expression)
							.getArgument(1);

					if (isSEF(rhs)) {
						result = statement;
					} else {
						SideEffectFreeTriple triple;
						Vector<BlockItemNode> blockItems = new Vector<BlockItemNode>();

						if (rhs instanceof FunctionCallNode) {
							// TODO check that arguments are SEF
							result = statement;
						} else if (rhs instanceof SpawnNode) {
							// TODO check that arguments are SEF
							result = statement;
						} else {
							Vector<ExpressionNode> assignArguments = new Vector<ExpressionNode>();

							assignArguments.add(((OperatorNode) expression)
									.getArgument(0));
							triple = processExpression(rhs);
							blockItems.addAll(triple.getBefore());
							assignArguments.add(triple.getExpression());
							blockItems.add(factory
									.newExpressionStatementNode(factory
											.newOperatorNode(
													expression.getSource(),
													Operator.ASSIGN,
													assignArguments)));
							blockItems.addAll(triple.getAfter());
							result = factory.newCompoundStatementNode(
									statement.getSource(), blockItems);
						}
						break;
					}
				case PLUS:
				case MINUS:
				case DIV:
				case TIMES:
					// TODO: Need to process these for precedent, or is this
					// taken care of?
					ExpressionNode left = ((OperatorNode) expression)
							.getArgument(0);
					ExpressionNode right = ((OperatorNode) expression)
							.getArgument(1);
					SideEffectFreeTriple leftTriple = processExpression(left);
					SideEffectFreeTriple rightTriple = processExpression(right);
					Vector<ExpressionNode> operands = new Vector<ExpressionNode>();
					StatementNode sideEffectFreeStatement;
					Vector<BlockItemNode> blockItems = new Vector<BlockItemNode>();

					operands.add(leftTriple.getExpression());
					operands.add(rightTriple.getExpression());
					sideEffectFreeStatement = factory
							.newExpressionStatementNode(factory
									.newOperatorNode(statement.getSource(),
											((OperatorNode) expression)
													.getOperator(), operands));
					blockItems.addAll(leftTriple.getBefore());
					blockItems.addAll(rightTriple.getBefore());
					blockItems.add(sideEffectFreeStatement);
					blockItems.addAll(rightTriple.getAfter());
					blockItems.addAll(leftTriple.getAfter());
					result = factory.newCompoundStatementNode(
							statement.getSource(), blockItems);
					break;
				case PLUSEQ:
					if (isSEF(((OperatorNode) expression).getArgument(1))) {
						ExpressionNode addition;
						Vector<ExpressionNode> arguments = new Vector<ExpressionNode>();
						Vector<ExpressionNode> assignmentArguments = new Vector<ExpressionNode>();

						arguments.add(((OperatorNode) expression)
								.getArgument(0));
						arguments.add(((OperatorNode) expression)
								.getArgument(1));
						addition = factory.newOperatorNode(
								expression.getSource(), Operator.PLUS,
								arguments);
						assignmentArguments.add(((OperatorNode) expression)
								.getArgument(0));
						assignmentArguments.add(addition);
						result = factory.newExpressionStatementNode(factory
								.newOperatorNode(expression.getSource(),
										Operator.ASSIGN, assignmentArguments));
					} else {
						throw new RuntimeException(
								"Side effects in a += are currently unsupported. "
										+ statement);
					}
					break;
				}
			} else {
				throw new RuntimeException(
						"Unsupported expression statement with side effects: "
								+ statement);
			}
		}
		return result;
	}

	private StatementNode loopStatement(LoopNode statement)
			throws SyntaxException {
		ExpressionNode condition = statement.getCondition();
		StatementNode newBody = processStatement(statement.getBody());
		StatementNode result;

		if (!isSEF(condition)) {
			throw new RuntimeException(
					"Loop conditions should be side effect free. " + condition);
		}
		switch (statement.getKind()) {
		case FOR:
			result = factory.newForLoopNode(statement.getSource(),
					((ForLoopNode) statement).getInitializer(), condition,
					((ForLoopNode) statement).getIncrementer(), newBody,
					statement.getInvariant());
			break;
		case DO_WHILE:
			result = factory.newDoLoopNode(statement.getSource(), condition,
					newBody, statement.getInvariant());
			break;
		default:
			result = factory.newWhileLoopNode(statement.getSource(), condition,
					newBody, statement.getInvariant());
		}
		return result;
	}

	private boolean isSEF(ExpressionNode expression) {
		boolean result = true;

		if (expression instanceof FunctionCallNode) {
			result = false;
		} else if (expression instanceof OperatorNode) {
			switch (((OperatorNode) expression).getOperator()) {
			case ASSIGN:
			case DIVEQ:
			case MINUSEQ:
			case MODEQ:
			case PLUSEQ:
			case POSTDECREMENT:
			case POSTINCREMENT:
			case PREDECREMENT:
			case PREINCREMENT:
			case SHIFTLEFTEQ:
			case SHIFTRIGHTEQ:
			case TIMESEQ:
				result = false;
				break;
			default:
				for (int i = 0; i < ((OperatorNode) expression)
						.getNumberOfArguments(); i++) {
					result = result
							&& isSEF(((OperatorNode) expression).getArgument(i));
				}
			}
		} else if (expression instanceof SpawnNode) {
			result = false;
		} else if (expression instanceof CastNode) {
			result = isSEF(((CastNode) expression).getArgument());
		}
		return result;
	}

	private SideEffectFreeTriple processExpression(ExpressionNode expression)
			throws SyntaxException {
		SideEffectFreeTriple result;

		if (expression instanceof OperatorNode) {
			switch (((OperatorNode) expression).getOperator()) {
			case ASSIGN:
				result = assign((OperatorNode) expression);
				break;
			case PREINCREMENT:
			case PREDECREMENT:
			case POSTINCREMENT:
			case POSTDECREMENT:
				result = incrementOrDecrement((OperatorNode) expression);
				break;
			case PLUS:
			case MINUS:
			case DIV:
			case TIMES:
			case SUBSCRIPT:
				// TODO: Need to process these for precedent, or is this taken
				// care of?
				ExpressionNode left = ((OperatorNode) expression)
						.getArgument(0);
				ExpressionNode right = ((OperatorNode) expression)
						.getArgument(1);
				SideEffectFreeTriple leftTriple = processExpression(left);
				SideEffectFreeTriple rightTriple = processExpression(right);
				Vector<ExpressionNode> operands = new Vector<ExpressionNode>();
				ExpressionNode sideEffectFreeExpression;
				Vector<BlockItemNode> before = new Vector<BlockItemNode>();
				Vector<BlockItemNode> after = new Vector<BlockItemNode>();

				operands.add(leftTriple.getExpression());
				operands.add(rightTriple.getExpression());
				sideEffectFreeExpression = factory.newOperatorNode(
						expression.getSource(),
						((OperatorNode) expression).getOperator(), operands);
				before.addAll(leftTriple.getBefore());
				before.addAll(rightTriple.getBefore());
				after.addAll(leftTriple.getAfter());
				after.addAll(leftTriple.getAfter());
				result = new SideEffectFreeTriple(before,
						sideEffectFreeExpression, after);
				break;
			default:
				throw new RuntimeException(
						"Node should not have side effects: " + expression);
			}
		} else if (isSEF(expression)) {
			result = new SideEffectFreeTriple(new Vector<BlockItemNode>(),
					expression, new Vector<BlockItemNode>());
		} else {
			throw new RuntimeException("Unknown side effect expression "
					+ expression);
		}
		return result;
	}

	private SideEffectFreeTriple assign(OperatorNode assign)
			throws SyntaxException {
		Vector<BlockItemNode> before = new Vector<BlockItemNode>();
		Vector<BlockItemNode> after = new Vector<BlockItemNode>();

		assert assign.getOperator() == Operator.ASSIGN;
		// Assume left hand side is side effect free.
		if (isSEF(assign.getArgument(1))) {
			before.add(factory.newExpressionStatementNode(assign));
		} else {
			SideEffectFreeTriple rhs = processExpression(assign.getArgument(1));
			Vector<ExpressionNode> arguments = new Vector<ExpressionNode>();

			before.addAll(rhs.getBefore());
			arguments.add(assign.getArgument(0));
			arguments.add(rhs.getExpression());
			before.add(factory.newExpressionStatementNode(factory
					.newOperatorNode(assign.getSource(), Operator.ASSIGN,
							arguments)));
			after.addAll(rhs.getAfter());
		}
		return new SideEffectFreeTriple(before, assign.getArgument(0), after);
	}

	private SideEffectFreeTriple incrementOrDecrement(OperatorNode operator)
			throws SyntaxException {
		Vector<BlockItemNode> before = new Vector<BlockItemNode>();
		Vector<BlockItemNode> after = new Vector<BlockItemNode>();
		ExpressionNode base = operator.getArgument(0);
		OperatorNode.Operator operation = operator.getOperator();
		ExpressionNode math, assignment;
		Vector<ExpressionNode> assignArguments = new Vector<ExpressionNode>();
		Vector<ExpressionNode> mathArguments = new Vector<ExpressionNode>();

		mathArguments.add(base);
		mathArguments.add(factory.newIntegerConstantNode(operator.getSource(),
				"1"));
		switch (operation) {
		case PREINCREMENT:
		case POSTINCREMENT:
			math = factory.newOperatorNode(operator.getSource(), Operator.PLUS,
					mathArguments);
			break;
		default:
			math = factory.newOperatorNode(operator.getSource(),
					Operator.MINUS, mathArguments);
		}
		assignArguments.add(base);
		assignArguments.add(math);
		assignment = factory.newOperatorNode(operator.getSource(),
				Operator.ASSIGN, assignArguments);
		switch (operation) {
		case PREINCREMENT:
		case PREDECREMENT:
			before.add(factory.newExpressionStatementNode(assignment));
			break;
		default:
			after.add(factory.newExpressionStatementNode(assignment));
		}
		return new SideEffectFreeTriple(before, base, after);
	}
}
