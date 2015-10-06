package edu.udel.cis.vsl.civl.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
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

	public SvcompUnPPWorker(ASTFactory astFactory) {
		super(SvcompUnPPTransformer.LONG_NAME, astFactory);
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> rootNode = ast.getRootNode();

		ast.release();
		// this.removeIoNodes(rootNode);
		// this.removePthreadTypedefs(rootNode);
		this.removeNodes(rootNode);
		ast = astFactory.newAST(rootNode, ast.getSourceFiles());
		// ast.prettyPrint(System.out, false);
		ast = this.addHeaders(ast);
		return ast;
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
