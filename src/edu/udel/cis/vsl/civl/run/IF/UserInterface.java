package edu.udel.cis.vsl.civl.run.IF;

import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.astO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.bar;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.collectHeapsO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.collectProcessesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.collectScopesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.date;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.deadlockO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.debugO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.echoO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.enablePrintfO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.errorBoundO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.guiO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.guidedO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.idO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.inputO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.macroO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.maxdepthO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.minO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.ompLoopDecompO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.ompNoSimplifyO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.preprocO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.procBoundO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.randomO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.saveStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.seedO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showAmpleSetO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showAmpleSetWtStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showInputVarsO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showMemoryUnitsO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showModelO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showPathConditionO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showProgramO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showProverQueriesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showQueriesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showSavedStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showStatesO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showTimeO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.showTransitionsO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.simplifyO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.solveO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.statelessPrintfO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.svcompO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.sysIncludePathO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.traceO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.userIncludePathO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.verboseO;
import static edu.udel.cis.vsl.civl.config.IF.CIVLConstants.version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.udel.cis.vsl.abc.FrontEnd;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.parse.IF.ParseException;
import edu.udel.cis.vsl.abc.preproc.IF.Preprocessor;
import edu.udel.cis.vsl.abc.preproc.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.IF.Combiner;
import edu.udel.cis.vsl.abc.transform.IF.Transform;
import edu.udel.cis.vsl.civl.config.IF.CIVLConfiguration;
import edu.udel.cis.vsl.civl.config.IF.CIVLConstants;
import edu.udel.cis.vsl.civl.gui.IF.CIVL_GUI;
import edu.udel.cis.vsl.civl.model.IF.CIVLException;
import edu.udel.cis.vsl.civl.model.IF.CIVLInternalException;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.CIVLSyntaxException;
import edu.udel.cis.vsl.civl.model.IF.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelBuilder;
import edu.udel.cis.vsl.civl.model.IF.Models;
import edu.udel.cis.vsl.civl.run.IF.CommandLine.CommandKind;
import edu.udel.cis.vsl.civl.run.common.CIVLCommand;
import edu.udel.cis.vsl.civl.run.common.CIVLCommandFactory;
import edu.udel.cis.vsl.civl.run.common.CompareCommandLine;
import edu.udel.cis.vsl.civl.run.common.NormalCommandLine;
import edu.udel.cis.vsl.civl.run.common.NormalCommandLine.NormalCommandKind;
import edu.udel.cis.vsl.civl.semantics.IF.Transition;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.transform.IF.TransformerFactory;
import edu.udel.cis.vsl.civl.transform.IF.Transforms;
import edu.udel.cis.vsl.gmc.CommandLineException;
import edu.udel.cis.vsl.gmc.CommandLineParser;
import edu.udel.cis.vsl.gmc.GMCConfiguration;
import edu.udel.cis.vsl.gmc.GMCSection;
import edu.udel.cis.vsl.gmc.MisguidedExecutionException;
import edu.udel.cis.vsl.gmc.Option;
import edu.udel.cis.vsl.gmc.Trace;
import edu.udel.cis.vsl.sarl.SARL;
import edu.udel.cis.vsl.sarl.IF.SymbolicUniverse;
import edu.udel.cis.vsl.sarl.IF.config.Configurations;

/**
 * Basic command line and API user interface for CIVL tools.
 * 
 * Modularization of the user interface:
 * 
 * <ui> <li>preprocess</li> <li>ast</li> <li>program</li> <li>model</li> <li>
 * random run</li> <li>verify</li> <li>replay</li> </ui>
 * 
 * @author Stephen F. Siegel
 * 
 */
public class UserInterface {

	public final static boolean debug = false;

	public final static SortedMap<String, Option> definedOptions = new TreeMap<>();

	/* ************************* Instance fields *************************** */

	/**
	 * Stderr: used only if something goes wrong, like a bad command line arg,
	 * or internal exception
	 */
	private PrintStream err = System.err;

	/** Stdout: where most output is going to go, including error reports */
	private PrintStream out = System.out;

	/**
	 * The parser from the Generic Model Checking package used to parse the
	 * command line.
	 */
	private CommandLineParser parser;

