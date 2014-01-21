package edu.udel.cis.vsl.civl.model.IF.statement;

import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;

/**
 * An MPI standard-mode blocking send statement. Syntax:
 * 
 * <pre>
 * int MPI_Send(const void* buf, int count, MPI_Datatype datatype,
 *              int dest, int tag, MPI_Comm comm)
 * </pre>
 * 
 * Note that there is a return value, which is used to return an error code.
 * Under normal circumstances it returns 0.
 * 
 * TODO: complete java-docs
 * 
 * @author siegel
 * 
 */
public interface MPISendStatement extends Statement {

	/**
	 * Returns the send buffer argument.
	 * 
	 * @return the send buffer argument
	 */
	Expression getBuffer();

	Expression getCount();

	Expression getDatatype();

	Expression getDestination();

	Expression getTag();

	Expression getCommunicator();

	/**
	 * Returns the left hand side, if the send statement occurs in an
	 * assignment. May be null.
	 * 
	 * @return left hand side of assignment or null
	 */
	LHSExpression getLeftHandSize();

}
