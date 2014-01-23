package edu.udel.cis.vsl.civl.model.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.civl.err.CIVLException;
import edu.udel.cis.vsl.civl.model.IF.CIVLFunction;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.variable.Variable;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.GMCConfiguration;

public class MPIModelBuilderWorker extends ModelBuilderWorker {
	private CommonMPIModelFactory mpiFactory;

	public MPIModelBuilderWorker(GMCConfiguration config, CommonMPIModelFactory factory,
			Program program, String name) {
		super(config, factory, program, name);
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

		//initialization(system);
		systemFunctionTranslator.translateRootFunction(systemScope, rootNode);
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
			throw new CIVLException("Program must have a main function.",
					mpiFactory.sourceOf(rootNode));
		}
//		// translate main function, using system as the CIVL function object,
//		// and combining initialization statements with its body
//		// translateFunctionDefinitionNode(mainFunctionNode, system,
//		// initialization);
//		translateUndefinedFunctions();
//		completeCallOrSpawnStatements();
//		factory.completeHeapType(heapType, mallocStatements);
//		completeBundleType();
//		completeModel(system);
//		this.staticAnalysis();
	}

}
