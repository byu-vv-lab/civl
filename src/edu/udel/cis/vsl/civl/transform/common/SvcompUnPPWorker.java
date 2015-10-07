package edu.udel.cis.vsl.civl.transform.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.parse.IF.CParser;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.transform.IF.SvcompUnPPTransformer;

/**
 * For *.i files, pruner will be applied first, and then svcomp transformer, and
 * link with implementation source, and then apply other language/standard
 * transformers as usual.
 * 
 * This class is responsible for the svcomp transformation
 * 
 * @author Manchun Zheng
 *
 */
public class SvcompUnPPWorker extends BaseWorker {

	private final static String PTHREAD_PREFIX = "pthread_";

	private final static String PTHREAD_HEADER = "pthread.h";

	private final static String IO_PREFIX = "_IO_";

	private final static String IO_HEADER = "stdio.h";

	private final static String EXIT = "exit";

	private final static String STDLIB_HEADER = "stdlib.h";

	private boolean needsPthreadHeader = false;

	private boolean needsIoHeader = false;

	private boolean needsStdlibHeader = false;

	private final static int UPPER_BOUND = 10;

	// private int scale_var_count = 0;

	private final static String SCALE_VAR = "scale";

	private Map<Integer, VariableDeclarationNode> numberVariableMap = new HashMap<>();

	public SvcompUnPPWorker(ASTFactory astFactory) {
		super(SvcompUnPPTransformer.LONG_NAME, astFactory);
		this.identifierPrefix = "_" + SvcompUnPPTransformer.CODE;
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> rootNode = ast.getRootNode();

		ast.release();
		// this.removeIoNodes(rootNode);
		// this.removePthreadTypedefs(rootNode);
		this.removeNodes(rootNode);
		rootNode = downScaler(rootNode);
		this.completeSources(rootNode);
		ast = astFactory.newAST(rootNode, ast.getSourceFiles());
		// ast.prettyPrint(System.out, false);
		ast = this.addHeaders(ast);
		return ast;
	}

	private SequenceNode<BlockItemNode> downScaler(
			SequenceNode<BlockItemNode> root) throws SyntaxException {
		List<BlockItemNode> newItems = new ArrayList<>();
		VariableDeclarationNode scale_bound = this.variableDeclaration(
				this.identifierPrefix + "_" + SCALE_VAR,
				this.basicType(BasicTypeKind.INT));

		scale_bound.getTypeNode().setInputQualified(true);
		newItems.add(this.assumeFunctionDeclaration(this.newSource("$assume",
				CParser.DECLARATION)));
		newItems.add(scale_bound);
		for (VariableDeclarationNode varNode : numberVariableMap.values()) {
			newItems.add(varNode);
			newItems.add(this.assumeNode(this.nodeFactory.newOperatorNode(
					varNode.getSource(), Operator.LTE,
					this.identifierExpression(varNode.getName()),
					this.identifierExpression(scale_bound.getName()))));
		}
		for (BlockItemNode item : root) {
			if (item == null)
				continue;

			downScalerWork(item);
			item.remove();
			newItems.add(item);
		}
		return this.nodeFactory.newSequenceNode(root.getSource(),
				"Translation Unit", newItems);
	}

	private void downScalerWork(ASTNode node) throws SyntaxException {
		if (node instanceof OperatorNode) {
			OperatorNode operatorNode = (OperatorNode) node;
			int numArgs = operatorNode.getNumberOfArguments();

			for (int i = 0; i < numArgs; i++) {
				ExpressionNode arg = operatorNode.getArgument(i);

				if (arg instanceof IntegerConstantNode) {
					ExpressionNode newArg = this
							.getDownScaledExpression((IntegerConstantNode) arg);

					if (newArg != null)
						operatorNode.setArgument(i, newArg);
				} else if (arg instanceof OperatorNode) {
					downScalerWork(arg);
				}
			}
			// Operator operator = operatorNode.getOperator();
			// ExpressionNode upper = null;
			// int argIndex = -1;
			//
			// if (operator == Operator.LT || operator == Operator.LTE) {
			// upper = operatorNode.getArgument(1);
			// argIndex = 1;
			// } else if (operator == Operator.GT || operator == Operator.GTE) {
			// upper = operatorNode.getArgument(0);
			// argIndex = 0;
			// }
			// if (upper != null) {
			// if (upper instanceof IntegerConstantNode) {
			// ExpressionNode newArgument = this
			// .getDownScaledExpression((IntegerConstantNode) upper);
			//
			// if (newArgument != null)
			// operatorNode.setArgument(argIndex, newArgument);
			// }
			// }
		} else {
			for (ASTNode child : node.children()) {
				if (child == null)
					continue;
				downScalerWork(child);
			}
		}
	}