	/**
	 * The time at which this instance of UserInterface was created.
	 */
	private double startTime;

	/**
	 * The ABC front end.
	 */
	private FrontEnd frontEnd = new FrontEnd();

	private TransformerFactory transformerFactory = Transforms
			.newTransformerFactory(frontEnd.getASTFactory());

	/* ************************** Static Code ***************************** */
	// initializes the command line options
	static {
		for (Option option : CIVLConstants.getAllOptions())
			definedOptions.put(option.name(), option);
		CIVLCommand.addShowOption(showModelO, verboseO, debugO, echoO,
				userIncludePathO, sysIncludePathO, svcompO, showInputVarsO,
				showProgramO, ompNoSimplifyO, ompLoopDecompO, macroO, preprocO,
				astO, showTimeO);
		CIVLCommand.addVerifyOrCompareOption(errorBoundO, verboseO, debugO,
				echoO, userIncludePathO, sysIncludePathO, showTransitionsO,
				showStatesO, showSavedStatesO, showQueriesO,
				showProverQueriesO, inputO, minO, maxdepthO, procBoundO,
				saveStatesO, simplifyO, solveO, enablePrintfO, showAmpleSetO,
				showAmpleSetWtStatesO, statelessPrintfO, deadlockO, svcompO,
				showProgramO, showPathConditionO, ompNoSimplifyO,
				ompLoopDecompO, collectProcessesO, collectScopesO,
				collectHeapsO, macroO, preprocO, astO, showTimeO,
				showMemoryUnitsO);
		CIVLCommand
				.addReplayOption(showModelO, verboseO, debugO, echoO,
						showTransitionsO, showStatesO, showSavedStatesO,
						showQueriesO, showProverQueriesO, idO, traceO,
						enablePrintfO, showAmpleSetO, showAmpleSetWtStatesO,
						statelessPrintfO, guiO, showProgramO,
						showPathConditionO, ompNoSimplifyO, collectProcessesO,
						collectScopesO, collectHeapsO, preprocO, astO,
						showMemoryUnitsO);
		CIVLCommand.addRunOption(errorBoundO, verboseO, randomO, guidedO,
				seedO, debugO, echoO, userIncludePathO, sysIncludePathO,
				showTransitionsO, showStatesO, showSavedStatesO, showQueriesO,
				showProverQueriesO, inputO, maxdepthO, procBoundO, simplifyO,
				enablePrintfO, showAmpleSetO, showAmpleSetWtStatesO,
				statelessPrintfO, deadlockO, svcompO, showProgramO,
				showPathConditionO, ompNoSimplifyO, ompLoopDecompO,
				collectProcessesO, collectScopesO, collectHeapsO, macroO,
				preprocO, astO, showMemoryUnitsO);
	}

	/* ************************** Constructors ***************************** */

	/**
	 * Creates a new instance of user interface.
	 */
	public UserInterface() {
		parser = new CommandLineParser(definedOptions.values());
	}

	/* ************************** Public Methods *************************** */

	/**
	 * Runs the appropriate CIVL tools based on the command line arguments.
	 * 
	 * @param args
	 *            command line arguments
	 * @return true iff everything succeeded and no errors were found
	 */
	public boolean run(String... args) {
		try {
			return runMain(args);
		} catch (CommandLineException e) {
			err.println(e.getMessage());
			err.println("Type \"civl help\" for command line syntax.");
			err.flush();
		}
		return false;
	}

	/**
	 * Runs the appropriate CIVL tools based on the command line arguments. This
	 * variant provided in case a collection is more convenient than an array.
	 * 
	 * @param args
	 *            command line arguments as collection
	 * @return true iff everything succeeded and no errors were found
	 */
	public boolean run(Collection<String> args) {
		return run(args.toArray(new String[args.size()]));
	}

	/**
	 * Runs command specified as one big String.
	 * 
	 * @param argsString
	 * @return
	 */
	public boolean run(String argsString) {
		String[] args = argsString.split(" ");

		return run(args);
	}

