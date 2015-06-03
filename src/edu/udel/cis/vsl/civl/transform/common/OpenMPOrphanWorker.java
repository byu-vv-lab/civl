package edu.udel.cis.vsl.civl.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * This transformer transforms away the orphaned constructs of OpenMP programs.
 * 
 */
public class OpenMPOrphanWorker extends BaseWorker {

	public OpenMPOrphanWorker(ASTFactory astFactory) {
		super("OpenMPOrphanTransformer", astFactory);
		this.identifierPrefix = "$omp_orphan_";
	}

	// TODO implement me
	@Override
	public AST transform(AST ast) throws SyntaxException {
		return ast;
		// return null;
	}

}