	private ExpressionNode getDownScaledExpression(IntegerConstantNode constant)
			throws SyntaxException {
		int upperValue = ((IntegerConstantNode) constant).getConstantValue()
				.getIntegerValue().intValue();

		if (numberVariableMap.containsKey(upperValue)) {
			return this.identifierExpression(numberVariableMap.get(upperValue)
					.getName());
		} else if (numberVariableMap.containsKey(upperValue - 1)) {
			ExpressionNode variableIdentifier = this
					.identifierExpression(numberVariableMap.get(upperValue - 1)
							.getName());

			return this.nodeFactory.newOperatorNode(constant.getSource(),
					Operator.PLUS, variableIdentifier, this.integerConstant(1));
		} else if (numberVariableMap.containsKey(upperValue + 1)) {
			ExpressionNode variableIdentifier = this
					.identifierExpression(numberVariableMap.get(upperValue + 1)
							.getName());

			this.nodeFactory
					.newOperatorNode(constant.getSource(), Operator.MINUS,
							variableIdentifier, this.integerConstant(1));
		}
		return null;
	}

	private AST addHeaders(AST ast) throws SyntaxException {
		if (needsIoHeader) {
			AST ioHeaderAST = this.parseSystemLibrary(IO_HEADER);

			ast = this.combineASTs(ioHeaderAST, ast);
		}
		if (needsStdlibHeader) {
			AST stdlibHeaderAST = this.parseSystemLibrary(STDLIB_HEADER);

			ast = this.combineASTs(stdlibHeaderAST, ast);
		}
		// ast = Transform.newTransformer("prune",
		// ast.getASTFactory()).transform(
		// ast);
		if (needsPthreadHeader) {
			AST pthreadHeaderAST = this.parseSystemLibrary(PTHREAD_HEADER);

			ast = this.combineASTs(pthreadHeaderAST, ast);
		}
		return ast;
	}

	private void removeNodes(SequenceNode<BlockItemNode> root) {
		for (BlockItemNode item : root) {
			boolean toRemove = false;

			if (item == null)
				continue;
			toRemove = isQualifiedIoNode(item);
			if (!toRemove)
				toRemove = isQualifiedPthreadNode(item);
			if (!toRemove) {
				isStdlibNode(item);
			}
			if (toRemove)
				item.remove();
			else if (item instanceof VariableDeclarationNode) {
				VariableDeclarationNode varDecl = (VariableDeclarationNode) item;
				TypeNode type = varDecl.getTypeNode();

				if (type instanceof ArrayTypeNode) {
					ArrayTypeNode arrayType = (ArrayTypeNode) type;
					ExpressionNode extent = arrayType.getExtent();

					if (extent instanceof IntegerConstantNode) {
						// TODO for now make the input variables the same
						// storage class as the array
						ExpressionNode newExtent = this.factorNewInputVariable(
								(IntegerConstantNode) extent,
								varDecl.hasStaticStorage());

						if (newExtent != null)
							arrayType.setExtent(newExtent);
					}
				}
			} else if (item instanceof FunctionDefinitionNode) {
				this.checkBigLoopBound(((FunctionDefinitionNode) item)
						.getBody());
			}
		}
	}

	private ExpressionNode factorNewInputVariable(IntegerConstantNode intConst,
			boolean isStatic) {
		int intValue = intConst.getConstantValue().getIntegerValue().intValue();

		if (intValue > UPPER_BOUND) {
			VariableDeclarationNode scaleVariable = this.variableDeclaration(
					this.newUniqueIdentifier(SCALE_VAR),
					this.basicType(BasicTypeKind.INT));

			scaleVariable.getTypeNode().setInputQualified(true);
			scaleVariable.setStaticStorage(isStatic);
			this.numberVariableMap.put(intValue, scaleVariable);
			return this.identifierExpression(scaleVariable.getName());
		}
		return null;
	}