	/**
	 * Run a non-compare command line, which could be
	 * show/run/replay/verify/help/config.
	 * 
	 * @param commandLine
	 *            The command line to be run.
	 * @return the result of running the command line
	 * @throws CommandLineException
	 * @throws ABCException
	 * @throws IOException
	 * @throws MisguidedExecutionException
	 */
	public boolean runNormalCommand(NormalCommandLine commandLine)
			throws CommandLineException, ABCException, IOException,
			MisguidedExecutionException {
		if (commandLine.normalCommandKind() == NormalCommandKind.HELP)
			runHelp(commandLine);
		else if (commandLine.normalCommandKind() == NormalCommandKind.CONFIG)
			Configurations.makeConfigFile();
		else {
			NormalCommandKind kind = commandLine.normalCommandKind();
			GMCConfiguration gmcConfig = commandLine.gmcConfig();
			GMCSection gmcSection = gmcConfig.getAnonymousSection();
			File traceFile = null;

			if (kind == NormalCommandKind.REPLAY) {
				String traceFilename;

				traceFilename = (String) gmcConfig.getAnonymousSection()
						.getValue(traceO);
				if (traceFilename == null) {
					traceFilename = commandLine.getCoreFileName()
							+ "_"
							+ gmcConfig.getAnonymousSection()
									.getValueOrDefault(idO) + ".trace";
					traceFile = new File(CIVLConstants.CIVLREP, traceFilename);
				} else
					traceFile = new File(traceFilename);
				gmcConfig = parser.newConfig();
				parser.parse(gmcConfig, traceFile);
				gmcSection = gmcConfig.getAnonymousSection();
				setToDefault(gmcSection, Arrays.asList(showModelO, verboseO,
						debugO, showStatesO, showSavedStatesO, showQueriesO,
						showProverQueriesO, enablePrintfO, statelessPrintfO));
				gmcSection.setScalarValue(showTransitionsO, true);
				gmcSection.setScalarValue(collectScopesO, false);
				gmcSection.setScalarValue(collectProcessesO, false);
				gmcSection.setScalarValue(collectHeapsO, false);
				gmcSection.read(commandLine.gmcConfig().getAnonymousSection());
			}
			ModelTranslator modelTranslator = new ModelTranslator(
					transformerFactory, frontEnd, gmcConfig, gmcSection,
					commandLine.files(), commandLine.getCoreFileName(),
					commandLine.getCoreFile());

			if (commandLine.gmcSection().isTrue(echoO))
				out.println(commandLine.getCommandString());
			switch (kind) {
			case SHOW:
				return runShow(modelTranslator);
			case VERIFY:
				return runVerify(modelTranslator);
			case REPLAY:
				return runReplay(modelTranslator, traceFile);
			case RUN:
				return runRun(modelTranslator);
			default:
				throw new CIVLInternalException(
						"missing implementation for command of "
								+ commandLine.normalCommandKind() + " kind",
						(CIVLSource) null);
			}
		}
		return true;
	}

