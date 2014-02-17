package edu.udel.cis.vsl.civl.model.common.expression;

import java.util.List;
import java.util.Set;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.SystemGuardExpression;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLHeapType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;

/**
 * A system guard expression stores the necessary information (library, function
 * name and arguments of the function call) for calculating the guard of a
 * system function call.
 * 
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class CommnSystemGuardExpression extends CommonExpression implements
		SystemGuardExpression {

	/* *************************** Instance Fields ************************* */

	/**
	 * The library that the invoked function belongs to.
	 */
	private String library;

	/**
	 * The name of the invoked function.
	 */
	private String functionName;

	/**
	 * The list of arguments that the function call uses.
	 */
	private Expression[] arguments;

	/* **************************** Constructors *************************** */

	public CommnSystemGuardExpression(CIVLSource source, String library,
			String function, List<Expression> args, CIVLType type) {
		super(source);
		this.library = library;
		this.functionName = function;
		this.arguments = new Expression[args.size()];
		args.toArray(this.arguments);
		this.expressionType = type;
	}

	/* *********************** Methods from Expression ********************* */

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.SYSTEM_GUARD;
	}

	@Override
	public Set<Variable> variableAddressedOf(Scope scope,
			CIVLHeapType heapType, CIVLType commType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variable> variableAddressedOf(CIVLHeapType heapType,
			CIVLType commType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ***************** Methods from SystemGuardExpression *************** */

	@Override
	public String library() {
		return this.library;
	}

	@Override
	public String functionName() {
		return this.functionName;
	}

	@Override
	public Expression[] arguments() {
		return this.arguments;
	}

	/* *********************** Methods from Object ********************* */
	@Override
	public String toString() {
		return "guard[" + this.library + "." + this.functionName + "()]";
	}

}
