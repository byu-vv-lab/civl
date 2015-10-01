package edu.udel.cis.vsl.civl.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.PointerTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.civl.transform.IF.SvcompTransformer;

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
public class SvcompWorker extends BaseWorker {

	private final static String PTHREAD_PREFIX = "pthread_";

	private final static String PTHREAD_HEADER = "pthread.h";

	private final static String IO_PREFIX = "_IO_";

	private final static String IO_HEADER = "stdio.h";

	private boolean needsPthreadHeader = false;

	private boolean needsIoHeader = false;

	public SvcompWorker(ASTFactory astFactory) {
		super(SvcompTransformer.LONG_NAME, astFactory);
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> rootNode = ast.getRootNode();

		ast.release();
		this.removeIoNodes(rootNode);
		this.removePthreadTypedefs(rootNode);
		ast = astFactory.newAST(rootNode, ast.getSourceFiles());
		ast = this.addHeaders(ast);
		return ast;
	}

	private AST addHeaders(AST ast) throws SyntaxException {
		if (needsPthreadHeader) {
			AST pthreadHeaderAST = this.parseSystemLibrary(PTHREAD_HEADER);

			ast = this.combineASTs(pthreadHeaderAST, ast);
		}
		if (needsIoHeader) {
			AST ioHeaderAST = this.parseSystemLibrary(IO_HEADER);

			ast = this.combineASTs(ioHeaderAST, ast);
		}
		return ast;
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
	 * @param root
	 */
	private void removeIoNodes(SequenceNode<BlockItemNode> root) {
		for (BlockItemNode item : root) {
			boolean toRemove = false;

			if (item instanceof TypedefDeclarationNode) {
				toRemove = isStructOrUnionOfIO(((TypedefDeclarationNode) item)
						.getTypeNode());
			} else if (item instanceof StructureOrUnionTypeNode) {
				toRemove = isStructOrUnionOfIO((StructureOrUnionTypeNode) item);
			} else if (item instanceof VariableDeclarationNode) {
				TypeNode type = ((VariableDeclarationNode) item).getTypeNode();

				if (type instanceof PointerTypeNode) {
					toRemove = this
							.isStructOrUnionOfIO(((PointerTypeNode) type)
									.referencedType());
				}
			}
			if (toRemove) {
				item.remove();
				this.needsIoHeader = true;
			}
		}
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
	private void removePthreadTypedefs(SequenceNode<BlockItemNode> root) {
		for (BlockItemNode item : root) {
			if (item instanceof TypedefDeclarationNode) {
				TypedefDeclarationNode typedef = (TypedefDeclarationNode) item;

				if (typedef.getName().startsWith(PTHREAD_PREFIX)) {
					typedef.remove();
					needsPthreadHeader = true;
				}
			}
		}
	}

}