	/**
	 * Run a compare command, which is either compare verify or compare replay.
	 * 
	 * @param compareCommand
	 *            The compare command to be run
	 * @return the result of running the command
	 * @throws CommandLineException
	 * @throws ABCException
	 * @throws IOException
	 * @throws MisguidedExecutionException
	 */
	public boolean runCompareCommand(CompareCommandLine compareCommand)
			throws CommandLineException, ABCException, IOException,
			MisguidedExecutionException {
		GMCConfiguration gmcConfig = compareCommand.gmcConfig();
		GMCSection anonymousSection = gmcConfig.getAnonymousSection(), specSection = gmcConfig
				.getSection(CompareCommandLine.SPEC), implSection = gmcConfig
				.getSection(CompareCommandLine.IMPL);
		NormalCommandLine spec = compareCommand.specification(), impl = compareCommand
				.implementation();
		SymbolicUniverse universe = SARL.newStandardUniverse();
		File traceFile = null;

		if (compareCommand.isReplay()) {
			String traceFilename;

			traceFilename = (String) anonymousSection.getValue(traceO);
			if (traceFilename == null) {
				traceFilename = "Composite_" + spec.getCoreFileName() + "_"
						+ impl.getCoreFileName() + "_"
						+ anonymousSection.getValueOrDefault(idO) + ".trace";
				traceFile = new File(new File(CIVLConstants.CIVLREP),
						traceFilename);
			} else
				traceFile = new File(traceFilename);
			gmcConfig = parser.newConfig();
			parser.parse(gmcConfig, traceFile);
			anonymousSection = gmcConfig.getAnonymousSection();
			setToDefault(anonymousSection, Arrays.asList(showModelO, verboseO,
					debugO, showStatesO, showQueriesO, showProverQueriesO,
					enablePrintfO, statelessPrintfO));
			anonymousSection.setScalarValue(showTransitionsO, true);
			anonymousSection.setScalarValue(collectScopesO, false);
			anonymousSection.setScalarValue(collectProcessesO, false);
			anonymousSection.setScalarValue(collectHeapsO, false);
			anonymousSection.read(compareCommand.gmcConfig()
					.getAnonymousSection());
		}
		specSection = gmcConfig.getSection(CompareCommandLine.SPEC);
		implSection = gmcConfig.getSection(CompareCommandLine.IMPL);
		anonymousSection = this.readInputs(
				this.readInputs(anonymousSection, specSection), implSection);

		ModelTranslator specWorker = new ModelTranslator(transformerFactory,
				frontEnd, gmcConfig, specSection, spec.files(),
				spec.getCoreFileName(), spec.getCoreFile(), universe), implWorker = new ModelTranslator(
				transformerFactory, frontEnd, gmcConfig, implSection,
				impl.files(), impl.getCoreFileName(), impl.getCoreFile(),
				universe);
		Program specProgram, implProgram, compositeProgram;
		Combiner combiner = Transform.compareCombiner();
		Model model;
		ModelBuilder modelBuilder = Models.newModelBuilder(specWorker.universe);
		AST combinedAST;
		CIVLConfiguration civlConfig = new CIVLConfiguration(anonymousSection);

		if (anonymousSection.isTrue(echoO))
			out.println(compareCommand.getCommandString());
		universe.setShowQueries(anonymousSection.isTrue(showQueriesO));
		universe.setShowProverQueries(anonymousSection
				.isTrue(showProverQueriesO));
		specProgram = specWorker.buildProgram();
		implProgram = implWorker.buildProgram();
		if (civlConfig.debugOrVerbose())
			out.println("Generating composite program...");
		combinedAST = combiner.combine(specProgram.getAST(),
				implProgram.getAST());
		compositeProgram = frontEnd.getProgramFactory(
				frontEnd.getStandardAnalyzer(Language.CIVL_C)).newProgram(
				combinedAST);
		if (civlConfig.debugOrVerbose() || civlConfig.showProgram()) {
			compositeProgram.prettyPrint(out);
		}
		if (civlConfig.debugOrVerbose())
			out.println("Extracting CIVL model...");
		model = modelBuilder.buildModel(
				anonymousSection,
				compositeProgram,
				"Composite_" + spec.getCoreFileName() + "_"
						+ impl.getCoreFileName(), debug, out);
		if (civlConfig.debugOrVerbose() || civlConfig.showModel()) {
			out.println(bar + " Model " + bar + "\n");
			model.print(out, civlConfig.debugOrVerbose());
		}
		if (compareCommand.isReplay())
			return this.runCompareReplay(gmcConfig, traceFile, model, universe);
		if (civlConfig.web())
			this.createWebLogs(model.program());
		return this.runCompareVerify(compareCommand.gmcConfig(), model,
				specWorker.preprocessor, specWorker.universe);
	}

	/* ************************* Private Methods *************************** */

