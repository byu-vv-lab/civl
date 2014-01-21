package edu.udel.cis.vsl.civl.model.common;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;

/**
 * This class translates MPI function call nodes into the corresponding MPI
 * Statements.
 * 
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 */
public class MPIStatementFactory {

	/***************************** Static Fields *****************************/

	/**
	 * The function name of MPI Send.
	 */
	static final String MPI_SEND = "$MPI_Send";

	/**
	 * The function name of MPI Receive.
	 */
	static final String MPI_RECV = "$MPI_Recv";

	/**
	 * The function name of MPI Barrier.
	 */
	static final String MPI_BARRIER = "$MPI_Barrier";

	/**
	 * The function name of MPI Isend.
	 */
	static final String MPI_ISEND = "$MPI_Isend";

	/**
	 * The function name of MPI Ireceive.
	 */
	static final String MPI_IRECV = "$MPI_Irecv";

	/**
	 * The function name of MPI Wait.
	 */
	static final String MPI_WAIT = "$MPI_Wait";

	/**************************** Instance Fields ****************************/

	// /**
	// * The model factory, used in the translation of MPI function calls.
	// */
	// private ModelFactory factory;

	/****************************** Constructors *****************************/

	/**
	 * Create a new instance of MPIStatementFactory.
	 * 
	 * @param factory
	 *            The model factory to be used for translating AST nodes.
	 */
	public MPIStatementFactory(ModelFactory factory) {
		// this.factory = factory;
	}

	/************************ Package-private Methods ************************/

	/**
	 * Translate a MPI_Send function call to an instance of
	 * {@link edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement}.
	 * 
	 * @param scope
	 *            The scope of this function call.
	 * @param functionCallNode
	 *            The AST node to be translated.
	 * @return A fragment containing exactly one statement, i.e., the MPI_Send
	 *         statement.
	 */
	Fragment translateMPI_SEND(Scope scope, FunctionCallNode functionCallNode) {
		// TODO to be implemented
		return null;
	}

}