	private void checkBigLoopBound(ASTNode node) {
		if (node instanceof ForLoopNode) {
			ExpressionNode condition = ((ForLoopNode) node).getCondition();

			if (condition instanceof OperatorNode) {
				OperatorNode operatorNode = (OperatorNode) condition;
				Operator operator = operatorNode.getOperator();
				ExpressionNode upper = null;
				int argIndex = -1;

				if (operator == Operator.LT || operator == Operator.LTE) {
					upper = operatorNode.getArgument(1);
					argIndex = 1;
				} else if (operator == Operator.GT || operator == Operator.GTE) {
					upper = operatorNode.getArgument(0);
					argIndex = 0;
				}
				if (upper != null) {
					if (upper instanceof IntegerConstantNode) {
						ExpressionNode newArg = this.factorNewInputVariable(
								(IntegerConstantNode) upper, false);

						if (newArg != null)
							operatorNode.setArgument(argIndex, newArg);
					}
				}
			}
		} else {
			for (ASTNode child : node.children()) {
				if (child != null)
					this.checkBigLoopBound(child);
			}
		}
	}

	private void isStdlibNode(BlockItemNode item) {
		if (item instanceof FunctionDeclarationNode) {
			FunctionDeclarationNode functionDecl = (FunctionDeclarationNode) item;

			if (functionDecl.getName().equals(EXIT))
				this.needsStdlibHeader = true;
		}
	}

	/**
	 * Removed any node in the root scope that satisfies at least one of the
	 * following:
	 * <ul>
	 * <li>
	 * struct definition in the form: <code>struct _IO_...</code>;</li>
	 * <li>
	 * variable declaration of the type <code>struct (_IO_...)*</code></li>
	 * </ul>
	 * 
	 * 
	 * <code>typedef SOME_TYPE pthread_*</code>. i.e., the identifier starts
	 * with "pthread_".
	 * 
	 * @param node
	 */
	private boolean isQualifiedIoNode(BlockItemNode node) {
		boolean toRemove = false;

		if (node instanceof TypedefDeclarationNode) {
			toRemove = isStructOrUnionOfIO(((TypedefDeclarationNode) node)
					.getTypeNode());
		} else if (node instanceof StructureOrUnionTypeNode) {
			toRemove = isStructOrUnionOfIO((StructureOrUnionTypeNode) node);
		} else if (node instanceof VariableDeclarationNode) {
			TypeNode type = ((VariableDeclarationNode) node).getTypeNode();

			if (type instanceof PointerTypeNode) {
				toRemove = this.isStructOrUnionOfIO(((PointerTypeNode) type)
						.referencedType());
			}
		}
		if (toRemove) {
			this.needsIoHeader = true;
		}
		return toRemove;
	}

	/**
	 * returns true iff the given type node is a struct or union type which has
	 * the tag starting with _IO_
	 * 
	 * @param typeNode
	 * @return
	 */
	private boolean isStructOrUnionOfIO(TypeNode typeNode) {
		if (typeNode instanceof StructureOrUnionTypeNode) {
			StructureOrUnionTypeNode structOrUnion = (StructureOrUnionTypeNode) typeNode;

			if (structOrUnion.getName().startsWith(IO_PREFIX))
				return true;
		}
		return false;
	}

	/**
	 * Removed any typedef declaration node in the root scope that has the form:
	 * <code>typedef SOME_TYPE pthread_*</code>. i.e., the identifier starts
	 * with "pthread_".
	 * 
	 * @param root
	 */
	private boolean isQualifiedPthreadNode(BlockItemNode item) {
		if (item instanceof TypedefDeclarationNode) {
			TypedefDeclarationNode typedef = (TypedefDeclarationNode) item;

			if (typedef.getName().startsWith(PTHREAD_PREFIX)) {
				needsPthreadHeader = true;
				return true;
			}
		} else if (item instanceof StructureOrUnionTypeNode) {
			StructureOrUnionTypeNode structOrUnion = (StructureOrUnionTypeNode) item;

			if (structOrUnion.getName().startsWith(PTHREAD_PREFIX)) {
				needsPthreadHeader = true;
				return true;
			}
		}
		return false;
	}

}