	/**
	 * Parses command line arguments and runs the CIVL tool(s) as specified by
	 * those arguments.
	 * 
	 * @param args
	 *            the command line arguments, e.g., {"verify", "-verbose",
	 *            "foo.c"}. This is an array of strings of length at least 1;
	 *            element 0 should be the name of the command
	 * @return true iff everything succeeded and no errors discovered
	 * @throws CommandLineException
	 *             if the args are not properly formatted commandline arguments
	 * @throws IOException
	 */
	private boolean runMain(String[] args) throws CommandLineException {
		this.startTime = System.currentTimeMillis();
		out.println("CIVL v" + version + " of " + date
				+ " -- http://vsl.cis.udel.edu/civl");
		out.flush();

		if (args == null || args.length < 1) {
			out.println("Incomplete command. Please type \'civl help\'"
					+ " for more instructions.");
			return false;
		} else {
			CommandLine commandLine = CIVLCommandFactory.parseCommand(
					definedOptions.values(), args);

			try {
				switch (commandLine.commandLineKind()) {
				case NORMAL:
					return runNormalCommand((NormalCommandLine) commandLine);
				case COMPARE:
					return runCompareCommand((CompareCommandLine) commandLine);
				default:
					throw new CIVLUnimplementedFeatureException("command of "
							+ commandLine.commandLineKind() + " kind");
				}
			} catch (ABCException e) {
				err.println(e);
			} catch (ABCRuntimeException e) {
				// not supposed to happen, so show the gory details...
				e.printStackTrace(err);
			} catch (IOException e) {
				err.println(e);
			} catch (MisguidedExecutionException e) {
				// this is almost definitely a bug, so throw it:
				throw new CIVLInternalException("Error in replay: "
						+ e.getMessage(), (CIVLSource) null);
			} catch (CIVLInternalException e) {
				// Something went wrong, report with full stack trace.
				throw e;
			} catch (CIVLException e) {
				err.println(e);
			}
			err.flush();
			return false;
		}
	}

	/**
	 * <p>
	 * Executes a "replay" command. This parses a trace file. The trace file
	 * contains all of the command line options that were used in the original
	 * verify run. These are parsed to form the new configuration object.
	 * </p>
	 * 
	 * <p>
	 * Some of these arguments are however ignored; they are set to their
	 * default values and then to new values if specified in the replay command.
	 * These options include things like showModel, verbose, etc. These are
	 * things that the user probably doesn't want to do the same way in the
	 * replay as she did in the verify. In contrast, arguments like input values
	 * have to be exactly the same in both commands.
	 * </p>
	 * 
	 * @param modelTranslator
	 * @return
	 * @throws CommandLineException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ABCException
	 * @throws MisguidedExecutionException
	 */
	private boolean runReplay(ModelTranslator modelTranslator, File traceFile)
			throws CommandLineException, FileNotFoundException, IOException,
			ABCException, MisguidedExecutionException {
		boolean result;
		Model model;
		TracePlayer replayer;
		boolean guiMode = modelTranslator.cmdSection.isTrue(guiO);
		Trace<Transition, State> trace;

		model = modelTranslator.translate();
		if (model != null) {
			replayer = TracePlayer.guidedPlayer(modelTranslator.gmcConfig,
					model, traceFile, out, err);
			trace = replayer.run();
			result = trace.result();
			if (guiMode) {
				@SuppressWarnings("unused")
				CIVL_GUI gui = new CIVL_GUI(trace, replayer.symbolicAnalyzer);
			}
			printStats(out, modelTranslator.universe);
			replayer.printStats();
			out.println();
			return result;
		}
		return false;
	}

	private boolean runRun(ModelTranslator modelTranslator)
			throws CommandLineException, ABCException, IOException,
			MisguidedExecutionException {
		boolean result;
		Model model;
		TracePlayer player;

		model = modelTranslator.translate();
		if (model != null) {
			player = TracePlayer.randomPlayer(modelTranslator.gmcConfig, model,
					out, err);
			out.println("\nRunning random simulation with seed "
					+ player.getSeed() + " ...");
			out.flush();
			result = player.run().result();
			printStats(out, modelTranslator.universe);
			player.printStats();
			out.println();
			return result;
		}
		return false;
	}

	private boolean runVerify(ModelTranslator modelTranslator)
			throws CommandLineException, ABCException, IOException {
		boolean result;
		Model model;
		Verifier verifier;

		if (modelTranslator.cmdSection.isTrue(showProverQueriesO))
			modelTranslator.universe.setShowProverQueries(true);
		if (modelTranslator.cmdSection.isTrue(showQueriesO))
			modelTranslator.universe.setShowQueries(true);
		model = modelTranslator.translate();
		if (modelTranslator.config.web())
			this.createWebLogs(model.program());
		if (model != null) {
			verifier = new Verifier(modelTranslator.gmcConfig, model, out, err,
					startTime);
			try {
				result = verifier.run();
			} catch (CIVLUnimplementedFeatureException unimplemented) {
				verifier.terminateUpdater();
				out.println();
				out.println("Error: " + unimplemented.toString());
				return false;
			} catch (CIVLSyntaxException syntax) {
				verifier.terminateUpdater();
				err.println(syntax);
				return false;
			} catch (Exception e) {
				verifier.terminateUpdater();
				throw e;
			}
			printStats(out, modelTranslator.universe);
			verifier.printStats();
			out.println();
			verifier.printResult();
			out.flush();
			return result;
		}
		return false;
	}

