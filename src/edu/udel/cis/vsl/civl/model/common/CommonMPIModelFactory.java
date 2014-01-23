package edu.udel.cis.vsl.civl.model.common;

import java.util.ArrayList;
import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.common.statement.CommonMPISendStatement;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;

/**
 * This class translates MPI function call nodes into the corresponding MPI
 * Statements.
 * 
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 */
public class CommonMPIModelFactory extends CommonModelFactory {

	/* *************************** Static Fields *************************** */

	/**
	 * The function name of MPI Send.
	 */
	static final String MPI_SEND = "MPI_Send";

	/**
	 * The function name of MPI Receive.
	 */
	static final String MPI_RECV = "MPI_Recv";

	/**
	 * The function name of MPI Barrier.
	 */
	static final String MPI_BARRIER = "MPI_Barrier";

	/**
	 * The function name of MPI Isend.
	 */
	static final String MPI_ISEND = "MPI_Isend";

	/**
	 * The function name of MPI Ireceive.
	 */
	static final String MPI_IRECV = "MPI_Irecv";

	/**
	 * The function name of MPI Wait.
	 */
	static final String MPI_WAIT = "MPI_Wait";

	/* **************************** Constructors *************************** */

	/**
	 * Create a new instance of MPIStatementFactory.
	 * 
	 * @param factory
	 *            The model factory to be used for translating AST nodes.
	 */
	public CommonMPIModelFactory(SymbolicUniverse universe) {
		super(universe);
	}

	/* ********************** Package-private Methods ********************** */

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
	 * 
	 */
	MPISendStatement translateMPI_SEND(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments) {
		CommonMPISendStatement sendStatement = new CommonMPISendStatement(
				source, location, lhs, arguments);

		sendStatement.setStatementScope(join(scope, lhs.expressionScope()));
		return sendStatement;
	}

	/* ************************* private methods *************************** */
	private Scope join(Scope s0, Scope s1) {
		List<Scope> s0Ancestors = new ArrayList<Scope>();
		Scope s0Ancestor = s0;
		Scope s1Ancestor = s1;

		if (s0 == null) {
			return s1;
		} else if (s1 == null) {
			return s0;
		}
		s0Ancestors.add(s0Ancestor);
		while (s0Ancestor.parent() != null) {
			s0Ancestor = s0Ancestor.parent();
			s0Ancestors.add(s0Ancestor);
		}
		while (true) {
			if (s0Ancestors.contains(s1Ancestor)) {
				return s1Ancestor;
			}
			s1Ancestor = s1Ancestor.parent();
		}
	}
}
