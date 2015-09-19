package edu.udel.cis.vsl.civl.model.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CollectiveExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.civl.model.IF.AbstractFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLTypeFactory;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.ContractClauseExpression.ContractKind;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.SystemFunctionCallExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;

public class ContractTranslator extends FunctionTranslator {
	/**
	 * The string type name of the Result Expression:<br>
	 * An special expression used to represent the result of a function in
	 * function contracts.
	 */
	public static final String contractResultName = "$result";

	private CIVLFunction function;

	private ModelFactory modelFactory;

	private CIVLTypeFactory typeFactory;

	private ModelBuilderWorker modelBuilder;

	private Expression processesGroup;

	private List<SystemFunctionCallExpression> contractCalls = null;

	/******************** Constructor ********************/
	ContractTranslator(ModelBuilderWorker modelBuilder,
			ModelFactory modelFactory, CIVLTypeFactory typeFactory,
			CIVLFunction function) {
		super(modelBuilder, modelFactory, function);
		this.modelFactory = modelFactory;
		this.typeFactory = typeFactory;
		this.modelBuilder = modelBuilder;
		this.function = function;
	}

	// TODO:doc
	public CIVLFunction translateContractNode(ContractNode contractNode) {
		// A processesGroup is associated to a contractNode, each time
		// processing a new contractNode, reset the global field of
		// processesGroup, ditto for contractCalls:
		processesGroup = null;
		contractCalls = null;
		if (contractNode instanceof EnsuresNode) {
			ContractClauseExpression clause = translateContractExpressionNode(
					((EnsuresNode) contractNode).getExpression(),
					function.outerScope(), modelFactory.sourceOf(contractNode),
					ContractKind.ENSURES);

			function.addPostcondition(clause);
		} else if (contractNode instanceof RequiresNode) {
			ContractClauseExpression clause = translateContractExpressionNode(
					((RequiresNode) contractNode).getExpression(),
					function.outerScope(), modelFactory.sourceOf(contractNode),
					ContractKind.REQUIRES);

			function.addPrecondition(clause);
		}
		return function;
	}

	@Override
	protected Expression translateFunctionCallExpression(
			FunctionCallNode callNode, Scope scope) {
		Expression result;
		ExpressionNode functionExpression = callNode.getFunction();
		Function callee;
		CIVLFunction civlFunction;
		String functionName;
		CIVLSource source = modelFactory.sourceOf(callNode);

		if (functionExpression instanceof IdentifierExpressionNode) {
			callee = (Function) ((IdentifierExpressionNode) functionExpression)
					.getIdentifier().getEntity();
		} else
			throw new CIVLUnimplementedFeatureException(
					"Function call must use identifier for now: "
							+ functionExpression.getSource());
		civlFunction = modelBuilder.functionMap.get(callee);
		functionName = civlFunction.name().name();
		assert civlFunction != null;
		if (civlFunction instanceof AbstractFunction) {
			List<Expression> arguments = new ArrayList<Expression>();

			for (int i = 0; i < callNode.getNumberOfArguments(); i++) {
				Expression actual = translateExpressionNode(
						callNode.getArgument(i), scope, true);

				actual = arrayToPointer(actual);
				arguments.add(actual);
			}
			result = modelFactory.abstractFunctionCallExpression(
					modelFactory.sourceOf(callNode),
					(AbstractFunction) civlFunction, arguments);
			return result;
		} else if (civlFunction.isSystemFunction()) {
			/*
			 * Following system functions can be used as expressions in
			 * contract. These functions accept more than one arguments
			 * syntactically but only the first N arguments which explicitly are
			 * declared in the prototype and the ones added by CIVL model are
			 * significant. Rest arguments will be ignored and removed by CIVL
			 * model.
			 */
			/*
			 * For example : $mpi_isRecvBufEmpty(int x, ...): This function
			 * explicitly declares one argument, and CIVL model may insert one
			 * more argument with type MPI_Comm. So the final model of the
			 * function declaration will only be: $mpi_isRecvBuffEmpty(int x,
			 * MPI_Comm comm);
			 */
			switch (functionName) {
			// Functions with one explicit argument:
			case "$mpi_isRecvBufEmpty":
				return this.transformMsgBufferCall(civlFunction, functionName,
						scope, callNode, source);
			case "$mpi_isSendBufEmpty":
				return this.transformMsgBufferCall(civlFunction, functionName,
						scope, callNode, source);
			default:
			}
		}
		throw new CIVLUnimplementedFeatureException("Using function call: "
				+ functionName + "as expression in contract.");
	}

	// TODO:doc
	private Expression transformMsgBufferCall(CIVLFunction civlFunction,
			String functionName, Scope scope, FunctionCallNode callNode,
			CIVLSource source) {
		// A location only be used to construct a systemCallExpression,
		// it doesn't have income statements
		// and the outgoing statement dosen't have target:
		Location floatingLocation;
		List<Expression> arguments = new LinkedList<>();
		Expression functionExpr = modelFactory.functionIdentifierExpression(
				source, civlFunction);
		Expression firstArg;
		CallOrSpawnStatement civlSysFunctionCall;
		ExpressionNode argNode;
		SystemFunctionCallExpression result;

		floatingLocation = modelFactory.location(source, scope);
		argNode = callNode.getArgument(0);
		firstArg = translateExpressionNode(argNode, scope, true);
		arguments.add(firstArg);
		// Add Collective Group as the second argument
		assert processesGroup != null : "Building model for " + functionName
				+ "() but there is no collective group information";
		arguments.add(processesGroup);
		civlSysFunctionCall = modelFactory.callOrSpawnStatement(source,
				floatingLocation, true, functionExpr, arguments,
				modelFactory.trueExpression(null));
		result = modelFactory.systemFunctionCallExpression(civlSysFunctionCall);
		if (contractCalls == null)
			contractCalls = new LinkedList<>();
		contractCalls.add(result);
		return result;
	}

	// TODO:doc
	private ContractClauseExpression translateContractExpressionNode(
			ExpressionNode expressionNode, Scope scope, CIVLSource source,
			ContractKind kind) {
		ExpressionNode bodyNode, procsGroupNode;
		Expression body;

		if (expressionNode.expressionKind().equals(ExpressionKind.COLLECTIVE)) {
			bodyNode = ((CollectiveExpressionNode) expressionNode).getBody();
			procsGroupNode = ((CollectiveExpressionNode) expressionNode)
					.getProcessesGroupExpression();
		} else {
			bodyNode = expressionNode;
			procsGroupNode = null;
		}
		if (procsGroupNode != null)
			processesGroup = translateExpressionNode(procsGroupNode, scope,
					true);
		body = translateExpressionNode(bodyNode, scope, true);
		return modelFactory.contractClauseExpression(source,
				this.typeFactory.booleanType(), processesGroup, body, kind,
				contractCalls);
	}
}