	private void createWebLogs(Program program) throws IOException {
		File file = new File(CIVLConstants.CIVLREP, "transformed.cvl");

		ensureRepositoryExists();
		if (file.exists())
			file.delete();
		file.createNewFile();

		FileOutputStream stream = new FileOutputStream(file);
		FileChannel channel = stream.getChannel();
		FileLock lock = channel.lock();
		PrintStream printStream = new PrintStream(stream);

		program.prettyPrint(printStream);
		printStream.flush();
		lock.release();
		printStream.close();
	}

	private void ensureRepositoryExists() throws IOException {
		File rep = new File(CIVLConstants.CIVLREP);

		if (rep.exists()) {
			if (!rep.isDirectory()) {
				rep.delete();
			}
		}
		if (!rep.exists()) {
			rep.mkdir();
		}
	}

	private boolean runCompareVerify(GMCConfiguration cmdConfig, Model model,
			Preprocessor preprocessor, SymbolicUniverse universe)
			throws CommandLineException, ABCException, IOException {
		Verifier verifier = new Verifier(cmdConfig, model, out, err, startTime);
		boolean result = false;

		try {
			result = verifier.run();
		} catch (CIVLUnimplementedFeatureException unimplemented) {
			verifier.terminateUpdater();
			out.println();
			out.println("Error: " + unimplemented.toString());
			return false;
		} catch (Exception e) {
			verifier.terminateUpdater();
			throw e;
		}
		printStats(out, universe);
		verifier.printStats();
		out.println();
		verifier.printResult();
		out.flush();
		return result;
	}

	private boolean runCompareReplay(GMCConfiguration gmcConfig,
			File traceFile, Model model, SymbolicUniverse universe)
			throws CommandLineException, FileNotFoundException, IOException,
			SyntaxException, PreprocessorException, ParseException,
			MisguidedExecutionException {
		boolean guiMode = gmcConfig.getAnonymousSection().isTrue(guiO);
		TracePlayer replayer;
		Trace<Transition, State> trace;
		boolean result;

		replayer = TracePlayer.guidedPlayer(gmcConfig, model, traceFile, out,
				err);
		trace = replayer.run();
		result = trace.result();
		if (guiMode) {
			@SuppressWarnings("unused")
			CIVL_GUI gui = new CIVL_GUI(trace, replayer.symbolicAnalyzer);
		}
		printStats(out, universe);
		replayer.printStats();
		out.println();
		return result;
	}

	private GMCSection readInputs(GMCSection lhs, GMCSection rhs) {
		GMCSection result = lhs.clone();
		Map<String, Object> inputs = rhs.getMapValue(CIVLConstants.inputO);

		if (inputs != null)
			for (Map.Entry<String, Object> entry : inputs.entrySet()) {
				result.putMapEntry(CIVLConstants.inputO, entry.getKey(),
						entry.getValue());
			}
		return result;
	}

	private boolean runShow(ModelTranslator modelTranslator)
			throws PreprocessorException {
		return modelTranslator.translate() != null;
	}

