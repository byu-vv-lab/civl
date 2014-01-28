package edu.udel.cis.vsl.civl.model.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.civl.err.CIVLException;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Fragment;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.MPIModelFactory;
import edu.udel.cis.vsl.civl.model.IF.Scope;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.GMCConfiguration;

public class MPIModelBuilderWorker extends ModelBuilderWorker {
	static final String NPROCS = "NPROCS";

	private MPIModelFactory mpiFactory;
	private Scope processMainScope;
	private CIVLFunction processMainFunction;

	public MPIModelBuilderWorker(GMCConfiguration config,
			MPIModelFactory factory, Program program, String name) {
		super(config, factory, program, name);
		this.mpiFactory = factory;
	}

	/* *************************** Public Methods ************************** */

	/**
	 * Build the MPI model from the AST
	 * 
	 * @throws CommandLineException
	 */
	@Override
	public void buildModel() throws CommandLineException {
		Identifier systemID = mpiFactory.identifier(mpiFactory.systemSource(),
				"_MPI_system");
		CIVLFunction system = mpiFactory.function(
				mpiFactory.sourceOf(program.getAST().getRootNode()), systemID,
				new ArrayList<Variable>(), null, null, null);
		ASTNode rootNode = program.getAST().getRootNode();
		MPIFunctionTranslator systemFunctionTranslator = new MPIFunctionTranslator(
				this, mpiFactory, system);
		Expression nprocsExpression;
		Fragment initialization;
		MPIFunctionTranslator processMainFunctionTranslator;

		initialization(system);
		// initialization(system);
		if (inputInitMap == null || !inputInitMap.containsKey(NPROCS)) {
			throw new CommandLineException(
					"NPROCS must be specified for running or verifying MPI programs.");
		}
		initializedInputs.add(NPROCS);
		nprocsExpression = nprocsExpression();
		this.mpiFactory.setNumberOfProcs(nprocsExpression);
		this.processMainFunction = systemFunctionTranslator
				.processMainFunction(systemScope, rootNode);
		this.processMainScope = this.processMainFunction.outerScope();
		initialization = systemFunctionTranslator.translateRootFunction(
				systemScope, rootNode, this.processMainScope);
		if (inputInitMap != null) {
			// if commandline specified input variables that do not
			// exist, throw exception...
			Set<String> commandLineVars = new HashSet<String>(
					inputInitMap.keySet());

			commandLineVars.removeAll(initializedInputs);
			if (!commandLineVars.isEmpty()) {
				String msg = "Program contains no input variables named ";
				boolean first = true;

				for (String name : commandLineVars) {
					if (first)
						first = false;
					else
						msg += ", ";
					msg += name;
				}
				throw new CommandLineException(msg);
			}
		}
		if (mainFunctionNode == null) {
			throw new CIVLException("A MPI program must have a main function.",
					mpiFactory.sourceOf(rootNode));
		}
		processMainFunctionTranslator = new MPIFunctionTranslator(this,
				mpiFactory, processMainFunction,
				this.mainFunctionNode.getBody());
		this.functionMap.put(mainFunctionNode.getEntity(), processMainFunction);
		processMainFunctionTranslator
				.translateProcessMainFunction(initialization);
		translateUndefinedFunctions();
		completeCallOrSpawnStatements();
		mpiFactory.completeHeapType(heapType, mallocStatements);
		completeBundleType();
		completeModel(system);
		this.staticAnalysis();
	}

	public Expression nprocsExpression() throws CommandLineException {
		Object nprocs = inputInitMap.get(NPROCS);

		if (nprocs != null) {
			initializedInputs.add(NPROCS);
			if (nprocs instanceof Integer)
				return mpiFactory.integerLiteralExpression(mpiFactory
						.systemSource(),
						new BigInteger(((Integer) nprocs).toString()));
			if (nprocs instanceof String)
				return mpiFactory.integerLiteralExpression(mpiFactory
						.systemSource(), new BigInteger((String) nprocs));
			else
				throw new CommandLineException(
						"Expected integer value for variable " + NPROCS
								+ " but saw " + nprocs);
		} else {
			throw new CommandLineException(
					"NPROCS must be specified for running or verifying MPI programs.");
		}
	}

	// public void setMpiSpawnStatement(CallOrSpawnStatement spawnMpi) {
	// this.mpiSpawnStatement = spawnMpi;
	// }
	//
	// public void setMpiProcessFunctionForSpawn(CIVLFunction function) {
	// this.mpiSpawnStatement.setFunction(function);
	// }

	public Scope processMainScope() {
		return this.processMainScope;
	}

	public CIVLFunction processMainFunction() {
		return this.processMainFunction;
	}
}
