package edu.udel.cis.vsl.civl.model.IF;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.IntegerLiteralExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.VariableExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.AssignStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPrimitiveType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

public interface MPIModelFactory extends ModelFactory {

	MPISendStatement mpiSendStatement(CIVLSource source, Location location,
			Scope scope, LHSExpression lhs, ArrayList<Expression> arguments);

	/**
	 * Create a new location.
	 * 
	 * @param source
	 *            The CIVL source of the location
	 * @param scope
	 *            The scope containing this location.
	 * @return The new location.
	 */
	Location location(Scope scope);

	Variable variable(CIVLPrimitiveType type, Identifier name, int vid);

	Identifier identifier(String name);

	Scope scope(Scope parent, LinkedHashSet<Variable> variables,
			CIVLFunction function);

	/**
	 * An integer literal expression.
	 * 
	 * @param value
	 *            The (arbitrary precision) integer value.
	 * @return The integer literal expression.
	 */
	IntegerLiteralExpression integerLiteralExpression(BigInteger value);

	/**
	 * An assignment statement.
	 * 
	 * @param source
	 *            The source location for this statement.
	 * @param lhs
	 *            The left hand side of the assignment.
	 * @param rhs
	 *            The right hand side of the assignment.
	 * @param isInitialization
	 *            True iff the assign statement to create is translated from a
	 *            the initialization node of variable declaration node.
	 * @return A new assignment statement.
	 */
	AssignStatement assignStatement(Location source, LHSExpression lhs,
			Expression rhs, boolean isInitialization);

	/**
	 * A variable expression.
	 * 
	 * @param variable
	 *            The variable being referenced.
	 * @return The variable expression.
	 */
	VariableExpression variableExpression(Variable variable);

	/**
	 * A fork statement. Used to spawn a new process.
	 * 
	 * @param source
	 *            The source location for this fork statement.
	 * @param isCall
	 *            is this a call statement (not spawn statement)?
	 * @param function
	 *            A function
	 * @param arguments
	 *            The arguments to the function.
	 * @return A new fork statement.
	 */
	CallOrSpawnStatement callOrSpawnStatement(Location source, boolean isCall,
			CIVLFunction function, List<Expression> arguments);

}