	private void runHelp(CommandLine command) {
		CommandKind arg = command.commandArg();

		if (arg == null)
			printUsage(out);
		else {
			out.println();
			switch (arg) {
			case COMPARE:
				out.println("COMPARE the functional equivalence of two programs.");
				out.println("\nUsage: civl compare [common options] -spec [spec options] "
						+ "filename+ -impl [impl options] filename+");
				out.println("\nOptions:");
				break;
			case GUI:
				out.println("Run the graphical interface of CIVL.");
				out.println("\nUsage: civl gui");
				break;
			case HELP:
				out.println("Prints the HELP information of CIVL");
				out.println("\nUsage: civl help [command]");
				out.println("command can be any of the following: "
						+ "compare, gui, help, replay, run, show and verify.");
				break;
			case REPLAY:
				out.println("REPLAY the counterexample trace of some verification result.");
				out.println("\nUsage: civl replay [options] filename+");
				out.println("    or civl replay [common options] -spec [spec options] "
						+ "filename+ -impl [impl options] filename+");
				out.println("the latter replays the counterexample of some comparison result.");
				out.println("\nOptions:");
				break;
			case RUN:
				out.println("RUN a program randomly.");
				out.println("\nUsage: civl run [options] filename+");
				out.println("\nOptions:");
				break;
			case SHOW:
				out.println("SHOW the preprocessing, parsing and translating result of a program.");
				out.println("\nUsage: civl show [options] filename+");
				out.println("\nOptions:");
				break;
			case CONFIG:
				out.println("Configure CIVL.  Detect theorem provers and create .sarl.");
				out.println("\nUsage: civl config");
				break;
			case VERIFY:
				out.println("VERIFY a certain program.");
				out.println("\nUsage: civl verify [options] filename+");
				out.println("\nOptions:");
				break;
			default:
				throw new CIVLInternalException(
						"missing implementation for command of " + arg
								+ " kind", (CIVLSource) null);
			}
			CIVLCommand.printOptionsOfCommand(arg, out);
		}
	}

	/**
	 * Prints usage information to the given stream and flushes the stream.
	 * 
	 * @param out
	 *            stream to which to print
	 */
	private void printUsage(PrintStream out) {
		out.println("Usage: civl (replay|run|show|verify) [options] filename+");
		out.println("    or civl (compare|replay) [common options] -spec [spec options]");
		out.println("       filename+ -impl [impl options] filename+");
		out.println("    or civl config");
		out.println("    or civl gui");
		out.println("    or civl help [command]");
		out.println("Semantics:");
		out.println("  config : configure CIVL");
		out.println("  replay : replay trace for program filename");
		out.println("  run    : run program filename");
		out.println("  help   : print this message");
		out.println("  show   : show result of preprocessing and parsing filename(s)");
		out.println("  verify : verify program filename");
		out.println("  gui    : launch civl in gui mode (beta)");
		out.println("Options:");
		for (Option option : definedOptions.values()) {
			option.print(out);
		}
		out.println("Type \'civl help command\' for usage and options");
		out.println("for a particular command, e.g., \'civl help compare\'");
		out.flush();
	}

	/* ************************* Private Methods *************************** */

	/**
	 * Prints statistics after a run. The end time is marked and compared to the
	 * start time to compute total elapsed time. Other statistics are taken from
	 * the symbolic universe created in this class. The remaining statistics are
	 * provided as parameters to this method.
	 * 
	 * @param out
	 *            the stream to which to print
	 * @param maxProcs
	 *            the maximum number of processes that existed in any state
	 *            encountered
	 * @param statesSeen
	 *            the number of states seen in the run
	 * @param statesMatched
	 *            the number of states encountered which were determined to have
	 *            been seen before
	 * @param transitions
	 *            the number of transitions executed in the course of the run
	 */
	private void printStats(PrintStream out, SymbolicUniverse universe) {
		// round up time to nearest 1/100th of second...
		double time = Math
				.ceil((System.currentTimeMillis() - startTime) / 10.0) / 100.0;
		long numValidCalls = universe.numValidCalls();
		long numProverCalls = universe.numProverValidCalls();
		long memory = Runtime.getRuntime().totalMemory();

		out.println("\n" + bar + " Stats " + bar);
		out.print("   validCalls          : ");
		out.println(numValidCalls);
		out.print("   proverCalls         : ");
		out.println(numProverCalls);
		out.print("   memory (bytes)      : ");
		out.println(memory);
		out.print("   time (s)            : ");
		out.println(time);
	}

	private void setToDefault(GMCSection config, Collection<Option> options) {
		for (Option option : options)
			setToDefault(config, option);
	}

	private void setToDefault(GMCSection config, Option option) {
		config.setScalarValue(option, option.defaultValue());
	}
}
